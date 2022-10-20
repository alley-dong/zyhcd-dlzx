package com.dlzx.pfservercar.service.impl;

import java.util.List;

import com.dlzx.pfservercar.domain.model.TTransportTaskTrayModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskTrayVm;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.TTransportTaskTrayMapper;
import com.dlzx.pfservercar.domain.TTransportTaskTray;
import com.dlzx.pfservercar.service.ITTransportTaskTrayService;

/**
 * 任务管理-运输任务托盘Service业务层处理
 * 
 * @author DongCL
 * @date 2021-01-05
 */
@Service
public class TTransportTaskTrayServiceImpl implements ITTransportTaskTrayService 
{
    @Autowired
    private TTransportTaskTrayMapper tTransportTaskTrayMapper;

    /**
     * 查询任务管理-运输任务托盘
     * 
     * @param id 任务管理-运输任务托盘ID
     * @return 任务管理-运输任务托盘
     */
    @Override
    public TTransportTaskTrayVm selectTTransportTaskTrayById(String id)
    {
        return tTransportTaskTrayMapper.selectTTransportTaskTrayById(id);
    }

    /**
     * 查询任务管理-运输任务托盘列表
     * 
     * @param tTransportTaskTray 任务管理-运输任务托盘
     * @return 任务管理-运输任务托盘
     */
    @Override
    public List<TTransportTaskTrayVm> selectTTransportTaskTrayList(TTransportTaskTrayModel tTransportTaskTray)
    {
        return tTransportTaskTrayMapper.selectTTransportTaskTrayList(tTransportTaskTray);
    }

    /**
     * 新增任务管理-运输任务托盘
     * 
     * @param tTransportTaskTray 任务管理-运输任务托盘
     * @return 结果
     */
    @Override
    public int insertTTransportTaskTray(TTransportTaskTrayModel tTransportTaskTray)
    {
        tTransportTaskTray.setCreateTime(DateUtils.getNowDate());
        return tTransportTaskTrayMapper.insertTTransportTaskTray(tTransportTaskTray);
    }

    /**
     * 修改任务管理-运输任务托盘
     * 
     * @param tTransportTaskTray 任务管理-运输任务托盘
     * @return 结果
     */
    @Override
    public int updateTTransportTaskTray(TTransportTaskTrayModel tTransportTaskTray)
    {
        tTransportTaskTray.setUpdateTime(DateUtils.getNowDate());
        return tTransportTaskTrayMapper.updateTTransportTaskTray(tTransportTaskTray);
    }

    /**
     * 批量删除任务管理-运输任务托盘
     * 
     * @param ids 需要删除的任务管理-运输任务托盘ID
     * @return 结果
     */
    @Override
    public int deleteTTransportTaskTrayByIds(String[] ids)
    {
        return tTransportTaskTrayMapper.deleteTTransportTaskTrayByIds(ids);
    }

    /**
     * 删除任务管理-运输任务托盘信息
     * 
     * @param id 任务管理-运输任务托盘ID
     * @return 结果
     */
    @Override
    public int deleteTTransportTaskTrayById(String id)
    {
        return tTransportTaskTrayMapper.deleteTTransportTaskTrayById(id);
    }
}
