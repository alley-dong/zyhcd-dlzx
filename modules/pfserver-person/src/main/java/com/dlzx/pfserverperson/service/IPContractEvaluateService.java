package com.dlzx.pfserverperson.service;

import java.util.List;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.pfserverperson.domain.PContractEvaluate;

/**
 * 合作方管理-合同评价记录Service接口
 * 
 * @author dlzx
 * @date 2020-12-09
 */
public interface IPContractEvaluateService 
{
    /**
     * 查询合作方管理-合同评价记录
     * 
     * @param pContractEvaluate 合作方管理-合同评价记录
     * @return 合作方管理-合同评价记录
     */
    public PContractEvaluate selectPContractEvaluateById(PContractEvaluate pContractEvaluate);

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
    public AjaxResult insertPContractEvaluate(PContractEvaluate pContractEvaluate);

    /**
     * 修改合作方管理-合同评价记录
     * 
     * @param pContractEvaluate 合作方管理-合同评价记录
     * @return 结果
     */
    public int updatePContractEvaluate(PContractEvaluate pContractEvaluate);

    /**
     * 批量删除合作方管理-合同评价记录
     * 
     * @param ids 需要删除的合作方管理-合同评价记录ID
     * @return 结果
     */
    public int deletePContractEvaluateByIds(String[] ids);

    /**
     * 删除合作方管理-合同评价记录信息
     * 
     * @param id 合作方管理-合同评价记录ID
     * @return 结果
     */
    public int deletePContractEvaluateById(String id);
}
