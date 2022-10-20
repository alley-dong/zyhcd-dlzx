package com.dlzx.pfserverperson.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.pfserverperson.domain.DPerformanceRecordDuan;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.TTransportTaskMapper;
import com.dlzx.pfserverperson.domain.TTransportTask;
import com.dlzx.pfserverperson.service.ITTransportTaskService;

/**
 * 任务管理-运输任务Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-18
 */
@Service
public class TTransportTaskServiceImpl implements ITTransportTaskService 
{
    @Autowired
    private TTransportTaskMapper tTransportTaskMapper;

    /**
     * 查询任务管理-运输任务
     * 
     * @param id 任务管理-运输任务ID
     * @return 任务管理-运输任务
     */
    @Override
    public TTransportTask selectTTransportTaskById(String id)
    {
        return tTransportTaskMapper.selectTTransportTaskById(id);
    }

    /**
     * 查询任务管理-运输任务列表
     * 
     * @param tTransportTask 任务管理-运输任务
     * @return 任务管理-运输任务
     */
    @Override
    public List<TTransportTask> selectTTransportTaskList(TTransportTask tTransportTask)
    {
        return tTransportTaskMapper.selectTTransportTaskList(tTransportTask);
    }

    @Override
    public List<TTransportTask> selectTTransportTaskByDriverId(List<String> driverIds) {
        return tTransportTaskMapper.selectTTransportTaskByDriverId(driverIds);
    }

    @Override
    public List<TTransportTask> selectTTransportTaskByViceDriverId(List<String> viceDriverIds) {
        return tTransportTaskMapper.selectTTransportTaskByViceDriverId(viceDriverIds);
    }

    // 短途绩效记录根据时间查驾驶员
    @Override
    public List<DPerformanceRecordDuan> selectTTransportTaskListDuan(TTransportTask tTransportTask)
    {
        return tTransportTaskMapper.selectTTransportTaskListDuan(tTransportTask);
    }
    /**
     * 新增任务管理-运输任务
     * 
     * @param tTransportTask 任务管理-运输任务
     * @return 结果
     */
    @Override
    public int insertTTransportTask(TTransportTask tTransportTask)
    {
        tTransportTask.setCreateTime(DateUtils.getNowDate());
        return tTransportTaskMapper.insertTTransportTask(tTransportTask);
    }

    /**
     * 修改任务管理-运输任务
     * 
     * @param tTransportTask 任务管理-运输任务
     * @return 结果
     */
    @Override
    public int updateTTransportTask(TTransportTask tTransportTask)
    {
        tTransportTask.setUpdateTime(DateUtils.getNowDate());
        return tTransportTaskMapper.updateTTransportTask(tTransportTask);
    }

    /**
     * 批量删除任务管理-运输任务
     * 
     * @param ids 需要删除的任务管理-运输任务ID
     * @return 结果
     */
    @Override
    public int deleteTTransportTaskByIds(String[] ids)
    {
        return tTransportTaskMapper.deleteTTransportTaskByIds(ids);
    }

    /**
     * 删除任务管理-运输任务信息
     * 
     * @param id 任务管理-运输任务ID
     * @return 结果
     */
    @Override
    public int deleteTTransportTaskById(String id)
    {
        return tTransportTaskMapper.deleteTTransportTaskById(id);
    }


}
