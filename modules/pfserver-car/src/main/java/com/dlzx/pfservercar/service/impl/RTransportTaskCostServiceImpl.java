package com.dlzx.pfservercar.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.client.PersonClient;
import com.dlzx.pfservercar.client.SystemClient;
import com.dlzx.pfservercar.domain.RReportConfig;
import com.dlzx.pfservercar.domain.TTransportTask;
import com.dlzx.pfservercar.mapper.EtcCardUsageRecordMapper;
import com.dlzx.pfservercar.mapper.TTransportTaskMapper;
import com.fasterxml.jackson.datatype.jsr310.DecimalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.RTransportTaskCostMapper;
import com.dlzx.pfservercar.domain.RTransportTaskCost;
import com.dlzx.pfservercar.service.IRTransportTaskCostService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 统计报-任务成本核算Service业务层处理
 * 
 * @author ylh
 * @date 2021-01-05
 */
@Service
public class RTransportTaskCostServiceImpl implements IRTransportTaskCostService 
{
    @Autowired
    private RTransportTaskCostMapper rTransportTaskCostMapper;
    @Autowired
    private TTransportTaskMapper tTransportTaskMapper;
    @Autowired
    private PersonClient personClient;
    @Autowired
    private SystemClient systemClient;
    @Autowired
    private EtcCardUsageRecordMapper etcCardUsageRecordMapper;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询统计报-任务成本核算
     * 
     * @param id 统计报-任务成本核算ID
     * @return 统计报-任务成本核算
     */
    @Override
    public RTransportTaskCost selectRTransportTaskCostById(String id)
    {
        return rTransportTaskCostMapper.selectRTransportTaskCostById(id);
    }

    /**
     * 查询统计报-任务成本核算列表
     * 
     * @param rTransportTaskCost 统计报-任务成本核算
     * @return 统计报-任务成本核算
     */
    @Override
    public List<RTransportTaskCost> selectRTransportTaskCostList(RTransportTaskCost rTransportTaskCost)
    {
        return rTransportTaskCostMapper.selectRTransportTaskCostList(rTransportTaskCost);
    }

    /**
     * 新增统计报-任务成本核算
     * 
     * @param rTransportTaskCost 统计报-任务成本核算
     * @return 结果
     */
    @Override
    public int insertRTransportTaskCost(RTransportTaskCost rTransportTaskCost)
    {
        rTransportTaskCost.setCreateTime(DateUtils.getNowDate());
        return rTransportTaskCostMapper.insertRTransportTaskCost(rTransportTaskCost);
    }

    /**
     * 修改统计报-任务成本核算
     * 
     * @param rTransportTaskCost 统计报-任务成本核算
     * @return 结果
     */
    @Override
    public int updateRTransportTaskCost(RTransportTaskCost rTransportTaskCost)
    {
        return rTransportTaskCostMapper.updateRTransportTaskCost(rTransportTaskCost);
    }

    /**
     * 批量删除统计报-任务成本核算
     * 
     * @param ids 需要删除的统计报-任务成本核算ID
     * @return 结果
     */
    @Override
    public int deleteRTransportTaskCostByIds(String[] ids)
    {
        return rTransportTaskCostMapper.deleteRTransportTaskCostByIds(ids);
    }

    /**
     * 删除统计报-任务成本核算信息
     * 
     * @param id 统计报-任务成本核算ID
     * @return 结果
     */
    @Override
    public int deleteRTransportTaskCostById(String id)
    {
        return rTransportTaskCostMapper.deleteRTransportTaskCostById(id);
    }

