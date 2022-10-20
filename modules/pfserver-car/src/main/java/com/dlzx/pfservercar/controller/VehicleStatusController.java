package com.dlzx.pfservercar.controller;

import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.utils.poi.ExcelUtilh;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.domain.CInsuranceVehicle;
import com.dlzx.pfservercar.domain.CInsuranceVehicleGoogds;
import com.dlzx.pfservercar.domain.CInsuranceVehicleItem;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.Common.SysDictionary;
import com.dlzx.pfservercar.service.ICInsuranceVehicleGoogdsService;
import com.dlzx.pfservercar.service.ICInsuranceVehicleItemService;
import com.dlzx.pfservercar.service.ICInsuranceVehicleService;
import com.dlzx.pfservercar.service.ICVehicleInspectRecordService;
import com.dlzx.pfservercar.service.ICVehicleService;
import com.dlzx.pfservercar.service.impl.Commonlmpl.SysDictionaryServiceImpl;
import com.dlzx.system.api.model.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * 车辆状态Controller
 *
 * @author yangluhe
 * @date 2020-11-24
 */
@Api("车辆状态管理")
@RestController
@RequestMapping("/car/vehicleStatus")
public class VehicleStatusController extends BaseController
{
    @Autowired
    private ICVehicleService cVehicleService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询车辆状态列表
     */
    @ApiOperation("车辆状态列表")
    @PreAuthorize(hasPermi = "pfservercar:vehiclestatus:list")
    @PostMapping(value = "/D07List")
    public TableDataInfo D07List(@RequestBody CVehicle cVehicle)
    {
        startPage(cVehicle.getPageNum(),cVehicle.getPageSize());
        List<CVehicle> list = cVehicleService.selectCVehicleStatusList(cVehicle);
        return getDataTable(list);
    }

    /**
     * 查看数据
     */
    @ApiOperation("编辑状态")
    @PreAuthorize(hasPermi = "pfservercar:vehiclestatus:edit")
    @GetMapping(value = "/D07Edit/{id}")
    public AjaxResult D07Edit(@PathVariable("id")String id){
        CVehicle cVehicle = cVehicleService.selectCVehicleById(id);
        return AjaxResult.success(cVehicle);
    }

    /**
     * 编辑状态保存
     */
    @ApiOperation("编辑保存状态")
    @PreAuthorize(hasPermi = "pfservercar:vehiclestatus:edit")
    @PostMapping("/D07EditSave")
    public AjaxResult D07EditSave(@RequestBody CVehicle cVehicle){
        CVehicle cVehicleOne = cVehicleService.selectCVehicleById(cVehicle.getId());
        //获取当前用户信息
        LoginUser userInfo = tokenService.getLoginUser();
        cVehicleOne.setUpdateBy(userInfo.getUsername());
        cVehicleOne.setVehicleStatus(cVehicle.getVehicleStatus());
        return toAjax(cVehicleService.updateCVehicle(cVehicleOne));
    }
}
