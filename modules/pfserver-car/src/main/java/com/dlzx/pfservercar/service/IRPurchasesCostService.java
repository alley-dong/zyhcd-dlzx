package com.dlzx.pfservercar.service;

import java.util.List;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.pfservercar.domain.RPurchasesCost;

/**
 * 统计报-新购车辆临时号牌费用统计Service接口
 * 
 * @author ruoyi
 * @date 2021-02-21
 */
public interface IRPurchasesCostService 
{
    /**
     * 查询统计报-新购车辆临时号牌费用统计
     * 
     * @param id 统计报-新购车辆临时号牌费用统计ID
     * @return 统计报-新购车辆临时号牌费用统计
     */
    public RPurchasesCost selectRPurchasesCostById(String id);

    /**
     * 查询统计报-新购车辆临时号牌费用统计列表
     * 
     * @param rPurchasesCost 统计报-新购车辆临时号牌费用统计
     * @return 统计报-新购车辆临时号牌费用统计集合
     */
    public List<RPurchasesCost> selectRPurchasesCostList(RPurchasesCost rPurchasesCost);

    /**
     * 新增统计报-新购车辆临时号牌费用统计
     * 
     * @param rPurchasesCost 统计报-新购车辆临时号牌费用统计
     * @return 结果
     */
    public int insertRPurchasesCost(RPurchasesCost rPurchasesCost);

    /**
     * 修改统计报-新购车辆临时号牌费用统计
     * 
     * @param rPurchasesCost 统计报-新购车辆临时号牌费用统计
     * @return 结果
     */
    public int updateRPurchasesCost(RPurchasesCost rPurchasesCost);

    /**
     * 批量删除统计报-新购车辆临时号牌费用统计
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRPurchasesCostByIds(String ids);

    /**
     * 删除统计报-新购车辆临时号牌费用统计信息
     * 
     * @param id 统计报-新购车辆临时号牌费用统计ID
     * @return 结果
     */
    public int deleteRPurchasesCostById(String id);

    /**
     * @describe 导入
     * @author DongCL
     * @date 2021-02-22 14:04
     * @param
     * @return
     */
    AjaxResult importPurchasesCost(List<RPurchasesCost> collect, String username);
}
