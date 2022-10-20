package com.dlzx.pfservercar.mapper;

import java.util.List;

import com.dlzx.pfservercar.domain.REtcCost;
import com.dlzx.pfservercar.domain.RInformationSystemServiceCost;
import com.dlzx.pfservercar.domain.model.ERPCostModel;

/**
 * 统计报-信息系统服务费统计Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-22
 */
public interface RInformationSystemServiceCostMapper 
{
    /**
     * 查询统计报-信息系统服务费统计
     * 
     * @param id 统计报-信息系统服务费统计ID
     * @return 统计报-信息系统服务费统计
     */
    public RInformationSystemServiceCost selectRInformationSystemServiceCostById(String id);

    /**
     * 查询统计报-信息系统服务费统计列表
     * 
     * @param rInformationSystemServiceCost 统计报-信息系统服务费统计
     * @return 统计报-信息系统服务费统计集合
     */
    public List<RInformationSystemServiceCost> selectRInformationSystemServiceCostList(RInformationSystemServiceCost rInformationSystemServiceCost);

    /**
     * 新增统计报-信息系统服务费统计
     * 
     * @param rInformationSystemServiceCost 统计报-信息系统服务费统计
     * @return 结果
     */
    public int insertRInformationSystemServiceCost(RInformationSystemServiceCost rInformationSystemServiceCost);

    /**
     * 修改统计报-信息系统服务费统计
     * 
     * @param rInformationSystemServiceCost 统计报-信息系统服务费统计
     * @return 结果
     */
    public int updateRInformationSystemServiceCost(RInformationSystemServiceCost rInformationSystemServiceCost);

    /**
     * 删除统计报-信息系统服务费统计
     * 
     * @param id 统计报-信息系统服务费统计ID
     * @return 结果
     */
    public int deleteRInformationSystemServiceCostById(String id);

    /**
     * 批量删除统计报-信息系统服务费统计
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRInformationSystemServiceCostByIds(String[] ids);

    /**
     * @describe 信息系统服务ERP费用
     * @author DongCL
     * @date 2021-02-21 16:12
     * @param
     * @return
     */
    List<ERPCostModel> queryERPInformationSystemServiceCostList(ERPCostModel erpCostModel);
}
