package com.dlzx.pfservercar.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 燃料管理-燃油定额对象 f_fuel_quota
 * 
 * @author dlzx
 * @date 2020-12-02
 */
public class FFuelQuota extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 定额名称 */
    @Excel(name = "定额名称")
    private String name;

    /** 线路ID */
    @Excel(name = "线路ID")
    private String routeId;

    /** 线路名称 */
    @Excel(name = "线路名称")
    private String routeName;

    /** 车辆型号ID 关联sys_dictionary表 */
    @Excel(name = "车辆型号ID 关联sys_dictionary表")
    private String vehicleModel;

    /** 车辆型号名称 */
    @Excel(name = "车辆型号名称")
    private String vehicleModelName;

    /** 油料定额 */
    @Excel(name = "油料定额")
    private BigDecimal quota;

    /** 油料定额 */
    @Excel(name = "空放定额")
    private BigDecimal ballastQuota;

    /** 油料定额 */
    @Excel(name = "倒货定额")
    private BigDecimal pourQuota;

    /** 油料定额 */
    @Excel(name = "油料类型")
    private Integer fuelType;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag = 1L;

    private String fuelTypeEnum;

    public String getFuelTypeEnum() {
        return fuelTypeEnum;
    }

    public void setFuelTypeEnum(String fuelTypeEnum) {
        this.fuelTypeEnum = fuelTypeEnum;
    }

    public BigDecimal getBallastQuota() {
        return ballastQuota;
    }

    public void setBallastQuota(BigDecimal ballastQuota) {
        this.ballastQuota = ballastQuota;
    }

    public BigDecimal getPourQuota() {
        return pourQuota;
    }

    public void setPourQuota(BigDecimal pourQuota) {
        this.pourQuota = pourQuota;
    }

    public Integer getFuelType() {
        return fuelType;
    }

    public void setFuelType(Integer fuelType) {
        this.fuelType = fuelType;
    }

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

    public String getVehicleModelName() 
    {
        return vehicleModelName;
    }
    public void setQuota(BigDecimal quota) 
    {
        this.quota = quota;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("routeId", getRouteId())
            .append("routeName", getRouteName())
            .append("vehicleModel", getVehicleModel())
            .append("vehicleModelName", getVehicleModelName())
            .append("quota", getQuota())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
