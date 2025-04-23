
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
 * 公开课听课
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/gongkaikeTingke")
public class GongkaikeTingkeController {
    private static final Logger logger = LoggerFactory.getLogger(GongkaikeTingkeController.class);

    @Autowired
    private GongkaikeTingkeService gongkaikeTingkeService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private GongkaikeService gongkaikeService;
    @Autowired
    private DudaolaoshiService dudaolaoshiService;

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
        PageUtils page = gongkaikeTingkeService.queryPage(params);

        //字典表数据转换
        List<GongkaikeTingkeView> list =(List<GongkaikeTingkeView>)page.getList();
        for(GongkaikeTingkeView c:list){
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
        GongkaikeTingkeEntity gongkaikeTingke = gongkaikeTingkeService.selectById(id);
        if(gongkaikeTingke !=null){
            //entity转view
            GongkaikeTingkeView view = new GongkaikeTingkeView();
            BeanUtils.copyProperties( gongkaikeTingke , view );//把实体数据重构到view中

                //级联表
                GongkaikeEntity gongkaike = gongkaikeService.selectById(gongkaikeTingke.getGongkaikeId());
                if(gongkaike != null){
                    BeanUtils.copyProperties( gongkaike , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setGongkaikeId(gongkaike.getId());
                }
                //级联表
                DudaolaoshiEntity dudaolaoshi = dudaolaoshiService.selectById(gongkaikeTingke.getDudaolaoshiId());
                if(dudaolaoshi != null){
                    BeanUtils.copyProperties( dudaolaoshi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setDudaolaoshiId(dudaolaoshi.getId());
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
    public R save(@RequestBody GongkaikeTingkeEntity gongkaikeTingke, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,gongkaikeTingke:{}",this.getClass().getName(),gongkaikeTingke.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("督导老师".equals(role)){
            gongkaikeTingke.setDudaolaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
            gongkaikeTingke.setGongkaikeTingkeUuidNumber(String.valueOf(new Date().getTime()));
        }

        Wrapper<GongkaikeTingkeEntity> queryWrapper = new EntityWrapper<GongkaikeTingkeEntity>()
            .eq("dudaolaoshi_id", gongkaikeTingke.getDudaolaoshiId())
            .eq("gongkaike_id", gongkaikeTingke.getGongkaikeId())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        GongkaikeTingkeEntity gongkaikeTingkeEntity = gongkaikeTingkeService.selectOne(queryWrapper);
        if(gongkaikeTingkeEntity==null){

            GongkaikeEntity gongkaikeEntity = gongkaikeService.selectById(gongkaikeTingke.getGongkaikeId());
            if(gongkaikeEntity == null)
                return R.error("查不到公开课");
            //查看是否到最大听课人数
            List<GongkaikeTingkeEntity> gongkaikeTingkeEntities = gongkaikeTingkeService.selectList(new EntityWrapper<GongkaikeTingkeEntity>()
                    .eq("gongkaike_id", gongkaikeTingke.getGongkaikeId())
            );
            if(gongkaikeEntity.getGongkaikeNumber().intValue() <=gongkaikeTingkeEntities.size())
                return R.error("该公开课预约听课人数已满");



            gongkaikeTingke.setInsertTime(new Date());
            gongkaikeTingke.setCreateTime(new Date());
            gongkaikeTingkeService.insert(gongkaikeTingke);
            return R.ok();
        }else {
            return R.error(511,"该督导老师已经预约过听此公开课");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody GongkaikeTingkeEntity gongkaikeTingke, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,gongkaikeTingke:{}",this.getClass().getName(),gongkaikeTingke.toString());


        gongkaikeTingke.setUpdateTime(new Date());
        GongkaikeTingkeEntity gongkaikeTingkeEntity = gongkaikeTingkeService.selectById(gongkaikeTingke);
        if(gongkaikeTingkeEntity == null)
            return R.error("查不到听课信息");

        gongkaikeTingkeService.updateById(gongkaikeTingke);//根据听课评价

        GongkaikeEntity gongkaikeEntity = gongkaikeService.selectById(gongkaikeTingkeEntity.getGongkaikeId());
        if(gongkaikeEntity == null)
            return R.error("查不到公开课");
        PutonglaoshiEntity putonglaoshiEntity = putonglaoshiService.selectById(gongkaikeEntity.getPutonglaoshiId());
        if(putonglaoshiEntity == null)
            return R.error("查不到该公开课的所属普通老师");

        /**
         * 更新公开课评分
         */
        List<GongkaikeTingkeEntity> gongkaikeTingkeEntities = gongkaikeTingkeService.selectList(
                new EntityWrapper<GongkaikeTingkeEntity>()
                        .eq("gongkaike_id", gongkaikeTingkeEntity.getGongkaikeId())
                        .isNotNull("gongkaike_tingke_pingfen")
                        .isNotNull("gongkaike_tingke_content")
                        .isNotNull("update_time")
        );//查询当前公开课已经评价的
        Double sum= 0.0;
        for(GongkaikeTingkeEntity g:gongkaikeTingkeEntities){
            sum+=g.getGongkaikeTingkePingfen();
        }
        double gongkaikepingjunchengji = new BigDecimal(sum).divide(new BigDecimal(gongkaikeTingkeEntities.size()),2).doubleValue();//当前公开课平均分值
        gongkaikeEntity.setGongkaikePingfen(gongkaikepingjunchengji);
        gongkaikeService.updateById(gongkaikeEntity);
        /**
         * 更新老师评分
         */
        List<GongkaikeEntity> gongkaikeEntities = gongkaikeService.selectList(new EntityWrapper<GongkaikeEntity>()
                .eq("putonglaoshi_id", gongkaikeEntity.getPutonglaoshiId())
        );//查询出当前普通老师的所有的公开课

        List<Integer> gongkaikeIds = new ArrayList<>();
        for(GongkaikeEntity g:gongkaikeEntities){
            gongkaikeIds.add(g.getId());
        }

        List<GongkaikeTingkeEntity> gongkaikeTingkeEntityList = gongkaikeTingkeService.selectList(new EntityWrapper<GongkaikeTingkeEntity>()
                .in("gongkaike_id", gongkaikeIds)
                .isNotNull("gongkaike_tingke_pingfen")
                .isNotNull("gongkaike_tingke_content")
                .isNotNull("update_time")
        );//查询出当前老师所有公开课下面的所有的评价


        Double sum1111= 0.0;
        for(GongkaikeTingkeEntity g:gongkaikeTingkeEntityList){
            sum1111+=g.getGongkaikeTingkePingfen();
        }
        double laoshipingfen = new BigDecimal(sum1111).divide(new BigDecimal(gongkaikeTingkeEntityList.size()),2).doubleValue();//当前老师下课程的平均分值
        putonglaoshiEntity.setLaoshiPingfen(laoshipingfen);
        putonglaoshiService.updateById(putonglaoshiEntity);
        return R.ok();

    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        gongkaikeTingkeService.deleteBatchIds(Arrays.asList(ids));
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
            List<GongkaikeTingkeEntity> gongkaikeTingkeList = new ArrayList<>();//上传的东西
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
                            GongkaikeTingkeEntity gongkaikeTingkeEntity = new GongkaikeTingkeEntity();
//                            gongkaikeTingkeEntity.setDudaolaoshiId(Integer.valueOf(data.get(0)));   //督导老师 要改的
//                            gongkaikeTingkeEntity.setGongkaikeId(Integer.valueOf(data.get(0)));   //公开课 要改的
//                            gongkaikeTingkeEntity.setGongkaikeTingkeUuidNumber(data.get(0));                    //申请编号 要改的
//                            gongkaikeTingkeEntity.setInsertTime(date);//时间
//                            gongkaikeTingkeEntity.setGongkaikeTingkePingfen(Integer.valueOf(data.get(0)));   //评分 要改的
//                            gongkaikeTingkeEntity.setGongkaikeTingkeContent("");//详情和图片
//                            gongkaikeTingkeEntity.setUpdateTime(sdf.parse(data.get(0)));          //评价时间 要改的
//                            gongkaikeTingkeEntity.setCreateTime(date);//时间
                            gongkaikeTingkeList.add(gongkaikeTingkeEntity);


                            //把要查询是否重复的字段放入map中
                                //申请编号
                                if(seachFields.containsKey("gongkaikeTingkeUuidNumber")){
                                    List<String> gongkaikeTingkeUuidNumber = seachFields.get("gongkaikeTingkeUuidNumber");
                                    gongkaikeTingkeUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> gongkaikeTingkeUuidNumber = new ArrayList<>();
                                    gongkaikeTingkeUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("gongkaikeTingkeUuidNumber",gongkaikeTingkeUuidNumber);
                                }
                        }

                        //查询是否重复
                         //申请编号
                        List<GongkaikeTingkeEntity> gongkaikeTingkeEntities_gongkaikeTingkeUuidNumber = gongkaikeTingkeService.selectList(new EntityWrapper<GongkaikeTingkeEntity>().in("gongkaike_tingke_uuid_number", seachFields.get("gongkaikeTingkeUuidNumber")));
                        if(gongkaikeTingkeEntities_gongkaikeTingkeUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(GongkaikeTingkeEntity s:gongkaikeTingkeEntities_gongkaikeTingkeUuidNumber){
                                repeatFields.add(s.getGongkaikeTingkeUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [申请编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        gongkaikeTingkeService.insertBatch(gongkaikeTingkeList);
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
