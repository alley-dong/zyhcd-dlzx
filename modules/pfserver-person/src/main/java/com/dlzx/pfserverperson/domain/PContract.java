package com.dlzx.pfserverperson.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 合作方管理-合同对象 p_contract
 * 
 * @author dlzx
 * @date 2020-11-26
 */
public class PContract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 合同类型 1客户合同 2供应商合同 */
    @Excel(name = "合同类型 1客户合同 2供应商合同")
    private Long type;

    /** 客户类型 1内部合同 2外部合同 */
    @Excel(name = "客户类型 1内部合同 2外部合同")
    private Long contractType;

    /** 预警状态 1正常 2预警 */
    @Excel(name = "预警状态 1正常 2预警")
    private Long flag;

    /** 合同名称 */
    @Excel(name = "合同名称")
    private String name;

    /** 供应商ID 关联p_carrier表id */
    @Excel(name = "供应商ID 关联p_carrier表id")
    private String carrierId;

    /** 供应商名称/客户名称 */
    @Excel(name = "供应商名称/客户名称")
    private String carrierName;

    /** 合同单价 */
    @Excel(name = "合同单价")
    private BigDecimal unitPrice;

    /** 付款方式 */
    @Excel(name = "付款方式")
    private String paymentMethod;

    /** 合同开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合同开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contractStartTime;

    /** 合同结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合同结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contractEndTiem;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag = 1L;

    /** 相差月份*/
    private Integer mathMonth;

    /** 客户类型翻译*/
    private String contractTypeEnum;

    /** 附件文件 url（多个逗号隔开） */
    private List<String> fileUrls;

    /** 附件文件(返回)*/
    private List<String> fileList;


    public List<String> getFileList() {
        return fileList;
    }

    public void setFileList(List<String> fileList) {
        this.fileList = fileList;
    }

    public List<String> getFileUrls() {
        return fileUrls;
    }

    public void setFileUrls(List<String> fileUrls) {
        this.fileUrls = fileUrls;
    }

    public String getContractTypeEnum() {
        return contractTypeEnum;
    }

    public void setContractTypeEnum(String contractTypeEnum) {
        this.contractTypeEnum = contractTypeEnum;
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
    public void setContractType(Long contractType) 
    {
        this.contractType = contractType;
    }

    public Long getContractType() 
    {
        return contractType;
    }
    public void setFlag(Long flag) 
    {
        this.flag = flag;
    }

    public Long getFlag() 
    {
        return flag;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setCarrierId(String carrierId) 
    {
        this.carrierId = carrierId;
    }

    public String getCarrierId() 
    {
        return carrierId;
    }
    public void setCarrierName(String carrierName) 
    {
        this.carrierName = carrierName;
    }

    public String getCarrierName() 
    {
        return carrierName;
    }
    public void setUnitPrice(BigDecimal unitPrice) 
    {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitPrice() 
    {
        return unitPrice;
    }
    public void setPaymentMethod(String paymentMethod) 
    {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() 
    {
        return paymentMethod;
    }
    public void setContractStartTime(Date contractStartTime) 
    {
        this.contractStartTime = contractStartTime;
    }

    public Date getContractStartTime() 
    {
        return contractStartTime;
    }
    public void setContractEndTiem(Date contractEndTiem) 
    {
        this.contractEndTiem = contractEndTiem;
    }

    public Integer getMathMonth() {
        return mathMonth;
    }

    public void setMathMonth(Integer mathMonth) {
        this.mathMonth = mathMonth;
    }

    public Date getContractEndTiem()
    {
        return contractEndTiem;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
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
            .append("contractType", getContractType())
            .append("flag", getFlag())
            .append("name", getName())
            .append("carrierId", getCarrierId())
            .append("carrierName", getCarrierName())
            .append("unitPrice", getUnitPrice())
            .append("paymentMethod", getPaymentMethod())
            .append("contractStartTime", getContractStartTime())
            .append("contractEndTiem", getContractEndTiem())
            .append("remarks", getRemarks())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
