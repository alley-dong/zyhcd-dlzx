package com.dlzx.pfservercar.controller;

import java.util.List;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.TokenService;
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
import com.dlzx.pfservercar.domain.FFuelQuota;
import com.dlzx.pfservercar.service.IFFuelQuotaService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 燃料管理-燃油定额Controller
 * 
 * @author dlzx
 * @date 2020-12-02
 */
@RestController
@RequestMapping("/fuelQuota")
public class FFuelQuotaController extends BaseController
{
    @Autowired
    private IFFuelQuotaService fFuelQuotaService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询燃料管理-燃油定额列表
     */
    @PreAuthorize(hasPermi = "pfservercar:quota:list")
    @PostMapping("/I02OilPlantsMoneyList")
    public TableDataInfo list(@RequestBody FFuelQuota fFuelQuota)
    {
        startPage(fFuelQuota.getPageNum(),fFuelQuota.getPageSize());
        List<FFuelQuota> list = fFuelQuotaService.selectFFuelQuotaList(fFuelQuota);
        return getDataTable(list);
    }

    /**
     * 获取燃料管理-燃油定额详细信息
     */
    @PreAuthorize(hasPermi = "pfservercar:quota:edit")
    @GetMapping(value = "/I02OilPlantsMoneyInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(fFuelQuotaService.selectFFuelQuotaById(id));
    }

    /**
     * 新增燃料管理-燃油定额
     */
    @PreAuthorize(hasPermi = "pfservercar:quota:add")
    @Log(title = "燃料管理-燃油定额", businessType = BusinessType.INSERT)
    @PostMapping("/I02OilPlantsMoneyAdd")
    public AjaxResult add(@RequestBody FFuelQuota fFuelQuota)
    {
        if (StringUtils.isEmpty(fFuelQuota.getRouteId()) && StringUtils.isEmpty(fFuelQuota.getRouteName())){
            return AjaxResult.error("线路名称不能为空");
        }
        if (StringUtils.isEmpty(fFuelQuota.getName())){
            return AjaxResult.error("定额名称不能为空");
        }
        if (StringUtils.isEmpty(fFuelQuota.getVehicleModel()) && StringUtils.isEmpty(fFuelQuota.getVehicleModelName())){
            return AjaxResult.error("车型不能为空");
        }
        if (StringUtils.isNull(fFuelQuota.getQuota())){
            return AjaxResult.error("油料定额不能为空");
        }
        if (StringUtils.isNull(fFuelQuota.getFuelType())){
            return AjaxResult.error("油料类型不能为空");
        }
        fFuelQuota.setId(UUID.randomUUID()+"");
        LoginUser userInfo = tokenService.getLoginUser();
        fFuelQuota.setCreateBy(userInfo.getUsername());
        return toAjax(fFuelQuotaService.insertFFuelQuota(fFuelQuota));
    }

    /**
     * 修改燃料管理-燃油定额
     */
    @PreAuthorize(hasPermi = "pfservercar:quota:edit")
    @Log(title = "燃料管理-燃油定额", businessType = BusinessType.UPDATE)
    @PostMapping("/I02OilPlantsMoneyEditSave")
    public AjaxResult edit(@RequestBody FFuelQuota fFuelQuota)
    {
        if (StringUtils.isEmpty(fFuelQuota.getRouteId()) && StringUtils.isEmpty(fFuelQuota.getRouteName())){
            return AjaxResult.error("线路名称不能为空");
        }
        if (StringUtils.isEmpty(fFuelQuota.getName())){
            return AjaxResult.error("定额名称不能为空");
        }
        if (StringUtils.isEmpty(fFuelQuota.getVehicleModel()) && StringUtils.isEmpty(fFuelQuota.getVehicleModelName())){
            return AjaxResult.error("车型不能为空");
        }
        if (StringUtils.isNull(fFuelQuota.getQuota())){
            return AjaxResult.error("油料定额不能为空");
        }
        LoginUser userInfo = tokenService.getLoginUser();
        fFuelQuota.setUpdateBy(userInfo.getUsername());
        return toAjax(fFuelQuotaService.updateFFuelQuota(fFuelQuota));
    }

    /**
     * 删除燃料管理-燃油定额
     */
    @PreAuthorize(hasPermi = "pfservercar:quota:delete")
    @Log(title = "燃料管理-燃油定额", businessType = BusinessType.DELETE)
	@GetMapping("/I02OilPlantsMoneyDelete/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(fFuelQuotaService.deleteFFuelQuotaByIds(ids));
    }

    /**
     * 导出燃料管理-燃油定额列表
     */
    @PreAuthorize(hasPermi = "pfservercar:quota:export")
    @Log(title = "燃料管理-燃油定额", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FFuelQuota fFuelQuota) throws IOException
    {
        List<FFuelQuota> list = fFuelQuotaService.selectFFuelQuotaList(fFuelQuota);
        ExcelUtil<FFuelQuota> util = new ExcelUtil<FFuelQuota>(FFuelQuota.class);
        util.exportExcel(response, list, "quota");
    }
}
