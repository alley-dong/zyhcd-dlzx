package com.dlzx.pfservercar.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.service.IRPurchasesCostService;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dlzx.pfservercar.domain.RPurchasesCost;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 统计报-新购车辆临时号牌费用统计Controller
 * 
 * @author ruoyi
 * @date 2021-02-21
 */
@RestController
@RequestMapping("/purchasesCost")
public class RPurchasesCostController extends BaseController
{

    @Autowired
    private IRPurchasesCostService rPurchasesCostService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询统计报-新购车辆临时号牌费用统计列表
     */
    @PreAuthorize(hasPermi = "pfservercar:purchasesCost:list")
    @PostMapping("/M13PurchasesCostList")
    public TableDataInfo list(@RequestBody RPurchasesCost rPurchasesCost)
    {
        startPage(rPurchasesCost.getPageNum(),rPurchasesCost.getPageSize());
        List<RPurchasesCost> list = rPurchasesCostService.selectRPurchasesCostList(rPurchasesCost);

        // 计算统计
        RPurchasesCost data = new RPurchasesCost();
        data.setLicensePlate(rPurchasesCost.getLicensePlate());
        data.setBeginTime(rPurchasesCost.getBeginTime());
        data.setEndTime(rPurchasesCost.getEndTime());
        List<RPurchasesCost> datas = rPurchasesCostService.selectRPurchasesCostList(data);
        BigDecimal amountTotal = BigDecimal.ZERO;
        for (RPurchasesCost purchasesCost : datas) {
            amountTotal = amountTotal.add(purchasesCost.getTaxAmount());
        }
        // 定义返回集合
        List<Map<String,Object>> mapList = new ArrayList<>();

        // 定义返回集合 对象
        Map<String,Object> map = new HashMap<>();
        map.put("amountTotal",amountTotal);
        map.put("list",list);
        mapList.add(map);
        return getDataTableMap(mapList,list);
    }


    /**
     * 导入统计报-新购车辆临时号牌费用统计列表
     */
    @PreAuthorize(hasPermi = "pfservercar:purchasesCost:import")
    @Log(title = "导入统计报-新购车辆临时号牌费用统计", businessType = BusinessType.IMPORT)
    @PostMapping("/M13PurchasesCostImport")
    public AjaxResult importData(@Validated MultipartFile file) throws Exception {
        ExcelUtil<RPurchasesCost> util = new ExcelUtil<RPurchasesCost>(RPurchasesCost.class);
        List<RPurchasesCost> rPurchasesCosts = new ArrayList<>();
        try {
            rPurchasesCosts = util.importExcel(file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return AjaxResult.error("数据格式有误");
        }
        LoginUser loginUser = tokenService.getLoginUser();
        return rPurchasesCostService.importPurchasesCost(rPurchasesCosts.stream().filter(f->!"".equals(f.getLicensePlate())).collect(Collectors.toList()), loginUser.getUsername());
    }

    /**
     * 导出统计报-新购车辆临时号牌费用统计列表
     */
    @PreAuthorize(hasPermi = "pfservercar:purchasesCost:export")
    @Log(title = "统计报-新购车辆临时号牌费用统计", businessType = BusinessType.EXPORT)
    @PostMapping("/M13PurchasesCostExport")
    @ResponseBody
    public void export(HttpServletResponse response,RPurchasesCost rPurchasesCost) throws IOException {
        List<RPurchasesCost> list = rPurchasesCostService.selectRPurchasesCostList(rPurchasesCost);
        ExcelUtil<RPurchasesCost> util = new ExcelUtil<RPurchasesCost>(RPurchasesCost.class);
        util.exportExcel(response, list, "purchasesCost");
    }


}
