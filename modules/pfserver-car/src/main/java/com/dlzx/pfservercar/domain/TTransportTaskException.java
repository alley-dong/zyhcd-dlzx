package com.dlzx.pfservercar.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

import javax.validation.constraints.NotBlank;

/**
 * 任务管理-运输任务异常上报对象 t_transport_task_exception
 * 
 * @author dcl
 * @date 2020-12-18
 */
public class TTransportTaskException extends BaseEntity
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

    /** 异常类型 1运行异常 2任务异常 */
    @Excel(name = "异常类型 1运行异常 2任务异常")
    private Long type;

    /** 异常情况描述 */
    @Excel(name = "异常情况描述")
    private String remarks;

    /** 公里数 */
    @Excel(name = "公里数")
    private Long kilometre;

    /** 司机ID-关联d_driver表 */
    @Excel(name = "司机ID-关联d_driver表")
    private String driverId;

    /** 司机姓名 */
    @Excel(name = "司机姓名")
    private String driverName;

    /** 线路ID */
    @Excel(name = "线路ID")
    private String routeId;

    /** 线路名称 */
    @Excel(name = "线路名称")
    private String routeName;

    /** 审核状态 1待审核 2已审核 3已驳回 4已撤回 */
    @Excel(name = "审核状态 1待审核 2已审核 3已驳回 4已撤回")
    private Long status;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reviewTime;

    /** 审核人 */
    @Excel(name = "审核人")
    private String reviewUser;

    /** 审核备注 */
    @Excel(name = "审核备注")
    private String reviewRemarks;

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
    public void setType(Long type) 
    {
        this.type = type;
    }

    @NotBlank(message = "异常类型不能为空")
    public Long getType() 
    {
        return type;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    @NotBlank(message = "异常情况描述")
    public String getRemarks() 
    {
        return remarks;
    }
    public void setKilometre(Long kilometre) 
    {
        this.kilometre = kilometre;
    }

    @NotBlank(message = "公里数")
    public Long getKilometre() 
    {
        return kilometre;
    }
    public void setDriverId(String driverId) 
    {
        this.driverId = driverId;
    }

    public String getDriverId() 
    {
        return driverId;
    }
    public void setRouteId(String routeId) 
    {
        this.routeId = routeId;
    }

    public String getRouteId() 
    {
        return routeId;
    }
    public void setRouteName(String routeName) 
    {
        this.routeName = routeName;
    }

    public String getRouteName() 
    {
        return routeName;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setReviewTime(Date reviewTime) 
    {
        this.reviewTime = reviewTime;
    }

    public Date getReviewTime() 
    {
        return reviewTime;
    }
    public void setReviewUser(String reviewUser) 
    {
        this.reviewUser = reviewUser;
    }

    public String getReviewUser() 
    {
        return reviewUser;
    }
    public void setReviewRemarks(String reviewRemarks) 
    {
        this.reviewRemarks = reviewRemarks;
    }

    public String getReviewRemarks() 
    {
        return reviewRemarks;
    }
    public void setDeleteFlag(Long deleteFlag) 
    {
        this.deleteFlag = deleteFlag;
    }

    public Long getDeleteFlag() 
    {
        return deleteFlag;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskId", getTaskId())
            .append("shipmentXid", getShipmentXid())
            .append("type", getType())
            .append("remarks", getRemarks())
            .append("kilometre", getKilometre())
            .append("driverId", getDriverId())
            .append("routeId", getRouteId())
            .append("routeName", getRouteName())
            .append("status", getStatus())
            .append("reviewTime", getReviewTime())
            .append("reviewUser", getReviewUser())
            .append("reviewRemarks", getReviewRemarks())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
