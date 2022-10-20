package com.dlzx.pfserverperson.controller;

import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.DriverTokenService;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverperson.domain.*;
import com.dlzx.pfserverperson.service.*;
import com.dlzx.system.api.model.LoginDriver;
import com.dlzx.system.api.model.LoginUser;
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
 * 驾驶员-公告Controller
 *
 * @author dlzx
 * @date 2020-11-24
 */
@RestController
@RequestMapping("/person/notice")
public class DNoticeController extends BaseController {
    @Autowired
    private IDNoticeService dNoticeService;
    @Autowired
    private IDDriverService dDriverService;
    @Autowired
    private IDNoticeUserService dNoticeUserService;
    @Autowired
    private IDNoticeUserRecordService dNoticeUserRecordService;
    @Autowired
    private IDDriverGroupUserService driverGroupUserService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private DriverTokenService driverTokenService;

    /**
     * 查询驾驶员-公告列表
     */
    @PreAuthorize(hasPermi = "person:notice:list")
    @PostMapping("/A42List")
    public TableDataInfo list(@RequestBody DNotice dNotice) {
        startPage(dNotice.getPageNum(), dNotice.getPageSize());
        List<DNotice> list = dNoticeService.selectDNoticeList(dNotice);
        return getDataTable(list);
    }

    @PreAuthorize(hasPermi = "person:notice:listRead")
    @PostMapping("/A47ListRead")
    public TableDataInfo listRead(@RequestBody DNoticeUser dNoticeUser) {
        List<DNoticeUser> list = dNoticeUserService.selectDNoticeUserTwoList(dNoticeUser);
        return getDataTable(list);
    }

    /**
     * 导出驾驶员-公告列表
     */
    @PreAuthorize(hasPermi = "person:notice:export")
    @Log(title = "驾驶员-公告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DNotice dNotice) throws IOException {
        List<DNotice> list = dNoticeService.selectDNoticeList(dNotice);
        ExcelUtil<DNotice> util = new ExcelUtil<DNotice>(DNotice.class);
        util.exportExcel(response, list, "notice");
    }

    /**
     * 获取驾驶员-公告详细信息
     */
    @PreAuthorize(hasPermi = "person:notice:edit")
    @GetMapping(value = "/A45GetInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        DNotice dNotice = dNoticeService.selectDNoticeById(id);
        List<DNoticeUser> dNoticeUserList = dNoticeUserService.selectDNoticeUserByNoticeId(id);
        List<DDriver> list = new ArrayList<>();

        List<String> driverIds = dNoticeUserList.stream().map(DNoticeUser::getUserId).collect(Collectors.toList());
        List<DDriver> dDrivers = dDriverService.selectDDriverListS(driverIds);
        for (DNoticeUser dNoticeUser : dNoticeUserList) {
            DDriver dDriver = new DDriver();
            dDriver.setId(dNoticeUser.getUserId());
            dDriver.setName(dDrivers.stream().filter(d->d.getId().equals(dNoticeUser.getUserId())).findFirst().orElse(new DDriver()).getName());
            list.add(dDriver);
        }
        DNoticeDriver dNoticeDriver = new DNoticeDriver();
        dNoticeDriver.setTitle(dNotice.getTitle());
        dNoticeDriver.setContent(dNotice.getContent());
        dNoticeDriver.setGroupId(dNotice.getGroupId());
        dNoticeDriver.setDriverIdList(list);
        dNoticeDriver.setDeadline(dNotice.getDeadline());
        return AjaxResult.success(dNoticeDriver);
    }

    /**
     * 获取驾驶员-公告详细信息
     */
    @PreAuthorize(hasPermi = "person:notice:getInfoCk")
    @GetMapping(value = "/A46GetInfoCk/{id}")
    public AjaxResult getInfoCk(@PathVariable("id") String id) {
        DNotice dNotice = dNoticeService.selectDNoticeById(id);
        DNoticeUser dNoticeUser = new DNoticeUser();
        dNoticeUser.setNoticeId(id);

        List<DNoticeUser> list = dNoticeUserService.selectDNoticeUserTwoList(dNoticeUser);
        List<String> listG = new ArrayList<>();
        for (DNoticeUser dNoticeUser1 : list) {
            listG.add(dNoticeUser1.getDriverName());
        }


        DNoticeDriver dNoticeDriver = new DNoticeDriver();
        dNoticeDriver.setId(id);
        dNoticeDriver.setTitle(dNotice.getTitle());
        dNoticeDriver.setContent(dNotice.getContent());
        dNoticeDriver.setDriverNameList(listG);
        dNoticeDriver.setDeadline(dNotice.getDeadline());

        return AjaxResult.success(dNoticeDriver);
    }

