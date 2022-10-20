package com.dlzx.pfservercar.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 车辆管理-理赔对象 c_insurance_claims
 * 
 * @author dlzx
 * @date 2020-12-03
 */
public class CInsuranceClaims extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 车辆ID */
    @Excel(name = "车辆ID")
    private String vehicleId;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String vehicleName;

    /** 事故ID */
    @Excel(name = "事故ID")
    private String accidentId;

    /** 事故时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "事故时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date accidentTime;

    private String aTime;

    /** 事故地点 */
    @Excel(name = "事故地点")
    private String accidentLocation;

    /** 司机ID */
    @Excel(name = "司机ID")
    private String driverId;

    /** 司机姓名 */
    @Excel(name = "司机姓名")
    private String driverName;

    /** 赔付方姓名 */
    @Excel(name = "赔付方姓名")
    private String payer;

    /** 赔付金额 */
    @Excel(name = "赔付金额")
    private BigDecimal payAmount;

    /** 赔付开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "赔付开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payTime;

    /** 赔付结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "赔付结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payEndTime;

    private String pTime;
    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag;

    // 是否标红
    private Long colorFlag;

    // 我方参与列表
    private List<CInsuranceClaimsParticipant> onOurPartList;

    // 对方参与列表
    private List<CInsuranceClaimsParticipant> oppositeSideList;

    // 第三方参与列表
    private List<CInsuranceClaimsParticipant>  thirdPartyList;

    public Long getColorFlag() {
        return colorFlag;
    }

    public void setColorFlag(Long colorFlag) {
        this.colorFlag = colorFlag;
    }

    public List<CInsuranceClaimsParticipant> getOnOurPartList() {
        return onOurPartList;
    }

    public void setOnOurPartList(List<CInsuranceClaimsParticipant> onOurPartList) {
        this.onOurPartList = onOurPartList;
    }

    public List<CInsuranceClaimsParticipant> getOppositeSideList() {
        return oppositeSideList;
    }

    public void setOppositeSideList(List<CInsuranceClaimsParticipant> oppositeSideList) {
        this.oppositeSideList = oppositeSideList;
    }

    public List<CInsuranceClaimsParticipant> getThirdPartyList() {
        return thirdPartyList;
    }

    public void setThirdPartyList(List<CInsuranceClaimsParticipant> thirdPartyList) {
        this.thirdPartyList = thirdPartyList;
    }

    public Date getPayEndTime() {
        return payEndTime;
    }
    public void setPayEndTime(Date payEndTime) {
        this.payEndTime = payEndTime;
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
    public void setVehicleName(String vehicleName) 
    {
        this.vehicleName = vehicleName;
    }
    @NotBlank(message = "事故车牌号不能为空")
    public String getVehicleName() 
    {
        return vehicleName;
    }
    public void setAccidentId(String accidentId) 
    {
        this.accidentId = accidentId;
    }

    public String getAccidentId() 
    {
        return accidentId;
    }
    public void setAccidentTime(Date accidentTime) 
    {
        this.accidentTime = accidentTime;
    }
    @NotNull(message = "事故时间不能为空")
    public Date getAccidentTime() 
    {
        return accidentTime;
    }
    public void setAccidentLocation(String accidentLocation) 
    {
        this.accidentLocation = accidentLocation;
    }

    public String getAccidentLocation() 
    {
        return accidentLocation;
    }
    public void setDriverId(String driverId) 
    {
        this.driverId = driverId;
    }

    public String getDriverId() 
    {
        return driverId;
    }
    public void setDriverName(String driverName) 
    {
        this.driverName = driverName;
    }

    public String getDriverName() 
    {
        return driverName;
    }
    public void setPayer(String payer) 
    {
        this.payer = payer;
    }
    public String getPayer()
    {
        return payer;
    }
    public void setPayAmount(BigDecimal payAmount) 
    {
        this.payAmount = payAmount;
    }
    public BigDecimal getPayAmount()
    {
        return payAmount;
    }
    public void setPayTime(Date payTime) 
    {
        this.payTime = payTime;
    }
    @NotNull(message = "发起赔付时间不能为空")
    public Date getPayTime() 
    {
        return payTime;
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
            .append("vehicleName", getVehicleName())
            .append("accidentId", getAccidentId())
            .append("accidentTime", getAccidentTime())
            .append("accidentLocation", getAccidentLocation())
            .append("driverId", getDriverId())
            .append("driverName", getDriverName())
            .append("payer", getPayer())
            .append("payAmount", getPayAmount())
            .append("payTime", getPayTime())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
                .append("aTime", getaTime())
                .append("pTime", getpTime())
            .toString();
    }

    public String getaTime() {
        return aTime;
    }

    public void setaTime(String aTime) {
        this.aTime = aTime;
    }

    public String getpTime() {
        return pTime;
    }

    public void setpTime(String pTime) {
        this.pTime = pTime;
    }
}
