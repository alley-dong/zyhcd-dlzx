package com.dlzx.pfservercar.service;

import java.util.List;
import com.dlzx.pfservercar.domain.GGoodsReceivingRecord;

/**
 * 库存管理-物品领用记录Service接口
 * 
 * @author dlzx
 * @date 2020-12-16
 */
public interface IGGoodsReceivingRecordService 
{
    /**
     * 查询库存管理-物品领用记录
     * 
     * @param id 库存管理-物品领用记录ID
     * @return 库存管理-物品领用记录
     */
    public GGoodsReceivingRecord selectGGoodsReceivingRecordById(String id);

    /**
     * 查询库存管理-物品领用记录列表
     * 
     * @param gGoodsReceivingRecord 库存管理-物品领用记录
     * @return 库存管理-物品领用记录集合
     */
    public List<GGoodsReceivingRecord> selectGGoodsReceivingRecordList(GGoodsReceivingRecord gGoodsReceivingRecord);

    public List<GGoodsReceivingRecord> selectGGoodsReceivingRecordListNum(GGoodsReceivingRecord gGoodsReceivingRecord);
    /**
     * 新增库存管理-物品领用记录
     * 
     * @param gGoodsReceivingRecord 库存管理-物品领用记录
     * @return 结果
     */
    public int insertGGoodsReceivingRecord(GGoodsReceivingRecord gGoodsReceivingRecord);

    /**
     * 修改库存管理-物品领用记录
     * 
     * @param gGoodsReceivingRecord 库存管理-物品领用记录
     * @return 结果
     */
    public int updateGGoodsReceivingRecord(GGoodsReceivingRecord gGoodsReceivingRecord);

    /**
     * 批量删除库存管理-物品领用记录
     * 
     * @param ids 需要删除的库存管理-物品领用记录ID
     * @return 结果
     */
    public int deleteGGoodsReceivingRecordByIds(String[] ids);

    /**
     * 删除库存管理-物品领用记录信息
     * 
     * @param id 库存管理-物品领用记录ID
     * @return 结果
     */
    public int deleteGGoodsReceivingRecordById(String id);
}
