package com.dlzx.system.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 库存管理-盘点项对象 g_goods_check_item
 * 
 * @author dlzx
 * @date 2020-12-03
 */
public class GGoodsCheckItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 盘点ID 关联g_goods_check */
    @Excel(name = "盘点ID 关联g_goods_check")
    private String checkId;

    /** 商品ID */
    @Excel(name = "商品ID")
    private String goodsId;

    /** 商品编号 */
    @Excel(name = "商品编号")
    private String goodsCode;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 规格 */
    @Excel(name = "规格")
    private String specification;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 当前库存量 */
    @Excel(name = "当前库存量")
    private Long currentQuantity;

    /** 盘点库存量 */
    @Excel(name = "盘点库存量")
    private Long checkQuantity;

    /** 盈亏 1盘盈 2盘亏 */
    @Excel(name = "盈亏 1盘盈 2盘亏")
    private Long status;

    /** 差异数量 */
    @Excel(name = "差异数量")
    private Long difference;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setCheckId(String checkId) 
    {
        this.checkId = checkId;
    }

    public String getCheckId() 
    {
        return checkId;
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
    public void setCurrentQuantity(Long currentQuantity) 
    {
        this.currentQuantity = currentQuantity;
    }

    public Long getCurrentQuantity() 
    {
        return currentQuantity;
    }
    public void setCheckQuantity(Long checkQuantity) 
    {
        this.checkQuantity = checkQuantity;
    }

    public Long getCheckQuantity() 
    {
        return checkQuantity;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setDifference(Long difference) 
    {
        this.difference = difference;
    }

    public Long getDifference() 
    {
        return difference;
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
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("checkId", getCheckId())
            .append("goodsId", getGoodsId())
            .append("goodsCode", getGoodsCode())
            .append("goodsName", getGoodsName())
            .append("specification", getSpecification())
            .append("unit", getUnit())
            .append("currentQuantity", getCurrentQuantity())
            .append("checkQuantity", getCheckQuantity())
            .append("status", getStatus())
            .append("difference", getDifference())
            .append("deleteFlag", getDeleteFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
