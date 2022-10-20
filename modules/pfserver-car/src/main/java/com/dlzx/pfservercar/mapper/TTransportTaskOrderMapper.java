package com.dlzx.pfservercar.mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.dlzx.pfservercar.domain.TTransportTaskOrder;
import com.dlzx.pfservercar.domain.model.OTMTransportTaskOrderModel;
import com.dlzx.pfservercar.domain.model.TTransportTaskOrderModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskOrderVm;

/**
 * 任务管理-运输任务订单Mapper接口
 * 
 * @author DongCL
 * @date 2021-01-05
 */
public interface TTransportTaskOrderMapper 
{
    /**
     * 查询任务管理-运输任务订单
     * 
     * @param id 任务管理-运输任务订单ID
     * @return 任务管理-运输任务订单
     */
    public TTransportTaskOrderVm selectTTransportTaskOrderById(String id);

    /**
     * 查询任务管理-运输任务订单列表
     * 
     * @param tTransportTaskOrder 任务管理-运输任务订单
     * @return 任务管理-运输任务订单集合
     */
    public List<TTransportTaskOrderVm> selectTTransportTaskOrderList(TTransportTaskOrderModel tTransportTaskOrder);

    /**
     * 新增任务管理-运输任务订单
     * 
     * @param tTransportTaskOrder 任务管理-运输任务订单
     * @return 结果
     */
    public int insertTTransportTaskOrder(TTransportTaskOrderModel tTransportTaskOrder);

    /**
     * 修改任务管理-运输任务订单
     * 
     * @param tTransportTaskOrder 任务管理-运输任务订单
     * @return 结果
     */
    public int updateTTransportTaskOrder(TTransportTaskOrderModel tTransportTaskOrder);

    /**
     * 删除任务管理-运输任务订单
     * 
     * @param id 任务管理-运输任务订单ID
     * @return 结果
     */
    public int deleteTTransportTaskOrderById(String id);

    /**
     * 批量删除任务管理-运输任务订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTTransportTaskOrderByIds(String[] ids);

    /**
     * @param otmTransportTaskOrderModel
     * @return
     * @describe OTM 车队运输任务订单数据
     * @author DongCL
     * @date 2020-12-29 10:54
     */
    int insertOTMTransportTaskOrder(OTMTransportTaskOrderModel otmTransportTaskOrderModel);

    TTransportTaskOrderVm selectAllByOrderMovementGid(@Param("orderMovementGid")String orderMovementGid);


}
