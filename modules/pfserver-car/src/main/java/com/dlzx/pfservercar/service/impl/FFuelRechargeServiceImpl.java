package com.dlzx.pfservercar.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.RFuelCost;
import com.dlzx.pfservercar.mapper.CVehicleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.FFuelRechargeMapper;
import com.dlzx.pfservercar.domain.FFuelRecharge;
import com.dlzx.pfservercar.service.IFFuelRechargeService;

/**
 * 燃料管理-充电记录Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-17
 */
@Service
public class FFuelRechargeServiceImpl implements IFFuelRechargeService 
{
    @Autowired
    private FFuelRechargeMapper fFuelRechargeMapper;

    @Autowired
    private CVehicleMapper cVehicleMapper;
    /**
     * 查询燃料管理-充电记录
     * 
     * @param id 燃料管理-充电记录ID
     * @return 燃料管理-充电记录
     */
    @Override
    public FFuelRecharge selectFFuelRechargeById(String id)
    {
        return fFuelRechargeMapper.selectFFuelRechargeById(id);
    }

    /**
     * 查询燃料管理-充电记录列表
     * 
     * @param fFuelRecharge 燃料管理-充电记录
     * @return 燃料管理-充电记录
     */
    @Override
    public List<FFuelRecharge> selectFFuelRechargeList(FFuelRecharge fFuelRecharge)
    {
        return fFuelRechargeMapper.selectFFuelRechargeList(fFuelRecharge);
    }

    /**
     * 新增燃料管理-充电记录
     * 
     * @param fFuelRecharge 燃料管理-充电记录
     * @return 结果
     */
    @Override
    public int insertFFuelRecharge(FFuelRecharge fFuelRecharge)
    {
        fFuelRecharge.setCreateTime(DateUtils.getNowDate());
        return fFuelRechargeMapper.insertFFuelRecharge(fFuelRecharge);
    }

    /**
     * 修改燃料管理-充电记录
     * 
     * @param fFuelRecharge 燃料管理-充电记录
     * @return 结果
     */
    @Override
    public int updateFFuelRecharge(FFuelRecharge fFuelRecharge)
    {
        fFuelRecharge.setUpdateTime(DateUtils.getNowDate());
        return fFuelRechargeMapper.updateFFuelRecharge(fFuelRecharge);
    }

    /**
     * 批量删除燃料管理-充电记录
     * 
     * @param ids 需要删除的燃料管理-充电记录ID
     * @return 结果
     */
    @Override
    public int deleteFFuelRechargeByIds(String[] ids)
    {
        return fFuelRechargeMapper.deleteFFuelRechargeByIds(ids);
    }

    /**
     * 删除燃料管理-充电记录信息
     * 
     * @param id 燃料管理-充电记录ID
     * @return 结果
     */
    @Override
    public int deleteFFuelRechargeById(String id)
    {
        return fFuelRechargeMapper.deleteFFuelRechargeById(id);
    }

    /**
     * 充电记录导入
     * */
    @Override
    public AjaxResult importFuelRecharge(List<FFuelRecharge> fFuelRecharges, String username) {
        List<CVehicle> cVehicles = cVehicleMapper.selectCVehicleListAll();
        for (FFuelRecharge fFuelRecharge : fFuelRecharges) {
            if (StringUtils.isNull(fFuelRecharge.getRechargeElectricity())) {
                return AjaxResult.error("充电电量不能为空");
            }
            if (StringUtils.isNull(fFuelRecharge.getRechargeAmount())) {
                return AjaxResult.error("充电金额不能为空");
            }
            if (StringUtils.isNull(fFuelRecharge.getRechargeDuration())) {
                return AjaxResult.error("充电时长不能为空");
            }
            fFuelRecharge.setId(UUID.randomUUID()+"");
            fFuelRecharge.setCreateTime(new Date());
            fFuelRecharge.setCreateBy(username);
            fFuelRecharge.setVehicleId(cVehicles.stream()
                    .filter(c->c.getLicensePlate().equals(fFuelRecharge.getLicensePlate()))
                    .findFirst()
                    .map(CVehicle::getId)
                    .orElse(null));
            List<CVehicle> collect = cVehicles.stream()
                    .filter(c -> c.getLicensePlate().equals(fFuelRecharge.getLicensePlate()))
                    .collect(Collectors.toList());
            if (collect.size()<=0){
                return AjaxResult.error("车辆"+fFuelRecharge.getLicensePlate()+"不存在，请检查后再导入");
            }
            List<CVehicle> collectVin = cVehicles.stream()
                    .filter(c -> c.getFrameNumber().equals(fFuelRecharge.getFrameNumber()))
                    .collect(Collectors.toList());
            if (collectVin.size()<=0){
                return AjaxResult.error("车辆VIN号"+fFuelRecharge.getFrameNumber()+"不存在，请检查后再导入");
            }
        }
        int result = 0;
        if (fFuelRecharges.size()>0){
            result = fFuelRechargeMapper.insertList(fFuelRecharges);
        }
        return AjaxResult.success("成功导入"+result+"条数据");
    }
}
