package com.dlzx.pfserverperson.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.nacos.api.config.filter.IFilterConfig;
import com.dlzx.common.core.constant.Constants;
import com.dlzx.common.core.domain.R;
import com.dlzx.common.core.enums.UserStatus;
import com.dlzx.common.core.exception.BaseException;
import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.utils.VerifyCodeUtil;
import com.dlzx.common.security.service.DriverTokenService;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.common.security.utils.SecurityUtils;
import com.dlzx.pfserverperson.domain.*;
import com.dlzx.pfserverperson.domain.commonDomain.DriverOperating;
import com.dlzx.pfserverperson.domain.commonDomain.WrittenReview;
import com.dlzx.pfserverperson.mapper.SysFileMapper;
import com.dlzx.pfserverperson.service.*;
import com.dlzx.system.api.model.LoginDriver;
import com.dlzx.system.api.model.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
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
 * 驾驶员-司机档案Controller
 * 
 * @author yangluhe
 * @date 2020-11-18
 */
@Api("驾驶员档案")
@RestController
@RequestMapping("/person/driver")
public class DDriverController extends BaseController
{
    @Autowired
    private IDDriverService dDriverService;
    @Autowired
    private IDDriverTypeService driverTypeService;
    @Autowired
    private IDDriverExamService driverExamService;
    @Autowired
    private IDDriverExamProblemService driverExamProblemService;
    @Autowired
    private IDDriverAccidentRecordService driverAccidentRecordService;
    @Autowired
    private IDDriverEducationRecordService driverEducationRecordService;
    @Autowired
    private IDDriverFamilyRecordService driverFamilyRecordService;
    @Autowired
    private IDDriverDriveRecordService driverDriveRecordService;
    @Autowired
    private IDExamPaperProblemService examPaperProblemService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ISysFileService sysFileService;
    @Autowired
    private SysFileMapper sysFileMapper;


