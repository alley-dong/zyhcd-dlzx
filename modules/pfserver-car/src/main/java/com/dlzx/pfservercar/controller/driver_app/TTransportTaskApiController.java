package com.dlzx.pfservercar.controller.driver_app;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.security.service.DriverTokenService;
import com.dlzx.pfservercar.domain.TTransportTaskInspect;
import com.dlzx.pfservercar.domain.model.TTransportTaskExceptionModel;
import com.dlzx.pfservercar.domain.model.TTransportTaskInspectModel;
import com.dlzx.pfservercar.domain.model.TTransportTaskModel;
import com.dlzx.pfservercar.domain.model.TTransportTaskOvertimeModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskExceptionVm;
import com.dlzx.pfservercar.domain.vm.TTransportTaskInspectVm;
import com.dlzx.pfservercar.domain.vm.TTransportTaskOvertimeVm;
import com.dlzx.pfservercar.domain.vm.TTransportTaskVm;
import com.dlzx.pfservercar.service.ITTransportTaskExceptionService;
import com.dlzx.pfservercar.service.ITTransportTaskInspectService;
import com.dlzx.pfservercar.service.ITTransportTaskOvertimeService;
import com.dlzx.pfservercar.service.ITTransportTaskService;
import com.dlzx.system.api.model.LoginDriver;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
* 应用模块名称
* Copyright: Copyright (C) 2020 DLANGEL, Inc. All rights reserved.
* @author  dcl
* @since 2020-12-21 13:30
*/
@RestController
@RequestMapping("/driver-api/transportTask")
public class TTransportTaskApiController extends BaseController {

    @Autowired
    private ITTransportTaskService transportTaskService;

    @Autowired
    private ITTransportTaskInspectService transportTaskInspectService;

    @Autowired
    private DriverTokenService driverTokenService;

    @Autowired
    private ITTransportTaskExceptionService transportTaskExceptionService;

    @Autowired
    private ITTransportTaskOvertimeService transportTaskOvertimeService;


    /**
     * App 运输任务-驾驶员运输任务列表
     */
    @RequestMapping("/M01TransportTaskList")
    @ResponseBody
    @Log(title = "运输任务-驾驶员运输任务列表")
    public TableDataInfo list(@RequestBody TTransportTaskModel tTransportTaskModel)
    {
        // 获取驾驶员信息
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        startPage(tTransportTaskModel.getPageNum(),tTransportTaskModel.getPageSize());
        tTransportTaskModel.setDriverId(loginDriver.getUserid());
        List<TTransportTaskVm> tTransportTaskVmList = transportTaskService.queryApiTaskList(tTransportTaskModel);
        return getDataTable(tTransportTaskVmList);
    }

    /**
     * App 运输任务-驾驶员运输任务记录列表
     */
    @RequestMapping("/M01TransportTaskRecordList")
    @ResponseBody
    @Log(title = "运输任务-驾驶员运输任务记录列表")
    public TableDataInfo record(@RequestBody TTransportTaskModel tTransportTaskModel)
    {
        // 获取驾驶员信息
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        startPage(tTransportTaskModel.getPageNum(),tTransportTaskModel.getPageSize());
        tTransportTaskModel.setDriverId(loginDriver.getUserid());
        List<TTransportTaskVm> tTransportTaskVmList = transportTaskService.queryApiTaskRecord(tTransportTaskModel);
        return getDataTable(tTransportTaskVmList);
    }

    /**
     * App 运输任务-运输任务详情
     */
    @RequestMapping("/M03TransportTaskInfo")
    @ResponseBody
    @Log(title = "运输任务-运输任务详情")
    public AjaxResult info(@RequestBody TTransportTaskModel tTransportTaskModel)
    {
        TTransportTaskVm tTransportTaskVm = transportTaskService.selectTTransportTaskById(tTransportTaskModel.getId());
        return AjaxResult.success(tTransportTaskVm);
    }

