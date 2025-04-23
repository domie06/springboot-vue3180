package com.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * 普通老师
 *
 * @author 
 * @email
 */
@TableName("putonglaoshi")
public class PutonglaoshiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public PutonglaoshiEntity() {

	}

	public PutonglaoshiEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
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
     * 普通老师工号
     */
    @TableField(value = "putonglaoshi_uuid_number")

    private String putonglaoshiUuidNumber;


    /**
     * 普通老师姓名
     */
    @TableField(value = "putonglaoshi_name")

    private String putonglaoshiName;


    /**
     * 普通老师手机号
     */
    @TableField(value = "putonglaoshi_phone")

    private String putonglaoshiPhone;


    /**
     * 普通老师身份证号
     */
    @TableField(value = "putonglaoshi_id_number")

    private String putonglaoshiIdNumber;


    /**
     * 普通老师头像
     */
    @TableField(value = "putonglaoshi_photo")

    private String putonglaoshiPhoto;


    /**
     * 性别
     */
    @TableField(value = "sex_types")

    private Integer sexTypes;


    /**
     * 所教科目
     */
    @TableField(value = "kemu_types")

    private Integer kemuTypes;


    /**
     * 老师评分
     */
    @TableField(value = "laoshi_pingfen")

    private Double laoshiPingfen;


    /**
     * 普通老师电子邮箱
     */
    @TableField(value = "putonglaoshi_email")

    private String putonglaoshiEmail;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 设置：普通老师工号
	 */
    public String getPutonglaoshiUuidNumber() {
        return putonglaoshiUuidNumber;
    }
    /**
	 * 获取：普通老师工号
	 */

    public void setPutonglaoshiUuidNumber(String putonglaoshiUuidNumber) {
        this.putonglaoshiUuidNumber = putonglaoshiUuidNumber;
    }
    /**
	 * 设置：普通老师姓名
	 */
    public String getPutonglaoshiName() {
        return putonglaoshiName;
    }
    /**
	 * 获取：普通老师姓名
	 */

    public void setPutonglaoshiName(String putonglaoshiName) {
        this.putonglaoshiName = putonglaoshiName;
    }
    /**
	 * 设置：普通老师手机号
	 */
    public String getPutonglaoshiPhone() {
        return putonglaoshiPhone;
    }
    /**
	 * 获取：普通老师手机号
	 */

    public void setPutonglaoshiPhone(String putonglaoshiPhone) {
        this.putonglaoshiPhone = putonglaoshiPhone;
    }
    /**
	 * 设置：普通老师身份证号
	 */
    public String getPutonglaoshiIdNumber() {
        return putonglaoshiIdNumber;
    }
    /**
	 * 获取：普通老师身份证号
	 */

    public void setPutonglaoshiIdNumber(String putonglaoshiIdNumber) {
        this.putonglaoshiIdNumber = putonglaoshiIdNumber;
    }
    /**
	 * 设置：普通老师头像
	 */
    public String getPutonglaoshiPhoto() {
        return putonglaoshiPhoto;
    }
    /**
	 * 获取：普通老师头像
	 */

    public void setPutonglaoshiPhoto(String putonglaoshiPhoto) {
        this.putonglaoshiPhoto = putonglaoshiPhoto;
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
	 * 设置：所教科目
	 */
    public Integer getKemuTypes() {
        return kemuTypes;
    }
    /**
	 * 获取：所教科目
	 */

    public void setKemuTypes(Integer kemuTypes) {
        this.kemuTypes = kemuTypes;
    }
    /**
	 * 设置：老师评分
	 */
    public Double getLaoshiPingfen() {
        return laoshiPingfen;
    }
    /**
	 * 获取：老师评分
	 */

    public void setLaoshiPingfen(Double laoshiPingfen) {
        this.laoshiPingfen = laoshiPingfen;
    }
    /**
	 * 设置：普通老师电子邮箱
	 */
    public String getPutonglaoshiEmail() {
        return putonglaoshiEmail;
    }
    /**
	 * 获取：普通老师电子邮箱
	 */

    public void setPutonglaoshiEmail(String putonglaoshiEmail) {
        this.putonglaoshiEmail = putonglaoshiEmail;
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

    @Override
    public String toString() {
        return "Putonglaoshi{" +
            "id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", putonglaoshiUuidNumber=" + putonglaoshiUuidNumber +
            ", putonglaoshiName=" + putonglaoshiName +
            ", putonglaoshiPhone=" + putonglaoshiPhone +
            ", putonglaoshiIdNumber=" + putonglaoshiIdNumber +
            ", putonglaoshiPhoto=" + putonglaoshiPhoto +
            ", sexTypes=" + sexTypes +
            ", kemuTypes=" + kemuTypes +
            ", laoshiPingfen=" + laoshiPingfen +
            ", putonglaoshiEmail=" + putonglaoshiEmail +
            ", createTime=" + createTime +
        "}";
    }
}
