package com.dlzx.pfserverparts.service;

import java.util.List;
import java.util.Map;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.pfserverparts.domain.DSalaryDetailedRecords;
import com.dlzx.pfserverparts.domain.ReviewSubmit;
import com.dlzx.pfserverparts.domain.SubmmitVo;

/**
 * 驾驶员-薪酬结算-详细记录Service接口
 * 
 * @author yangluhe
 * @date 2020-12-21
 */
public interface IDSalaryDetailedRecordsService 
{
    /**
     * 查询驾驶员-薪酬结算-详细记录
     * 
     * @param id 驾驶员-薪酬结算-详细记录ID
     * @return 驾驶员-薪酬结算-详细记录
     */
    public DSalaryDetailedRecords selectDSalaryDetailedRecordsById(String id);

    /**
     * 查询驾驶员-薪酬结算-详细记录列表
     * 
     * @param dSalaryDetailedRecords 驾驶员-薪酬结算-详细记录
     * @return 驾驶员-薪酬结算-详细记录集合
     */
    public List<DSalaryDetailedRecords> selectDSalaryDetailedRecordsList(DSalaryDetailedRecords dSalaryDetailedRecords);

    /**
     * 新增驾驶员-薪酬结算-详细记录
     * 
     * @param dSalaryDetailedRecords 驾驶员-薪酬结算-详细记录
     * @return 结果
     */
    public int insertDSalaryDetailedRecords(DSalaryDetailedRecords dSalaryDetailedRecords);

    /**
     * 修改驾驶员-薪酬结算-详细记录
     * 
     * @param dSalaryDetailedRecords 驾驶员-薪酬结算-详细记录
     * @return 结果
     */
    public int updateDSalaryDetailedRecords(DSalaryDetailedRecords dSalaryDetailedRecords);

    /**
     * 批量删除驾驶员-薪酬结算-详细记录
     * 
     * @param ids 需要删除的驾驶员-薪酬结算-详细记录ID
     * @return 结果
     */
    public int deleteDSalaryDetailedRecordsByIds(String[] ids);

    /**
     * 删除驾驶员-薪酬结算-详细记录信息
     * 
     * @param id 驾驶员-薪酬结算-详细记录ID
     * @return 结果
     */
    public int deleteDSalaryDetailedRecordsById(String id);

    /**
     * 工资核算获取短途和长途列表
     * @return
     */
    public AjaxResult selectDSalaryRecordsTypeList();

    /**
     * 查看工资明细单
     * @param id
     * @return
     */
    public Map selectDSalaryDetail(String id);

    /**
     * 编辑保存
     * @param dSalaryDetailedRecords
     * @return
     */
    public int editSaveDetailRecord(DSalaryDetailedRecords dSalaryDetailedRecords);

    /**
     * 撤销功能
     * @param id
     * @return
     */
    public int revoke(String id);

    /**
     * 审核功能
     * @param reviewSubmit
     * @return
     */
    AjaxResult review(ReviewSubmit reviewSubmit);

    int submmit(SubmmitVo submmitVo);

    /**
     * App 列表
     * @param dSalaryDetailedRecords
     * @return
     */
    public List<DSalaryDetailedRecords> selectAppList(DSalaryDetailedRecords dSalaryDetailedRecords);
}
