package com.dlzx.pfservercar.service.impl;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.web.domain.ESBResult;
import com.dlzx.pfservercar.domain.model.ERPCostModel;
import com.dlzx.pfservercar.mapper.CInsuranceVehicleMapper;
import com.dlzx.pfservercar.mapper.CVehicleInspectRecordMapper;
import com.dlzx.pfservercar.mapper.GGoodsReceivingRecordMapper;
import com.dlzx.pfservercar.mapper.RDepreciationCostMapper;
import com.dlzx.pfservercar.mapper.REtcCostMapper;
import com.dlzx.pfservercar.mapper.RFuelCostMapper;
import com.dlzx.pfservercar.mapper.RInformationSystemServiceCostMapper;
import com.dlzx.pfservercar.mapper.RPurchasesCostMapper;
import com.dlzx.pfservercar.mapper.RRepairDetailedMapper;
import com.dlzx.pfservercar.service.ERPCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类描述：
 * @className ERPCostServiceImpl
 * company 赛拓信息技术有限公司
 * @author DongCL
 * @date 2021-02-21 15:39
 */
@Service
public class ERPCostServiceImpl implements ERPCostService {

    @Autowired
    private RRepairDetailedMapper rRepairDetailedMapper;

    @Autowired
    private CInsuranceVehicleMapper cInsuranceVehicleMapper;

    @Autowired
    private RFuelCostMapper rFuelCostMapper;

    @Autowired
    private REtcCostMapper rEtcCostMapper;

    @Autowired
    private CVehicleInspectRecordMapper cVehicleInspectRecordMapper;

    @Autowired
    private GGoodsReceivingRecordMapper gGoodsReceivingRecordMapper;

    @Autowired
    private RInformationSystemServiceCostMapper rInformationSystemServiceCostMapper;

    @Autowired
    private RDepreciationCostMapper rDepreciationCostMapper;

    @Autowired
    private RPurchasesCostMapper rPurchasesCostMapper;


