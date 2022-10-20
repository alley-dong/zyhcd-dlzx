package com.dlzx.system.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.system.mapper.RGarageMapper;
import com.dlzx.system.domain.RGarage;
import com.dlzx.system.service.IRGarageService;

/**
 * 维修保养-维修站Service业务层处理
 * 
 * @author dlzx
 * @date 2020-11-17
 */
@Service
public class RGarageServiceImpl implements IRGarageService 
{
    @Autowired
    private RGarageMapper rGarageMapper;

    /**
     * 查询维修保养-维修站
     * 
     * @param id 维修保养-维修站ID
     * @return 维修保养-维修站
     */
    @Override
    public RGarage selectRGarageById(String id)
    {
        return rGarageMapper.selectRGarageById(id);
    }

    /**
     * 查询维修保养-维修站列表
     * 
     * @param rGarage 维修保养-维修站
     * @return 维修保养-维修站
     */
    @Override
    public List<RGarage> selectRGarageList(RGarage rGarage)
    {
        return rGarageMapper.selectRGarageList(rGarage);
    }

    @Override
    public List<RGarage> selectRGarageListRG(RGarage rGarage)
    {
        return rGarageMapper.selectRGarageListRG(rGarage);
    }


    @Override
    public List<RGarage> selectRGarageListL()
    {
        return rGarageMapper.selectRGarageListL();
    }

    @Override
    public String selectIdByName(String name)
    {
        return rGarageMapper.selectIdByName(name);
    }
    /**
     * 新增维修保养-维修站
     * 
     * @param rGarage 维修保养-维修站
     * @return 结果
     */
    @Override
    public int insertRGarage(RGarage rGarage)
    {
        rGarage.setCreateTime(DateUtils.getNowDate());
        return rGarageMapper.insertRGarage(rGarage);
    }

    /**
     * 修改维修保养-维修站
     * 
     * @param rGarage 维修保养-维修站
     * @return 结果
     */
    @Override
    public int updateRGarage(RGarage rGarage)
    {
        rGarage.setUpdateTime(DateUtils.getNowDate());
        return rGarageMapper.updateRGarage(rGarage);
    }

    /**
     * 批量删除维修保养-维修站
     * 
     * @param ids 需要删除的维修保养-维修站ID
     * @return 结果
     */
    @Override
    public int deleteRGarageByIds(String[] ids)
    {
        return rGarageMapper.deleteRGarageByIds(ids);
    }

    /**
     * 删除维修保养-维修站信息
     * 
     * @param id 维修保养-维修站ID
     * @return 结果
     */
    @Override
    public int deleteRGarageById(String id)
    {
        return rGarageMapper.deleteRGarageById(id);
    }
}
