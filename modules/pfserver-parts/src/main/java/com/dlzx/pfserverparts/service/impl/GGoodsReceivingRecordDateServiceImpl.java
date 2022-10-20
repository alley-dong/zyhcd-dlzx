package com.dlzx.pfserverparts.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverparts.mapper.GGoodsReceivingRecordDateMapper;
import com.dlzx.pfserverparts.domain.GGoodsReceivingRecordDate;
import com.dlzx.pfserverparts.service.IGGoodsReceivingRecordDateService;

/**
 * 库存管理-物品领用记录-领取日期Service业务层处理
 * 
 * @author guo
 * @date 2020-12-11
 */
@Service
public class GGoodsReceivingRecordDateServiceImpl implements IGGoodsReceivingRecordDateService 
{
    @Autowired
    private GGoodsReceivingRecordDateMapper gGoodsReceivingRecordDateMapper;

    /**
     * 查询库存管理-物品领用记录-领取日期
     * 
     * @param id 库存管理-物品领用记录-领取日期ID
     * @return 库存管理-物品领用记录-领取日期
     */
    @Override
    public GGoodsReceivingRecordDate selectGGoodsReceivingRecordDateById(String id)
    {
        return gGoodsReceivingRecordDateMapper.selectGGoodsReceivingRecordDateById(id);
    }

    /**
     * 查询库存管理-物品领用记录-领取日期列表
     * 
     * @param gGoodsReceivingRecordDate 库存管理-物品领用记录-领取日期
     * @return 库存管理-物品领用记录-领取日期
     */
    @Override
    public List<GGoodsReceivingRecordDate> selectGGoodsReceivingRecordDateList(GGoodsReceivingRecordDate gGoodsReceivingRecordDate)
    {
        return gGoodsReceivingRecordDateMapper.selectGGoodsReceivingRecordDateList(gGoodsReceivingRecordDate);
    }

    @Override
    public List<GGoodsReceivingRecordDate> selectGGoodsReceivingRecordDateByDate(GGoodsReceivingRecordDate gGoodsReceivingRecordDate) {
        return gGoodsReceivingRecordDateMapper.selectGGoodsReceivingRecordDateByDate(gGoodsReceivingRecordDate);
    }


    /**
     * 新增库存管理-物品领用记录-领取日期
     * 
     * @param gGoodsReceivingRecordDate 库存管理-物品领用记录-领取日期
     * @return 结果
     */
    @Override
    public int insertGGoodsReceivingRecordDate(GGoodsReceivingRecordDate gGoodsReceivingRecordDate)
    {
        return gGoodsReceivingRecordDateMapper.insertGGoodsReceivingRecordDate(gGoodsReceivingRecordDate);
    }

    @Override
    public void insertList(List<GGoodsReceivingRecordDate> gGoodsReceivingRecordDate) {
        gGoodsReceivingRecordDateMapper.insertList(gGoodsReceivingRecordDate);
    }

    /**
     * 修改库存管理-物品领用记录-领取日期
     * 
     * @param gGoodsReceivingRecordDate 库存管理-物品领用记录-领取日期
     * @return 结果
     */
    @Override
    public int updateGGoodsReceivingRecordDate(GGoodsReceivingRecordDate gGoodsReceivingRecordDate)
    {
        return gGoodsReceivingRecordDateMapper.updateGGoodsReceivingRecordDate(gGoodsReceivingRecordDate);
    }

    /**
     * 批量删除库存管理-物品领用记录-领取日期
     * 
     * @param ids 需要删除的库存管理-物品领用记录-领取日期ID
     * @return 结果
     */
    @Override
    public int deleteGGoodsReceivingRecordDateByIds(String[] ids)
    {
        return gGoodsReceivingRecordDateMapper.deleteGGoodsReceivingRecordDateByIds(ids);
    }

    /**
     * 删除库存管理-物品领用记录-领取日期信息
     * 
     * @param id 库存管理-物品领用记录-领取日期ID
     * @return 结果
     */
    @Override
    public int deleteGGoodsReceivingRecordDateById(String id)
    {
        return gGoodsReceivingRecordDateMapper.deleteGGoodsReceivingRecordDateById(id);
    }
}
