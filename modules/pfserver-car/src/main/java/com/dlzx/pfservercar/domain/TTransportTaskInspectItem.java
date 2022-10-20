package com.dlzx.pfservercar.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

import javax.validation.constraints.NotBlank;

/**
 * 任务管理-运输任务点检检查项对象 t_transport_task_inspect_item
 * 
 * @author dlzx
 * @date 2020-12-18
 */
public class TTransportTaskInspectItem extends BaseEntity
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

    /** 点检记录ID */
    @Excel(name = "点检记录ID")
    private String inspectId;

    /** 检查项 */
    @Excel(name = "检查项")
    private Long type;

    /** 状态 1正常 2维修 */
    @Excel(name = "状态 1正常 2维修")
    private Long status;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 附件地址 */
    @Excel(name = "附件地址")
    private String files;

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
    public void setInspectId(String inspectId) 
    {
        this.inspectId = inspectId;
    }
    public String getInspectId()
    {
        return inspectId;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }
    @NotBlank(message = "检查项不能为空")
    public Long getType() 
    {
        return type;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }
    @NotBlank(message = "点检项状态不能为空")
    public Long getStatus() 
    {
        return status;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }
    public void setFiles(String files) 
    {
        this.files = files;
    }

    public String getFiles() 
    {
        return files;
    }
    public void setDeleteFlag(Long deleteFlag) 
    {
        this.deleteFlag = deleteFlag;
    }

    public Long getDeleteFlag() 
    {
        return deleteFlag;
    }

}
