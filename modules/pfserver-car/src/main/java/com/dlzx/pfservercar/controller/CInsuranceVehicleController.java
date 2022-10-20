package com.dlzx.pfservercar.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.web.domain.SysExamineConfigParams;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.client.SystemClient;
import com.dlzx.pfservercar.domain.*;
import com.dlzx.pfservercar.mapper.SysDictDataMapper;
import com.dlzx.pfservercar.service.ICInsuranceTypeItemService;
import com.dlzx.pfservercar.service.ICInsuranceTypeService;
import com.dlzx.pfservercar.service.ICInsuranceVehicleGoogdsService;
import com.dlzx.pfservercar.service.ICInsuranceVehicleItemService;
import com.dlzx.pfservercar.service.ICVehicleService;
import com.dlzx.pfservercar.service.impl.CInsuranceVehicleGoogdsServiceImpl;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfservercar.service.ICInsuranceVehicleService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 车辆管理-车辆保险Controller
 * 
 * @author dlzx
 * @date 2020-11-24
 */
@RestController
@RequestMapping("/car/insuranceVehicle")
public class CInsuranceVehicleController extends BaseController
{
    @Autowired
    private ICInsuranceVehicleService cInsuranceVehicleService;
    @Autowired
    private ICInsuranceVehicleItemService icInsuranceVehicleItemService;
    @Autowired
    private ICInsuranceTypeItemService cInsuranceTypeItemService;
    @Autowired
    private ICInsuranceTypeService cInsuranceTypeService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ICInsuranceVehicleGoogdsService icInsuranceVehicleGoogdsService;
    @Autowired
    private SystemClient systemClient;
    @Autowired
    private ICVehicleService cVehicleService;

    /**
     * 根据保险ID取所有的保费
     */
    public BigDecimal insuranceCost(CInsuranceVehicleItem cInsuranceVehicleItem) {
        List<CInsuranceVehicleItem> cInsuranceVehicleItems = icInsuranceVehicleItemService.selectCInsuranceVehicleItemList(cInsuranceVehicleItem);
        BigDecimal total = BigDecimal.ZERO;
        for (CInsuranceVehicleItem cInsuranceVehicleItem1 : cInsuranceVehicleItems) {
            total=total.add(cInsuranceVehicleItem1.getInsuranceCost());
        }
        return total;
    }

    /**
     * 查询车辆管理-车辆保险列表  YLH
     */
    @PreAuthorize(hasPermi = "car:insuranceVehicle:list")
    @PostMapping(value = {"/E01List"})
    public TableDataInfo E01List(@RequestBody CInsuranceVehicle cInsuranceVehicle)
    {
        // 获取cInsuranceVehicle的id
//        String id = cInsuranceVehicle.getId();
//        CInsuranceVehicleItem cInsuranceVehicleItem = new CInsuranceVehicleItem();
//        cInsuranceVehicleItem.setInsuranceId(id);
//        if (cInsuranceVehicleItem.getInsuranceId() != null){
//            // 调用
//            BigDecimal bigDecimal = insuranceCost(cInsuranceVehicleItem);
//            cInsuranceVehicle.setTotalPremium(bigDecimal);
//            // 修改
//            cInsuranceVehicleService.updateCInsuranceVehicle(cInsuranceVehicle);
//        }
        startPage(cInsuranceVehicle.getPageNum(),cInsuranceVehicle.getPageSize());
        List<CInsuranceVehicle> list = cInsuranceVehicleService.selectCInsuranceVe(cInsuranceVehicle);
//        ArrayList<CInsuranceVehicle> collect = list.stream().collect(Collectors.collectingAndThen(
//                Collectors.toCollection(() -> new TreeSet<>(
//                        Comparator.comparing(
//                                CInsuranceVehicle::getId))), ArrayList::new));
        return getDataTable(list);
    }

