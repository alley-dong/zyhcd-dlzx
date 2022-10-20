package com.dlzx.system.controller;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.system.api.model.LoginUser;
import com.dlzx.system.domain.SysExamineConfigProcess;
import com.dlzx.system.service.SysExamineConfigProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;


/**
 * 系统设置
 *
 * @author guoyonggang
 */
@RestController
@RequestMapping("/sysExamineConfigProcess")
public class SysExamineConfigProcessController extends BaseController {

    @Autowired
    SysExamineConfigProcessService sysExamineConfigProcessService;

    /**
     * 查询系统管理-审批流程配置列表
     */
//    @PreAuthorize(hasPermi = "system:process:list")
    @GetMapping("/examineConfigProcessList")
    public AjaxResult list(SysExamineConfigProcess sysExamineConfigProcess) {
        List<SysExamineConfigProcess> list = sysExamineConfigProcessService.selectSysExamineConfigProcessList(sysExamineConfigProcess);
        return AjaxResult.success(list);
    }

    /**
     * 修改系统管理-审批流程配置
     */
    @PreAuthorize(hasPermi = "system:process:edit")
    @Log(title = "系统管理-审批流程配置", businessType = BusinessType.UPDATE)
    @PostMapping("/examineConfigProcessEdit")
    public AjaxResult edit(@RequestBody SysExamineConfigProcess sysExamineConfigProcess) {
        return sysExamineConfigProcessService.updateSysExamineConfigProcess(sysExamineConfigProcess);
    }

    /**
     * 删除系统管理-审批流程配置
     */
    @PreAuthorize(hasPermi = "system:process:remove")
    @Log(title = "系统管理-审批流程配置", businessType = BusinessType.DELETE)
    @GetMapping("/examineConfigProcessDelete")
    public AjaxResult remove(SysExamineConfigProcess sysExamineConfigProcess) {
        return sysExamineConfigProcessService.deleteSysExamineConfigProcessById(sysExamineConfigProcess);
    }

    /**
     * 新增系统管理-审批流程配置
     */
    @PreAuthorize(hasPermi = "system:process:add")
    @Log(title = "系统管理-审批流程配置", businessType = BusinessType.INSERT)
    @PostMapping("/examineConfigProcessAdd")
    public AjaxResult add(@RequestBody SysExamineConfigProcess sysExamineConfigProcess) {
        return sysExamineConfigProcessService.insertSysExamineConfigProcess(sysExamineConfigProcess);
    }
}
