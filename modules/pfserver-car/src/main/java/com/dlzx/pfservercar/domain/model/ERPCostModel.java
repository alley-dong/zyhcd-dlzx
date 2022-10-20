package com.dlzx.pfservercar.domain.model;

import com.dlzx.common.core.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 类描述：费用上报
 * @className ERPCostModel
 * company 赛拓信息技术有限公司
 * @author DongCL
 * @date 2021-02-21 13:31
 */
public class ERPCostModel {


    /* 入参 */
    /**
     * 费用类型
     * */
    private String type;

    /**
     * 起始时间
     * */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    /**
     * 终止时间
     * */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;





    /* 公用 */
    /**
     * 车牌号
     * */
    private String licensePlate;

    /**
     * 数量（尿素、维修保养、轮胎）
     */
    private Long number;

    /**
     * 单价（尿素、维修保养、轮胎）
     */
    private BigDecimal unitPrice;

    /**
     * 金额（维修保养、信息系统服务费、轮胎、尿素、新购车辆临时号牌费）
     */
    private BigDecimal amount;





    /* 维修保养 */
    /**
     * 主车牌号
     */
    private String mainLicensePlate;

    /**
     * 挂车牌号
     */
    private String hangLicensePlate;

    /**
     * 维修站名称
     */
    private String garageName;

    /**
     * 维修时间
     * */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date completeTime;

    /**
     * 名称
     */
    private String name;

    /**
     * 单位
     */
    private String unit;

    /**
     * 工时数
     */
    private BigDecimal duration;





    /* 保险费 */
    /**
     * 保险类型
     */
    private String typeName;

    /**
     * 保险种类
     */
    private String insuranceTypeName;

    /**
     * 保费（元）
     *  */
    private BigDecimal insuranceCost;

    /**
     * 起保日期
     * */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String insuranceStartDateExport;

    /**
     * 终保日期
     * */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String insuranceEndDateExport;

    /**
     * 办理保险日期
     * */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String insuranceHandleTimeExport;





    /* 燃料费 */

    /** 油品 */
    private String oils;

    /** 油量 */
    private BigDecimal oilQuantity;

    /** 单价（元含税） */
    private BigDecimal taxUnitPrice;

    /** 加油金额（含税） */
    private BigDecimal taxAmount;

    /** 交易时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date transactionTime;

    /** 单价（元  不含税） */
    private BigDecimal fuelUnitPrice;

    /** 加油金额（元 不含税） */
    private BigDecimal fuelAmount;





    /* ETC费用 */

    /** 金额（含税） */
    private BigDecimal etcTaxAmount;

    /** 金额（元 不含税） */
    private BigDecimal etcAmount;

    /** 税额 */
    private BigDecimal tax;

    /** 报销时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date reimburseTime;





    /* 检车费*/

    /**
     * 检车日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date inspectDate;

    /**
     * 车辆年检费用
     */
    private BigDecimal vehiceInspectCost;

    /**
     * 运营年检费用
     */
    private BigDecimal operateInspectCost;

    /**
     * 复检费用
     */
    private BigDecimal reviewCost;

    /**
     * 合计费用
     */
    private BigDecimal totalCost;





    /** 折旧费*/
    private BigDecimal depreciationCost;

    /** 折旧时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date depreciationMonth;




    /* 尿素、轮胎费用 */
    /** 规格 */
    private String specification;

    /** 领用时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date receiveTime;





    /* 信息系统服务费 */
    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    /** 截止时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date closeTime;

    /** 系统名称 */
    private String systemName;





    /* 新购车辆临时号牌费 */
    /** 车辆型号-名称 */
    @Excel(name = "车辆型号-名称")
    private String vehicleModelName;

    /** 项目 */
    @Excel(name = "项目")
    private String projectName;