    /**
     * App 考勤-驾驶员登录
     */
    @RequestMapping(path = "/driverAuthLogin",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    @Log(title = "驾驶员登录")
    public AjaxResult login(@RequestBody DDriver dDriver)
    {
        return dDriverService.login(dDriver);
    }


    /**
     * @describe App更新用户密码
     * @author DongCL
     * @date 2021-02-23 16:45
     * @param
     * @return
     */
    @RequestMapping(path = "/driverUpdatePassword",method = RequestMethod.POST)
    @ResponseBody
    @Log(title = "App更新用户密码")
    public int driverUpdatePassword(@RequestParam(value = "id",required = false)String id, @RequestParam(value = "encryptPassword",required = false) String encryptPassword)
    {
        return dDriverService.updateDDriverPassword(id,encryptPassword);
    }

    /**
     * @describe App获取驾驶员信息
     * @author DongCL
     * @date 2021-02-23 16:46
     * @param
     * @return
     */
    @RequestMapping(path = "/queryDriverById",method = RequestMethod.POST)
    @ResponseBody
    @Log(title = "App获取驾驶员信息")
    public DDriver queryDriverById(@RequestParam(value = "driverId",required = false) String driverId)
    {
        return dDriverService.selectDDriverById(driverId);
    }


    /**
     * 查询驾驶员-司机档案列表
     */
    @ApiOperation("查询驾驶员-司机档案列表")
    @PreAuthorize(hasPermi = "pfserverperson:driver:list")
    @PostMapping("/A09List")
    public TableDataInfo A09List(@RequestBody DDriver dDriver)
    {
        startPage(dDriver.getPageNum(),dDriver.getPageSize());
        List<DDriver> list = dDriverService.selectDDriverList(dDriver);
        return getDataTable(list);
    }

    /**
     * 查询驾驶员-驾驶员列表
     */
    @ApiOperation("查询驾驶员-驾驶员列表")
    @PreAuthorize(hasPermi = "pfserverperson:driver:passlist")
    @PostMapping("/A24List")
    public TableDataInfo A24List(@RequestBody DDriver dDriver){
        startPage(dDriver.getPageNum(),dDriver.getPageSize());
        List<DDriver> dDriverList = dDriverService.selectDDriverListFile(dDriver);
        return getDataTable(dDriverList);
    }

    @PreAuthorize(hasPermi = "pfserverperson:driver:list")
    @PostMapping("/A19List")
    public TableDataInfo A19List(@RequestBody DDriver dDriver)
    {
        startPage(dDriver.getPageNum(),dDriver.getPageSize());
        List<DDriver> list = dDriverService.selectDDriverListQi(dDriver);
        return getDataTable(list);
    }

    @PreAuthorize(hasPermi = "pfserverperson:driver:list")
    @PostMapping("/A19ListTong")
    public TableDataInfo A19ListTong(@RequestBody DDriver dDriver)
    {

        List<DDriver> list = dDriverService.selectDDriverListTong(dDriver);
        return getDataTable(list);
    }

    @PreAuthorize(hasPermi = "pfserverperson:driver:list")
    @PostMapping("/A19ListZhong")
    public TableDataInfo A19ListZhong(@RequestBody DDriver dDriver)
    {
        startPage(dDriver.getPageNum(),dDriver.getPageSize());
        List<DDriver> list = dDriverService.selectDDriverListZhong(dDriver);
        return getDataTable(list);
    }

    /**
     * 导出驾驶员-司机档案列表
     */
    @PreAuthorize(hasPermi = "system:driver:export")
    @Log(title = "驾驶员-司机档案", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DDriver dDriver) throws IOException
    {
        List<DDriver> list = dDriverService.selectDDriverList(dDriver);
        ExcelUtil<DDriver> util = new ExcelUtil<DDriver>(DDriver.class);
        util.exportExcel(response, list, "driver");
    }

    /**
     * 获取驾驶员-司机档案详细信息
     */

    @PreAuthorize(hasPermi = "pfserverperson:driver:info")
    @GetMapping(value = "/A11Info/{id}")
    public AjaxResult A11Info(@PathVariable("id") String id)
    {
        return AjaxResult.success(dDriverService.selectDDriverById(id));
    }

    /**
     * 获取登陆人驾驶员信息（消息推送校验）
     */
    @GetMapping(value = "/DriverId/{name}")
    public DDriver DriverId(@PathVariable("name") String name)
    {
        return dDriverService.selectDDriverId(name);
    }

    /**
     * 获取登陆人驾驶员信息（消息推送校验）
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:info")
    @GetMapping(value = "/DriverInfo/{id}")
    public DDriver DriverInfo(@PathVariable("id") String id)
    {
        return dDriverService.selectDDriverById(id);
    }


    /**
     * 新增驾驶员-司机档案
     */
    @ApiOperation("新增驾驶员-司机档案")
    @PreAuthorize(hasPermi = "pfserverperson:driver:add")
    @Log(title = "驾驶员-司机档案", businessType = BusinessType.INSERT)
    @PostMapping("/A09Add")
    public AjaxResult A09Add(@Validated @RequestBody DDriver dDriver)
    {
        if (dDriver.getName()==null||dDriver.getName().equals("")){
            return AjaxResult.error("姓名不能为空");
        }
//        if (StringUtils.isEmpty(dDriver.getQualification())){
//            return AjaxResult.error("从业资格证不能为空");
//        }
        //校验身份证号
        int checkIdCard = dDriverService.selectDDriverByIdCard(dDriver.getIdCard());
        if (checkIdCard>0){
            return AjaxResult.error("该身份证号已存在");
        }
        //校验手机号
        int checkIdConctact = dDriverService.selectDDriverByIdContact(dDriver.getContact());
        if (checkIdConctact>0){
            return AjaxResult.error("该手机号已存在");
        }
        //获取当前用户信息
        LoginUser userInfo = tokenService.getLoginUser();
        dDriver.setCreateBy(userInfo.getUsername());
        UUID uuid = UUID.randomUUID();
        dDriver.setId(uuid.toString());
        dDriver.setDeleteFlag(1);
        dDriver.setStatus(Long.valueOf(1));
        dDriver.setInterviewStatus(Long.valueOf(1));
        dDriver.setExamStatus(Long.valueOf(1));
        dDriver.setDriveStatus(Long.valueOf(1));
        dDriver.setDisableStatus(Long.valueOf(1));
        dDriver.setPassword(SecurityUtils.encryptPassword("123456"));

        return toAjax(dDriverService.insertDDriver(dDriver));
    }

    /**
     * 修改驾驶员-司机档案
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:edit")
    @ApiOperation("修改驾驶员-司机档案")
    @GetMapping(value = "/A10Edit/{id}")
    public AjaxResult A10Edit(@PathVariable("id")String id){
        return AjaxResult.success(dDriverService.selectDDriverById(id));
    }


    /**
     * 修改保存驾驶员-司机档案
     */
    @ApiOperation("修改保存驾驶员-司机档案")
    @PreAuthorize(hasPermi = "pfserverperson:driver:edit")
    @Log(title = "修改保存驾驶员-司机档案", businessType = BusinessType.UPDATE)
    @PostMapping("/A10EditSave")
    public AjaxResult A10EditSave(@Validated @RequestBody DDriver dDriver)
    {
        if (dDriver.getName()==null||dDriver.getName().equals("")){
            return AjaxResult.error("姓名不能为空");
        }
//        if (StringUtils.isEmpty(dDriver.getQualification())){
//            return AjaxResult.error("从业资格证不能为空");
//        }
        DDriver dDriverOne = dDriverService.selectDDriverById(dDriver.getId());
        String idcheck = dDriver.getIdCard();
        Boolean check = dDriverOne.getIdCard().equals(idcheck);
        if (check == false){
            int checkIdCard = dDriverService.selectDDriverByIdCard(dDriver.getIdCard());
            if (checkIdCard>0){
                return AjaxResult.error("该身份证号已存在");
            }
        }
        dDriverOne.setName(dDriver.getName());
        dDriverOne.setSex(dDriver.getSex());
        dDriverOne.setBirthday(dDriver.getBirthday());
        dDriverOne.setAddress(dDriver.getAddress());
        dDriverOne.setIdCard(dDriver.getIdCard());
        dDriverOne.setDntryTime(dDriver.getDntryTime());
        dDriverOne.setNation(dDriver.getNation());
        dDriverOne.setPoliticalAffiliation(dDriver.getPoliticalAffiliation());
        dDriverOne.setMaritalStatus(dDriver.getMaritalStatus());
        dDriverOne.setEducation(dDriver.getEducation());
        dDriverOne.setContact(dDriver.getContact());
        dDriverOne.setUrgentTel(dDriver.getUrgentTel());
        dDriverOne.setDrivingLicenseTime(dDriver.getDrivingLicenseTime());
        dDriverOne.setDrivingLicenseType(dDriver.getDrivingLicenseType());
        dDriverOne.setDriveAge(dDriver.getDriveAge());
        dDriverOne.setDriverTypeId(dDriver.getDriverTypeId());
        dDriverOne.setStaffType(dDriver.getStaffType());
        dDriverOne.setQualification(dDriver.getQualification());
        LoginUser userInfo = tokenService.getLoginUser();
        dDriverOne.setUpdateBy(userInfo.getUsername());
        dDriverOne.setDriverTypeId(dDriver.getDriverTypeId());
        dDriverOne.setDriverTypeName(dDriver.getDriverTypeName());
        dDriverOne.setDriverPhoto(dDriver.getDriverPhoto());
        dDriverOne.setPerformanceType(dDriver.getPerformanceType());
        return toAjax(dDriverService.updateOTMDriver(dDriverOne));
    }

    /**
     * 删除驾驶员-司机档案
     */
    @ApiOperation("删除驾驶员-司机档案")
    @PreAuthorize(hasPermi = "pfserverperson:driver:delete")
    @Log(title = "驾驶员-司机档案", businessType = BusinessType.DELETE)
	@GetMapping(value = "/A09Delete/{id}")
    public AjaxResult A09Delete(@PathVariable("id")String id)
    {
        DDriver dDriver = dDriverService.selectDDriverById(id);
        dDriver.setDeleteFlag(2);
        return toAjax(dDriverService.updateDDriver(dDriver));
    }



    /**
     * 面试记录
     */
    @ApiOperation("面试记录")
    @PostMapping("/A12Interview/{id}")
    @Log(title = "面试记录",businessType = BusinessType.OTHER)
    public AjaxResult A12Interview(@PathVariable("id")String id){
        ModelMap mmap = new ModelMap();
        DDriver dDriver = dDriverService.selectDDriverById(id);
        mmap.put("interview",dDriver.getInterviewRecord());
        mmap.put("status",dDriver.getInterviewStatus());
        mmap.put("reason",dDriver.getInterviewRemarks());
        return AjaxResult.success(mmap);
    }

    /**
     * 实操记录
     */
    @ApiOperation("实操记录")
    @PostMapping("/A14Practice/{id}")
    @Log(title = "实操记录",businessType = BusinessType.OTHER)
    public AjaxResult A14Practice(@PathVariable("id")String id){
        ModelMap mmap = new ModelMap();
        DDriver dDriver = dDriverService.selectDDriverById(id);
        mmap.put("driveStraight",dDriver.getDriveStraightDrivingStatus());
        mmap.put("driveParking",dDriver.getDriveParkingStatus());
        mmap.put("driveEmergency",dDriver.getDriveEmergencyStatus());
        if (dDriver.getDriveEmergencyStatus()==2){
            mmap.put("reason",dDriver.getDriveEmergencyRemarks());
        }
        return AjaxResult.success(mmap);
    }

    /**
     * 笔试记录
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:info")
    @ApiOperation("笔试记录")
    @Log(title = "笔试记录",businessType = BusinessType.OTHER)
    @GetMapping(value = "/A13WrittenExamination/{id}")
    public AjaxResult A13WrittenExamination(@PathVariable("id")String id){
        ModelMap mmap = new ModelMap();
        DDriverExam dDriverExam = driverExamService.selectDDriverExamByDriverId(id);
        if (null == dDriverExam){
            return AjaxResult.success("该驾驶员无笔试记录");
        }
        if (dDriverExam.getStatus() == 1) {
            String examId = dDriverExam.getId();
            List<DDriverExamProblem> dDriverExamProblemRadio = new ArrayList<>();
            List<DDriverExamProblem> dDriverExamProblemCheckbox = new ArrayList<>();
            List<DDriverExamProblem> dDriverExamProblemJudgment = new ArrayList<>();
            List<String> trueAnswer = new ArrayList<>();
            List<String> answer = new ArrayList<>();
            List<DDriverExamProblem> dDriverExamProblem = driverExamProblemService.selectDDriverExamProblemByExamId(examId);
            for (DDriverExamProblem dDriverExamProblem1 : dDriverExamProblem) {
                if (dDriverExamProblem1.getType() == Long.valueOf(1)) {
                    dDriverExamProblemRadio.add(dDriverExamProblem1);
                }
                if (dDriverExamProblem1.getType() == Long.valueOf(2)) {
                    dDriverExamProblemCheckbox.add(dDriverExamProblem1);
                }
                if (dDriverExamProblem1.getType() == Long.valueOf(3)) {
                    dDriverExamProblemJudgment.add(dDriverExamProblem1);
                }
                trueAnswer.add(dDriverExamProblem1.getTrueAnswer());
                answer.add(dDriverExamProblem1.getAnswer());
            }
            Long totalSellerDan = dDriverExamProblem.stream().filter(m -> m.getType() == 1).map(DDriverExamProblem::getScore).reduce(new Long(0), Long::sum);
            Long totalSellerDuo = dDriverExamProblem.stream().filter(m -> m.getType() == 2).map(DDriverExamProblem::getScore).reduce(new Long(0), Long::sum);
            Long totalSellerPan = dDriverExamProblem.stream().filter(m -> m.getType() == 3).map(DDriverExamProblem::getScore).reduce(new Long(0), Long::sum);
            Long scoreHe = totalSellerDan + totalSellerDuo + totalSellerPan;
            // 单选
            mmap.put("radio", dDriverExamProblemRadio);
            // 多选
            mmap.put("checkBox", dDriverExamProblemCheckbox);
            // 判断
            mmap.put("judgment", dDriverExamProblemJudgment);
            mmap.put("radioScore", totalSellerPan);
            mmap.put("checkScore", totalSellerDan);
            mmap.put("judgmentScore", totalSellerDuo);
            mmap.put("trueAnswer", trueAnswer);
            mmap.put("answer", answer);
            mmap.put("total", scoreHe);
            DDriver dDriver = dDriverService.selectDDriverById(id);
            mmap.put("reviewStatus", dDriver.getExamStatus());
            mmap.put("reviewRemarks", dDriver.getExamRemarks());
        } else {
            String paperId = dDriverExam.getPaperId();
            List<DExamPaperProblem> examPaperProblemRadio = new ArrayList<>();
            List<DExamPaperProblem> examPaperProblemCheckBox = new ArrayList<>();
            List<DExamPaperProblem> examPaperProblemJudgment = new ArrayList<>();
            List<String> trueAnswer = new ArrayList<>();
            List<String> answer = new ArrayList<>();
            List<DExamPaperProblem> examPaperProblemList = examPaperProblemService.selectDExamPaperProblemByPaperId(paperId);
            for (DExamPaperProblem dExamPaperProblem : examPaperProblemList) {
                // update by 4.26 examPaperProblem 和  driverExamProblem 的 answer字段意思不一样
                dExamPaperProblem.setTrueAnswer(dExamPaperProblem.getAnswer());
                dExamPaperProblem.setAnswer(null);
                if (dExamPaperProblem.getType() == Long.valueOf(1)) {
                    examPaperProblemRadio.add(dExamPaperProblem);
                }
                if (dExamPaperProblem.getType() == Long.valueOf(2)) {
                    examPaperProblemCheckBox.add(dExamPaperProblem);
                }
                if (dExamPaperProblem.getType() == Long.valueOf(3)) {
                    examPaperProblemJudgment.add(dExamPaperProblem);
                }
                trueAnswer.add(dExamPaperProblem.getAnswer());
            }
            mmap.put("trueAnswer", trueAnswer);
            mmap.put("radio", examPaperProblemRadio);
            mmap.put("checkBox", examPaperProblemCheckBox);
            mmap.put("judgment", examPaperProblemJudgment);
            DDriver dDriver = dDriverService.selectDDriverById(id);
            mmap.put("reviewStatus", dDriver.getExamStatus());
            mmap.put("reviewRemarks", dDriver.getExamRemarks());
        }
        return AjaxResult.success(mmap);
    }

    /**
     * 驾驶员事故记录
     * @return
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:info")
    @ApiOperation("驾驶员事故记录")
    @Log(title = "驾驶员事故记录",businessType = BusinessType.OTHER)
    @GetMapping(value = "/A11DriverAccidentList/{id}")
    public AjaxResult A11DriverAccidentList(@PathVariable("id")String id){
        List<DDriverAccidentRecord> dDriverAccidentRecordList = driverAccidentRecordService.selectDDriverAccidentRecordByDriverId(id);
        return AjaxResult.success(dDriverAccidentRecordList);
    }

    /**
     * 驾驶员事故模块新增
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:driverAccidentAdd")
    @ApiOperation("驾驶员事故模块新增")
    @Log(title = "新增驾驶员事故记录",businessType = BusinessType.INSERT)
    @PostMapping("/A11DriverAccidentAdd")
    public AjaxResult A11DriverAccidentAdd(@Validated @RequestBody DDriverAccidentRecord dDriverAccidentRecord){

        if (StringUtils.isNull(dDriverAccidentRecord.getAccidentTime())){
            return AjaxResult.error("事故事件不能为空");
        }
        if (StringUtils.isEmpty(dDriverAccidentRecord.getPlace())){
            return AjaxResult.error("事故地点不能为空");
        }
        if (StringUtils.isEmpty(dDriverAccidentRecord.getRemarks())){
            return AjaxResult.error("详细说明不能为空");
        }

        //获取当前登录用户信息
        LoginUser userInfo = tokenService.getLoginUser();
        dDriverAccidentRecord.setCreateBy(userInfo.getUsername());
        UUID uuid = UUID.randomUUID();
        dDriverAccidentRecord.setId(uuid.toString());
        dDriverAccidentRecord.setDeleteFlag(Long.valueOf(1));
        return toAjax(driverAccidentRecordService.insertDDriverAccidentRecord(dDriverAccidentRecord));
    }

    /**
     * 驾驶员事故记录删除
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:driverAccidentDelete")
    @ApiOperation("驾驶员事故记录删除")
    @Log(title = "驾驶员事故记录删除",businessType = BusinessType.DELETE)
    @GetMapping(value = "/A11DriverAccidentDelete/{id}")
    public AjaxResult A11DriverAccidentDelete(@PathVariable("id")String id){
        DDriverAccidentRecord dDriverAccidentRecord = driverAccidentRecordService.selectDDriverAccidentRecordById(id);
        dDriverAccidentRecord.setDeleteFlag(Long.valueOf(2));

        return toAjax(driverAccidentRecordService.updateDDriverAccidentRecord(dDriverAccidentRecord));
    }

    /**
     *驾驶员事故记录编辑
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:driverAccidentEdit")
    @ApiOperation("驾驶员事故记录编辑")
    @Log(title = "驾驶员事故记录编辑",businessType = BusinessType.UPDATE)
    @PostMapping("/A11DriverAccidentEdit/{id}")
    public AjaxResult A11DriverAccidentEdit(@PathVariable("id")String id){
        return AjaxResult.success(driverAccidentRecordService.selectDDriverAccidentRecordById(id));
    }

    /**
     * 驾驶员事故记录编辑保存
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:driverAccidentEdit")
    @ApiOperation("驶员事故记录编辑保存")
    @Log(title = "驾驶员事故记录编辑保存",businessType = BusinessType.UPDATE)
    @PostMapping("/A11DriverAccidentEditSave")
    public AjaxResult A11DriverAccidentEditSave(@RequestBody DDriverAccidentRecord dDriverAccidentRecord){

        if (StringUtils.isNull(dDriverAccidentRecord.getAccidentTime())){
            return AjaxResult.error("事故事件不能为空");
        }
        if (StringUtils.isEmpty(dDriverAccidentRecord.getPlace())){
            return AjaxResult.error("事故地点不能为空");
        }
        if (StringUtils.isEmpty(dDriverAccidentRecord.getRemarks())){
            return AjaxResult.error("详细说明不能为空");
        }

        LoginUser userInfo = tokenService.getLoginUser();
        dDriverAccidentRecord.setUpdateBy(userInfo.getUsername());
        return toAjax(driverAccidentRecordService.updateDDriverAccidentRecord(dDriverAccidentRecord));
    }

    /**
     * 驾驶员教育经历记录列表
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:info")
    @ApiOperation("驾驶员教育经历记录列表")
    @Log(title = "驾驶员教育经历记录列表",businessType = BusinessType.OTHER)
    @GetMapping(value = "/A11DriverEducationList/{id}")
    public AjaxResult A11DriverEducationList(@PathVariable("id")String id){
        List<DDriverEducationRecord> dDriverEducationRecordList = driverEducationRecordService.selectDDriverEducationRecordByDriverId(id);
        return AjaxResult.success(dDriverEducationRecordList);
    }

    /**
     * 教育经历新增
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:driverEducationAdd")
    @ApiOperation("教育经历新增")
    @Log(title = "驾驶员教育经历新增",businessType = BusinessType.INSERT)
    @PostMapping("/A11DriverEducationAdd")
    public AjaxResult A11DriverEducationAdd(@RequestBody DDriverEducationRecord dDriverEducationRecord) throws ParseException {

        if (StringUtils.isEmpty(dDriverEducationRecord.getSchool())){
            return AjaxResult.error("就读学校不能为空");
        }
        if (StringUtils.isNull(dDriverEducationRecord.getStartTime())){
            return AjaxResult.error("开始时间不能为空");
        }
        if (StringUtils.isNull(dDriverEducationRecord.getFinshTime())){
            return AjaxResult.error("结束时间不能为空");
        }
        //获取当前登录信息
        LoginUser userInfo = tokenService.getLoginUser();
        dDriverEducationRecord.setCreateBy(userInfo.getUsername());
        UUID uuid = UUID.randomUUID();
        dDriverEducationRecord.setId(uuid.toString());
        dDriverEducationRecord.setCreateTime(new Date());
        dDriverEducationRecord.setDeleteFlag(Long.valueOf(1));
        if (dDriverEducationRecord.getFinshTime().before(dDriverEducationRecord.getStartTime())){
            return AjaxResult.error("开始时间不能大于结束时间");
        }
        return toAjax(driverEducationRecordService.insertDDriverEducationRecord(dDriverEducationRecord));
    }

    /**
     * 教育经历删除
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:driverEducationDelete")
    @ApiOperation("教育经历删除")
    @Log(title = "驾驶员教育经历删除",businessType = BusinessType.DELETE)
    @GetMapping(value = "/A11DriverEducationDelete/{id}")
    public AjaxResult A11DriverEducationDelete(@PathVariable("id")String id){
        DDriverEducationRecord dDriverEducationRecord = driverEducationRecordService.selectDDriverEducationRecordById(id);
        dDriverEducationRecord.setDeleteFlag(Long.valueOf(2));
        return toAjax(driverEducationRecordService.updateDDriverEducationRecord(dDriverEducationRecord));
    }

    /**
     * 教育经历编辑渲染
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:driverEducationEdit")
    @ApiOperation("教育经历编辑渲染")
    @Log(title = "驾驶员教育经历编辑渲染",businessType = BusinessType.UPDATE)
    @PostMapping("/A11DriverEducationEdit/{id}")
    public AjaxResult A11DriverEducationEdit(@PathVariable("id")String id){
        return AjaxResult.success(driverEducationRecordService.selectDDriverEducationRecordById(id));
    }

    /**
     * 教育经历编辑保存
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:driverEducationEdit")
    @ApiOperation("教育经历编辑保存")
    @Log(title = "驾驶员教育经历编辑保存",businessType = BusinessType.UPDATE)
    @PostMapping("/A11DriverEducationEditSave")
    public AjaxResult A11DriverEducationEditSave(@RequestBody DDriverEducationRecord dDriverEducationRecord) throws ParseException {

        if (StringUtils.isEmpty(dDriverEducationRecord.getSchool())){
            return AjaxResult.error("就读学校不能为空");
        }
        if (StringUtils.isNull(dDriverEducationRecord.getStartTime())){
            return AjaxResult.error("开始时间不能为空");
        }
        if (StringUtils.isNull(dDriverEducationRecord.getFinshTime())){
            return AjaxResult.error("结束时间不能为空");
        }

        LoginUser userInfo = tokenService.getLoginUser();
        dDriverEducationRecord.setUpdateTime(new Date());
        dDriverEducationRecord.setUpdateBy(userInfo.getUsername());
        if (dDriverEducationRecord.getFinshTime().before(dDriverEducationRecord.getStartTime())){
            return AjaxResult.error("开始时间不能大于结束时间");
        }
        return toAjax(driverEducationRecordService.updateDDriverEducationRecord(dDriverEducationRecord));
    }

    /**
     * 家庭经历列表
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:info")
    @ApiOperation("家庭经历列表")
    @Log(title = "家庭状况列表",businessType = BusinessType.OTHER)
    @GetMapping(value = "/A11DriverFamilyList/{id}")
    public AjaxResult A11DriverFamilyList(@PathVariable("id")String id){
        List<DDriverFamilyRecord> dDriverFamilyRecordList = driverFamilyRecordService.selectDDriverFamilyRecordByDriverId(id);
        return AjaxResult.success(dDriverFamilyRecordList);
    }

    /**
     * 新增家庭经历
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:driverFamilyAdd")
    @ApiOperation("新增家庭经历")
    @Log(title = "新增家庭记录",businessType = BusinessType.INSERT)
    @PostMapping("/A11DriverFamilyAdd")
    public AjaxResult A11DriverFamilyAdd(@RequestBody DDriverFamilyRecord dDriverFamilyRecord){

        if (StringUtils.isEmpty(dDriverFamilyRecord.getRelation())){
            return AjaxResult.error("关系不能为空");
        }
        if (StringUtils.isEmpty(dDriverFamilyRecord.getName())){
            return AjaxResult.error("姓名不能为空");
        }
        if (StringUtils.isEmpty(dDriverFamilyRecord.getTel())){
            return AjaxResult.error("联系方式不能为空");
        }
        if (StringUtils.isEmpty(dDriverFamilyRecord.getPosition())){
            return AjaxResult.error("职务不能为空");
        }
        if (StringUtils.isEmpty(dDriverFamilyRecord.getWorkUnit())){
            return AjaxResult.error("工作单位不能为空");
        }

        //获取档期那用户登录信息
        LoginUser userInfo = tokenService.getLoginUser();
        dDriverFamilyRecord.setCreateBy(userInfo.getUsername());
        UUID uuid = UUID.randomUUID();
        dDriverFamilyRecord.setId(uuid.toString());
        dDriverFamilyRecord.setDeleteFlag(Long.valueOf(1));
        return toAjax(driverFamilyRecordService.insertDDriverFamilyRecord(dDriverFamilyRecord));
    }

    /**
     * 编辑家庭经历渲染
     *
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:driverFamilyEdit")
    @ApiOperation("编辑家庭经历渲染")
    @Log(title = "编辑家庭记录",businessType = BusinessType.UPDATE)
    @PostMapping("/A11DriverFamilyEdit/{id}")
    public AjaxResult A11DriverFamilyEdit(@PathVariable("id")String id){
        DDriverFamilyRecord dDriverFamilyRecord = driverFamilyRecordService.selectDDriverFamilyRecordById(id);
        return AjaxResult.success(dDriverFamilyRecord);
    }

    /**
     * 编辑保存
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:driverFamilyEdit")
    @ApiOperation("编辑保存家庭记录")
    @Log(title = "编辑保存",businessType = BusinessType.UPDATE)
    @PostMapping("/A11DriverFamilyEditSave")
    public AjaxResult A11DriverFamilyEditSave(@RequestBody DDriverFamilyRecord dDriverFamilyRecord){
        if (StringUtils.isEmpty(dDriverFamilyRecord.getRelation())){
            return AjaxResult.error("关系不能为空");
        }
        if (StringUtils.isEmpty(dDriverFamilyRecord.getName())){
            return AjaxResult.error("姓名不能为空");
        }
        if (StringUtils.isEmpty(dDriverFamilyRecord.getTel())){
            return AjaxResult.error("联系方式不能为空");
        }
        if (StringUtils.isEmpty(dDriverFamilyRecord.getPosition())){
            return AjaxResult.error("职务不能为空");
        }
        if (StringUtils.isEmpty(dDriverFamilyRecord.getWorkUnit())){
            return AjaxResult.error("工作单位不能为空");
        }

        //获取当前信息
        LoginUser userInfo = tokenService.getLoginUser();
        dDriverFamilyRecord.setUpdateBy(userInfo.getUsername());
        return toAjax(driverFamilyRecordService.updateDDriverFamilyRecord(dDriverFamilyRecord));
    }

    /**
     * 删除
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:driverFamilyDelete")
    @ApiOperation("删除家庭记录")
    @Log(title = "删除",businessType = BusinessType.DELETE)
    @GetMapping(value = "/A11DriverFamilyDelete/{id}")
    public AjaxResult A11DriverFamilyDelete(@PathVariable("id")String id){
        DDriverFamilyRecord dDriverFamilyRecord = driverFamilyRecordService.selectDDriverFamilyRecordById(id);
        //设置删除标识
        dDriverFamilyRecord.setDeleteFlag(Long.valueOf(2));
        return toAjax(driverFamilyRecordService.updateDDriverFamilyRecord(dDriverFamilyRecord));
    }

    /**
     * 驾龄列表
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:info")
    @ApiOperation("驾龄列表")
    @Log(title = "驾龄列表",businessType = BusinessType.OTHER)
    @GetMapping(value = "/A11DriverDriverList/{id}")
    public AjaxResult A11DriverDriverList(@PathVariable("id")String id){
        List<DDriverDriveRecord> dDriverDriveRecords = driverDriveRecordService.selectDDriverDriverRecordByDriverId(id);
        return AjaxResult.success(dDriverDriveRecords);
    }

    /**
     * insert
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:driverDriverAdd")
    @ApiOperation("新增驾龄")
    @PostMapping("/A11DriverDriverAdd")
    public AjaxResult A11DriverDriverAdd(@RequestBody DDriverDriveRecord dDriverDriveRecord) throws ParseException {
        if (StringUtils.isEmpty(dDriverDriveRecord.getWorkUnit())){
            return AjaxResult.error("工作单位不能为空");
        }
        if (StringUtils.isEmpty(dDriverDriveRecord.getPosition())){
            return AjaxResult.error("职务不能为空");
        }
        if (StringUtils.isNull(dDriverDriveRecord.getStartTime())){
            return AjaxResult.error("入职时间不能为空");
        }
        if (StringUtils.isNull(dDriverDriveRecord.getFinshTime())){
            return AjaxResult.error("离职时间不能为空");
        }
        if (StringUtils.isNull(dDriverDriveRecord.getRoute())){
            return AjaxResult.error("驾驶线路不能为空");
        }
        UUID uuid = UUID.randomUUID();
        dDriverDriveRecord.setId(uuid.toString());
        //获取当前用户信息
        LoginUser userInfo = tokenService.getLoginUser();
        dDriverDriveRecord.setCreateBy(userInfo.getUsername());
        /*DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse(dDriverDriveRecord.getEndTime());*/
        if (dDriverDriveRecord.getFinshTime().before(dDriverDriveRecord.getStartTime())){
            return AjaxResult.error("开始时间不能大于结束时间");
        }
        dDriverDriveRecord.setCreateTime(new Date());
        dDriverDriveRecord.setDeleteFlag(Long.valueOf(1));
        return toAjax(driverDriveRecordService.insertDDriverDriveRecord(dDriverDriveRecord));
    }

