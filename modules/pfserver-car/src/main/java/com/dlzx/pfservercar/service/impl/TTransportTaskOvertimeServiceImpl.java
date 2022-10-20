package com.dlzx.pfservercar.service.impl;

import java.util.List;

import com.dlzx.pfservercar.domain.model.TTransportTaskOvertimeModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskOvertimeVm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.TTransportTaskOvertimeMapper;
import com.dlzx.pfservercar.domain.TTransportTaskOvertime;
import com.dlzx.pfservercar.service.ITTransportTaskOvertimeService;

/**
 *  任务管理-运输任务加班Service业务层处理
 * 
 * @author dcl
 * @date 2020-12-18
 */
@Service
public class TTransportTaskOvertimeServiceImpl implements ITTransportTaskOvertimeService 
{
    @Autowired
    private TTransportTaskOvertimeMapper tTransportTaskOvertimeMapper;

    /**
     * 查询 任务管理-运输任务加班
     * 
     * @param id  任务管理-运输任务加班ID
     * @return  任务管理-运输任务加班
     */
    @Override
    public TTransportTaskOvertimeVm selectTTransportTaskOvertimeById(String id)
    {
        return tTransportTaskOvertimeMapper.selectTTransportTaskOvertimeById(id);
    }

    /**
     * 查询 任务管理-运输任务加班列表
     * 
     * @param tTransportTaskOvertimeModel  任务管理-运输任务加班
     * @return  任务管理-运输任务加班
     */
    @Override
    public List<TTransportTaskOvertimeVm> selectTTransportTaskOvertimeList(TTransportTaskOvertimeModel tTransportTaskOvertimeModel)
    {
        return tTransportTaskOvertimeMapper.selectTTransportTaskOvertimeList(tTransportTaskOvertimeModel);
    }

    /**
     * 新增 任务管理-运输任务加班
     * 
     * @param tTransportTaskOvertimeModel  任务管理-运输任务加班
     * @return 结果
     */
    @Override
    public int insertTTransportTaskOvertime(TTransportTaskOvertimeModel tTransportTaskOvertimeModel)
    {
        return tTransportTaskOvertimeMapper.insertTTransportTaskOvertime(tTransportTaskOvertimeModel);
    }

    /**
     * 修改 任务管理-运输任务加班
     * 
     * @param tTransportTaskOvertimeModel  任务管理-运输任务加班
     * @return 结果
     */
    @Override
    public int updateTTransportTaskOvertime(TTransportTaskOvertimeModel tTransportTaskOvertimeModel)
    {
        return tTransportTaskOvertimeMapper.updateTTransportTaskOvertime(tTransportTaskOvertimeModel);
    }

    /**
     * 批量删除 任务管理-运输任务加班
     * 
     * @param ids 需要删除的 任务管理-运输任务加班ID
     * @return 结果
     */
    @Override
    public int deleteTTransportTaskOvertimeByIds(String[] ids)
    {
        return tTransportTaskOvertimeMapper.deleteTTransportTaskOvertimeByIds(ids);
    }

    /**
     * 删除 任务管理-运输任务加班信息
     * 
     * @param id  任务管理-运输任务加班ID
     * @return 结果
     */
    @Override
    public int deleteTTransportTaskOvertimeById(String id)
    {
        return tTransportTaskOvertimeMapper.deleteTTransportTaskOvertimeById(id);
    }

    /**
     * App 运输任务-运输任务加班记录列表
     */
    @Override
    public List<TTransportTaskOvertimeVm> queryTTransportTaskOvertimeList(TTransportTaskOvertimeModel tTransportTaskOvertimeModel) {
        return tTransportTaskOvertimeMapper.queryTTransportTaskOvertimeList(tTransportTaskOvertimeModel);
    }

}
