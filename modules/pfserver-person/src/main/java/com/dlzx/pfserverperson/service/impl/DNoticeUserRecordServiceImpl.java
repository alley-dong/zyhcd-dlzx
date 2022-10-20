package com.dlzx.pfserverperson.service.impl;

import java.util.List;

import com.dlzx.pfserverperson.domain.DNoticeUser;
import com.dlzx.pfserverperson.mapper.DNoticeUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DNoticeUserRecordMapper;
import com.dlzx.pfserverperson.domain.DNoticeUserRecord;
import com.dlzx.pfserverperson.service.IDNoticeUserRecordService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 驾驶员-公告人员记录Service业务层处理
 * 
 * @author dlzx
 * @date 2020-11-24
 */
@Service
public class DNoticeUserRecordServiceImpl implements IDNoticeUserRecordService 
{
    @Autowired
    private DNoticeUserRecordMapper dNoticeUserRecordMapper;
    @Autowired
    private DNoticeUserMapper dNoticeUserMapper;
    /**
     * 查询驾驶员-公告人员记录
     * 
     * @param id 驾驶员-公告人员记录ID
     * @return 驾驶员-公告人员记录
     */
    @Override
    public DNoticeUserRecord selectDNoticeUserRecordById(String id)
    {
        return dNoticeUserRecordMapper.selectDNoticeUserRecordById(id);
    }

    /**
     * 查询驾驶员-公告人员记录列表
     * 
     * @param dNoticeUserRecord 驾驶员-公告人员记录
     * @return 驾驶员-公告人员记录
     */
    @Override
    public List<DNoticeUserRecord> selectDNoticeUserRecordList(DNoticeUserRecord dNoticeUserRecord)
    {
        return dNoticeUserRecordMapper.selectDNoticeUserRecordList(dNoticeUserRecord);
    }

    @Override
    public List<String> selectDNoticeUserRecordListS(List<String> driverList)
    {
        return dNoticeUserRecordMapper.selectDNoticeUserRecordListS(driverList);
    }
    /**
     * 新增驾驶员-公告人员记录
     * 
     * @param dNoticeUserRecord 驾驶员-公告人员记录
     * @return 结果
     */
    @Override
    public int insertDNoticeUserRecord(DNoticeUserRecord dNoticeUserRecord)
    {
        return dNoticeUserRecordMapper.insertDNoticeUserRecord(dNoticeUserRecord);
    }

    @Override
    public int insertDNoticeUserRecordT(List<DNoticeUserRecord> userList)
    {
        return dNoticeUserRecordMapper.insertDNoticeUserRecordT(userList);
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
    public int insertDNoticeUserSOrRecord(String noticeId,List<DNoticeUser> userList, List<DNoticeUserRecord> userRecordList) {
        int result = 1;
        try {
            dNoticeUserMapper.deleteDNoticeUserByNoticeId(noticeId);
            if (userList.size() > 0) {
                result = dNoticeUserMapper.insertDNoticeUserS(userList);

            }
            if (userRecordList.size() > 0) {
                result = this.insertDNoticeUserRecordT(userRecordList);

            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
    /**
     * 修改驾驶员-公告人员记录
     * 
     * @param dNoticeUserRecord 驾驶员-公告人员记录
     * @return 结果
     */
    @Override
    public int updateDNoticeUserRecord(DNoticeUserRecord dNoticeUserRecord)
    {
        return dNoticeUserRecordMapper.updateDNoticeUserRecord(dNoticeUserRecord);
    }

    /**
     * 批量删除驾驶员-公告人员记录
     * 
     * @param ids 需要删除的驾驶员-公告人员记录ID
     * @return 结果
     */
    @Override
    public int deleteDNoticeUserRecordByIds(String[] ids)
    {
        return dNoticeUserRecordMapper.deleteDNoticeUserRecordByIds(ids);
    }

    /**
     * 删除驾驶员-公告人员记录信息
     * 
     * @param id 驾驶员-公告人员记录ID
     * @return 结果
     */
    @Override
    public int deleteDNoticeUserRecordById(String id)
    {
        return dNoticeUserRecordMapper.deleteDNoticeUserRecordById(id);
    }
}
