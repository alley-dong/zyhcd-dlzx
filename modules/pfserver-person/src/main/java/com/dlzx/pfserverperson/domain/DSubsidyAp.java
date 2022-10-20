package com.dlzx.pfserverperson.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 驾驶员-补助对象 d_subsidy
 *
 * @author dlzx
 * @date 2020-12-08
 */
public class DSubsidyAp extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 司机ID
     */
    @Excel(name = "司机ID")
    private String driverId;

    /**
     * 类型 1异地装卸货 2加班
     */
    @Excel(name = "类型 1异地装卸货 2加班")
    private Long type;

    /**
     * 补助开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "补助开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date subsidyStartDate;

    /**
     * 补助结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "补助结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date subsidyEndDate;

    /**
     * 申请原因
     */
    @Excel(name = "申请原因")
    private String remarks;

    /**
     * 审核状态
     */
    @Excel(name = "审核状态")
    private Long status;

    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reviewTime;

    /**
     * 审核人
     */
    @Excel(name = "审核人")
    private String reviewUser;

    /**
     * 审核备注
     */
    @Excel(name = "审核备注")
    private String reviewRemarks;

    /**
     * 删除状态 1正常 2删除
     */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag;
//天数
    private Long days;
//具体天集合
    private String daysStr;

    private String bTime;

    private String eTime;

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


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setType(Long type) {
        this.type = type;
    }
    @NotNull(message = "补助类型不能为空")
    public Long getType() {
        return type;
    }

    public void setSubsidyStartDate(Date subsidyStartDate) {
        this.subsidyStartDate = subsidyStartDate;
    }
    @NotNull(message = "补助开始时间不能为空")
    public Date getSubsidyStartDate() {
        return subsidyStartDate;
    }

    public void setSubsidyEndDate(Date subsidyEndDate) {
        this.subsidyEndDate = subsidyEndDate;
    }
    @NotNull(message = "补助结束时间不能为空")
    public Date getSubsidyEndDate() {
        return subsidyEndDate;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    @NotBlank(message = "申请原因不能为空")
    public String getRemarks() {
        return remarks;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewUser(String reviewUser) {
        this.reviewUser = reviewUser;
    }

    public String getReviewUser() {
        return reviewUser;
    }

    public void setReviewRemarks(String reviewRemarks) {
        this.reviewRemarks = reviewRemarks;
    }

    public String getReviewRemarks() {
        return reviewRemarks;
    }

    public void setDeleteFlag(Long deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Long getDeleteFlag() {
        return deleteFlag;
    }

    public void setDays(Long days) {
        this.days = days;
    }

    public Long getDays() {
        return days;
    }

    public void setDaysStr(String daysStr) {
        this.daysStr = daysStr;
    }

    public String getDaysStr() {
        return daysStr;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("driverId", getDriverId())
                .append("type", getType())
                .append("subsidyStartDate", getSubsidyStartDate())
                .append("subsidyEndDate", getSubsidyEndDate())
                .append("remarks", getRemarks())
                .append("status", getStatus())
                .append("reviewTime", getReviewTime())
                .append("reviewUser", getReviewUser())
                .append("reviewRemarks", getReviewRemarks())
                .append("deleteFlag", getDeleteFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("days", getDays())
                .append("daysStr", getDaysStr())
                .append("bTime", getbTime())
                .append("eTime", geteTime())
                .toString();
    }
}
