package com.dlzx.pfservercar.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 维修保养-维修明细对象 r_repair_detailed
 * 
 * @author yangluhe
 * @date 2020-12-04
 */
public class RRepairDetailed extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 是否新增 1是 2否 */
    @Excel(name = "是否新增 1是 2否")
    private Long isNew;

    /** 维修站ID-关联r_garage表 */
    @Excel(name = "维修站ID-关联r_garage表")
    private String garageId;

    /** 维修站名称 */
    @Excel(name = "维修站名称")
    private String garageName;

    /** 车辆型号 关联sys_dictionary表 */
    @Excel(name = "车辆型号 关联sys_dictionary表")
    private String vehicleModel;

    /** 车辆型号-名称 */
    @Excel(name = "车辆型号-名称")
    private String vehicleModelName;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal unitPrice;

    /** 工时数 */
    @Excel(name = "工时数")
    private BigDecimal duration;

    /** 数量 */
    @Excel(name = "数量")
    private Long number;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal amount;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 月维修次数 */
    @Excel(name = "月维修次数")
    private Long monthNumber;

    /** 年维修次数 */
    @Excel(name = "年维修次数")
    private Long yearNumber;

    /** 删除状态 1正常 2删除 */
    private Long deleteFlag;

    /**维修单ID-关联r_repair表*/
    private String repairId;

    /**维修单号*/
    private String repairCode;

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    @Excel(name = "完成日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date completeTime;

    @Excel(name = "维修单号")
    private String code;

    @Excel(name = "主车牌号")
    private String mainLicensePlate;

    /**车检费用的车牌号*/
    private String licensePlate;

    public Long getmNumber() {
        return mNumber;
    }

    public void setmNumber(Long mNumber) {
        this.mNumber = mNumber;
    }

    public Long getyNumber() {
        return yNumber;
    }

    public void setyNumber(Long yNumber) {
        this.yNumber = yNumber;
    }

    /**开始时间*/
    private Date startTime;

    private Long mNumber;

    private Long yNumber;

    public Long getRepairType() {
        return repairType;
    }

    public void setRepairType(Long repairType) {
        this.repairType = repairType;
    }

    private Long repairType;

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

    /**结束时间*/
    private Date finshTime;

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    private Long type;

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

    @Excel(name = "挂车牌号")
    private String hangLicensePlate;

    public RRepair getRepair() {
        return repair;
    }

    public void setRepair(RRepair repair) {
        this.repair = repair;
    }

    private RRepair repair;

    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    public String getRepairCode() {
        return repairCode;
    }

    public void setRepairCode(String repairCode) {
        this.repairCode = repairCode;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setIsNew(Long isNew) 
    {
        this.isNew = isNew;
    }

    public Long getIsNew() 
    {
        return isNew;
    }
    public void setGarageId(String garageId) 
    {
        this.garageId = garageId;
    }

    public String getGarageId() 
    {
        return garageId;
    }
    public void setGarageName(String garageName) 
    {
        this.garageName = garageName;
    }

    public String getGarageName() 
    {
        return garageName;
    }
    public void setVehicleModel(String vehicleModel) 
    {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleModel() 
    {
        return vehicleModel;
    }
    public void setVehicleModelName(String vehicleModelName) 
    {
        this.vehicleModelName = vehicleModelName;
    }

    public String getVehicleModelName() 
    {
        return vehicleModelName;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setUnitPrice(BigDecimal unitPrice) 
    {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitPrice() 
    {
        return unitPrice;
    }
    public void setDuration(BigDecimal duration) 
    {
        this.duration = duration;
    }

    public BigDecimal getDuration() 
    {
        return duration;
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
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }
    public void setMonthNumber(Long monthNumber) 
    {
        this.monthNumber = monthNumber;
    }

    public Long getMonthNumber() 
    {
        return monthNumber;
    }
    public void setYearNumber(Long yearNumber) 
    {
        this.yearNumber = yearNumber;
    }

    public Long getYearNumber() 
    {
        return yearNumber;
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
            .append("isNew", getIsNew())
            .append("garageId", getGarageId())
            .append("garageName", getGarageName())
            .append("vehicleModel", getVehicleModel())
            .append("vehicleModelName", getVehicleModelName())
            .append("name", getName())
            .append("unit", getUnit())
            .append("unitPrice", getUnitPrice())
            .append("duration", getDuration())
            .append("number", getNumber())
            .append("amount", getAmount())
            .append("remarks", getRemarks())
            .append("monthNumber", getMonthNumber())
            .append("yearNumber", getYearNumber())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
                .append("repairId",getRepairId())
                .append("repairCode",getRepairCode())
                .append("repair",getRepair())
                .append("code",getCode())
                .append("mainLicensePlate",getMainLicensePlate())
                .append("hangLicensePlate",getHangLicensePlate())
                .append("completeTime",getCompleteTime())
                .append("type",getType())
                .append("licensePlate",getLicensePlate())
                .append("startTime",getStartTime())
                .append("finshTime",getFinshTime())
                .append("repairType",getRepairType())
            .toString();
    }
}
