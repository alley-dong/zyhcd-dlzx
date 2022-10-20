package com.dlzx.pfservercar.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.FFuelPlaceItemMapper;
import com.dlzx.pfservercar.domain.FFuelPlaceItem;
import com.dlzx.pfservercar.service.IFFuelPlaceItemService;

/**
 * 燃油定点加油站Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-01
 */
@Service
public class FFuelPlaceItemServiceImpl implements IFFuelPlaceItemService 
{
    @Autowired
    private FFuelPlaceItemMapper fFuelPlaceItemMapper;

    /**
     * 查询燃油定点加油站
     * 
     * @param id 燃油定点加油站ID
     * @return 燃油定点加油站
     */
    @Override
    public FFuelPlaceItem selectFFuelPlaceItemById(String id)
    {
        return fFuelPlaceItemMapper.selectFFuelPlaceItemById(id);
    }

    /**
     * 查询燃油定点加油站列表
     * 
     * @param fFuelPlaceItem 燃油定点加油站
     * @return 燃油定点加油站
     */
    @Override
    public List<FFuelPlaceItem> selectFFuelPlaceItemList(FFuelPlaceItem fFuelPlaceItem)
    {
        return fFuelPlaceItemMapper.selectFFuelPlaceItemList(fFuelPlaceItem);
    }

    /**
     * 新增燃油定点加油站
     * 
     * @param fFuelPlaceItem 燃油定点加油站
     * @return 结果
     */
    @Override
    public int insertFFuelPlaceItem(FFuelPlaceItem fFuelPlaceItem)
    {
        return fFuelPlaceItemMapper.insertFFuelPlaceItem(fFuelPlaceItem);
    }

    /**
     * 修改燃油定点加油站
     * 
     * @param fFuelPlaceItem 燃油定点加油站
     * @return 结果
     */
    @Override
    public int updateFFuelPlaceItem(FFuelPlaceItem fFuelPlaceItem)
    {
        return fFuelPlaceItemMapper.updateFFuelPlaceItem(fFuelPlaceItem);
    }

    /**
     * 批量删除燃油定点加油站
     * 
     * @param ids 需要删除的燃油定点加油站ID
     * @return 结果
     */
    @Override
    public int deleteFFuelPlaceItemByIds(String[] ids)
    {
        return fFuelPlaceItemMapper.deleteFFuelPlaceItemByIds(ids);
    }

    /**
     * 删除燃油定点加油站信息
     * 
     * @param id 燃油定点加油站ID
     * @return 结果
     */
    @Override
    public int deleteFFuelPlaceItemById(String id)
    {
        return fFuelPlaceItemMapper.deleteFFuelPlaceItemById(id);
    }
}
