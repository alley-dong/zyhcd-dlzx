package com.dlzx.pfservercar.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.RRepairDetailedMapper;
import com.dlzx.pfservercar.domain.RRepairDetailed;
import com.dlzx.pfservercar.service.IRRepairDetailedService;

/**
 * 维修保养-维修明细Service业务层处理
 * 
 * @author yangluhe
 * @date 2020-12-04
 */
@Service
public class RRepairDetailedServiceImpl implements IRRepairDetailedService 
{
    @Autowired
    private RRepairDetailedMapper rRepairDetailedMapper;

    /**
     * 查询维修保养-维修明细
     * 
     * @param id 维修保养-维修明细ID
     * @return 维修保养-维修明细
     */
    @Override
    public RRepairDetailed selectRRepairDetailedById(String id)
    {
        return rRepairDetailedMapper.selectRRepairDetailedById(id);
    }

    /**
     * 查询维修保养-维修明细列表
     * 
     * @param rRepairDetailed 维修保养-维修明细
     * @return 维修保养-维修明细
     */
    @Override
    public List<RRepairDetailed> selectRRepairDetailedList(RRepairDetailed rRepairDetailed)
    {
        return rRepairDetailedMapper.selectRRepairDetailedList(rRepairDetailed);
    }

    /**
     * 新增维修保养-维修明细
     * 
     * @param rRepairDetailed 维修保养-维修明细
     * @return 结果
     */
    @Override
    public int insertRRepairDetailed(RRepairDetailed rRepairDetailed)
    {
        rRepairDetailed.setCreateTime(DateUtils.getNowDate());
        return rRepairDetailedMapper.insertRRepairDetailed(rRepairDetailed);
    }

    /**
     * 修改维修保养-维修明细
     * 
     * @param rRepairDetailed 维修保养-维修明细
     * @return 结果
     */
    @Override
    public int updateRRepairDetailed(RRepairDetailed rRepairDetailed)
    {
        rRepairDetailed.setUpdateTime(DateUtils.getNowDate());
        return rRepairDetailedMapper.updateRRepairDetailed(rRepairDetailed);
    }

    /**
     * 批量删除维修保养-维修明细
     * 
     * @param ids 需要删除的维修保养-维修明细ID
     * @return 结果
     */
    @Override
    public int deleteRRepairDetailedByIds(String[] ids)
    {
        return rRepairDetailedMapper.deleteRRepairDetailedByIds(ids);
    }

    /**
     * 删除维修保养-维修明细信息
     * 
     * @param id 维修保养-维修明细ID
     * @return 结果
     */
    @Override
    public int deleteRRepairDetailedById(String id)
    {
        return rRepairDetailedMapper.deleteRRepairDetailedById(id);
    }

    /**
     * 审核维修明细
     */
    @Override
    public List<RRepairDetailed> selectRRepairDetailedNotNewList(String repairId){
        return rRepairDetailedMapper.selectRRepairDetailedNotNewList(repairId);
    }

    /**
     * 审核新增的维修明细
     */
    @Override
    public List<RRepairDetailed> selectRRepairDetailedIsNewList(String repairId){
        return rRepairDetailedMapper.selectRRepairDetailedIsNewList(repairId);
    }

    /**
     * 根据garageId查找所有数据
     *
     */
    @Override
    public List<RRepairDetailed> selectRRepairDetailedAllByGarageId(String repairId){
        return rRepairDetailedMapper.selectRRepairDetailedAllByGarageId(repairId);
    }

    /**
     * 新申请维修明细列表
     */
    @Override
    public List<RRepairDetailed> selectRRepairDetailedAddListRecord(RRepairDetailed rRepairDetailed){
        return rRepairDetailedMapper.selectRRepairDetailedAddListRecord(rRepairDetailed);
    }

    /**
     * 查所有的数据联表
     */
    @Override
    public List<RRepairDetailed> selectCostVehicleList(RRepairDetailed rRepairDetailed){
        return rRepairDetailedMapper.selectCostVehicleList(rRepairDetailed);
    }

    /**
     * 计算年月维修次数
     */
    @Override
    public RRepairDetailed selectMonthNumber(RRepairDetailed rRepairDetailed){
        return rRepairDetailedMapper.selectMonthNumber(rRepairDetailed);
    }
}
