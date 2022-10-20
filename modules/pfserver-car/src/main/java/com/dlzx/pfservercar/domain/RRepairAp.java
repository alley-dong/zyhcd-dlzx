package com.dlzx.pfservercar.domain;

import com.dlzx.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * @author ylh
 * @program:dlzx
 * @description：App 维修保养记录 DTO
 * @create:2020-12-09 09-21
 */
public class RRepairAp  extends BaseEntity {

    private String id;

    /**维保类型*/
    private Long repairType;

    /**审核状态*/
    private Long status;

    public Long getIsOther() {
        return isOther;
    }

    public void setIsOther(Long isOther) {
        this.isOther = isOther;
    }

    /**is_other*/
    private Long isOther;

    public List<RRepairItemDate> getRepairItemDate() {
        return repairItemDate;
    }

    public void setRepairItemDate(List<RRepairItemDate> repairItemDate) {
        this.repairItemDate = repairItemDate;
    }

    /**RRepariItemDate*/
    private List<RRepairItemDate> repairItemDate;

    public Long getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(Long repairStatus) {
        this.repairStatus = repairStatus;
    }

    /**维修状态*/
    private Long repairStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getRepairType() {
        return repairType;
    }

    public void setRepairType(Long repairType) {
        this.repairType = repairType;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("status", getStatus())
                .append("repairType", getRepairType())
                .append("repairStatus", getRepairStatus())
                .append("isOther",getIsOther())
                .append("repairItemDate",getRepairItemDate())
                .toString();
    }

}