    /**
     * @describe ERP 车辆费用
     * @author DongCL
     * @date 2021-02-21 15:34
     * @param
     * @return
     */
    @Override
    public ESBResult queryERPCostList(ERPCostModel erpCostModel) {

        if (StringUtils.isEmpty(erpCostModel.getType())){
            return ESBResult.error("请传入费用类型");
        }

        // 费用类型
        Integer costType = Integer.parseInt(erpCostModel.getType());

        // 返回信息
        Map<String,Object> map = new HashMap<>();
        switch (costType){
            case 1:
                // 维修保养
                List<ERPCostModel> erprEpairCostModels = rRepairDetailedMapper.queryERPCostVehicleList(erpCostModel);

                BigDecimal epairCostTotal = erprEpairCostModels.stream().map(costModel -> {
                    if (costModel.getAmount()==null){
                        return BigDecimal.ZERO;
                    }else {
                        return costModel.getAmount();
                    }
                }).reduce(BigDecimal.ZERO,BigDecimal::add);

                map.put("costList",erprEpairCostModels);
                map.put("epairCostTotal",epairCostTotal);
                return ESBResult.success(map);
            case 2:
                // 保险
                List<ERPCostModel> erpInsuranceCostModels = cInsuranceVehicleMapper.queryERPInsuranceCostList(erpCostModel);

                BigDecimal insuranceCostTotal = erpInsuranceCostModels.stream().map(costModel -> {
                    if (costModel.getInsuranceCost()==null){
                        return BigDecimal.ZERO;
                    }else {
                        return costModel.getInsuranceCost();
                    }
                }).reduce(BigDecimal.ZERO,BigDecimal::add);

                map.put("costList",erpInsuranceCostModels);
                map.put("insuranceCostTotal",insuranceCostTotal);
                return ESBResult.success(map);
            case 3:
                // 燃料
                List<ERPCostModel> erpFuelCostList = rFuelCostMapper.queryERPFuelCostList(erpCostModel);

                // 含税
                BigDecimal taxFuelCostTotal = erpFuelCostList.stream().map(costModel -> {
                    if (costModel.getTaxAmount()==null){
                        return BigDecimal.ZERO;
                    }else {
                        return costModel.getTaxAmount();
                    }
                }).reduce(BigDecimal.ZERO,BigDecimal::add);

                // 不含税
                BigDecimal fuelCostTotal = erpFuelCostList.stream().map(costModel -> {
                    if (costModel.getFuelAmount()==null){
                        return BigDecimal.ZERO;
                    }else {
                        return costModel.getFuelAmount();
                    }
                }).reduce(BigDecimal.ZERO,BigDecimal::add);

                map.put("costList",erpFuelCostList);
                map.put("fuelTaxCostTotal",taxFuelCostTotal);
                map.put("fuelCostTotal",fuelCostTotal);
                return ESBResult.success(map);
            case 4:
                // ETC
                List<ERPCostModel> rEtcCosts = rEtcCostMapper.queryERPEtcCostList(erpCostModel);

                // 含税
                BigDecimal taxEtcCostTotal = rEtcCosts.stream().map(costModel -> {
                    if (costModel.getEtcTaxAmount()==null){
                        return BigDecimal.ZERO;
                    }else {
                        return costModel.getEtcTaxAmount();
                    }
                }).reduce(BigDecimal.ZERO,BigDecimal::add);

                // 不含税
                BigDecimal etcCostTotal = rEtcCosts.stream().map(costModel -> {
                    if (costModel.getEtcAmount()==null){
                        return BigDecimal.ZERO;
                    }else {
                        return costModel.getEtcAmount();
                    }
                }).reduce(BigDecimal.ZERO,BigDecimal::add);

                map.put("costList",rEtcCosts);
                map.put("etcTaxCostTotal",taxEtcCostTotal);
                map.put("etcCostTotal",etcCostTotal);
                return ESBResult.success(map);
            case 5:
                // 检车
                List<ERPCostModel> cVehicleInspectRecordList =cVehicleInspectRecordMapper.queryERPInspectRecordList(erpCostModel);

                // 车辆年检费用合计
                BigDecimal totalVehiceInspectCost = BigDecimal.ZERO;
                // 营运年检费用合计
                BigDecimal totalOperateInspectCost = BigDecimal.ZERO;
                // 复检费
                BigDecimal totalReviewCost = BigDecimal.ZERO;
                // 检车费合计
                BigDecimal allTotalCost = BigDecimal.ZERO;
                //循环计算合计金额
                for (ERPCostModel cVehicleInspectRecordOne:cVehicleInspectRecordList){
                    totalVehiceInspectCost = totalVehiceInspectCost.add(cVehicleInspectRecordOne.getVehiceInspectCost());
                    totalOperateInspectCost = totalOperateInspectCost.add(cVehicleInspectRecordOne.getOperateInspectCost());
                    totalReviewCost = totalReviewCost.add(cVehicleInspectRecordOne.getReviewCost());
                    allTotalCost = allTotalCost.add(cVehicleInspectRecordOne.getTotalCost());
                }
                map.put("costList",cVehicleInspectRecordList);
                map.put("totalVehiceInspectCost",totalVehiceInspectCost);
                map.put("totalOperateInspectCost",totalOperateInspectCost);
                map.put("totalReviewCost",totalReviewCost);
                map.put("allTotalCost",allTotalCost);
                return ESBResult.success(map);
            case 6:
                // 折旧
                List<ERPCostModel> rDepreciationCostList = rDepreciationCostMapper.queryERPDepreciationCostList(erpCostModel);

                BigDecimal depreciationCostTotal = rDepreciationCostList.stream().map(costModel -> {
                    if (costModel.getDepreciationCost()==null){
                        return BigDecimal.ZERO;
                    }else {
                        return costModel.getDepreciationCost();
                    }
                }).reduce(BigDecimal.ZERO,BigDecimal::add);

                map.put("costList",rDepreciationCostList);
                map.put("depreciationCostTotal",depreciationCostTotal);
                return ESBResult.success(map);
            case 7:
                // 尿素
                List<ERPCostModel> receivingRecordUreatList = gGoodsReceivingRecordMapper.queryERPReceivingRecordUreatList(erpCostModel);

                BigDecimal receivingUreatCostTotal = receivingRecordUreatList.stream().map(costModel -> {
                    if (costModel.getAmount()==null){
                        return BigDecimal.ZERO;
                    }else {
                        return costModel.getAmount();
                    }
                }).reduce(BigDecimal.ZERO,BigDecimal::add);

                map.put("costList",receivingRecordUreatList);
                map.put("receivingUreatCostTotal",receivingUreatCostTotal);
                return ESBResult.success(map);
            case 8:
                // 轮胎
                List<ERPCostModel> receivingRecordTyreList = gGoodsReceivingRecordMapper.queryERPReceivingRecordTyreList(erpCostModel);

                BigDecimal receivingTyreCostTotal = receivingRecordTyreList.stream().map(costModel -> {
                    if (costModel.getAmount()==null){
                        return BigDecimal.ZERO;
                    }else {
                        return costModel.getAmount();
                    }
                }).reduce(BigDecimal.ZERO,BigDecimal::add);

                map.put("costList",receivingRecordTyreList);
                map.put("receivingTyreCostTotal",receivingTyreCostTotal);
                return ESBResult.success(map);
            case 9:
                // 信息系统服务
                List<ERPCostModel> rInformationSystemServiceCostList = rInformationSystemServiceCostMapper.queryERPInformationSystemServiceCostList(erpCostModel);
                BigDecimal informationCostTotal = rInformationSystemServiceCostList.stream().map(costModel -> {
                    if (costModel.getAmount()==null){
                        return BigDecimal.ZERO;
                    }else {
                        return costModel.getAmount();
                    }
                }).reduce(BigDecimal.ZERO,BigDecimal::add);
                map.put("costList",rInformationSystemServiceCostList);
                map.put("informationCostTotal",informationCostTotal);
                return ESBResult.success(map);
            case 10:
                // 新购车辆临时号牌
                List<ERPCostModel> erpPurchasesCostList = rPurchasesCostMapper.queryERPPurchasesCostList(erpCostModel);
                BigDecimal erpPurchasesCostTotal = erpPurchasesCostList.stream().map(costModel -> {
                    if (costModel.getAmount()==null){
                        return BigDecimal.ZERO;
                    }else {
                        return costModel.getAmount();
                    }
                }).reduce(BigDecimal.ZERO,BigDecimal::add);
                map.put("costList",erpPurchasesCostList);
                map.put("erpPurchasesCostTotal",erpPurchasesCostTotal);
                return ESBResult.success(map);
            default:
                return ESBResult.error("错误的费用类型！");
        }
    }

}