    /** 购买时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "购买时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date purchasesTime;





    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMainLicensePlate() {
        return mainLicensePlate;
    }

    public void setMainLicensePlate(String mainLicensePlate) {
        this.mainLicensePlate = mainLicensePlate;
    }

    public String getHangLicensePlate() {
        return hangLicensePlate;
    }

    public void setHangLicensePlate(String hangLicensePlate) {
        this.hangLicensePlate = hangLicensePlate;
    }

    public String getGarageName() {
        return garageName;
    }

    public void setGarageName(String garageName) {
        this.garageName = garageName;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getInsuranceTypeName() {
        return insuranceTypeName;
    }

    public void setInsuranceTypeName(String insuranceTypeName) {
        this.insuranceTypeName = insuranceTypeName;
    }

    public BigDecimal getInsuranceCost() {
        return insuranceCost;
    }

    public void setInsuranceCost(BigDecimal insuranceCost) {
        this.insuranceCost = insuranceCost;
    }

    public String getInsuranceStartDateExport() {
        return insuranceStartDateExport;
    }

    public void setInsuranceStartDateExport(String insuranceStartDateExport) {
        this.insuranceStartDateExport = insuranceStartDateExport;
    }

    public String getInsuranceEndDateExport() {
        return insuranceEndDateExport;
    }

    public void setInsuranceEndDateExport(String insuranceEndDateExport) {
        this.insuranceEndDateExport = insuranceEndDateExport;
    }

    public String getInsuranceHandleTimeExport() {
        return insuranceHandleTimeExport;
    }

    public void setInsuranceHandleTimeExport(String insuranceHandleTimeExport) {
        this.insuranceHandleTimeExport = insuranceHandleTimeExport;
    }

    public String getOils() {
        return oils;
    }

    public void setOils(String oils) {
        this.oils = oils;
    }

    public BigDecimal getOilQuantity() {
        return oilQuantity;
    }

    public void setOilQuantity(BigDecimal oilQuantity) {
        this.oilQuantity = oilQuantity;
    }

    public BigDecimal getTaxUnitPrice() {
        return taxUnitPrice;
    }

    public void setTaxUnitPrice(BigDecimal taxUnitPrice) {
        this.taxUnitPrice = taxUnitPrice;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public BigDecimal getFuelUnitPrice() {
        return fuelUnitPrice;
    }

    public void setFuelUnitPrice(BigDecimal fuelUnitPrice) {
        this.fuelUnitPrice = fuelUnitPrice;
    }

    public BigDecimal getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(BigDecimal fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public BigDecimal getEtcTaxAmount() {
        return etcTaxAmount;
    }

    public void setEtcTaxAmount(BigDecimal etcTaxAmount) {
        this.etcTaxAmount = etcTaxAmount;
    }

    public BigDecimal getEtcAmount() {
        return etcAmount;
    }

    public void setEtcAmount(BigDecimal etcAmount) {
        this.etcAmount = etcAmount;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public Date getReimburseTime() {
        return reimburseTime;
    }

    public void setReimburseTime(Date reimburseTime) {
        this.reimburseTime = reimburseTime;
    }

    public Date getInspectDate() {
        return inspectDate;
    }

    public void setInspectDate(Date inspectDate) {
        this.inspectDate = inspectDate;
    }

    public BigDecimal getVehiceInspectCost() {
        return vehiceInspectCost;
    }

    public void setVehiceInspectCost(BigDecimal vehiceInspectCost) {
        this.vehiceInspectCost = vehiceInspectCost;
    }

    public BigDecimal getOperateInspectCost() {
        return operateInspectCost;
    }

    public void setOperateInspectCost(BigDecimal operateInspectCost) {
        this.operateInspectCost = operateInspectCost;
    }

    public BigDecimal getReviewCost() {
        return reviewCost;
    }

    public void setReviewCost(BigDecimal reviewCost) {
        this.reviewCost = reviewCost;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getVehicleModelName() {
        return vehicleModelName;
    }

    public void setVehicleModelName(String vehicleModelName) {
        this.vehicleModelName = vehicleModelName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getPurchasesTime() {
        return purchasesTime;
    }

    public void setPurchasesTime(Date purchasesTime) {
        this.purchasesTime = purchasesTime;
    }

    public BigDecimal getDepreciationCost() {
        return depreciationCost;
    }

    public void setDepreciationCost(BigDecimal depreciationCost) {
        this.depreciationCost = depreciationCost;
    }

    public Date getDepreciationMonth() {
        return depreciationMonth;
    }

    public void setDepreciationMonth(Date depreciationMonth) {
        this.depreciationMonth = depreciationMonth;
    }
}
