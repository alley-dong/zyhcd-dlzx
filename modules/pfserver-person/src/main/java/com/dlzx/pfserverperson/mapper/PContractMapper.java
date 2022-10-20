package com.dlzx.pfserverperson.mapper;

import java.util.List;
import com.dlzx.pfserverperson.domain.PContract;

/**
 * 合作方管理-合同Mapper接口
 * 
 * @author dlzx
 * @date 2020-11-26
 */
public interface PContractMapper 
{
    /**
     * 查询合作方管理-合同
     * 
     * @param id 合作方管理-合同ID
     * @return 合作方管理-合同
     */
    public PContract selectPContractById(String id);

    /**
     * 查询合作方管理-合同列表
     * 
     * @param pContract 合作方管理-合同
     * @return 合作方管理-合同集合
     */
    public List<PContract> selectPContractList(PContract pContract);

    /**
     * 新增合作方管理-合同
     * 
     * @param pContract 合作方管理-合同
     * @return 结果
     */
    public int insertPContract(PContract pContract);

    /**
     * 修改合作方管理-合同
     * 
     * @param pContract 合作方管理-合同
     * @return 结果
     */
    public int updatePContract(PContract pContract);

    /**
     * 删除合作方管理-合同
     * 
     * @param id 合作方管理-合同ID
     * @return 结果
     */
    public int deletePContractById(String id);

    /**
     * 批量删除合作方管理-合同
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePContractByIds(String[] ids);


    /**
     * 修改客户合同列表标记/预警状态
     *
     * @param pContract 合作方管理-合同
     * @return 结果
     */
    int updateCustomerContractStatus(PContract pContract);
}
