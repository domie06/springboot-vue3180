package com.entity.vo;

import com.entity.DudaolaoshiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 督导老师
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("dudaolaoshi")
public class DudaolaoshiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 账户
     */

    @TableField(value = "username")
    private String username;


    /**
     * 密码
     */

    @TableField(value = "password")
    private String password;


    /**
     * 督导老师工号
     */

    @TableField(value = "dudaolaoshi_uuid_number")
    private String dudaolaoshiUuidNumber;


    /**
     * 督导老师姓名
     */

    @TableField(value = "dudaolaoshi_name")
    private String dudaolaoshiName;


    /**
     * 督导老师手机号
     */

    @TableField(value = "dudaolaoshi_phone")
    private String dudaolaoshiPhone;


    /**
     * 督导老师身份证号
     */

    @TableField(value = "dudaolaoshi_id_number")
    private String dudaolaoshiIdNumber;


    /**
     * 督导老师头像
     */

    @TableField(value = "dudaolaoshi_photo")
    private String dudaolaoshiPhoto;


    /**
     * 性别
     */

    @TableField(value = "sex_types")
    private Integer sexTypes;


    /**
     * 督导老师电子邮箱
     */

    @TableField(value = "dudaolaoshi_email")
    private String dudaolaoshiEmail;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：督导老师工号
	 */
    public String getDudaolaoshiUuidNumber() {
        return dudaolaoshiUuidNumber;
    }


    /**
	 * 获取：督导老师工号
	 */

    public void setDudaolaoshiUuidNumber(String dudaolaoshiUuidNumber) {
        this.dudaolaoshiUuidNumber = dudaolaoshiUuidNumber;
    }
    /**
	 * 设置：督导老师姓名
	 */
    public String getDudaolaoshiName() {
        return dudaolaoshiName;
    }


    /**
	 * 获取：督导老师姓名
	 */

    public void setDudaolaoshiName(String dudaolaoshiName) {
        this.dudaolaoshiName = dudaolaoshiName;
    }
    /**
	 * 设置：督导老师手机号
	 */
    public String getDudaolaoshiPhone() {
        return dudaolaoshiPhone;
    }


    /**
	 * 获取：督导老师手机号
	 */

    public void setDudaolaoshiPhone(String dudaolaoshiPhone) {
        this.dudaolaoshiPhone = dudaolaoshiPhone;
    }
    /**
	 * 设置：督导老师身份证号
	 */
    public String getDudaolaoshiIdNumber() {
        return dudaolaoshiIdNumber;
    }


    /**
	 * 获取：督导老师身份证号
	 */

    public void setDudaolaoshiIdNumber(String dudaolaoshiIdNumber) {
        this.dudaolaoshiIdNumber = dudaolaoshiIdNumber;
    }
    /**
	 * 设置：督导老师头像
	 */
    public String getDudaolaoshiPhoto() {
        return dudaolaoshiPhoto;
    }


    /**
	 * 获取：督导老师头像
	 */

    public void setDudaolaoshiPhoto(String dudaolaoshiPhoto) {
        this.dudaolaoshiPhoto = dudaolaoshiPhoto;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：督导老师电子邮箱
	 */
    public String getDudaolaoshiEmail() {
        return dudaolaoshiEmail;
    }


    /**
	 * 获取：督导老师电子邮箱
	 */

    public void setDudaolaoshiEmail(String dudaolaoshiEmail) {
        this.dudaolaoshiEmail = dudaolaoshiEmail;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
