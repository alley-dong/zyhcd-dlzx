package com.dlzx.system.service.impl;

import java.util.List;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.system.domain.SysLog;
import com.dlzx.system.mapper.SysLogMapper;
import com.dlzx.system.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author dongchanglong
 * @date 2021-05-06
 */
@Service
public class SysLogServiceImpl implements ISysLogService
{
    @Autowired
    private SysLogMapper sysLogMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public SysLog selectSysLogById(Long id)
    {
        return sysLogMapper.selectSysLogById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysLog 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<SysLog> selectSysLogList(SysLog sysLog)
    {
        return sysLogMapper.selectSysLogList(sysLog);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysLog 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertSysLog(SysLog sysLog)
    {
        sysLog.setCreateTime(DateUtils.getNowDate());
        sysLog.setDeleteFlag(0);
        return sysLogMapper.insertSysLog(sysLog);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysLog 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateSysLog(SysLog sysLog)
    {
        return sysLogMapper.updateSysLog(sysLog);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteSysLogByIds(Long[] ids)
    {
        return sysLogMapper.deleteSysLogByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteSysLogById(Long id)
    {
        return sysLogMapper.deleteSysLogById(id);
    }
}
