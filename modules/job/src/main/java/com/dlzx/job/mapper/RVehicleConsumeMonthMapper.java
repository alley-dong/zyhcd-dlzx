package com.dlzx.job.mapper;

import com.dlzx.job.domain.RVehicleConsumeMonth;

import java.util.List;

/**
 * 统计报-车辆消耗月度统计Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-15
 */
public interface RVehicleConsumeMonthMapper 
{

    public int insertRVehicleConsumeMonthList(List<RVehicleConsumeMonth> list);

    List<RVehicleConsumeMonth> selectRVehicleConsumeMonthList(RVehicleConsumeMonth rVehicleConsumeMonth1);
}
