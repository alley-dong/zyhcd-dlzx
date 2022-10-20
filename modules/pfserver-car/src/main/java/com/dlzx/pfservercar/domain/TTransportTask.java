package com.dlzx.pfservercar.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 任务管理-运输任务对象 t_transport_task
 * 
 * @author dlzx
 * @date 2020-12-18
 */
public class TTransportTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 运单号 */
    @Excel(name = "运单号")
    private String shipmentXid;

    /** 运单类型 */
    @Excel(name = "运单类型")
    private Long shipmentType;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String customerName;

    /** 线路ID */
    @Excel(name = "线路ID")
    private String routeId;

    /** 线路名称 */
    @Excel(name = "线路名称")
    private String routeName;

    /** 任务状态 */
    @Excel(name = "任务状态")
    private Long status;

    /** 是否异常任务 1是 2否 */
    @Excel(name = "是否异常任务 1是 2否")
    private Long isException;

    /** 发货联系人 */
    @Excel(name = "发货联系人")
    private String consignor;

    /** 发货联系电话 */
    @Excel(name = "发货联系电话")
    private String consignorTel;

    /** 收货联系人 */
    @Excel(name = "收货联系人")
    private String consignee;

    /** 收货联系电话 */
    @Excel(name = "收货联系电话")
    private String consigneeTel;

    /** 主车ID */
    @Excel(name = "主车ID")
    private String mainVehicleId;

    /** 主车车牌号 */
    @Excel(name = "主车车牌号")
    private String mainLicensePlate;

    /** 主车车型ID */
    @Excel(name = "主车车型ID")
    private String mainVehicleModel;

    /** 主车车型 */
    @Excel(name = "主车车型")
    private String mainVehicleModelName;

    /** 挂车ID */
    @Excel(name = "挂车ID")
    private String hangVehicleId;

    /** 挂车车牌号 */
    @Excel(name = "挂车车牌号")
    private String hangLicensePlate;

    /** 挂车车型ID */
    @Excel(name = "挂车车型ID")
    private String hangVehicleModel;

    /** 挂车车型 */
    @Excel(name = "挂车车型")
    private String hangVehicleModelName;

    /** 司机ID-关联d_driver表 */
    @Excel(name = "司机ID-关联d_driver表")
    private String driverId;

    /** 司机姓名 */
    @Excel(name = "司机姓名")
    private String driverName;

    /** 副司机ID-关联d_driver表 */
    @Excel(name = "副司机ID-关联d_driver表")
    private String viceDriverId;

    /** 副司机姓名 */
    @Excel(name = "副司机姓名")
    private String viceDriverName;

    /** 计划提货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "计划提货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date pickTime;

    /** 计划送达时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "计划送达时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planDeliveryTime;

    /** 总数量 */
    @Excel(name = "总数量")
    private Long totalCount;

    /** 总重量 */
    @Excel(name = "总重量")
    private Long totalWeight;

    /** 总体积 */
    @Excel(name = "总体积")
    private Long totalVolume;

    /** 总包装数量 */
    @Excel(name = "总包装数量")
    private Long totalPackCount;

    /** 发车点检次数 */
    @Excel(name = "发车点检次数")
    private Long startInspectCount;

    /** 在途点检次数 */
    @Excel(name = "在途点检次数")
    private Long processInspectCount;

    /** 在途最小点检次数 */
    @Excel(name = "在途最小点检次数")
    private Long processInspectMinCount;

    /** 归队点检次数 */
    @Excel(name = "归队点检次数")
    private Long endInspectCount;

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

    /** 业务部门 */
    @Excel(name = "业务部门")
    private String businessUnitName;

    /** 承运商名称 */
    @Excel(name = "承运商名称")
    private String servprovName;

    /** 业务类型 1本社 2外包 */
    @Excel(name = "业务类型")
    private Long businessType;

    /** 运费含税总金额 */
    @Excel(name = "运费含税总金额")
    private BigDecimal totalAmount;

    /** 不含税总金额 */
    @Excel(name = "不含税总金额")
    private BigDecimal amount;

    /** 合计费用 */
    @Excel(name = "合计费用")
    private BigDecimal totalCost;


    /*业务*/
    private String bType;

    /*计费方式*/
    private String fType;

    /*任务收入*/
    private BigDecimal tAmount;

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

    public String getbType() {
        return bType;
    }

    public void setbType(String bType) {
        this.bType = bType;
    }

    public String getfType() {
        return fType;
    }

    public void setfType(String fType) {
        this.fType = fType;
    }

    public BigDecimal gettAmount() {
        return tAmount;
    }

    public void settAmount(BigDecimal tAmount) {
        this.tAmount = tAmount;
    }

    public String getBusinessUnitName() {
        return businessUnitName;
    }

    public void setBusinessUnitName(String businessUnitName) {
        this.businessUnitName = businessUnitName;
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
    public void setShipmentType(Long shipmentType) 
    {
        this.shipmentType = shipmentType;
    }

    public Long getShipmentType() 
    {
        return shipmentType;
    }
    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public String getCustomerName() 
    {
        return customerName;
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
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setIsException(Long isException) 
    {
        this.isException = isException;
    }

    public Long getIsException() 
    {
        return isException;
    }
    public void setConsignor(String consignor) 
    {
        this.consignor = consignor;
    }

    public String getConsignor() 
    {
        return consignor;
    }
    public void setConsignorTel(String consignorTel) 
    {
        this.consignorTel = consignorTel;
    }

    public String getConsignorTel() 
    {
        return consignorTel;
    }
    public void setConsignee(String consignee) 
    {
        this.consignee = consignee;
    }

    public String getConsignee() 
    {
        return consignee;
    }
    public void setConsigneeTel(String consigneeTel) 
    {
        this.consigneeTel = consigneeTel;
    }

    public String getConsigneeTel() 
    {
        return consigneeTel;
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
    public void setMainVehicleModel(String mainVehicleModel) 
    {
        this.mainVehicleModel = mainVehicleModel;
    }

    public String getMainVehicleModel() 
    {
        return mainVehicleModel;
    }
    public void setMainVehicleModelName(String mainVehicleModelName) 
    {
        this.mainVehicleModelName = mainVehicleModelName;
    }

    public String getMainVehicleModelName() 
    {
        return mainVehicleModelName;
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
    public void setHangVehicleModel(String hangVehicleModel) 
    {
        this.hangVehicleModel = hangVehicleModel;
    }

    public String getHangVehicleModel() 
    {
        return hangVehicleModel;
    }
    public void setHangVehicleModelName(String hangVehicleModelName) 
    {
        this.hangVehicleModelName = hangVehicleModelName;
    }

    public String getHangVehicleModelName() 
    {
        return hangVehicleModelName;
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
    public void setViceDriverId(String viceDriverId) 
    {
        this.viceDriverId = viceDriverId;
    }

    public String getViceDriverId() 
    {
        return viceDriverId;
    }
    public void setViceDriverName(String viceDriverName) 
    {
        this.viceDriverName = viceDriverName;
    }

    public String getViceDriverName() 
    {
        return viceDriverName;
    }
    public void setPickTime(Date pickTime) 
    {
        this.pickTime = pickTime;
    }

    public Date getPickTime()
    {
        return pickTime;
    }
    public void setPlanDeliveryTime(Date planDeliveryTime)
    {
        this.planDeliveryTime = planDeliveryTime;
    }

    public Date getPlanDeliveryTime() 
    {
        return planDeliveryTime;
    }
    public void setTotalCount(Long totalCount) 
    {
        this.totalCount = totalCount;
    }

    public Long getTotalCount() 
    {
        return totalCount;
    }
    public void setTotalWeight(Long totalWeight) 
    {
        this.totalWeight = totalWeight;
    }

    public Long getTotalWeight() 
    {
        return totalWeight;
    }
    public void setTotalVolume(Long totalVolume) 
    {
        this.totalVolume = totalVolume;
    }

    public Long getTotalVolume() 
    {
        return totalVolume;
    }
    public void setTotalPackCount(Long totalPackCount) 
    {
        this.totalPackCount = totalPackCount;
    }

    public Long getTotalPackCount() 
    {
        return totalPackCount;
    }
    public void setStartInspectCount(Long startInspectCount) 
    {
        this.startInspectCount = startInspectCount;
    }

    public Long getStartInspectCount() 
    {
        return startInspectCount;
    }
    public void setProcessInspectCount(Long processInspectCount) 
    {
        this.processInspectCount = processInspectCount;
    }

    public Long getProcessInspectCount() 
    {
        return processInspectCount;
    }
    public void setProcessInspectMinCount(Long processInspectMinCount) 
    {
        this.processInspectMinCount = processInspectMinCount;
    }

    public Long getProcessInspectMinCount() 
    {
        return processInspectMinCount;
    }
    public void setEndInspectCount(Long endInspectCount) 
    {
        this.endInspectCount = endInspectCount;
    }

    public Long getEndInspectCount() 
    {
        return endInspectCount;
    }
    public void setDeleteFlag(Long deleteFlag) 
    {
        this.deleteFlag = deleteFlag;
    }

    public Long getDeleteFlag() 
    {
        return deleteFlag;
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
            .append("id", getId())
            .append("shipmentXid", getShipmentXid())
            .append("shipmentType", getShipmentType())
            .append("customerName", getCustomerName())
            .append("routeId", getRouteId())
            .append("routeName", getRouteName())
            .append("status", getStatus())
            .append("isException", getIsException())
            .append("consignor", getConsignor())
            .append("consignorTel", getConsignorTel())
            .append("consignee", getConsignee())
            .append("consigneeTel", getConsigneeTel())
            .append("mainVehicleId", getMainVehicleId())
            .append("mainLicensePlate", getMainLicensePlate())
            .append("mainVehicleModel", getMainVehicleModel())
            .append("mainVehicleModelName", getMainVehicleModelName())
            .append("hangVehicleId", getHangVehicleId())
            .append("hangLicensePlate", getHangLicensePlate())
            .append("hangVehicleModel", getHangVehicleModel())
            .append("hangVehicleModelName", getHangVehicleModelName())
            .append("driverId", getDriverId())
            .append("driverName", getDriverName())
            .append("viceDriverId", getViceDriverId())
            .append("viceDriverName", getViceDriverName())
            .append("pickTime", getPickTime())
            .append("planDeliveryTime", getPlanDeliveryTime())
            .append("totalCount", getTotalCount())
            .append("totalWeight", getTotalWeight())
            .append("totalVolume", getTotalVolume())
            .append("totalPackCount", getTotalPackCount())
            .append("startInspectCount", getStartInspectCount())
            .append("processInspectCount", getProcessInspectCount())
            .append("processInspectMinCount", getProcessInspectMinCount())
            .append("endInspectCount", getEndInspectCount())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("taskStartTime", getTaskStartTime())
            .append("taskEndTime", getTaskEndTime())
            .append("businessUnitName", getBusinessUnitName())
            .append("servprovName", getServprovName())
            .append("businessType", getBusinessType())
            .append("totalAmount", getTotalAmount())
            .append("amount", getAmount())
            .append("totalCost", getTotalCost())
            .toString();
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}
