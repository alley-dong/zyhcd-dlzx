package com.dlzx.pfservercar.service.impl;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.RDepreciationCost;
import com.dlzx.pfservercar.domain.RFuelCost;
import com.dlzx.pfservercar.mapper.RDepreciationCostMapper;
import com.dlzx.pfservercar.service.ICVehicleService;
import com.dlzx.pfservercar.service.RDepreciationCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 统计报-折旧费用统计Service业务层处理
 *
 * @author dlzx
 * @date 2020-12-16
 */
@Service
public class RDepreciationCostServiceImpl implements RDepreciationCostService {

    @Autowired
    RDepreciationCostMapper rDepreciationCostMapper;

    @Autowired
    ICVehicleService icVehicleService;

    @Override
    public List<RDepreciationCost> selectRDepreciationCostList(RDepreciationCost rDepreciationCost) {
        return rDepreciationCostMapper.selectRDepreciationCostList(rDepreciationCost);
    }

    @Override
    public BigDecimal selectRDepreciationCostMoney(RDepreciationCost rDepreciationCost) {
        return rDepreciationCostMapper.selectRDepreciationCostMoney(rDepreciationCost);
    }

    @Override
    public AjaxResult importFuelCost(List<RDepreciationCost> rDepreciationCosts1, String username) {
        // 1.校验是否为本车辆档案中车辆
        List<CVehicle>  collect=null;
        List<CVehicle> cVehicleList = icVehicleService.selectCVehicleListAll();
        for (RDepreciationCost items : rDepreciationCosts1) {
            //筛选号码如果<0是非档案中号码，如果大于0集合中存放的是传过来的集合车牌号加id
              collect = cVehicleList.stream()
                    .filter(c -> c.getLicensePlate().equals(items.getLicensePlate()))
                    .collect(Collectors.toList());
            if (collect.size() <= 0) {
                return AjaxResult.error("车牌号" + "(" + items.getLicensePlate() + ")" + "非本系统中车辆档案号码，请重校正车辆牌照合法性");
            }
        }
        //2. 校验车辆是否为折旧费用中已存在的车辆
//        List<RDepreciationCost> rDepreciationCosts2 = rDepreciationCostMapper.selectListRDepreciationCostCost();
//        for (RDepreciationCost item : rDepreciationCosts1) {
//            //此集合校验传过来的批量数据在我折旧费用表中是否存在>0已经存在  小于0就是不存在
//            List<RDepreciationCost> collects = rDepreciationCosts2.stream()
//                    .filter(c -> c.getLicensePlate().equals(item.getLicensePlate()))
//                    .collect(Collectors.toList());
//            if (collects.size() > 0) {
//                return AjaxResult.error("车牌号" +"("+ item.getLicensePlate() +")"+ "在折旧费用中已经存在，请勿重复录入重复车牌号");
//            }
//        }

          //进行插入 插入id需要从档案中筛选出来，筛选条件为车牌号相同
              List<RDepreciationCost> rDepreciationCostList=new ArrayList<>();
        for (RDepreciationCost item : rDepreciationCosts1) {
            if (cVehicleList != null && cVehicleList.size() > 0) {
                for (CVehicle c : cVehicleList) {
                    if (item.getLicensePlate().equals(c.getLicensePlate())) {
                        //主键
                        RDepreciationCost rDepreciationCost=new RDepreciationCost();
                        rDepreciationCost.setId(UUID.randomUUID() + "");
                        rDepreciationCost.setVehicleId(c.getId());
                        //车牌号
                        rDepreciationCost.setLicensePlate(c.getLicensePlate());
                        //折旧费用
                        rDepreciationCost.setDepreciationCost(item.getDepreciationCost());
                        //折旧月份
                        rDepreciationCost.setDepreciationMonth(item.getDepreciationMonth());
                        rDepreciationCost.setCreateBy(username);
                        rDepreciationCost.setCreateTime(new Date());
                        rDepreciationCostList.add(rDepreciationCost);

                    }
                }
            }

        }
        int result = rDepreciationCostMapper.insertList(rDepreciationCostList);
        return AjaxResult.success("成功导入" + result + "条车辆信息");
    }

    /**
     * @describe 获取折旧费用车辆下拉列表
     * @author DongCL
     * @date 2021-01-16 10:50
     * @return
     */
    @Override
    public List<String> queryLicensePlatePullDown() {
        return rDepreciationCostMapper.queryLicensePlatePullDown();
    }
}



