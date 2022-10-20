package com.dlzx.pfserverperson.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 驾驶员-长途绩效记录对象 d_performance_record
 *
 * @author dlzx
 * @date 2020-12-21
 */
public class DPerformanceRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

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
     * 任务ID
     */
    @Excel(name = "任务ID")
    private String taskId;

    /**
     * 绩效ID
     */
    @Excel(name = "绩效ID")
    private String performanceId;

    /**
     * 绩效名称
     */
    @Excel(name = "绩效名称")
    private String performanceName;

    /**
     * 任务开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskStartTime;

    /**
     * 任务结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskEndTime;

    /**
     * 任务状态 1异常 2正常
     */
    @Excel(name = "任务状态 1异常 2正常")
    private Long taskStatus;

    /**
     * 公里数
     */
    @Excel(name = "公里数")
    private Long kilometre;

    /**
     * 对班司机ID
     */
    @Excel(name = "对班司机ID")
    private String viceDriverId;

    /**
     * 对班司机名称
     */
    @Excel(name = "对班司机名称")
    private String viceDriverName;

    /**
     * 绩效金额
     */
    @Excel(name = "绩效金额")
    private BigDecimal amount;

     /**
     * 篷布补助
     */
    private BigDecimal tarpaulin;

    /**
     * 本月绩效工资
     */
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

    public BigDecimal getTarpaulin() {
        return tarpaulin;
    }

    public void setTarpaulin(BigDecimal tarpaulin) {
        this.tarpaulin = tarpaulin;
    }

    public BigDecimal getAmo() {
        return amo;
    }

    public void setAmo(BigDecimal amo) {
        this.amo = amo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setPerformanceId(String performanceId) {
        this.performanceId = performanceId;
    }

    public String getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceName(String performanceName) {
        this.performanceName = performanceName;
    }

    public String getPerformanceName() {
        return performanceName;
    }

    public void setTaskStartTime(Date taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public Date getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskEndTime(Date taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public Date getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskStatus(Long taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Long getTaskStatus() {
        return taskStatus;
    }

    public void setViceDriverId(String viceDriverId) {
        this.viceDriverId = viceDriverId;
    }

    public String getViceDriverId() {
        return viceDriverId;
    }

    public void setViceDriverName(String viceDriverName) {
        this.viceDriverName = viceDriverName;
    }

    public String getViceDriverName() {
        return viceDriverName;
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
                .append("driverId", getDriverId())
                .append("driverName", getDriverName())
                .append("taskId", getTaskId())
                .append("performanceId", getPerformanceId())
                .append("performanceName", getPerformanceName())
                .append("taskStartTime", getTaskStartTime())
                .append("taskEndTime", getTaskEndTime())
                .append("taskStatus", getTaskStatus())
                .append("viceDriverId", getViceDriverId())
                .append("viceDriverName", getViceDriverName())
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
                .append("amo", getAmo())
                .append("kilometre", getKilometre())
                .toString();
    }

    public Long getKilometre() {
        return kilometre;
    }

    public void setKilometre(Long kilometre) {
        this.kilometre = kilometre;
    }
}
