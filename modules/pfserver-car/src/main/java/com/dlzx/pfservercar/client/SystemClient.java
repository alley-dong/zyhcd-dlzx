package com.dlzx.pfservercar.client;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.domain.SysExamineConfigParams;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.security.handler.FeignSupportConfig;
import com.dlzx.pfservercar.domain.RReportConfig;
import com.dlzx.pfservercar.domain.ReviewSubmit;
import com.dlzx.pfservercar.domain.SysExamineConfigProcess;
import com.dlzx.pfservercar.domain.SysExamineTask;
import com.dlzx.pfservercar.domain.SysLog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@FeignClient(value = "system",configuration = FeignSupportConfig.class) //注册中心服务的名字
//@FeignClient(value = "system",url = "http://localhost:9201/") //本地调试
public interface SystemClient {

    /**
     * 审批公共接口-提交申请
     * */
    @RequestMapping(path = "/task/SubmitCommon",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public int submitCommon(@RequestBody SysExamineConfigParams sysExamineConfigParams);

    /**
     * 后台提交申请
     */
    @Log(title = "后台提交申请")
    @RequestMapping(path = "/task/adminSubmitCommon",method = RequestMethod.GET)
    public int adminSubmitCommon(@RequestParam(value = "type",required = false)Long type, @RequestParam(value = "relationId",required = false)String relationId,@RequestParam(value = "driverId",required = false)String driverId,@RequestParam(value = "name",required = false)String name);

    /**
     * 审批公共接口-获取审批记录
     */
    @RequestMapping(path = "/task/sysExamineTaskList",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public SysExamineTask sysExamineTaskList(@RequestBody SysExamineTask sysExamineTask);


    /**
     * 审批公共接口-获取审批业务审批人
     */
    @RequestMapping(path = "/task/queryExamineConfigProcessList",method = RequestMethod.POST)
    public AjaxResult queryExamineConfigProcessList(@RequestParam("type")Long type);

    /**
     * 审批公共接口-审核校验
     */
    @RequestMapping(path ="/task/reviewCommon",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Map reviewCommon(@RequestBody ReviewSubmit reviewSubmit);

    /**
     * 审批公共接口-审核校验
     */
    @RequestMapping(path ="/log/insertSysLog",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public int insertSysLog(@RequestBody SysLog sysLog);

     /**
     * 审批公共接口-审核删除
     */
    @RequestMapping(path = "/task/deleteExamineTaskByRelationId",method = RequestMethod.POST)
    public AjaxResult deleteExamineTaskByRelationId(@RequestParam("relationId")String relationId);

    /**
     * 管理员公共接口-消息节点
     */
    @RequestMapping(path = "/administrator-app/msg/addAdminMsg",method = RequestMethod.POST)
    public AjaxResult addAdminMsg(@RequestParam(value = "type",required = false)Long type, @RequestParam(value = "relationId",required = false)String relationId,
                                  @RequestParam(value = "title",required = false)String title, @RequestParam(value = "content",required = false)String content);

//
//    /**
//     * 驾驶员公共接口-消息节点
//     */
//    @RequestMapping(path = "/driver-api/msg/addDriverMsg",method = RequestMethod.POST)
//    public AjaxResult addDriverMsg(@RequestParam(value = "type",required = false)Long type, @RequestParam(value = "relationId",required = false)String relationId,
//                                   @RequestParam(value = "driverId",required = false)String driverId, @RequestParam(value = "title",required = false)String title,
//                                   @RequestParam(value = "content",required = false)String content);

    /**
     * 查找参数配置
     * @return
     */
    @GetMapping(path = "/statisticsConfig/Info")
    public RReportConfig Info();

    @RequestMapping(path = "/task/queryExamineTaskList",method = RequestMethod.POST)
    public TableDataInfo queryExamineTaskList(@RequestParam("userid")Long userid);

    @RequestMapping(path = "/task/isFixedUpdate",method = RequestMethod.POST)
    public AjaxResult isFixedUpdate(@RequestParam(value = "status",required = false)Long status, @RequestParam(value = "reviewTime",required = false)Date reviewTime, @RequestParam("id")String id);


    @RequestMapping(path = "/task/updateStationStatus",method = RequestMethod.POST)
    public int updateStationStatus(@RequestParam(value = "relationId",required = false)String relationId);
}