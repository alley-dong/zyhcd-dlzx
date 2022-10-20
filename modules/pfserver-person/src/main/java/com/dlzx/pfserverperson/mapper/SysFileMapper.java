package com.dlzx.pfserverperson.mapper;

import java.util.List;
import com.dlzx.pfserverperson.domain.SysFile;
import org.apache.ibatis.annotations.Param;

/**
 * 文件Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-08
 */
public interface SysFileMapper 
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

    public int updateSysFileDel(@Param("id") String id, @Param("type") String type);
    /**
     * 删除文件
     * 
     * @param id 文件ID
     * @return 结果
     */
    public int deleteSysFileById(Long id);

    /**
     * 批量删除文件
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysFileByIds(Long[] ids);

    /**
     * 批量插入
     *
     * @param files
     * @return 结果
     */
    int insertList(List<SysFile> files);

    /**
     * 根据RelationId 删除
     *
     * @param relationId
     * @return 结果
     */
    void delelteFilesByRelationId(String relationId);

    /**
     * 根据RelationId 查询
     *
     * @param relationId
     * @return 结果
     */
    List<SysFile> queryFilesByRelationId(String relationId);

    List<SysFile> queryFilesByRelationIdByType(@Param("id") String id, @Param("type") String type);
}
