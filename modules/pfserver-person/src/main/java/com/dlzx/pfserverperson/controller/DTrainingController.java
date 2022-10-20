package com.dlzx.pfserverperson.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.DriverTokenService;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverperson.domain.*;
import com.dlzx.pfserverperson.service.*;
import com.dlzx.system.api.model.LoginDriver;
import com.dlzx.system.api.model.LoginUser;
import com.sun.org.apache.xpath.internal.objects.XString;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 驾驶员-培训Controller
 *
 * @author dlzx
 * @date 2020-11-26
 */
@RestController
@RequestMapping("/person/training")
public class DTrainingController extends BaseController {
    @Autowired
    private IDTrainingService dTrainingService;
    @Autowired
    private IDDriverService dDriverService;
    @Autowired
    private IDTrainingUserService dTrainingUserService;
    @Autowired
    private IDDriverGroupService driverGroupService;
    @Autowired
    private IDTrainingUserRecordService dTrainingUserRecordService;
    @Autowired
    private IDDriverGroupUserService driverGroupUserService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private DriverTokenService driverTokenService;

    /**
     * 查询驾驶员-培训列表
     */
    @PreAuthorize(hasPermi = "person:training:list")
    @PostMapping("/A33List")
    public TableDataInfo list(@RequestBody DTraining dTraining) {
        startPage(dTraining.getPageNum(), dTraining.getPageSize());
        List<DTraining> list = dTrainingService.selectDTrainingList(dTraining);
        return getDataTable(list);
    }

    @PreAuthorize(hasPermi = "person:training:listRead")
    @PostMapping("/A38ListRead")
    public TableDataInfo listRead(@RequestBody DTrainingUser dTrainingUser) {
        List<DTrainingUser> list = dTrainingUserService.selectDTrainingUserTwoList(dTrainingUser);
        return getDataTable(list);
    }

    /**
     * 获取驾驶员-培训详细信息
     */
    @PreAuthorize(hasPermi = "person:training:edit")
    @GetMapping(value = "/A36GetInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        DTraining dTraining = dTrainingService.selectDTrainingById(id);
        List<DTrainingUser> dTrainingUserList = dTrainingUserService.selectDTrainingUserByTrainingId(id);
        List<DDriver> list = new ArrayList<>();

        List<String> driverIds = dTrainingUserList.stream().map(DTrainingUser::getUserId).collect(Collectors.toList());
        List<DDriver> dDrivers = dDriverService.selectDDriverListS(driverIds);
        for (DTrainingUser dTrainingUser : dTrainingUserList) {
            DDriver dDriver = new DDriver();
            dDriver.setId(dTrainingUser.getUserId());
            dDriver.setName(dDrivers.stream().filter(d->d.getId().equals(dTrainingUser.getUserId())).findFirst().orElse(new DDriver()).getName());
            list.add(dDriver);
        }
        DTrainingDriver dTrainingDriver = new DTrainingDriver();
        dTrainingDriver.setTitle(dTraining.getTitle());
        dTrainingDriver.setContent(dTraining.getContent());
        dTrainingDriver.setTrainingEndTime(dTraining.getTrainingEndTime());
        dTrainingDriver.setMinDuration(dTraining.getMinDuration());
        dTrainingDriver.setEducation(dTraining.getEducation());
        dTrainingDriver.setVideoUrl(dTraining.getVideoUrl());
//        String gId=str.substring(1, str.length() -1);
//
        List<String> trainingList = JSONObject.parseArray(dTraining.getGroupId(), String.class);

        dTrainingDriver.setGroupIdList(trainingList);
        dTrainingDriver.setDriverIdList(list);
        dTrainingDriver.setFileList(dTraining.getFileList());
        return AjaxResult.success(dTrainingDriver);

    }

    /**
     * 导出驾驶员-培训列表
     */
    @PreAuthorize(hasPermi = "person:training:export")
    @Log(title = "驾驶员-培训", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DTraining dTraining) throws IOException {
        List<DTraining> list = dTrainingService.selectDTrainingList(dTraining);
        ExcelUtil<DTraining> util = new ExcelUtil<DTraining>(DTraining.class);
        util.exportExcel(response, list, "training");
    }

