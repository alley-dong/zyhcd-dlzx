package com.dlzx.pfservercar.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ylh
 * @program:dlzx
 * @description：新增的维修明细记录表
 * @create:2020-12-07 20-44
 */
public class AddRepairRecord extends BaseEntity {

    /**
     * id
     */
    private String id;

    /**
     * 维修类别
     */
    private Long type;

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    /**
     * 编号
     */
    @Excel(name = "编号")
    private String code;

    @Excel(name = "维修时间")
    private String cTime;

    /**
     * 主车牌号
     */
    @Excel(name = "主车牌号")
    private String mainLicensePlate;

    /**
     * 挂车牌号
     */
    @Excel(name = "挂车牌号")
    private String hangLicensePlate;

    /**
     * 维修站名称
     */
    @Excel(name = "维修站称")
    private String garageName;

    /**
     * 名称
     */
    @Excel(name = "名称")
    private String name;

    /**
     * 单价
     */
    @Excel(name = "单价")
    private BigDecimal unitPrice;

    /**
     * 单位
     */
    @Excel(name = "单位")
    private String unit;

    /**
     * 数量
     */
    @Excel(name = "数量")
    private Long number;

    /**
     * 工时数
     */
    @Excel(name = "工时数")
    private BigDecimal duration;

    /**
     * 金额
     */
    @Excel(name = "金额")
    private BigDecimal amount;

    private Long repairType;

    public Long getRepairType() {
        return repairType;
    }

    public void setRepairType(Long repairType) {
        this.repairType = repairType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGarageName() {
        return garageName;
    }

    public void setGarageName(String garageName) {
        this.garageName = garageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }


    /**
     * 完成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date completeTime;

    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }


    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    private Date finishTime;
}
