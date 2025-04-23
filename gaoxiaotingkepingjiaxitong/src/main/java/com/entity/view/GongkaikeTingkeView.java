package com.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.GongkaikeTingkeEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * 公开课听课
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("gongkaike_tingke")
public class GongkaikeTingkeView extends GongkaikeTingkeEntity implements Serializable {
    private static final long serialVersionUID = 1L;




		//级联表 gongkaike
			/**
			* 公开课 的 普通老师
			*/
			private Integer gongkaikePutonglaoshiId;
			/**
			* 公开课编号
			*/
			private String gongkaikeUuidNumber;
			/**
			* 公开课名称
			*/
			private String gongkaikeName;
			/**
			* 公开课类型
			*/
			private Integer gongkaikeTypes;
				/**
				* 公开课类型的值
				*/
				private String gongkaikeValue;
			/**
			* 授课班级
			*/
			private Integer banjiTypes;
				/**
				* 授课班级的值
				*/
				private String banjiValue;
			/**
			* 章节内容
			*/
			private String gongkaikeContent;
			/**
			* 公开课预估时长
			*/
			private String gongkaikeShichang;
			/**
			* 公开课开课位置
			*/
			private String gongkaikeAddress;
			/**
			* 最大听课人数
			*/
			private Integer gongkaikeNumber;
			/**
			* 公开课评分
			*/
			private Double gongkaikePingfen;
			/**
			* 开课时间
			*/
			@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
			@DateTimeFormat
			private Date kaikeTime;

		//级联表 dudaolaoshi
			/**
			* 督导老师工号
			*/
			private String dudaolaoshiUuidNumber;
			/**
			* 督导老师姓名
			*/
			private String dudaolaoshiName;
			/**
			* 督导老师手机号
			*/
			private String dudaolaoshiPhone;
			/**
			* 督导老师身份证号
			*/
			private String dudaolaoshiIdNumber;
			/**
			* 督导老师头像
			*/
			private String dudaolaoshiPhoto;
			/**
			* 督导老师电子邮箱
			*/
			private String dudaolaoshiEmail;

	public GongkaikeTingkeView() {

	}

