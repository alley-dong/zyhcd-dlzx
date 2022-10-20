package com.dlzx.pfservercar.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 库存管理-物品领用记录对象 g_goods_receiving_record
 * 
 * @author dlzx
 * @date 2020-12-09
 */
public class GGoodsReceivingRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 物品类别ID */
    @Excel(name = "物品类别ID")
    private String goodsTypeId;

    /** 物品类别名称 */
    @Excel(name = "物品类别名称")
    private String goodsTypeName;

    /** 物品ID */
    @Excel(name = "物品ID")
    private String goodsId;

    /** 物品名称 */
    @Excel(name = "物品名称")
    private String goodsName;

    /** 车辆ID 关联c_vehicle表 */
    @Excel(name = "车辆ID 关联c_vehicle表")
    private String vehicleId;

    /** 车牌号码 */
    @Excel(name = "车牌号码")
    private String licensePlate;

    /** 司机ID-关联d_driver表 */
    @Excel(name = "司机ID-关联d_driver表")
    private String driverId;

    /** 线路ID */
    @Excel(name = "线路ID")
    private String routeId;

    /** 线路名称 */
    @Excel(name = "线路名称")
    private String routeName;

    /** 规格 */
    @Excel(name = "规格")
    private String specification;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 领用数量 */
    @Excel(name = "领用数量")
    private Long number;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 用途 1维修 2换装 */
    @Excel(name = "用途 1维修 2换装")
    private Long type;

    /** 审核状态 1待审核 2已审核 3已驳回 */
    @Excel(name = "审核状态 1待审核 2已审核 3已驳回")
    private Long status;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reviewTime;

    /** 驾驶员端时间筛选项 */
    @JsonFormat(pattern = "yyyy-MM")
    private Date beginTimes;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTimes;

    /** 领用时间(管理员端时间筛选项) */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date receiveTime;

    /** 审核人 */
    @Excel(name = "审核人")
    private String reviewUser;

    /** 审核备注 */
    @Excel(name = "审核备注")
    private String reviewRemarks;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag;

    /** 用途 1维修 2换装 */
    private String typeEnum;

    /** 审核状态 1待审核 2已审核 3已驳回 */
    private String statusEnum;

    /** 审批人 */
    private String userid;




    /** 领用时间 */
    @Excel(name = "领用时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private List<Date> receiveDate;

    /** 物品ID */
    @Excel(name = "物品ID")
    private List<String> itGoodsId;

    /** 物品名称 */
    @Excel(name = "物品名称")
    private List<String> itGoodsName;

    /** 查询映射领用时间 */
    @Excel(name = "领用时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date receiveDates;

    private List<Date> receiveDateList;

    private List<String> receiveDateLists;


    /** 物品名称 */
    private List<GGoodsReceivingRecordItem> gGoodsReceivingRecordItems;

    /** 时间列表 */
    List<GGoodsReceivingRecordDate> gGoodsReceivingRecordDates;


    private Long speNum;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setGoodsTypeId(String goodsTypeId)
    {
        this.goodsTypeId = goodsTypeId;
    }

    public String getGoodsTypeId()
    {
        return goodsTypeId;
    }
    public void setGoodsTypeName(String goodsTypeName)
    {
        this.goodsTypeName = goodsTypeName;
    }

    public String getGoodsTypeName()
    {
        return goodsTypeName;
    }
    public void setGoodsId(String goodsId)
    {
        this.goodsId = goodsId;
    }

    public String getGoodsId()
    {
        return goodsId;
    }
    public void setGoodsName(String goodsName)
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName()
    {
        return goodsName;
    }
    public void setVehicleId(String vehicleId)
    {
        this.vehicleId = vehicleId;
    }

    public String getVehicleId()
    {
        return vehicleId;
    }
    public void setLicensePlate(String licensePlate)
    {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate()
    {
        return licensePlate;
    }
    public void setDriverId(String driverId)
    {
        this.driverId = driverId;
    }

    public String getDriverId()
    {
        return driverId;
    }
    public void setRouteId(String routeId)
    {
        this.routeId = routeId;
    }

    public String getRouteId()
    {
        return routeId;
    }
    public void setRouteName(String routeName)
    {
        this.routeName = routeName;
    }

    public String getRouteName()
    {
        return routeName;
    }
    public void setSpecification(String specification)
    {
        this.specification = specification;
    }

    public String getSpecification()
    {
        return specification;
    }
    public void setNumber(Long number)
    {
        this.number = number;
    }

    public Long getNumber()
    {
        return number;
    }
    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }

    public String getRemarks()
    {
        return remarks;
    }
    public void setType(Long type)
    {
        this.type = type;
    }

    public Long getType()
    {
        return type;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }
    public void setReviewTime(Date reviewTime)
    {
        this.reviewTime = reviewTime;
    }

    public Date getReviewTime()
    {
        return reviewTime;
    }
    public void setReviewUser(String reviewUser)
    {
        this.reviewUser = reviewUser;
    }

    public String getReviewUser()
    {
        return reviewUser;
    }
    public void setReviewRemarks(String reviewRemarks)
    {
        this.reviewRemarks = reviewRemarks;
    }

    public String getReviewRemarks()
    {
        return reviewRemarks;
    }
    public void setDeleteFlag(Long deleteFlag)
    {
        this.deleteFlag = deleteFlag;
    }

    public Long getDeleteFlag()
    {
        return deleteFlag;
    }

    /** 驾驶员名称 */
    @Excel(name = "驾驶员名称")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    /** 记录ID 关联g_goods_receiving_record表 */
    @Excel(name = "记录ID 关联g_goods_receiving_record表")
    private String recordId;

    /** 物品类别ID */
    @Excel(name = "物品类别ID")
    private String iGoodsTypeId;

    /** 物品类别名称 */
    @Excel(name = "物品类别名称")
    private String iGoodsTypeName;

    /** 物品ID */
    @Excel(name = "物品ID")
    private String iGoodsId;

    /** 物品名称 */
    @Excel(name = "物品名称")
    private String iGoodsName;

    /** 领用数量 */
    @Excel(name = "领用数量")
    private Long iNumber;

    /** 库存数量 */
    @Excel(name = "库存数量")
    private Long amount;

    /** 类型  1尿素 2工作餐 3轮胎 4 低值易耗品 5备件 */
    private Integer goodsType;

    public Date getBeginTimes() {
        return beginTimes;
    }

    public void setBeginTimes(Date beginTimes) {
        this.beginTimes = beginTimes;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public String getiGoodsTypeId() {
        return iGoodsTypeId;
    }

    public void setiGoodsTypeId(String iGoodsTypeId) {
        this.iGoodsTypeId = iGoodsTypeId;
    }

    public String getiGoodsTypeName() {
        return iGoodsTypeName;
    }

    public void setiGoodsTypeName(String iGoodsTypeName) {
        this.iGoodsTypeName = iGoodsTypeName;
    }

    public String getiGoodsId() {
        return iGoodsId;
    }

    public void setiGoodsId(String iGoodsId) {
        this.iGoodsId = iGoodsId;
    }

    public String getiGoodsName() {
        return iGoodsName;
    }

    public void setiGoodsName(String iGoodsName) {
        this.iGoodsName = iGoodsName;
    }

    public Long getiNumber() {
        return iNumber;
    }

    public void setiNumber(Long iNumber) {
        this.iNumber = iNumber;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        if (amount == null){
            amount = 0L;
        }
        this.amount = amount;
    }


    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(String typeEnum) {
        this.typeEnum = typeEnum;
    }

    public String getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(String statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public List<Date> getReceiveDateList() {
        return receiveDateList;
    }

    public void setReceiveDateList(List<Date> receiveDateList) {
        this.receiveDateList = receiveDateList;
    }

    public List<String> getReceiveDateLists() {
        return receiveDateLists;
    }

    public void setReceiveDateLists(List<String> receiveDateLists) {
        this.receiveDateLists = receiveDateLists;
    }

    public List<GGoodsReceivingRecordItem> getgGoodsReceivingRecordItems() {
        return gGoodsReceivingRecordItems;
    }

    public void setgGoodsReceivingRecordItems(List<GGoodsReceivingRecordItem> gGoodsReceivingRecordItems) {
        this.gGoodsReceivingRecordItems = gGoodsReceivingRecordItems;
    }

    public List<GGoodsReceivingRecordDate> getgGoodsReceivingRecordDates() {
        return gGoodsReceivingRecordDates;
    }

    public void setgGoodsReceivingRecordDates(List<GGoodsReceivingRecordDate> gGoodsReceivingRecordDates) {
        this.gGoodsReceivingRecordDates = gGoodsReceivingRecordDates;
    }

    public List<Date> getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(List<Date> receiveDate) {
        this.receiveDate = receiveDate;
    }

    public List<String> getItGoodsId() {
        return itGoodsId;
    }

    public void setItGoodsId(List<String> itGoodsId) {
        this.itGoodsId = itGoodsId;
    }

    public List<String> getItGoodsName() {
        return itGoodsName;
    }

    public void setItGoodsName(List<String> itGoodsName) {
        this.itGoodsName = itGoodsName;
    }

    public Date getReceiveDates() {
        return receiveDates;
    }

    public void setReceiveDates(Date receiveDates) {
        this.receiveDates = receiveDates;
    }

    public Long getSpeNum() {
        return speNum;
    }

    public void setSpeNum(Long speNum) {
        this.speNum = speNum;
    }

    public Date getEndTimes() {
        return endTimes;
    }

    public void setEndTimes(Date endTimes) {
        this.endTimes = endTimes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("goodsTypeId", getGoodsTypeId())
                .append("goodsTypeName", getGoodsTypeName())
                .append("goodsId", getGoodsId())
                .append("goodsName", getGoodsName())
                .append("vehicleId", getVehicleId())
                .append("licensePlate", getLicensePlate())
                .append("driverId", getDriverId())
                .append("routeId", getRouteId())
                .append("routeName", getRouteName())
                .append("specification", getSpecification())
                .append("number", getNumber())
                .append("remarks", getRemarks())
                .append("type", getType())
                .append("status", getStatus())
                .append("reviewTime", getReviewTime())
                .append("reviewUser", getReviewUser())
                .append("reviewRemarks", getReviewRemarks())
                .append("deleteFlag", getDeleteFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())

                .append("speNum", getSpeNum())
                .toString();
    }


}
