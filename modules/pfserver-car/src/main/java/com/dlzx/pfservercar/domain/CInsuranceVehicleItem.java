package com.dlzx.pfservercar.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 车辆管理-车辆保险保险项对象 c_insurance_vehicle_item
 * 
 * @author dlzx
 * @date 2020-11-24
 */
public class CInsuranceVehicleItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 保险ID */
    @Excel(name = "保险ID")
    private String insuranceId;

    /** 保险类型ID */
    @Excel(name = "保险类型ID")
    private String insuranceTypeId;

    /** 保险类型名称 */
    @Excel(name = "保险类型名称")
    private String insuranceTypeName;

    /** 保险项ID */
    @Excel(name = "保险项ID")
    private String insuranceItemId;

    /** 保险项名称 */
    @Excel(name = "保险项名称")
    private String insuranceItemName;

    /** 保额 */
    @Excel(name = "保额")
    private BigDecimal insuranceQuota;

    /** 保费 */
    @Excel(name = "保费")
    private BigDecimal insuranceCost;

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
    public void setInsuranceId(String insuranceId) 
    {
        this.insuranceId = insuranceId;
    }

    public String getInsuranceId() 
    {
        return insuranceId;
    }
    public void setInsuranceTypeId(String insuranceTypeId) 
    {
        this.insuranceTypeId = insuranceTypeId;
    }

    public String getInsuranceTypeId() 
    {
        return insuranceTypeId;
    }
    public void setInsuranceTypeName(String insuranceTypeName) 
    {
        this.insuranceTypeName = insuranceTypeName;
    }

    public String getInsuranceTypeName() 
    {
        return insuranceTypeName;
    }
    public void setInsuranceItemId(String insuranceItemId) 
    {
        this.insuranceItemId = insuranceItemId;
    }

    public String getInsuranceItemId() 
    {
        return insuranceItemId;
    }
    public void setInsuranceItemName(String insuranceItemName) 
    {
        this.insuranceItemName = insuranceItemName;
    }

    public String getInsuranceItemName() 
    {
        return insuranceItemName;
    }
    public void setInsuranceQuota(BigDecimal insuranceQuota) 
    {
        this.insuranceQuota = insuranceQuota;
    }

    public BigDecimal getInsuranceQuota() 
    {
        return insuranceQuota;
    }
    public void setInsuranceCost(BigDecimal insuranceCost) 
    {
        this.insuranceCost = insuranceCost;
    }

    public BigDecimal getInsuranceCost() 
    {
        return insuranceCost;
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
            .append("insuranceId", getInsuranceId())
            .append("insuranceTypeId", getInsuranceTypeId())
            .append("insuranceTypeName", getInsuranceTypeName())
            .append("insuranceItemId", getInsuranceItemId())
            .append("insuranceItemName", getInsuranceItemName())
            .append("insuranceQuota", getInsuranceQuota())
            .append("insuranceCost", getInsuranceCost())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
