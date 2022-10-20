package com.dlzx.pfserverparts.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.security.service.DriverTokenService;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverparts.domain.GGoods;
import com.dlzx.pfserverparts.domain.GGoodsCheckItem;
import com.dlzx.pfserverparts.domain.GGoodsType;
import com.dlzx.pfserverparts.service.IGGoodsCheckItemService;
import com.dlzx.pfserverparts.service.IGGoodsService;
import com.dlzx.pfserverparts.service.IGGoodsTypeService;
import com.dlzx.system.api.model.LoginUser;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfserverparts.domain.GGoodsCheck;
import com.dlzx.pfserverparts.service.IGGoodsCheckService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 库存管理-盘点Controller
 * 
 * @author dlzx
 * @date 2020-12-03
 */
@RestController
@RequestMapping("/parts/goodsCheck")
public class GGoodsCheckController extends BaseController
{
    @Autowired
    private IGGoodsCheckService gGoodsCheckService;

    @Autowired
    private IGGoodsTypeService gGoodsTypeService;

    /**
     * 查询库存管理-盘点列表
     */
    @PreAuthorize(hasPermi = "parts:goodsCheck:list")
    @PostMapping("/K14List")
    public TableDataInfo K14List(@RequestBody GGoodsCheck gGoodsCheck)
    {
        startPage(gGoodsCheck.getPageNum(), gGoodsCheck.getPageSize());
        List<GGoodsCheck> list = gGoodsCheckService.selectGGoodsCheckList(gGoodsCheck);
        return getDataTable(list);
    }

    /**
     * 查询库存管理-盘点列表-管理员APP
     */
    @RequestMapping(path = "/T03List",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public TableDataInfo T03List(@RequestBody GGoodsCheck gGoodsCheck)
    {
        startPage(gGoodsCheck.getPageNum(), gGoodsCheck.getPageSize());
        List<GGoodsCheck> list = gGoodsCheckService.selectGGoodsCheckList(gGoodsCheck);
        return getDataTable(list);
    }

    /**
     * 导出库存管理-盘点列表
     */
    @PreAuthorize(hasPermi = "parts:goodsCheck:export")
    @Log(title = "库存管理-盘点", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GGoodsCheck gGoodsCheck) throws IOException
    {
        List<GGoodsCheck> list = gGoodsCheckService.selectGGoodsCheckList(gGoodsCheck);
        ExcelUtil<GGoodsCheck> util = new ExcelUtil<GGoodsCheck>(GGoodsCheck.class);
        util.exportExcel(response, list, "check");
    }

    /**
     * 获取库存管理-盘点详细信息
     */
    @PreAuthorize(hasPermi = "parts:goodsCheck:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(gGoodsCheckService.selectGGoodsCheckById(id));
    }

    /**
     * 新增库存管理-盘点
     */
    @PreAuthorize(hasPermi = "parts:goodsCheck:add")
    @Log(title = "库存管理-盘点", businessType = BusinessType.INSERT)
    @PostMapping("/Add")
    public AjaxResult Add(@RequestBody GGoodsCheck gGoodsCheck)
    {
        return toAjax(gGoodsCheckService.insertGGoodsCheck(gGoodsCheck));
    }


    /**
     * 新增库存管理-盘点
     * 盘点管理页面的新增按钮
     */
    @PreAuthorize(hasPermi = "parts:goodsCheck:add")
    @Log(title = "库存管理-盘点新增", businessType = BusinessType.INSERT)
    @PostMapping("/K14Add")
    public AjaxResult K14Add(@RequestBody GGoodsType gGoodsType)
    {
        return AjaxResult.success(gGoodsCheckService.submitGGoodsCheck(gGoodsType));
    }

    /**
     * 查询库存管理-物品类别列表-->点击添加查询g_goods_type中的name
     */
    @PreAuthorize(hasPermi = "parts:goodsCheck:list")
    @PostMapping("/K14AddGetInfo")
    public TableDataInfo K14AddGetInfo(@RequestBody  GGoodsType gGoodsType)
    {
        startPage(gGoodsType.getPageNum(), gGoodsType.getPageSize());
        List<GGoodsType> list = gGoodsTypeService.selectGGoodsTypeList(gGoodsType);
        return getDataTable(list);
    }

    /**
     * 修改库存管理-盘点
     */
    @PreAuthorize(hasPermi = "parts:goodsCheck:edit")
    @Log(title = "库存管理-盘点", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GGoodsCheck gGoodsCheck)
    {
        return toAjax(gGoodsCheckService.updateGGoodsCheck(gGoodsCheck));
    }

    /**
     * 删除库存管理-盘点
     */
    @PreAuthorize(hasPermi = "parts:goodsCheck:remove")
    @Log(title = "库存管理-盘点", businessType = BusinessType.DELETE)
	@GetMapping("/K14Delete/{id}")
    public AjaxResult remove(@PathVariable String id)
    {
        GGoodsCheck gGoodsCheck = gGoodsCheckService.selectGGoodsCheckById(id);
        gGoodsCheck.setDeleteFlag(Long.valueOf(2));
        return toAjax(gGoodsCheckService.updateGGoodsCheck(gGoodsCheck));
    }
}
