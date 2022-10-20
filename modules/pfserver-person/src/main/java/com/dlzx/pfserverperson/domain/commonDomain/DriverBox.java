package com.dlzx.pfserverperson.domain.commonDomain;

/**
 * @author ylh
 * @program:dlzx
 * @description：驾驶员类型下拉框
 * @create:2020-11-30 16-29
 */
public class DriverBox {

    /**驾驶员类型id*/
    private String id;

    /**驾驶员类型名称*/
    private String name;

    private Long performanceType;

    public Long getPerformanceType() {
        return performanceType;
    }

    public void setPerformanceType(Long performanceType) {
        this.performanceType = performanceType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
