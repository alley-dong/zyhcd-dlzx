package com.dlzx.job.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 驾驶员-培训对象 d_training
 *
 * @author dlzx
 * @date 2020-11-26
 */
public class DTrainingAp extends BaseEntity {
    private static final long serialVersionUID = 1L;


    private String id;

    /**
     * 标题
     */
    @Excel(name = "标题")
    private String title;


    private String trainingId;
    /**
     * 培训结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "培训结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date trainingEndTime;


    /**
     * 状态 1未完成 2已完成 3超期未完成 4超期已完成
     */
    @Excel(name = "状态 1未完成 2已完成 3超期未完成 4超期已完成")
    private Long status;

    private String userId;

    private Date dangTime;
    //开始时间
    private String bTime;
    //结束时间
    private String eTime;
    //浏览时间
    private Long seeTime;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTrainingEndTime(Date trainingEndTime) {
        this.trainingEndTime = trainingEndTime;
    }

    public Date getTrainingEndTime() {
        return trainingEndTime;
    }

    public Date getDangTime() {
        return dangTime;
    }

    public void setDangTime(Date dangTime) {
        this.dangTime = dangTime;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(String trainingId) {
        this.trainingId = trainingId;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)

                .append("id", getId())
                .append("title", getTitle())
                .append("trainingId", getTrainingId())
                .append("trainingEndTime", getTrainingEndTime())
                .append("dangTime", getDangTime())
                .append("status", getStatus())
                .append("userId", getUserId())

                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("bTime", getbTime())
                .append("eTime", geteTime())
                .append("seeTime", getSeeTime())
                .toString();
    }


    public String getbTime() {
        return bTime;
    }

    public void setbTime(String bTime) {
        this.bTime = bTime;
    }

    public String geteTime() {
        return eTime;
    }

    public void seteTime(String eTime) {
        this.eTime = eTime;
    }

    public Long getSeeTime() {
        return seeTime;
    }

    public void setSeeTime(Long seeTime) {
        this.seeTime = seeTime;
    }
}
