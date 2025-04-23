package com.entity.vo;

import com.entity.GongkaikeTingkeEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 公开课听课
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("gongkaike_tingke")
public class GongkaikeTingkeVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 督导老师
     */

    @TableField(value = "dudaolaoshi_id")
    private Integer dudaolaoshiId;


    /**
     * 公开课
     */

    @TableField(value = "gongkaike_id")
    private Integer gongkaikeId;


    /**
     * 申请编号
     */

    @TableField(value = "gongkaike_tingke_uuid_number")
    private String gongkaikeTingkeUuidNumber;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 评分
     */

    @TableField(value = "gongkaike_tingke_pingfen")
    private Integer gongkaikeTingkePingfen;


    /**
     * 评价内容
     */

    @TableField(value = "gongkaike_tingke_content")
    private String gongkaikeTingkeContent;


    /**
     * 评价时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "update_time")
    private Date updateTime;


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
	 * 设置：督导老师
	 */
    public Integer getDudaolaoshiId() {
        return dudaolaoshiId;
    }


    /**
	 * 获取：督导老师
	 */

    public void setDudaolaoshiId(Integer dudaolaoshiId) {
        this.dudaolaoshiId = dudaolaoshiId;
    }
    /**
	 * 设置：公开课
	 */
    public Integer getGongkaikeId() {
        return gongkaikeId;
    }


    /**
	 * 获取：公开课
	 */

    public void setGongkaikeId(Integer gongkaikeId) {
        this.gongkaikeId = gongkaikeId;
    }
    /**
	 * 设置：申请编号
	 */
    public String getGongkaikeTingkeUuidNumber() {
        return gongkaikeTingkeUuidNumber;
    }


    /**
	 * 获取：申请编号
	 */

    public void setGongkaikeTingkeUuidNumber(String gongkaikeTingkeUuidNumber) {
        this.gongkaikeTingkeUuidNumber = gongkaikeTingkeUuidNumber;
    }
    /**
	 * 设置：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：申请时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：评分
	 */
    public Integer getGongkaikeTingkePingfen() {
        return gongkaikeTingkePingfen;
    }


    /**
	 * 获取：评分
	 */

    public void setGongkaikeTingkePingfen(Integer gongkaikeTingkePingfen) {
        this.gongkaikeTingkePingfen = gongkaikeTingkePingfen;
    }
    /**
	 * 设置：评价内容
	 */
    public String getGongkaikeTingkeContent() {
        return gongkaikeTingkeContent;
    }


    /**
	 * 获取：评价内容
	 */

    public void setGongkaikeTingkeContent(String gongkaikeTingkeContent) {
        this.gongkaikeTingkeContent = gongkaikeTingkeContent;
    }
    /**
	 * 设置：评价时间
	 */
    public Date getUpdateTime() {
        return updateTime;
    }


    /**
	 * 获取：评价时间
	 */

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
