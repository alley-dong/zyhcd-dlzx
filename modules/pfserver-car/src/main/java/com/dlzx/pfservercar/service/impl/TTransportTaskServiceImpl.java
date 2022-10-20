package com.dlzx.pfservercar.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dlzx.common.core.constant.HttpStatus;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.utils.VerifyCodeUtil;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.domain.ESBHeader;
import com.dlzx.common.core.web.domain.ESBResult;
import com.dlzx.common.core.web.domain.TMSCarDto;
import com.dlzx.common.core.web.domain.TMSDriverDto;
import com.dlzx.common.core.web.domain.TMSMsgBody;
import com.dlzx.common.core.web.domain.TMSParams;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.pfservercar.client.PersonClient;
import com.dlzx.pfservercar.client.SystemClient;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.DDriver;
import com.dlzx.pfservercar.domain.DPerformance;
import com.dlzx.pfservercar.domain.DPerformanceOvertimeRecord;
import com.dlzx.pfservercar.domain.DPerformanceRecord;
import com.dlzx.pfservercar.domain.DPerformanceShortHaulRecord;
import com.dlzx.pfservercar.domain.FFuelPlace;
import com.dlzx.pfservercar.domain.FFuelPlaceItem;
import com.dlzx.pfservercar.domain.SysFile;
import com.dlzx.pfservercar.domain.SysLog;
import com.dlzx.pfservercar.domain.TRoute;
import com.dlzx.pfservercar.domain.TTransportTaskInspect;
import com.dlzx.pfservercar.domain.TTransportTaskInspectItem;
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
import com.dlzx.pfservercar.domain.model.TTransportTaskExceptionModel;
import com.dlzx.pfservercar.domain.model.TTransportTaskInspectItemModel;
import com.dlzx.pfservercar.domain.model.TTransportTaskInspectModel;
import com.dlzx.pfservercar.domain.model.TTransportTaskModel;
import com.dlzx.pfservercar.domain.model.TTransportTaskOvertimeModel;
import com.dlzx.pfservercar.domain.model.TTransportTaskShipmentStopModel;
import com.dlzx.pfservercar.domain.model.TTransportTaskTrayModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskExceptionVm;
import com.dlzx.pfservercar.domain.vm.TTransportTaskOrderVm;
import com.dlzx.pfservercar.domain.vm.TTransportTaskOvertimeVm;
import com.dlzx.pfservercar.domain.vm.TTransportTaskPartsVm;
import com.dlzx.pfservercar.domain.vm.TTransportTaskShipmentStopVm;
import com.dlzx.pfservercar.domain.vm.TTransportTaskTrayVm;
import com.dlzx.pfservercar.domain.vm.TTransportTaskVm;
import com.dlzx.pfservercar.mapper.CVehicleMapper;
import com.dlzx.pfservercar.mapper.FFuelPlaceItemMapper;
import com.dlzx.pfservercar.mapper.FFuelPlaceMapper;
import com.dlzx.pfservercar.mapper.SysDictDataMapper;
import com.dlzx.pfservercar.mapper.SysFileMapper;
import com.dlzx.pfservercar.mapper.TTransportTaskCostMapper;
import com.dlzx.pfservercar.mapper.TTransportTaskExceptionMapper;
import com.dlzx.pfservercar.mapper.TTransportTaskInspectItemMapper;
import com.dlzx.pfservercar.mapper.TTransportTaskInspectMapper;
import com.dlzx.pfservercar.mapper.TTransportTaskOrderMapper;
import com.dlzx.pfservercar.mapper.TTransportTaskOvertimeMapper;
import com.dlzx.pfservercar.mapper.TTransportTaskPartsMapper;
import com.dlzx.pfservercar.mapper.TTransportTaskShipmentStopMapper;
import com.dlzx.pfservercar.mapper.TTransportTaskTrayMapper;
import com.dlzx.pfservercar.service.IDDriverMsgService;
import com.dlzx.pfservercar.service.ITRouteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.TTransportTaskMapper;
import com.dlzx.pfservercar.domain.TTransportTask;
import com.dlzx.pfservercar.service.ITTransportTaskService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 任务管理-运输任务Service业务层处理
 *
 * @author dlzx
 * @date 2020-12-18
 */
@Service
public class TTransportTaskServiceImpl implements ITTransportTaskService {

    @Value("${esb-config.request-url}")
    private String request_url;

    @Autowired
    private TTransportTaskMapper tTransportTaskMapper;

    @Autowired
    private TTransportTaskPartsMapper tTransportTaskPartsMapper;

    @Autowired
    private FFuelPlaceMapper fFuelPlaceMapper;

    @Autowired
    private FFuelPlaceItemMapper fFuelPlaceItemMapper;

    @Autowired
    private TTransportTaskInspectMapper tTransportTaskInspectMapper;

    @Autowired
    private TTransportTaskInspectItemMapper tTransportTaskInspectItemMapper;

    @Autowired
    private TTransportTaskExceptionMapper tTransportTaskExceptionMapper;

    @Autowired
    private TTransportTaskShipmentStopMapper tTransportTaskShipmentStopMapper;

    @Autowired
    private TTransportTaskTrayMapper tTransportTaskTrayMapper;

    @Autowired
    private TTransportTaskCostMapper tTransportTaskCostMapper;

    @Autowired
    private TTransportTaskOrderMapper tTransportTaskOrderMapper;

    @Autowired
    private TTransportTaskOvertimeMapper tTransportTaskOvertimeMapper;

    @Autowired
    private SysFileMapper sysFileMapper;

    @Autowired
    private PersonClient personClient;

    @Autowired
    private CVehicleMapper cVehicleMapper;

    @Autowired
    private ITRouteService itRouteService;

    @Autowired
    private IDDriverMsgService idDriverMsgService;

    @Autowired
    private SystemClient systemClient;

