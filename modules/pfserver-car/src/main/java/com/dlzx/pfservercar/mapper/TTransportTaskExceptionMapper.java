package com.dlzx.pfservercar.mapper;

import java.util.List;
import com.dlzx.pfservercar.domain.TTransportTaskException;
import com.dlzx.pfservercar.domain.model.TTransportTaskExceptionModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskExceptionVm;

/**
 * 任务管理-运输任务异常上报Mapper接口
 * 
 * @author dcl
 * @date 2020-12-18
 */
public interface TTransportTaskExceptionMapper 
{
    /**
     * 查询任务管理-运输任务异常上报
     * 
     * @param id 任务管理-运输任务异常上报ID
     * @return 任务管理-运输任务异常上报
     */
    public TTransportTaskExceptionVm selectTTransportTaskExceptionById(String id);

    /**
     * 查询任务管理-运输任务异常上报列表
     * 
     * @param tTransportTaskExceptionModel 任务管理-运输任务异常上报
     * @return 任务管理-运输任务异常上报集合
     */
    public List<TTransportTaskExceptionVm> selectTTransportTaskExceptionList(TTransportTaskExceptionModel tTransportTaskExceptionModel);

    /**
     * 新增任务管理-运输任务异常上报
     * 
     * @param tTransportTaskExceptionModel 任务管理-运输任务异常上报
     * @return 结果
     */
    public int insertTTransportTaskException(TTransportTaskExceptionModel tTransportTaskExceptionModel);

    /**
     * 修改任务管理-运输任务异常上报
     * 
     * @param tTransportTaskExceptionModel 任务管理-运输任务异常上报
     * @return 结果
     */
    public int updateTTransportTaskException(TTransportTaskExceptionModel tTransportTaskExceptionModel);

    /**
     * 删除任务管理-运输任务异常上报
     * 
     * @param id 任务管理-运输任务异常上报ID
     * @return 结果
     */
    public int deleteTTransportTaskExceptionById(String id);

    /**
     * 批量删除任务管理-运输任务异常上报
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTTransportTaskExceptionByIds(String[] ids);

    /**
     * 任务异常审核
     * */
    void checkTaskException(TTransportTaskExceptionModel tTransportTaskExceptionModel);

}
