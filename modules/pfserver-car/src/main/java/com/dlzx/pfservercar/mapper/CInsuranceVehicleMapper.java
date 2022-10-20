package com.dlzx.pfservercar.mapper;

import java.util.List;
import com.dlzx.pfservercar.domain.CInsuranceVehicle;
import com.dlzx.pfservercar.domain.model.ERPCostModel;

/**
 * 车辆管理-车辆保险Mapper接口
 * 
 * @author dlzx
 * @date 2020-11-24
 */
public interface CInsuranceVehicleMapper 
{
    /**
     * 查询车辆管理-车辆保险
     * 
     * @param id 车辆管理-车辆保险ID
     * @return 车辆管理-车辆保险
     */
    public CInsuranceVehicle selectCInsuranceVehicleById(String id);

    /**
     * 查询车辆管理-车辆保险列表
     * 
     * @param cInsuranceVehicle 车辆管理-车辆保险
     * @return 车辆管理-车辆保险集合
     */
    public List<CInsuranceVehicle> selectCInsuranceVehicleList(CInsuranceVehicle cInsuranceVehicle);

    /**
     * 根据ID获取详情
     * @param id
     * @return
     */
    public List<CInsuranceVehicle> selectCInsuranceVehicleInfo(String id);

    /**
     * 查询保险费 M05
     * @param cInsuranceVehicle
     * @return
     */
    public List<CInsuranceVehicle> selectCInsuranceVehicleORCIVG(CInsuranceVehicle cInsuranceVehicle);

    /**
     * 新增车辆管理-车辆保险
     * 
     * @param cInsuranceVehicle 车辆管理-车辆保险
     * @return 结果
     */
    public int insertCInsuranceVehicle(CInsuranceVehicle cInsuranceVehicle);

    /**
     * 修改车辆管理-车辆保险
     * 
     * @param cInsuranceVehicle 车辆管理-车辆保险
     * @return 结果
     */
    public int updateCInsuranceVehicle(CInsuranceVehicle cInsuranceVehicle);


    public int checkCInsuranceVehicle(CInsuranceVehicle cInsuranceVehicle);

    /**
     * 删除车辆管理-车辆保险
     * 
     * @param id 车辆管理-车辆保险ID
     * @return 结果
     */
    public int deleteCInsuranceVehicleById(String id);

    /**
     * 批量删除车辆管理-车辆保险
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCInsuranceVehicleByIds(String[] ids);

    /**
     * E01lIST
     */
    public List<CInsuranceVehicle> selectCInsuranceVe(CInsuranceVehicle cInsuranceVehicle);

    /**
     * E06lIST
     */
    public List<CInsuranceVehicle> selectCInsuranceGe(CInsuranceVehicle cInsuranceVehicle);

    public CInsuranceVehicle selectCInsuranceLastList(String id);

    public List<CInsuranceVehicle> selectCostIn(CInsuranceVehicle cInsuranceVehicle);

    /**
     * @describe 保险ERP费用
     * @author DongCL
     * @date 2021-02-21 16:12
     * @param
     * @return
     */
    List<ERPCostModel> queryERPInsuranceCostList(ERPCostModel erpCostModel);
}