    /**
     * 获取驾驶员-培训详细信息
     */
    @PreAuthorize(hasPermi = "person:training:getInfoCk")
    @GetMapping(value = "/A37GetInfoCK/{id}")
    public AjaxResult getInfoCk(@PathVariable("id") String id) {
        DTraining dTraining = dTrainingService.selectDTrainingById(id);
        DTrainingUser dTrainingUser = new DTrainingUser();
        dTrainingUser.setTrainingId(id);

        List<DTrainingUser> list = dTrainingUserService.selectDTrainingUserTwoList(dTrainingUser);
        List<String> listG = new ArrayList<>();
        for (DTrainingUser dTrainingUser1 : list) {
            listG.add(dTrainingUser1.getDriverName());
        }

        DTrainingDriver dTrainingDriver = new DTrainingDriver();
        dTrainingDriver.setId(id);
        dTrainingDriver.setTitle(dTraining.getTitle());
        dTrainingDriver.setContent(dTraining.getContent());
        dTrainingDriver.setEducation(dTraining.getEducation());
        dTrainingDriver.setMinDuration(dTraining.getMinDuration());
        dTrainingDriver.setTrainingEndTime(dTraining.getTrainingEndTime());
        dTrainingDriver.setVideoUrl(dTraining.getVideoUrl());
        dTrainingDriver.setFileList(dTraining.getFileList());
        dTrainingDriver.setDriverNameList(listG);

        return AjaxResult.success(dTrainingDriver);
    }

    /**
     * 新增驾驶员-培训
     */
    @PreAuthorize(hasPermi = "person:training:add")
    @Log(title = "驾驶员-培训", businessType = BusinessType.INSERT)
    @PostMapping("/A34Add")
    public AjaxResult add(@RequestBody @Validated DTrainingDriverAdd dTrainingDriver) throws ParseException {


        if (dTrainingDriver.getGroupId().size() == 0 && dTrainingDriver.getDriverId().size() == 0) {
            return AjaxResult.error("选择培训人员或选择群组至少勾选一种");
        } else {
            LoginUser userInfo = tokenService.getLoginUser();
            String Id = UUID.randomUUID().toString();
            DTraining dTraining = new DTraining();
            dTraining.setId(Id);
            dTraining.setGroupId(JSON.toJSONString(dTrainingDriver.getGroupId()));
            dTraining.setTitle(dTrainingDriver.getTitle());
            dTraining.setContent(dTrainingDriver.getContent());
            dTraining.setVideoUrl(dTrainingDriver.getVideoUrl());
            dTraining.setEducation(dTrainingDriver.getEducation());
            dTraining.setMinDuration(dTrainingDriver.getMinDuration());
            dTraining.settEndTime(dTrainingDriver.getTrainingEndTime());
            dTraining.setStatus(Long.valueOf(1));
            dTraining.setCreateBy(userInfo.getUsername());
            dTraining.setDeleteFlag(Long.valueOf(1));
            dTraining.setFileUrls(dTrainingDriver.getFileUrls());
            dTrainingService.insertDTrainingBC(dTraining);

            List<String> groupId = dTrainingDriver.getGroupId();
            List<String> driverId = dTrainingDriver.getDriverId();
            if (groupId.size() > 0) {
                String[] groupArr = groupId.toArray(new String[groupId.size()]);
                List<String> list = driverGroupUserService.selectDDriverGroupUserListS(groupArr);
                driverId.addAll(list);
                driverId = driverId.stream().distinct().collect(Collectors.toList());
            }

            // 公告人员
            List<DTrainingUser> userList = new ArrayList<>();
            DTrainingUser dTrainingUser;

            // 公告人员记录
            List<DTrainingUserRecord> userRecordList = new ArrayList<>();
            DTrainingUserRecord userRecord;

            List<DDriver> dDriverList = dDriverService.selectDDriverListS(driverId);
            for (int i = 0; i < dDriverList.size(); i++) {
                DDriver driver = dDriverList.get(i);

                // 添加公告人员信息
                String id = UUID.randomUUID().toString();
                dTrainingUser = new DTrainingUser();
                dTrainingUser.setId(id);
                dTrainingUser.setTrainingId(Id);
                dTrainingUser.setUserId(driver.getId());
                userList.add(dTrainingUser);

                // 添加公告人员记录信息
                userRecord = new DTrainingUserRecord();
                userRecord.setId(UUID.randomUUID().toString());
                userRecord.setTrainingId(Id);
                userRecord.setUserId(driver.getId());
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(dTrainingDriver.getTrainingEndTime());
                if (DateUtils.getNowDate().getTime() <= date.getTime()) {
                    userRecord.setStatus(Long.valueOf(1));
                } else if (DateUtils.getNowDate().getTime() > date.getTime()) {
                    userRecord.setStatus(Long.valueOf(3));
                }
                userRecord.setDriverName(driver.getName());
                userRecord.setIdCard(driver.getIdCard());
                userRecord.setTel(driver.getContact());
                userRecordList.add(userRecord);
            }

            int result = dTrainingUserService.insertDTrainingUserSOrRecord(userList, userRecordList);
            return toAjax(result);
        }

    }

