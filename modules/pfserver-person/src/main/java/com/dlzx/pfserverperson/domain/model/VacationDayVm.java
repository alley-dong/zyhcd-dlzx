package com.dlzx.pfserverperson.domain.model;

import com.dlzx.pfserverperson.domain.DVacationDay;

import java.util.List;

/**
 * 类描述：请假统计返回
 * @className VacationDayExcelVm
 * company 赛拓信息技术有限公司
 * @author DongCL
 * @date 2021-02-07 13:29
 */
public class VacationDayVm {

    /** 驾驶员姓名 */
    private String driverName;
    /** 在途 */
    private Long way = 0L;
    /** 在家 */
    private Long atHome = 0L;
    /** 请假 */
    private Long leave = 0L;
    /** 出勤天数 */
    private Long totalAttendance = 0L;

    /** 存放状态 */
    private List<EveryDayDetailVm> dVacationDayList;


    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Long getWay() {
        return way;
    }

    public void setWay(Long way) {
        this.way = way;
    }

    public Long getAtHome() {
        return atHome;
    }

    public void setAtHome(Long atHome) {
        this.atHome = atHome;
    }

    public Long getLeave() {
        return leave;
    }

    public void setLeave(Long leave) {
        this.leave = leave;
    }

    public Long getTotalAttendance() {
        return totalAttendance;
    }

    public void setTotalAttendance(Long totalAttendance) {
        this.totalAttendance = totalAttendance;
    }

    public List<EveryDayDetailVm> getdVacationDayList() {
        return dVacationDayList;
    }

    public void setdVacationDayList(List<EveryDayDetailVm> dVacationDayList) {
        this.dVacationDayList = dVacationDayList;
    }
}
