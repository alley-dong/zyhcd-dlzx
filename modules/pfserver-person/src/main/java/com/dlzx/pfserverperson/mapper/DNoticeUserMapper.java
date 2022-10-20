package com.dlzx.pfserverperson.mapper;

import java.util.List;

import com.dlzx.pfserverperson.domain.DNoticeAp;
import com.dlzx.pfserverperson.domain.DNoticeUser;

/**
 * 驾驶员-公告人员关联Mapper接口
 * 
 * @author dlzx
 * @date 2020-11-24
 */
public interface DNoticeUserMapper 
{
    /**
     * 查询驾驶员-公告人员关联
     * 
     * @param id 驾驶员-公告人员关联ID
     * @return 驾驶员-公告人员关联
     */
    public DNoticeUser selectDNoticeUserById(String id);

    public List<DNoticeUser> selectDNoticeUserByNoticeId(String noticeid);
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
     * 修改驾驶员-公告人员关联
     * 
     * @param dNoticeUser 驾驶员-公告人员关联
     * @return 结果
     */
    public int updateDNoticeUser(DNoticeUser dNoticeUser);

    /**
     * 删除驾驶员-公告人员关联
     * 
     * @param id 驾驶员-公告人员关联ID
     * @return 结果
     */
    public int deleteDNoticeUserById(String id);

    public int deleteDNoticeUserByNoticeId(String noticeId);
    /**
     * 批量删除驾驶员-公告人员关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDNoticeUserByIds(String[] ids);

    public List<DNoticeAp> selectDNoticeList(DNoticeAp dNoticeAp);
}
