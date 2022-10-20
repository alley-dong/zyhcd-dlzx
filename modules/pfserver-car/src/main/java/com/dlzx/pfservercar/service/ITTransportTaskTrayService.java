package com.dlzx.pfservercar.service;

import java.util.List;
import com.dlzx.pfservercar.domain.TTransportTaskTray;
import com.dlzx.pfservercar.domain.model.TTransportTaskTrayModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskTrayVm;

/**
 * 任务管理-运输任务托盘Service接口
 * 
 * @author DongCL
 * @date 2021-01-05
 */
public interface ITTransportTaskTrayService 
{
    /**
     * 查询任务管理-运输任务托盘
     * 
     * @param id 任务管理-运输任务托盘ID
     * @return 任务管理-运输任务托盘
     */
    public TTransportTaskTrayVm selectTTransportTaskTrayById(String id);

    /**
     * 查询任务管理-运输任务托盘列表
     * 
     * @param tTransportTaskTray 任务管理-运输任务托盘
     * @return 任务管理-运输任务托盘集合
     */
    public List<TTransportTaskTrayVm> selectTTransportTaskTrayList(TTransportTaskTrayModel tTransportTaskTray);

    /**
     * 新增任务管理-运输任务托盘
     * 
     * @param tTransportTaskTray 任务管理-运输任务托盘
     * @return 结果
     */
    public int insertTTransportTaskTray(TTransportTaskTrayModel tTransportTaskTray);

    /**
     * 修改任务管理-运输任务托盘
     * 
     * @param tTransportTaskTray 任务管理-运输任务托盘
     * @return 结果
     */
    public int updateTTransportTaskTray(TTransportTaskTrayModel tTransportTaskTray);

    /**
     * 批量删除任务管理-运输任务托盘
     * 
     * @param ids 需要删除的任务管理-运输任务托盘ID
     * @return 结果
     */
    public int deleteTTransportTaskTrayByIds(String[] ids);

    /**
     * 删除任务管理-运输任务托盘信息
     * 
     * @param id 任务管理-运输任务托盘ID
     * @return 结果
     */
    public int deleteTTransportTaskTrayById(String id);
}
