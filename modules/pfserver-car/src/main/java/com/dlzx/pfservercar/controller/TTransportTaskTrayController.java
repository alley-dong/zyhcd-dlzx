package com.dlzx.pfservercar.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.pfservercar.domain.model.TTransportTaskTrayModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskTrayVm;
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
import com.dlzx.pfservercar.domain.TTransportTaskTray;
import com.dlzx.pfservercar.service.ITTransportTaskTrayService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 任务管理-运输任务托盘Controller
 * 
 * @author DongCL
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/transportTray")
public class TTransportTaskTrayController extends BaseController
{
    @Autowired
    private ITTransportTaskTrayService tTransportTaskTrayService;

    /**
     * 查询任务管理-运输任务托盘列表
     */
    @PreAuthorize(hasPermi = "system:transportTray:list")
    @GetMapping("/list")
    public TableDataInfo list(TTransportTaskTrayModel tTransportTaskTray)
    {
        startPage();
        List<TTransportTaskTrayVm> list = tTransportTaskTrayService.selectTTransportTaskTrayList(tTransportTaskTray);
        return getDataTable(list);
    }

    /**
     * 导出任务管理-运输任务托盘列表
     */
    @PreAuthorize(hasPermi = "system:transportTray:export")
    @Log(title = "任务管理-运输任务托盘", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TTransportTaskTrayModel tTransportTaskTray) throws IOException
    {
        List<TTransportTaskTrayVm> list = tTransportTaskTrayService.selectTTransportTaskTrayList(tTransportTaskTray);
        ExcelUtil<TTransportTaskTrayVm> util = new ExcelUtil<TTransportTaskTrayVm>(TTransportTaskTrayVm.class);
        util.exportExcel(response, list, "transportTray");
    }

    /**
     * 获取任务管理-运输任务托盘详细信息
     */
    @PreAuthorize(hasPermi = "system:transportTray:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(tTransportTaskTrayService.selectTTransportTaskTrayById(id));
    }

    /**
     * 新增任务管理-运输任务托盘
     */
    @PreAuthorize(hasPermi = "system:transportTray:add")
    @Log(title = "任务管理-运输任务托盘", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TTransportTaskTrayModel tTransportTaskTray)
    {
        return toAjax(tTransportTaskTrayService.insertTTransportTaskTray(tTransportTaskTray));
    }

    /**
     * 修改任务管理-运输任务托盘
     */
    @PreAuthorize(hasPermi = "system:transportTray:edit")
    @Log(title = "任务管理-运输任务托盘", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TTransportTaskTrayModel tTransportTaskTray)
    {
        return toAjax(tTransportTaskTrayService.updateTTransportTaskTray(tTransportTaskTray));
    }

    /**
     * 删除任务管理-运输任务托盘
     */
    @PreAuthorize(hasPermi = "system:transportTray:remove")
    @Log(title = "任务管理-运输任务托盘", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tTransportTaskTrayService.deleteTTransportTaskTrayByIds(ids));
    }
}
