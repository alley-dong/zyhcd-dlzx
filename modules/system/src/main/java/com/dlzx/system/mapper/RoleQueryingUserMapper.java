package com.dlzx.system.mapper;

import com.dlzx.system.api.domain.SysUser;
import com.dlzx.system.domain.SysExamineConfigProcess;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface RoleQueryingUserMapper {
    /**
     *通过角色查询用户
     */
    public List<SysUser> selectroleQueryingUserList(String roleId);



}
