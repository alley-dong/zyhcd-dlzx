package com.dlzx.pfservercar.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.pfservercar.domain.EtcCardUsageRecordImport;
import com.dlzx.pfservercar.domain.REtcCostBiao;
import io.swagger.annotations.ApiOperation;
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
import com.dlzx.pfservercar.domain.REtcCost;
import com.dlzx.pfservercar.service.IREtcCostService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 统计报-ETC费用统计Controller
 * 
 * @author dlzx
 * @date 2020-12-17
 */
@RestController
@RequestMapping("/car/etcCost")
public class REtcCostController extends BaseController
{
    @Autowired
    private IREtcCostService rEtcCostService;

    /**
     * 查询统计报-ETC费用统计列表
     */
    @PreAuthorize(hasPermi = "car:etcCost:list")
    @PostMapping("/M07List")
    public TableDataInfo list(@RequestBody  REtcCost rEtcCost)
    {
        startPage(rEtcCost.getPageNum(),rEtcCost.getPageSize());
        List<REtcCost> list = rEtcCostService.selectREtcCostList(rEtcCost);

        // 计算统计
        REtcCost data = new REtcCost();
        data.setLicensePlate(rEtcCost.getLicensePlate());
        data.setbTime(rEtcCost.getbTime());
        data.seteTime(rEtcCost.geteTime());
        List<REtcCost> rEtcCosts = rEtcCostService.selectREtcCostList(data);
        BigDecimal TaxSum = rEtcCosts.stream().map(aa -> aa.getTaxAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal AmoSum = rEtcCosts.stream().map(aa -> aa.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);


        // 定义返回集合
        List<Map<String,Object>> mapList = new ArrayList<>();

        // 定义返回集合 对象
        Map<String,Object> map = new HashMap<>();
        map.put("amountAll",AmoSum);
        map.put("taxAmountAll",TaxSum);
        map.put("list",list);
        mapList.add(map);
        return getDataTableMap(mapList,list);
    }

    /**
     * 导出统计报-ETC费用统计列表
     */
    @PreAuthorize(hasPermi = "car:etcCost:export")
    @Log(title = "统计报-ETC费用统计", businessType = BusinessType.EXPORT)
    @PostMapping("/M07Export")
    public void export(HttpServletResponse response,REtcCost rEtcCost) throws IOException
    {
        List<REtcCost> list = rEtcCostService.selectREtcCostList(rEtcCost);
        ExcelUtil<REtcCost> util = new ExcelUtil<REtcCost>(REtcCost.class);
        util.exportExcel(response, list, "etcCost");
    }


    /**
     * 导入列表
     */
    @ApiOperation("导入列表")
    @PreAuthorize(hasPermi = "car:etcCost:import")
    @Log(title = "统计报-ETC费用统计", businessType = BusinessType.IMPORT)
    @PostMapping("/M07ImportData")
    public AjaxResult importData(@Validated MultipartFile file) throws Exception {
        ExcelUtil<REtcCost> util = new ExcelUtil<REtcCost>(REtcCost.class);
        List<REtcCost> recordList = new ArrayList<>();
        try {
            recordList = util.importExcel(file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return AjaxResult.error("无文件导入");
        }

        Map<String, String> message = rEtcCostService.importUser(recordList);
        String code = message.get("code");
        if ("0".equals(code)) {
            return AjaxResult.success(message.get("msg"));
        } else {
            return AjaxResult.error(message.get("msg"));
        }
    }

    /**
     * 获取统计报-ETC费用统计详细信息
     */
    @PreAuthorize(hasPermi = "system:cost:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(rEtcCostService.selectREtcCostById(id));
    }

    /**
     * 新增统计报-ETC费用统计
     */
    @PreAuthorize(hasPermi = "system:cost:add")
    @Log(title = "统计报-ETC费用统计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody REtcCost rEtcCost)
    {
        return toAjax(rEtcCostService.insertREtcCost(rEtcCost));
    }

    /**
     * 修改统计报-ETC费用统计
     */
    @PreAuthorize(hasPermi = "system:cost:edit")
    @Log(title = "统计报-ETC费用统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody REtcCost rEtcCost)
    {
        return toAjax(rEtcCostService.updateREtcCost(rEtcCost));
    }

    /**
     * 删除统计报-ETC费用统计
     */
    @PreAuthorize(hasPermi = "system:cost:remove")
    @Log(title = "统计报-ETC费用统计", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(rEtcCostService.deleteREtcCostByIds(ids));
    }
}
