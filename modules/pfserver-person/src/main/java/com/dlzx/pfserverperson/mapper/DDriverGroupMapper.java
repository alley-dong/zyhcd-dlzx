package com.dlzx.pfserverperson.mapper;

import java.util.List;
import com.dlzx.pfserverperson.domain.DDriverGroup;
import com.dlzx.pfserverperson.domain.DDriverGroupUser;

/**
 * 驾驶员-驾驶员群组Mapper接口
 * 
 * @author dlzx
 * @date 2020-11-23
 */
public interface DDriverGroupMapper 
{
    /**
     * 查询驾驶员-驾驶员群组
     * 
     * @param id 驾驶员-驾驶员群组ID
     * @return 驾驶员-驾驶员群组
     */
    public DDriverGroup selectDDriverGroupById(String id);

    /**
     * 查询驾驶员-驾驶员群组列表
     * 
     * @param dDriverGroup 驾驶员-驾驶员群组
     * @return 驾驶员-驾驶员群组集合
     */
    public List<DDriverGroup> selectDDriverGroupList(DDriverGroup dDriverGroup);

    public List<DDriverGroup> selectDDriverGroupListQunZu(String name);
    /**
     * 新增驾驶员-驾驶员群组
     * 
     * @param dDriverGroup 驾驶员-驾驶员群组
     * @return 结果
     */
    public int insertDDriverGroup(DDriverGroup dDriverGroup);

    /**
     * 修改驾驶员-驾驶员群组
     * 
     * @param dDriverGroup 驾驶员-驾驶员群组
     * @return 结果
     */
    public int updateDDriverGroup(DDriverGroup dDriverGroup);


    /**
     * 删除驾驶员-驾驶员群组
     * 
     * @param id 驾驶员-驾驶员群组ID
     * @return 结果
     */
    public int deleteDDriverGroupById(String id);


    /**
     * 批量删除驾驶员-驾驶员群组
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDDriverGroupByIds(String[] ids);
}
