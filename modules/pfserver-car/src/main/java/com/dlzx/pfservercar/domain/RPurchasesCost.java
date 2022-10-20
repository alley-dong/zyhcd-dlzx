package com.dlzx.pfservercar.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 统计报-新购车辆临时号牌费用统计对象 r_fuel_purchases_cost
 * 
 * @author ruoyi
 * @date 2021-02-21
 */
public class RPurchasesCost extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 车辆ID */
    private String vehicleId;

    /** 车牌号码 */
    @Excel(name = "车牌号")
    private String licensePlate;

    /** 车辆型号 关联sys_dictionary表 */
    private String vehicleModel;

    /** 车辆型号-名称 */
    @Excel(name = "车型")
    private String vehicleModelName;

    /** 项目 */
    @Excel(name = "项目")
    private String projectName;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal taxAmount;

    /** 购买时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "购买时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date purchasesTime;

    /** 删除状态 1正常 2删除 */
    private Long deleteFlag = 1L;

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getVehicleId()
    {
        return vehicleId;
    }
    public void setLicensePlate(String licensePlate) 
    {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() 
    {
        return licensePlate;
    }
    public void setVehicleModel(String vehicleModel) 
    {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleModel() 
    {
        return vehicleModel;
    }
    public void setVehicleModelName(String vehicleModelName) 
    {
        this.vehicleModelName = vehicleModelName;
    }

    public String getVehicleModelName() 
    {
        return vehicleModelName;
    }
    public void setTaxAmount(BigDecimal taxAmount) 
    {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getTaxAmount() 
    {
        return taxAmount;
    }
    public void setPurchasesTime(Date purchasesTime) 
    {
        this.purchasesTime = purchasesTime;
    }

    public Date getPurchasesTime() 
    {
        return purchasesTime;
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
            .append("vehicleModel", getVehicleModel())
            .append("vehicleModelName", getVehicleModelName())
            .append("taxAmount", getTaxAmount())
            .append("purchasesTime", getPurchasesTime())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
