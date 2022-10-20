package com.dlzx.pfserverparts.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverparts.mapper.DSalaryRecordsMapper;
import com.dlzx.pfserverparts.domain.DSalaryRecords;
import com.dlzx.pfserverparts.service.IDSalaryRecordsService;

/**
 * 驾驶员-薪酬结算Service业务层处理
 * 
 * @author yangluhe
 * @date 2020-12-21
 */
@Service
public class DSalaryRecordsServiceImpl implements IDSalaryRecordsService 
{
    @Autowired
    private DSalaryRecordsMapper dSalaryRecordsMapper;

    /**
     * 查询驾驶员-薪酬结算
     * 
     * @param id 驾驶员-薪酬结算ID
     * @return 驾驶员-薪酬结算
     */
    @Override
    public DSalaryRecords selectDSalaryRecordsById(String id)
    {
        return dSalaryRecordsMapper.selectDSalaryRecordsById(id);
    }

    /**
     * 查询驾驶员-薪酬结算列表
     * 
     * @param dSalaryRecords 驾驶员-薪酬结算
     * @return 驾驶员-薪酬结算
     */
    @Override
    public List<DSalaryRecords> selectDSalaryRecordsList(DSalaryRecords dSalaryRecords)
    {
        return dSalaryRecordsMapper.selectDSalaryRecordsList(dSalaryRecords);
    }

    /**
     * 新增驾驶员-薪酬结算
     * 
     * @param dSalaryRecords 驾驶员-薪酬结算
     * @return 结果
     */
    @Override
    public int insertDSalaryRecords(DSalaryRecords dSalaryRecords)
    {
        dSalaryRecords.setCreateTime(DateUtils.getNowDate());
        return dSalaryRecordsMapper.insertDSalaryRecords(dSalaryRecords);
    }

    /**
     * 修改驾驶员-薪酬结算
     * 
     * @param dSalaryRecords 驾驶员-薪酬结算
     * @return 结果
     */
    @Override
    public int updateDSalaryRecords(DSalaryRecords dSalaryRecords)
    {
        dSalaryRecords.setUpdateTime(DateUtils.getNowDate());
        return dSalaryRecordsMapper.updateDSalaryRecords(dSalaryRecords);
    }

    /**
     * 批量删除驾驶员-薪酬结算
     * 
     * @param ids 需要删除的驾驶员-薪酬结算ID
     * @return 结果
     */
    @Override
    public int deleteDSalaryRecordsByIds(String[] ids)
    {
        return dSalaryRecordsMapper.deleteDSalaryRecordsByIds(ids);
    }

    /**
     * 删除驾驶员-薪酬结算信息
     * 
     * @param id 驾驶员-薪酬结算ID
     * @return 结果
     */
    @Override
    public int deleteDSalaryRecordsById(String id)
    {
        return dSalaryRecordsMapper.deleteDSalaryRecordsById(id);
    }
}
