package com.dlzx.pfserverperson.service.impl;

import java.util.List;

import com.dlzx.pfserverperson.domain.DNoticeAp;
import com.dlzx.pfserverperson.domain.DNoticeUserRecord;
import com.dlzx.pfserverperson.mapper.DNoticeUserRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DNoticeUserMapper;
import com.dlzx.pfserverperson.domain.DNoticeUser;
import com.dlzx.pfserverperson.service.IDNoticeUserService;
import org.springframework.transaction.annotation.Transactional;


/**
 * 驾驶员-公告人员关联Service业务层处理
 *
 * @author dlzx
 * @date 2020-11-24
 */
@Service
public class DNoticeUserServiceImpl implements IDNoticeUserService {
    @Autowired
    private DNoticeUserMapper dNoticeUserMapper;

    @Autowired
    private DNoticeUserRecordMapper dNoticeUserRecordMapper;


    /**
     * 查询驾驶员-公告人员关联
     *
     * @param id 驾驶员-公告人员关联ID
     * @return 驾驶员-公告人员关联
     */
    @Override
    public DNoticeUser selectDNoticeUserById(String id) {
        return dNoticeUserMapper.selectDNoticeUserById(id);
    }

    @Override
    public List<DNoticeUser> selectDNoticeUserByNoticeId(String noticeid) {
        return dNoticeUserMapper.selectDNoticeUserByNoticeId(noticeid);
    }

    /**
     * 查询驾驶员-公告人员关联列表
     *
     * @param dNoticeUser 驾驶员-公告人员关联
     * @return 驾驶员-公告人员关联
     */
    @Override
    public List<DNoticeUser> selectDNoticeUserList(DNoticeUser dNoticeUser) {
        return dNoticeUserMapper.selectDNoticeUserList(dNoticeUser);
    }

    /**
     * 查询公告已读列表
     *
     * @param dNoticeUser 驾驶员-公告人员关联
     * @return 驾驶员-公告人员关联集合
     */
    @Override
    public List<DNoticeUser> selectDNoticeUserTwoList(DNoticeUser dNoticeUser) {
        return dNoticeUserMapper.selectDNoticeUserTwoList(dNoticeUser);
    }

    /**
     * 新增驾驶员-公告人员关联
     *
     * @param dNoticeUser 驾驶员-公告人员关联
     * @return 结果
     */
    @Override
    public int insertDNoticeUser(DNoticeUser dNoticeUser) {
        return dNoticeUserMapper.insertDNoticeUser(dNoticeUser);
    }

    @Override
    public int insertDNoticeUserS(List<DNoticeUser> userList) {
        return dNoticeUserMapper.insertDNoticeUserS(userList);
    }

    /**
     * 新增公告-事务
     *
     * @param userList       人员信息
     * @param userRecordList 人员记录信息
     * @return
     */
    @Override
    @Transactional
    public int insertDNoticeUserSOrRecord(List<DNoticeUser> userList, List<DNoticeUserRecord> userRecordList) {
        int result = 1;
        try {
            if (userList.size() > 0) {
                result = this.insertDNoticeUserS(userList);

            }
            if (userRecordList.size() > 0) {
                result = dNoticeUserRecordMapper.insertDNoticeUserRecordT(userRecordList);

            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    /**
     * 修改驾驶员-公告人员关联
     *
     * @param dNoticeUser 驾驶员-公告人员关联
     * @return 结果
     */
    @Override
    public int updateDNoticeUser(DNoticeUser dNoticeUser) {
        return dNoticeUserMapper.updateDNoticeUser(dNoticeUser);
    }

    /**
     * 批量删除驾驶员-公告人员关联
     *
     * @param ids 需要删除的驾驶员-公告人员关联ID
     * @return 结果
     */
    @Override
    public int deleteDNoticeUserByIds(String[] ids) {
        return dNoticeUserMapper.deleteDNoticeUserByIds(ids);
    }

    /**
     * 删除驾驶员-公告人员关联信息
     *
     * @param id 驾驶员-公告人员关联ID
     * @return 结果
     */
    @Override
    public int deleteDNoticeUserById(String id) {
        return dNoticeUserMapper.deleteDNoticeUserById(id);
    }

    @Override
    public int deleteDNoticeUserByNoticeId(String noticeId) {
        return dNoticeUserMapper.deleteDNoticeUserByNoticeId(noticeId);
    }

    @Override
    public List<DNoticeAp> selectDNoticeList(DNoticeAp dNoticeAp) {
        return dNoticeUserMapper.selectDNoticeList(dNoticeAp);
    }
}
