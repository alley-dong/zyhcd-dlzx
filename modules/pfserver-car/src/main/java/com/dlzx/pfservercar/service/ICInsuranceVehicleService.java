package com.dlzx.pfservercar.service;

import java.util.List;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.pfservercar.domain.CInsuranceVehicle;
import com.dlzx.pfservercar.domain.CInsuranceVehicleItem;
import com.dlzx.pfservercar.domain.ReviewSubmit;

/**
 * 车辆管理-车辆保险Service接口
 * 
 * @author dlzx
 * @date 2020-11-24
 */
public interface ICInsuranceVehicleService 
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
     * 批量删除车辆管理-车辆保险
     * 
     * @param ids 需要删除的车辆管理-车辆保险ID
     * @return 结果
     */
    public int deleteCInsuranceVehicleByIds(String[] ids);

    /**
     * 删除车辆管理-车辆保险信息
     * 
     * @param id 车辆管理-车辆保险ID
     * @return 结果
     */
    public int deleteCInsuranceVehicleById(String id);

    /**
     * E01lIST
     */
    public List<CInsuranceVehicle> selectCInsuranceVe(CInsuranceVehicle cInsuranceVehicle);

    /**
     * E06lIST
     */
    public List<CInsuranceVehicle> selectCInsuranceGe(CInsuranceVehicle cInsuranceVehicle);

    AjaxResult checkReview(ReviewSubmit reviewSubmit);

    public CInsuranceVehicle selectCInsuranceLastList(String id);

    public List<CInsuranceVehicleItem> selectCInsuranceItemByInsuranceId(String id);

    public List<CInsuranceVehicle> selectCostIn(CInsuranceVehicle cInsuranceVehicle);
}
