package com.dlzx.pfservercar.mapper;

import java.util.Date;
import java.util.List;
import com.dlzx.pfservercar.domain.CVehicleInspectMonthly;

/**
 * 车辆管理-车辆定检发布Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-08
 */
public interface CVehicleInspectMonthlyMapper 
{
    /**
     * 查询车辆管理-车辆定检发布
     * 
     * @param id 车辆管理-车辆定检发布ID
     * @return 车辆管理-车辆定检发布
     */
    public CVehicleInspectMonthly selectCVehicleInspectMonthlyById(String id);

    public List<CVehicleInspectMonthly> selectCVehicleInspectMonthlyByInspectDate(String inspectDate);

    /**
     * 查询车辆管理-车辆定检发布列表
     * 
     * @param cVehicleInspectMonthly 车辆管理-车辆定检发布
     * @return 车辆管理-车辆定检发布集合
     */
    public List<CVehicleInspectMonthly> selectCVehicleInspectMonthlyList(CVehicleInspectMonthly cVehicleInspectMonthly);

    /**
     * 新增车辆管理-车辆定检发布
     * 
     * @param cVehicleInspectMonthly 车辆管理-车辆定检发布
     * @return 结果
     */
    public int insertCVehicleInspectMonthly(CVehicleInspectMonthly cVehicleInspectMonthly);

    /**
     * 修改车辆管理-车辆定检发布
     * 
     * @param cVehicleInspectMonthly 车辆管理-车辆定检发布
     * @return 结果
     */
    public int updateCVehicleInspectMonthly(CVehicleInspectMonthly cVehicleInspectMonthly);


    public int updateCVehicleInspectMonthlyDate(Date inspectDate);
    public int updateCVehicleInspectMonthlyDateNo(Date inspectDate);

    /**
     * 删除车辆管理-车辆定检发布
     * 
     * @param id 车辆管理-车辆定检发布ID
     * @return 结果
     */
    public int deleteCVehicleInspectMonthlyById(String id);

    /**
     * 批量删除车辆管理-车辆定检发布
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCVehicleInspectMonthlyByIds(String[] ids);
}
