package com.dlzx.pfserverperson.controller;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfserverperson.domain.*;
import com.dlzx.pfserverperson.domain.commonDomain.DriverBox;
import com.dlzx.pfserverperson.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ylh
 * @program:dlzx
 * @description：
 * @create:2020-11-18 20-38
 */
@Api("下拉选")
@RestController
@RequestMapping("/select/common")
public class SelectController {

    @Autowired
    private IDDriverTypeService driverTypeService;

    @Autowired
    private ISysDictionaryService sysDictionaryService;

    @Autowired
    private ITRouteService tRouteService;

    @Autowired
    private IDPerformanceService dPerformanceService;

    @Autowired
    private IDDriverService dDriverService;
   /* @Autowired
    private ISysDictionaryService sysDictionaryService;*/

    /**
     * 驾驶员类型下拉框
     */

    @ApiOperation("驾驶员类型下拉框")
    @PostMapping("/A10DriverBox")
    public AjaxResult A10DriverBox(DDriverType dDriverType) {
        List<DDriverType> dDriverTypeList = driverTypeService.selectDDriverTypeList(dDriverType);
        List<DriverBox> typeList = new ArrayList<>();
        for (DDriverType dDriverType1 : dDriverTypeList) {
            DriverBox driverBox = new DriverBox();
            driverBox.setPerformanceType(dDriverType1.getType());
            driverBox.setId(dDriverType1.getId());
            driverBox.setName(dDriverType1.getName());
            typeList.add(driverBox);
        }
        return AjaxResult.success(typeList);
    }

    /* @ApiOperation("车辆类型")
    @GetMapping("/D02DriverTypeBox/{id}")
    public AjaxResult D02DriverTypeBox(@PathVariable("id")String id){
        List<SysDictionary> sysDictionaryList =sysDictionaryService.selectSysDictionaryDriverType(id);
        return AjaxResult.success(sysDictionaryList);
    }*/
    @ApiOperation("车辆类型下拉框")
    @PostMapping("/G02CarList")
    public AjaxResult CarList() {

        List<SysDictionary> list = sysDictionaryService.selectSysDictionaryListCar();
        return AjaxResult.success(list);
    }

    @ApiOperation("主车车辆类型下拉框")
    @PostMapping("/G02ZhuCarList")
    public AjaxResult zhuCarList() {

        List<SysDictionary> list = sysDictionaryService.selectSysDictionaryListCarZhu();
        return AjaxResult.success(list);
    }

    @ApiOperation("挂车车辆类型下拉框")
    @PostMapping("/G02GuaCarList")
    public AjaxResult guaCarList() {

        List<SysDictionary> list = sysDictionaryService.selectSysDictionaryListCarGua();
        return AjaxResult.success(list);
    }
//    @ApiOperation("长途选择路线下拉框")
//    @PostMapping("/G02ChangRoutList")
//    public AjaxResult ChangRoutList(TRoute tRoute)
//    {
//
//        List<TRoute> list = tRouteService.selectTRouteListChang(tRoute);
//        return AjaxResult.success(list);
//    }

    @ApiOperation("路线下拉框")
    @PostMapping("/J04RoutList")
    public AjaxResult RoutList() {
        TRoute tRoute =new TRoute();
        List<TRoute> list = tRouteService.selectTRouteListQuan(tRoute);
        return AjaxResult.success(list);
    }

    @ApiOperation("通过的驾驶员下拉框")
    @PostMapping("/L06DriverList")
    public AjaxResult DriverList() {

        List<DDriver> list = dDriverService.selectDDriverListName();
        return AjaxResult.success(list);
    }

    @ApiOperation("长途绩效下拉框")
    @PostMapping("/G08List")
    public AjaxResult listJiXiao() {
        DPerformance dPerformance=new DPerformance();
        List<DPerformance> list = dPerformanceService.selectDPerformanceListJiXiao(dPerformance);
        return AjaxResult.success(list);
    }


}
