package com.dlzx.pfserverperson.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.dlzx.pfserverperson.domain.DPerformance;
import com.dlzx.pfserverperson.domain.DPerformanceRecord;
import com.dlzx.pfserverperson.domain.OTMTTransportTaskModel;

/**
 * 驾驶员-长途绩效记录Service接口
 * 
 * @author dlzx
 * @date 2020-12-21
 */
public interface IDPerformanceRecordService 
{
    /**
     * 查询驾驶员-长途绩效记录
     * 
     * @param id 驾驶员-长途绩效记录ID
     * @return 驾驶员-长途绩效记录
     */
    public DPerformanceRecord selectDPerformanceRecordById(String id);

    /**
     * 查询驾驶员-长途绩效记录列表
     * 
     * @param dPerformanceRecord 驾驶员-长途绩效记录
     * @return 驾驶员-长途绩效记录集合
     */
    public List<DPerformanceRecord> selectDPerformanceRecordList(DPerformanceRecord dPerformanceRecord);

    /**
     * 新增驾驶员-长途绩效记录
     * 
     * @param dPerformanceRecord 驾驶员-长途绩效记录
     * @return 结果
     */
    public int insertDPerformanceRecord(DPerformanceRecord dPerformanceRecord);

    /**
     * 修改驾驶员-长途绩效记录
     * 
     * @param dPerformanceRecord 驾驶员-长途绩效记录
     * @return 结果
     */
    public int updateDPerformanceRecord(DPerformanceRecord dPerformanceRecord);

    /**
     * 批量删除驾驶员-长途绩效记录
     * 
     * @param ids 需要删除的驾驶员-长途绩效记录ID
     * @return 结果
     */
    public int deleteDPerformanceRecordByIds(String[] ids);

    /**
     * 删除驾驶员-长途绩效记录信息
     * 
     * @param id 驾驶员-长途绩效记录ID
     * @return 结果
     */
    public int deleteDPerformanceRecordById(String id);

    /**
     * 统计所有长途司机的绩效工资
     * @param dPerformanceRecord
     * @return
     */
    public List<DPerformanceRecord> selectDPerformanceRecordSalaryList(DPerformanceRecord dPerformanceRecord);

    /**
     * 通过任务id计算金额总和
     * @param id
     * @return
     */
    public BigDecimal selectSumAmount(String id);

    /**
     * @describe car服务调用 - 更新长途绩效的开始/结束时间
     * @author DongCL
     * @date 2021-01-08 10:25
     * @param otmtTransportTaskModel
     * @return AjaxResult
     */
    int updateTaskTime(OTMTTransportTaskModel otmtTransportTaskModel);


    /**
     * @describe car服务调用 - 根据任务ID获取长途绩效记录
     * @author DongCL
     * @date 2021-01-08 10:25
     * @param taskId 任务ID
     * @return AjaxResult
     */
    DPerformanceRecord queryPerformanceById(String taskId);
}
