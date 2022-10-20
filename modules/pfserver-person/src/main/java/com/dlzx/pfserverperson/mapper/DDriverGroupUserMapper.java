package com.dlzx.pfserverperson.mapper;

import java.util.List;
import com.dlzx.pfserverperson.domain.DDriverGroupUser;

/**
 * 驾驶员-驾驶员群组成员Mapper接口
 * 
 * @author dlzx
 * @date 2020-11-23
 */
public interface DDriverGroupUserMapper 
{
    /**
     * 查询驾驶员-驾驶员群组成员
     * 
     * @param id 驾驶员-驾驶员群组成员ID
     * @return 驾驶员-驾驶员群组成员
     */
    public DDriverGroupUser selectDDriverGroupUserById(String id);

    /**
     * 查询驾驶员-驾驶员群组成员
     *
     * @param id 驾驶员-驾驶员群组成员ID
     * @return 驾驶员-驾驶员群组成员集合
     */
    public List<DDriverGroupUser> selectDDriverGroupUserListById(String id);
    /**
     * 查询驾驶员-驾驶员群组成员列表
     * 
     * @param dDriverGroupUser 驾驶员-驾驶员群组成员
     * @return 驾驶员-驾驶员群组成员集合
     */
    public List<DDriverGroupUser> selectDDriverGroupUserList(DDriverGroupUser dDriverGroupUser);

    /**
     * 查询驾驶员-驾驶员群组成员列表
     *
     * @param groupId 群组ID
     * @return 群组成员集合
     */
    public List<String> selectDDriverGroupUserListS(String[] groupId);
    /**
     * 新增驾驶员-驾驶员群组成员
     * 
     * @param dDriverGroupUser 驾驶员-驾驶员群组成员
     * @return 结果
     */
    public int insertDDriverGroupUser(DDriverGroupUser dDriverGroupUser);
    /**
     * 新增驾驶员-驾驶员群组成员
     *
     * @param list 驾驶员-驾驶员群组成员集合
     * @return 结果
     */
    public int insertDDriverGroupUserList(List<DDriverGroupUser> list);
    /**
     * 修改驾驶员-驾驶员群组成员
     * 
     * @param dDriverGroupUser 驾驶员-驾驶员群组成员
     * @return 结果
     */
    public int updateDDriverGroupUser(DDriverGroupUser dDriverGroupUser);


    /**
     * 删除驾驶员-驾驶员群组成员
     * 
     * @param id 驾驶员-驾驶员群组成员ID
     * @return 结果
     */
    public int deleteDDriverGroupUserById(String id);

    /**
     * 删除驾驶员-驾驶员群组成员信息
     *
     * @param groupId 需要删除的群组ID
     * @return 结果
     */
    public int deleteDDriverGroupUserByGroupId(String groupId);

    /**
     * 批量删除驾驶员-驾驶员群组成员
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDDriverGroupUserByIds(String[] ids);
}
