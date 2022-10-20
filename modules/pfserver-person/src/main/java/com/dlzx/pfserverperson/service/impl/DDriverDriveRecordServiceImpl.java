package com.dlzx.pfserverperson.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DDriverDriveRecordMapper;
import com.dlzx.pfserverperson.domain.DDriverDriveRecord;
import com.dlzx.pfserverperson.service.IDDriverDriveRecordService;

/**
 * 驾驶员-驾龄经历Service业务层处理
 * 
 * @author yangluhe
 * @date 2020-11-19
 */
@Service
public class DDriverDriveRecordServiceImpl implements IDDriverDriveRecordService 
{
    @Autowired
    private DDriverDriveRecordMapper dDriverDriveRecordMapper;

    /**
     * 查询驾驶员-驾龄经历
     * 
     * @param id 驾驶员-驾龄经历ID
     * @return 驾驶员-驾龄经历
     */
    @Override
    public DDriverDriveRecord selectDDriverDriveRecordById(String id)
    {
        return dDriverDriveRecordMapper.selectDDriverDriveRecordById(id);
    }

    /**
     * 查询驾驶员-驾龄经历列表
     * 
     * @param dDriverDriveRecord 驾驶员-驾龄经历
     * @return 驾驶员-驾龄经历
     */
    @Override
    public List<DDriverDriveRecord> selectDDriverDriveRecordList(DDriverDriveRecord dDriverDriveRecord)
    {
        return dDriverDriveRecordMapper.selectDDriverDriveRecordList(dDriverDriveRecord);
    }

    /**
     * 新增驾驶员-驾龄经历
     * 
     * @param dDriverDriveRecord 驾驶员-驾龄经历
     * @return 结果
     */
    @Override
    public int insertDDriverDriveRecord(DDriverDriveRecord dDriverDriveRecord)
    {
        dDriverDriveRecord.setCreateTime(DateUtils.getNowDate());
        return dDriverDriveRecordMapper.insertDDriverDriveRecord(dDriverDriveRecord);
    }

    /**
     * 修改驾驶员-驾龄经历
     * 
     * @param dDriverDriveRecord 驾驶员-驾龄经历
     * @return 结果
     */
    @Override
    public int updateDDriverDriveRecord(DDriverDriveRecord dDriverDriveRecord)
    {
        dDriverDriveRecord.setUpdateTime(DateUtils.getNowDate());
        return dDriverDriveRecordMapper.updateDDriverDriveRecord(dDriverDriveRecord);
    }

    /**
     * 批量删除驾驶员-驾龄经历
     * 
     * @param ids 需要删除的驾驶员-驾龄经历ID
     * @return 结果
     */
    @Override
    public int deleteDDriverDriveRecordByIds(String[] ids)
    {
        return dDriverDriveRecordMapper.deleteDDriverDriveRecordByIds(ids);
    }

    /**
     * 删除驾驶员-驾龄经历信息
     * 
     * @param id 驾驶员-驾龄经历ID
     * @return 结果
     */
    @Override
    public int deleteDDriverDriveRecordById(String id)
    {
        return dDriverDriveRecordMapper.deleteDDriverDriveRecordById(id);
    }

    /**
     * 根据司机id查列表
     */
    @Override
    public List<DDriverDriveRecord> selectDDriverDriverRecordByDriverId(String id){
        return dDriverDriveRecordMapper.selectDDriverDriverRecordByDriverId(id);
    }
}
