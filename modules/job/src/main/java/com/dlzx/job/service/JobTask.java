package com.dlzx.job.service;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.job.client.PersonClient;
import com.dlzx.job.domain.CVehicle;
import com.dlzx.job.domain.CVehicleInspectMonthly;
import com.dlzx.job.domain.CVehicleInspectMonthlyItem;
import com.dlzx.job.domain.DTrainingAp;
import com.dlzx.job.domain.FOilCardUsageRecord;
import com.dlzx.job.domain.GGoodsReceivingRecord;
import com.dlzx.job.domain.RVehicleConsumeMonth;
import com.dlzx.job.mapper.CVehicleInspectMonthlyItemMapper;
import com.dlzx.job.mapper.CVehicleInspectMonthlyMapper;
import com.dlzx.job.mapper.CVehicleMapper;
import com.dlzx.job.mapper.DTrainingUserMapper;
import com.dlzx.job.mapper.FOilCardUsageRecordMapper;
import com.dlzx.job.mapper.GGoodsReceivingRecordMapper;
import com.dlzx.job.mapper.RVehicleConsumeMonthMapper;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author ylh
 * @program:dlzx
 * @description：定时任务
 * @create:2021-01-21 20-28
 */
@Component("task")
public class JobTask {

    @Autowired
    private CVehicleMapper cVehicleMapper;
    @Autowired
    private CVehicleInspectMonthlyMapper cVehicleInspectMonthlyMapper;
    @Autowired
    private CVehicleInspectMonthlyItemMapper cVehicleInspectMonthlyItemMapper;
    @Autowired
    private DTrainingUserMapper dTrainingUserMapper;
    @Autowired
    private GGoodsReceivingRecordMapper gGoodsReceivingRecordMapper;
    @Autowired
    private FOilCardUsageRecordMapper fOilCardUsageRecordMapper;
    @Autowired
    private RVehicleConsumeMonthMapper rVehicleConsumeMonthMapper;
    @Autowired
    private PersonClient personClient;

    @Scheduled(cron = "0 0 0 1 * ?")
    @Transactional
    public void inspectVehicle() throws SchedulerException
    {
        List<CVehicle> list = cVehicleMapper.selectCVehicleIsInspect();
        // 获取到list中的数据条数有多少 需要给cInsuranceVehicleGoogds表添加定检车辆数
        // 和未完成的数量
        int size = list.size();
        CVehicleInspectMonthly cVehicleInspectMonthly = new CVehicleInspectMonthly();
        //生成ID
        String s = UUID.randomUUID().toString();
        cVehicleInspectMonthly.setId(s);
        cVehicleInspectMonthly.setInspectDate(new Date());
        cVehicleInspectMonthly.setTotalNumber(Long.valueOf(size));
        cVehicleInspectMonthly.setCompleteNumber(Long.valueOf(0));
        cVehicleInspectMonthly.setIncompleteNumber(Long.valueOf(size));
        int rows = cVehicleInspectMonthlyMapper.insertCVehicleInspectMonthly(cVehicleInspectMonthly);

        if (rows > 0){
            // new 一个集合用来存放cVehicleInspectMonthlyItem
            // 用作批量添加
            List<CVehicleInspectMonthlyItem> list1 = new ArrayList<>();

            // 将车辆ID 车牌号添加到cVehicleInspectMonthlyItem表中
            for (CVehicle cVehicle : list) {
                CVehicleInspectMonthlyItem cVehicleInspectMonthlyItem = new CVehicleInspectMonthlyItem();
                cVehicleInspectMonthlyItem.setId(UUID.randomUUID().toString());
                // 设置MonthlyId
                cVehicleInspectMonthlyItem.setMonthlyId(s);
                // 将需要定检的车辆ID 添加给cVehicleInspectMonthlyItem
                cVehicleInspectMonthlyItem.setVehicleId(cVehicle.getId());
                // 将需要定检的车牌号码给cVehicleInspectMonthlyItem
                cVehicleInspectMonthlyItem.setLicensePlate(cVehicle.getLicensePlate());
                // 将状态设置为1未完成 sql直接给定值
                list1.add(cVehicleInspectMonthlyItem);
            }
            // 批量添加
            cVehicleInspectMonthlyItemMapper.insertList(list1);
        }
    }

