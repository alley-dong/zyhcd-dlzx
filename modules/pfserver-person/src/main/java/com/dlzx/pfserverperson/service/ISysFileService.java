package com.dlzx.pfserverperson.service;

import java.util.List;
import com.dlzx.pfserverperson.domain.SysFile;

/**
 * 文件Service接口
 * 
 * @author dlzx
 * @date 2020-12-10
 */
public interface ISysFileService 
{
    /**
     * 查询文件
     * 
     * @param id 文件ID
     * @return 文件
     */
    public SysFile selectSysFileById(Long id);

    public List<SysFile> selectSysFileByListId(String id);
    /**
     * 查询文件列表
     * 
     * @param sysFile 文件
     * @return 文件集合
     */
    public List<SysFile> selectSysFileList(SysFile sysFile);

    /**
     * 新增文件
     * 
     * @param sysFile 文件
     * @return 结果
     */
    public int insertSysFile(SysFile sysFile);


    /**
     * 修改文件
     * 
     * @param sysFile 文件
     * @return 结果
     */
    public int updateSysFile(SysFile sysFile);

    public int updateSysFileDel(String id,String type);
    /**
     * 批量删除文件
     * 
     * @param ids 需要删除的文件ID
     * @return 结果
     */
    public int deleteSysFileByIds(Long[] ids);

    /**
     * 删除文件信息
     * 
     * @param id 文件ID
     * @return 结果
     */
    public int deleteSysFileById(Long id);

    /**
     * 批量插入文件
     */
    public int insertSysfileBatch(List<SysFile> sysFiles);
}
