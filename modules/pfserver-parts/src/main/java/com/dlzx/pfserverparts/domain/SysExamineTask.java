package com.dlzx.pfserverparts.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 系统管理-审批任务对象 sys_examine_task
 * 
 * @author yangluhe
 * @date 2020-12-11
 */
public class SysExamineTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 审批业务-关联字典项表 */
    @Excel(name = "审批业务-关联字典项表")
    private Long type;

    /** 关联ID */
    @Excel(name = "关联ID")
    private String relationId;

    /** 级别 */
    @Excel(name = "级别")
    private Long level;

    /** 角色ID */
    @Excel(name = "角色ID")
    private Long roleId;

    /** 角色名称 */
    @Excel(name = "角色名称")
    private String roleName;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 任务状态 1启用 2禁用 */
    @Excel(name = "任务状态 1启用 2禁用")
    private Long taskStatus;

    /** 人员类型 1审批人 2抄送人 */
    @Excel(name = "人员类型 1审批人 2抄送人")
    private Long userType;

    /** 审核状态 1待审核 2已审核 3已驳回 */
    @Excel(name = "审核状态 1待审核 2已审核 3已驳回")
    private Long status;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reviewTime;

    /** 审核备注 */
    @Excel(name = "审核备注")
    private String reviewRemarks;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag;

    /** 审批人列表*/
    private List<SysExamineTask> sysExamineUserTaskList;
    /** 抄送人列表*/
    private List<SysExamineTask> sysDuplicateUserTaskList;

    public List<SysExamineTask> getSysExamineUserTaskList() {
        return sysExamineUserTaskList;
    }

    public void setSysExamineUserTaskList(List<SysExamineTask> sysExamineUserTaskList) {
        this.sysExamineUserTaskList = sysExamineUserTaskList;
    }

    public List<SysExamineTask> getSysDuplicateUserTaskList() {
        return sysDuplicateUserTaskList;
    }

    public void setSysDuplicateUserTaskList(List<SysExamineTask> sysDuplicateUserTaskList) {
        this.sysDuplicateUserTaskList = sysDuplicateUserTaskList;
    }

    /** 任务状态 1启用 2禁用 */
    private String taskStatusEnum;

    /** 人员类型 1审批人 2抄送人 */
    private String userTypeEnum;

    /** 审核状态 1待审核 2已审核 3已驳回 */
    private String statusEnum;

    public String getTaskStatusEnum() {
        return taskStatusEnum;
    }

    public void setTaskStatusEnum(String taskStatusEnum) {
        this.taskStatusEnum = taskStatusEnum;
    }

    public String getUserTypeEnum() {
        return userTypeEnum;
    }

    public void setUserTypeEnum(String userTypeEnum) {
        this.userTypeEnum = userTypeEnum;
    }

    public String getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(String statusEnum) {
        this.statusEnum = statusEnum;
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
    public void setRelationId(String relationId) 
    {
        this.relationId = relationId;
    }

    public String getRelationId() 
    {
        return relationId;
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
    public void setTaskStatus(Long taskStatus) 
    {
        this.taskStatus = taskStatus;
    }

    public Long getTaskStatus() 
    {
        return taskStatus;
    }
    public void setUserType(Long userType) 
    {
        this.userType = userType;
    }

    public Long getUserType() 
    {
        return userType;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("relationId", getRelationId())
            .append("level", getLevel())
            .append("roleId", getRoleId())
            .append("roleName", getRoleName())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("taskStatus", getTaskStatus())
            .append("userType", getUserType())
            .append("status", getStatus())
            .append("reviewTime", getReviewTime())
            .append("reviewRemarks", getReviewRemarks())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
