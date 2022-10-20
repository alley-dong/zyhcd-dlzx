package com.dlzx.pfservercar.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 任务管理-运输任务站点对象 t_transport_task_shipment_stop
 * 
 * @author DongCL
 * @date 2021-01-05
 */
public class TTransportTaskShipmentStop extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 站点ID */
    @Excel(name = "站点ID")
    private String shipmentStopId;

    /** 任务ID（运单ID） */
    @Excel(name = "任务ID", readConverterExp = "运=单ID")
    private String taskId;

    /** 序号 */
    @Excel(name = "序号")
    private String stopNum;

    /** 站点编号 */
    @Excel(name = "站点编号")
    private String shipmentStopXid;

    /** 站点名称 */
    @Excel(name = "站点名称")
    private String shipmentStopName;

    /** 计划离开时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划离开时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date plannedDeparture;

    /** 计划到达时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划到达时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date plannedArrival;

    /** 停靠省 */
    @Excel(name = "停靠省")
    private String locationProvince;

    /** 停靠市 */
    @Excel(name = "停靠市")
    private String locationCity;

    /** 停靠区 */
    @Excel(name = "停靠区")
    private String locationDistrict;

    /** 停靠地址 */
    @Excel(name = "停靠地址")
    private String locationAddress;

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
    public void setShipmentStopId(String shipmentStopId) 
    {
        this.shipmentStopId = shipmentStopId;
    }

    public String getShipmentStopId() 
    {
        return shipmentStopId;
    }
    public void setTaskId(String taskId) 
    {
        this.taskId = taskId;
    }

    public String getTaskId() 
    {
        return taskId;
    }
    public void setStopNum(String stopNum) 
    {
        this.stopNum = stopNum;
    }

    public String getStopNum() 
    {
        return stopNum;
    }
    public void setShipmentStopXid(String shipmentStopXid) 
    {
        this.shipmentStopXid = shipmentStopXid;
    }

    public String getShipmentStopXid() 
    {
        return shipmentStopXid;
    }
    public void setShipmentStopName(String shipmentStopName) 
    {
        this.shipmentStopName = shipmentStopName;
    }

    public String getShipmentStopName() 
    {
        return shipmentStopName;
    }
    public void setPlannedDeparture(Date plannedDeparture) 
    {
        this.plannedDeparture = plannedDeparture;
    }

    public Date getPlannedDeparture() 
    {
        return plannedDeparture;
    }
    public void setPlannedArrival(Date plannedArrival) 
    {
        this.plannedArrival = plannedArrival;
    }

    public Date getPlannedArrival() 
    {
        return plannedArrival;
    }
    public void setLocationProvince(String locationProvince) 
    {
        this.locationProvince = locationProvince;
    }

    public String getLocationProvince() 
    {
        return locationProvince;
    }
    public void setLocationCity(String locationCity) 
    {
        this.locationCity = locationCity;
    }

    public String getLocationCity() 
    {
        return locationCity;
    }
    public void setLocationDistrict(String locationDistrict) 
    {
        this.locationDistrict = locationDistrict;
    }

    public String getLocationDistrict() 
    {
        return locationDistrict;
    }
    public void setLocationAddress(String locationAddress) 
    {
        this.locationAddress = locationAddress;
    }

    public String getLocationAddress() 
    {
        return locationAddress;
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
            .append("shipmentStopId", getShipmentStopId())
            .append("taskId", getTaskId())
            .append("stopNum", getStopNum())
            .append("shipmentStopXid", getShipmentStopXid())
            .append("shipmentStopName", getShipmentStopName())
            .append("plannedDeparture", getPlannedDeparture())
            .append("plannedArrival", getPlannedArrival())
            .append("locationProvince", getLocationProvince())
            .append("locationCity", getLocationCity())
            .append("locationDistrict", getLocationDistrict())
            .append("locationAddress", getLocationAddress())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
