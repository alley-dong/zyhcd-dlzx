package com.dlzx.pfservercar.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.CVehicleInspectRecordMapper;
import com.dlzx.pfservercar.domain.CVehicleInspectRecord;
import com.dlzx.pfservercar.service.ICVehicleInspectRecordService;

/**
 * 车辆管理-车辆年检记录Service业务层处理
 * 
 * @author yangluhe
 * @date 2020-11-27
 */
@Service
public class CVehicleInspectRecordServiceImpl implements ICVehicleInspectRecordService 
{
    @Autowired
    private CVehicleInspectRecordMapper cVehicleInspectRecordMapper;

    /**
     * 查询车辆管理-车辆年检记录
     * 
     * @param id 车辆管理-车辆年检记录ID
     * @return 车辆管理-车辆年检记录
     */
    @Override
    public CVehicleInspectRecord selectCVehicleInspectRecordById(String id)
    {
        return cVehicleInspectRecordMapper.selectCVehicleInspectRecordById(id);
    }

    /**
     * 查询车辆管理-车辆年检记录列表
     * 
     * @param cVehicleInspectRecord 车辆管理-车辆年检记录
     * @return 车辆管理-车辆年检记录
     */
    @Override
    public List<CVehicleInspectRecord> selectCVehicleInspectRecordList(CVehicleInspectRecord cVehicleInspectRecord)
    {
        return cVehicleInspectRecordMapper.selectCVehicleInspectRecordList(cVehicleInspectRecord);
    }

    /**
     * 新增车辆管理-车辆年检记录
     * 
     * @param cVehicleInspectRecord 车辆管理-车辆年检记录
     * @return 结果
     */
    @Override
    public int insertCVehicleInspectRecord(CVehicleInspectRecord cVehicleInspectRecord)
    {
        cVehicleInspectRecord.setCreateTime(DateUtils.getNowDate());
        return cVehicleInspectRecordMapper.insertCVehicleInspectRecord(cVehicleInspectRecord);
    }

    /**
     * 修改车辆管理-车辆年检记录
     * 
     * @param cVehicleInspectRecord 车辆管理-车辆年检记录
     * @return 结果
     */
    @Override
    public int updateCVehicleInspectRecord(CVehicleInspectRecord cVehicleInspectRecord)
    {
        cVehicleInspectRecord.setUpdateTime(DateUtils.getNowDate());
        return cVehicleInspectRecordMapper.updateCVehicleInspectRecord(cVehicleInspectRecord);
    }

    /**
     * 批量删除车辆管理-车辆年检记录
     * 
     * @param ids 需要删除的车辆管理-车辆年检记录ID
     * @return 结果
     */
    @Override
    public int deleteCVehicleInspectRecordByIds(String[] ids)
    {
        return cVehicleInspectRecordMapper.deleteCVehicleInspectRecordByIds(ids);
    }

    /**
     * 删除车辆管理-车辆年检记录信息
     * 
     * @param id 车辆管理-车辆年检记录ID
     * @return 结果
     */
    @Override
    public int deleteCVehicleInspectRecordById(String id)
    {
        return cVehicleInspectRecordMapper.deleteCVehicleInspectRecordById(id);
    }

    /**
     * 查看详情列表
     */
    @Override
    public List<CVehicleInspectRecord> selectCVehicleInspectRecordInfoList(CVehicleInspectRecord cVehicleInspectRecord){
        return cVehicleInspectRecordMapper.selectCVehicleInspectRecordInfoList(cVehicleInspectRecord);
    }
}
