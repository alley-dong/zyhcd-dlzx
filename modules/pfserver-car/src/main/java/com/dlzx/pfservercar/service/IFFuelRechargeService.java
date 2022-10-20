package com.dlzx.pfservercar.service;

import java.util.List;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.pfservercar.domain.FFuelRecharge;

/**
 * 燃料管理-充电记录Service接口
 * 
 * @author dlzx
 * @date 2020-12-17
 */
public interface IFFuelRechargeService 
{
    /**
     * 查询燃料管理-充电记录
     * 
     * @param id 燃料管理-充电记录ID
     * @return 燃料管理-充电记录
     */
    public FFuelRecharge selectFFuelRechargeById(String id);

    /**
     * 查询燃料管理-充电记录列表
     * 
     * @param fFuelRecharge 燃料管理-充电记录
     * @return 燃料管理-充电记录集合
     */
    public List<FFuelRecharge> selectFFuelRechargeList(FFuelRecharge fFuelRecharge);

    /**
     * 新增燃料管理-充电记录
     * 
     * @param fFuelRecharge 燃料管理-充电记录
     * @return 结果
     */
    public int insertFFuelRecharge(FFuelRecharge fFuelRecharge);

    /**
     * 修改燃料管理-充电记录
     * 
     * @param fFuelRecharge 燃料管理-充电记录
     * @return 结果
     */
    public int updateFFuelRecharge(FFuelRecharge fFuelRecharge);

    /**
     * 批量删除燃料管理-充电记录
     * 
     * @param ids 需要删除的燃料管理-充电记录ID
     * @return 结果
     */
    public int deleteFFuelRechargeByIds(String[] ids);

    /**
     * 删除燃料管理-充电记录信息
     * 
     * @param id 燃料管理-充电记录ID
     * @return 结果
     */
    public int deleteFFuelRechargeById(String id);

    /**
     * 充电记录导入
    * */
    AjaxResult importFuelRecharge(List<FFuelRecharge> fFuelRecharges, String username);
}
