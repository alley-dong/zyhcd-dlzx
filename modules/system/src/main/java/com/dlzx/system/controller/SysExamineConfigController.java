package com.dlzx.system.controller;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.system.domain.SysExamineConfig;
import com.dlzx.system.service.ISysDictDataService;
import com.dlzx.system.service.ISysExamineConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统管理-审批配置Controller
 *
 * @author dlzx
 * @date 2020-12-11
 */
@RestController
@RequestMapping("/sysExamineConfig")
public class SysExamineConfigController  extends BaseController {

    @Autowired
    private ISysExamineConfigService sysExamineConfigService;

    @Autowired
    private ISysDictDataService dictDataService;


    /**
     * 查询系统管理-审批配置列表
     */
    @PreAuthorize(hasAnyPermi = { "system:config:list", "system:dict:query" })
    @GetMapping("/examineList")
    @ResponseBody
    public TableDataInfo list(SysExamineConfig sysExamineConfig) {
        startPage(sysExamineConfig.getPageNum(), sysExamineConfig.getPageSize());
        List<SysExamineConfig> list = sysExamineConfigService.selectSysExamineConfigList(sysExamineConfig);
        return getDataTable(list);
    }

//    @PreAuthorize(hasPermi = "system:data:query")
    @GetMapping(value = "/dictCode")
    public AjaxResult getInfo() {
        return AjaxResult.success(dictDataService.selectSysDictData());
    }
}
