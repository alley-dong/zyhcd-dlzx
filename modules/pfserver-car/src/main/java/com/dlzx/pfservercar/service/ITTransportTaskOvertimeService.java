package com.dlzx.pfservercar.service;

import java.util.List;
import com.dlzx.pfservercar.domain.TTransportTaskOvertime;
import com.dlzx.pfservercar.domain.model.TTransportTaskOvertimeModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskOvertimeVm;

/**
 *  任务管理-运输任务加班Service接口
 * 
 * @author dcl
 * @date 2020-12-18
 */
public interface ITTransportTaskOvertimeService 
{
    /**
     * 查询 任务管理-运输任务加班
     * 
     * @param id  任务管理-运输任务加班ID
     * @return  任务管理-运输任务加班
     */
    public TTransportTaskOvertimeVm selectTTransportTaskOvertimeById(String id);

    /**
     * 查询 任务管理-运输任务加班列表
     * 
     * @param tTransportTaskOvertimeModel  任务管理-运输任务加班
     * @return  任务管理-运输任务加班集合
     */
    public List<TTransportTaskOvertimeVm> selectTTransportTaskOvertimeList(TTransportTaskOvertimeModel tTransportTaskOvertimeModel);

    /**
     * 新增 任务管理-运输任务加班
     * 
     * @param tTransportTaskOvertimeModel  任务管理-运输任务加班
     * @return 结果
     */
    public int insertTTransportTaskOvertime(TTransportTaskOvertimeModel tTransportTaskOvertimeModel);

    /**
     * 修改 任务管理-运输任务加班
     * 
     * @param tTransportTaskOvertimeModel  任务管理-运输任务加班
     * @return 结果
     */
    public int updateTTransportTaskOvertime(TTransportTaskOvertimeModel tTransportTaskOvertimeModel);

    /**
     * 批量删除 任务管理-运输任务加班
     * 
     * @param ids 需要删除的 任务管理-运输任务加班ID
     * @return 结果
     */
    public int deleteTTransportTaskOvertimeByIds(String[] ids);

    /**
     * 删除 任务管理-运输任务加班信息
     * 
     * @param id  任务管理-运输任务加班ID
     * @return 结果
     */
    public int deleteTTransportTaskOvertimeById(String id);

    /**
     * App 运输任务-运输任务加班记录列表
     */
    List<TTransportTaskOvertimeVm> queryTTransportTaskOvertimeList(TTransportTaskOvertimeModel tTransportTaskOvertimeModel);

}
