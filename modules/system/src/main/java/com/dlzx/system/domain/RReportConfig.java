package com.dlzx.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 统计报-统计数据配置对象 r_report_config
 * 
 * @author dlzx
 * @date 2021-01-05
 */
public class RReportConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 驾驶员工资 */
    @Excel(name = "驾驶员工资")
    private BigDecimal driverPay;

    /** 管理人员薪酬 */
    @Excel(name = "管理人员薪酬")
    private BigDecimal adminPay;

    /** 外包服务费 */
    @Excel(name = "外包服务费")
    private BigDecimal serviceCost;

    /** 燃料费用 */
    @Excel(name = "燃料费用")
    private BigDecimal fuelCost;

    /** 检车费 */
    @Excel(name = "检车费")
    private BigDecimal inspectionFee;

    /** 保险费 */
    @Excel(name = "保险费")
    private BigDecimal insurance;

    /** 维保费用 */
    @Excel(name = "维保费用")
    private BigDecimal maintenanceCost;

    /** 轮胎费用 */
    @Excel(name = "轮胎费用")
    private BigDecimal trieCost;

    /** 低值易耗品费用 */
    @Excel(name = "低值易耗品费用")
    private BigDecimal consumablesCost;

    /** 折旧费用 */
    @Excel(name = "折旧费用")
    private BigDecimal depreciationCost;

    /** 信息系统服务费 */
    @Excel(name = "信息系统服务费")
    private BigDecimal systemCost;

    @Excel(name = "尿素费用")
    private BigDecimal ureaCost;

    public BigDecimal getUreaCost() {
        return ureaCost;
    }

    public void setUreaCost(BigDecimal ureaCost) {
        this.ureaCost = ureaCost;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setDriverPay(BigDecimal driverPay) 
    {
        this.driverPay = driverPay;
    }

    public BigDecimal getDriverPay() 
    {
        return driverPay;
    }
    public void setAdminPay(BigDecimal adminPay) 
    {
        this.adminPay = adminPay;
    }

    public BigDecimal getAdminPay() 
    {
        return adminPay;
    }
    public void setServiceCost(BigDecimal serviceCost) 
    {
        this.serviceCost = serviceCost;
    }

    public BigDecimal getServiceCost() 
    {
        return serviceCost;
    }
    public void setFuelCost(BigDecimal fuelCost) 
    {
        this.fuelCost = fuelCost;
    }

    public BigDecimal getFuelCost() 
    {
        return fuelCost;
    }
    public void setInspectionFee(BigDecimal inspectionFee) 
    {
        this.inspectionFee = inspectionFee;
    }

    public BigDecimal getInspectionFee() 
    {
        return inspectionFee;
    }
    public void setInsurance(BigDecimal insurance) 
    {
        this.insurance = insurance;
    }

    public BigDecimal getInsurance() 
    {
        return insurance;
    }
    public void setMaintenanceCost(BigDecimal maintenanceCost) 
    {
        this.maintenanceCost = maintenanceCost;
    }

    public BigDecimal getMaintenanceCost() 
    {
        return maintenanceCost;
    }
    public void setTrieCost(BigDecimal trieCost) 
    {
        this.trieCost = trieCost;
    }

    public BigDecimal getTrieCost() 
    {
        return trieCost;
    }
    public void setConsumablesCost(BigDecimal consumablesCost) 
    {
        this.consumablesCost = consumablesCost;
    }

    public BigDecimal getConsumablesCost() 
    {
        return consumablesCost;
    }
    public void setDepreciationCost(BigDecimal depreciationCost) 
    {
        this.depreciationCost = depreciationCost;
    }

    public BigDecimal getDepreciationCost() 
    {
        return depreciationCost;
    }
    public void setSystemCost(BigDecimal systemCost) 
    {
        this.systemCost = systemCost;
    }

    public BigDecimal getSystemCost() 
    {
        return systemCost;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("driverPay", getDriverPay())
            .append("adminPay", getAdminPay())
            .append("serviceCost", getServiceCost())
            .append("fuelCost", getFuelCost())
            .append("inspectionFee", getInspectionFee())
            .append("insurance", getInsurance())
            .append("maintenanceCost", getMaintenanceCost())
            .append("trieCost", getTrieCost())
            .append("consumablesCost", getConsumablesCost())
            .append("depreciationCost", getDepreciationCost())
            .append("systemCost", getSystemCost())
                .append("ureaCost",getUreaCost())
            .toString();
    }
}
