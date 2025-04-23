
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
 * 督导老师
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/dudaolaoshi")
public class DudaolaoshiController {
    private static final Logger logger = LoggerFactory.getLogger(DudaolaoshiController.class);

    @Autowired
    private DudaolaoshiService dudaolaoshiService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private PutonglaoshiService putonglaoshiService;


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
            params.put("orderBy","id");
        }
        PageUtils page = dudaolaoshiService.queryPage(params);

        //字典表数据转换
        List<DudaolaoshiView> list =(List<DudaolaoshiView>)page.getList();
        for(DudaolaoshiView c:list){
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
        DudaolaoshiEntity dudaolaoshi = dudaolaoshiService.selectById(id);
        if(dudaolaoshi !=null){
            //entity转view
            DudaolaoshiView view = new DudaolaoshiView();
            BeanUtils.copyProperties( dudaolaoshi , view );//把实体数据重构到view中

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
    public R save(@RequestBody DudaolaoshiEntity dudaolaoshi, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,dudaolaoshi:{}",this.getClass().getName(),dudaolaoshi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<DudaolaoshiEntity> queryWrapper = new EntityWrapper<DudaolaoshiEntity>()
            .eq("username", dudaolaoshi.getUsername())
            .or()
            .eq("dudaolaoshi_phone", dudaolaoshi.getDudaolaoshiPhone())
            .or()
            .eq("dudaolaoshi_id_number", dudaolaoshi.getDudaolaoshiIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DudaolaoshiEntity dudaolaoshiEntity = dudaolaoshiService.selectOne(queryWrapper);
        if(dudaolaoshiEntity==null){
            dudaolaoshi.setCreateTime(new Date());
            dudaolaoshi.setPassword("123456");
            dudaolaoshiService.insert(dudaolaoshi);
            return R.ok();
        }else {
            return R.error(511,"账户或者督导老师手机号或者督导老师身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody DudaolaoshiEntity dudaolaoshi, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,dudaolaoshi:{}",this.getClass().getName(),dudaolaoshi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<DudaolaoshiEntity> queryWrapper = new EntityWrapper<DudaolaoshiEntity>()
            .notIn("id",dudaolaoshi.getId())
            .andNew()
            .eq("username", dudaolaoshi.getUsername())
            .or()
            .eq("dudaolaoshi_phone", dudaolaoshi.getDudaolaoshiPhone())
            .or()
            .eq("dudaolaoshi_id_number", dudaolaoshi.getDudaolaoshiIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DudaolaoshiEntity dudaolaoshiEntity = dudaolaoshiService.selectOne(queryWrapper);
        if("".equals(dudaolaoshi.getDudaolaoshiPhoto()) || "null".equals(dudaolaoshi.getDudaolaoshiPhoto())){
                dudaolaoshi.setDudaolaoshiPhoto(null);
        }
        if(dudaolaoshiEntity==null){
            dudaolaoshiService.updateById(dudaolaoshi);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户或者督导老师手机号或者督导老师身份证号已经被使用");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        dudaolaoshiService.deleteBatchIds(Arrays.asList(ids));
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
            List<DudaolaoshiEntity> dudaolaoshiList = new ArrayList<>();//上传的东西
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
                            DudaolaoshiEntity dudaolaoshiEntity = new DudaolaoshiEntity();
//                            dudaolaoshiEntity.setUsername(data.get(0));                    //账户 要改的
//                            //dudaolaoshiEntity.setPassword("123456");//密码
//                            dudaolaoshiEntity.setDudaolaoshiUuidNumber(data.get(0));                    //督导老师工号 要改的
//                            dudaolaoshiEntity.setDudaolaoshiName(data.get(0));                    //督导老师姓名 要改的
//                            dudaolaoshiEntity.setDudaolaoshiPhone(data.get(0));                    //督导老师手机号 要改的
//                            dudaolaoshiEntity.setDudaolaoshiIdNumber(data.get(0));                    //督导老师身份证号 要改的
//                            dudaolaoshiEntity.setDudaolaoshiPhoto("");//详情和图片
//                            dudaolaoshiEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            dudaolaoshiEntity.setDudaolaoshiEmail(data.get(0));                    //督导老师电子邮箱 要改的
//                            dudaolaoshiEntity.setCreateTime(date);//时间
                            dudaolaoshiList.add(dudaolaoshiEntity);


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
                                //督导老师工号
                                if(seachFields.containsKey("dudaolaoshiUuidNumber")){
                                    List<String> dudaolaoshiUuidNumber = seachFields.get("dudaolaoshiUuidNumber");
                                    dudaolaoshiUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> dudaolaoshiUuidNumber = new ArrayList<>();
                                    dudaolaoshiUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("dudaolaoshiUuidNumber",dudaolaoshiUuidNumber);
                                }
                                //督导老师手机号
                                if(seachFields.containsKey("dudaolaoshiPhone")){
                                    List<String> dudaolaoshiPhone = seachFields.get("dudaolaoshiPhone");
                                    dudaolaoshiPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> dudaolaoshiPhone = new ArrayList<>();
                                    dudaolaoshiPhone.add(data.get(0));//要改的
                                    seachFields.put("dudaolaoshiPhone",dudaolaoshiPhone);
                                }
                                //督导老师身份证号
                                if(seachFields.containsKey("dudaolaoshiIdNumber")){
                                    List<String> dudaolaoshiIdNumber = seachFields.get("dudaolaoshiIdNumber");
                                    dudaolaoshiIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> dudaolaoshiIdNumber = new ArrayList<>();
                                    dudaolaoshiIdNumber.add(data.get(0));//要改的
                                    seachFields.put("dudaolaoshiIdNumber",dudaolaoshiIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<DudaolaoshiEntity> dudaolaoshiEntities_username = dudaolaoshiService.selectList(new EntityWrapper<DudaolaoshiEntity>().in("username", seachFields.get("username")));
                        if(dudaolaoshiEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(DudaolaoshiEntity s:dudaolaoshiEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //督导老师工号
                        List<DudaolaoshiEntity> dudaolaoshiEntities_dudaolaoshiUuidNumber = dudaolaoshiService.selectList(new EntityWrapper<DudaolaoshiEntity>().in("dudaolaoshi_uuid_number", seachFields.get("dudaolaoshiUuidNumber")));
                        if(dudaolaoshiEntities_dudaolaoshiUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(DudaolaoshiEntity s:dudaolaoshiEntities_dudaolaoshiUuidNumber){
                                repeatFields.add(s.getDudaolaoshiUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [督导老师工号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //督导老师手机号
                        List<DudaolaoshiEntity> dudaolaoshiEntities_dudaolaoshiPhone = dudaolaoshiService.selectList(new EntityWrapper<DudaolaoshiEntity>().in("dudaolaoshi_phone", seachFields.get("dudaolaoshiPhone")));
                        if(dudaolaoshiEntities_dudaolaoshiPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(DudaolaoshiEntity s:dudaolaoshiEntities_dudaolaoshiPhone){
                                repeatFields.add(s.getDudaolaoshiPhone());
                            }
                            return R.error(511,"数据库的该表中的 [督导老师手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //督导老师身份证号
                        List<DudaolaoshiEntity> dudaolaoshiEntities_dudaolaoshiIdNumber = dudaolaoshiService.selectList(new EntityWrapper<DudaolaoshiEntity>().in("dudaolaoshi_id_number", seachFields.get("dudaolaoshiIdNumber")));
                        if(dudaolaoshiEntities_dudaolaoshiIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(DudaolaoshiEntity s:dudaolaoshiEntities_dudaolaoshiIdNumber){
                                repeatFields.add(s.getDudaolaoshiIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [督导老师身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        dudaolaoshiService.insertBatch(dudaolaoshiList);
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
        DudaolaoshiEntity dudaolaoshi = dudaolaoshiService.selectOne(new EntityWrapper<DudaolaoshiEntity>().eq("username", username));
        if(dudaolaoshi==null || !dudaolaoshi.getPassword().equals(password))
            return R.error("账号或密码不正确");
        //  // 获取监听器中的字典表
        // ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        // Map<String, Map<Integer, String>> dictionaryMap= (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        // Map<Integer, String> role_types = dictionaryMap.get("role_types");
        // role_types.get(.getRoleTypes());
        String token = tokenService.generateToken(dudaolaoshi.getId(),username, "dudaolaoshi", "督导老师");
        R r = R.ok();
        r.put("token", token);
        r.put("role","督导老师");
        r.put("username",dudaolaoshi.getDudaolaoshiName());
        r.put("tableName","dudaolaoshi");
        r.put("userId",dudaolaoshi.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody DudaolaoshiEntity dudaolaoshi){
//    	ValidatorUtils.validateEntity(user);
        Wrapper<DudaolaoshiEntity> queryWrapper = new EntityWrapper<DudaolaoshiEntity>()
            .eq("username", dudaolaoshi.getUsername())
            .or()
            .eq("dudaolaoshi_phone", dudaolaoshi.getDudaolaoshiPhone())
            .or()
            .eq("dudaolaoshi_id_number", dudaolaoshi.getDudaolaoshiIdNumber())
            ;
        DudaolaoshiEntity dudaolaoshiEntity = dudaolaoshiService.selectOne(queryWrapper);
        if(dudaolaoshiEntity != null)
            return R.error("账户或者督导老师手机号或者督导老师身份证号已经被使用");
        dudaolaoshi.setCreateTime(new Date());
        dudaolaoshiService.insert(dudaolaoshi);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        DudaolaoshiEntity dudaolaoshi = new DudaolaoshiEntity();
        dudaolaoshi.setPassword("123456");
        dudaolaoshi.setId(id);
        dudaolaoshiService.updateById(dudaolaoshi);
        return R.ok();
    }


    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        DudaolaoshiEntity dudaolaoshi = dudaolaoshiService.selectOne(new EntityWrapper<DudaolaoshiEntity>().eq("username", username));
        if(dudaolaoshi!=null){
            dudaolaoshi.setPassword("123456");
            boolean b = dudaolaoshiService.updateById(dudaolaoshi);
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
    public R getCurrDudaolaoshi(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        DudaolaoshiEntity dudaolaoshi = dudaolaoshiService.selectById(id);
        if(dudaolaoshi !=null){
            //entity转view
            DudaolaoshiView view = new DudaolaoshiView();
            BeanUtils.copyProperties( dudaolaoshi , view );//把实体数据重构到view中

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
