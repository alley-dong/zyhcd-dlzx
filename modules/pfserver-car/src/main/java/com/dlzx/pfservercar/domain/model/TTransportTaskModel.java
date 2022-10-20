package com.dlzx.pfservercar.domain.model;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.dlzx.pfservercar.domain.TTransportTask;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * 任务管理-运输任务传入对象
 * 
 * @author dcl
 * @date 2020-12-18
 */
public class TTransportTaskModel extends TTransportTask
{
    private static final long serialVersionUID = 1L;

    /* 任务装货图片列表 */
    private List<String> loadingFileList;

    /* 任务卸货图片列表 */
    private List<String> unloadFilesList;

    /** 任务同步时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date synchronizationTime;

    public Date getSynchronizationTime() {
        return synchronizationTime;
    }

    public void setSynchronizationTime(Date synchronizationTime) {
        this.synchronizationTime = synchronizationTime;
    }

    public List<String> getLoadingFileList() {
        return loadingFileList;
    }

    public void setLoadingFileList(List<String> loadingFileList) {
        this.loadingFileList = loadingFileList;
    }

    public List<String> getUnloadFilesList() {
        return unloadFilesList;
    }

    public void setUnloadFilesList(List<String> unloadFilesList) {
        this.unloadFilesList = unloadFilesList;
    }
}
