package com.dlzx.pfservercar.mapper;

import java.util.List;
import com.dlzx.pfservercar.domain.CInsuranceVehicleItem;

/**
 * 车辆管理-车辆保险保险项Mapper接口
 * 
 * @author dlzx
 * @date 2020-11-24
 */
public interface CInsuranceVehicleItemMapper 
{
    /**
     * 查询车辆管理-车辆保险保险项
     * 
     * @param id 车辆管理-车辆保险保险项ID
     * @return 车辆管理-车辆保险保险项
     */
    public CInsuranceVehicleItem selectCInsuranceVehicleItemById(String id);

    /**
     * 查询车辆管理-车辆保险保险项列表
     * 
     * @param cInsuranceVehicleItem 车辆管理-车辆保险保险项
     * @return 车辆管理-车辆保险保险项集合
     */
    public List<CInsuranceVehicleItem> selectCInsuranceVehicleItemList(CInsuranceVehicleItem cInsuranceVehicleItem);

    /**
     * 新增车辆管理-车辆保险保险项
     * 
     * @param cInsuranceVehicleItem 车辆管理-车辆保险保险项
     * @return 结果
     */
    public int insertCInsuranceVehicleItem(CInsuranceVehicleItem cInsuranceVehicleItem);

    /**
     * 修改车辆管理-车辆保险保险项
     * 
     * @param cInsuranceVehicleItem 车辆管理-车辆保险保险项
     * @return 结果
     */
    public int updateCInsuranceVehicleItem(CInsuranceVehicleItem cInsuranceVehicleItem);

    /**
     * 删除车辆管理-车辆保险保险项
     * 
     * @param id 车辆管理-车辆保险保险项ID
     * @return 结果
     */
    public int deleteCInsuranceVehicleItemById(String id);

    /**
     * 批量删除车辆管理-车辆保险保险项
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCInsuranceVehicleItemByIds(String[] ids);

    /**
     * 批量新增
     */
    public int insertBatch(List<CInsuranceVehicleItem> item);

    public List<CInsuranceVehicleItem> selectCInsuranceItemByInsuranceId(String id);
}
