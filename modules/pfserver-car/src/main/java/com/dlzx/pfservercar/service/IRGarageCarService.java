package com.dlzx.pfservercar.service;

import java.util.List;
import com.dlzx.pfservercar.domain.RGarageCar;

/**
 * 维修保养-维修站Service接口
 * 
 * @author yangluhe
 * @date 2020-12-01
 */
public interface IRGarageCarService 
{
    /**
     * 查询维修保养-维修站
     * 
     * @param id 维修保养-维修站ID
     * @return 维修保养-维修站
     */
    public RGarageCar selectRGarageCarById(String id);

    /**
     * 查询维修保养-维修站列表
     * 
     * @param rGarageCar 维修保养-维修站
     * @return 维修保养-维修站集合
     */
    public List<RGarageCar> selectRGarageCarList(RGarageCar rGarageCar);

    /**
     * 新增维修保养-维修站
     * 
     * @param rGarageCar 维修保养-维修站
     * @return 结果
     */
    public int insertRGarageCar(RGarageCar rGarageCar);

    /**
     * 修改维修保养-维修站
     * 
     * @param rGarageCar 维修保养-维修站
     * @return 结果
     */
    public int updateRGarageCar(RGarageCar rGarageCar);

    /**
     * 批量删除维修保养-维修站
     * 
     * @param ids 需要删除的维修保养-维修站ID
     * @return 结果
     */
    public int deleteRGarageCarByIds(String[] ids);

    /**
     * 删除维修保养-维修站信息
     * 
     * @param id 维修保养-维修站ID
     * @return 结果
     */
    public int deleteRGarageCarById(String id);

    /**
     * 维修保养站下拉选
     */
    public List<RGarageCar> selectRGarageCarSelectBox();

    /**
     * @describe 维修服务站（定点）
     * @author DongCL
     * @date 2021-04-21 14:38
     * @param
     * @return
     */
    List<RGarageCar> selectRGarageCarDownPull();
}
