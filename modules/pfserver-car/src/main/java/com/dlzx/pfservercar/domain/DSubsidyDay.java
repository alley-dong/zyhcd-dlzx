package com.dlzx.pfservercar.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 驾驶员-补助天数对象 d_subsidy_day
 * 
 * @author dlzx
 * @date 2020-12-14
 */
public class DSubsidyDay extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 补助ID 关联d_subsidy表 */
    @Excel(name = "补助ID 关联d_subsidy表")
    private String subsidyId;

    /** 补助时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "补助时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date subsidyDate;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setSubsidyId(String subsidyId) 
    {
        this.subsidyId = subsidyId;
    }

    public String getSubsidyId() 
    {
        return subsidyId;
    }
    public void setSubsidyDate(Date subsidyDate) 
    {
        this.subsidyDate = subsidyDate;
    }

    public Date getSubsidyDate() 
    {
        return subsidyDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("subsidyId", getSubsidyId())
            .append("subsidyDate", getSubsidyDate())
            .toString();
    }
}
