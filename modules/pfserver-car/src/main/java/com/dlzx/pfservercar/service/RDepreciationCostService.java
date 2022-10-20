package com.dlzx.pfservercar.service;


import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.pfservercar.domain.RDepreciationCost;
import com.dlzx.pfservercar.domain.RFuelCost;

import java.math.BigDecimal;
import java.util.List;

public interface RDepreciationCostService {
    /**
     * 查询统计报-折旧费用统计列表
     *
     * @param rDepreciationCost 统计报-折旧费用统计
     * @return 统计报-折旧费用统计集合
     */
    public List<RDepreciationCost> selectRDepreciationCostList(RDepreciationCost rDepreciationCost);

    /**
     * 查询统计报-折旧费用统计列表
     *
     * @param rDepreciationCost 统计报-统计折旧费
     * @return 统计报-折旧费用统计集合
     */
    public BigDecimal selectRDepreciationCostMoney(RDepreciationCost rDepreciationCost);

    /**
     * 导入车辆信息
     * */
    AjaxResult importFuelCost(List<RDepreciationCost> rFuelCosts, String username);

    /**
     * @describe 获取折旧费用车辆下拉列表
     * @author DongCL
     * @date 2021-01-16 10:50
     * @return
     */
    List<String> queryLicensePlatePullDown();
}
