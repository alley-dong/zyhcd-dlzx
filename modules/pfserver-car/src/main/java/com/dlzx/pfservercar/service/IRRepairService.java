package com.dlzx.pfservercar.service;

import java.util.List;
import java.util.Map;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.pfservercar.domain.Common.SubmitVo;
import com.dlzx.pfservercar.domain.RRepair;
import com.dlzx.pfservercar.domain.RRepairOutExcel;
import com.dlzx.pfservercar.domain.ReviewSubmit;
import com.dlzx.pfservercar.domain.ReviewSubmitR;

/**
 * 维修保养-维修保养单Service接口
 * 
 * @author yangluhe
 * @date 2020-12-04
 */
public interface IRRepairService 
{
    /**
     * 查询维修保养-维修保养单
     * 
     * @param id 维修保养-维修保养单ID
     * @return 维修保养-维修保养单
     */
    public RRepair selectRRepairById(String id);

    /**
     * 查询维修保养-维修保养单列表
     * 
     * @param rRepair 维修保养-维修保养单
     * @return 维修保养-维修保养单集合
     */
    public List<RRepair> selectRRepairList(RRepair rRepair);

    /**
     * 新增维修保养-维修保养单
     * 
     * @param rRepair 维修保养-维修保养单
     * @return 结果
     */
    public int insertRRepair(RRepair rRepair);

    /**
     * 修改维修保养-维修保养单
     * 
     * @param rRepair 维修保养-维修保养单
     * @return 结果
     */
    public int updateRRepair(RRepair rRepair);

    /**
     * 修改维修保养-维修保养单
     *
     * @param rRepair 维修保养-维修保养单
     * @return 结果
     */
    public int updateRRepairUpload(RRepair rRepair);

    /**
     * 批量删除维修保养-维修保养单
     * 
     * @param ids 需要删除的维修保养-维修保养单ID
     * @return 结果
     */
    public int deleteRRepairByIds(String[] ids);

    /**
     * 删除维修保养-维修保养单信息
     * 
     * @param id 维修保养-维修保养单ID
     * @return 结果
     */
    public int deleteRRepairById(String id);

    /***
     * 维修订单管理（维修站申请）
     */
    public List<RRepair> selectRRepairDriverPass(RRepair rRepair);

    /**
     * 维修保养管理（维修站申请审批）
     */
    public List<RRepair> selectRRepairStationReview(RRepair rRepair);

    /**
     * 台账
     */
    public List<RRepair> selectRRepairBook(RRepair rRepair);

    /**
     * 维修保养AppList
     */

    public List<RRepair> selectRRepairAppList(RRepair rRepair);

    /**
     * 审核
     */
    public AjaxResult checkRRepair(ReviewSubmitR reviewSubmit);

    /**
     * 维修站审核
     */
    public AjaxResult checkGarageRRepair(ReviewSubmit reviewSubmit);

    /**
     * 新增提交时审批任务表填入数据
     */
    public int submit(SubmitVo submitVo);

    /**
     * 事务新增
     */
    public int insertAffair(RRepair rRepair);

    /**
     * 修改
     */
    int updateAffair(RRepair rRepair);

    /**
     * @describe 维保导出
     * @author DongCL
     * @date 2021-05-06 13:47
     * @param
     * @return
     */
    List<RRepairOutExcel> queryRRepairImport(RRepair rRepair);
}
