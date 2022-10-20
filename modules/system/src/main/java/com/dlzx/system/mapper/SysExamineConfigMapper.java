package com.dlzx.system.mapper;


import com.dlzx.system.domain.SysExamineConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SysExamineConfigMapper {

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
