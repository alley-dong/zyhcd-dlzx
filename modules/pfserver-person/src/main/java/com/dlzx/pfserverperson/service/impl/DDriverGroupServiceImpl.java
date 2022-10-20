package com.dlzx.pfserverperson.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.pfserverperson.domain.DDriverGroupUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DDriverGroupMapper;
import com.dlzx.pfserverperson.domain.DDriverGroup;
import com.dlzx.pfserverperson.service.IDDriverGroupService;

/**
 * 驾驶员-驾驶员群组Service业务层处理
 * 
 * @author dlzx
 * @date 2020-11-23
 */
@Service
public class DDriverGroupServiceImpl implements IDDriverGroupService 
{
    @Autowired
    private DDriverGroupMapper dDriverGroupMapper;

    /**
     * 查询驾驶员-驾驶员群组
     * 
     * @param id 驾驶员-驾驶员群组ID
     * @return 驾驶员-驾驶员群组
     */
    @Override
    public DDriverGroup selectDDriverGroupById(String id)
    {
        return dDriverGroupMapper.selectDDriverGroupById(id);
    }

    /**
     * 查询驾驶员-驾驶员群组列表
     * 
     * @param dDriverGroup 驾驶员-驾驶员群组
     * @return 驾驶员-驾驶员群组
     */
    @Override
    public List<DDriverGroup> selectDDriverGroupList(DDriverGroup dDriverGroup)
    {
        return dDriverGroupMapper.selectDDriverGroupList(dDriverGroup);
    }

    @Override
    public List<DDriverGroup> selectDDriverGroupListQunZu(String name)
    {
        return dDriverGroupMapper.selectDDriverGroupListQunZu(name);
    }

    /**
     * 新增驾驶员-驾驶员群组
     * 
     * @param dDriverGroup 驾驶员-驾驶员群组
     * @return 结果
     */
    @Override
    public int insertDDriverGroup(DDriverGroup dDriverGroup)
    {
        dDriverGroup.setCreateTime(DateUtils.getNowDate());
        return dDriverGroupMapper.insertDDriverGroup(dDriverGroup);
    }

    /**
     * 修改驾驶员-驾驶员群组
     * 
     * @param dDriverGroup 驾驶员-驾驶员群组
     * @return 结果
     */
    @Override
    public int updateDDriverGroup(DDriverGroup dDriverGroup)
    {
        dDriverGroup.setUpdateTime(DateUtils.getNowDate());
        return dDriverGroupMapper.updateDDriverGroup(dDriverGroup);
    }


    /**
     * 批量删除驾驶员-驾驶员群组
     * 
     * @param ids 需要删除的驾驶员-驾驶员群组ID
     * @return 结果
     */
    @Override
    public int deleteDDriverGroupByIds(String[] ids)
    {
        return dDriverGroupMapper.deleteDDriverGroupByIds(ids);
    }

    /**
     * 删除驾驶员-驾驶员群组信息
     * 
     * @param id 驾驶员-驾驶员群组ID
     * @return 结果
     */
    @Override
    public int deleteDDriverGroupById(String id)
    {
        return dDriverGroupMapper.deleteDDriverGroupById(id);
    }


}
