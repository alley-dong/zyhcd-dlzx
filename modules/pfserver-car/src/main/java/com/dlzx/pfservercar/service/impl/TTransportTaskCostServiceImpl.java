package com.dlzx.pfservercar.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.TTransportTaskCostMapper;
import com.dlzx.pfservercar.domain.TTransportTaskCost;
import com.dlzx.pfservercar.service.ITTransportTaskCostService;

/**
 * 任务管理-运输任务费用Service业务层处理
 * 
 * @author yangluhe
 * @date 2021-01-05
 */
@Service
public class TTransportTaskCostServiceImpl implements ITTransportTaskCostService 
{
    @Autowired
    private TTransportTaskCostMapper tTransportTaskCostMapper;

    /**
     * 查询任务管理-运输任务费用
     * 
     * @param id 任务管理-运输任务费用ID
     * @return 任务管理-运输任务费用
     */
    @Override
    public TTransportTaskCost selectTTransportTaskCostById(String id)
    {
        return tTransportTaskCostMapper.selectTTransportTaskCostById(id);
    }

    /**
     * 查询任务管理-运输任务费用列表
     * 
     * @param tTransportTaskCost 任务管理-运输任务费用
     * @return 任务管理-运输任务费用
     */
    @Override
    public List<TTransportTaskCost> selectTTransportTaskCostList(TTransportTaskCost tTransportTaskCost)
    {
        return tTransportTaskCostMapper.selectTTransportTaskCostList(tTransportTaskCost);
    }

    /**
     * 新增任务管理-运输任务费用
     * 
     * @param tTransportTaskCost 任务管理-运输任务费用
     * @return 结果
     */
    @Override
    public int insertTTransportTaskCost(TTransportTaskCost tTransportTaskCost)
    {
        tTransportTaskCost.setCreateTime(DateUtils.getNowDate());
        return tTransportTaskCostMapper.insertTTransportTaskCost(tTransportTaskCost);
    }

    /**
     * 修改任务管理-运输任务费用
     * 
     * @param tTransportTaskCost 任务管理-运输任务费用
     * @return 结果
     */
    @Override
    public int updateTTransportTaskCost(TTransportTaskCost tTransportTaskCost)
    {
        tTransportTaskCost.setUpdateTime(DateUtils.getNowDate());
        return tTransportTaskCostMapper.updateTTransportTaskCost(tTransportTaskCost);
    }

    /**
     * 批量删除任务管理-运输任务费用
     * 
     * @param ids 需要删除的任务管理-运输任务费用ID
     * @return 结果
     */
    @Override
    public int deleteTTransportTaskCostByIds(String[] ids)
    {
        return tTransportTaskCostMapper.deleteTTransportTaskCostByIds(ids);
    }

    /**
     * 删除任务管理-运输任务费用信息
     * 
     * @param id 任务管理-运输任务费用ID
     * @return 结果
     */
    @Override
    public int deleteTTransportTaskCostById(String id)
    {
        return tTransportTaskCostMapper.deleteTTransportTaskCostById(id);
    }
}
