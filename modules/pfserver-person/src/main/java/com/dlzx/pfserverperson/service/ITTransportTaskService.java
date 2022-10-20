package com.dlzx.pfserverperson.service;

import java.util.List;

import com.dlzx.pfserverperson.domain.DPerformanceRecordDuan;
import com.dlzx.pfserverperson.domain.TTransportTask;


/**
 * 任务管理-运输任务Service接口
 *
 * @author dlzx
 * @date 2020-12-18
 */
public interface ITTransportTaskService {
    /**
     * 查询任务管理-运输任务
     *
     * @param id 任务管理-运输任务ID
     * @return 任务管理-运输任务
     */
    public TTransportTask selectTTransportTaskById(String id);

    /**
     * 查询任务管理-运输任务列表
     *
     * @param tTransportTask 任务管理-运输任务
     * @return 任务管理-运输任务集合
     */
    public List<TTransportTask> selectTTransportTaskList(TTransportTask tTransportTask);

    public List<TTransportTask> selectTTransportTaskByDriverId(List<String> driverId);
    public List<TTransportTask> selectTTransportTaskByViceDriverId(List<String> viceDriverId);

    // 短途绩效记录根据时间查驾驶员
    public List<DPerformanceRecordDuan> selectTTransportTaskListDuan(TTransportTask tTransportTask);

    /**
     * 新增任务管理-运输任务
     *
     * @param tTransportTask 任务管理-运输任务
     * @return 结果
     */
    public int insertTTransportTask(TTransportTask tTransportTask);

    /**
     * 修改任务管理-运输任务
     *
     * @param tTransportTask 任务管理-运输任务
     * @return 结果
     */
    public int updateTTransportTask(TTransportTask tTransportTask);

    /**
     * 批量删除任务管理-运输任务
     *
     * @param ids 需要删除的任务管理-运输任务ID
     * @return 结果
     */
    public int deleteTTransportTaskByIds(String[] ids);

    /**
     * 删除任务管理-运输任务信息
     *
     * @param id 任务管理-运输任务ID
     * @return 结果
     */
    public int deleteTTransportTaskById(String id);


}
