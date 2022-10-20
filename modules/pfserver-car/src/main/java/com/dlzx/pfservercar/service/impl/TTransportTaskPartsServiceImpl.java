package com.dlzx.pfservercar.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.pfservercar.domain.model.TTransportTaskPartsModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskPartsVm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.TTransportTaskPartsMapper;
import com.dlzx.pfservercar.domain.TTransportTaskParts;
import com.dlzx.pfservercar.service.ITTransportTaskPartsService;

/**
 * 任务管理-运输部件Service业务层处理
 * 
 * @author dcl
 * @date 2020-12-18
 */
@Service
public class TTransportTaskPartsServiceImpl implements ITTransportTaskPartsService 
{
    @Autowired
    private TTransportTaskPartsMapper tTransportTaskPartsMapper;

    /**
     * 查询任务管理-运输部件
     * 
     * @param id 任务管理-运输部件ID
     * @return 任务管理-运输部件
     */
    @Override
    public TTransportTaskPartsVm selectTTransportTaskPartsById(String id)
    {
        return tTransportTaskPartsMapper.selectTTransportTaskPartsById(id);
    }

    /**
     * 查询任务管理-运输部件列表
     * 
     * @param tTransportTaskPartsModel 任务管理-运输部件
     * @return 任务管理-运输部件
     */
    @Override
    public List<TTransportTaskPartsVm> selectTTransportTaskPartsList(TTransportTaskPartsModel tTransportTaskPartsModel)
    {
        return tTransportTaskPartsMapper.selectTTransportTaskPartsList(tTransportTaskPartsModel);
    }

    /**
     * 新增任务管理-运输部件
     * 
     * @param tTransportTaskPartsModel 任务管理-运输部件
     * @return 结果
     */
    @Override
    public int insertTTransportTaskParts(TTransportTaskPartsModel tTransportTaskPartsModel)
    {
        tTransportTaskPartsModel.setCreateTime(DateUtils.getNowDate());
        return tTransportTaskPartsMapper.insertTTransportTaskParts(tTransportTaskPartsModel);
    }

    /**
     * 修改任务管理-运输部件
     * 
     * @param tTransportTaskPartsModel 任务管理-运输部件
     * @return 结果
     */
    @Override
    public int updateTTransportTaskParts(TTransportTaskPartsModel tTransportTaskPartsModel)
    {
        tTransportTaskPartsModel.setUpdateTime(DateUtils.getNowDate());
        return tTransportTaskPartsMapper.updateTTransportTaskParts(tTransportTaskPartsModel);
    }

    /**
     * 批量删除任务管理-运输部件
     * 
     * @param ids 需要删除的任务管理-运输部件ID
     * @return 结果
     */
    @Override
    public int deleteTTransportTaskPartsByIds(String[] ids)
    {
        return tTransportTaskPartsMapper.deleteTTransportTaskPartsByIds(ids);
    }

    /**
     * 删除任务管理-运输部件信息
     * 
     * @param id 任务管理-运输部件ID
     * @return 结果
     */
    @Override
    public int deleteTTransportTaskPartsById(String id)
    {
        return tTransportTaskPartsMapper.deleteTTransportTaskPartsById(id);
    }
}
