package com.dlzx.pfservercar.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

import javax.validation.constraints.NotNull;

/**
 * 车辆管理-理赔参与方对象 c_insurance_claims_participant
 * 
 * @author dongchanglong
 * @date 2021-03-12
 */
public class CInsuranceClaimsParticipant extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 理赔ID */
    @Excel(name = "理赔ID")
    private String insuranceClaimsId;

    /** 赔付方类型（1我方 2对方 3第三方） */
    @Excel(name = "赔付方类型", readConverterExp = "1=我方,2=对方,3=第三方")
    private Long userType;

    /** 赔付方名称 */
    @Excel(name = "赔付方名称")
    private String name;

    /** 责任比例 */
    @Excel(name = "责任比例")
    private BigDecimal proportion;

    /** 赔付金额 */
    @Excel(name = "赔付金额")
    private BigDecimal compensateMoney;

    /** 赔付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "赔付时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date compensateTime;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag = 1L;

    public String getInsuranceClaimsId() {
        return insuranceClaimsId;
    }

    public void setInsuranceClaimsId(String insuranceClaimsId) {
        this.insuranceClaimsId = insuranceClaimsId;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }

    @NotNull(message = "名称不能为空")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserType(Long userType)
    {
        this.userType = userType;
    }

    public Long getUserType() 
    {
        return userType;
    }
    public void setProportion(BigDecimal proportion) 
    {
        this.proportion = proportion;
    }

    @NotNull(message = "责任比例不能为空")
    public BigDecimal getProportion() 
    {
        return proportion;
    }
    public void setCompensateMoney(BigDecimal compensateMoney) 
    {
        this.compensateMoney = compensateMoney;
    }

    @NotNull(message = "赔付金额不能为空")
    public BigDecimal getCompensateMoney() 
    {
        return compensateMoney;
    }
    public void setCompensateTime(Date compensateTime) 
    {
        this.compensateTime = compensateTime;
    }

    @NotNull(message = "赔付时间不能为空")
    public Date getCompensateTime() 
    {
        return compensateTime;
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
            .append("userType", getUserType())
            .append("proportion", getProportion())
            .append("compensateMoney", getCompensateMoney())
            .append("compensateTime", getCompensateTime())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
