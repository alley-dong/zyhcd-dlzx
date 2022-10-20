package com.dlzx.pfserverperson.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 驾驶员-公告对象 d_notice
 *
 * @author dlzx
 * @date 2020-11-24
 */
public class DNoticeAp extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    @Excel(name = "标题")
    private String title;

    /**
     * 公告ID
     */
    @Excel(name = "公告ID")
    private String noticeId;

    /**
     * 截止时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

    private Date sTime;

    public Date getsTime() {
        return sTime;
    }

    public void setsTime(Date sTime) {
        this.sTime = sTime;
    }

    public Date getfTime() {
        return fTime;
    }

    public void setfTime(Date fTime) {
        this.fTime = fTime;
    }

    private Date fTime;

    /**
     * 状态 1未读 2已读
     */
    @Excel(name = "状态 1未读 2已读")
    private Long status;

    private String userId;

    private String bTime;
    private String eTime;

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }




    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)

                .append("title", getTitle())

                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("noticeId", getNoticeId())
                .append("status", getStatus())
                .append("userId", getUserId())
                .append("bTime", getbTime())
                .append("eTime", geteTime())
                .toString();
    }


    public String getbTime() {
        return bTime;
    }

    public void setbTime(String bTime) {
        this.bTime = bTime;
    }

    public String geteTime() {
        return eTime;
    }

    public void seteTime(String eTime) {
        this.eTime = eTime;
    }
}