    /**
     * 计算任务成本存入数据表并返显
     * @param rTransportTaskCost
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int selectCostList(RTransportTaskCost rTransportTaskCost){
        //查询任务ID、运单号、线路、主车车牌号、挂车车牌号、任务结束时间、路线公里数、业务、计费方式、任务收入
        List<TTransportTask> tTransportTaskList = tTransportTaskMapper.selectTTransportTaskCost(rTransportTaskCost.getId());
        //创建空对象list
        List<RTransportTaskCost> rTransportTaskCostList = new ArrayList<>();
        BigDecimal total = tTransportTaskList.stream().map(TTransportTask::gettAmount).reduce(BigDecimal.ZERO, RTransportTaskCostServiceImpl::sum);
        //驾驶员绩效金额总和
        BigDecimal basicAmount = personClient.transportMoney(rTransportTaskCost.getId());
        //底薪总和
        BigDecimal driverNum = BigDecimal.valueOf(1);
        if (!StringUtils.isEmpty(tTransportTaskList.get(0).getViceDriverName())){
            driverNum = BigDecimal.valueOf(2);
        }
         RReportConfig rReportConfig = systemClient.Info();
        BigDecimal totalBasic = this.multiply(driverNum,rReportConfig.getDriverPay());
        //ETC金额总和
        BigDecimal etcMoney = etcCardUsageRecordMapper.selectEtcRecordCost(rTransportTaskCost.getId());
        //驾驶员工资
        BigDecimal driverPay = this.getBigDecimalSum(basicAmount,totalBasic);
        //管理人员薪酬
        BigDecimal adminPay = this.multiply(rReportConfig.getAdminPay(),new BigDecimal(tTransportTaskList.get(0).getMileage()));
        //外包服务费
        BigDecimal serviceCost = this.multiply(rReportConfig.getServiceCost(),new BigDecimal(tTransportTaskList.get(0).getMileage()));
        //燃料费用
        BigDecimal fuelCost = this.multiply(rReportConfig.getFuelCost(),new BigDecimal(tTransportTaskList.get(0).getMileage()));
        //检车费
        BigDecimal inspectionFee = this.multiply(rReportConfig.getInspectionFee(),new BigDecimal(tTransportTaskList.get(0).getMileage()));
        //保险费
        BigDecimal insurance = this.multiply(rReportConfig.getInsurance(),new BigDecimal(tTransportTaskList.get(0).getMileage()));
        //维保费用
        BigDecimal maintenanceCost = this.multiply(rReportConfig.getMaintenanceCost(),new BigDecimal(tTransportTaskList.get(0).getMileage()));
        //轮胎费用
        BigDecimal trieCost = this.multiply(rReportConfig.getTrieCost(),new BigDecimal(tTransportTaskList.get(0).getMileage()));
        //低值易耗品费用
        BigDecimal consumablesCost = this.multiply(rReportConfig.getConsumablesCost(),new BigDecimal(tTransportTaskList.get(0).getMileage()));
        //折旧费用
        BigDecimal depreciationCost = this.multiply(rReportConfig.getDepreciationCost(),new BigDecimal(tTransportTaskList.get(0).getMileage()));
        //信息系统服务费
        BigDecimal systemCost = this.multiply(rReportConfig.getSystemCost(),new BigDecimal(tTransportTaskList.get(0).getMileage()));
        //尿素费用
        BigDecimal ureaCost = this.multiply(rReportConfig.getUreaCost(),new BigDecimal(tTransportTaskList.get(0).getMileage()));
        for (TTransportTask tTransportTask:tTransportTaskList){
            //计算比例
            BigDecimal prop = tTransportTask.gettAmount().divide(total, 3, RoundingMode.HALF_UP);
            RTransportTaskCost transportTaskCost = new RTransportTaskCost();
            transportTaskCost.setTaskEndTime(tTransportTask.getTaskEndTime());
            transportTaskCost.setId(UUID.randomUUID().toString());
            transportTaskCost.setTaskId(tTransportTask.getId());
            transportTaskCost.setShipmentXid(tTransportTask.getShipmentXid());
            transportTaskCost.setRouteName(tTransportTask.getRouteName());
            transportTaskCost.setBusinessType(tTransportTask.getbType());
            transportTaskCost.setFeeType(tTransportTask.getfType());
            transportTaskCost.setTaskCost(tTransportTask.gettAmount());
            transportTaskCost.setMainLicensePlate(tTransportTask.getMainLicensePlate());
            transportTaskCost.setHangLicensePlate(tTransportTask.getHangLicensePlate());
            transportTaskCost.setDriverPay(this.multiply(prop,driverPay));
            transportTaskCost.setAdminPay(this.multiply(prop,adminPay));
            transportTaskCost.setServiceCost(this.multiply(prop,serviceCost));
            transportTaskCost.setEtcCost(this.multiply(prop,etcMoney));
            transportTaskCost.setUreaCost(this.multiply(prop,ureaCost));
            transportTaskCost.setFuelCost(this.multiply(prop,fuelCost));
            transportTaskCost.setInspectionFee(this.multiply(prop,inspectionFee));
            transportTaskCost.setInsurance(this.multiply(prop,insurance));
            transportTaskCost.setMaintenanceCost(this.multiply(prop,maintenanceCost));
            transportTaskCost.setTrieCost(this.multiply(prop,trieCost));
            transportTaskCost.setConsumablesCost(this.multiply(prop,consumablesCost));
            transportTaskCost.setDepreciationCost(this.multiply(prop,depreciationCost));
            transportTaskCost.setSystemCost(this.multiply(prop,systemCost));
            transportTaskCost.setCreateBy(tokenService.getLoginUser().getUsername());
            transportTaskCost.setCreateTime(new Date());
            transportTaskCost.setTotalCost(this.getBigDecimalSum(transportTaskCost.getDriverPay(),transportTaskCost.getAdminPay(),
                    transportTaskCost.getServiceCost(),transportTaskCost.getEtcCost(),transportTaskCost.getUreaCost(),transportTaskCost.getFuelCost(),
                    transportTaskCost.getInspectionFee(),transportTaskCost.getInsurance(),transportTaskCost.getMaintenanceCost(),transportTaskCost.getTrieCost(),
                    transportTaskCost.getConsumablesCost(),transportTaskCost.getDepreciationCost(),transportTaskCost.getSystemCost()));
            //盈亏=任务收入-费用合计
            BigDecimal profit = transportTaskCost.getTaskCost().subtract(transportTaskCost.getTotalCost()).setScale(3, BigDecimal.ROUND_UP);
            if (profit.signum() == -1){
                transportTaskCost.setProfitStatus(2L);
            }
            if (profit.signum() == 1){
                transportTaskCost.setProfitStatus(1L);
            }
            if (profit.signum() == 0){
                transportTaskCost.setProfitStatus(0L);
            }
            transportTaskCost.setProfitAndLoss(profit.abs());
            rTransportTaskCostList.add(transportTaskCost);
        }
            return rTransportTaskCostMapper.insertBatch(rTransportTaskCostList);
    }



    /**
     * 多个BigDecimal数相加和
     *
     * @param i
     * @param arg
     * @return
     */
    public static BigDecimal getBigDecimalSum(BigDecimal i, BigDecimal... arg) {
        BigDecimal sum = i;
        for (BigDecimal b : arg) {
            if (b == null){
                b = BigDecimal.ZERO;
            }
            sum = sum.add(b);
        }
        return sum;
    }

    /**
     * 校验其中的参数为空 lambda
     * @param in
     * @return
     */

    public static BigDecimal ifNullSet0(BigDecimal in) {
        if (in != null) {
            return in;
        }
        return BigDecimal.ZERO;
    }

    public static BigDecimal sum(BigDecimal ...in){
        BigDecimal result = BigDecimal.ZERO;
        for (int i = 0; i < in.length; i++){
            result = result.add(ifNullSet0(in[i]));
        }
        return result;
    }

    /**
     * 支持null相乘
     * @param x
     * @param y
     * @return
     */
    public static BigDecimal multiply(BigDecimal x, BigDecimal y) {
        if (x == null || y == null) {
            return null;
        }
        return x.multiply(y);
    }
}
