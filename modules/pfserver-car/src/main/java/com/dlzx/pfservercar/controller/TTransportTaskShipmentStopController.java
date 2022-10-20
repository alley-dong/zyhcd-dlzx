package com.dlzx.pfservercar.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.pfservercar.domain.model.TTransportTaskShipmentStopModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskShipmentStopVm;
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
import com.dlzx.pfservercar.domain.TTransportTaskShipmentStop;
import com.dlzx.pfservercar.service.ITTransportTaskShipmentStopService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 任务管理-运输任务站点Controller
 * 
 * @author DongCL
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/transportStop")
public class TTransportTaskShipmentStopController extends BaseController
{
    @Autowired
    private ITTransportTaskShipmentStopService tTransportTaskShipmentStopService;

    /**
     * 查询任务管理-运输任务站点列表
     */
    @PreAuthorize(hasPermi = "system:transportStop:list")
    @GetMapping("/list")
    public TableDataInfo list(TTransportTaskShipmentStopModel tTransportTaskShipmentStop)
    {
        startPage();
        List<TTransportTaskShipmentStopVm> list = tTransportTaskShipmentStopService.selectTTransportTaskShipmentStopList(tTransportTaskShipmentStop);
        return getDataTable(list);
    }

    /**
     * 导出任务管理-运输任务站点列表
     */
    @PreAuthorize(hasPermi = "system:transportStop:export")
    @Log(title = "任务管理-运输任务站点", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TTransportTaskShipmentStopModel tTransportTaskShipmentStop) throws IOException
    {
        List<TTransportTaskShipmentStopVm> list = tTransportTaskShipmentStopService.selectTTransportTaskShipmentStopList(tTransportTaskShipmentStop);
        ExcelUtil<TTransportTaskShipmentStopVm> util = new ExcelUtil<TTransportTaskShipmentStopVm>(TTransportTaskShipmentStopVm.class);
        util.exportExcel(response, list, "transportStop");
    }

    /**
     * 获取任务管理-运输任务站点详细信息
     */
    @PreAuthorize(hasPermi = "system:transportStop:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(tTransportTaskShipmentStopService.selectTTransportTaskShipmentStopById(id));
    }

    /**
     * 新增任务管理-运输任务站点
     */
    @PreAuthorize(hasPermi = "system:transportStop:add")
    @Log(title = "任务管理-运输任务站点", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TTransportTaskShipmentStopModel tTransportTaskShipmentStop)
    {
        return toAjax(tTransportTaskShipmentStopService.insertTTransportTaskShipmentStop(tTransportTaskShipmentStop));
    }

    /**
     * 修改任务管理-运输任务站点
     */
    @PreAuthorize(hasPermi = "system:transportStop:edit")
    @Log(title = "任务管理-运输任务站点", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TTransportTaskShipmentStopModel tTransportTaskShipmentStop)
    {
        return toAjax(tTransportTaskShipmentStopService.updateTTransportTaskShipmentStop(tTransportTaskShipmentStop));
    }

    /**
     * 删除任务管理-运输任务站点
     */
    @PreAuthorize(hasPermi = "system:transportStop:remove")
    @Log(title = "任务管理-运输任务站点", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tTransportTaskShipmentStopService.deleteTTransportTaskShipmentStopByIds(ids));
    }
}
