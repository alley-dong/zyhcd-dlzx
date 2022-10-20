package com.dlzx.job.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 库存管理-物品领用记录-备件物品对象 g_goods_receiving_record_item
 * 
 * @author dlzx
 * @date 2020-12-10
 */
public class GGoodsReceivingRecordItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 记录ID 关联g_goods_receiving_record表 */
    @Excel(name = "记录ID 关联g_goods_receiving_record表")
    private String recordId;

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

    /** 领用数量 */
    @Excel(name = "领用数量")
    private Long number;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setRecordId(String recordId) 
    {
        this.recordId = recordId;
    }

    public String getRecordId() 
    {
        return recordId;
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
    public void setNumber(Long number) 
    {
        this.number = number;
    }

    public Long getNumber() 
    {
        return number;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("recordId", getRecordId())
            .append("goodsTypeId", getGoodsTypeId())
            .append("goodsTypeName", getGoodsTypeName())
            .append("goodsId", getGoodsId())
            .append("goodsName", getGoodsName())
            .append("number", getNumber())
            .toString();
    }
}
