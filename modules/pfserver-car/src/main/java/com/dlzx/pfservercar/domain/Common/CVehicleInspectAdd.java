package com.dlzx.pfservercar.domain.Common;

import com.dlzx.common.core.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ylh
 * @program:dlzx
 * @description：记录新增和编辑DTO
 * @create:2020-11-27 18-55
 */
public class CVehicleInspectAdd {


    /** 注册日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registerDate;

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String licensePlate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**备注*/
    private String remarks;

    /**司机id*/
    private String id;

    private String files;

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    /**检车日期*/
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date inspectDate;

    /**下一次检车日期*/
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date nextInspectDate;

    /**车检费用*/
    private BigDecimal vehiceInspectCost;

    /**运营年检费用*/
    private BigDecimal operateInspectCost;

    /**复检费用*/
    private BigDecimal reviewCost;

    @NotNull(message = "检车时间不能为空")
    public Date getInspectDate() {
        return inspectDate;
    }

    public void setInspectDate(Date inspectDate) {
        this.inspectDate = inspectDate;
    }

    @NotNull(message = "下次检车时间不能为空")
    public Date getNextInspectDate() {
        return nextInspectDate;
    }

    public void setNextInspectDate(Date nextInspectDate) {
        this.nextInspectDate = nextInspectDate;
    }

    public BigDecimal getVehiceInspectCost() {
        return vehiceInspectCost;
    }

    public void setVehiceInspectCost(BigDecimal vehiceInspectCost) {
        this.vehiceInspectCost = vehiceInspectCost;
    }

    public BigDecimal getOperateInspectCost() {
        return operateInspectCost;
    }

    public void setOperateInspectCost(BigDecimal operateInspectCost) {
        this.operateInspectCost = operateInspectCost;
    }

    public BigDecimal getReviewCost() {
        return reviewCost;
    }

    public void setReviewCost(BigDecimal reviewCost) {
        this.reviewCost = reviewCost;
    }
}
