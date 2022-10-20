package com.dlzx.pfserverperson.controller;

import java.util.List;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
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
import com.dlzx.pfserverperson.domain.PCarrier;
import com.dlzx.pfserverperson.service.IPCarrierService;

/**
 * 合作方管理-供应商Controller
 * 
 * @author dlzx
 * @date 2020-11-26
 */
@RestController
@RequestMapping("/carrier")
public class PCarrierController extends BaseController
{
    @Autowired
    private IPCarrierService pCarrierService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询合作方管理-供应商列表
     */
//    @PreAuthorize(hasPermi = "pfserverperson:carrier:list")
    @PostMapping("/F09CarriersList")
    public TableDataInfo list(@RequestBody PCarrier pCarrier)
    {
        startPage(pCarrier.getPageNum(),pCarrier.getPageSize());
        List<PCarrier> list = pCarrierService.selectPCarrierList(pCarrier);
        return getDataTable(list);
    }

    /**
     * 导出合作方管理-供应商列表
     */
    @PreAuthorize(hasPermi = "pfserverperson:carrier:export")
    @Log(title = "合作方管理-供应商", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PCarrier pCarrier) throws IOException
    {
        List<PCarrier> list = pCarrierService.selectPCarrierList(pCarrier);
        ExcelUtil<PCarrier> util = new ExcelUtil<PCarrier>(PCarrier.class);
        util.exportExcel(response, list, "carrier");
    }

    /**
     * 获取合作方管理-供应商详细信息
     */
    @PreAuthorize(hasPermi = "pfserverperson:carrier:edit")
    @GetMapping(value = "/F09CarriersInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(pCarrierService.selectPCarrierById(id));
    }

    /**
     * 新增合作方管理-供应商
     */
    @PreAuthorize(hasPermi = "pfserverperson:carrier:add")
    @Log(title = "合作方管理-供应商", businessType = BusinessType.INSERT)
    @PostMapping("/F09CarriersAdd")
    public AjaxResult add(@RequestBody PCarrier pCarrier)
    {
        // 校验运营商名称不能为空
        if (StringUtils.isEmpty(pCarrier.getName())){
            return AjaxResult.error("供应商名称不能为空");
        }
        // 校验添加的供应商名称是否重复
        if (!pCarrierService.checkPCarrierName(pCarrier.getName())){
            return AjaxResult.error("供应商名称已存在");
        }

        //生成uuid
        UUID uuid = UUID.randomUUID();
        pCarrier.setId(uuid+"");

        // 后台操作人
        LoginUser userInfo = tokenService.getLoginUser();
        pCarrier.setCreateBy(userInfo.getUsername());
        return toAjax(pCarrierService.insertPCarrier(pCarrier));
    }

    /**
     * 修改合作方管理-供应商
     */
    @PreAuthorize(hasPermi = "pfserverperson:carrier:edit")
    @Log(title = "合作方管理-供应商", businessType = BusinessType.UPDATE)
    @PostMapping("/F09CarriersEditSave")
    public AjaxResult edit(@RequestBody PCarrier pCarrier)
    {
        // 校验运营商名称不能为空
        if (StringUtils.isEmpty(pCarrier.getName())){
            return AjaxResult.error("供应商名称不能为空");
        }

        // 校验修改的供应商名称是否重复
        if (!pCarrierService.checkUpdatePCarrierName(pCarrier.getName(),pCarrier.getId())){
            return AjaxResult.error("供应商名称已存在");
        }
        LoginUser userInfo = tokenService.getLoginUser();
        pCarrier.setUpdateBy(userInfo.getUsername());
        return toAjax(pCarrierService.updatePCarrier(pCarrier));
    }

    /**
     * 删除合作方管理-供应商
     */
    @PreAuthorize(hasPermi = "pfserverperson:carrier:delete")
    @Log(title = "合作方管理-供应商", businessType = BusinessType.DELETE)
	@GetMapping("/F09CarriersDelete/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(pCarrierService.deletePCarrierByIds(ids));
    }
}
