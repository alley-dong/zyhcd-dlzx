package com.dlzx.pfservercar.service;

import java.util.List;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.pfservercar.domain.CInsuranceVehicle;
import com.dlzx.pfservercar.domain.CVehicleInspect;
import com.dlzx.pfservercar.domain.ReviewSubmitR;

/**
 * 车辆管理-车辆定检记录Service接口
 * 
 * @author dlzx
 * @date 2020-12-08
 */
public interface ICVehicleInspectService 
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
     * 批量删除车辆管理-车辆定检记录
     * 
     * @param ids 需要删除的车辆管理-车辆定检记录ID
     * @return 结果
     */
    public int deleteCVehicleInspectByIds(String[] ids);

    /**
     * 删除车辆管理-车辆定检记录信息
     * 
     * @param id 车辆管理-车辆定检记录ID
     * @return 结果
     */
    public int deleteCVehicleInspectById(String id);

    // 审批
    public AjaxResult checkCInsuranceVehicle(ReviewSubmitR reviewSubmit);

    public CVehicleInspect selectCVehicleInspectByMonthlyItemId(String id);
}
