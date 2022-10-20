package com.dlzx.pfservercar.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.pfservercar.domain.model.TTransportTaskInspectItemModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskInspectItemVm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.TTransportTaskInspectItemMapper;
import com.dlzx.pfservercar.domain.TTransportTaskInspectItem;
import com.dlzx.pfservercar.service.ITTransportTaskInspectItemService;

/**
 * 任务管理-运输任务点检检查项Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-18
 */
@Service
public class TTransportTaskInspectItemServiceImpl implements ITTransportTaskInspectItemService 
{
    @Autowired
    private TTransportTaskInspectItemMapper tTransportTaskInspectItemMapper;

    /**
     * 查询任务管理-运输任务点检检查项
     * 
     * @param id 任务管理-运输任务点检检查项ID
     * @return 任务管理-运输任务点检检查项
     */
    @Override
    public TTransportTaskInspectItemVm selectTTransportTaskInspectItemById(String id)
    {
        return tTransportTaskInspectItemMapper.selectTTransportTaskInspectItemById(id);
    }

    /**
     * 查询任务管理-运输任务点检检查项列表
     * 
     * @param tTransportTaskInspectItemModel 任务管理-运输任务点检检查项
     * @return 任务管理-运输任务点检检查项
     */
    @Override
    public List<TTransportTaskInspectItemVm> selectTTransportTaskInspectItemList(TTransportTaskInspectItemModel tTransportTaskInspectItemModel)
    {
        return tTransportTaskInspectItemMapper.selectTTransportTaskInspectItemList(tTransportTaskInspectItemModel);
    }

    /**
     * 新增任务管理-运输任务点检检查项
     * 
     * @param tTransportTaskInspectItemModel 任务管理-运输任务点检检查项
     * @return 结果
     */
    @Override
    public int insertTTransportTaskInspectItem(TTransportTaskInspectItemModel tTransportTaskInspectItemModel)
    {
        tTransportTaskInspectItemModel.setCreateTime(DateUtils.getNowDate());
        return tTransportTaskInspectItemMapper.insertTTransportTaskInspectItem(tTransportTaskInspectItemModel);
    }

    /**
     * 修改任务管理-运输任务点检检查项
     * 
     * @param tTransportTaskInspectItemModel 任务管理-运输任务点检检查项
     * @return 结果
     */
    @Override
    public int updateTTransportTaskInspectItem(TTransportTaskInspectItemModel tTransportTaskInspectItemModel)
    {
        tTransportTaskInspectItemModel.setUpdateTime(DateUtils.getNowDate());
        return tTransportTaskInspectItemMapper.updateTTransportTaskInspectItem(tTransportTaskInspectItemModel);
    }

    /**
     * 批量删除任务管理-运输任务点检检查项
     * 
     * @param ids 需要删除的任务管理-运输任务点检检查项ID
     * @return 结果
     */
    @Override
    public int deleteTTransportTaskInspectItemByIds(String[] ids)
    {
        return tTransportTaskInspectItemMapper.deleteTTransportTaskInspectItemByIds(ids);
    }

    /**
     * 删除任务管理-运输任务点检检查项信息
     * 
     * @param id 任务管理-运输任务点检检查项ID
     * @return 结果
     */
    @Override
    public int deleteTTransportTaskInspectItemById(String id)
    {
        return tTransportTaskInspectItemMapper.deleteTTransportTaskInspectItemById(id);
    }
}
