package com.dlzx.pfserverperson.mapper;

import java.util.List;
import com.dlzx.pfserverperson.domain.DDriverEducationRecord;

/**
 * 驾驶员-教育经历Mapper接口
 * 
 * @author yangluhe
 * @date 2020-11-19
 */
public interface DDriverEducationRecordMapper 
{
    /**
     * 查询驾驶员-教育经历
     * 
     * @param id 驾驶员-教育经历ID
     * @return 驾驶员-教育经历
     */
    public DDriverEducationRecord selectDDriverEducationRecordById(String id);

    /**
     * 查询驾驶员-教育经历列表
     * 
     * @param dDriverEducationRecord 驾驶员-教育经历
     * @return 驾驶员-教育经历集合
     */
    public List<DDriverEducationRecord> selectDDriverEducationRecordList(DDriverEducationRecord dDriverEducationRecord);

    /**
     * 新增驾驶员-教育经历
     * 
     * @param dDriverEducationRecord 驾驶员-教育经历
     * @return 结果
     */
    public int insertDDriverEducationRecord(DDriverEducationRecord dDriverEducationRecord);

    /**
     * 修改驾驶员-教育经历
     * 
     * @param dDriverEducationRecord 驾驶员-教育经历
     * @return 结果
     */
    public int updateDDriverEducationRecord(DDriverEducationRecord dDriverEducationRecord);

    /**
     * 删除驾驶员-教育经历
     * 
     * @param id 驾驶员-教育经历ID
     * @return 结果
     */
    public int deleteDDriverEducationRecordById(String id);

    /**
     * 批量删除驾驶员-教育经历
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDDriverEducationRecordByIds(String[] ids);

    /**
     * 教育经历列表
     */
    public List<DDriverEducationRecord> selectDDriverEducationRecordByDriverId(String id);
}
