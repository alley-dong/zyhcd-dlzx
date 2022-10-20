package com.dlzx.pfservercar.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.utils.poi.ExcelUtilh;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.system.api.model.LoginUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import com.dlzx.pfservercar.domain.RGarageRepairItem;
import com.dlzx.pfservercar.service.IRGarageRepairItemService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 维修保养-维修站-维修明细Controller
 * 
 * @author yangluhe
 * @date 2020-12-02
 */
@RestController
@RequestMapping("/car/GarageRepairItem")
public class RGarageRepairItemController extends BaseController
{
    @Autowired
    private IRGarageRepairItemService rGarageRepairItemService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询维修保养-维修站-维修明细列表
     */
//    @Log(title = "查看维修保养维修明细列表",businessType = BusinessType.ITEM)
    @PreAuthorize(hasPermi = "pfservercar:garagerepairitem:list")
    @PostMapping("/H16list")
    public TableDataInfo H16list(@RequestBody RGarageRepairItem rGarageRepairItem)
    {
        startPage(rGarageRepairItem.getPageNum(),rGarageRepairItem.getPageSize());
        List<RGarageRepairItem> list = rGarageRepairItemService.selectRGarageRepairItemList(rGarageRepairItem);
        return getDataTable(list);
    }


    /**
     * 获取维修保养-维修站-维修明细详细信息
     */
    @Log(title = "维修保养维修明细详情")
//    @PreAuthorize(hasPermi = "pfservercar:garagerepairitem:info")
    @GetMapping(value = "/H16Info/{id}")
    public AjaxResult H16Info(@PathVariable("id") String id)
    {
        return AjaxResult.success(rGarageRepairItemService.selectRGarageRepairItemById(id));
    }

    /**
     * 新增维修保养-维修站-维修明细
     */
    @PreAuthorize(hasPermi = "pfservercar:garagerepairitem:add")
    @Log(title = "维修保养-维修站-维修明细", businessType = BusinessType.INSERT)
    @PostMapping("/H16Add")
    public AjaxResult H16Add(@Validated @RequestBody RGarageRepairItem rGarageRepairItem)
    {
        //获取登录用户信息
        LoginUser userInfo = tokenService.getLoginUser();
        rGarageRepairItem.setCreateBy(userInfo.getUsername());
        rGarageRepairItem.setId(UUID.randomUUID().toString());
        rGarageRepairItem.setDeleteFlag(Long.valueOf(1));
        return toAjax(rGarageRepairItemService.insertRGarageRepairItem(rGarageRepairItem));
    }

    /**
     * 修改维修保养-维修站-维修明细
     */
    @PreAuthorize(hasPermi = "pfservercar:garagerepairitem:editsave")
    @Log(title = "维修保养-维修站-维修明细", businessType = BusinessType.UPDATE)
    @PostMapping("/H16EditSave")
    public AjaxResult H16EditSave(@Validated @RequestBody RGarageRepairItem rGarageRepairItem)
    {
        RGarageRepairItem rGarageRepairItemOne = rGarageRepairItemService.selectRGarageRepairItemById(rGarageRepairItem.getId());
        rGarageRepairItemOne.setVehicleModelName(rGarageRepairItem.getVehicleModelName());
        rGarageRepairItemOne.setName(rGarageRepairItem.getName());
        rGarageRepairItemOne.setUnitPrice(rGarageRepairItem.getUnitPrice());
        rGarageRepairItemOne.setUnit(rGarageRepairItem.getUnit());
        rGarageRepairItemOne.setDuration(rGarageRepairItem.getDuration());
        //获取登录用户信息
        LoginUser userInfo = tokenService.getLoginUser();
        rGarageRepairItemOne.setUpdateBy(userInfo.getUsername());
        return toAjax(rGarageRepairItemService.updateRGarageRepairItem(rGarageRepairItemOne));
    }

    /**
     * 删除维修保养-维修站-维修明细
     */
    @PreAuthorize(hasPermi = "pfservercar:garagerepairitem:delete")
    @Log(title = "维修保养-维修站-维修明细", businessType = BusinessType.DELETE)
	@GetMapping("/H16Delete/{id}")
    public AjaxResult H16Delete(@PathVariable("id") String id)
    {
        RGarageRepairItem rGarageRepairItem = rGarageRepairItemService.selectRGarageRepairItemById(id);
        rGarageRepairItem.setDeleteFlag(Long.valueOf(2));
        return toAjax(rGarageRepairItemService.updateRGarageRepairItem(rGarageRepairItem));
    }

    /**
     * 下载维修保养维修明细模板
     * @return
     */
    @Log(title = "下载维修保养维修明细模板")
    @PreAuthorize(hasPermi = "pfservercar:garagerepairitem:template")
    @GetMapping(value = "/H16Template")
    public AjaxResult H16Template() {
        ExcelUtilh<RGarageRepairItem> util = new ExcelUtilh<RGarageRepairItem>(RGarageRepairItem.class);
        return util.importTemplateExcel( "维修明细模板");
    }

    /**
     * 维修明细导入
     */
    @ApiOperation("导入维修明细")
    @PreAuthorize(hasPermi = "pfservercar:garagerepairitem:import")
    @Log(title = "维修明细导入", businessType = BusinessType.IMPORT)
    @PostMapping("/H16Import")
    public AjaxResult H16Import(@Validated MultipartFile file, boolean updateSupport,String garageId) throws Exception{
        //excel导入判断数据格式和表格式
        ExcelUtilh<RGarageRepairItem> util = new ExcelUtilh<RGarageRepairItem>(RGarageRepairItem.class);
        List<RGarageRepairItem> rGarageRepairItemList = new ArrayList<>();
        try {
            rGarageRepairItemList = util.importExcel(file.getInputStream());
        } catch (Exception e){
            e.printStackTrace();
            e.getMessage();
            return AjaxResult.error("数据格式有误");
        }

        //excel导入数据判断数据内容是否符合要求
        Map<String,String> message = rGarageRepairItemService.importRarageRepairItem(rGarageRepairItemList,updateSupport,garageId);
        String code = message.get("code");
        if("0".equals(code)){
            return AjaxResult.success(message.get("msg"));
        } else {
            return AjaxResult.error(message.get("msg"));
        }
    }
}
