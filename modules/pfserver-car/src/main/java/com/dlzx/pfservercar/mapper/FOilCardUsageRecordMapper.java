package com.dlzx.pfservercar.mapper;

import java.util.List;
import com.dlzx.pfservercar.domain.FOilCardUsageRecord;

/**
 * 燃料管理-油卡使用记录Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-02
 */
public interface FOilCardUsageRecordMapper 
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
     * 删除燃料管理-油卡使用记录
     * 
     * @param id 燃料管理-油卡使用记录ID
     * @return 结果
     */
    public int deleteFOilCardUsageRecordById(String id);

    /**
     * 批量删除燃料管理-油卡使用记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFOilCardUsageRecordByIds(String[] ids);

    /**
     * 批量插入
     * */
    Integer insertList(List<FOilCardUsageRecord> fOilCardUsageRecordImportList);
}
