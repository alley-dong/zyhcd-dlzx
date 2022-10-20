package com.dlzx.pfserverperson.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/*
 * 公告新增/编辑实体类
 * */
public class DNoticeDriverAdd {
    private static final long serialVersionUID = 1L;

    private String id;
    //标题
    private String title;
    //群组id
    private List<String> groupId;
    //驾驶员id
    private List<String> driverId;
    //公告内容
    private String content;

    /**
     * 截止时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deadline;


    @NotNull(message = "公告截止时间不能为空")
    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

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

    public void setContent(String content) {
        this.content = content;
    }

    @NotBlank(message = "内容不能为空")
    public String getContent() {
        return content;
    }

    public void setGroupId(List<String> groupId) {
        this.groupId = groupId;
    }
    @NotNull(message = "选择群组不能为空")
    public List<String> getGroupId() {
        return groupId;
    }

    public void setDriverId(List<String> driverId) {
        this.driverId = driverId;
    }
    @NotNull(message = "选择参与人员不能为空")
    public List<String> getDriverId() {
        return driverId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("title", getTitle())
                .append("content", getContent())
                .append("groupId", getGroupId())
                .append("driverId", getDriverId())
                .toString();
    }
}
