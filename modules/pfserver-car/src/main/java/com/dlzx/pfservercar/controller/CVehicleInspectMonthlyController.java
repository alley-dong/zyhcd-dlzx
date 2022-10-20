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
import com.dlzx.pfservercar.domain.CVehicleInspectMonthly;
import com.dlzx.pfservercar.service.ICVehicleInspectMonthlyService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 车辆管理-车辆定检发布Controller
 * 
 * @author dlzx
 * @date 2020-12-08
 */
@RestController
@RequestMapping("/car/vehicleInspectMonthly")
public class CVehicleInspectMonthlyController extends BaseController
{
    @Autowired
    private ICVehicleInspectMonthlyService cVehicleInspectMonthlyService;

    /**
     * 查询车辆管理-车辆定检记录-列表
     */
    @PreAuthorize(hasPermi = "car:vehicleInspectMonthly:list")
    @PostMapping("/H22List")
    public TableDataInfo H22List(@RequestBody CVehicleInspectMonthly cVehicleInspectMonthly)
    {
        startPage(cVehicleInspectMonthly.getPageNum(),cVehicleInspectMonthly.getPageSize());
        List<CVehicleInspectMonthly> list = cVehicleInspectMonthlyService.selectCVehicleInspectMonthlyList(cVehicleInspectMonthly);
        return getDataTable(list);
    }

    /**
     * 导出车辆管理-车辆定检发布列表
     */
    @PreAuthorize(hasPermi = "car:vehicleInspectMonthly:export")
    @Log(title = "车辆管理-车辆定检发布", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CVehicleInspectMonthly cVehicleInspectMonthly) throws IOException
    {
        List<CVehicleInspectMonthly> list = cVehicleInspectMonthlyService.selectCVehicleInspectMonthlyList(cVehicleInspectMonthly);
        ExcelUtil<CVehicleInspectMonthly> util = new ExcelUtil<CVehicleInspectMonthly>(CVehicleInspectMonthly.class);
        util.exportExcel(response, list, "monthly");
    }

    /**
     * 获取车辆管理-车辆定检发布详细信息
     */
    @PreAuthorize(hasPermi = "car:vehicleInspectMonthly:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cVehicleInspectMonthlyService.selectCVehicleInspectMonthlyById(id));
    }

    /**
     * 新增车辆管理-车辆定检发布
     */
    @PreAuthorize(hasPermi = "car:vehicleInspectMonthly:add")
    @Log(title = "车辆管理-车辆定检发布", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CVehicleInspectMonthly cVehicleInspectMonthly)
    {
        return toAjax(cVehicleInspectMonthlyService.insertCVehicleInspectMonthly(cVehicleInspectMonthly));
    }

    /**
     * 修改车辆管理-车辆定检发布
     */
    @PreAuthorize(hasPermi = "car:vehicleInspectMonthly:edit")
    @Log(title = "车辆管理-车辆定检发布", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CVehicleInspectMonthly cVehicleInspectMonthly)
    {
        return toAjax(cVehicleInspectMonthlyService.updateCVehicleInspectMonthly(cVehicleInspectMonthly));
    }

    /**
     * 删除车辆管理-车辆定检发布
     */
    @PreAuthorize(hasPermi = "car:vehicleInspectMonthly:remove")
    @Log(title = "车辆管理-车辆定检发布", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cVehicleInspectMonthlyService.deleteCVehicleInspectMonthlyByIds(ids));
    }
}
