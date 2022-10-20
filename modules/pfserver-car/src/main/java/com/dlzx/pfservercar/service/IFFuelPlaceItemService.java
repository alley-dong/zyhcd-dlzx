package com.dlzx.pfservercar.service;

import java.util.List;
import com.dlzx.pfservercar.domain.FFuelPlaceItem;

/**
 * 燃油定点加油站Service接口
 * 
 * @author dlzx
 * @date 2020-12-01
 */
public interface IFFuelPlaceItemService 
{
    /**
     * 查询燃油定点加油站
     * 
     * @param id 燃油定点加油站ID
     * @return 燃油定点加油站
     */
    public FFuelPlaceItem selectFFuelPlaceItemById(String id);

    /**
     * 查询燃油定点加油站列表
     * 
     * @param fFuelPlaceItem 燃油定点加油站
     * @return 燃油定点加油站集合
     */
    public List<FFuelPlaceItem> selectFFuelPlaceItemList(FFuelPlaceItem fFuelPlaceItem);

    /**
     * 新增燃油定点加油站
     * 
     * @param fFuelPlaceItem 燃油定点加油站
     * @return 结果
     */
    public int insertFFuelPlaceItem(FFuelPlaceItem fFuelPlaceItem);

    /**
     * 修改燃油定点加油站
     * 
     * @param fFuelPlaceItem 燃油定点加油站
     * @return 结果
     */
    public int updateFFuelPlaceItem(FFuelPlaceItem fFuelPlaceItem);

    /**
     * 批量删除燃油定点加油站
     * 
     * @param ids 需要删除的燃油定点加油站ID
     * @return 结果
     */
    public int deleteFFuelPlaceItemByIds(String[] ids);

    /**
     * 删除燃油定点加油站信息
     * 
     * @param id 燃油定点加油站ID
     * @return 结果
     */
    public int deleteFFuelPlaceItemById(String id);
}
