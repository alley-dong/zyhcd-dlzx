package com.dlzx.pfservercar.domain;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 统计报-信息系统服务费统计对象 r_information_system_service_cost
 *
 * @author dlzx
 * @date 2020-12-22
 */
public class RInformationSystemServiceBiao extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private List<RInformationSystemServiceCost> list;

    /**
     * 金额(元)
     */
    @Excel(name = "金额(元)")
    private BigDecimal amountAll;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("list", getList())
                .append("amountAll", getAmountAll())

                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }


    public List<RInformationSystemServiceCost> getList() {
        return list;
    }

    public void setList(List<RInformationSystemServiceCost> list) {
        this.list = list;
    }

    public BigDecimal getAmountAll() {
        return amountAll;
    }

    public void setAmountAll(BigDecimal amountAll) {
        this.amountAll = amountAll;
    }
}
