package com.dlzx.pfserverperson.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DPerformanceShortHaulRecordMapper;
import com.dlzx.pfserverperson.domain.DPerformanceShortHaulRecord;
import com.dlzx.pfserverperson.service.IDPerformanceShortHaulRecordService;

/**
 * 驾驶员-短途绩效记录Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-21
 */
@Service
public class DPerformanceShortHaulRecordServiceImpl implements IDPerformanceShortHaulRecordService 
{
    @Autowired
    private DPerformanceShortHaulRecordMapper dPerformanceShortHaulRecordMapper;

    /**
     * 查询驾驶员-短途绩效记录
     * 
     * @param id 驾驶员-短途绩效记录ID
     * @return 驾驶员-短途绩效记录
     */
    @Override
    public DPerformanceShortHaulRecord selectDPerformanceShortHaulRecordById(String id)
    {
        return dPerformanceShortHaulRecordMapper.selectDPerformanceShortHaulRecordById(id);
    }

    /**
     * 查询驾驶员-短途绩效记录列表
     * 
     * @param dPerformanceShortHaulRecord 驾驶员-短途绩效记录
     * @return 驾驶员-短途绩效记录
     */
    @Override
    public List<DPerformanceShortHaulRecord> selectDPerformanceShortHaulRecordList(DPerformanceShortHaulRecord dPerformanceShortHaulRecord)
    {
        return dPerformanceShortHaulRecordMapper.selectDPerformanceShortHaulRecordList(dPerformanceShortHaulRecord);
    }

    /**
     * 新增驾驶员-短途绩效记录
     * 
     * @param dPerformanceShortHaulRecord 驾驶员-短途绩效记录
     * @return 结果
     */
    @Override
    public int insertDPerformanceShortHaulRecord(DPerformanceShortHaulRecord dPerformanceShortHaulRecord)
    {
        dPerformanceShortHaulRecord.setCreateTime(DateUtils.getNowDate());
        return dPerformanceShortHaulRecordMapper.insertDPerformanceShortHaulRecord(dPerformanceShortHaulRecord);
    }

//
@Override
public int insertDPerformanceShortHaulRecordList(List<DPerformanceShortHaulRecord> list)
{

    return dPerformanceShortHaulRecordMapper.insertDPerformanceShortHaulRecordList(list);
}

    /**
     * 修改驾驶员-短途绩效记录
     * 
     * @param dPerformanceShortHaulRecord 驾驶员-短途绩效记录
     * @return 结果
     */
    @Override
    public int updateDPerformanceShortHaulRecord(DPerformanceShortHaulRecord dPerformanceShortHaulRecord)
    {
        dPerformanceShortHaulRecord.setUpdateTime(DateUtils.getNowDate());
        return dPerformanceShortHaulRecordMapper.updateDPerformanceShortHaulRecord(dPerformanceShortHaulRecord);
    }

    /**
     * 批量删除驾驶员-短途绩效记录
     * 
     * @param ids 需要删除的驾驶员-短途绩效记录ID
     * @return 结果
     */
    @Override
    public int deleteDPerformanceShortHaulRecordByIds(String[] ids)
    {
        return dPerformanceShortHaulRecordMapper.deleteDPerformanceShortHaulRecordByIds(ids);
    }

    /**
     * 删除驾驶员-短途绩效记录信息
     * 
     * @param id 驾驶员-短途绩效记录ID
     * @return 结果
     */
    @Override
    public int deleteDPerformanceShortHaulRecordById(String id)
    {
        return dPerformanceShortHaulRecordMapper.deleteDPerformanceShortHaulRecordById(id);
    }

    /**
     * 统计所有短途司机的绩效工资
     * @param dPerformanceShortHaulRecord
     * @return
     */
    @Override
    public List<DPerformanceShortHaulRecord> selectDPerShortSalary(DPerformanceShortHaulRecord dPerformanceShortHaulRecord){
        return dPerformanceShortHaulRecordMapper.selectDPerShortSalary(dPerformanceShortHaulRecord);
    }
}
