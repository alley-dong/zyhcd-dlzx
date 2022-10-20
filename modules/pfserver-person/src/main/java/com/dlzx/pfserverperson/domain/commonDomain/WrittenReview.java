package com.dlzx.pfserverperson.domain.commonDomain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author ylh
 * @program:dlzx
 * @description：
 * @create:2020-11-19 20-53
 */
public class WrittenReview {
    private static final long serialVersionUID = 1L;

    private String id;

    private Long examStatus;

    private String examRemarks;

    public String getExamRemarks() {
        return examRemarks;
    }

    public void setExamRemarks(String examRemarks) {
        this.examRemarks = examRemarks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getExamStatus() {
        return examStatus;
    }

    public void setExamStatus(Long examStatus) {
        this.examStatus = examStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("examStatus", getExamStatus())
                .append("examRemarks",getExamRemarks())
                .toString();
    }
}
