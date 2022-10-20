package com.dlzx.pfserverparts.controller;

import java.util.List;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverparts.domain.GGoods;
import com.dlzx.pfserverparts.service.IGGoodsService;
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
import com.dlzx.pfserverparts.domain.GGoodsType;
import com.dlzx.pfserverparts.service.IGGoodsTypeService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 库存管理-物品类别Controller
 * 
 * @author jijiawen
 * @date 2020-12-01
 */
@RestController
@RequestMapping("/parts/goodsType")
public class GGoodsTypeController extends BaseController
{
    @Autowired
    private IGGoodsTypeService gGoodsTypeService;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private IGGoodsService goodsService;

    /**
     * 查询库存管理-物品类别列表
     */
    @PreAuthorize(hasPermi = "parts:goodsType:list")
    @PostMapping(value = {"/K01List"})
    public TableDataInfo K01List(@RequestBody GGoodsType gGoodsType)
    {
        startPage(gGoodsType.getPageNum(), gGoodsType.getPageSize());
        List<GGoodsType> list = gGoodsTypeService.selectGGoodsTypeList(gGoodsType);
        return getDataTable(list);
    }

    /**
     * 导出库存管理-物品类别列表
     */
    @PreAuthorize(hasPermi = "parts:goodsType:export")
    @Log(title = "库存管理-物品类别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GGoodsType gGoodsType) throws IOException
    {
        List<GGoodsType> list = gGoodsTypeService.selectGGoodsTypeList(gGoodsType);
        ExcelUtil<GGoodsType> util = new ExcelUtil<GGoodsType>(GGoodsType.class);
        util.exportExcel(response, list, "pfserverparts");
    }

    /**
     * 获取库存管理-物品类别详细信息
     */
    @PreAuthorize(hasPermi = "parts:goodsType:edit")
    @GetMapping(value = "/K01GetInfo/{id}")
    public AjaxResult K01GetInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(gGoodsTypeService.selectGGoodsTypeById(id));
    }

    /**
     * 新增库存管理-物品类别
     */
    @PreAuthorize(hasPermi = "parts:goodsType:add")
    @Log(title = "库存管理-物品类别", businessType = BusinessType.INSERT)
    @PostMapping("/K01Add")
    public AjaxResult K01Add(@RequestBody GGoodsType gGoodsType)
    {
        if (gGoodsType.getName() == null || gGoodsType.getName().equals("")) {
            return AjaxResult.error("物品类别不能为空");
        }
        int i = gGoodsTypeService.countAll(gGoodsType.getName());
        if (i > 0) {
            return AjaxResult.error("此物品类别已存在");
        }

        if ("尿素".equals(gGoodsType.getName())
                || "轮胎".equals(gGoodsType.getName())
                || "工作餐".equals(gGoodsType.getName())
                ||"低值易耗品".equals(gGoodsType.getName())
                ||"备件".equals(gGoodsType.getName())){
            gGoodsType.setIsDelete(2L);
        }
        gGoodsType.setCreateBy(tokenService.getLoginUser().getUsername());
        gGoodsType.setId(UUID.randomUUID().toString());
        gGoodsType.setDeleteFlag(Long.valueOf(1));
        gGoodsType.setStatus(Long.valueOf(1));
        return toAjax(gGoodsTypeService.insertGGoodsType(gGoodsType));
    }

    /**
     * 修改库存管理-物品类别
     */
    @PreAuthorize(hasPermi = "parts:goodsType:edit")
    @Log(title = "库存管理-物品类别-禁用", businessType = BusinessType.UPDATE)
    @PostMapping("/K01EditSave")
    public AjaxResult K01EditSave(@RequestBody GGoodsType gGoodsType)
    {
        if (gGoodsType.getName() == null || gGoodsType.getName().equals("")) {
            return AjaxResult.error("物品类别不能为空");
        }
        int i = gGoodsTypeService.countAllEdit(gGoodsType.getName());
        if (i > 1) {
            return AjaxResult.error("此物品类别已存在");
        }
        if (StringUtils.isNotNull(gGoodsType.getStatus()) && gGoodsType.getStatus()==2){
            GGoods gGood = new GGoods();
            gGood.setGoodsTypeId(gGoodsType.getId());
            List<GGoods> gGoods = goodsService.selectGGoodsList(gGood);
            if (gGoods.size()>0){
                AjaxResult.error("当前类别下存在商品不能删除！");
            }
        }
        gGoodsType.setUpdateBy(tokenService.getLoginUser().getUsername());
        return toAjax(gGoodsTypeService.updateGGoodsType(gGoodsType));
    }

    /**
     * 删除库存管理-物品类别
     */
    @PreAuthorize(hasPermi = "parts:goodsType:remove")
    @Log(title = "库存管理-物品类别", businessType = BusinessType.DELETE)
	@GetMapping("/K01Delete/{id}")
    public AjaxResult K01Delete(@PathVariable String id)
    {
        GGoods gGood = new GGoods();
        gGood.setGoodsTypeId(id);
        List<GGoods> gGoods = goodsService.selectGGoodsList(gGood);
        if (gGoods.size()>0){
            return AjaxResult.error("当前类别下存在商品不能删除！");
        }

        GGoodsType gGoodsType = gGoodsTypeService.selectGGoodsTypeById(id);
       gGoodsType.setDeleteFlag(Long.valueOf(2));
       return toAjax(gGoodsTypeService.updateGGoodsType(gGoodsType));
    }
}
