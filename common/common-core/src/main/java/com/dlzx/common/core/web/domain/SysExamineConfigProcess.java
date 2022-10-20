package com.dlzx.common.core.web.domain;

import com.dlzx.common.core.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


/**
 * 系统管理-审批流程配置对象 SysExamineConfigProcess
 *
 * @author dlzx
 * @date 2020-12-11
 */
public class SysExamineConfigProcess extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 审批业务-关联字典项表 */
    @Excel(name = "审批业务")
    private Long type;

    /** 审批配置ID */
    @Excel(name = "审批配置ID")
    private String configId;

    /** 审批级别 */
    @Excel(name = "审批级别")
    private Long level;

    /** 审批角色ID */
    @Excel(name = "审批角色ID")
    private Long roleId;

    /** 审批角色名称 */
    @Excel(name = "审批角色名称")
    private String roleName;

    /** 审批用户ID */
    @Excel(name = "审批用户ID")
    private Long userId;

    /** 审批用户名称 */
    @Excel(name = "审批用户名称")
    private String userName;

    /** 抄送角色ID */
    @Excel(name = "抄送角色ID")
    private Long ccRoleId;

    /** 抄送角色名称 */
    @Excel(name = "抄送角色名称")
    private String ccRoleName;

    /** 抄送用户ID */
    @Excel(name = "抄送用户ID")
    private Long ccUserId;

    /** 抄送用户名称 */
    @Excel(name = "抄送用户名称")
    private String ccUserName;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag=1L;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createBy;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private LocalDate createTime;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updateBy;

    /** 更新时间 */
    @Excel(name = "更新时间")
    private LocalDate updateTime;



    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setType(Long type)
    {
        this.type = type;
    }


    @NotBlank(message = "审批业务类型不能为空")
    public Long getType()
    {
        return type;
    }
    public void setConfigId(String configId)
    {
        this.configId = configId;
    }

    @NotBlank(message = "审批配置ID不能为空")
    public String getConfigId()
    {
        return configId;
    }
    public void setLevel(Long level)
    {
        this.level = level;
    }

    @NotBlank(message = "审批级别不能为空")
    public Long getLevel()
    {
        return level;
    }
    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    @NotBlank(message = "审配角色ID不能为空")
    public Long getRoleId()
    {
        return roleId;
    }
    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    @NotBlank(message = "审配角色名称不能为空")
    public String getRoleName()
    {
        return roleName;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    @NotBlank(message = "审批用户ID不能为空")
    public Long getUserId()
    {
        return userId;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    @NotBlank(message = "审批用户名称不能为空")
    public String getUserName()
    {
        return userName;
    }
    public void setCcRoleId(Long ccRoleId)
    {
        this.ccRoleId = ccRoleId;
    }

    public Long getCcRoleId()
    {
        return ccRoleId;
    }
    public void setCcRoleName(String ccRoleName)
    {
        this.ccRoleName = ccRoleName;
    }

    public String getCcRoleName()
    {
        return ccRoleName;
    }
    public void setCcUserId(Long ccUserId)
    {
        this.ccUserId = ccUserId;
    }

    public Long getCcUserId()
    {
        return ccUserId;
    }
    public void setCcUserName(String ccUserName)
    {
        this.ccUserName = ccUserName;
    }

    public String getCcUserName()
    {
        return ccUserName;
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
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("type", getType())
                .append("configId", getConfigId())
                .append("level", getLevel())
                .append("roleId", getRoleId())
                .append("roleName", getRoleName())
                .append("userId", getUserId())
                .append("userName", getUserName())
                .append("ccRoleId", getCcRoleId())
                .append("ccRoleName", getCcRoleName())
                .append("ccUserId", getCcUserId())
                .append("ccUserName", getCcUserName())
                .append("deleteFlag", getDeleteFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}