    /**
     * 查询任务管理-运输任务
     *
     * @param id 任务管理-运输任务ID
     * @return 任务管理-运输任务
     */
    @Override
    public TTransportTaskVm selectTTransportTaskById(String id) {
        TTransportTaskVm tTransportTaskVm = tTransportTaskMapper.selectTTransportTaskById(id);

        if(tTransportTaskVm != null){
            // 点检
            tTransportTaskVm.setProcessInspect(tTransportTaskVm.getProcessInspectCount()+"/"+tTransportTaskVm.getProcessInspectMinCount());

            // 装货照片
            List<SysFile> loadingFileList = sysFileMapper.queryFilesByRelationId(id, "12");
            if (loadingFileList.size() > 0) {
                List<String> list = loadingFileList.stream()
                        .map(SysFile::getFilePath)
                        .collect(Collectors.toList());
                tTransportTaskVm.setLoadingFileList(list);
            }
            // 卸货照片
            List<SysFile> unloadFilesList = sysFileMapper.queryFilesByRelationId(id, "13");
            if (unloadFilesList.size() > 0) {
                List<String> lists = unloadFilesList.stream()
                        .map(SysFile::getFilePath)
                        .collect(Collectors.toList());
                tTransportTaskVm.setUnloadFilesList(lists);
            }
            // 定点加油站
            FFuelPlace fuelPlace = fFuelPlaceMapper.queryFFuelPlaceByRouteId(tTransportTaskVm.getRouteId());
            if (fuelPlace != null) {
                FFuelPlaceItem fFuelPlaceItem = new FFuelPlaceItem();
                fFuelPlaceItem.setPlaceId(fuelPlace.getId());
                List<FFuelPlaceItem> fFuelPlaceItems = fFuelPlaceItemMapper.selectFFuelPlaceItemList(fFuelPlaceItem);
                if (fFuelPlaceItems.size() > 0) {
                    tTransportTaskVm.setfFuelPlaceItems(fFuelPlaceItems);
                }
            }
            // 异常信息
            TTransportTaskExceptionModel tTransportTaskExceptionModel = new TTransportTaskExceptionModel();
            tTransportTaskExceptionModel.setTaskId(tTransportTaskVm.getId());

            List<TTransportTaskExceptionVm> tTransportTaskExceptionVms = tTransportTaskExceptionMapper.selectTTransportTaskExceptionList(tTransportTaskExceptionModel);
            TTransportTaskExceptionVm tTransportTaskExceptionVm = tTransportTaskExceptionVms.stream()
                    .findFirst()
                    .orElse(new TTransportTaskExceptionVm());

            // 任务异常情况照片
            List<SysFile> taskExceptionFileList = sysFileMapper.queryFilesByRelationId(id, "14");
            if (taskExceptionFileList.size() > 0) {
                tTransportTaskExceptionVm.setTaskExceptionFileList(taskExceptionFileList.stream()
                        .map(SysFile::getFilePath)
                        .collect(Collectors.toList()));
            }
            tTransportTaskVm.settTransportTaskExceptionVm(tTransportTaskExceptionVm);

            // 获取任务下部件
            List<TTransportTaskPartsVm> tTransportTaskPartsVms = tTransportTaskPartsMapper.selectTaskPartsByTaskId(tTransportTaskVm.getId());
            if (tTransportTaskPartsVms != null) {
                if (tTransportTaskPartsVms.size() > 0) {
                    tTransportTaskVm.settTransportTaskPartsVmList(tTransportTaskPartsVms);
                }
            }

            // 获取站点信息
            TTransportTaskShipmentStopModel tTransportTaskShipmentStopModel = new TTransportTaskShipmentStopModel();
            tTransportTaskShipmentStopModel.setTaskId(tTransportTaskVm.getId());
            List<TTransportTaskShipmentStopVm> tTransportTaskShipmentStopVms = tTransportTaskShipmentStopMapper.selectTTransportTaskShipmentStopList(tTransportTaskShipmentStopModel);
            if (!StringUtils.isArray(tTransportTaskShipmentStopVms)){
                tTransportTaskShipmentStopVms.forEach(stop->{
                    // 拼接地址
                    stop.setAddress(stop.getLocationProvince()+"-"+stop.getLocationCity()+"-"+stop.getLocationDistrict()+"-"+stop.getLocationAddress());
                });
                tTransportTaskVm.settTransportTaskShipmentStopVmList(tTransportTaskShipmentStopVms);
            }

            // 获取托盘信息
            TTransportTaskTrayModel tTransportTaskTrayModel = new TTransportTaskTrayModel();
            tTransportTaskTrayModel.setTaskId(tTransportTaskVm.getId());
            List<TTransportTaskTrayVm> tTransportTaskTrayVms = tTransportTaskTrayMapper.selectTTransportTaskTrayList(tTransportTaskTrayModel);
            if (!StringUtils.isArray(tTransportTaskTrayVms)){
                tTransportTaskVm.settTransportTaskTrayVmList(tTransportTaskTrayVms);
            }

            return tTransportTaskVm;
        }else {
            return new TTransportTaskVm();
        }
    }

    /**
     * 查询任务管理-运输任务列表
     *
     * @param tTransportTaskModel 任务管理-运输任务
     * @return 任务管理-运输任务
     */
    @Override
    public List<TTransportTaskVm> selectTTransportTaskList(TTransportTaskModel tTransportTaskModel) {
        return tTransportTaskMapper.selectTTransportTaskList(tTransportTaskModel);
    }

    /**
     * 查询任务管理-运输任务列表
     *
     * @param tTransportTask 任务管理-运输任务
     * @return 任务管理-运输任务集合
     */
    @Override
    public List<TTransportTask> selectTTransportTaskAccountingList(TTransportTask tTransportTask) {
        return tTransportTaskMapper.selectTTransportTaskAccountingList(tTransportTask);
    }

    @Override
    public List<TTransportTask> selectTTransportTaskRouteName() {
        return tTransportTaskMapper.selectTTransportTaskRouteName();
    }

    /**
     * 新增任务管理-运输任务
     *
     * @param tTransportTaskModel 任务管理-运输任务
     * @return 结果
     */
    @Override
    public int insertTTransportTask(TTransportTaskModel tTransportTaskModel) {
        tTransportTaskModel.setCreateTime(DateUtils.getNowDate());
        return tTransportTaskMapper.insertTTransportTask(tTransportTaskModel);
    }

    /**
     * 修改任务管理-运输任务
     *
     * @param tTransportTaskModel 任务管理-运输任务
     * @return 结果
     */
    @Override
    public int updateTTransportTask(TTransportTaskModel tTransportTaskModel) {
        tTransportTaskModel.setUpdateTime(DateUtils.getNowDate());
        return tTransportTaskMapper.updateTTransportTask(tTransportTaskModel);
    }

    /**
     * 批量删除任务管理-运输任务
     *
     * @param ids 需要删除的任务管理-运输任务ID
     * @return 结果
     */
    @Override
    public int deleteTTransportTaskByIds(String[] ids) {
        return tTransportTaskMapper.deleteTTransportTaskByIds(ids);
    }

    /**
     * 删除任务管理-运输任务信息
     *
     * @param id 任务管理-运输任务ID
     * @return 结果
     */
    @Override
    public int deleteTTransportTaskById(String id) {
        return tTransportTaskMapper.deleteTTransportTaskById(id);
    }

    /**
     * 获取驾驶员的线路及车型
     *
     * @param tTransportTaskModel TTransportTaskVm中驾驶员ID
     * @return TTransportTaskVm
     */
    @Override
    public List<TTransportTaskVm> queryDriverTRouteList(TTransportTaskModel tTransportTaskModel) {
        return tTransportTaskMapper.queryDriverTRouteList(tTransportTaskModel);
    }

    /**
     * 获取驾驶员运输任务列表
     */
    @Override
    public List<TTransportTaskVm> queryApiTaskList(TTransportTaskModel tTransportTaskModel) {
        return tTransportTaskMapper.queryApiTaskList(tTransportTaskModel);
    }

    /**
     * 获取驾驶员运输任务记录
     */
    @Override
    public List<TTransportTaskVm> queryApiTaskRecord(TTransportTaskModel tTransportTaskModel) {
        return tTransportTaskMapper.queryApiTaskRecord(tTransportTaskModel);
    }

