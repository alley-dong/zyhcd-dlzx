package com.dlzx.pfservercar.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 安全管理-违章记录对象 safe_traffic_transgression
 *
 * @author dlzx
 * @date 2020-12-10
 */
public class SafeTrafficTransgression extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 车辆ID 关联c_vehicle表
     */
    @Excel(name = "车辆ID 关联c_vehicle表")
    private String vehicleId;

    /**
     * 车牌号
     */
    @Excel(name = "车牌号")
    private String licensePlate;

    /**
     * 驾驶员ID 关联d_driver表
     */
    @Excel(name = "驾驶员ID 关联d_driver表")
    private String driverId;

    /**
     * 驾驶员姓名
     */
    @Excel(name = "驾驶员姓名")
    private String driverName;

    /**
     * 违章时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name = "违章时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date wzsj;

    /**
     * 违章地址
     */

    @Excel(name = "违章地址")
    private String wzdd;

    /**
     * 违章行为
     */
    @Excel(name = "违章行为")
    private String wzxw;

    /**
     * 违章罚款
     */
    @Excel(name = "违章罚款")
    private String wzfk;

    /**
     * 违章记分
     */
    @Excel(name = "违章记分")
    private String wzjf;

    /**
     * 城市名称
     */
    @Excel(name = "城市名称")
    private String csmc;

    /**
     * 城市代码
     */
    @Excel(name = "城市代码")
    private String csdm;

    /**
     * 违法代码
     */
    @Excel(name = "违法代码")
    private String wfdm;

    /**
     * 采集机构
     */
    @Excel(name = "采集机构")
    private String cjjg;

    /**
     * 决定书编号
     */
    @Excel(name = "决定书编号")
    private String jdsbh;

    /**
     * 监控编号
     */
    @Excel(name = "监控编号")
    private String jkbh;

    /**
     * 订单编号
     */
    @Excel(name = "订单编号")
    private String orderOn;

    /**
     * 违章状态 1未处理 2已处理
     */
    @Excel(name = "违章状态 1未处理 2已处理")
    private Long status;

    /**
     * 处理违章截至时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "处理违章截至时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deadline;

    /**
     * 删除状态 1正常 2删除
     */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag = 1L;
    //违章文件
    private List<SysFile> fileList;
    //违章图片
    private List<String> photoUrls;

    /**
     * 完成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date completeTime;

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

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setWzsj(Date wzsj) {
        this.wzsj = wzsj;
    }

    public Date getWzsj() {
        return wzsj;
    }

    public void setWzdd(String wzdd) {
        this.wzdd = wzdd;
    }

    public String getWzdd() {
        return wzdd;
    }

    public void setWzxw(String wzxw) {
        this.wzxw = wzxw;
    }

    public String getWzxw() {
        return wzxw;
    }

    public void setWzfk(String wzfk) {
        this.wzfk = wzfk;
    }

    public String getWzfk() {
        return wzfk;
    }

    public void setWzjf(String wzjf) {
        this.wzjf = wzjf;
    }

    public String getWzjf() {
        return wzjf;
    }

    public void setCsmc(String csmc) {
        this.csmc = csmc;
    }

    public String getCsmc() {
        return csmc;
    }

    public void setCsdm(String csdm) {
        this.csdm = csdm;
    }

    public String getCsdm() {
        return csdm;
    }

    public void setWfdm(String wfdm) {
        this.wfdm = wfdm;
    }

    public String getWfdm() {
        return wfdm;
    }

    public void setCjjg(String cjjg) {
        this.cjjg = cjjg;
    }

    public String getCjjg() {
        return cjjg;
    }

    public void setJdsbh(String jdsbh) {
        this.jdsbh = jdsbh;
    }

    public String getJdsbh() {
        return jdsbh;
    }

    public void setJkbh(String jkbh) {
        this.jkbh = jkbh;
    }

    public String getJkbh() {
        return jkbh;
    }

    public void setOrderOn(String orderOn) {
        this.orderOn = orderOn;
    }

    public String getOrderOn() {
        return orderOn;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getStatus() {
        return status;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeleteFlag(Long deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Long getDeleteFlag() {
        return deleteFlag;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public List<SysFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<SysFile> fileList) {
        this.fileList = fileList;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("vehicleId", getVehicleId())
                .append("licensePlate", getLicensePlate())
                .append("driverId", getDriverId())
                .append("driverName", getDriverName())
                .append("wzsj", getWzsj())
                .append("wzdd", getWzdd())
                .append("wzxw", getWzxw())
                .append("wzfk", getWzfk())
                .append("wzjf", getWzjf())
                .append("csmc", getCsmc())
                .append("csdm", getCsdm())
                .append("wfdm", getWfdm())
                .append("cjjg", getCjjg())
                .append("jdsbh", getJdsbh())
                .append("jkbh", getJkbh())
                .append("orderOn", getOrderOn())
                .append("status", getStatus())
                .append("deadline", getDeadline())
                .append("deleteFlag", getDeleteFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("completeTime", getCompleteTime())
                .append("fileList", getFileList())
                .toString();
    }


}
