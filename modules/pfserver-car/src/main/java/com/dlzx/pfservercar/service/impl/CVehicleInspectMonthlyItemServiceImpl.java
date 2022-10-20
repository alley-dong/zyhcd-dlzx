package com.dlzx.pfservercar.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.CVehicleInspectMonthlyItemMapper;
import com.dlzx.pfservercar.domain.CVehicleInspectMonthlyItem;
import com.dlzx.pfservercar.service.ICVehicleInspectMonthlyItemService;

/**
 * 车辆管理-车辆定检记录车辆Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-31
 */
@Service
public class CVehicleInspectMonthlyItemServiceImpl implements ICVehicleInspectMonthlyItemService 
{
    @Autowired
    private CVehicleInspectMonthlyItemMapper cVehicleInspectMonthlyItemMapper;

    /**
     * 查询车辆管理-车辆定检记录车辆
     * 
     * @param id 车辆管理-车辆定检记录车辆ID
     * @return 车辆管理-车辆定检记录车辆
     */
    @Override
    public CVehicleInspectMonthlyItem selectCVehicleInspectMonthlyItemById(String id)
    {
        return cVehicleInspectMonthlyItemMapper.selectCVehicleInspectMonthlyItemById(id);
    }

    @Override
    public String selectCVehicleInspectMonthlyItemByVehicleId(CVehicleInspectMonthlyItem cVehicleInspectMonthlyItem) {
        return cVehicleInspectMonthlyItemMapper.selectCVehicleInspectMonthlyItemByVehicleId(cVehicleInspectMonthlyItem);
    }

    /**
     * 查询车辆管理-车辆定检记录车辆列表
     * 
     * @param cVehicleInspectMonthlyItem 车辆管理-车辆定检记录车辆
     * @return 车辆管理-车辆定检记录车辆
     */
    @Override
    public List<CVehicleInspectMonthlyItem> selectCVehicleInspectMonthlyItemList(CVehicleInspectMonthlyItem cVehicleInspectMonthlyItem)
    {
        return cVehicleInspectMonthlyItemMapper.selectCVehicleInspectMonthlyItemList(cVehicleInspectMonthlyItem);
    }

    /**
     * 新增车辆管理-车辆定检记录车辆
     * 
     * @param cVehicleInspectMonthlyItem 车辆管理-车辆定检记录车辆
     * @return 结果
     */
    @Override
    public int insertCVehicleInspectMonthlyItem(CVehicleInspectMonthlyItem cVehicleInspectMonthlyItem)
    {
        cVehicleInspectMonthlyItem.setCreateTime(DateUtils.getNowDate());
        return cVehicleInspectMonthlyItemMapper.insertCVehicleInspectMonthlyItem(cVehicleInspectMonthlyItem);
    }

    @Override
    public int insertList(List<CVehicleInspectMonthlyItem> cVehicleInspectMonthlyItem) {
        return cVehicleInspectMonthlyItemMapper.insertList(cVehicleInspectMonthlyItem);
    }

    /**
     * 修改车辆管理-车辆定检记录车辆
     * 
     * @param cVehicleInspectMonthlyItem 车辆管理-车辆定检记录车辆
     * @return 结果
     */
    @Override
    public int updateCVehicleInspectMonthlyItem(CVehicleInspectMonthlyItem cVehicleInspectMonthlyItem)
    {
        cVehicleInspectMonthlyItem.setUpdateTime(DateUtils.getNowDate());
        return cVehicleInspectMonthlyItemMapper.updateCVehicleInspectMonthlyItem(cVehicleInspectMonthlyItem);
    }

    /**
     * 批量删除车辆管理-车辆定检记录车辆
     * 
     * @param ids 需要删除的车辆管理-车辆定检记录车辆ID
     * @return 结果
     */
    @Override
    public int deleteCVehicleInspectMonthlyItemByIds(String[] ids)
    {
        return cVehicleInspectMonthlyItemMapper.deleteCVehicleInspectMonthlyItemByIds(ids);
    }

    /**
     * 删除车辆管理-车辆定检记录车辆信息
     * 
     * @param id 车辆管理-车辆定检记录车辆ID
     * @return 结果
     */
    @Override
    public int deleteCVehicleInspectMonthlyItemById(String id)
    {
        return cVehicleInspectMonthlyItemMapper.deleteCVehicleInspectMonthlyItemById(id);
    }
}
