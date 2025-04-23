package com.dao;

import com.entity.PutonglaoshiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.PutonglaoshiView;

/**
 * 普通老师 Dao 接口
 *
 * @author 
 */
public interface PutonglaoshiDao extends BaseMapper<PutonglaoshiEntity> {

   List<PutonglaoshiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
