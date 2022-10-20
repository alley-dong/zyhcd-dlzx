package com.dlzx.pfservercar.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 驾驶员-绩效对象 d_performance
 * 
 * @author dlzx
 * @date 2020-11-30
 */
public class DPerformance extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 绩效类型 1长途绩效 2短途绩效 */
    @Excel(name = "绩效类型 1长途绩效 2短途绩效")
    private Long type;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 线路ID */
    @Excel(name = "线路ID")
    private String routeId;

    /** 线路名称 */
    @Excel(name = "线路名称")
    private String routeName;

    /** 计费类型 1天计费 2趟计费 */
    @Excel(name = "计费类型 1天计费 2趟计费")
    private Long chargeType;

    /** 车辆类别 关联sys_dictionary表 */
    @Excel(name = "车辆类别 关联sys_dictionary表")
    private String vehicleType;

    /** 车辆类别-名称 */
    @Excel(name = "车辆类别-名称")
    private String vehicleTypeName;

    /** 驾驶员类型ID */
    @Excel(name = "驾驶员类型ID")
    private String driverTypeId;

    /** 驾驶员类型名称 */
    @Excel(name = "驾驶员类型名称")
    private String driverTypeName;

    /** 是否有对班司机 1是 2否 */
    @Excel(name = "是否有对班司机 1是 2否")
    private Long isVice;

    /** 绩效金额 */
    @Excel(name = "绩效金额")
    private BigDecimal amount;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag;

    /** 篷布补助 */
    private BigDecimal tarpaulin;

    public BigDecimal getTarpaulin() {
        return tarpaulin;
    }

    public void setTarpaulin(BigDecimal tarpaulin) {
        this.tarpaulin = tarpaulin;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setRouteId(String routeId) 
    {
        this.routeId = routeId;
    }

    public String getRouteId() 
    {
        return routeId;
    }
    public void setRouteName(String routeName) 
    {
        this.routeName = routeName;
    }

    public String getRouteName() 
    {
        return routeName;
    }
    public void setChargeType(Long chargeType) 
    {
        this.chargeType = chargeType;
    }

    public Long getChargeType() 
    {
        return chargeType;
    }
    public void setVehicleType(String vehicleType) 
    {
        this.vehicleType = vehicleType;
    }

    public String getVehicleType() 
    {
        return vehicleType;
    }
    public void setVehicleTypeName(String vehicleTypeName) 
    {
        this.vehicleTypeName = vehicleTypeName;
    }

    public String getVehicleTypeName() 
    {
        return vehicleTypeName;
    }
    public void setDriverTypeId(String driverTypeId) 
    {
        this.driverTypeId = driverTypeId;
    }

    public String getDriverTypeId() 
    {
        return driverTypeId;
    }
    public void setDriverTypeName(String driverTypeName) 
    {
        this.driverTypeName = driverTypeName;
    }

    public String getDriverTypeName() 
    {
        return driverTypeName;
    }
    public void setIsVice(Long isVice) 
    {
        this.isVice = isVice;
    }

    public Long getIsVice() 
    {
        return isVice;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
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
            .append("type", getType())
            .append("name", getName())
            .append("routeId", getRouteId())
            .append("routeName", getRouteName())
            .append("chargeType", getChargeType())
            .append("vehicleType", getVehicleType())
            .append("vehicleTypeName", getVehicleTypeName())
            .append("driverTypeId", getDriverTypeId())
            .append("driverTypeName", getDriverTypeName())
            .append("isVice", getIsVice())
            .append("amount", getAmount())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
