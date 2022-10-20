package com.dlzx.pfserverparts.mapper;

import java.util.List;
import com.dlzx.pfserverparts.domain.DSalaryDetailedRecords;

/**
 * 驾驶员-薪酬结算-详细记录Mapper接口
 * 
 * @author yangluhe
 * @date 2020-12-21
 */
public interface DSalaryDetailedRecordsMapper 
{
    /**
     * 查询驾驶员-薪酬结算-详细记录
     * 
     * @param id 驾驶员-薪酬结算-详细记录ID
     * @return 驾驶员-薪酬结算-详细记录
     */
    public DSalaryDetailedRecords selectDSalaryDetailedRecordsById(String id);

    /**
     * 查询驾驶员-薪酬结算-详细记录列表
     * 
     * @param dSalaryDetailedRecords 驾驶员-薪酬结算-详细记录
     * @return 驾驶员-薪酬结算-详细记录集合
     */
    public List<DSalaryDetailedRecords> selectDSalaryDetailedRecordsList(DSalaryDetailedRecords dSalaryDetailedRecords);

    /**
     * 新增驾驶员-薪酬结算-详细记录
     * 
     * @param dSalaryDetailedRecords 驾驶员-薪酬结算-详细记录
     * @return 结果
     */
    public int insertDSalaryDetailedRecords(DSalaryDetailedRecords dSalaryDetailedRecords);

    /**
     * 修改驾驶员-薪酬结算-详细记录
     * 
     * @param dSalaryDetailedRecords 驾驶员-薪酬结算-详细记录
     * @return 结果
     */
    public int updateDSalaryDetailedRecords(DSalaryDetailedRecords dSalaryDetailedRecords);

    /**
     * 删除驾驶员-薪酬结算-详细记录
     * 
     * @param id 驾驶员-薪酬结算-详细记录ID
     * @return 结果
     */
    public int deleteDSalaryDetailedRecordsById(String id);

    /**
     * 批量删除驾驶员-薪酬结算-详细记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDSalaryDetailedRecordsByIds(String[] ids);

    /**
     * 批量新增
     */
    public int insertBatch(List<DSalaryDetailedRecords> item);

    /**
     * 查看工资明细
     * @param id
     * @return
     */
    public List<DSalaryDetailedRecords> selectDSalaryDetailForm(String id);

    /**
     * APPlist
     */
    public List<DSalaryDetailedRecords> selectDSalaryDetailApp(DSalaryDetailedRecords dSalaryDetailedRecords);

    public int updateDetailRecordsBatch(String id);
}
