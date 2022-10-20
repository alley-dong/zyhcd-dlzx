package com.dlzx.pfservercar.service;

import java.util.List;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.pfservercar.domain.TTransportTaskException;
import com.dlzx.pfservercar.domain.model.TTransportTaskExceptionModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskExceptionVm;

/**
 * 任务管理-运输任务异常上报Service接口
 * 
 * @author dcl
 * @date 2020-12-18
 */
public interface ITTransportTaskExceptionService 
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
     * 批量删除任务管理-运输任务异常上报
     * 
     * @param ids 需要删除的任务管理-运输任务异常上报ID
     * @return 结果
     */
    public int deleteTTransportTaskExceptionByIds(String[] ids);

    /**
     * 删除任务管理-运输任务异常上报信息
     * 
     * @param id 任务管理-运输任务异常上报ID
     * @return 结果
     */
    public int deleteTTransportTaskExceptionById(String id);

    /**
    * 任务异常审核
    * */
    AjaxResult transportTaskExceptionCheck(TTransportTaskExceptionModel tTransportTaskExceptionModel);

   /**
    * 运输任务异常上报
    * */
    int submitException(TTransportTaskExceptionModel tTransportTaskExceptionModel);

    /**
     * 异常再次上报
    * */
    int resubmitTaskException(TTransportTaskExceptionModel tTransportTaskExceptionModel);
}
