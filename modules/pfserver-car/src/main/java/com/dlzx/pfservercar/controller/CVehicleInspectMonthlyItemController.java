package com.dlzx.pfservercar.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.pfservercar.domain.CVehicleInspect;
import com.dlzx.pfservercar.domain.CVehicleInspectItem;
import com.dlzx.pfservercar.service.ICVehicleInspectItemService;
import com.dlzx.pfservercar.service.ICVehicleInspectService;
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
import com.dlzx.pfservercar.domain.CVehicleInspectMonthlyItem;
import com.dlzx.pfservercar.service.ICVehicleInspectMonthlyItemService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 车辆管理-车辆定检记录车辆Controller
 * 
 * @author dlzx
 * @date 2020-12-31
 */
@RestController
@RequestMapping("/car/vehicleInspectMonthlyItem")
public class CVehicleInspectMonthlyItemController extends BaseController
{
    @Autowired
    private ICVehicleInspectMonthlyItemService cVehicleInspectMonthlyItemService;
    @Autowired
    private ICVehicleInspectService icVehicleInspectService;
    @Autowired
    private ICVehicleInspectItemService icVehicleInspectItemService;

    /**
     * 查询车辆管理-车辆定检记录车辆详情列表
     * 将H22页面带ID过来 作为monthlyId传进来
     */
    @PreAuthorize(hasPermi = "car:vehicleInspectMonthlyItem:list")
    @PostMapping("/H23List")
    public TableDataInfo list(@RequestBody CVehicleInspectMonthlyItem cVehicleInspectMonthlyItem)
    {
        startPage(cVehicleInspectMonthlyItem.getPageNum(), cVehicleInspectMonthlyItem.getPageSize());
        List<CVehicleInspectMonthlyItem> list = cVehicleInspectMonthlyItemService.selectCVehicleInspectMonthlyItemList(cVehicleInspectMonthlyItem);
        return getDataTable(list);
    }

    /**
     * 导出车辆管理-车辆定检记录车辆列表
     */
    @PreAuthorize(hasPermi = "system:item:export")
    @Log(title = "车辆管理-车辆定检记录车辆", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CVehicleInspectMonthlyItem cVehicleInspectMonthlyItem) throws IOException
    {
        List<CVehicleInspectMonthlyItem> list = cVehicleInspectMonthlyItemService.selectCVehicleInspectMonthlyItemList(cVehicleInspectMonthlyItem);
        ExcelUtil<CVehicleInspectMonthlyItem> util = new ExcelUtil<CVehicleInspectMonthlyItem>(CVehicleInspectMonthlyItem.class);
        util.exportExcel(response, list, "item");
    }

    /**
     * 获取车辆管理-车辆定检记录车辆详细信息 YLH
     */
    @PreAuthorize(hasPermi = "system:item:query")
    @GetMapping(value = "/H24Info/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        CVehicleInspectMonthlyItem cVehicleInspectMonthlyItem = cVehicleInspectMonthlyItemService.selectCVehicleInspectMonthlyItemById(id);
        CVehicleInspect cVehicleInspect = icVehicleInspectService.selectCVehicleInspectByMonthlyItemId(id);
        if (cVehicleInspect != null){
            List<CVehicleInspectItem> cVehicleInspectItem = icVehicleInspectItemService.selectCVehicleInspectItemByInspectId(cVehicleInspect.getId());
            cVehicleInspectMonthlyItem.setcVehicleInspectItemList(cVehicleInspectItem);
            cVehicleInspectMonthlyItem.setType(cVehicleInspect.getType());
        }
        return AjaxResult.success(cVehicleInspectMonthlyItem);
    }

    /**
     * 新增车辆管理-车辆定检记录车辆
     */
    @PreAuthorize(hasPermi = "system:item:add")
    @Log(title = "车辆管理-车辆定检记录车辆", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CVehicleInspectMonthlyItem cVehicleInspectMonthlyItem)
    {
        return toAjax(cVehicleInspectMonthlyItemService.insertCVehicleInspectMonthlyItem(cVehicleInspectMonthlyItem));
    }

    /**
     * 修改车辆管理-车辆定检记录车辆
     */
    @PreAuthorize(hasPermi = "system:item:edit")
    @Log(title = "车辆管理-车辆定检记录车辆", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CVehicleInspectMonthlyItem cVehicleInspectMonthlyItem)
    {
        return toAjax(cVehicleInspectMonthlyItemService.updateCVehicleInspectMonthlyItem(cVehicleInspectMonthlyItem));
    }

    /**
     * 删除车辆管理-车辆定检记录车辆
     */
    @PreAuthorize(hasPermi = "system:item:remove")
    @Log(title = "车辆管理-车辆定检记录车辆", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cVehicleInspectMonthlyItemService.deleteCVehicleInspectMonthlyItemByIds(ids));
    }
}
