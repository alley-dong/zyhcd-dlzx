package com.dlzx.pfserverperson.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DDriverAccidentRecordMapper;
import com.dlzx.pfserverperson.domain.DDriverAccidentRecord;
import com.dlzx.pfserverperson.service.IDDriverAccidentRecordService;

/**
 * 驾驶员-交通事故经历Service业务层处理
 * 
 * @author yangluhe
 * @date 2020-11-19
 */
@Service
public class DDriverAccidentRecordServiceImpl implements IDDriverAccidentRecordService 
{
    @Autowired
    private DDriverAccidentRecordMapper dDriverAccidentRecordMapper;

    /**
     * 查询驾驶员-交通事故经历
     * 
     * @param id 驾驶员-交通事故经历ID
     * @return 驾驶员-交通事故经历
     */
    @Override
    public DDriverAccidentRecord selectDDriverAccidentRecordById(String id)
    {
        return dDriverAccidentRecordMapper.selectDDriverAccidentRecordById(id);
    }

    /**
     * 查询驾驶员-交通事故经历列表
     * 
     * @param dDriverAccidentRecord 驾驶员-交通事故经历
     * @return 驾驶员-交通事故经历
     */
    @Override
    public List<DDriverAccidentRecord> selectDDriverAccidentRecordList(DDriverAccidentRecord dDriverAccidentRecord)
    {
        return dDriverAccidentRecordMapper.selectDDriverAccidentRecordList(dDriverAccidentRecord);
    }

    /**
     * 新增驾驶员-交通事故经历
     * 
     * @param dDriverAccidentRecord 驾驶员-交通事故经历
     * @return 结果
     */
    @Override
    public int insertDDriverAccidentRecord(DDriverAccidentRecord dDriverAccidentRecord)
    {
        dDriverAccidentRecord.setCreateTime(DateUtils.getNowDate());
        return dDriverAccidentRecordMapper.insertDDriverAccidentRecord(dDriverAccidentRecord);
    }

    /**
     * 修改驾驶员-交通事故经历
     * 
     * @param dDriverAccidentRecord 驾驶员-交通事故经历
     * @return 结果
     */
    @Override
    public int updateDDriverAccidentRecord(DDriverAccidentRecord dDriverAccidentRecord)
    {
        dDriverAccidentRecord.setUpdateTime(DateUtils.getNowDate());
        return dDriverAccidentRecordMapper.updateDDriverAccidentRecord(dDriverAccidentRecord);
    }

    /**
     * 批量删除驾驶员-交通事故经历
     * 
     * @param ids 需要删除的驾驶员-交通事故经历ID
     * @return 结果
     */
    @Override
    public int deleteDDriverAccidentRecordByIds(String[] ids)
    {
        return dDriverAccidentRecordMapper.deleteDDriverAccidentRecordByIds(ids);
    }

    /**
     * 删除驾驶员-交通事故经历信息
     * 
     * @param id 驾驶员-交通事故经历ID
     * @return 结果
     */
    @Override
    public int deleteDDriverAccidentRecordById(String id)
    {
        return dDriverAccidentRecordMapper.deleteDDriverAccidentRecordById(id);
    }

    /**
     * 根据司机id查询事故列表
     */
    @Override
    public List<DDriverAccidentRecord> selectDDriverAccidentRecordByDriverId(String id){
        return dDriverAccidentRecordMapper.selectDDriverAccidentRecordByDriverId(id);
    }
}
