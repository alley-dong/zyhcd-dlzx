package com.dlzx.pfservercar.controller;

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
import com.dlzx.pfservercar.domain.TTransportTaskCost;
import com.dlzx.pfservercar.service.ITTransportTaskCostService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 任务管理-运输任务费用Controller
 * 
 * @author yangluhe
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/car/taskCost")
public class TTransportTaskCostController extends BaseController
{
    @Autowired
    private ITTransportTaskCostService tTransportTaskCostService;

    /**
     * 查询任务管理-运输任务费用列表
     */
    @PreAuthorize(hasPermi = "pfservercar:taskCost:list")
    @PostMapping("/N05List")
    public TableDataInfo N05List(TTransportTaskCost tTransportTaskCost)
    {
        startPage(tTransportTaskCost.getPageNum(), tTransportTaskCost.getPageSize());
        List<TTransportTaskCost> list = tTransportTaskCostService.selectTTransportTaskCostList(tTransportTaskCost);
        return getDataTable(list);
    }

    /**
     * 导出任务管理-运输任务费用列表
     */
    @PreAuthorize(hasPermi = "system:taskCost:export")
    @Log(title = "任务管理-运输任务费用", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TTransportTaskCost tTransportTaskCost) throws IOException
    {
        List<TTransportTaskCost> list = tTransportTaskCostService.selectTTransportTaskCostList(tTransportTaskCost);
        ExcelUtil<TTransportTaskCost> util = new ExcelUtil<TTransportTaskCost>(TTransportTaskCost.class);
        util.exportExcel(response, list, "taskCost");
    }

    /**
     * 获取任务管理-运输任务费用详细信息
     */
    @PreAuthorize(hasPermi = "system:taskCost:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(tTransportTaskCostService.selectTTransportTaskCostById(id));
    }

    /**
     * 新增任务管理-运输任务费用
     */
    @PreAuthorize(hasPermi = "system:taskCost:add")
    @Log(title = "任务管理-运输任务费用", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TTransportTaskCost tTransportTaskCost)
    {
        return toAjax(tTransportTaskCostService.insertTTransportTaskCost(tTransportTaskCost));
    }

    /**
     * 修改任务管理-运输任务费用
     */
    @PreAuthorize(hasPermi = "system:taskCost:edit")
    @Log(title = "任务管理-运输任务费用", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TTransportTaskCost tTransportTaskCost)
    {
        return toAjax(tTransportTaskCostService.updateTTransportTaskCost(tTransportTaskCost));
    }

    /**
     * 删除任务管理-运输任务费用
     */
    @PreAuthorize(hasPermi = "system:taskCost:remove")
    @Log(title = "任务管理-运输任务费用", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tTransportTaskCostService.deleteTTransportTaskCostByIds(ids));
    }
}
