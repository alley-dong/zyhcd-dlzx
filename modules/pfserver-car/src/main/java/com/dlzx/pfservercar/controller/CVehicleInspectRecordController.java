package com.dlzx.pfservercar.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.Common.CVehicleInspectAdd;
import com.dlzx.pfservercar.service.ICVehicleService;
import com.dlzx.system.api.model.LoginUser;
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
import com.dlzx.pfservercar.domain.CVehicleInspectRecord;
import com.dlzx.pfservercar.service.ICVehicleInspectRecordService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 车辆管理-车辆年检记录Controller
 * 
 * @author yangluhe
 * @date 2020-11-27
 */
@RestController
@RequestMapping("/car/vehicleInspectRecord")
public class CVehicleInspectRecordController extends BaseController
{
    @Autowired
    private ICVehicleInspectRecordService cVehicleInspectRecordService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ICVehicleService cVehicleService;

    /**
     * 查询车辆管理-车辆年检记录列表
     */
    @PreAuthorize(hasPermi = "pfservercar:vehicleInspectRecord:list")
    @PostMapping("/D05list")
    public TableDataInfo D05list(@RequestBody CVehicleInspectRecord cVehicleInspectRecord) throws ParseException {
        startPage(cVehicleInspectRecord.getPageNum(),cVehicleInspectRecord.getPageSize());
        SimpleDateFormat sdfOne = new SimpleDateFormat("yyyyMMdd");
        CVehicle cVehicle = new CVehicle();
        cVehicle.setLicensePlate(cVehicleInspectRecord.getLicensePlate());
        cVehicle.setInspectDate(cVehicleInspectRecord.getInspectDate());
        List<CVehicle> list = cVehicleService.selectCVehicleRecordList(cVehicle);
        //到期判断
        for (CVehicle cVehicleOne:list){
            if (cVehicleOne.getNextInspectDate()!=null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                Calendar rightNow = Calendar.getInstance();
                rightNow.setTime(cVehicleOne.getNextInspectDate());
                rightNow.add(Calendar.MONTH, -3);//日期加3个月
                Date dt1 = rightNow.getTime();
                Date nowDate = new Date();
                if (nowDate.before(dt1)) {
                    cVehicleOne.setOutTimeFlag(Long.valueOf(1));
                } else {
                    cVehicleOne.setOutTimeFlag(Long.valueOf(2));
                }
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出车辆管理-车辆年检记录列表
     */
    @PreAuthorize(hasPermi = "pfservercar:vehicleInspectRecord:export")
    @Log(title = "车辆管理-车辆年检记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CVehicleInspectRecord cVehicleInspectRecord) throws IOException
    {
        List<CVehicleInspectRecord> list = cVehicleInspectRecordService.selectCVehicleInspectRecordList(cVehicleInspectRecord);
        ExcelUtil<CVehicleInspectRecord> util = new ExcelUtil<CVehicleInspectRecord>(CVehicleInspectRecord.class);
        util.exportExcel(response, list, "vehicleInspectRecord");
    }

    /**
     * 获取车辆管理-车辆年检记录详细信息
     */
    @PreAuthorize(hasPermi = "pfservercar:vehicleInspectRecord:infolist")
    @PostMapping(value = "/D05InfoList")
    public TableDataInfo D05InfoList(@RequestBody CVehicleInspectRecord cVehicleInspectRecord)
    {
        startPage(cVehicleInspectRecord.getPageNum(),cVehicleInspectRecord.getPageSize());
        List<CVehicleInspectRecord> cVehicleInspectRecordInfoList = cVehicleInspectRecordService.selectCVehicleInspectRecordInfoList(cVehicleInspectRecord);
        return getDataTable(cVehicleInspectRecordInfoList);
    }

    /**
     * 新增车辆管理-车辆年检记录
     */
    @PreAuthorize(hasPermi = "pfservercar:vehicleInspectRecord:add")
    @Log(title = "车辆管理-车辆年检记录", businessType = BusinessType.INSERT)
    @PostMapping(value = "/D06add")
    public AjaxResult D06add(@Validated @RequestBody CVehicleInspectAdd cVehicleInspectAdd)
    {
        CVehicleInspectRecord cVehicleInspectRecord = new CVehicleInspectRecord();
        CVehicle cVehicle = cVehicleService.selectCVehicleById(cVehicleInspectAdd.getId());
        cVehicleInspectRecord.setId(UUID.randomUUID().toString());
        cVehicleInspectRecord.setDeleteFlag(Long.valueOf(1));
        cVehicleInspectRecord.setTruckId(cVehicleInspectAdd.getId());
        cVehicleInspectRecord.setLicensePlate(cVehicle.getLicensePlate());
        cVehicleInspectRecord.setRegisterDate(cVehicle.getRegisterDate());
        //获取当前用户信息
        LoginUser userInfo = tokenService.getLoginUser();
        cVehicleInspectRecord.setCreateBy(userInfo.getUsername());
        cVehicleInspectRecord.setRemarks(cVehicleInspectAdd.getRemarks());
        cVehicleInspectRecord.setInspectDate(cVehicleInspectAdd.getInspectDate());
        cVehicleInspectRecord.setNextInspectDate(cVehicleInspectAdd.getNextInspectDate());
        if (cVehicleInspectAdd.getOperateInspectCost()==null){
            cVehicleInspectRecord.setOperateInspectCost(BigDecimal.ZERO);
        }
        else {
            cVehicleInspectRecord.setOperateInspectCost(cVehicleInspectAdd.getOperateInspectCost());
        }
        if (cVehicleInspectAdd.getReviewCost()==null){
            cVehicleInspectRecord.setReviewCost(BigDecimal.ZERO);
        }
        else {
            cVehicleInspectRecord.setReviewCost(cVehicleInspectAdd.getReviewCost());
        }
        if (cVehicleInspectAdd.getVehiceInspectCost()==null){
            cVehicleInspectRecord.setVehiceInspectCost(BigDecimal.ZERO);
        }
        else {
            cVehicleInspectRecord.setVehiceInspectCost(cVehicleInspectAdd.getVehiceInspectCost());
        }
        BigDecimal to = cVehicleInspectRecord.getOperateInspectCost().add(cVehicleInspectRecord.getReviewCost());
        BigDecimal total = cVehicleInspectRecord.getVehiceInspectCost().add(to);
        cVehicleInspectRecord.setTotalCost(total);
        cVehicleInspectRecord.setLicensePlate(cVehicleInspectAdd.getLicensePlate());
        cVehicleInspectRecord.setRegisterDate(cVehicleInspectAdd.getRegisterDate());
        cVehicleInspectRecord.setFiles(cVehicleInspectAdd.getFiles());
        cVehicle.setNextInspectDate(cVehicleInspectAdd.getNextInspectDate());
        cVehicle.setInspectDate(cVehicleInspectAdd.getInspectDate());
        cVehicleService.updateCVehicle(cVehicle);
        return toAjax(cVehicleInspectRecordService.insertCVehicleInspectRecord(cVehicleInspectRecord));
    }

    /**
     * 修改车辆管理-车辆年检记录
     */
    @PreAuthorize(hasPermi = "pfservercar:vehicleInspectRecord:edit")
    @Log(title = "车辆管理-车辆年检记录", businessType = BusinessType.UPDATE)
    @PostMapping("/D06EditSave")
    public AjaxResult D06EditSave(@Validated @RequestBody CVehicleInspectRecord cVehicleInspectRecord)
    {
        CVehicleInspectRecord cVehicleInspectRecordOne = cVehicleInspectRecordService.selectCVehicleInspectRecordById(cVehicleInspectRecord.getId());
        cVehicleInspectRecordOne.setRemarks(cVehicleInspectRecord.getRemarks());
        cVehicleInspectRecordOne.setInspectDate(cVehicleInspectRecord.getInspectDate());
        cVehicleInspectRecordOne.setNextInspectDate(cVehicleInspectRecord.getNextInspectDate());
        if (cVehicleInspectRecord.getOperateInspectCost()==null){
            cVehicleInspectRecordOne.setOperateInspectCost(BigDecimal.ZERO);
        }
        else {
            cVehicleInspectRecordOne.setOperateInspectCost(cVehicleInspectRecord.getOperateInspectCost());
        }
        if (cVehicleInspectRecord.getReviewCost()==null){
            cVehicleInspectRecordOne.setReviewCost(BigDecimal.ZERO);
        }
        else {
            cVehicleInspectRecordOne.setReviewCost(cVehicleInspectRecord.getReviewCost());
        }
        if (cVehicleInspectRecord.getVehiceInspectCost()==null){
            cVehicleInspectRecordOne.setVehiceInspectCost(BigDecimal.ZERO);
        }
        else {
            cVehicleInspectRecordOne.setVehiceInspectCost(cVehicleInspectRecord.getVehiceInspectCost());
        }
        //获取当前用户信息
        LoginUser userInfo = tokenService.getLoginUser();
        cVehicleInspectRecordOne.setUpdateBy(userInfo.getUsername());
        BigDecimal to = cVehicleInspectRecordOne.getOperateInspectCost().add(cVehicleInspectRecordOne.getReviewCost());
        BigDecimal total = cVehicleInspectRecordOne.getVehiceInspectCost().add(to);
        cVehicleInspectRecordOne.setTotalCost(total);
        cVehicleInspectRecordOne.setFiles(cVehicleInspectRecord.getFiles());
        cVehicleInspectRecordService.updateCVehicleInspectRecord(cVehicleInspectRecordOne);
        CVehicle cVehicle = cVehicleService.selectCVehicleById(cVehicleInspectRecordOne.getTruckId());
        //查找数据
        CVehicleInspectRecord VehicleInspectRecord = new CVehicleInspectRecord();
        VehicleInspectRecord.setTruckId(cVehicleInspectRecordOne.getTruckId());
        cVehicle.setInspectDate(cVehicleInspectRecordService.selectCVehicleInspectRecordInfoList(VehicleInspectRecord).get(0).getInspectDate());
        cVehicle.setNextInspectDate(cVehicleInspectRecordService.selectCVehicleInspectRecordInfoList(VehicleInspectRecord).get(0).getNextInspectDate());
        return toAjax(cVehicleService.updateCVehicle(cVehicle));
    }

    /**
     * 删除车辆管理-车辆年检记录
     */
    @PreAuthorize(hasPermi = "pfservercar:vehicleInspectRecord:delete")
    @Log(title = "车辆管理-车辆年检记录", businessType = BusinessType.DELETE)
	@GetMapping("/D06Delete/{id}")
    public AjaxResult D06Delete(@PathVariable("id") String id)
    {
        CVehicleInspectRecord cVehicleInspectRecord = cVehicleInspectRecordService.selectCVehicleInspectRecordById(id);
        CVehicle cVehicle = cVehicleService.selectCVehicleById(cVehicleInspectRecord.getTruckId());
        cVehicleInspectRecord.setDeleteFlag(Long.valueOf(2));
        CVehicleInspectRecord vehicleInspectRecord = new CVehicleInspectRecord();
        vehicleInspectRecord.setTruckId(cVehicleInspectRecord.getTruckId());
        if (cVehicleInspectRecordService.selectCVehicleInspectRecordInfoList(vehicleInspectRecord).size()==0){
            cVehicle.setNextInspectDate(null);
        }
        else {
            cVehicle.setNextInspectDate(cVehicleInspectRecordService.selectCVehicleInspectRecordInfoList(vehicleInspectRecord).get(0).getNextInspectDate());
        }
        cVehicleService.updateCVehicle(cVehicle);
        return toAjax(cVehicleInspectRecordService.updateCVehicleInspectRecord(cVehicleInspectRecord));
    }

    /**
     * 编辑渲染数据
     */
    @PreAuthorize(hasPermi = "pfservercar:vehicleInspectRecord:edit")
    @Log(title = "车辆管理-车辆编辑渲染数据",businessType = BusinessType.UPDATE)
    @GetMapping("/D06Edit/{id}")
    public AjaxResult D06Edit(@PathVariable("id")String id){
        CVehicleInspectRecord cVehicleInspectRecord = cVehicleInspectRecordService.selectCVehicleInspectRecordById(id);
        return AjaxResult.success(cVehicleInspectRecord);
    }
}
