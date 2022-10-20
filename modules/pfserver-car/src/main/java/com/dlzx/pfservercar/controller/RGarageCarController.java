package com.dlzx.pfservercar.controller;

import java.util.List;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.security.service.TokenService;
import com.dlzx.system.api.model.LoginUser;
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
import com.dlzx.pfservercar.domain.RGarageCar;
import com.dlzx.pfservercar.service.IRGarageCarService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 维修保养-维修站Controller
 * 
 * @author yangluhe
 * @date 2020-12-01
 */
@RestController
@RequestMapping("/car/garageCar")
public class RGarageCarController extends BaseController
{
    @Autowired
    private IRGarageCarService rGarageCarService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询维修保养-维修站列表
     */
    @PreAuthorize(hasPermi = "pfservercar:garageCar:list")
    @PostMapping("/H15List")
    public TableDataInfo H15List(@RequestBody RGarageCar rGarageCar)
    {
        startPage(rGarageCar.getPageNum(),rGarageCar.getPageSize());
        List<RGarageCar> list = rGarageCarService.selectRGarageCarList(rGarageCar);
        return getDataTable(list);
    }

    /**
     * 导出维修保养-维修站列表
     */
    @PreAuthorize(hasPermi = "pfservercar:garageCar:export")
    @Log(title = "维修保养-维修站", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RGarageCar rGarageCar) throws IOException
    {
        List<RGarageCar> list = rGarageCarService.selectRGarageCarList(rGarageCar);
        ExcelUtil<RGarageCar> util = new ExcelUtil<RGarageCar>(RGarageCar.class);
        util.exportExcel(response, list, "garageCar");
    }

    /**
     * 获取维修保养-维修站详细信息
     */
    @PreAuthorize(hasPermi = "pfservercar:garageCar:edit")
    @GetMapping(value = "/H15Info/{id}")
    public AjaxResult H15Info(@PathVariable("id") String id)
    {
        return AjaxResult.success(rGarageCarService.selectRGarageCarById(id));
    }

    /**
     * 新增维修保养-维修站
     */
    @PreAuthorize(hasPermi = "pfservercar:garageCar:add")
    @Log(title = "维修保养-维修站", businessType = BusinessType.INSERT)
    @PostMapping("/H15Add")
    public AjaxResult H15Add(@Validated @RequestBody RGarageCar rGarageCar)
    {
        //获取登录用户信息
        LoginUser userInfo = tokenService.getLoginUser();
        rGarageCar.setId(UUID.randomUUID().toString());
        rGarageCar.setCreateBy(userInfo.getUsername());
        rGarageCar.setDeleteFlag(Long.valueOf(1));
        return toAjax(rGarageCarService.insertRGarageCar(rGarageCar));
    }

    /**
     * 修改维修保养-维修站
     */
    @PreAuthorize(hasPermi = "pfservercar:garageCar:edit")
    @Log(title = "维修保养-维修站", businessType = BusinessType.UPDATE)
    @PostMapping("/H15EditSave")
    public AjaxResult H15EditSave(@Validated @RequestBody RGarageCar rGarageCar)
    {
        RGarageCar rGarageCarOne = rGarageCarService.selectRGarageCarById(rGarageCar.getId());
        //获取登录用户信息
        LoginUser userInfo = tokenService.getLoginUser();
        rGarageCarOne.setUpdateBy(userInfo.getUsername());
        rGarageCarOne.setName(rGarageCar.getName());
        rGarageCarOne.setAddress(rGarageCar.getAddress());
        rGarageCarOne.setTel(rGarageCar.getTel());
        rGarageCarOne.setIsFixed(rGarageCar.getIsFixed());
        return toAjax(rGarageCarService.updateRGarageCar(rGarageCarOne));
    }

    /**
     * 删除维修保养-维修站
     */
    @PreAuthorize(hasPermi = "pfservercar:garageCar:delete")
    @Log(title = "维修保养-维修站", businessType = BusinessType.DELETE)
	@GetMapping("/H15Delete/{id}")
    public AjaxResult H15Delete(@PathVariable("id") String id)
    {
        RGarageCar rGarageCar = rGarageCarService.selectRGarageCarById(id);
        rGarageCar.setDeleteFlag(Long.valueOf(2));
        return toAjax(rGarageCarService.updateRGarageCar(rGarageCar));
    }
}
