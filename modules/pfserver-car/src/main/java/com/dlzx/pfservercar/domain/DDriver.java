package com.dlzx.pfservercar.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.dlzx.common.core.web.domain.TMSDriverDto;
import com.dlzx.pfservercar.domain.model.OTMDriverModel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

/**
 * 驾驶员-司机档案对象 d_driver
 * 
 * @author yangluhe
 * @date 2020-11-18
 */
public class DDriver extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 状态 1进行中 2未通过 3已通过 4储备 */
    @Excel(name = "状态 1进行中 2未通过 3已通过 4储备")
    private Long status;

    /** 司机姓名 */
    @Excel(name = "司机姓名")
    private String name;

    /** 司机性别 */
    @Excel(name = "司机性别")
    private Long sex;

    /** 出生日期 */
    /*@JsonFormat(pattern = "yyyy-MM-dd")*/
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String idCard;

    /** 民族 */
    @Excel(name = "民族")
    private String nation;

    /** 政治面貌 */
    @Excel(name = "政治面貌")
    private String politicalAffiliation;

    /** 婚姻状况 */
    @Excel(name = "婚姻状况")
    private String maritalStatus;

    /** 学历 */
    @Excel(name = "学历")
    private String education;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contact;

    /** 紧急联系人电话 */
    @Excel(name = "紧急联系人电话")
    private String urgentTel;

    /** 驾照类型 */
    @Excel(name = "驾照类型")
    private String drivingLicenseType;

    /** 驾照获取时间 */
   /* @JsonFormat(pattern = "yyyy-MM-dd")*/
    @Excel(name = "驾照获取时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date drivingLicenseTime;

    /** 驾龄 */
    @Excel(name = "驾龄")
    private String driveAge;

    /** 入职时间 */
    /*@JsonFormat(pattern = "yyyy-MM-dd")*/
    @Excel(name = "入职时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dntryTime;

    /** 家庭地址 */
    @Excel(name = "家庭地址")
    private String address;

    /** 驾驶员类型 */
    @Excel(name = "驾驶员类型")
    private String driverTypeId;

    /** 绩效类型 1长途 2短途 */
    @Excel(name = "绩效类型 1长途 2短途")
    private Long performanceType;

    /** 员工类型 1正式工 1合同工 */
    @Excel(name = "员工类型 1正式工 1合同工")
    private Long staffType;

    /** 面试记录 */
    @Excel(name = "面试记录")
    private String interviewRecord;

    /** 面试状态 1进行中 2未通过 3已通过 4储备 */
    @Excel(name = "面试状态 1进行中 2未通过 3已通过 4储备")
    private Long interviewStatus;

    /** 面试备注 */
    @Excel(name = "面试备注")
    private String interviewRemarks;

    /** 笔试状态 1进行中 2未通过 3已通过 4储备 */
    @Excel(name = "笔试状态 1进行中 2未通过 3已通过 4储备")
    private Long examStatus;

    /** 笔试备注 */
    @Excel(name = "笔试备注")
    private String examRemarks;

    /** 实操-直线行驶状态 1进行中 2未通过 3已通过 */
    @Excel(name = "实操-直线行驶状态 1进行中 2未通过 3已通过")
    private Long driveStraightDrivingStatus;

    /** 实操-直线行驶备注 */
    @Excel(name = "实操-直线行驶备注")
    private String driveStraightDrivingRemarks;

    /** 实操-倒车入库状态 1进行中 2未通过 3已通过 */
    @Excel(name = "实操-倒车入库状态 1进行中 2未通过 3已通过")
    private Long driveParkingStatus;

    /** 实操-倒车入库备注 */
    @Excel(name = "实操-倒车入库备注")
    private String driveParkingRemarks;

    /** 实操-故障处理状态 1进行中 2未通过 3已通过 */
    @Excel(name = "实操-故障处理状态 1进行中 2未通过 3已通过")
    private Long driveEmergencyStatus;

    /** 实操-故障处理备注 */
    @Excel(name = "实操-故障处理备注")
    private String driveEmergencyRemarks;

    /** 实操状态 1进行中 2未通过 3已通过 4储备 */
    @Excel(name = "实操状态 1进行中 2未通过 3已通过 4储备")
    private Long driveStatus;

    /** 实操备注 */
    @Excel(name = "实操备注")
    private String driveRemarks;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createUser;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updateUser;

    /** 删除标识 */
    private int deleteFlag;

    /**密码*/
    private String password;

    /**标识推送使用*/
    private String token;

    /**设备类型*/
    private String deviceType;

    /**启用禁用状态*/
    private Long disableStatus;

    /**驾驶员类型名称*/
    private String driverTypeName;

    /**附件地址*/
    private List<String> fileUrl;

    /**从业资格证号*/
    private String qualification;

    private String oldPassword;
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @NotBlank(message = "从业资格证不能为空")
    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public List<String> getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(List<String> fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getDriverTypeName() {
        return driverTypeName;
    }

    public void setDriverTypeName(String driverTypeName) {
        this.driverTypeName = driverTypeName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @NotBlank(message = "是否生效不能为空")
    public Long getDisableStatus() {
        return disableStatus;
    }

    public void setDisableStatus(Long disableStatus) {
        this.disableStatus = disableStatus;
    }

    public String getDriverPhoto() {
        return driverPhoto;
    }

    public void setDriverPhoto(String driverPhoto) {
        this.driverPhoto = driverPhoto;
    }

    /** 上传照片路径 */
    private String driverPhoto;

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    @NotBlank(message = "姓名不能为空")
    public String getName() 
    {
        return name;
    }
    public void setSex(Long sex) 
    {
        this.sex = sex;
    }

    @NotNull(message = "性别不能为空")
    public Long getSex() 
    {
        return sex;
    }
    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }

    public Date getBirthday() 
    {
        return birthday;
    }
    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    @NotBlank(message = "身份证号不能为空")
    public String getIdCard() 
    {
        return idCard;
    }
    public void setNation(String nation) 
    {
        this.nation = nation;
    }

    public String getNation() 
    {
        return nation;
    }
    public void setPoliticalAffiliation(String politicalAffiliation) 
    {
        this.politicalAffiliation = politicalAffiliation;
    }

    public String getPoliticalAffiliation() 
    {
        return politicalAffiliation;
    }
    public void setMaritalStatus(String maritalStatus) 
    {
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatus() 
    {
        return maritalStatus;
    }
    public void setEducation(String education) 
    {
        this.education = education;
    }

    public String getEducation() 
    {
        return education;
    }
    public void setContact(String contact) 
    {
        this.contact = contact;
    }

    @NotBlank(message = "联系电话不能为空")
    public String getContact() 
    {
        return contact;
    }
    public void setUrgentTel(String urgentTel) 
    {
        this.urgentTel = urgentTel;
    }

    public String getUrgentTel() 
    {
        return urgentTel;
    }
    public void setDrivingLicenseType(String drivingLicenseType) 
    {
        this.drivingLicenseType = drivingLicenseType;
    }

    @NotBlank(message = "准驾车型不能为空")
    public String getDrivingLicenseType() 
    {
        return drivingLicenseType;
    }
    public void setDrivingLicenseTime(Date drivingLicenseTime) 
    {
        this.drivingLicenseTime = drivingLicenseTime;
    }

    public Date getDrivingLicenseTime() 
    {
        return drivingLicenseTime;
    }
    public void setDriveAge(String driveAge) 
    {
        this.driveAge = driveAge;
    }

    public String getDriveAge() 
    {
        return driveAge;
    }
    public void setDntryTime(Date dntryTime) 
    {
        this.dntryTime = dntryTime;
    }

    public Date getDntryTime() 
    {
        return dntryTime;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setDriverTypeId(String driverTypeId) 
    {
        this.driverTypeId = driverTypeId;
    }

    public String getDriverTypeId() 
    {
        return driverTypeId;
    }
    public void setPerformanceType(Long performanceType) 
    {
        this.performanceType = performanceType;
    }

    public Long getPerformanceType() 
    {
        return performanceType;
    }
    public void setStaffType(Long staffType) 
    {
        this.staffType = staffType;
    }

    public Long getStaffType() 
    {
        return staffType;
    }
    public void setInterviewRecord(String interviewRecord) 
    {
        this.interviewRecord = interviewRecord;
    }

    public String getInterviewRecord() 
    {
        return interviewRecord;
    }
    public void setInterviewStatus(Long interviewStatus) 
    {
        this.interviewStatus = interviewStatus;
    }

    public Long getInterviewStatus() 
    {
        return interviewStatus;
    }
    public void setInterviewRemarks(String interviewRemarks) 
    {
        this.interviewRemarks = interviewRemarks;
    }

    public String getInterviewRemarks() 
    {
        return interviewRemarks;
    }
    public void setExamStatus(Long examStatus) 
    {
        this.examStatus = examStatus;
    }

    public Long getExamStatus() 
    {
        return examStatus;
    }
    public void setExamRemarks(String examRemarks) 
    {
        this.examRemarks = examRemarks;
    }

    public String getExamRemarks() 
    {
        return examRemarks;
    }
    public void setDriveStraightDrivingStatus(Long driveStraightDrivingStatus) 
    {
        this.driveStraightDrivingStatus = driveStraightDrivingStatus;
    }

    public Long getDriveStraightDrivingStatus() 
    {
        return driveStraightDrivingStatus;
    }
    public void setDriveStraightDrivingRemarks(String driveStraightDrivingRemarks) 
    {
        this.driveStraightDrivingRemarks = driveStraightDrivingRemarks;
    }

    public String getDriveStraightDrivingRemarks() 
    {
        return driveStraightDrivingRemarks;
    }
    public void setDriveParkingStatus(Long driveParkingStatus) 
    {
        this.driveParkingStatus = driveParkingStatus;
    }

    public Long getDriveParkingStatus() 
    {
        return driveParkingStatus;
    }
    public void setDriveParkingRemarks(String driveParkingRemarks) 
    {
        this.driveParkingRemarks = driveParkingRemarks;
    }

    public String getDriveParkingRemarks() 
    {
        return driveParkingRemarks;
    }
    public void setDriveEmergencyStatus(Long driveEmergencyStatus) 
    {
        this.driveEmergencyStatus = driveEmergencyStatus;
    }

    public Long getDriveEmergencyStatus() 
    {
        return driveEmergencyStatus;
    }
    public void setDriveEmergencyRemarks(String driveEmergencyRemarks) 
    {
        this.driveEmergencyRemarks = driveEmergencyRemarks;
    }

    public String getDriveEmergencyRemarks() 
    {
        return driveEmergencyRemarks;
    }
    public void setDriveStatus(Long driveStatus) 
    {
        this.driveStatus = driveStatus;
    }

    public Long getDriveStatus() 
    {
        return driveStatus;
    }
    public void setDriveRemarks(String driveRemarks) 
    {
        this.driveRemarks = driveRemarks;
    }

    public String getDriveRemarks() 
    {
        return driveRemarks;
    }
    public void setCreateUser(String createUser) 
    {
        this.createUser = createUser;
    }

    public String getCreateUser() 
    {
        return createUser;
    }
    public void setUpdateUser(String updateUser) 
    {
        this.updateUser = updateUser;
    }

    public String getUpdateUser() 
    {
        return updateUser;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("status", getStatus())
            .append("name", getName())
            .append("sex", getSex())
            .append("birthday", getBirthday())
            .append("idCard", getIdCard())
            .append("nation", getNation())
            .append("politicalAffiliation", getPoliticalAffiliation())
            .append("maritalStatus", getMaritalStatus())
            .append("education", getEducation())
            .append("contact", getContact())
            .append("urgentTel", getUrgentTel())
            .append("drivingLicenseType", getDrivingLicenseType())
            .append("drivingLicenseTime", getDrivingLicenseTime())
            .append("driveAge", getDriveAge())
            .append("dntryTime", getDntryTime())
            .append("address", getAddress())
            .append("driverTypeId", getDriverTypeId())
            .append("performanceType", getPerformanceType())
            .append("staffType", getStaffType())
            .append("interviewRecord", getInterviewRecord())
            .append("interviewStatus", getInterviewStatus())
            .append("interviewRemarks", getInterviewRemarks())
            .append("examStatus", getExamStatus())
            .append("examRemarks", getExamRemarks())
            .append("driveStraightDrivingStatus", getDriveStraightDrivingStatus())
            .append("driveStraightDrivingRemarks", getDriveStraightDrivingRemarks())
            .append("driveParkingStatus", getDriveParkingStatus())
            .append("driveParkingRemarks", getDriveParkingRemarks())
            .append("driveEmergencyStatus", getDriveEmergencyStatus())
            .append("driveEmergencyRemarks", getDriveEmergencyRemarks())
            .append("driveStatus", getDriveStatus())
            .append("driveRemarks", getDriveRemarks())
            .append("createUser", getCreateUser())
            .append("createTime", getCreateTime())
            .append("updateUser", getUpdateUser())
            .append("updateTime", getUpdateTime())
                .append("deleteFlag",getDeleteFlag())
                .append("driverPhoto",getDriverPhoto())
                .append("password",getPassword())
                .append("token",getToken())
                .append("deviceType",getDeviceType())
                .append("disableStatus",getDisableStatus())
                .append("driverTypeName",getDriverTypeName())
                .append("fileUrl",getFileUrl())
                .append("qualification",getQualification())
            .toString();
    }


    /**
     * @describe Bean赋值
     * @author DongCL
     * @date 2021-01-06 14:26
     * @return OTMDriverModel
     */
    public OTMDriverModel convert() {
        return new Function<DDriver, OTMDriverModel>() {
            @Override
            public OTMDriverModel apply(DDriver dDriver) {
                OTMDriverModel otmDriverModel = new OTMDriverModel();
                //属性拷贝
                //主键ID
                otmDriverModel.setTEMPLATE_ID(dDriver.getId());
                //司机姓名
                otmDriverModel.setNAME(dDriver.getName());
                //手机号
                otmDriverModel.setPHONE(dDriver.getContact());
                //身份证号
                otmDriverModel.setID_NUMBER(dDriver.getIdCard());
                //从业资格证号
                otmDriverModel.setQUALIFICATION_CERTIFICATE(dDriver.getQualification());
                //是否生效
                otmDriverModel.setENABLED_FLAG(dDriver.getDisableStatus()==1L?"Y":"N");
                //准驾车型
                otmDriverModel.setVEHICLE_CLASS(dDriver.getDrivingLicenseType());
                return otmDriverModel;
            }
        }.apply(this);
    }



    /**
     * @describe Bean赋值
     * @author DongCL
     * @date 2021-01-06 14:26
     * @return OTMDriverModel
     */
    public TMSDriverDto TmsConvert() {
        return new Function<DDriver, TMSDriverDto>() {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            @Override
            public TMSDriverDto apply(DDriver dDriver) {
                TMSDriverDto tmsDriverDto = new TMSDriverDto();
                //属性拷贝
                tmsDriverDto.setDriverName(dDriver.getName());
                tmsDriverDto.setSourceId(dDriver.getId());
                tmsDriverDto.setDrivingLicense(dDriver.getIdCard());
                tmsDriverDto.setValidPeriodFrom(dDriver.getDrivingLicenseTime()==null? formatter.format(new Date()):formatter.format(dDriver.getDrivingLicenseTime()));
                tmsDriverDto.setValidPeriodTo(dDriver.getDrivingLicenseTime()==null? formatter.format(new Date()):formatter.format(dDriver.getDrivingLicenseTime()));
                tmsDriverDto.setQualificationCertificate(dDriver.getQualification());
                tmsDriverDto.setTelephone(dDriver.getContact());
                tmsDriverDto.setServprovName("专业运营部");
                tmsDriverDto.setEnabledFlag(dDriver.getDeleteFlag()==1?"Y":"N");
                tmsDriverDto.setRemark(dDriver.getRemark());
                // 从业资格证
                tmsDriverDto.setQualificationCertificate(StringUtils.isEmpty(dDriver.getQualification())? "123456":dDriver.getQualification());
                return tmsDriverDto;
            }
        }.apply(this);
    }
}
