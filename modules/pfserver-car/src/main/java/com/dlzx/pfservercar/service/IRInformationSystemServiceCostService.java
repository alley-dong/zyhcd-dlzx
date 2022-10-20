package com.dlzx.pfservercar.service;

import java.util.List;
import java.util.Map;

import com.dlzx.pfservercar.domain.REtcCost;
import com.dlzx.pfservercar.domain.RInformationSystemServiceCost;

/**
 * 统计报-信息系统服务费统计Service接口
 * 
 * @author dlzx
 * @date 2020-12-22
 */
public interface IRInformationSystemServiceCostService 
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
     * 批量删除统计报-信息系统服务费统计
     * 
     * @param ids 需要删除的统计报-信息系统服务费统计ID
     * @return 结果
     */
    public int deleteRInformationSystemServiceCostByIds(String[] ids);

    /**
     * 删除统计报-信息系统服务费统计信息
     * 
     * @param id 统计报-信息系统服务费统计ID
     * @return 结果
     */
    public int deleteRInformationSystemServiceCostById(String id);


    public Map<String,String> importUser(List<RInformationSystemServiceCost> recordList);
}
