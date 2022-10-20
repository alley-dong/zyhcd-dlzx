package com.dlzx.pfserverparts.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 驾驶员-薪酬结算对象 d_salary_records
 * 
 * @author yangluhe
 * @date 2020-12-21
 */
public class DSalaryRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 薪酬日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "薪酬日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date vacationDate;

    /** 工资总额 */
    @Excel(name = "工资总额")
    private BigDecimal totalAmount;

    /** 状态 1未下发 2已下发 */
    @Excel(name = "状态 1未下发 2已下发")
    private Long salaryStatus;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Long status;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reviewTime;

    /** 审核人 */
    @Excel(name = "审核人")
    private String reviewUser;

    /** 审核备注 */
    @Excel(name = "审核备注")
    private String reviewRemarks;

    /** 删除状态 1正常 2删除 */
    private Long deleteFlag;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setVacationDate(Date vacationDate) 
    {
        this.vacationDate = vacationDate;
    }

    public Date getVacationDate() 
    {
        return vacationDate;
    }
    public void setTotalAmount(BigDecimal totalAmount) 
    {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() 
    {
        return totalAmount;
    }
    public void setSalaryStatus(Long salaryStatus) 
    {
        this.salaryStatus = salaryStatus;
    }

    public Long getSalaryStatus() 
    {
        return salaryStatus;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setReviewTime(Date reviewTime) 
    {
        this.reviewTime = reviewTime;
    }

    public Date getReviewTime() 
    {
        return reviewTime;
    }
    public void setReviewUser(String reviewUser) 
    {
        this.reviewUser = reviewUser;
    }

    public String getReviewUser() 
    {
        return reviewUser;
    }
    public void setReviewRemarks(String reviewRemarks) 
    {
        this.reviewRemarks = reviewRemarks;
    }

    public String getReviewRemarks() 
    {
        return reviewRemarks;
    }
    public void setDeleteFlag(Long deleteFlag) 
    {
        this.deleteFlag = deleteFlag;
    }

    public Long getDeleteFlag() 
    {
        return deleteFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("vacationDate", getVacationDate())
            .append("totalAmount", getTotalAmount())
            .append("salaryStatus", getSalaryStatus())
            .append("status", getStatus())
            .append("reviewTime", getReviewTime())
            .append("reviewUser", getReviewUser())
            .append("reviewRemarks", getReviewRemarks())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
