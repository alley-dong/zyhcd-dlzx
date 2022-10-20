package com.dlzx.pfservercar.mapper;

import java.util.List;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.pfservercar.domain.TTransportTaskShipmentStop;
import com.dlzx.pfservercar.domain.model.OTMTransportTaskShipmentStopModel;
import com.dlzx.pfservercar.domain.model.TTransportTaskShipmentStopModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskShipmentStopVm;

/**
 * 任务管理-运输任务站点Mapper接口
 * 
 * @author DongCL
 * @date 2021-01-05
 */
public interface TTransportTaskShipmentStopMapper 
{
    /**
     * 查询任务管理-运输任务站点
     * 
     * @param id 任务管理-运输任务站点ID
     * @return 任务管理-运输任务站点
     */
    public TTransportTaskShipmentStopVm selectTTransportTaskShipmentStopById(String id);

    /**
     * 查询任务管理-运输任务站点列表
     * 
     * @param tTransportTaskShipmentStop 任务管理-运输任务站点
     * @return 任务管理-运输任务站点集合
     */
    public List<TTransportTaskShipmentStopVm> selectTTransportTaskShipmentStopList(TTransportTaskShipmentStopModel tTransportTaskShipmentStop);

    /**
     * 新增任务管理-运输任务站点
     * 
     * @param tTransportTaskShipmentStop 任务管理-运输任务站点
     * @return 结果
     */
    public int insertTTransportTaskShipmentStop(TTransportTaskShipmentStopModel tTransportTaskShipmentStop);

    /**
     * 修改任务管理-运输任务站点
     * 
     * @param tTransportTaskShipmentStop 任务管理-运输任务站点
     * @return 结果
     */
    public int updateTTransportTaskShipmentStop(TTransportTaskShipmentStopModel tTransportTaskShipmentStop);

    /**
     * 删除任务管理-运输任务站点
     * 
     * @param id 任务管理-运输任务站点ID
     * @return 结果
     */
    public int deleteTTransportTaskShipmentStopById(String id);

    /**
     * 批量删除任务管理-运输任务站点
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTTransportTaskShipmentStopByIds(String[] ids);

    /**
     * @describe OTM 车队运输任务站点
     * @author DongCL
     * @date 2021-01-07 13:34
     * @param otmTransportTaskShipmentStopModel
     * @return AjaxResult
     */
    int insertOTMTransportTaskShipmentStop(OTMTransportTaskShipmentStopModel otmTransportTaskShipmentStopModel);
}
