package com.dlzx.pfserverperson.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.pfserverperson.mapper.SysDictDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.PContractMetricsMapper;
import com.dlzx.pfserverperson.domain.PContractMetrics;
import com.dlzx.pfserverperson.service.IPContractMetricsService;

/**
 * 合作方管理-合同评价指标Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-09
 */
@Service
public class PContractMetricsServiceImpl implements IPContractMetricsService 
{
    @Autowired
    private PContractMetricsMapper pContractMetricsMapper;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    /**
     * 查询合作方管理-合同评价指标
     * 
     * @param id 合作方管理-合同评价指标ID
     * @return 合作方管理-合同评价指标
     */
    @Override
    public PContractMetrics selectPContractMetricsById(String id)
    {
        PContractMetrics pContractMetrics = pContractMetricsMapper.selectPContractMetricsById(id);
        pContractMetrics.setTypeEnum(sysDictDataMapper.selectDictLabel("p_contract_metrics_type",pContractMetrics.getType().toString()));
        return pContractMetrics;
    }

    /**
     * 查询合作方管理-合同评价指标列表
     * 
     * @param pContractMetrics 合作方管理-合同评价指标
     * @return 合作方管理-合同评价指标
     */
    @Override
    public List<PContractMetrics> selectPContractMetricsList(PContractMetrics pContractMetrics)
    {
        return pContractMetricsMapper.selectPContractMetricsList(pContractMetrics);
    }

    /**
     * 新增合作方管理-合同评价指标
     * 
     * @param pContractMetrics 合作方管理-合同评价指标
     * @return 结果
     */
    @Override
    public int insertPContractMetrics(PContractMetrics pContractMetrics)
    {
        pContractMetrics.setCreateTime(DateUtils.getNowDate());
        return pContractMetricsMapper.insertPContractMetrics(pContractMetrics);
    }

    /**
     * 修改合作方管理-合同评价指标
     * 
     * @param pContractMetrics 合作方管理-合同评价指标
     * @return 结果
     */
    @Override
    public int updatePContractMetrics(PContractMetrics pContractMetrics)
    {
        pContractMetrics.setUpdateTime(DateUtils.getNowDate());
        return pContractMetricsMapper.updatePContractMetrics(pContractMetrics);
    }

    /**
     * 批量删除合作方管理-合同评价指标
     * 
     * @param ids 需要删除的合作方管理-合同评价指标ID
     * @return 结果
     */
    @Override
    public int deletePContractMetricsByIds(String[] ids)
    {
        return pContractMetricsMapper.deletePContractMetricsByIds(ids);
    }

    /**
     * 删除合作方管理-合同评价指标信息
     * 
     * @param id 合作方管理-合同评价指标ID
     * @return 结果
     */
    @Override
    public int deletePContractMetricsById(String id)
    {
        return pContractMetricsMapper.deletePContractMetricsById(id);
    }
}
