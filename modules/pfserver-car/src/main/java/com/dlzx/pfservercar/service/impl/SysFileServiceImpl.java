package com.dlzx.pfservercar.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.SysFileMapper;
import com.dlzx.pfservercar.domain.SysFile;
import com.dlzx.pfservercar.service.ISysFileService;

/**
 * 文件Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-10
 */
@Service
public class SysFileServiceImpl implements ISysFileService 
{
    @Autowired
    private SysFileMapper sysFileMapper;

    /**
     * 查询文件
     * 
     * @param id 文件ID
     * @return 文件
     */
    @Override
    public SysFile selectSysFileById(Long id)
    {
        return sysFileMapper.selectSysFileById(id);
    }

    @Override
    public List<SysFile> selectSysFileByListId(String id)
    {
        return sysFileMapper.selectSysFileByListId(id);
    }

    /**
     * 查询文件列表
     * 
     * @param sysFile 文件
     * @return 文件
     */
    @Override
    public List<SysFile> selectSysFileList(SysFile sysFile)
    {
        return sysFileMapper.selectSysFileList(sysFile);
    }

    /**
     * 新增文件
     * 
     * @param sysFile 文件
     * @return 结果
     */
    @Override
    public int insertSysFile(SysFile sysFile)
    {
        sysFile.setCreateTime(DateUtils.getNowDate());
        return sysFileMapper.insertSysFile(sysFile);
    }

    /**
     * 修改文件
     * 
     * @param sysFile 文件
     * @return 结果
     */
    @Override
    public int updateSysFile(SysFile sysFile)
    {
        sysFile.setUpdateTime(DateUtils.getNowDate());
        return sysFileMapper.updateSysFile(sysFile);
    }

    /**
     * 批量删除文件
     * 
     * @param ids 需要删除的文件ID
     * @return 结果
     */
    @Override
    public int deleteSysFileByIds(Long[] ids)
    {
        return sysFileMapper.deleteSysFileByIds(ids);
    }

    /**
     * 删除文件信息
     * 
     * @param id 文件ID
     * @return 结果
     */
    @Override
    public int deleteSysFileById(Long id)
    {
        return sysFileMapper.deleteSysFileById(id);
    }

    /**
     * 批量插入数据
     */
    @Override
    public int insertSysfileBatch(List<SysFile> sysFiles){
        return sysFileMapper.insertList(sysFiles);
    }
}
