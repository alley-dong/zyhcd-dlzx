package com.dlzx.pfserverperson.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.utils.poi.ExportExcel;
import com.dlzx.common.core.utils.domain.PContractEvaluatExcel;
import com.dlzx.common.core.utils.domain.PContractEvaluateMetricsExcel;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverperson.domain.PContractEvaluateMetrics;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfserverperson.domain.PContractEvaluate;
import com.dlzx.pfserverperson.service.IPContractEvaluateService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 合作方管理-合同评价记录Controller
 *
 * @author dlzx
 * @date 2020-12-09
 */
@RestController
@RequestMapping("/evaluate")
public class PContractEvaluateController extends BaseController {
    @Autowired
    private IPContractEvaluateService pContractEvaluateService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询合作方管理-合同评价记录列表
     */
    @PreAuthorize(hasPermi = "pfserverperson:evaluate:list")
    @PostMapping("/F12ContractEvaluateList")
    public TableDataInfo list(@RequestBody PContractEvaluate pContractEvaluate) {
        startPage(pContractEvaluate.getPageNum(), pContractEvaluate.getPageSize());
        List<PContractEvaluate> list = pContractEvaluateService.selectPContractEvaluateList(pContractEvaluate);
        return getDataTable(list);
    }

    /**
     * 获取合作方管理-合同评价记录详细信息
     */
    @PreAuthorize(hasPermi = "pfserverperson:evaluate:info")
    @PostMapping(value = "/F13ContractEvaluateInfo")
    public AjaxResult getInfo(@RequestBody PContractEvaluate pContractEvaluate) {
        return AjaxResult.success(pContractEvaluateService.selectPContractEvaluateById(pContractEvaluate));
    }

    /**
     * 新增合作方管理-合同评价记录
     */
    @PreAuthorize(hasPermi = "pfserverperson:evaluate:add")
    @Log(title = "合作方管理-合同评价记录", businessType = BusinessType.INSERT)
    @PostMapping("/F12ContractEvaluateAdd")
    public AjaxResult add(@RequestBody PContractEvaluate pContractEvaluate) {
        if (StringUtils.isNull(pContractEvaluate.getEvaluateMonth())) {
            return AjaxResult.error("评价月份不能为空");
        }
        if (StringUtils.isNull(pContractEvaluate.getCarrierId()) && StringUtils.isNull(pContractEvaluate.getCarrierName())) {
            return AjaxResult.error("供应商名称不能为空");
        }
        if (StringUtils.isNull(pContractEvaluate.getTypeId()) && StringUtils.isNull(pContractEvaluate.getTypeName())) {
            return AjaxResult.error("评价指标类型不能为空");
        }
        //生成uuid
        UUID uuid = UUID.randomUUID();
        pContractEvaluate.setId(uuid + "");

        LoginUser userInfo = tokenService.getLoginUser();
        pContractEvaluate.setCreateBy(userInfo.getUsername());

        return pContractEvaluateService.insertPContractEvaluate(pContractEvaluate);
    }

    /**
     * 删除合作方管理-合同评价记录
     */
    @PreAuthorize(hasPermi = "pfserverperson:evaluate:delete")
    @Log(title = "合作方管理-合同评价记录", businessType = BusinessType.DELETE)
    @GetMapping("/F12ContractEvaluateDelete/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(pContractEvaluateService.deletePContractEvaluateByIds(ids));
    }

    /**
     * 修改合作方管理-合同评价记录
     */
    @PreAuthorize(hasPermi = "pfserverperson:evaluate:edit")
    @Log(title = "合作方管理-合同评价记录", businessType = BusinessType.UPDATE)
    @PostMapping("/F12ContractEvaluateEditSave")
    public AjaxResult edit(@RequestBody PContractEvaluate pContractEvaluate) {
        LoginUser userInfo = tokenService.getLoginUser();
        pContractEvaluate.setUpdateBy(userInfo.getUsername());
        return toAjax(pContractEvaluateService.updatePContractEvaluate(pContractEvaluate));
    }

    /**
     * 导出合作方管理-合同评价记录列表
     */
//    @PreAuthorize(hasPermi = "pfserverperson:evaluate:export")
    @Log(title = "合作方管理-合同评价记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public void export(HttpServletResponse response, PContractEvaluate pContractEvaluate) throws IOException {

        if (StringUtils.isNull(pContractEvaluate.getYearMoneyType())){
            throw new CustomException("请选择年度预算");
        }
        // 查询要导出的信息，放入新的实体，在 common服务用
        PContractEvaluate pContractEvaluates = pContractEvaluateService.selectPContractEvaluateById(pContractEvaluate);
        PContractEvaluatExcel pContractEvaluatExcel = new PContractEvaluatExcel();
        BeanUtils.copyProperties(pContractEvaluates,pContractEvaluatExcel);

        // 预算类型
        pContractEvaluatExcel.setYearMoneyType(pContractEvaluate.getYearMoneyType());
        List<PContractEvaluateMetricsExcel>pContractEvaluateMetricsExcels = new ArrayList<>();
        if (pContractEvaluates.getpContractEvaluateMetricsList() != null && pContractEvaluates.getpContractEvaluateMetricsList().size()>0){
            for (PContractEvaluateMetrics pContractEvaluateMetrics : pContractEvaluates.getpContractEvaluateMetricsList()) {
                PContractEvaluateMetricsExcel pContractEvaluateMetricsExcel = new PContractEvaluateMetricsExcel();
                BeanUtils.copyProperties(pContractEvaluateMetrics,pContractEvaluateMetricsExcel);
                pContractEvaluateMetricsExcels.add(pContractEvaluateMetricsExcel);
            }
            pContractEvaluatExcel.setpContractEvaluateMetricsList(pContractEvaluateMetricsExcels);

            ExportExcel<PContractEvaluatExcel> util = new ExportExcel<>();
            util.getValue(response,pContractEvaluatExcel);
        }else {
            throw new CustomException("当前指标类型未添加评价指标");
        }
    }
}
