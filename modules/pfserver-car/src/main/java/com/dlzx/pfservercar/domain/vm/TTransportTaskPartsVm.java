package com.dlzx.pfservercar.domain.vm;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.dlzx.pfservercar.domain.TTransportTaskParts;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 任务管理-运输部件对象 t_transport_task_parts
 * 
 * @author dcl
 * @date 2020-12-18
 */
public class TTransportTaskPartsVm extends TTransportTaskParts
{
    private static final long serialVersionUID = 1L;


    /**
     * 类型翻译
    * */
    private String typeEnum;

    /**
     * 零件类型
    * */
    private String partsTypeEnum;

    public String getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(String typeEnum) {
        this.typeEnum = typeEnum;
    }

    public String getPartsTypeEnum() {
        return partsTypeEnum;
    }

    public void setPartsTypeEnum(String partsTypeEnum) {
        this.partsTypeEnum = partsTypeEnum;
    }
}
