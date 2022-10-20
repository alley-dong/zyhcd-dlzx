package com.dlzx.pfserverparts.service;

import java.util.Date;
import java.util.List;
import com.dlzx.pfserverparts.domain.GGoodsReceivingRecordDate;

/**
 * 库存管理-物品领用记录-领取日期Service接口
 * 
 * @author guo
 * @date 2020-12-11
 */
public interface IGGoodsReceivingRecordDateService 
{
    /**
     * 查询库存管理-物品领用记录-领取日期
     * 
     * @param id 库存管理-物品领用记录-领取日期ID
     * @return 库存管理-物品领用记录-领取日期
     */
    public GGoodsReceivingRecordDate selectGGoodsReceivingRecordDateById(String id);

    /**
     * 查询库存管理-物品领用记录-领取日期列表
     * 
     * @param gGoodsReceivingRecordDate 库存管理-物品领用记录-领取日期
     * @return 库存管理-物品领用记录-领取日期集合
     */
    public List<GGoodsReceivingRecordDate> selectGGoodsReceivingRecordDateList(GGoodsReceivingRecordDate gGoodsReceivingRecordDate);
    public List<GGoodsReceivingRecordDate> selectGGoodsReceivingRecordDateByDate(GGoodsReceivingRecordDate gGoodsReceivingRecordDate);
    /**
     * 新增库存管理-物品领用记录-领取日期
     * 
     * @param gGoodsReceivingRecordDate 库存管理-物品领用记录-领取日期
     * @return 结果
     */
    public int insertGGoodsReceivingRecordDate(GGoodsReceivingRecordDate gGoodsReceivingRecordDate);

    /**
     * 批量添加
     */
    public void insertList(List<GGoodsReceivingRecordDate> gGoodsReceivingRecordDate);
    /**
     * 修改库存管理-物品领用记录-领取日期
     * 
     * @param gGoodsReceivingRecordDate 库存管理-物品领用记录-领取日期
     * @return 结果
     */
    public int updateGGoodsReceivingRecordDate(GGoodsReceivingRecordDate gGoodsReceivingRecordDate);

    /**
     * 批量删除库存管理-物品领用记录-领取日期
     * 
     * @param ids 需要删除的库存管理-物品领用记录-领取日期ID
     * @return 结果
     */
    public int deleteGGoodsReceivingRecordDateByIds(String[] ids);

    /**
     * 删除库存管理-物品领用记录-领取日期信息
     * 
     * @param id 库存管理-物品领用记录-领取日期ID
     * @return 结果
     */
    public int deleteGGoodsReceivingRecordDateById(String id);
}
