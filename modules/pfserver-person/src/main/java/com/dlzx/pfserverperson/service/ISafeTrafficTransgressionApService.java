package com.dlzx.pfserverperson.service;

import com.dlzx.pfserverperson.domain.SafeTrafficTransgression;
import com.dlzx.pfserverperson.domain.SafeTrafficTransgressionAp;
import com.dlzx.pfserverperson.domain.SysFile;

import java.util.List;

/**
 * 安全管理-违章记录Service接口
 * 
 * @author dlzx
 * @date 2020-12-10
 */
public interface ISafeTrafficTransgressionApService
{
    /**
     * 查询安全管理-违章记录
     * 
     * @param id 安全管理-违章记录ID
     * @return 安全管理-违章记录
     */
    public SafeTrafficTransgressionAp selectSafeTrafficTransgressionById(String id);

    /**
     * 查询安全管理-违章记录列表
     * 
     * @param safeTrafficTransgression 安全管理-违章记录
     * @return 安全管理-违章记录集合
     */
    public List<SafeTrafficTransgression> selectSafeTrafficTransgressionList(SafeTrafficTransgression safeTrafficTransgression);

    public List<SafeTrafficTransgressionAp> selectSafeTrafficTransgressionListDriver(SafeTrafficTransgressionAp safeTrafficTransgression);
    /**
     * 新增安全管理-违章记录
     * 
     * @param safeTrafficTransgression 安全管理-违章记录
     * @return 结果
     */
    public int insertSafeTrafficTransgression(SafeTrafficTransgression safeTrafficTransgression);

    /**
     * 修改安全管理-违章记录
     * 
     * @param safeTrafficTransgression 安全管理-违章记录
     * @return 结果
     */
    public int updateSafeTrafficTransgression(SafeTrafficTransgressionAp safeTrafficTransgression);

    public int updateSafeTrafficTransgressionUrl(SafeTrafficTransgressionAp safeTrafficTransgression,List<SysFile> list);
    /**
     * 批量删除安全管理-违章记录
     * 
     * @param ids 需要删除的安全管理-违章记录ID
     * @return 结果
     */
    public int deleteSafeTrafficTransgressionByIds(String[] ids);

    /**
     * 删除安全管理-违章记录信息
     * 
     * @param id 安全管理-违章记录ID
     * @return 结果
     */
    public int deleteSafeTrafficTransgressionById(String id);
}
