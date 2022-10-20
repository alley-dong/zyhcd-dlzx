package com.dlzx.pfserverparts.domain;

import java.math.BigDecimal;

/**
 * @author ylh
 * @program:dlzx
 * @description：薪酬管理--合计DTO
 * @create:2020-12-22 18-50
 */
public class TotalMoney {

    /**合计底薪*/
    private BigDecimal totalBasic;

    /**合计工龄工薪*/
    private BigDecimal totalWage;

    /**绩效工资*/
    private BigDecimal totalMerit;

    /**加班补助*/
    private BigDecimal totalOvertime;

    /**异地等待装卸货补助*/
    private BigDecimal totalWait;

    /**流量补助*/
    private BigDecimal totalFlow;

    public BigDecimal getTotalBasic() {
        return totalBasic;
    }

    public void setTotalBasic(BigDecimal totalBasic) {
        this.totalBasic = totalBasic;
    }

    public BigDecimal getTotalWage() {
        return totalWage;
    }

    public void setTotalWage(BigDecimal totalWage) {
        this.totalWage = totalWage;
    }

    public BigDecimal getTotalMerit() {
        return totalMerit;
    }

    public void setTotalMerit(BigDecimal totalMerit) {
        this.totalMerit = totalMerit;
    }

    public BigDecimal getTotalOvertime() {
        return totalOvertime;
    }

    public void setTotalOvertime(BigDecimal totalOvertime) {
        this.totalOvertime = totalOvertime;
    }

    public BigDecimal getTotalWait() {
        return totalWait;
    }

    public void setTotalWait(BigDecimal totalWait) {
        this.totalWait = totalWait;
    }

    public BigDecimal getTotalFlow() {
        return totalFlow;
    }

    public void setTotalFlow(BigDecimal totalFlow) {
        this.totalFlow = totalFlow;
    }

    public BigDecimal getTotalOther() {
        return totalOther;
    }

    public void setTotalOther(BigDecimal totalOther) {
        this.totalOther = totalOther;
    }

    public BigDecimal getTotalReward() {
        return totalReward;
    }

    public void setTotalReward(BigDecimal totalReward) {
        this.totalReward = totalReward;
    }

    public BigDecimal getTotalPenalty() {
        return totalPenalty;
    }

    public void setTotalPenalty(BigDecimal totalPenalty) {
        this.totalPenalty = totalPenalty;
    }

    /**其他补助*/

    private BigDecimal totalOther;

    /**奖励*/
    private BigDecimal totalReward;

    /**罚款*/
    private BigDecimal totalPenalty;

    public BigDecimal getTotalAll() {
        return totalAll;
    }

    public void setTotalAll(BigDecimal totalAll) {
        this.totalAll = totalAll;
    }

    /**实发金额*/
    private BigDecimal totalAll;
}