    /**
     * App 运输任务-提交运输点检记录
     */
    @RequestMapping("/M13SubmitTaskInspect")
    @ResponseBody
    @Log(title = "运输任务-提交运输点检记录")
    public AjaxResult submit(@RequestBody TTransportTaskInspectModel tTransportTaskInspectModel)
    {
        if (StringUtils.isNull(tTransportTaskInspectModel.getType())){
            return AjaxResult.error("点检类型不能为空");
        }
        // 获取驾驶员信息
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        tTransportTaskInspectModel.setDriverId(loginDriver.getUserid());
        tTransportTaskInspectModel.setDriverName(loginDriver.getUsername());
        return transportTaskService.submitTTransportTaskInspect(tTransportTaskInspectModel);
    }

    /**
     * App 运输任务-运输任务点检记录列表
     */
    @RequestMapping("/M15TaskInspectList")
    @ResponseBody
    @Log(title = "运输任务-运输任务点检记录列表")
    public TableDataInfo inspectList(@RequestBody TTransportTaskInspectModel tTransportTaskInspectModel)
    {
        startPage(tTransportTaskInspectModel.getPageNum(),tTransportTaskInspectModel.getPageSize());
        List<TTransportTaskInspectVm> tTransportTaskInspectVms = transportTaskInspectService.selectTTransportTaskInspectList(tTransportTaskInspectModel);
        return getDataTable(tTransportTaskInspectVms);
    }

    /**
     * App 运输任务-运输任务点检记录详情
     */
    @RequestMapping("/M17TaskInspectInfo")
    @ResponseBody
    @Log(title = "运输任务-运输任务点检记录详情")
    public AjaxResult inspectInfo(@RequestBody TTransportTaskInspectModel tTransportTaskInspectModel)
    {
        TTransportTaskInspectVm tTransportTaskInspectVm = transportTaskInspectService.selectTTransportTaskInspectById(tTransportTaskInspectModel.getId());
        return AjaxResult.success(tTransportTaskInspectVm);
    }

    /**
     * App 运输任务-运输任务异常情况记录列表
     */
    @RequestMapping("/M06TransportTaskExceptionList")
    @ResponseBody
    @Log(title = "运输任务-运输任务异常情况记录列表")
    public TableDataInfo exceptionList(@RequestBody TTransportTaskExceptionModel tTransportTaskInspectModel)
    {
        startPage(tTransportTaskInspectModel.getPageNum(),tTransportTaskInspectModel.getPageSize());
        // 获取驾驶员信息
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        tTransportTaskInspectModel.setDriverId(loginDriver.getUserid());
        List<TTransportTaskExceptionVm> tTransportTaskExceptionVms = transportTaskExceptionService.selectTTransportTaskExceptionList(tTransportTaskInspectModel);
        return getDataTable(tTransportTaskExceptionVms);
    }

    /**
     * App 运输任务-运输任务异常情况记录详情
     */
    @RequestMapping("/M07TransportTaskExceptionInfo")
    @ResponseBody
    @Log(title = "运输任务-运输任务异常情况记录详情")
    public AjaxResult exceptionInfo(@RequestBody TTransportTaskExceptionModel tTransportTaskExceptionModel)
    {
        TTransportTaskExceptionVm tTransportTaskExceptionVm = transportTaskExceptionService.selectTTransportTaskExceptionById(tTransportTaskExceptionModel.getId());
        return AjaxResult.success(tTransportTaskExceptionVm);
    }


