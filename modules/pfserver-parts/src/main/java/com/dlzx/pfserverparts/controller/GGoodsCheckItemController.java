package com.dlzx.pfserverparts.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverparts.domain.GGoods;
import com.dlzx.pfserverparts.domain.GGoodsCheck;
import com.dlzx.pfserverparts.domain.GGoodsInventory;
import com.dlzx.pfserverparts.domain.GGoodsType;
import com.dlzx.pfserverparts.service.IGGoodsCheckService;
import com.dlzx.pfserverparts.service.IGGoodsInventoryService;
import com.dlzx.pfserverparts.service.IGGoodsService;
import com.dlzx.pfserverparts.service.IGGoodsTypeService;
import com.dlzx.system.api.model.LoginUser;
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
import com.dlzx.pfserverparts.domain.GGoodsCheckItem;
import com.dlzx.pfserverparts.service.IGGoodsCheckItemService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 库存管理-盘点项Controller
 * 
 * @author dlzx
 * @date 2020-12-03
 */
@RestController
@RequestMapping("/parts/checkItem")
public class GGoodsCheckItemController extends BaseController
{
    @Autowired
    private IGGoodsCheckItemService gGoodsCheckItemService;

    @Autowired
    private IGGoodsTypeService gGoodsTypeService;

    @Autowired
    private IGGoodsInventoryService goodsInventoryService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IGGoodsService gGoodsService;

    @Autowired
    private IGGoodsCheckService goodsCheckService;
    /**
     * 查询库存管理-盘点项列表
     */
    @PreAuthorize(hasPermi = "parts:checkItem:list")
    @PostMapping("/list")
    public TableDataInfo list(GGoodsCheckItem gGoodsCheckItem)
    {
        startPage();
        List<GGoodsCheckItem> list = gGoodsCheckItemService.selectGGoodsCheckItemList(gGoodsCheckItem);
        return getDataTable(list);
    }

    /**
     * 查询库存管理-盘点项列表-根据g_goods_check的主键ID查询
     * check_id
     */
    @PreAuthorize(hasPermi = "parts:checkItem:list")
    @PostMapping("/K15CheckList")
    public TableDataInfo K15CheckList(@RequestBody  GGoodsCheckItem gGoodsCheckItem)
    {
        startPage(gGoodsCheckItem.getPageNum(), gGoodsCheckItem.getPageSize());
        List<GGoodsCheckItem> list = gGoodsCheckItemService.queryGGoodsCheckItemList(gGoodsCheckItem);
        return getDataTable(list);
    }

