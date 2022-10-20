package com.dlzx.pfservercar.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.mapper.CVehicleMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.RFuelCostMapper;
import com.dlzx.pfservercar.domain.RFuelCost;
import com.dlzx.pfservercar.service.IRFuelCostService;

/**
 * 统计报-燃料费用统计Service业务层处理
 *
 * @author dlzx
 * @date 2020-12-15
 */
@Service
public class RFuelCostServiceImpl implements IRFuelCostService {
    @Autowired
    private RFuelCostMapper rFuelCostMapper;
    @Autowired
    private CVehicleMapper cVehicleMapper;

    /**
     * 查询统计报-燃料费用统计
     *
     * @param id 统计报-燃料费用统计ID
     * @return 统计报-燃料费用统计
     */
    @Override
    public RFuelCost selectRFuelCostById(String id) {
        return rFuelCostMapper.selectRFuelCostById(id);
    }

    /**
     * 查询统计报-燃料费用统计列表
     *
     * @param rFuelCost 统计报-燃料费用统计
     * @return 统计报-燃料费用统计
     */
    @Override
    public List<RFuelCost> selectRFuelCostList(RFuelCost rFuelCost) {
        return rFuelCostMapper.selectRFuelCostList(rFuelCost);
    }

    /**
     * 新增统计报-燃料费用统计
     *
     * @param rFuelCost 统计报-燃料费用统计
     * @return 结果
     */
    @Override
    public int insertRFuelCost(RFuelCost rFuelCost) {
        rFuelCost.setCreateTime(DateUtils.getNowDate());
        return rFuelCostMapper.insertRFuelCost(rFuelCost);
    }

    /**
     * 修改统计报-燃料费用统计
     *
     * @param rFuelCost 统计报-燃料费用统计
     * @return 结果
     */
    @Override
    public int updateRFuelCost(RFuelCost rFuelCost) {
        rFuelCost.setUpdateTime(DateUtils.getNowDate());
        return rFuelCostMapper.updateRFuelCost(rFuelCost);
    }

    /**
     * 批量删除统计报-燃料费用统计
     *
     * @param ids 需要删除的统计报-燃料费用统计ID
     * @return 结果
     */
    @Override
    public int deleteRFuelCostByIds(String[] ids) {
        return rFuelCostMapper.deleteRFuelCostByIds(ids);
    }

    /**
     * 删除统计报-燃料费用统计信息
     *
     * @param id 统计报-燃料费用统计ID
     * @return 结果
     */
    @Override
    public int deleteRFuelCostById(String id) {
        return rFuelCostMapper.deleteRFuelCostById(id);
    }

    /**
     * 油品下拉列表
     */
    @Override
    public List<String> queryOilPullDown() {
        return rFuelCostMapper.queryOilPullDown();
    }

    @Override
    public AjaxResult importFuelCost(List<RFuelCost> rFuelCosts, String username) {
        List<CVehicle> cVehicles = cVehicleMapper.selectCVehicleListAll();

        for (RFuelCost rFuelCost : rFuelCosts) {
            if (rFuelCost.getAmount() == null) {
                return AjaxResult.error("加油金额(元 不含税)不能为空");
            }
            if (rFuelCost.getUnitPrice() == null) {
                return AjaxResult.error("单价(元 不含税)不能为空");
            }
            if (rFuelCost.getTaxAmount() == null) {
                return AjaxResult.error("加油金额(元 含税)不能为空");
            }
            if (rFuelCost.getTaxUnitPrice() == null) {
                return AjaxResult.error("单价(元 含税)不能为空");
            }
            RFuelCost rFuel = new RFuelCost();
            rFuel.setTransactionTime(rFuelCost.getTransactionTime());
            rFuel.setLicensePlate(rFuelCost.getLicensePlate());
            List<RFuelCost> rFuelCostsList = rFuelCostMapper.selectRFuelCostList(rFuel);
            if (rFuelCostsList.size() > 0) {
                for (RFuelCost fuelCost : rFuelCostsList) {
                    rFuelCost.setId(fuelCost.getId());
                    BeanUtils.copyProperties(rFuelCost, fuelCost);
                    rFuelCostMapper.updateRFuelCost(rFuelCost);
                }

                // 修改则不新增
                rFuelCost.setDeleteFlag(2L);
            } else {
                rFuelCost.setId(UUID.randomUUID() + "");
                rFuelCost.setCreateTime(new Date());
                rFuelCost.setCreateBy(username);
                //计算税率  （含税金额-不含税金额）/不含税金额  结果保留两位小数四舍五入再乘以100%
                BigDecimal multiply = rFuelCost.getTaxAmount().subtract(rFuelCost.getAmount());
                BigDecimal divide = multiply.divide(rFuelCost.getAmount(), 6, BigDecimal.ROUND_HALF_UP);
                BigDecimal bigDecimal = divide.multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
                rFuelCost.setTaxRate(bigDecimal);
                rFuelCost.setVehicleId(cVehicles.stream()
                        .filter(c -> c.getLicensePlate().equals(rFuelCost.getLicensePlate()))
                        .findFirst()
                        .map(CVehicle::getId)
                        .orElse(null));
                List<CVehicle> collect = cVehicles.stream()
                        .filter(c -> c.getLicensePlate().equals(rFuelCost.getLicensePlate()))
                        .collect(Collectors.toList());
                if (collect.size() <= 0) {
                    return AjaxResult.error("车辆" + rFuelCost.getLicensePlate() + "不存在");
                }
            }
        }
        int result = 0;
        List<RFuelCost> collect = rFuelCosts.stream().filter(f -> f.getDeleteFlag() == 1).collect(Collectors.toList());
        if (collect.size()>0){
            result = rFuelCostMapper.insertList(collect);
        }
        return AjaxResult.success("成功导入" + result + "条数据");
    }
}
