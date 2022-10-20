package com.dlzx.system.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 库存管理-盘点对象 g_goods_check
 * 
 * @author dlzx
 * @date 2020-12-03
 */
public class GGoodsCheck extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 盘点编号 */
    @Excel(name = "盘点编号")
    private String code;

    /** 商品类别ID-关联g_goods_type */
    @Excel(name = "商品类别ID-关联g_goods_type")
    private String goodsTypeId;

    /** 商品类别名称 */
    @Excel(name = "商品类别名称")
    private String goodsTypeName;

    /** 状态 1未提交 2已提交 */
    @Excel(name = "状态 1未提交 2已提交")
    private Long status;

    private String statusEmnu;
    // 开始时间
    private String beginTimes;
    // 结束时间
    private String endTimes;

    /** 删除状态 1正常 2删除 */
    @Excel(name = "删除状态 1正常 2删除")
    private Long deleteFlag;


    /** 物品名称 */
    @Excel(name = "物品名称")
    private String goodName;


    /** 盘点项集合*/
    private List<GGoodsCheckItem> gGoodsCheckItems;

    public List<GGoodsCheckItem> getgGoodsCheckItems() {
        return gGoodsCheckItems;
    }

    public void setgGoodsCheckItems(List<GGoodsCheckItem> gGoodsCheckItems) {
        this.gGoodsCheckItems = gGoodsCheckItems;
    }

    public String getBeginTimes() {
        return beginTimes;
    }

    public void setBeginTimes(String beginTimes) {
        this.beginTimes = beginTimes;
    }

    public String getEndTimes() {
        return endTimes;
    }

    public void setEndTimes(String endTimes) {
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
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }
    public void setDeleteFlag(Long deleteFlag)
    {
        this.deleteFlag = deleteFlag;
    }

    public Long getDeleteFlag()
    {
        return deleteFlag;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("code", getCode())
                .append("goodsTypeId", getGoodsTypeId())
                .append("goodsTypeName", getGoodsTypeName())
                .append("status", getStatus())
                .append("deleteFlag", getDeleteFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("beginTimes", getBeginTimes())
                .append("endTimes", getEndTimes())
                .toString();
    }

    public String getStatusEmnu() {
        return statusEmnu;
    }

    public void setStatusEmnu(String statusEmnu) {
        this.statusEmnu = statusEmnu;
    }
}
