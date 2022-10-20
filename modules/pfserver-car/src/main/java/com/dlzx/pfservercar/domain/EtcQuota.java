package com.dlzx.pfservercar.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * ETC管理-ETC定额对象 etc_quota
 * 
 * @author dlzx
 * @date 2020-12-02
 */
public class EtcQuota extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 线路ID */
    @Excel(name = "线路ID")
    private String routeId;

    /** 线路名称 */
    @Excel(name = "线路名称")
    private String routeName;

    /** 车辆ID */
    @Excel(name = "车辆ID")
    private String vehicleModel;

    /** 车辆名称 */
    @Excel(name = "车辆名称")
    private String vehicleModelName;

    /** 定额名称 */
    @Excel(name = "定额名称")
    private String name;

    /** ETC定额 */
    @Excel(name = "ETC定额")
    private BigDecimal quota;

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
    @NotBlank(message = "线路名称不能为空")
    public String getRouteName() 
    {
        return routeName;
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
    @NotBlank(message = "车型不能为空")
    public String getVehicleModelName()
    {
        return vehicleModelName;
    }
    public void setName(String name) 
    {
        this.name = name;
    }
    @NotBlank(message = "定额名称不能为空")
    public String getName() 
    {
        return name;
    }
    public void setQuota(BigDecimal quota) 
    {
        this.quota = quota;
    }
    @NotNull(message = "ETC定额不能为空")
    public BigDecimal getQuota() 
    {
        return quota;
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
            .append("routeId", getRouteId())
            .append("routeName", getRouteName())
            .append("vehicleModel", getVehicleModel())
            .append("vehicleModelName", getVehicleModelName())
            .append("name", getName())
            .append("quota", getQuota())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