    @Scheduled(cron = "0 0/1 * * * ? ")
    public void changeDriverStatus(){
        DTrainingAp dTrainingAp = new DTrainingAp();
        dTrainingAp.setDangTime(DateUtils.getNowDate());
        dTrainingUserMapper.updateDTrainingStatus(dTrainingAp);
    }


    @Scheduled(cron = "0 0 1 1 * ?")
    public void add()
    {
        Date date1 = DateUtils.getNowDate();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date1);
        calendar.add(Calendar.DATE, -1);
        Date date2 = calendar.getTime();
        DateFormat format1 = new SimpleDateFormat("yyyy-MM");
        String mon = format1.format(date2);
        Date month = new Date();
        try {
            month = format1.parse(mon);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 获取已领用尿素的车辆
        GGoodsReceivingRecord gGoodsReceivingRecord = new GGoodsReceivingRecord();
        gGoodsReceivingRecord.setCreateTime(date2);
        List<GGoodsReceivingRecord> list = gGoodsReceivingRecordMapper.selectGGoodsReceivingRecordListNum(gGoodsReceivingRecord);

        // 获取加油油量
        FOilCardUsageRecord fOilCardUsageRecord = new FOilCardUsageRecord();
        fOilCardUsageRecord.setTransactionTime(date2);
        List<FOilCardUsageRecord> list1 = fOilCardUsageRecordMapper.selectFOilCardUsageRecordListNum(fOilCardUsageRecord);

        // 获取所有主车
        CVehicle cVehicle = new CVehicle();
        List<CVehicle> listCar = cVehicleMapper.selectCVehicleList(cVehicle);
        List<RVehicleConsumeMonth> listMonth = new ArrayList<>();

        for (CVehicle vehicle : listCar) {
            RVehicleConsumeMonth rVehicleConsumeMonth1=new RVehicleConsumeMonth();
            rVehicleConsumeMonth1.setId(UUID.randomUUID()+"");
            rVehicleConsumeMonth1.setVehicleId(vehicle.getId());
            rVehicleConsumeMonth1.setLicensePlate(vehicle.getLicensePlate());
            rVehicleConsumeMonth1.setMonth(month);
            rVehicleConsumeMonth1.setCreateTime(DateUtils.getNowDate());
            rVehicleConsumeMonth1.setCreateBy("定时");
            rVehicleConsumeMonth1.setDeleteFlag(1L);
            // 获取上个月的月度行驶总里程
            rVehicleConsumeMonth1.setLastMonthEndMileage(0L);
            //默认值
            rVehicleConsumeMonth1.setMonthFuelVolume(0L);
            rVehicleConsumeMonth1.setMonthUreaVolume(0L);

            List<FOilCardUsageRecord> listYou = list1.stream().filter(a -> vehicle.getLicensePlate().equals(a.getLicensePlate())).collect(Collectors.toList());
            if (listYou.size() > 0) {
                rVehicleConsumeMonth1.setMonthFuelVolume(listYou.stream()
                        .mapToLong(FOilCardUsageRecord::getOilNum)
                        .sum());
            }
            List<GGoodsReceivingRecord> listNiao = list.stream().filter(a -> vehicle.getLicensePlate().equals(a.getLicensePlate())).collect(Collectors.toList());
            if (listNiao.size() > 0) {
                rVehicleConsumeMonth1.setMonthUreaVolume(listNiao.stream()
                        .mapToLong(GGoodsReceivingRecord::getSpeNum)
                        .sum());
            }
            listMonth.add(rVehicleConsumeMonth1);
        }
        rVehicleConsumeMonthMapper.insertRVehicleConsumeMonthList(listMonth);
    }

    private Long queryUpMonth(String vehicleId){
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);//当前时间前去一个月，即一个月前的时间
        date = calendar.getTime();

        RVehicleConsumeMonth rVehicleConsumeMonth1 = new RVehicleConsumeMonth();
        rVehicleConsumeMonth1.setMonth(date);
        rVehicleConsumeMonth1.setVehicleId(vehicleId);
        List<RVehicleConsumeMonth> list = rVehicleConsumeMonthMapper.selectRVehicleConsumeMonthList(rVehicleConsumeMonth1);

        Long monthEndMileage = 0L;
        if (list.size() > 0) {
            monthEndMileage = list.get(0).getMonthEndMileage();
        }
        return monthEndMileage;
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void insertDriverVacation(){
        personClient.DVacationAdd();
    }
}
