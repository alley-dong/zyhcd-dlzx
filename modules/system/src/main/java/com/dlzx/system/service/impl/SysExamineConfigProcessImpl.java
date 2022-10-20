package com.dlzx.system.service.impl;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.system.api.domain.SysRole;
import com.dlzx.system.api.domain.SysUser;
import com.dlzx.system.api.model.LoginUser;
import com.dlzx.system.domain.SysExamineConfig;
import com.dlzx.system.domain.SysExamineConfigProcess;
import com.dlzx.system.domain.SysExamineTask;
import com.dlzx.system.mapper.SysExamineConfigProcessMapper;
import com.dlzx.system.mapper.SysExamineTaskMapper;
import com.dlzx.system.service.ISysExamineConfigService;
import com.dlzx.system.service.SysExamineConfigProcessService;
import com.dlzx.system.service.SysUserRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SysExamineConfigProcessImpl implements SysExamineConfigProcessService {

    @Autowired
    SysExamineConfigProcessMapper sysExamineConfigProcessMapper;

    @Autowired
    private ISysExamineConfigService sysExamineConfigService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysExamineTaskMapper sysExamineTaskMapper;

    @Override
    public List<SysExamineConfigProcess> selectSysExamineConfigProcessList(SysExamineConfigProcess sysExamineConfigProcess) {
        return sysExamineConfigProcessMapper.selectSysExamineConfigProcessList(sysExamineConfigProcess);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updateSysExamineConfigProcess(SysExamineConfigProcess sysExamineConfigProcess) {

        if (sysExamineConfigProcess.getType()==10 || sysExamineConfigProcess.getType()==11 || sysExamineConfigProcess.getType()==8 || sysExamineConfigProcess.getType()==6){
            // 如果选了角色，未选择审批人则提示
            if (!StringUtils.isNull(sysExamineConfigProcess.getRoleId()) && StringUtils.isNull(sysExamineConfigProcess.getUserId())){
                return AjaxResult.error("请选择审批人！");
            }
        }
        try {
            LoginUser userInfo = tokenService.getLoginUser();
            // 删除重新添加
            sysExamineConfigProcessMapper.deleteById(sysExamineConfigProcess);
            sysExamineConfigProcess.setUpdateBy(userInfo.getUsername());
            sysExamineConfigProcess.setUpdateTime(new Date());
            int updateSysExamineConfigProcessResult = sysExamineConfigProcessMapper.insertSysExamineConfigProcess(sysExamineConfigProcess);
            //修改sys_examine_config_process数据
//            int updateSysExamineConfigProcessResult = sysExamineConfigProcessMapper.updateSysExamineConfigProcess(sysExamineConfigProcess);
            //查询sys_examine_config_process修改后的数据
            SysExamineConfigProcess sysExamineConfigProcesss = sysExamineConfigProcessMapper.selectSysExamineConfigProcessResult(sysExamineConfigProcess.getConfigId());

            SysExamineConfig sysExamineConfig = new SysExamineConfig();
            sysExamineConfig.setRoleName(sysExamineConfigProcesss.getRoleName());
            sysExamineConfig.setCcRoleName(sysExamineConfigProcesss.getCcRoleName()==null? " " :sysExamineConfigProcesss.getCcRoleName());
            sysExamineConfig.setUpdateBy(userInfo.getUsername());
            sysExamineConfig.setUpdateTime(new Date());
            sysExamineConfig.setId(sysExamineConfigProcesss.getConfigId());
            //修改sys_examine_config数据
            int updateSysExamineConfigresult = sysExamineConfigService.updateSysExamineConfig(sysExamineConfig);

            if (updateSysExamineConfigProcessResult > 0 && updateSysExamineConfigresult > 0) {
                return AjaxResult.success();
            } else {
                return AjaxResult.error();
            }
        } catch (Exception e) {
            throw e;
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult deleteSysExamineConfigProcessById(SysExamineConfigProcess sysExamineConfigProcess) {
        try {

            LoginUser userInfo = tokenService.getLoginUser();

            // 根据type 和 关联表id 查询 最低等级
            Long selectMaxRankResult = sysExamineConfigProcessMapper.selectMaxRanks(sysExamineConfigProcess);
            if (sysExamineConfigProcess.getLevel().longValue() == selectMaxRankResult.longValue() && !(sysExamineConfigProcess.getLevel()==1L)) {
                int deleteSysExamineConfigProcessResult = sysExamineConfigProcessMapper.deleteSysExamineConfigProcessById(sysExamineConfigProcess);
                SysExamineConfigProcess sysExamineConfigProcesss = sysExamineConfigProcessMapper.selectSysExamineConfigProcessResult(sysExamineConfigProcess.getConfigId());
                SysExamineConfig sysExamineConfig = new SysExamineConfig();
                sysExamineConfig.setId(sysExamineConfigProcesss.getConfigId());
                sysExamineConfig.setLevel(sysExamineConfigProcesss.getLevel());
                sysExamineConfig.setRoleName(sysExamineConfigProcesss.getRoleName());
                sysExamineConfig.setCcRoleName(sysExamineConfigProcesss.getCcRoleName());
                sysExamineConfig.setUpdateBy(userInfo.getUsername());
                sysExamineConfig.setUpdateTime(new Date());
                int updateSysExamineConfigResult = sysExamineConfigService.updateSysExamineConfig(sysExamineConfig);
                if (deleteSysExamineConfigProcessResult > 0 && updateSysExamineConfigResult > 0) {
                    return AjaxResult.success();
                } else {
                    return AjaxResult.error("删除审批级别要从最低级别开始，最高级别1级不可删除！");
                }
            } else {
                return AjaxResult.error("删除审批级别要从最低级别开始，最高级别1级不可删除！");
            }
        } catch (Exception e) {
            throw e;
        }


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult insertSysExamineConfigProcess(SysExamineConfigProcess sysExamineConfigProcess) {
        if (sysExamineConfigProcess.getType()==10 || sysExamineConfigProcess.getType()==11 || sysExamineConfigProcess.getType()==8 || sysExamineConfigProcess.getType()==6){
            // 如果选了角色，未选择审批人则提示
            if (!StringUtils.isNull(sysExamineConfigProcess.getRoleId()) && StringUtils.isNull(sysExamineConfigProcess.getUserId())){
                return AjaxResult.error("请选择审批人！");
            }
        }
        try {
            // 获取当前级别（原级别+1）
            Long maxRankLevelResult = sysExamineConfigProcessMapper.selectMaxRank(sysExamineConfigProcess);
            LoginUser userInfo = tokenService.getLoginUser();
            sysExamineConfigProcess.setCreateBy(userInfo.getUsername());
            sysExamineConfigProcess.setCreateTime(new Date());
            sysExamineConfigProcess.setId(UUID.randomUUID() + "");
            sysExamineConfigProcess.setLevel(maxRankLevelResult==null?1:maxRankLevelResult);
            // 插入一条记录
            int insertSysExamineConfigProcessResult = sysExamineConfigProcessMapper.insertSysExamineConfigProcess(sysExamineConfigProcess);
            // 查询审批配置
            SysExamineConfigProcess sysExamineConfigProcesss = sysExamineConfigProcessMapper.selectSysExamineConfigProcessResult(sysExamineConfigProcess.getConfigId());

            // 更新审批配置
            SysExamineConfig sysExamineConfig = new SysExamineConfig();
            sysExamineConfig.setId(sysExamineConfigProcesss.getConfigId());
            sysExamineConfig.setLevel(sysExamineConfigProcesss.getLevel());
            sysExamineConfig.setRoleName(sysExamineConfigProcesss.getRoleName());
            sysExamineConfig.setCcRoleName(sysExamineConfigProcesss.getCcRoleName());
            sysExamineConfig.setUpdateBy(userInfo.getUsername());
            sysExamineConfig.setUpdateTime(new Date());
            int insertSysExamineConfigResult = sysExamineConfigService.updateSysExamineConfig(sysExamineConfig);
            if (insertSysExamineConfigProcessResult > 0 && insertSysExamineConfigResult > 0) {
                return AjaxResult.success();
            } else {
                return AjaxResult.error();
            }
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<SysExamineConfigProcess> queryExamineConfigByType(Long type,String relationId) {

        // 获取审批人列表
        List<SysExamineConfigProcess> sysExamineConfigProcesses = sysExamineConfigProcessMapper.selectSysExamineConfigProcessByType(type);

        // update by 2021 4.9 要求维保重新提交回显抄送人数据
        List<SysExamineTask> sysExamineTasks = new ArrayList<>();
        if (StringUtils.isNotEmpty(relationId)){
            SysExamineTask sysExamineTask = new SysExamineTask();
            sysExamineTask.setRelationId(relationId);
            sysExamineTask.setType(type);
            sysExamineTasks = sysExamineTaskMapper.selectSysExamineTaskList(sysExamineTask);
        }
        for (SysExamineConfigProcess sysExamineConfigProcess : sysExamineConfigProcesses) {

            if (sysExamineTasks.size()>0){
                // 抄送人
                sysExamineConfigProcess.setCcUserName(sysExamineTasks.stream().filter(s -> s.getLevel().equals(sysExamineConfigProcess.getLevel()) && 2 == s.getUserType()).findFirst().orElse(new SysExamineTask()).getUserName());
                sysExamineConfigProcess.setCcUserId(sysExamineTasks.stream().filter(s -> s.getLevel().equals(sysExamineConfigProcess.getLevel()) && 2 == s.getUserType()).findFirst().orElse(new SysExamineTask()).getUserId());

                // 审批人
                sysExamineConfigProcess.setUserName(sysExamineTasks.stream().filter(s -> s.getLevel().equals(sysExamineConfigProcess.getLevel()) && 1 == s.getUserType()).findFirst().orElse(new SysExamineTask()).getUserName());
                sysExamineConfigProcess.setUserId(sysExamineTasks.stream().filter(s -> s.getLevel().equals(sysExamineConfigProcess.getLevel()) && 1 == s.getUserType()).findFirst().orElse(new SysExamineTask()).getUserId());
            }
            // 如果角色不为空，但是用户ID为空则给前端返回该角色下所有的用户
            if (StringUtils.isNotNull(sysExamineConfigProcess.getRoleId()) && StringUtils.isNull(sysExamineConfigProcess.getUserId())) {
                // 审批人列表
                List<SysUser> sysUserList = new ArrayList<>();
                List<SysUser> sysUsers = sysUserRoleService.selectSysUserList(sysExamineConfigProcess.getRoleId() + "");
                for (SysUser sysUser : sysUsers) {
                    sysUser.setLevel(sysExamineConfigProcess.getLevel());
                }
                sysUserList.addAll(sysUsers);
                sysExamineConfigProcess.setSysUserList(sysUserList);
            }
            if (StringUtils.isNotNull(sysExamineConfigProcess.getCcRoleId()) && StringUtils.isNull(sysExamineConfigProcess.getCcUserId())) {
                // 抄送人列表
                List<SysUser> sysCcUserList = new ArrayList<>();
                List<SysUser> sysCcUsers = sysUserRoleService.selectSysUserList(sysExamineConfigProcess.getCcRoleId() + "");
                for (SysUser sysUser : sysCcUsers) {
                    sysUser.setLevel(sysExamineConfigProcess.getLevel());
                }
                sysCcUserList.addAll(sysCcUsers);
                sysExamineConfigProcess.setSysCcUserList(sysCcUserList);
            }
        }
        return sysExamineConfigProcesses;
    }

}
