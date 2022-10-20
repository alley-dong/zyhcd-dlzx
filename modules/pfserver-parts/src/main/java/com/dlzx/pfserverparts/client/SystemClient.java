package com.dlzx.pfserverparts.client;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.domain.SysExamineConfigParams;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.security.handler.FeignSupportConfig;
import com.dlzx.pfserverparts.domain.ReviewSubmit;
import com.dlzx.pfserverparts.domain.SysExamineConfigProcess;
import com.dlzx.pfserverparts.domain.SysExamineTask;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Component
@FeignClient(value = "system",configuration = FeignSupportConfig.class) //注册中心服务的名字
//@FeignClient(value = "system",url = "http://localhost:9201/",configuration = FeignSupportConfig.class) //注册中心服务的名字
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
    @RequestMapping("/task/sysExamineTaskList")
    public SysExamineTask sysExamineTaskList(@RequestBody SysExamineTask sysExamineTask);


    /**
     * 审批公共接口-获取审批业务审批人
     */
    @RequestMapping("/task/queryExamineConfigProcessList")
    public List<SysExamineConfigProcess> queryExamineConfigProcessList(Long type);

    /**
     * 审批公共接口-审核校验
     */
    @RequestMapping("/task/reviewCommon")
    public Map reviewCommon(@RequestBody ReviewSubmit reviewSubmit);

     /**
     * 审批公共接口-审核删除
     */
    @RequestMapping(path = "/task/deleteExamineTaskByRelationId",method = RequestMethod.POST)
    public AjaxResult deleteExamineTaskByRelationId(@RequestParam(value = "relationId",required = false)String relationId);

}