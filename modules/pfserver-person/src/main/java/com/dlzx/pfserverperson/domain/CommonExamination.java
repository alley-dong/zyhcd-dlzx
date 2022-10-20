package com.dlzx.pfserverperson.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author ylh
 * @program:dlzx
 * @description：
 * @create:2020-11-19 20-14
 */
public class CommonExamination {

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
     private String interviewRecord;

    private Long interviewStatus;

    public String getInterviewRemarks() {
        return interviewRemarks;
    }

    public void setInterviewRemarks(String interviewRemarks) {
        this.interviewRemarks = interviewRemarks;
    }

    private String interviewRemarks;

    public String getInterviewRecord() {
        return interviewRecord;
    }

    public void setInterviewRecord(String interviewRecord) {
        this.interviewRecord = interviewRecord;
    }

    public Long getInterviewStatus() {
        return interviewStatus;
    }

    public void setInterviewStatus(Long interviewStatus) {
        this.interviewStatus = interviewStatus;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("interviewRecord", getInterviewRecord())
                .append("interviewStatus", getInterviewStatus())
                .append("interviewRemarks",getInterviewRemarks())
                .toString();
    }
}
