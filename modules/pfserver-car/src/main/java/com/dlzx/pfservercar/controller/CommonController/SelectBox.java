package com.dlzx.pfservercar.controller.CommonController;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfservercar.domain.*;
import com.dlzx.pfservercar.domain.Common.RGarageCarBox;
import com.dlzx.pfservercar.domain.Common.SysDictionary;
import com.dlzx.pfservercar.service.*;
import com.dlzx.pfservercar.service.CommonService.ISysDictionaryService;
import com.dlzx.pfservercar.service.impl.RGarageRepairItemServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ylh
 * @program:dlzx
 * @description：公用下来选以及联动下拉框
 * @create:2020-11-26 15-06
 */
@RestController
@RequestMapping("/commonSelectBox")
public class SelectBox {

    @Autowired
    private ISysDictionaryService sysDictionaryService;

    @Autowired
    private ICVehicleService cVehicleService;

    @Autowired
    private IRGarageCarService rGarageCarService;

    @Autowired
    private IRGarageRepairItemService garageRepairItemService;

    @Autowired
    private ISafeAccidentService safeAccidentService;

    @Autowired
    private ICInsuranceClaimsService cInsuranceClaimsService;

    @GetMapping(value = "/D02CvhicleSelectBox/{id}")
    public AjaxResult D02CvhicleSelectBox(@PathVariable("id") String id) {
        List<SysDictionary> sysDictionaryList = sysDictionaryService.selectSysDictionaryDriverType(id);
        return AjaxResult.success(sysDictionaryList);
    }

    @ApiOperation("车牌号下拉框")
    @PostMapping("/J02CarNumList")
    public AjaxResult CarNumList() {
        CVehicle cVehicle = new CVehicle();
        List<CVehicle> list = cVehicleService.selectCVehicleListCarNum(cVehicle);
        List<CVehicleH> list1=new ArrayList<>();
        for (CVehicle cVehicle1:list){
            CVehicleH cVehicle2=new CVehicleH();
            cVehicle2.setId(cVehicle1.getId());
            cVehicle2.setLicensePlate(cVehicle1.getLicensePlate());
            cVehicle2.setFrameNumber(cVehicle1.getFrameNumber());
            list1.add(cVehicle2);
        }

        return AjaxResult.success(list1);
    }


    @ApiOperation("主车车牌号下拉框")
    @PostMapping("/N02CarNumList")
    public AjaxResult ZhuCarNumList() {
        CVehicle cVehicle = new CVehicle();
        List<CVehicle> list = cVehicleService.selectCVehicleListCarZhuNum(cVehicle);
        List<CVehicleH> list1=new ArrayList<>();
        for (CVehicle cVehicle1:list){
            CVehicleH cVehicle2=new CVehicleH();
            cVehicle2.setId(cVehicle1.getId());
            cVehicle2.setLicensePlate(cVehicle1.getLicensePlate());
            list1.add(cVehicle2);
        }
        return AjaxResult.success(list1);
    }

    @ApiOperation("维修保养单审核一级维修服务站下拉框")
    @Log(title = "维修服务站下拉框",businessType = BusinessType.OTHER)
    @PostMapping("/H02RGarage")
    public AjaxResult H02RGarage(){
        List<RGarageCar> rGarageCarList = rGarageCarService.selectRGarageCarSelectBox();
        List<RGarageCarBox> rGarageCarBoxList = new ArrayList<>();
        for (RGarageCar rGarageCar:rGarageCarList){
            RGarageCarBox rGarageCarBox = new RGarageCarBox();
            rGarageCarBox.setId(rGarageCar.getId());
            rGarageCarBox.setName(rGarageCar.getName());
            rGarageCarBoxList.add(rGarageCarBox);
        }
        return AjaxResult.success(rGarageCarBoxList);
    }


    @ApiOperation("维修保养单审核一级维修服务站下拉框（定点）")
    @Log(title = "维修服务站下拉框",businessType = BusinessType.OTHER)
    @PostMapping("/H02RGarageList")
    public AjaxResult H02RGarageList(){
        List<RGarageCar> rGarageCarList = rGarageCarService.selectRGarageCarDownPull();
        List<RGarageCarBox> rGarageCarBoxList = new ArrayList<>();
        for (RGarageCar rGarageCar:rGarageCarList){
            RGarageCarBox rGarageCarBox = new RGarageCarBox();
            rGarageCarBox.setId(rGarageCar.getId());
            rGarageCarBox.setName(rGarageCar.getName());
            rGarageCarBoxList.add(rGarageCarBox);
        }
        return AjaxResult.success(rGarageCarBoxList);
    }


