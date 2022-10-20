package com.dlzx.pfservercar.service;

import java.util.List;
import com.dlzx.pfservercar.domain.TTransportTaskParts;
import com.dlzx.pfservercar.domain.model.TTransportTaskPartsModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskPartsVm;

/**
 * 任务管理-运输部件Service接口
 * 
 * @author dcl
 * @date 2020-12-18
 */
public interface ITTransportTaskPartsService 
{
    /**
     * 查询任务管理-运输部件
     * 
     * @param id 任务管理-运输部件ID
     * @return 任务管理-运输部件
     */
    public TTransportTaskPartsVm selectTTransportTaskPartsById(String id);

    /**
     * 查询任务管理-运输部件列表
     * 
     * @param tTransportTaskPartsModel 任务管理-运输部件
     * @return 任务管理-运输部件集合
     */
    public List<TTransportTaskPartsVm> selectTTransportTaskPartsList(TTransportTaskPartsModel tTransportTaskPartsModel);

    /**
     * 新增任务管理-运输部件
     * 
     * @param tTransportTaskPartsModel 任务管理-运输部件
     * @return 结果
     */
    public int insertTTransportTaskParts(TTransportTaskPartsModel tTransportTaskPartsModel);

    /**
     * 修改任务管理-运输部件
     * 
     * @param tTransportTaskPartsModel 任务管理-运输部件
     * @return 结果
     */
    public int updateTTransportTaskParts(TTransportTaskPartsModel tTransportTaskPartsModel);

    /**
     * 批量删除任务管理-运输部件
     * 
     * @param ids 需要删除的任务管理-运输部件ID
     * @return 结果
     */
    public int deleteTTransportTaskPartsByIds(String[] ids);

    /**
     * 删除任务管理-运输部件信息
     * 
     * @param id 任务管理-运输部件ID
     * @return 结果
     */
    public int deleteTTransportTaskPartsById(String id);
}
