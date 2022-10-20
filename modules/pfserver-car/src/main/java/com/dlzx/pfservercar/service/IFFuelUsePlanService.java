package com.dlzx.pfservercar.service;

import java.math.BigDecimal;
import java.util.List;

import com.dlzx.pfservercar.domain.FFuelQuota;
import com.dlzx.pfservercar.domain.FFuelUsePlan;

/**
 * 燃料管理-燃油使用计划Service接口
 * 
 * @author dlzx
 * @date 2020-12-02
 */
public interface IFFuelUsePlanService 
{
    /**
     * 查询燃料管理-燃油使用计划
     * 
     * @param id 燃料管理-燃油使用计划ID
     * @return 燃料管理-燃油使用计划
     */
    public FFuelUsePlan selectFFuelUsePlanById(String id);

    /**
     * 查询燃料管理-燃油使用计划列表
     * 
     * @param fFuelUsePlan 燃料管理-燃油使用计划
     * @return 燃料管理-燃油使用计划集合
     */
    public List<FFuelUsePlan> selectFFuelUsePlanList(FFuelUsePlan fFuelUsePlan);

    /**
     * 新增燃料管理-燃油使用计划
     * 
     * @param fFuelUsePlan 燃料管理-燃油使用计划
     * @return 结果
     */
    public int insertFFuelUsePlan(FFuelUsePlan fFuelUsePlan);

    /**
     * 修改燃料管理-燃油使用计划
     * 
     * @param fFuelUsePlan 燃料管理-燃油使用计划
     * @return 结果
     */
    public int updateFFuelUsePlan(FFuelUsePlan fFuelUsePlan);

    /**
     * 批量删除燃料管理-燃油使用计划
     * 
     * @param ids 需要删除的燃料管理-燃油使用计划ID
     * @return 结果
     */
    public int deleteFFuelUsePlanByIds(String[] ids);

    /**
     * 删除燃料管理-燃油使用计划信息
     * 
     * @param id 燃料管理-燃油使用计划ID
     * @return 结果
     */
    public int deleteFFuelUsePlanById(String id);

    /**
     * 根据车型和线路获取 定额
     *
     * @param fFuelUsePlan 燃料管理-燃油使用计划
     * @return 结果
     */
    List<FFuelQuota> queryPlanMoney(FFuelUsePlan fFuelUsePlan);

    /**
     * 燃油使用计划列表下 车辆列表
     *
     * @param fFuelUsePlan 燃料管理-燃油使用计划列表下 车辆列表
     * @return 结果
     */
    List<FFuelUsePlan> queryCarFFuelUsePlanList(FFuelUsePlan fFuelUsePlan);

    /**
     * @describe 获取车辆下的油料类型
     * @author DongCL
     * @date 2021-05-08 11:58
     * @param
     * @return
     */
    List<FFuelUsePlan> fuelUsePlanList(FFuelUsePlan data);

    /**
     * @describe 根据车牌号和日期删除
     * @author DongCL
     * @date 2021-05-11 18:09
     * @param
     * @return
     */
    int deleteFFuelUsePlanBylicensePlate(FFuelUsePlan fFuelUsePlan);

    /**
     * @describe 修改
     * @author DongCL
     * @date 2021-05-11 18:25
     * @param
     * @return
     */
    int updateInfo(FFuelUsePlan fFuelUsePlan);
}
