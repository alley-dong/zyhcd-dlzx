package com.dlzx.pfservercar.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * ETC管理-ETC卡对象 etc_card
 *
 * @author dlzx
 * @date 2020-12-01
 */
public class EtcCardImport extends BaseEntity {
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


    @Excel(name = "订单时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dingDanTime;

    @Excel(name = "订单状态 (已发货/未发货)")
    private String dingDanStatus;
    /**
     * 订单时间
     */
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "订单时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderTime;

    /**
     * 订单状态 1已发货 2未发货
     */

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

    @NotBlank(message = "订单号不能为空")
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

    @NotNull(message = "订单状态不能为空")
    public Long getStatus() {
        return status;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @NotNull(message = "领取方式不能为空")
    public String getMethod() {
        return method;
    }

    public void setObu(String obu) {
        this.obu = obu;
    }

    @NotBlank(message = "OBU号不能为空")
    public String getObu() {
        return obu;
    }

    public void setDeleteFlag(Long deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Long getDeleteFlag() {
        return deleteFlag;
    }

    public void setDingDanTime(Date dingDanTime) {
        this.dingDanTime = dingDanTime;
    }

    public Date getDingDanTime() {
        return dingDanTime;
    }

    public void setDingDanStatus(String dingDanStatus) {
        this.dingDanStatus = dingDanStatus;
    }

    public String getDingDanStatus() {
        return dingDanStatus;
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
                .append("dingDanTime", getDingDanTime())
                .append("dingDanStatus", getDingDanStatus())
                .toString();
    }

    public boolean equals() {
        String str = new StringBuilder()
                .append(getId() != null ? getId() : "")
                .append(getLicensePlate() != null ? getLicensePlate() : "")
                .append(getEtcCardNumber() != null ? getEtcCardNumber() : "")
                .append(getOrderNumber() != null ? getOrderNumber() : "")
                .append(getOrderTime() != null ? getOrderTime() : "")
                .append(getStatus() != null ? getStatus() : "")
                .append(getMethod() != null ? getMethod() : "")
                .append(getObu() != null ? getObu() : "")
                .append(getDeleteFlag() != null ? getDeleteFlag() : "")
                .append(getCreateTime() != null ? getCreateTime() : "")
                .append(getUpdateTime() != null ? getUpdateTime() : "")
                .append(getDingDanTime() != null ? getDingDanTime() : "")
                .append(getDingDanStatus() != null ? getDingDanStatus() : "")
                .toString();
        boolean flag = ("".equals(str.trim()) || str.trim().length() == 0);
//        System.out.println("数据长度:"+str+""+flag);
        return flag;


    }
}
