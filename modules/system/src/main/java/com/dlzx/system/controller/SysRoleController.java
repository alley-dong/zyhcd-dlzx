package com.dlzx.system.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.mongo.utils.StringUtil;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dlzx.common.core.constant.UserConstants;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.common.security.utils.SecurityUtils;
import com.dlzx.system.api.domain.SysRole;
import com.dlzx.system.service.ISysRoleService;

/**
 * 角色信息
 * 
 * @author dlzx
 */
@RestController
@RequestMapping("/role")
public class SysRoleController extends BaseController
{
    @Autowired
    private ISysRoleService roleService;
    @Autowired
    private TokenService tokenService;

    @PreAuthorize(hasPermi = "system:role:list")
    @PostMapping("/B02List")
    public TableDataInfo list(@RequestBody SysRole role)
    {
        startPage(role.getPageNum(),role.getPageSize());
        List<SysRole> list = roleService.selectRoleList(role);
        return getDataTable(list);
    }


    @PostMapping("/B02ListT")
    public AjaxResult list1()
    {
        List<SysRole> list = roleService.selectRoleListT();
        return AjaxResult.success(list);
    }

    @Log(title = "角色管理", businessType = BusinessType.EXPORT)
    @PreAuthorize(hasPermi = "system:role:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysRole role) throws IOException
    {
        List<SysRole> list = roleService.selectRoleList(role);
        ExcelUtil<SysRole> util = new ExcelUtil<SysRole>(SysRole.class);
        util.exportExcel(response, list, "角色数据");
    }

    /**
     * 根据角色编号获取详细信息
     */
    @PreAuthorize(hasPermi = "system:role:getInfo")
    @GetMapping(value = "/B02GetInfo/{roleId}")
    public AjaxResult getInfo(@PathVariable Long roleId)
    {
        return AjaxResult.success(roleService.selectRoleById(roleId));
    }

    /**
     * 新增角色
     */
    @PreAuthorize(hasPermi = "system:role:add")
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @PostMapping("/B03Add")
    public AjaxResult add(@Validated @RequestBody SysRole role)
    {
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role)))
        {
            return AjaxResult.error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }
//        else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role)))
//        {
//            return AjaxResult.error("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
//        }
        LoginUser userInfo = tokenService.getLoginUser();
        role.setCreateBy(userInfo.getUsername());
//        role.setCreateBy(SecurityUtils.getUsername());
        // 判断权限标识符如果为NULL则默认设置为空字符串
        role.setRoleKey(StringUtil.isEmpty(role.getRoleKey())?"":role.getRoleKey());
        return toAjax(roleService.insertRole(role));

    }

    /**
     * 修改保存角色
     */
    @PreAuthorize(hasPermi = "system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("/B04Edit")
    public AjaxResult edit(@Validated @RequestBody SysRole role)
    {
        roleService.checkRoleAllowed(role);
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role)))
        {
            return AjaxResult.error("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }
//        else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role)))
//        {
//            return AjaxResult.error("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
//        }
//        role.setUpdateBy(SecurityUtils.getUsername());
        LoginUser userInfo = tokenService.getLoginUser();
        role.setUpdateBy(userInfo.getUsername());
        return toAjax(roleService.updateRole(role));
    }

    /**
     * 修改保存数据权限
     */
    @PreAuthorize(hasPermi = "system:role:edit")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PutMapping("/dataScope")
    public AjaxResult dataScope(@RequestBody SysRole role)
    {
        roleService.checkRoleAllowed(role);
        return toAjax(roleService.authDataScope(role));
    }

    /**
     * 状态修改
     */
    @PreAuthorize(hasPermi = "system:role:changeStatus")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("/B02ChangeStatus")
    public AjaxResult changeStatus(@RequestBody SysRole role)
    {
        roleService.checkRoleAllowed(role);
        role.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(roleService.updateRoleStatus(role));
    }

    /**
     * 删除角色
     */
    @PreAuthorize(hasPermi = "system:role:delete")
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @GetMapping("/B02Delete/{roleId}")
    public AjaxResult remove(@PathVariable ("roleId") Long roleId)
    {
        LoginUser userInfo = tokenService.getLoginUser();
        SysRole role=roleService.selectRoleById(roleId);
        role.setDelFlag("2");
        role.setUpdateBy(userInfo.getUsername());
        return toAjax(roleService.updateRoleDel(role));
    }

    /**
     * 获取角色选择框列表
     */
    @PreAuthorize(hasPermi = "system:role:query")
    @GetMapping("/optionselect")
    public AjaxResult optionselect()
    {
        return AjaxResult.success(roleService.selectRoleAll());
    }
}