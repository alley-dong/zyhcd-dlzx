package com.dlzx.pfserverperson.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.PCarrierMapper;
import com.dlzx.pfserverperson.domain.PCarrier;
import com.dlzx.pfserverperson.service.IPCarrierService;

/**
 * 合作方管理-供应商Service业务层处理
 * 
 * @author dlzx
 * @date 2020-11-26
 */
@Service
public class PCarrierServiceImpl implements IPCarrierService 
{
    @Autowired
    private PCarrierMapper pCarrierMapper;

    /**
     * 查询合作方管理-供应商
     * 
     * @param id 合作方管理-供应商ID
     * @return 合作方管理-供应商
     */
    @Override
    public PCarrier selectPCarrierById(String id)
    {
        return pCarrierMapper.selectPCarrierById(id);
    }

    /**
     * 查询合作方管理-供应商列表
     * 
     * @param pCarrier 合作方管理-供应商
     * @return 合作方管理-供应商
     */
    @Override
    public List<PCarrier> selectPCarrierList(PCarrier pCarrier)
    {
        return pCarrierMapper.selectPCarrierList(pCarrier);
    }

    /**
     * 新增合作方管理-供应商
     * 
     * @param pCarrier 合作方管理-供应商
     * @return 结果
     */
    @Override
    public int insertPCarrier(PCarrier pCarrier)
    {
        pCarrier.setCreateTime(DateUtils.getNowDate());
        return pCarrierMapper.insertPCarrier(pCarrier);
    }

    /**
     * 修改合作方管理-供应商
     * 
     * @param pCarrier 合作方管理-供应商
     * @return 结果
     */
    @Override
    public int updatePCarrier(PCarrier pCarrier)
    {
        pCarrier.setUpdateTime(DateUtils.getNowDate());
        return pCarrierMapper.updatePCarrier(pCarrier);
    }

    /**
     * 批量删除合作方管理-供应商
     * 
     * @param ids 需要删除的合作方管理-供应商ID
     * @return 结果
     */
    @Override
    public int deletePCarrierByIds(String[] ids)
    {
        return pCarrierMapper.deletePCarrierByIds(ids);
    }

    /**
     * 删除合作方管理-供应商信息
     * 
     * @param id 合作方管理-供应商ID
     * @return 结果
     */
    @Override
    public int deletePCarrierById(String id)
    {
        return pCarrierMapper.deletePCarrierById(id);
    }

    @Override
    public Boolean checkPCarrierName(String name) {
        List<PCarrier> carrierList = pCarrierMapper.checkPCarrierName(name);
        if (carrierList.size()>0){
            return false;
        }
        return true;
    }

    @Override
    public Boolean checkUpdatePCarrierName(String name,String id) {
        List<PCarrier> carrierList = pCarrierMapper.checkPCarrierName(name);
        if (carrierList.size()>0){
            if (carrierList.stream().filter(l -> !l.getId().equals(id)).collect(Collectors.toList()).size()>0){
                return false;
            }
        }
        return true;
    }
}