    /**
     * 提交运输点检记录
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AjaxResult submitTTransportTaskInspect(TTransportTaskInspectModel tTransportTaskInspectModel) {

        int result = 0;
        try {
            TTransportTaskInspect tTransportTaskInspect = new TTransportTaskInspect();
            BeanUtils.copyProperties(tTransportTaskInspectModel, tTransportTaskInspect);
            tTransportTaskInspect.setId(UUID.randomUUID() + "");
            tTransportTaskInspect.setCreateTime(new Date());
            result = tTransportTaskInspectMapper.insertTTransportTaskInspect(tTransportTaskInspect);
            if (result > 0) {
                List<TTransportTaskInspectItem> transportTaskInspectItemList = new ArrayList<>();
                // 排除status为空的
                List<TTransportTaskInspectItemModel> collect = tTransportTaskInspectModel.gettTransportTaskInspectItemModels().stream().filter(t -> t.getStatus() != null).collect(Collectors.toList());
                for (TTransportTaskInspectItemModel tTransportTaskInspectItemModel : collect) {
                    if (tTransportTaskInspectItemModel.getStatus()==2){
                        if (tTransportTaskInspectItemModel.getFileList().length<=0){
                            return AjaxResult.error("请上传点检项"+tTransportTaskInspectItemModel.getName() + "的图片");
                        }
                    }
                    TTransportTaskInspectItem tTransportTaskInspectItem = new TTransportTaskInspectItem();
                    BeanUtils.copyProperties(tTransportTaskInspectItemModel, tTransportTaskInspectItem);
                    tTransportTaskInspectItem.setId(UUID.randomUUID() + "");
                    tTransportTaskInspectItem.setCreateTime(new Date());
                    tTransportTaskInspectItem.setFiles(StringUtils.join(tTransportTaskInspectItemModel.getFileList(), ","));
                    tTransportTaskInspectItem.setInspectId(tTransportTaskInspect.getId());
                    transportTaskInspectItemList.add(tTransportTaskInspectItem);
                }
                if (transportTaskInspectItemList.size()>0){
                    tTransportTaskInspectItemMapper.insertList(transportTaskInspectItemList);
                }

                // 更新任务表的点检次数
                TTransportTaskVm tTransportTaskVm = tTransportTaskMapper.selectTTransportTaskById(tTransportTaskInspectModel.getTaskId());
                TTransportTaskModel tTransportTask = new TTransportTaskModel();
                BeanUtils.copyProperties(tTransportTaskVm, tTransportTask);

                if (tTransportTaskInspectModel.getType()==1){
                    // 更新发车点检数
                    tTransportTask.setStartInspectCount(tTransportTask.getStartInspectCount() + 1);
                }else if (tTransportTaskInspectModel.getType()==2){
                    // 更新在途点检数
                    tTransportTask.setProcessInspectCount(tTransportTask.getProcessInspectCount() + 1);
                }else if (tTransportTaskInspectModel.getType()==3){
                    // 更新归队点检数
                    tTransportTask.setEndInspectCount(tTransportTask.getEndInspectCount() + 1);
                }
                this.updateTTransportTask(tTransportTask);
            }
        } catch (BeansException e) {
            throw e;
        }
        return AjaxResult.success();
    }

    /**
     * @return otmDriverModels 司机数据集合
     * @describe OTM 车队司机基础数据
     * @author DongCL
     * @date 2020-12-24 13:29
     */
    @Override
    public List<OTMDriverModel> OTMCarDriverList() {

        // 驾驶员数据
        TableDataInfo tableDataInfo = personClient.OTMCarDriverList();
        // 返回驾驶员集合
        List<OTMDriverModel> otmDriverModels = new ArrayList<>();
        if (tableDataInfo.getTotal() > 0) {
            List<OTMDriverModel> list = (List<OTMDriverModel>) tableDataInfo.getRows();
            for (Object object : list) {
                // 将list中的数据转成json字符串 再将json转成需要的对象
                OTMDriverModel dDriver = JSONObject.parseObject(JSON.toJSONString(object), OTMDriverModel.class);
                OTMDriverModel otmDriverModel = new OTMDriverModel();
                BeanUtils.copyProperties(dDriver, otmDriverModel);
                otmDriverModels.add(otmDriverModel);
            }
        }
        return otmDriverModels;
    }

    /**
     * @return otmDriverModels 车辆数据集合
     * @describe OTM 车队车辆基础数据
     * @author DongCL
     * @date 2020-12-24 14:13
     */
    @Override
    public List<OTMVehicleModel> OTMCarVehicleList() {
        // 驾驶员数据
        List<OTMVehicleModel> cVehicles = cVehicleMapper.selectOTMVehicleListAll();
        // 返回驾驶员集合
        List<OTMVehicleModel> otmVehicleModels = new ArrayList<>();
        if (!StringUtils.isArray(cVehicles)) {
            cVehicles.stream().forEach(c -> {
                OTMVehicleModel otmVehicleModel = new OTMVehicleModel();
                BeanUtils.copyProperties(c, otmVehicleModel);
                otmVehicleModels.add(otmVehicleModel);
            });
        }
        return otmVehicleModels;
    }

    /**
     * @return
     * @describe OTM 车队线路基础数据
     * @author DongCL
     * @date 2020-12-25 09:37
     */
    @Override
    public ESBResult OTMCarRouteList(OTMRouteModel otmRouteModel) {

        if (StringUtils.isEmpty(otmRouteModel.getX_LANE_XID())){
            return ESBResult.error("请传入线路编码");
        }
        try {
            otmRouteModel.setSOURCE_LOCATION_NAME(otmRouteModel.getSOURCE_PROVINCE() + otmRouteModel.getSOURCE_CITY() + otmRouteModel.getSOURCE_DISTRICT() + otmRouteModel.getSOURCE_LOCATION_NAME());
            otmRouteModel.setDEST_LOCATION_NAME(otmRouteModel.getDEST_PROVINCE() + otmRouteModel.getDEST_CITY() + otmRouteModel.getDEST_DISTRICT() + otmRouteModel.getDEST_LOCATION_NAME());

            List<OTMRouteModel>otmRouteModels = new ArrayList<>();
            otmRouteModels.add(otmRouteModel);

            if (itRouteService.insertList(otmRouteModels)>0){
                this.insertSysLog("OTM","操作成功","OTM ->> VMS ->> 线路同步接口");
            }
            return ESBResult.success();
        } catch (Exception e) {
//            return ESBResult.error(e.getMessage());
            this.insertSysLog("OTM","操作失败","OTM ->> VMS ->> 线路同步接口");
            return ESBResult.error("操作失败");
        }
    }

