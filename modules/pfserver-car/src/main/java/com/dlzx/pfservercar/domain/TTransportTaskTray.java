package com.dlzx.pfservercar.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 任务管理-运输任务托盘对象 t_transport_task_tray
 * 
 * @author DongCL
 * @date 2021-01-05
 */
public class TTransportTaskTray extends BaseEntity
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

    /** 订单ID */
    @Excel(name = "订单ID")
    private String orderId;

    /** 托单ID */
    @Excel(name = "托单ID")
    private String shipUnitGid;

    /** 托盘代码 */
    @Excel(name = "托盘代码")
    private String tranHandlingUnitGid;

    /** 托盘名称 */
    @Excel(name = "托盘名称")
    private String tranHandlingUnitName;

    /** 托盘数量 */
    @Excel(name = "托盘数量")
    private Long shipUnitCount;

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
    public void setOrderId(String orderId) 
    {
        this.orderId = orderId;
    }

    public String getOrderId() 
    {
        return orderId;
    }
    public void setShipUnitGid(String shipUnitGid) 
    {
        this.shipUnitGid = shipUnitGid;
    }

    public String getShipUnitGid() 
    {
        return shipUnitGid;
    }
    public void setTranHandlingUnitGid(String tranHandlingUnitGid) 
    {
        this.tranHandlingUnitGid = tranHandlingUnitGid;
    }

    public String getTranHandlingUnitGid() 
    {
        return tranHandlingUnitGid;
    }
    public void setTranHandlingUnitName(String tranHandlingUnitName) 
    {
        this.tranHandlingUnitName = tranHandlingUnitName;
    }

    public String getTranHandlingUnitName() 
    {
        return tranHandlingUnitName;
    }
    public void setShipUnitCount(Long shipUnitCount) 
    {
        this.shipUnitCount = shipUnitCount;
    }

    public Long getShipUnitCount() 
    {
        return shipUnitCount;
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
            .append("orderId", getOrderId())
            .append("shipUnitGid", getShipUnitGid())
            .append("tranHandlingUnitGid", getTranHandlingUnitGid())
            .append("tranHandlingUnitName", getTranHandlingUnitName())
            .append("shipUnitCount", getShipUnitCount())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
