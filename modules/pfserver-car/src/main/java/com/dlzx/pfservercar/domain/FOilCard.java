package com.dlzx.pfservercar.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

import javax.validation.constraints.NotBlank;

/**
 * 油卡管理对象 f_oil_card
 * 
 * @author dlzx
 * @date 2020-12-01
 */
public class FOilCard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 车辆ID */
    @Excel(name = "车辆ID")
    private String vehicleId;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String licensePlate;

    /** 油卡卡号 */
    @Excel(name = "油卡卡号")
    private String oilCardNumber;

    /** 油品 */
    @Excel(name = "油品(字典值)")
    private Long oils;

    /** 办卡时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "办卡日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cardTime;

    /** 到期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "到期日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dueTime;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag = 1L;


    /** 是否标红 0不 1是 */
    @Excel(name = "是否标红 0不 1是")
    private Integer mathDays = 0;

    /** 油品翻译 */
    @Excel(name = "油品")
    private String oilsEnum;

    public String getOilsEnum() {
        return oilsEnum;
    }

    public void setOilsEnum(String oilsEnum) {
        this.oilsEnum = oilsEnum;
    }

    public Integer getMathDays() {
        return mathDays;
    }

    public void setMathDays(Integer mathDays) {
        this.mathDays = mathDays;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setVehicleId(String vehicleId) 
    {
        this.vehicleId = vehicleId;
    }

    public String getVehicleId() 
    {
        return vehicleId;
    }
    public void setLicensePlate(String licensePlate) 
    {
        this.licensePlate = licensePlate;
    }

    @NotBlank(message = "车牌号不能为空")
    public String getLicensePlate() 
    {
        return licensePlate;
    }
    public void setOilCardNumber(String oilCardNumber) 
    {
        this.oilCardNumber = oilCardNumber;
    }

    @NotBlank(message = "卡号不能为空")
    public String getOilCardNumber() 
    {
        return oilCardNumber;
    }
    public void setOils(Long oils)
    {
        this.oils = oils;
    }

    public Long getOils()
    {
        return oils;
    }
    public void setCardTime(Date cardTime) 
    {
        this.cardTime = cardTime;
    }

    @NotBlank(message = "办卡日期不能为空")
    public Date getCardTime() 
    {
        return cardTime;
    }
    public void setDueTime(Date dueTime) 
    {
        this.dueTime = dueTime;
    }

    @NotBlank(message = "到期日期不能为空")
    public Date getDueTime() 
    {
        return dueTime;
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
            .append("vehicleId", getVehicleId())
            .append("licensePlate", getLicensePlate())
            .append("oilCardNumber", getOilCardNumber())
            .append("oils", getOils())
            .append("cardTime", getCardTime())
            .append("dueTime", getDueTime())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
