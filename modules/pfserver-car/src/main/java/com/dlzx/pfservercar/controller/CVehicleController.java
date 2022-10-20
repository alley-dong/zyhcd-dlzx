package com.dlzx.pfservercar.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.utils.poi.ExcelUtilh;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.client.SystemClient;
import com.dlzx.pfservercar.domain.CInsuranceVehicleGoogds;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.Common.SysDictionary;
import com.dlzx.pfservercar.domain.DDriver;
import com.dlzx.pfservercar.domain.ReviewSubmit;
import com.dlzx.pfservercar.domain.SysExamineConfigProcess;
import com.dlzx.pfservercar.domain.SysFile;
import com.dlzx.pfservercar.mapper.SysFileMapper;
import com.dlzx.pfservercar.service.ICVehicleInspectRecordService;
import com.dlzx.pfservercar.service.ISysFileService;
import com.dlzx.pfservercar.service.impl.CInsuranceVehicleServiceImpl;
import com.dlzx.pfservercar.service.impl.Commonlmpl.SysDictionaryServiceImpl;
import com.dlzx.system.api.model.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.service.ICVehicleService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;
import com.dlzx.pfservercar.service.ICVehicleService;
import com.dlzx.pfservercar.service.ICInsuranceTypeItemService;
import com.dlzx.pfservercar.service.ICInsuranceVehicleItemService;
import com.dlzx.pfservercar.service.ICInsuranceVehicleService;
import com.dlzx.pfservercar.service.ICInsuranceVehicleGoogdsService;
import com.dlzx.pfservercar.domain.CInsuranceVehicle;
import com.dlzx.pfservercar.domain.CInsuranceVehicleItem;
import com.dlzx.pfservercar.domain.CInsuranceTypeItem;


/**
 * 车辆档案Controller
 *
 * @author yangluhe
 * @date 2020-11-24
 */
@Api("车辆档案管理")
@RestController
@RequestMapping("/car/vehicle")
public class CVehicleController extends BaseController {
    @Autowired
    private ICVehicleService cVehicleService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SysDictionaryServiceImpl sysDictionaryService;
    @Autowired
    private ICVehicleInspectRecordService cVehicleInspectRecordService;
    @Autowired
    private ICInsuranceVehicleService icInsuranceVehicleService;
    @Autowired
    private ICInsuranceVehicleGoogdsService icInsuranceVehicleGoogdsService;
    @Autowired
    private ICInsuranceVehicleItemService icInsuranceVehicleItemService;
    @Autowired
    private SystemClient systemClient;
    @Autowired
    private ISysFileService sysFileService;
    @Autowired
    private SysFileMapper sysFileMapper;


    /**
     * 查询车辆档案列表
     */
    @ApiOperation("车辆档案列表")
    @PreAuthorize(hasPermi = "pfservercar:vehicle:list")
    @PostMapping(value = {"/D01VehicleList", "/E08VehicleList"})
    public TableDataInfo D01VehicleList(@RequestBody CVehicle cVehicle) {
        startPage(cVehicle.getPageNum(), cVehicle.getPageSize());
        List<CVehicle> list = cVehicleService.selectCVehicleNoDisableList(cVehicle);
        return getDataTable(list);
    }

