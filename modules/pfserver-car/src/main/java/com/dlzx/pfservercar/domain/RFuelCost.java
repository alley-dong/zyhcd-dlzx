package com.dlzx.pfservercar.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 统计报-燃料费用统计对象 r_fuel_cost
 * 
 * @author dlzx
 * @date 2020-12-15
 */
public class RFuelCost extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 车牌号码 */
    @Excel(name = "车牌号")
    private String licensePlate;

    /** 车辆ID */
    private String vehicleId;

    /** 油品 */
    @Excel(name = "油品")
    private String oils;

    /** 油量 */
    @Excel(name = "油量（L）")
    private BigDecimal oilQuantity;

    /** 单价（元含税） */
    @Excel(name = "单价（元含税）")
    private BigDecimal taxUnitPrice;

    /** 加油金额（含税） */
    @Excel(name = "加油金额（元含税）")
    private BigDecimal taxAmount;

    /** 交易时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "交易时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date transactionTime;

    /** 单价（元  不含税） */
    @Excel(name = "单价（元不含税）")
    private BigDecimal unitPrice;

    /** 加油金额（元 不含税） */
    @Excel(name = "加油金额（元不含税）")
    private BigDecimal amount;

    /** 税率 */
    @Excel(name = "税率")
    private BigDecimal taxRate;

    /** 删除状态 1正常 2删除 */
    private Long deleteFlag = 1L;

    /** 交易开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    /** 交易结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date overTime;


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
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
    public void setOils(String oils) 
    {
        this.oils = oils;
    }

    public String getOils() 
    {
        return oils;
    }
    public void setOilQuantity(BigDecimal oilQuantity) 
    {
        this.oilQuantity = oilQuantity;
    }

    public BigDecimal getOilQuantity() 
    {
        return oilQuantity;
    }
    public void setTaxUnitPrice(BigDecimal taxUnitPrice) 
    {
        this.taxUnitPrice = taxUnitPrice;
    }

    public BigDecimal getTaxUnitPrice() 
    {
        return taxUnitPrice;
    }
    public void setTaxAmount(BigDecimal taxAmount) 
    {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getTaxAmount() 
    {
        return taxAmount;
    }
    public void setTransactionTime(Date transactionTime) 
    {
        this.transactionTime = transactionTime;
    }

    public Date getTransactionTime() 
    {
        return transactionTime;
    }
    public void setUnitPrice(BigDecimal unitPrice) 
    {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitPrice() 
    {
        return unitPrice;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setTaxRate(BigDecimal taxRate) 
    {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxRate() 
    {
        return taxRate;
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
            .append("oils", getOils())
            .append("oilQuantity", getOilQuantity())
            .append("taxUnitPrice", getTaxUnitPrice())
            .append("taxAmount", getTaxAmount())
            .append("transactionTime", getTransactionTime())
            .append("unitPrice", getUnitPrice())
            .append("amount", getAmount())
            .append("taxRate", getTaxRate())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
