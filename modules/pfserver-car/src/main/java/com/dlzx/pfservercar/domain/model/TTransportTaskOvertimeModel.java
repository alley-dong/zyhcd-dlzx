package com.dlzx.pfservercar.domain.model;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.dlzx.pfservercar.domain.TTransportTaskOvertime;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 *  任务管理-运输任务加班对象 t_transport_task_overtime
 * 
 * @author dcl
 * @date 2020-12-18
 */
public class TTransportTaskOvertimeModel extends TTransportTaskOvertime
{
    private static final long serialVersionUID = 1L;

    // 1进行中 2已完成
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
