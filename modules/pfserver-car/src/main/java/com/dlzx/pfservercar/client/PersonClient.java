package com.dlzx.pfservercar.client;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.security.handler.FeignSupportConfig;
import com.dlzx.pfservercar.domain.*;
import com.dlzx.pfservercar.domain.model.OTMTTransportShortTaskModel;
import com.dlzx.pfservercar.domain.model.OTMTTransportTaskModel;
import com.dlzx.system.api.model.LoginDriver;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
@FeignClient(value = "pfserver-person",configuration = FeignSupportConfig.class) //注册中心服务的名字
//@FeignClient(value = "pfserver-person",url = "http://localhost:9500/",configuration = FeignSupportConfig.class) //本地调试
public interface PersonClient {


    /**
     * App 考勤-驾驶员请假列表
     * */
    @RequestMapping(path = "/vacation/E01VacationList",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public TableDataInfo E01VacationList(@RequestBody DVacation dVacation);

    /**
     * App 考勤-请假详情（区分四种状态）
     */
    @RequestMapping(path = "/vacation/E02VacationDetail")
    public AjaxResult E02VacationDetail(@RequestParam("id") String id);

    /**
     * App 考勤-新增请假记录
     */
    @RequestMapping(path = "/vacation/E04VacationAdd",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult E04VacationAdd(@RequestBody DVacation dVacation);
    /**
     * App 考勤-请假申请编辑接口
     */
    @RequestMapping(path = "/vacation/E04VacationEditSave",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult E04VacationEditSave(@RequestBody DVacation dVacation);

    /**
     * App 考勤-撤销请假申请接口
     */
    @RequestMapping(path = "/vacation/E03UndoVacation",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult E03UndoVacation(@RequestBody DVacation dVacation);

    /**
     * App 个人中心-个人信息接口
     */
    @RequestMapping(path = "/person/driver/A01DriverInfo",method = RequestMethod.POST)
    public AjaxResult A01DriverInfo(@RequestParam("userid") String userid);


    /**
     * 车队司机基础数据（OTM -> 车务系统）（弃用）
     */
    @RequestMapping(path = "/person/driver/OTMCarDriverList",method = RequestMethod.POST)
    public TableDataInfo OTMCarDriverList();

    /**
     * App 驾驶员公告列表
     * */
    @RequestMapping(path = "/person/notice/D01List",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public TableDataInfo D01List(@RequestBody DNoticeAp dNoticeAp);

    /**
     * App 驾驶员公告详情
     */
    @RequestMapping(path = "/person/notice/D02List")
    public AjaxResult D02List(@RequestParam("id") String id);

    /**
     * App 驾驶员培训列表
     * */
    @RequestMapping(path = "/person/training/C01List",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public TableDataInfo C01List(@RequestBody DTrainingAp dTrainingAp);

    /**
     * App 驾驶员公告详情
     */
    @RequestMapping(path = "/person/training/C02List")
    public AjaxResult C02List(@RequestParam("id") String id);

    /**
     * App 驾驶员培训列表
     * */
    @RequestMapping(path = "/person/training/C01Change",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult C01Change(@RequestBody DTrainingAp dTrainingAp);


    /**
     * App 驾驶员培训列表
     * */
    @RequestMapping(path = "/person/training/C02Edit",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult C02Edit(@RequestBody DTrainingAp dTrainingAp);

    /**
     * App 驾驶员试题列表
     * */
    @RequestMapping(path = "/person/dDriverExamProblem/B01List",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public TableDataInfo B01List();


    /**
     * App 驾驶员答题列表
     * */
    @RequestMapping(path = "/person/dDriverExamProblem/B02List",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public TableDataInfo B02List(@RequestBody List<DExamPaperDaAn> list);

    /**
     * App 驾驶员违章列表
     * */
    @RequestMapping(path = "/person/safeTrafficTransgression/K01List",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public TableDataInfo K01List(@RequestBody SafeTrafficTransgression safeTrafficTransgression);

    /**
     * App 驾驶员违章状态更改
     * */
    @RequestMapping(path = "/person/safeTrafficTransgression/K02Change",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult K02Change(@RequestBody SafeTrafficTransgression safeTrafficTransgression);


    /**
     * App 驾驶员违章凭证上传
     * */
    @RequestMapping(path = "/person/safeTrafficTransgression/K02Shang",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult K02Shang(@RequestBody TrafficUrl trafficUrl);

    /**
     * App 驾驶员违章详情
     */
    @RequestMapping(path = "/person/safeTrafficTransgression/K02GetInfo")
    public AjaxResult K02GetInfo(@RequestParam("id") String id);

    /**
     * App 驾驶员违章凭证上传
     * */
    @RequestMapping(path = "/person/subsidy/F01List",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public TableDataInfo F01List(@RequestBody DSubsidy dSubsidy);
    /**
     * App 驾驶员违章凭证上传
     * */
    @RequestMapping(path = "/person/subsidy/F02Add",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult F02Add(@RequestBody DSubsidy dSubsidy);
    /**
     * App 驾驶员违章凭证上传
     * */
    @RequestMapping(path = "/person/subsidy/F07Edit",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult F07Edit(@RequestBody DSubsidy dSubsidy);
    /**
     * App 驾驶员违章详情
     */
    @RequestMapping(path = "/person/subsidy/F07GetInfo")
    public AjaxResult F07GetInfo(@RequestParam("id") String id);
    /**
     * App 驾驶员违章详情
     */
    @RequestMapping(path = "/person/subsidy/F04Change")
    public AjaxResult F04Change(@RequestParam("id") String id);

    /**
     * 通过任务id计算绩效金额总和
     * @param id
     * @return
     */
    @GetMapping(path = "/person/performanceRecord/transportMoney/{id}")
    public BigDecimal transportMoney(@PathVariable(value = "id",required = false)String id);


    /**
     * @describe car服务调用 - 新增长途绩效记录
     * @author DongCL
     * @date 2021-01-08 10:25
     * @param dPerformanceRecord
     * @return AjaxResult
     */
    @RequestMapping(path = "/person/performanceRecord/createTaskRecord",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult createPerformanceRecord(@RequestBody DPerformanceRecord dPerformanceRecord);

    /**
     * @describe car服务调用 - 获取绩效信息
     * @author DongCL
     * @date 2021-01-08 10:25
     * @param vehicleType 车型
     * @param routeId 线路ID
     * @param isVice 是否有对班
     * @return AjaxResult
     */
    @RequestMapping(path = "/person/performanceRecord/performanceDetail",method = RequestMethod.POST)
    public List<DPerformance> PerformanceDetail(@RequestParam(value = "vehicleType",required = false)String vehicleType, @RequestParam(value = "routeId",required = false)String routeId,
                                   @RequestParam(value = "isVice",required = false)Long isVice);

    /**
     * @describe car服务调用 - 根据任务ID获取长途绩效记录
     * @author DongCL
     * @date 2021-01-08 10:25
     * @param taskId 任务ID
     * @return AjaxResult
     */
    @RequestMapping(path = "/person/performanceRecord/queryPerformanceById",method = RequestMethod.POST)
    public DPerformanceRecord queryPerformanceById(@RequestParam(value = "taskId",required = false)String taskId);

    /**
     * @describe car服务调用 - 根据任务ID获取长途绩效记录
     * @author DongCL
     * @date 2021-01-08 10:25
     * @param dPerformanceRecord 长途绩效对象
     * @return AjaxResult
     */
    @RequestMapping(path = "/person/performanceRecord/updatePerformanceRecord",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult updatePerformanceRecord(DPerformanceRecord dPerformanceRecord);


    /**
     * @describe car服务调用 - 更新绩效任务时间
     * @author DongCL
     * @date 2021-01-08 10:25
     * @param otmtTransportTaskModel
     * @return AjaxResult
     */
    @RequestMapping(path = "/person/performanceRecord/updateTaskTime",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult updateTaskTime(@RequestBody OTMTTransportTaskModel otmtTransportTaskModel);


    /**
     * @describe car服务调用 - 插入短途绩效记录
     * @author DongCL
     * @date 2021-01-14 10:06
     * @param dPerformanceShortHaulRecord
     * @return
     */
    @RequestMapping(path = "/person/performanceShortHaulRecord/submitShortRecord",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult submitShortRecord(@RequestBody DPerformanceShortHaulRecord dPerformanceShortHaulRecord);

    /**
     * @describe car服务调用 - 插入加班绩效记录
     * @author DongCL
     * @date 2021-01-14 10:06
     * @param dPerformanceRecord
     * @return
     */
    @RequestMapping(path = "/person/performanceOvertimeRecord/submitOverTimeRecord",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult createPerformanceOverTimeRecord(DPerformanceOvertimeRecord dPerformanceRecord);


    /**
     * @describe car服务调用 - 驾驶员登录
     * @author DongCL
     * @date 2021-01-25 11:43
     * @param dDriver
     * @return
     */
    @RequestMapping(path = "/person/driver/driverAuthLogin",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
//    public LoginDriver driverAuthLogin(DDriver dDriver);
    public AjaxResult driverAuthLogin(DDriver dDriver);

    /**
     * @describe car服务调用 - 请假类型
     * @author DongCL
     * @date 2021-01-25 11:43
     * @return
     */
    @RequestMapping(path = "/person/leaveType/E03VacationTypeList",method = RequestMethod.POST)
    TableDataInfo E03VacationTypeList();


    /**
     * @describe 更新用户密码
     * @author DongCL
     * @date 2021-02-23 16:45
     * @param
     * @return
     */
    @RequestMapping(path = "/person/driver/driverUpdatePassword",method = RequestMethod.POST)
    int driverUpdatePassword(@RequestParam(value = "id",required = false)String id, @RequestParam(value = "encryptPassword",required = false) String encryptPassword);


    /**
     * @describe 获取驾驶员信息
     * @author DongCL
     * @date 2021-02-23 16:46
     * @param
     * @return
     */
    @RequestMapping(path = "/person/driver/queryDriverById",method = RequestMethod.POST)
    DDriver queryDriverById(@RequestParam(value = "driverId",required = false) String driverId);
}