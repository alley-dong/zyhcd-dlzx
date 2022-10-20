package com.dlzx.pfserverperson.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.pfserverperson.domain.DDriver;
import com.dlzx.pfserverperson.domain.model.EveryDayDetailVm;
import com.dlzx.pfserverperson.domain.model.VacationDayVm;
import com.dlzx.pfserverperson.mapper.DDriverMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DVacationDayMapper;
import com.dlzx.pfserverperson.domain.DVacationDay;
import com.dlzx.pfserverperson.service.IDVacationDayService;

/**
 * 驾驶员-每日考勤记录Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-21
 */
@Service
public class DVacationDayServiceImpl implements IDVacationDayService 
{
    @Autowired
    private DVacationDayMapper dVacationDayMapper;
    @Autowired
    private DDriverMapper dDriverMapper;

    /**
     * 查询驾驶员-每日考勤记录
     * 
     * @param id 驾驶员-每日考勤记录ID
     * @return 驾驶员-每日考勤记录
     */
    @Override
    public DVacationDay selectDVacationDayById(String id)
    {
        return dVacationDayMapper.selectDVacationDayById(id);
    }

    /**
     * 查询驾驶员-每日考勤记录列表
     * 
     * @param dVacationDay 驾驶员-每日考勤记录
     * @return 驾驶员-每日考勤记录
     */
    @Override
    public List<DVacationDay> selectDVacationDayList(DVacationDay dVacationDay)
    {
        return dVacationDayMapper.selectDVacationDayList(dVacationDay);
    }

    @Override
    public List<VacationDayVm> selectDVacationDayStatisticsList(DVacationDay dVacationDay) {

        List<DVacationDay> dVacationDays = dVacationDayMapper.selectDVacationDayStatisticsList(dVacationDay);

        // 获取所有的驾驶员(劳务工、长途的)
        List<DDriver> dDrivers = dDriverMapper.selectDDriverOfIdsList();

        List<VacationDayVm> vacationDayVms = new ArrayList<>();
        // 定义显示时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        for (DDriver dDriver : dDrivers) {
            VacationDayVm vacationDayVm = new VacationDayVm();
            // 驾驶员姓名
            vacationDayVm.setDriverName(dDriver.getName());
            List<DVacationDay> driverVacationDayList = dVacationDays.stream()
                    .filter(d -> d.getDriverId().equals(dDriver.getId()))
                    .collect(Collectors.toList());


            // 每个驾驶员的当月出勤列表
            List<EveryDayDetailVm> everyDayDetailVms = new ArrayList<>();
            for (DVacationDay vacationDay : driverVacationDayList) {
                EveryDayDetailVm everyDayDetailVm = new EveryDayDetailVm();
                everyDayDetailVm.setDay(Integer.parseInt(sdf.format(vacationDay.getVacationDate())));
                everyDayDetailVm.setDetail(vacationDay.getStatusEnum());
                everyDayDetailVms.add(everyDayDetailVm);
            }
            vacationDayVm.setdVacationDayList(everyDayDetailVms);

            // 在途
            vacationDayVm.setWay(driverVacationDayList.stream()
                    .filter(d->d.getStatus()==1)
                    .count());

            // 在家
            vacationDayVm.setAtHome(driverVacationDayList.stream()
                    .filter(d->d.getStatus()==2)
                    .count());

            // 请假
            vacationDayVm.setLeave(driverVacationDayList.stream()
                    .filter(d->d.getStatus()==3)
                    .count());

            // 出勤天数
            vacationDayVm.setTotalAttendance(driverVacationDayList.stream()
                    .filter(d->d.getStatus()!=3)
                    .count());

            vacationDayVms.add(vacationDayVm);
        }

        return vacationDayVms;
    }

    @Override
    public List<DVacationDay> selectDVacationDayCountList(DVacationDay dVacationDay) {
        return dVacationDayMapper.selectDVacationDayCountList(dVacationDay);
    }

    @Override
    public List<DVacationDay> selectDVacationDayByDidList(String driverId) {
        return dVacationDayMapper.selectDVacationDayByDidList(driverId);
    }

    /**
     * 新增驾驶员-每日考勤记录
     * 
     * @param dVacationDay 驾驶员-每日考勤记录
     * @return 结果
     */
    @Override
    public int insertDVacationDay(DVacationDay dVacationDay)
    {
        dVacationDay.setCreateTime(DateUtils.getNowDate());
        return dVacationDayMapper.insertDVacationDay(dVacationDay);
    }

    /**
     * 修改驾驶员-每日考勤记录
     * 
     * @param dVacationDay 驾驶员-每日考勤记录
     * @return 结果
     */
    @Override
    public int updateDVacationDay(DVacationDay dVacationDay)
    {
        dVacationDay.setUpdateTime(DateUtils.getNowDate());
        return dVacationDayMapper.updateDVacationDay(dVacationDay);
    }

    /**
     * 批量删除驾驶员-每日考勤记录
     * 
     * @param ids 需要删除的驾驶员-每日考勤记录ID
     * @return 结果
     */
    @Override
    public int deleteDVacationDayByIds(String[] ids)
    {
        return dVacationDayMapper.deleteDVacationDayByIds(ids);
    }

    /**
     * 删除驾驶员-每日考勤记录信息
     * 
     * @param id 驾驶员-每日考勤记录ID
     * @return 结果
     */
    @Override
    public int deleteDVacationDayById(String id)
    {
        return dVacationDayMapper.deleteDVacationDayById(id);
    }

    /**
     * 计算金额
     */
    @Override
    public List<DVacationDay> selectDVacationDaySalaryByDriverId(DVacationDay dVacationDay){
        return dVacationDayMapper.selectDVacationDaySalaryByDriverId(dVacationDay);
    }

    /**
     * @describe 批量新增
     * @author DongCL
     * @date 2021-02-07 11:56
     * @param
     * @return
     */
    @Override
    public int insertList(List<DVacationDay> dVacationDays) {
        return dVacationDayMapper.insertList(dVacationDays);
    }
}