    /**
     * App 运输任务-运输任务异常上报
     */
    @RequestMapping("/M05SubmitTaskException")
    @ResponseBody
    @Log(title = "运输任务-运输任务异常上报")
    public AjaxResult submitException(@RequestBody TTransportTaskExceptionModel tTransportTaskExceptionModel)
    {
        if (StringUtils.isNull(tTransportTaskExceptionModel.getType())){
            return AjaxResult.error("请选择异常类型");
        }else {
            if (tTransportTaskExceptionModel.getType() == 2 && StringUtils.isNull(tTransportTaskExceptionModel.getKilometre())){
                return AjaxResult.error("请填入实际行驶公里数");
            }
        }
        if (StringUtils.isEmpty(tTransportTaskExceptionModel.getRemarks())){
            return AjaxResult.error("请填入异常情况描述");
        }

        tTransportTaskExceptionModel.setStatus(1L);
        tTransportTaskExceptionModel.setId(UUID.randomUUID()+"");
        // 获取驾驶员信息
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        tTransportTaskExceptionModel.setDriverId(loginDriver.getUserid());
        tTransportTaskExceptionModel.setDriverName(loginDriver.getUsername());
        tTransportTaskExceptionModel.setCreateBy(loginDriver.getUsername());
        return AjaxResult.success(transportTaskExceptionService.submitException(tTransportTaskExceptionModel));
    }

    /**
     * App 运输任务-运输任务异常撤销
     */
    @RequestMapping("/M08RevocationTaskException")
    @Log(title = "运输任务-运输任务异常撤销")
    public AjaxResult revocation(@RequestBody TTransportTaskExceptionModel tTransportTaskExceptionModel)
    {
        // 撤回
        tTransportTaskExceptionModel.setStatus(4L);
        return AjaxResult.success(transportTaskExceptionService.updateTTransportTaskException(tTransportTaskExceptionModel));
    }

    /**
     * App 运输任务-运输任务异常再次上报
     */
    @RequestMapping("/M11ResubmitTaskException")
    @Log(title = "运输任务-运输任务异常再次上报")
    public AjaxResult undo(@RequestBody TTransportTaskExceptionModel tTransportTaskExceptionModel)
    {
        // 待审核
        tTransportTaskExceptionModel.setStatus(1L);
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        tTransportTaskExceptionModel.setDriverId(loginDriver.getUserid());
        tTransportTaskExceptionModel.setDriverName(loginDriver.getUsername());
        return AjaxResult.success(transportTaskExceptionService.resubmitTaskException(tTransportTaskExceptionModel));
    }


    /**
     * App 运输任务-运输任务加班记录列表
     */
    @RequestMapping("/TaskOvertimeList")
    @ResponseBody
    @Log(title = "运输任务-运输任务加班记录列表")
    public TableDataInfo overtimeList(@RequestBody TTransportTaskOvertimeModel tTransportTaskOvertimeModel) {
        startPage(tTransportTaskOvertimeModel.getPageNum(), tTransportTaskOvertimeModel.getPageSize());

        // 驾驶员信息
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        tTransportTaskOvertimeModel.setDriverId(loginDriver.getUserid());
        List<TTransportTaskOvertimeVm> tTransportTaskOvertimeVms = transportTaskOvertimeService.queryTTransportTaskOvertimeList(tTransportTaskOvertimeModel);
        return getDataTable(tTransportTaskOvertimeVms);
    }


    /**
     * App 运输任务-运输任务加班记录详情
     */
    @RequestMapping("/TaskOvertimeInfo")
    @ResponseBody
    @Log(title = "运输任务-运输任务加班记录详情")
    public AjaxResult overtimeInfo(@RequestBody TTransportTaskOvertimeModel tTransportTaskOvertimeModel) {
        TTransportTaskOvertimeVm tTransportTaskOvertimeVm = transportTaskOvertimeService.selectTTransportTaskOvertimeById(tTransportTaskOvertimeModel.getId());
        return AjaxResult.success(tTransportTaskOvertimeVm);
    }


    /**
     * App 运输任务-运输任务装/卸货图片上传
     */
    @RequestMapping("/TransportTaskUpload")
    @ResponseBody
    @Log(title = "运输任务-运输任务装/卸货图片上传")
    public AjaxResult TransportTaskUpload(@RequestBody TTransportTaskModel tTransportTaskModel) {
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        tTransportTaskModel.setDriverId(loginDriver.getUserid());
        tTransportTaskModel.setCreateBy(loginDriver.getUsername());
        return transportTaskService.updatePhoto(tTransportTaskModel);
    }

}
