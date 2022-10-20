package com.dlzx.pfserverperson.mapper;

import java.util.List;
import com.dlzx.pfserverperson.domain.PCarrier;

/**
 * 合作方管理-供应商Mapper接口
 * 
 * @author dlzx
 * @date 2020-11-26
 */
public interface PCarrierMapper 
{
    /**
     * 查询合作方管理-供应商
     * 
     * @param id 合作方管理-供应商ID
     * @return 合作方管理-供应商
     */
    public PCarrier selectPCarrierById(String id);

    /**
     * 查询合作方管理-供应商列表
     * 
     * @param pCarrier 合作方管理-供应商
     * @return 合作方管理-供应商集合
     */
    public List<PCarrier> selectPCarrierList(PCarrier pCarrier);

    /**
     * 新增合作方管理-供应商
     * 
     * @param pCarrier 合作方管理-供应商
     * @return 结果
     */
    public int insertPCarrier(PCarrier pCarrier);

    /**
     * 修改合作方管理-供应商
     * 
     * @param pCarrier 合作方管理-供应商
     * @return 结果
     */
    public int updatePCarrier(PCarrier pCarrier);

    /**
     * 删除合作方管理-供应商
     * 
     * @param id 合作方管理-供应商ID
     * @return 结果
     */
    public int deletePCarrierById(String id);

    /**
     * 批量删除合作方管理-供应商
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePCarrierByIds(String[] ids);

    List<PCarrier> checkPCarrierName(String name);
}
