package com.dlzx.pfservercar.domain.vm;

import com.dlzx.common.core.annotation.Excel;
import com.dlzx.common.core.web.domain.BaseEntity;
import com.dlzx.pfservercar.domain.FFuelPlaceItem;
import com.dlzx.pfservercar.domain.TTransportTask;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 任务管理-运输任务返回对象
 *
 * @author dcl
 * @date 2020-12-18
 */
public class TTransportTaskVm extends TTransportTask {

    private static final long serialVersionUID = 1L;

    /* 司机线路 - 司机姓名 */
    private String driverNamePlus;

    /* 状态翻译 */
    private String statusEnum;

    /* 运单类型 */
    private String shipmentTypeEnum;

    /* 任务部件列表 */
    private List<TTransportTaskPartsVm> tTransportTaskPartsVmList;

    /* 任务站点列表 */
    private List<TTransportTaskShipmentStopVm> tTransportTaskShipmentStopVmList;

    /* 任务托盘列表 */
    private List<TTransportTaskTrayVm> tTransportTaskTrayVmList;

    /* 任务装货图片列表 */
    private List<String> loadingFileList;

    /* 任务卸货图片列表 */
    private List<String> unloadFilesList;

    /** 发车点检 */
    private String startInspect;

    /** 在途点检 */
    private String processInspect;

    /** 归队点检 */
    private String endInspect;

    /* 任务点检列表 */
    private List<TTransportTaskInspectVm> tTransportTaskInspectVms;

    /* 定点加油站列表 */
    private List<FFuelPlaceItem> fFuelPlaceItems;

    /* 异常信息 */
    private TTransportTaskExceptionVm tTransportTaskExceptionVm;

    /* 出发时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date taskStartDate;

    /* 到达时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date taskEndDate;

    public Date getTaskStartDate() {
        return taskStartDate;
    }

    public void setTaskStartDate(Date taskStartDate) {
        this.taskStartDate = taskStartDate;
    }

    public Date getTaskEndDate() {
        return taskEndDate;
    }

    public void setTaskEndDate(Date taskEndDate) {
        this.taskEndDate = taskEndDate;
    }

    public List<TTransportTaskTrayVm> gettTransportTaskTrayVmList() {
        return tTransportTaskTrayVmList;
    }

    public void settTransportTaskTrayVmList(List<TTransportTaskTrayVm> tTransportTaskTrayVmList) {
        this.tTransportTaskTrayVmList = tTransportTaskTrayVmList;
    }

    public TTransportTaskExceptionVm gettTransportTaskExceptionVm() {
        return tTransportTaskExceptionVm;
    }

    public void settTransportTaskExceptionVm(TTransportTaskExceptionVm tTransportTaskExceptionVm) {
        this.tTransportTaskExceptionVm = tTransportTaskExceptionVm;
    }

    public List<TTransportTaskShipmentStopVm> gettTransportTaskShipmentStopVmList() {
        return tTransportTaskShipmentStopVmList;
    }

    public void settTransportTaskShipmentStopVmList(List<TTransportTaskShipmentStopVm> tTransportTaskShipmentStopVmList) {
        this.tTransportTaskShipmentStopVmList = tTransportTaskShipmentStopVmList;
    }

    public List<FFuelPlaceItem> getfFuelPlaceItems() {
        return fFuelPlaceItems;
    }

    public void setfFuelPlaceItems(List<FFuelPlaceItem> fFuelPlaceItems) {
        this.fFuelPlaceItems = fFuelPlaceItems;
    }

    public List<TTransportTaskInspectVm> gettTransportTaskInspectVms() {
        return tTransportTaskInspectVms;
    }

    public void settTransportTaskInspectVms(List<TTransportTaskInspectVm> tTransportTaskInspectVms) {
        this.tTransportTaskInspectVms = tTransportTaskInspectVms;
    }

    public String getStartInspect() {
        return startInspect;
    }

    public void setStartInspect(String startInspect) {
        this.startInspect = startInspect;
    }

    public String getProcessInspect() {
        return processInspect;
    }

    public void setProcessInspect(String processInspect) {
        this.processInspect = processInspect;
    }

    public String getEndInspect() {
        return endInspect;
    }

    public void setEndInspect(String endInspect) {
        this.endInspect = endInspect;
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

    public List<TTransportTaskPartsVm> gettTransportTaskPartsVmList() {
        return tTransportTaskPartsVmList;
    }

    public void settTransportTaskPartsVmList(List<TTransportTaskPartsVm> tTransportTaskPartsVmList) {
        this.tTransportTaskPartsVmList = tTransportTaskPartsVmList;
    }

    public String getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(String statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getShipmentTypeEnum() {
        return shipmentTypeEnum;
    }

    public void setShipmentTypeEnum(String shipmentTypeEnum) {
        this.shipmentTypeEnum = shipmentTypeEnum;
    }

    public String getDriverNamePlus() {
        return driverNamePlus;
    }

    public void setDriverNamePlus(String driverNamePlus) {
        this.driverNamePlus = driverNamePlus;
    }
}