    /**
     * @param otmtTransportTaskModel
     * @return
     * @describe OTM 车队运输任务
     * @author DongCL
     * @date 2020-12-28 14:25
     */
    @Override
    public ESBResult OTMCarTransportTask(OTMTTransportTaskModel otmtTransportTaskModel) {

        if (StringUtils.isEmpty(otmtTransportTaskModel.getSHIPMENT_GID())){
            return ESBResult.error("运单ID不能为空!");
        }
        try {
            TTransportTask tTransportTask = tTransportTaskMapper.selectTTransportTaskByXid(otmtTransportTaskModel.getSHIPMENT_XID());
            if (tTransportTask!= null){
                return ESBResult.error("运单号已存在!");
            }
            if(StringUtils.isNull(otmtTransportTaskModel.getDEST_PHONE_NUMBER())){
                return ESBResult.error("请输入收货联系电话");
            }
            if(!VerifyCodeUtil.isPhone(otmtTransportTaskModel.getDEST_PHONE_NUMBER())) {
                return ESBResult.error("请输入正确的收货联系电话");
            }

            if(StringUtils.isNull(otmtTransportTaskModel.getSOURCE_PHONE_NUMBER())){
                return ESBResult.error("请输入发货联系电话");
            }
            if(!VerifyCodeUtil.isPhone(otmtTransportTaskModel.getSOURCE_PHONE_NUMBER())) {
                return ESBResult.error("请输入正确的发货联系电话");
            }
            if(!VerifyCodeUtil.isPhone(otmtTransportTaskModel.getDRIVER_NUMBER())) {
                return ESBResult.error("请输入正确的司机电话");
            }
            if (!StringUtils.isEmpty(otmtTransportTaskModel.getASSISTANT_DRIVER_NUMBER())){
                if(!VerifyCodeUtil.isPhone(otmtTransportTaskModel.getASSISTANT_DRIVER_NUMBER())) {
                    return ESBResult.error("请输入正确的副司机电话");
                }
            }
            if (!StringUtils.isEmpty(otmtTransportTaskModel.getX_LANE_GID())){
                TRoute tRoute = itRouteService.selectTRouteById(otmtTransportTaskModel.getX_LANE_GID());
                if (tRoute == null){
                    return ESBResult.error("线路不存在！");
                }
                // 更新最少点检次数
                otmtTransportTaskModel.setProcessInspectMinCount(tRoute.getMinInspect());
            }
            if (!StringUtils.isNull(otmtTransportTaskModel)) {
                // 主车信息
                CVehicle mainCVehicle = cVehicleMapper.queryCVehicleByLicensePlate(otmtTransportTaskModel.getEQUIPMENT_NUMBER());
                if (mainCVehicle == null){
                    return ESBResult.error("主车信息不存在");
                }
                if (!StringUtils.isNull(mainCVehicle)) {
                    if (!StringUtils.isEmpty(mainCVehicle.getId())) {
                        otmtTransportTaskModel.setMainVehicleId(mainCVehicle.getId());
                    }
                    if (!StringUtils.isEmpty(mainCVehicle.getVehicleModel())) {
                        otmtTransportTaskModel.setMainVehicleModel(mainCVehicle.getVehicleModel());
                    }
                    if (!StringUtils.isEmpty(mainCVehicle.getVehicleModelName())) {
                        otmtTransportTaskModel.setMainVehicleModelName(mainCVehicle.getVehicleModelName());
                    }
                }
                // 挂车信息
                CVehicle hangCVehicle = cVehicleMapper.queryCVehicleByLicensePlate(otmtTransportTaskModel.getVEHICLE_PLATE_NUMBER());
                if (!StringUtils.isNull(hangCVehicle)) {
                    if (!StringUtils.isEmpty(hangCVehicle.getId())) {
                        otmtTransportTaskModel.setHangVehicleId(hangCVehicle.getId());
                    }
                    if (!StringUtils.isEmpty(hangCVehicle.getVehicleModel())) {
                        otmtTransportTaskModel.setHangVehicleModel(hangCVehicle.getVehicleModel());
                    }
                    if (!StringUtils.isEmpty(hangCVehicle.getVehicleModelName())) {
                        otmtTransportTaskModel.setHangVehicleModelName(hangCVehicle.getVehicleModelName());
                    }
                }

                // 主司机信息
                DDriver mainDriver = cVehicleMapper.queryCDriverByOTMPhone(otmtTransportTaskModel.getDRIVER_NUMBER());
                if (mainDriver != null ) {
                    if (!StringUtils.isEmpty(mainDriver.getId())) {
                        otmtTransportTaskModel.setDriverId(mainDriver.getId());

                        // 6  驾驶员消息节点
                        idDriverMsgService.insertDDriverMsg(6L,otmtTransportTaskModel.getSHIPMENT_GID(),mainDriver.getId(),"运输任务","");
                    }
                }else {
                    return ESBResult.error("驾驶员信息不存在");
                }
                // 副司机信息
                DDriver assistantDriver = cVehicleMapper.queryCDriverByOTMPhone(otmtTransportTaskModel.getASSISTANT_DRIVER_NUMBER());
                if (!StringUtils.isNull(assistantDriver)) {
                    if (!StringUtils.isEmpty(assistantDriver.getId())) {
                        otmtTransportTaskModel.setViceDriverId(assistantDriver.getId());

                        // 6  驾驶员消息节点
                        idDriverMsgService.insertDDriverMsg(6L,otmtTransportTaskModel.getSHIPMENT_GID(),assistantDriver.getId(),"运输任务","");
                    }
                }

                // 是否外包
                if ("专业运营部".equals(otmtTransportTaskModel.getSERVPROV_NAME())) {
                    otmtTransportTaskModel.setBusinessType(1L);
                } else {
                    otmtTransportTaskModel.setBusinessType(2L);
                }

                // 如果类型是加班 则将数据插入到加班表中
                if ("加班".equals(otmtTransportTaskModel.getSHIPMENT_TYPE())){
                    TTransportTaskOvertimeModel convert = otmtTransportTaskModel.convert();
                    tTransportTaskOvertimeMapper.insertTTransportTaskOvertime(convert);

                    // 加班绩效
                    performanceOverTimeRecord(otmtTransportTaskModel,convert.getId());

                    return ESBResult.success(this.insertSysLog("OTM","操作成功","OTM ->> VMS ->> 运输任务(加班)同步接口"));
                }else {
                    // 长途任务记录
                    otmtTransportTaskModel.setSHIPMENT_TYPE("1");
                    tTransportTaskMapper.insertOTMTransportTask(otmtTransportTaskModel);

                    // 判断任务是否符合绩效条件, 若符合则插入长途绩效记录
                    performanceRecord(otmtTransportTaskModel);

                    return ESBResult.success(this.insertSysLog("OTM","操作成功","OTM ->> VMS ->> 运输任务同步接口"));
                }

            }else {
                this.insertSysLog("OTM","操作失败","OTM ->> VMS ->> 运输任务同步接口");
                return ESBResult.error("传入数据不能为空");
            }
        } catch (Exception e) {
//            return ESBResult.error(e.getMessage());
            this.insertSysLog("OTM","操作失败","OTM ->> VMS ->> 运输任务同步接口");
            return ESBResult.error("操作失败");
        }
    }

