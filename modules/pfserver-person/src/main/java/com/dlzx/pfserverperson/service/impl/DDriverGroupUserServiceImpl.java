package com.dlzx.pfserverperson.service.impl;

import java.util.List;

import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DDriverGroupUserMapper;
import com.dlzx.pfserverperson.domain.DDriverGroupUser;
import com.dlzx.pfserverperson.service.IDDriverGroupUserService;

/**
 * 驾驶员-驾驶员群组成员Service业务层处理
 * 
 * @author dlzx
 * @date 2020-11-23
 */
@Service
public class DDriverGroupUserServiceImpl implements IDDriverGroupUserService 
{
    @Autowired
    private DDriverGroupUserMapper dDriverGroupUserMapper;

    /**
     * 查询驾驶员-驾驶员群组成员
     * 
     * @param id 驾驶员-驾驶员群组成员ID
     * @return 驾驶员-驾驶员群组成员
     */
    @Override
    public DDriverGroupUser selectDDriverGroupUserById(String id)
    {
        return dDriverGroupUserMapper.selectDDriverGroupUserById(id);
    }

    /**
     * 查询驾驶员-驾驶员群组成员
     *
     * @param id 驾驶员-驾驶员群组成员ID
     * @return 驾驶员-驾驶员群组成员集合
     */
    @Override
    public List<DDriverGroupUser> selectDDriverGroupUserListById(String id)
    {
        return dDriverGroupUserMapper.selectDDriverGroupUserListById(id);
    }

    /**
     * 查询驾驶员-驾驶员群组成员列表
     * 
     * @param dDriverGroupUser 驾驶员-驾驶员群组成员
     * @return 驾驶员-驾驶员群组成员
     */
    @Override
    public List<DDriverGroupUser> selectDDriverGroupUserList(DDriverGroupUser dDriverGroupUser)
    {
        return dDriverGroupUserMapper.selectDDriverGroupUserList(dDriverGroupUser);
    }

    /**
     * 查询驾驶员-驾驶员群组成员列表
     *
     * @param groupId 群组ID
     * @return 群组成员集合
     */
    @Override
    public List<String> selectDDriverGroupUserListS(String[] groupId)
    {
        return dDriverGroupUserMapper.selectDDriverGroupUserListS(groupId);
    }

    /**
     * 新增驾驶员-驾驶员群组成员
     * 
     * @param dDriverGroupUser 驾驶员-驾驶员群组成员
     * @return 结果
     */
    @Override
    public int insertDDriverGroupUser(DDriverGroupUser dDriverGroupUser)
    {
        return dDriverGroupUserMapper.insertDDriverGroupUser(dDriverGroupUser);
    }

    /**
     * 新增驾驶员-驾驶员群组成员
     *
     * @param list 驾驶员-驾驶员群组成员集合
     * @return 结果
     */
    @Override
    public int insertDDriverGroupUserList(List<DDriverGroupUser> list)
    {
        return dDriverGroupUserMapper.insertDDriverGroupUserList(list);
    }
    /**
     * 修改驾驶员-驾驶员群组成员
     * 
     * @param dDriverGroupUser 驾驶员-驾驶员群组成员
     * @return 结果
     */
    @Override
    public int updateDDriverGroupUser(DDriverGroupUser dDriverGroupUser)
    {
        return dDriverGroupUserMapper.updateDDriverGroupUser(dDriverGroupUser);
    }


    /**
     * 批量删除驾驶员-驾驶员群组成员
     * 
     * @param ids 需要删除的驾驶员-驾驶员群组成员ID
     * @return 结果
     */
    @Override
    public int deleteDDriverGroupUserByIds(String[] ids)
    {
        return dDriverGroupUserMapper.deleteDDriverGroupUserByIds(ids);
    }

    /**
     * 删除驾驶员-驾驶员群组成员信息
     * 
     * @param id 驾驶员-驾驶员群组成员ID
     * @return 结果
     */
    @Override
    public int deleteDDriverGroupUserById(String id)
    {
        return dDriverGroupUserMapper.deleteDDriverGroupUserById(id);
    }
    /**
     * 删除驾驶员-驾驶员群组成员信息
     *
     * @param groupId 需要删除的群组ID
     * @return 结果
     */
    @Override
    public int deleteDDriverGroupUserByGroupId(String groupId)
    {
        return dDriverGroupUserMapper.deleteDDriverGroupUserByGroupId(groupId);
    }
}
