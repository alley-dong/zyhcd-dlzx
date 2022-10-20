package com.dlzx.pfservercar.service;

import java.util.List;
import com.dlzx.pfservercar.domain.RRepairItemDate;
import com.dlzx.pfservercar.domain.SysExamineTask;

/**
 * 维修保养-维修保养单-维修保养项数据Service接口
 * 
 * @author yangluhe
 * @date 2020-12-07
 */
public interface IRRepairItemDateService 
{
    /**
     * 查询维修保养-维修保养单-维修保养项数据
     * 
     * @param id 维修保养-维修保养单-维修保养项数据ID
     * @return 维修保养-维修保养单-维修保养项数据
     */
    public List<RRepairItemDate> selectRRepairItemDateById(String id);

    /**
     * 查询维修保养-维修保养单-维修保养项数据列表
     * 
     * @param rRepairItemDate 维修保养-维修保养单-维修保养项数据
     * @return 维修保养-维修保养单-维修保养项数据集合
     */
    public List<RRepairItemDate> selectRRepairItemDateList(RRepairItemDate rRepairItemDate);

    /**
     * 新增维修保养-维修保养单-维修保养项数据
     * 
     * @param rRepairItemDate 维修保养-维修保养单-维修保养项数据
     * @return 结果
     */
    public int insertRRepairItemDate(RRepairItemDate rRepairItemDate);

    /**
     * 修改维修保养-维修保养单-维修保养项数据
     * 
     * @param rRepairItemDate 维修保养-维修保养单-维修保养项数据
     * @return 结果
     */
    public int updateRRepairItemDate(RRepairItemDate rRepairItemDate);

    /**
     * 批量删除维修保养-维修保养单-维修保养项数据
     * 
     * @param ids 需要删除的维修保养-维修保养单-维修保养项数据ID
     * @return 结果
     */
    public int deleteRRepairItemDateByIds(String[] ids);

    /**
     * 删除维修保养-维修保养单-维修保养项数据信息
     * 
     * @param id 维修保养-维修保养单-维修保养项数据ID
     * @return 结果
     */
    public int deleteRRepairItemDateById(String id);

    /**
     * 批量新增维修保养-维修保养单-维修保养项数据
     */
    public int insertRRepairItemDateBatch(List<RRepairItemDate> item);

    /**
     * 回显数据
     */
    public List<RRepairItemDate> selectRRepairItemDateByRepairId(String id);

    /**
     * 驾驶员端App 审批表显示
     */
    public SysExamineTask queryRecord(SysExamineTask sysExamineTask);

    /**
     * 批量删除文件项
     */
    public int deleteRRepairItemDateBatch(String id);

    /**
     * 维修项统计
     */
    public List<RRepairItemDate> selectRRepairItemDateStat(RRepairItemDate rRepairItemDate);
}
