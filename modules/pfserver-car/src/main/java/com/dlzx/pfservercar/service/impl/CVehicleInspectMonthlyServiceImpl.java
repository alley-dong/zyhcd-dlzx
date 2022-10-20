package com.dlzx.pfservercar.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.CVehicleInspectMonthlyMapper;
import com.dlzx.pfservercar.domain.CVehicleInspectMonthly;
import com.dlzx.pfservercar.service.ICVehicleInspectMonthlyService;

/**
 * 车辆管理-车辆定检发布Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-08
 */
@Service
public class CVehicleInspectMonthlyServiceImpl implements ICVehicleInspectMonthlyService 
{
    @Autowired
    private CVehicleInspectMonthlyMapper cVehicleInspectMonthlyMapper;

    /**
     * 查询车辆管理-车辆定检发布
     * 
     * @param id 车辆管理-车辆定检发布ID
     * @return 车辆管理-车辆定检发布
     */
    @Override
    public CVehicleInspectMonthly selectCVehicleInspectMonthlyById(String id)
    {
        return cVehicleInspectMonthlyMapper.selectCVehicleInspectMonthlyById(id);
    }

    /**
     * 查询车辆管理-车辆定检发布列表
     * 
     * @param cVehicleInspectMonthly 车辆管理-车辆定检发布
     * @return 车辆管理-车辆定检发布
     */
    @Override
    public List<CVehicleInspectMonthly> selectCVehicleInspectMonthlyList(CVehicleInspectMonthly cVehicleInspectMonthly)
    {
        return cVehicleInspectMonthlyMapper.selectCVehicleInspectMonthlyList(cVehicleInspectMonthly);
    }

    @Override
    public List<CVehicleInspectMonthly> selectCVehicleInspectMonthlyByInspectDate(String inspectDate) {
        return cVehicleInspectMonthlyMapper.selectCVehicleInspectMonthlyByInspectDate(inspectDate);
    }

    @Override
    public int updateCVehicleInspectMonthlyDate(Date inspectDate) {
        return cVehicleInspectMonthlyMapper.updateCVehicleInspectMonthlyDate(inspectDate);
    }

    @Override
    public int updateCVehicleInspectMonthlyDateNo(Date inspectDate) {
        return cVehicleInspectMonthlyMapper.updateCVehicleInspectMonthlyDateNo(inspectDate);
    }

    /**
     * 新增车辆管理-车辆定检发布
     * 
     * @param cVehicleInspectMonthly 车辆管理-车辆定检发布
     * @return 结果
     */
    @Override
    public int insertCVehicleInspectMonthly(CVehicleInspectMonthly cVehicleInspectMonthly)
    {
        return cVehicleInspectMonthlyMapper.insertCVehicleInspectMonthly(cVehicleInspectMonthly);
    }

    /**
     * 修改车辆管理-车辆定检发布
     * 
     * @param cVehicleInspectMonthly 车辆管理-车辆定检发布
     * @return 结果
     */
    @Override
    public int updateCVehicleInspectMonthly(CVehicleInspectMonthly cVehicleInspectMonthly)
    {
        return cVehicleInspectMonthlyMapper.updateCVehicleInspectMonthly(cVehicleInspectMonthly);
    }

    /**
     * 批量删除车辆管理-车辆定检发布
     * 
     * @param ids 需要删除的车辆管理-车辆定检发布ID
     * @return 结果
     */
    @Override
    public int deleteCVehicleInspectMonthlyByIds(String[] ids)
    {
        return cVehicleInspectMonthlyMapper.deleteCVehicleInspectMonthlyByIds(ids);
    }

    /**
     * 删除车辆管理-车辆定检发布信息
     * 
     * @param id 车辆管理-车辆定检发布ID
     * @return 结果
     */
    @Override
    public int deleteCVehicleInspectMonthlyById(String id)
    {
        return cVehicleInspectMonthlyMapper.deleteCVehicleInspectMonthlyById(id);
    }
}
