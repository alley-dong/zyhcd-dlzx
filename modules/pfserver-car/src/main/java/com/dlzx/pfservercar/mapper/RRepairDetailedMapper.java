package com.dlzx.pfservercar.mapper;

import java.util.List;
import com.dlzx.pfservercar.domain.RRepairDetailed;
import com.dlzx.pfservercar.domain.model.ERPCostModel;

/**
 * 维修保养-维修明细Mapper接口
 * 
 * @author yangluhe
 * @date 2020-12-04
 */
public interface RRepairDetailedMapper 
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
     * 删除维修保养-维修明细
     * 
     * @param id 维修保养-维修明细ID
     * @return 结果
     */
    public int deleteRRepairDetailedById(String id);

    /**
     * 批量删除维修保养-维修明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRRepairDetailedByIds(String[] ids);

    /**
     * 审批维修明细
     */
    public List<RRepairDetailed> selectRRepairDetailedNotNewList(String repairId);

    /**
     * 审批新增的维修明细
     */
    public List<RRepairDetailed> selectRRepairDetailedIsNewList(String repairId);

    /**
     * 根据garageId 查找所有明细
     */
    public List<RRepairDetailed> selectRRepairDetailedAllByGarageId(String repairId);

    /**
     * 新申请维修明细记录
     */
    public List<RRepairDetailed> selectRRepairDetailedAddListRecord(RRepairDetailed rRepairDetailed);

    /**
     * 统计费用检车费列表
     */
    public List<RRepairDetailed> selectCostVehicleList(RRepairDetailed rRepairDetailed);

    /**
     * 计算统计月年维修次数
     */
    public RRepairDetailed selectMonthNumber(RRepairDetailed rRepairDetailed);

    /**
     * @describe 维修保养ERP费用
     * @author DongCL
     * @date 2021-02-21 16:12
     * @param
     * @return
     */
    List<ERPCostModel> queryERPCostVehicleList(ERPCostModel erpCostModel);
}
