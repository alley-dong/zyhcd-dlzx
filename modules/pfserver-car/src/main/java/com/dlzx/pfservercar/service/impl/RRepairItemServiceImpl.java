package com.dlzx.pfservercar.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.RRepairItemMapper;
import com.dlzx.pfservercar.domain.RRepairItem;
import com.dlzx.pfservercar.service.IRRepairItemService;

/**
 * 维修保养-维修保养项Service业务层处理
 * 
 * @author yangluhe
 * @date 2020-12-01
 */
@Service
public class RRepairItemServiceImpl implements IRRepairItemService 
{
    @Autowired
    private RRepairItemMapper rRepairItemMapper;

    /**
     * 查询维修保养-维修保养项
     * 
     * @param id 维修保养-维修保养项ID
     * @return 维修保养-维修保养项
     */
    @Override
    public RRepairItem selectRRepairItemById(String id)
    {
        return rRepairItemMapper.selectRRepairItemById(id);
    }

    /**
     * 查询维修保养-维修保养项列表
     * 
     * @param rRepairItem 维修保养-维修保养项
     * @return 维修保养-维修保养项
     */
    @Override
    public List<RRepairItem> selectRRepairItemList(RRepairItem rRepairItem)
    {
        return rRepairItemMapper.selectRRepairItemList(rRepairItem);
    }

    /**
     * 新增维修保养-维修保养项
     * 
     * @param rRepairItem 维修保养-维修保养项
     * @return 结果
     */
    @Override
    public int insertRRepairItem(RRepairItem rRepairItem)
    {
        rRepairItem.setCreateTime(DateUtils.getNowDate());
        return rRepairItemMapper.insertRRepairItem(rRepairItem);
    }

    /**
     * 修改维修保养-维修保养项
     * 
     * @param rRepairItem 维修保养-维修保养项
     * @return 结果
     */
    @Override
    public int updateRRepairItem(RRepairItem rRepairItem)
    {
        rRepairItem.setUpdateTime(DateUtils.getNowDate());
        return rRepairItemMapper.updateRRepairItem(rRepairItem);
    }

    /**
     * 批量删除维修保养-维修保养项
     * 
     * @param ids 需要删除的维修保养-维修保养项ID
     * @return 结果
     */
    @Override
    public int deleteRRepairItemByIds(String[] ids)
    {
        return rRepairItemMapper.deleteRRepairItemByIds(ids);
    }

    /**
     * 删除维修保养-维修保养项信息
     * 
     * @param id 维修保养-维修保养项ID
     * @return 结果
     */
    @Override
    public int deleteRRepairItemById(String id)
    {
        return rRepairItemMapper.deleteRRepairItemById(id);
    }

    /**
     * 根据类型查询名称
     */
    @Override
    public List<RRepairItem> selectRRepairItemByType(Long type){
        return rRepairItemMapper.selectRRepairItemByType(type);
    }
}
