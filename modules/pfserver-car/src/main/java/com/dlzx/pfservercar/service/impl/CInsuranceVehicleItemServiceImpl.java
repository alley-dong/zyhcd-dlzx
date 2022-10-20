package com.dlzx.pfservercar.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.CInsuranceVehicleItemMapper;
import com.dlzx.pfservercar.domain.CInsuranceVehicleItem;
import com.dlzx.pfservercar.service.ICInsuranceVehicleItemService;

/**
 * 车辆管理-车辆保险保险项Service业务层处理
 * 
 * @author dlzx
 * @date 2020-11-24
 */
@Service
public class CInsuranceVehicleItemServiceImpl implements ICInsuranceVehicleItemService 
{
    @Autowired
    private CInsuranceVehicleItemMapper cInsuranceVehicleItemMapper;

    /**
     * 查询车辆管理-车辆保险保险项
     * 
     * @param id 车辆管理-车辆保险保险项ID
     * @return 车辆管理-车辆保险保险项
     */
    @Override
    public CInsuranceVehicleItem selectCInsuranceVehicleItemById(String id)
    {
        return cInsuranceVehicleItemMapper.selectCInsuranceVehicleItemById(id);
    }

    /**
     * 查询车辆管理-车辆保险保险项列表
     * 
     * @param cInsuranceVehicleItem 车辆管理-车辆保险保险项
     * @return 车辆管理-车辆保险保险项
     */
    @Override
    public List<CInsuranceVehicleItem> selectCInsuranceVehicleItemList(CInsuranceVehicleItem cInsuranceVehicleItem)
    {
        return cInsuranceVehicleItemMapper.selectCInsuranceVehicleItemList(cInsuranceVehicleItem);
    }

    /**
     * 新增车辆管理-车辆保险保险项
     * 
     * @param cInsuranceVehicleItem 车辆管理-车辆保险保险项
     * @return 结果
     */
    @Override
    public int insertCInsuranceVehicleItem(CInsuranceVehicleItem cInsuranceVehicleItem)
    {
        cInsuranceVehicleItem.setCreateTime(DateUtils.getNowDate());
        return cInsuranceVehicleItemMapper.insertCInsuranceVehicleItem(cInsuranceVehicleItem);
    }

    /**
     * 修改车辆管理-车辆保险保险项
     * 
     * @param cInsuranceVehicleItem 车辆管理-车辆保险保险项
     * @return 结果
     */
    @Override
    public int updateCInsuranceVehicleItem(CInsuranceVehicleItem cInsuranceVehicleItem)
    {
        cInsuranceVehicleItem.setUpdateTime(DateUtils.getNowDate());
        return cInsuranceVehicleItemMapper.updateCInsuranceVehicleItem(cInsuranceVehicleItem);
    }

    /**
     * 批量删除车辆管理-车辆保险保险项
     * 
     * @param ids 需要删除的车辆管理-车辆保险保险项ID
     * @return 结果
     */
    @Override
    public int deleteCInsuranceVehicleItemByIds(String[] ids)
    {
        return cInsuranceVehicleItemMapper.deleteCInsuranceVehicleItemByIds(ids);
    }

    /**
     * 删除车辆管理-车辆保险保险项信息
     * 
     * @param id 车辆管理-车辆保险保险项ID
     * @return 结果
     */
    @Override
    public int deleteCInsuranceVehicleItemById(String id)
    {
        return cInsuranceVehicleItemMapper.deleteCInsuranceVehicleItemById(id);
    }

    @Override
    public int insertBatch(List<CInsuranceVehicleItem> item){
        return cInsuranceVehicleItemMapper.insertBatch(item);
    }
}
