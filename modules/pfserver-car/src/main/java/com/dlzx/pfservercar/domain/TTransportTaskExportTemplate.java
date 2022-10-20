package com.dlzx.pfservercar.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 任务管理-运输任务对象 t_transport_task
 * 
 * @author dlzx
 * @date 2020-12-18
 */
public class TTransportTaskExportTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 运单号 */
    @Excel(name = "运单号")
    private String shipmentXid;

    /** 线路ID */
    private String routeId;

    /** 业务类型 1本社 2外包 */
    @Excel(name = "业务")
    private Long businessType;

    /** 线路名称 */
    @Excel(name = "线路")
    private String routeName;

    /** 任务开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date taskStartTime;

    /** 任务结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date taskEndTime;

    /** 承运商名称 */
    @Excel(name = "承运商")
    private String servprovName;

    /** 不含税总金额 */
    @Excel(name = "运费（元）")
    private BigDecimal amount;

    public BigDecimal getProportion() {
        return proportion;
    }

    public void setProportion(BigDecimal proportion) {
        this.proportion = proportion;
    }

    /*比例*/
    private BigDecimal proportion;

    private String mileage;

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getServprovName() {
        return servprovName;
    }

    public void setServprovName(String servprovName) {
        this.servprovName = servprovName;
    }

    public Long getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Long businessType) {
        this.businessType = businessType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setShipmentXid(String shipmentXid)
    {
        this.shipmentXid = shipmentXid;
    }

    public String getShipmentXid() 
    {
        return shipmentXid;
    }

    public void setRouteId(String routeId) 
    {
        this.routeId = routeId;
    }

    public String getRouteId() 
    {
        return routeId;
    }
    public void setRouteName(String routeName) 
    {
        this.routeName = routeName;
    }

    public String getRouteName() 
    {
        return routeName;
    }

    public void setTaskStartTime(Date taskStartTime)
    {
        this.taskStartTime = taskStartTime;
    }

    public Date getTaskStartTime() 
    {
        return taskStartTime;
    }
    public void setTaskEndTime(Date taskEndTime) 
    {
        this.taskEndTime = taskEndTime;
    }

    public Date getTaskEndTime() 
    {
        return taskEndTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("shipmentXid", getShipmentXid())
            .append("routeId", getRouteId())
            .append("routeName", getRouteName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("taskStartTime", getTaskStartTime())
            .append("taskEndTime", getTaskEndTime())
            .append("servprovName", getServprovName())
            .append("businessType", getBusinessType())
            .append("amount", getAmount())
            .toString();
    }
}
