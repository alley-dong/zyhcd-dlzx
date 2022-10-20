package com.dlzx.pfservercar.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 【请填写功能名称】对象 sys_log
 * 
 * @author dongchanglong
 * @date 2021-05-06
 */
public class SysLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增id */
    private Long id;

    /** 删除标识(0：默认1：删除) */
    @Excel(name = "删除标识(0：默认1：删除)")
    private Integer deleteFlag;

    /** 操作人 */
    @Excel(name = "操作人")
    private String logBy;

    /** 操作类型(发送，接受) */
    @Excel(name = "操作类型(发送，接受)")
    private String logType;

    /** 操作内容 */
    @Excel(name = "操作内容")
    private String message;

    private String startTime;
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDeleteFlag(Integer deleteFlag) 
    {
        this.deleteFlag = deleteFlag;
    }

    public Integer getDeleteFlag() 
    {
        return deleteFlag;
    }
    public void setLogBy(String logBy) 
    {
        this.logBy = logBy;
    }

    public String getLogBy() 
    {
        return logBy;
    }
    public void setLogType(String logType) 
    {
        this.logType = logType;
    }

    public String getLogType() 
    {
        return logType;
    }
    public void setMessage(String message) 
    {
        this.message = message;
    }

    public String getMessage() 
    {
        return message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createTime", getCreateTime())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("logBy", getLogBy())
            .append("logType", getLogType())
            .append("message", getMessage())
            .toString();
    }
}