    /**
     * 新增驾驶员-公告
     */
    @PreAuthorize(hasPermi = "person:notice:add")
    @Log(title = "驾驶员-公告", businessType = BusinessType.INSERT)
    @PostMapping("/A43Add")
    public AjaxResult add(@RequestBody @Validated DNoticeDriverAdd dNoticeDriver) {
        if (dNoticeDriver.getGroupId().size() == 0 && dNoticeDriver.getDriverId().size() == 0) {
            return AjaxResult.error("选择群组人员或选择群组至少勾选一种");
        } else {
            LoginUser userInfo = tokenService.getLoginUser();
            String Id = UUID.randomUUID().toString();
            DNotice dNotice = new DNotice();
            dNotice.setId(Id);
            dNotice.setGroupId(JSON.toJSONString(dNoticeDriver.getGroupId()));
            dNotice.setTitle(dNoticeDriver.getTitle());
            dNotice.setContent(dNoticeDriver.getContent());
            dNotice.setDeadline(dNoticeDriver.getDeadline());
            dNotice.setCreateBy(userInfo.getUsername());
            dNotice.setStatus(1L);
            dNotice.setDeleteFlag(Long.valueOf(1));
            dNoticeService.insertDNoticeBC(dNotice);

            List<String> groupId = dNoticeDriver.getGroupId();
            List<String> driverId = dNoticeDriver.getDriverId();
            if (groupId.size() > 0) {
                String[] groupArr = groupId.toArray(new String[groupId.size()]);
                List<String> list = driverGroupUserService.selectDDriverGroupUserListS(groupArr);
                driverId.addAll(list);
                driverId = driverId.stream().distinct().collect(Collectors.toList());
            }

            // 公告人员
            List<DNoticeUser> userList = new ArrayList<>();
            DNoticeUser dNoticeUser;

            // 公告人员记录
            List<DNoticeUserRecord> userRecordList = new ArrayList<>();
            DNoticeUserRecord userRecord;

            List<DDriver> dDriverList = dDriverService.selectDDriverListS(driverId);
            for (int i = 0; i < dDriverList.size(); i++) {
                DDriver driver = dDriverList.get(i);

                // 添加公告人员信息
                String id = UUID.randomUUID().toString();
                dNoticeUser = new DNoticeUser();
                dNoticeUser.setId(id);
                dNoticeUser.setNoticeId(Id);
                dNoticeUser.setUserId(driver.getId());
                userList.add(dNoticeUser);

                // 添加公告人员记录信息
                userRecord = new DNoticeUserRecord();
                userRecord.setId(UUID.randomUUID().toString());
                userRecord.setNoticeId(Id);
                userRecord.setUserId(driver.getId());
                userRecord.setDriverName(driver.getName());
                if (driver.getStatus() == 3L) {
                    userRecord.setStatus(1L);
                }
                userRecord.setIdCard(driver.getIdCard());
                userRecord.setTel(driver.getContact());
                userRecordList.add(userRecord);
            }

            int result = dNoticeUserService.insertDNoticeUserSOrRecord(userList, userRecordList);
            return toAjax(result);
        }
    }

    /**
     * 修改驾驶员-公告
     */
    @PreAuthorize(hasPermi = "person:notice:edit")
    @Log(title = "驾驶员-公告", businessType = BusinessType.UPDATE)
    @PostMapping("/A45Edit")
    public AjaxResult edit(@RequestBody @Validated DNoticeDriverAdd dNoticeDriver) {
        if (dNoticeDriver.getGroupId().size() == 0 && dNoticeDriver.getDriverId().size() == 0) {
            return AjaxResult.error("选择群组人员或选择群组至少勾选一种");
        } else {
            LoginUser userInfo = tokenService.getLoginUser();
            DNotice dNotice = dNoticeService.selectDNoticeById(dNoticeDriver.getId());
            if (dNotice == null) {
                return AjaxResult.error("编辑公告不存在");
            }
            dNotice.setGroupId(JSON.toJSONString(dNoticeDriver.getGroupId()));
            dNotice.setTitle(dNoticeDriver.getTitle());
            dNotice.setDeadline(dNoticeDriver.getDeadline());
            dNotice.setContent(dNoticeDriver.getContent());
            dNotice.setUpdateBy(userInfo.getUsername());
            dNoticeService.updateDNotice(dNotice);

            List<String> groupId = dNoticeDriver.getGroupId();
            List<String> driverId = dNoticeDriver.getDriverId();
            if (groupId.size() > 0) {
                String[] groupArr = groupId.toArray(new String[groupId.size()]);
                List<String> list1 = driverGroupUserService.selectDDriverGroupUserListS(groupArr);
                driverId.addAll(list1);
                driverId = driverId.stream().distinct().collect(Collectors.toList());
            }
            // 公告人员
            List<DNoticeUser> userList = new ArrayList<>();
            DNoticeUser dNoticeUser;

            // 公告人员记录
            List<DNoticeUserRecord> userRecordList = new ArrayList<>();
            DNoticeUserRecord userRecord;


            //根据公告id查记录表信息
            DNoticeUserRecord dNoticeUserRecord = new DNoticeUserRecord();
            dNoticeUserRecord.setNoticeId(dNoticeDriver.getId());
            List<DNoticeUserRecord> list1 = dNoticeUserRecordService.selectDNoticeUserRecordList(dNoticeUserRecord);

            List<DDriver> dDriverList = dDriverService.selectDDriverListS(driverId);
            for (int i = 0; i < dDriverList.size(); i++) {
                DDriver driver = dDriverList.get(i);

                // 添加公告人员信息
                String id = UUID.randomUUID().toString();
                dNoticeUser = new DNoticeUser();
                dNoticeUser.setId(id);
                dNoticeUser.setNoticeId(dNoticeDriver.getId());
                dNoticeUser.setUserId(driver.getId());
                userList.add(dNoticeUser);

                List<DNoticeUserRecord> listCar = list1.stream().filter(b -> Objects.equals(driver.getId(), b.getUserId())).collect(Collectors.toList());
                if (listCar.size() == 0) {
                    // 添加公告人员记录信息
                    userRecord = new DNoticeUserRecord();
                    userRecord.setId(UUID.randomUUID().toString());
                    userRecord.setNoticeId(dNoticeDriver.getId());
                    userRecord.setUserId(driver.getId());
                    userRecord.setDriverName(driver.getName());
                    userRecord.setStatus(driver.getStatus());
                    userRecord.setIdCard(driver.getIdCard());
                    userRecord.setTel(driver.getContact());
                    userRecordList.add(userRecord);
                }


            }


            int result = dNoticeUserRecordService.insertDNoticeUserSOrRecord(dNoticeDriver.getId(), userList, userRecordList);
            return toAjax(result);
        }

    }

