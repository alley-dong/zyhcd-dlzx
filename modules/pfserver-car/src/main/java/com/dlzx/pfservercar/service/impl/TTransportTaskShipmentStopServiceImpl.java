package com.dlzx.pfservercar.service.impl;

import java.util.List;

import com.dlzx.pfservercar.domain.model.TTransportTaskShipmentStopModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskShipmentStopVm;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.TTransportTaskShipmentStopMapper;
import com.dlzx.pfservercar.domain.TTransportTaskShipmentStop;
import com.dlzx.pfservercar.service.ITTransportTaskShipmentStopService;

/**
 * 任务管理-运输任务站点Service业务层处理
 * 
 * @author DongCL
 * @date 2021-01-05
 */
@Service
public class TTransportTaskShipmentStopServiceImpl implements ITTransportTaskShipmentStopService 
{
    @Autowired
    private TTransportTaskShipmentStopMapper tTransportTaskShipmentStopMapper;

    /**
     * 查询任务管理-运输任务站点
     * 
     * @param id 任务管理-运输任务站点ID
     * @return 任务管理-运输任务站点
     */
    @Override
    public TTransportTaskShipmentStopVm selectTTransportTaskShipmentStopById(String id)
    {
        return tTransportTaskShipmentStopMapper.selectTTransportTaskShipmentStopById(id);
    }

    /**
     * 查询任务管理-运输任务站点列表
     * 
     * @param tTransportTaskShipmentStop 任务管理-运输任务站点
     * @return 任务管理-运输任务站点
     */
    @Override
    public List<TTransportTaskShipmentStopVm> selectTTransportTaskShipmentStopList(TTransportTaskShipmentStopModel tTransportTaskShipmentStop)
    {
        return tTransportTaskShipmentStopMapper.selectTTransportTaskShipmentStopList(tTransportTaskShipmentStop);
    }

    /**
     * 新增任务管理-运输任务站点
     * 
     * @param tTransportTaskShipmentStop 任务管理-运输任务站点
     * @return 结果
     */
    @Override
    public int insertTTransportTaskShipmentStop(TTransportTaskShipmentStopModel tTransportTaskShipmentStop)
    {
        tTransportTaskShipmentStop.setCreateTime(DateUtils.getNowDate());
        return tTransportTaskShipmentStopMapper.insertTTransportTaskShipmentStop(tTransportTaskShipmentStop);
    }

    /**
     * 修改任务管理-运输任务站点
     * 
     * @param tTransportTaskShipmentStop 任务管理-运输任务站点
     * @return 结果
     */
    @Override
    public int updateTTransportTaskShipmentStop(TTransportTaskShipmentStopModel tTransportTaskShipmentStop)
    {
        tTransportTaskShipmentStop.setUpdateTime(DateUtils.getNowDate());
        return tTransportTaskShipmentStopMapper.updateTTransportTaskShipmentStop(tTransportTaskShipmentStop);
    }

    /**
     * 批量删除任务管理-运输任务站点
     * 
     * @param ids 需要删除的任务管理-运输任务站点ID
     * @return 结果
     */
    @Override
    public int deleteTTransportTaskShipmentStopByIds(String[] ids)
    {
        return tTransportTaskShipmentStopMapper.deleteTTransportTaskShipmentStopByIds(ids);
    }

    /**
     * 删除任务管理-运输任务站点信息
     * 
     * @param id 任务管理-运输任务站点ID
     * @return 结果
     */
    @Override
    public int deleteTTransportTaskShipmentStopById(String id)
    {
        return tTransportTaskShipmentStopMapper.deleteTTransportTaskShipmentStopById(id);
    }
}
