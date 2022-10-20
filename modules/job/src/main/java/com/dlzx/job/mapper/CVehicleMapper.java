package com.dlzx.job.mapper;

import com.dlzx.job.domain.CVehicle;

import java.util.List;
import java.util.Map;

/**
 * 车辆档案Mapper接口
 * 
 * @author yangluhe
 * @date 2020-11-24
 */
public interface CVehicleMapper 
{
    /**
     * 查询需要定检的车辆id和车牌号
     * @param
     * @return
     */
    public List<CVehicle> selectCVehicleIsInspect();

    /**
     * 查询车辆档案列表
     *
     * @param cVehicle 车辆档案
     * @return 车辆档案集合
     */
    public List<CVehicle> selectCVehicleList(CVehicle cVehicle);
}
