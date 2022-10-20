package com.dlzx.pfserverparts.mapper;


import com.dlzx.pfserverparts.domain.GGoodsReceivingRecordItem;

import java.util.List;

/**
 * 库存管理-物品领用记录-备件物品Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-10
 */
public interface GGoodsReceivingRecordItemMapper 
{
    /**
     * 查询库存管理-物品领用记录-备件物品
     * 
     * @param id 库存管理-物品领用记录-备件物品ID
     * @return 库存管理-物品领用记录-备件物品
     */
    public GGoodsReceivingRecordItem selectGGoodsReceivingRecordItemById(String id);

    /**
     * 批量插入
     * @param gGoodsReceivingRecordItems
     * @return
     */
    public void insertList(List<GGoodsReceivingRecordItem> gGoodsReceivingRecordItems);

    /**
     * 查询库存管理-物品领用记录-备件物品列表
     * 
     * @param gGoodsReceivingRecordItem 库存管理-物品领用记录-备件物品
     * @return 库存管理-物品领用记录-备件物品集合
     */
    public List<GGoodsReceivingRecordItem> selectGGoodsReceivingRecordItemList(GGoodsReceivingRecordItem gGoodsReceivingRecordItem);

    /**
     * 新增库存管理-物品领用记录-备件物品
     * 
     * @param gGoodsReceivingRecordItem 库存管理-物品领用记录-备件物品
     * @return 结果
     */
    public int insertGGoodsReceivingRecordItem(GGoodsReceivingRecordItem gGoodsReceivingRecordItem);

    /**
     * 修改库存管理-物品领用记录-备件物品
     * 
     * @param gGoodsReceivingRecordItem 库存管理-物品领用记录-备件物品
     * @return 结果
     */
    public int updateGGoodsReceivingRecordItem(GGoodsReceivingRecordItem gGoodsReceivingRecordItem);

    /**
     * 删除库存管理-物品领用记录-备件物品
     * 
     * @param id 库存管理-物品领用记录-备件物品ID
     * @return 结果
     */
    public int deleteGGoodsReceivingRecordItemById(String id);

    /**
     * 批量删除库存管理-物品领用记录-备件物品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGGoodsReceivingRecordItemByIds(String[] ids);

    /**
     * @describe 根据recordId 批量删除
     * @author DongCL
     * @date 2021-01-21 15:07
     * @param
     * @return
     */
    public int deleteGoodsItemByRecordId(String id);

    /**
     * @describe 获取备件列表
     * @author DongCL
     * @date 2021-03-01 15:11
     * @param
     * @return
     */
    List<GGoodsReceivingRecordItem> queryGGoodsReceivingRecordItemList(GGoodsReceivingRecordItem gGoodsReceivingRecordItem);
}
