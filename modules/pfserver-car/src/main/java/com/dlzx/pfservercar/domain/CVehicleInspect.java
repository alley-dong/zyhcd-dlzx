package com.dlzx.pfservercar.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 车辆管理-车辆定检记录对象 c_vehicle_inspect
 * 
 * @author dlzx
 * @date 2020-12-08
 */
public class CVehicleInspect extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 定检发布ID 关联c_vehicle_inspect_monthly表 */
    @Excel(name = "定检发布ID 关联c_vehicle_inspect_monthly表")
    private String monthlyId;

    /** 检查类型 1主车 2挂车 */
    @Excel(name = "检查类型 1主车 2挂车")
    private Long type;

    /** 车辆ID */
    @Excel(name = "车辆ID")
    private String vehicleId;

    /** 车牌号码 */
    @Excel(name = "车牌号码")
    private String licensePlate;

    /** 司机ID */
    @Excel(name = "司机ID")
    private String driverId;

    /** 司机名称 */
    @Excel(name = "司机ID")
    private String driverName;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Long status;

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

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag;

    /** 定检日期 */
    @Excel(name = "定检日期")
    private String inspectDate;

    /**
     * 开始时间
     */
    private Date sTime;

    /**
     * 结束时间
     */
    private Date fTime;

    public Date getsTime() {
        return sTime;
    }

    public void setsTime(Date sTime) {
        this.sTime = sTime;
    }

    public Date getfTime() {
        return fTime;
    }

    public void setfTime(Date fTime) {
        this.fTime = fTime;
    }

    /** 审批人 */
    private String userid;

    private List<CVehicleInspectItem> cVehicleInspectItemList;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public List<CVehicleInspectItem> getcVehicleInspectItemList() {
        return cVehicleInspectItemList;
    }

    public void setcVehicleInspectItemList(List<CVehicleInspectItem> cVehicleInspectItemList) {
        this.cVehicleInspectItemList = cVehicleInspectItemList;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setMonthlyId(String monthlyId) 
    {
        this.monthlyId = monthlyId;
    }

    public String getMonthlyId() 
    {
        return monthlyId;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setVehicleId(String vehicleId) 
    {
        this.vehicleId = vehicleId;
    }

    public String getVehicleId() 
    {
        return vehicleId;
    }
    public void setLicensePlate(String licensePlate) 
    {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() 
    {
        return licensePlate;
    }
    public void setDriverId(String driverId) 
    {
        this.driverId = driverId;
    }

    public String getDriverId() 
    {
        return driverId;
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

    public String getInspectDate() {
        return inspectDate;
    }

    public void setInspectDate(String inspectDate) {
        this.inspectDate = inspectDate;
    }

    /** 记录ID 关联c_vehicle_inspect表 */
    @Excel(name = "记录ID 关联c_vehicle_inspect表")
    private String inspectId;

    /** 检查类型 */
    @JsonIgnore
    @Excel(name = "检查类型")
    private Long cviiType;

    /** 检查项目类型 */
    @JsonIgnore
    @Excel(name = "检查项目类型")
    private Long itemType;

    /** 检查内容 */
    @JsonIgnore
    @Excel(name = "检查内容")
    private String name;

    /** 附件地址 */
    @JsonIgnore
    @Excel(name = "附件地址")
    private String files;

    /** 总车辆 */
    @JsonIgnore
    @Excel(name = "总车辆")
    private Long totalNumber;

    /** 完成车辆 */
    @JsonIgnore
    @Excel(name = "完成车辆")
    private Long completeNumber;

    /** 未完成车辆 */
    @JsonIgnore
    @Excel(name = "未完成车辆")
    private Long incompleteNumber;

    /** 状态 */
    @JsonIgnore
    @Excel(name = "状态")
    private Long cviiStatus;

    public String getMonthlyItemId() {
        return monthlyItemId;
    }

    public void setMonthlyItemId(String monthlyItemId) {
        this.monthlyItemId = monthlyItemId;
    }

    private String monthlyItemId;

    @Override
    public String toString() {
        return "CVehicleInspect{" +
                "id='" + id + '\'' +
                ", monthlyId='" + monthlyId + '\'' +
                ", type=" + type +
                ", vehicleId='" + vehicleId + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", driverId='" + driverId + '\'' +
                ", driverName='" + driverName + '\'' +
                ", status=" + status +
                ", reviewTime=" + reviewTime +
                ", reviewUser='" + reviewUser + '\'' +
                ", reviewRemarks='" + reviewRemarks + '\'' +
                ", deleteFlag=" + deleteFlag +
                ", inspectDate='" + inspectDate + '\'' +
                ", userid='" + userid + '\'' +
                ", cVehicleInspectItemList=" + cVehicleInspectItemList +
                ", inspectId='" + inspectId + '\'' +
                ", cviiType=" + cviiType +
                ", itemType=" + itemType +
                ", name='" + name + '\'' +
                ", files='" + files + '\'' +
                ", totalNumber=" + totalNumber +
                ", completeNumber=" + completeNumber +
                ", incompleteNumber=" + incompleteNumber +
                ", cviiStatus=" + cviiStatus +
                '}';
    }

    public String getInspectId() {
        return inspectId;
    }

    public void setInspectId(String inspectId) {
        this.inspectId = inspectId;
    }

    public Long getItemType() {
        return itemType;
    }

    public void setItemType(Long itemType) {
        this.itemType = itemType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public Long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Long totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Long getCompleteNumber() {
        return completeNumber;
    }

    public void setCompleteNumber(Long completeNumber) {
        this.completeNumber = completeNumber;
    }

    public Long getIncompleteNumber() {
        return incompleteNumber;
    }

    public void setIncompleteNumber(Long incompleteNumber) {
        this.incompleteNumber = incompleteNumber;
    }

    public Long getCviiStatus() {
        return cviiStatus;
    }

    public void setCviiStatus(Long cviiStatus) {
        this.cviiStatus = cviiStatus;
    }

    public Long getCviiType() {
        return cviiType;
    }

    public void setCviiType(Long cviiType) {
        this.cviiType = cviiType;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
