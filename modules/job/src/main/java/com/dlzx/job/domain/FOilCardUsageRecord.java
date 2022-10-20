package com.dlzx.job.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 燃料管理-油卡使用记录对象 f_oil_card_usage_record
 *
 * @author dlzx
 * @date 2020-12-02
 */
public class FOilCardUsageRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 车辆ID
     */
    @Excel(name = "车辆ID")
    private String vehicleId;

    /**
     * 车牌号
     */
    @Excel(name = "车牌号")
    private String licensePlate;

    /**
     * 油卡ID
     */
    @Excel(name = "油卡ID")
    private String oilCardId;

    /**
     * 油卡卡号
     */
    @Excel(name = "卡号")
    private String oilCardNumber;

    /**
     * 消费金额
     */
    @Excel(name = "金额")
    private BigDecimal consume;

    /**
     * 剩余余额
     */
    @Excel(name = "余额")
    private BigDecimal surplus;

    /**
     * 单位
     */
    @Excel(name = "单位")
    private String unit;

    /**
     * 交易状态
     */
    @Excel(name = "交易状态")
    private String transactionStatus;

    /**
     * 交易时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "交易时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date transactionTime;

    /**
     * 交易地址
     */
    @Excel(name = "交易地址")
    private String transactionAddress;

    /**
     * 商品类别
     */
    @Excel(name = "商品类别")
    private String productType;

    /**
     * 商品名称
     */
    @Excel(name = "商品名称")
    private String productName;

    /**
     * 油量
     */
    @Excel(name = "油量")
    private BigDecimal oilQuantity;

    /**
     * 状态 1异常 2正常
     */
    @Excel(name = "状态 1异常 2正常")
    private Long status;

    /**
     * 异常类型 1非定点加油站 2 24小时内连续加油
     */
    @Excel(name = "异常类型 1非定点加油站 2 24小时内连续加油")
    private String exceptionType;

    /**
     * 删除状态 1正常 2删除
     */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag = 1L;

    /**
     * 线路ID
     */
    @Excel(name = "线路ID")
    private String routeId;

    /**
     * 交易结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交易结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date transactionEndTime;

    private Long oilNum;

    public Date getTransactionEndTime() {
        return transactionEndTime;
    }

    public void setTransactionEndTime(Date transactionEndTime) {
        this.transactionEndTime = transactionEndTime;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getTransactionAddress() {
        return transactionAddress;
    }

    public void setTransactionAddress(String transactionAddress) {
        this.transactionAddress = transactionAddress;
    }

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

    public void setOilCardId(String oilCardId) {
        this.oilCardId = oilCardId;
    }

    public String getOilCardId() {
        return oilCardId;
    }

    public void setOilCardNumber(String oilCardNumber) {
        this.oilCardNumber = oilCardNumber;
    }

    public String getOilCardNumber() {
        return oilCardNumber;
    }

    public void setConsume(BigDecimal consume) {
        this.consume = consume;
    }

    public BigDecimal getConsume() {
        return consume;
    }

    public void setSurplus(BigDecimal surplus) {
        this.surplus = surplus;
    }

    public BigDecimal getSurplus() {
        return surplus;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setOilQuantity(BigDecimal oilQuantity) {
        this.oilQuantity = oilQuantity;
    }

    public BigDecimal getOilQuantity() {
        return oilQuantity;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setDeleteFlag(Long deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Long getDeleteFlag() {
        return deleteFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("vehicleId", getVehicleId())
                .append("licensePlate", getLicensePlate())
                .append("oilCardId", getOilCardId())
                .append("oilCardNumber", getOilCardNumber())
                .append("consume", getConsume())
                .append("surplus", getSurplus())
                .append("unit", getUnit())
                .append("transactionStatus", getTransactionStatus())
                .append("transactionTime", getTransactionTime())
                .append("productType", getProductType())
                .append("productName", getProductName())
                .append("oilQuantity", getOilQuantity())
                .append("status", getStatus())
                .append("exceptionType", getExceptionType())
                .append("deleteFlag", getDeleteFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("oilNum", getOilNum())
                .toString();
    }

    public Long getOilNum() {
        return oilNum;
    }

    public void setOilNum(Long oilNum) {
        this.oilNum = oilNum;
    }
}
