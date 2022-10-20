package com.dlzx.pfservercar.mapper;

import com.dlzx.pfservercar.domain.RDepreciationCost;
import com.dlzx.pfservercar.domain.REtcCost;
import com.dlzx.pfservercar.domain.RFuelCost;
import com.dlzx.pfservercar.domain.model.ERPCostModel;

import java.math.BigDecimal;
import java.util.List;

public interface RDepreciationCostMapper {
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
     * 查询统计报-查询所有车辆
     *
     * @param
     * @return
     */

    public  List<RDepreciationCost> selectListRDepreciationCostCost();

    /**
     * 批量插入车辆
     * */
    int insertList(List<RDepreciationCost> rDepreciationCosts);

    /**
     * @describe 获取折旧费用车辆下拉列表
     * @author DongCL
     * @date 2021-01-16 10:50
     * @return
     */
    List<String> queryLicensePlatePullDown();

    /**
     * @describe 折旧ERP费用
     * @author DongCL
     * @date 2021-02-21 16:12
     * @param
     * @return
     */
    List<ERPCostModel> queryERPDepreciationCostList(ERPCostModel erpCostModel);
}
