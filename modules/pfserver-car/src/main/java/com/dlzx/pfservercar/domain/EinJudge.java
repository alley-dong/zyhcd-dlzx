package com.dlzx.pfservercar.domain;

/**
 * @author ylh
 * @program:dlzx
 * @description：E02校验是否车辆续保
 * @create:2021-01-17 14-55
 */
public class EinJudge {

    private Long type;

    private String id;

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
