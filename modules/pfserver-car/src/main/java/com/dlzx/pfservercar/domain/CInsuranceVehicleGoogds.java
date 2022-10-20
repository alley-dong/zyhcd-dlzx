package com.dlzx.pfservercar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 车辆管理-货物保险-车辆对象 c_insurance_vehicle_googds
 * 
 * @author dlzx
 * @date 2020-11-27
 */
public class CInsuranceVehicleGoogds extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 主键映射 */
    private String CIVID;

    /** 主键映射civg */
    private String CIVGD;

    /** 保险ID */
    @Excel(name = "保险ID")
    private String insuranceId;

    /** 车辆ID */
    @Excel(name = "车辆ID")
    private String vehicleId;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String licensePlate;

    /** 车架号 */
    @Excel(name = "车架号")
    private String frameNumber;

    /** 保险开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "保险开始日期")
    private Date insuranceStartDate;

    /** 保险结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "保险结束日期")
    private Date insuranceEndDate;

    /** 保险办理日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "保险办理日期")
    private Date insuranceHandleTime;

    /** 合计保费 */
    @Excel(name = "合计保费")
    private BigDecimal totalPremium;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Long status;

    /** 状态 */
    @Excel(name = "状态")
    private Long type;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reviewTime;

    /** 审核人 */
    @Excel(name = "审核人")
    private String reviewUser;

    /** 审核备注 */
    @Excel(name = "审核备注")
    private String reviewRemarks;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 附件文件 url（多个逗号隔开） */
    @JsonIgnore
    private String fileUrls;

    /** 附件文件(返回)*/
    private List<String> fileList;

    public List<String> getFileList() {
        return fileList;
    }

    public void setFileList(List<String> fileList) {
        this.fileList = fileList;
    }

    public String getFileUrls() {
        return fileUrls;
    }

    public void setFileUrls(String fileUrls) {
        this.fileUrls = fileUrls;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    // 车辆id 车牌号
    private List<CInsuranceVehicleGoogds> cVehicleList;
    // 保险 保额保费
    private List<CInsuranceVehicleItem> cInsuranceVehicleItemList;

    /** 保险类型ID */
    @JsonIgnore
    @Excel(name = "保险类型ID")
    private String insuranceTypeId;

    /** 保险类型名称 */
    @JsonIgnore
    @Excel(name = "保险类型名称")
    private String insuranceTypeName;

    /** 保险项ID */
    @JsonIgnore
    @Excel(name = "保险项ID")
    private String insuranceItemId;

    /** 保险项名称 */
    @JsonIgnore
    @Excel(name = "保险项名称")
    private String insuranceItemName;

    /** 保额 */
    @JsonIgnore
    @Excel(name = "保额")
    private BigDecimal insuranceQuota;

    /** 保费 */
    @JsonIgnore
    @Excel(name = "保费")
    private BigDecimal insuranceCost;

    public Long getOutTimeFlag() {
        return outTimeFlag;
    }

    public void setOutTimeFlag(Long outTimeFlag) {
        this.outTimeFlag = outTimeFlag;
    }

    private Long outTimeFlag;

    public String getCIVGD() {
        return CIVGD;
    }

    public void setCIVGD(String CIVGD) {
        this.CIVGD = CIVGD;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(String frameNumber) {
        this.frameNumber = frameNumber;
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

    public BigDecimal getTotalPremium() {
        return totalPremium;
    }

    public void setTotalPremium(BigDecimal totalPremium) {
        this.totalPremium = totalPremium;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getReviewUser() {
        return reviewUser;
    }

    public void setReviewUser(String reviewUser) {
        this.reviewUser = reviewUser;
    }

    public String getReviewRemarks() {
        return reviewRemarks;
    }

    public void setReviewRemarks(String reviewRemarks) {
        this.reviewRemarks = reviewRemarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<CInsuranceVehicleGoogds> getcVehicleList() {
        return cVehicleList;
    }

    public void setcVehicleList(List<CInsuranceVehicleGoogds> cVehicleList) {
        this.cVehicleList = cVehicleList;
    }

    public List<CInsuranceVehicleItem> getcInsuranceVehicleItemList() {
        return cInsuranceVehicleItemList;
    }

    public void setcInsuranceVehicleItemList(List<CInsuranceVehicleItem> cInsuranceVehicleItemList) {
        this.cInsuranceVehicleItemList = cInsuranceVehicleItemList;
    }

    public String getCIVID() {
        return CIVID;
    }

    public void setCIVID(String CIVID) {
        this.CIVID = CIVID;
    }

    public CInsuranceVehicleGoogds() {
    }

    public CInsuranceVehicleGoogds(String id, String insuranceId, String vehicleId, String licensePlate, String frameNumber, Date insuranceStartDate, Date insuranceEndDate, Date insuranceHandleTime, BigDecimal totalPremium, Long status, Date reviewTime, String reviewUser, String reviewRemarks, String remarks, List<CInsuranceVehicleGoogds> cVehicleList, List<CInsuranceVehicleItem> cInsuranceVehicleItemList) {
        this.id = id;
        this.insuranceId = insuranceId;
        this.vehicleId = vehicleId;
        this.licensePlate = licensePlate;
        this.frameNumber = frameNumber;
        this.insuranceStartDate = insuranceStartDate;
        this.insuranceEndDate = insuranceEndDate;
        this.insuranceHandleTime = insuranceHandleTime;
        this.totalPremium = totalPremium;
        this.status = status;
        this.reviewTime = reviewTime;
        this.reviewUser = reviewUser;
        this.reviewRemarks = reviewRemarks;
        this.remarks = remarks;
        this.cVehicleList = cVehicleList;
        this.cInsuranceVehicleItemList = cInsuranceVehicleItemList;
        this.outTimeFlag = outTimeFlag;
    }

    @Override
    public String toString() {
        return "CInsuranceVehicleGoogds{" +
                "id='" + id + '\'' +
                ", CIVID='" + CIVID + '\'' +
                ", CIVGD='" + CIVGD + '\'' +
                ", insuranceId='" + insuranceId + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", frameNumber='" + frameNumber + '\'' +
                ", insuranceStartDate=" + insuranceStartDate +
                ", insuranceEndDate=" + insuranceEndDate +
                ", insuranceHandleTime=" + insuranceHandleTime +
                ", totalPremium=" + totalPremium +
                ", status=" + status +
                ", type=" + type +
                ", reviewTime=" + reviewTime +
                ", reviewUser='" + reviewUser + '\'' +
                ", reviewRemarks='" + reviewRemarks + '\'' +
                ", remarks='" + remarks + '\'' +
                ", fileUrls='" + fileUrls + '\'' +
                ", fileList=" + fileList +
                ", cVehicleList=" + cVehicleList +
                ", cInsuranceVehicleItemList=" + cInsuranceVehicleItemList +
                ", insuranceTypeId='" + insuranceTypeId + '\'' +
                ", insuranceTypeName='" + insuranceTypeName + '\'' +
                ", insuranceItemId='" + insuranceItemId + '\'' +
                ", insuranceItemName='" + insuranceItemName + '\'' +
                ", insuranceQuota=" + insuranceQuota +
                ", insuranceCost=" + insuranceCost +
                '}';
    }
}