    /**
     * 修改驾驶员-培训
     */
    @PreAuthorize(hasPermi = "person:training:edit")
    @Log(title = "驾驶员-培训", businessType = BusinessType.UPDATE)
    @PostMapping("/A36Edit")
    public AjaxResult edit(@RequestBody @Validated DTrainingDriverAdd dTrainingDriver) throws ParseException {
        if (dTrainingDriver.getGroupId().size() == 0 && dTrainingDriver.getDriverId().size() == 0) {
            return AjaxResult.error("选择群组人员或选择群组至少勾选一种");
        } else {
            LoginUser userInfo = tokenService.getLoginUser();
            DTraining dTraining = dTrainingService.selectDTrainingById(dTrainingDriver.getId());
            if (dTraining == null) {
                return AjaxResult.error("此培训不存在");
            }
            dTraining.setGroupId(JSON.toJSONString(dTrainingDriver.getGroupId()));
            dTraining.setTitle(dTrainingDriver.getTitle());
            dTraining.setContent(dTrainingDriver.getContent());
            dTraining.setVideoUrl(dTrainingDriver.getVideoUrl());
            dTraining.setUpdateBy(userInfo.getUsername());
            dTraining.setEducation(dTrainingDriver.getEducation());
            dTraining.setMinDuration(dTrainingDriver.getMinDuration());
            dTraining.settEndTime(dTrainingDriver.getTrainingEndTime());
            dTraining.setFileUrls(dTrainingDriver.getFileUrls());
            dTrainingService.updateDTraining(dTraining);

            List<String> groupId = dTrainingDriver.getGroupId();
            List<String> driverId = dTrainingDriver.getDriverId();
            if (groupId.size() > 0) {
                String[] groupArr = groupId.toArray(new String[groupId.size()]);
                List<String> list1 = driverGroupUserService.selectDDriverGroupUserListS(groupArr);
                driverId.addAll(list1);
                driverId = driverId.stream().distinct().collect(Collectors.toList());
            }
            // 培训人员
            List<DTrainingUser> userList = new ArrayList<>();
            DTrainingUser dTrainingUser;

            // 培训人员记录
            List<DTrainingUserRecord> userRecordList = new ArrayList<>();
            DTrainingUserRecord userRecord;


            //根据培训id查记录表信息
            DTrainingUserRecord dTrainingUserRecord = new DTrainingUserRecord();
            dTrainingUserRecord.setTrainingId(dTrainingDriver.getId());
            List<DTrainingUserRecord> list1 = dTrainingUserRecordService.selectDTrainingUserRecordList(dTrainingUserRecord);

            //如果修改了截止时间，则修改记录状态
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (simpleDateFormat.parse(dTrainingDriver.getTrainingEndTime()).getTime()>System.currentTimeMillis()){
                for (DTrainingUserRecord trainingUserRecord : list1) {
                    if (trainingUserRecord.getStatus()==3){
                        trainingUserRecord.setStatus(1L);
                        dTrainingUserRecordService.updateDTrainingUserRecord(trainingUserRecord);
                    }else if(trainingUserRecord.getStatus()==4){
                        trainingUserRecord.setStatus(2L);
                        dTrainingUserRecordService.updateDTrainingUserRecord(trainingUserRecord);
                    }
                }
            }

            List<DDriver> dDriverList = dDriverService.selectDDriverListS(driverId);
            for (int i = 0; i < dDriverList.size(); i++) {
                DDriver driver = dDriverList.get(i);

                // 添加培训人员信息
                String id = UUID.randomUUID().toString();
                dTrainingUser = new DTrainingUser();
                dTrainingUser.setId(id);
                dTrainingUser.setTrainingId(dTrainingDriver.getId());
                dTrainingUser.setUserId(driver.getId());
                userList.add(dTrainingUser);

                boolean aa = list1.stream().anyMatch(o -> o.getUserId().equals(driver.getId()));
                List<DTrainingUserRecord> listCar = list1.stream().filter(b -> Objects.equals(driver.getId(), b.getUserId())).collect(Collectors.toList());
                if (listCar.size() == 0) {
                    // 添加培训人员记录信息
                    userRecord = new DTrainingUserRecord();
                    userRecord.setId(UUID.randomUUID().toString());
                    userRecord.setTrainingId(dTrainingDriver.getId());
                    userRecord.setUserId(driver.getId());
                    userRecord.setDriverName(driver.getName());
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = format.parse(dTrainingDriver.getTrainingEndTime());
                    if (DateUtils.getNowDate().getTime() <= date.getTime()) {
                        userRecord.setStatus(Long.valueOf(1));
                    } else if (DateUtils.getNowDate().getTime() > date.getTime()) {
                        userRecord.setStatus(Long.valueOf(3));
                    }
                    userRecord.setIdCard(driver.getIdCard());
                    userRecord.setTel(driver.getContact());
                    userRecordList.add(userRecord);
                }


            }


            int result = dTrainingUserRecordService.insertDTrainingUserSOrRecord(dTrainingDriver.getId(), userList, userRecordList);
            return toAjax(result);
        }
    }

