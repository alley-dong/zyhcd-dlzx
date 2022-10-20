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
import com.dlzx.pfservercar.domain.RFuelCost;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.BeanUtils;
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
import com.dlzx.pfservercar.domain.FFuelRecharge;
import com.dlzx.pfservercar.service.IFFuelRechargeService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 燃料管理-充电记录Controller
 * 
 * @author dlzx
 * @date 2020-12-17
 */
@RestController
@RequestMapping("/fuelRecharge")
public class FFuelRechargeController extends BaseController
{
    @Autowired
    private IFFuelRechargeService fFuelRechargeService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询燃料管理-充电记录列表
     */
    @PreAuthorize(hasPermi = "pfservercar:recharge:list")
    @PostMapping("/I11FuelRechargeList")
    public TableDataInfo list(@RequestBody FFuelRecharge fFuelRecharge)
    {
        startPage(fFuelRecharge.getPageNum(),fFuelRecharge.getPageSize());
        List<FFuelRecharge> list = fFuelRechargeService.selectFFuelRechargeList(fFuelRecharge);
        // 默认应统计所有记录，按条件查询时，应统计符合条件的所有记录
        FFuelRecharge data = new FFuelRecharge();
        BeanUtils.copyProperties(fFuelRecharge,data);
        data.setPageNum(null);
        data.setPageSize(null);
        List<FFuelRecharge> listTotal = fFuelRechargeService.selectFFuelRechargeList(data);

        // 充电电量
        BigDecimal rechargeElectricityTotal = BigDecimal.ZERO;
        // 充电金额
        BigDecimal rechargeAmountTotal = BigDecimal.ZERO;
        //
        BigDecimal rechargeDurationTotal = BigDecimal.ZERO;
        for (FFuelRecharge fuelRecharge : listTotal) {
            rechargeElectricityTotal = rechargeElectricityTotal.add(fuelRecharge.getRechargeElectricity());
            rechargeDurationTotal = rechargeDurationTotal.add(new BigDecimal(fuelRecharge.getRechargeDuration()));
            rechargeAmountTotal = rechargeAmountTotal.add(fuelRecharge.getRechargeAmount());
        }
        // 定义返回集合
        List<Map<String,Object>> mapList = new ArrayList<>();

        // 定义返回集合 对象
        Map<String,Object> map = new HashMap<>();
        map.put("rechargeElectricityTotal",rechargeElectricityTotal);
        map.put("rechargeAmountTotal",rechargeAmountTotal);
        map.put("rechargeDurationTotal",rechargeDurationTotal);
        // 充电车辆
        map.put("rechargeVehicle",listTotal.stream().collect(Collectors.groupingBy(FFuelRecharge::getLicensePlate)).size());
        // 充电次数
        map.put("rechargeNumber",listTotal.size());
        map.put("list",list);
        mapList.add(map);
        return getDataTableMap(mapList,list);
    }

    /**
     * 燃料管理-导入充电记录
     */
    @PreAuthorize(hasPermi = "pfservercar:recharge:import")
    @Log(title = "导入充电记录", businessType = BusinessType.IMPORT)
    @PostMapping("/I11FuelRechargeImport")
    public AjaxResult importData(@Validated MultipartFile file) throws Exception {
        ExcelUtil<FFuelRecharge> util = new ExcelUtil<FFuelRecharge>(FFuelRecharge.class);
        List<FFuelRecharge> fFuelRecharges = new ArrayList<>();
        try {
            fFuelRecharges = util.importExcel(file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return AjaxResult.error("数据格式有误");
        }
        LoginUser loginUser = tokenService.getLoginUser();
        return fFuelRechargeService.importFuelRecharge(fFuelRecharges,loginUser.getUsername());
    }

    /**
     * 燃料管理-导出充电记录
     */
    @PreAuthorize(hasPermi = "pfservercar:recharge:export")
    @Log(title = "导出充电记录", businessType = BusinessType.EXPORT)
    @PostMapping("/I11FuelRechargeExport")
    public void export(HttpServletResponse response, FFuelRecharge fFuelRecharge) throws IOException
    {
        List<FFuelRecharge> list = fFuelRechargeService.selectFFuelRechargeList(fFuelRecharge);
        ExcelUtil<FFuelRecharge> util = new ExcelUtil<FFuelRecharge>(FFuelRecharge.class);
        util.exportExcel(response, list, "recharge");
    }








    /**
     * 获取燃料管理-充电记录详细信息
     */
    @PreAuthorize(hasPermi = "system:recharge:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(fFuelRechargeService.selectFFuelRechargeById(id));
    }

    /**
     * 新增燃料管理-充电记录
     */
    @PreAuthorize(hasPermi = "system:recharge:add")
    @Log(title = "燃料管理-充电记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FFuelRecharge fFuelRecharge)
    {
        return toAjax(fFuelRechargeService.insertFFuelRecharge(fFuelRecharge));
    }

    /**
     * 修改燃料管理-充电记录
     */
    @PreAuthorize(hasPermi = "system:recharge:edit")
    @Log(title = "燃料管理-充电记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FFuelRecharge fFuelRecharge)
    {
        return toAjax(fFuelRechargeService.updateFFuelRecharge(fFuelRecharge));
    }

    /**
     * 删除燃料管理-充电记录
     */
    @PreAuthorize(hasPermi = "system:recharge:remove")
    @Log(title = "燃料管理-充电记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(fFuelRechargeService.deleteFFuelRechargeByIds(ids));
    }
}
