package com.dlzx.job.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 车辆管理-车辆定检记录车辆对象 c_vehicle_inspect_monthly_item
 * 
 * @author dlzx
 * @date 2020-12-31
 */
public class CVehicleInspectMonthlyItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 定检发布ID 关联c_vehicle_inspect_monthly表 */
    @Excel(name = "定检发布ID 关联c_vehicle_inspect_monthly表")
    private String monthlyId;

    /** 车辆ID */
    @Excel(name = "车辆ID")
    private String vehicleId;

    /** 车牌号码 */
    @Excel(name = "车牌号码")
    private String licensePlate;

    /** 定检人ID */
    @Excel(name = "定检人ID")
    private String driverId;

    /** 定检人名称 */
    @Excel(name = "定检人名称")
    private String driverName;

    /** 定检时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "定检时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inspectDate;

    /** 状态 1未完成 2已完成 */
    @Excel(name = "状态 1未完成 2已完成")
    private Long status;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setMonthlyId(String monthlyId) 
    {
        this.monthlyId = monthlyId;
    }

    public String getMonthlyId() 
    {
        return monthlyId;
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
    public void setInspectDate(Date inspectDate) 
    {
        this.inspectDate = inspectDate;
    }

    public Date getInspectDate() 
    {
        return inspectDate;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
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
            .append("monthlyId", getMonthlyId())
            .append("vehicleId", getVehicleId())
            .append("licensePlate", getLicensePlate())
            .append("driverId", getDriverId())
            .append("driverName", getDriverName())
            .append("inspectDate", getInspectDate())
            .append("status", getStatus())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
                .append("monthlyId",getMonthlyId())
            .toString();
    }
}
