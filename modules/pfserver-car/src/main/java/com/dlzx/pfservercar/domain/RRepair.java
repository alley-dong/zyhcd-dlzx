package com.dlzx.pfservercar.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 维修保养-维修保养单对象 r_repair
 * 
 * @author yangluhe
 * @date 2020-12-04
 */
public class RRepair extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId;

    /** 维修单号 */
    @Excel(name = "维修单号")
    private String code;

    /** 维修类型 1主车维修 2挂车维修 */
    @Excel(name = "维修类型 1主车维修 2挂车维修")
    private Long type;

    /** 维修状态 1未开始 2进行中 3已完成 */
    @Excel(name = "维修状态 1未开始 2进行中 3已完成")
    private Long repairStatus;

    /** 司机ID */
    @Excel(name = "司机ID")
    private String driverId;

    /** 司机姓名 */
    @Excel(name = "司机姓名")
    private String driverName;

    /** 主车ID */
    @Excel(name = "主车ID")
    private String mainVehicleId;

    /** 主车车牌号 */
    @Excel(name = "主车车牌号")
    private String mainLicensePlate;

    /** 主车车型ID */
    @Excel(name = "主车车型ID")
    private String mainVehicleModel;

    /** 主车车型 */
    @Excel(name = "主车车型")
    private String mainVehicleModelName;

    /** 挂车ID */
    @Excel(name = "挂车ID")
    private String hangVehicleId;

    /** 挂车车牌号 */
    @Excel(name = "挂车车牌号")
    private String hangLicensePlate;

    /** 挂车车型ID */
    @Excel(name = "挂车车型ID")
    private String hangVehicleModel;

    /** 挂车车型 */
    @Excel(name = "挂车车型")
    private String hangVehicleModelName;

    /** 当前公里数 */
    @Excel(name = "当前公里数")
    private String mileage;

    /** 行驶路线ID */
    @Excel(name = "行驶路线ID")
    private String routeId;

    /** 行驶路线 */
    @Excel(name = "行驶路线")
    private String routeName;

    /** 故障地点 */
    @Excel(name = "故障地点")
    private String accidentLocation;

    /** 总质量 */
    @Excel(name = "总质量")
    private String totalMass;

    /** 当前路况 */
    @Excel(name = "当前路况")
    private Long roadCondition;

    private String road;

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    /** 天气情况 */
    @Excel(name = "天气情况")
    private Long weatherCondition;

    private String weather;

    /** 维保类型 1正常维修 2事故维修 3保养 */
    @Excel(name = "维保类型 1正常维修 2事故维修 3保养")
    private Long repairType;

    /** 是否异地维修 1是 2否 */
    @Excel(name = "是否异地维修 1是 2否")
    private Long isOther;

    /** 事故ID */
    @Excel(name = "事故ID")
    private String accidentId;

    /** 事故时间 */
    @Excel(name = "事故时间")
    private String accidentTime;

    /** 事故视频 */
    @Excel(name = "事故视频")
    private String accidentVideo;

    /** 维修站ID-关联r_garage表 */
    @Excel(name = "维修站ID-关联r_garage表")
    private String garageId;

    /** 维修站名称 */
    @Excel(name = "维修站名称")
    private String garageName;

    /** 完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date completeTime;

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

    private String repairDetialPhoto;

    /**地址*/
    private String address;

    /**电话*/
    private String mobile;

    /**维修站审核状态*/
    private Long garageStatus;

    /**维修站审核时间*/
    private Date garageReviewTime;

    /**是否非定点维修*/
    private Long isFixed;

    /**App里维修保养新增维修保养项*/
    private List<RRepairItemDate> repairItemDate;

    /**维修站审核人*/
    private String garageReviewUser;

    /**维修站审核备注*/
    private String garageReviewRemarks;

    private Long repairDuration;


    /** 维修服务站附件文件 url（多个逗号隔开） */
    private List<String> fileUrls;

    /** 维修服务站附件文件(返回)*/
    private List<String> fileList;


    public List<String> getFileUrls() {
        return fileUrls;
    }

    public void setFileUrls(List<String> fileUrls) {
        this.fileUrls = fileUrls;
    }

    public List<String> getFileList() {
        return fileList;
    }

    public void setFileList(List<String> fileList) {
        this.fileList = fileList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getIsFixed() {
        return isFixed;
    }

    public void setIsFixed(Long isFixed) {
        this.isFixed = isFixed;
    }

    public List<RRepairItemDate> getRepairItemDate() {
        return repairItemDate;
    }

    public void setRepairItemDate(List<RRepairItemDate> repairItemDate) {
        this.repairItemDate = repairItemDate;
    }

    public Long getGarageStatus() {
        return garageStatus;
    }

    public void setGarageStatus(Long garageStatus) {
        this.garageStatus = garageStatus;
    }

    public Date getGarageReviewTime() {
        return garageReviewTime;
    }

    public void setGarageReviewTime(Date garageReviewTime) {
        this.garageReviewTime = garageReviewTime;
    }

    public String getGarageReviewUser() {
        return garageReviewUser;
    }

    public void setGarageReviewUser(String garageReviewUser) {
        this.garageReviewUser = garageReviewUser;
    }

    public String getGarageReviewRemarks() {
        return garageReviewRemarks;
    }

    public void setGarageReviewRemarks(String garageReviewRemarks) {
        this.garageReviewRemarks = garageReviewRemarks;
    }

    public Long getRepairDuration() {
        return repairDuration;
    }

    public void setRepairDuration(Long repairDuration) {
        this.repairDuration = repairDuration;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getStartTime() {
        return startTime;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinshTime() {
        return finshTime;
    }

    public void setFinshTime(Date finshTime) {
        this.finshTime = finshTime;
    }

    private Date startTime;

    private Date finshTime;

    public String getRepairDetialPhoto() {
        return repairDetialPhoto;
    }

    public void setRepairDetialPhoto(String repairDetialPhoto) {
        this.repairDetialPhoto = repairDetialPhoto;
    }

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setRepairStatus(Long repairStatus) 
    {
        this.repairStatus = repairStatus;
    }

    public Long getRepairStatus() 
    {
        return repairStatus;
    }
    public void setDriverId(String driverId) 
    {
        this.driverId = driverId;
    }

    public String getDriverId() 
    {
        return driverId;
    }
    public void setDriverName(String driverName) 
    {
        this.driverName = driverName;
    }

    public String getDriverName() 
    {
        return driverName;
    }
    public void setMainVehicleId(String mainVehicleId) 
    {
        this.mainVehicleId = mainVehicleId;
    }

    public String getMainVehicleId() 
    {
        return mainVehicleId;
    }
    public void setMainLicensePlate(String mainLicensePlate) 
    {
        this.mainLicensePlate = mainLicensePlate;
    }

    @NotBlank(message = "主车车牌不能为空")
    public String getMainLicensePlate() 
    {
        return mainLicensePlate;
    }
    public void setMainVehicleModel(String mainVehicleModel) 
    {
        this.mainVehicleModel = mainVehicleModel;
    }

    public String getMainVehicleModel() 
    {
        return mainVehicleModel;
    }
    public void setMainVehicleModelName(String mainVehicleModelName) 
    {
        this.mainVehicleModelName = mainVehicleModelName;
    }

    @NotBlank(message = "车辆型号不能为空")
    public String getMainVehicleModelName() 
    {
        return mainVehicleModelName;
    }
    public void setHangVehicleId(String hangVehicleId) 
    {
        this.hangVehicleId = hangVehicleId;
    }

    public String getHangVehicleId() 
    {
        return hangVehicleId;
    }
    public void setHangLicensePlate(String hangLicensePlate) 
    {
        this.hangLicensePlate = hangLicensePlate;
    }

    @NotBlank(message = "挂车车牌号不能为空")
    public String getHangLicensePlate() 
    {
        return hangLicensePlate;
    }
    public void setHangVehicleModel(String hangVehicleModel) 
    {
        this.hangVehicleModel = hangVehicleModel;
    }

    public String getHangVehicleModel()
    {
        return hangVehicleModel;
    }
    public void setHangVehicleModelName(String hangVehicleModelName) 
    {
        this.hangVehicleModelName = hangVehicleModelName;
    }

    @NotBlank(message = "挂车车型不能为空")
    public String getHangVehicleModelName() 
    {
        return hangVehicleModelName;
    }
    public void setMileage(String mileage) 
    {
        this.mileage = mileage;
    }

    @NotBlank(message = "当前公里数不能为空")
    public String getMileage() 
    {
        return mileage;
    }
    public void setRouteId(String routeId) 
    {
        this.routeId = routeId;
    }

    public String getRouteId() 
    {
        return routeId;
    }
    public void setRouteName(String routeName) 
    {
        this.routeName = routeName;
    }

    @NotBlank(message = "行驶路线不能为空")
    public String getRouteName() 
    {
        return routeName;
    }
    public void setAccidentLocation(String accidentLocation) 
    {
        this.accidentLocation = accidentLocation;
    }

    @NotBlank(message = "故障地点不能为空")
    public String getAccidentLocation() 
    {
        return accidentLocation;
    }
    public void setTotalMass(String totalMass) 
    {
        this.totalMass = totalMass;
    }

    @NotBlank(message = "总质量不能为空")
    public String getTotalMass() 
    {
        return totalMass;
    }
    public void setRoadCondition(Long roadCondition) 
    {
        this.roadCondition = roadCondition;
    }

    public Long getRoadCondition() 
    {
        return roadCondition;
    }
    public void setWeatherCondition(Long weatherCondition) 
    {
        this.weatherCondition = weatherCondition;
    }

    public Long getWeatherCondition() 
    {
        return weatherCondition;
    }
    public void setRepairType(Long repairType) 
    {
        this.repairType = repairType;
    }

    @NotNull(message = "维保类型不能为空")
    public Long getRepairType() 
    {
        return repairType;
    }
    public void setIsOther(Long isOther) 
    {
        this.isOther = isOther;
    }

    @NotNull(message = "是否异地维修不能为空")
    public Long getIsOther() 
    {
        return isOther;
    }
    public void setAccidentId(String accidentId) 
    {
        this.accidentId = accidentId;
    }

    public String getAccidentId() 
    {
        return accidentId;
    }
    public void setAccidentTime(String accidentTime) 
    {
        this.accidentTime = accidentTime;
    }

    public String getAccidentTime() 
    {
        return accidentTime;
    }
    public void setAccidentVideo(String accidentVideo) 
    {
        this.accidentVideo = accidentVideo;
    }

    public String getAccidentVideo() 
    {
        return accidentVideo;
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
    public void setCompleteTime(Date completeTime) 
    {
        this.completeTime = completeTime;
    }

    public Date getCompleteTime() 
    {
        return completeTime;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("type", getType())
            .append("repairStatus", getRepairStatus())
            .append("driverId", getDriverId())
            .append("driverName", getDriverName())
            .append("mainVehicleId", getMainVehicleId())
            .append("mainLicensePlate", getMainLicensePlate())
            .append("mainVehicleModel", getMainVehicleModel())
            .append("mainVehicleModelName", getMainVehicleModelName())
            .append("hangVehicleId", getHangVehicleId())
            .append("hangLicensePlate", getHangLicensePlate())
            .append("hangVehicleModel", getHangVehicleModel())
            .append("hangVehicleModelName", getHangVehicleModelName())
            .append("mileage", getMileage())
            .append("routeId", getRouteId())
            .append("routeName", getRouteName())
            .append("accidentLocation", getAccidentLocation())
            .append("totalMass", getTotalMass())
            .append("roadCondition", getRoadCondition())
            .append("weatherCondition", getWeatherCondition())
            .append("repairType", getRepairType())
            .append("isOther", getIsOther())
            .append("accidentId", getAccidentId())
            .append("accidentTime", getAccidentTime())
            .append("accidentVideo", getAccidentVideo())
            .append("garageId", getGarageId())
            .append("garageName", getGarageName())
            .append("completeTime", getCompleteTime())
            .append("status", getStatus())
            .append("reviewTime", getReviewTime())
            .append("reviewUser", getReviewUser())
            .append("reviewRemarks", getReviewRemarks())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
                .append("repairDuration",getRepairDuration())
                .append("garageStatus",getGarageStatus())
                .append("garageReviewTime",getGarageReviewTime())
                .append("garageReviewUser",getGarageReviewUser())
                .append("garageReviewRemarks")
                .append("beginTime",getBeginTime())
                .append("endTime",getEndTime())
                .append("repairItemDate",getRepairItemDate())
                .append("road",getRoad())
                .append("weather",getWeather())
                .append("repairDetialPhoto",getRepairDetialPhoto())
                .append("startTime",getStartTime())
                .append("finshTime",getFinshTime())
                .append("isFixed",getIsFixed())
            .toString();
    }
}
