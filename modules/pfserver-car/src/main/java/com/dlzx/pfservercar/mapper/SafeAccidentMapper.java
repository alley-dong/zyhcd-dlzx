package com.dlzx.pfservercar.mapper;

import java.util.List;

import com.dlzx.pfservercar.domain.SafeAccident;

/**
 * 安全管理-事故Mapper接口
 *
 * @author dlzx
 * @date 2020-12-08
 */
public interface SafeAccidentMapper {
    /**
     * 查询安全管理-事故
     *
     * @param id 安全管理-事故ID
     * @return 安全管理-事故
     */
    public SafeAccident selectSafeAccidentById(String id);

    /**
     * 查询安全管理-事故列表
     *
     * @param safeAccident 安全管理-事故
     * @return 安全管理-事故集合
     */
    public List<SafeAccident> selectSafeAccidentList(SafeAccident safeAccident);

    public List<SafeAccident> selectSafeAccidentListVehName(SafeAccident safeAccident);

    //查事故记录
    public List<SafeAccident> selectSafeAccidentListVehNameTu(SafeAccident safeAccident);

    public List<SafeAccident> selectSafeAccidentListVehNameAll(SafeAccident safeAccident);

    /**
     * 新增安全管理-事故
     *
     * @param safeAccident 安全管理-事故
     * @return 结果
     */
    public int insertSafeAccident(SafeAccident safeAccident);

    /**
     * 修改安全管理-事故
     *
     * @param safeAccident 安全管理-事故
     * @return 结果
     */
    public int updateSafeAccident(SafeAccident safeAccident);

    /**
     * 删除安全管理-事故
     *
     * @param id 安全管理-事故ID
     * @return 结果
     */
    public int deleteSafeAccidentById(String id);

    /**
     * 批量删除安全管理-事故
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSafeAccidentByIds(String[] ids);

    /**
     * 事故记录列表
     *
     * @param safeAccident 安全管理-事故
     * @return 结果
     */
    List<SafeAccident> querySafeAccidentList(SafeAccident safeAccident);

    /**
     * 事故审批
     */
    int checkSafeAccident(SafeAccident safeAccident);

    /**
     * 查看车牌号下的事故
     */
    List<SafeAccident> selectSafeAccidentByType(String vehicleId);
}
