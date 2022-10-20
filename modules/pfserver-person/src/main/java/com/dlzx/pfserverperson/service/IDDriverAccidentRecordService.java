package com.dlzx.pfserverperson.service;

import java.util.List;
import com.dlzx.pfserverperson.domain.DDriverAccidentRecord;

/**
 * 驾驶员-交通事故经历Service接口
 * 
 * @author yangluhe
 * @date 2020-11-19
 */
public interface IDDriverAccidentRecordService 
{
    /**
     * 查询驾驶员-交通事故经历
     * 
     * @param id 驾驶员-交通事故经历ID
     * @return 驾驶员-交通事故经历
     */
    public DDriverAccidentRecord selectDDriverAccidentRecordById(String id);

    /**
     * 查询驾驶员-交通事故经历列表
     * 
     * @param dDriverAccidentRecord 驾驶员-交通事故经历
     * @return 驾驶员-交通事故经历集合
     */
    public List<DDriverAccidentRecord> selectDDriverAccidentRecordList(DDriverAccidentRecord dDriverAccidentRecord);

    /**
     * 新增驾驶员-交通事故经历
     * 
     * @param dDriverAccidentRecord 驾驶员-交通事故经历
     * @return 结果
     */
    public int insertDDriverAccidentRecord(DDriverAccidentRecord dDriverAccidentRecord);

    /**
     * 修改驾驶员-交通事故经历
     * 
     * @param dDriverAccidentRecord 驾驶员-交通事故经历
     * @return 结果
     */
    public int updateDDriverAccidentRecord(DDriverAccidentRecord dDriverAccidentRecord);

    /**
     * 批量删除驾驶员-交通事故经历
     * 
     * @param ids 需要删除的驾驶员-交通事故经历ID
     * @return 结果
     */
    public int deleteDDriverAccidentRecordByIds(String[] ids);

    /**
     * 删除驾驶员-交通事故经历信息
     * 
     * @param id 驾驶员-交通事故经历ID
     * @return 结果
     */
    public int deleteDDriverAccidentRecordById(String id);

    /**
     * 根据司机id查询记录列表
     */
    public List<DDriverAccidentRecord> selectDDriverAccidentRecordByDriverId(String id);
}
