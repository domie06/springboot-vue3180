package com.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.PutonglaoshiEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * 普通老师
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("putonglaoshi")
public class PutonglaoshiView extends PutonglaoshiEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 性别的值
		*/
		private String sexValue;
		/**
		* 所教科目的值
		*/
		private String kemuValue;



	public PutonglaoshiView() {

	}

	public PutonglaoshiView(PutonglaoshiEntity putonglaoshiEntity) {
		try {
			BeanUtils.copyProperties(this, putonglaoshiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 性别的值
			*/
			public String getSexValue() {
				return sexValue;
			}
			/**
			* 设置： 性别的值
			*/
			public void setSexValue(String sexValue) {
				this.sexValue = sexValue;
			}
			/**
			* 获取： 所教科目的值
			*/
			public String getKemuValue() {
				return kemuValue;
			}
			/**
			* 设置： 所教科目的值
			*/
			public void setKemuValue(String kemuValue) {
				this.kemuValue = kemuValue;
			}









}
