package com.dlzx.pfserverperson.controller;

import java.util.List;
import java.io.IOException;
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
import com.dlzx.pfserverperson.domain.PContractEvaluateMetrics;
import com.dlzx.pfserverperson.service.IPContractEvaluateMetricsService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 合作方管理-合同评价记录-指标Controller
 * 
 * @author dlzx
 * @date 2020-12-09
 */
@RestController
@RequestMapping("/evaluateMetrics")
public class PContractEvaluateMetricsController extends BaseController
{
    @Autowired
    private IPContractEvaluateMetricsService pContractEvaluateMetricsService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询合作方管理-合同评价记录-指标列表
     */
    @PreAuthorize(hasPermi = "pfserverperson:metrics:list")
    @PostMapping("/F13ContractEvaluateMetricsList")
    public TableDataInfo list(@RequestBody PContractEvaluateMetrics pContractEvaluateMetrics)
    {
        startPage(pContractEvaluateMetrics.getPageNum(),pContractEvaluateMetrics.getPageSize());
        List<PContractEvaluateMetrics> list = pContractEvaluateMetricsService.selectPContractEvaluateMetricsList(pContractEvaluateMetrics);
        return getDataTable(list);
    }

    /**
     * 获取合作方管理-合同评价记录-指标详细信息
     */
//    @PreAuthorize(hasPermi = "pfserverperson:metrics:info")
    @GetMapping(value = "/F14ContractEvaluateMetricsInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(pContractEvaluateMetricsService.selectPContractEvaluateMetricsById(id));
    }

    /**
     * 修改合作方管理-合同评价记录-指标
     */
//    @PreAuthorize(hasPermi = "pfserverperson:metrics:edit")
    @Log(title = "合作方管理-合同评价记录-指标", businessType = BusinessType.UPDATE)
    @PostMapping("/F13ContractEvaluateMetricsEditSave")
    public AjaxResult edit(@RequestBody PContractEvaluateMetrics pContractEvaluateMetrics)
    {
        if (StringUtils.isNull(pContractEvaluateMetrics.getScore())){
            return AjaxResult.error("请输入得分");
        }
        LoginUser userInfo = tokenService.getLoginUser();
        pContractEvaluateMetrics.setUpdateBy(userInfo.getUsername());
        return toAjax(pContractEvaluateMetricsService.updatePContractEvaluateMetrics(pContractEvaluateMetrics));
    }





    /**
     * 新增合作方管理-合同评价记录-指标
     */
    @PreAuthorize(hasPermi = "pfserverperson:metrics:add")
    @Log(title = "合作方管理-合同评价记录-指标", businessType = BusinessType.INSERT)
    @PostMapping("/F13ContractEvaluateMetricsAdd")
    public AjaxResult add(@RequestBody PContractEvaluateMetrics pContractEvaluateMetrics)
    {
        return toAjax(pContractEvaluateMetricsService.insertPContractEvaluateMetrics(pContractEvaluateMetrics));
    }

    /**
     * 删除合作方管理-合同评价记录-指标
     */
    @PreAuthorize(hasPermi = "pfserverperson:metrics:delete")
    @Log(title = "合作方管理-合同评价记录-指标", businessType = BusinessType.DELETE)
	@GetMapping("/delete/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(pContractEvaluateMetricsService.deletePContractEvaluateMetricsByIds(ids));
    }

    /**
     * 导出合作方管理-合同评价记录-指标列表
     */
    @PreAuthorize(hasPermi = "pfserverperson:metrics:export")
    @Log(title = "合作方管理-合同评价记录-指标", businessType = BusinessType.EXPORT)
    @PostMapping("/F14ContractEvaluateMetricsExport")
    public void export(HttpServletResponse response, PContractEvaluateMetrics pContractEvaluateMetrics) throws IOException
    {
        List<PContractEvaluateMetrics> list = pContractEvaluateMetricsService.selectPContractEvaluateMetricsList(pContractEvaluateMetrics);
        ExcelUtil<PContractEvaluateMetrics> util = new ExcelUtil<PContractEvaluateMetrics>(PContractEvaluateMetrics.class);
        util.exportExcel(response, list, "metrics");
    }
}
