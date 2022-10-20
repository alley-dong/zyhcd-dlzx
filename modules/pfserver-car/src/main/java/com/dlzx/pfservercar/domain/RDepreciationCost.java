package com.dlzx.pfservercar.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * 统计报-折旧费用统计对象 r_depreciation_cost
 *
 * @author dlzx
 * @date 2020-12-16
 */
public class RDepreciationCost extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 车辆XUY */
//    @Excel(name = "车辆序号")
    private String vehicleId;

    /** 车牌号码 */
    @Excel(name = "车牌号")
    private String licensePlate;

    /** 折旧费*/
    @Excel(name = "折旧费")
    private BigDecimal depreciationCost;

    /** 折旧月份 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "折旧月份", width = 30, dateFormat = "yyyy-MM-dd")
    private Date depreciationMonth;

    /** 合计折旧费用 */
    private BigDecimal countDepreciationCost;

    /** 折旧月份开始日期 */
    @JsonFormat(pattern = "yyyy-MM")
    private Date beginDepreciationMonth;

    /** 折旧月份开始结束 */
    @JsonFormat(pattern = "yyyy-MM")
    private Date endDepreciationMonth;

    /** 删除状态 1正常 2删除 */
    private Long deleteFlag=1L;

    /** 车牌号码(集合) */
    private List<String> licensePlates;

    public List<String> getLicensePlates() {
        return licensePlates;
    }

    public void setLicensePlates(List<String> licensePlates) {
        this.licensePlates = licensePlates;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public BigDecimal getDepreciationCost() {
        return depreciationCost;
    }

    public void setDepreciationCost(BigDecimal depreciationCost) {
        this.depreciationCost = depreciationCost;
    }

    public Date getDepreciationMonth() {
        return depreciationMonth;
    }

    public void setDepreciationMonth(Date depreciationMonth) {
        this.depreciationMonth = depreciationMonth;
    }

    public BigDecimal getCountDepreciationCost() {
        return countDepreciationCost;
    }

    public void setCountDepreciationCost(BigDecimal countDepreciationCost) {
        this.countDepreciationCost = countDepreciationCost;
    }

    public Date getBeginDepreciationMonth() {
        return beginDepreciationMonth;
    }

    public void setBeginDepreciationMonth(Date beginDepreciationMonth) {
        this.beginDepreciationMonth = beginDepreciationMonth;
    }

    public Date getEndDepreciationMonth() {
        return endDepreciationMonth;
    }

    public void setEndDepreciationMonth(Date endDepreciationMonth) {
        this.endDepreciationMonth = endDepreciationMonth;
    }

    public Long getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Long deleteFlag) {
        this.deleteFlag = deleteFlag;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("depreciationMonth", getDepreciationMonth())
                .append("licensePlate", getLicensePlate())
                .append("vehicleId", getVehicleId())
                .append("depreciationCost", getDepreciationCost())
                .append("deleteFlag", getDeleteFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}