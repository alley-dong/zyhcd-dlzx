package com.dlzx.pfservercar.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.GGoodsReceivingRecordMapper;
import com.dlzx.pfservercar.domain.GGoodsReceivingRecord;
import com.dlzx.pfservercar.service.IGGoodsReceivingRecordService;

/**
 * 库存管理-物品领用记录Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-16
 */
@Service
public class GGoodsReceivingRecordServiceImpl implements IGGoodsReceivingRecordService 
{
    @Autowired
    private GGoodsReceivingRecordMapper gGoodsReceivingRecordMapper;

    /**
     * 查询库存管理-物品领用记录
     * 
     * @param id 库存管理-物品领用记录ID
     * @return 库存管理-物品领用记录
     */
    @Override
    public GGoodsReceivingRecord selectGGoodsReceivingRecordById(String id)
    {
        return gGoodsReceivingRecordMapper.selectGGoodsReceivingRecordById(id);
    }

    /**
     * 查询库存管理-物品领用记录列表
     * 
     * @param gGoodsReceivingRecord 库存管理-物品领用记录
     * @return 库存管理-物品领用记录
     */
    @Override
    public List<GGoodsReceivingRecord> selectGGoodsReceivingRecordList(GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        return gGoodsReceivingRecordMapper.selectGGoodsReceivingRecordList(gGoodsReceivingRecord);
    }

    @Override
    public List<GGoodsReceivingRecord> selectGGoodsReceivingRecordListNum(GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        return gGoodsReceivingRecordMapper.selectGGoodsReceivingRecordListNum(gGoodsReceivingRecord);
    }
    /**
     * 新增库存管理-物品领用记录
     * 
     * @param gGoodsReceivingRecord 库存管理-物品领用记录
     * @return 结果
     */
    @Override
    public int insertGGoodsReceivingRecord(GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        gGoodsReceivingRecord.setCreateTime(DateUtils.getNowDate());
        return gGoodsReceivingRecordMapper.insertGGoodsReceivingRecord(gGoodsReceivingRecord);
    }

    /**
     * 修改库存管理-物品领用记录
     * 
     * @param gGoodsReceivingRecord 库存管理-物品领用记录
     * @return 结果
     */
    @Override
    public int updateGGoodsReceivingRecord(GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        gGoodsReceivingRecord.setUpdateTime(DateUtils.getNowDate());
        return gGoodsReceivingRecordMapper.updateGGoodsReceivingRecord(gGoodsReceivingRecord);
    }

    /**
     * 批量删除库存管理-物品领用记录
     * 
     * @param ids 需要删除的库存管理-物品领用记录ID
     * @return 结果
     */
    @Override
    public int deleteGGoodsReceivingRecordByIds(String[] ids)
    {
        return gGoodsReceivingRecordMapper.deleteGGoodsReceivingRecordByIds(ids);
    }

    /**
     * 删除库存管理-物品领用记录信息
     * 
     * @param id 库存管理-物品领用记录ID
     * @return 结果
     */
    @Override
    public int deleteGGoodsReceivingRecordById(String id)
    {
        return gGoodsReceivingRecordMapper.deleteGGoodsReceivingRecordById(id);
    }
}
