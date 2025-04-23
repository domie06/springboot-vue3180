package com.entity.model;

import com.entity.DudaolaoshiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 督导老师
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class DudaolaoshiModel implements Serializable {
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
     * 性别
     */
    private Integer sexTypes;


    /**
     * 督导老师电子邮箱
     */
    private String dudaolaoshiEmail;


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
	 * 获取：督导老师工号
	 */
    public String getDudaolaoshiUuidNumber() {
        return dudaolaoshiUuidNumber;
    }


    /**
	 * 设置：督导老师工号
	 */
    public void setDudaolaoshiUuidNumber(String dudaolaoshiUuidNumber) {
        this.dudaolaoshiUuidNumber = dudaolaoshiUuidNumber;
    }
    /**
	 * 获取：督导老师姓名
	 */
    public String getDudaolaoshiName() {
        return dudaolaoshiName;
    }


    /**
	 * 设置：督导老师姓名
	 */
    public void setDudaolaoshiName(String dudaolaoshiName) {
        this.dudaolaoshiName = dudaolaoshiName;
    }
    /**
	 * 获取：督导老师手机号
	 */
    public String getDudaolaoshiPhone() {
        return dudaolaoshiPhone;
    }


    /**
	 * 设置：督导老师手机号
	 */
    public void setDudaolaoshiPhone(String dudaolaoshiPhone) {
        this.dudaolaoshiPhone = dudaolaoshiPhone;
    }
    /**
	 * 获取：督导老师身份证号
	 */
    public String getDudaolaoshiIdNumber() {
        return dudaolaoshiIdNumber;
    }


    /**
	 * 设置：督导老师身份证号
	 */
    public void setDudaolaoshiIdNumber(String dudaolaoshiIdNumber) {
        this.dudaolaoshiIdNumber = dudaolaoshiIdNumber;
    }
    /**
	 * 获取：督导老师头像
	 */
    public String getDudaolaoshiPhoto() {
        return dudaolaoshiPhoto;
    }


    /**
	 * 设置：督导老师头像
	 */
    public void setDudaolaoshiPhoto(String dudaolaoshiPhoto) {
        this.dudaolaoshiPhoto = dudaolaoshiPhoto;
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
	 * 获取：督导老师电子邮箱
	 */
    public String getDudaolaoshiEmail() {
        return dudaolaoshiEmail;
    }


    /**
	 * 设置：督导老师电子邮箱
	 */
    public void setDudaolaoshiEmail(String dudaolaoshiEmail) {
        this.dudaolaoshiEmail = dudaolaoshiEmail;
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
