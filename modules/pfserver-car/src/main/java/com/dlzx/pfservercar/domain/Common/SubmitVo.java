package com.dlzx.pfservercar.domain.Common;

import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * @author ylh
 * @program:dlzx
 * @description：新增提交审批任务表审核记录
 * @create:2020-12-14 10-35
 */
public class SubmitVo extends BaseEntity {

    /**审核类型*/
    private Long type;

    /**关联主键id*/
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