    /**
     * 导出库存管理-盘点项列表
     */
    @PreAuthorize(hasPermi = "parts:checkItem:export")
    @Log(title = "库存管理-盘点项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GGoodsCheckItem gGoodsCheckItem) throws IOException
    {
        List<GGoodsCheckItem> list = gGoodsCheckItemService.selectGGoodsCheckItemList(gGoodsCheckItem);
        ExcelUtil<GGoodsCheckItem> util = new ExcelUtil<GGoodsCheckItem>(GGoodsCheckItem.class);
        util.exportExcel(response, list, "item");
    }

    /**
     * 获取库存管理-盘点项详细信息
     */
    @PreAuthorize(hasPermi = "parts:checkItem:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(gGoodsCheckItemService.selectGGoodsCheckItemById(id));
    }

    /**
     * @describe 提交
     * @author DongCL
     * @date 2021-01-21 18:10
     * @param
     * @return
     */
//    @PreAuthorize(hasPermi = "parts:checkItem:add")
    @Log(title = "库存管理-盘点项提交", businessType = BusinessType.INSERT)
    @PostMapping("/K15Add")
    public AjaxResult K15Add(@RequestBody GGoodsCheck gGoodsCheck)
    {
        for (GGoodsCheckItem gGoodsCheckItem : gGoodsCheck.getgGoodsCheckItems()) {
            if (StringUtils.isNull(gGoodsCheckItem.getDifference())){
                return AjaxResult.error("请填写差异数量");
            }
            if (StringUtils.isNull(gGoodsCheckItem.getStatus())){
                return AjaxResult.error("请填写盈亏状态");
            }
        }
        return toAjax(gGoodsCheckItemService.submitCheckItem(gGoodsCheck));
    }

    /**
     * 新增库存管理-提交 -------
     * 前端抓取checkId传给java后台
     * 通过checkId修改g_goods_check中的status字段值 1未提交 2已提交
     */
    @PreAuthorize(hasPermi = "parts:checkItem:add")
    @Log(title = "库存管理-盘点项提交", businessType = BusinessType.INSERT)
    @RequestMapping(path = "/T04Add",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult T04Add(@RequestBody GGoodsCheckItem gGoodsCheckItem)
    {
        LoginUser loginUser = tokenService.getLoginUser();
        gGoodsCheckItem.setUpdateBy(loginUser.getUsername());
        // 获取ID
        List<GGoodsCheck> gGoodsChecks = goodsCheckService.selectGGoodsCheckByCheckId(gGoodsCheckItem.getCheckId());
        for (GGoodsCheck gGoodsCheck : gGoodsChecks) {
            // 修改为已提交
            gGoodsCheck.setStatus(Long.valueOf(2));
            gGoodsCheck.setUpdateBy(loginUser.getUsername());
            goodsCheckService.updateGGoodsCheck(gGoodsCheck);
        }
        return toAjax(gGoodsCheckItemService.updateGGoodsCheckItem(gGoodsCheckItem));
    }

    /**
     * 修改(编辑)库存管理-盘点项
     */
//    @PreAuthorize(hasPermi = "parts:checkItem:edit")
    @Log(title = "库存管理-盘点项", businessType = BusinessType.UPDATE)
    @PostMapping("/K15EditSave")
    public AjaxResult K15EditSave(@RequestBody GGoodsCheckItem gGoodsCheckItem)
    {
        if (StringUtils.isNull(gGoodsCheckItem.getCheckQuantity())){
            return AjaxResult.error("请填写盘点数量！");
        }
        // 计算
        gGoodsCheckItemService.updateCheckItem(gGoodsCheckItem);
        return toAjax(gGoodsCheckItemService.updateGGoodsCheckItem(gGoodsCheckItem));
    }


    @PreAuthorize(hasPermi = "parts:checkItem:list")
    @GetMapping("/K16List/{checkId}")
    public TableDataInfo K16List(@PathVariable("checkId") String checkId)
    {
        List<GGoodsCheckItem> list = gGoodsCheckItemService.selectGGoodsCheckItemByCheckId(checkId);
        return getDataTable(list);
    }

    /**
     * 查询库存管理-盘点项列表
     * K15Add提交之后才会出现在gGoodsCheckItem表里
     * 所以直接查询此表都是提交的数据
     */
    @PreAuthorize(hasPermi = "parts:checkItem:list")
    @PostMapping("/K16List")
    public TableDataInfo K16List(@RequestBody  GGoodsCheckItem gGoodsCheckItem)
    {
        startPage(gGoodsCheckItem.getPageNum(), gGoodsCheckItem.getPageSize());
        List<GGoodsCheckItem> list = gGoodsCheckItemService.selectGGoodsCheckItemList(gGoodsCheckItem);
        return getDataTable(list);
    }

    @PreAuthorize(hasPermi = "parts:checkItem:list")
    @PostMapping("/K16GoodsList")
    public TableDataInfo K16GoodsList(@RequestBody  GGoodsCheckItem gGoodsCheckItem)
    {
        startPage(gGoodsCheckItem.getPageNum(), gGoodsCheckItem.getPageSize());
        String checkId = gGoodsCheckItem.getCheckId();
        GGoods gGoods = gGoodsService.selectGGoodsById(checkId);
        ArrayList<GGoods> gGoods1 = new ArrayList<>();
        gGoods1.add(gGoods);
        return getDataTable(gGoods1);
    }

    /**
     * 查询库存管理-盘点项列表
     * K15Add提交之后才会出现在gGoodsCheckItem表里
     * 所以直接查询此表都是提交的数据/{checkId}
     */
    @PreAuthorize(hasPermi = "parts:checkItem:list")
    @RequestMapping("/T06List")
    @ResponseBody
    public TableDataInfo T06List(@RequestBody  GGoodsCheckItem gGoodsCheckItem)
    {
        startPage();
        List<GGoodsCheckItem> list = gGoodsCheckItemService.selectGGoodsCheckItemList(gGoodsCheckItem);
        return getDataTable(list);
    }


    /**
     * 删除库存管理-盘点项
     */
    @PreAuthorize(hasPermi = "parts:checkItem:remove")
    @Log(title = "库存管理-盘点项", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(gGoodsCheckItemService.deleteGGoodsCheckItemByIds(ids));
    }
}
