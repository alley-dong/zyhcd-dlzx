package com.dlzx.pfservercar.service.impl;

import java.util.List;

import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.pfservercar.domain.FFuelPlaceItem;
import com.dlzx.pfservercar.mapper.FFuelPlaceItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.FFuelPlaceMapper;
import com.dlzx.pfservercar.domain.FFuelPlace;
import com.dlzx.pfservercar.service.IFFuelPlaceService;

/**
 * 燃油定点管理Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-01
 */
@Service
public class FFuelPlaceServiceImpl implements IFFuelPlaceService 
{
    @Autowired
    private FFuelPlaceMapper fFuelPlaceMapper;
    @Autowired
    private FFuelPlaceItemMapper fFuelPlaceItemMapper;

    /**
     * 查询燃油定点管理
     * 
     * @param id 燃油定点管理ID
     * @return 燃油定点管理
     */
    @Override
    public FFuelPlace selectFFuelPlaceById(String id)
    {
        FFuelPlace fFuelPlace = fFuelPlaceMapper.selectFFuelPlaceById(id);
        if (fFuelPlace== null){
            throw new CustomException("详情信息不存在");
        }
        FFuelPlaceItem fFuelPlaceItem = new FFuelPlaceItem();
        fFuelPlaceItem.setPlaceId(fFuelPlace.getId());
        List<FFuelPlaceItem> fFuelPlaceItems = fFuelPlaceItemMapper.selectFFuelPlaceItemList(fFuelPlaceItem);

        // 更新定点下的加油站集合
        fFuelPlace.setfFuelPlaceItemList(fFuelPlaceItems);
        return fFuelPlace;
    }

    /**
     * 查询燃油定点管理列表
     * 
     * @param fFuelPlace 燃油定点管理
     * @return 燃油定点管理
     */
    @Override
    public List<FFuelPlace> selectFFuelPlaceList(FFuelPlace fFuelPlace)
    {
        return fFuelPlaceMapper.selectFFuelPlaceList(fFuelPlace);
    }

    /**
     * 新增燃油定点管理
     * 
     * @param fFuelPlace 燃油定点管理
     * @return 结果
     */
    @Override
    public int insertFFuelPlace(FFuelPlace fFuelPlace)
    {
        fFuelPlace.setCreateTime(DateUtils.getNowDate());
        return fFuelPlaceMapper.insertFFuelPlace(fFuelPlace);
    }

    /**
     * 修改燃油定点管理
     * 
     * @param fFuelPlace 燃油定点管理
     * @return 结果
     */
    @Override
    public int updateFFuelPlace(FFuelPlace fFuelPlace)
    {
        fFuelPlace.setUpdateTime(DateUtils.getNowDate());
        return fFuelPlaceMapper.updateFFuelPlace(fFuelPlace);
    }

    /**
     * 批量删除燃油定点管理
     * 
     * @param ids 需要删除的燃油定点管理ID
     * @return 结果
     */
    @Override
    public int deleteFFuelPlaceByIds(String[] ids)
    {
        return fFuelPlaceMapper.deleteFFuelPlaceByIds(ids);
    }

    /**
     * 删除燃油定点管理信息
     * 
     * @param id 燃油定点管理ID
     * @return 结果
     */
    @Override
    public int deleteFFuelPlaceById(String id)
    {
        return fFuelPlaceMapper.deleteFFuelPlaceById(id);
    }
}
