package com.dlzx.pfservercar.mapper;

import java.util.List;
import com.dlzx.pfservercar.domain.TTransportTaskInspect;
import com.dlzx.pfservercar.domain.vm.TTransportTaskInspectVm;

/**
 * 任务管理-运输任务点检Mapper接口
 * 
 * @author dcl
 * @date 2020-12-18
 */
public interface TTransportTaskInspectMapper 
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
     * @param tTransportTaskInspect 任务管理-运输任务点检
     * @return 任务管理-运输任务点检集合
     */
    public List<TTransportTaskInspectVm> selectTTransportTaskInspectList(TTransportTaskInspect tTransportTaskInspect);

    /**
     * 新增任务管理-运输任务点检
     * 
     * @param tTransportTaskInspect 任务管理-运输任务点检
     * @return 结果
     */
    public int insertTTransportTaskInspect(TTransportTaskInspect tTransportTaskInspect);

    /**
     * 修改任务管理-运输任务点检
     * 
     * @param tTransportTaskInspect 任务管理-运输任务点检
     * @return 结果
     */
    public int updateTTransportTaskInspect(TTransportTaskInspect tTransportTaskInspect);

    /**
     * 删除任务管理-运输任务点检
     * 
     * @param id 任务管理-运输任务点检ID
     * @return 结果
     */
    public int deleteTTransportTaskInspectById(String id);

    /**
     * 批量删除任务管理-运输任务点检
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTTransportTaskInspectByIds(String[] ids);

    /**
     * 查询任务管理-运输任务点检详情
     *
     * @param taskId 任务管理-运输任务点检详情
     * @return 任务管理-运输任务点检集合
     */
    List<TTransportTaskInspectVm> queryTTransportTaskInspectByTaskId(String taskId);
}