    /**
     * 删除驾驶员-公告
     */
    @PreAuthorize(hasPermi = "person:notice:delete")
    @Log(title = "驾驶员-公告", businessType = BusinessType.DELETE)
    @GetMapping("/A42Delete/{id}")
    public AjaxResult remove(@PathVariable @RequestBody String id) {
        LoginUser userInfo = tokenService.getLoginUser();
        DNotice dNotice = dNoticeService.selectDNoticeById(id);
        dNotice.setDeleteFlag(Long.valueOf(2));
        dNotice.setUpdateBy(userInfo.getUsername());
        return toAjax(dNoticeService.updateDNotice(dNotice));
    }

    /**
     * 状态驾驶员-培训
     */
    @PreAuthorize(hasPermi = "person:training:change")
    @Log(title = "驾驶员-公告", businessType = BusinessType.DELETE)
    @GetMapping("/A42Change/{id}")
    public AjaxResult change(@PathVariable String id) {
        DNotice dNotice = dNoticeService.selectDNoticeById(id);
        LoginUser userInfo = tokenService.getLoginUser();
        if (dNotice == null) {
            return AjaxResult.error("没有该条公告信息");
        } else {
            if (dNotice.getStatus() == 1) {
                dNotice.setCreateTime(DateUtils.getNowDate());
                dNotice.setUpdateBy(userInfo.getUsername());
                dNotice.setStatus(Long.valueOf(2));
                dNotice.setUpdateTime(DateUtils.getNowDate());
            }
            dNoticeService.updateDNotice(dNotice);

            return AjaxResult.success();
        }
    }

    @RequestMapping(path = "/D01List", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    @Log(title = "驾驶员公告列表")
    public TableDataInfo D01List(@RequestBody DNoticeAp dNoticeAp) {
        startPage(dNoticeAp.getPageNum(), dNoticeAp.getPageSize());
//        LoginUser userInfo = tokenService.getLoginUser();
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        dNoticeAp.setUserId(loginDriver.getUserid().toString());
//        dNoticeAp.setUserId("1");
        if (StringUtils.isNotNull(dNoticeAp.getbTime())) {
            Date date = DateUtils.parseDate(dNoticeAp.getbTime());
            dNoticeAp.setsTime(date);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.MONTH, 1);
            date = calendar.getTime();
            System.out.println(date);
            dNoticeAp.setfTime(date);
        }
        List<DNoticeAp> list = dNoticeUserService.selectDNoticeList(dNoticeAp);
        return getDataTable(list);
    }

    @RequestMapping("/D02List")
    @ResponseBody
    @Log(title = "驾驶员公告详情")
    public AjaxResult list(@RequestParam("id") String id) {
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        DNoticeUserRecord dNoticeUserRecord = new DNoticeUserRecord();
        dNoticeUserRecord.setNoticeId(id);
        dNoticeUserRecord.setUserId(loginDriver.getUserid());
        List<DNoticeUserRecord> list = dNoticeUserRecordService.selectDNoticeUserRecordList(dNoticeUserRecord);
        if (list.size()>0) {
            list.get(0).setStatus(2L);
            dNoticeUserRecordService.updateDNoticeUserRecord(list.get(0));
        }
        return AjaxResult.success(dNoticeService.selectDNoticeById(id));
    }
}
