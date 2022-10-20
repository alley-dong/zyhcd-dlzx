package com.dlzx.pfserverperson.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 驾驶员-加班绩效记录对象 d_performance_overtime_record
 *
 * @author dlzx
 * @date 2020-12-22
 */
public class DPerformanceOvertimeRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 任务ID
     */
    @Excel(name = "任务ID")
    private String taskId;

    /**
     * 加班日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "加班日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date performanceDate;

    /**
     * 驾驶员ID
     */
    @Excel(name = "驾驶员ID")
    private String driverId;

    /**
     * 驾驶员名称
     */
    @Excel(name = "驾驶员名称")
    private String driverName;

    /**
     * 加班费用
     */
    @Excel(name = "加班费用")
    private BigDecimal amount;

    public BigDecimal getAmo() {
        return amo;
    }

    public void setAmo(BigDecimal amo) {
        this.amo = amo;
    }

    /**一个月的加班费用*/
    private BigDecimal amo;


    /**
     * 主车ID
     */
    @Excel(name = "主车ID")
    private String mainVehicleId;

    /**
     * 主车车牌号
     */
    @Excel(name = "主车车牌号")
    private String mainLicensePlate;

    /**
     * 挂车ID
     */
    @Excel(name = "挂车ID")
    private String hangVehicleId;

    /**
     * 挂车车牌号
     */
    @Excel(name = "挂车车牌号")
    private String hangLicensePlate;

    /**
     * 删除状态 1正常 2删除
     */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag;

    private Date bTime;

    private Date eTime;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setPerformanceDate(Date performanceDate) {
        this.performanceDate = performanceDate;
    }

    public Date getPerformanceDate() {
        return performanceDate;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setMainVehicleId(String mainVehicleId) {
        this.mainVehicleId = mainVehicleId;
    }

    public String getMainVehicleId() {
        return mainVehicleId;
    }

    public void setMainLicensePlate(String mainLicensePlate) {
        this.mainLicensePlate = mainLicensePlate;
    }

    public String getMainLicensePlate() {
        return mainLicensePlate;
    }

    public void setHangVehicleId(String hangVehicleId) {
        this.hangVehicleId = hangVehicleId;
    }

    public String getHangVehicleId() {
        return hangVehicleId;
    }

    public void setHangLicensePlate(String hangLicensePlate) {
        this.hangLicensePlate = hangLicensePlate;
    }

    public String getHangLicensePlate() {
        return hangLicensePlate;
    }

    public void setDeleteFlag(Long deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Long getDeleteFlag() {
        return deleteFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("taskId", getTaskId())
                .append("performanceDate", getPerformanceDate())
                .append("driverId", getDriverId())
                .append("driverName", getDriverName())
                .append("amount", getAmount())
                .append("mainVehicleId", getMainVehicleId())
                .append("mainLicensePlate", getMainLicensePlate())
                .append("hangVehicleId", getHangVehicleId())
                .append("hangLicensePlate", getHangLicensePlate())
                .append("deleteFlag", getDeleteFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("eTime", geteTime())
                .append("bTime", getbTime())
                .append("amo",getAmo())
                .toString();
    }

    public Date getbTime() {
        return bTime;
    }

    public void setbTime(Date bTime) {
        this.bTime = bTime;
    }

    public Date geteTime() {
        return eTime;
    }

    public void seteTime(Date eTime) {
        this.eTime = eTime;
    }
}
