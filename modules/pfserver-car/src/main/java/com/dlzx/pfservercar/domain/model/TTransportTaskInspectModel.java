package com.dlzx.pfservercar.domain.model;

import com.dlzx.pfservercar.domain.TTransportTaskInspect;
import com.dlzx.pfservercar.domain.vm.TTransportTaskInspectItemVm;

import java.util.List;

/**
 * 任务管理-运输任务点检对象
 * 
 * @author dcl
 * @date 2020-12-18
 */
public class TTransportTaskInspectModel extends TTransportTaskInspect
{
    private static final long serialVersionUID = 1L;

    /* 点检检查项 */
    private List<TTransportTaskInspectItemModel> tTransportTaskInspectItemModels;

    public List<TTransportTaskInspectItemModel> gettTransportTaskInspectItemModels() {
        return tTransportTaskInspectItemModels;
    }

    public void settTransportTaskInspectItemModels(List<TTransportTaskInspectItemModel> tTransportTaskInspectItemModels) {
        this.tTransportTaskInspectItemModels = tTransportTaskInspectItemModels;
    }
}
