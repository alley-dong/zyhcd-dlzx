package com.dlzx.pfserverperson.domain.commonDomain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author ylh
 * @program:dlzx
 * @description：
 * @create:2020-11-19 21-08
 */
public class DriverOperating {
    private static final long serialVersionUID = 1L;

    private String id;

    //实操直线
    private Long driveStraightDrivingStatus;

    //直线备注
    private String driveStraightDrivingRemarks;

    //实操倒车
    private Long driveParkingStatus;

    //倒车备注
    private String driveParkingRemarks;

    //实操故障
    private Long driveEmergencyStatus;

    //故障备注
    private String driveEmergencyRemarks;

    //实操状态
    private Long driveStatus;

    //实操备注
    private String driveRemarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDriveStraightDrivingStatus() {
        return driveStraightDrivingStatus;
    }

    public void setDriveStraightDrivingStatus(Long driveStraightDrivingStatus) {
        this.driveStraightDrivingStatus = driveStraightDrivingStatus;
    }

    public String getDriveStraightDrivingRemarks() {
        return driveStraightDrivingRemarks;
    }

    public void setDriveStraightDrivingRemarks(String driveStraightDrivingRemarks) {
        this.driveStraightDrivingRemarks = driveStraightDrivingRemarks;
    }

    public Long getDriveParkingStatus() {
        return driveParkingStatus;
    }

    public void setDriveParkingStatus(Long driveParkingStatus) {
        this.driveParkingStatus = driveParkingStatus;
    }

    public String getDriveParkingRemarks() {
        return driveParkingRemarks;
    }

    public void setDriveParkingRemarks(String driveParkingRemarks) {
        this.driveParkingRemarks = driveParkingRemarks;
    }

    public Long getDriveEmergencyStatus() {
        return driveEmergencyStatus;
    }

    public void setDriveEmergencyStatus(Long driveEmergencyStatus) {
        this.driveEmergencyStatus = driveEmergencyStatus;
    }

    public String getDriveEmergencyRemarks() {
        return driveEmergencyRemarks;
    }

    public void setDriveEmergencyRemarks(String driveEmergencyRemarks) {
        this.driveEmergencyRemarks = driveEmergencyRemarks;
    }

    public Long getDriveStatus() {
        return driveStatus;
    }

    public void setDriveStatus(Long driveStatus) {
        this.driveStatus = driveStatus;
    }

    public String getDriveRemarks() {
        return driveRemarks;
    }

    public void setDriveRemarks(String driveRemarks) {
        this.driveRemarks = driveRemarks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("driveStraightDrivingStatus", getDriveStraightDrivingStatus())
                .append("driveStraightDrivingRemarks", getDriveStraightDrivingRemarks())
                .append("driveParkingStatus", getDriveParkingStatus())
                .append("driveParkingRemarks",getDriveParkingRemarks())
                .append("driveEmergencyStatus", getDriveEmergencyStatus())
                .append("driveEmergencyRemarks", getDriveEmergencyRemarks())
                .append("driveStatus", getDriveStatus())
                .append("driveRemarks", getDriveRemarks())
                .toString();
    }
}
