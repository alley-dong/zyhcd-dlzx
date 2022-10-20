package com.dlzx.pfservercar.service;

import java.util.List;
import com.dlzx.pfservercar.domain.RVehicleConsumeMonth;

/**
 * 统计报-车辆消耗月度统计Service接口
 * 
 * @author dlzx
 * @date 2020-12-15
 */
public interface IRVehicleConsumeMonthService 
{
    /**
     * 查询统计报-车辆消耗月度统计
     * 
     * @param id 统计报-车辆消耗月度统计ID
     * @return 统计报-车辆消耗月度统计
     */
    public RVehicleConsumeMonth selectRVehicleConsumeMonthById(String id);

    /**
     * 查询统计报-车辆消耗月度统计列表
     * 
     * @param rVehicleConsumeMonth 统计报-车辆消耗月度统计
     * @return 统计报-车辆消耗月度统计集合
     */
    public List<RVehicleConsumeMonth> selectRVehicleConsumeMonthList(RVehicleConsumeMonth rVehicleConsumeMonth);

    public List<RVehicleConsumeMonth> selectRVehicleConsumeMonthListAll(RVehicleConsumeMonth rVehicleConsumeMonth);
    /**
     * 新增统计报-车辆消耗月度统计
     * 
     * @param rVehicleConsumeMonth 统计报-车辆消耗月度统计
     * @return 结果
     */
    public int insertRVehicleConsumeMonth(RVehicleConsumeMonth rVehicleConsumeMonth);

    public int insertRVehicleConsumeMonthList(List<RVehicleConsumeMonth> list);
    /**
     * 修改统计报-车辆消耗月度统计
     * 
     * @param rVehicleConsumeMonth 统计报-车辆消耗月度统计
     * @return 结果
     */
    public int updateRVehicleConsumeMonth(RVehicleConsumeMonth rVehicleConsumeMonth);

    /**
     * 批量删除统计报-车辆消耗月度统计
     * 
     * @param ids 需要删除的统计报-车辆消耗月度统计ID
     * @return 结果
     */
    public int deleteRVehicleConsumeMonthByIds(String[] ids);

    /**
     * 删除统计报-车辆消耗月度统计信息
     * 
     * @param id 统计报-车辆消耗月度统计ID
     * @return 结果
     */
    public int deleteRVehicleConsumeMonthById(String id);
}
