package com.dlzx.pfservercar.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.FFuelQuota;
import com.dlzx.pfservercar.domain.SysDictData;
import com.dlzx.pfservercar.mapper.CVehicleMapper;
import com.dlzx.pfservercar.mapper.FFuelQuotaMapper;
import com.dlzx.pfservercar.mapper.SysDictDataMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.FFuelUsePlanMapper;
import com.dlzx.pfservercar.domain.FFuelUsePlan;
import com.dlzx.pfservercar.service.IFFuelUsePlanService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 燃料管理-燃油使用计划Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-02
 */
@Service
public class FFuelUsePlanServiceImpl implements IFFuelUsePlanService 
{
    @Autowired
    private FFuelUsePlanMapper fFuelUsePlanMapper;

    @Autowired
    private CVehicleMapper cVehicleMapper;

    @Autowired
    private FFuelQuotaMapper fFuelQuotaMapper;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;


    /**
     * 查询燃料管理-燃油使用计划
     * 
     * @param id 燃料管理-燃油使用计划ID
     * @return 燃料管理-燃油使用计划
     */
    @Override
    public FFuelUsePlan selectFFuelUsePlanById(String id)
    {
        return fFuelUsePlanMapper.selectFFuelUsePlanById(id);
    }

    /**
     * 查询燃料管理-燃油使用计划列表
     * 
     * @param fFuelUsePlan 燃料管理-燃油使用计划
     * @return 燃料管理-燃油使用计划
     */
    @Override
    public List<FFuelUsePlan> selectFFuelUsePlanList(FFuelUsePlan fFuelUsePlan)
    {
        return fFuelUsePlanMapper.selectFFuelUsePlanList(fFuelUsePlan);
    }

    /**
     * 新增燃料管理-生成燃油使用计划
     * 
     * @param fFuelUsePlan 燃料管理-生成燃油使用计划
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertFFuelUsePlan(FFuelUsePlan fFuelUsePlan)
    {
        try{
            // 获取当前时间
            Date nowDate = DateUtils.getNowDate();
            // 批量插入集合
            List<FFuelUsePlan> insertCVehicleList = new ArrayList<>();
            // 获取上一条记录的 车辆列表信息
            List<FFuelUsePlan> planDataList = fFuelUsePlanMapper.selectFFuelUsePlanList(new FFuelUsePlan());
            if (planDataList.size()>0){
                FFuelUsePlan carPlan = new FFuelUsePlan();
                carPlan.setPlanDate(planDataList.get(0).getPlanDate());
                List<FFuelUsePlan> list = fFuelUsePlanMapper.fuelUsePlanList(carPlan);

                // 更新到下个月的计划
                for (FFuelUsePlan fuelUsePlan : list) {
                    FFuelUsePlan fuelUsePlanAgain = new FFuelUsePlan();
                    BeanUtils.copyProperties(fuelUsePlan,fuelUsePlanAgain);
                    fuelUsePlanAgain.setId(UUID.randomUUID()+"");
                    fuelUsePlanAgain.setCreateTime(nowDate);
                    fuelUsePlanAgain.setPlanDate(fFuelUsePlan.getPlanDate());
                    insertCVehicleList.add(fuelUsePlanAgain);
                }
            }
            // 获取所有主车车辆
            List<CVehicle> cVehicles = cVehicleMapper.selectMainCVehicleAll();

            SysDictData sysDictData = new SysDictData();
            sysDictData.setDictType("f_fuel_quota_type");
            List<SysDictData> sysDictDataList = sysDictDataMapper.selectDictDataList(sysDictData);
            for (SysDictData dictData : sysDictDataList) {
                // 创建循环插入的实体集合
                for (CVehicle cVehicle : cVehicles) {
                    FFuelUsePlan fuelUsePlan = new FFuelUsePlan();
                    fuelUsePlan.setLicensePlate(cVehicle.getLicensePlate());
                    fuelUsePlan.setVehicleId(cVehicle.getId());
                    fuelUsePlan.setVehicleModel(cVehicle.getVehicleModel());
                    fuelUsePlan.setVehicleModelName(cVehicle.getVehicleModelName());
                    fuelUsePlan.setId(UUID.randomUUID()+"");
                    fuelUsePlan.setCreateTime(nowDate);
                    fuelUsePlan.setPlanDate(fFuelUsePlan.getPlanDate());
                    // 燃油类型
//                    fuelUsePlan.setFuelType(Integer.parseInt(dictData.getDictValue()));
                    if (insertCVehicleList.stream().filter(c->c.getLicensePlate().equals(cVehicle.getLicensePlate())).count()<=0){
                        insertCVehicleList.add(fuelUsePlan);
                    }
                }
            }
            // 批量插入
            return fFuelUsePlanMapper.insertInsertCVehicleList(insertCVehicleList);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 修改燃料管理-燃油使用计划
     * 
     * @param fFuelUsePlan 燃料管理-燃油使用计划
     * @return 结果
     */
    @Override
    public int updateFFuelUsePlan(FFuelUsePlan fFuelUsePlan)
    {
        fFuelUsePlan.setUpdateTime(DateUtils.getNowDate());
        return fFuelUsePlanMapper.updateFFuelUsePlan(fFuelUsePlan);
    }

