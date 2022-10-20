package com.dlzx.pfservercar.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 维修保养-维修站-维修明细对象 r_garage_repair_item
 * 
 * @author yangluhe
 * @date 2020-12-02
 */
public class RGarageRepairItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 维修站ID-关联r_garage表 */
    @Excel(name = "维修站ID-关联r_garage表")
    private String garageId;

    /** 车辆型号 关联sys_dictionary表 */
    @Excel(name = "车辆型号 关联sys_dictionary表")
    private String vehicleModel;

    /** 车辆型号-名称 */
    @Excel(name = "车辆型号")
    private String vehicleModelName;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal unitPrice;

    /** 工时数 */
    @Excel(name = "工时数")
    private BigDecimal duration;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setGarageId(String garageId) 
    {
        this.garageId = garageId;
    }

    public String getGarageId() 
    {
        return garageId;
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

    @NotBlank(message = "车辆类型名称不能为空")
    public String getVehicleModelName() 
    {
        return vehicleModelName;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    @NotBlank(message = "名称不能为空")
    public String getName() 
    {
        return name;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    @NotBlank(message = "单位不能为空")
    public String getUnit() 
    {
        return unit;
    }
    public void setUnitPrice(BigDecimal unitPrice) 
    {
        this.unitPrice = unitPrice;
    }

    @NotNull(message = "单价不能为空")
    public BigDecimal getUnitPrice() 
    {
        return unitPrice;
    }
    public void setDuration(BigDecimal duration) 
    {
        this.duration = duration;
    }

    @NotNull(message = "工时数不能为空")
    public BigDecimal getDuration() 
    {
        return duration;
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
            .append("garageId", getGarageId())
            .append("vehicleModel", getVehicleModel())
            .append("vehicleModelName", getVehicleModelName())
            .append("name", getName())
            .append("unit", getUnit())
            .append("unitPrice", getUnitPrice())
            .append("duration", getDuration())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
    public boolean equals() {
        String str = new StringBuilder()
                .append(getId()!=null?getId():"")
                .append(getName()!=null?getName():"")
                .append(getDuration()!=null?getDuration():"")
                .append(getUnit()!=null?getUnit():"")
                .append(getUnitPrice()!=null?getUnitPrice():"")
                .append(getVehicleModelName()!=null?getVehicleModelName():"")
                .append(getVehicleModel()!=null?getVehicleModel():"")
                .append(getDeleteFlag()!=null?getDeleteFlag():"")
                .append(getCreateBy()!=null?getCreateBy():"")
                .append(getCreateTime()!=null?getCreateTime():"")
                .append(getUpdateBy()!=null?getUpdateBy():"")
                .append(getUpdateTime()!=null?getUpdateTime():"")
                .append(getGarageId()!=null?getGarageId():"")
                .toString();
        boolean flag = ("".equals(str.trim())||str.trim().length()==0);
//        System.out.println("数据长度:"+str+""+flag);
        return flag;

    }
}
