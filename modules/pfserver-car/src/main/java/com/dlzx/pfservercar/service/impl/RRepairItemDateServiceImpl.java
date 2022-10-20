package com.dlzx.pfservercar.service.impl;

import java.util.List;

import com.dlzx.pfservercar.client.SystemClient;
import com.dlzx.pfservercar.domain.SysExamineTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.RRepairItemDateMapper;
import com.dlzx.pfservercar.domain.RRepairItemDate;
import com.dlzx.pfservercar.service.IRRepairItemDateService;

/**
 * 维修保养-维修保养单-维修保养项数据Service业务层处理
 * 
 * @author yangluhe
 * @date 2020-12-07
 */
@Service
public class RRepairItemDateServiceImpl implements IRRepairItemDateService 
{
    @Autowired
    private RRepairItemDateMapper rRepairItemDateMapper;
    @Autowired
    private SystemClient systemClient;

    /**
     * 查询维修保养-维修保养单-维修保养项数据
     * 
     * @param id 维修保养-维修保养单-维修保养项数据ID
     * @return 维修保养-维修保养单-维修保养项数据
     */
    @Override
    public List<RRepairItemDate> selectRRepairItemDateById(String id)
    {
        return rRepairItemDateMapper.selectRRepairItemDateById(id);
    }

    /**
     * 查询维修保养-维修保养单-维修保养项数据列表
     * 
     * @param rRepairItemDate 维修保养-维修保养单-维修保养项数据
     * @return 维修保养-维修保养单-维修保养项数据
     */
    @Override
    public List<RRepairItemDate> selectRRepairItemDateList(RRepairItemDate rRepairItemDate)
    {
        return rRepairItemDateMapper.selectRRepairItemDateList(rRepairItemDate);
    }

    /**
     * 新增维修保养-维修保养单-维修保养项数据
     * 
     * @param rRepairItemDate 维修保养-维修保养单-维修保养项数据
     * @return 结果
     */
    @Override
    public int insertRRepairItemDate(RRepairItemDate rRepairItemDate)
    {
        return rRepairItemDateMapper.insertRRepairItemDate(rRepairItemDate);
    }

    /**
     * 修改维修保养-维修保养单-维修保养项数据
     * 
     * @param rRepairItemDate 维修保养-维修保养单-维修保养项数据
     * @return 结果
     */
    @Override
    public int updateRRepairItemDate(RRepairItemDate rRepairItemDate)
    {
        return rRepairItemDateMapper.updateRRepairItemDate(rRepairItemDate);
    }

    /**
     * 批量删除维修保养-维修保养单-维修保养项数据
     * 
     * @param ids 需要删除的维修保养-维修保养单-维修保养项数据ID
     * @return 结果
     */
    @Override
    public int deleteRRepairItemDateByIds(String[] ids)
    {
        return rRepairItemDateMapper.deleteRRepairItemDateByIds(ids);
    }

    /**
     * 删除维修保养-维修保养单-维修保养项数据信息
     * 
     * @param id 维修保养-维修保养单-维修保养项数据ID
     * @return 结果
     */
    @Override
    public int deleteRRepairItemDateById(String id)
    {
        return rRepairItemDateMapper.deleteRRepairItemDateById(id);
    }

    /**
     * 批量新增
     */
    @Override
    public int insertRRepairItemDateBatch(List<RRepairItemDate> item){
        return rRepairItemDateMapper.insertRRepairItemDateBatch(item);
    }

    /**
     * 回显数据
     */
    @Override
    public List<RRepairItemDate> selectRRepairItemDateByRepairId(String id){
        return rRepairItemDateMapper.selectRRepairItemDateByRepairId(id);
    }

    /**
     * 驾驶员App 端 审批列表显示
     */
    @Override
    public SysExamineTask queryRecord(SysExamineTask sysExamineTask){
        return systemClient.sysExamineTaskList(sysExamineTask);
    }

    /**
     * 批量删除
     */
    @Override
    public int deleteRRepairItemDateBatch(String id){
        return rRepairItemDateMapper.deleteRRepairItemDateBatch(id);
    }

    /**
     * 维修项统计
     */
    @Override
    public List<RRepairItemDate> selectRRepairItemDateStat(RRepairItemDate rRepairItemDate){
        return rRepairItemDateMapper.selectRRepairItemDateStat(rRepairItemDate);
    }
}
