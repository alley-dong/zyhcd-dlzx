package com.dlzx.pfservercar.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 安全管理-事故对象 safe_accident
 * 
 * @author dlzx
 * @date 2020-12-08
 */
public class SafeAccident extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 车辆ID */
    @Excel(name = "车辆ID")
    private String vehicleId;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String vehicleName;

    /** 驾驶员ID */
    @Excel(name = "驾驶员ID")
    private String driverId;

    /** 驾驶员姓名 */
    @Excel(name = "驾驶员姓名")
    private String driverName;

    /** 事故发生时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "事故发生时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date accidentTime;

    private String aTime;
    /** 事故地点 */
    @Excel(name = "事故地点")
    private String accidentPlace;

    /** 经度 */
    @Excel(name = "经度")
    private String longitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private String latitude;

    /** 审核状态（1.待审批 2.已通过 3.已驳回） */
    @Excel(name = "审核状态", readConverterExp = "1=.待审批,2=.已通过,3=.已驳回")
    private Long status;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date reviewTime;

    /** 审核人 */
    @Excel(name = "审核人")
    private String reviewUser;

    /** 审核备注 */
    @Excel(name = "审核备注")
    private String reviewRemarks;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag = 1L;

    /** 一方驾驶证正面 */
    @Excel(name = "一方驾驶证正面")
    private String oneSideDriveLicenceCardJust;

    /** 一方驾驶证反面 */
    @Excel(name = "一方驾驶证反面")
    private String oneSideDriveLicenceCardAgainst;

    /** 一方行驶证反面 */
    @Excel(name = "一方行驶证正面")
    private String oneSideTravelLicenceCardJust;

    /** 一方行驶证反面 */
    @Excel(name = "一方行驶证反面")
    private String oneSideTravelLicenceCardAgainst;

    /** 另一方驾驶证正面 */
    @Excel(name = "另一方驾驶证正面")
    private String theOtherDriveLicenceCardJust;

    /** 另一方驾驶证正面 */
    @Excel(name = "另一方驾驶证反面")
    private String theOtherDriveLicenceCardAgainst;

    /** 另一方行驶证反面 */
    @Excel(name = "另一方行驶证正面")
    private String theOtherTravelLicenceCardJust;

    /** 另一方行驶证反面 */
    @Excel(name = "另一方行驶证反面")
    private String theOtherTravelLicenceCardAgainst;

    /** 驾驶员身份证正面 */
    @Excel(name = "驾驶员身份证正面")
    private String driverIdCardJust;

    /** 驾驶员身份证反面 */
    @Excel(name = "驾驶员身份证反面")
    private String driverIdCardAgainst;

    /** 车辆被保险人身份证正面 */
    @Excel(name = "车辆被保险人身份证正面")
    private String carTheInsuredIdCardJust;

    /** 车辆被保险人身份证反面 */
    @Excel(name = "车辆被保险人身份证反面")
    private String carTheInsuredIdCardAgainst;

    /** 事故责任认定书 */
    @Excel(name = "事故责任认定书")
    private String liabilityStatement;

    /** 审核状态（1.待审批 2.已通过 3.已驳回） */
    private String statusEnum;

    /** 事故照片（多个逗号隔开） */
    private List<String> accidentPhotos;

    /** 审批人 */
    private String userid;

    /** 其他照片（多个逗号隔开）*/
    private List<String> partsPhotos;

    /** 事故照片（返回） */
    private List<String> accidentFileList;

    /** 其他照片（返回）*/
    private List<String> partsFilesList;

    /**App下拉框内容*/
    private String contentBox;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getContentBox() {
        return contentBox;
    }

    public void setContentBox(String contentBox) {
        this.contentBox = contentBox;
    }

    public List<String> getAccidentFileList() {
        return accidentFileList;
    }

    public void setAccidentFileList(List<String> accidentFileList) {
        this.accidentFileList = accidentFileList;
    }

    public List<String> getPartsFilesList() {
        return partsFilesList;
    }

    public void setPartsFilesList(List<String> partsFilesList) {
        this.partsFilesList = partsFilesList;
    }

    public List<String> getAccidentPhotos() {
        return accidentPhotos;
    }

    public void setAccidentPhotos(List<String> accidentPhotos) {
        this.accidentPhotos = accidentPhotos;
    }

    public List<String> getPartsPhotos() {
        return partsPhotos;
    }

    public void setPartsPhotos(List<String> partsPhotos) {
        this.partsPhotos = partsPhotos;
    }

    public String getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(String statusEnum) {
        this.statusEnum = statusEnum;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setVehicleId(String vehicleId) 
    {
        this.vehicleId = vehicleId;
    }

    public String getVehicleId() 
    {
        return vehicleId;
    }
    public void setVehicleName(String vehicleName) 
    {
        this.vehicleName = vehicleName;
    }

    public String getVehicleName() 
    {
        return vehicleName;
    }
    public void setDriverId(String driverId) 
    {
        this.driverId = driverId;
    }

    public String getDriverId() 
    {
        return driverId;
    }
    public void setAccidentTime(Date accidentTime) 
    {
        this.accidentTime = accidentTime;
    }

    public Date getAccidentTime() 
    {
        return accidentTime;
    }
    public void setAccidentPlace(String accidentPlace) 
    {
        this.accidentPlace = accidentPlace;
    }

    public String getAccidentPlace() 
    {
        return accidentPlace;
    }
    public void setLongitude(String longitude) 
    {
        this.longitude = longitude;
    }

    public String getLongitude() 
    {
        return longitude;
    }
    public void setLatitude(String latitude) 
    {
        this.latitude = latitude;
    }

    public String getLatitude() 
    {
        return latitude;
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
    public void setOneSideDriveLicenceCardJust(String oneSideDriveLicenceCardJust) 
    {
        this.oneSideDriveLicenceCardJust = oneSideDriveLicenceCardJust;
    }

    public String getOneSideDriveLicenceCardJust() 
    {
        return oneSideDriveLicenceCardJust;
    }
    public void setOneSideTravelLicenceCardAgainst(String oneSideTravelLicenceCardAgainst) 
    {
        this.oneSideTravelLicenceCardAgainst = oneSideTravelLicenceCardAgainst;
    }

    public String getOneSideTravelLicenceCardAgainst() 
    {
        return oneSideTravelLicenceCardAgainst;
    }
    public void setTheOtherDriveLicenceCardJust(String theOtherDriveLicenceCardJust) 
    {
        this.theOtherDriveLicenceCardJust = theOtherDriveLicenceCardJust;
    }

    public String getTheOtherDriveLicenceCardJust() 
    {
        return theOtherDriveLicenceCardJust;
    }
    public void setTheOtherTravelLicenceCardAgainst(String theOtherTravelLicenceCardAgainst) 
    {
        this.theOtherTravelLicenceCardAgainst = theOtherTravelLicenceCardAgainst;
    }

    public String getTheOtherTravelLicenceCardAgainst() 
    {
        return theOtherTravelLicenceCardAgainst;
    }
    public void setDriverIdCardJust(String driverIdCardJust) 
    {
        this.driverIdCardJust = driverIdCardJust;
    }

    public String getDriverIdCardJust() 
    {
        return driverIdCardJust;
    }
    public void setDriverIdCardAgainst(String driverIdCardAgainst) 
    {
        this.driverIdCardAgainst = driverIdCardAgainst;
    }

    public String getDriverIdCardAgainst() 
    {
        return driverIdCardAgainst;
    }
    public void setCarTheInsuredIdCardJust(String carTheInsuredIdCardJust) 
    {
        this.carTheInsuredIdCardJust = carTheInsuredIdCardJust;
    }

    public String getCarTheInsuredIdCardJust() 
    {
        return carTheInsuredIdCardJust;
    }
    public void setCarTheInsuredIdCardAgainst(String carTheInsuredIdCardAgainst) 
    {
        this.carTheInsuredIdCardAgainst = carTheInsuredIdCardAgainst;
    }

    public String getCarTheInsuredIdCardAgainst() 
    {
        return carTheInsuredIdCardAgainst;
    }
    public void setLiabilityStatement(String liabilityStatement) 
    {
        this.liabilityStatement = liabilityStatement;
    }

    public String getLiabilityStatement() 
    {
        return liabilityStatement;
    }

    public String getOneSideDriveLicenceCardAgainst() {
        return oneSideDriveLicenceCardAgainst;
    }

    public void setOneSideDriveLicenceCardAgainst(String oneSideDriveLicenceCardAgainst) {
        this.oneSideDriveLicenceCardAgainst = oneSideDriveLicenceCardAgainst;
    }

    public String getOneSideTravelLicenceCardJust() {
        return oneSideTravelLicenceCardJust;
    }

    public void setOneSideTravelLicenceCardJust(String oneSideTravelLicenceCardJust) {
        this.oneSideTravelLicenceCardJust = oneSideTravelLicenceCardJust;
    }

    public String getTheOtherDriveLicenceCardAgainst() {
        return theOtherDriveLicenceCardAgainst;
    }

    public void setTheOtherDriveLicenceCardAgainst(String theOtherDriveLicenceCardAgainst) {
        this.theOtherDriveLicenceCardAgainst = theOtherDriveLicenceCardAgainst;
    }

    public String getTheOtherTravelLicenceCardJust() {
        return theOtherTravelLicenceCardJust;
    }

    public void setTheOtherTravelLicenceCardJust(String theOtherTravelLicenceCardJust) {
        this.theOtherTravelLicenceCardJust = theOtherTravelLicenceCardJust;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("vehicleId", getVehicleId())
            .append("vehicleName", getVehicleName())
            .append("driverId", getDriverId())
            .append("accidentTime", getAccidentTime())
            .append("accidentPlace", getAccidentPlace())
            .append("longitude", getLongitude())
            .append("latitude", getLatitude())
            .append("status", getStatus())
            .append("reviewTime", getReviewTime())
            .append("reviewUser", getReviewUser())
            .append("reviewRemarks", getReviewRemarks())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("oneSideDriveLicenceCardJust", getOneSideDriveLicenceCardJust())
            .append("oneSideTravelLicenceCardAgainst", getOneSideTravelLicenceCardAgainst())
            .append("theOtherDriveLicenceCardJust", getTheOtherDriveLicenceCardJust())
            .append("theOtherTravelLicenceCardAgainst", getTheOtherTravelLicenceCardAgainst())
            .append("driverIdCardJust", getDriverIdCardJust())
            .append("driverIdCardAgainst", getDriverIdCardAgainst())
            .append("carTheInsuredIdCardJust", getCarTheInsuredIdCardJust())
            .append("carTheInsuredIdCardAgainst", getCarTheInsuredIdCardAgainst())
            .append("liabilityStatement", getLiabilityStatement())
                .append("contentBox",getContentBox())
                .append("aTime",getaTime())
            .toString();
    }

    public String getaTime() {
        return aTime;
    }

    public void setaTime(String aTime) {
        this.aTime = aTime;
    }
}