    /**
     * 批量删除燃料管理-燃油使用计划
     * 
     * @param ids 需要删除的燃料管理-燃油使用计划ID
     * @return 结果
     */
    @Override
    public int deleteFFuelUsePlanByIds(String[] ids)
    {
        return fFuelUsePlanMapper.deleteFFuelUsePlanByIds(ids);
    }

    /**
     * 删除燃料管理-燃油使用计划信息
     * 
     * @param id 燃料管理-燃油使用计划ID
     * @return 结果
     */
    @Override
    public int deleteFFuelUsePlanById(String id)
    {
        return fFuelUsePlanMapper.deleteFFuelUsePlanById(id);
    }

    /**
     * 根据车型和线路获取 定额
     *
     * @param fFuelUsePlan 燃料管理-燃油使用计划
     * @return 结果
     */
    @Override
    public List<FFuelQuota> queryPlanMoney(FFuelUsePlan fFuelUsePlan) {
        return fFuelQuotaMapper.queryModelAndRoute(fFuelUsePlan);
    }

    /**
     * 燃油使用计划列表下 车辆列表
     *
     * @param fFuelUsePlan 燃料管理-燃油使用计划列表下 车辆列表
     * @return 结果
     */
    @Override
    public List<FFuelUsePlan> queryCarFFuelUsePlanList(FFuelUsePlan fFuelUsePlan) {
        return fFuelUsePlanMapper.queryCarFFuelUsePlanList(fFuelUsePlan);
    }

    @Override
    public List<FFuelUsePlan> fuelUsePlanList(FFuelUsePlan data) {
        return fFuelUsePlanMapper.fuelUsePlanList(data);
    }

    /**
     * @describe 根据车牌号和日期删除
     * @author DongCL
     * @date 2021-05-11 18:09
     * @param
     * @return
     */
    @Override
    public int deleteFFuelUsePlanBylicensePlate(FFuelUsePlan fFuelUsePlan) {
        return fFuelUsePlanMapper.deleteFFuelUsePlanBylicensePlate(fFuelUsePlan);
    }

    @Override
    public int updateInfo(FFuelUsePlan fFuelUsePlan) {

        FFuelUsePlan data = new FFuelUsePlan();
        data.setVehicleModel(fFuelUsePlan.getVehicleModel());
        data.setPlanDate(fFuelUsePlan.getPlanDate());
        data.setLicensePlate(fFuelUsePlan.getLicensePlate());
        List<FFuelUsePlan> fFuelUsePlans = fFuelUsePlanMapper.fuelUsePlanList(data);
        for (FFuelUsePlan fuelUsePlan : fFuelUsePlans) {
            fFuelUsePlanMapper.deleteFFuelUsePlanById(fuelUsePlan.getId());
        }

        if (fFuelUsePlan.getInfo().size()>0){
            for (FFuelUsePlan fuelUsePlan : fFuelUsePlan.getInfo()) {
                fuelUsePlan.setId(UUID.randomUUID()+"");
                fFuelUsePlanMapper.insertFFuelUsePlan(fuelUsePlan);
            }
            return 1;
        }else {
            // 修改趟数和线路
            return fFuelUsePlanMapper.updateFFuelUsePlan(fFuelUsePlan);
        }
    }
}
