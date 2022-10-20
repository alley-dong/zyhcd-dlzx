package com.dlzx.pfserverperson.service;

import java.util.List;
import com.dlzx.pfserverperson.domain.DVacationDay;
import com.dlzx.pfserverperson.domain.model.VacationDayVm;
import org.apache.ibatis.annotations.Param;

/**
 * 驾驶员-每日考勤记录Service接口
 * 
 * @author dlzx
 * @date 2020-12-21
 */
public interface IDVacationDayService 
{
    /**
     * 查询驾驶员-每日考勤记录
     * 
     * @param id 驾驶员-每日考勤记录ID
     * @return 驾驶员-每日考勤记录
     */
    public DVacationDay selectDVacationDayById(String id);

    /**
     * 查询驾驶员-每日考勤记录列表
     * 
     * @param dVacationDay 驾驶员-每日考勤记录
     * @return 驾驶员-每日考勤记录集合
     */
    public List<DVacationDay> selectDVacationDayList(DVacationDay dVacationDay);

    public List<VacationDayVm> selectDVacationDayStatisticsList(DVacationDay dVacationDay);

    /**
     * 查询每个月多少条记录
     * @return
     */
    public List<DVacationDay> selectDVacationDayCountList(DVacationDay dVacationDay);

    public List<DVacationDay> selectDVacationDayByDidList(String driverId);

    /**
     * 新增驾驶员-每日考勤记录
     * 
     * @param dVacationDay 驾驶员-每日考勤记录
     * @return 结果
     */
    public int insertDVacationDay(DVacationDay dVacationDay);

    /**
     * 修改驾驶员-每日考勤记录
     * 
     * @param dVacationDay 驾驶员-每日考勤记录
     * @return 结果
     */
    public int updateDVacationDay(DVacationDay dVacationDay);

    /**
     * 批量删除驾驶员-每日考勤记录
     * 
     * @param ids 需要删除的驾驶员-每日考勤记录ID
     * @return 结果
     */
    public int deleteDVacationDayByIds(String[] ids);

    /**
     * 删除驾驶员-每日考勤记录信息
     * 
     * @param id 驾驶员-每日考勤记录ID
     * @return 结果
     */
    public int deleteDVacationDayById(String id);

    /**
     * 计算底薪金额
     */
    public List<DVacationDay> selectDVacationDaySalaryByDriverId(DVacationDay dVacationDay);

    /**
     * @describe 批量新增
     * @author DongCL
     * @date 2021-02-07 11:56
     * @param
     * @return
     */
    int insertList(List<DVacationDay> dVacationDays);
}
