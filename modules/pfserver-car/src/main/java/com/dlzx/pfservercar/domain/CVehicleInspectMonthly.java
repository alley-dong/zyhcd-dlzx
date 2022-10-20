package com.dlzx.pfservercar.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 车辆管理-车辆定检发布对象 c_vehicle_inspect_monthly
 * 
 * @author dlzx
 * @date 2020-12-08
 */
public class CVehicleInspectMonthly
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 定检日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "定检日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inspectDate;

    /** 总车辆 */
    @Excel(name = "总车辆")
    private Long totalNumber;

    /** 完成车辆 */
    @Excel(name = "完成车辆")
    private Long completeNumber;

    /** 未完成车辆 */
    @Excel(name = "未完成车辆")
    private Long incompleteNumber;

    /** 页码 */
    @JsonIgnore
    private Integer pageNum;

    /** 条数 */
    @JsonIgnore
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setInspectDate(Date inspectDate) 
    {
        this.inspectDate = inspectDate;
    }

    public Date getInspectDate() 
    {
        return inspectDate;
    }
    public void setTotalNumber(Long totalNumber) 
    {
        this.totalNumber = totalNumber;
    }

    public Long getTotalNumber() 
    {
        return totalNumber;
    }
    public void setCompleteNumber(Long completeNumber) 
    {
        this.completeNumber = completeNumber;
    }

    public Long getCompleteNumber() 
    {
        return completeNumber;
    }
    public void setIncompleteNumber(Long incompleteNumber) 
    {
        this.incompleteNumber = incompleteNumber;
    }

    public Long getIncompleteNumber() 
    {
        return incompleteNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("inspectDate", getInspectDate())
            .append("totalNumber", getTotalNumber())
            .append("completeNumber", getCompleteNumber())
            .append("incompleteNumber", getIncompleteNumber())
            .toString();
    }
}
