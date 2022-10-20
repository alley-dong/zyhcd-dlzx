package com.dlzx.pfservercar.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 车辆管理-车辆保险对象 c_insurance_vehicle
 * 
 * @author dlzx
 * @date 2020-11-24
 */
public class CInsuranceVehicle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 主键映射CIVIID */
    private String CIVIID;

    /** 保险类型 1新车保险 2车辆续保 */

    private Long type;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String licensePlate;

    @Excel(name = "保险类型")
    private String typeName;

    /** 保险类型 1新车保险 2车辆续保 */
    private String insuranceType;

    /** 车辆ID */
    private String vehicleId;


    /** 车架号 */
    private String frameNumber;



    /** 备注 */
    private String remarks;

    /** 合计保费 */
    private BigDecimal totalPremium;

    /** 审核状态 */
    private Long status;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date reviewTime;

    /** 审核人 */
    private String reviewUser;

    /** 审核备注 */
    private String reviewRemarks;

    /** 删除状态 1正常 2删除 */
    private Long deleteFlag;

    //  c_insurance_vehicle_item
    /** 保险ID */
    private String insuranceId;

    /** 保险类型ID */
    private String insuranceTypeId;

    /** 保险类型名称 */
    @Excel(name = "保险种类")
    private String insuranceTypeName;

    /** 保险项ID */
    private String insuranceItemId;

    /** 保险项名称 */
    private String insuranceItemName;

    /** 保额 */
    private BigDecimal insuranceQuota;

    /** 保费 */
    @Excel(name = "保费")
    private BigDecimal insuranceCost;

    /** 保险开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date insuranceStartDate;

    @Excel(name = "起保日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String insuranceStartDateExport;

    @Excel(name = "终保日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String insuranceEndDateExport;

    @Excel(name = "办理保险日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String insuranceHandleTimeExport;

    /** 保险结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date insuranceEndDate;

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

    /** 保险办理日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date insuranceHandleTime;

    /** 总计保费 */
    private BigDecimal insuranceCostTotal;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    private String typeId;

    /** 审批人 */
    private String userid;

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

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date finshTime;

    public List<String> getLicensePlates() {
        return licensePlates;
    }

    public void setLicensePlates(List<String> licensePlates) {
        this.licensePlates = licensePlates;
    }

    /** 车牌号码(集合) */
    private List<String> licensePlates;

    /** CIVG的车牌 */
    private String CIVGlicensePlate;

    private List<CInsuranceVehicleItem> cInsuranceVehicleItems;

    private List<CInsuranceVehicleGoogds> cInsuranceVehicleGoogds;

    public List<CInsuranceVehicleGoogds> getcInsuranceVehicleGoogds() {
        return cInsuranceVehicleGoogds;
    }

    public void setcInsuranceVehicleGoogds(List<CInsuranceVehicleGoogds> cInsuranceVehicleGoogds) {
        this.cInsuranceVehicleGoogds = cInsuranceVehicleGoogds;
    }

    @Override
    public String toString() {
        return "CInsuranceVehicle{" +
                "id='" + id + '\'' +
                ", CIVIID='" + CIVIID + '\'' +
                ", type=" + type +
                ", insuranceType='" + insuranceType + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", frameNumber='" + frameNumber + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", remarks='" + remarks + '\'' +
                ", totalPremium=" + totalPremium +
                ", status=" + status +
                ", reviewTime=" + reviewTime +
                ", reviewUser='" + reviewUser + '\'' +
                ", reviewRemarks='" + reviewRemarks + '\'' +
                ", deleteFlag=" + deleteFlag +
                ", insuranceId='" + insuranceId + '\'' +
                ", insuranceTypeId='" + insuranceTypeId + '\'' +
                ", insuranceTypeName='" + insuranceTypeName + '\'' +
                ", insuranceItemId='" + insuranceItemId + '\'' +
                ", insuranceItemName='" + insuranceItemName + '\'' +
                ", insuranceQuota=" + insuranceQuota +
                ", insuranceCost=" + insuranceCost +
                ", insuranceStartDate=" + insuranceStartDate +
                ", insuranceEndDate=" + insuranceEndDate +
                ", insuranceHandleTime=" + insuranceHandleTime +
                ", insuranceCostTotal=" + insuranceCostTotal +
                ", userid='" + userid + '\'' +
                ", cInsuranceVehicleItems=" + cInsuranceVehicleItems +
                '}';
    }

    public String getCIVIID() {
        return CIVIID;
    }

    public void setCIVIID(String CIVIID) {
        this.CIVIID = CIVIID;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setFrameNumber(String frameNumber) 
    {
        this.frameNumber = frameNumber;
    }

    public String getFrameNumber() 
    {
        return frameNumber;
    }
    public void setLicensePlate(String licensePlate) 
    {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() 
    {
        return licensePlate;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }
    public void setTotalPremium(BigDecimal totalPremium) 
    {
        this.totalPremium = totalPremium;
    }

    public BigDecimal getTotalPremium() 
    {
        return totalPremium;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setReviewTime(Date reviewTime) 
    {
        this.reviewTime = reviewTime;
    }

    public Date getReviewTime() 
    {
        return reviewTime;
    }
    public void setReviewUser(String reviewUser) 
    {
        this.reviewUser = reviewUser;
    }

    public String getReviewUser() 
    {
        return reviewUser;
    }
    public void setReviewRemarks(String reviewRemarks) 
    {
        this.reviewRemarks = reviewRemarks;
    }

    public String getReviewRemarks() 
    {
        return reviewRemarks;
    }
    public void setDeleteFlag(Long deleteFlag) 
    {
        this.deleteFlag = deleteFlag;
    }

    public Long getDeleteFlag() 
    {
        return deleteFlag;
    }

    public String getInsuranceItemName() {
        return insuranceItemName;
    }

    public void setInsuranceItemName(String insuranceItemName) {
        this.insuranceItemName = insuranceItemName;
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

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public Date getInsuranceStartDate() {
        return insuranceStartDate;
    }

    public void setInsuranceStartDate(Date insuranceStartDate) {
        this.insuranceStartDate = insuranceStartDate;
    }

    public Date getInsuranceEndDate() {
        return insuranceEndDate;
    }

    public void setInsuranceEndDate(Date insuranceEndDate) {
        this.insuranceEndDate = insuranceEndDate;
    }

    public Date getInsuranceHandleTime() {
        return insuranceHandleTime;
    }

    public void setInsuranceHandleTime(Date insuranceHandleTime) {
        this.insuranceHandleTime = insuranceHandleTime;
    }

    public BigDecimal getInsuranceCostTotal() {
        return insuranceCostTotal;
    }

    public void setInsuranceCostTotal(BigDecimal insuranceCostTotal) {
        this.insuranceCostTotal = insuranceCostTotal;
    }

    public List<CInsuranceVehicleItem> getcInsuranceVehicleItems() {
        return cInsuranceVehicleItems;
    }

    public void setcInsuranceVehicleItems(List<CInsuranceVehicleItem> cInsuranceVehicleItems) {
        this.cInsuranceVehicleItems = cInsuranceVehicleItems;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }
}
