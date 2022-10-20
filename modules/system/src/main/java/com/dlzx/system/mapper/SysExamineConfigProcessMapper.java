package com.dlzx.system.mapper;

import com.dlzx.system.domain.SysExamineConfigProcess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统设置
 *
 * @author guoyonggang
 */
@Mapper
public interface SysExamineConfigProcessMapper {

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
    public int updateSysExamineConfigProcess(SysExamineConfigProcess sysExamineConfigProcess);

    /**
     * 删除系统管理-审批流程配置
     *
     * @param sysExamineConfigProcess 系统管理-审批流程配置ID
     * @return 结果
     */
    public int deleteSysExamineConfigProcessById(SysExamineConfigProcess sysExamineConfigProcess);

    /**
     * 新增系统管理-审批流程配置
     *
     * @param sysExamineConfigProcess 系统管理-审批流程配置
     * @return 结果
     */
    public int insertSysExamineConfigProcess(SysExamineConfigProcess sysExamineConfigProcess);

    /**
     * 审批公共查询审批列表接口
     */
    public List<SysExamineConfigProcess> selectSysExamineConfigProcessByType(Long type);


    /**
     * 查询最大等级接口加1
     */
    public Long selectMaxRank(SysExamineConfigProcess sysExamineConfigProcess);

    /**
     * 查询SysExamineConfigProcess数据接口
     */
    public SysExamineConfigProcess selectSysExamineConfigProcessResult(String configId);

    /**
     * 查询最大等级接口
     */
    public Long selectMaxRanks(SysExamineConfigProcess sysExamineConfigProcess);


    /**
     * 真删除
    * */
    int deleteById(SysExamineConfigProcess sysExamineConfigProcess);

    SysExamineConfigProcess querySysExamineConfigProcessByType(@Param("type") Long type, @Param("id")String id, @Param("userId")String userId);
}