    /**
     * update
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:driverDriverEdit")
    @ApiOperation("编辑驾龄")
    @PostMapping("/A11DriverDriverEdit/{id}")
    public AjaxResult A11DriverDriverEdit(@PathVariable("id")String id){
        DDriverDriveRecord dDriverDriveRecord = driverDriveRecordService.selectDDriverDriveRecordById(id);
        return AjaxResult.success(dDriverDriveRecord);
    }

    /**
     * updateSave
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:driverDriverEdit")
    @ApiOperation("保存驾龄")
    @PostMapping("/A11DriverDriverEditSave")
    public AjaxResult A11DriverDriverEditSave(@RequestBody DDriverDriveRecord dDriverDriveRecord) throws ParseException {

        if (StringUtils.isEmpty(dDriverDriveRecord.getWorkUnit())){
            return AjaxResult.error("工作单位不能为空");
        }
        if (StringUtils.isEmpty(dDriverDriveRecord.getPosition())){
            return AjaxResult.error("职务不能为空");
        }
        if (StringUtils.isNull(dDriverDriveRecord.getStartTime())){
            return AjaxResult.error("入职时间不能为空");
        }
        if (StringUtils.isNull(dDriverDriveRecord.getFinshTime())){
            return AjaxResult.error("离职时间不能为空");
        }
        if (StringUtils.isNull(dDriverDriveRecord.getRoute())){
            return AjaxResult.error("驾驶线路不能为空");
        }

        LoginUser userInfo = tokenService.getLoginUser();
        dDriverDriveRecord.setUpdateBy(userInfo.getUsername());
        dDriverDriveRecord.setUpdateTime(new Date());
        /*DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse(dDriverDriveRecord.getEndTime());*/
        if (dDriverDriveRecord.getFinshTime().before(dDriverDriveRecord.getStartTime())){
            return AjaxResult.error("开始时间不能大于结束时间");
        }
        return toAjax(driverDriveRecordService.updateDDriverDriveRecord(dDriverDriveRecord));
    }

    /**
     * delete
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:driverDriverDelete")
    @ApiOperation("删除驾龄")
    @GetMapping(value = "/A11DriverDriverDelete/{id}")
    public AjaxResult A11DriverDriverDelete(@PathVariable("id")String id){
        DDriverDriveRecord dDriverDriveRecord = driverDriveRecordService.selectDDriverDriveRecordById(id);
        dDriverDriveRecord.setDeleteFlag(Long.valueOf(2));
        return toAjax(driverDriveRecordService.updateDDriverDriveRecord(dDriverDriveRecord));
    }

    /**
     * 面试审核
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:interviewReview")
    @ApiOperation("面试审核")
    @PostMapping("/A15InterviewReview")
    public AjaxResult A15InterviewReview(@RequestBody CommonExamination commonExamination){
        DDriver dDriver = dDriverService.selectDDriverById(commonExamination.getId());
        dDriver.setInterviewRecord(commonExamination.getInterviewRecord());
        dDriver.setInterviewStatus(commonExamination.getInterviewStatus());
        dDriver.setInterviewRemarks(commonExamination.getInterviewRemarks());
        LoginUser userInfo = tokenService.getLoginUser();
        dDriver.setUpdateBy(userInfo.getUsername());
        if (commonExamination.getInterviewStatus()==2){
            dDriver.setStatus(Long.valueOf(2));
        }
        if (commonExamination.getInterviewStatus()==4){
            dDriver.setStatus(Long.valueOf(4));
        }
        if (commonExamination.getInterviewStatus() == 3 && dDriver.getExamStatus() == 3 && dDriver.getDriveStatus() == 3){
            dDriver.setStatus(Long.valueOf(4));
        }
        return toAjax(dDriverService.updateDDriver(dDriver));
    }

    /**
     * 笔试审核
     */

    @ApiOperation("笔试审核")
    @PostMapping("/A16WrittenReview/{id}")
    public AjaxResult A16WrittenReview(@PathVariable("id")String id){
        ModelMap mmap = new ModelMap();
        DDriverExam dDriverExam = driverExamService.selectDDriverExamByDriverId(id);
        String examId = dDriverExam.getId();
        List<DDriverExamProblem> dDriverExamProblemRadio = new ArrayList<>();
        List<DDriverExamProblem> dDriverExamProblemCheckbox = new ArrayList<>();
        List<DDriverExamProblem> dDriverExamProblemJudgment = new ArrayList<>();
        List<DDriverExamProblem> dDriverExamProblem = driverExamProblemService.selectDDriverExamProblemByExamId(examId);
        for (DDriverExamProblem dDriverExamProblem1:dDriverExamProblem){
            if(dDriverExamProblem1.getType()==Long.valueOf(1)){
                dDriverExamProblemRadio.add(dDriverExamProblem1);
            }
            if (dDriverExamProblem1.getType()==Long.valueOf(2)){
                dDriverExamProblemCheckbox.add(dDriverExamProblem1);
            }
            if (dDriverExamProblem1.getType()==Long.valueOf(3)){
                dDriverExamProblemJudgment.add(dDriverExamProblem1);
            }
        }
        mmap.put("radio",dDriverExamProblemRadio);
        mmap.put("checkBox",dDriverExamProblemCheckbox);
        mmap.put("judgment",dDriverExamProblemJudgment);
        mmap.put("radioScore",dDriverExam.getRadioScore());
        mmap.put("checkScore",dDriverExam.getCheckboxScore());
        mmap.put("judgmentScore",dDriverExam.getJudgmentScore());
        return AjaxResult.success(mmap);
    }

    /**
     * 笔试审核保存
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:writtenReview")
    @ApiOperation("笔试审核保存")
    @PostMapping("/A16WrittenReviewSave")
    public AjaxResult A16WrittenReview(@RequestBody WrittenReview writtenReview){
        DDriver dDriver = dDriverService.selectDDriverById(writtenReview.getId());
        LoginUser userInfo = tokenService.getLoginUser();
        dDriver.setUpdateBy(userInfo.getUsername());
        dDriver.setUpdateTime(new Date());
        dDriver.setExamStatus(writtenReview.getExamStatus());
        dDriver.setExamRemarks(writtenReview.getExamRemarks());
        if (writtenReview.getExamStatus()==2){
            dDriver.setStatus(Long.valueOf(2));
        }
        if (writtenReview.getExamStatus()==4){
            dDriver.setStatus(Long.valueOf(4));
        }
        if(writtenReview.getExamStatus() == 3 && dDriver.getInterviewStatus() == 3 && dDriver.getDriveStatus() == 3){
            dDriver.setStatus(Long.valueOf(4));
        }
        return toAjax(dDriverService.updateDDriver(dDriver));
    }

    /**
     * 实操审核
     */
    @PreAuthorize(hasPermi = "pfserverperson:driver:practiceReview")
    @ApiOperation("实操审核")
    @PostMapping("/A17PracticeReviewSave")
    public AjaxResult A17PracticeReviewSave(@RequestBody DriverOperating driverOperating){
        LoginUser userInfo = tokenService.getLoginUser();
        DDriver dDriver = dDriverService.selectDDriverById(driverOperating.getId());
        dDriver.setUpdateBy(userInfo.getUsername());
        dDriver.setDriveStraightDrivingStatus(driverOperating.getDriveStraightDrivingStatus());
        dDriver.setDriveStraightDrivingRemarks(driverOperating.getDriveStraightDrivingRemarks());
        dDriver.setDriveEmergencyStatus(driverOperating.getDriveEmergencyStatus());
        dDriver.setDriveEmergencyRemarks(driverOperating.getDriveEmergencyRemarks());
        dDriver.setDriveParkingStatus(driverOperating.getDriveParkingStatus());
        dDriver.setDriveParkingRemarks(driverOperating.getDriveParkingRemarks());
        dDriver.setDriveStatus(driverOperating.getDriveStatus());
        dDriver.setDriveRemarks(driverOperating.getDriveRemarks());
        if (driverOperating.getDriveStatus()==2){
            dDriver.setStatus(Long.valueOf(2));
        }
        if (driverOperating.getDriveStatus()==4){
            dDriver.setStatus(Long.valueOf(4));
        }
        if (driverOperating.getDriveStatus() == 3 && dDriver.getExamStatus() == 3 && dDriver.getInterviewStatus() == 3){
            dDriver.setStatus(Long.valueOf(4));
        }
        return toAjax(dDriverService.updateDDriver(dDriver));
    }


    @GetMapping("/A09Employment/{id}")
    @Log(title = "录用按钮")
    public AjaxResult A09Employment(@PathVariable("id") String id){
        DDriver dDriver = dDriverService.selectDDriverById(id);
        if (dDriver.getStatus() == 4L){
            dDriver.setStatus(3L);
        }
        return toAjax(dDriverService.updateDDriver(dDriver));
    }


    /**
     * App 个人中心-个人信息接口
     */
    @RequestMapping(path = "/A01DriverInfo",method = RequestMethod.POST)
    @ResponseBody
    @Log(title = "个人中心-个人信息接口")
    public AjaxResult list(@RequestParam("userid") String userid)
    {
        DDriver dDriver = dDriverService.selectDDriverById(userid);
        return AjaxResult.success(dDriver);
    }

    /**
     * 车队司机基础数据（OTM -> 车务系统）
     */
    @RequestMapping(path = "/OTMCarDriverList",method = RequestMethod.POST)
    @ResponseBody
    @Log(title = "车队司机基础数据（OTM -> 车务系统）")
    public TableDataInfo OTMCarDriverList()
    {
        return getDataTable(dDriverService.selectOTMDriverAll());
    }

    /**
     * 上传附件保存数据表
     */
    @Log(title = "上传附件保存数据表")
    @PreAuthorize(hasPermi = "pfserverperson:driver:edit")
    @PostMapping("/A10Enclosure")
    public AjaxResult A10Enclosure(@RequestBody DDriver dDriver){
        LoginUser userInfo = tokenService.getLoginUser();
        List<SysFile> sysFileList = new ArrayList<>();
        if (dDriver.getFileUrl().size()==0){
            sysFileMapper.delelteFilesByRelationId(dDriver.getId());
            return toAjax(1);
        }
        else {
            sysFileMapper.delelteFilesByRelationId(dDriver.getId());
            for (String url:dDriver.getFileUrl()){
            SysFile sysFile = new SysFile();
            sysFile.setId(UUID.randomUUID().toString());
            sysFile.setType(9L);
            sysFile.setRelationId(dDriver.getId());
            sysFile.setFilePath(url);
            sysFile.setDeleteFlag(1L);
            sysFile.setCreateUser(userInfo.getUsername());
            sysFile.setCreateTime(new Date());
            sysFileList.add(sysFile);
            }
            int fileNum = sysFileService.insertSysfileBatch(sysFileList);
            if (fileNum == dDriver.getFileUrl().size()){
                return AjaxResult.success("成功插入"+(fileNum)+"条");
            }
            else {
                return AjaxResult.error("成功插入"+(fileNum)+"条，有"+(dDriver.getFileUrl().size()-fileNum)+"插入失败");
            }
        }
    }

    /**
     * 长途司机list
     */
    @PostMapping("/longDdriverList")
    public List<DDriver> longDdriverList(){
        return dDriverService.selectDDriverLongD();
    }

    /**
     * 短途司机list
     */
    @PostMapping("/shortDdriverList")
    public List<DDriver> shortDdriverList(){
        return dDriverService.selectDDriverShortD();
    }

    @PostMapping("/allDrivers")
    public List<DDriver> selectDDriverAll(){
        return dDriverService.selectDDriverAll();
    }

    /**
     * @describe 外包类型驾驶员
     * @author DongCL
     * @date 2021-05-10 09:38
     * @param
     * @return
     */
    @PostMapping("/driversList")
    public List<DDriver> driversList(){
        return dDriverService.selectDDriverStaff();
    }


    /**
     * 新增驾驶员档案
     * @param dDriver
     * @return
     */
    @Log(title = "新增驾驶员档案")
    @PostMapping("/A24Add")
    @PreAuthorize(hasPermi = "pfserverperson:driver:driverfileadd")
    public AjaxResult A24Add(@Validated @RequestBody DDriver dDriver){
        //校验身份证号
        int checkIdCard = dDriverService.selectDDriverByIdCard(dDriver.getIdCard());
        if (checkIdCard>0){
            return AjaxResult.error("该身份证号已存在");
        }
//        if (StringUtils.isEmpty(dDriver.getQualification())){
//            return AjaxResult.error("从业资格证不能为空");
//        }
        //获取当前用户信息
        LoginUser userInfo = tokenService.getLoginUser();
        dDriver.setCreateBy(userInfo.getUsername());
        UUID uuid = UUID.randomUUID();
        dDriver.setId(uuid.toString());
        dDriver.setDeleteFlag(1);
        dDriver.setStatus(Long.valueOf(3));
        dDriver.setInterviewStatus(Long.valueOf(3));
        dDriver.setExamStatus(Long.valueOf(3));
        dDriver.setDriveStatus(Long.valueOf(3));
        dDriver.setDisableStatus(Long.valueOf(1));
        dDriver.setPassword(SecurityUtils.encryptPassword("123456"));
        List<SysFile> sysFileList = new ArrayList<>();
        if (dDriver.getFileUrl()!= null &&dDriver.getFileUrl().size()>0){
            for (String url:dDriver.getFileUrl()){
                SysFile sysFile = new SysFile();
                sysFile.setId(UUID.randomUUID().toString());
                sysFile.setType(9L);
                sysFile.setRelationId(dDriver.getId());
                sysFile.setFilePath(url);
                sysFile.setDeleteFlag(1L);
                sysFile.setCreateUser(userInfo.getUsername());
                sysFile.setCreateTime(new Date());
                sysFileList.add(sysFile);
            }
            int fileNum = sysFileService.insertSysfileBatch(sysFileList);
        }
        return toAjax(dDriverService.insertDDriver(dDriver));
    }


    /**
     * @describe
     * @author DongCL
     * @date 2021-01-08 15:59
     * @param contact 手机号
     * @return
     */
    @RequestMapping(value = "/loginDriverList",method = RequestMethod.POST)
    public DDriver driverLogin(@RequestParam("contact") String contact){
        return dDriverService.queryDriverByContact(contact);
    }

}

