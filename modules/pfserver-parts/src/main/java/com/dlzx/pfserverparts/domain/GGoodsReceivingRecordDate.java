package com.dlzx.pfserverparts.domain;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 库存管理-物品领用记录-领取日期对象 g_goods_receiving_record_date
 * 
 * @author guo
 * @date 2020-12-11
 */
public class GGoodsReceivingRecordDate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 记录ID 关联g_goods_receiving_record表 */
    @Excel(name = "记录ID 关联g_goods_receiving_record表")
    private String recordId;

    /** 领取日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "领取日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receiveDate;

    /** 车牌号码 */
    @Excel(name = "车牌号码")
    private String licensePlate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String toString() {
        return "GGoodsReceivingRecordDate{" +
                "id='" + id + '\'' +
                ", recordId='" + recordId + '\'' +
                ", receiveDate=" + receiveDate +
                ", licensePlate='" + licensePlate + '\'' +
                '}';
    }
}
