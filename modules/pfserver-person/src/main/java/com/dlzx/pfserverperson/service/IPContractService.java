package com.dlzx.pfserverperson.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.dlzx.pfserverperson.domain.PContract;

/**
 * 合作方管理-合同Service接口
 * 
 * @author dlzx
 * @date 2020-11-26
 */
public interface IPContractService 
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
     * 批量删除合作方管理-合同
     * 
     * @param ids 需要删除的合作方管理-合同ID
     * @return 结果
     */
    public int deletePContractByIds(String[] ids);

    /**
     * 删除合作方管理-合同信息
     * 
     * @param id 合作方管理-合同ID
     * @return 结果
     */
    public int deletePContractById(String id);

    /**
    * 客户/供应商合同页面 标记/取消预警
    * */
    int updateCustomerContractStatus(PContract pContract);

    /**
    * 计算合同到期时间
    * */
    void checkMonth(PContract item, SimpleDateFormat df, Date now) throws ParseException;
}
