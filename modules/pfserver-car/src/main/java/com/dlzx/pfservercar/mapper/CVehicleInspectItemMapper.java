package com.dlzx.pfservercar.mapper;

import java.util.List;
import com.dlzx.pfservercar.domain.CVehicleInspectItem;

/**
 * 车辆管理-车辆定检记录-检查项Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-08
 */
public interface CVehicleInspectItemMapper 
{
    /**
     * 查询车辆管理-车辆定检记录-检查项
     * 
     * @param id 车辆管理-车辆定检记录-检查项ID
     * @return 车辆管理-车辆定检记录-检查项
     */
    public CVehicleInspectItem selectCVehicleInspectItemById(String id);

    /**
     * 查询车辆管理-车辆定检记录-检查项列表
     * 
     * @param cVehicleInspectItem 车辆管理-车辆定检记录-检查项
     * @return 车辆管理-车辆定检记录-检查项集合
     */
    public List<CVehicleInspectItem> selectCVehicleInspectItemList(CVehicleInspectItem cVehicleInspectItem);

    /**
     * 新增车辆管理-车辆定检记录-检查项
     * 
     * @param cVehicleInspectItem 车辆管理-车辆定检记录-检查项
     * @return 结果
     */
    public int insertCVehicleInspectItem(CVehicleInspectItem cVehicleInspectItem);


    public int insertList(List<CVehicleInspectItem> item);


    /**
     * 修改车辆管理-车辆定检记录-检查项
     * 
     * @param cVehicleInspectItem 车辆管理-车辆定检记录-检查项
     * @return 结果
     */
    public int updateCVehicleInspectItem(CVehicleInspectItem cVehicleInspectItem);

    /**
     * 删除车辆管理-车辆定检记录-检查项
     * 
     * @param id 车辆管理-车辆定检记录-检查项ID
     * @return 结果
     */
    public int deleteCVehicleInspectItemById(String id);

    /**
     * 批量删除车辆管理-车辆定检记录-检查项
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCVehicleInspectItemByIds(String[] ids);

    /**
     * 查询定检记录下的检查项
     * @param id
     * @return
     */
    public List<CVehicleInspectItem> selectCVehicleInspectItemByInspectId(String id);

    /**
     * @describe 获取主车定检项
     * @author DongCL
     * @date 2021-03-24 16:19
     * @param
     * @return
     */
    List<CVehicleInspectItem> selectCMainVehicleInspectItemByInspectId(String id);


    /**
     * @describe 获取挂车定检项
     * @author DongCL
     * @date 2021-03-24 16:19
     * @param
     * @return
     */
    List<CVehicleInspectItem> selectCHangVehicleInspectItemByInspectId(String id);

    /**
     * @describe 根据定检id 删除item信息
     * @author DongCL
     * @date 2021-04-14 13:34
     * @param
     * @return
     */
    int deleteCVehicleInspectItemByinspectId(String id);
}
