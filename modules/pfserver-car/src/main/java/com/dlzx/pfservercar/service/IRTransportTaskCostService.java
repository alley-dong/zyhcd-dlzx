package com.dlzx.pfservercar.service;

import java.util.List;
import com.dlzx.pfservercar.domain.RTransportTaskCost;

/**
 * 统计报-任务成本核算Service接口
 * 
 * @author ylh
 * @date 2021-01-05
 */
public interface IRTransportTaskCostService 
{
    /**
     * 查询统计报-任务成本核算
     * 
     * @param id 统计报-任务成本核算ID
     * @return 统计报-任务成本核算
     */
    public RTransportTaskCost selectRTransportTaskCostById(String id);

    /**
     * 查询统计报-任务成本核算列表
     * 
     * @param rTransportTaskCost 统计报-任务成本核算
     * @return 统计报-任务成本核算集合
     */
    public List<RTransportTaskCost> selectRTransportTaskCostList(RTransportTaskCost rTransportTaskCost);

    /**
     * 新增统计报-任务成本核算
     * 
     * @param rTransportTaskCost 统计报-任务成本核算
     * @return 结果
     */
    public int insertRTransportTaskCost(RTransportTaskCost rTransportTaskCost);

    /**
     * 修改统计报-任务成本核算
     * 
     * @param rTransportTaskCost 统计报-任务成本核算
     * @return 结果
     */
    public int updateRTransportTaskCost(RTransportTaskCost rTransportTaskCost);

    /**
     * 批量删除统计报-任务成本核算
     * 
     * @param ids 需要删除的统计报-任务成本核算ID
     * @return 结果
     */
    public int deleteRTransportTaskCostByIds(String[] ids);

    /**
     * 删除统计报-任务成本核算信息
     * 
     * @param id 统计报-任务成本核算ID
     * @return 结果
     */
    public int deleteRTransportTaskCostById(String id);

    /**
     * 统计报-列表
     * @param rTransportTaskCost
     * @return
     */
    public int selectCostList(RTransportTaskCost rTransportTaskCost);
}
