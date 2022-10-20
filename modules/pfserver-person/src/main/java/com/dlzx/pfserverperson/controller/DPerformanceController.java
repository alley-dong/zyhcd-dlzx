package com.dlzx.pfserverperson.controller;

import java.util.List;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverperson.domain.DPerformanceChang;
import com.dlzx.pfserverperson.domain.DPerformanceDuan;
import com.dlzx.system.api.model.LoginUser;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import com.dlzx.pfserverperson.domain.DPerformance;
import com.dlzx.pfserverperson.service.IDPerformanceService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 驾驶员-绩效Controller
 *
 * @author dlzx
 * @date 2020-11-30
 */
@RestController
@RequestMapping("/person/performance")
public class DPerformanceController extends BaseController {
    @Autowired
    private IDPerformanceService dPerformanceService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询驾驶员-绩效列表
     */
    @PreAuthorize(hasPermi = "person:performance:list")
    @PostMapping("/G01List")
    public TableDataInfo list(@RequestBody DPerformance dPerformance) {
        startPage(dPerformance.getPageNum(), dPerformance.getPageSize());
        List<DPerformance> list = dPerformanceService.selectDPerformanceList(dPerformance);
        return getDataTable(list);
    }

    /**
     * 导出驾驶员-绩效列表
     */
    @PreAuthorize(hasPermi = "person:performance:export")
    @Log(title = "驾驶员-绩效", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DPerformance dPerformance) throws IOException {
        List<DPerformance> list = dPerformanceService.selectDPerformanceList(dPerformance);
        ExcelUtil<DPerformance> util = new ExcelUtil<DPerformance>(DPerformance.class);
        util.exportExcel(response, list, "performance");
    }

    /**
     * 获取驾驶员-绩效详细信息
     */
    @PreAuthorize(hasPermi = "person:performance:getInfo")
    @GetMapping(value = "/G01GetInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(dPerformanceService.selectDPerformanceById(id));
    }

    /**
     * 新增驾驶员-绩效
     */
    @PreAuthorize(hasPermi = "person:performance:add")
    @Log(title = "驾驶员-绩效", businessType = BusinessType.INSERT)
    @PostMapping("/G02AddChang")
    public AjaxResult ChangAdd(@RequestBody @Validated DPerformanceChang dPerformanceChang) {
        String name = dPerformanceChang.getName();
        DPerformance dPerformance = dPerformanceService.selectDPerformanceByName(name);
        if (dPerformance != null) {
            return AjaxResult.error("该绩效名称已存在");
        } else {
            LoginUser userInfo = tokenService.getLoginUser();
            String Id = UUID.randomUUID().toString();
            dPerformanceChang.setId(Id);
            dPerformanceChang.setCreateBy(userInfo.getUsername());
            dPerformanceChang.setDeleteFlag(Long.valueOf(1));
            return toAjax(dPerformanceService.insertDPerformanceC(dPerformanceChang));
        }
    }

    @PreAuthorize(hasPermi = "person:performance:add")
    @Log(title = "驾驶员-绩效", businessType = BusinessType.INSERT)
    @PostMapping("/G05AddDuan")
    public AjaxResult DuanAdd(@RequestBody @Validated DPerformanceDuan dPerformanceDuan) {
        String name = dPerformanceDuan.getName();
        DPerformance dPerformance = dPerformanceService.selectDPerformanceByName(name);
        if (dPerformance != null) {
            return AjaxResult.error("该绩效名称已存在");
        } else {
            LoginUser userInfo = tokenService.getLoginUser();
            String Id = UUID.randomUUID().toString();
            dPerformanceDuan.setId(Id);
            dPerformanceDuan.setCreateBy(userInfo.getUsername());
            dPerformanceDuan.setDeleteFlag(Long.valueOf(1));
            return toAjax(dPerformanceService.insertDPerformanceD(dPerformanceDuan));
        }
    }

    /**
     * 修改驾驶员-绩效
     */
    @PreAuthorize(hasPermi = "person:performance:edit")
    @Log(title = "驾驶员-绩效", businessType = BusinessType.UPDATE)
    @PostMapping("/G04EditChang")
    public AjaxResult ChangEdit(@RequestBody @Validated DPerformanceChang dPerformanceChang) {
        String name = dPerformanceChang.getName();
        DPerformance dPerformance = dPerformanceService.selectDPerformanceByName(name);
        DPerformance dPerformance1=dPerformanceService.selectDPerformanceById(dPerformanceChang.getId());
        if (dPerformance != null && !dPerformance.getName().equals(dPerformance1.getName())) {
            return AjaxResult.error("该绩效名称已存在");
        } else {
            LoginUser userInfo = tokenService.getLoginUser();
            dPerformanceChang.setUpdateBy(userInfo.getUsername());
            return toAjax(dPerformanceService.updateDPerformanceC(dPerformanceChang));
        }
    }

    @PreAuthorize(hasPermi = "person:performance:edit")
    @Log(title = "驾驶员-绩效", businessType = BusinessType.UPDATE)
    @PostMapping("/G07EditDuan")
    public AjaxResult DuanEdit(@RequestBody @Validated DPerformanceDuan dPerformanceDuan) {
        String name = dPerformanceDuan.getName();
        DPerformance dPerformance = dPerformanceService.selectDPerformanceByName(name);
        DPerformance dPerformance1=dPerformanceService.selectDPerformanceById(dPerformanceDuan.getId());
        if (dPerformance != null && !dPerformance.getName().equals(dPerformance1.getName())) {
            return AjaxResult.error("该绩效名称已存在");
        } else {
            LoginUser userInfo = tokenService.getLoginUser();
            dPerformanceDuan.setUpdateBy(userInfo.getUsername());
            return toAjax(dPerformanceService.updateDPerformanceD(dPerformanceDuan));
        }
    }

    /**
     * 删除驾驶员-绩效
     */
    @PreAuthorize(hasPermi = "person:performance:delete")
    @Log(title = "驾驶员-绩效", businessType = BusinessType.DELETE)
    @GetMapping("/G01Delete/{id}")
    public AjaxResult remove(@PathVariable String id) {
        LoginUser userInfo = tokenService.getLoginUser();
        DPerformance dPerformance = dPerformanceService.selectDPerformanceById(id);
        dPerformance.setDeleteFlag(Long.valueOf(2));
        dPerformance.setUpdateBy(userInfo.getUsername());
        return toAjax(dPerformanceService.updateDPerformance(dPerformance));
    }
}
