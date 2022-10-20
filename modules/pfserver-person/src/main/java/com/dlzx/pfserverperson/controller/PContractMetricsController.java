package com.dlzx.pfserverperson.controller;

import java.util.List;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.exception.CustomException;
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
import com.dlzx.pfserverperson.domain.PContractMetrics;
import com.dlzx.pfserverperson.service.IPContractMetricsService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 合作方管理-合同评价指标Controller
 * 
 * @author dlzx
 * @date 2020-12-09
 */
@RestController
@RequestMapping("/metrics")
public class PContractMetricsController extends BaseController
{
    @Autowired
    private IPContractMetricsService pContractMetricsService;


    @Autowired
    private TokenService tokenService;

    /**
     * 查询合作方管理-合同评价指标列表
     */
    @PreAuthorize(hasPermi = "pfserverperson:metrics:list")
    @PostMapping("/F11ContractMetricsList")
    public TableDataInfo list(@RequestBody PContractMetrics pContractMetrics)
    {
        if (StringUtils.isNull(pContractMetrics.getTypeId())){
            throw new CustomException("请选择评价指标类型");
        }
        startPage(pContractMetrics.getPageNum(),pContractMetrics.getPageSize());
        List<PContractMetrics> list = pContractMetricsService.selectPContractMetricsList(pContractMetrics);
        return getDataTable(list);
    }

    /**
     * 获取合作方管理-合同评价指标详细信息
     */
    @PreAuthorize(hasPermi = "pfserverperson:metrics:info")
    @GetMapping(value = "/F11ContractMetricsInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(pContractMetricsService.selectPContractMetricsById(id));
    }

    /**
     * 新增合作方管理-合同评价指标
     */
    @PreAuthorize(hasPermi = "pfserverperson:metrics:add")
    @Log(title = "合作方管理-合同评价指标", businessType = BusinessType.INSERT)
    @PostMapping("/F11ContractMetricsAdd")
    public AjaxResult add(@RequestBody PContractMetrics pContractMetrics)
    {
        if (StringUtils.isNull(pContractMetrics.getTypeId())){
            return AjaxResult.error("请选择评价指标类型");
        }
        if (StringUtils.isNull(pContractMetrics.getType())){
            return AjaxResult.error("评价部分不能为空");
        }
        //生成uuid
        UUID uuid = UUID.randomUUID();
        pContractMetrics.setId(uuid+"");

        LoginUser userInfo = tokenService.getLoginUser();
        pContractMetrics.setCreateBy(userInfo.getUsername());
        return toAjax(pContractMetricsService.insertPContractMetrics(pContractMetrics));
    }

    /**
     * 修改合作方管理-合同评价指标
     */
    @PreAuthorize(hasPermi = "pfserverperson:metrics:edit")
    @Log(title = "合作方管理-合同评价指标", businessType = BusinessType.UPDATE)
    @PostMapping("/F11ContractMetricsEditSave")
    public AjaxResult edit(@RequestBody PContractMetrics pContractMetrics)
    {
        if (StringUtils.isNull(pContractMetrics.getTypeId())){
            return AjaxResult.error("请选择评价指标类型");
        }
        if (StringUtils.isNull(pContractMetrics.getType())){
            return AjaxResult.error("评价部分不能为空");
        }
        LoginUser userInfo = tokenService.getLoginUser();
        pContractMetrics.setUpdateBy(userInfo.getUsername());
        return toAjax(pContractMetricsService.updatePContractMetrics(pContractMetrics));
    }

    /**
     * 删除合作方管理-合同评价指标
     */
    @PreAuthorize(hasPermi = "sypfserverpersonstem:metrics:delete")
    @Log(title = "合作方管理-合同评价指标", businessType = BusinessType.DELETE)
	@GetMapping("/F11ContractMetricsDelete/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(pContractMetricsService.deletePContractMetricsByIds(ids));
    }


    /**
     * 导出合作方管理-合同评价指标列表
     */
    @PreAuthorize(hasPermi = "pfserverperson:metrics:export")
    @Log(title = "合作方管理-合同评价指标", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PContractMetrics pContractMetrics) throws IOException
    {
        List<PContractMetrics> list = pContractMetricsService.selectPContractMetricsList(pContractMetrics);
        ExcelUtil<PContractMetrics> util = new ExcelUtil<PContractMetrics>(PContractMetrics.class);
        util.exportExcel(response, list, "metrics");
    }

}
