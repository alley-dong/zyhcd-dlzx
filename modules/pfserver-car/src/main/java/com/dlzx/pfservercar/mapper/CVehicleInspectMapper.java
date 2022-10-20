package com.dlzx.pfservercar.mapper;

import java.util.List;
import com.dlzx.pfservercar.domain.CVehicleInspect;

/**
 * 车辆管理-车辆定检记录Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-08
 */
public interface CVehicleInspectMapper 
{
    /**
     * 查询车辆管理-车辆定检记录
     * 
     * @param id 车辆管理-车辆定检记录ID
     * @return 车辆管理-车辆定检记录
     */
    public CVehicleInspect selectCVehicleInspectById(String id);

    public List<CVehicleInspect> selectCVehicleInspectInfo(String monthlyId);

    /**
     * 查询车辆管理-车辆定检记录列表
     * 
     * @param cVehicleInspect 车辆管理-车辆定检记录
     * @return 车辆管理-车辆定检记录集合
     */
    public List<CVehicleInspect> selectCVehicleInspectList(CVehicleInspect cVehicleInspect);

    /**
     * app列表查询
     * @param cVehicleInspect
     * @return
     */
    public List<CVehicleInspect> selectCVehicleInspectListApp(CVehicleInspect cVehicleInspect);

    /**
     * 新增车辆管理-车辆定检记录
     * 
     * @param cVehicleInspect 车辆管理-车辆定检记录
     * @return 结果
     */
    public int insertCVehicleInspect(CVehicleInspect cVehicleInspect);

    /**
     * 修改车辆管理-车辆定检记录
     * 
     * @param cVehicleInspect 车辆管理-车辆定检记录
     * @return 结果
     */
    public int updateCVehicleInspect(CVehicleInspect cVehicleInspect);

    /**
     * 删除车辆管理-车辆定检记录
     * 
     * @param id 车辆管理-车辆定检记录ID
     * @return 结果
     */
    public int deleteCVehicleInspectById(String id);

    /**
     * 批量删除车辆管理-车辆定检记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCVehicleInspectByIds(String[] ids);

    public int checkCVehicleInspect(CVehicleInspect cVehicleInspect);

    public CVehicleInspect selectCVehicleInspectByMonthlyItemId(String id);
}
