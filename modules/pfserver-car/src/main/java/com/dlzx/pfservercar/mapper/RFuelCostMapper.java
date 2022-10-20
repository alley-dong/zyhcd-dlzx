package com.dlzx.pfservercar.mapper;

import java.util.List;
import com.dlzx.pfservercar.domain.RFuelCost;
import com.dlzx.pfservercar.domain.model.ERPCostModel;

/**
 * 统计报-燃料费用统计Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-15
 */
public interface RFuelCostMapper 
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
     * 删除统计报-燃料费用统计
     * 
     * @param id 统计报-燃料费用统计ID
     * @return 结果
     */
    public int deleteRFuelCostById(String id);

    /**
     * 批量删除统计报-燃料费用统计
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRFuelCostByIds(String[] ids);

    /**
     * 油品下拉列表
     * */
    List<String> queryOilPullDown();

    /**
     * 批量插入燃料
    * */
    int insertList(List<RFuelCost> rFuelCosts);

    /**
     * @describe 燃料ERP费用
     * @author DongCL
     * @date 2021-02-21 16:12
     * @param
     * @return
     */
    List<ERPCostModel> queryERPFuelCostList(ERPCostModel erpCostModel);
}
