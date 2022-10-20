package com.dlzx.pfserverperson.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

public class DNoticeDriver {
    private static final long serialVersionUID = 1L;

    private String id;
    //标题
    private String title;
    //公告id
    private String noticeId;
    //群组id
    private String groupId;
    //驾驶员id
    private String driverId;
    //驾驶员id表
    private List<DDriver> driverIdList;
    //驾驶员姓名表
    private List<String> driverNameList;
    //公告内容
    private String content;

    /**
     * 截止时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

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

    public String getTitle() {
        return title;
    }

    public void setDriverNameList(List<String> driverNameList) {
        this.driverNameList = driverNameList;
    }

    public List<String> getDriverNameList() {
        return driverNameList;
    }

    public void setDriverIdList(List<DDriver> driverIdList) {
        this.driverIdList = driverIdList;
    }

    public List<DDriver> getDriverIdList() {
        return driverIdList;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeId() {
        return noticeId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("title", getTitle())
                .append("driverNameList", getDriverNameList())
                .append("content", getContent())
                .append("groupId", getGroupId())
                .append("driverIdList", getDriverIdList())
                .append("driverId", getDriverId())
                .append("noticeId", getNoticeId())
                .toString();
    }
}
