package com.dlzx.pfservercar.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.pfservercar.domain.model.TTransportTaskOrderModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskOrderVm;
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
import com.dlzx.pfservercar.domain.TTransportTaskOrder;
import com.dlzx.pfservercar.service.ITTransportTaskOrderService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 任务管理-运输任务订单Controller
 * 
 * @author DongCL
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/transportOrder")
public class TTransportTaskOrderController extends BaseController
{
    @Autowired
    private ITTransportTaskOrderService tTransportTaskOrderService;

    /**
     * 查询任务管理-运输任务订单列表
     */
    @PreAuthorize(hasPermi = "system:transportOrder:list")
    @GetMapping("/list")
    public TableDataInfo list(TTransportTaskOrderModel tTransportTaskOrder)
    {
        startPage();
        List<TTransportTaskOrderVm> list = tTransportTaskOrderService.selectTTransportTaskOrderList(tTransportTaskOrder);
        return getDataTable(list);
    }

    /**
     * 导出任务管理-运输任务订单列表
     */
    @PreAuthorize(hasPermi = "system:transportOrder:export")
    @Log(title = "任务管理-运输任务订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TTransportTaskOrderModel tTransportTaskOrder) throws IOException
    {
        List<TTransportTaskOrderVm> list = tTransportTaskOrderService.selectTTransportTaskOrderList(tTransportTaskOrder);
        ExcelUtil<TTransportTaskOrderVm> util = new ExcelUtil<TTransportTaskOrderVm>(TTransportTaskOrderVm.class);
        util.exportExcel(response, list, "transportOrder");
    }

    /**
     * 获取任务管理-运输任务订单详细信息
     */
    @PreAuthorize(hasPermi = "system:transportOrder:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(tTransportTaskOrderService.selectTTransportTaskOrderById(id));
    }

    /**
     * 新增任务管理-运输任务订单
     */
    @PreAuthorize(hasPermi = "system:transportOrder:add")
    @Log(title = "任务管理-运输任务订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TTransportTaskOrderModel tTransportTaskOrder)
    {
        return toAjax(tTransportTaskOrderService.insertTTransportTaskOrder(tTransportTaskOrder));
    }

    /**
     * 修改任务管理-运输任务订单
     */
    @PreAuthorize(hasPermi = "system:transportOrder:edit")
    @Log(title = "任务管理-运输任务订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TTransportTaskOrderModel tTransportTaskOrder)
    {
        return toAjax(tTransportTaskOrderService.updateTTransportTaskOrder(tTransportTaskOrder));
    }

    /**
     * 删除任务管理-运输任务订单
     */
    @PreAuthorize(hasPermi = "system:transportOrder:remove")
    @Log(title = "任务管理-运输任务订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tTransportTaskOrderService.deleteTTransportTaskOrderByIds(ids));
    }
}
