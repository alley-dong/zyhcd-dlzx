package com.dlzx.system.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.web.domain.SysExamineConfigParams;
import com.dlzx.common.security.service.DriverTokenService;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.system.api.model.LoginDriver;
import com.dlzx.system.api.model.LoginUser;
import com.dlzx.system.domain.AppTokenUpdate;
import com.dlzx.system.domain.ReviewSubmit;
import com.dlzx.system.domain.SysExamineConfigProcess;
import com.dlzx.system.mapper.SysExamineTaskMapper;
import com.dlzx.system.service.SysExamineConfigProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.system.domain.SysExamineTask;
import com.dlzx.system.service.ISysExamineTaskService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 系统管理-审批任务Controller
 * 
 * @author yangluhe
 * @date 2020-12-11
 */
@RestController
@RequestMapping("/task")
public class SysExamineTaskController extends BaseController
{
    @Autowired
    private ISysExamineTaskService sysExamineTaskService;

    @Autowired
    private SysExamineConfigProcessService sysExamineConfigProcessService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private DriverTokenService driverTokenService;
    @Autowired
    private SysExamineTaskMapper sysExamineTaskMapper;

    /**
     * 查询系统管理-审批任务列表
     */
    @PreAuthorize(hasPermi = "system:task:list")
    @GetMapping("/list")
    public TableDataInfo list(SysExamineTask sysExamineTask)
    {
        startPage();
        List<SysExamineTask> list = sysExamineTaskService.selectSysExamineTaskList(sysExamineTask);
        return getDataTable(list);
    }

