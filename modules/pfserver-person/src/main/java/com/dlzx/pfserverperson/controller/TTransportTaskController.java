package com.dlzx.pfserverperson.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
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
import com.dlzx.pfserverperson.domain.TTransportTask;
import com.dlzx.pfserverperson.service.ITTransportTaskService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 任务管理-运输任务Controller
 * 
 * @author dlzx
 * @date 2020-12-21
 */
@RestController
@RequestMapping("/transportTask")
public class TTransportTaskController extends BaseController
{
    @Autowired
    private ITTransportTaskService tTransportTaskService;

    /**
     * 查询任务管理-运输任务列表
     */
    @PreAuthorize(hasPermi = "system:transportTask:list")
    @GetMapping("/list")
    public TableDataInfo list(TTransportTask tTransportTask)
    {
        startPage();
        List<TTransportTask> list = tTransportTaskService.selectTTransportTaskList(tTransportTask);
        return getDataTable(list);
    }

    /**
     * 导出任务管理-运输任务列表
     */
    @PreAuthorize(hasPermi = "system:transportTask:export")
    @Log(title = "任务管理-运输任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TTransportTask tTransportTask) throws IOException
    {
        List<TTransportTask> list = tTransportTaskService.selectTTransportTaskList(tTransportTask);
        ExcelUtil<TTransportTask> util = new ExcelUtil<TTransportTask>(TTransportTask.class);
        util.exportExcel(response, list, "transportTask");
    }

    /**
     * 获取任务管理-运输任务详细信息
     */
    @PreAuthorize(hasPermi = "system:transportTask:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(tTransportTaskService.selectTTransportTaskById(id));
    }

    /**
     * 新增任务管理-运输任务
     */
    @PreAuthorize(hasPermi = "system:transportTask:add")
    @Log(title = "任务管理-运输任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TTransportTask tTransportTask)
    {
        return toAjax(tTransportTaskService.insertTTransportTask(tTransportTask));
    }

    /**
     * 修改任务管理-运输任务
     */
    @PreAuthorize(hasPermi = "system:transportTask:edit")
    @Log(title = "任务管理-运输任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TTransportTask tTransportTask)
    {
        return toAjax(tTransportTaskService.updateTTransportTask(tTransportTask));
    }

    /**
     * 删除任务管理-运输任务
     */
    @PreAuthorize(hasPermi = "system:transportTask:remove")
    @Log(title = "任务管理-运输任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tTransportTaskService.deleteTTransportTaskByIds(ids));
    }
}
