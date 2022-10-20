package com.dlzx.job.mapper;

import com.dlzx.job.domain.GGoodsReceivingRecord;

import java.util.List;

/**
 * 库存管理-物品领用记录Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-16
 */
public interface GGoodsReceivingRecordMapper 
{

    public List<GGoodsReceivingRecord> selectGGoodsReceivingRecordListNum(GGoodsReceivingRecord gGoodsReceivingRecord);

}
