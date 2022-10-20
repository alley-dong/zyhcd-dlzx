package com.dlzx.system.controller.administrator_app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.system.api.model.LoginDriver;
import com.dlzx.system.api.model.LoginUser;
import com.dlzx.system.domain.SysExamineConfigProcess;
import com.dlzx.system.domain.SysExamineTask;
import com.dlzx.system.mapper.SysExamineTaskMapper;
import com.dlzx.system.service.ISysExamineTaskService;
import com.dlzx.system.service.SysExamineConfigProcessService;
import org.checkerframework.checker.units.qual.A;
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
import com.dlzx.system.domain.AdministratorMsg;
import com.dlzx.system.service.IAdministratorMsgService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 管理员-管理员消息Controller
 * 
 * @author dlzx
 * @date 2020-12-14
 */
@RestController
@RequestMapping("/administrator-app/msg")
public class AdministratorMsgController extends BaseController
{
    @Autowired
    private IAdministratorMsgService administratorMsgService;

    @Autowired
    private SysExamineTaskMapper sysExamineTaskMapper;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ISysExamineTaskService sysExamineTaskService;

    @Autowired
    private SysExamineConfigProcessService sysExamineConfigProcessService;

    /**
     * 管理员App 个人中心-个人信息接口
     */
    @RequestMapping("/N03AdministratorInfo")
    @ResponseBody
    @Log(title = "个人中心-个人信息接口")
    public AjaxResult info()
    {
        LoginUser loginUser = tokenService.getLoginUser();
        if (loginUser==null){
            return AjaxResult.error("用户信息为空");
        }
        return AjaxResult.success(loginUser);
    }

    /**
     * App 个人消息-消息列表接口
     */
    @RequestMapping("/N03AdministratorMessageList")
    @ResponseBody
    @Log(title = "App 个人消息-消息列表接口")
    public TableDataInfo msgList(@RequestBody AdministratorMsg administratorMsg)
    {
        startPage(administratorMsg.getPageNum(),administratorMsg.getPageSize());
        LoginUser loginUser = tokenService.getLoginUser();
        if (loginUser==null){
            throw new CustomException("用户信息为空");
        }
        startPage(administratorMsg.getPageNum(),administratorMsg.getPageSize());
        List<AdministratorMsg>dDriverMsgs = administratorMsgService.queryAdministratorMsgList(loginUser.getUserid());
        return getDataTable(dDriverMsgs);
    }

    /**
     * App 个人消息-修改阅读状态接口
     */
    @RequestMapping("/N03AdministratorReadStatus")
    @ResponseBody
    @Log(title = "App 个人消息-修改阅读状态接口")
    public AjaxResult updateReadStatus(@RequestBody AdministratorMsg administratorMsg)
    {
        LoginUser loginUser = tokenService.getLoginUser();
        if (loginUser==null){
            throw new CustomException("用户信息为空");
        }
        // 已读
        administratorMsg.setId(administratorMsg.getId());
        administratorMsg.setStatus(2L);
        int result = administratorMsgService.updateAdministratorMsg(administratorMsg);
        return AjaxResult.success(result);
    }

    /**
     * App 个人消息-消息删除接口
     */
    @RequestMapping("/N03AdministratorDelMessage")
    @ResponseBody
    @Log(title = "App 个人消息-消息删除接口")
    public AjaxResult delMsg(@RequestBody AdministratorMsg administratorMsg)
    {
        LoginUser loginUser = tokenService.getLoginUser();
        if (loginUser==null){
            throw new CustomException("用户信息为空");
        }
        // 删除
        administratorMsg.setId(administratorMsg.getId());
        administratorMsg.setDeleteFlag(2L);
        int result = administratorMsgService.updateAdministratorMsg(administratorMsg);
        return AjaxResult.success(result);
    }


    /**
     * App 个人消息-获取未读提醒接口
     *
     */
    @RequestMapping("/N02AdministratorQueryUnreadList")
    @ResponseBody
    @Log(title = "App 个人消息-获取未读提醒接口")
    public AjaxResult unreadList()
    {
        LoginUser loginUser = tokenService.getLoginUser();
        if (loginUser==null){
            throw new CustomException("用户信息为空");
        }
        // 返回值
        Map<String,Object> result = new HashMap<>();
        // 入参
        SysExamineTask sysExamineTask = new SysExamineTask();
        sysExamineTask.setUserId(loginUser.getUserid());
        // 审批待办
        result.put("backlogCount",sysExamineTaskService.querySysExamineTaskBacklogList(sysExamineTask) == null ? 0 : sysExamineTaskService.querySysExamineTaskBacklogList(sysExamineTask).size());
        // 审批记录
        result.put("taskCount",sysExamineTaskMapper.queryExamineCount(sysExamineTask) == null ? 0 : sysExamineTaskMapper.queryExamineCount(sysExamineTask).size());
        // 抄送记录
        result.put("sendCount",sysExamineTaskMapper.querySendDuplicateCount(sysExamineTask)== null ? 0 : sysExamineTaskMapper.querySendDuplicateCount(sysExamineTask).size());
        return AjaxResult.success(result);
    }

    /**
     * App 管理员消息-插入消息节点
     */
    @Log(title = "管理员-管理员消息")
    @RequestMapping(path = "/addAdminMsg")
    public AjaxResult addMsg(@RequestParam(value = "type",required = false)Long type,@RequestParam(value = "relationId",required = false)String relationId,
                            @RequestParam(value = "title",required = false)String title,@RequestParam(value = "content",required = false)String content,
                             @RequestParam(value = "userId",required = false)String userId)
    {
        return toAjax(administratorMsgService.insertMessagePort(type,relationId,title,content,userId));

    }




    /**
     * 查询管理员-管理员消息列表
     */
    @PreAuthorize(hasPermi = "system:msg:list")
    @GetMapping("/list")
    public TableDataInfo list(AdministratorMsg administratorMsg)
    {
        startPage();
        List<AdministratorMsg> list = administratorMsgService.selectAdministratorMsgList(administratorMsg);
        return getDataTable(list);
    }

    /**
     * 导出管理员-管理员消息列表
     */
    @PreAuthorize(hasPermi = "system:msg:export")
    @Log(title = "管理员-管理员消息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdministratorMsg administratorMsg) throws IOException
    {
        List<AdministratorMsg> list = administratorMsgService.selectAdministratorMsgList(administratorMsg);
        ExcelUtil<AdministratorMsg> util = new ExcelUtil<AdministratorMsg>(AdministratorMsg.class);
        util.exportExcel(response, list, "msg");
    }

    /**
     * 获取管理员-管理员消息详细信息
     */
    @PreAuthorize(hasPermi = "system:msg:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(administratorMsgService.selectAdministratorMsgById(id));
    }

    /**
     * 新增管理员-管理员消息
     */
    @PreAuthorize(hasPermi = "system:msg:add")
    @Log(title = "管理员-管理员消息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdministratorMsg administratorMsg)
    {
        return toAjax(administratorMsgService.insertAdministratorMsg(administratorMsg));
    }

    /**
     * 修改管理员-管理员消息
     */
    @PreAuthorize(hasPermi = "system:msg:edit")
    @Log(title = "管理员-管理员消息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdministratorMsg administratorMsg)
    {
        return toAjax(administratorMsgService.updateAdministratorMsg(administratorMsg));
    }

    /**
     * 删除管理员-管理员消息
     */
    @PreAuthorize(hasPermi = "system:msg:remove")
    @Log(title = "管理员-管理员消息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(administratorMsgService.deleteAdministratorMsgByIds(ids));
    }
}
