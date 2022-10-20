package com.dlzx.pfserverperson.service;

import java.util.List;
import com.dlzx.pfserverperson.domain.PContractMetrics;

/**
 * 合作方管理-合同评价指标Service接口
 * 
 * @author dlzx
 * @date 2020-12-09
 */
public interface IPContractMetricsService 
{
    /**
     * 查询合作方管理-合同评价指标
     * 
     * @param id 合作方管理-合同评价指标ID
     * @return 合作方管理-合同评价指标
     */
    public PContractMetrics selectPContractMetricsById(String id);

    /**
     * 查询合作方管理-合同评价指标列表
     * 
     * @param pContractMetrics 合作方管理-合同评价指标
     * @return 合作方管理-合同评价指标集合
     */
    public List<PContractMetrics> selectPContractMetricsList(PContractMetrics pContractMetrics);

    /**
     * 新增合作方管理-合同评价指标
     * 
     * @param pContractMetrics 合作方管理-合同评价指标
     * @return 结果
     */
    public int insertPContractMetrics(PContractMetrics pContractMetrics);

    /**
     * 修改合作方管理-合同评价指标
     * 
     * @param pContractMetrics 合作方管理-合同评价指标
     * @return 结果
     */
    public int updatePContractMetrics(PContractMetrics pContractMetrics);

    /**
     * 批量删除合作方管理-合同评价指标
     * 
     * @param ids 需要删除的合作方管理-合同评价指标ID
     * @return 结果
     */
    public int deletePContractMetricsByIds(String[] ids);

    /**
     * 删除合作方管理-合同评价指标信息
     * 
     * @param id 合作方管理-合同评价指标ID
     * @return 结果
     */
    public int deletePContractMetricsById(String id);
}
