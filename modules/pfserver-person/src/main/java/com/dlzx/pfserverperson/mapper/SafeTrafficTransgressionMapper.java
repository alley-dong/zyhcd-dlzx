package com.dlzx.pfserverperson.mapper;

import java.util.List;
import com.dlzx.pfserverperson.domain.SafeTrafficTransgression;
import com.dlzx.pfserverperson.domain.SafeTrafficTransgressionAp;

/**
 * 安全管理-违章记录Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-10
 */
public interface SafeTrafficTransgressionMapper 
{
    /**
     * 查询安全管理-违章记录
     * 
     * @param id 安全管理-违章记录ID
     * @return 安全管理-违章记录
     */
    public SafeTrafficTransgression selectSafeTrafficTransgressionById(String id);

    /**
     * 查询安全管理-违章记录列表
     * 
     * @param safeTrafficTransgression 安全管理-违章记录
     * @return 安全管理-违章记录集合
     */
    public List<SafeTrafficTransgression> selectSafeTrafficTransgressionList(SafeTrafficTransgression safeTrafficTransgression);

    public List<SafeTrafficTransgression> selectSafeTrafficTransgressionListDriver(SafeTrafficTransgression safeTrafficTransgression);

    //    查询驾驶员档案-违章记录表
    public List<SafeTrafficTransgression> selectSafeTrafficTransgressionListPerson(SafeTrafficTransgression safeTrafficTransgression);
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
    public int updateSafeTrafficTransgression(SafeTrafficTransgression safeTrafficTransgression);

    /**
     * 删除安全管理-违章记录
     * 
     * @param id 安全管理-违章记录ID
     * @return 结果
     */
    public int deleteSafeTrafficTransgressionById(String id);

    /**
     * 批量删除安全管理-违章记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSafeTrafficTransgressionByIds(String[] ids);
}
