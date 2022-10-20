package com.dlzx.system.service;

import com.dlzx.system.api.domain.SysUser;
import com.dlzx.system.domain.SysExamineConfigProcess;

import java.util.List;

public interface SysUserRoleService {
    /**
     * 通过角色查询用户
     * @param roleId
     * @return
     */
    public List<SysUser> selectSysUserList(String roleId);



}
