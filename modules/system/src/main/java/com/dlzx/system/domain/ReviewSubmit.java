package com.dlzx.system.domain;

/**
 * @author ylh
 * @program:dlzx
 * @description：公共审核DTO
 * @create:2020-12-11 21-35
 */
public class ReviewSubmit {

    private String relationId;

    private String userId;

    private Long level;

    private Long status;

    private String reviewRemarks;

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getReviewRemarks() {
        return reviewRemarks;
    }

    public void setReviewRemarks(String reviewRemarks) {
        this.reviewRemarks = reviewRemarks;
    }
}
