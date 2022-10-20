package com.dlzx.pfservercar.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 燃料管理-充电记录对象 f_fuel_recharge
 * 
 * @author dlzx
 * @date 2020-12-17
 */
public class FFuelRecharge extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String licensePlate;

    /** 车辆ID */
//    @Excel(name = "车辆ID")
    private String vehicleId;

    /** 车架号(VIN)  */
    @Excel(name = "车架号VIN")
    private String frameNumber;

    /** 卡号/流水号  */
    @Excel(name = "卡号/流水号")
    private String cardNumber;

    /** 充电电量(KWh)  */
    @Excel(name = "充电电量(KWh)")
    private BigDecimal rechargeElectricity;

    /** 充电金额(元) */
    @Excel(name = "充电金额(元)")
    private BigDecimal rechargeAmount;

    /** 充电时长(分钟)  */
    @Excel(name = "充电时长(分钟)")
    private Integer rechargeDuration;

    /** 充电开始时间  */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "充电开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 充电结束时间  */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "充电结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date overTime;

    /** 尖电量  */
    @Excel(name = "尖电量")
    private BigDecimal needleElectricity;

    /** 峰电量  */
    @Excel(name = "峰电量")
    private BigDecimal peakElectricity;

    /** 平电量  */
    @Excel(name = "平电量")
    private BigDecimal flatElectricity;

    /** 谷电量  */
    @Excel(name = "谷电量")
    private BigDecimal grainElectricity;

    /** 开始SOC */
    @Excel(name = "开始SOC")
    private String startSoc;

    /** 结束SOC */
    @Excel(name = "结束SOC")
    private String overSoc;

    /** 停充原因  */
    @Excel(name = "停充原因")
    private String stopCause;

    /** 删除状态 1正常 2删除 */
    private Long deleteFlag = 1L;


    /** 充电开始时间搜索条件  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginTimes;

    /** 充电结束时间搜索条件  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTimes;

    public Date getBeginTimes() {
        return beginTimes;
    }

    public void setBeginTimes(Date beginTimes) {
        this.beginTimes = beginTimes;
    }

    public Date getEndTimes() {
        return endTimes;
    }

    public void setEndTimes(Date endTimes) {
        this.endTimes = endTimes;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
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
    public void setFrameNumber(String frameNumber) 
    {
        this.frameNumber = frameNumber;
    }

    public String getFrameNumber() 
    {
        return frameNumber;
    }
    public void setCardNumber(String cardNumber) 
    {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() 
    {
        return cardNumber;
    }
    public void setRechargeElectricity(BigDecimal rechargeElectricity) 
    {
        this.rechargeElectricity = rechargeElectricity;
    }

    public BigDecimal getRechargeElectricity() 
    {
        return rechargeElectricity;
    }
    public void setRechargeAmount(BigDecimal rechargeAmount) 
    {
        this.rechargeAmount = rechargeAmount;
    }

    public BigDecimal getRechargeAmount() 
    {
        return rechargeAmount;
    }
    public void setRechargeDuration(Integer rechargeDuration) 
    {
        this.rechargeDuration = rechargeDuration;
    }

    public Integer getRechargeDuration() 
    {
        return rechargeDuration;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setOverTime(Date overTime) 
    {
        this.overTime = overTime;
    }

    public Date getOverTime() 
    {
        return overTime;
    }
    public void setNeedleElectricity(BigDecimal needleElectricity) 
    {
        this.needleElectricity = needleElectricity;
    }

    public BigDecimal getNeedleElectricity() 
    {
        return needleElectricity;
    }
    public void setPeakElectricity(BigDecimal peakElectricity) 
    {
        this.peakElectricity = peakElectricity;
    }

    public BigDecimal getPeakElectricity() 
    {
        return peakElectricity;
    }
    public void setFlatElectricity(BigDecimal flatElectricity) 
    {
        this.flatElectricity = flatElectricity;
    }

    public BigDecimal getFlatElectricity() 
    {
        return flatElectricity;
    }
    public void setGrainElectricity(BigDecimal grainElectricity) 
    {
        this.grainElectricity = grainElectricity;
    }

    public BigDecimal getGrainElectricity() 
    {
        return grainElectricity;
    }
    public void setStartSoc(String startSoc) 
    {
        this.startSoc = startSoc;
    }

    public String getStartSoc() 
    {
        return startSoc;
    }
    public void setOverSoc(String overSoc) 
    {
        this.overSoc = overSoc;
    }

    public String getOverSoc() 
    {
        return overSoc;
    }
    public void setStopCause(String stopCause) 
    {
        this.stopCause = stopCause;
    }

    public String getStopCause() 
    {
        return stopCause;
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
            .append("licensePlate", getLicensePlate())
            .append("vehicleId", getVehicleId())
            .append("frameNumber", getFrameNumber())
            .append("cardNumber", getCardNumber())
            .append("rechargeElectricity", getRechargeElectricity())
            .append("rechargeAmount", getRechargeAmount())
            .append("rechargeDuration", getRechargeDuration())
            .append("startTime", getStartTime())
            .append("overTime", getOverTime())
            .append("needleElectricity", getNeedleElectricity())
            .append("peakElectricity", getPeakElectricity())
            .append("flatElectricity", getFlatElectricity())
            .append("grainElectricity", getGrainElectricity())
            .append("startSoc", getStartSoc())
            .append("overSoc", getOverSoc())
            .append("stopCause", getStopCause())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
