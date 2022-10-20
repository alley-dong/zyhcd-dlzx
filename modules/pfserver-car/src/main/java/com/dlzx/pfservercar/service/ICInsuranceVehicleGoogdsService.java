package com.dlzx.pfservercar.service;

import java.util.List;

import com.dlzx.pfservercar.domain.CInsuranceVehicle;
import com.dlzx.pfservercar.domain.CInsuranceVehicleGoogds;
import com.dlzx.pfservercar.domain.SysFile;

/**
 * 车辆管理-货物保险-车辆Service接口
 * 
 * @author dlzx
 * @date 2020-11-27
 */
public interface ICInsuranceVehicleGoogdsService 
{
    /**
     * 查询车辆管理-货物保险-车辆
     * 
     * @param id 车辆管理-货物保险-车辆ID
     * @return 车辆管理-货物保险-车辆
     */
    public CInsuranceVehicle selectCInsuranceVehicleGoogdsById(String id);

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
     * 连表查询
     * c_insurance_vehicle_googds主   c_insurance_vehicle从  c_insurance_vehicle_item从
     * @param cInsuranceVehicleGoogds
     * @return
     */
    public List<CInsuranceVehicleGoogds> selectCInsuranceVehicleGoogdAndCIV(CInsuranceVehicleGoogds cInsuranceVehicleGoogds);

    /**
     * 查询图片
     * @param civgd
     * @return
     */
    public List<SysFile> selectSysFileList(String civgd);


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
     * 批量删除车辆管理-货物保险-车辆
     * 
     * @param ids 需要删除的车辆管理-货物保险-车辆ID
     * @return 结果
     */
    public int deleteCInsuranceVehicleGoogdsByIds(String[] ids);

    /**
     * 删除车辆管理-货物保险-车辆信息
     * 
     * @param id 车辆管理-货物保险-车辆ID
     * @return 结果
     */
    public int deleteCInsuranceVehicleGoogdsById(String id);

    /**
     * 批量新增
     */
    public int insertBatch(List<CInsuranceVehicleGoogds> cInsuranceVehicleGoogdsList);

    public CInsuranceVehicleGoogds selectCInVeGoodById(String id);

    public int updateCIVG(CInsuranceVehicleGoogds cInsuranceVehicleGoogds);
}
