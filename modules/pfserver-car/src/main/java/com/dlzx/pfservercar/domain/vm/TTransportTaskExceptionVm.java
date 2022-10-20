package com.dlzx.pfservercar.domain.vm;

import com.dlzx.pfservercar.domain.TTransportTaskException;
import java.util.List;
/**
 * 任务管理-运输任务异常上报对象
 * 
 * @author dcl
 * @date 2020-12-18
 */
public class TTransportTaskExceptionVm extends TTransportTaskException
{
    private static final long serialVersionUID = 1L;

    /* 状态翻译 */
    private String statusEnum;

    /* 运单类型 */
    private String typeEnum;

    /*任务异常情况照片 */
    private List<String> taskExceptionFileList;


    public List<String> getTaskExceptionFileList() {
        return taskExceptionFileList;
    }

    public void setTaskExceptionFileList(List<String> taskExceptionFileList) {
        this.taskExceptionFileList = taskExceptionFileList;
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
