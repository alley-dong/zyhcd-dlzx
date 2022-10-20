package com.dlzx.pfservercar.service.impl;

import java.util.List;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.pfservercar.domain.model.TTransportTaskOrderModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskOrderVm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.TTransportTaskOrderMapper;
import com.dlzx.pfservercar.domain.TTransportTaskOrder;
import com.dlzx.pfservercar.service.ITTransportTaskOrderService;

/**
 * 任务管理-运输任务订单Service业务层处理
 * 
 * @author DongCL
 * @date 2021-01-05
 */
@Service
public class TTransportTaskOrderServiceImpl implements ITTransportTaskOrderService 
{
    @Autowired
    private TTransportTaskOrderMapper tTransportTaskOrderMapper;

    /**
     * 查询任务管理-运输任务订单
     * 
     * @param id 任务管理-运输任务订单ID
     * @return 任务管理-运输任务订单
     */
    @Override
    public TTransportTaskOrderVm selectTTransportTaskOrderById(String id)
    {
        return tTransportTaskOrderMapper.selectTTransportTaskOrderById(id);
    }

    /**
     * 查询任务管理-运输任务订单列表
     * 
     * @param tTransportTaskOrder 任务管理-运输任务订单
     * @return 任务管理-运输任务订单
     */
    @Override
    public List<TTransportTaskOrderVm> selectTTransportTaskOrderList(TTransportTaskOrderModel tTransportTaskOrder)
    {
        return tTransportTaskOrderMapper.selectTTransportTaskOrderList(tTransportTaskOrder);
    }

    /**
     * 新增任务管理-运输任务订单
     * 
     * @param tTransportTaskOrder 任务管理-运输任务订单
     * @return 结果
     */
    @Override
    public int insertTTransportTaskOrder(TTransportTaskOrderModel tTransportTaskOrder)
    {
        tTransportTaskOrder.setCreateTime(DateUtils.getNowDate());
        return tTransportTaskOrderMapper.insertTTransportTaskOrder(tTransportTaskOrder);
    }

    /**
     * 修改任务管理-运输任务订单
     * 
     * @param tTransportTaskOrder 任务管理-运输任务订单
     * @return 结果
     */
    @Override
    public int updateTTransportTaskOrder(TTransportTaskOrderModel tTransportTaskOrder)
    {
        tTransportTaskOrder.setUpdateTime(DateUtils.getNowDate());
        return tTransportTaskOrderMapper.updateTTransportTaskOrder(tTransportTaskOrder);
    }

    /**
     * 批量删除任务管理-运输任务订单
     * 
     * @param ids 需要删除的任务管理-运输任务订单ID
     * @return 结果
     */
    @Override
    public int deleteTTransportTaskOrderByIds(String[] ids)
    {
        return tTransportTaskOrderMapper.deleteTTransportTaskOrderByIds(ids);
    }

    /**
     * 删除任务管理-运输任务订单信息
     * 
     * @param id 任务管理-运输任务订单ID
     * @return 结果
     */
    @Override
    public int deleteTTransportTaskOrderById(String id)
    {
        return tTransportTaskOrderMapper.deleteTTransportTaskOrderById(id);
    }
}
