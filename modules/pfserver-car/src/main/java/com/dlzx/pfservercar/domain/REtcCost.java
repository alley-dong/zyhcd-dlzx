package com.dlzx.pfservercar.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 统计报-ETC费用统计对象 r_etc_cost
 * 
 * @author dlzx
 * @date 2020-12-17
 */
public class REtcCost extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 车牌号码 */
    @Excel(name = "车牌号")
    private String licensePlate;

    /** 车辆ID */

    private String vehicleId;

    /** 金额（含税） */
    @Excel(name = "金额（元含税）")
    private BigDecimal taxAmount;

    /** 金额（元 不含税） */
    @Excel(name = "金额（元不含税）")
    private BigDecimal amount;

    /** 税额 */
    @Excel(name = "税额")
    private BigDecimal tax;

    /** 删除状态 1正常 2删除 */

    private Long deleteFlag;

    @Excel(name = "报销时间", width = 40, dateFormat = "yyyy-MM-dd")
    private Date reimburseTime;

    private Date bTime;

    private Date eTime;

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
    public void setTaxAmount(BigDecimal taxAmount) 
    {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getTaxAmount() 
    {
        return taxAmount;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setTax(BigDecimal tax) 
    {
        this.tax = tax;
    }

    public BigDecimal getTax() 
    {
        return tax;
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
            .append("taxAmount", getTaxAmount())
            .append("amount", getAmount())
            .append("tax", getTax())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
                .append("reimburseTime", getReimburseTime())
                .append("bTime", getbTime())
                .append("eTime", geteTime())
            .toString();
    }

    public Date getbTime() {
        return bTime;
    }

    public void setbTime(Date bTime) {
        this.bTime = bTime;
    }

    public Date geteTime() {
        return eTime;
    }

    public void seteTime(Date eTime) {
        this.eTime = eTime;
    }

    public Date getReimburseTime() {
        return reimburseTime;
    }

    public void setReimburseTime(Date reimburseTime) {
        this.reimburseTime = reimburseTime;
    }

    public boolean equals() {
        String str = new StringBuilder()
                .append(getId()!=null?getId():"")
                .append(getVehicleId()!=null?getVehicleId():"")
                .append(getLicensePlate()!=null?getLicensePlate():"")
                .append(getTaxAmount()!=null?getTaxAmount():"")
                .append(getTax()!=null?getTax():"")
                .append(getAmount()!=null?getAmount():"")
                .append(getReimburseTime()!=null?getReimburseTime():"")
                .append(getDeleteFlag()!=null?getDeleteFlag():"")
                .append(getCreateTime()!=null?getCreateTime():"")
                .append(getUpdateTime()!=null?getUpdateTime():"")
                .toString();
        boolean flag = ("".equals(str.trim())||str.trim().length()==0);
//        System.out.println("数据长度:"+str+""+flag);
        return flag;


    }
}
