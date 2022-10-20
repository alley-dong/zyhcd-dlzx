package com.dlzx.pfserverperson.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DDriverEducationRecordMapper;
import com.dlzx.pfserverperson.domain.DDriverEducationRecord;
import com.dlzx.pfserverperson.service.IDDriverEducationRecordService;

/**
 * 驾驶员-教育经历Service业务层处理
 * 
 * @author yangluhe
 * @date 2020-11-19
 */
@Service
public class DDriverEducationRecordServiceImpl implements IDDriverEducationRecordService 
{
    @Autowired
    private DDriverEducationRecordMapper dDriverEducationRecordMapper;

    /**
     * 查询驾驶员-教育经历
     * 
     * @param id 驾驶员-教育经历ID
     * @return 驾驶员-教育经历
     */
    @Override
    public DDriverEducationRecord selectDDriverEducationRecordById(String id)
    {
        return dDriverEducationRecordMapper.selectDDriverEducationRecordById(id);
    }

    /**
     * 查询驾驶员-教育经历列表
     * 
     * @param dDriverEducationRecord 驾驶员-教育经历
     * @return 驾驶员-教育经历
     */
    @Override
    public List<DDriverEducationRecord> selectDDriverEducationRecordList(DDriverEducationRecord dDriverEducationRecord)
    {
        return dDriverEducationRecordMapper.selectDDriverEducationRecordList(dDriverEducationRecord);
    }

    /**
     * 新增驾驶员-教育经历
     * 
     * @param dDriverEducationRecord 驾驶员-教育经历
     * @return 结果
     */
    @Override
    public int insertDDriverEducationRecord(DDriverEducationRecord dDriverEducationRecord)
    {
        dDriverEducationRecord.setCreateTime(DateUtils.getNowDate());
        return dDriverEducationRecordMapper.insertDDriverEducationRecord(dDriverEducationRecord);
    }

    /**
     * 修改驾驶员-教育经历
     * 
     * @param dDriverEducationRecord 驾驶员-教育经历
     * @return 结果
     */
    @Override
    public int updateDDriverEducationRecord(DDriverEducationRecord dDriverEducationRecord)
    {
        dDriverEducationRecord.setUpdateTime(DateUtils.getNowDate());
        return dDriverEducationRecordMapper.updateDDriverEducationRecord(dDriverEducationRecord);
    }

    /**
     * 批量删除驾驶员-教育经历
     * 
     * @param ids 需要删除的驾驶员-教育经历ID
     * @return 结果
     */
    @Override
    public int deleteDDriverEducationRecordByIds(String[] ids)
    {
        return dDriverEducationRecordMapper.deleteDDriverEducationRecordByIds(ids);
    }

    /**
     * 删除驾驶员-教育经历信息
     * 
     * @param id 驾驶员-教育经历ID
     * @return 结果
     */
    @Override
    public int deleteDDriverEducationRecordById(String id)
    {
        return dDriverEducationRecordMapper.deleteDDriverEducationRecordById(id);
    }

    /**
     * 驾驶员教育经历
     */
    @Override
    public List<DDriverEducationRecord> selectDDriverEducationRecordByDriverId(String id){
        return dDriverEducationRecordMapper.selectDDriverEducationRecordByDriverId(id);
    }
}
