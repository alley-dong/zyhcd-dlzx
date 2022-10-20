package com.dlzx.pfserverperson.service;

import java.util.List;
import com.dlzx.pfserverperson.domain.DDriverDriveRecord;

/**
 * 驾驶员-驾龄经历Service接口
 * 
 * @author yangluhe
 * @date 2020-11-19
 */
public interface IDDriverDriveRecordService 
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
     * 批量删除驾驶员-驾龄经历
     * 
     * @param ids 需要删除的驾驶员-驾龄经历ID
     * @return 结果
     */
    public int deleteDDriverDriveRecordByIds(String[] ids);

    /**
     * 删除驾驶员-驾龄经历信息
     * 
     * @param id 驾驶员-驾龄经历ID
     * @return 结果
     */
    public int deleteDDriverDriveRecordById(String id);

    /**
     * genju id
     */
    public List<DDriverDriveRecord> selectDDriverDriverRecordByDriverId(String id);
}
