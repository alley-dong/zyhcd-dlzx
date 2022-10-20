package com.dlzx.pfserverperson.mapper;

import java.util.List;
import com.dlzx.pfserverperson.domain.PContractEvaluate;

/**
 * 合作方管理-合同评价记录Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-09
 */
public interface PContractEvaluateMapper 
{
    /**
     * 查询合作方管理-合同评价记录
     * 
     * @param id 合作方管理-合同评价记录ID
     * @return 合作方管理-合同评价记录
     */
    public PContractEvaluate selectPContractEvaluateById(String id);

    /**
     * 查询合作方管理-合同评价记录列表
     * 
     * @param pContractEvaluate 合作方管理-合同评价记录
     * @return 合作方管理-合同评价记录集合
     */
    public List<PContractEvaluate> selectPContractEvaluateList(PContractEvaluate pContractEvaluate);

    /**
     * 新增合作方管理-合同评价记录
     * 
     * @param pContractEvaluate 合作方管理-合同评价记录
     * @return 结果
     */
    public int insertPContractEvaluate(PContractEvaluate pContractEvaluate);

    /**
     * 修改合作方管理-合同评价记录
     * 
     * @param pContractEvaluate 合作方管理-合同评价记录
     * @return 结果
     */
    public int updatePContractEvaluate(PContractEvaluate pContractEvaluate);

    /**
     * 删除合作方管理-合同评价记录
     * 
     * @param id 合作方管理-合同评价记录ID
     * @return 结果
     */
    public int deletePContractEvaluateById(String id);

    /**
     * 批量删除合作方管理-合同评价记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePContractEvaluateByIds(String[] ids);
}