    /**
     * 保险台账列表 ylh
     */
    @ApiOperation("保险台账列表")
    @PreAuthorize(hasPermi = "pfservercar:vehicle:list")
    @PostMapping(value = {"/E14List"})
    public TableDataInfo E14List(@RequestBody CVehicle cVehicle) {
        startPage(cVehicle.getPageNum(), cVehicle.getPageSize());
        List<CVehicle> list = cVehicleService.selectIBookList(cVehicle);
        for (CVehicle insuranceVehicleGoogds : list) {
            if (insuranceVehicleGoogds.getInsuranceEndDate() != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(insuranceVehicleGoogds.getInsuranceEndDate());
                calendar.add(Calendar.MONTH, -1);
                if (calendar.getTime().before(new Date())) {
                    insuranceVehicleGoogds.setOutTimeFlag(1L);
                } else {
                    insuranceVehicleGoogds.setOutTimeFlag(0L);
                }
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出车辆档案列表
     */
    @PreAuthorize(hasPermi = "pfservercar:vehicle:export")
    @Log(title = "车辆档案", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CVehicle cVehicle) throws IOException {
        List<CVehicle> list = cVehicleService.selectCVehicleList(cVehicle);
        ExcelUtil<CVehicle> util = new ExcelUtil<CVehicle>(CVehicle.class);
        util.exportExcel(response, list, "vehicle");
    }

    /**
     * 获取车辆档案详细信息
     */
    @PreAuthorize(hasPermi = "pfservercar:vehicle:info")
    @GetMapping(value = "/D03VehicleInfo/{id}")
    public AjaxResult D03VehicleInfo(@PathVariable("id") String id) {
        return AjaxResult.success(cVehicleService.selectCVehicleById(id));
    }

    /**
     * 新增车辆档案
     */
    @ApiOperation("车辆档案新增")
    @PreAuthorize(hasPermi = "pfservercar:vehicle:add")
    @Log(title = "车辆档案", businessType = BusinessType.INSERT)
    @PostMapping("/D02VehicleAdd")
    public AjaxResult D02VehicleAdd(@Validated @RequestBody CVehicle cVehicle) {
        //获取登录用户信息
        LoginUser userInfo = tokenService.getLoginUser();
        cVehicle.setCreateBy(userInfo.getUsername());
        List<CVehicle> cVehicleList = cVehicleService.selectCVehicleList(new CVehicle());
        for (CVehicle cVehicleOne : cVehicleList) {
            if (cVehicle.getLicensePlate().equals(cVehicleOne.getLicensePlate())) {
                return AjaxResult.error("车牌号不能重复");
            }
        }
        //校验车牌号
        int checkLicense = cVehicleService.selectCVehicleByLicensePlate(cVehicle.getLicensePlate());
        if (checkLicense > 0) {
            return AjaxResult.error("该车牌号已存在");
        }
        List<SysDictionary> sysDictionaryList = sysDictionaryService.selectSysDictionaryList(new SysDictionary());
        List<SysDictionary> vehicleTypeAll = new ArrayList<>();
        List<SysDictionary> vehicleModelAll = new ArrayList<>();
        //取出字典库中车辆类别和车辆型号
        for (SysDictionary sysDictionary : sysDictionaryList) {
            if (sysDictionary.getType() == 1 && sysDictionary.getLevel() == 1) {
                vehicleTypeAll.add(sysDictionary);
            }
            if (sysDictionary.getType() == 2 && sysDictionary.getLevel() == 2) {
                vehicleModelAll.add(sysDictionary);
            }
        }
        //判断是否在字典库中
            SysDictionary sysDictionaryCheckType = vehicleTypeAll.stream().filter(o -> (Objects.nonNull(o.getName()))&&(Objects.equals(o.getName(),cVehicle.getVehicleTypeName()))).findFirst().orElse(null);
            if (null == sysDictionaryCheckType) {
                return AjaxResult.error("该车辆类型不存在");
            } else {
                cVehicle.setVehicleType(sysDictionaryCheckType.getId());
            }
            SysDictionary sysDictionaryCheckModel = vehicleModelAll.stream().filter(o ->(Objects.nonNull(o.getName())) &&(Objects.equals(o.getName(),cVehicle.getVehicleModelName()))).findFirst().orElse(null);
            if (null == sysDictionaryCheckModel) {
                return AjaxResult.error("该车辆型号不存在");
            } else {
                cVehicle.setVehicleModel(sysDictionaryCheckModel.getId());
            }
        cVehicle.setId(UUID.randomUUID().toString());
        cVehicle.setDeleteFlag(Long.valueOf(1));
        cVehicle.setDisable(1L);
        return toAjax(cVehicleService.insertCVehicle(cVehicle));
    }

    /**
     * 修改车辆档案
     */
    @PreAuthorize(hasPermi = "pfservercar:vehicle:editsave")
    @Log(title = "车辆档案", businessType = BusinessType.UPDATE)
    @PostMapping("/D04VehicleEditSave")
    public AjaxResult D04VehicleEditSave(@Validated @RequestBody CVehicle cVehicle) {
        CVehicle cVehicleUpdate = cVehicleService.selectCVehicleById(cVehicle.getId());
        //获取登录用户信息
        LoginUser userInfo = tokenService.getLoginUser();
        cVehicleUpdate.setUpdateBy(userInfo.getUsername());
        //校验车牌号
        int checkLicense = cVehicleService.selectCVehicleByLicensePlate(cVehicle.getLicensePlate());
        if (!cVehicle.getLicensePlate().equals(cVehicleUpdate.getLicensePlate())) {
            if (checkLicense > 0) {
                return AjaxResult.error("该车牌号已存在");
            }
        }
        List<SysDictionary> sysDictionaryList = sysDictionaryService.selectSysDictionaryList(new SysDictionary());
        List<SysDictionary> vehicleTypeAll = new ArrayList<>();
        List<SysDictionary> vehicleModelAll = new ArrayList<>();
        //取出字典库中车辆类别和车辆型号
        for (SysDictionary sysDictionary : sysDictionaryList) {
            if (sysDictionary.getType() == 1 && sysDictionary.getLevel() == 1) {
                vehicleTypeAll.add(sysDictionary);
            }
            if (sysDictionary.getType() == 2 && sysDictionary.getLevel() == 2) {
                vehicleModelAll.add(sysDictionary);
            }
        }
        //判断是否在字典库中
        if (vehicleTypeAll != null){
        SysDictionary sysDictionaryCheckType = vehicleTypeAll.stream().filter(o ->(Objects.nonNull(o.getName()))&&(Objects.equals(o.getName(),cVehicle.getVehicleTypeName()))).findFirst().orElse(null);
        if (null == sysDictionaryCheckType) {
            return AjaxResult.error("该车辆类型不存在");
        } else {
            cVehicleUpdate.setVehicleType(sysDictionaryCheckType.getId());
        }
        }
        if (vehicleModelAll != null){
        SysDictionary sysDictionaryCheckModel = vehicleModelAll.stream().filter(o ->(Objects.nonNull(o.getName()))&&(Objects.equals(o.getName(),cVehicle.getVehicleModelName()))).findFirst().orElse(null);
        if (null == sysDictionaryCheckModel) {
            return AjaxResult.error("该车辆型号不存在");
        } else {
            cVehicleUpdate.setVehicleModel(sysDictionaryCheckModel.getId());
        }
        }
        cVehicleUpdate.setVehicleTypeName(cVehicle.getVehicleTypeName());
        cVehicleUpdate.setIdentifier(cVehicle.getIdentifier());
        cVehicleUpdate.setVehicleModelName(cVehicle.getVehicleModelName());
        cVehicleUpdate.setLicensePlate(cVehicle.getLicensePlate());
        cVehicleUpdate.setBrand(cVehicle.getBrand());
        cVehicleUpdate.setDriveForm(cVehicle.getDriveForm());
        cVehicleUpdate.setHorsepower(cVehicle.getHorsepower());
        cVehicleUpdate.setEngine(cVehicle.getEngine());
        cVehicleUpdate.setEngineNumber(cVehicle.getEngineNumber());
        cVehicleUpdate.setFrameNumber(cVehicle.getFrameNumber());
        cVehicleUpdate.setGearbox(cVehicle.getGearbox());
        cVehicleUpdate.setFuelType(cVehicle.getFuelType());
        cVehicleUpdate.setRearAxle(cVehicle.getRearAxle());
        cVehicleUpdate.setManufacturer(cVehicle.getManufacturer());
        cVehicleUpdate.setMainTankVolume(cVehicle.getMainTankVolume());
        cVehicleUpdate.setViceTankVolume(cVehicle.getViceTankVolume());
        cVehicleUpdate.setTotalMass(cVehicle.getTotalMass());
        cVehicleUpdate.setApprovedLoad(cVehicle.getApprovedLoad());
        cVehicleUpdate.setCurbWeight(cVehicle.getCurbWeight());
        cVehicleUpdate.setGpsCost(cVehicle.getGpsCost());
        cVehicleUpdate.setInspectCost(cVehicle.getInspectCost());
        cVehicleUpdate.setPurchaseDate(cVehicle.getPurchaseDate());
        cVehicleUpdate.setPurchasePrice(cVehicle.getPurchasePrice());
        cVehicleUpdate.setPurchaseTax(cVehicle.getPurchaseTax());
        cVehicleUpdate.setDistributor(cVehicle.getDistributor());
        cVehicleUpdate.setOriginalPrice(cVehicle.getOriginalPrice());
        cVehicleUpdate.setInspectDate(cVehicle.getInspectDate());
        cVehicleUpdate.setScrapDate(cVehicle.getScrapDate());
        cVehicleUpdate.setRegisterDate(cVehicle.getRegisterDate());
        cVehicleUpdate.setIsInspect(cVehicle.getIsInspect());
        cVehicleUpdate.setColor(cVehicle.getColor());
        cVehicleUpdate.setFileUrl(cVehicle.getFileUrl());

        return toAjax(cVehicleService.updateCVehicleAndESBData(cVehicleUpdate));
    }

    /**
     * 删除车辆档案
     */
    @PreAuthorize(hasPermi = "pfservercar:vehicle:delete")
    @Log(title = "车辆档案", businessType = BusinessType.DELETE)
    @GetMapping(value = "/D01VehicleDelete/{id}")
    public AjaxResult remove(@PathVariable String id) {
        CVehicle cVehicle = cVehicleService.selectCVehicleById(id);
        cVehicle.setDeleteFlag(Long.valueOf(2));
        return toAjax(cVehicleService.updateCVehicleAndESBData(cVehicle));
    }

    /**
     * 下载车辆档案模板
     *
     * @return
     */
    @PreAuthorize(hasPermi = "pfservercar:vehicle:template")
    @GetMapping(value = "/D01VehicleTemplate")
    public AjaxResult D01VehicleTemplate() {
        ExcelUtilh<CVehicle> util = new ExcelUtilh<CVehicle>(CVehicle.class);
        return util.importTemplateExcel("题表模板");
    }

    /**
     * 导入车辆档案信息
     */
    @ApiOperation("导入车辆档案列表")
    @PreAuthorize(hasPermi = "pfservercar:vehicle:import")
    @Log(title = "驾驶员-题库", businessType = BusinessType.IMPORT)
    @PostMapping("/D01VehicleImport")
    public AjaxResult D01VehicleImport(@Validated MultipartFile file, boolean updateSupport, String sellId) throws Exception {
        //excel导入判断数据格式和表格式
        ExcelUtil<CVehicle> util = new ExcelUtil<CVehicle>(CVehicle.class);
        List<CVehicle> cVehicleList = new ArrayList<>();
        try {
            cVehicleList = util.importExcel(file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return AjaxResult.error("数据格式有误");
        }

        //excel导入数据判断数据内容是否符合要求
        Map<String, String> message = cVehicleService.importVechicle(cVehicleList, updateSupport, sellId);
        String code = message.get("code");
        if ("0".equals(code)) {
            return AjaxResult.success(message.get("msg"));
        } else {
            return AjaxResult.error(message.get("msg"));
        }
    }

    /**
     * 上传附件保存数据表
     */
    @Log(title = "上传附件保存数据表")
    @PreAuthorize(hasPermi = "pfservercar:vehicle:enclosure")
    @PostMapping("/D02Enclosure")
    public AjaxResult D02Enclosure(@RequestBody CVehicle cVehicle) {
        LoginUser userInfo = tokenService.getLoginUser();
        List<SysFile> sysFileList = new ArrayList<>();
        if (cVehicle.getFileUrl().size() == 0) {
            return AjaxResult.error("不能上传空文件");
        } else {
            sysFileMapper.delelteFilesByRelationId(cVehicle.getId());
            for (String url : cVehicle.getFileUrl()) {
                SysFile sysFile = new SysFile();
                sysFile.setId(UUID.randomUUID().toString());
                sysFile.setType(9L);
                sysFile.setRelationId(cVehicle.getId());
                sysFile.setFilePath(url);
                sysFile.setDeleteFlag(1L);
                sysFile.setCreateUser(userInfo.getUsername());
                sysFile.setCreateTime(new Date());
                sysFileList.add(sysFile);
            }
            int fileNum = sysFileService.insertSysfileBatch(sysFileList);
            if (fileNum == cVehicle.getFileUrl().size()) {
                return AjaxResult.success("成功插入" + (fileNum) + "条");
            } else {
                return AjaxResult.error("成功插入" + (fileNum) + "条，有" + (cVehicle.getFileUrl().size() - fileNum) + "插入失败");
            }
        }
    }

    @GetMapping("/D01Disable/{id}")
    @Log(title = "禁启用功能")
    public AjaxResult D01Disable(@PathVariable("id")String id){
        CVehicle cVehicle = cVehicleService.selectCVehicleById(id);
        if (cVehicle == null){
            return AjaxResult.error("改车辆信息不存在");
        }
        else {
            if (StringUtils.isNotEmpty(cVehicle.getDisable().toString())){
                if (cVehicle.getDisable() == 1){
                    cVehicle.setDisable(2L);
                }
                else {
                    cVehicle.setDisable(1L);
                }
            }
            return toAjax(cVehicleService.updateCDisable(cVehicle));
        }
    }
}
