package com.dlzx.pfserverperson.mapper;

import java.util.List;
import com.dlzx.pfserverperson.domain.PContractMetricsType;

/**
 * 合作方管理-合同评价指标类型Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-09
 */
public interface PContractMetricsTypeMapper 
{
    /**
     * 查询合作方管理-合同评价指标类型
     * 
     * @param id 合作方管理-合同评价指标类型ID
     * @return 合作方管理-合同评价指标类型
     */
    public PContractMetricsType selectPContractMetricsTypeById(String id);

    /**
     * 查询合作方管理-合同评价指标类型列表
     * 
     * @param pContractMetricsType 合作方管理-合同评价指标类型
     * @return 合作方管理-合同评价指标类型集合
     */
    public List<PContractMetricsType> selectPContractMetricsTypeList(PContractMetricsType pContractMetricsType);

    /**
     * 新增合作方管理-合同评价指标类型
     * 
     * @param pContractMetricsType 合作方管理-合同评价指标类型
     * @return 结果
     */
    public int insertPContractMetricsType(PContractMetricsType pContractMetricsType);

    /**
     * 修改合作方管理-合同评价指标类型
     * 
     * @param pContractMetricsType 合作方管理-合同评价指标类型
     * @return 结果
     */
    public int updatePContractMetricsType(PContractMetricsType pContractMetricsType);

    /**
     * 删除合作方管理-合同评价指标类型
     * 
     * @param id 合作方管理-合同评价指标类型ID
     * @return 结果
     */
    public int deletePContractMetricsTypeById(String id);

    /**
     * 批量删除合作方管理-合同评价指标类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePContractMetricsTypeByIds(String[] ids);
}
