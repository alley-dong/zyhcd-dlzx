package com.dlzx.pfservercar.mapper;

import java.util.List;
import com.dlzx.pfservercar.domain.RGarageRepairItem;
import org.apache.ibatis.annotations.Param;

/**
 * 维修保养-维修站-维修明细Mapper接口
 * 
 * @author yangluhe
 * @date 2020-12-02
 */
public interface RGarageRepairItemMapper 
{
    /**
     * 查询维修保养-维修站-维修明细
     * 
     * @param id 维修保养-维修站-维修明细ID
     * @return 维修保养-维修站-维修明细
     */
    public RGarageRepairItem selectRGarageRepairItemById(String id);

    /**
     * 查询维修保养-维修站-维修明细列表
     * 
     * @param rGarageRepairItem 维修保养-维修站-维修明细
     * @return 维修保养-维修站-维修明细集合
     */
    public List<RGarageRepairItem> selectRGarageRepairItemList(RGarageRepairItem rGarageRepairItem);

    /**
     * 新增维修保养-维修站-维修明细
     * 
     * @param rGarageRepairItem 维修保养-维修站-维修明细
     * @return 结果
     */
    public int insertRGarageRepairItem(RGarageRepairItem rGarageRepairItem);

    /**
     * 修改维修保养-维修站-维修明细
     * 
     * @param rGarageRepairItem 维修保养-维修站-维修明细
     * @return 结果
     */
    public int updateRGarageRepairItem(RGarageRepairItem rGarageRepairItem);

    /**
     * 删除维修保养-维修站-维修明细
     * 
     * @param id 维修保养-维修站-维修明细ID
     * @return 结果
     */
    public int deleteRGarageRepairItemById(String id);

    /**
     * 批量删除维修保养-维修站-维修明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRGarageRepairItemByIds(String[] ids);

    /**
     * 查找所有数据
     */
    public List<RGarageRepairItem> selectRGarageRepairItemAll();

    /**
     * 查找名称
     */
    public List<RGarageRepairItem> selectRGarageRepairItemByGarageId(@Param("id")String id,@Param("name")String name);

    /**
     * 根据维修站id查找车型
     * @param id
     * @return
     */
    public List<RGarageRepairItem> selectVehicleModelByGarage(String id);

}
