package com.dlzx.pfservercar.service;

import java.util.List;
import com.dlzx.pfservercar.domain.TTransportTaskCost;

/**
 * 任务管理-运输任务费用Service接口
 * 
 * @author yangluhe
 * @date 2021-01-05
 */
public interface ITTransportTaskCostService 
{
    /**
     * 查询任务管理-运输任务费用
     * 
     * @param id 任务管理-运输任务费用ID
     * @return 任务管理-运输任务费用
     */
    public TTransportTaskCost selectTTransportTaskCostById(String id);

    /**
     * 查询任务管理-运输任务费用列表
     * 
     * @param tTransportTaskCost 任务管理-运输任务费用
     * @return 任务管理-运输任务费用集合
     */
    public List<TTransportTaskCost> selectTTransportTaskCostList(TTransportTaskCost tTransportTaskCost);

    /**
     * 新增任务管理-运输任务费用
     * 
     * @param tTransportTaskCost 任务管理-运输任务费用
     * @return 结果
     */
    public int insertTTransportTaskCost(TTransportTaskCost tTransportTaskCost);

    /**
     * 修改任务管理-运输任务费用
     * 
     * @param tTransportTaskCost 任务管理-运输任务费用
     * @return 结果
     */
    public int updateTTransportTaskCost(TTransportTaskCost tTransportTaskCost);

    /**
     * 批量删除任务管理-运输任务费用
     * 
     * @param ids 需要删除的任务管理-运输任务费用ID
     * @return 结果
     */
    public int deleteTTransportTaskCostByIds(String[] ids);

    /**
     * 删除任务管理-运输任务费用信息
     * 
     * @param id 任务管理-运输任务费用ID
     * @return 结果
     */
    public int deleteTTransportTaskCostById(String id);
}
