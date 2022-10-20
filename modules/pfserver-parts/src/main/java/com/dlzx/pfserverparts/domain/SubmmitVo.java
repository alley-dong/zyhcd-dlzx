package com.dlzx.pfserverparts.domain;

import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * @author ylh
 * @program:dlzx
 * @description：新增提交时向任务表配置审核
 * @create:2020-12-14 09-04
 */
public class SubmmitVo extends BaseEntity {

    /**审核流程类型*/
    private Long type;

    /**关联表主键*/
    private String relationId;

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }
}
