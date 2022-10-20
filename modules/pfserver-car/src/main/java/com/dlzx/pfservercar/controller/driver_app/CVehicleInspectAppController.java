package com.dlzx.pfservercar.controller.driver_app;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.domain.SysExamineConfigParams;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.common.security.service.DriverTokenService;
import com.dlzx.pfservercar.client.SystemClient;
import com.dlzx.pfservercar.domain.CInsuranceVehicle;
import com.dlzx.pfservercar.domain.CInsuranceVehicleItem;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.CVehicleInspect;
import com.dlzx.pfservercar.domain.CVehicleInspectItem;
import com.dlzx.pfservercar.domain.CVehicleInspectMonthly;
import com.dlzx.pfservercar.domain.CVehicleInspectMonthlyItem;
import com.dlzx.pfservercar.service.ICVehicleInspectItemService;
import com.dlzx.pfservercar.service.ICVehicleInspectMonthlyService;
import com.dlzx.pfservercar.service.ICVehicleInspectService;
import com.dlzx.pfservercar.service.ICVehicleService;
import com.dlzx.system.api.model.LoginDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/driver-api/inspect")
public class CVehicleInspectAppController extends BaseController {
    @Autowired
    private ICVehicleInspectService icVehicleInspectService;
    @Autowired
    private ICVehicleInspectItemService icVehicleInspectItemService;
    @Autowired
    private ICVehicleInspectMonthlyService icVehicleInspectMonthlyService;
    @Autowired
    private DriverTokenService driverTokenService;
    @Autowired
    private SystemClient systemClient;
    @Autowired
    private ICVehicleService vehicleService;

