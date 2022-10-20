package com.dlzx.pfservercar.mapper;

import java.util.List;
import com.dlzx.pfservercar.domain.RPurchasesCost;
import com.dlzx.pfservercar.domain.model.ERPCostModel;

/**
 * 统计报-新购车辆临时号牌费用统计Mapper接口
 * 
 * @author ruoyi
 * @date 2021-02-21
 */
public interface RPurchasesCostMapper 
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
     * 删除统计报-新购车辆临时号牌费用统计
     * 
     * @param id 统计报-新购车辆临时号牌费用统计ID
     * @return 结果
     */
    public int deleteRPurchasesCostById(String id);

    /**
     * 批量删除统计报-新购车辆临时号牌费用统计
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRPurchasesCostByIds(String[] ids);


    /**
     * @describe 新购车辆临时号牌ERP费用
     * @author DongCL
     * @date 2021-02-21 16:12
     * @param
     * @return
     */
    List<ERPCostModel> queryERPPurchasesCostList(ERPCostModel erpCostModel);

    /**
     * @describe 批量插入
     * @author DongCL
     * @date 2021-02-22 14:13
     * @param
     * @return
     */
    int insertList(List<RPurchasesCost> rPurchasesCosts);
}
