package com.dlzx.pfserverparts.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;

/**
 * 库存管理-物品对象 g_goods
 * 
 * @author dlzx
 * @date 2020-12-01
 */
public class GGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 商品编号 */
    @Excel(name = "商品编号")
    private String code;

    /** 商品类别ID-关联g_goods_type */
    @Excel(name = "商品类别ID-关联g_goods_type")
    private String goodsTypeId;

    /** 商品类别名称 */
    @Excel(name = "商品类别名称")
    private String goodsTypeName;

    /** 商品类别 类别 */
    @Excel(name = "商品类别类别（用来前端跳页和后段校验）")
    private Integer type;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String name;

    /** 规格 */
    @Excel(name = "规格")
    private String specification;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal unitPrice;

    /** 供货商 */
    @Excel(name = "供货商")
    private String supplier;

    /** 数量 */
    @Excel(name = "数量")
    private Long amount;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag;

    /** GGoodsType主键ID */
    @Excel(name = "类别名称")
    private String typeID;

    /** 状态 1启用 2禁用 */
    @Excel(name = "状态 1启用 2禁用")
    private Long status;

    /** 类别名称 */
    @Excel(name = "类别名称")
    private String typeName;

    /** 库存成本 */
    private String cost;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
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
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
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
    public void setAmount(Long amount) 
    {
        this.amount = amount;
    }

    public Long getAmount() 
    {
        return amount;
    }
    public void setDeleteFlag(Long deleteFlag) 
    {
        this.deleteFlag = deleteFlag;
    }

    public Long getDeleteFlag() 
    {
        return deleteFlag;
    }

    @Override
    public String toString() {
        return "GGoods{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", goodsTypeId='" + goodsTypeId + '\'' +
                ", goodsTypeName='" + goodsTypeName + '\'' +
                ", name='" + name + '\'' +
                ", specification='" + specification + '\'' +
                ", unit='" + unit + '\'' +
                ", unitPrice=" + unitPrice +
                ", supplier='" + supplier + '\'' +
                ", amount=" + amount +
                ", deleteFlag=" + deleteFlag +
                ", typeID='" + typeID + '\'' +
                ", status=" + status +
                ", typeName='" + typeName + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
