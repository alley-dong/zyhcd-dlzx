package com.dlzx.pfservercar.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.CInsuranceTypeItemMapper;
import com.dlzx.pfservercar.domain.CInsuranceTypeItem;
import com.dlzx.pfservercar.service.ICInsuranceTypeItemService;

/**
 * 车辆管理-险种项Service业务层处理
 * 
 * @author dlzx
 * @date 2020-11-23
 */
@Service
public class CInsuranceTypeItemServiceImpl implements ICInsuranceTypeItemService 
{
    @Autowired
    private CInsuranceTypeItemMapper cInsuranceTypeItemMapper;

    /**
     * 查询车辆管理-险种项
     * 
     * @param id 车辆管理-险种项ID
     * @return 车辆管理-险种项
     */
    @Override
    public CInsuranceTypeItem selectCInsuranceTypeItemById(String id)
    {
        return cInsuranceTypeItemMapper.selectCInsuranceTypeItemById(id);
    }

    /**
     * 查询车辆管理-险种项列表
     * 
     * @param cInsuranceTypeItem 车辆管理-险种项
     * @return 车辆管理-险种项
     */
    @Override
    public List<CInsuranceTypeItem> selectCInsuranceTypeItemList(CInsuranceTypeItem cInsuranceTypeItem)
    {
        return cInsuranceTypeItemMapper.selectCInsuranceTypeItemList(cInsuranceTypeItem);
    }

    /**
     * 新增车辆管理-险种项
     * 
     * @param cInsuranceTypeItem 车辆管理-险种项
     * @return 结果
     */
    @Override
    public int insertCInsuranceTypeItem(CInsuranceTypeItem cInsuranceTypeItem)
    {
        cInsuranceTypeItem.setCreateTime(DateUtils.getNowDate());
        return cInsuranceTypeItemMapper.insertCInsuranceTypeItem(cInsuranceTypeItem);
    }

    /**
     * 修改车辆管理-险种项
     * 
     * @param cInsuranceTypeItem 车辆管理-险种项
     * @return 结果
     */
    @Override
    public int updateCInsuranceTypeItem(CInsuranceTypeItem cInsuranceTypeItem)
    {
        cInsuranceTypeItem.setUpdateTime(DateUtils.getNowDate());
        return cInsuranceTypeItemMapper.updateCInsuranceTypeItem(cInsuranceTypeItem);
    }

    /**
     * 批量删除车辆管理-险种项
     * 
     * @param ids 需要删除的车辆管理-险种项ID
     * @return 结果
     */
    @Override
    public int deleteCInsuranceTypeItemByIds(String[] ids)
    {
        return cInsuranceTypeItemMapper.deleteCInsuranceTypeItemByIds(ids);
    }

    /**
     * 删除车辆管理-险种项信息
     * 
     * @param id 车辆管理-险种项ID
     * @return 结果
     */
    @Override
    public int deleteCInsuranceTypeItemById(String id)
    {
        return cInsuranceTypeItemMapper.deleteCInsuranceTypeItemById(id);
    }

    @Override
    public List<CInsuranceTypeItem> selectTypeList(CInsuranceTypeItem cInsuranceTypeItem){
        return cInsuranceTypeItemMapper.selectTypeList(cInsuranceTypeItem);
    }

    @Override
    public CInsuranceTypeItem selectRepeatTypeItem(CInsuranceTypeItem cInsuranceTypeItem){
        return cInsuranceTypeItemMapper.selectRepeatTypeItem(cInsuranceTypeItem);
    }
}