    /**
     * @param otmTransportTaskOrderModel
     * @return
     * @describe OTM 车队运输任务订单数据
     * @author DongCL
     * @date 2020-12-29 10:54
     */
    @Override
    public ESBResult OTMCarTransportTaskOrder(OTMTransportTaskOrderModel otmTransportTaskOrderModel) {

        if (StringUtils.isEmpty(otmTransportTaskOrderModel.getTEMPLATE_ID())){
            return ESBResult.error("请传入接口表主键");
        }
        try {
            if (StringUtils.isNull(otmTransportTaskOrderModel.getSOURCE_PHONE_NUMBER())){
                return ESBResult.error("请输入发货联系电话");
            }
            if(!VerifyCodeUtil.isPhone(otmTransportTaskOrderModel.getSOURCE_PHONE_NUMBER())) {
                return ESBResult.error("请输入正确的发货联系电话");
            }

            if (StringUtils.isNull(otmTransportTaskOrderModel.getDEST_PHONE_NUMBER())){
                return ESBResult.error("请输入收货联系电话");
            }
            if(!VerifyCodeUtil.isPhone(otmTransportTaskOrderModel.getDEST_PHONE_NUMBER())) {
                return ESBResult.error("请输入正确的收货联系电话");
            }
            if (!StringUtils.isNull(otmTransportTaskOrderModel)){

                if (tTransportTaskOrderMapper.insertOTMTransportTaskOrder(otmTransportTaskOrderModel)>0){
                    this.insertSysLog("OTM","操作成功","OTM ->> VMS ->> 车队运输任务订单同步接口");
                }
                return ESBResult.success();
            }else {
                this.insertSysLog("OTM","操作失败","OTM ->> VMS ->> 车队运输任务订单同步接口");
                return ESBResult.error("传入数据不能为空");
            }
        } catch (Exception e) {
//            return ESBResult.error(e.getMessage());
            this.insertSysLog("OTM","操作失败","OTM ->> VMS ->> 车队运输任务订单同步接口");
            return ESBResult.error("操作失败");
        }
    }

    /**
     * @describe OTM 车队运输任务订单明细数据
     * @author DongCL
     * @date 2020-12-28 19:36
     * @param otmTransportTaskOrderPartsModel
     * @return
     */
    @Override
    public ESBResult OTMCarTransportTaskOrderParts(OTMTransportTaskOrderPartsModel otmTransportTaskOrderPartsModel) {
        if (StringUtils.isEmpty(otmTransportTaskOrderPartsModel.getTEMPLATE_ID())){
            return ESBResult.error("请传入接口表主键");
        }
        try {
            if (!StringUtils.isNull(otmTransportTaskOrderPartsModel)){
                // 获取订单业务类型信息
                TTransportTaskOrderVm tTransportTaskOrderVm = tTransportTaskOrderMapper.selectAllByOrderMovementGid(otmTransportTaskOrderPartsModel.getORDER_MOVEMENT_GID());
                if(!StringUtils.isNull(tTransportTaskOrderVm)){
                    otmTransportTaskOrderPartsModel.setBUSINESS_TYPE(tTransportTaskOrderVm.getBusinessType());

                    // 获取任务信息
                    TTransportTaskVm tTransportTaskVm = tTransportTaskMapper.selectTTransportTaskById(tTransportTaskOrderVm.getTaskId());
                    if(!StringUtils.isNull(tTransportTaskVm)){
                        otmTransportTaskOrderPartsModel.setSHIPMENT_GID(tTransportTaskVm.getId());
                        otmTransportTaskOrderPartsModel.setShipmentXid(tTransportTaskVm.getShipmentXid());
                    }
                }
                if (tTransportTaskPartsMapper.insertOTMTransportTaskParts(otmTransportTaskOrderPartsModel)>0){
                    this.insertSysLog("OTM","操作成功","OTM ->> VMS ->> 车队运输任务订单明细同步接口");
                }
                return ESBResult.success();
            }else {
                this.insertSysLog("OTM","操作失败","OTM ->> VMS ->> 车队运输任务订单明细同步接口");
                return ESBResult.error("传入数据不能为空");
            }
        } catch (Exception e) {
//            return ESBResult.error(e.getMessage());
            this.insertSysLog("操作失败","操作成功","OTM ->> VMS ->> 车队运输任务订单明细同步接口");
            return ESBResult.error("操作失败");
        }
    }

    /**
     * @describe 更新任务时间
     * @author DongCL
     * @date 2020-12-29 13:41
     * @param
     * @return
     */
    @Override
    public ESBResult UpdateOTMCarTransportTask(OTMTTransportTaskTimeModel otmtTransportTaskTimeModel) {
        try {
            TTransportTask tTransportTaskByXid = tTransportTaskMapper.selectTTransportTaskByXid(otmtTransportTaskTimeModel.getSHIPMENT_XID());
            if (tTransportTaskByXid == null){
                return ESBResult.error("任务不存在!");
            }
            // 更新任务状态
            if (StringUtils.isNotNull(otmtTransportTaskTimeModel.getDelivery_date())){
                // 1进行中 2已结束
                otmtTransportTaskTimeModel.setStatus("2");
            }
            tTransportTaskMapper.UpdateOTMCarTransportTask(otmtTransportTaskTimeModel);

            // 更新加班任务时间
            /*TTransportTaskOvertimeModel tTransportTaskOvertimeModel = new TTransportTaskOvertimeModel();
            tTransportTaskOvertimeModel.setShipmentXid(otmtTransportTaskTimeModel.getSHIPMENT_XID());
            if (StringUtils.isNotNull(otmtTransportTaskTimeModel.getStart_shipping_date())){
                tTransportTaskOvertimeModel.setTaskStartTime(otmtTransportTaskTimeModel.getStart_shipping_date());
            }
            if (StringUtils.isNotNull(otmtTransportTaskTimeModel.getDelivery_date())){
                tTransportTaskOvertimeModel.setTaskEndTime(otmtTransportTaskTimeModel.getDelivery_date());
            }
            tTransportTaskOvertimeMapper.UpdateOTMCarTransportTask(tTransportTaskOvertimeModel);
*/
            // 更新长途绩效时间
            TTransportTask tTransportTask = tTransportTaskMapper.selectTTransportTaskByXid(otmtTransportTaskTimeModel.getSHIPMENT_XID());
            OTMTTransportTaskModel otmtTransportTaskModel = new OTMTTransportTaskModel();
            otmtTransportTaskModel.setSHIPMENT_XID(otmtTransportTaskTimeModel.getSHIPMENT_XID());
            otmtTransportTaskModel.setSHIPMENT_GID(tTransportTask.getId());
            otmtTransportTaskModel.setACTUAL_DEPARTURE(otmtTransportTaskTimeModel.getStart_shipping_date());
            otmtTransportTaskModel.setACTUAL_ARRIVAL(otmtTransportTaskTimeModel.getDelivery_date());

            if (StringUtils.isNull(otmtTransportTaskTimeModel.getDelivery_date())){
                List<CVehicle> cVehicleArrayList = new ArrayList<>();
                if (StringUtils.isNotNull(tTransportTask.getMainVehicleId())){
                    CVehicle cVehicle = new CVehicle();
                    cVehicle.setId(tTransportTask.getMainVehicleId());
                    cVehicle.setVehicleStatus(1L); //运输中
                    cVehicleArrayList.add(cVehicle);
                }
                if (StringUtils.isNotNull(tTransportTask.getHangVehicleId())){
                    CVehicle cVehicle = new CVehicle();
                    cVehicle.setId(tTransportTask.getHangVehicleId());
                    cVehicle.setVehicleStatus(1L); //运输中
                    cVehicleArrayList.add(cVehicle);
                }
                // 更新车辆状态
                cVehicleMapper.updateByVehicleStatusList(cVehicleArrayList);
            }else {
                List<CVehicle> cVehicleArrayList = new ArrayList<>();
                if (StringUtils.isNotNull(tTransportTask.getMainVehicleId())){
                    CVehicle cVehicle = new CVehicle();
                    cVehicle.setId(tTransportTask.getMainVehicleId());
                    cVehicle.setVehicleStatus(4L); //空闲中
                    cVehicleArrayList.add(cVehicle);
                }
                if (StringUtils.isNotNull(tTransportTask.getHangVehicleId())){
                    CVehicle cVehicle = new CVehicle();
                    cVehicle.setId(tTransportTask.getHangVehicleId());
                    cVehicle.setVehicleStatus(4L); //空闲中
                    cVehicleArrayList.add(cVehicle);
                }
                // 更新车辆状态
                cVehicleMapper.updateByVehicleStatusList(cVehicleArrayList);
            }
            personClient.updateTaskTime(otmtTransportTaskModel);
            return ESBResult.success(this.insertSysLog("OTM","操作成功","OTM ->> VMS ->> 更新任务时间接口"));
        } catch (Exception e) {
//            return ESBResult.error(e.getMessage());
            this.insertSysLog("OTM","操作失败","OTM ->> VMS ->> 更新任务时间接口");
            return ESBResult.error("操作失败");
        }
    }

