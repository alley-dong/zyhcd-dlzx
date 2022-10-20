package com.dlzx.pfservercar.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.pfservercar.domain.RVehicleConsumeMonth;
import com.dlzx.pfservercar.domain.TTransportTaskExportTemplate;
import com.dlzx.pfservercar.domain.model.TTransportTaskModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskVm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfservercar.domain.TTransportTask;
import com.dlzx.pfservercar.service.ITTransportTaskService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 任务管理-运输任务Controller
 * 
 * @author dlzx
 * @date 2020-12-18
 */
@RestController
@RequestMapping("/transportTask")
public class TTransportTaskController extends BaseController
{
    @Autowired
    private ITTransportTaskService tTransportTaskService;

    /**
     * 驾驶员档案-驾驶员线路记录
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:info")
    @PostMapping("/driverTRouteList")
    public TableDataInfo driverTRouteList(@RequestBody TTransportTaskModel tTransportTaskModel)
    {
        startPage(tTransportTaskModel.getPageNum(),tTransportTaskModel.getPageSize());
        List<TTransportTaskVm> list = tTransportTaskService.queryDriverTRouteList(tTransportTaskModel);
        return getDataTable(list);
    }

    /**
     * 承运商成本核算
     */
    @PreAuthorize(hasPermi = "pfservercar:transportTask:TaskList")
    @PostMapping("/N05TaskList")
    public TableDataInfo N05TaskList(@RequestBody TTransportTask tTransportTask)
    {
        startPage(tTransportTask.getPageNum(),tTransportTask.getPageSize());
        List<TTransportTaskExportTemplate> tTransportTaskExportTemplates = new ArrayList<>();
        List<TTransportTask> list = tTransportTaskService.selectTTransportTaskAccountingList(tTransportTask);
        for (TTransportTask task: list){
            TTransportTaskExportTemplate tTransportTaskExportTemplate = new TTransportTaskExportTemplate();
            tTransportTaskExportTemplate.setShipmentXid(task.getShipmentXid());
            tTransportTaskExportTemplate.setBusinessType(task.getBusinessType());
            tTransportTaskExportTemplate.setRouteName(task.getRouteName());
            tTransportTaskExportTemplate.setServprovName(task.getServprovName());
            tTransportTaskExportTemplate.setAmount(task.getAmount());
            tTransportTaskExportTemplates.add(tTransportTaskExportTemplate);
        }
        return getDataTable(tTransportTaskExportTemplates);
    }

    /**
     * 承运商成本核算-下拉
     */
//    @PreAuthorize(hasPermi = "pfservercar:transportTask:Info")
    @GetMapping("/N05RouteName")
    public TableDataInfo N05RouteName()
    {
        List<TTransportTask> list = tTransportTaskService.selectTTransportTaskRouteName();
        return getDataTable(list);
    }

    /**
     * 导出承运商成本核算
     */
    @PreAuthorize(hasPermi = "pfservercar:transportTask:export")
    @Log(title = "承运商成本核算", businessType = BusinessType.EXPORT)
    @PostMapping("/N05Export")
    public void export(HttpServletResponse response, TTransportTask tTransportTask) throws IOException {
        List<TTransportTaskExportTemplate> tTransportTaskExportTemplates = new ArrayList<>();
        List<TTransportTask> list = tTransportTaskService.selectTTransportTaskAccountingList(tTransportTask);
        for (TTransportTask task: list){
            TTransportTaskExportTemplate tTransportTaskExportTemplate = new TTransportTaskExportTemplate();
            tTransportTaskExportTemplate.setShipmentXid(task.getShipmentXid());
            tTransportTaskExportTemplate.setBusinessType(task.getBusinessType());
            tTransportTaskExportTemplate.setRouteName(task.getRouteName());
            tTransportTaskExportTemplate.setServprovName(task.getServprovName());
            tTransportTaskExportTemplate.setAmount(task.getAmount());
            tTransportTaskExportTemplates.add(tTransportTaskExportTemplate);
        }
        ExcelUtil<TTransportTaskExportTemplate> util = new ExcelUtil<TTransportTaskExportTemplate>(TTransportTaskExportTemplate.class);
        util.exportExcel(response, tTransportTaskExportTemplates, "Carrier accounting");
    }
    
    /**
     * 查询任务管理-运输任务列表
     */
    @PreAuthorize(hasPermi = "pfservercar:transportTask:list")
    @PostMapping("/P04TransportTaskList")
    public TableDataInfo list(@RequestBody TTransportTaskModel tTransportTaskModel)
    {
        startPage(tTransportTaskModel.getPageNum(),tTransportTaskModel.getPageSize());
        List<TTransportTaskVm> list = tTransportTaskService.selectTTransportTaskList(tTransportTaskModel);
        return getDataTable(list);
    }

    /**
     * 获取任务管理-运输任务详细信息
     */
    @PreAuthorize(hasPermi = "pfservercar:transportTask:info")
    @PostMapping(value = "/P04TransportTaskInfo")
    public AjaxResult getInfo(@RequestBody TTransportTaskModel tTransportTaskModel)
    {
        return AjaxResult.success(tTransportTaskService.selectTTransportTaskById(tTransportTaskModel.getId()));
    }

}
