package com.dlzx.pfservercar.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dlzx.pfservercar.domain.Common.Spanrow;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 燃料管理-燃油使用计划对象 f_fuel_use_plan
 * 
 * @author dlzx
 * @date 2020-12-02
 */
public class FFuelUsePlan extends BaseEntity
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

    /** 车辆型号ID 关联sys_dictionary表 */
    @Excel(name = "车辆型号ID 关联sys_dictionary表")
    private String vehicleModel;

    /** 车辆型号-名称 */
    @Excel(name = "车辆型号-名称")
    private String vehicleModelName;

    /** 线路ID */
    @Excel(name = "线路ID")
    private String routeId;

    /** 线路名称 */
    @Excel(name = "线路名称")
    private String routeName;

    /** 计划日期 */
    @JsonFormat(pattern = "yyyy-MM")
    @Excel(name = "计划日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planDate;

    /** 燃料定额 */
    @Excel(name = "燃料定额")
    private BigDecimal fuelQuota;

    /** 空放定额 */
    @Excel(name = "空放定额")
    private BigDecimal ballastQuota;

    /** 倒货定额 */
    @Excel(name = "倒货定额")
    private BigDecimal pourQuota;

    /** 计划趟数 */
    @Excel(name = "计划趟数")
    private Long planQuantity;

    /** 燃油单价 */
    @Excel(name = "燃油单价")
    private BigDecimal unitPrice;

    /** 燃油金额 */
    @Excel(name = "燃油金额")
    private BigDecimal fuelPrice;

    /** 油料定额 */
    @Excel(name = "油料类型")
    private Integer fuelType;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag = 1L;


    /** 油料总金额(废弃)*/
    private BigDecimal fuelTotalPrice;
    /** 车辆数*/
    private Long carQuantity;

    private String fuelTypeEnum;

    // 车辆列表（油料类型不同）
    private List<FFuelUsePlan> info= new ArrayList<>();

    // 车辆列表（油料类型不同）
//    private List<Spanrow> spanrow;

    /** 空放金额*/
    private BigDecimal ballastQuotaPrice;
    /** 倒货金额*/
    private BigDecimal pourQuotaPrice;

    /** 燃油总金额*/
    private BigDecimal fuelPriceTotal= BigDecimal.ZERO;
    /** 空放总金额*/
    private BigDecimal ballastQuotaPriceTotal= BigDecimal.ZERO;
    /** 倒货总金额*/
    private BigDecimal pourQuotaPriceTotal= BigDecimal.ZERO;


    public BigDecimal getFuelPriceTotal() {
        return fuelPriceTotal;
    }

    public void setFuelPriceTotal(BigDecimal fuelPriceTotal) {
        this.fuelPriceTotal = fuelPriceTotal;
    }

    public BigDecimal getBallastQuotaPriceTotal() {
        return ballastQuotaPriceTotal;
    }

    public void setBallastQuotaPriceTotal(BigDecimal ballastQuotaPriceTotal) {
        this.ballastQuotaPriceTotal = ballastQuotaPriceTotal;
    }

    public BigDecimal getPourQuotaPriceTotal() {
        return pourQuotaPriceTotal;
    }

    public void setPourQuotaPriceTotal(BigDecimal pourQuotaPriceTotal) {
        this.pourQuotaPriceTotal = pourQuotaPriceTotal;
    }

    public BigDecimal getBallastQuotaPrice() {
        return ballastQuotaPrice;
    }

    public void setBallastQuotaPrice(BigDecimal ballastQuotaPrice) {
        this.ballastQuotaPrice = ballastQuotaPrice;
    }

    public BigDecimal getPourQuotaPrice() {
        return pourQuotaPrice;
    }

    public void setPourQuotaPrice(BigDecimal pourQuotaPrice) {
        this.pourQuotaPrice = pourQuotaPrice;
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

    //    public List<Spanrow> getSpanrow() {
//        return spanrow;
//    }
//
//    public void setSpanrow(List<Spanrow> spanrow) {
//        this.spanrow = spanrow;
//    }

    public List<FFuelUsePlan> getInfo() {
        return info;
    }

    public void setInfo(List<FFuelUsePlan> info) {
        this.info = info;
    }

    public String getFuelTypeEnum() {
        return fuelTypeEnum;
    }

    public void setFuelTypeEnum(String fuelTypeEnum) {
        this.fuelTypeEnum = fuelTypeEnum;
    }

    public Integer getFuelType() {
        return fuelType;
    }

    public void setFuelType(Integer fuelType) {
        this.fuelType = fuelType;
    }

    public BigDecimal getFuelTotalPrice() {
        return fuelTotalPrice;
    }

    public void setFuelTotalPrice(BigDecimal fuelTotalPrice) {
        this.fuelTotalPrice = fuelTotalPrice;
    }

    public Long getCarQuantity() {
        return carQuantity;
    }

    public void setCarQuantity(Long carQuantity) {
        this.carQuantity = carQuantity;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
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

    public String getLicensePlate() 
    {
        return licensePlate;
    }
    public void setVehicleModelName(String vehicleModelName) 
    {
        this.vehicleModelName = vehicleModelName;
    }

    public String getVehicleModelName() 
    {
        return vehicleModelName;
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
    public void setPlanDate(Date planDate) 
    {
        this.planDate = planDate;
    }

    public Date getPlanDate() 
    {
        return planDate;
    }
    public void setFuelQuota(BigDecimal fuelQuota) 
    {
        this.fuelQuota = fuelQuota;
    }

    public BigDecimal getFuelQuota() 
    {
        return fuelQuota;
    }
    public void setPlanQuantity(Long planQuantity) 
    {
        this.planQuantity = planQuantity;
    }

    public Long getPlanQuantity() 
    {
        return planQuantity;
    }
    public void setUnitPrice(BigDecimal unitPrice) 
    {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitPrice() 
    {
        return unitPrice;
    }
    public void setFuelPrice(BigDecimal fuelPrice) 
    {
        this.fuelPrice = fuelPrice;
    }

    public BigDecimal getFuelPrice() 
    {
        return fuelPrice;
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
            .append("vehicleModelName", getVehicleModelName())
            .append("routeId", getRouteId())
            .append("routeName", getRouteName())
            .append("planDate", getPlanDate())
            .append("fuelQuota", getFuelQuota())
            .append("planQuantity", getPlanQuantity())
            .append("unitPrice", getUnitPrice())
            .append("fuelPrice", getFuelPrice())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
