package com.dlzx.pfserverperson.service;

import java.util.List;

import com.dlzx.pfserverperson.domain.DTrainingUser;
import com.dlzx.pfserverperson.domain.DTrainingUserRecord;
import com.dlzx.pfserverperson.domain.SafeTrafficTransgression;
import com.dlzx.system.api.model.LoginUser;

/**
 * 安全管理-违章记录Service接口
 *
 * @author dlzx
 * @date 2020-12-10
 */
public interface ISafeTrafficTransgressionService {
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

    public int insertSafeTrafficTransgressionUrl(SafeTrafficTransgression safeTrafficTransgression, String Id, LoginUser userInfo);
    /**
     * 修改安全管理-违章记录
     *
     * @param safeTrafficTransgression 安全管理-违章记录
     * @return 结果
     */
    public int updateSafeTrafficTransgression(SafeTrafficTransgression safeTrafficTransgression);

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

    /**
     * @describe
     * @author DongCL
     * @date 2021-04-13 14:39
     * @param
     * @return
     */
    public int updateSafeTrafficTransgressionStatus(SafeTrafficTransgression safeTrafficTransgression);


}