    @PostMapping("/E02Judge")
    public AjaxResult E02Judge(@RequestBody EinJudge einJudge){
        CInsuranceVehicle cInsuranceVehicle = cInsuranceVehicleService.selectCInsuranceLastList(einJudge.getId());
        if (cInsuranceVehicle != null){
            List<CInsuranceVehicleItem> cInsuranceVehicleItems = cInsuranceVehicleService.selectCInsuranceItemByInsuranceId(cInsuranceVehicle.getId());
            return AjaxResult.success(cInsuranceVehicleItems);
        }
        else {
            return AjaxResult.success(null);
        }
    }

    /**
     * 新增下拉选E02Box  YLH
     * @return
     */
    @PostMapping("/E02Box")
    public AjaxResult E02Box(){
        List<CVehicle> cVehicleList = cVehicleService.selectCVehicleList(new CVehicle());
        return AjaxResult.success(cVehicleList);
    }

    /**
     * /E02InsuranceTypeList   YLH
     * @param cInsuranceTypeItem
     * @return
     */
    @PostMapping("/E02InsuranceTypeList")
    @PreAuthorize(hasPermi = "car:insuranceVehicle:addtypelist")
    public TableDataInfo E02InsuranceTypeList(@RequestBody CInsuranceTypeItem cInsuranceTypeItem){
        return getDataTable(cInsuranceTypeItemService.selectTypeList(cInsuranceTypeItem));
    }



    /**
     * 查询车辆管理-车辆保险列表 YLH
     */
    @PreAuthorize(hasPermi = "car:insuranceVehicle:gelist")
    @PostMapping("/E06List")
    public TableDataInfo E04List(@RequestBody CInsuranceVehicle cInsuranceVehicle)
    {
        startPage(cInsuranceVehicle.getPageNum(),cInsuranceVehicle.getPageSize());

        // 获取cInsuranceVehicle的id
//        String id = cInsuranceVehicle.getId();
//        CInsuranceVehicleItem cInsuranceVehicleItem = new CInsuranceVehicleItem();
//        cInsuranceVehicleItem.setInsuranceId(id);
//        if (cInsuranceVehicleItem.getInsuranceId() != null){
//            // 调用
//            BigDecimal bigDecimal = insuranceCost(cInsuranceVehicleItem);
//            cInsuranceVehicle.setTotalPremium(bigDecimal);
//            // 修改
//            cInsuranceVehicleService.updateCInsuranceVehicle(cInsuranceVehicle);
//        }

        List<CInsuranceVehicle> list = cInsuranceVehicleService.selectCInsuranceGe(cInsuranceVehicle);


//        ArrayList<CInsuranceVehicle> collect = list.stream().collect(Collectors.collectingAndThen(
//                Collectors.toCollection(() -> new TreeSet<>(
//                        Comparator.comparing(
//                                CInsuranceVehicle::getId))), ArrayList::new));
        return getDataTable(list);
    }

