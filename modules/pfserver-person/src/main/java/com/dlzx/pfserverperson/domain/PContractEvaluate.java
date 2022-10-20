package com.dlzx.pfserverperson.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 合作方管理-合同评价记录对象 p_contract_evaluate
 * 
 * @author dlzx
 * @date 2020-12-09
 */
public class PContractEvaluate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 评价月份 */
    @JsonFormat(pattern = "yyyy-MM")
    @Excel(name = "评价月份", width = 30, dateFormat = "yyyy-MM")
    private Date evaluateMonth;

    /** 类型ID */
    @Excel(name = "类型ID")
    private String typeId;

    /** 类型名称 */
    @Excel(name = "类型名称")
    private String typeName;

    /** 承运商ID 关联p_carrier表id */
    @Excel(name = "承运商ID 关联p_carrier表id")
    private String carrierId;

    /** 承运商名称 */
    @Excel(name = "承运商名称")
    private String carrierName;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 总分 */
    @Excel(name = "总分")
    private Long totalScore;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag = 1L;


    /** 供应商 评价列表 */
    private List<PContractEvaluateMetrics> pContractEvaluateMetricsList;

    /** 附件文件 url（多个逗号隔开） */
    private List<String> fileUrls;

    /** 附件文件(返回)*/
    private List<String> fileList;

    /** 权重得分 */
    private BigDecimal weightTotalScore;


    /** 年度预算 1小于200万  2大于等于200万 */
    private Long yearMoneyType;


    public Long getYearMoneyType() {
        return yearMoneyType;
    }

    public void setYearMoneyType(Long yearMoneyType) {
        this.yearMoneyType = yearMoneyType;
    }

    public BigDecimal getWeightTotalScore() {
        return weightTotalScore;
    }

    public void setWeightTotalScore(BigDecimal weightTotalScore) {
        this.weightTotalScore = weightTotalScore;
    }

    public List<PContractEvaluateMetrics> getpContractEvaluateMetricsList() {
        return pContractEvaluateMetricsList;
    }

    public void setpContractEvaluateMetricsList(List<PContractEvaluateMetrics> pContractEvaluateMetricsList) {
        this.pContractEvaluateMetricsList = pContractEvaluateMetricsList;
    }

    public List<String> getFileUrls() {
        return fileUrls;
    }

    public void setFileUrls(List<String> fileUrls) {
        this.fileUrls = fileUrls;
    }

    public List<String> getFileList() {
        return fileList;
    }

    public void setFileList(List<String> fileList) {
        this.fileList = fileList;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setEvaluateMonth(Date evaluateMonth) 
    {
        this.evaluateMonth = evaluateMonth;
    }

    public Date getEvaluateMonth() 
    {
        return evaluateMonth;
    }
    public void setTypeId(String typeId) 
    {
        this.typeId = typeId;
    }

    public String getTypeId() 
    {
        return typeId;
    }
    public void setTypeName(String typeName) 
    {
        this.typeName = typeName;
    }

    public String getTypeName() 
    {
        return typeName;
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
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }
    public void setTotalScore(Long totalScore) 
    {
        this.totalScore = totalScore;
    }

    public Long getTotalScore() 
    {
        return totalScore;
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
            .append("evaluateMonth", getEvaluateMonth())
            .append("typeId", getTypeId())
            .append("typeName", getTypeName())
            .append("carrierId", getCarrierId())
            .append("carrierName", getCarrierName())
            .append("remarks", getRemarks())
            .append("totalScore", getTotalScore())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
