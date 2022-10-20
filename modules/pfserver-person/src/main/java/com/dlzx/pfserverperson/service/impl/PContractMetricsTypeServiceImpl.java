package com.dlzx.pfserverperson.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.PContractMetricsTypeMapper;
import com.dlzx.pfserverperson.domain.PContractMetricsType;
import com.dlzx.pfserverperson.service.IPContractMetricsTypeService;

/**
 * 合作方管理-合同评价指标类型Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-09
 */
@Service
public class PContractMetricsTypeServiceImpl implements IPContractMetricsTypeService 
{
    @Autowired
    private PContractMetricsTypeMapper pContractMetricsTypeMapper;

    /**
     * 查询合作方管理-合同评价指标类型
     * 
     * @param id 合作方管理-合同评价指标类型ID
     * @return 合作方管理-合同评价指标类型
     */
    @Override
    public PContractMetricsType selectPContractMetricsTypeById(String id)
    {
        return pContractMetricsTypeMapper.selectPContractMetricsTypeById(id);
    }

    /**
     * 查询合作方管理-合同评价指标类型列表
     * 
     * @param pContractMetricsType 合作方管理-合同评价指标类型
     * @return 合作方管理-合同评价指标类型
     */
    @Override
    public List<PContractMetricsType> selectPContractMetricsTypeList(PContractMetricsType pContractMetricsType)
    {
        return pContractMetricsTypeMapper.selectPContractMetricsTypeList(pContractMetricsType);
    }

    /**
     * 新增合作方管理-合同评价指标类型
     * 
     * @param pContractMetricsType 合作方管理-合同评价指标类型
     * @return 结果
     */
    @Override
    public int insertPContractMetricsType(PContractMetricsType pContractMetricsType)
    {
        pContractMetricsType.setCreateTime(DateUtils.getNowDate());
        return pContractMetricsTypeMapper.insertPContractMetricsType(pContractMetricsType);
    }

    /**
     * 修改合作方管理-合同评价指标类型
     * 
     * @param pContractMetricsType 合作方管理-合同评价指标类型
     * @return 结果
     */
    @Override
    public int updatePContractMetricsType(PContractMetricsType pContractMetricsType)
    {
        pContractMetricsType.setUpdateTime(DateUtils.getNowDate());
        return pContractMetricsTypeMapper.updatePContractMetricsType(pContractMetricsType);
    }

    /**
     * 批量删除合作方管理-合同评价指标类型
     * 
     * @param ids 需要删除的合作方管理-合同评价指标类型ID
     * @return 结果
     */
    @Override
    public int deletePContractMetricsTypeByIds(String[] ids)
    {
        return pContractMetricsTypeMapper.deletePContractMetricsTypeByIds(ids);
    }

    /**
     * 删除合作方管理-合同评价指标类型信息
     * 
     * @param id 合作方管理-合同评价指标类型ID
     * @return 结果
     */
    @Override
    public int deletePContractMetricsTypeById(String id)
    {
        return pContractMetricsTypeMapper.deletePContractMetricsTypeById(id);
    }
}
