package com.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.GongkaikeEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * 公开课
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("gongkaike")
public class GongkaikeView extends GongkaikeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 公开课类型的值
		*/
		private String gongkaikeValue;
		/**
		* 授课班级的值
		*/
		private String banjiValue;



		//级联表 putonglaoshi
			/**
			* 普通老师工号
			*/
			private String putonglaoshiUuidNumber;
			/**
			* 普通老师姓名
			*/
			private String putonglaoshiName;
			/**
			* 普通老师手机号
			*/
			private String putonglaoshiPhone;
			/**
			* 普通老师身份证号
			*/
			private String putonglaoshiIdNumber;
			/**
			* 普通老师头像
			*/
			private String putonglaoshiPhoto;
			/**
			* 所教科目
			*/
			private Integer kemuTypes;
				/**
				* 所教科目的值
				*/
				private String kemuValue;
			/**
			* 老师评分
			*/
			private Double laoshiPingfen;
			/**
			* 普通老师电子邮箱
			*/
			private String putonglaoshiEmail;

	public GongkaikeView() {

	}

	public GongkaikeView(GongkaikeEntity gongkaikeEntity) {
		try {
			BeanUtils.copyProperties(this, gongkaikeEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 公开课类型的值
			*/
			public String getGongkaikeValue() {
				return gongkaikeValue;
			}
			/**
			* 设置： 公开课类型的值
			*/
			public void setGongkaikeValue(String gongkaikeValue) {
				this.gongkaikeValue = gongkaikeValue;
			}
			/**
			* 获取： 授课班级的值
			*/
			public String getBanjiValue() {
				return banjiValue;
			}
			/**
			* 设置： 授课班级的值
			*/
			public void setBanjiValue(String banjiValue) {
				this.banjiValue = banjiValue;
			}












				//级联表的get和set putonglaoshi

					/**
					* 获取： 普通老师工号
					*/
					public String getPutonglaoshiUuidNumber() {
						return putonglaoshiUuidNumber;
					}
					/**
					* 设置： 普通老师工号
					*/
					public void setPutonglaoshiUuidNumber(String putonglaoshiUuidNumber) {
						this.putonglaoshiUuidNumber = putonglaoshiUuidNumber;
					}

					/**
					* 获取： 普通老师姓名
					*/
					public String getPutonglaoshiName() {
						return putonglaoshiName;
					}
					/**
					* 设置： 普通老师姓名
					*/
					public void setPutonglaoshiName(String putonglaoshiName) {
						this.putonglaoshiName = putonglaoshiName;
					}

					/**
					* 获取： 普通老师手机号
					*/
					public String getPutonglaoshiPhone() {
						return putonglaoshiPhone;
					}
					/**
					* 设置： 普通老师手机号
					*/
					public void setPutonglaoshiPhone(String putonglaoshiPhone) {
						this.putonglaoshiPhone = putonglaoshiPhone;
					}

					/**
					* 获取： 普通老师身份证号
					*/
					public String getPutonglaoshiIdNumber() {
						return putonglaoshiIdNumber;
					}
					/**
					* 设置： 普通老师身份证号
					*/
					public void setPutonglaoshiIdNumber(String putonglaoshiIdNumber) {
						this.putonglaoshiIdNumber = putonglaoshiIdNumber;
					}

					/**
					* 获取： 普通老师头像
					*/
					public String getPutonglaoshiPhoto() {
						return putonglaoshiPhoto;
					}
					/**
					* 设置： 普通老师头像
					*/
					public void setPutonglaoshiPhoto(String putonglaoshiPhoto) {
						this.putonglaoshiPhoto = putonglaoshiPhoto;
					}

					/**
					* 获取： 所教科目
					*/
					public Integer getKemuTypes() {
						return kemuTypes;
					}
					/**
					* 设置： 所教科目
					*/
					public void setKemuTypes(Integer kemuTypes) {
						this.kemuTypes = kemuTypes;
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

					/**
					* 获取： 老师评分
					*/
					public Double getLaoshiPingfen() {
						return laoshiPingfen;
					}
					/**
					* 设置： 老师评分
					*/
					public void setLaoshiPingfen(Double laoshiPingfen) {
						this.laoshiPingfen = laoshiPingfen;
					}

					/**
					* 获取： 普通老师电子邮箱
					*/
					public String getPutonglaoshiEmail() {
						return putonglaoshiEmail;
					}
					/**
					* 设置： 普通老师电子邮箱
					*/
					public void setPutonglaoshiEmail(String putonglaoshiEmail) {
						this.putonglaoshiEmail = putonglaoshiEmail;
					}




}
