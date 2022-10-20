package com.dlzx.pfserverparts.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.dlzx.pfserverparts.domain.GGoodsReceivingRecordDate;
import com.dlzx.pfserverparts.service.IGGoodsReceivingRecordDateService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 库存管理-物品领用记录-领取日期Controller
 * 
 * @author guo
 * @date 2020-12-11
 */
@RestController
@RequestMapping("/date")
public class GGoodsReceivingRecordDateController extends BaseController
{
    @Autowired
    private IGGoodsReceivingRecordDateService gGoodsReceivingRecordDateService;

    /**
     * 查询库存管理-物品领用记录-领取日期列表
     */
    @PreAuthorize(hasPermi = "system:date:list")
    @GetMapping("/list")
    public TableDataInfo list(GGoodsReceivingRecordDate gGoodsReceivingRecordDate)
    {
        startPage();
        List<GGoodsReceivingRecordDate> list = gGoodsReceivingRecordDateService.selectGGoodsReceivingRecordDateList(gGoodsReceivingRecordDate);
        return getDataTable(list);
    }

    /**
     * 导出库存管理-物品领用记录-领取日期列表
     */
    @PreAuthorize(hasPermi = "system:date:export")
    @Log(title = "库存管理-物品领用记录-领取日期", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GGoodsReceivingRecordDate gGoodsReceivingRecordDate) throws IOException
    {
        List<GGoodsReceivingRecordDate> list = gGoodsReceivingRecordDateService.selectGGoodsReceivingRecordDateList(gGoodsReceivingRecordDate);
        ExcelUtil<GGoodsReceivingRecordDate> util = new ExcelUtil<GGoodsReceivingRecordDate>(GGoodsReceivingRecordDate.class);
        util.exportExcel(response, list, "date");
    }

    /**
     * 获取库存管理-物品领用记录-领取日期详细信息
     */
    @PreAuthorize(hasPermi = "system:date:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(gGoodsReceivingRecordDateService.selectGGoodsReceivingRecordDateById(id));
    }

    /**
     * 新增库存管理-物品领用记录-领取日期
     */
    @PreAuthorize(hasPermi = "system:date:add")
    @Log(title = "库存管理-物品领用记录-领取日期", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GGoodsReceivingRecordDate gGoodsReceivingRecordDate)
    {
        return toAjax(gGoodsReceivingRecordDateService.insertGGoodsReceivingRecordDate(gGoodsReceivingRecordDate));
    }

    /**
     * 修改库存管理-物品领用记录-领取日期
     */
    @PreAuthorize(hasPermi = "system:date:edit")
    @Log(title = "库存管理-物品领用记录-领取日期", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GGoodsReceivingRecordDate gGoodsReceivingRecordDate)
    {
        return toAjax(gGoodsReceivingRecordDateService.updateGGoodsReceivingRecordDate(gGoodsReceivingRecordDate));
    }

    /**
     * 删除库存管理-物品领用记录-领取日期
     */
    @PreAuthorize(hasPermi = "system:date:remove")
    @Log(title = "库存管理-物品领用记录-领取日期", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(gGoodsReceivingRecordDateService.deleteGGoodsReceivingRecordDateByIds(ids));
    }
}
