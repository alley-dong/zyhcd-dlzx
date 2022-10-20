package com.dlzx.pfserverperson.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 驾驶员-请假类型对象 d_leave_type
 * 
 * @author yangluhe
 * @date 2020-11-24
 */
public class DLeaveType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 员工类型 1正式工 1合同工 */
    @Excel(name = "员工类型 1正式工 1合同工")
    private Long staffType;

    /** 类型名称 */
    @Excel(name = "类型名称")
    private String name;

    /** 是否有薪 1是 2否 */
    @Excel(name = "是否有薪 1是 2否")
    private Long isSalary;

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
    public void setStaffType(Long staffType) 
    {
        this.staffType = staffType;
    }

    @NotNull(message = "员工类型不能为空")
    public Long getStaffType() 
    {
        return staffType;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    @Length(max=10,message = "请假类型不能超过十个字")
    @NotBlank(message = "请假类型不能为空")
    public String getName() 
    {
        return name;
    }
    public void setIsSalary(Long isSalary) 
    {
        this.isSalary = isSalary;
    }

    @NotNull(message = "年薪不能为空")
    public Long getIsSalary() 
    {
        return isSalary;
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
            .append("staffType", getStaffType())
            .append("name", getName())
            .append("isSalary", getIsSalary())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
