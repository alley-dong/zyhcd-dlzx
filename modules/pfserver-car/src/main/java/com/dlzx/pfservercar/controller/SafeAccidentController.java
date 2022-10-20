package com.dlzx.pfservercar.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.system.api.model.LoginUser;
import org.checkerframework.checker.units.qual.A;
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
import com.dlzx.pfservercar.domain.SafeAccident;
import com.dlzx.pfservercar.service.ISafeAccidentService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 安全管理-事故Controller
 * 
 * @author dlzx
 * @date 2020-12-08
 */
@RestController
@RequestMapping("/accident")
public class SafeAccidentController extends BaseController
{
    @Autowired
    private ISafeAccidentService safeAccidentService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询安全管理-事故列表
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:info")
    @PostMapping(value = {"/L01SafeAccidentList", "/A30List"})
    public TableDataInfo list(@RequestBody SafeAccident safeAccident)
    {
        startPage(safeAccident.getPageNum(),safeAccident.getPageSize());
        List<SafeAccident> list = safeAccidentService.selectSafeAccidentList(safeAccident);
        return getDataTable(list);
    }

    /**
     * 交通事故审批
     */
    @PreAuthorize(hasPermi = "pfservercar:vacation:check")
    @Log(title = "交通事故审批", businessType = BusinessType.UPDATE)
    @PostMapping("/L02SafeAccidentCheck")
    public AjaxResult check(@RequestBody SafeAccident safeAccident)
    {
        if (StringUtils.isEmpty(safeAccident.getStatus().toString())){
            return AjaxResult.error("请选择审核结果");
        }
        if (safeAccident.getStatus()==2 && StringUtils.isEmpty(safeAccident.getReviewRemarks())){
            return AjaxResult.error("请输入不通过原因");
        }
        // 后台操作人
        LoginUser userInfo = tokenService.getLoginUser();
        safeAccident.setUserid(userInfo.getUserid()+"");
        safeAccident.setCreateBy(userInfo.getUsername()+"");
        return safeAccidentService.checkSafeAccident(safeAccident);
    }

    /**
     * 获取安全管理-事故详细信息
     */
    @PreAuthorize(hasPermi = "pfservercar:accident:info")
    @GetMapping(value = {"/L03SafeAccidentInfo/{id}", "/A31GetInfo/{id}"})
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(safeAccidentService.selectSafeAccidentById(id));
    }






    /**
     * 导出安全管理-事故列表
     */
    @PreAuthorize(hasPermi = "pfservercar:accident:export")
    @Log(title = "安全管理-事故", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SafeAccident safeAccident) throws IOException
    {
        List<SafeAccident> list = safeAccidentService.selectSafeAccidentList(safeAccident);
        ExcelUtil<SafeAccident> util = new ExcelUtil<SafeAccident>(SafeAccident.class);
        util.exportExcel(response, list, "accident");
    }

    /**
     * 新增安全管理-事故
     */
    @PreAuthorize(hasPermi = "pfservercar:accident:add")
    @Log(title = "安全管理-事故", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SafeAccident safeAccident)
    {
        return toAjax(safeAccidentService.insertSafeAccident(safeAccident));
    }

    /**
     * 修改安全管理-事故
     */
    @PreAuthorize(hasPermi = "pfservercar:accident:edit")
    @Log(title = "安全管理-事故", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SafeAccident safeAccident)
    {
        return toAjax(safeAccidentService.updateSafeAccident(safeAccident));
    }

    /**
     * 删除安全管理-事故
     */
    @PreAuthorize(hasPermi = "pfservercar:accident:remove")
    @Log(title = "安全管理-事故", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(safeAccidentService.deleteSafeAccidentByIds(ids));
    }

}
