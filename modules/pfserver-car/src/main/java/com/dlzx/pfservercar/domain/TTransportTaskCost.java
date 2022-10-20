package com.dlzx.pfservercar.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 任务管理-运输任务费用对象 t_transport_task_cost
 * 
 * @author yangluhe
 * @date 2021-01-05
 */
public class TTransportTaskCost extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 任务ID（运单ID） */
    @Excel(name = "任务ID", readConverterExp = "运=单ID")
    private String taskId;

    /** 运单号 */
    @Excel(name = "运单号")
    private String shipmentXid;

    /** 收付款标记（AR:应收，AP：应付） */
    @Excel(name = "收付款标记", readConverterExp = "A=R:应收，AP：应付")
    private String paymentType;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String customerName;

    /** 承运商名称 */
    @Excel(name = "承运商名称")
    private String servprovName;

    /** 业务类型 */
    @Excel(name = "业务类型")
    private String businessType;

    /** 费用类型(吨公里结算运费，按件结算运费） */
    @Excel(name = "费用类型(吨公里结算运费，按件结算运费）")
    private String feeType;

    /** 含税总金额 */
    @Excel(name = "含税总金额")
    private BigDecimal totalAmount;

    /** 税率 */
    @Excel(name = "税率")
    private BigDecimal taxRate;

    /** 不含税总金额 */
    @Excel(name = "不含税总金额")
    private BigDecimal amount;

    /** TMS系统账单号 */
    @Excel(name = "TMS系统账单号")
    private String settleNum;

    /** 单证号 */
    @Excel(name = "单证号")
    private String documentNumber;

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
    public void setTaskId(String taskId) 
    {
        this.taskId = taskId;
    }

    public String getTaskId() 
    {
        return taskId;
    }
    public void setShipmentXid(String shipmentXid) 
    {
        this.shipmentXid = shipmentXid;
    }

    public String getShipmentXid() 
    {
        return shipmentXid;
    }
    public void setPaymentType(String paymentType) 
    {
        this.paymentType = paymentType;
    }

    public String getPaymentType() 
    {
        return paymentType;
    }
    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public String getCustomerName() 
    {
        return customerName;
    }
    public void setServprovName(String servprovName) 
    {
        this.servprovName = servprovName;
    }

    public String getServprovName() 
    {
        return servprovName;
    }
    public void setBusinessType(String businessType) 
    {
        this.businessType = businessType;
    }

    public String getBusinessType() 
    {
        return businessType;
    }
    public void setFeeType(String feeType) 
    {
        this.feeType = feeType;
    }

    public String getFeeType() 
    {
        return feeType;
    }
    public void setTotalAmount(BigDecimal totalAmount) 
    {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() 
    {
        return totalAmount;
    }
    public void setTaxRate(BigDecimal taxRate) 
    {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxRate() 
    {
        return taxRate;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setSettleNum(String settleNum) 
    {
        this.settleNum = settleNum;
    }

    public String getSettleNum() 
    {
        return settleNum;
    }
    public void setDocumentNumber(String documentNumber) 
    {
        this.documentNumber = documentNumber;
    }

    public String getDocumentNumber() 
    {
        return documentNumber;
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
            .append("taskId", getTaskId())
            .append("shipmentXid", getShipmentXid())
            .append("paymentType", getPaymentType())
            .append("customerName", getCustomerName())
            .append("servprovName", getServprovName())
            .append("businessType", getBusinessType())
            .append("feeType", getFeeType())
            .append("totalAmount", getTotalAmount())
            .append("taxRate", getTaxRate())
            .append("amount", getAmount())
            .append("settleNum", getSettleNum())
            .append("documentNumber", getDocumentNumber())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
