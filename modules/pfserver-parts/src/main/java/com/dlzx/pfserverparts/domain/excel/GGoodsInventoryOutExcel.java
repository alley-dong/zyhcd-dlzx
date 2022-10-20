package com.dlzx.pfserverparts.domain.excel;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class GGoodsInventoryOutExcel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 类型 1出库 2入库 */
    private Long type;

    /** 入库单号 */
    @Excel(name = "出库单号")
    private String code;

    /** 出入库时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "出库时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date inventoryTime;

    /** 商品类别 */
    @Excel(name = "物品类别")
    private String goodsTypeName;

    /** 商品编号 */
    @Excel(name = "物品编号")
    private String goodsCode;

    /** 商品名称 */
    @Excel(name = "物品名称")
    private String goodsName;

    /** 规格 */
    @Excel(name = "规格")
    private String specification;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 单价 */
    @Excel(name = "单价（元）")
    private BigDecimal unitPrice;

    /** 出入库数量 */
    @Excel(name = "出库数量")
    private Long inventoryNumber;

    /** 出入库数量 */
    @Excel(name = "金额（元）")
    private BigDecimal totalMoney;

    /** 类型 类型 1.正常入库2.盘点入库3.手动出库4.领用出库5.盘点出库6.报废出库 */
    @Excel(name = "出库类别", readConverterExp = "1=正常入库,2=盘点入库,3=手动出库,4=领用出库,5=盘点出库,6=报废出库")
    private Long inventoryType;

    /** 供应商 */
    @Excel(name = "供应商")
    private String supplier;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String licensePlate;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    @Excel(name = "操作员")
    private String createBy;



    /** 商品类别ID-关联g_goods_type */
    private String goodsTypeId;

    /** 商品ID */
    private String goodsId;

    // 开始时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginTimes;
    // 结束时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTimes;

    /** 出入库数量 */
    private int inventoryTotalQuantity;

    /** 出入库数量 */
    private int inventoryOutTotalQuantity;

    /** 删除状态 1正常 2删除 */
    private Long deleteFlag = 1L;

    /** 明细查询类型 */
    private int detailType;

    @Override
    public String getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public int getDetailType() {
        return detailType;
    }

    public void setDetailType(int detailType) {
        this.detailType = detailType;
    }

    public Date getBeginTimes() {
        return beginTimes;
    }

    public void setBeginTimes(Date beginTimes) {
        this.beginTimes = beginTimes;
    }

    public Date getEndTimes() {
        return endTimes;
    }

    public void setEndTimes(Date endTimes) {
        this.endTimes = endTimes;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setType(Long type)
    {
        this.type = type;
    }

    public Long getType()
    {
        return type;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void setInventoryType(Long inventoryType)
    {
        this.inventoryType = inventoryType;
    }

    public Long getInventoryType()
    {
        return inventoryType;
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
    public void setGoodsCode(String goodsCode)
    {
        this.goodsCode = goodsCode;
    }

    public String getGoodsCode()
    {
        return goodsCode;
    }
    public void setGoodsName(String goodsName)
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName()
    {
        return goodsName;
    }
    public void setSpecification(String specification)
    {
        this.specification = specification;
    }

    public String getSpecification()
    {
        return specification;
    }
    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public String getUnit()
    {
        return unit;
    }
    public void setUnitPrice(BigDecimal unitPrice)
    {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitPrice()
    {
        return unitPrice;
    }
    public void setSupplier(String supplier)
    {
        this.supplier = supplier;
    }

    public String getSupplier()
    {
        return supplier;
    }
    public void setInventoryTime(Date inventoryTime)
    {
        this.inventoryTime = inventoryTime;
    }

    public Date getInventoryTime()
    {
        return inventoryTime;
    }
    public void setInventoryNumber(Long inventoryNumber)
    {
        this.inventoryNumber = inventoryNumber;
    }

    public Long getInventoryNumber()
    {
        return inventoryNumber;
    }
    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }

    public String getRemarks()
    {
        return remarks;
    }
    public void setDeleteFlag(Long deleteFlag)
    {
        this.deleteFlag = deleteFlag;
    }

    public Long getDeleteFlag()
    {
        return deleteFlag;
    }

    public int getInventoryTotalQuantity() {
        return inventoryTotalQuantity;
    }

    public void setInventoryTotalQuantity(int inventoryTotalQuantity) {
        this.inventoryTotalQuantity = inventoryTotalQuantity;
    }

    public int getInventoryOutTotalQuantity() {
        return inventoryOutTotalQuantity;
    }

    public void setInventoryOutTotalQuantity(int inventoryOutTotalQuantity) {
        this.inventoryOutTotalQuantity = inventoryOutTotalQuantity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}