    /**
     * 删除驾驶员-培训
     */
    @PreAuthorize(hasPermi = "person:training:delete")
    @Log(title = "驾驶员-培训", businessType = BusinessType.DELETE)
    @GetMapping("/A33Delete/{id}")
    public AjaxResult remove(@PathVariable String id) {
        DTraining dTraining = dTrainingService.selectDTrainingById(id);
        int msg = 1;
        if (dTraining == null) {
            return AjaxResult.error("无此条培训");
        } else {
            if (dTraining.getStatus() == 1) {
                LoginUser userInfo = tokenService.getLoginUser();
                dTraining.setDeleteFlag(Long.valueOf(2));
                dTraining.setUpdateBy(userInfo.getUsername());
                dTrainingService.updateDTraining(dTraining);

            } else {
                return AjaxResult.error("发布信息不能删除");
            }
            return toAjax(msg);
        }
    }

    /**
     * 状态驾驶员-培训
     */
    @PreAuthorize(hasPermi = "person:training:change")
    @Log(title = "驾驶员-培训", businessType = BusinessType.DELETE)
    @GetMapping("/A33Change/{id}")
    public AjaxResult change(@PathVariable String id) {
        DTraining dTraining = dTrainingService.selectDTrainingById(id);
        LoginUser userInfo = tokenService.getLoginUser();
        if (dTraining == null) {
            return AjaxResult.error("没有该条培训信息");
        } else {
            if (dTraining.getStatus() == 1) {
                dTraining.setCreateTime(DateUtils.getNowDate());
                dTraining.setUpdateBy(userInfo.getUsername());
                dTraining.setStatus(Long.valueOf(2));
            } else if (dTraining.getStatus() == 2) {
                dTraining.setCreateTime(null);
                dTraining.setUpdateBy(userInfo.getUsername());
                dTraining.setStatus(Long.valueOf(1));
            }
            dTrainingService.updateDTrainingStatus(dTraining);

            return AjaxResult.success();
        }
    }

    @RequestMapping(path = "/C01List", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    @Log(title = "驾驶员培训列表")
    public TableDataInfo C01List(@RequestBody DTrainingAp dTrainingAp) {
        startPage(dTrainingAp.getPageNum(), dTrainingAp.getPageSize());
//        LoginUser userInfo = tokenService.getLoginUser();
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
//        dTrainingAp.setUserId("2");
        dTrainingAp.setUserId(loginDriver.getUserid().toString());
        if (StringUtils.isNotNull(dTrainingAp.getbTime())) {
            Date date = DateUtils.parseDate(dTrainingAp.getbTime());
            dTrainingAp.setsTime(date);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.MONTH, 1);
            date = calendar.getTime();
            dTrainingAp.setfTime(date);
            dTrainingAp.seteTime(DateUtils.dateTime(date));
        }
        List<DTrainingAp> list = dTrainingUserService.selectDTrainingList(dTrainingAp);
        return getDataTable(list);
    }