    /**
     * 导出系统管理-审批任务列表
     */
    @PreAuthorize(hasPermi = "system:task:export")
    @Log(title = "系统管理-审批任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysExamineTask sysExamineTask) throws IOException
    {
        List<SysExamineTask> list = sysExamineTaskService.selectSysExamineTaskList(sysExamineTask);
        ExcelUtil<SysExamineTask> util = new ExcelUtil<SysExamineTask>(SysExamineTask.class);
        util.exportExcel(response, list, "task");
    }

    /**
     * 获取系统管理-审批任务详细信息
     */
    @PreAuthorize(hasPermi = "system:task:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysExamineTaskService.selectSysExamineTaskById(id));
    }

    /**
     * 新增系统管理-审批任务
     */
    @PreAuthorize(hasPermi = "system:task:add")
    @Log(title = "系统管理-审批任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysExamineTask sysExamineTask)
    {
        return toAjax(sysExamineTaskService.insertSysExamineTask(sysExamineTask));
    }

    /**
     * 修改系统管理-审批任务
     */
    @PreAuthorize(hasPermi = "system:task:edit")
    @Log(title = "系统管理-审批任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysExamineTask sysExamineTask)
    {
        return toAjax(sysExamineTaskService.updateSysExamineTask(sysExamineTask));
    }

    /**
     * 删除系统管理-审批任务
     */
    @PreAuthorize(hasPermi = "system:task:remove")
    @Log(title = "系统管理-审批任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(sysExamineTaskService.deleteSysExamineTaskByIds(ids));
    }



    /**
     * 驾驶员提交申请
     */
    @Log(title = "提交申请")
    @RequestMapping(path = "/SubmitCommon",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public int SubmitCommon(@RequestBody SysExamineConfigParams sysExamineConfigParams){
        return sysExamineTaskService.selectTaskList(sysExamineConfigParams);
    }

    /**
     * 后台提交申请
     */
    @Log(title = "后台提交申请")
    @RequestMapping(path = "/adminSubmitCommon",method = RequestMethod.GET)
    public int adminSubmitCommon(@RequestParam(value = "type",required = false)Long type,@RequestParam(value = "relationId",required = false) String relationId,
                            @RequestParam(value = "driverId",required = false)String driverId,@RequestParam(value = "name",required = false)String name){
        return sysExamineTaskService.selectAdminTaskList(type,relationId,driverId,name);
    }

    /**
     * 查询系统管理-获取审批记录
     */
    @PostMapping("/sysExamineTaskList")
    public AjaxResult sysExamineTaskList(@RequestBody SysExamineTask sysExamineTask)
    {
        SysExamineTask sysExamineTasks = sysExamineTaskService.querySysExamineTaskList(sysExamineTask);
        return AjaxResult.success(sysExamineTasks);
    }


    /**
     * 查询系统管理-获取审批记录（新）
     */
    @PostMapping("/sysExamineTaskDriverList")
    public AjaxResult sysExamineTaskDriverList(@RequestBody SysExamineTask sysExamineTask)
    {
        return AjaxResult.success(sysExamineTaskService.sysExamineTaskDriverList(sysExamineTask));
    }


    /**
     * @describe 获取审批业务审批人
     * @author DongCL
     * @date 2021-04-09 09:26
     * @param type 业务类型必填
     * @param relationId 业务表ID 非必填（在维保重新提交的时候传入）
     * @return
     */
    @RequestMapping("/queryExamineConfigProcessList")
    @ResponseBody
    public TableDataInfo queryExamineConfigProcessList(Long type,String relationId)
    {
        List<SysExamineConfigProcess> list = sysExamineConfigProcessService.queryExamineConfigByType(type,relationId);
        return getDataTable(list);
    }

    /**
    * 审核校验
    * */
    @PostMapping("/reviewCommon")
    public Map reviewCommon(@RequestBody ReviewSubmit reviewSubmit){
        return sysExamineTaskService.reviewCommon(reviewSubmit);
    }

    /**
     * 删除审批记录
     */
    @RequestMapping(path = "/deleteExamineTaskByRelationId",method = RequestMethod.POST)
    public AjaxResult deleteExamineTaskByRelationId(@RequestParam("relationId") String relationId)
    {
        int result = sysExamineTaskService.deleteExamineTaskByRelationId(relationId);
        return AjaxResult.success(result);
    }

    /**
     * 首页审批记录
     */
    @RequestMapping(path = "/queryExamineTaskList",method = RequestMethod.POST)
    public TableDataInfo queryExamineTaskList(@RequestParam("userid")Long userid)
    {
        SysExamineTask sysExamineTask = new SysExamineTask();
        sysExamineTask.setUserId(userid);
        sysExamineTask.setStatus(1L);
        sysExamineTask.setTaskStatus(1L); // 获取启用的
        return getDataTable(sysExamineTaskService.selectSysExamineTaskList(sysExamineTask));
    }

    /**
     * 针对维修保养维修站审核异地维修为已通过
     */
    @RequestMapping(path = "/isFixedUpdate",method = RequestMethod.POST)
    public AjaxResult isFixedUpdate(@RequestParam(value = "status",required = false)Long status, @RequestParam(value = "reviewTime",required = false)Date reviewTime,  @RequestParam("id")String id){
        return toAjax(sysExamineTaskMapper.isFixedUpdate(status,new Date(),id));
    }

    /**
     * 针对维修保养维修站审核重制审批记录
     */
    @RequestMapping(path = "/updateStationStatus",method = RequestMethod.POST)
    public int updateStationStatus(@RequestParam("relationId")String relationId){

        // 修改审批任务数据
        sysExamineTaskMapper.updateStationStatus(relationId);
        // 修改任务展示状态
        return sysExamineTaskMapper.updateTaskStatus(relationId);
    }

    /**
     * 登录获取设备token存入数据表中
     */
    @Log(title = "获取登录token")
    @RequestMapping(path = "/tokenUpdate",method = RequestMethod.POST)
    public AjaxResult tokenUpdate(@RequestBody AppTokenUpdate appTokenUpdate){
        return toAjax(sysExamineTaskService.updateAppToken(appTokenUpdate));
    }

    /**
     * 退出 清除token
     */
    @RequestMapping(path = "/tokenClean",method = RequestMethod.POST)
    public AjaxResult tokenClean(){
        return toAjax(sysExamineTaskService.cleanToken());
    }


    /**
     * 查询token是否存在
     */
    @RequestMapping(path = "/queryToken",method = RequestMethod.POST)
    public AjaxResult queryToken(){
        return AjaxResult.success(sysExamineTaskService.queryToken());
    }
}
