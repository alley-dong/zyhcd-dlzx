package com.dlzx.pfservercar.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 任务管理-运输任务订单对象 t_transport_task_order
 * 
 * @author DongCL
 * @date 2021-01-05
 */
public class TTransportTaskOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 任务ID（运单ID） */
    @Excel(name = "任务ID", readConverterExp = "运=单ID")
    private String taskId;

    /** 站点ID */
    @Excel(name = "站点ID")
    private String shipmentStopId;

    /** 订单ID */
    @Excel(name = "订单ID")
    private String orderMovementGid;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderNumber;

    /** 业务类型 */
    @Excel(name = "业务类型")
    private String businessType;

    /** 业务部门 */
    @Excel(name = "业务部门")
    private String businessUnitName;

    /** 出发地编码 */
    @Excel(name = "出发地编码")
    private String sourceLocationCode;

    /** 出发地名称 */
    @Excel(name = "出发地名称")
    private String sourceLocationName;

    /** 发货省 */
    @Excel(name = "发货省")
    private String sourceProvince;

    /** 发货市 */
    @Excel(name = "发货市")
    private String sourceCity;

    /** 发货区县 */
    @Excel(name = "发货区县")
    private String sourceDistrict;

    /** 发货地址 */
    @Excel(name = "发货地址")
    private String sourceAddress;

    /** 发货联系人 */
    @Excel(name = "发货联系人")
    private String sourceContacts;

    /** 发货联系电话 */
    @Excel(name = "发货联系电话")
    private String sourcePhoneNumber;

    /** 目的地编码 */
    @Excel(name = "目的地编码")
    private String destLocationCode;

    /** 收货点名称 */
    @Excel(name = "收货点名称")
    private String destLocationName;

    /** 收货省 */
    @Excel(name = "收货省")
    private String destProvince;

    /** 收货市 */
    @Excel(name = "收货市")
    private String destCity;

    /** 收货区县 */
    @Excel(name = "收货区县")
    private String destDistrict;

    /** 收货地址 */
    @Excel(name = "收货地址")
    private String destAddress;

    /** 收货联系人 */
    @Excel(name = "收货联系人")
    private String destContacts;

    /** 收货联系电话 */
    @Excel(name = "收货联系电话")
    private String destPhoneNumber;

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
    public void setShipmentStopId(String shipmentStopId) 
    {
        this.shipmentStopId = shipmentStopId;
    }

    public String getShipmentStopId() 
    {
        return shipmentStopId;
    }
    public void setOrderMovementGid(String orderMovementGid) 
    {
        this.orderMovementGid = orderMovementGid;
    }

    public String getOrderMovementGid() 
    {
        return orderMovementGid;
    }
    public void setOrderNumber(String orderNumber) 
    {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() 
    {
        return orderNumber;
    }
    public void setBusinessType(String businessType) 
    {
        this.businessType = businessType;
    }

    public String getBusinessType() 
    {
        return businessType;
    }
    public void setBusinessUnitName(String businessUnitName) 
    {
        this.businessUnitName = businessUnitName;
    }

    public String getBusinessUnitName() 
    {
        return businessUnitName;
    }
    public void setSourceLocationCode(String sourceLocationCode) 
    {
        this.sourceLocationCode = sourceLocationCode;
    }

    public String getSourceLocationCode() 
    {
        return sourceLocationCode;
    }
    public void setSourceLocationName(String sourceLocationName) 
    {
        this.sourceLocationName = sourceLocationName;
    }

    public String getSourceLocationName() 
    {
        return sourceLocationName;
    }
    public void setSourceProvince(String sourceProvince) 
    {
        this.sourceProvince = sourceProvince;
    }

    public String getSourceProvince() 
    {
        return sourceProvince;
    }
    public void setSourceCity(String sourceCity) 
    {
        this.sourceCity = sourceCity;
    }

    public String getSourceCity() 
    {
        return sourceCity;
    }
    public void setSourceDistrict(String sourceDistrict) 
    {
        this.sourceDistrict = sourceDistrict;
    }

    public String getSourceDistrict() 
    {
        return sourceDistrict;
    }
    public void setSourceAddress(String sourceAddress) 
    {
        this.sourceAddress = sourceAddress;
    }

    public String getSourceAddress() 
    {
        return sourceAddress;
    }
    public void setSourceContacts(String sourceContacts) 
    {
        this.sourceContacts = sourceContacts;
    }

    public String getSourceContacts() 
    {
        return sourceContacts;
    }
    public void setSourcePhoneNumber(String sourcePhoneNumber) 
    {
        this.sourcePhoneNumber = sourcePhoneNumber;
    }

    public String getSourcePhoneNumber() 
    {
        return sourcePhoneNumber;
    }
    public void setDestLocationCode(String destLocationCode) 
    {
        this.destLocationCode = destLocationCode;
    }

    public String getDestLocationCode() 
    {
        return destLocationCode;
    }
    public void setDestLocationName(String destLocationName) 
    {
        this.destLocationName = destLocationName;
    }

    public String getDestLocationName() 
    {
        return destLocationName;
    }
    public void setDestProvince(String destProvince) 
    {
        this.destProvince = destProvince;
    }

    public String getDestProvince() 
    {
        return destProvince;
    }
    public void setDestCity(String destCity) 
    {
        this.destCity = destCity;
    }

    public String getDestCity() 
    {
        return destCity;
    }
    public void setDestDistrict(String destDistrict) 
    {
        this.destDistrict = destDistrict;
    }

    public String getDestDistrict() 
    {
        return destDistrict;
    }
    public void setDestAddress(String destAddress) 
    {
        this.destAddress = destAddress;
    }

    public String getDestAddress() 
    {
        return destAddress;
    }
    public void setDestContacts(String destContacts) 
    {
        this.destContacts = destContacts;
    }

    public String getDestContacts() 
    {
        return destContacts;
    }
    public void setDestPhoneNumber(String destPhoneNumber) 
    {
        this.destPhoneNumber = destPhoneNumber;
    }

    public String getDestPhoneNumber() 
    {
        return destPhoneNumber;
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
            .append("shipmentStopId", getShipmentStopId())
            .append("orderMovementGid", getOrderMovementGid())
            .append("orderNumber", getOrderNumber())
            .append("businessType", getBusinessType())
            .append("businessUnitName", getBusinessUnitName())
            .append("sourceLocationCode", getSourceLocationCode())
            .append("sourceLocationName", getSourceLocationName())
            .append("sourceProvince", getSourceProvince())
            .append("sourceCity", getSourceCity())
            .append("sourceDistrict", getSourceDistrict())
            .append("sourceAddress", getSourceAddress())
            .append("sourceContacts", getSourceContacts())
            .append("sourcePhoneNumber", getSourcePhoneNumber())
            .append("destLocationCode", getDestLocationCode())
            .append("destLocationName", getDestLocationName())
            .append("destProvince", getDestProvince())
            .append("destCity", getDestCity())
            .append("destDistrict", getDestDistrict())
            .append("destAddress", getDestAddress())
            .append("destContacts", getDestContacts())
            .append("destPhoneNumber", getDestPhoneNumber())
            .append("remark", getRemark())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
