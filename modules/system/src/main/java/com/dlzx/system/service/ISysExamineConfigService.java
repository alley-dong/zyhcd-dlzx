package com.dlzx.system.service;

import com.dlzx.system.domain.SysExamineConfig;

import java.util.List;

/**
 * 系统管理-审批配置Service接口
 *
 * @author dlzx
 * @date 2020-12-11
 */

public interface ISysExamineConfigService {
    /**
     * 查询系统管理-审批配置列表
     *
     * @param sysExamineConfig 系统管理-审批配置
     * @return 系统管理-审批配置集合
     */
    public List<SysExamineConfig> selectSysExamineConfigList(SysExamineConfig sysExamineConfig);

    /**
     * 修改系统管理-审批配置
     *
     * @param sysExamineConfig 系统管理-审批配置
     * @return 结果
     */
    public int updateSysExamineConfig(SysExamineConfig sysExamineConfig);




}
