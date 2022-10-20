package com.dlzx.pfservercar.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 任务管理-运输部件对象 t_transport_task_parts
 * 
 * @author dcl
 * @date 2020-12-18
 */
public class TTransportTaskParts extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 任务ID */
    @Excel(name = "任务ID")
    private String taskId;

    /** 运单号 */
    @Excel(name = "运单号")
    private String shipmentXid;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderOn;

    /** 业务类型 */
    @Excel(name = "业务类型")
    private Long type;

    /** 零件编号 */
    @Excel(name = "零件编号")
    private String partsCode;

    /** 零件名称 */
    @Excel(name = "零件名称")
    private String partsName;

    /** 零件类型 */
    @Excel(name = "零件类型")
    private Long partsType;

    /** 零件数量 */
    @Excel(name = "零件数量")
    private Long partsCount;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag = 1L;

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
    public void setOrderOn(String orderOn) 
    {
        this.orderOn = orderOn;
    }

    public String getOrderOn() 
    {
        return orderOn;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setPartsCode(String partsCode) 
    {
        this.partsCode = partsCode;
    }

    public String getPartsCode() 
    {
        return partsCode;
    }
    public void setPartsName(String partsName) 
    {
        this.partsName = partsName;
    }

    public String getPartsName() 
    {
        return partsName;
    }
    public void setPartsType(Long partsType) 
    {
        this.partsType = partsType;
    }

    public Long getPartsType() 
    {
        return partsType;
    }
    public void setPartsCount(Long partsCount) 
    {
        this.partsCount = partsCount;
    }

    public Long getPartsCount() 
    {
        return partsCount;
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
            .append("orderOn", getOrderOn())
            .append("type", getType())
            .append("partsCode", getPartsCode())
            .append("partsName", getPartsName())
            .append("partsType", getPartsType())
            .append("partsCount", getPartsCount())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
