
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 普通老师
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/putonglaoshi")
public class PutonglaoshiController {
    private static final Logger logger = LoggerFactory.getLogger(PutonglaoshiController.class);

    @Autowired
    private PutonglaoshiService putonglaoshiService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private DudaolaoshiService dudaolaoshiService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("普通老师".equals(role))
            params.put("putonglaoshiId",request.getSession().getAttribute("userId"));
        else if("督导老师".equals(role))
            params.put("dudaolaoshiId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","laoshi_pingfen");
        }
        PageUtils page = putonglaoshiService.queryPage(params);

        //字典表数据转换
        List<PutonglaoshiView> list =(List<PutonglaoshiView>)page.getList();
        for(PutonglaoshiView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        PutonglaoshiEntity putonglaoshi = putonglaoshiService.selectById(id);
        if(putonglaoshi !=null){
            //entity转view
            PutonglaoshiView view = new PutonglaoshiView();
            BeanUtils.copyProperties( putonglaoshi , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody PutonglaoshiEntity putonglaoshi, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,putonglaoshi:{}",this.getClass().getName(),putonglaoshi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<PutonglaoshiEntity> queryWrapper = new EntityWrapper<PutonglaoshiEntity>()
            .eq("username", putonglaoshi.getUsername())
            .or()
            .eq("putonglaoshi_phone", putonglaoshi.getPutonglaoshiPhone())
            .or()
            .eq("putonglaoshi_id_number", putonglaoshi.getPutonglaoshiIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        PutonglaoshiEntity putonglaoshiEntity = putonglaoshiService.selectOne(queryWrapper);
        if(putonglaoshiEntity==null){
            putonglaoshi.setCreateTime(new Date());
            putonglaoshi.setPassword("123456");
            putonglaoshiService.insert(putonglaoshi);
            return R.ok();
        }else {
            return R.error(511,"账户或者普通老师手机号或者普通老师身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody PutonglaoshiEntity putonglaoshi, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,putonglaoshi:{}",this.getClass().getName(),putonglaoshi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<PutonglaoshiEntity> queryWrapper = new EntityWrapper<PutonglaoshiEntity>()
            .notIn("id",putonglaoshi.getId())
            .andNew()
            .eq("username", putonglaoshi.getUsername())
            .or()
            .eq("putonglaoshi_phone", putonglaoshi.getPutonglaoshiPhone())
            .or()
            .eq("putonglaoshi_id_number", putonglaoshi.getPutonglaoshiIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        PutonglaoshiEntity putonglaoshiEntity = putonglaoshiService.selectOne(queryWrapper);
        if("".equals(putonglaoshi.getPutonglaoshiPhoto()) || "null".equals(putonglaoshi.getPutonglaoshiPhoto())){
                putonglaoshi.setPutonglaoshiPhoto(null);
        }
        if(putonglaoshiEntity==null){
            putonglaoshiService.updateById(putonglaoshi);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户或者普通老师手机号或者普通老师身份证号已经被使用");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        putonglaoshiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<PutonglaoshiEntity> putonglaoshiList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("../../upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            PutonglaoshiEntity putonglaoshiEntity = new PutonglaoshiEntity();
//                            putonglaoshiEntity.setUsername(data.get(0));                    //账户 要改的
//                            //putonglaoshiEntity.setPassword("123456");//密码
//                            putonglaoshiEntity.setPutonglaoshiUuidNumber(data.get(0));                    //普通老师工号 要改的
//                            putonglaoshiEntity.setPutonglaoshiName(data.get(0));                    //普通老师姓名 要改的
//                            putonglaoshiEntity.setPutonglaoshiPhone(data.get(0));                    //普通老师手机号 要改的
//                            putonglaoshiEntity.setPutonglaoshiIdNumber(data.get(0));                    //普通老师身份证号 要改的
//                            putonglaoshiEntity.setPutonglaoshiPhoto("");//详情和图片
//                            putonglaoshiEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            putonglaoshiEntity.setKemuTypes(Integer.valueOf(data.get(0)));   //所教科目 要改的
//                            putonglaoshiEntity.setLaoshiPingfen(Integer.valueOf(data.get(0)));   //老师评分 要改的
//                            putonglaoshiEntity.setPutonglaoshiEmail(data.get(0));                    //普通老师电子邮箱 要改的
//                            putonglaoshiEntity.setCreateTime(date);//时间
                            putonglaoshiList.add(putonglaoshiEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //普通老师工号
                                if(seachFields.containsKey("putonglaoshiUuidNumber")){
                                    List<String> putonglaoshiUuidNumber = seachFields.get("putonglaoshiUuidNumber");
                                    putonglaoshiUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> putonglaoshiUuidNumber = new ArrayList<>();
                                    putonglaoshiUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("putonglaoshiUuidNumber",putonglaoshiUuidNumber);
                                }
                                //普通老师手机号
                                if(seachFields.containsKey("putonglaoshiPhone")){
                                    List<String> putonglaoshiPhone = seachFields.get("putonglaoshiPhone");
                                    putonglaoshiPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> putonglaoshiPhone = new ArrayList<>();
                                    putonglaoshiPhone.add(data.get(0));//要改的
                                    seachFields.put("putonglaoshiPhone",putonglaoshiPhone);
                                }
                                //普通老师身份证号
                                if(seachFields.containsKey("putonglaoshiIdNumber")){
                                    List<String> putonglaoshiIdNumber = seachFields.get("putonglaoshiIdNumber");
                                    putonglaoshiIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> putonglaoshiIdNumber = new ArrayList<>();
                                    putonglaoshiIdNumber.add(data.get(0));//要改的
                                    seachFields.put("putonglaoshiIdNumber",putonglaoshiIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<PutonglaoshiEntity> putonglaoshiEntities_username = putonglaoshiService.selectList(new EntityWrapper<PutonglaoshiEntity>().in("username", seachFields.get("username")));
                        if(putonglaoshiEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(PutonglaoshiEntity s:putonglaoshiEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //普通老师工号
                        List<PutonglaoshiEntity> putonglaoshiEntities_putonglaoshiUuidNumber = putonglaoshiService.selectList(new EntityWrapper<PutonglaoshiEntity>().in("putonglaoshi_uuid_number", seachFields.get("putonglaoshiUuidNumber")));
                        if(putonglaoshiEntities_putonglaoshiUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(PutonglaoshiEntity s:putonglaoshiEntities_putonglaoshiUuidNumber){
                                repeatFields.add(s.getPutonglaoshiUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [普通老师工号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //普通老师手机号
                        List<PutonglaoshiEntity> putonglaoshiEntities_putonglaoshiPhone = putonglaoshiService.selectList(new EntityWrapper<PutonglaoshiEntity>().in("putonglaoshi_phone", seachFields.get("putonglaoshiPhone")));
                        if(putonglaoshiEntities_putonglaoshiPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(PutonglaoshiEntity s:putonglaoshiEntities_putonglaoshiPhone){
                                repeatFields.add(s.getPutonglaoshiPhone());
                            }
                            return R.error(511,"数据库的该表中的 [普通老师手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //普通老师身份证号
                        List<PutonglaoshiEntity> putonglaoshiEntities_putonglaoshiIdNumber = putonglaoshiService.selectList(new EntityWrapper<PutonglaoshiEntity>().in("putonglaoshi_id_number", seachFields.get("putonglaoshiIdNumber")));
                        if(putonglaoshiEntities_putonglaoshiIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(PutonglaoshiEntity s:putonglaoshiEntities_putonglaoshiIdNumber){
                                repeatFields.add(s.getPutonglaoshiIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [普通老师身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        putonglaoshiService.insertBatch(putonglaoshiList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }


    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        PutonglaoshiEntity putonglaoshi = putonglaoshiService.selectOne(new EntityWrapper<PutonglaoshiEntity>().eq("username", username));
        if(putonglaoshi==null || !putonglaoshi.getPassword().equals(password))
            return R.error("账号或密码不正确");
        //  // 获取监听器中的字典表
        // ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        // Map<String, Map<Integer, String>> dictionaryMap= (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        // Map<Integer, String> role_types = dictionaryMap.get("role_types");
        // role_types.get(.getRoleTypes());
        String token = tokenService.generateToken(putonglaoshi.getId(),username, "putonglaoshi", "普通老师");
        R r = R.ok();
        r.put("token", token);
        r.put("role","普通老师");
        r.put("username",putonglaoshi.getPutonglaoshiName());
        r.put("tableName","putonglaoshi");
        r.put("userId",putonglaoshi.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody PutonglaoshiEntity putonglaoshi){
//    	ValidatorUtils.validateEntity(user);
        Wrapper<PutonglaoshiEntity> queryWrapper = new EntityWrapper<PutonglaoshiEntity>()
            .eq("username", putonglaoshi.getUsername())
            .or()
            .eq("putonglaoshi_phone", putonglaoshi.getPutonglaoshiPhone())
            .or()
            .eq("putonglaoshi_id_number", putonglaoshi.getPutonglaoshiIdNumber())
            ;
        PutonglaoshiEntity putonglaoshiEntity = putonglaoshiService.selectOne(queryWrapper);
        if(putonglaoshiEntity != null)
            return R.error("账户或者普通老师手机号或者普通老师身份证号已经被使用");
        putonglaoshi.setCreateTime(new Date());
        putonglaoshiService.insert(putonglaoshi);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        PutonglaoshiEntity putonglaoshi = new PutonglaoshiEntity();
        putonglaoshi.setPassword("123456");
        putonglaoshi.setId(id);
        putonglaoshiService.updateById(putonglaoshi);
        return R.ok();
    }


    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        PutonglaoshiEntity putonglaoshi = putonglaoshiService.selectOne(new EntityWrapper<PutonglaoshiEntity>().eq("username", username));
        if(putonglaoshi!=null){
            putonglaoshi.setPassword("123456");
            boolean b = putonglaoshiService.updateById(putonglaoshi);
            if(!b){
               return R.error();
            }
        }else{
           return R.error("账号不存在");
        }
        return R.ok();
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrPutonglaoshi(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        PutonglaoshiEntity putonglaoshi = putonglaoshiService.selectById(id);
        if(putonglaoshi !=null){
            //entity转view
            PutonglaoshiView view = new PutonglaoshiView();
            BeanUtils.copyProperties( putonglaoshi , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }





}
