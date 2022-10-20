package com.dlzx.pfservercar.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 *  任务管理-运输任务加班对象 t_transport_task_overtime
 * 
 * @author dcl
 * @date 2020-12-18
 */
public class TTransportTaskOvertime extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 运单号 */
    @Excel(name = "运单号")
    private String shipmentXid;

    /** 干线运单号 */
    @Excel(name = "干线运单号")
    private String arteryShipmentXid;

    /** 主车ID */
    @Excel(name = "主车ID")
    private String mainVehicleId;

    /** 主车车牌号 */
    @Excel(name = "主车车牌号")
    private String mainLicensePlate;

    /** 挂车ID */
    @Excel(name = "挂车ID")
    private String hangVehicleId;

    /** 挂车车牌号 */
    @Excel(name = "挂车车牌号")
    private String hangLicensePlate;

    /** 司机ID-关联d_driver表 */
    @Excel(name = "司机ID-关联d_driver表")
    private String driverId;

    /** 司机姓名 */
    @Excel(name = "司机姓名")
    private String driverName;

    /** 油料费 */
    @Excel(name = "油料费")
    private BigDecimal oilAmount;

    /** 加班费 */
    @Excel(name = "加班费")
    private BigDecimal overtimeAmount;

    /** 到货起点 */
    @Excel(name = "到货起点")
    private String origin;

    /** 倒货终点 */
    @Excel(name = "倒货终点")
    private String destination;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag = 1L;

    /** 任务开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskStartTime;

    /** 任务结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskEndTime;


    public Date getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(Date taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public Date getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(Date taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setShipmentXid(String shipmentXid) 
    {
        this.shipmentXid = shipmentXid;
    }

    public String getShipmentXid() 
    {
        return shipmentXid;
    }
    public void setArteryShipmentXid(String arteryShipmentXid) 
    {
        this.arteryShipmentXid = arteryShipmentXid;
    }

    public String getArteryShipmentXid() 
    {
        return arteryShipmentXid;
    }
    public void setMainVehicleId(String mainVehicleId) 
    {
        this.mainVehicleId = mainVehicleId;
    }

    public String getMainVehicleId() 
    {
        return mainVehicleId;
    }
    public void setMainLicensePlate(String mainLicensePlate) 
    {
        this.mainLicensePlate = mainLicensePlate;
    }

    public String getMainLicensePlate() 
    {
        return mainLicensePlate;
    }
    public void setHangVehicleId(String hangVehicleId) 
    {
        this.hangVehicleId = hangVehicleId;
    }

    public String getHangVehicleId() 
    {
        return hangVehicleId;
    }
    public void setHangLicensePlate(String hangLicensePlate) 
    {
        this.hangLicensePlate = hangLicensePlate;
    }

    public String getHangLicensePlate() 
    {
        return hangLicensePlate;
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
    public void setOilAmount(BigDecimal oilAmount) 
    {
        this.oilAmount = oilAmount;
    }

    public BigDecimal getOilAmount() 
    {
        return oilAmount;
    }
    public void setOvertimeAmount(BigDecimal overtimeAmount) 
    {
        this.overtimeAmount = overtimeAmount;
    }

    public BigDecimal getOvertimeAmount() 
    {
        return overtimeAmount;
    }
    public void setOrigin(String origin) 
    {
        this.origin = origin;
    }

    public String getOrigin() 
    {
        return origin;
    }
    public void setDestination(String destination) 
    {
        this.destination = destination;
    }

    public String getDestination() 
    {
        return destination;
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
            .append("shipmentXid", getShipmentXid())
            .append("arteryShipmentXid", getArteryShipmentXid())
            .append("mainVehicleId", getMainVehicleId())
            .append("mainLicensePlate", getMainLicensePlate())
            .append("hangVehicleId", getHangVehicleId())
            .append("hangLicensePlate", getHangLicensePlate())
            .append("driverId", getDriverId())
            .append("driverName", getDriverName())
            .append("oilAmount", getOilAmount())
            .append("overtimeAmount", getOvertimeAmount())
            .append("origin", getOrigin())
            .append("destination", getDestination())
            .append("deleteFlag", getDeleteFlag())
            .toString();
    }
}
