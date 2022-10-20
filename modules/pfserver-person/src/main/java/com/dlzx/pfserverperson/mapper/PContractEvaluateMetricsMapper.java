package com.dlzx.pfserverperson.mapper;

import java.util.List;
import com.dlzx.pfserverperson.domain.PContractEvaluateMetrics;

/**
 * 合作方管理-合同评价记录-指标Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-09
 */
public interface PContractEvaluateMetricsMapper 
{
    /**
     * 查询合作方管理-合同评价记录-指标
     * 
     * @param id 合作方管理-合同评价记录-指标ID
     * @return 合作方管理-合同评价记录-指标
     */
    public PContractEvaluateMetrics selectPContractEvaluateMetricsById(String id);

    /**
     * 查询合作方管理-合同评价记录-指标列表
     * 
     * @param pContractEvaluateMetrics 合作方管理-合同评价记录-指标
     * @return 合作方管理-合同评价记录-指标集合
     */
    public List<PContractEvaluateMetrics> selectPContractEvaluateMetricsList(PContractEvaluateMetrics pContractEvaluateMetrics);

    /**
     * 新增合作方管理-合同评价记录-指标
     * 
     * @param pContractEvaluateMetrics 合作方管理-合同评价记录-指标
     * @return 结果
     */
    public int insertPContractEvaluateMetrics(PContractEvaluateMetrics pContractEvaluateMetrics);

    /**
     * 修改合作方管理-合同评价记录-指标
     * 
     * @param pContractEvaluateMetrics 合作方管理-合同评价记录-指标
     * @return 结果
     */
    public int updatePContractEvaluateMetrics(PContractEvaluateMetrics pContractEvaluateMetrics);

    /**
     * 删除合作方管理-合同评价记录-指标
     * 
     * @param id 合作方管理-合同评价记录-指标ID
     * @return 结果
     */
    public int deletePContractEvaluateMetricsById(String id);

    /**
     * 批量删除合作方管理-合同评价记录-指标
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePContractEvaluateMetricsByIds(String[] ids);

    void insertList(List<PContractEvaluateMetrics> pContractEvaluateMetrics);
}
