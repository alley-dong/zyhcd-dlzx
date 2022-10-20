package com.dlzx.pfserverperson.mapper;

import java.util.List;

import com.dlzx.pfserverperson.domain.DNoticeUser;
import com.dlzx.pfserverperson.domain.DNoticeUserRecord;

/**
 * 驾驶员-公告人员记录Mapper接口
 * 
 * @author dlzx
 * @date 2020-11-24
 */
public interface DNoticeUserRecordMapper 
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

    public int insertDNoticeUserRecordT(List<DNoticeUserRecord> userList);
    /**
     * 修改驾驶员-公告人员记录
     * 
     * @param dNoticeUserRecord 驾驶员-公告人员记录
     * @return 结果
     */
    public int updateDNoticeUserRecord(DNoticeUserRecord dNoticeUserRecord);

    /**
     * 删除驾驶员-公告人员记录
     * 
     * @param id 驾驶员-公告人员记录ID
     * @return 结果
     */
    public int deleteDNoticeUserRecordById(String id);

    /**
     * 批量删除驾驶员-公告人员记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDNoticeUserRecordByIds(String[] ids);
}
