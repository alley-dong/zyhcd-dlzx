package com.dlzx.pfserverperson.controller;

import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.common.security.utils.SecurityUtils;
import com.dlzx.pfserverperson.domain.DDriver;
import com.dlzx.pfserverperson.domain.DDriverEducationRecord;
import com.dlzx.pfserverperson.domain.DDriverFileDomain.DDriverAe;
import com.dlzx.pfserverperson.domain.commonDomain.PasswordReset;
import com.dlzx.pfserverperson.service.*;
import com.dlzx.system.api.model.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.weaver.loadtime.Aj;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.github.pagehelper.page.PageMethod.startPage;

/**
 * @author ylh
 * @program:dlzx
 * @description：
 * @create:2020-11-20 21-06
 */
@Api("驾驶员档案列表")
@RestController
@RequestMapping("/person/dDriverFile")
public class DDriverFileController extends BaseController {

    @Autowired
    private IDDriverService driverService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private IDDriverAccidentRecordService driverAccidentRecordService;
    @Autowired
    private IDDriverEducationRecordService driverEducationRecordService;
    @Autowired
    private IDDriverFamilyRecordService driverFamilyRecordService;
    @Autowired
    private IDDriverDriveRecordService driverDriveRecordService;

    /*@ApiOperation("列表")
    @PreAuthorize(hasPermi = "person:dDriverFile:list")
    @Log(title = "查询驾驶员档案列表")
    @GetMapping("/A24List")
    public TableDataInfo A24List(DDriver dDriver){
        startPage();
        List<DDriver> dDriverList = driverService.selectDDriverList(dDriver);
        return getDataTable(dDriverList);
    }*/

    /*@ApiOperation("新增")
    @Log(title = "新增驾驶员档案",businessType = BusinessType.INSERT)
    @PostMapping("/A24Add")
    public AjaxResult A24Add(@Validated @RequestBody DDriverAe dDriverAe){
        List<DDriver> dDriverList = driverService.selectDDriverList(new DDriver());
        for (DDriver dDriver:dDriverList){
            if (dDriver.getIdCard().equals(dDriverAe.getIdCard())){
                return AjaxResult.error("该身份证号码已存在");
            }
        }
        LoginUser userInfo = tokenService.getLoginUser();
        DDriver dDriver = new DDriver();
        dDriver.setCreateUser(userInfo.getUsername());
        dDriver.setCreateTime(new Date());
        dDriver.setName(dDriverAe.getName());
        dDriver.setSex(dDriverAe.getSex());
        dDriver.setIdCard(dDriverAe.getIdCard());
        dDriver.setContact(dDriverAe.getContact());
        return toAjax(driverService.updateDDriver(dDriver));
    }*/

    /*@Log(title = "删除驾驶员档案",businessType = BusinessType.CLEAN)
    @PostMapping("/A24Delete/{id}")
    public AjaxResult A24Delete(@PathVariable("id")String id){
        return toAjax(driverService.deleteDDriverById(id));
    }*/

    /*@Log(title = "重置密码",businessType = BusinessType.RESETPASSWORD)
    @PostMapping("/A24ResetPassword")*/


    @Log(title = "查看驾驶员档案信息")
    @PostMapping("/A24Info/{id}")
    public AjaxResult A24Info(@PathVariable("id")String id){
        DDriver dDriver = driverService.selectDDriverById(id);
        return AjaxResult.success(dDriver);
    }

