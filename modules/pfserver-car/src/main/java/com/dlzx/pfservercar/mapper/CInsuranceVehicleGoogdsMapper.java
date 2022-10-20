package com.dlzx.pfservercar.mapper;

import java.util.List;
import com.dlzx.pfservercar.domain.CInsuranceVehicleGoogds;

/**
 * 车辆管理-货物保险-车辆Mapper接口
 * 
 * @author dlzx
 * @date 2020-11-27
 */
public interface CInsuranceVehicleGoogdsMapper 
{
    /**
     * 查询车辆管理-货物保险-车辆
     * 
     * @param id 车辆管理-货物保险-车辆ID
     * @return 车辆管理-货物保险-车辆
     */
    public CInsuranceVehicleGoogds selectCInsuranceVehicleGoogdsById(String id);

    /**
     * 查询车辆管理-货物保险-车辆列表
     * 
     * @param cInsuranceVehicleGoogds 车辆管理-货物保险-车辆
     * @return 车辆管理-货物保险-车辆集合
     */
    public List<CInsuranceVehicleGoogds> selectCInsuranceVehicleGoogdsList(CInsuranceVehicleGoogds cInsuranceVehicleGoogds);

    /**
     * 获取详细信息
     * @param id
     * @return
     */
    public CInsuranceVehicleGoogds selectCInsuranceVehicleGoogdInfo(String id);

    /**
     * 查询车辆管理-货物保险-车辆列表
     *
     * @param cInsuranceVehicleGoogds 车辆管理-货物保险-车辆
     * @return 车辆管理-货物保险-车辆集合
     */
    public List<CInsuranceVehicleGoogds> selectCInsuranceVehicleGoogdAndCIV(CInsuranceVehicleGoogds cInsuranceVehicleGoogds);

    /**
     * 新增车辆管理-货物保险-车辆
     * 
     * @param cInsuranceVehicleGoogds 车辆管理-货物保险-车辆
     * @return 结果
     */
    public int insertCInsuranceVehicleGoogds(CInsuranceVehicleGoogds cInsuranceVehicleGoogds);

    /**
     * 修改车辆管理-货物保险-车辆
     * 
     * @param cInsuranceVehicleGoogds 车辆管理-货物保险-车辆
     * @return 结果
     */
    public int updateCInsuranceVehicleGoogds(CInsuranceVehicleGoogds cInsuranceVehicleGoogds);

    /**
     * 删除车辆管理-货物保险-车辆
     * 
     * @param id 车辆管理-货物保险-车辆ID
     * @return 结果
     */
    public int deleteCInsuranceVehicleGoogdsById(String id);

    /**
     * 批量删除车辆管理-货物保险-车辆
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCInsuranceVehicleGoogdsByIds(String[] ids);

    /**
     * 批量新增
     */
    public int insertBatch(List<CInsuranceVehicleGoogds> item);

    /**
     *
     */
    public List<CInsuranceVehicleGoogds> selectCIGByCId(String id);
}
