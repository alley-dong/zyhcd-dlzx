package com.dlzx.pfservercar.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 驾驶员-短途绩效记录对象 d_performance_short_haul_record
 *
 * @author dlzx
 * @date 2020-12-21
 */
public class DPerformanceRecordDuan extends BaseEntity {
    private static final long serialVersionUID = 1L;
    //任务id
    private String id;
    /**
     * 绩效日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "绩效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskEndTime;

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
     * 驾驶员类型ID
     */
    @Excel(name = "驾驶员类型ID")
    private String driverTypeId;

    /**
     * 驾驶员类型名称
     */
    @Excel(name = "驾驶员类型名称")
    private String name;

    /**
     * 主车ID
     */
    @Excel(name = "主车ID")
    private String mainVehicleId;

    /**
     * 车牌号
     */
    @Excel(name = "车牌号")
    private String mainLicensePlate;


    /**
     * 绩效金额
     */
    @Excel(name = "绩效金额")
    private BigDecimal amount;


    public void setTaskEndTime(Date taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public Date getTaskEndTime() {
        return taskEndTime;
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

    public void setDriverTypeId(String driverTypeId) {
        this.driverTypeId = driverTypeId;
    }

    public String getDriverTypeId() {
        return driverTypeId;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("taskEndTime", getTaskEndTime())
                .append("driverId", getDriverId())
                .append("driverName", getDriverName())
                .append("mainVehicleId", getMainVehicleId())
                .append("driverTypeId", getDriverTypeId())
                .append("name", getName())
                .append("amount", getAmount())
                .append("mainLicensePlate", getMainLicensePlate())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainLicensePlate() {
        return mainLicensePlate;
    }

    public void setMainLicensePlate(String mainLicensePlate) {
        this.mainLicensePlate = mainLicensePlate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMainVehicleId() {
        return mainVehicleId;
    }

    public void setMainVehicleId(String mainVehicleId) {
        this.mainVehicleId = mainVehicleId;
    }
}