    @ApiOperation("维修订单审批（未提交）明细表一新增名称下拉框")
    @Log(title = "维修名称下拉框",businessType = BusinessType.OTHER)
    @PostMapping("/H10AddBox")
    public AjaxResult H10AddBox(@RequestBody RGarageCarBox rGarageCarBox) {
        List<RGarageRepairItem> rGarageRepairItemList = garageRepairItemService.selectRGarageRepairItemByGarageId(rGarageCarBox.getId(),rGarageCarBox.getName());
        List<RGarageCarBox> rGarageCarBoxList = new ArrayList<>();
        for (RGarageRepairItem rGarageRepairItem:rGarageRepairItemList){
            RGarageCarBox rGarageCarBoxOne = new RGarageCarBox();
            rGarageCarBoxOne.setId(rGarageRepairItem.getId());
            rGarageCarBoxOne.setName(rGarageRepairItem.getName());
            rGarageCarBoxList.add(rGarageCarBoxOne);
        }
        return AjaxResult.success(rGarageCarBoxList);
    }

    @ApiOperation("事故车牌号下拉框")
    @PostMapping("/E18ShiGuCarNumList")
    public AjaxResult shiGuList() {
        SafeAccident safeAccident =new SafeAccident();
        List<SafeAccident> list1= safeAccidentService.selectSafeAccidentListVehName(safeAccident);
        return AjaxResult.success(list1);
    }

    @ApiOperation("事故时间下拉框")
    @PostMapping("/E18ShiGuTimeList")
    public AjaxResult shiJianList(@RequestBody SafeAccident safeAccident) {
        List<SafeAccident> list1= safeAccidentService.selectSafeAccidentListVehNameTu(safeAccident);
        CInsuranceClaims cInsuranceClaims=new CInsuranceClaims();
        cInsuranceClaims.setVehicleName(safeAccident.getVehicleName());
        List<CInsuranceClaims> list = cInsuranceClaimsService.selectCInsuranceClaimsList(cInsuranceClaims);
        List<CInsuranceClaims> list2=new ArrayList<>();
        for (int i=0;i<list1.size();i++){
            SafeAccident pro=list1.get(i);
            Date accidentTime =pro.getAccidentTime();
            String vehicleName =pro.getVehicleName();
            List<CInsuranceClaims> listAccident = list.stream().filter(b -> Objects.equals(accidentTime, b.getAccidentTime()) && Objects.equals(vehicleName, b.getVehicleName())).collect(Collectors.toList());

            if (listAccident.size()==0){
                CInsuranceClaims cInsuranceClaims1=new CInsuranceClaims();
                cInsuranceClaims1.setAccidentTime(accidentTime);
                list2.add(cInsuranceClaims1);
            }
        }
        return AjaxResult.success(list2);
    }

    /**
     * 根据维修站id查找车型名称
     * @param id
     * @return
     */
    @GetMapping("/H10VehicleModelByGarageId/{id}")
    public AjaxResult H10VehicleModelByGarageId(@PathVariable("id")String id){
        return AjaxResult.success(garageRepairItemService.selectVehicleModelByGarage(id));
    }

    @PostMapping("/H16SelectBox")
    public AjaxResult H16SelectBox(){
        return AjaxResult.success(sysDictionaryService.selectAll());
    }

    @PostMapping("/queryRepairSelectBox")
    public AjaxResult queryRepairSelectBox(){
        return AjaxResult.success(sysDictionaryService.selectRepairAll());
    }


    @ApiOperation("车牌号下拉框（可根据车牌号模糊查询）")
    @PostMapping("/J02LikeCarNumList")
    public AjaxResult J02LikeCarNumList(@RequestBody CVehicle cVehicle) {
        List<CVehicle> list = cVehicleService.selectCVehicleListCarNum(cVehicle);
        List<CVehicleH> list1=new ArrayList<>();
        for (CVehicle cVehicle1:list){
            CVehicleH cVehicle2=new CVehicleH();
            cVehicle2.setId(cVehicle1.getId());
            cVehicle2.setLicensePlate(cVehicle1.getLicensePlate());
            cVehicle2.setFrameNumber(cVehicle1.getFrameNumber());
            list1.add(cVehicle2);
        }

        return AjaxResult.success(list1);
    }
}
