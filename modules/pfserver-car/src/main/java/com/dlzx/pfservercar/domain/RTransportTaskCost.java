package com.dlzx.pfservercar.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 统计报-任务成本核算对象 r_transport_task_cost
 * 
 * @author ylh
 * @date 2021-01-05
 */
public class RTransportTaskCost extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 任务ID */
//    @Excel(name = "任务ID")
    private String taskId;

    /** 运单号 */
    @Excel(name = "运单号")
    private String shipmentXid;

    /** 订单ID */
//    @Excel(name = "订单ID")
    private String orderId;

    /** 订单号 */
//    @Excel(name = "订单号")
    private String orderNumber;

    /** 线路 */
    @Excel(name = "线路")
    private String routeName;

    /** 业务类型 */
    @Excel(name = "业务类型")
    private String businessType;

    /** 主车车牌号 */
    @Excel(name = "主车车牌号")
    private String mainLicensePlate;

    /** 挂车车牌号 */
    @Excel(name = "挂车车牌号")
    private String hangLicensePlate;

    /** 驾驶员工资 */
    @Excel(name = "驾驶员工资")
    private BigDecimal driverPay;

    /** 管理人员薪酬 */
    @Excel(name = "管理人员薪酬")
    private BigDecimal adminPay;

    /** 外包服务费 */
    @Excel(name = "外包服务费")
    private BigDecimal serviceCost;

    /** ETC费用 */
    @Excel(name = "ETC费用")
    private BigDecimal etcCost;

    /** 燃料费用 */
    @Excel(name = "燃料费用")
    private BigDecimal fuelCost;

    /** 检车费 */
    @Excel(name = "检车费")
    private BigDecimal inspectionFee;

    /** 保险费 */
    @Excel(name = "保险费")
    private BigDecimal insurance;

    /** 维保费用 */
    @Excel(name = "维保费用")
    private BigDecimal maintenanceCost;

    @Excel(name = "尿素费用")
    private BigDecimal ureaCost;

    /** 轮胎费用 */
    @Excel(name = "轮胎费用")
    private BigDecimal trieCost;

    /** 低值易耗品费用 */
    @Excel(name = "低值易耗品费用")
    private BigDecimal consumablesCost;

    /** 折旧费用 */
    @Excel(name = "折旧费用")
    private BigDecimal depreciationCost;

    /** 信息系统服务费 */
    @Excel(name = "信息系统服务费")
    private BigDecimal systemCost;

    /** 合计费用 */
    @Excel(name = "合计费用")
    private BigDecimal totalCost;

    /** 计费方式 */
    @Excel(name = "计费方式")
    private String feeType;

    /** 任务收入 */
    @Excel(name = "任务收入")
    private BigDecimal taskCost;

    /** 盈亏 1盘盈 2盘亏 */
