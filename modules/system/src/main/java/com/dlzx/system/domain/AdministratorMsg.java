package com.dlzx.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 管理员-管理员消息对象 administrator_msg
 * 
 * @author dlzx
 * @date 2020-12-14
 */
public class AdministratorMsg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 消息类型 1请假审核 2维修保养审核 3补助审核 4.培训 5.公告  6.运输任务 7.事故上报 8.违章处理  9.车辆定检  10.物品领用 */
    @Excel(name = "消息类型 1请假审核 2维修保养审核 3补助审核 4.培训 5.公告  6.运输任务 7.事故上报 8.违章处理  9.车辆定检  10.物品领用")
    private Long type;

    /** 关系ID-根据消息类型关联表 */
    @Excel(name = "关系ID-根据消息类型关联表")
    private String relationId;

    /** 管理员ID-关联sys_user表 */
    @Excel(name = "管理员ID-关联sys_user表")
    private String userId;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 状态 1未读 2已读 */
    @Excel(name = "状态 1未读 2已读")
    private Long status;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag = 1L;

    /** 类型翻译 */
    private String typeEnum;

    public String getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(String typeEnum) {
        this.typeEnum = typeEnum;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
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
            .append("userId", getUserId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("status", getStatus())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
