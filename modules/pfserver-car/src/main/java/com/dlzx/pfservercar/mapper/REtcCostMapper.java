package com.dlzx.pfservercar.mapper;

import java.util.List;
import com.dlzx.pfservercar.domain.REtcCost;
import com.dlzx.pfservercar.domain.model.ERPCostModel;

/**
 * 统计报-ETC费用统计Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-17
 */
public interface REtcCostMapper 
{
    /**
     * 查询统计报-ETC费用统计
     * 
     * @param id 统计报-ETC费用统计ID
     * @return 统计报-ETC费用统计
     */
    public REtcCost selectREtcCostById(String id);

    /**
     * 查询统计报-ETC费用统计列表
     * 
     * @param rEtcCost 统计报-ETC费用统计
     * @return 统计报-ETC费用统计集合
     */
    public List<REtcCost> selectREtcCostList(REtcCost rEtcCost);

    /**
     * 新增统计报-ETC费用统计
     * 
     * @param rEtcCost 统计报-ETC费用统计
     * @return 结果
     */
    public int insertREtcCost(REtcCost rEtcCost);

    /**
     * 修改统计报-ETC费用统计
     * 
     * @param rEtcCost 统计报-ETC费用统计
     * @return 结果
     */
    public int updateREtcCost(REtcCost rEtcCost);

    /**
     * 删除统计报-ETC费用统计
     * 
     * @param id 统计报-ETC费用统计ID
     * @return 结果
     */
    public int deleteREtcCostById(String id);

    /**
     * 批量删除统计报-ETC费用统计
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteREtcCostByIds(String[] ids);

    /**
     * @describe ETC ERP费用
     * @author DongCL
     * @date 2021-02-21 16:12
     * @param
     * @return
     */
    List<ERPCostModel> queryERPEtcCostList(ERPCostModel erpCostModel);

}
