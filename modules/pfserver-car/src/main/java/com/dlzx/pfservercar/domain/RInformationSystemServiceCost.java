package com.dlzx.pfservercar.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 统计报-信息系统服务费统计对象 r_information_system_service_cost
 * 
 * @author dlzx
 * @date 2020-12-22
 */
public class RInformationSystemServiceCost extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 截止时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "截止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date closeTime;

    /** 车辆id */

    private String vehicleId;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String licensePlate;

    /** 系统名称 */
    @Excel(name = "系统名称")
    private String systemName;

    /** 金额(元) */
    @Excel(name = "金额（元）")
    private BigDecimal amount;

    /** 删除状态 1正常 2删除 */

    private Long deleteFlag;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setCloseTime(Date closeTime) 
    {
        this.closeTime = closeTime;
    }

    public Date getCloseTime() 
    {
        return closeTime;
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
    public void setSystemName(String systemName) 
    {
        this.systemName = systemName;
    }

    public String getSystemName() 
    {
        return systemName;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

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
            .append("startTime", getStartTime())
            .append("closeTime", getCloseTime())
            .append("vehicleId", getVehicleId())
            .append("licensePlate", getLicensePlate())
            .append("systemName", getSystemName())
            .append("amount", getAmount())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }

    public boolean equals() {
        String str = new StringBuilder()
                .append(getId()!=null?getId():"")
                .append(getStartTime()!=null?getStartTime():"")
                .append(getCloseTime()!=null?getCloseTime():"")
                .append(getVehicleId()!=null?getVehicleId():"")
                .append(getLicensePlate()!=null?getLicensePlate():"")
                .append(getSystemName()!=null?getSystemName():"")
                .append(getAmount()!=null?getAmount():"")
                .append(getDeleteFlag()!=null?getDeleteFlag():"")
                .append(getCreateBy()!=null?getCreateBy():"")
                .append(getCreateTime()!=null?getCreateTime():"")
                .append(getUpdateBy()!=null?getUpdateBy():"")
                .append(getUpdateTime()!=null?getUpdateTime():"")
                .toString();
        boolean flag = ("".equals(str.trim())||str.trim().length()==0);
//        System.out.println("数据长度:"+str+""+flag);
        return flag;


    }
}
