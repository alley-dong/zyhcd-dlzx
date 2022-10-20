package com.dlzx.common.core.web.domain;

import java.util.Date;

/**
 * 类描述：driver DTO
 * @className TMSDriverDto
 * company 赛拓信息技术有限公司
 * @author DongCL
 * @date 2021-02-02 18:38
 */
public class TMSDriverDto {

    private String driverName;
    private String sourceId;
    private String drivingLicense;
    private String vehicleClass;
    private String issuingOrganizations;
    private String validPeriodFrom;
    private String validPeriodTo;
    private String qualificationCertificate;
    private String telephone;
    private String carNumber;
    private String servprovName;
    private String enabledFlag;
    private String remark;
    private String senderName;
    private String receiverName;
    private String status;
    private String code;
    private String message;
    private Date transactionTime;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public String getIssuingOrganizations() {
        return issuingOrganizations;
    }

    public void setIssuingOrganizations(String issuingOrganizations) {
        this.issuingOrganizations = issuingOrganizations;
    }

    public String getValidPeriodFrom() {
        return validPeriodFrom;
    }

    public void setValidPeriodFrom(String validPeriodFrom) {
        this.validPeriodFrom = validPeriodFrom;
    }

    public String getValidPeriodTo() {
        return validPeriodTo;
    }

    public void setValidPeriodTo(String validPeriodTo) {
        this.validPeriodTo = validPeriodTo;
    }

    public String getQualificationCertificate() {
        return qualificationCertificate;
    }

    public void setQualificationCertificate(String qualificationCertificate) {
        this.qualificationCertificate = qualificationCertificate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getServprovName() {
        return servprovName;
    }

    public void setServprovName(String servprovName) {
        this.servprovName = servprovName;
    }

    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }
}
