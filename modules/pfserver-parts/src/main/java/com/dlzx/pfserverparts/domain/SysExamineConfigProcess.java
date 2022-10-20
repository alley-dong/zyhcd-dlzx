package com.dlzx.pfserverparts.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.dlzx.system.api.domain.SysUser;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.List;


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
    @Excel(name = "审批业务-关联字典项表")
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
    private Long deleteFlag;


    /** 审批用户列表 */
    private List<SysUser> sysUserList;

    /** 抄送用户列表 */
    private List<SysUser> sysCcUserList;

    public List<SysUser> getSysUserList() {
        return sysUserList;
    }

    public void setSysUserList(List<SysUser> sysUserList) {
        this.sysUserList = sysUserList;
    }

    public List<SysUser> getSysCcUserList() {
        return sysCcUserList;
    }

    public void setSysCcUserList(List<SysUser> sysCcUserList) {
        this.sysCcUserList = sysCcUserList;
    }
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

    public Long getType()
    {
        return type;
    }
    public void setConfigId(String configId)
    {
        this.configId = configId;
    }

    public String getConfigId()
    {
        return configId;
    }
    public void setLevel(Long level)
    {
        this.level = level;
    }

    public Long getLevel()
    {
        return level;
    }
    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    public Long getRoleId()
    {
        return roleId;
    }
    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String getRoleName()
    {
        return roleName;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

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