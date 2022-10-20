package com.dlzx.pfservercar.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.system.api.model.LoginUser;
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
import com.dlzx.pfservercar.domain.RFuelCost;
import com.dlzx.pfservercar.service.IRFuelCostService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 统计报-燃料费用统计Controller
 * 
 * @author dlzx
 * @date 2020-12-15
 */
@RestController
@RequestMapping("/fuelCost")
public class RFuelCostController extends BaseController
{
    @Autowired
    private IRFuelCostService rFuelCostService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询统计报-燃料费用统计列表
     */
    @PreAuthorize(hasPermi = "pfservercar:cost:list")
    @RequestMapping("/M06FuelCostList")
    public TableDataInfo list(@RequestBody RFuelCost rFuelCost)
    {
        startPage(rFuelCost.getPageNum(),rFuelCost.getPageSize());
        List<RFuelCost> list = rFuelCostService.selectRFuelCostList(rFuelCost);
        BigDecimal taxAmountTotal = BigDecimal.ZERO;
        BigDecimal amountTotal = BigDecimal.ZERO;

        // 计算统计
        RFuelCost data = new RFuelCost();
        data.setLicensePlate(rFuelCost.getLicensePlate());
        data.setOils(rFuelCost.getOils());
        data.setStartTime(rFuelCost.getStartTime());
        data.setOverTime(rFuelCost.getOverTime());
        List<RFuelCost> rFuelCosts = rFuelCostService.selectRFuelCostList(data);
        for (RFuelCost fuelCost : rFuelCosts) {
            taxAmountTotal = taxAmountTotal.add(fuelCost.getTaxAmount());
            amountTotal = amountTotal.add(fuelCost.getAmount());
        }
        // 定义返回集合
        List<Map<String,Object>> mapList = new ArrayList<>();

        // 定义返回集合 对象
        Map<String,Object> map = new HashMap<>();
        map.put("taxAmountTotal",taxAmountTotal);
        map.put("amountTotal",amountTotal);
        map.put("list",list);
        mapList.add(map);
        return getDataTableMap(mapList,list);
    }

    /**
     * 获取燃料油品下拉列表
     */
//    @PreAuthorize(hasPermi = "pfservercar:cost:pull")
    @Log(title = "获取燃料油品下拉列表")
    @PostMapping("/M06FuelCostPullDown")
    public TableDataInfo export()
    {
        List<String> oilList = rFuelCostService.queryOilPullDown();
        return getDataTable(oilList);
    }

    /**
     * 导入统计报-燃料费用统计列表
     */
    @PreAuthorize(hasPermi = "pfservercar:cost:import")
    @Log(title = "导入统计报-燃料费用统计", businessType = BusinessType.IMPORT)
    @PostMapping("/M06FuelCostImport")
    public AjaxResult importData(@Validated MultipartFile file) throws Exception {
        ExcelUtil<RFuelCost> util = new ExcelUtil<RFuelCost>(RFuelCost.class);
        List<RFuelCost> rFuelCosts = new ArrayList<>();
        try {
            rFuelCosts = util.importExcel(file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return AjaxResult.error("数据格式有误");
        }
        LoginUser loginUser = tokenService.getLoginUser();
        return rFuelCostService.importFuelCost(rFuelCosts.stream().filter(f->!"".equals(f.getLicensePlate())).collect(Collectors.toList()), loginUser.getUsername());
    }

    /**
     * 导出统计报-燃料费用统计列表
     */
    @PreAuthorize(hasPermi = "pfservercar:cost:export")
    @Log(title = "统计报-燃料费用统计", businessType = BusinessType.EXPORT)
    @PostMapping("/M06FuelCostExport")
    public void export(HttpServletResponse response, RFuelCost rFuelCost) throws IOException
    {
        List<RFuelCost> list = rFuelCostService.selectRFuelCostList(rFuelCost);
        ExcelUtil<RFuelCost> util = new ExcelUtil<RFuelCost>(RFuelCost.class);
        util.exportExcel(response, list, "cost");
    }








    /**
     * 获取统计报-燃料费用统计详细信息
     */
    @PreAuthorize(hasPermi = "pfservercar:cost:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(rFuelCostService.selectRFuelCostById(id));
    }

    /**
     * 新增统计报-燃料费用统计
     */
    @PreAuthorize(hasPermi = "pfservercar:cost:add")
    @Log(title = "统计报-燃料费用统计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RFuelCost rFuelCost)
    {
        return toAjax(rFuelCostService.insertRFuelCost(rFuelCost));
    }

    /**
     * 修改统计报-燃料费用统计
     */
    @PreAuthorize(hasPermi = "pfservercar:cost:edit")
    @Log(title = "统计报-燃料费用统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RFuelCost rFuelCost)
    {
        return toAjax(rFuelCostService.updateRFuelCost(rFuelCost));
    }

    /**
     * 删除统计报-燃料费用统计
     */
    @PreAuthorize(hasPermi = "pfservercar:cost:remove")
    @Log(title = "统计报-燃料费用统计", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(rFuelCostService.deleteRFuelCostByIds(ids));
    }
}
