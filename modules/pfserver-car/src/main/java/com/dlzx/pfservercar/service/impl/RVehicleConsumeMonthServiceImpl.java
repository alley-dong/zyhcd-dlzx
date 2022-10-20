package com.dlzx.pfservercar.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.RVehicleConsumeMonthMapper;
import com.dlzx.pfservercar.domain.RVehicleConsumeMonth;
import com.dlzx.pfservercar.service.IRVehicleConsumeMonthService;

/**
 * 统计报-车辆消耗月度统计Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-15
 */
@Service
public class RVehicleConsumeMonthServiceImpl implements IRVehicleConsumeMonthService 
{
    @Autowired
    private RVehicleConsumeMonthMapper rVehicleConsumeMonthMapper;

    /**
     * 查询统计报-车辆消耗月度统计
     * 
     * @param id 统计报-车辆消耗月度统计ID
     * @return 统计报-车辆消耗月度统计
     */
    @Override
    public RVehicleConsumeMonth selectRVehicleConsumeMonthById(String id)
    {
        return rVehicleConsumeMonthMapper.selectRVehicleConsumeMonthById(id);
    }

    /**
     * 查询统计报-车辆消耗月度统计列表
     * 
     * @param rVehicleConsumeMonth 统计报-车辆消耗月度统计
     * @return 统计报-车辆消耗月度统计
     */
    @Override
    public List<RVehicleConsumeMonth> selectRVehicleConsumeMonthList(RVehicleConsumeMonth rVehicleConsumeMonth)
    {
        return rVehicleConsumeMonthMapper.selectRVehicleConsumeMonthList(rVehicleConsumeMonth);
    }

    @Override
    public List<RVehicleConsumeMonth> selectRVehicleConsumeMonthListAll(RVehicleConsumeMonth rVehicleConsumeMonth)
    {
        return rVehicleConsumeMonthMapper.selectRVehicleConsumeMonthListAll(rVehicleConsumeMonth);
    }

    /**
     * 新增统计报-车辆消耗月度统计
     * 
     * @param rVehicleConsumeMonth 统计报-车辆消耗月度统计
     * @return 结果
     */
    @Override
    public int insertRVehicleConsumeMonth(RVehicleConsumeMonth rVehicleConsumeMonth)
    {
        rVehicleConsumeMonth.setCreateTime(DateUtils.getNowDate());
        return rVehicleConsumeMonthMapper.insertRVehicleConsumeMonth(rVehicleConsumeMonth);
    }

    @Override
    public int insertRVehicleConsumeMonthList(List<RVehicleConsumeMonth> list)
    {

        return rVehicleConsumeMonthMapper.insertRVehicleConsumeMonthList(list);
    }
    /**
     * 修改统计报-车辆消耗月度统计
     * 
     * @param rVehicleConsumeMonth 统计报-车辆消耗月度统计
     * @return 结果
     */
    @Override
    public int updateRVehicleConsumeMonth(RVehicleConsumeMonth rVehicleConsumeMonth)
    {
        rVehicleConsumeMonth.setUpdateTime(DateUtils.getNowDate());
        return rVehicleConsumeMonthMapper.updateRVehicleConsumeMonth(rVehicleConsumeMonth);
    }

    /**
     * 批量删除统计报-车辆消耗月度统计
     * 
     * @param ids 需要删除的统计报-车辆消耗月度统计ID
     * @return 结果
     */
    @Override
    public int deleteRVehicleConsumeMonthByIds(String[] ids)
    {
        return rVehicleConsumeMonthMapper.deleteRVehicleConsumeMonthByIds(ids);
    }

    /**
     * 删除统计报-车辆消耗月度统计信息
     * 
     * @param id 统计报-车辆消耗月度统计ID
     * @return 结果
     */
    @Override
    public int deleteRVehicleConsumeMonthById(String id)
    {
        return rVehicleConsumeMonthMapper.deleteRVehicleConsumeMonthById(id);
    }
}
