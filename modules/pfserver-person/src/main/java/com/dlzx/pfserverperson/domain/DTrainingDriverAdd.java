package com.dlzx.pfserverperson.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/*
 * 公告新增/编辑实体类
 * */
public class DTrainingDriverAdd {
    private static final long serialVersionUID = 1L;

    private String id;
    //标题
    private String title;
    //
    private Long minDuration;
    //
    private String trainingEndTime;
    //
    private String education;
    //群组id

    private List<String> groupId;
    //驾驶员id
    private List<String> driverId;
    //培训内容
    private String content;

    private String videoUrl;

    /**
     * 多文件url
     * */
    private List<String> fileUrls;

    public List<String> getFileUrls() {
        return fileUrls;
    }

    public void setFileUrls(List<String> fileUrls) {
        this.fileUrls = fileUrls;
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
    @NotNull(message = "选择培训人员不能为空")
    public List<String> getDriverId() {
        return driverId;
    }
    public void setMinDuration(Long minDuration)
    {
        this.minDuration = minDuration;
    }
    @NotNull(message = "最少阅读时长不能为空")
    @Min(value = 1,message = "阅读时长应为正整数")
    public Long getMinDuration()
    {
        return minDuration;
    }
    public void setTrainingEndTime(String trainingEndTime)
    {
        this.trainingEndTime = trainingEndTime;
    }
    @NotBlank(message = "培训截止时间不能为空")
    public String getTrainingEndTime()
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
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("title", getTitle())
                .append("content", getContent())
                .append("groupId", getGroupId())
                .append("driverId", getDriverId())
                .append("minDuration", getMinDuration())
                .append("trainingEndTime", getTrainingEndTime())
                .append("education", getEducation())
                .append("videoUrl", getVideoUrl())
                .toString();
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
