package com.dlzx.job.mapper;

import com.dlzx.job.domain.CVehicleInspectMonthlyItem;

import java.util.List;

/**
 * 车辆管理-车辆定检记录车辆Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-31
 */
public interface CVehicleInspectMonthlyItemMapper 
{
    /**
     * 查询车辆管理-车辆定检记录车辆
     * 
     * @param id 车辆管理-车辆定检记录车辆ID
     * @return 车辆管理-车辆定检记录车辆
     */
    public CVehicleInspectMonthlyItem selectCVehicleInspectMonthlyItemById(String id);


    public String selectCVehicleInspectMonthlyItemByVehicleId(CVehicleInspectMonthlyItem cVehicleInspectMonthlyItem);

    /**
     * 查询车辆管理-车辆定检记录车辆列表
     * 
     * @param cVehicleInspectMonthlyItem 车辆管理-车辆定检记录车辆
     * @return 车辆管理-车辆定检记录车辆集合
     */
    public List<CVehicleInspectMonthlyItem> selectCVehicleInspectMonthlyItemList(CVehicleInspectMonthlyItem cVehicleInspectMonthlyItem);

    /**
     * 新增车辆管理-车辆定检记录车辆
     * 
     * @param cVehicleInspectMonthlyItem 车辆管理-车辆定检记录车辆
     * @return 结果
     */
    public int insertCVehicleInspectMonthlyItem(CVehicleInspectMonthlyItem cVehicleInspectMonthlyItem);

    /**
     * 批量添加
     * @param cVehicleInspectMonthlyItem
     * @return
     */
    public int insertList(List<CVehicleInspectMonthlyItem> cVehicleInspectMonthlyItem);
    /**
     * 修改车辆管理-车辆定检记录车辆
     * 
     * @param cVehicleInspectMonthlyItem 车辆管理-车辆定检记录车辆
     * @return 结果
     */
    public int updateCVehicleInspectMonthlyItem(CVehicleInspectMonthlyItem cVehicleInspectMonthlyItem);

    /**
     * 删除车辆管理-车辆定检记录车辆
     * 
     * @param id 车辆管理-车辆定检记录车辆ID
     * @return 结果
     */
    public int deleteCVehicleInspectMonthlyItemById(String id);

    /**
     * 批量删除车辆管理-车辆定检记录车辆
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCVehicleInspectMonthlyItemByIds(String[] ids);
}
