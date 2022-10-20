package com.dlzx.pfservercar.domain.model;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.dlzx.pfservercar.domain.TTransportTaskInspectItem;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 任务管理-运输任务点检检查项对象 t_transport_task_inspect_item
 * 
 * @author dlzx
 * @date 2020-12-18
 */
public class TTransportTaskInspectItemModel extends TTransportTaskInspectItem
{
    private static final long serialVersionUID = 1L;

    /* 检查项照片 */
    private String[] fileList;

    /* 检查项名称 */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getFileList() {
        return fileList;
    }

    public void setFileList(String[] fileList) {
        this.fileList = fileList;
    }
}
