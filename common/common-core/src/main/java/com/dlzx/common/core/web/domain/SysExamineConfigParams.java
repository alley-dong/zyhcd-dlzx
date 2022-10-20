package com.dlzx.common.core.web.domain;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 类描述：审批入参公用类
 * @className SysExamineConfigParams
 * company 赛拓信息技术有限公司
 * @author DongCL
 * @date 2021-03-29 09:31
 */
public class SysExamineConfigParams {

    // 类型
    private Long type;

    // 关联表ID
    private String relationId;

    // 司机ID
    private String driverId;

    // 名称
    private String name;

    // 审批人列表
    List<SysExamineConfigProcess> sysExamineConfigProcessList;

    public SysExamineConfigParams() {
    }

    public SysExamineConfigParams(Long type, String relationId, String driverId, String name, List<SysExamineConfigProcess> sysExamineConfigProcessList) {
        this.type = type;
        this.relationId = relationId;
        this.driverId = driverId;
        this.name = name;
        this.sysExamineConfigProcessList = sysExamineConfigProcessList;
    }

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

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SysExamineConfigProcess> getSysExamineConfigProcessList() {
        return sysExamineConfigProcessList;
    }

    public void setSysExamineConfigProcessList(List<SysExamineConfigProcess> sysExamineConfigProcessList) {
        this.sysExamineConfigProcessList = sysExamineConfigProcessList;
    }
}
