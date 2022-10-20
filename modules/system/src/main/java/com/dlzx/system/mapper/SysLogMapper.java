package com.dlzx.system.mapper;

import com.dlzx.system.domain.SysLog;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author dongchanglong
 * @date 2021-05-06
 */
public interface SysLogMapper 
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
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteSysLogById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysLogByIds(Long[] ids);
}
