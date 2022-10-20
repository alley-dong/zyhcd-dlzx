package com.dlzx.pfservercar.service;

import java.util.List;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.domain.ESBResult;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.DDriver;
import com.dlzx.pfservercar.domain.TTransportTask;
import com.dlzx.pfservercar.domain.model.OTMDriverModel;
import com.dlzx.pfservercar.domain.model.OTMRouteModel;
import com.dlzx.pfservercar.domain.model.OTMTTransportShortTaskModel;
import com.dlzx.pfservercar.domain.model.OTMTTransportTaskModel;
import com.dlzx.pfservercar.domain.model.OTMTTransportTaskTimeModel;
import com.dlzx.pfservercar.domain.model.OTMTransportTaskCostModel;
import com.dlzx.pfservercar.domain.model.OTMTransportTaskOrderModel;
import com.dlzx.pfservercar.domain.model.OTMTransportTaskOrderPartsModel;
import com.dlzx.pfservercar.domain.model.OTMTransportTaskShipmentStopModel;
import com.dlzx.pfservercar.domain.model.OTMTransportTaskTrayModel;
import com.dlzx.pfservercar.domain.model.OTMVehicleModel;
import com.dlzx.pfservercar.domain.model.TTransportTaskInspectModel;
import com.dlzx.pfservercar.domain.model.TTransportTaskModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskVm;
import org.checkerframework.checker.units.qual.A;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 任务管理-运输任务Service接口
 * 
 * @author dlzx
 * @date 2020-12-18
 */
public interface ITTransportTaskService 
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
     * 承运商核算
     *
     * @param tTransportTask 任务管理-运输任务
     * @return 任务管理-运输任务集合
     */
    public List<TTransportTask> selectTTransportTaskAccountingList(TTransportTask tTransportTask);

    /**
     * 承运商查看下拉列表-线路
     * @param
     * @return
     */
    public List<TTransportTask> selectTTransportTaskRouteName();

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
     * 提交运输点检记录
    * */
    AjaxResult submitTTransportTaskInspect(TTransportTaskInspectModel tTransportTaskInspectModel);

    /**
     * @describe OTM 车队司机基础数据
     * @author DongCL
     * @date 2020-12-24 13:29
     * @return otmDriverModels 司机数据集合
     */
    List<OTMDriverModel> OTMCarDriverList();

    /**
     * @describe OTM 车队车辆基础数据
     * @author DongCL
     * @date 2020-12-24 14:13
     * @return otmDriverModels 车辆数据集合
     */
    List<OTMVehicleModel> OTMCarVehicleList();

    /**
     * @describe OTM 车队线路基础数据
     * @author DongCL
     * @date 2020-12-25 09:37
     * @return
     */
    ESBResult OTMCarRouteList(OTMRouteModel otmRouteModel);


    /**
     * @describe OTM 车队运输任务数据
     * @author DongCL
     * @date 2020-12-28 14:25
     * @param otmtTransportTaskModel
     * @return
     */
    ESBResult OTMCarTransportTask(OTMTTransportTaskModel otmtTransportTaskModel);

    /**
     * @describe OTM 车队运输任务订单数据
     * @author DongCL
     * @date 2020-12-29 10:54
     * @param otmTransportTaskOrderModel
     * @return
     */
    ESBResult OTMCarTransportTaskOrder(OTMTransportTaskOrderModel otmTransportTaskOrderModel);

    /**
     * @describe OTM 车队运输任务订单明细数据
     * @author DongCL
     * @date 2020-12-28 19:36
     * @param otmTransportTaskOrderPartsModel
     * @return
     */
    ESBResult OTMCarTransportTaskOrderParts(OTMTransportTaskOrderPartsModel otmTransportTaskOrderPartsModel);

    /**
     * @describe 更新任务时间
     * @author DongCL
     * @date 2020-12-29 13:41
     * @param
     * @return
     */
    ESBResult UpdateOTMCarTransportTask(OTMTTransportTaskTimeModel otmtTransportTaskTimeModel);

    /**
     * @describe 同步驾驶员数据->OTM
     * @author DongCL
     * @date 2021-01-06 13:37
     * @param dDriver
     * @return OTMDriverModel
     */
    int OTMCarDriver(DDriver dDriver);

    /**
     * @describe 同步车辆数据->OTM
     * @author DongCL
     * @date 2021-01-06 13:37
     * @param cVehicle
     * @return OTMDriverModel
     */
    int OTMCarVehicle(CVehicle cVehicle);

    /**
     * @describe OTM 车队运输任务站点
     * @author DongCL
     * @date 2021-01-07 13:34
     * @param otmTransportTaskShipmentStopModel
     * @return AjaxResult
     */
    ESBResult OTMCarTransportTaskShipmentStop(OTMTransportTaskShipmentStopModel otmTransportTaskShipmentStopModel);

    /**
     * @describe OTM 车队运输任务托盘
     * @author DongCL
     * @date 2021-01-07 13:35
     * @param otmTransportTaskTrayModel
     * @return AjaxResult
     */
    ESBResult OTMCarTransportTaskTray(OTMTransportTaskTrayModel otmTransportTaskTrayModel);

    /**
     * @describe OTM 车队运输任务运费
     * @author DongCL
     * @date 2021-01-07 13:35
     * @param otmTransportTaskCostModel
     * @return AjaxResult
     */
    ESBResult OTMCarTransportTaskCost(OTMTransportTaskCostModel otmTransportTaskCostModel);

    /**
     * @describe OTM 车队短途台班
     * @author DongCL
     * @date 2020-12-28 14:25
     * @param otmtTransportShortTaskModel
     * @return
     */
    ESBResult OTMCarTransportShortTask(OTMTTransportShortTaskModel otmtTransportShortTaskModel);

    /**
     * @describe 上传照片
     * @author DongCL
     * @date 2021-01-20 09:34
     * @param
     * @return
     */
    AjaxResult updatePhoto(TTransportTaskModel tTransportTaskModel);
}
