package com.dlzx.pfservercar.controller;

import java.util.List;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.domain.CInsuranceType;
import com.dlzx.pfservercar.service.ICInsuranceTypeService;
import com.dlzx.system.api.model.LoginUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfservercar.domain.CInsuranceTypeItem;
import com.dlzx.pfservercar.service.ICInsuranceTypeItemService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 车辆管理-险种项Controller
 * 
 * @author dlzx
 * @date 2020-11-23
 */
@RestController
@RequestMapping("/car/insuranceTypeItem")
public class CInsuranceTypeItemController extends BaseController
{
    @Autowired
    private ICInsuranceTypeItemService cInsuranceTypeItemService;
    @Autowired
    private ICInsuranceTypeService cInsuranceTypeService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询车辆管理-险种项列表
     */
    @PreAuthorize(hasPermi = "car:insuranceTypeItem:list")
    @PostMapping(value = {"/E13List", "/E09List"})
    public TableDataInfo E13List(@RequestBody CInsuranceTypeItem cInsuranceTypeItem)
    {
        startPage(cInsuranceTypeItem.getPageNum(),cInsuranceTypeItem.getPageSize());
        List<CInsuranceTypeItem> cInsuranceTypeItemList = cInsuranceTypeItemService.selectCInsuranceTypeItemList(cInsuranceTypeItem);
        return getDataTable(cInsuranceTypeItemList);
    }

    /**
     * 导出车辆管理-险种项列表
     */
    @PreAuthorize(hasPermi = "car:insuranceTypeItem:export")
    @Log(title = "车辆管理-险种项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CInsuranceTypeItem cInsuranceTypeItem) throws IOException
    {
        List<CInsuranceTypeItem> list = cInsuranceTypeItemService.selectCInsuranceTypeItemList(cInsuranceTypeItem);
        ExcelUtil<CInsuranceTypeItem> util = new ExcelUtil<CInsuranceTypeItem>(CInsuranceTypeItem.class);
        util.exportExcel(response, list, "Insurance");
    }

    /**
     * 获取车辆管理-险种项详细信息
     * 无
     */
    @PreAuthorize(hasPermi = "car:insuranceTypeItem:query")
    @GetMapping(value = "/E13GetInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cInsuranceTypeItemService.selectCInsuranceTypeItemById(id));
    }

    /**
     * 新增车辆管理-险种项
     */
    @PreAuthorize(hasPermi = "car:insuranceTypeItem:add")
    @Log(title = "车辆管理-险种项", businessType = BusinessType.INSERT)
    @PostMapping("/E13Add")
    public AjaxResult E13Add(@RequestBody CInsuranceTypeItem cInsuranceTypeItem)
    {

        if (cInsuranceTypeItem.getName()==null||cInsuranceTypeItem.getName().equals("")) {
            return AjaxResult.error("险种不能为空");
        }

        CInsuranceTypeItem cInsuranceList = cInsuranceTypeItemService.selectRepeatTypeItem(cInsuranceTypeItem);
        if (cInsuranceList !=null){
            return AjaxResult.error("险种已存在");
        }
        LoginUser userInfo = tokenService.getLoginUser();
        cInsuranceTypeItem.setCreateBy(userInfo.getUsername());
        cInsuranceTypeItem.setId(UUID.randomUUID().toString());
        cInsuranceTypeItem.setDeleteFlag((long) 1);
        return toAjax(cInsuranceTypeItemService.insertCInsuranceTypeItem(cInsuranceTypeItem));
    }

    /**
     * 修改车辆管理-险种项
     */
    @PreAuthorize(hasPermi = "car:insuranceTypeItem:edit")
    @Log(title = "车辆管理-险种项", businessType = BusinessType.UPDATE)
    @PostMapping("/E13EditSave")
    public AjaxResult E13EditSave(@RequestBody CInsuranceTypeItem cInsuranceTypeItem)
    {
        if (cInsuranceTypeItem.getName() == null || cInsuranceTypeItem.getName().equals("")) {
            return AjaxResult.error("险种不能为空");
        }
        LoginUser userInfo = tokenService.getLoginUser();
        cInsuranceTypeItem.setUpdateBy(userInfo.getUsername());
        return toAjax(cInsuranceTypeItemService.updateCInsuranceTypeItem(cInsuranceTypeItem));
    }

    /**
     * 删除车辆管理-险种项
     * 逻辑删除 1正常 2删除
     */
    @PreAuthorize(hasPermi = "car:insuranceTypeItem:remove")
    @Log(title = "车辆管理-险种项", businessType = BusinessType.DELETE)
    @GetMapping("/E13Delete/{id}")
    public AjaxResult E13Delete(@PathVariable("id") String id) {
        CInsuranceTypeItem cInsuranceTypeItem = cInsuranceTypeItemService.selectCInsuranceTypeItemById(id);
        cInsuranceTypeItem.setDeleteFlag((long) 2);
        return toAjax(cInsuranceTypeItemService.updateCInsuranceTypeItem(cInsuranceTypeItem));
    }
}
