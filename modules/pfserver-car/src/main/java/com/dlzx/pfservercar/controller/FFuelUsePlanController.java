package com.dlzx.pfservercar.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.domain.Common.Spanrow;
import com.dlzx.pfservercar.domain.FFuelQuota;
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
import com.dlzx.pfservercar.domain.FFuelUsePlan;
import com.dlzx.pfservercar.service.IFFuelUsePlanService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 燃料管理-燃油使用计划Controller
 * 
 * @author dlzx
 * @date 2020-12-02
 */
@RestController
@RequestMapping("/fuelPlan")
public class FFuelUsePlanController extends BaseController
{
    @Autowired
    private IFFuelUsePlanService fFuelUsePlanService;

    @Autowired
    private TokenService tokenService;

    /**
     * 燃油使用计划-燃油使用计划列表
     */
    @PreAuthorize(hasPermi = "pfservercar:plan:list")
    @PostMapping("/I07FuelOilPlanList")
    public TableDataInfo list(@RequestBody FFuelUsePlan fFuelUsePlan)
    {
        startPage(fFuelUsePlan.getPageNum(),fFuelUsePlan.getPageSize());
        List<FFuelUsePlan> list = fFuelUsePlanService.selectFFuelUsePlanList(fFuelUsePlan);
        return getDataTable(list);
    }

    /**
     * 燃油使用计划-生成燃油使用计划
     */
    @PreAuthorize(hasPermi = "pfservercar:plan:add")
    @Log(title = "燃料管理-生成燃油使用计划", businessType = BusinessType.INSERT)
    @PostMapping("/I07FuelOilPlanAdd")
    public AjaxResult add(@RequestBody FFuelUsePlan fFuelUsePlan)
    {
        if(StringUtils.isNull(fFuelUsePlan.getPlanDate())){
            return AjaxResult.error("请选择日期");
        }
        List<FFuelUsePlan> list = fFuelUsePlanService.queryCarFFuelUsePlanList(fFuelUsePlan);
        if (list.size()>0){
            return AjaxResult.error("当前选中的日期已生成计划");
        }
        LoginUser userInfo = tokenService.getLoginUser();
        fFuelUsePlan.setCreateBy(userInfo.getUsername());
        return toAjax(fFuelUsePlanService.insertFFuelUsePlan(fFuelUsePlan));
    }


    /**
     * 燃油使用计划列表下-车辆列表
     */
    @PreAuthorize(hasPermi = "pfservercar:plan:carList")
    @PostMapping("/I08CarFuelOilPlanList")
    public TableDataInfo i08CarFuelOilPlanList(@RequestBody FFuelUsePlan fFuelUsePlan)
    {
        startPage(fFuelUsePlan.getPageNum(),fFuelUsePlan.getPageSize());
        List<FFuelUsePlan> list = fFuelUsePlanService.queryCarFFuelUsePlanList(fFuelUsePlan);
       /* Map<String,Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            FFuelUsePlan data  = new FFuelUsePlan();
            data.setLicensePlate(list.get(i).getLicensePlate());
            data.setPlanDate(list.get(i).getPlanDate());
            List<FFuelUsePlan> fFuelUsePlans = fFuelUsePlanService.fuelUsePlanList(data);

            if (fFuelUsePlans.size()>1){
                Integer integer = map.get(list.get(i).getLicensePlate());
                if (integer == null){
                    map.put(list.get(i).getLicensePlate(),i);
                }
                List<Spanrow> spanrows = new ArrayList<>();
                for (int j = 0; j < 6; j++) {
                    Spanrow spanrow = new Spanrow();
                    spanrow.setRowspan(j);
                    spanrow.setLinespan(fFuelUsePlans.size()==1?null:fFuelUsePlans.size());
                    spanrow.setRowNum(map.get(list.get(i).getLicensePlate()));
                    spanrows.add(spanrow);
                }
                list.get(i).setSpanrow(spanrows);
            }
        }*/
       BigDecimal fuelPriceTotal =  BigDecimal.ZERO;
       BigDecimal ballastQuotaPriceTotal =  BigDecimal.ZERO;
       BigDecimal pourQuotaPriceTotal =  BigDecimal.ZERO;
        for (FFuelUsePlan fuelUsePlan : list) {
            // 合并的数据
            FFuelUsePlan data  = new FFuelUsePlan();
            data.setLicensePlate(fuelUsePlan.getLicensePlate());
            data.setPlanDate(fuelUsePlan.getPlanDate());
            fuelUsePlan.setInfo(fFuelUsePlanService.fuelUsePlanList(data));
            // 计算金额
            for (FFuelUsePlan usePlan : fuelUsePlan.getInfo()) {
                // 油料
                if (StringUtils.isNotNull(usePlan.getFuelPrice())){
                    fuelPriceTotal = fuelPriceTotal.add(usePlan.getFuelPrice());
                }
                // 空放
                if (StringUtils.isNotNull(usePlan.getBallastQuotaPrice())){
                    ballastQuotaPriceTotal = ballastQuotaPriceTotal.add(usePlan.getBallastQuotaPrice());
                }
                // 倒货
                if (StringUtils.isNotNull(usePlan.getPourQuotaPrice())){
                    pourQuotaPriceTotal = pourQuotaPriceTotal.add(usePlan.getPourQuotaPrice());
                }
            }
            fuelUsePlan.setFuelTypeEnum(null);
        }
        return getDataTable(list,fuelPriceTotal,ballastQuotaPriceTotal,pourQuotaPriceTotal);
    }

