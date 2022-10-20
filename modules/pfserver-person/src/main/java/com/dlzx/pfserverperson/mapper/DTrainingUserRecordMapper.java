package com.dlzx.pfserverperson.mapper;

import java.util.List;

import com.dlzx.pfserverperson.domain.DNoticeUserRecord;
import com.dlzx.pfserverperson.domain.DTrainingUserRecord;

/**
 * 驾驶员-培训人员记录Mapper接口
 * 
 * @author dlzx
 * @date 2020-11-26
 */
public interface DTrainingUserRecordMapper 
{
    /**
     * 查询驾驶员-培训人员记录
     * 
     * @param id 驾驶员-培训人员记录ID
     * @return 驾驶员-培训人员记录
     */
    public DTrainingUserRecord selectDTrainingUserRecordById(String id);

    /**
     * 查询驾驶员-培训人员记录列表
     * 
     * @param dTrainingUserRecord 驾驶员-培训人员记录
     * @return 驾驶员-培训人员记录集合
     */
    public List<DTrainingUserRecord> selectDTrainingUserRecordList(DTrainingUserRecord dTrainingUserRecord);

    /**
     * 新增驾驶员-培训人员记录
     * 
     * @param dTrainingUserRecord 驾驶员-培训人员记录
     * @return 结果
     */
    public int insertDTrainingUserRecord(DTrainingUserRecord dTrainingUserRecord);

    public int insertDTrainingUserRecordT(List<DTrainingUserRecord> userList);
    /**
     * 修改驾驶员-培训人员记录
     * 
     * @param dTrainingUserRecord 驾驶员-培训人员记录
     * @return 结果
     */
    public int updateDTrainingUserRecord(DTrainingUserRecord dTrainingUserRecord);

    /**
     * 删除驾驶员-培训人员记录
     * 
     * @param id 驾驶员-培训人员记录ID
     * @return 结果
     */
    public int deleteDTrainingUserRecordById(String id);

    /**
     * 批量删除驾驶员-培训人员记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDTrainingUserRecordByIds(String[] ids);
}
