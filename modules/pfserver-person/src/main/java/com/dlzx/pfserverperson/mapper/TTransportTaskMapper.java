package com.dlzx.pfserverperson.mapper;

import java.util.List;

import com.dlzx.pfserverperson.domain.DPerformanceRecordDuan;
import com.dlzx.pfserverperson.domain.TTransportTask;


/**
 * 任务管理-运输任务Mapper接口
 *
 * @author dlzx
 * @date 2020-12-18
 */
public interface TTransportTaskMapper {
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

    // 短途绩效记录根据时间查驾驶员
    public List<DPerformanceRecordDuan> selectTTransportTaskListDuan(TTransportTask tTransportTask);

    public List<TTransportTask> selectTTransportTaskByDriverId(List<String> driverIds);
    public List<TTransportTask> selectTTransportTaskByViceDriverId(List<String> viceDriverIds);

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
     * 删除任务管理-运输任务
     *
     * @param id 任务管理-运输任务ID
     * @return 结果
     */
    public int deleteTTransportTaskById(String id);

    /**
     * 批量删除任务管理-运输任务
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTTransportTaskByIds(String[] ids);


}