    /**
     * 燃油使用计划列表下-车辆详情信息
     */
    @PreAuthorize(hasPermi = "pfservercar:plan:info")
    @GetMapping(value = "/I08CarFuelOilPlanInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(fFuelUsePlanService.selectFFuelUsePlanById(id));
    }

    /**
     * 燃油使用计划列表下-车辆详情信息
     */
    @PostMapping(value = "/I08CarFuelOilPlanByLicensePlateInfo")
    public AjaxResult I08CarFuelOilPlanBylicensePlateInfo(@RequestBody FFuelUsePlan fFuelUsePlan)
    {
        FFuelUsePlan fuelUsePlan = fFuelUsePlanService.queryCarFFuelUsePlanList(fFuelUsePlan).stream().findFirst().orElse(null);
        if (fuelUsePlan!=null){
            // 合并的数据
            fuelUsePlan.setInfo(fFuelUsePlanService.fuelUsePlanList(fFuelUsePlan));
        }
        return AjaxResult.success(fuelUsePlan);
    }

    /**
     * 燃油使用计划列表下-删除车辆详情信息
     */
    @PostMapping(value = "/I08CarFuelOilPlanByLicensePlateDelete")
    public AjaxResult I08CarFuelOilPlanBylicensePlateDelete(@RequestBody FFuelUsePlan fFuelUsePlan)
    {
        return AjaxResult.success(fFuelUsePlanService.deleteFFuelUsePlanBylicensePlate(fFuelUsePlan));
    }

    /**
     * 燃油使用计划列表下-修改车辆详情信息
     */
    @PostMapping(value = "/I08CarFuelOilPlanByLicensePlateUpdate")
    public AjaxResult I08CarFuelOilPlanByLicensePlateUpdate(@RequestBody FFuelUsePlan fFuelUsePlan)
    {
        return AjaxResult.success(fFuelUsePlanService.updateInfo(fFuelUsePlan));
    }

    /**
     * 燃油使用计划列表下-根据车型和线路获取定额
     */
//    @PreAuthorize(hasPermi = "pfservercar:plan:money")
    @GetMapping("/I09CarFuelOilPlanMoney")
    public AjaxResult i09CarFuelOilPlanMoney(FFuelUsePlan fFuelUsePlan)
    {
        if (StringUtils.isEmpty(fFuelUsePlan.getRouteId())){
            return AjaxResult.error("线路不能为空");
        }
        if (StringUtils.isEmpty(fFuelUsePlan.getVehicleModel())){
            return AjaxResult.error("车型不能为空");
        }
//        if (StringUtils.isNull(fFuelUsePlan.getFuelType())){
//            return AjaxResult.error("油料类型不能为空");
//        }
        List<FFuelQuota> list = fFuelUsePlanService.queryPlanMoney(fFuelUsePlan);
        if (list.size()> 0){
            return AjaxResult.success(list);
        }else {
            return AjaxResult.error("当前线路和车型未设置定额");
        }
    }

    /**
     * 燃油使用计划列表下-车辆编辑
     */
//    @PreAuthorize(hasPermi = "pfservercar:plan:edit")
    @Log(title = "燃料管理-燃油使用计划", businessType = BusinessType.UPDATE)
    @PostMapping("/I08CarFuelOilPlanEditSave")
    public AjaxResult edit(@RequestBody FFuelUsePlan fFuelUsePlan)
    {

        if (StringUtils.isEmpty(fFuelUsePlan.getRouteId())&& StringUtils.isEmpty(fFuelUsePlan.getRouteName())){
            return AjaxResult.error("线路不能为空");
        }
        if (StringUtils.isNull(fFuelUsePlan.getFuelQuota())){
            return AjaxResult.error("定额不能为空");
        }
        if (StringUtils.isNull(fFuelUsePlan.getPlanQuantity())){
            return AjaxResult.error("计划趟次不能为空");
        }
        if (StringUtils.isNull(fFuelUsePlan.getUnitPrice())){
            return AjaxResult.error("油料单价不能为空");
        }
        if (StringUtils.isNull(fFuelUsePlan.getFuelPrice())){
            return AjaxResult.error("油料金额不能为空");
        }
        return toAjax(fFuelUsePlanService.updateFFuelUsePlan(fFuelUsePlan));
    }

    /**
     * 燃油使用计划列表下-车辆删除
     */
    @PreAuthorize(hasPermi = "pfservercar:plan:delete")
    @Log(title = "燃料管理-燃油使用计划", businessType = BusinessType.DELETE)
	@GetMapping("/I08CarFuelOilPlanDelete/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(fFuelUsePlanService.deleteFFuelUsePlanByIds(ids));
    }



    /**
     * 导出燃料管理-燃油使用计划列表
     */
    @PreAuthorize(hasPermi = "pfservercar:plan:export")
    @Log(title = "燃料管理-燃油使用计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FFuelUsePlan fFuelUsePlan) throws IOException
    {
        List<FFuelUsePlan> list = fFuelUsePlanService.selectFFuelUsePlanList(fFuelUsePlan);
        ExcelUtil<FFuelUsePlan> util = new ExcelUtil<FFuelUsePlan>(FFuelUsePlan.class);
        util.exportExcel(response, list, "plan");
    }

}