//    @Excel(name = "盈亏 1盘盈 2盘亏")
    private Long profitStatus;

    public String getProfitName() {
        return profitName;
    }

    public void setProfitName(String profitName) {
        this.profitName = profitName;
    }

    private String profitName;

    /** 盈亏费用 */
    @Excel(name = "盈亏费用")
    private BigDecimal profitAndLoss;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date taskEndTime;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startTime;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date finshTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinshTime() {
        return finshTime;
    }

    public void setFinshTime(Date finshTime) {
        this.finshTime = finshTime;
    }

    public Date getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(Date taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public BigDecimal getUreaCost() {
        return ureaCost;
    }

    public void setUreaCost(BigDecimal ureaCost) {
        this.ureaCost = ureaCost;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setTaskId(String taskId) 
    {
        this.taskId = taskId;
    }

    public String getTaskId() 
    {
        return taskId;
    }
    public void setShipmentXid(String shipmentXid) 
    {
        this.shipmentXid = shipmentXid;
    }

    public String getShipmentXid() 
    {
        return shipmentXid;
    }
    public void setOrderId(String orderId) 
    {
        this.orderId = orderId;
    }

    public String getOrderId() 
    {
        return orderId;
    }
    public void setOrderNumber(String orderNumber) 
    {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() 
    {
        return orderNumber;
    }
    public void setRouteName(String routeName) 
    {
        this.routeName = routeName;
    }

    public String getRouteName() 
    {
        return routeName;
    }
    public void setBusinessType(String businessType) 
    {
        this.businessType = businessType;
    }

    public String getBusinessType() 
    {
        return businessType;
    }
    public void setMainLicensePlate(String mainLicensePlate) 
    {
        this.mainLicensePlate = mainLicensePlate;
    }

    public String getMainLicensePlate() 
    {
        return mainLicensePlate;
    }
    public void setHangLicensePlate(String hangLicensePlate) 
    {
        this.hangLicensePlate = hangLicensePlate;
    }

    public String getHangLicensePlate() 
    {
        return hangLicensePlate;
    }
    public void setDriverPay(BigDecimal driverPay) 
    {
        this.driverPay = driverPay;
    }

    public BigDecimal getDriverPay() 
    {
        return driverPay;
    }
    public void setAdminPay(BigDecimal adminPay) 
    {
        this.adminPay = adminPay;
    }

    public BigDecimal getAdminPay() 
    {
        return adminPay;
    }
    public void setServiceCost(BigDecimal serviceCost) 
    {
        this.serviceCost = serviceCost;
    }

    public BigDecimal getServiceCost() 
    {
        return serviceCost;
    }
    public void setEtcCost(BigDecimal etcCost) 
    {
        this.etcCost = etcCost;
    }

    public BigDecimal getEtcCost() 
    {
        return etcCost;
    }
    public void setFuelCost(BigDecimal fuelCost) 
    {
        this.fuelCost = fuelCost;
    }

    public BigDecimal getFuelCost() 
    {
        return fuelCost;
    }
    public void setInspectionFee(BigDecimal inspectionFee) 
    {
        this.inspectionFee = inspectionFee;
    }

    public BigDecimal getInspectionFee() 
    {
        return inspectionFee;
    }
    public void setInsurance(BigDecimal insurance) 
    {
        this.insurance = insurance;
    }

    public BigDecimal getInsurance() 
    {
        return insurance;
    }
    public void setMaintenanceCost(BigDecimal maintenanceCost) 
    {
        this.maintenanceCost = maintenanceCost;
    }

    public BigDecimal getMaintenanceCost() 
    {
        return maintenanceCost;
    }
    public void setTrieCost(BigDecimal trieCost) 
    {
        this.trieCost = trieCost;
    }

    public BigDecimal getTrieCost() 
    {
        return trieCost;
    }
    public void setConsumablesCost(BigDecimal consumablesCost) 
    {
        this.consumablesCost = consumablesCost;
    }

    public BigDecimal getConsumablesCost() 
    {
        return consumablesCost;
    }
    public void setDepreciationCost(BigDecimal depreciationCost) 
    {
        this.depreciationCost = depreciationCost;
    }

    public BigDecimal getDepreciationCost() 
    {
        return depreciationCost;
    }
    public void setSystemCost(BigDecimal systemCost) 
    {
        this.systemCost = systemCost;
    }

    public BigDecimal getSystemCost() 
    {
        return systemCost;
    }
    public void setTotalCost(BigDecimal totalCost) 
    {
        this.totalCost = totalCost;
    }

    public BigDecimal getTotalCost() 
    {
        return totalCost;
    }
    public void setFeeType(String feeType)
    {
        this.feeType = feeType;
    }

    public String getFeeType()
    {
        return feeType;
    }
    public void setTaskCost(BigDecimal taskCost) 
    {
        this.taskCost = taskCost;
    }

    public BigDecimal getTaskCost() 
    {
        return taskCost;
    }
    public void setProfitStatus(Long profitStatus) 
    {
        this.profitStatus = profitStatus;
    }

    public Long getProfitStatus() 
    {
        return profitStatus;
    }
    public void setProfitAndLoss(BigDecimal profitAndLoss) 
    {
        this.profitAndLoss = profitAndLoss;
    }

    public BigDecimal getProfitAndLoss() 
    {
        return profitAndLoss;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskId", getTaskId())
            .append("shipmentXid", getShipmentXid())
            .append("orderId", getOrderId())
            .append("orderNumber", getOrderNumber())
            .append("routeName", getRouteName())
            .append("businessType", getBusinessType())
            .append("mainLicensePlate", getMainLicensePlate())
            .append("hangLicensePlate", getHangLicensePlate())
            .append("driverPay", getDriverPay())
            .append("adminPay", getAdminPay())
            .append("serviceCost", getServiceCost())
            .append("etcCost", getEtcCost())
            .append("fuelCost", getFuelCost())
            .append("inspectionFee", getInspectionFee())
            .append("insurance", getInsurance())
            .append("maintenanceCost", getMaintenanceCost())
            .append("trieCost", getTrieCost())
            .append("consumablesCost", getConsumablesCost())
            .append("depreciationCost", getDepreciationCost())
            .append("systemCost", getSystemCost())
            .append("totalCost", getTotalCost())
            .append("feeType", getFeeType())
            .append("taskCost", getTaskCost())
            .append("profitStatus", getProfitStatus())
            .append("profitAndLoss", getProfitAndLoss())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
                .append("ureaCost",getUreaCost())
            .toString();
    }
}
