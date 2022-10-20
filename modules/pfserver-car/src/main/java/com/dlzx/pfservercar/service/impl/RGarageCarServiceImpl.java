package com.dlzx.pfservercar.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.RGarageCarMapper;
import com.dlzx.pfservercar.domain.RGarageCar;
import com.dlzx.pfservercar.service.IRGarageCarService;

/**
 * 维修保养-维修站Service业务层处理
 * 
 * @author yangluhe
 * @date 2020-12-01
 */
@Service
public class RGarageCarServiceImpl implements IRGarageCarService 
{
    @Autowired
    private RGarageCarMapper rGarageCarMapper;

    /**
     * 查询维修保养-维修站
     * 
     * @param id 维修保养-维修站ID
     * @return 维修保养-维修站
     */
    @Override
    public RGarageCar selectRGarageCarById(String id)
    {
        return rGarageCarMapper.selectRGarageCarById(id);
    }

    /**
     * 查询维修保养-维修站列表
     * 
     * @param rGarageCar 维修保养-维修站
     * @return 维修保养-维修站
     */
    @Override
    public List<RGarageCar> selectRGarageCarList(RGarageCar rGarageCar)
    {
        return rGarageCarMapper.selectRGarageCarList(rGarageCar);
    }

    /**
     * 新增维修保养-维修站
     * 
     * @param rGarageCar 维修保养-维修站
     * @return 结果
     */
    @Override
    public int insertRGarageCar(RGarageCar rGarageCar)
    {
        rGarageCar.setCreateTime(DateUtils.getNowDate());
        return rGarageCarMapper.insertRGarageCar(rGarageCar);
    }

    /**
     * 修改维修保养-维修站
     * 
     * @param rGarageCar 维修保养-维修站
     * @return 结果
     */
    @Override
    public int updateRGarageCar(RGarageCar rGarageCar)
    {
        rGarageCar.setUpdateTime(DateUtils.getNowDate());
        return rGarageCarMapper.updateRGarageCar(rGarageCar);
    }

    /**
     * 批量删除维修保养-维修站
     * 
     * @param ids 需要删除的维修保养-维修站ID
     * @return 结果
     */
    @Override
    public int deleteRGarageCarByIds(String[] ids)
    {
        return rGarageCarMapper.deleteRGarageCarByIds(ids);
    }

    /**
     * 删除维修保养-维修站信息
     * 
     * @param id 维修保养-维修站ID
     * @return 结果
     */
    @Override
    public int deleteRGarageCarById(String id)
    {
        return rGarageCarMapper.deleteRGarageCarById(id);
    }

    /**
     * 维修保养站下拉框
     */
    @Override
    public List<RGarageCar> selectRGarageCarSelectBox(){
        return rGarageCarMapper.selectRGarageCarSelectBox();
    }

    /**
     * @describe 维修服务站（定点）
     * @author DongCL
     * @date 2021-04-21 14:38
     * @param
     * @return
     */
    @Override
    public List<RGarageCar> selectRGarageCarDownPull() {
        return rGarageCarMapper.selectRGarageCarDownPull();
    }
}
