
package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.GongkaikeEntity;
import com.entity.PutonglaoshiEntity;
import com.entity.view.GongkaikeView;
import com.service.*;
import com.utils.PageUtils;
import com.utils.PoiUtil;
import com.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 公开课
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/gongkaike")
public class GongkaikeController {
    private static final Logger logger = LoggerFactory.getLogger(GongkaikeController.class);

    @Autowired
    private GongkaikeService gongkaikeService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private PutonglaoshiService putonglaoshiService;

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
            params.put("orderBy","gongkaike_pingfen");
        }
        PageUtils page = gongkaikeService.queryPage(params);

        //字典表数据转换
        List<GongkaikeView> list =(List<GongkaikeView>)page.getList();
        for(GongkaikeView c:list){
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
        GongkaikeEntity gongkaike = gongkaikeService.selectById(id);
        if(gongkaike !=null){
            //entity转view
            GongkaikeView view = new GongkaikeView();
            BeanUtils.copyProperties( gongkaike , view );//把实体数据重构到view中

                //级联表
                PutonglaoshiEntity putonglaoshi = putonglaoshiService.selectById(gongkaike.getPutonglaoshiId());
                if(putonglaoshi != null){
                    BeanUtils.copyProperties( putonglaoshi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setPutonglaoshiId(putonglaoshi.getId());
                }
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
    public R save(@RequestBody GongkaikeEntity gongkaike, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,gongkaike:{}",this.getClass().getName(),gongkaike.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("普通老师".equals(role)){
            gongkaike.setPutonglaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
            gongkaike.setGongkaikePingfen(0.0);
        }

        Wrapper<GongkaikeEntity> queryWrapper = new EntityWrapper<GongkaikeEntity>()
            .eq("putonglaoshi_id", gongkaike.getPutonglaoshiId())
            .eq("gongkaike_uuid_number", gongkaike.getGongkaikeUuidNumber())
            .eq("gongkaike_name", gongkaike.getGongkaikeName())
            .eq("gongkaike_types", gongkaike.getGongkaikeTypes())
            .eq("banji_types", gongkaike.getBanjiTypes())
            .eq("gongkaike_shichang", gongkaike.getGongkaikeShichang())
            .eq("gongkaike_address", gongkaike.getGongkaikeAddress())
            .eq("gongkaike_number", gongkaike.getGongkaikeNumber())
            .eq("gongkaike_pingfen", gongkaike.getGongkaikePingfen())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        GongkaikeEntity gongkaikeEntity = gongkaikeService.selectOne(queryWrapper);
        if(gongkaikeEntity==null){
            gongkaike.setInsertTime(new Date());
            gongkaike.setCreateTime(new Date());
            gongkaikeService.insert(gongkaike);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody GongkaikeEntity gongkaike, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,gongkaike:{}",this.getClass().getName(),gongkaike.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("普通老师".equals(role))
//            gongkaike.setPutonglaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<GongkaikeEntity> queryWrapper = new EntityWrapper<GongkaikeEntity>()
            .notIn("id",gongkaike.getId())
            .andNew()
            .eq("putonglaoshi_id", gongkaike.getPutonglaoshiId())
            .eq("gongkaike_uuid_number", gongkaike.getGongkaikeUuidNumber())
            .eq("gongkaike_name", gongkaike.getGongkaikeName())
            .eq("gongkaike_types", gongkaike.getGongkaikeTypes())
            .eq("banji_types", gongkaike.getBanjiTypes())
            .eq("gongkaike_shichang", gongkaike.getGongkaikeShichang())
            .eq("gongkaike_address", gongkaike.getGongkaikeAddress())
            .eq("gongkaike_number", gongkaike.getGongkaikeNumber())
            .eq("gongkaike_pingfen", gongkaike.getGongkaikePingfen())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        GongkaikeEntity gongkaikeEntity = gongkaikeService.selectOne(queryWrapper);
        if(gongkaikeEntity==null){
            gongkaikeService.updateById(gongkaike);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        gongkaikeService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save(String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<GongkaikeEntity> gongkaikeList = new ArrayList<>();//上传的东西
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
                            GongkaikeEntity gongkaikeEntity = new GongkaikeEntity();
//                            gongkaikeEntity.setPutonglaoshiId(Integer.valueOf(data.get(0)));   //普通老师 要改的
//                            gongkaikeEntity.setGongkaikeUuidNumber(data.get(0));                    //公开课编号 要改的
//                            gongkaikeEntity.setGongkaikeName(data.get(0));                    //公开课名称 要改的
//                            gongkaikeEntity.setGongkaikeTypes(Integer.valueOf(data.get(0)));   //公开课类型 要改的
//                            gongkaikeEntity.setBanjiTypes(Integer.valueOf(data.get(0)));   //授课班级 要改的
//                            gongkaikeEntity.setGongkaikeContent("");//详情和图片
//                            gongkaikeEntity.setGongkaikeShichang(data.get(0));                    //公开课预估时长 要改的
//                            gongkaikeEntity.setGongkaikeAddress(data.get(0));                    //公开课开课位置 要改的
//                            gongkaikeEntity.setGongkaikeNumber(Integer.valueOf(data.get(0)));   //最大听课人数 要改的
//                            gongkaikeEntity.setGongkaikePingfen(Integer.valueOf(data.get(0)));   //公开课评分 要改的
//                            gongkaikeEntity.setKaikeTime(sdf.parse(data.get(0)));          //开课时间 要改的
//                            gongkaikeEntity.setInsertTime(date);//时间
//                            gongkaikeEntity.setCreateTime(date);//时间
                            gongkaikeList.add(gongkaikeEntity);


                            //把要查询是否重复的字段放入map中
                                //公开课编号
                                if(seachFields.containsKey("gongkaikeUuidNumber")){
                                    List<String> gongkaikeUuidNumber = seachFields.get("gongkaikeUuidNumber");
                                    gongkaikeUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> gongkaikeUuidNumber = new ArrayList<>();
                                    gongkaikeUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("gongkaikeUuidNumber",gongkaikeUuidNumber);
                                }
                        }

                        //查询是否重复
                         //公开课编号
                        List<GongkaikeEntity> gongkaikeEntities_gongkaikeUuidNumber = gongkaikeService.selectList(new EntityWrapper<GongkaikeEntity>().in("gongkaike_uuid_number", seachFields.get("gongkaikeUuidNumber")));
                        if(gongkaikeEntities_gongkaikeUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(GongkaikeEntity s:gongkaikeEntities_gongkaikeUuidNumber){
                                repeatFields.add(s.getGongkaikeUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [公开课编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        gongkaikeService.insertBatch(gongkaikeList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}