    /**
     * @describe 同步驾驶员数据->OTM
     * @author DongCL
     * @date 2021-01-06 13:37
     * @param dDriver
     * @return OTMDriverModel
     */
    @Override
    public int OTMCarDriver(DDriver dDriver) {
        TMSDriverDto tmsDriverDto = dDriver.TmsConvert();
        // 驾驶员信息
        TMSMsgBody tmsMsgBody = new TMSMsgBody();
        List<TMSDriverDto> list = new ArrayList<>();
        list.add(tmsDriverDto);
        tmsMsgBody.setDriverDto(list);

        // header And msgBody
        TMSParams tmsParams = new TMSParams();
        tmsParams.setMsgBody(tmsMsgBody);
        ESBHeader esbHeader = new ESBHeader();
        esbHeader.setMessageID(UUID.randomUUID()+"");
        esbHeader.setInterfaceID("VMS-OTM-001");
        esbHeader.setTransID(tmsDriverDto.getSourceId());
        esbHeader.setSender("VMS");
        esbHeader.setReceiver("OTM");
        tmsParams.setMsgHeader(esbHeader);
        String params = JSONUtil.toJsonStr(tmsParams);

        // 最外层
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("headers", esbHeader);
        paramMap.put("payload",params);
        String json = JSONUtil.toJsonStr(paramMap);
        System.out.println("入参==="+json);
        System.out.println("请求地址=================  http://"+request_url+"/OTM/VMS/VMS2OTM_Driver/ProxyServices/DriverPS");
        String result = HttpUtil.post("http://"+request_url+"/OTM/VMS/VMS2OTM_Driver/ProxyServices/DriverPS", json);
        HashMap<String,HashMap<String,Object>>hashMap = JSONObject.parseObject(result, HashMap.class);
        TMSParams responseEsbData = JSONObject.parseObject(JSONObject.toJSONString(hashMap.get("responseEsbData")), TMSParams.class);
        if (responseEsbData == null || responseEsbData.getResultType()==null || !"0".equals(responseEsbData.getResultType())){
            this.insertSysLog("VMS","操作失败","VMS ->> OTM ->> 同步驾驶员数据接口");
            return HttpStatus.OTM_ERROR;
        }
        this.insertSysLog("VMS","操作成功","VMS ->> OTM ->> 同步驾驶员数据接口");
        return HttpStatus.OTM_SUCCESS;
    }

    /**
     * @describe 同步车辆数据->OTM
     * @author DongCL
     * @date 2021-01-06 13:37
     * @param cVehicle
     * @return OTMDriverModel
     */
    @Override
    public int OTMCarVehicle(CVehicle cVehicle) {

        TMSCarDto tmsCarDto = cVehicle.TMSConvert();
        // 车辆信息
        TMSMsgBody tmsMsgBody = new TMSMsgBody();
        List<TMSCarDto> list = new ArrayList<>();
        list.add(tmsCarDto);
        tmsMsgBody.setEquipmentDto(list);

        // header And msgBody
        TMSParams tmsParams = new TMSParams();
        tmsParams.setMsgBody(tmsMsgBody);
        ESBHeader esbHeader = new ESBHeader();
        esbHeader.setMessageID(UUID.randomUUID()+"");
        esbHeader.setInterfaceID("VMS-OTM-002");
        esbHeader.setTransID(UUID.randomUUID()+"");
        esbHeader.setSender("VMS");
        esbHeader.setReceiver("OTM");
        tmsParams.setMsgHeader(esbHeader);
        String params = JSONUtil.toJsonStr(tmsParams);

        // 最外层
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("headers", esbHeader);
        paramMap.put("payload",params);
        String json = JSONUtil.toJsonStr(paramMap);
        System.out.println("OTM入参===="+json);
        System.out.println("请求地址=================  http://"+request_url+"/OTM/VMS/VMS2OTM_Car/ProxyServices/CarPS");
        String result = HttpUtil.post("http://"+request_url+"/OTM/VMS/VMS2OTM_Car/ProxyServices/CarPS", json);
        System.out.println("OTM出参===="+result);
        HashMap<String,HashMap<String,Object>>hashMap = JSONObject.parseObject(result, HashMap.class);
        TMSParams responseEsbData = JSONObject.parseObject(JSONObject.toJSONString(hashMap.get("responseEsbData")), TMSParams.class);
        if (responseEsbData == null || responseEsbData.getResultType()==null || !"0".equals(responseEsbData.getResultType())){
            this.insertSysLog("VMS","操作失败","VMS ->> OTM ->> 同步车辆数据接口");
            return HttpStatus.OTM_ERROR;
        }
        this.insertSysLog("VMS","操作成功","VMS ->> OTM ->> 同步车辆数据接口");
        return HttpStatus.OTM_SUCCESS;
    }

