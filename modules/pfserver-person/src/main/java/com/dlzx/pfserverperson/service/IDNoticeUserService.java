package com.dlzx.pfserverperson.service;

import java.util.List;

import com.dlzx.pfserverperson.domain.DNoticeAp;
import com.dlzx.pfserverperson.domain.DNoticeUser;
import com.dlzx.pfserverperson.domain.DNoticeUserRecord;

/**
 * 驾驶员-公告人员关联Service接口
 * 
 * @author dlzx
 * @date 2020-11-24
 */
public interface IDNoticeUserService 
{
    /**
     * 查询驾驶员-公告人员关联
     * 
     * @param id 驾驶员-公告人员关联ID
     * @return 驾驶员-公告人员关联
     */
    public DNoticeUser selectDNoticeUserById(String id);

    public List<DNoticeUser> selectDNoticeUserByNoticeId(String noticeId);
    /**
     * 查询驾驶员-公告人员关联列表
     * 
     * @param dNoticeUser 驾驶员-公告人员关联
     * @return 驾驶员-公告人员关联集合
     */
    public List<DNoticeUser> selectDNoticeUserList(DNoticeUser dNoticeUser);

    /**
     * 查询公告已读列表
     *
     * @param dNoticeUser 驾驶员-公告人员关联
     * @return 驾驶员-公告人员关联集合
     */
    public List<DNoticeUser> selectDNoticeUserTwoList(DNoticeUser dNoticeUser);
    /**
     * 新增驾驶员-公告人员关联
     * 
     * @param dNoticeUser 驾驶员-公告人员关联
     * @return 结果
     */
    public int insertDNoticeUser(DNoticeUser dNoticeUser);

    public int insertDNoticeUserS(List<DNoticeUser> userList);

    /**
     * 新增公告-事务
     * @param userList 人员信息
     * @param userRecordList 人员记录信息
     * @return
     */
    public int insertDNoticeUserSOrRecord(List<DNoticeUser> userList,List<DNoticeUserRecord> userRecordList);
    /**
     * 修改驾驶员-公告人员关联
     * 
     * @param dNoticeUser 驾驶员-公告人员关联
     * @return 结果
     */
    public int updateDNoticeUser(DNoticeUser dNoticeUser);

    /**
     * 批量删除驾驶员-公告人员关联
     * 
     * @param ids 需要删除的驾驶员-公告人员关联ID
     * @return 结果
     */
    public int deleteDNoticeUserByIds(String[] ids);

    /**
     * 删除驾驶员-公告人员关联信息
     * 
     * @param id 驾驶员-公告人员关联ID
     * @return 结果
     */
    public int deleteDNoticeUserById(String id);

    public int deleteDNoticeUserByNoticeId(String noticeId);

    //查app公告列表
    public List<DNoticeAp> selectDNoticeList(DNoticeAp dNoticeAp);
}
