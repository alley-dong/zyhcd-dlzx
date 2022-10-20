package com.dlzx.pfservercar.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 统计报-车辆消耗月度统计对象 r_vehicle_consume_month
 * 
 * @author dlzx
 * @date 2020-12-15
 */
public class RVehicleConsumeMonth extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 月份 */
    @JsonFormat(pattern = "yyyy-MM")
    @Excel(name = "月份", width = 30, dateFormat = "yyyy-MM")
    private Date month;

    /** 车牌号码 */
    @Excel(name = "车牌号码")
    private String licensePlate;

    /** 车辆ID */
    @Excel(name = "车辆ID")
    private String vehicleId;

    /** 月末时点里程 */
    @Excel(name = "月末时点里程")
    private BigDecimal monthEndMileage;

    /** 月度行驶总里程（公里） */
    @Excel(name = "月度行驶总里程", readConverterExp = "公=里")
    private Long lastMonthEndMileage;

    /** 月度加油量（升） */
    @Excel(name = "月度加油量", readConverterExp = "升=")
    private Long monthFuelVolume;

    /** 月度尿素领用量（升） */
    @Excel(name = "月度尿素领用量", readConverterExp = "升=")
    private Long monthUreaVolume;

    /** 百公里油耗 */
    @Excel(name = "百公里油耗")
    private BigDecimal kmFuel;

    /** 百公里尿素消耗 */
    @Excel(name = "百公里尿素消耗")
    private BigDecimal kmUrea;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag;

    private Long number;
    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setMonth(Date month) 
    {
        this.month = month;
    }

    public Date getMonth() 
    {
        return month;
    }
    public void setLicensePlate(String licensePlate) 
    {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() 
    {
        return licensePlate;
    }
    public void setVehicleId(String vehicleId) 
    {
        this.vehicleId = vehicleId;
    }

    public String getVehicleId() 
    {
        return vehicleId;
    }
    public void setMonthEndMileage(BigDecimal monthEndMileage)
    {
        this.monthEndMileage = monthEndMileage;
    }

    public BigDecimal getMonthEndMileage()
    {
        return monthEndMileage;
    }
    public void setLastMonthEndMileage(Long lastMonthEndMileage) 
    {
        this.lastMonthEndMileage = lastMonthEndMileage;
    }

    public Long getLastMonthEndMileage() 
    {
        return lastMonthEndMileage;
    }
    public void setMonthFuelVolume(Long monthFuelVolume) 
    {
        this.monthFuelVolume = monthFuelVolume;
    }

    public Long getMonthFuelVolume() 
    {
        return monthFuelVolume;
    }
    public void setMonthUreaVolume(Long monthUreaVolume) 
    {
        this.monthUreaVolume = monthUreaVolume;
    }

    public Long getMonthUreaVolume() 
    {
        return monthUreaVolume;
    }
    public void setKmFuel(BigDecimal kmFuel) 
    {
        this.kmFuel = kmFuel;
    }

    public BigDecimal getKmFuel() 
    {
        return kmFuel;
    }
    public void setKmUrea(BigDecimal kmUrea) 
    {
        this.kmUrea = kmUrea;
    }

    public BigDecimal getKmUrea() 
    {
        return kmUrea;
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
            .append("month", getMonth())
            .append("licensePlate", getLicensePlate())
            .append("vehicleId", getVehicleId())
            .append("monthEndMileage", getMonthEndMileage())
            .append("lastMonthEndMileage", getLastMonthEndMileage())
            .append("monthFuelVolume", getMonthFuelVolume())
            .append("monthUreaVolume", getMonthUreaVolume())
            .append("kmFuel", getKmFuel())
            .append("kmUrea", getKmUrea())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
                .append("number", getNumber())
            .toString();
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
