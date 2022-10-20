package com.dlzx.pfserverperson.service;

import java.util.List;

import com.dlzx.pfserverperson.domain.DNoticeUser;
import com.dlzx.pfserverperson.domain.DNoticeUserRecord;
import com.dlzx.pfserverperson.domain.DTrainingUser;
import com.dlzx.pfserverperson.domain.DTrainingUserRecord;

/**
 * 驾驶员-培训人员记录Service接口
 * 
 * @author dlzx
 * @date 2020-11-26
 */
public interface IDTrainingUserRecordService 
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
     * 编辑公告-事务
     * @param userList 人员信息
     * @param userRecordList 人员记录信息
     * @return
     */
    public int insertDTrainingUserSOrRecord(String trainingId, List<DTrainingUser> userList, List<DTrainingUserRecord> userRecordList);

    /**
     * 修改驾驶员-培训人员记录
     * 
     * @param dTrainingUserRecord 驾驶员-培训人员记录
     * @return 结果
     */
    public int updateDTrainingUserRecord(DTrainingUserRecord dTrainingUserRecord);

    /**
     * 批量删除驾驶员-培训人员记录
     * 
     * @param ids 需要删除的驾驶员-培训人员记录ID
     * @return 结果
     */
    public int deleteDTrainingUserRecordByIds(String[] ids);

    /**
     * 删除驾驶员-培训人员记录信息
     * 
     * @param id 驾驶员-培训人员记录ID
     * @return 结果
     */
    public int deleteDTrainingUserRecordById(String id);
}
