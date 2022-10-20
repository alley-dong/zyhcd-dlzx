package com.dlzx.pfservercar.controller;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.FOilCard;
import com.dlzx.pfservercar.service.ICVehicleService;
import com.dlzx.system.api.model.LoginUser;
import org.bouncycastle.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 应用模块名称
 * Copyright: Copyright (C) 2020 DLANGEL, Inc. All rights reserved.
 * @author  dcl
 * @since 2020-12-17 10:07
 */

@RestController
@RequestMapping("/fuelRechargeCard")
public class FFuelRechargeCardController extends BaseController {

    @Autowired
    private ICVehicleService icVehicleService;

    @Autowired
    private TokenService tokenService;

    /**
     * 燃料管理-充电卡管理列表
     */
    @PreAuthorize(hasPermi = "pfservercar:fuelRechargeCard:list")
    @PostMapping("/I10FuelRechargeCardList")
    public TableDataInfo list(@RequestBody CVehicle cVehicle)
    {
        startPage(cVehicle.getPageNum(),cVehicle.getPageSize());
        List<CVehicle> cVehicles = icVehicleService.selectCVehicleRechargeList(cVehicle);
        return getDataTable(cVehicles);
    }

    /**
     * 燃料管理-充电卡详情
     */
    @PreAuthorize(hasPermi = "pfservercar:fuelRechargeCard:info")
    @Log(title = "新增充电卡", businessType = BusinessType.INSERT)
    @PostMapping("/I10FuelRechargeCardInfo")
    public AjaxResult info(@RequestBody CVehicle cVehicle)
    {
        if (StringUtils.isNull(cVehicle.getLicensePlate())){
            return AjaxResult.error("车牌卡号不能为空");
        }
        CVehicle dto = icVehicleService.queryCVehicleByLicensePlate(cVehicle.getLicensePlate());
        if (dto != null){
            return AjaxResult.success(dto);
        }else {
            return AjaxResult.error("车辆不存在");
        }
    }

    /**
     * 燃料管理-新增/修改充电卡
     */
    @PreAuthorize(hasPermi = "pfservercar:fuelRechargeCard:add")
    @Log(title = "新增充电卡", businessType = BusinessType.INSERT)
    @PostMapping("/I10FuelRechargeCardAdd")
    public AjaxResult add(@RequestBody CVehicle cVehicle)
    {
        if (StringUtils.isNull(cVehicle.getLicensePlate())){
            return AjaxResult.error("车牌卡号不能为空");
        }
        if (StringUtils.isNull(cVehicle.getCardNumber())){
            return AjaxResult.error("卡号/流水号不能为空");
        }
        List<CVehicle> cVehicles = icVehicleService.queryCardNumber(cVehicle.getCardNumber());
        List<CVehicle> collect = cVehicles.stream().filter(l -> !l.getId().equals(cVehicle.getId())).collect(Collectors.toList());
        if (collect.size()>0){
            return AjaxResult.error("卡号/流水号已绑定");
        }

        CVehicle dto = icVehicleService.queryCVehicleByLicensePlate(cVehicle.getLicensePlate());
        if (dto != null){
            LoginUser userInfo = tokenService.getLoginUser();
            cVehicle.setUpdateBy(userInfo.getUsername());
            cVehicle.setCardNumber(cVehicle.getCardNumber());
            return toAjax(icVehicleService.updateCardCVehicle(cVehicle));
        }else {
            return AjaxResult.error("车辆不存在");
        }
    }

    /**
     * 燃料管理-删除充电卡
     */
    @PreAuthorize(hasPermi = "pfservercar:fuelRechargeCard:delete")
    @Log(title = "删除充电卡", businessType = BusinessType.DELETE)
    @PostMapping("/I10FuelRechargeCardDelete")
    public AjaxResult remove(@RequestBody CVehicle cVehicle)
    {
        CVehicle dto = icVehicleService.queryCVehicleByLicensePlate(cVehicle.getLicensePlate());
        if (dto != null){
            LoginUser userInfo = tokenService.getLoginUser();
            cVehicle.setUpdateBy(userInfo.getUsername());
            cVehicle.setCardNumber("");
            return toAjax(icVehicleService.updateCVehicle(cVehicle));
        }else {
            return AjaxResult.error("车辆不存在");
        }
    }

    /**
     * 燃料管理-充电卡导入
     */
    @PreAuthorize(hasPermi = "pfservercar:fuelRechargeCard:import")
    @Log(title = "充电卡导入", businessType = BusinessType.IMPORT)
    @PostMapping("/I10FuelRechargeCardImport")
    public AjaxResult importData(@Validated MultipartFile file) throws Exception {
        ExcelUtil<CVehicle> util = new ExcelUtil<CVehicle>(CVehicle.class);
        List<CVehicle> cVehicles = new ArrayList<>();
        try {
            cVehicles = util.importExcel(file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return AjaxResult.error("数据格式有误");
        }
        LoginUser loginUser = tokenService.getLoginUser();
        return icVehicleService.importFuelRechargeCard(cVehicles,loginUser.getUsername());
    }

}
