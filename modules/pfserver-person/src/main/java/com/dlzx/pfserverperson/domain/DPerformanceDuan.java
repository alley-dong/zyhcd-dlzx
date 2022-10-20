package com.dlzx.pfserverperson.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 驾驶员-绩效对象 d_performance
 * 
 * @author dlzx
 * @date 2020-11-30
 */
public class DPerformanceDuan extends BaseEntity
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

    /** 驾驶员类型ID */
    @Excel(name = "驾驶员类型ID")
    private String driverTypeId;

    /** 驾驶员类型名称 */
    @Excel(name = "驾驶员类型名称")
    private String driverTypeName;

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
    @NotBlank(message = "绩效名称不能为空")
    public String getName() 
    {
        return name;
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
    @NotBlank(message = "驾驶员类型不能为空")
    public String getDriverTypeName() 
    {
        return driverTypeName;
    }

    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }
    @NotNull(message = "绩效金额不能为空")
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
            .append("driverTypeId", getDriverTypeId())
            .append("driverTypeName", getDriverTypeName())
            .append("amount", getAmount())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
