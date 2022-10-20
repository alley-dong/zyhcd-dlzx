package com.dlzx.pfservercar.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.pfservercar.domain.REtcCost;
import com.dlzx.pfservercar.domain.RInformationSystemServiceBiao;
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
import com.dlzx.pfservercar.domain.RInformationSystemServiceCost;
import com.dlzx.pfservercar.service.IRInformationSystemServiceCostService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 统计报-信息系统服务费统计Controller
 * 
 * @author dlzx
 * @date 2020-12-22
 */
@RestController
@RequestMapping("/car/informationSystemServiceCost")
public class RInformationSystemServiceCostController extends BaseController
{
    @Autowired
    private IRInformationSystemServiceCostService rInformationSystemServiceCostService;

    /**
     * 查询统计报-信息系统服务费统计列表
     */
    @PreAuthorize(hasPermi = "car:informationSystemServiceCost:list")
    @PostMapping("/M12List")
    public TableDataInfo list(@RequestBody RInformationSystemServiceCost rInformationSystemServiceCost)
    {
        startPage(rInformationSystemServiceCost.getPageNum(),rInformationSystemServiceCost.getPageSize());
        List<RInformationSystemServiceCost> list = rInformationSystemServiceCostService.selectRInformationSystemServiceCostList(rInformationSystemServiceCost);

        // 计算统计
        RInformationSystemServiceCost data = new RInformationSystemServiceCost();
        data.setLicensePlate(rInformationSystemServiceCost.getLicensePlate());
        data.setSystemName(rInformationSystemServiceCost.getSystemName());
        data.setStartTime(rInformationSystemServiceCost.getStartTime());
        data.setCloseTime(rInformationSystemServiceCost.getCloseTime());
        List<RInformationSystemServiceCost> datas = rInformationSystemServiceCostService.selectRInformationSystemServiceCostList(data);
        BigDecimal AmoSum = datas.stream().map(aa -> aa.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);

        // 定义返回集合
        List<Map<String,Object>> mapList = new ArrayList<>();

        // 定义返回集合 对象
        Map<String,Object> map = new HashMap<>();
        map.put("amountAll",AmoSum);
        map.put("list",list);
        mapList.add(map);
        return getDataTableMap(mapList,list);
    }

    /**
     * 导出统计报-信息系统服务费统计列表
     */
    @PreAuthorize(hasPermi = "car:informationSystemServiceCost:export")
    @Log(title = "统计报-信息系统服务费统计", businessType = BusinessType.EXPORT)
    @PostMapping("/M12Export")
    public void export(HttpServletResponse response,RInformationSystemServiceCost rInformationSystemServiceCost) throws IOException
    {
        List<RInformationSystemServiceCost> list = rInformationSystemServiceCostService.selectRInformationSystemServiceCostList(rInformationSystemServiceCost);
        ExcelUtil<RInformationSystemServiceCost> util = new ExcelUtil<RInformationSystemServiceCost>(RInformationSystemServiceCost.class);
        util.exportExcel(response, list, "cost");
    }


    /**
     * 导入列表
     */
    @ApiOperation("导入列表")
    @PreAuthorize(hasPermi = "car:informationSystemServiceCost:import")
    @Log(title = "统计报-信息系统服务费统计", businessType = BusinessType.IMPORT)
    @PostMapping("/M12ImportData")
    public AjaxResult importData(@Validated MultipartFile file) throws Exception {
        ExcelUtil<RInformationSystemServiceCost> util = new ExcelUtil<RInformationSystemServiceCost>(RInformationSystemServiceCost.class);
        List<RInformationSystemServiceCost> recordList = new ArrayList<>();
        try {
            recordList = util.importExcel(file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return AjaxResult.error("无文件导入");
        }

        Map<String, String> message = rInformationSystemServiceCostService.importUser(recordList);
        String code = message.get("code");
        if ("0".equals(code)) {
            return AjaxResult.success(message.get("msg"));
        } else {
            return AjaxResult.error(message.get("msg"));
        }
    }
    /**
     * 获取统计报-信息系统服务费统计详细信息
     */
    @PreAuthorize(hasPermi = "system:cost:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(rInformationSystemServiceCostService.selectRInformationSystemServiceCostById(id));
    }

    /**
     * 新增统计报-信息系统服务费统计
     */
    @PreAuthorize(hasPermi = "system:cost:add")
    @Log(title = "统计报-信息系统服务费统计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RInformationSystemServiceCost rInformationSystemServiceCost)
    {
        return toAjax(rInformationSystemServiceCostService.insertRInformationSystemServiceCost(rInformationSystemServiceCost));
    }

    /**
     * 修改统计报-信息系统服务费统计
     */
    @PreAuthorize(hasPermi = "system:cost:edit")
    @Log(title = "统计报-信息系统服务费统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RInformationSystemServiceCost rInformationSystemServiceCost)
    {
        return toAjax(rInformationSystemServiceCostService.updateRInformationSystemServiceCost(rInformationSystemServiceCost));
    }

    /**
     * 删除统计报-信息系统服务费统计
     */
    @PreAuthorize(hasPermi = "system:cost:remove")
    @Log(title = "统计报-信息系统服务费统计", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(rInformationSystemServiceCostService.deleteRInformationSystemServiceCostByIds(ids));
    }
}
