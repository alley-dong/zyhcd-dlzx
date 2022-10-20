package com.dlzx.pfserverparts.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 驾驶员-薪酬结算-详细记录对象 d_salary_detailed_records
 * 
 * @author yangluhe
 * @date 2020-12-21
 */
public class DSalaryDetailedRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 类型 1长途 2短途 */
    private Long type;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Excel(name = "驾驶员类型")
    private String typeName;

    /** 薪酬ID */
    private String salaryId;

    /** 司机ID */
    private String driverId;

    /** 司机名称 */
    @Excel(name = "司机名称")
    private String driverName;

    /** 底薪 */
    @Excel(name = "底薪")
    private BigDecimal basicSalary;

    /** 工龄工资 */
    @Excel(name = "工龄工资")
    private BigDecimal wageForSeniority;

    /** 绩效工资 */
    @Excel(name = "绩效工资")
    private BigDecimal meritSalary;

    /** 加班补助 */
    @Excel(name = "加班补助")
    private BigDecimal overtimePay;

    /** 篷布补助 */
    @Excel(name = "篷布补助")
    private BigDecimal tarpaulin;

    /** 异地等待装卸货补助 */
    @Excel(name = "异地等待装卸货补助")
    private BigDecimal waitPay;

    /** 流量补助 */
    @Excel(name = "流量补助")
    private BigDecimal flowPay;

    /** 其他补助 */
    @Excel(name = "其他补助")
    private BigDecimal otherPay;

    /** 奖励 */
    @Excel(name = "奖励")
    private BigDecimal rewardFee;

    /** 罚款 */
    @Excel(name = "罚款")
    private BigDecimal penaltyFee;

    /** 实发工资 */
    @Excel(name = "实发工资")
    private BigDecimal salary;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 删除状态 1正常 2删除 */
    private Long deleteFlag;


    @JsonFormat(pattern = "yyyy-MM")
    private Date vactionTime;


    public BigDecimal getTarpaulin() {
        return tarpaulin;
    }

    public void setTarpaulin(BigDecimal tarpaulin) {
        this.tarpaulin = tarpaulin;
    }

    public Date getVactionTime() {
        return vactionTime;
    }

    public void setVactionTime(Date vactionTime) {
        this.vactionTime = vactionTime;
    }

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setSalaryId(String salaryId) 
    {
        this.salaryId = salaryId;
    }

    public String getSalaryId() 
    {
        return salaryId;
    }
    public void setDriverId(String driverId) 
    {
        this.driverId = driverId;
    }

    public String getDriverId() 
    {
        return driverId;
    }
    public void setDriverName(String driverName) 
    {
        this.driverName = driverName;
    }

    public String getDriverName() 
    {
        return driverName;
    }
    public void setBasicSalary(BigDecimal basicSalary) 
    {
        this.basicSalary = basicSalary;
    }

    public BigDecimal getBasicSalary() 
    {
        return basicSalary;
    }
    public void setWageForSeniority(BigDecimal wageForSeniority) 
    {
        this.wageForSeniority = wageForSeniority;
    }

    public BigDecimal getWageForSeniority() 
    {
        return wageForSeniority;
    }
    public void setMeritSalary(BigDecimal meritSalary) 
    {
        this.meritSalary = meritSalary;
    }

    public BigDecimal getMeritSalary() 
    {
        return meritSalary;
    }
    public void setOvertimePay(BigDecimal overtimePay) 
    {
        this.overtimePay = overtimePay;
    }

    public BigDecimal getOvertimePay() 
    {
        return overtimePay;
    }
    public void setWaitPay(BigDecimal waitPay) 
    {
        this.waitPay = waitPay;
    }

    public BigDecimal getWaitPay() 
    {
        return waitPay;
    }
    public void setFlowPay(BigDecimal flowPay) 
    {
        this.flowPay = flowPay;
    }

    public BigDecimal getFlowPay() 
    {
        return flowPay;
    }
    public void setOtherPay(BigDecimal otherPay) 
    {
        this.otherPay = otherPay;
    }

    public BigDecimal getOtherPay() 
    {
        return otherPay;
    }
    public void setRewardFee(BigDecimal rewardFee) 
    {
        this.rewardFee = rewardFee;
    }

    public BigDecimal getRewardFee() 
    {
        return rewardFee;
    }
    public void setPenaltyFee(BigDecimal penaltyFee) 
    {
        this.penaltyFee = penaltyFee;
    }

    public BigDecimal getPenaltyFee() 
    {
        return penaltyFee;
    }
    public void setSalary(BigDecimal salary) 
    {
        this.salary = salary;
    }

    public BigDecimal getSalary() 
    {
        return salary;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
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
            .append("type", getType())
            .append("salaryId", getSalaryId())
            .append("driverId", getDriverId())
            .append("driverName", getDriverName())
            .append("basicSalary", getBasicSalary())
            .append("wageForSeniority", getWageForSeniority())
            .append("meritSalary", getMeritSalary())
            .append("overtimePay", getOvertimePay())
            .append("waitPay", getWaitPay())
            .append("flowPay", getFlowPay())
            .append("otherPay", getOtherPay())
            .append("rewardFee", getRewardFee())
            .append("penaltyFee", getPenaltyFee())
            .append("salary", getSalary())
            .append("remarks", getRemarks())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
                .append("vactionTime",getVactionTime())
                .append("typeName",getTypeName())
            .toString();
    }
}
