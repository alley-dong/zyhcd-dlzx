package com.dlzx.pfserverparts.controller;

import java.math.BigDecimal;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.text.UUID;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverparts.domain.GGoodsType;
import com.dlzx.pfserverparts.service.IGGoodsTypeService;
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
import com.dlzx.pfserverparts.domain.GGoods;
import com.dlzx.pfserverparts.service.IGGoodsService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 库存管理-物品Controller
 * 
 * @author dlzx
 * @date 2020-12-01
 */
@RestController
@RequestMapping("/parts/goods")
public class GGoodsController extends BaseController
{
    @Autowired
    private IGGoodsService gGoodsService;
    @Autowired
    private IGGoodsTypeService gGoodsTypeService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询库存管理-物品类别列表
     */
    @PreAuthorize(hasPermi = "parts:goods:list")
    @PostMapping("/K02AddList")
    public TableDataInfo K02List(@RequestBody  GGoodsType gGoodsType)
    {
        startPage(gGoodsType.getPageNum(), gGoodsType.getPageSize());
        List<GGoodsType> list = gGoodsTypeService.selectGGoodsTypeList(gGoodsType);
        return getDataTable(list);
    }

    /**
     * 查询库存管理-物品列表
     */
    @PreAuthorize(hasPermi = "parts:goods:list")
    @PostMapping("/K02List")
    public TableDataInfo K02List(@RequestBody  GGoods gGoods)
    {
        startPage(gGoods.getPageNum(), gGoods.getPageSize());
        List<GGoods> list = gGoodsService.selectGGoodsList(gGoods);
        return getDataTable(list);
    }

    /**
     * 导出库存管理-物品列表
     */
    @PreAuthorize(hasPermi = "parts:goods:export")
    @Log(title = "库存管理-物品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GGoods gGoods) throws IOException
    {
        List<GGoods> list = gGoodsService.selectGGoodsList(gGoods);
        ExcelUtil<GGoods> util = new ExcelUtil<GGoods>(GGoods.class);
        util.exportExcel(response, list, "goods");
    }

    /**
     * 获取库存管理-物品详细信息
     */
    @PreAuthorize(hasPermi = "parts:goods:edit")
    @GetMapping(value = "/K02EditSave/{id}")
    public AjaxResult K02EditSave(@PathVariable("id") String id)
    {
        return AjaxResult.success(gGoodsService.selectGGoodsById(id));
    }

    /**
     * 新增库存管理-物品
     */
    @PreAuthorize(hasPermi = "parts:goods:add")
    @Log(title = "库存管理-物品", businessType = BusinessType.INSERT)
    @PostMapping("/K02Add")
    public AjaxResult K02Add(@RequestBody GGoods gGoods)
    {
        if (gGoods.getName() == null || gGoods.getName().equals("")) {
            return AjaxResult.error("商品名称不能为空");
        }
        List<GGoods>gGoodNameList = gGoodsService.countAll(gGoods.getName());
        if (gGoodNameList.size() > 0) {
            return AjaxResult.error("此商品名称已存在");
        }
        if (StringUtils.isEmpty(gGoods.getCode())){
            return AjaxResult.error("物品编号不能为空");
        }
        List<GGoods>gGoodCodeList = gGoodsService.countCodeAll(gGoods.getCode());
        if (gGoodCodeList.size() > 0) {
            return AjaxResult.error("物品编号已存在");
        }
        if (StringUtils.isEmpty(gGoods.getSpecification())){
            return AjaxResult.error("规格不能为空");
        }
        if (StringUtils.isNotNull(gGoods.getType())){
            if (1L == gGoods.getType() && !org.apache.commons.lang.StringUtils.isNumeric(gGoods.getSpecification())){
                return AjaxResult.error("请输入数字！");
            }
        }
        if (StringUtils.isEmpty(gGoods.getUnit())){
            return AjaxResult.error("单位不能为空");
        }
        if (StringUtils.isNull(gGoods.getUnitPrice())){
            return AjaxResult.error("单价不能为空");
        }
        // update by 2021 3.15 dongchanglon 需求改为单价可以为0
        if (gGoods.getUnitPrice().compareTo(BigDecimal.ZERO)<0){
            return AjaxResult.error("单价不能小于0");
        }
        gGoods.setId(UUID.randomUUID().toString());
        gGoods.setDeleteFlag(Long.valueOf(1));
        gGoods.setCreateBy(tokenService.getLoginUser().getUsername());
        return toAjax(gGoodsService.insertGGoods(gGoods));
    }

    /**
     * 修改库存管理-物品
     */
    @PreAuthorize(hasPermi = "parts:goods:edit")
    @Log(title = "库存管理-物品编辑", businessType = BusinessType.UPDATE)
    @PostMapping("/K02EditSave")
    public AjaxResult K02EditSave(@RequestBody GGoods gGoods)
    {
        if (gGoods.getName() == null || gGoods.getName().equals("")) {
            return AjaxResult.error("商品名称不能为空");
        }
        List<GGoods>gGoodNameList = gGoodsService.countAll(gGoods.getName());
        long count = gGoodNameList.stream().filter(g -> !g.getId().equals(gGoods.getId())).count();
        if (count > 0) {
            return AjaxResult.error("此商品名称已存在");
        }
        if (StringUtils.isEmpty(gGoods.getCode())){
            return AjaxResult.error("物品编号不能为空");
        }
        List<GGoods>gGoodCodeList = gGoodsService.countCodeAll(gGoods.getCode());
        long code = gGoodCodeList.stream().filter(g -> !g.getId().equals(gGoods.getId())).count();
        if (code > 0) {
            return AjaxResult.error("物品编号已存在");
        }
        if (StringUtils.isEmpty(gGoods.getSpecification())){
            return AjaxResult.error("规格不能为空");
        }
        if (StringUtils.isEmpty(gGoods.getUnit())){
            return AjaxResult.error("单位不能为空");
        }
        if (StringUtils.isNull(gGoods.getUnitPrice())){
            return AjaxResult.error("单价不能为空");
        }
        // update by 2021 3.15 dongchanglon 需求改为单价可以为0
        if (gGoods.getUnitPrice().compareTo(BigDecimal.ZERO)<0){
            return AjaxResult.error("单价不能小于0");
        }
        gGoods.setUpdateBy(tokenService.getLoginUser().getUsername());
        return toAjax(gGoodsService.updateGGoods(gGoods));
    }

    /**
     * 删除库存管理-物品
     */
    @PreAuthorize(hasPermi = "parts:goods:remove")
    @Log(title = "库存管理-物品", businessType = BusinessType.DELETE)
	@GetMapping("/K02Delete/{id}")
    public AjaxResult K02Delete(@PathVariable String id)
    {
        GGoods gGoods = gGoodsService.selectGGoodsById(id);
        gGoods.setDeleteFlag(Long.valueOf(2));
        return toAjax(gGoodsService.updateGGoods(gGoods));
    }

}
