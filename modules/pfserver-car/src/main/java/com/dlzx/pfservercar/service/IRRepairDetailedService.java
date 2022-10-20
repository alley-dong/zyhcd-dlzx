package com.dlzx.pfservercar.service;

import java.util.List;
import com.dlzx.pfservercar.domain.RRepairDetailed;

/**
 * 维修保养-维修明细Service接口
 * 
 * @author yangluhe
 * @date 2020-12-04
 */
public interface IRRepairDetailedService 
{
    /**
     * 查询维修保养-维修明细
     * 
     * @param id 维修保养-维修明细ID
     * @return 维修保养-维修明细
     */
    public RRepairDetailed selectRRepairDetailedById(String id);

    /**
     * 查询维修保养-维修明细列表
     * 
     * @param rRepairDetailed 维修保养-维修明细
     * @return 维修保养-维修明细集合
     */
    public List<RRepairDetailed> selectRRepairDetailedList(RRepairDetailed rRepairDetailed);

    /**
     * 新增维修保养-维修明细
     * 
     * @param rRepairDetailed 维修保养-维修明细
     * @return 结果
     */
    public int insertRRepairDetailed(RRepairDetailed rRepairDetailed);

    /**
     * 修改维修保养-维修明细
     * 
     * @param rRepairDetailed 维修保养-维修明细
     * @return 结果
     */
    public int updateRRepairDetailed(RRepairDetailed rRepairDetailed);

    /**
     * 批量删除维修保养-维修明细
     * 
     * @param ids 需要删除的维修保养-维修明细ID
     * @return 结果
     */
    public int deleteRRepairDetailedByIds(String[] ids);

    /**
     * 删除维修保养-维修明细信息
     * 
     * @param id 维修保养-维修明细ID
     * @return 结果
     */
    public int deleteRRepairDetailedById(String id);

    /**
     * 审核维修明细
     */
    public List<RRepairDetailed> selectRRepairDetailedNotNewList(String repairId);

    /**
     * 审批新增维修明细
     */
    public List<RRepairDetailed> selectRRepairDetailedIsNewList(String garageId);

    /**
     * 根据garageId查找所有数据
     */
    public List<RRepairDetailed> selectRRepairDetailedAllByGarageId(String garageId);

    /**
     * 新申请维修明细
     */
    public List<RRepairDetailed> selectRRepairDetailedAddListRecord(RRepairDetailed rRepairDetailed);

    /**
     * 查询所有数据联表
     */
    public List<RRepairDetailed> selectCostVehicleList(RRepairDetailed rRepairDetailed);

    /**
     * 计算维修年月次数
     */
    public RRepairDetailed selectMonthNumber(RRepairDetailed rRepairDetailed);
}