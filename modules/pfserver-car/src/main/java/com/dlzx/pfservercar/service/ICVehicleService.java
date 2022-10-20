package com.dlzx.pfservercar.service;

import java.util.List;
import java.util.Map;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.pfservercar.domain.CInsuranceVehicleGoogds;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.DDriver;
import com.dlzx.pfservercar.domain.SysExamineTask;
import com.dlzx.pfservercar.domain.model.OTMVehicleModel;

/**
 * 车辆档案Service接口
 * 
 * @author yangluhe
 * @date 2020-11-24
 */
public interface ICVehicleService 
{
    /**
     * 查询车辆档案
     * 
     * @param id 车辆档案ID
     * @return 车辆档案
     */
    public CVehicle selectCVehicleById(String id);


    /**
     * 查询车辆档案列表
     * 
     * @param cVehicle 车辆档案
     * @return 车辆档案集合
     */
    public List<CVehicle> selectCVehicleList(CVehicle cVehicle);

    public String selectCVehicleIdByLicensePlate(String licensePlate);

    public List<CVehicle> selectCVehicleListAll();

    public List<CVehicle> selectCVehicleListCarNum(CVehicle cVehicle);
//    主车车牌号
    public List<CVehicle> selectCVehicleListCarZhuNum(CVehicle cVehicle);
    /**
     * 查询审批条件
     *
     * @param cVehicle 车辆档案
     * @return 车辆档案集合
     */
    public List<CVehicle> selectCVehicleAndCIV(CVehicle cVehicle);
    /**
     * 新增车辆档案
     * 
     * @param cVehicle 车辆档案
     * @return 结果
     */
    public int insertCVehicle(CVehicle cVehicle);

    /**
     * 修改车辆档案
     * 
     * @param cVehicle 车辆档案
     * @return 结果
     */
    public int updateCVehicle(CVehicle cVehicle);

    /**
     * 批量删除车辆档案
     * 
     * @param ids 需要删除的车辆档案ID
     * @return 结果
     */
    public int deleteCVehicleByIds(String[] ids);

    /**
     * 删除车辆档案信息
     * 
     * @param id 车辆档案ID
     * @return 结果
     */
    public int deleteCVehicleById(String id);

    /**
     * 导入数据判断
     */
    public Map<String,String> importVechicle(List<CVehicle> cVehicleList, Boolean isUpdateSupport, String sellId);

    /**
     * 校验车牌号
     */
    public int selectCVehicleByLicensePlate(String licensePlate);

    /**
     * 车检记录list
     */
    public List<CVehicle> selectCVehicleRecordList(CVehicle cVehicle);

    /**
     * 查询需要定检的车辆id和车牌号
     * @param
     * @return
     */
    public List<CVehicle> selectCVehicleIsInspect();

    /**
     * 车辆状态
     */
    public List<CVehicle> selectCVehicleStatusList(CVehicle cVehicle);

    /**
     * 根据车牌号查询车辆信息
    * */
    CVehicle queryCVehicleByLicensePlate(String licensePlate);

    /**
    * 查询充电卡车辆列表
    * */
    List<CVehicle> selectCVehicleRechargeList(CVehicle cVehicle);

    /**
     * 充电卡导入
     * */
    AjaxResult importFuelRechargeCard(List<CVehicle> cVehicles, String username);

    /**
     * OTM-车队车辆基础数据
    * */
    List<OTMVehicleModel> selectOTMVehicleListAll();

    /**
     * OTM-车队根据车排号查询车辆基础数据
    * */
    List<CVehicle> queryCVehicleByOTMLicensePlate(List<String> equipmentnumber);

    /**
     * OTM-车队根据司机手机号查询司机基础数据
     * */
    DDriver queryCDriverByOTMPhone(String driverNumber);

    /**
     * @describe 首页统计数据
     * @author DongCL
     * @date 2021-01-13 11:55
     * @return data
     */
    Map<String, Object> queryIndexStatistics();

    /**
     * @describe 首页待办
     * @author DongCL
     * @date 2021-01-13 11:55
     * @return data
     */
//    List<SysExamineTask> queryIndexExamineTaskList(Long userid);

    /**
     *
     */
    List<CVehicle> selectIBookList(CVehicle cVehicle);

    /**
     * @describe 根据卡号查询车辆
     * @author DongCL
     * @date 2021-01-29 14:11
     * @param
     * @return
     */
    List<CVehicle> queryCardNumber(String cardNumber);

    int updateCardCVehicle(CVehicle cVehicle);

    int updateCDisable(CVehicle cVehicle);

    /**
     * @describe 修改车辆数据（ESB使用）
     * @author DongCL
     * @date 2021-03-04 12:39
     * @param
     * @return
     */
    int updateCVehicleAndESBData(CVehicle cVehicle);

    /**
     * @describe 获取车辆列表
     * @author DongCL
     * @date 2021-03-09 15:10
     * @param
     * @return
     */
    List<CVehicle> selectCVehicleNoDisableList(CVehicle cVehicle);
}