	public GongkaikeTingkeView(GongkaikeTingkeEntity gongkaikeTingkeEntity) {
		try {
			BeanUtils.copyProperties(this, gongkaikeTingkeEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}














				//级联表的get和set gongkaike

					/**
					* 获取：公开课 的 普通老师
					*/
					public Integer getGongkaikePutonglaoshiId() {
						return gongkaikePutonglaoshiId;
					}
					/**
					* 设置：公开课 的 普通老师
					*/
					public void setGongkaikePutonglaoshiId(Integer gongkaikePutonglaoshiId) {
						this.gongkaikePutonglaoshiId = gongkaikePutonglaoshiId;
					}


					/**
					* 获取： 公开课编号
					*/
					public String getGongkaikeUuidNumber() {
						return gongkaikeUuidNumber;
					}
					/**
					* 设置： 公开课编号
					*/
					public void setGongkaikeUuidNumber(String gongkaikeUuidNumber) {
						this.gongkaikeUuidNumber = gongkaikeUuidNumber;
					}

					/**
					* 获取： 公开课名称
					*/
					public String getGongkaikeName() {
						return gongkaikeName;
					}
					/**
					* 设置： 公开课名称
					*/
					public void setGongkaikeName(String gongkaikeName) {
						this.gongkaikeName = gongkaikeName;
					}

					/**
					* 获取： 公开课类型
					*/
					public Integer getGongkaikeTypes() {
						return gongkaikeTypes;
					}
					/**
					* 设置： 公开课类型
					*/
					public void setGongkaikeTypes(Integer gongkaikeTypes) {
						this.gongkaikeTypes = gongkaikeTypes;
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
					* 获取： 授课班级
					*/
					public Integer getBanjiTypes() {
						return banjiTypes;
					}
					/**
					* 设置： 授课班级
					*/
					public void setBanjiTypes(Integer banjiTypes) {
						this.banjiTypes = banjiTypes;
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

					/**
					* 获取： 章节内容
					*/
					public String getGongkaikeContent() {
						return gongkaikeContent;
					}
					/**
					* 设置： 章节内容
					*/
					public void setGongkaikeContent(String gongkaikeContent) {
						this.gongkaikeContent = gongkaikeContent;
					}

					/**
					* 获取： 公开课预估时长
					*/
					public String getGongkaikeShichang() {
						return gongkaikeShichang;
					}
					/**
					* 设置： 公开课预估时长
					*/
					public void setGongkaikeShichang(String gongkaikeShichang) {
						this.gongkaikeShichang = gongkaikeShichang;
					}

					/**
					* 获取： 公开课开课位置
					*/
					public String getGongkaikeAddress() {
						return gongkaikeAddress;
					}
					/**
					* 设置： 公开课开课位置
					*/
					public void setGongkaikeAddress(String gongkaikeAddress) {
						this.gongkaikeAddress = gongkaikeAddress;
					}

					/**
					* 获取： 最大听课人数
					*/
					public Integer getGongkaikeNumber() {
						return gongkaikeNumber;
					}
					/**
					* 设置： 最大听课人数
					*/
					public void setGongkaikeNumber(Integer gongkaikeNumber) {
						this.gongkaikeNumber = gongkaikeNumber;
					}

					/**
					* 获取： 公开课评分
					*/
					public Double getGongkaikePingfen() {
						return gongkaikePingfen;
					}
					/**
					* 设置： 公开课评分
					*/
					public void setGongkaikePingfen(Double gongkaikePingfen) {
						this.gongkaikePingfen = gongkaikePingfen;
					}

					/**
					* 获取： 开课时间
					*/
					public Date getKaikeTime() {
						return kaikeTime;
					}
					/**
					* 设置： 开课时间
					*/
					public void setKaikeTime(Date kaikeTime) {
						this.kaikeTime = kaikeTime;
					}








				//级联表的get和set dudaolaoshi

					/**
					* 获取： 督导老师工号
					*/
					public String getDudaolaoshiUuidNumber() {
						return dudaolaoshiUuidNumber;
					}
					/**
					* 设置： 督导老师工号
					*/
					public void setDudaolaoshiUuidNumber(String dudaolaoshiUuidNumber) {
						this.dudaolaoshiUuidNumber = dudaolaoshiUuidNumber;
					}

					/**
					* 获取： 督导老师姓名
					*/
					public String getDudaolaoshiName() {
						return dudaolaoshiName;
					}
					/**
					* 设置： 督导老师姓名
					*/
					public void setDudaolaoshiName(String dudaolaoshiName) {
						this.dudaolaoshiName = dudaolaoshiName;
					}

					/**
					* 获取： 督导老师手机号
					*/
					public String getDudaolaoshiPhone() {
						return dudaolaoshiPhone;
					}
					/**
					* 设置： 督导老师手机号
					*/
					public void setDudaolaoshiPhone(String dudaolaoshiPhone) {
						this.dudaolaoshiPhone = dudaolaoshiPhone;
					}

					/**
					* 获取： 督导老师身份证号
					*/
					public String getDudaolaoshiIdNumber() {
						return dudaolaoshiIdNumber;
					}
					/**
					* 设置： 督导老师身份证号
					*/
					public void setDudaolaoshiIdNumber(String dudaolaoshiIdNumber) {
						this.dudaolaoshiIdNumber = dudaolaoshiIdNumber;
					}

					/**
					* 获取： 督导老师头像
					*/
					public String getDudaolaoshiPhoto() {
						return dudaolaoshiPhoto;
					}
					/**
					* 设置： 督导老师头像
					*/
					public void setDudaolaoshiPhoto(String dudaolaoshiPhoto) {
						this.dudaolaoshiPhoto = dudaolaoshiPhoto;
					}

					/**
					* 获取： 督导老师电子邮箱
					*/
					public String getDudaolaoshiEmail() {
						return dudaolaoshiEmail;
					}
					/**
					* 设置： 督导老师电子邮箱
					*/
					public void setDudaolaoshiEmail(String dudaolaoshiEmail) {
						this.dudaolaoshiEmail = dudaolaoshiEmail;
					}




}
