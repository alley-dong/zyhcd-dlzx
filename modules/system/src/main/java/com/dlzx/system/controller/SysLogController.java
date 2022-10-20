package com.dlzx.system.controller;

import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.system.domain.SysLog;
import com.dlzx.system.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * 【请填写功能名称】Controller
 *
 * @author dongchanglong
 * @date 2021-05-06
 */
@RestController
@RequestMapping("/log")
public class SysLogController extends BaseController {
    @Autowired
    private ISysLogService sysLogService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize(hasPermi = "system:log:list")
    @GetMapping("/list")
    public TableDataInfo list(SysLog sysLog) {
        startPage();
        List<SysLog> list = sysLogService.selectSysLogList(sysLog);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize(hasPermi = "system:log:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysLog sysLog) throws IOException {
        List<SysLog> list = sysLogService.selectSysLogList(sysLog);
        ExcelUtil<SysLog> util = new ExcelUtil<SysLog>(SysLog.class);
        util.exportExcel(response, list, "log");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize(hasPermi = "system:log:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(sysLogService.selectSysLogById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
//    @PreAuthorize(hasPermi = "system:log:add")
    @RequestMapping(path ="/insertSysLog",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public int add(@RequestBody SysLog sysLog) {
        return sysLogService.insertSysLog(sysLog);
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize(hasPermi = "system:log:edit")
    @PutMapping
    public AjaxResult edit(@RequestBody SysLog sysLog) {
        return toAjax(sysLogService.updateSysLog(sysLog));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize(hasPermi = "system:log:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(sysLogService.deleteSysLogByIds(ids));
    }
}