    /**
     * 查询车辆管理-车辆保险列表
     * E03 E04 E05
     */
    @PreAuthorize(hasPermi = "car:insuranceVehicle:list")
    @GetMapping (value = {"/E04GetInfo/{id}"})
    public TableDataInfo E04GetInfo(@PathVariable("id") String id)
    {
        CInsuranceVehicleItem cInsuranceVehicleItem = new CInsuranceVehicleItem();
        CInsuranceVehicle cInsuranceVehicle = new CInsuranceVehicle();
        cInsuranceVehicleItem.setInsuranceId(id);
        if (cInsuranceVehicleItem.getInsuranceId() != null){
            // 调用
            BigDecimal bigDecimal = insuranceCost(cInsuranceVehicleItem);
            cInsuranceVehicle.setTotalPremium(bigDecimal);
            cInsuranceVehicle.setId(id);
            // 修改
            cInsuranceVehicleService.updateCInsuranceVehicle(cInsuranceVehicle);
        }

        List<CInsuranceVehicle> list = cInsuranceVehicleService.selectCInsuranceVehicleInfo(id);
        List<CInsuranceVehicleItem> list1 = new ArrayList<>();
        for (CInsuranceVehicle vehicle : list) {
            CInsuranceVehicleItem cInsuranceVehicleItem1 = new CInsuranceVehicleItem();
            cInsuranceVehicleItem1.setId(vehicle.getCIVIID());
            cInsuranceVehicleItem1.setInsuranceId(vehicle.getInsuranceId());
            cInsuranceVehicleItem1.setInsuranceTypeId(vehicle.getInsuranceTypeId());
            cInsuranceVehicleItem1.setInsuranceItemId(vehicle.getInsuranceItemId());
            cInsuranceVehicleItem1.setInsuranceItemName(vehicle.getInsuranceItemName());
            cInsuranceVehicleItem1.setInsuranceQuota(vehicle.getInsuranceQuota());
            cInsuranceVehicleItem1.setInsuranceCost(vehicle.getInsuranceCost());
            list1.add(cInsuranceVehicleItem1);
            vehicle.setcInsuranceVehicleItems(list1);
        }

        ArrayList<CInsuranceVehicle> collect = list.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(
                                CInsuranceVehicle::getId))), ArrayList::new));
        return getDataTable(collect);
    }

    /**
     * 获取车辆管理-车辆保险详细信息 YLH
     * 页面回显数据
     */
