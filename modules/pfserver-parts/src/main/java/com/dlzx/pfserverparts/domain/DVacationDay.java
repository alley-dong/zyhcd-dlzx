package com.dlzx.pfserverparts.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 驾驶员-每日考勤记录对象 d_vacation_day
 * 
 * @author dlzx
 * @date 2020-12-21
 */
public class DVacationDay extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 考勤日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "考勤日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date vacationDate;

    /** 司机ID */
    @Excel(name = "司机ID")
    private String driverId;

    /** 司机名称 */
    @Excel(name = "司机名称")
    private String driverName;

    /** 出勤状态 1在途 2在家 3请假 */
    @Excel(name = "出勤状态 1在途 2在家 3请假")
    private Long status;

    /** 驾驶员类型 */
    @Excel(name = "驾驶员类型")
    private String driverTypeId;

    /** 驾驶员类型 */
    @Excel(name = "驾驶员类型")
    private String MONTH;

    /** 驾驶员类型 */
    @Excel(name = "驾驶员类型")
    private int totalMouth;

    /** 每日底薪金额 */
    @Excel(name = "每日底薪金额")
    private BigDecimal basicSalary;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag;
    /** 在途 */
    private int way;
    /** 在家 */
    private int atHome;
    /** 请假 */
    private int leave;
    /** 出勤天数 */
    private int totalAttendance;

    /**每个月16日*/
    private Date firstSalaryDate;

    public Date getFirstSalaryDate() {
        return firstSalaryDate;
    }

    public void setFirstSalaryDate(Date firstSalaryDate) {
        this.firstSalaryDate = firstSalaryDate;
    }

    public Date getNextSalaryDate() {
        return nextSalaryDate;
    }

    public void setNextSalaryDate(Date nextSalaryDate) {
        this.nextSalaryDate = nextSalaryDate;
    }

    /**下个月15日*/
    private Date nextSalaryDate;

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    /**每个月的底薪金额*/
    private String salary;

    /** 存放日期和状态 */
    private List<DVacationDay> dVacationDayList;

    public int getWay() {
        return way;
    }

    public void setWay(int way) {
        this.way = way;
    }

    public int getAtHome() {
        return atHome;
    }

    public void setAtHome(int atHome) {
        this.atHome = atHome;
    }

    public int getLeave() {
        return leave;
    }

    public void setLeave(int leave) {
        this.leave = leave;
    }

    public int getTotalAttendance() {
        return totalAttendance;
    }

    public void setTotalAttendance(int totalAttendance) {
        this.totalAttendance = totalAttendance;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setVacationDate(Date vacationDate) 
    {
        this.vacationDate = vacationDate;
    }

    public Date getVacationDate() 
    {
        return vacationDate;
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
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setDriverTypeId(String driverTypeId) 
    {
        this.driverTypeId = driverTypeId;
    }

    public String getDriverTypeId() 
    {
        return driverTypeId;
    }
    public void setBasicSalary(BigDecimal basicSalary) 
    {
        this.basicSalary = basicSalary;
    }

    public BigDecimal getBasicSalary() 
    {
        return basicSalary;
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
            .append("vacationDate", getVacationDate())
            .append("driverId", getDriverId())
            .append("driverName", getDriverName())
            .append("status", getStatus())
            .append("driverTypeId", getDriverTypeId())
            .append("basicSalary", getBasicSalary())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("MONTH", getMONTH())
            .append("totalMouth", getTotalMouth())
            .append("dVacationDayList", getdVacationDayList())
            .append("way", getWay())
            .append("atHome", getAtHome())
            .append("leave", getLeave())
            .append("totalAttendance", getTotalAttendance())
                .append("salary",getSalary())
            .toString();
    }

    public String getMONTH() {
        return MONTH;
    }

    public void setMONTH(String MONTH) {
        this.MONTH = MONTH;
    }

    public int getTotalMouth() {
        return totalMouth;
    }

    public void setTotalMouth(int totalMouth) {
        this.totalMouth = totalMouth;
    }

    public List<DVacationDay> getdVacationDayList() {
        return dVacationDayList;
    }

    public void setdVacationDayList(List<DVacationDay> dVacationDayList) {
        this.dVacationDayList = dVacationDayList;
    }
}
