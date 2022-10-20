package com.dlzx.pfservercar.mapper;

import java.util.List;
import com.dlzx.pfservercar.domain.RRepairItem;

/**
 * 维修保养-维修保养项Mapper接口
 * 
 * @author yangluhe
 * @date 2020-12-01
 */
public interface RRepairItemMapper 
{
    /**
     * 查询维修保养-维修保养项
     * 
     * @param id 维修保养-维修保养项ID
     * @return 维修保养-维修保养项
     */
    public RRepairItem selectRRepairItemById(String id);

    /**
     * 查询维修保养-维修保养项列表
     * 
     * @param rRepairItem 维修保养-维修保养项
     * @return 维修保养-维修保养项集合
     */
    public List<RRepairItem> selectRRepairItemList(RRepairItem rRepairItem);

    /**
     * 新增维修保养-维修保养项
     * 
     * @param rRepairItem 维修保养-维修保养项
     * @return 结果
     */
    public int insertRRepairItem(RRepairItem rRepairItem);

    /**
     * 修改维修保养-维修保养项
     * 
     * @param rRepairItem 维修保养-维修保养项
     * @return 结果
     */
    public int updateRRepairItem(RRepairItem rRepairItem);

    /**
     * 删除维修保养-维修保养项
     * 
     * @param id 维修保养-维修保养项ID
     * @return 结果
     */
    public int deleteRRepairItemById(String id);

    /**
     * 批量删除维修保养-维修保养项
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRRepairItemByIds(String[] ids);

    /**
     * 根据类型查询名称
     */
    public List<RRepairItem> selectRRepairItemByType(Long type);
}
