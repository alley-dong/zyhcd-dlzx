package com.dlzx.pfservercar.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * ETC管理-ETC卡使用记录对象 etc_card_usage_record
 *
 * @author dlzx
 * @date 2020-12-02
 */
public class EtcCardUsageRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 车辆ID
     */
//    @Excel(name = "车辆ID")
    private String vehicleId;

    /**
     * 车牌号
     */
    @Excel(name = "车牌号")
    private String licensePlate;

    /**
     * ETC卡ID
     */
//    @Excel(name = "ETC卡ID")
    private String etcCardId;

    /**
     * ETC卡号
     */
    @Excel(name = "ETC卡号")
    private String etcCardNumber;

    /**
     * 消费金额
     */
    @Excel(name = "消费金额")
    private BigDecimal amount;

    /**
     * 入站时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "入站时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date inboundTime;

    /**
     * 出站时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "出站时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date outboundTime;

    /**
     * 路段地点
     */
    @Excel(name = "路段地点")
    private String roadSection;

    /**
     * 删除状态 1正常 2删除
     */
//    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag;

    private String taskId;

    private Date firstTime;

    private Date finishTime;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setEtcCardId(String etcCardId) {
        this.etcCardId = etcCardId;
    }

    public String getEtcCardId() {
        return etcCardId;
    }

    public void setEtcCardNumber(String etcCardNumber) {
        this.etcCardNumber = etcCardNumber;
    }

    public String getEtcCardNumber() {
        return etcCardNumber;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setInboundTime(Date inboundTime) {
        this.inboundTime = inboundTime;
    }

    public Date getInboundTime() {
        return inboundTime;
    }

    public void setOutboundTime(Date outboundTime) {
        this.outboundTime = outboundTime;
    }

    public Date getOutboundTime() {
        return outboundTime;
    }

    public void setRoadSection(String roadSection) {
        this.roadSection = roadSection;
    }

    public String getRoadSection() {
        return roadSection;
    }

    public void setDeleteFlag(Long deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Long getDeleteFlag() {
        return deleteFlag;
    }

    public void setFirstTime(Date firstTime) {
        this.firstTime = firstTime;
    }

    public Date getFirstTime() {
        return firstTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("vehicleId", getVehicleId())
                .append("licensePlate", getLicensePlate())
                .append("etcCardId", getEtcCardId())
                .append("etcCardNumber", getEtcCardNumber())
                .append("amount", getAmount())
                .append("inboundTime", getInboundTime())
                .append("outboundTime", getOutboundTime())
                .append("firstTime", getFirstTime())
                .append("finishTime", getFinishTime())
                .append("roadSection", getRoadSection())
                .append("deleteFlag", getDeleteFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("taskId", getTaskId())
                .toString();
    }


    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
