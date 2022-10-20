package com.dlzx.pfserverperson.service;

import java.util.List;
import com.dlzx.pfserverperson.domain.DPerformanceOvertimeRecord;

/**
 * 驾驶员-加班绩效记录Service接口
 * 
 * @author dlzx
 * @date 2020-12-22
 */
public interface IDPerformanceOvertimeRecordService 
{
    /**
     * 查询驾驶员-加班绩效记录
     * 
     * @param id 驾驶员-加班绩效记录ID
     * @return 驾驶员-加班绩效记录
     */
    public DPerformanceOvertimeRecord selectDPerformanceOvertimeRecordById(String id);

    /**
     * 查询驾驶员-加班绩效记录列表
     * 
     * @param dPerformanceOvertimeRecord 驾驶员-加班绩效记录
     * @return 驾驶员-加班绩效记录集合
     */
    public List<DPerformanceOvertimeRecord> selectDPerformanceOvertimeRecordList(DPerformanceOvertimeRecord dPerformanceOvertimeRecord);

    /**
     * 新增驾驶员-加班绩效记录
     * 
     * @param dPerformanceOvertimeRecord 驾驶员-加班绩效记录
     * @return 结果
     */
    public int insertDPerformanceOvertimeRecord(DPerformanceOvertimeRecord dPerformanceOvertimeRecord);

    /**
     * 修改驾驶员-加班绩效记录
     * 
     * @param dPerformanceOvertimeRecord 驾驶员-加班绩效记录
     * @return 结果
     */
    public int updateDPerformanceOvertimeRecord(DPerformanceOvertimeRecord dPerformanceOvertimeRecord);

    /**
     * 批量删除驾驶员-加班绩效记录
     * 
     * @param ids 需要删除的驾驶员-加班绩效记录ID
     * @return 结果
     */
    public int deleteDPerformanceOvertimeRecordByIds(String[] ids);

    /**
     * 删除驾驶员-加班绩效记录信息
     * 
     * @param id 驾驶员-加班绩效记录ID
     * @return 结果
     */
    public int deleteDPerformanceOvertimeRecordById(String id);

    /**
     * 所有司机加班费用
     * @param dPerformanceOvertimeRecord
     * @return
     */
    public List<DPerformanceOvertimeRecord> selectDPerOverTimeList(DPerformanceOvertimeRecord dPerformanceOvertimeRecord);
}
