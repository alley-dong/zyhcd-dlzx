package com.dlzx.pfservercar.controller;

import java.util.List;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.TokenService;
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
import com.dlzx.pfservercar.domain.FFuelPlaceItem;
import com.dlzx.pfservercar.service.IFFuelPlaceItemService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 燃油定点加油站Controller
 * 
 * @author dlzx
 * @date 2020-12-01
 */
@RestController
@RequestMapping("/placeItem")
public class FFuelPlaceItemController extends BaseController
{
    @Autowired
    private IFFuelPlaceItemService fFuelPlaceItemService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询燃油定点加油站列表
     */
    @PreAuthorize(hasPermi = "pfservercar:item:list")
    @PostMapping("/I04OilPlantsAddressList")
    public TableDataInfo list(@RequestBody FFuelPlaceItem fFuelPlaceItem)
    {
        startPage(fFuelPlaceItem.getPageNum(),fFuelPlaceItem.getPageSize());
        List<FFuelPlaceItem> list = fFuelPlaceItemService.selectFFuelPlaceItemList(fFuelPlaceItem);
        return getDataTable(list);
    }

    /**
     * 新增燃油定点加油站
     */
    @PreAuthorize(hasPermi = "pfservercar:item:add")
    @Log(title = "燃油定点加油站", businessType = BusinessType.INSERT)
    @PostMapping("/I04OilPlantsAddressAdd")
    public AjaxResult add(@RequestBody FFuelPlaceItem fFuelPlaceItem)
    {
        if (StringUtils.isEmpty(fFuelPlaceItem.getAddress())){
            return AjaxResult.error("加油站地址不能为空");
        }
        if (StringUtils.isEmpty(fFuelPlaceItem.getName())){
            return AjaxResult.error("加油站名称不能为空");
        }
        if (StringUtils.isEmpty(fFuelPlaceItem.getPlaceId())){
            return AjaxResult.error("定点Id不能为空");
        }
        LoginUser userInfo = tokenService.getLoginUser();
        fFuelPlaceItem.setCreateBy(userInfo.getUsername());
        fFuelPlaceItem.setId(UUID.randomUUID()+"");
        return toAjax(fFuelPlaceItemService.insertFFuelPlaceItem(fFuelPlaceItem));
    }

    /**
     * 修改燃油定点加油站
     */
    @PreAuthorize(hasPermi = "pfservercar:item:edit")
    @Log(title = "燃油定点加油站", businessType = BusinessType.UPDATE)
    @PostMapping("/I04OilPlantsAddressEditSave")
    public AjaxResult edit(@RequestBody FFuelPlaceItem fFuelPlaceItem)
    {
        if (StringUtils.isEmpty(fFuelPlaceItem.getAddress())){
            return AjaxResult.error("加油站地址不能为空");
        }
        if (StringUtils.isEmpty(fFuelPlaceItem.getName())){
            return AjaxResult.error("加油站名称不能为空");
        }
        if (StringUtils.isEmpty(fFuelPlaceItem.getPlaceId())){
            return AjaxResult.error("定点Id不能为空");
        }
        LoginUser userInfo = tokenService.getLoginUser();
        fFuelPlaceItem.setUpdateBy(userInfo.getUsername());
        return toAjax(fFuelPlaceItemService.updateFFuelPlaceItem(fFuelPlaceItem));
    }

    /**
     * 删除燃油定点加油站
     */
    @PreAuthorize(hasPermi = "pfservercar:item:delete")
    @Log(title = "燃油定点加油站", businessType = BusinessType.DELETE)
	@GetMapping("/I04OilPlantsAddressDelete/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(fFuelPlaceItemService.deleteFFuelPlaceItemByIds(ids));
    }

    /**
     * 获取燃油定点加油站详细信息
     */
    @PreAuthorize(hasPermi = "pfservercar:item:info")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(fFuelPlaceItemService.selectFFuelPlaceItemById(id));
    }

    /**
     * 导出燃油定点加油站列表
     */
    @PreAuthorize(hasPermi = "pfservercar:item:export")
    @Log(title = "燃油定点加油站", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FFuelPlaceItem fFuelPlaceItem) throws IOException
    {
        List<FFuelPlaceItem> list = fFuelPlaceItemService.selectFFuelPlaceItemList(fFuelPlaceItem);
        ExcelUtil<FFuelPlaceItem> util = new ExcelUtil<FFuelPlaceItem>(FFuelPlaceItem.class);
        util.exportExcel(response, list, "item");
    }

}
