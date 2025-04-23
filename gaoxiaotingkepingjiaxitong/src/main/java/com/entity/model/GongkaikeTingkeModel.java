package com.entity.model;

import com.entity.GongkaikeTingkeEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 公开课听课
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class GongkaikeTingkeModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 督导老师
     */
    private Integer dudaolaoshiId;


    /**
     * 公开课
     */
    private Integer gongkaikeId;


    /**
     * 申请编号
     */
    private String gongkaikeTingkeUuidNumber;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 评分
     */
    private Integer gongkaikeTingkePingfen;


    /**
     * 评价内容
     */
    private String gongkaikeTingkeContent;


    /**
     * 评价时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date updateTime;


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
	 * 获取：督导老师
	 */
    public Integer getDudaolaoshiId() {
        return dudaolaoshiId;
    }


    /**
	 * 设置：督导老师
	 */
    public void setDudaolaoshiId(Integer dudaolaoshiId) {
        this.dudaolaoshiId = dudaolaoshiId;
    }
    /**
	 * 获取：公开课
	 */
    public Integer getGongkaikeId() {
        return gongkaikeId;
    }


    /**
	 * 设置：公开课
	 */
    public void setGongkaikeId(Integer gongkaikeId) {
        this.gongkaikeId = gongkaikeId;
    }
    /**
	 * 获取：申请编号
	 */
    public String getGongkaikeTingkeUuidNumber() {
        return gongkaikeTingkeUuidNumber;
    }


    /**
	 * 设置：申请编号
	 */
    public void setGongkaikeTingkeUuidNumber(String gongkaikeTingkeUuidNumber) {
        this.gongkaikeTingkeUuidNumber = gongkaikeTingkeUuidNumber;
    }
    /**
	 * 获取：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：申请时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：评分
	 */
    public Integer getGongkaikeTingkePingfen() {
        return gongkaikeTingkePingfen;
    }


    /**
	 * 设置：评分
	 */
    public void setGongkaikeTingkePingfen(Integer gongkaikeTingkePingfen) {
        this.gongkaikeTingkePingfen = gongkaikeTingkePingfen;
    }
    /**
	 * 获取：评价内容
	 */
    public String getGongkaikeTingkeContent() {
        return gongkaikeTingkeContent;
    }


    /**
	 * 设置：评价内容
	 */
    public void setGongkaikeTingkeContent(String gongkaikeTingkeContent) {
        this.gongkaikeTingkeContent = gongkaikeTingkeContent;
    }
    /**
	 * 获取：评价时间
	 */
    public Date getUpdateTime() {
        return updateTime;
    }


    /**
	 * 设置：评价时间
	 */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
