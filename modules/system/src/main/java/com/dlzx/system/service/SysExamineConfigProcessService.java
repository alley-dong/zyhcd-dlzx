package com.dlzx.system.service;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.system.domain.SysExamineConfigProcess;

import java.util.List;

/**
 * 用户 业务层
 *
 * @author dlzx
 */
public interface SysExamineConfigProcessService {
    /**
     * 查询系统管理-审批流程配置列表
     *
     * @param sysExamineConfigProcess 系统管理-审批流程配置
     * @return 系统管理-审批流程配置集合
     */
    public List<SysExamineConfigProcess> selectSysExamineConfigProcessList(SysExamineConfigProcess sysExamineConfigProcess);


    /**
     * 修改系统管理-审批流程配置
     *
     * @param sysExamineConfigProcess 系统管理-审批流程配置
     * @return 结果
     */
    public AjaxResult updateSysExamineConfigProcess(SysExamineConfigProcess sysExamineConfigProcess);

    /**
     * 删除系统管理-审批流程配置信息
     *
     * @param sysExamineConfigProcess 系统管理-审批流程配置ID
     * @return 结果
     */
    public AjaxResult deleteSysExamineConfigProcessById(SysExamineConfigProcess sysExamineConfigProcess);

    /**
     * 新增系统管理-审批流程配置
     *
     * @param sysExamineConfigProcess 系统管理-审批流程配置
     * @return 结果
     */
    public AjaxResult insertSysExamineConfigProcess(SysExamineConfigProcess sysExamineConfigProcess);

    /**
     * 根据类型查询此type的审批数据
     *
     * @param type 系统管理-审批流程配置
     * @return 结果
     */
    List<SysExamineConfigProcess> queryExamineConfigByType(Long type,String relationId);

}
