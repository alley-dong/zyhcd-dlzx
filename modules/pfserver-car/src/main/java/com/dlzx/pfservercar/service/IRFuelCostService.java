package com.dlzx.pfservercar.service;

import java.util.List;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.pfservercar.domain.RFuelCost;

/**
 * 统计报-燃料费用统计Service接口
 * 
 * @author dlzx
 * @date 2020-12-15
 */
public interface IRFuelCostService 
{
    /**
     * 查询统计报-燃料费用统计
     * 
     * @param id 统计报-燃料费用统计ID
     * @return 统计报-燃料费用统计
     */
    public RFuelCost selectRFuelCostById(String id);

    /**
     * 查询统计报-燃料费用统计列表
     * 
     * @param rFuelCost 统计报-燃料费用统计
     * @return 统计报-燃料费用统计集合
     */
    public List<RFuelCost> selectRFuelCostList(RFuelCost rFuelCost);

    /**
     * 新增统计报-燃料费用统计
     * 
     * @param rFuelCost 统计报-燃料费用统计
     * @return 结果
     */
    public int insertRFuelCost(RFuelCost rFuelCost);

    /**
     * 修改统计报-燃料费用统计
     * 
     * @param rFuelCost 统计报-燃料费用统计
     * @return 结果
     */
    public int updateRFuelCost(RFuelCost rFuelCost);

    /**
     * 批量删除统计报-燃料费用统计
     * 
     * @param ids 需要删除的统计报-燃料费用统计ID
     * @return 结果
     */
    public int deleteRFuelCostByIds(String[] ids);

    /**
     * 删除统计报-燃料费用统计信息
     * 
     * @param id 统计报-燃料费用统计ID
     * @return 结果
     */
    public int deleteRFuelCostById(String id);

    /**
     * 油品下拉列表
    * */
    List<String> queryOilPullDown();

    /**
     * 导入燃料
     * */
    AjaxResult importFuelCost(List<RFuelCost> rFuelCosts,String username);
}