//    @PreAuthorize(hasPermi = "car:insuranceVehicle:query")
    @GetMapping("/E02GetInfo/{id}")
    public AjaxResult E02GetInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cInsuranceVehicleService.selectCInsuranceVehicleById(id));
    }

    /**
     * 新增车辆管理-车辆保险   YLH
     *
     * 此处为保险管理（车辆保险）-新增（车辆保险）方法
     * 封装CInsuranceTypeTableJoinAdd DTO类 其中包含页面所添加的所有信息
     * 页面信息数据分别添加到 c_inserance_vehicle表 c_insurance_vehicle_item表 中
     * 页面选择险种回显数据接口为   car/insuranceTypeItem/E13List  险种类型（商业险）   险种（车辆损失险）
     * E13List接口支持name模糊查询  前面传typeId值
     *
     * 添加页面上的险种类型 险种 保额 保费 为c_insurance_vehicle_item表中的数据
     * @param cInsuranceTypeTableJoinAdd
     * @return
     */
    @PreAuthorize(hasPermi = "car:insuranceVehicle:add")
    @Log(title = "车辆管理-车辆保险", businessType = BusinessType.INSERT)
    @PostMapping(value = {"/E02Add","/SubmitCommon"})
    public AjaxResult E02Add(@Validated @RequestBody CInsuranceTypeTableJoinAdd cInsuranceTypeTableJoinAdd) {
        if (cInsuranceTypeTableJoinAdd.getType()==1&&StringUtils.isEmpty(cInsuranceTypeTableJoinAdd.getFrameNumber())){
            return AjaxResult.error("车架号不能为空");
        }
        if (cInsuranceTypeTableJoinAdd.getType()==2&&StringUtils.isEmpty(cInsuranceTypeTableJoinAdd.getLicensePlate())){
            return AjaxResult.error("车牌号不能为空");
        }
        if (cInsuranceTypeTableJoinAdd.getcInsuranceVehicleItems().size() == 0){
            return AjaxResult.error("请选择保险项目");
        }
        else {
            for (CInsuranceVehicleItem cInsuranceVehicleItem:cInsuranceTypeTableJoinAdd.getcInsuranceVehicleItems()){
                if (cInsuranceVehicleItem.getInsuranceCost()==null){
                    return AjaxResult.error("请填写保费");
                }
            }
        }
        // c_inserance_vehicle表
        CInsuranceVehicle cInsuranceVehicle = new CInsuranceVehicle();
        // 给cInsuranceVehicle的type属性赋值 保险类型
        cInsuranceVehicle.setType(cInsuranceTypeTableJoinAdd.getType());
        // 给cInsuranceVehicle的FrameNumber属性赋值 车架号
        cInsuranceVehicle.setFrameNumber(cInsuranceTypeTableJoinAdd.getFrameNumber());
        // 给cInsuranceVehicle的LicensePlate属性赋值 车牌号
        cInsuranceVehicle.setLicensePlate(cInsuranceTypeTableJoinAdd.getLicensePlate());
        // 给cInsuranceVehicle的Remarks属性赋值 备注
        cInsuranceVehicle.setRemarks(cInsuranceTypeTableJoinAdd.getRemarks());
        cInsuranceVehicle.setVehicleId(cInsuranceTypeTableJoinAdd.getVehicleId());
        UUID uuid = UUID.randomUUID();
        cInsuranceVehicle.setId(uuid+"");

//        CVehicle cVehicle = new CVehicle();
//        cVehicle.setFrameNumber(cInsuranceTypeTableJoinAdd.getFrameNumber());
//        cVehicle.setLicensePlate(cInsuranceTypeTableJoinAdd.getLicensePlate());
//        List<CVehicle> list = cVehicleService.selectCVehicleList(cVehicle);
//        String licensePlate = null;
//        for (CVehicle vehicle : list) {
//            licensePlate = vehicle.getId();
//        }

        LoginUser userInfo = tokenService.getLoginUser();
        cInsuranceVehicle.setCreateBy(userInfo.getUsername());

        // c_insurance_vehicle_item表
        if (cInsuranceTypeTableJoinAdd.getcInsuranceVehicleItems().size()>0){
            for (CInsuranceVehicleItem insuranceVehicleItem : cInsuranceTypeTableJoinAdd.getcInsuranceVehicleItems()) {
                // 获取当前登录的用户为创建人
                insuranceVehicleItem.setCreateBy(userInfo.getUsername());
                insuranceVehicleItem.setInsuranceId(uuid+"");
                UUID uuid1 = UUID.randomUUID();
                insuranceVehicleItem.setId(uuid1+"");
                insuranceVehicleItem.setDeleteFlag((long) 1);
            }
            BigDecimal totalQuantity = cInsuranceTypeTableJoinAdd.getcInsuranceVehicleItems().stream().map(i -> {
                if (i.getInsuranceCost()==null){
                    return BigDecimal.ZERO;
                }else {
                    return i.getInsuranceCost();
                }
            }).reduce(BigDecimal.ZERO,BigDecimal::add);
            cInsuranceVehicle.setTotalPremium(totalQuantity);
            //批量新增
            icInsuranceVehicleItemService.insertBatch(cInsuranceTypeTableJoinAdd.getcInsuranceVehicleItems());
        }
        // 设置逻辑删除为 1正常
        cInsuranceVehicle.setDeleteFlag((long) 1);
        //车辆保险审批新增
        int result = systemClient.adminSubmitCommon(11L,cInsuranceVehicle.getId(),"",userInfo.getSysUser().getNickName());//type = 11
        //无审批人情况直接通过
        if (result == 0){
            cInsuranceVehicle.setStatus(2L);
        }
        else {
            cInsuranceVehicle.setStatus(1L);
        }
        //c_insurance_vehicle_googds新增
        CInsuranceVehicleGoogds cInsuranceVehicleGoogds = new CInsuranceVehicleGoogds();
        cInsuranceVehicleGoogds.setId(UUID.randomUUID().toString());
        cInsuranceVehicleGoogds.setLicensePlate(cInsuranceTypeTableJoinAdd.getLicensePlate());
        cInsuranceVehicleGoogds.setVehicleId(cInsuranceTypeTableJoinAdd.getVehicleId());
        cInsuranceVehicleGoogds.setInsuranceId(cInsuranceVehicle.getId());
        cInsuranceVehicleGoogds.setFrameNumber(cInsuranceTypeTableJoinAdd.getFrameNumber());
        cInsuranceVehicleGoogds.setCreateBy(tokenService.getLoginUser().getUsername());
        icInsuranceVehicleGoogdsService.insertCInsuranceVehicleGoogds(cInsuranceVehicleGoogds);

        return toAjax(cInsuranceVehicleService.insertCInsuranceVehicle(cInsuranceVehicle));
    }
    /**
     * 修改车辆管理-车辆保险
     */
    @PreAuthorize(hasPermi = "car:insuranceVehicle:edit")
    @Log(title = "车辆管理-车辆保险", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CInsuranceVehicle cInsuranceVehicle)
    {
        return toAjax(cInsuranceVehicleService.updateCInsuranceVehicle(cInsuranceVehicle));
    }

    /**
     * 修改车辆管理-车辆保险  保额保费
     * c_insurance_vehicle_item保额保费在这个表中 修改的是这个表的数据
     */
    @PreAuthorize(hasPermi = "car:insuranceVehicle:edit")
    @Log(title = "车辆管理-车辆保险", businessType = BusinessType.UPDATE)
    @PostMapping("/E02EditSave")
    public AjaxResult E02EditSave(@RequestBody CInsuranceVehicleItem cInsuranceVehicleItem)
    {
        LoginUser userInfo = tokenService.getLoginUser();
        cInsuranceVehicleItem.setUpdateBy(userInfo.getUsername());
        return toAjax(icInsuranceVehicleItemService.updateCInsuranceVehicleItem(cInsuranceVehicleItem));
    }

    /**
     * 删除车辆管理-车辆保险
     */
    @PreAuthorize(hasPermi = "car:insuranceVehicle:remove")
    @Log(title = "车辆管理-车辆保险", businessType = BusinessType.DELETE)
	@GetMapping("/E02Delete/{id}")
    public AjaxResult E02Delete(@PathVariable("id") String id)
    {
        CInsuranceVehicle cInsuranceVehicle = cInsuranceVehicleService.selectCInsuranceVehicleById(id);
        cInsuranceVehicle.setDeleteFlag((long) 2);
        return toAjax(cInsuranceVehicleService.updateCInsuranceVehicle(cInsuranceVehicle));
    }

    /**
     * 保险费 M05
     */
    @PreAuthorize(hasPermi = "car:insuranceVehicle:list")
    @PostMapping(value = {"/M05List"})
    public TableDataInfo M05List(@RequestBody CInsuranceVehicle cInsuranceVehicle)
    {
        startPage(cInsuranceVehicle.getPageNum(),cInsuranceVehicle.getPageSize());
        List<CInsuranceVehicle> cInsuranceVehicles = cInsuranceVehicleService.selectCostIn(cInsuranceVehicle);

        // 计算统计
        CInsuranceVehicle insuranceVehicle = new CInsuranceVehicle();
        insuranceVehicle.setTypeId(cInsuranceVehicle.getTypeId());
        insuranceVehicle.setLicensePlates(cInsuranceVehicle.getLicensePlates());
        insuranceVehicle.setStartTime(cInsuranceVehicle.getStartTime());
        insuranceVehicle.setFinshTime(cInsuranceVehicle.getFinshTime());
        List<CInsuranceVehicle> cInsuranceVehicleList = cInsuranceVehicleService.selectCostIn(insuranceVehicle);
        BigDecimal result2 = cInsuranceVehicleList.stream().map(i -> {
            if (i.getInsuranceCost()==null){
                return BigDecimal.ZERO;
            }else {
                return i.getInsuranceCost();
            }
        }).reduce(BigDecimal.ZERO,BigDecimal::add);

        // 定义返回集合
        List<Map<String,Object>> mapList = new ArrayList<>();

        // 定义返回集合 对象
        Map<String,Object> map = new HashMap<>();
        map.put("list",cInsuranceVehicles);
        map.put("totalCost",result2);
        mapList.add(map);
        return getDataTableMap(mapList,cInsuranceVehicles);
    }

    /**
     * 保险费 M05导出
     */
    @PreAuthorize(hasPermi = "car:insuranceVehicle:export")
    @Log(title = "保险费", businessType = BusinessType.EXPORT)
    @PostMapping("/M05Export")
    public void export(HttpServletResponse response, CInsuranceVehicleCostExport cInsuranceVehicle) throws IOException
    {
        CInsuranceVehicle cInsuranceVehicleOne = new CInsuranceVehicle();
        cInsuranceVehicleOne.setLicensePlate(cInsuranceVehicle.getLicensePlate());
        cInsuranceVehicleOne.setTypeId(cInsuranceVehicle.getInsuranceTypeId());
        cInsuranceVehicleOne.setStartTime(cInsuranceVehicle.getStartTime());
        cInsuranceVehicleOne.setFinshTime(cInsuranceVehicle.getFinshTime());
        List<CInsuranceVehicle> cInsuranceVehicles = cInsuranceVehicleService.selectCostIn(cInsuranceVehicleOne);
        for (CInsuranceVehicle cInsuranceVehicle1 : cInsuranceVehicles){
            if (cInsuranceVehicle1.getType() ==1){
                cInsuranceVehicle1.setTypeName("新车保险");
            }
            if(cInsuranceVehicle1.getType() == 2){
                cInsuranceVehicle1.setTypeName("车辆续保");
            }
            if(cInsuranceVehicle1.getType() == 3){
                cInsuranceVehicle1.setTypeName("货物保险");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (cInsuranceVehicle1.getInsuranceStartDate() != null){
                cInsuranceVehicle1.setInsuranceStartDateExport(sdf.format(cInsuranceVehicle1.getInsuranceStartDate()));
            }
            if (cInsuranceVehicle1.getInsuranceEndDate() != null){
                cInsuranceVehicle1.setInsuranceEndDateExport(sdf.format(cInsuranceVehicle1.getInsuranceEndDate()));
            }
            if (cInsuranceVehicle1.getInsuranceHandleTime() != null){
                cInsuranceVehicle1.setInsuranceHandleTimeExport(sdf.format(cInsuranceVehicle1.getInsuranceHandleTime()));
            }
        }
        ExcelUtil<CInsuranceVehicle> util = new ExcelUtil<CInsuranceVehicle>(CInsuranceVehicle.class);
        util.exportExcel(response, cInsuranceVehicles, "insuranceCost");
    }

    /**
     * 货物保险-审批提交
     */
    @PreAuthorize(hasPermi = "pfservercar:vehicle:editsave")
    @Log(title = "保险审批", businessType = BusinessType.UPDATE)
    @PostMapping(value = {"/reviewCommon", "/E10EditSave", "/E03EditSave"})
    public AjaxResult E03EditSave(@Validated @RequestBody  CInsuranceVehicle cInsuranceVehicle) {
        if (StringUtils.isNull(cInsuranceVehicle.getStatus())){
            return AjaxResult.error("请选择审核结果");
        }
        if (cInsuranceVehicle.getStatus()==3 && StringUtils.isEmpty(cInsuranceVehicle.getReviewRemarks())){
            return AjaxResult.error("请输入不通过原因");
        }
        LoginUser userInfo = tokenService.getLoginUser();
        cInsuranceVehicle.setUserid(userInfo.getUserid().toString());
        return toAjax(cInsuranceVehicleService.checkCInsuranceVehicle(cInsuranceVehicle));
    }

    @PreAuthorize(hasPermi = "pfserver:vehicle:check")
    @Log(title = "保险审批")
    @PostMapping("/E03Check")
    public AjaxResult E03Check(@Validated @RequestBody ReviewSubmit reviewSubmit){
        if (reviewSubmit.getStatus()==2 && StringUtils.isEmpty(reviewSubmit.getReviewRemarks())){
            return AjaxResult.error("请输入不通过原因");
        }
        reviewSubmit.setUserId(tokenService.getLoginUser().getUserid().toString());
        return cInsuranceVehicleService.checkReview(reviewSubmit);
    }
}
