package com.dlzx.pfserverperson.mapper;

import java.util.List;
import com.dlzx.pfserverperson.domain.DDriverDriveRecord;

/**
 * 驾驶员-驾龄经历Mapper接口
 * 
 * @author yangluhe
 * @date 2020-11-19
 */
public interface DDriverDriveRecordMapper 
{
    /**
     * 查询驾驶员-驾龄经历
     * 
     * @param id 驾驶员-驾龄经历ID
     * @return 驾驶员-驾龄经历
     */
    public DDriverDriveRecord selectDDriverDriveRecordById(String id);

    /**
     * 查询驾驶员-驾龄经历列表
     * 
     * @param dDriverDriveRecord 驾驶员-驾龄经历
     * @return 驾驶员-驾龄经历集合
     */
    public List<DDriverDriveRecord> selectDDriverDriveRecordList(DDriverDriveRecord dDriverDriveRecord);

    /**
     * 新增驾驶员-驾龄经历
     * 
     * @param dDriverDriveRecord 驾驶员-驾龄经历
     * @return 结果
     */
    public int insertDDriverDriveRecord(DDriverDriveRecord dDriverDriveRecord);

    /**
     * 修改驾驶员-驾龄经历
     * 
     * @param dDriverDriveRecord 驾驶员-驾龄经历
     * @return 结果
     */
    public int updateDDriverDriveRecord(DDriverDriveRecord dDriverDriveRecord);

    /**
     * 删除驾驶员-驾龄经历
     * 
     * @param id 驾驶员-驾龄经历ID
     * @return 结果
     */
    public int deleteDDriverDriveRecordById(String id);

    /**
     * 批量删除驾驶员-驾龄经历
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDDriverDriveRecordByIds(String[] ids);

    /**
     * genju司机id查询列表
     */
    public List<DDriverDriveRecord> selectDDriverDriverRecordByDriverId(String id);
}
