package com.dlzx.pfservercar.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * ETC管理-ETC卡对象 etc_card
 *
 * @author dlzx
 * @date 2020-12-01
 */
public class EtcCard extends BaseEntity {
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
     * ETC卡号
     */
    @Excel(name = "ETC卡号")
    private String etcCardNumber;

    /**
     * 订单号
     */
    @Excel(name = "订单号")
    private String orderNumber;

    /**
     * 订单时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订单时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderTime;

    /**
     * 订单状态 1已发货 2未发货
     */
    @Excel(name = "订单状态 1已发货 2未发货")
    private Long status;

    /**
     * 领取方式
     */
    @Excel(name = "领取方式")
    private String method;

    /**
     * OBU号
     */
    @Excel(name = "OBU号")
    private String obu;

    /**
     * 删除状态 1正常 2删除
     */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag;

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

    @NotBlank(message = "车牌号不能为空")
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setEtcCardNumber(String etcCardNumber) {
        this.etcCardNumber = etcCardNumber;
    }

    @NotBlank(message = "卡号不能为空")
    public String getEtcCardNumber() {
        return etcCardNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }


    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setStatus(Long status) {
        this.status = status;
    }


    public Long getStatus() {
        return status;
    }

    public void setMethod(String method) {
        this.method = method;
    }


    public String getMethod() {
        return method;
    }

    public void setObu(String obu) {
        this.obu = obu;
    }


    public String getObu() {
        return obu;
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
                .append("etcCardNumber", getEtcCardNumber())
                .append("orderNumber", getOrderNumber())
                .append("orderTime", getOrderTime())
                .append("status", getStatus())
                .append("method", getMethod())
                .append("obu", getObu())
                .append("deleteFlag", getDeleteFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }





}
