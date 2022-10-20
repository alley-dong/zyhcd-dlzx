package com.dlzx.system.service;

import java.util.List;
import com.dlzx.system.domain.RGarage;
import org.springframework.stereotype.Service;

/**
 * 维修保养-维修站Service接口
 * 
 * @author dlzx
 * @date 2020-11-17
 */
@Service
public interface IRGarageService 
{
    /**
     * 查询维修保养-维修站
     * 
     * @param id 维修保养-维修站ID
     * @return 维修保养-维修站
     */
    public RGarage selectRGarageById(String id);

    /**
     * 查询维修保养-维修站列表
     * 
     * @param rGarage 维修保养-维修站
     * @return 维修保养-维修站集合
     */
    public List<RGarage> selectRGarageList(RGarage rGarage);

    public List<RGarage> selectRGarageListRG(RGarage rGarage);

    public List<RGarage> selectRGarageListL();

    public String selectIdByName(String name);
    /**
     * 新增维修保养-维修站
     * 
     * @param rGarage 维修保养-维修站
     * @return 结果
     */
    public int insertRGarage(RGarage rGarage);

    /**
     * 修改维修保养-维修站
     * 
     * @param rGarage 维修保养-维修站
     * @return 结果
     */
    public int updateRGarage(RGarage rGarage);

    /**
     * 批量删除维修保养-维修站
     * 
     * @param ids 需要删除的维修保养-维修站ID
     * @return 结果
     */
    public int deleteRGarageByIds(String[] ids);

    /**
     * 删除维修保养-维修站信息
     * 
     * @param id 维修保养-维修站ID
     * @return 结果
     */
    public int deleteRGarageById(String id);
}
