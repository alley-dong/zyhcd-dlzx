package com.dlzx.pfservercar.mapper;

import java.util.List;
import com.dlzx.pfservercar.domain.TTransportTaskParts;
import com.dlzx.pfservercar.domain.model.OTMTTransportTaskModel;
import com.dlzx.pfservercar.domain.model.OTMTransportTaskOrderModel;
import com.dlzx.pfservercar.domain.model.OTMTransportTaskOrderPartsModel;
import com.dlzx.pfservercar.domain.model.TTransportTaskPartsModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskPartsVm;

/**
 * 任务管理-运输部件Mapper接口
 * 
 * @author dcl
 * @date 2020-12-18
 */
public interface TTransportTaskPartsMapper 
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
     * 删除任务管理-运输部件
     * 
     * @param id 任务管理-运输部件ID
     * @return 结果
     */
    public int deleteTTransportTaskPartsById(String id);

    /**
     * 批量删除任务管理-运输部件
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTTransportTaskPartsByIds(String[] ids);

    /**
    * 根据任务ID获取部件
    * @param taskId
    * */
    List<TTransportTaskPartsVm> selectTaskPartsByTaskId(String taskId);

    /**
     * 批量插入
    * */
    int insertOrderList(List<OTMTransportTaskOrderModel> otmTransportTaskOrderModels);

    /**
     * 批量插入
     * */
    int insertOrderPartsList(List<OTMTransportTaskOrderPartsModel> otmTransportTaskOrderPartsModels);

    /**
     * 根据订单ID查询 任务订单
    * */
    List<TTransportTaskParts> selectTaskPartsByOrderId(List<String> orderids);

    /**
     * @describe OTM 车队运输任务订单明细数据
     * @author DongCL
     * @date 2020-12-28 19:36
     * @param otmTransportTaskOrderPartsModel
     * @return
     */
    int insertOTMTransportTaskParts(OTMTransportTaskOrderPartsModel otmTransportTaskOrderPartsModel);
}
