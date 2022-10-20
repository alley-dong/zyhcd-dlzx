package com.dlzx.pfservercar.controller.driver_app;

import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.domain.ESBParams;
import com.dlzx.common.core.web.domain.ESBResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.DDriver;
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
import com.dlzx.pfservercar.service.ITTransportTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 类描述：
 * @className TTransportTaskESBController
 * company: 赛拓信息技术有限公司
 * @author：DongCL
 * @date 2020-12-24 13:31
 */
@RestController
@RequestMapping("/esb-api/transportTask")
public class TTransportTaskESBController extends BaseController {

    @Autowired
    private ITTransportTaskService transportTaskService;

    @RequestMapping(path = "/OTMCarDriver",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    @Log(title = "车队运输任务驾驶员基础数据")
    public int OTMCarDriver(@RequestBody DDriver dDriver)
    {
        return transportTaskService.OTMCarDriver(dDriver);
    }

    /**
     * @describe OTM 车队线路基础数据
     * @author DongCL
     * @date 2020-12-25 09:36
     * @param esbParams
     * @return
     */
    @RequestMapping("/OTMCarRouteList")
    @ResponseBody
    @Log(title = "车队线路基础数据")
    public ESBResult carRouteList(@RequestBody ESBParams<OTMRouteModel> esbParams)
    {
        return transportTaskService.OTMCarRouteList(esbParams.getMsgBody());
    }

    /**
     * @describe OTM 车队运输任务
     * @author DongCL
     * @date 2020-12-28 14:25
     * @param esbParams
     * @return
     */
    @RequestMapping("/SubmitOTMCarTTransportTask")
    @ResponseBody
    @Log(title = "车队运输任务数据")
    public ESBResult submitOTMCarTTransportTask(@RequestBody ESBParams<OTMTTransportTaskModel> esbParams)
    {
        return transportTaskService.OTMCarTransportTask(esbParams.getMsgBody());
    }

    /**
     * @describe OTM 车队短途台班
     * @author DongCL
     * @date 2020-12-28 14:25
     * @param esbParams
     * @return
     */
    @RequestMapping("/SubmitOTMCarTTransportShortTask")
    @ResponseBody
    @Log(title = "车队运输短途任务绩效数据")
    public ESBResult submitOTMCarTTransportShortTask(@RequestBody ESBParams<OTMTTransportShortTaskModel> esbParams)
    {
        return transportTaskService.OTMCarTransportShortTask(esbParams.getMsgBody());
    }

    /**
     * @describe OTM 车队运输任务站点
     * @author DongCL
     * @date 2020-01-07 13:06
     * @param
     * @return
     */
    @RequestMapping("/SubmitOTMCarTransportTaskShipmentStop")
    @ResponseBody
    @Log(title = "车队运输任务站点数据")
    public ESBResult submitOTMCarTransportTaskShipmentStop(@RequestBody ESBParams<OTMTransportTaskShipmentStopModel> esbParams)
    {
        return transportTaskService.OTMCarTransportTaskShipmentStop(esbParams.getMsgBody());
    }

    /**
     * @describe OTM 车队运输任务托盘
     * @author DongCL
     * @date 2020-01-07 13:06
     * @param
     * @return
     */
    @RequestMapping("/SubmitOTMCarTransportTaskTray")
    @ResponseBody
    @Log(title = "车队运输任务托盘数据")
    public ESBResult submitOTMCarTransportTaskTray(@RequestBody ESBParams<OTMTransportTaskTrayModel> esbParams)
    {
        return transportTaskService.OTMCarTransportTaskTray(esbParams.getMsgBody());
    }

    /**
     * @describe OTM 车队运输任务运费
     * @author DongCL
     * @date 2020-01-07 13:06
     * @param
     * @return
     */
    @RequestMapping("/SubmitOTMCarTransportTaskCost")
    @ResponseBody
    @Log(title = "车队运输任务运费数据")
    public ESBResult submitOTMCarTransportTaskCost(@RequestBody ESBParams<OTMTransportTaskCostModel> esbParams)
    {
        return transportTaskService.OTMCarTransportTaskCost(esbParams.getMsgBody());
    }

    /**
     * @describe OTM 车队运输任务订单
     * @author DongCL
     * @date 2020-12-28 19:36
     * @param
     * @return
     */
    @RequestMapping("/SubmitOTMCarTransportTaskOrder")
    @ResponseBody
    @Log(title = "车队运输任务订单数据")
    public ESBResult submitOTMCarTransportTaskOrder(@RequestBody ESBParams<OTMTransportTaskOrderModel> esbParams)
    {
        return transportTaskService.OTMCarTransportTaskOrder(esbParams.getMsgBody());
    }
    /**
     * @describe OTM 车队运输任务订单明细数据
     * @author DongCL
     * @date 2020-12-28 19:36
     * @param
     * @return
     */
    @RequestMapping("/SubmitOTMCarTransportTaskOrderParts")
    @ResponseBody
    @Log(title = "车队运输任务订单明细数据")
    public ESBResult submitOTMCarTransportTaskOrderParts(@RequestBody ESBParams<OTMTransportTaskOrderPartsModel> esbParams)
    {
        return transportTaskService.OTMCarTransportTaskOrderParts(esbParams.getMsgBody());
    }



    /**
     * @describe OTM 更新任务时间
     * @author DongCL
     * @date 2020-12-28 19:36
     * @param
     * @return
     */
    @RequestMapping("/UpdateOTMCarTransportTaskTime")
    @ResponseBody
    @Log(title = "更新任务开始时间")
    public ESBResult updateStartOTMCarTransportTask(@RequestBody ESBParams<OTMTTransportTaskTimeModel> esbParams)
    {
        return transportTaskService.UpdateOTMCarTransportTask(esbParams.getMsgBody());
    }



    /**  弃用*/
    /**
     * @remake 弃用
     * @describe OTM 车队司机基础数据
     * @author DongCL
     * @date 2020-12-24 13:29
     * @return otmDriverModels 司机数据集合
     */
    @RequestMapping("/OTMCarDriverList")
    @ResponseBody
    @Log(title = "车队司机基础数据")
    public TableDataInfo carDriverList()
    {
        List<OTMDriverModel> otmDriverModels = transportTaskService.OTMCarDriverList();
        return getDataTable(otmDriverModels);
    }


    /**
     * @remake 弃用
     * @describe OTM 车队车辆基础数据
     * @author DongCL
     * @date 2020-12-24 14:13
     * @return otmVehicleModels 车辆数据集合
     */
    @RequestMapping("/OTMCarVehicleList")
    @ResponseBody
    @Log(title = "车队司机基础数据")
    public TableDataInfo carVehicleList()
    {
        List<OTMVehicleModel> otmVehicleModels = transportTaskService.OTMCarVehicleList();
        return getDataTable(otmVehicleModels);
    }
}
