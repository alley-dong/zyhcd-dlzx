package com.dlzx.pfservercar.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.domain.CInsuranceVehicle;
import com.dlzx.system.api.model.LoginUser;
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
import com.dlzx.pfservercar.domain.CInsuranceVehicleItem;
import com.dlzx.pfservercar.service.ICInsuranceVehicleItemService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 车辆管理-车辆保险保险项Controller
 * 
 * @author dlzx
 * @date 2020-11-24
 */
@RestController
@RequestMapping("/car/insuranceVehicleItem")
public class CInsuranceVehicleItemController extends BaseController
{
    @Autowired
    private ICInsuranceVehicleItemService cInsuranceVehicleItemService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询车辆管理-车辆保险保险项列表
     */
    @PreAuthorize(hasPermi = "car:insuranceVehicleItem:list")
    @PostMapping("/E02List")
    public TableDataInfo list(CInsuranceVehicleItem cInsuranceVehicleItem)
    {
        startPage(cInsuranceVehicleItem.getPageNum(),cInsuranceVehicleItem.getPageSize());
        List<CInsuranceVehicleItem> list = cInsuranceVehicleItemService.selectCInsuranceVehicleItemList(cInsuranceVehicleItem);
        return getDataTable(list);
    }

    /**
     * 导出车辆管理-车辆保险保险项列表
     */
    @PreAuthorize(hasPermi = "car:insuranceVehicleItem:export")
    @Log(title = "车辆管理-车辆保险保险项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CInsuranceVehicleItem cInsuranceVehicleItem) throws IOException
    {
        List<CInsuranceVehicleItem> list = cInsuranceVehicleItemService.selectCInsuranceVehicleItemList(cInsuranceVehicleItem);
        ExcelUtil<CInsuranceVehicleItem> util = new ExcelUtil<CInsuranceVehicleItem>(CInsuranceVehicleItem.class);
        util.exportExcel(response, list, "item");
    }

    /**
     * 获取车辆管理-车辆保险保险项详细信息
     */
//    @PreAuthorize(hasPermi = "car:insuranceVehicleItem:query")
    @GetMapping(value = "/E02GetInfo/{id}")
    public AjaxResult E02GetInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cInsuranceVehicleItemService.selectCInsuranceVehicleItemById(id));
    }

    /**
     * 新增车辆管理-车辆保险保险项
     */
    @PreAuthorize(hasPermi = "car:insuranceVehicleItem:add")
    @Log(title = "车辆管理-车辆保险保险项", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CInsuranceVehicleItem cInsuranceVehicleItem)
    {
        return toAjax(cInsuranceVehicleItemService.insertCInsuranceVehicleItem(cInsuranceVehicleItem));
    }

    /**
     * 修改车辆管理-车辆保险保险项-车辆保险  保额保费
     */
    @PreAuthorize(hasPermi = "car:insuranceVehicleItem:edit")
    @Log(title = "车辆管理-车辆保险保险项", businessType = BusinessType.UPDATE)
    @PostMapping("/EditSave")
    public AjaxResult EditSave(@RequestBody CInsuranceVehicleItem cInsuranceVehicleItem)
    {
        if (cInsuranceVehicleItem.getInsuranceQuota() == null || cInsuranceVehicleItem.getInsuranceCost() == null) {
            return AjaxResult.error("保额保费不能为空");
        }
        LoginUser userInfo = tokenService.getLoginUser();
        cInsuranceVehicleItem.setUpdateBy(userInfo.getUsername());
        return toAjax(cInsuranceVehicleItemService.updateCInsuranceVehicleItem(cInsuranceVehicleItem));
    }

    /**
     * 删除车辆管理-车辆保险保险项
     * 在新增保险的页面列表中删除
     */
    @PreAuthorize(hasPermi = "car:insuranceVehicleItem:remove")
    @Log(title = "车辆管理-车辆保险保险项", businessType = BusinessType.DELETE)
	@GetMapping("/E02Delete/{id}")
    public AjaxResult E02Delete(@PathVariable("id") String id)
    {
        CInsuranceVehicleItem cInsuranceVehicleItem = cInsuranceVehicleItemService.selectCInsuranceVehicleItemById(id);
        cInsuranceVehicleItem.setDeleteFlag((long) 2);
        return toAjax(cInsuranceVehicleItemService.updateCInsuranceVehicleItem(cInsuranceVehicleItem));
    }
}
