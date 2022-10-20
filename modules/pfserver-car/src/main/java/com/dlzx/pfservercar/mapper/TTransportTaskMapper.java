package com.dlzx.pfservercar.mapper;

import java.util.List;
import com.dlzx.pfservercar.domain.TTransportTask;
import com.dlzx.pfservercar.domain.model.OTMTTransportTaskModel;
import com.dlzx.pfservercar.domain.model.OTMTTransportTaskTimeModel;
import com.dlzx.pfservercar.domain.model.TTransportTaskModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskVm;

/**
 * 任务管理-运输任务Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-18
 */
public interface TTransportTaskMapper 
{
    /**
     * 查询任务管理-运输任务
     * 
     * @param id 任务管理-运输任务ID
     * @return 任务管理-运输任务
     */
    public TTransportTaskVm selectTTransportTaskById(String id);

    /**
     * 查询任务管理-运输任务列表
     * 
     * @param tTransportTaskModel 任务管理-运输任务
     * @return 任务管理-运输任务集合
     */
    public List<TTransportTaskVm> selectTTransportTaskList(TTransportTaskModel tTransportTaskModel);

   /**
    * 承运商成本核算
    * @param tTransportTask
    * @return
    */
    public List<TTransportTask> selectTTransportTaskAccountingList(TTransportTask tTransportTask);

    /**
     * 承运商查看下拉列表-线路
     * @param
     * @return
     */
    public List<TTransportTask> selectTTransportTaskRouteName();

   /**
     * 查询任务管理-运输任务列表
     *
     * @param tTransportTask 任务管理-运输任务
     * @return 任务管理-运输任务集合
     */
    public List<TTransportTask> selectTTransportTaskListEtcUse(TTransportTask tTransportTask);

    /**
     * 新增任务管理-运输任务
     * 
     * @param tTransportTaskModel 任务管理-运输任务
     * @return 结果
     */
    public int insertTTransportTask(TTransportTaskModel tTransportTaskModel);

    /**
     * 修改任务管理-运输任务
     * 
     * @param tTransportTaskModel 任务管理-运输任务
     * @return 结果
     */
    public int updateTTransportTask(TTransportTaskModel tTransportTaskModel);

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

    /**
     * 获取驾驶员的线路及车型
     * @param tTransportTaskModel TTransportTaskVm中驾驶员ID
     * @return TTransportTaskVm
     * */
    List<TTransportTaskVm> queryDriverTRouteList(TTransportTaskModel tTransportTaskModel);


    /**
     * 获取驾驶员运输任务列表
     * */
    List<TTransportTaskVm> queryApiTaskList(TTransportTaskModel tTransportTaskModel);

    /**
     * 获取驾驶员运输任务记录
     * */
    List<TTransportTaskVm> queryApiTaskRecord(TTransportTaskModel tTransportTaskModel);

    /**
     * 根据主键id获取列表
    * */
    List<TTransportTask> queryTaskListById(List<String> taskIds);

    /**
     * 更新任务时间
     * */
    int UpdateOTMCarTransportTask(OTMTTransportTaskTimeModel otmtTransportTaskTimeModel);

   /**
    * 任务成本核算
    * @param id
    * @return
    */
    List<TTransportTask> selectTTransportTaskCost(String id);

    /**
     * @describe OTM新增任务
     * @author DongCL
     * @date 2021-01-07 16:33
     * @param
     * @return
     */
    int insertOTMTransportTask(OTMTTransportTaskModel otmtTransportTaskModel);

    /**
     * @describe
     * @author DongCL
     * @date 2021-01-13 14:41
     * @param
     * @return
     */
    TTransportTask selectTTransportTaskByXid(String shipment_xid);
}
