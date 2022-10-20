package com.dlzx.pfservercar.controller;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.utils.poi.ExcelUtilh;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.minio.service.MinioService;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.RDepreciationCost;
import com.dlzx.pfservercar.domain.RFuelCost;
import com.dlzx.pfservercar.service.RDepreciationCostService;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import com.dlzx.common.minio.service.MinioService;
import java.util.*;

import static com.github.pagehelper.page.PageMethod.startPage;

/**
 * 统计报-折旧费用统计Controller
 *
 * @author dlzx
 * @date 2020-12-16
 */
@RestController
@RequestMapping("/depreciationCost")
public class RDepreciationCostController extends BaseController {

     @Autowired
    RDepreciationCostService rDepreciationCostService;

    @Autowired
    private TokenService tokenService;

    @Autowired
     private  MinioService minioService;
    /**
     * 查询统计报-折旧费用统计列表
     */
    @PreAuthorize(hasPermi = "depreciation:cost:list")
    @PostMapping("/M09list")
    @ResponseBody
    public TableDataInfo list(@RequestBody RDepreciationCost rDepreciationCost) {
        startPage(rDepreciationCost.getPageNum(), rDepreciationCost.getPageSize());
        List<RDepreciationCost> list = rDepreciationCostService.selectRDepreciationCostList(rDepreciationCost);

        // 计算统计
        RDepreciationCost data = new RDepreciationCost();
        data.setLicensePlates(rDepreciationCost.getLicensePlates());
        data.setBeginDepreciationMonth(rDepreciationCost.getBeginDepreciationMonth());
        data.setEndDepreciationMonth(rDepreciationCost.getEndDepreciationMonth());
        BigDecimal bigDecimal = rDepreciationCostService.selectRDepreciationCostMoney(data);

        // 定义返回集合
        List<Map<String,Object>> mapList = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("bigDecimal", bigDecimal);
        map.put("list",list);
        mapList.add(map);
        return getDataTableMap(mapList,list);
    }

    /**
     * 获取折旧费用车辆下拉列表
     */
    // @PreAuthorize(hasPermi = "system:cost:pull")
    @Log(title = "获取折旧费用车辆下拉列表")
    @PostMapping("/M09DepreciationCostPullDown")
    public TableDataInfo export()
    {
        List<String> depreciationList = rDepreciationCostService.queryLicensePlatePullDown();
        return getDataTable(depreciationList);
    }

    /**
     * 导出统计报-折旧费用统计导出
     */
        @PreAuthorize(hasPermi = "depreciation:cost:export")
    @Log(title = "统计报-折旧费用统计", businessType = BusinessType.EXPORT)
    @PostMapping("/M09export")
    public void export(HttpServletResponse response, RDepreciationCost rDepreciationCost) throws IOException
    {
        List<RDepreciationCost> list = rDepreciationCostService.selectRDepreciationCostList(rDepreciationCost);
        ExcelUtil<RDepreciationCost> util = new ExcelUtil<RDepreciationCost>(RDepreciationCost.class);
        util.exportExcel(response, list, "cost");
    }

    /**
     * 导入统计报-折旧费用统计列表
     */

    @PreAuthorize(hasPermi = "depreciation:cost:import")
    @Log(title = "导入统计报-折旧费用统计列表", businessType = BusinessType.IMPORT)
    @PostMapping("/M09CostImport")
    public AjaxResult importData(@Validated MultipartFile file) throws Exception {
        ExcelUtil<RDepreciationCost> util = new ExcelUtil<RDepreciationCost>(RDepreciationCost.class);
        List<RDepreciationCost> rDepreciationCosts  = util.importExcel(file.getInputStream());
        try {
            for(RDepreciationCost item:rDepreciationCosts) {
                if (StringUtils.isEmpty(item.getLicensePlate())) {
                    return AjaxResult.error("车牌号不能为空");
                }
                if (StringUtils.isNull(item.getDepreciationMonth())) {
                    return AjaxResult.error("车辆折旧月份不能为空");
                }
                if (minioService.isLegalDate(item.getDepreciationMonth()) == false) {
                    return AjaxResult.error("车辆折旧月份不规范");
                }
                if (StringUtils.isNull(item.getDepreciationCost())) {
                    return AjaxResult.error("车辆折旧费用不能为空");
                }
                if (minioService.checkPrice(item.getDepreciationCost()) == false) {
                    return AjaxResult.error("车辆折旧费用不规范");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return AjaxResult.error("数据格式有误");
        }
        LoginUser loginUser = tokenService.getLoginUser();
        return rDepreciationCostService.importFuelCost(rDepreciationCosts,loginUser.getUsername());
    }






}
