package com.dlzx.pfservercar.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

import javax.validation.constraints.NotBlank;

/**
 * 车辆管理-车辆年检记录对象 c_vehicle_inspect_record
 *
 * @author yangluhe
 * @date 2020-11-27
 */
public class CVehicleInspectRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 车辆id
     */
    /*    @Excel(name = "车辆id")*/
    private String truckId;

    /**
     * 车牌号
     */
    @Excel(name = "车牌号")
    private String licensePlate;

    /**
     * 检车日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检车日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inspectDate;

    /**
     * 车辆年检费用
     */
    @Excel(name = "车辆年检费用")
    private BigDecimal vehiceInspectCost;

    /**
     * 运营年检费用
     */
    @Excel(name = "运营年检费用")
    private BigDecimal operateInspectCost;

    /**
     * 复检费用
     */
    @Excel(name = "复检费用")
    private BigDecimal reviewCost;


    /**
     * 合计费用
     */
    @Excel(name = "合计费用")
    private BigDecimal totalCost;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remarks;

    public Date getNextInspectDate() {
        return nextInspectDate;
    }

    public void setNextInspectDate(Date nextInspectDate) {
        this.nextInspectDate = nextInspectDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date nextInspectDate;

    /**
     * 注册日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registerDate;

    private String files;

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }


    /**
     * 删除状态 0删除 1未删除
     */
    private Long deleteFlag;

    public Long getRemindFlag() {
        return remindFlag;
    }

    public void setRemindFlag(Long remindFlag) {
        this.remindFlag = remindFlag;
    }

    /*提醒下次检车标识*/
    private Long remindFlag;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

    public String getTruckId() {
        return truckId;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @NotBlank(message = "车牌号不能为空")
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setInspectDate(Date inspectDate) {
        this.inspectDate = inspectDate;
    }

    public Date getInspectDate() {
        return inspectDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setVehiceInspectCost(BigDecimal vehiceInspectCost) {
        this.vehiceInspectCost = vehiceInspectCost;
    }

    public BigDecimal getVehiceInspectCost() {
        return vehiceInspectCost;
    }

    public void setOperateInspectCost(BigDecimal operateInspectCost) {
        this.operateInspectCost = operateInspectCost;
    }

    public BigDecimal getOperateInspectCost() {
        return operateInspectCost;
    }

    public void setReviewCost(BigDecimal reviewCost) {
        this.reviewCost = reviewCost;
    }

    public BigDecimal getReviewCost() {
        return reviewCost;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setDeleteFlag(Long deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Long getDeleteFlag() {
        return deleteFlag;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinshTime() {
        return finshTime;
    }

    public void setFinshTime(Date finshTime) {
        this.finshTime = finshTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date finshTime;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("truckId", getTruckId())
                .append("licensePlate", getLicensePlate())
                .append("inspectDate", getInspectDate())
                .append("registerDate", getRegisterDate())
                .append("vehiceInspectCost", getVehiceInspectCost())
                .append("operateInspectCost", getOperateInspectCost())
                .append("reviewCost", getReviewCost())
                .append("remarks", getRemarks())
                .append("deleteFlag", getDeleteFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("nextInspectDate", getNextInspectDate())
                .append("remindFlag", getRemindFlag())
                .append("totalCost", getTotalCost())
                .append("startTime", getStartTime())
                .append("finshTime", getFinshTime())
                .append("files",getFiles())
                .toString();
    }
}
