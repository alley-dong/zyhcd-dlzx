package com.dlzx.pfserverperson.mapper;

import java.util.List;
import com.dlzx.pfserverperson.domain.DVacationDay;
import org.apache.ibatis.annotations.Param;

/**
 * 驾驶员-每日考勤记录Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-21
 */
public interface DVacationDayMapper 
{
    /**
     * 查询驾驶员-每日考勤记录
     * 
     * @param id 驾驶员-每日考勤记录ID
     * @return 驾驶员-每日考勤记录
     */
    public DVacationDay selectDVacationDayById(String id);

    /**
     * 查询所有月份有多少记录
     * @param
     * @return
     */
    public List<DVacationDay> selectDVacationDayCountList(DVacationDay dVacationDay);

    /**
     * 通过司机id查询
     * @return
     */
    public List<DVacationDay> selectDVacationDayByDidList(String driverId);

    /**
     * 查询驾驶员-每日考勤记录列表
     * 
     * @param dVacationDay 驾驶员-每日考勤记录
     * @return 驾驶员-每日考勤记录集合
     */
    public List<DVacationDay> selectDVacationDayList(DVacationDay dVacationDay);

    public List<DVacationDay> selectDVacationDayStatisticsList(DVacationDay dVacationDay);

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
     * 删除驾驶员-每日考勤记录
     * 
     * @param id 驾驶员-每日考勤记录ID
     * @return 结果
     */
    public int deleteDVacationDayById(String id);

    /**
     * 批量删除驾驶员-每日考勤记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDVacationDayByIds(String[] ids);

    /**
     * 计算每月金额
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
