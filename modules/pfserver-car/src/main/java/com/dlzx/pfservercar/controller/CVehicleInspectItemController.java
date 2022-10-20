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
import com.dlzx.pfservercar.domain.CVehicleInspectItem;
import com.dlzx.pfservercar.service.ICVehicleInspectItemService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 车辆管理-车辆定检记录-检查项Controller
 * 
 * @author dlzx
 * @date 2020-12-08
 */
@RestController
@RequestMapping("/item")
public class CVehicleInspectItemController extends BaseController
{
    @Autowired
    private ICVehicleInspectItemService cVehicleInspectItemService;

    /**
     * 查询车辆管理-车辆定检记录-检查项列表
     */
    @PreAuthorize(hasPermi = "system:item:list")
    @GetMapping("/list")
    public TableDataInfo list(CVehicleInspectItem cVehicleInspectItem)
    {
        startPage();
        List<CVehicleInspectItem> list = cVehicleInspectItemService.selectCVehicleInspectItemList(cVehicleInspectItem);
        return getDataTable(list);
    }

    /**
     * 导出车辆管理-车辆定检记录-检查项列表
     */
    @PreAuthorize(hasPermi = "system:item:export")
    @Log(title = "车辆管理-车辆定检记录-检查项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CVehicleInspectItem cVehicleInspectItem) throws IOException
    {
        List<CVehicleInspectItem> list = cVehicleInspectItemService.selectCVehicleInspectItemList(cVehicleInspectItem);
        ExcelUtil<CVehicleInspectItem> util = new ExcelUtil<CVehicleInspectItem>(CVehicleInspectItem.class);
        util.exportExcel(response, list, "item");
    }

    /**
     * 获取车辆管理-车辆定检记录-检查项详细信息
     */
    @PreAuthorize(hasPermi = "system:item:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cVehicleInspectItemService.selectCVehicleInspectItemById(id));
    }

    /**
     * 新增车辆管理-车辆定检记录-检查项
     */
    @PreAuthorize(hasPermi = "system:item:add")
    @Log(title = "车辆管理-车辆定检记录-检查项", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CVehicleInspectItem cVehicleInspectItem)
    {
        return toAjax(cVehicleInspectItemService.insertCVehicleInspectItem(cVehicleInspectItem));
    }

    /**
     * 修改车辆管理-车辆定检记录-检查项
     */
    @PreAuthorize(hasPermi = "system:item:edit")
    @Log(title = "车辆管理-车辆定检记录-检查项", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CVehicleInspectItem cVehicleInspectItem)
    {
        return toAjax(cVehicleInspectItemService.updateCVehicleInspectItem(cVehicleInspectItem));
    }

    /**
     * 删除车辆管理-车辆定检记录-检查项
     */
    @PreAuthorize(hasPermi = "system:item:remove")
    @Log(title = "车辆管理-车辆定检记录-检查项", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cVehicleInspectItemService.deleteCVehicleInspectItemByIds(ids));
    }
}
