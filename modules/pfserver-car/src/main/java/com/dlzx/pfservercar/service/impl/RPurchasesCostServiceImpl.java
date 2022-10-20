package com.dlzx.pfservercar.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dlzx.common.core.text.Convert;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.RFuelCost;
import com.dlzx.pfservercar.mapper.CVehicleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.RPurchasesCostMapper;
import com.dlzx.pfservercar.domain.RPurchasesCost;
import com.dlzx.pfservercar.service.IRPurchasesCostService;

/**
 * 统计报-新购车辆临时号牌费用统计Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-21
 */
@Service
public class RPurchasesCostServiceImpl implements IRPurchasesCostService 
{
    @Autowired
    private RPurchasesCostMapper rPurchasesCostMapper;

    @Autowired
    private CVehicleMapper cVehicleMapper;

    /**
     * 查询统计报-新购车辆临时号牌费用统计
     * 
     * @param id 统计报-新购车辆临时号牌费用统计ID
     * @return 统计报-新购车辆临时号牌费用统计
     */
    @Override
    public RPurchasesCost selectRPurchasesCostById(String id)
    {
        return rPurchasesCostMapper.selectRPurchasesCostById(id);
    }

    /**
     * 查询统计报-新购车辆临时号牌费用统计列表
     * 
     * @param rPurchasesCost 统计报-新购车辆临时号牌费用统计
     * @return 统计报-新购车辆临时号牌费用统计
     */
    @Override
    public List<RPurchasesCost> selectRPurchasesCostList(RPurchasesCost rPurchasesCost)
    {
        return rPurchasesCostMapper.selectRPurchasesCostList(rPurchasesCost);
    }

    /**
     * 新增统计报-新购车辆临时号牌费用统计
     * 
     * @param rPurchasesCost 统计报-新购车辆临时号牌费用统计
     * @return 结果
     */
    @Override
    public int insertRPurchasesCost(RPurchasesCost rPurchasesCost)
    {
        rPurchasesCost.setCreateTime(DateUtils.getNowDate());
        return rPurchasesCostMapper.insertRPurchasesCost(rPurchasesCost);
    }

    /**
     * 修改统计报-新购车辆临时号牌费用统计
     * 
     * @param rPurchasesCost 统计报-新购车辆临时号牌费用统计
     * @return 结果
     */
    @Override
    public int updateRPurchasesCost(RPurchasesCost rPurchasesCost)
    {
        rPurchasesCost.setUpdateTime(DateUtils.getNowDate());
        return rPurchasesCostMapper.updateRPurchasesCost(rPurchasesCost);
    }

    /**
     * 删除统计报-新购车辆临时号牌费用统计对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRPurchasesCostByIds(String ids)
    {
        return rPurchasesCostMapper.deleteRPurchasesCostByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除统计报-新购车辆临时号牌费用统计信息
     * 
     * @param id 统计报-新购车辆临时号牌费用统计ID
     * @return 结果
     */
    @Override
    public int deleteRPurchasesCostById(String id)
    {
        return rPurchasesCostMapper.deleteRPurchasesCostById(id);
    }

    /**
     * @describe 导入
     * @author DongCL
     * @date 2021-02-22 14:04
     * @param
     * @return
     */
    @Override
    public AjaxResult importPurchasesCost(List<RPurchasesCost> rPurchasesCosts, String username) {
        List<CVehicle> cVehicles = cVehicleMapper.selectCVehicleListAll();
        if (rPurchasesCosts.size()<=0){
            return AjaxResult.error("传入excel内容为空");
        }
        for (RPurchasesCost rPurchasesCost : rPurchasesCosts) {
            if (rPurchasesCost.getTaxAmount()==null){
                return AjaxResult.error("金额不能为空");
            }
            if (rPurchasesCost.getLicensePlate()==null){
                return AjaxResult.error("车牌号不能为空");
            }
            if (rPurchasesCost.getVehicleModelName()==null){
                return AjaxResult.error("车型不能为空");
            }
            if (rPurchasesCost.getPurchasesTime()==null){
                return AjaxResult.error("购买日期不能为空");
            }
            rPurchasesCost.setId(UUID.randomUUID()+"");
            rPurchasesCost.setCreateTime(new Date());
            rPurchasesCost.setCreateBy(username);
            rPurchasesCost.setVehicleId(cVehicles.stream()
                    .filter(c->c.getLicensePlate().equals(rPurchasesCost.getLicensePlate()))
                    .findFirst()
                    .map(CVehicle::getId)
                    .orElse(null));
            List<CVehicle> collect = cVehicles.stream()
                    .filter(c -> c.getLicensePlate().equals(rPurchasesCost.getLicensePlate()))
                    .collect(Collectors.toList());
            if (collect.size()<=0){
                return AjaxResult.error("车辆"+rPurchasesCost.getLicensePlate()+"不存在");
            }
        }
        int result = rPurchasesCostMapper.insertList(rPurchasesCosts);
        return AjaxResult.success("成功导入"+result+"条数据");
    }
}
