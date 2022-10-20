package com.dlzx.pfservercar.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 统计报-ETC费用统计对象 r_etc_cost
 *
 * @author dlzx
 * @date 2020-12-17
 */
public class REtcCostBiao extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private List<REtcCost> list;

    /**
     * 金额（含税）
     */
    @Excel(name = "金额(含税)")
    private BigDecimal taxAmountAll;

    /**
     * 金额（元 不含税）
     */
    @Excel(name = "金额(不含税)")
    private BigDecimal amountAll;




    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)

                .append("list", getList())
                .append("taxAmountAll", getTaxAmountAll())
                .append("amountAll", getAmountAll())

                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())

                .toString();
    }


    public List<REtcCost> getList() {
        return list;
    }

    public void setList(List<REtcCost> list) {
        this.list = list;
    }

    public BigDecimal getTaxAmountAll() {
        return taxAmountAll;
    }

    public void setTaxAmountAll(BigDecimal taxAmountAll) {
        this.taxAmountAll = taxAmountAll;
    }

    public BigDecimal getAmountAll() {
        return amountAll;
    }

    public void setAmountAll(BigDecimal amountAll) {
        this.amountAll = amountAll;
    }
}
