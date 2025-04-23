package com.dao;

import com.entity.DudaolaoshiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.DudaolaoshiView;

/**
 * 督导老师 Dao 接口
 *
 * @author 
 */
public interface DudaolaoshiDao extends BaseMapper<DudaolaoshiEntity> {

   List<DudaolaoshiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
