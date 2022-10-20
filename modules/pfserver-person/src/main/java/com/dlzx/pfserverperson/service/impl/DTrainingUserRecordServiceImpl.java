package com.dlzx.pfserverperson.service.impl;

import java.util.List;

import com.dlzx.pfserverperson.domain.DNoticeUser;
import com.dlzx.pfserverperson.domain.DNoticeUserRecord;
import com.dlzx.pfserverperson.domain.DTrainingUser;
import com.dlzx.pfserverperson.mapper.DNoticeUserMapper;
import com.dlzx.pfserverperson.mapper.DTrainingUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DTrainingUserRecordMapper;
import com.dlzx.pfserverperson.domain.DTrainingUserRecord;
import com.dlzx.pfserverperson.service.IDTrainingUserRecordService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 驾驶员-培训人员记录Service业务层处理
 * 
 * @author dlzx
 * @date 2020-11-26
 */
@Service
public class DTrainingUserRecordServiceImpl implements IDTrainingUserRecordService 
{
    @Autowired
    private DTrainingUserRecordMapper dTrainingUserRecordMapper;
    @Autowired
    private DTrainingUserMapper dTrainingUserMapper;
    /**
     * 查询驾驶员-培训人员记录
     * 
     * @param id 驾驶员-培训人员记录ID
     * @return 驾驶员-培训人员记录
     */
    @Override
    public DTrainingUserRecord selectDTrainingUserRecordById(String id)
    {
        return dTrainingUserRecordMapper.selectDTrainingUserRecordById(id);
    }

    /**
     * 查询驾驶员-培训人员记录列表
     * 
     * @param dTrainingUserRecord 驾驶员-培训人员记录
     * @return 驾驶员-培训人员记录
     */
    @Override
    public List<DTrainingUserRecord> selectDTrainingUserRecordList(DTrainingUserRecord dTrainingUserRecord)
    {
        return dTrainingUserRecordMapper.selectDTrainingUserRecordList(dTrainingUserRecord);
    }

    /**
     * 新增驾驶员-培训人员记录
     * 
     * @param dTrainingUserRecord 驾驶员-培训人员记录
     * @return 结果
     */
    @Override
    public int insertDTrainingUserRecord(DTrainingUserRecord dTrainingUserRecord)
    {
        return dTrainingUserRecordMapper.insertDTrainingUserRecord(dTrainingUserRecord);
    }

    @Override
    public int insertDTrainingUserRecordT(List<DTrainingUserRecord> userList)
    {
        return dTrainingUserRecordMapper.insertDTrainingUserRecordT(userList);
    }

    /**
     * 编辑公告-事务
     *
     * @param userList       人员信息
     * @param userRecordList 人员记录信息
     * @return
     */
    @Override
    @Transactional
    public int insertDTrainingUserSOrRecord(String trainingId, List<DTrainingUser> userList, List<DTrainingUserRecord> userRecordList) {
        int result = 1;
        try {
            dTrainingUserMapper.deleteDTrainingUserByTrainingId(trainingId);
            if (userList.size() > 0) {
                result = dTrainingUserMapper.insertDTrainingUserS(userList);

            }
            if (userRecordList.size() > 0) {
                result = this.insertDTrainingUserRecordT(userRecordList);

            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    /**
     * 修改驾驶员-培训人员记录
     * 
     * @param dTrainingUserRecord 驾驶员-培训人员记录
     * @return 结果
     */
    @Override
    public int updateDTrainingUserRecord(DTrainingUserRecord dTrainingUserRecord)
    {
        return dTrainingUserRecordMapper.updateDTrainingUserRecord(dTrainingUserRecord);
    }

    /**
     * 批量删除驾驶员-培训人员记录
     * 
     * @param ids 需要删除的驾驶员-培训人员记录ID
     * @return 结果
     */
    @Override
    public int deleteDTrainingUserRecordByIds(String[] ids)
    {
        return dTrainingUserRecordMapper.deleteDTrainingUserRecordByIds(ids);
    }

    /**
     * 删除驾驶员-培训人员记录信息
     * 
     * @param id 驾驶员-培训人员记录ID
     * @return 结果
     */
    @Override
    public int deleteDTrainingUserRecordById(String id)
    {
        return dTrainingUserRecordMapper.deleteDTrainingUserRecordById(id);
    }
}