    /**
     * 驾驶员教育经历记录列表
     */
    @ApiOperation("驾驶员教育经历记录列表")
    @Log(title = "驾驶员教育经历记录列表",businessType = BusinessType.OTHER)
    @PostMapping("/A25DriverEducationList/{id}")
    public AjaxResult A11DriverEducationList(@PathVariable("id")String id){
        List<DDriverEducationRecord> dDriverEducationRecordList = driverEducationRecordService.selectDDriverEducationRecordByDriverId(id);
        return AjaxResult.success(dDriverEducationRecordList);
    }
    /**
     * 教育经历新增
     */
    @ApiOperation("教育经历新增")
    @Log(title = "驾驶员教育经历新增",businessType = BusinessType.INSERT)
    @PostMapping("/A25DriverEducationAdd")
    public AjaxResult A11DriverEducationAdd(@RequestBody DDriverEducationRecord dDriverEducationRecord) throws ParseException {
        LoginUser userInfo = tokenService.getLoginUser();
        dDriverEducationRecord.setCreateUser(userInfo.getUsername());
        UUID uuid = UUID.randomUUID();
        dDriverEducationRecord.setId(uuid+"");
        dDriverEducationRecord.setCreateTime(new Date());
        dDriverEducationRecord.setDeleteFlag(Long.valueOf(1));
        DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse(dDriverEducationRecord.getEndTime());
        if (date.before(dDriverEducationRecord.getStartTime())){
            return AjaxResult.error("开始时间不能大于结束时间");
        }
        return toAjax(driverEducationRecordService.insertDDriverEducationRecord(dDriverEducationRecord));
    }

    /**
     * 教育经历删除
     */
    @ApiOperation("教育经历删除")
    @Log(title = "驾驶员教育经历删除",businessType = BusinessType.DELETE)
    @PostMapping("/A25DriverEducationDelete/{id}")
    public AjaxResult A11DriverEducationDelete(@PathVariable("id")String id){
        return toAjax(driverEducationRecordService.deleteDDriverEducationRecordById(id));
    }

    /**
     * 重置密码
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:resetPassword")
    @ApiOperation("重置密码")
    @Log(title = "重置密码",businessType = BusinessType.RESETPASSWORD)
    @GetMapping("/A24ResetPassword/{id}")
    public AjaxResult A24ResetPassword(@PathVariable("id")String id){
        DDriver dDriver = driverService.selectDDriverById(id);
        return AjaxResult.success(dDriver);
    }

    /**
     * 保存密码
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:resetPasswordSave")
    @ApiOperation("密码保存")
    @Log(title = "保存密码",businessType = BusinessType.RESETPASSWORD)
    @PostMapping("/A24ResetPasswordSave")
    public AjaxResult A24ResetPasswordSave(@RequestBody PasswordReset passwordReset){
        DDriver dDriver = driverService.selectDDriverById(passwordReset.getId());
        //加密
        dDriver.setPassword(SecurityUtils.encryptPassword(passwordReset.getPassword()));
        //更新人名
        LoginUser userInfo = tokenService.getLoginUser();
        dDriver.setUpdateBy(userInfo.getUsername());
        return toAjax(driverService.updateDDriver(dDriver));
    }

    /***
     * 校验旧密码是否输入正确
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:checkoldpassword")
    @ApiOperation("校验密码")
    @PostMapping("/A24checkOldPassword")
    public AjaxResult A24checkOldPassword(@RequestBody PasswordReset passwordReset){
        DDriver dDriver = driverService.selectDDriverById(passwordReset.getId());
        if (!SecurityUtils.matchesPassword(passwordReset.getPassword(),dDriver.getPassword())){
            return AjaxResult.error("旧密码错误");
        }
        else {
            return AjaxResult.success("校验成功");
        }
    }

    /**
     *启禁用状态
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:disableStatus")
    @ApiOperation("启禁用状态")
    @Log(title = "启禁用状态",businessType = BusinessType.OTHER)
    @GetMapping("/A24DisableStatus/{id}")
    public AjaxResult A24DisableStatus(@PathVariable("id")String id){
        DDriver dDriver = driverService.selectDDriverById(id);
        return AjaxResult.success(dDriver);
    }

    /**
     * 保存状态
     *
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:disableStatusSave")
    @ApiOperation("保存状态")
        @Log(title = "保存状态",businessType = BusinessType.OTHER)
        @PostMapping("/A24DisableStatusSave")
        public AjaxResult A24DisableStatusSave(@RequestBody PasswordReset passwordReset){
            DDriver dDriver = driverService.selectDDriverById(passwordReset.getId());
            dDriver.setDisableStatus(passwordReset.getDisableStatus());
            LoginUser userInfo = tokenService.getLoginUser();
            dDriver.setUpdateBy(userInfo.getUsername());
            return toAjax(driverService.updateDDriver(dDriver));
    }
}
