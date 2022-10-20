package com.dlzx.pfserverperson.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 驾驶员-公告对象 d_notice
 *
 * @author dlzx
 * @date 2020-11-24
 */
public class DNotice extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 状态 1未发布 2已发布
     */
    @Excel(name = "状态 1未发布 2已发布")
    private Long status;

    /**
     * 标题
     */
    @Excel(name = "标题")
    private String title;

    /**
     * 分组ID-数组形式，数据回显使用
     */
    @Excel(name = "分组ID-数组形式，数据回显使用")
    private String groupId;

    /**
     * 培训内容
     */
    @Excel(name = "培训内容")
    private String content;

    /**
     * 截止时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

    /**
     * 删除状态 1正常 2删除
     */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotBlank(message = "标题不能为空")
    public String getTitle() {
        return title;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @NotBlank(message = "公告内容不能为空")
    public String getContent() {
        return content;
    }

    public void setDeleteFlag(Long deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Long getDeleteFlag() {
        return deleteFlag;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("status", getStatus())
                .append("title", getTitle())
                .append("groupId", getGroupId())
                .append("content", getContent())
                .append("deleteFlag", getDeleteFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
