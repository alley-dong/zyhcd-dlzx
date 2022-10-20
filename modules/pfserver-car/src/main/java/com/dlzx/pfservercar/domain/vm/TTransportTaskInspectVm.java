package com.dlzx.pfservercar.domain.vm;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.dlzx.pfservercar.domain.TTransportTaskInspect;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 任务管理-运输任务点检对象 t_transport_task_inspect
 * 
 * @author dcl
 * @date 2020-12-18
 */
public class TTransportTaskInspectVm extends TTransportTaskInspect
{
    private static final long serialVersionUID = 1L;

    /* 点检类型 */
    private String typeEnum;

    /* 点检检查项 */
    private List<TTransportTaskInspectItemVm> tTransportTaskInspectItemVms;


    public String getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(String typeEnum) {
        this.typeEnum = typeEnum;
    }

    public List<TTransportTaskInspectItemVm> gettTransportTaskInspectItemVms() {
        return tTransportTaskInspectItemVms;
    }

    public void settTransportTaskInspectItemVms(List<TTransportTaskInspectItemVm> tTransportTaskInspectItemVms) {
        this.tTransportTaskInspectItemVms = tTransportTaskInspectItemVms;
    }
}
