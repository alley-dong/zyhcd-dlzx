package com.dlzx.system.service;

import com.dlzx.system.domain.SysLog;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author dongchanglong
 * @date 2021-05-06
 */
public interface ISysLogService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public SysLog selectSysLogById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysLog 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SysLog> selectSysLogList(SysLog sysLog);

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysLog 【请填写功能名称】
     * @return 结果
     */
    public int insertSysLog(SysLog sysLog);

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysLog 【请填写功能名称】
     * @return 结果
     */
    public int updateSysLog(SysLog sysLog);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteSysLogByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteSysLogById(Long id);
}
