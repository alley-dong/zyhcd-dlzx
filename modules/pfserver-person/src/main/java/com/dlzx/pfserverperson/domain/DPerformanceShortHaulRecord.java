package com.dlzx.pfserverperson.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 驾驶员-短途绩效记录对象 d_performance_short_haul_record
 * 
 * @author dlzx
 * @date 2020-12-21
 */
public class DPerformanceShortHaulRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 绩效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "绩效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date performanceDate;

    /** 绩效日期（后台搜索使用） */
    @JsonFormat(pattern = "yyyy-MM")
    private Date performanceFromDate;

    /** 驾驶员ID */
    @Excel(name = "驾驶员ID")
    private String driverId;

    /** 驾驶员名称 */
    @Excel(name = "驾驶员名称")
    private String driverName;

    /** 任务ID */
    @Excel(name = "任务ID")
    private String taskId;

    /** 驾驶员类型ID */
    @Excel(name = "驾驶员类型ID")
    private String driverTypeId;

    /** 驾驶员类型名称 */
    @Excel(name = "驾驶员类型名称")
    private String driverTypeName;

    /** 车辆ID 关联c_vehicle表 */
    @Excel(name = "车辆ID 关联c_vehicle表")
    private String vehicleId;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String licensePlate;

    /** 台班单价 */
    @Excel(name = "台班单价")
    private BigDecimal unitPrice;

    /** 台班量 */
    @Excel(name = "台班量")
    private Long number;

    /** 绩效金额 */
    @Excel(name = "绩效金额")
    private BigDecimal amount;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag = 1L;

    /**绩效总额*/
    private BigDecimal amo;

    public BigDecimal getAmo() {
        return amo;
    }

    public void setAmo(BigDecimal amo) {
        this.amo = amo;
    }

    /**开始时间*/
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date shortStartTime;

    /**结束时间*/
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date shortEndTime;

    public Date getShortStartTime() {
        return shortStartTime;
    }

    public Date getPerformanceFromDate() {
        return performanceFromDate;
    }

    public void setPerformanceFromDate(Date performanceFromDate) {
        this.performanceFromDate = performanceFromDate;
    }

    public void setShortStartTime(Date shortStartTime) {
        this.shortStartTime = shortStartTime;
    }

    public Date getShortEndTime() {
        return shortEndTime;
    }

    public void setShortEndTime(Date shortEndTime) {
        this.shortEndTime = shortEndTime;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setPerformanceDate(Date performanceDate) 
    {
        this.performanceDate = performanceDate;
    }

    public Date getPerformanceDate() 
    {
        return performanceDate;
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
    public void setTaskId(String taskId) 
    {
        this.taskId = taskId;
    }

    public String getTaskId() 
    {
        return taskId;
    }
    public void setDriverTypeId(String driverTypeId) 
    {
        this.driverTypeId = driverTypeId;
    }

    public String getDriverTypeId() 
    {
        return driverTypeId;
    }
    public void setDriverTypeName(String driverTypeName) 
    {
        this.driverTypeName = driverTypeName;
    }

    public String getDriverTypeName() 
    {
        return driverTypeName;
    }
    public void setVehicleId(String vehicleId) 
    {
        this.vehicleId = vehicleId;
    }

    public String getVehicleId() 
    {
        return vehicleId;
    }
    public void setLicensePlate(String licensePlate) 
    {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() 
    {
        return licensePlate;
    }
    public void setUnitPrice(BigDecimal unitPrice) 
    {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitPrice() 
    {
        return unitPrice;
    }
    public void setNumber(Long number) 
    {
        this.number = number;
    }

    public Long getNumber() 
    {
        return number;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
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
            .append("performanceDate", getPerformanceDate())
            .append("driverId", getDriverId())
            .append("driverName", getDriverName())
            .append("taskId", getTaskId())
            .append("driverTypeId", getDriverTypeId())
            .append("driverTypeName", getDriverTypeName())
            .append("vehicleId", getVehicleId())
            .append("licensePlate", getLicensePlate())
            .append("unitPrice", getUnitPrice())
            .append("number", getNumber())
            .append("amount", getAmount())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
                .append("amo",getAmo())
                .append("shortStartTime",getShortStartTime())
                .append("shortEndTime",getShortEndTime())
            .toString();
    }
}
