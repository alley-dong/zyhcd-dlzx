package com.dlzx.system.service.impl;

import com.dlzx.system.api.domain.SysUser;
import com.dlzx.system.domain.SysExamineConfigProcess;
import com.dlzx.system.mapper.RoleQueryingUserMapper;
import com.dlzx.system.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    RoleQueryingUserMapper roleQueryingUserMapper;
    @Override
    /**
     * 通过角色查询用户
     * @param roleId
     * @return
     */
    public List<SysUser> selectSysUserList(String roleId) {
        return roleQueryingUserMapper.selectroleQueryingUserList(roleId);
    }



}
