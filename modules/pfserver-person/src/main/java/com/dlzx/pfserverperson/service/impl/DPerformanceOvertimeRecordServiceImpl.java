package com.dlzx.pfserverperson.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DPerformanceOvertimeRecordMapper;
import com.dlzx.pfserverperson.domain.DPerformanceOvertimeRecord;
import com.dlzx.pfserverperson.service.IDPerformanceOvertimeRecordService;

/**
 * 驾驶员-加班绩效记录Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-22
 */
@Service
public class DPerformanceOvertimeRecordServiceImpl implements IDPerformanceOvertimeRecordService 
{
    @Autowired
    private DPerformanceOvertimeRecordMapper dPerformanceOvertimeRecordMapper;

    /**
     * 查询驾驶员-加班绩效记录
     * 
     * @param id 驾驶员-加班绩效记录ID
     * @return 驾驶员-加班绩效记录
     */
    @Override
    public DPerformanceOvertimeRecord selectDPerformanceOvertimeRecordById(String id)
    {
        return dPerformanceOvertimeRecordMapper.selectDPerformanceOvertimeRecordById(id);
    }

    /**
     * 查询驾驶员-加班绩效记录列表
     * 
     * @param dPerformanceOvertimeRecord 驾驶员-加班绩效记录
     * @return 驾驶员-加班绩效记录
     */
    @Override
    public List<DPerformanceOvertimeRecord> selectDPerformanceOvertimeRecordList(DPerformanceOvertimeRecord dPerformanceOvertimeRecord)
    {
        return dPerformanceOvertimeRecordMapper.selectDPerformanceOvertimeRecordList(dPerformanceOvertimeRecord);
    }

    /**
     * 新增驾驶员-加班绩效记录
     * 
     * @param dPerformanceOvertimeRecord 驾驶员-加班绩效记录
     * @return 结果
     */
    @Override
    public int insertDPerformanceOvertimeRecord(DPerformanceOvertimeRecord dPerformanceOvertimeRecord)
    {
        dPerformanceOvertimeRecord.setCreateTime(DateUtils.getNowDate());
        return dPerformanceOvertimeRecordMapper.insertDPerformanceOvertimeRecord(dPerformanceOvertimeRecord);
    }

    /**
     * 修改驾驶员-加班绩效记录
     * 
     * @param dPerformanceOvertimeRecord 驾驶员-加班绩效记录
     * @return 结果
     */
    @Override
    public int updateDPerformanceOvertimeRecord(DPerformanceOvertimeRecord dPerformanceOvertimeRecord)
    {
        dPerformanceOvertimeRecord.setUpdateTime(DateUtils.getNowDate());
        return dPerformanceOvertimeRecordMapper.updateDPerformanceOvertimeRecord(dPerformanceOvertimeRecord);
    }

    /**
     * 批量删除驾驶员-加班绩效记录
     * 
     * @param ids 需要删除的驾驶员-加班绩效记录ID
     * @return 结果
     */
    @Override
    public int deleteDPerformanceOvertimeRecordByIds(String[] ids)
    {
        return dPerformanceOvertimeRecordMapper.deleteDPerformanceOvertimeRecordByIds(ids);
    }

    /**
     * 删除驾驶员-加班绩效记录信息
     * 
     * @param id 驾驶员-加班绩效记录ID
     * @return 结果
     */
    @Override
    public int deleteDPerformanceOvertimeRecordById(String id)
    {
        return dPerformanceOvertimeRecordMapper.deleteDPerformanceOvertimeRecordById(id);
    }

    /**
     * 所有司机加班费用
     * @param dPerformanceOvertimeRecord
     * @return
     */
    @Override
    public List<DPerformanceOvertimeRecord> selectDPerOverTimeList(DPerformanceOvertimeRecord dPerformanceOvertimeRecord){
        return dPerformanceOvertimeRecordMapper.selectDPerOverTimeList(dPerformanceOvertimeRecord);
    }
}
