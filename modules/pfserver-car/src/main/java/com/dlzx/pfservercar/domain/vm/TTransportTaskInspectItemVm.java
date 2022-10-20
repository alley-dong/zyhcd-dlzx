package com.dlzx.pfservercar.domain.vm;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.dlzx.pfservercar.domain.TTransportTaskInspectItem;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 任务管理-运输任务点检检查项对象 t_transport_task_inspect_item
 * 
 * @author dlzx
 * @date 2020-12-18
 */
public class TTransportTaskInspectItemVm extends TTransportTaskInspectItem
{
    private static final long serialVersionUID = 1L;

    /** 维修状态翻译 */
    private String statusEnum;

    /** 检查项类型 */
    private String typeEnum;

    /* 检查项照片 */
    private String[] fileList;

    public String[] getFileList() {
        return fileList;
    }

    public void setFileList(String[] fileList) {
        this.fileList = fileList;
    }

    public String getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(String statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(String typeEnum) {
        this.typeEnum = typeEnum;
    }
}
