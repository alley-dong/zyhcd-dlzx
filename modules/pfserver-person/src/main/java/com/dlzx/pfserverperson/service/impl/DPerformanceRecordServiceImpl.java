package com.dlzx.pfserverperson.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.pfserverperson.domain.DPerformance;
import com.dlzx.pfserverperson.domain.OTMTTransportTaskModel;
import com.dlzx.pfserverperson.mapper.DPerformanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DPerformanceRecordMapper;
import com.dlzx.pfserverperson.domain.DPerformanceRecord;
import com.dlzx.pfserverperson.service.IDPerformanceRecordService;

/**
 * 驾驶员-长途绩效记录Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-21
 */
@Service
public class DPerformanceRecordServiceImpl implements IDPerformanceRecordService 
{
    @Autowired
    private DPerformanceRecordMapper dPerformanceRecordMapper;

    @Autowired
    private DPerformanceMapper dPerformanceMapper;

    /**
     * 查询驾驶员-长途绩效记录
     * 
     * @param id 驾驶员-长途绩效记录ID
     * @return 驾驶员-长途绩效记录
     */
    @Override
    public DPerformanceRecord selectDPerformanceRecordById(String id)
    {
        return dPerformanceRecordMapper.selectDPerformanceRecordById(id);
    }

    /**
     * 查询驾驶员-长途绩效记录列表
     * 
     * @param dPerformanceRecord 驾驶员-长途绩效记录
     * @return 驾驶员-长途绩效记录
     */
    @Override
    public List<DPerformanceRecord> selectDPerformanceRecordList(DPerformanceRecord dPerformanceRecord)
    {
        return dPerformanceRecordMapper.selectDPerformanceRecordList(dPerformanceRecord);
    }

    /**
     * 新增驾驶员-长途绩效记录
     * 
     * @param dPerformanceRecord 驾驶员-长途绩效记录
     * @return 结果
     */
    @Override
    public int insertDPerformanceRecord(DPerformanceRecord dPerformanceRecord)
    {
        dPerformanceRecord.setCreateTime(DateUtils.getNowDate());
        return dPerformanceRecordMapper.insertDPerformanceRecord(dPerformanceRecord);
    }

    /**
     * 修改驾驶员-长途绩效记录
     * 
     * @param dPerformanceRecord 驾驶员-长途绩效记录
     * @return 结果
     */
    @Override
    public int updateDPerformanceRecord(DPerformanceRecord dPerformanceRecord)
    {
        dPerformanceRecord.setUpdateTime(DateUtils.getNowDate());
        return dPerformanceRecordMapper.updateDPerformanceRecord(dPerformanceRecord);
    }

    /**
     * 批量删除驾驶员-长途绩效记录
     * 
     * @param ids 需要删除的驾驶员-长途绩效记录ID
     * @return 结果
     */
    @Override
    public int deleteDPerformanceRecordByIds(String[] ids)
    {
        return dPerformanceRecordMapper.deleteDPerformanceRecordByIds(ids);
    }

    /**
     * 删除驾驶员-长途绩效记录信息
     * 
     * @param id 驾驶员-长途绩效记录ID
     * @return 结果
     */
    @Override
    public int deleteDPerformanceRecordById(String id)
    {
        return dPerformanceRecordMapper.deleteDPerformanceRecordById(id);
    }

    /**
     * 统计所有长途司机的绩效工资
     * @param dPerformanceRecord
     * @return
     */
    @Override
    public List<DPerformanceRecord> selectDPerformanceRecordSalaryList(DPerformanceRecord dPerformanceRecord){
        return dPerformanceRecordMapper.selectDPerformanceRecordSalaryList(dPerformanceRecord);
    }

    /**
     * 通过任务id计算绩效金额总和
     * @param id
     * @return
     */
    @Override
    public BigDecimal selectSumAmount(String id){
        return dPerformanceRecordMapper.selectSumAmount(id);
    }

    /**
     * @describe car服务调用 - 更新长途绩效的开始/结束时间
     * @author DongCL
     * @date 2021-01-08 10:25
     * @param otmtTransportTaskModel
     * @return AjaxResult
     */
    @Override
    public int updateTaskTime(OTMTTransportTaskModel otmtTransportTaskModel) {

        // 如果更新结束时间
        if (otmtTransportTaskModel.getACTUAL_ARRIVAL()!= null){

            // 根据任务ID 获取长途绩效记录
            DPerformanceRecord dPerformanceRecord = dPerformanceRecordMapper.queryPerformanceById(otmtTransportTaskModel.getSHIPMENT_GID());

            if (dPerformanceRecord != null) {
                // 绩效记录
                DPerformance dPerformance = dPerformanceMapper.selectDPerformanceById(dPerformanceRecord.getPerformanceId());
                if (dPerformance != null){
                    // 计算任务累计天数
                    if (dPerformance.getChargeType()==2){
                        // 结束时间 减去 开始时间
                        long sub = Math.abs(otmtTransportTaskModel.getACTUAL_ARRIVAL().getTime() - dPerformanceRecord.getTaskStartTime().getTime());
                        long day = sub/1000/60/60/24;
                        dPerformanceRecord.setAmount(dPerformance.getAmount().multiply(BigDecimal.valueOf(day)));
                    }else {
                        dPerformanceRecord.setAmount(dPerformance.getAmount());
                    }
                    // 更新绩效金额
                    dPerformanceRecordMapper.updateDPerformanceRecord(dPerformanceRecord);
                }
            }
        }
        // 更新任务开始/结束时间
        return dPerformanceRecordMapper.updateDPerformanceTaskTimeByTaskId(otmtTransportTaskModel);
    }

    /**
     * @describe car服务调用 - 根据任务ID获取长途绩效记录
     * @author DongCL
     * @date 2021-01-08 10:25
     * @param taskId 任务ID
     * @return AjaxResult
     */
    @Override
    public DPerformanceRecord queryPerformanceById(String taskId) {
        return dPerformanceRecordMapper.queryPerformanceById(taskId);
    }
}
