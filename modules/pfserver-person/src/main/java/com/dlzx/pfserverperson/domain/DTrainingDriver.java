package com.dlzx.pfserverperson.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

public class DTrainingDriver {
    private static final long serialVersionUID = 1L;

    private String id;
    //标题
    private String title;

    private Long minDuration;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date trainingEndTime;

    private String education;

    private String groupId;

    private List<DDriver> driverIdList;
    //驾驶员姓名表
    private List<String> driverNameList;

    private String content;

    private List<String> groupIdList;

    private String videoUrl;

    /**
     * 多文件url(出参)
     * */
    private List<String> fileList;

    public List<String> getFileList() {
        return fileList;
    }

    public void setFileList(List<String> fileList) {
        this.fileList = fileList;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
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

    public void setMinDuration(Long minDuration) {
        this.minDuration = minDuration;
    }

    public Long getMinDuration() {
        return minDuration;
    }

    public void setTrainingEndTime(Date trainingEndTime) {
        this.trainingEndTime = trainingEndTime;
    }

    public Date getTrainingEndTime() {
        return trainingEndTime;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getEducation() {
        return education;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setDriverNameList(List<String> driverNameList) {
        this.driverNameList = driverNameList;
    }

    public List<String> getDriverNameList() {
        return driverNameList;
    }
    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }

    public String getGroupId()
    {
        return groupId;
    }
    public void setDriverIdList(List<DDriver> driverIdList)
    {
        this.driverIdList = driverIdList;
    }

    public List<DDriver> getDriverIdList()
    {
        return driverIdList;
    }
    public List<String> getGroupIdList() {
        return groupIdList;
    }

    public void setGroupIdList(List<String> groupIdList) {
        this.groupIdList = groupIdList;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("title", getTitle())
                .append("minDuration", getMinDuration())
                .append("trainingEndTime", getTrainingEndTime())
                .append("education", getEducation())
                .append("driverNameList", getDriverNameList())
                .append("content", getContent())
                .append("groupId", getGroupId())
                .append("driverIdList", getDriverIdList())
                .append("groupIdList", getGroupIdList())
                .append("videoUrl",getVideoUrl())
                .toString();
    }


}