    /**
     * @describe OTM 车队运输任务站点
     * @author DongCL
     * @date 2021-01-07 13:34
     * @param otmTransportTaskShipmentStopModel
     * @return AjaxResult
     */
    @Override
    public ESBResult OTMCarTransportTaskShipmentStop(OTMTransportTaskShipmentStopModel otmTransportTaskShipmentStopModel) {
        if (StringUtils.isEmpty(otmTransportTaskShipmentStopModel.getTEMPLATE_ID())){
            return ESBResult.error("请传入接口表主键");
        }
        try {
            if (tTransportTaskShipmentStopMapper.insertOTMTransportTaskShipmentStop(otmTransportTaskShipmentStopModel)>0){
                this.insertSysLog("OTM","操作成功","OTM ->> VMS ->> 车队运输任务站点同步接口");
            }
            return ESBResult.success();
        } catch (Exception e) {
//            return ESBResult.error(e.getMessage());
            this.insertSysLog("OTM","操作失败","OTM ->> VMS ->> 车队运输任务站点同步接口");
            return ESBResult.error("操作失败");
        }
    }

    /**
     * @describe OTM 车队运输任务托盘
     * @author DongCL
     * @date 2021-01-07 13:35
     * @param otmTransportTaskTrayModel
     * @return AjaxResult
     */
    @Override
    public ESBResult OTMCarTransportTaskTray(OTMTransportTaskTrayModel otmTransportTaskTrayModel) {
        if (StringUtils.isEmpty(otmTransportTaskTrayModel.getTEMPLATE_ID())){
            return ESBResult.error("请传入接口表主键");
        }
        try {
            TTransportTaskOrderVm tTransportTaskOrderVm = tTransportTaskOrderMapper.selectAllByOrderMovementGid(otmTransportTaskTrayModel.getORDER_MOVEMENT_GID());
            if(!StringUtils.isNull(tTransportTaskOrderVm)){
                if (StringUtils.isNotEmpty(tTransportTaskOrderVm.getTaskId())){
                    // 任务ID
                    otmTransportTaskTrayModel.setSHIPMENT_GID(tTransportTaskOrderVm.getTaskId());
                    // 任务编号
                    TTransportTaskVm tTransportTaskVm = tTransportTaskMapper.selectTTransportTaskById(tTransportTaskOrderVm.getTaskId());
                    otmTransportTaskTrayModel.setShipmentXid(tTransportTaskVm.getShipmentXid());
                }
            }
            if (tTransportTaskTrayMapper.insertOTMTransportTaskTray(otmTransportTaskTrayModel)>0){
                this.insertSysLog("OTM","操作成功","OTM ->> VMS ->> 车队运输任务托盘同步接口");
            }
            return ESBResult.success();
        } catch (Exception e) {
//            return ESBResult.error(e.getMessage());
            this.insertSysLog("OTM","操作失败","OTM ->> VMS ->> 车队运输任务托盘同步接口");
            return ESBResult.error("操作失败");
        }
    }

    /**
     * @describe OTM 车队运输任务运费
     * @author DongCL
     * @date 2021-01-07 13:35
     * @param otmTransportTaskCostModel
     * @return AjaxResult
     */
    @Override
    public ESBResult OTMCarTransportTaskCost(OTMTransportTaskCostModel otmTransportTaskCostModel) {
        if (StringUtils.isEmpty(otmTransportTaskCostModel.getTEMPLATE_ID())){
            return ESBResult.error("请传入接口表主键");
        }
        try {
            // 去查询任务信息
            TTransportTaskVm tTransportTaskVm = tTransportTaskMapper.selectTTransportTaskById(otmTransportTaskCostModel.getSHIPMENT_GID());
            if(!StringUtils.isNull(tTransportTaskVm)){

                // 更新任务表中的费用
                tTransportTaskVm.setTotalAmount(new BigDecimal(otmTransportTaskCostModel.getTOTAL_AMOUNT()));
                tTransportTaskVm.setAmount(new BigDecimal(otmTransportTaskCostModel.getAMOUNT()));
                TTransportTaskModel tTransportTaskModel = new TTransportTaskModel();
                BeanUtils.copyProperties(tTransportTaskVm,tTransportTaskModel);
                tTransportTaskMapper.updateTTransportTask(tTransportTaskModel);

                // 获取运单号
                otmTransportTaskCostModel.setShipment_xid(tTransportTaskVm.getShipmentXid());
            }
            if (tTransportTaskCostMapper.insertOTMTransportTaskCost(otmTransportTaskCostModel)>0){
                this.insertSysLog("OTM","操作成功","OTM ->> VMS ->> 车队运输任务运费同步接口");
            }
            return ESBResult.success();
        } catch (BeansException e) {
//            return ESBResult.error(e.getMessage());
            this.insertSysLog("OTM","操作失败","OTM ->> VMS ->> 车队运输任务运费同步接口");
            return ESBResult.error("操作失败");
        }
    }


    /**
     * @describe OTM 车队短途台班
     * @author DongCL
     * @date 2020-12-28 14:25
     * @param otmtTransportShortTaskModel
     * @return
     */
    @Override
    public ESBResult OTMCarTransportShortTask(OTMTTransportShortTaskModel otmtTransportShortTaskModel) {
        try {
            if(!VerifyCodeUtil.isPhone(otmtTransportShortTaskModel.getPhone_number())) {
                return ESBResult.error("请输入正确的手机号码");
            }
            DDriver dDriver = cVehicleMapper.queryCDriverByOTMPhone(otmtTransportShortTaskModel.getPhone_number());
            DPerformanceShortHaulRecord dPerformanceShortHaulRecord = new DPerformanceShortHaulRecord();
            dPerformanceShortHaulRecord.setId(otmtTransportShortTaskModel.getTEMPLATE_ID());
            dPerformanceShortHaulRecord.setPerformanceDate(otmtTransportShortTaskModel.getMachine_date());
            dPerformanceShortHaulRecord.setDriverId(dDriver.getId());
            dPerformanceShortHaulRecord.setDriverName(dDriver.getName());
            dPerformanceShortHaulRecord.setDriverTypeId(dDriver.getDriverTypeId());
            dPerformanceShortHaulRecord.setDriverTypeName(dDriver.getDriverTypeName());
            dPerformanceShortHaulRecord.setNumber(otmtTransportShortTaskModel.getTotal_machine_time_count());
            dPerformanceShortHaulRecord.setAmount(otmtTransportShortTaskModel.getMachine_time_price());

            personClient.submitShortRecord(dPerformanceShortHaulRecord);
            return ESBResult.success(this.insertSysLog("OTM","操作成功","OTM ->> VMS ->> 车队短途台班同步接口"));
        } catch (Exception e) {
//            return ESBResult.error(e.getMessage());
            this.insertSysLog("OTM","操作失败","OTM ->> VMS ->> 车队短途台班同步接口");
            return ESBResult.error("操作失败");
        }
    }


