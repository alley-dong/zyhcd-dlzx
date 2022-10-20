package com.dlzx.pfservercar.mapper;

import java.util.List;
import com.dlzx.pfservercar.domain.FFuelQuota;
import com.dlzx.pfservercar.domain.FFuelUsePlan;

/**
 * 燃料管理-燃油定额Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-02
 */
public interface FFuelQuotaMapper 
{
    /**
     * 查询燃料管理-燃油定额
     * 
     * @param id 燃料管理-燃油定额ID
     * @return 燃料管理-燃油定额
     */
    public FFuelQuota selectFFuelQuotaById(String id);

    /**
     * 查询燃料管理-燃油定额列表
     * 
     * @param fFuelQuota 燃料管理-燃油定额
     * @return 燃料管理-燃油定额集合
     */
    public List<FFuelQuota> selectFFuelQuotaList(FFuelQuota fFuelQuota);

    /**
     * 新增燃料管理-燃油定额
     * 
     * @param fFuelQuota 燃料管理-燃油定额
     * @return 结果
     */
    public int insertFFuelQuota(FFuelQuota fFuelQuota);

    /**
     * 修改燃料管理-燃油定额
     * 
     * @param fFuelQuota 燃料管理-燃油定额
     * @return 结果
     */
    public int updateFFuelQuota(FFuelQuota fFuelQuota);

    /**
     * 删除燃料管理-燃油定额
     * 
     * @param id 燃料管理-燃油定额ID
     * @return 结果
     */
    public int deleteFFuelQuotaById(String id);

    /**
     * 批量删除燃料管理-燃油定额
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFFuelQuotaByIds(String[] ids);

    /**
     * 根据车型和线路获取 定额
     *
     * @param fFuelUsePlan 燃料管理-燃油使用计划
     * @return 结果
     */
    List<FFuelQuota> queryModelAndRoute(FFuelUsePlan fFuelUsePlan);
}
