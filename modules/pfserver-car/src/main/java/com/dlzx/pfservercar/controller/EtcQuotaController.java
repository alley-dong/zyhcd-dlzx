package com.dlzx.pfservercar.controller;

import java.util.List;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.validation.annotation.Validated;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfservercar.domain.EtcQuota;
import com.dlzx.pfservercar.service.IEtcQuotaService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * ETC管理-ETC定额Controller
 *
 * @author dlzx
 * @date 2020-12-02
 */
@RestController
@RequestMapping("/car/etcQuota")
public class EtcQuotaController extends BaseController {
    @Autowired
    private IEtcQuotaService etcQuotaService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询ETC管理-ETC定额列表
     */
    @PreAuthorize(hasPermi = "car:etcQuota:list")
    @PostMapping("/J04List")
    public TableDataInfo list(@RequestBody EtcQuota etcQuota) {
        startPage(etcQuota.getPageNum(), etcQuota.getPageSize());
        List<EtcQuota> list = etcQuotaService.selectEtcQuotaList(etcQuota);
        return getDataTable(list);
    }

    /**
     * 导出ETC管理-ETC定额列表
     */
    @PreAuthorize(hasPermi = "car:etcQuota:export")
    @Log(title = "ETC管理-ETC定额", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EtcQuota etcQuota) throws IOException {
        List<EtcQuota> list = etcQuotaService.selectEtcQuotaList(etcQuota);
        ExcelUtil<EtcQuota> util = new ExcelUtil<EtcQuota>(EtcQuota.class);
        util.exportExcel(response, list, "quota");
    }

    /**
     * 获取ETC管理-ETC定额详细信息
     */
    @PreAuthorize(hasPermi = "car:etcQuota:edit")
    @GetMapping(value = "/J04GetInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(etcQuotaService.selectEtcQuotaById(id));
    }

    /**
     * 新增ETC管理-ETC定额
     */
    @PreAuthorize(hasPermi = "car:etcQuota:add")
    @Log(title = "ETC管理-ETC定额", businessType = BusinessType.INSERT)
    @PostMapping("/J04Add")
    public AjaxResult add(@RequestBody @Validated EtcQuota etcQuota) {
        List<EtcQuota> list = etcQuotaService.selectEtcQuotaListDingE(etcQuota.getRouteId(),etcQuota.getVehicleModel());
        List<EtcQuota> etcQuotaList = etcQuotaService.selectEtcQuotaList(new EtcQuota());
        if (list.size() == 0) {
            if (etcQuotaList != null && etcQuotaList.size()>0){
                for (EtcQuota etcQuotaOne : etcQuotaList){
                    if (etcQuota.getName().equals(etcQuotaOne.getName())){
                        return AjaxResult.error("定额名称已存在");
                    }
                }
            }
            LoginUser userInfo = tokenService.getLoginUser();
            String Id = UUID.randomUUID().toString();
            etcQuota.setId(Id);
            etcQuota.setCreateBy(userInfo.getUsername());
            etcQuota.setDeleteFlag(Long.valueOf(1));
            return toAjax(etcQuotaService.insertEtcQuota(etcQuota));
        } else {
            return AjaxResult.error("该定额已存在");
        }
    }


    /**
     * 修改ETC管理-ETC定额
     */
    @PreAuthorize(hasPermi = "car:etcQuota:edit")
    @Log(title = "ETC管理-ETC定额", businessType = BusinessType.UPDATE)
    @PostMapping("/J04Edit")
    public AjaxResult edit(@RequestBody @Validated EtcQuota etcQuota) {
        List<EtcQuota> list = etcQuotaService.selectEtcQuotaListDingE(etcQuota.getRouteId(),etcQuota.getVehicleModel());
        EtcQuota etcQuota1 = etcQuotaService.selectEtcQuotaById(etcQuota.getId());
        if (list != null && list.size()>0) {
            for (EtcQuota etcQuotaOne : list) {
                if (!etcQuotaOne.getId().equals(etcQuota.getId())) {
                    return AjaxResult.error("定额已存在");
                }
            }
        }
        return toAjax(etcQuotaService.updateEtcQuota(etcQuota));
    }

    /**
     * 删除ETC管理-ETC定额
     */
    @PreAuthorize(hasPermi = "car:etcQuota:delete")
    @Log(title = "ETC管理-ETC定额", businessType = BusinessType.DELETE)
    @GetMapping("/J04Delete/{id}")
    public AjaxResult remove(@PathVariable String id) {
        LoginUser userInfo = tokenService.getLoginUser();
        EtcQuota etcQuota = etcQuotaService.selectEtcQuotaById(id);
        etcQuota.setDeleteFlag(Long.valueOf(2));
        etcQuota.setUpdateBy(userInfo.getUsername());
        return toAjax(etcQuotaService.updateEtcQuota(etcQuota));
    }
}
