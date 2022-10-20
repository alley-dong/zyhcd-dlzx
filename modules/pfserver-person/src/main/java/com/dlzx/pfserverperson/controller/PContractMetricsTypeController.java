package com.dlzx.pfserverperson.controller;

import java.util.List;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.system.api.model.LoginUser;
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
import com.dlzx.pfserverperson.domain.PContractMetricsType;
import com.dlzx.pfserverperson.service.IPContractMetricsTypeService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 合作方管理-合同评价指标类型Controller
 * 
 * @author dlzx
 * @date 2020-12-09
 */
@RestController
@RequestMapping("/metricsType")
public class PContractMetricsTypeController extends BaseController
{
    @Autowired
    private IPContractMetricsTypeService pContractMetricsTypeService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询合作方管理-合同评价指标类型列表
     */
//    @PreAuthorize(hasPermi = "pfserverperson:type:list")
    @PostMapping("/F10ContractMetricsTypeList")
    public TableDataInfo list(@RequestBody PContractMetricsType pContractMetricsType)
    {
        startPage(pContractMetricsType.getPageNum(),pContractMetricsType.getPageSize());
        List<PContractMetricsType> list = pContractMetricsTypeService.selectPContractMetricsTypeList(pContractMetricsType);
        return getDataTable(list);
    }

    /**
     * 查询合作方管理-合同评价指标类型下拉
     */
//    @PreAuthorize(hasPermi = "pfserverperson:type:pull")
    @GetMapping("/F10ContractMetricsTypePullDown")
    public TableDataInfo pullDown()
    {
        List<PContractMetricsType> list = pContractMetricsTypeService.selectPContractMetricsTypeList(new PContractMetricsType());
        List<String> collect = list.stream().map(PContractMetricsType::getName).filter(distinctByKey(l->l)).collect(Collectors.toList());
        return getDataTable(collect);
    }

    static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

        /**
         * 获取合作方管理-合同评价指标类型详细信息
         */
    @PreAuthorize(hasPermi = "pfserverperson:type:edit")
    @GetMapping(value = "/F10ContractMetricsTypeInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(pContractMetricsTypeService.selectPContractMetricsTypeById(id));
    }

    /**
     * 新增合作方管理-合同评价指标类型
     */
    @PreAuthorize(hasPermi = "pfserverperson:type:add")
    @Log(title = "合作方管理-合同评价指标类型", businessType = BusinessType.INSERT)
    @PostMapping("/F10ContractMetricsTypeAdd")
    public AjaxResult add(@RequestBody PContractMetricsType pContractMetricsType)
    {
        if (StringUtils.isNull(pContractMetricsType.getName())){
            return AjaxResult.error("类型名称不能为空");
        }

        //生成uuid
        UUID uuid = UUID.randomUUID();
        pContractMetricsType.setId(uuid+"");

        LoginUser userInfo = tokenService.getLoginUser();
        pContractMetricsType.setCreateBy(userInfo.getUsername());
        return toAjax(pContractMetricsTypeService.insertPContractMetricsType(pContractMetricsType));
    }

    /**
     * 修改合作方管理-合同评价指标类型
     */
    @PreAuthorize(hasPermi = "pfserverperson:type:edit")
    @Log(title = "合作方管理-合同评价指标类型", businessType = BusinessType.UPDATE)
    @PostMapping("/F10ContractMetricsTypeEditSave")
    public AjaxResult edit(@RequestBody PContractMetricsType pContractMetricsType)
    {
        if (StringUtils.isNull(pContractMetricsType.getName())){
            return AjaxResult.error("类型名称不能为空");
        }
        LoginUser userInfo = tokenService.getLoginUser();
        pContractMetricsType.setUpdateBy(userInfo.getUsername());
        return toAjax(pContractMetricsTypeService.updatePContractMetricsType(pContractMetricsType));
    }

    /**
     * 删除合作方管理-合同评价指标类型
     */
    @PreAuthorize(hasPermi = "pfserverperson:type:delete")
    @Log(title = "合作方管理-合同评价指标类型", businessType = BusinessType.DELETE)
	@GetMapping("/F10ContractMetricsTypeDelete/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(pContractMetricsTypeService.deletePContractMetricsTypeByIds(ids));
    }

    /**
     * 导出合作方管理-合同评价指标类型列表
     */
    @PreAuthorize(hasPermi = "pfserverperson:type:export")
    @Log(title = "合作方管理-合同评价指标类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PContractMetricsType pContractMetricsType) throws IOException
    {
        List<PContractMetricsType> list = pContractMetricsTypeService.selectPContractMetricsTypeList(pContractMetricsType);
        ExcelUtil<PContractMetricsType> util = new ExcelUtil<PContractMetricsType>(PContractMetricsType.class);
        util.exportExcel(response, list, "type");
    }

}
