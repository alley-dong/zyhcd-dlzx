package com.dlzx.pfserverperson.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 驾驶员-培训对象 d_training
 * 
 * @author dlzx
 * @date 2020-11-26
 */
public class DTraining extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 状态 1未发布 2已发布 */
    @Excel(name = "状态 1未发布 2已发布")
    private Long status;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 最少阅读时长 */
    @Excel(name = "最少阅读时长")
    private Long minDuration;

    /** 培训结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd ")
    @Excel(name = "培训结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date trainingEndTime;

    private String tEndTime;
    /** 培训要求 */
    @Excel(name = "培训要求")
    private String education;

    /** 视频地址 */
    @Excel(name = "视频地址")
    private String videoUrl;

    /** 分组ID-数组形式，数据回显使用 */
    @Excel(name = "分组ID-数组形式，数据回显使用")
    private String groupId;

    /** 培训内容 */
    @Excel(name = "培训内容")
    private String content;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag;

    /**
     * 多文件url(入参)
     * */
    private List<String> fileUrls;

    /**
     * 多文件url(出参)
     * */
    private List<String> fileList;

    /**
     *  阅读状态
     * */
    private String userStatus;

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public List<String> getFileList() {
        return fileList;
    }

    public void setFileList(List<String> fileList) {
        this.fileList = fileList;
    }

    public List<String> getFileUrls() {
        return fileUrls;
    }

    public void setFileUrls(List<String> fileUrls) {
        this.fileUrls = fileUrls;
    }

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }
    @NotBlank(message = "标题不能为空")
    public String getTitle() 
    {
        return title;
    }
    public void setMinDuration(Long minDuration) 
    {
        this.minDuration = minDuration;
    }
    @NotBlank(message = "设置阅读时长不能为空")
    public Long getMinDuration() 
    {
        return minDuration;
    }
    public void setTrainingEndTime(Date trainingEndTime) 
    {
        this.trainingEndTime = trainingEndTime;
    }
    @NotNull(message = "截止培训时间不能为空")
    public Date getTrainingEndTime() 
    {
        return trainingEndTime;
    }
    public void setEducation(String education) 
    {
        this.education = education;
    }
    @NotBlank(message = "培训要求不能为空")
    public String getEducation() 
    {
        return education;
    }
    public void setVideoUrl(String videoUrl) 
    {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() 
    {
        return videoUrl;
    }
    public void setGroupId(String groupId) 
    {
        this.groupId = groupId;
    }

    public String getGroupId() 
    {
        return groupId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }
    @NotBlank(message = "培训内容不能为空")
    public String getContent() 
    {
        return content;
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
            .append("status", getStatus())
            .append("title", getTitle())
            .append("minDuration", getMinDuration())
            .append("trainingEndTime", getTrainingEndTime())
            .append("education", getEducation())
            .append("videoUrl", getVideoUrl())
            .append("groupId", getGroupId())
            .append("content", getContent())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
                .append("tEndTime", gettEndTime())
            .toString();
    }

    public String gettEndTime() {
        return tEndTime;
    }

    public void settEndTime(String tEndTime) {
        this.tEndTime = tEndTime;
    }
}
