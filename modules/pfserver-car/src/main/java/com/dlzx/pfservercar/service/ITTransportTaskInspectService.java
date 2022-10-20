package com.dlzx.pfservercar.service;

import java.util.List;
import com.dlzx.pfservercar.domain.TTransportTaskInspect;
import com.dlzx.pfservercar.domain.model.TTransportTaskInspectModel;
import com.dlzx.pfservercar.domain.model.TTransportTaskModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskInspectVm;
import com.dlzx.pfservercar.domain.vm.TTransportTaskVm;

/**
 * 任务管理-运输任务点检Service接口
 * 
 * @author dcl
 * @date 2020-12-18
 */
public interface ITTransportTaskInspectService 
{
    /**
     * 查询任务管理-运输任务点检
     * 
     * @param id 任务管理-运输任务点检ID
     * @return 任务管理-运输任务点检
     */
    public TTransportTaskInspectVm selectTTransportTaskInspectById(String id);

    /**
     * 查询任务管理-运输任务点检列表
     * 
     * @param tTransportTaskInspectModel 任务管理-运输任务点检
     * @return 任务管理-运输任务点检集合
     */
    public List<TTransportTaskInspectVm> selectTTransportTaskInspectList(TTransportTaskInspectModel tTransportTaskInspectModel);

    /**
     * 新增任务管理-运输任务点检
     * 
     * @param tTransportTaskInspectModel 任务管理-运输任务点检
     * @return 结果
     */
    public int insertTTransportTaskInspect(TTransportTaskInspectModel tTransportTaskInspectModel);

    /**
     * 修改任务管理-运输任务点检
     * 
     * @param tTransportTaskInspectModel 任务管理-运输任务点检
     * @return 结果
     */
    public int updateTTransportTaskInspect(TTransportTaskInspectModel tTransportTaskInspectModel);

    /**
     * 批量删除任务管理-运输任务点检
     * 
     * @param ids 需要删除的任务管理-运输任务点检ID
     * @return 结果
     */
    public int deleteTTransportTaskInspectByIds(String[] ids);

    /**
     * 删除任务管理-运输任务点检信息
     * 
     * @param id 任务管理-运输任务点检ID
     * @return 结果
     */
    public int deleteTTransportTaskInspectById(String id);

    /**
     * 查询任务管理-运输任务点检列表
     *
     * @param tTransportTaskModel 任务管理-运输任务点检列表
     * @return 任务管理-运输任务点检集合
     */
    List<TTransportTaskVm> queryTTransportTaskInspectList(TTransportTaskModel tTransportTaskModel);

    /**
     * 查询任务管理-运输任务点检详情
     *
     * @param taskId 任务管理-运输任务点检详情
     * @return 任务管理-运输任务点检集合
     */
    TTransportTaskVm queryTTransportTaskInspectByTaskId(String taskId);
}
