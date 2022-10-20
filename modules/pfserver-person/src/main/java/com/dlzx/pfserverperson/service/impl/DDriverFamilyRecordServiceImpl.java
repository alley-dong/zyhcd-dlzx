package com.dlzx.pfserverperson.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DDriverFamilyRecordMapper;
import com.dlzx.pfserverperson.domain.DDriverFamilyRecord;
import com.dlzx.pfserverperson.service.IDDriverFamilyRecordService;

/**
 * 驾驶员-家庭状况经历Service业务层处理
 * 
 * @author yangluhe
 * @date 2020-11-19
 */
@Service
public class DDriverFamilyRecordServiceImpl implements IDDriverFamilyRecordService 
{
    @Autowired
    private DDriverFamilyRecordMapper dDriverFamilyRecordMapper;

    /**
     * 查询驾驶员-家庭状况经历
     * 
     * @param id 驾驶员-家庭状况经历ID
     * @return 驾驶员-家庭状况经历
     */
    @Override
    public DDriverFamilyRecord selectDDriverFamilyRecordById(String id)
    {
        return dDriverFamilyRecordMapper.selectDDriverFamilyRecordById(id);
    }

    /**
     * 查询驾驶员-家庭状况经历列表
     * 
     * @param dDriverFamilyRecord 驾驶员-家庭状况经历
     * @return 驾驶员-家庭状况经历
     */
    @Override
    public List<DDriverFamilyRecord> selectDDriverFamilyRecordList(DDriverFamilyRecord dDriverFamilyRecord)
    {
        return dDriverFamilyRecordMapper.selectDDriverFamilyRecordList(dDriverFamilyRecord);
    }

    /**
     * 新增驾驶员-家庭状况经历
     * 
     * @param dDriverFamilyRecord 驾驶员-家庭状况经历
     * @return 结果
     */
    @Override
    public int insertDDriverFamilyRecord(DDriverFamilyRecord dDriverFamilyRecord)
    {
        dDriverFamilyRecord.setCreateTime(DateUtils.getNowDate());
        return dDriverFamilyRecordMapper.insertDDriverFamilyRecord(dDriverFamilyRecord);
    }

    /**
     * 修改驾驶员-家庭状况经历
     * 
     * @param dDriverFamilyRecord 驾驶员-家庭状况经历
     * @return 结果
     */
    @Override
    public int updateDDriverFamilyRecord(DDriverFamilyRecord dDriverFamilyRecord)
    {
        dDriverFamilyRecord.setUpdateTime(DateUtils.getNowDate());
        return dDriverFamilyRecordMapper.updateDDriverFamilyRecord(dDriverFamilyRecord);
    }

    /**
     * 批量删除驾驶员-家庭状况经历
     * 
     * @param ids 需要删除的驾驶员-家庭状况经历ID
     * @return 结果
     */
    @Override
    public int deleteDDriverFamilyRecordByIds(String[] ids)
    {
        return dDriverFamilyRecordMapper.deleteDDriverFamilyRecordByIds(ids);
    }

    /**
     * 删除驾驶员-家庭状况经历信息
     * 
     * @param id 驾驶员-家庭状况经历ID
     * @return 结果
     */
    @Override
    public int deleteDDriverFamilyRecordById(String id)
    {
        return dDriverFamilyRecordMapper.deleteDDriverFamilyRecordById(id);
    }

    /**
     * 根据司机id查找家庭列表
     */
    @Override
    public List<DDriverFamilyRecord> selectDDriverFamilyRecordByDriverId(String id){
        return dDriverFamilyRecordMapper.selectDDriverFamilyRecordByDriverId(id);
    }
}