    private ESBResult performanceOverTimeRecord(OTMTTransportTaskModel otmtTransportTaskModel, String id) {
        TTransportTaskOvertimeVm tTransportTaskOvertimeVm = tTransportTaskOvertimeMapper.selectTTransportTaskOvertimeById(id);
        if (tTransportTaskOvertimeVm != null) {
            try {

                // 生成绩效
                DPerformanceOvertimeRecord dPerformanceRecord = new DPerformanceOvertimeRecord();
                dPerformanceRecord.setId(UUID.randomUUID()+"");
                dPerformanceRecord.setTaskId(otmtTransportTaskModel.getSHIPMENT_GID());
                dPerformanceRecord.setDriverId(tTransportTaskOvertimeVm.getDriverId());
                dPerformanceRecord.setDriverName(tTransportTaskOvertimeVm.getDriverName());
                dPerformanceRecord.setPerformanceDate(new Date());
                dPerformanceRecord.setAmount(new BigDecimal(otmtTransportTaskModel.getEXTEND_TEXT3() == null ? "0" : otmtTransportTaskModel.getEXTEND_TEXT3()));

                // 获取主车信息
                if (StringUtils.isNotNull(tTransportTaskOvertimeVm.getMainVehicleId())) {
                    CVehicle cVehicle = cVehicleMapper.selectCVehicleById(tTransportTaskOvertimeVm.getMainVehicleId());
                    dPerformanceRecord.setMainVehicleId(cVehicle.getId());
                    dPerformanceRecord.setMainLicensePlate(cVehicle.getLicensePlate());
                }
                // 获取挂车信息
                if (StringUtils.isNotNull(tTransportTaskOvertimeVm.getHangVehicleId())) {
                    CVehicle hangVehicle = cVehicleMapper.selectCVehicleById(tTransportTaskOvertimeVm.getHangVehicleId());
                    dPerformanceRecord.setHangVehicleId(hangVehicle.getId());
                    dPerformanceRecord.setHangLicensePlate(hangVehicle.getLicensePlate());
                }
                personClient.createPerformanceOverTimeRecord(dPerformanceRecord);
            } catch (Exception e) {
                return ESBResult.error("操作失败");
            }
        }
        return ESBResult.success();
    }

    private ESBResult performanceRecord(OTMTTransportTaskModel otmtTransportTaskModel) {

        TTransportTaskVm tTransportTaskVm = tTransportTaskMapper.selectTTransportTaskById(otmtTransportTaskModel.getSHIPMENT_GID());
        if (tTransportTaskVm != null){
            try {
                // 获取主车信息
                CVehicle cVehicle = cVehicleMapper.selectCVehicleById(otmtTransportTaskModel.getMainVehicleId());
                // 根据 车型、线路、是否有对班司机查询一条绩效信息
                List<DPerformance> dPerformances = personClient.PerformanceDetail(cVehicle.getVehicleModel(), tTransportTaskVm.getRouteId(), tTransportTaskVm.getViceDriverId() == null ? 2L : 1L);
                if (dPerformances != null && dPerformances.size() > 0) {
                    DPerformance dPerformance = dPerformances.stream().findFirst().orElse(null);
                    // 生成绩效
                    DPerformanceRecord dPerformanceRecord = new DPerformanceRecord();
                    dPerformanceRecord.setId(UUID.randomUUID()+"");
                    dPerformanceRecord.setDriverId(tTransportTaskVm.getDriverId());
                    dPerformanceRecord.setDriverName(tTransportTaskVm.getDriverName());
                    dPerformanceRecord.setTaskId(tTransportTaskVm.getId());
                    dPerformanceRecord.setPerformanceId(dPerformance.getId());
                    dPerformanceRecord.setPerformanceName(dPerformance.getName());
                    dPerformanceRecord.setTaskStartTime(tTransportTaskVm.getTaskStartTime());
                    dPerformanceRecord.setTaskStatus(2L); //正常
                    dPerformanceRecord.setViceDriverId(tTransportTaskVm.getViceDriverId() == null ? "" : tTransportTaskVm.getViceDriverId());
                    dPerformanceRecord.setViceDriverName(tTransportTaskVm.getViceDriverName() == null ? "" : tTransportTaskVm.getViceDriverName());
                    dPerformanceRecord.setMainVehicleId(tTransportTaskVm.getMainVehicleId());
                    dPerformanceRecord.setMainLicensePlate(tTransportTaskVm.getMainLicensePlate());
                    dPerformanceRecord.setHangVehicleId(tTransportTaskVm.getHangVehicleId());
                    dPerformanceRecord.setHangLicensePlate(tTransportTaskVm.getMainLicensePlate());
                    personClient.createPerformanceRecord(dPerformanceRecord);
                }
            } catch (Exception e) {
                return ESBResult.error("操作失败");
            }
        }
        return ESBResult.success();
    }


    /**
     * @describe 上传照片
     * @author DongCL
     * @date 2021-01-20 09:34
     * @param
     * @return
     */
    @Override
    public AjaxResult updatePhoto(TTransportTaskModel tTransportTaskModel) {

        // 删除之前上传的
        sysFileMapper.delelteFilesByRelationId(tTransportTaskModel.getId());

        Date nowDate = DateUtils.getNowDate();
        List<SysFile> sysFiles = new ArrayList<>();
        if (StringUtils.isNotNull(tTransportTaskModel.getLoadingFileList())){
            // 12 装货
            this.uploadPhoto(sysFiles,12L,tTransportTaskModel.getLoadingFileList(),tTransportTaskModel,nowDate);
        }
        if (StringUtils.isNotNull(tTransportTaskModel.getUnloadFilesList())){

            // 13 卸货
            this.uploadPhoto(sysFiles,13L,tTransportTaskModel.getUnloadFilesList(),tTransportTaskModel,nowDate);
        }

        if (sysFiles.size()>0){
            sysFileMapper.insertList(sysFiles);
        }
        return AjaxResult.success();
    }


    private List<SysFile> uploadPhoto(List<SysFile>sysFiles, Long type,List<String>files,TTransportTaskModel tTransportTaskModel, Date nowDate){
        // 插入附件表
        for (String file : files) {
            SysFile sysFile = new SysFile();
            sysFile.setId(UUID.randomUUID()+"");
            sysFile.setFilePath(file);
            sysFile.setRelationId(tTransportTaskModel.getId());
            sysFile.setCreateUser(tTransportTaskModel.getCreateBy());
            sysFile.setCreateTime(nowDate);
            //类型(1,事故照片2,事故其他 3,考勤附件 4,客户合同附件 5供应商合同附件 6供应商评价附件 7交通违章图片 8违章处理图片 9驾驶员档案附件 10车辆档案附件 11保险保单照片 12.任务装货照片 13任务卸货照片 14任务异常情况照片)
            sysFile.setType(type);
            sysFiles.add(sysFile);
        }
        return sysFiles;
    }

    private int insertSysLog(String logBy,String logType,String message){
        SysLog sysLog = new SysLog();
        sysLog.setLogBy(logBy);
        sysLog.setLogType(logType);
        sysLog.setMessage(message);
        return systemClient.insertSysLog(sysLog);
    }

}
