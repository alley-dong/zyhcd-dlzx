package com.dlzx.pfserverperson.service;

import java.util.List;

import com.dlzx.pfserverperson.domain.DNoticeUser;
import com.dlzx.pfserverperson.domain.DNoticeUserRecord;

/**
 * 驾驶员-公告人员记录Service接口
 * 
 * @author dlzx
 * @date 2020-11-24
 */
public interface IDNoticeUserRecordService 
{
    /**
     * 查询驾驶员-公告人员记录
     * 
     * @param id 驾驶员-公告人员记录ID
     * @return 驾驶员-公告人员记录
     */
    public DNoticeUserRecord selectDNoticeUserRecordById(String id);

    /**
     * 查询驾驶员-公告人员记录列表
     * 
     * @param dNoticeUserRecord 驾驶员-公告人员记录
     * @return 驾驶员-公告人员记录集合
     */
    public List<DNoticeUserRecord> selectDNoticeUserRecordList(DNoticeUserRecord dNoticeUserRecord);

    public List<String> selectDNoticeUserRecordListS(List<String> driverList);
    /**
     * 新增驾驶员-公告人员记录
     * 
     * @param dNoticeUserRecord 驾驶员-公告人员记录
     * @return 结果
     */
    public int insertDNoticeUserRecord(DNoticeUserRecord dNoticeUserRecord);

    /**
     * 新增多条信息
     * @param userList 数据集合
     * @return
     */
    public int insertDNoticeUserRecordT(List<DNoticeUserRecord> userList);

    /**
     * 编辑公告-事务
     * @param userList 人员信息
     * @param userRecordList 人员记录信息
     * @return
     */
    public int insertDNoticeUserSOrRecord(String noticeId,List<DNoticeUser> userList,List<DNoticeUserRecord> userRecordList);

    /**
     * 修改驾驶员-公告人员记录
     * 
     * @param dNoticeUserRecord 驾驶员-公告人员记录
     * @return 结果
     */
    public int updateDNoticeUserRecord(DNoticeUserRecord dNoticeUserRecord);

    /**
     * 批量删除驾驶员-公告人员记录
     * 
     * @param ids 需要删除的驾驶员-公告人员记录ID
     * @return 结果
     */
    public int deleteDNoticeUserRecordByIds(String[] ids);

    /**
     * 删除驾驶员-公告人员记录信息
     * 
     * @param id 驾驶员-公告人员记录ID
     * @return 结果
     */
    public int deleteDNoticeUserRecordById(String id);
}
