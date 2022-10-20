package com.dlzx.pfserverparts.service;

import java.util.List;
import com.dlzx.pfserverparts.domain.DSalaryRecords;

/**
 * 驾驶员-薪酬结算Service接口
 * 
 * @author yangluhe
 * @date 2020-12-21
 */
public interface IDSalaryRecordsService 
{
    /**
     * 查询驾驶员-薪酬结算
     * 
     * @param id 驾驶员-薪酬结算ID
     * @return 驾驶员-薪酬结算
     */
    public DSalaryRecords selectDSalaryRecordsById(String id);

    /**
     * 查询驾驶员-薪酬结算列表
     * 
     * @param dSalaryRecords 驾驶员-薪酬结算
     * @return 驾驶员-薪酬结算集合
     */
    public List<DSalaryRecords> selectDSalaryRecordsList(DSalaryRecords dSalaryRecords);

    /**
     * 新增驾驶员-薪酬结算
     * 
     * @param dSalaryRecords 驾驶员-薪酬结算
     * @return 结果
     */
    public int insertDSalaryRecords(DSalaryRecords dSalaryRecords);

    /**
     * 修改驾驶员-薪酬结算
     * 
     * @param dSalaryRecords 驾驶员-薪酬结算
     * @return 结果
     */
    public int updateDSalaryRecords(DSalaryRecords dSalaryRecords);

    /**
     * 批量删除驾驶员-薪酬结算
     * 
     * @param ids 需要删除的驾驶员-薪酬结算ID
     * @return 结果
     */
    public int deleteDSalaryRecordsByIds(String[] ids);

    /**
     * 删除驾驶员-薪酬结算信息
     * 
     * @param id 驾驶员-薪酬结算ID
     * @return 结果
     */
    public int deleteDSalaryRecordsById(String id);
}
