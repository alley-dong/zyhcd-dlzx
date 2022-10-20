package com.dlzx.pfservercar.mapper;

import java.util.List;
import com.dlzx.pfservercar.domain.TTransportTaskOvertime;
import com.dlzx.pfservercar.domain.model.TTransportTaskOvertimeModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskOvertimeVm;

/**
 *  任务管理-运输任务加班Mapper接口
 * 
 * @author dcl
 * @date 2020-12-18
 */
public interface TTransportTaskOvertimeMapper 
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
     * @param tTransportTaskOvertime  任务管理-运输任务加班
     * @return  任务管理-运输任务加班集合
     */
    public List<TTransportTaskOvertimeVm> selectTTransportTaskOvertimeList(TTransportTaskOvertime tTransportTaskOvertime);

    /**
     * 新增 任务管理-运输任务加班
     * 
     * @param tTransportTaskOvertime  任务管理-运输任务加班
     * @return 结果
     */
    public int insertTTransportTaskOvertime(TTransportTaskOvertime tTransportTaskOvertime);

    /**
     * 修改 任务管理-运输任务加班
     * 
     * @param tTransportTaskOvertime  任务管理-运输任务加班
     * @return 结果
     */
    public int updateTTransportTaskOvertime(TTransportTaskOvertime tTransportTaskOvertime);

    /**
     * 删除 任务管理-运输任务加班
     * 
     * @param id  任务管理-运输任务加班ID
     * @return 结果
     */
    public int deleteTTransportTaskOvertimeById(String id);

    /**
     * 批量删除 任务管理-运输任务加班
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTTransportTaskOvertimeByIds(String[] ids);

    /**
     * App 运输任务-运输任务加班记录列表
     */
    List<TTransportTaskOvertimeVm> queryTTransportTaskOvertimeList(TTransportTaskOvertimeModel tTransportTaskOvertimeModel);

    /**
     * 更新加班任务时间
    * */
    int UpdateOTMCarTransportTask(TTransportTaskOvertimeModel tTransportTaskOvertimeModel);
}
