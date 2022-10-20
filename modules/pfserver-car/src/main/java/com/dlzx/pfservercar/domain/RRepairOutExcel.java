package com.dlzx.pfservercar.domain;

import com.dlzx.common.core.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 类描述：
 * @className RRepairOutExcel
 * company 赛拓信息技术有限公司
 * @author DongCL
 * @date 2021-05-06 13:34
 */
import java.math.BigDecimal;

public class RRepairOutExcel {

    /** 日期 */
    @Excel(name = "日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String createTime;

    /** 维修项目 */
    @Excel(name = "维修项目")
    private String name;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 数量 */
    @Excel(name = "数量")
    private Long number;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal unitPrice;

    /** 工时数 */
    @Excel(name = "工时数")
    private BigDecimal duration;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal amount;

    /** 总计 */
    @Excel(name = "总计")
    private BigDecimal total;

    /** 车型 */
    @Excel(name = "车型")
    private String vehicleModelName;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String licensePlate;

    /** id */
    private String repairId;


    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getVehicleModelName() {
        return vehicleModelName;
    }

    public void setVehicleModelName(String vehicleModelName) {
        this.vehicleModelName = vehicleModelName;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
