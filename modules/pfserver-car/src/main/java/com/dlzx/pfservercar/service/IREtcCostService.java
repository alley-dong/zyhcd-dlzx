package com.dlzx.pfservercar.service;

import java.util.List;
import java.util.Map;

import com.dlzx.pfservercar.domain.EtcCardUsageRecordImport;
import com.dlzx.pfservercar.domain.REtcCost;

/**
 * 统计报-ETC费用统计Service接口
 *
 * @author dlzx
 * @date 2020-12-17
 */
public interface IREtcCostService {
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
     * 批量删除统计报-ETC费用统计
     *
     * @param ids 需要删除的统计报-ETC费用统计ID
     * @return 结果
     */
    public int deleteREtcCostByIds(String[] ids);

    /**
     * 删除统计报-ETC费用统计信息
     *
     * @param id 统计报-ETC费用统计ID
     * @return 结果
     */
    public int deleteREtcCostById(String id);

    //    导入
    public Map<String, String> importUser(List<REtcCost> recordList);
}
