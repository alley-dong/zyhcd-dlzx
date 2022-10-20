package com.dlzx.pfserverperson.domain.model;

/**
 * 类描述：考勤每天的详情
 * @className EveryDayDetailExcelVm
 * company 赛拓信息技术有限公司
 * @author DongCL
 * @date 2021-02-24 17:42
 */
public class EveryDayDetailVm {
    private Integer day;
    private String detail;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
