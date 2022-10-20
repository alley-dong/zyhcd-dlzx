package com.dlzx.pfserverperson.mapper;

import java.util.List;
import com.dlzx.pfserverperson.domain.DDriverFamilyRecord;

/**
 * 驾驶员-家庭状况经历Mapper接口
 * 
 * @author yangluhe
 * @date 2020-11-19
 */
public interface DDriverFamilyRecordMapper 
{
    /**
     * 查询驾驶员-家庭状况经历
     * 
     * @param id 驾驶员-家庭状况经历ID
     * @return 驾驶员-家庭状况经历
     */
    public DDriverFamilyRecord selectDDriverFamilyRecordById(String id);

    /**
     * 查询驾驶员-家庭状况经历列表
     * 
     * @param dDriverFamilyRecord 驾驶员-家庭状况经历
     * @return 驾驶员-家庭状况经历集合
     */
    public List<DDriverFamilyRecord> selectDDriverFamilyRecordList(DDriverFamilyRecord dDriverFamilyRecord);

    /**
     * 新增驾驶员-家庭状况经历
     * 
     * @param dDriverFamilyRecord 驾驶员-家庭状况经历
     * @return 结果
     */
    public int insertDDriverFamilyRecord(DDriverFamilyRecord dDriverFamilyRecord);

    /**
     * 修改驾驶员-家庭状况经历
     * 
     * @param dDriverFamilyRecord 驾驶员-家庭状况经历
     * @return 结果
     */
    public int updateDDriverFamilyRecord(DDriverFamilyRecord dDriverFamilyRecord);

    /**
     * 删除驾驶员-家庭状况经历
     * 
     * @param id 驾驶员-家庭状况经历ID
     * @return 结果
     */
    public int deleteDDriverFamilyRecordById(String id);

    /**
     * 批量删除驾驶员-家庭状况经历
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDDriverFamilyRecordByIds(String[] ids);

    /**
     * 根据司机id查找列表
     */
    public List<DDriverFamilyRecord> selectDDriverFamilyRecordByDriverId(String id);
}
