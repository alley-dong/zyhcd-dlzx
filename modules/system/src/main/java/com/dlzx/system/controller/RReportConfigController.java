package com.dlzx.system.controller;

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
import com.dlzx.system.domain.RReportConfig;
import com.dlzx.system.service.IRReportConfigService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 统计报-统计数据配置Controller
 * 
 * @author dlzx
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/statisticsConfig")
public class RReportConfigController extends BaseController
{
    @Autowired
    private IRReportConfigService rReportConfigService;

    /**
     * 获取统计报-统计数据配置详细信息/列表
     */
    @PreAuthorize(hasPermi = "system:statisticsConfig:query")
    @GetMapping(value = "/B08Info")
    public AjaxResult B08Info()
    {
        return AjaxResult.success(rReportConfigService.selectRReportConfigById("1"));
    }

    /**
     * 修改统计报-统计数据配置
     */
    @PreAuthorize(hasPermi = "system:statisticsConfig:edit")
    @Log(title = "统计报-统计数据配置", businessType = BusinessType.UPDATE)
    @PostMapping("/B08Edit")
    public AjaxResult B08Edit(@RequestBody RReportConfig rReportConfig)
    {
        rReportConfig.setId("1");
        return toAjax(rReportConfigService.updateRReportConfig(rReportConfig));
    }
    /**
     * 获取统计报-统计数据配置详细信息/列表
     */
    @PreAuthorize(hasPermi = "system:statisticsConfig:info")
    @GetMapping(value = "/Info")
    public RReportConfig Info()
    {
        return rReportConfigService.selectRReportConfigById("1");
    }
}
