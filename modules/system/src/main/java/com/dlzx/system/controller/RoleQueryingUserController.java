package com.dlzx.system.controller;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.system.api.domain.SysUser;
import com.dlzx.system.domain.SysExamineConfigProcess;
import com.dlzx.system.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/config")
public class RoleQueryingUserController extends BaseController {

    @Autowired
    SysUserRoleService sysUserRoleService;

    /**
     * 通过角色查询用户
     */
    @PreAuthorize(hasPermi = "system:process:list")
    @GetMapping("/B05UserRolelist")
    @ResponseBody
    public AjaxResult list(@RequestParam(value = "roleId",required = false) String roleId) {
//        if (StringUtils.isEmpty(roleId)) {
//            return   AjaxResult.error("角色ID不能为空");
//        }
        List<SysUser> list = sysUserRoleService.selectSysUserList(roleId);
        return AjaxResult.success(list);
    }



}
