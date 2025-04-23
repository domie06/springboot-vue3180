package com.entity.model;

import com.entity.GongkaikeEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 公开课
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class GongkaikeModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 普通老师
     */
    private Integer putonglaoshiId;


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
     * 授课班级
     */
    private Integer banjiTypes;


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
    private Integer gongkaikePingfen;


    /**
     * 开课时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date kaikeTime;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


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
	 * 获取：普通老师
	 */
    public Integer getPutonglaoshiId() {
        return putonglaoshiId;
    }


    /**
	 * 设置：普通老师
	 */
    public void setPutonglaoshiId(Integer putonglaoshiId) {
        this.putonglaoshiId = putonglaoshiId;
    }
    /**
	 * 获取：公开课编号
	 */
    public String getGongkaikeUuidNumber() {
        return gongkaikeUuidNumber;
    }


    /**
	 * 设置：公开课编号
	 */
    public void setGongkaikeUuidNumber(String gongkaikeUuidNumber) {
        this.gongkaikeUuidNumber = gongkaikeUuidNumber;
    }
    /**
	 * 获取：公开课名称
	 */
    public String getGongkaikeName() {
        return gongkaikeName;
    }


    /**
	 * 设置：公开课名称
	 */
    public void setGongkaikeName(String gongkaikeName) {
        this.gongkaikeName = gongkaikeName;
    }
    /**
	 * 获取：公开课类型
	 */
    public Integer getGongkaikeTypes() {
        return gongkaikeTypes;
    }


    /**
	 * 设置：公开课类型
	 */
    public void setGongkaikeTypes(Integer gongkaikeTypes) {
        this.gongkaikeTypes = gongkaikeTypes;
    }
    /**
	 * 获取：授课班级
	 */
    public Integer getBanjiTypes() {
        return banjiTypes;
    }


    /**
	 * 设置：授课班级
	 */
    public void setBanjiTypes(Integer banjiTypes) {
        this.banjiTypes = banjiTypes;
    }
    /**
	 * 获取：章节内容
	 */
    public String getGongkaikeContent() {
        return gongkaikeContent;
    }


    /**
	 * 设置：章节内容
	 */
    public void setGongkaikeContent(String gongkaikeContent) {
        this.gongkaikeContent = gongkaikeContent;
    }
    /**
	 * 获取：公开课预估时长
	 */
    public String getGongkaikeShichang() {
        return gongkaikeShichang;
    }


    /**
	 * 设置：公开课预估时长
	 */
    public void setGongkaikeShichang(String gongkaikeShichang) {
        this.gongkaikeShichang = gongkaikeShichang;
    }
    /**
	 * 获取：公开课开课位置
	 */
    public String getGongkaikeAddress() {
        return gongkaikeAddress;
    }


    /**
	 * 设置：公开课开课位置
	 */
    public void setGongkaikeAddress(String gongkaikeAddress) {
        this.gongkaikeAddress = gongkaikeAddress;
    }
    /**
	 * 获取：最大听课人数
	 */
    public Integer getGongkaikeNumber() {
        return gongkaikeNumber;
    }


    /**
	 * 设置：最大听课人数
	 */
    public void setGongkaikeNumber(Integer gongkaikeNumber) {
        this.gongkaikeNumber = gongkaikeNumber;
    }
    /**
	 * 获取：公开课评分
	 */
    public Integer getGongkaikePingfen() {
        return gongkaikePingfen;
    }


    /**
	 * 设置：公开课评分
	 */
    public void setGongkaikePingfen(Integer gongkaikePingfen) {
        this.gongkaikePingfen = gongkaikePingfen;
    }
    /**
	 * 获取：开课时间
	 */
    public Date getKaikeTime() {
        return kaikeTime;
    }


    /**
	 * 设置：开课时间
	 */
    public void setKaikeTime(Date kaikeTime) {
        this.kaikeTime = kaikeTime;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：录入时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