    @RequestMapping("/C02List")
    @ResponseBody
    @Log(title = "驾驶员培训详情列表")
    public AjaxResult C02List(@RequestParam("id") String id) {

        return AjaxResult.success(dTrainingService.selectDriverDTrainingById(id));
    }

    @RequestMapping(path = "/C01Change", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    @Log(title = "驾驶员培训更改状态")
    public AjaxResult C01Change(@RequestBody DTrainingAp dTrainingAp) {

        dTrainingAp.setDangTime(DateUtils.getNowDate());
        return toAjax(dTrainingUserService.updateDTrainingStatus(dTrainingAp));

    }

    @RequestMapping(path = "/C02Edit", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    @Log(title = "驾驶员培训编辑")
    public AjaxResult C02Edit(@RequestBody DTrainingAp dTrainingAp) {


        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        DTrainingUser dTrainingUser = new DTrainingUser();
        dTrainingUser.setUserId(loginDriver.getUserid());
        dTrainingUser.setTrainingId(dTrainingAp.getId());
        List<DTrainingUser> list = dTrainingUserService.selectDTrainingUserTwoList(dTrainingUser);

        DTrainingUser dTrainingUser1 = list.get(0);
        long x = 60 * dTrainingUser1.getMinDuration();
        if (dTrainingUser1.getStatus() == 2 || dTrainingUser1.getStatus() == 4) {
            if (dTrainingAp.getSeeTime() >= x) {
                DTrainingUserRecord dTrainingUserRecord = new DTrainingUserRecord();
                dTrainingUserRecord.setUserId(loginDriver.getUserid());
                dTrainingUserRecord.setTrainingId(dTrainingAp.getId());
                List<DTrainingUserRecord> list1 = dTrainingUserRecordService.selectDTrainingUserRecordList(dTrainingUserRecord);
                DTrainingUserRecord dTrainingUserRecord1 = list1.get(0);
                dTrainingUserRecord1.setLatelyTime(DateUtils.getNowDate());
                return toAjax(dTrainingUserRecordService.updateDTrainingUserRecord(dTrainingUserRecord1));
            }
        }
        if (dTrainingUser1.getStatus() == 1 && dTrainingAp.getSeeTime() >= x) {
            DTrainingUserRecord dTrainingUserRecord = new DTrainingUserRecord();
            dTrainingUserRecord.setUserId(loginDriver.getUserid());
            dTrainingUserRecord.setTrainingId(dTrainingAp.getId());
            List<DTrainingUserRecord> list1 = dTrainingUserRecordService.selectDTrainingUserRecordList(dTrainingUserRecord);
            DTrainingUserRecord dTrainingUserRecord1 = list1.get(0);
            dTrainingUserRecord1.setCompleteTime(DateUtils.getNowDate());
            dTrainingUserRecord1.setLatelyTime(DateUtils.getNowDate());
            dTrainingUserRecord1.setStatus(2L);
            return toAjax(dTrainingUserRecordService.updateDTrainingUserRecord(dTrainingUserRecord1));
        } else if (dTrainingUser1.getStatus() == 3 && dTrainingAp.getSeeTime() >= x) {
            DTrainingUserRecord dTrainingUserRecord = new DTrainingUserRecord();
            dTrainingUserRecord.setUserId(loginDriver.getUserid());
            dTrainingUserRecord.setTrainingId(dTrainingAp.getId());
            List<DTrainingUserRecord> list1 = dTrainingUserRecordService.selectDTrainingUserRecordList(dTrainingUserRecord);
            DTrainingUserRecord dTrainingUserRecord1 = list1.get(0);
            dTrainingUserRecord1.setCompleteTime(DateUtils.getNowDate());
            dTrainingUserRecord1.setLatelyTime(DateUtils.getNowDate());
            dTrainingUserRecord1.setStatus(4L);
            return toAjax(dTrainingUserRecordService.updateDTrainingUserRecord(dTrainingUserRecord1));
        } else {
            // 就算没阅读完也要更新最近学习时间
            DTrainingUserRecord dTrainingUserRecord = new DTrainingUserRecord();
            dTrainingUserRecord.setUserId(loginDriver.getUserid());
            dTrainingUserRecord.setTrainingId(dTrainingAp.getId());
            dTrainingUserRecord.setLatelyTime(DateUtils.getNowDate());
            dTrainingUserRecordService.updateDTrainingUserRecord(dTrainingUserRecord);
            return AjaxResult.error("阅读时长未达到最短阅读时长");
        }

    }
}
