package com.dlzx.pfservercar.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

import javax.validation.constraints.NotBlank;

/**
 * 任务管理-运输任务点检对象 t_transport_task_inspect
 * 
 * @author dcl
 * @date 2020-12-18
 */
public class TTransportTaskInspect extends BaseEntity
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

    /** 点检类型 1发车点检 2在途点检 3归队点检 */
    @Excel(name = "点检类型 1发车点检 2在途点检 3归队点检")
    private Long type;

    /** 司机ID-关联d_driver表 */
    @Excel(name = "司机ID-关联d_driver表")
    private String driverId;

    /** 司机姓名 */
    @Excel(name = "司机姓名")
    private String driverName;

    /** 线路名称 */
    @Excel(name = "线路名称")
    private String routeName;

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
    @NotBlank(message = "任务ID不能为空")
    public String getTaskId() 
    {
        return taskId;
    }
    public void setShipmentXid(String shipmentXid) 
    {
        this.shipmentXid = shipmentXid;
    }
    @NotBlank(message = "运单号不能为空")
    public String getShipmentXid() 
    {
        return shipmentXid;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }
    @NotBlank(message = "点检类型不能为空")
    public Long getType() 
    {
        return type;
    }
    public void setDriverId(String driverId) 
    {
        this.driverId = driverId;
    }
    @NotBlank(message = "司机ID不能为空")
    public String getDriverId() 
    {
        return driverId;
    }
    public void setRouteName(String routeName) 
    {
        this.routeName = routeName;
    }
    @NotBlank(message = "线路名称不能为空")
    public String getRouteName() 
    {
        return routeName;
    }
    public void setDeleteFlag(Long deleteFlag) 
    {
        this.deleteFlag = deleteFlag;
    }
    public Long getDeleteFlag()
    {
        return deleteFlag;
    }
    @NotBlank(message = "司机名称不能为空")
    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

}
