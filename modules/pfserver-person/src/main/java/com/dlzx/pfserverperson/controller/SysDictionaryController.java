package com.dlzx.pfserverperson.controller;

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
import com.dlzx.pfserverperson.domain.SysDictionary;
import com.dlzx.pfserverperson.service.ISysDictionaryService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 字典多级联动Controller
 * 
 * @author yangluhe
 * @date 2020-11-30
 */
@RestController
@RequestMapping("/dictionary")
public class SysDictionaryController extends BaseController
{
    @Autowired
    private ISysDictionaryService sysDictionaryService;

    /**
     * 查询字典多级联动列表
     */
    @PreAuthorize(hasPermi = "system:dictionary:list")
    @GetMapping("/list")
    public TableDataInfo list(SysDictionary sysDictionary)
    {
        startPage();
        List<SysDictionary> list = sysDictionaryService.selectSysDictionaryList(sysDictionary);
        return getDataTable(list);
    }

    /**
     * 导出字典多级联动列表
     */
    @PreAuthorize(hasPermi = "system:dictionary:export")
    @Log(title = "字典多级联动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysDictionary sysDictionary) throws IOException
    {
        List<SysDictionary> list = sysDictionaryService.selectSysDictionaryList(sysDictionary);
        ExcelUtil<SysDictionary> util = new ExcelUtil<SysDictionary>(SysDictionary.class);
        util.exportExcel(response, list, "dictionary");
    }

    /**
     * 获取字典多级联动详细信息
     */
    @PreAuthorize(hasPermi = "system:dictionary:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysDictionaryService.selectSysDictionaryById(id));
    }

    /**
     * 新增字典多级联动
     */
    @PreAuthorize(hasPermi = "system:dictionary:add")
    @Log(title = "字典多级联动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysDictionary sysDictionary)
    {
        return toAjax(sysDictionaryService.insertSysDictionary(sysDictionary));
    }

    /**
     * 修改字典多级联动
     */
    @PreAuthorize(hasPermi = "system:dictionary:edit")
    @Log(title = "字典多级联动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysDictionary sysDictionary)
    {
        return toAjax(sysDictionaryService.updateSysDictionary(sysDictionary));
    }

    /**
     * 删除字典多级联动
     */
    @PreAuthorize(hasPermi = "system:dictionary:remove")
    @Log(title = "字典多级联动", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(sysDictionaryService.deleteSysDictionaryByIds(ids));
    }
}
