package com.dlzx.system.controller.administrator_app;

import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.system.api.model.LoginUser;
import com.dlzx.system.domain.ReviewSubmit;
import com.dlzx.system.domain.SysExamineConfigProcess;
import com.dlzx.system.domain.SysExamineTask;
import com.dlzx.system.mapper.SysExamineTaskMapper;
import com.dlzx.system.service.ISysExamineTaskService;
import com.dlzx.system.service.SysExamineConfigProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统管理-审批任务Controller
 * 
 * @author yangluhe
 * @date 2020-12-11
 */
@RestController
@RequestMapping("/administrator-app/task")
public class ExamineTaskController extends BaseController
{
    @Autowired
    private ISysExamineTaskService sysExamineTaskService;
    @Autowired
    private SysExamineTaskMapper sysExamineTaskMapper;

    @Autowired
    private TokenService tokenService;


    /**
     * 管理员app-审批待办列表
     */
    @RequestMapping("/P01ExamineBacklogList")
    @ResponseBody
    public TableDataInfo p01ExamineBacklogList(@RequestBody SysExamineTask sysExamineTask)
    {
        startPage(sysExamineTask.getPageNum(),sysExamineTask.getPageSize());
        LoginUser loginUser = tokenService.getLoginUser();
        if (loginUser == null){
            throw new CustomException("管理员信息不存在");
        }
        sysExamineTask.setUserId(loginUser.getUserid());
        List<SysExamineTask> sysExamineTasks = sysExamineTaskService.querySysExamineTaskBacklogList(sysExamineTask);
        return getDataTable(sysExamineTasks);
    }

    /**
     * 管理员app-审批记录
     */
    @RequestMapping("/Q01ExamineList")
    @ResponseBody
    public TableDataInfo q01ExamineList(@RequestBody SysExamineTask sysExamineTask)
    {
        startPage(sysExamineTask.getPageNum(),sysExamineTask.getPageSize());
        LoginUser loginUser = tokenService.getLoginUser();
        if (loginUser == null){
            throw new CustomException("管理员信息不存在");
        }
        sysExamineTask.setUserId(loginUser.getUserid());
        List<SysExamineTask> sysExamineTasks = sysExamineTaskService.queryExamineList(sysExamineTask);
        return getDataTable(sysExamineTasks);
    }

    /**
     * 管理员app-抄送记录
     */
    @RequestMapping("/R01SendDuplicateList")
    @ResponseBody
    public TableDataInfo r01SendDuplicateList(@RequestBody SysExamineTask sysExamineTask)
    {
        startPage(sysExamineTask.getPageNum(),sysExamineTask.getPageSize());
        LoginUser loginUser = tokenService.getLoginUser();
        if (loginUser == null){
            throw new CustomException("管理员信息不存在");
        }
        sysExamineTask.setUserId(loginUser.getUserid());
        List<SysExamineTask> sysExamineTasks = sysExamineTaskService.querySendDuplicateList(sysExamineTask);
        return getDataTable(sysExamineTasks);
    }


    /**
     * 弃用
     * 管理员app-审批记录未查看条数
     */
    @RequestMapping("/Q01ExamineCount")
    @ResponseBody
    public AjaxResult q01ExamineCount(@RequestBody SysExamineTask sysExamineTask)
    {
        LoginUser loginUser = tokenService.getLoginUser();
        if (loginUser == null){
            throw new CustomException("管理员信息不存在");
        }
        sysExamineTask.setUserId(loginUser.getUserid());
        List<SysExamineTask> sysExamineTasks = sysExamineTaskMapper.queryExamineCount(sysExamineTask);
        return AjaxResult.success(sysExamineTasks.size());
    }

    /**
     * 弃用
     * 管理员app-抄送记录未查看条数
     */
    @RequestMapping("/R01SendDuplicateCount")
    @ResponseBody
    public AjaxResult r01SendDuplicateCount(@RequestBody SysExamineTask sysExamineTask)
    {
        LoginUser loginUser = tokenService.getLoginUser();
        if (loginUser == null){
            throw new CustomException("管理员信息不存在");
        }
        sysExamineTask.setUserId(loginUser.getUserid());
        List<SysExamineTask> sysExamineTasks = sysExamineTaskMapper.querySendDuplicateCount(sysExamineTask);
        return AjaxResult.success(sysExamineTasks.size());
    }



    /**
     * @describe 管理员app记录单条记录阅读
     * @author DongCL
     * @date 2021-02-24 09:27
     * @param
     * @return
     */
    @RequestMapping("/Q01ReadStatus")
    @ResponseBody
    public AjaxResult q01ReadStatus(@RequestBody SysExamineTask sysExamineTask)
    {
        LoginUser loginUser = tokenService.getLoginUser();
        if (loginUser == null){
            throw new CustomException("管理员信息不存在");
        }
        // 已读
        sysExamineTask.setIsShow(2);
        return AjaxResult.success(sysExamineTaskService.updateSysExamineTaskReadStatus(sysExamineTask));
    }



    /**
     * @describe 管理员app记录全部阅读
     * @author DongCL
     * @date 2021-02-24 09:27
     * @param
     * @return
     */
    @RequestMapping("/Q01AllReadStatus")
    @ResponseBody
    public AjaxResult q01AllReadStatus()
    {
        SysExamineTask sysExamineTask = new SysExamineTask();
        LoginUser loginUser = tokenService.getLoginUser();
        if (loginUser == null){
            throw new CustomException("管理员信息不存在");
        }
        sysExamineTask.setUserId(loginUser.getUserid());
        // 已读
        sysExamineTask.setIsShow(2);
        return AjaxResult.success(sysExamineTaskService.updateSysExamineTaskReadStatus(sysExamineTask));
    }
}
