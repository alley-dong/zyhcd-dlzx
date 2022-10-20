package com.dlzx.pfservercar.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * 多表数据连接添加
 * c_insurance_type
 * c_insurance_type_item
 * c_insurance_vehicle
 * c_insurance_vehicle_item
 */
public class CInsuranceTypeTableJoinAdd extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private CVehicle cVehicle;

    /** 主键 */
    private String id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    // item

    /** ITEM主键 */
    private String itemId;

    /** ITEM名称 */
    @Excel(name = "名称")
    private String itemName;

    //c_insurance_vehicle

    /** 保险类型 1新车保险 2车辆续保 3货物保险 */
    @Excel(name = "保险类型 1新车保险 2车辆续保 3货物保险")
    private Long type;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Long status;

    /** 车架号 */
    @Excel(name = "车架号")
    private String frameNumber;

    // c_insurance_vehicle   c_vehicle 共有
    /** 车牌号 */
    @Excel(name = "车牌号")
    private String licensePlate;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 合计保费 */
    @Excel(name = "合计保费")
    private BigDecimal totalPremium;

    // c_insurance_vehicle_item

    /** 保险ID */
    @Excel(name = "保险ID")
    private String insuranceId;

    /** 保险类型ID */
    @Excel(name = "保险类型ID")
    private String insuranceTypeId;

    /** 保险类型名称 */
    @Excel(name = "保险类型名称")
    private String insuranceTypeName;

    /** 保险项ID */
    @Excel(name = "保险项ID")
    private String insuranceItemId;

    /** 保险项名称 */
    @Excel(name = "保险项名称")
    private String insuranceItemName;

    /** 保额 */
    @Excel(name = "保额")
    private BigDecimal insuranceQuota;

    /** 保费 */
    @Excel(name = "保费")
    private BigDecimal insuranceCost;

    // c_vehicle

    /**车辆类别-名称*/
    @Excel(name = "车辆类别")
    private String vehicleTypeName;

    /**车辆型号-名称*/
    @Excel(name = "车辆型号")
    private String vehicleModelName;

    private String vehicleId;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    private List<CInsuranceVehicleItem> cInsuranceVehicleItems;

    public CInsuranceTypeTableJoinAdd() {
    }

    public CInsuranceTypeTableJoinAdd(CVehicle cVehicle, String id, String name, String itemId, String itemName, Long type, Long status, String frameNumber, String licensePlate, String remarks, BigDecimal totalPremium, String insuranceId, String insuranceTypeId, String insuranceTypeName, String insuranceItemId, String insuranceItemName, BigDecimal insuranceQuota, BigDecimal insuranceCost, String vehicleTypeName, String vehicleModelName) {
        this.cVehicle = cVehicle;
        this.id = id;
        this.name = name;
        this.itemId = itemId;
        this.itemName = itemName;
        this.type = type;
        this.status = status;
        this.frameNumber = frameNumber;
        this.licensePlate = licensePlate;
        this.remarks = remarks;
        this.totalPremium = totalPremium;
        this.insuranceId = insuranceId;
        this.insuranceTypeId = insuranceTypeId;
        this.insuranceTypeName = insuranceTypeName;
        this.insuranceItemId = insuranceItemId;
        this.insuranceItemName = insuranceItemName;
        this.insuranceQuota = insuranceQuota;
        this.insuranceCost = insuranceCost;
        this.vehicleTypeName = vehicleTypeName;
        this.vehicleModelName = vehicleModelName;
    }

    @Override
    public String toString() {
        return "CInsuranceTypeTableJoinAdd{" +
                "cVehicle=" + cVehicle +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", frameNumber='" + frameNumber + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", remarks='" + remarks + '\'' +
                ", totalPremium=" + totalPremium +
                ", insuranceId='" + insuranceId + '\'' +
                ", insuranceTypeId='" + insuranceTypeId + '\'' +
                ", insuranceTypeName='" + insuranceTypeName + '\'' +
                ", insuranceItemId='" + insuranceItemId + '\'' +
                ", insuranceItemName='" + insuranceItemName + '\'' +
                ", insuranceQuota=" + insuranceQuota +
                ", insuranceCost=" + insuranceCost +
                ", vehicleTypeName='" + vehicleTypeName + '\'' +
                ", vehicleModelName='" + vehicleModelName + '\'' +
                ", cInsuranceVehicleItems=" + cInsuranceVehicleItems +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public CVehicle getcVehicle() {
        return cVehicle;
    }

    public void setcVehicle(CVehicle cVehicle) {
        this.cVehicle = cVehicle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @NotNull(message = "保险类型不能为空")
    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(String frameNumber) {
        this.frameNumber = frameNumber;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public BigDecimal getTotalPremium() {
        return totalPremium;
    }

    public void setTotalPremium(BigDecimal totalPremium) {
        this.totalPremium = totalPremium;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getInsuranceTypeId() {
        return insuranceTypeId;
    }

    public void setInsuranceTypeId(String insuranceTypeId) {
        this.insuranceTypeId = insuranceTypeId;
    }

    public String getInsuranceTypeName() {
        return insuranceTypeName;
    }

    public void setInsuranceTypeName(String insuranceTypeName) {
        this.insuranceTypeName = insuranceTypeName;
    }

    public String getInsuranceItemId() {
        return insuranceItemId;
    }

    public void setInsuranceItemId(String insuranceItemId) {
        this.insuranceItemId = insuranceItemId;
    }

    public String getInsuranceItemName() {
        return insuranceItemName;
    }

    public void setInsuranceItemName(String insuranceItemName) {
        this.insuranceItemName = insuranceItemName;
    }

    public BigDecimal getInsuranceQuota() {
        return insuranceQuota;
    }

    public void setInsuranceQuota(BigDecimal insuranceQuota) {
        this.insuranceQuota = insuranceQuota;
    }

    public BigDecimal getInsuranceCost() {
        return insuranceCost;
    }

    public void setInsuranceCost(BigDecimal insuranceCost) {
        this.insuranceCost = insuranceCost;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public String getVehicleModelName() {
        return vehicleModelName;
    }

    public void setVehicleModelName(String vehicleModelName) {
        this.vehicleModelName = vehicleModelName;
    }

    public List<CInsuranceVehicleItem> getcInsuranceVehicleItems() {
        return cInsuranceVehicleItems;
    }

    public void setcInsuranceVehicleItems(List<CInsuranceVehicleItem> cInsuranceVehicleItems) {
        this.cInsuranceVehicleItems = cInsuranceVehicleItems;
    }
}
