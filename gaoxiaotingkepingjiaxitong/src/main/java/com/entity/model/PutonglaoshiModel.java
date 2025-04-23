package com.entity.model;

import com.entity.PutonglaoshiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 普通老师
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class PutonglaoshiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


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
     * 性别
     */
    private Integer sexTypes;


    /**
     * 所教科目
     */
    private Integer kemuTypes;


    /**
     * 老师评分
     */
    private Integer laoshiPingfen;


    /**
     * 普通老师电子邮箱
     */
    private String putonglaoshiEmail;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 设置：账户
	 */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 设置：密码
	 */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：普通老师工号
	 */
    public String getPutonglaoshiUuidNumber() {
        return putonglaoshiUuidNumber;
    }


    /**
	 * 设置：普通老师工号
	 */
    public void setPutonglaoshiUuidNumber(String putonglaoshiUuidNumber) {
        this.putonglaoshiUuidNumber = putonglaoshiUuidNumber;
    }
    /**
	 * 获取：普通老师姓名
	 */
    public String getPutonglaoshiName() {
        return putonglaoshiName;
    }


    /**
	 * 设置：普通老师姓名
	 */
    public void setPutonglaoshiName(String putonglaoshiName) {
        this.putonglaoshiName = putonglaoshiName;
    }
    /**
	 * 获取：普通老师手机号
	 */
    public String getPutonglaoshiPhone() {
        return putonglaoshiPhone;
    }


    /**
	 * 设置：普通老师手机号
	 */
    public void setPutonglaoshiPhone(String putonglaoshiPhone) {
        this.putonglaoshiPhone = putonglaoshiPhone;
    }
    /**
	 * 获取：普通老师身份证号
	 */
    public String getPutonglaoshiIdNumber() {
        return putonglaoshiIdNumber;
    }


    /**
	 * 设置：普通老师身份证号
	 */
    public void setPutonglaoshiIdNumber(String putonglaoshiIdNumber) {
        this.putonglaoshiIdNumber = putonglaoshiIdNumber;
    }
    /**
	 * 获取：普通老师头像
	 */
    public String getPutonglaoshiPhoto() {
        return putonglaoshiPhoto;
    }


    /**
	 * 设置：普通老师头像
	 */
    public void setPutonglaoshiPhoto(String putonglaoshiPhoto) {
        this.putonglaoshiPhoto = putonglaoshiPhoto;
    }
    /**
	 * 获取：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 设置：性别
	 */
    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：所教科目
	 */
    public Integer getKemuTypes() {
        return kemuTypes;
    }


    /**
	 * 设置：所教科目
	 */
    public void setKemuTypes(Integer kemuTypes) {
        this.kemuTypes = kemuTypes;
    }
    /**
	 * 获取：老师评分
	 */
    public Integer getLaoshiPingfen() {
        return laoshiPingfen;
    }


    /**
	 * 设置：老师评分
	 */
    public void setLaoshiPingfen(Integer laoshiPingfen) {
        this.laoshiPingfen = laoshiPingfen;
    }
    /**
	 * 获取：普通老师电子邮箱
	 */
    public String getPutonglaoshiEmail() {
        return putonglaoshiEmail;
    }


    /**
	 * 设置：普通老师电子邮箱
	 */
    public void setPutonglaoshiEmail(String putonglaoshiEmail) {
        this.putonglaoshiEmail = putonglaoshiEmail;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
