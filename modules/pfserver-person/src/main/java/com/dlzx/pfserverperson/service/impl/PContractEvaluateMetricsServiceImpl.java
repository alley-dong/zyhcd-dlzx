package com.dlzx.pfserverperson.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.pfserverperson.domain.PContract;
import com.dlzx.pfserverperson.domain.SysFile;
import com.dlzx.pfserverperson.mapper.SysDictDataMapper;
import com.dlzx.pfserverperson.mapper.SysFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.PContractEvaluateMetricsMapper;
import com.dlzx.pfserverperson.domain.PContractEvaluateMetrics;
import com.dlzx.pfserverperson.service.IPContractEvaluateMetricsService;

/**
 * 合作方管理-合同评价记录-指标Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-09
 */
@Service
public class PContractEvaluateMetricsServiceImpl implements IPContractEvaluateMetricsService 
{
    @Autowired
    private PContractEvaluateMetricsMapper pContractEvaluateMetricsMapper;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    /**
     * 查询合作方管理-合同评价记录-指标
     * 
     * @param id 合作方管理-合同评价记录-指标ID
     * @return 合作方管理-合同评价记录-指标
     */
    @Override
    public PContractEvaluateMetrics selectPContractEvaluateMetricsById(String id)
    {
        PContractEvaluateMetrics pContractEvaluateMetrics = pContractEvaluateMetricsMapper.selectPContractEvaluateMetricsById(id);
        pContractEvaluateMetrics.setTypeEnum(sysDictDataMapper.selectDictLabel("p_contract_metrics_type",pContractEvaluateMetrics.getType().toString()));
        return pContractEvaluateMetrics;
    }

    /**
     * 查询合作方管理-合同评价记录-指标列表
     * 
     * @param pContractEvaluateMetrics 合作方管理-合同评价记录-指标
     * @return 合作方管理-合同评价记录-指标
     */
    @Override
    public List<PContractEvaluateMetrics> selectPContractEvaluateMetricsList(PContractEvaluateMetrics pContractEvaluateMetrics)
    {
        return pContractEvaluateMetricsMapper.selectPContractEvaluateMetricsList(pContractEvaluateMetrics);
    }

    /**
     * 新增合作方管理-合同评价记录-指标
     * 
     * @param pContractEvaluateMetrics 合作方管理-合同评价记录-指标
     * @return 结果
     */
    @Override
    public int insertPContractEvaluateMetrics(PContractEvaluateMetrics pContractEvaluateMetrics)
    {
        pContractEvaluateMetrics.setCreateTime(DateUtils.getNowDate());
        return pContractEvaluateMetricsMapper.insertPContractEvaluateMetrics(pContractEvaluateMetrics);
    }

    /**
     * 修改合作方管理-合同评价记录-指标
     * 
     * @param pContractEvaluateMetrics 合作方管理-合同评价记录-指标
     * @return 结果
     */
    @Override
    public int updatePContractEvaluateMetrics(PContractEvaluateMetrics pContractEvaluateMetrics)
    {
        pContractEvaluateMetrics.setUpdateTime(DateUtils.getNowDate());
        return pContractEvaluateMetricsMapper.updatePContractEvaluateMetrics(pContractEvaluateMetrics);
    }

    /**
     * 批量删除合作方管理-合同评价记录-指标
     * 
     * @param ids 需要删除的合作方管理-合同评价记录-指标ID
     * @return 结果
     */
    @Override
    public int deletePContractEvaluateMetricsByIds(String[] ids)
    {
        return pContractEvaluateMetricsMapper.deletePContractEvaluateMetricsByIds(ids);
    }

    /**
     * 删除合作方管理-合同评价记录-指标信息
     * 
     * @param id 合作方管理-合同评价记录-指标ID
     * @return 结果
     */
    @Override
    public int deletePContractEvaluateMetricsById(String id)
    {
        return pContractEvaluateMetricsMapper.deletePContractEvaluateMetricsById(id);
    }
}