    /**
     * 查询车辆管理-车辆定检记录列表
     */
    @ResponseBody
    @Log(title = "车辆管理-车辆定检-列表")
    @RequestMapping(path = "/I01List",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public TableDataInfo I01List(@RequestBody CVehicleInspect cVehicleInspect) throws ParseException {

        // 获取司机信息  todo —— udpate by 2021 3.11 19.29
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        cVehicleInspect.setDriverId(loginDriver.getUserid());

        startPage(cVehicleInspect.getPageNum(), cVehicleInspect.getPageSize());
        if (!StringUtils.isEmpty(cVehicleInspect.getInspectDate())){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            //开始时间
            Date sTime = simpleDateFormat.parse(cVehicleInspect.getInspectDate());
            //结束时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sTime);
            calendar.add(Calendar.MONTH,1);
            Date fTime = calendar.getTime();
            cVehicleInspect.setsTime(sTime);
            cVehicleInspect.setfTime(fTime);
        }
        return getDataTable(icVehicleInspectService.selectCVehicleInspectList(cVehicleInspect));
    }

    /**
     * 查询车辆管理-车辆定检记录列表
     * 可以返回所有
     */
    @ResponseBody
    @Log(title = "车辆管理-车辆定检")
    @RequestMapping(value = {"/I02GetInfo"})
    public AjaxResult I02GetInfo(@RequestBody CVehicle cVehicle)
    {
        List<CVehicle> cVehicleList = vehicleService.selectCVehicleList(cVehicle);
        return AjaxResult.success(cVehicleList);
    }

    /**
     * 车辆定检-再次提交APP
     * 差一个审核表修改成1
     * 差一个批量修改数据因为需要修改图片信息
     */
    @Log(title = "车辆管理-车辆定检-再次提交", businessType = BusinessType.UPDATE)
    @RequestMapping(value = {"/I07Add"})
    public AjaxResult I07Add(@RequestBody CVehicleInspect cVehicleInspect)
    {
        cVehicleInspect.setStatus(Long.valueOf(1));

        // c_vehicle_inspect_item 表
        List<CVehicleInspectItem> cVehicleInspectItems = cVehicleInspect.getcVehicleInspectItemList();
        List<CVehicleInspectItem> list = new ArrayList<>();
        for (CVehicleInspectItem cVehicleInspectItem : cVehicleInspectItems) {
            CVehicleInspectItem cVehicleInspectItem1 = new CVehicleInspectItem();
            String s = UUID.randomUUID().toString();
            cVehicleInspectItem1.setId(s);
            cVehicleInspectItem1.setInspectId(cVehicleInspect.getId());
            cVehicleInspectItem1.setType(cVehicleInspect.getType());
            cVehicleInspectItem1.setItemType(cVehicleInspectItem.getItemType());
            cVehicleInspectItem1.setName(cVehicleInspectItem.getName());
            cVehicleInspectItem1.setStatus(cVehicleInspectItem.getStatus());
            cVehicleInspectItem1.setFiles(cVehicleInspectItem.getFiles());
            list.add(cVehicleInspectItem1);
        }
        if (list.size()>0){
            icVehicleInspectItemService.deleteCVehicleInspectItemByinspectId(cVehicleInspect.getId());

            icVehicleInspectItemService.insertList(list);
        }

        // 给c_vehicle_inspect_monthly表加数值
        Long status = cVehicleInspect.getStatus();
        // 当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String format = simpleDateFormat.format(date);
        List<CVehicleInspectMonthly> cVehicleInspectMonthlies = icVehicleInspectMonthlyService.selectCVehicleInspectMonthlyByInspectDate(format);
        if (cVehicleInspectMonthlies.size()>0 && status ==2L) {
            // 通过 完成+1 未完成-1
            for (CVehicleInspectMonthly cVehicleInspectMonthly1 : cVehicleInspectMonthlies) {
                Date inspectDate = cVehicleInspectMonthly1.getInspectDate();
                // sql实现+1操作
                icVehicleInspectMonthlyService.updateCVehicleInspectMonthlyDate(inspectDate);
            }

        }
        cVehicleInspect.setMonthlyId(cVehicleInspectMonthlies.get(0).getId());
        //新增审批任务
        int result = systemClient.submitCommon(new SysExamineConfigParams(13L,cVehicleInspect.getId(),cVehicleInspect.getDriverId(),cVehicleInspect.getCreateBy(),cVehicleInspect.getSysExamineConfigProcessList()));
        //若无审批人情况直接通过审批
        if (result == 0){
            cVehicleInspect.setStatus(2L);
        }
        return toAjax(icVehicleInspectService.updateCVehicleInspect(cVehicleInspect));
    }

    /**
     * 新增车辆定检
     * 用L02GetInfo获取到选中的车牌号的ID
     * 用ID来关联c_vehicle_inspect_item这个表
     */
    @Log(title = "车辆管理-车辆定检-新增定检", businessType = BusinessType.UPDATE)
    @RequestMapping(value = {"/I02Add"})
    public AjaxResult I02Add(@RequestBody CVehicleInspect cVehicleInspect)
    {
        cVehicleInspect.setId(UUID.randomUUID().toString());
        cVehicleInspect.setStatus(Long.valueOf(1));
        cVehicleInspect.setDeleteFlag(Long.valueOf(1));
        cVehicleInspect.setCreateBy(driverTokenService.getLoginDriver().getUsername());
        cVehicleInspect.setDriverId(driverTokenService.getLoginDriver().getUserid());

        // c_vehicle_inspect_item 表 update by 4.26 对没选择status的数据进行过滤
        List<CVehicleInspectItem> cVehicleInspectItems = cVehicleInspect.getcVehicleInspectItemList().stream().filter(c -> c.getStatus() != null).collect(Collectors.toList());
        List<CVehicleInspectItem> list = new ArrayList<>();
        for (CVehicleInspectItem cVehicleInspectItem : cVehicleInspectItems) {
            if (cVehicleInspectItem.getStatus()==2){
                if (StringUtils.isEmpty(cVehicleInspectItem.getFiles())){
                    return AjaxResult.error("请上传定检项"+cVehicleInspectItem.getName() + "的图片");
                }
            }
            CVehicleInspectItem cVehicleInspectItem1 = new CVehicleInspectItem();
            String s = UUID.randomUUID().toString();
            cVehicleInspectItem1.setId(s);
            cVehicleInspectItem1.setInspectId(cVehicleInspect.getId());
            cVehicleInspectItem1.setType(cVehicleInspect.getType());
            cVehicleInspectItem1.setItemType(cVehicleInspectItem.getItemType());
            cVehicleInspectItem1.setName(cVehicleInspectItem.getName());
            cVehicleInspectItem1.setStatus(cVehicleInspectItem.getStatus());
            cVehicleInspectItem1.setFiles(cVehicleInspectItem.getFiles());
            list.add(cVehicleInspectItem1);
        }
        if (list.size()>0){
            icVehicleInspectItemService.insertList(list);
        }

        // 给c_vehicle_inspect_monthly表加数值
        Long status = cVehicleInspect.getStatus();
        // 当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String format = simpleDateFormat.format(date);
        List<CVehicleInspectMonthly> cVehicleInspectMonthlies = icVehicleInspectMonthlyService.selectCVehicleInspectMonthlyByInspectDate(format);
        if (cVehicleInspectMonthlies.size()>0 && status ==2L) {
            // 通过 完成+1 未完成-1
            for (CVehicleInspectMonthly cVehicleInspectMonthly1 : cVehicleInspectMonthlies) {
                Date inspectDate = cVehicleInspectMonthly1.getInspectDate();
                // sql实现+1操作
                icVehicleInspectMonthlyService.updateCVehicleInspectMonthlyDate(inspectDate);
            }

        }
        cVehicleInspect.setMonthlyId(cVehicleInspectMonthlies.get(0).getId());
        //新增审批任务
        int result = systemClient.submitCommon(new SysExamineConfigParams(13L,cVehicleInspect.getId(),cVehicleInspect.getDriverId(),cVehicleInspect.getCreateBy(),cVehicleInspect.getSysExamineConfigProcessList()));
        //若无审批人情况直接通过审批
        if (result == 0){
            cVehicleInspect.setStatus(2L);
        }
        return toAjax(icVehicleInspectService.insertCVehicleInspect(cVehicleInspect));
    }

    /**
     * 车辆管理-记录详情(待审批)
     */
    @ResponseBody
    @Log(title = "车辆管理-车辆定检")
    @RequestMapping(value = {"/I04List/{id}"})
    public AjaxResult I04List(@PathVariable("id")String id)
    {
        CVehicleInspect cVehicleInspect = icVehicleInspectService.selectCVehicleInspectById(id);

        List<CVehicleInspectItem> cVehicleInspectItemList= new ArrayList<>();
        if (cVehicleInspect.getType()==1){
            cVehicleInspectItemList = icVehicleInspectItemService.selectCMainVehicleInspectItemByInspectId(id);
        }else {
            cVehicleInspectItemList = icVehicleInspectItemService.selectCHangVehicleInspectItemByInspectId(id);
        }
        cVehicleInspect.setcVehicleInspectItemList(cVehicleInspectItemList);
//        ArrayList<CVehicleInspect> collect = list.stream().collect(Collectors.collectingAndThen(
//                Collectors.toCollection(() -> new TreeSet<>(
//                        Comparator.comparing(
//                                CVehicleInspect::getId))), ArrayList::new));
        return AjaxResult.success(cVehicleInspect);
    }
}
