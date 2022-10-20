package com.dlzx.pfservercar.service;

import java.util.List;
import java.util.Map;

import com.dlzx.pfservercar.domain.FOilCardUsageRecord;

/**
 * 燃料管理-油卡使用记录Service接口
 * 
 * @author dlzx
 * @date 2020-12-02
 */
public interface IFOilCardUsageRecordService 
{
    /**
     * 查询燃料管理-油卡使用记录
     * 
     * @param id 燃料管理-油卡使用记录ID
     * @return 燃料管理-油卡使用记录
     */
    public FOilCardUsageRecord selectFOilCardUsageRecordById(String id);

    /**
     * 查询燃料管理-油卡使用记录列表
     * 
     * @param fOilCardUsageRecord 燃料管理-油卡使用记录
     * @return 燃料管理-油卡使用记录集合
     */
    public List<FOilCardUsageRecord> selectFOilCardUsageRecordList(FOilCardUsageRecord fOilCardUsageRecord);

    public List<FOilCardUsageRecord> selectFOilCardUsageRecordListNum(FOilCardUsageRecord fOilCardUsageRecord);
    /**
     * 查询燃料管理-油料使用异常记录列表
     *
     * @param fOilCardUsageRecord 燃料管理-油料使用异常记录列表
     * @return 燃料管理-油料使用异常记录列表
     */
    public List<FOilCardUsageRecord> selectFOilCardUsageAbnormalRecordList(FOilCardUsageRecord fOilCardUsageRecord);

    /**
     * 新增燃料管理-油卡使用记录
     * 
     * @param fOilCardUsageRecord 燃料管理-油卡使用记录
     * @return 结果
     */
    public int insertFOilCardUsageRecord(FOilCardUsageRecord fOilCardUsageRecord);

    /**
     * 修改燃料管理-油卡使用记录
     * 
     * @param fOilCardUsageRecord 燃料管理-油卡使用记录
     * @return 结果
     */
    public int updateFOilCardUsageRecord(FOilCardUsageRecord fOilCardUsageRecord);

    /**
     * 批量删除燃料管理-油卡使用记录
     * 
     * @param ids 需要删除的燃料管理-油卡使用记录ID
     * @return 结果
     */
    public int deleteFOilCardUsageRecordByIds(String[] ids);

    /**
     * 删除燃料管理-油卡使用记录信息
     * 
     * @param id 燃料管理-油卡使用记录ID
     * @return 结果
     */
    public int deleteFOilCardUsageRecordById(String id);

    /**
     * 油料使用记录-油料使用记录导入
     *
     * @param fOilCardUsageRecords 油料使用记录列表
     * @param routeId 线路ID
     * @return 结果
     */
    Map<String, Integer> importFOilCardUsageRecord(List<FOilCardUsageRecord> fOilCardUsageRecords,String routeId);
}
