package com.dlzx.pfserverperson.service;

import java.util.List;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.pfserverperson.domain.*;
import com.dlzx.pfserverperson.domain.commonDomain.SubmmitVo;

/**
 * 驾驶员-补助Service接口
 * 
 * @author dlzx
 * @date 2020-12-08
 */
public interface IDSubsidyService 
{
    /**
     * 查询驾驶员-补助
     * 
     * @param id 驾驶员-补助ID
     * @return 驾驶员-补助
     */
    public DSubsidy selectDSubsidyById(String id);

    public DSubsidy selectDSubsidyListTongById(String id);
    /**
     * 查询驾驶员-补助列表
     * 
     * @param dSubsidy 驾驶员-补助
     * @return 驾驶员-补助集合
     */
    public List<DSubsidy> selectDSubsidyList(DSubsidy dSubsidy);

    public List<DSubsidy> selectDSubsidyListTong(DSubsidy dSubsidy);
    /**
     * 新增驾驶员-补助
     * 
     * @param dSubsidy 驾驶员-补助
     * @return 结果
     */
    public int insertDSubsidy(DSubsidy dSubsidy);

    /**
     * 新增补助-事务
     * @param submitVo 审批信息
     * @param list 补助日期
     * @param dSubsidy 补助信息
     * @return
     */
//    public int insertDSubsidyOrDays(SubmmitVo submitVo, List<DSubsidyDay> list,DSubsidy dSubsidy);

    /**
     * 修改驾驶员-补助
     * 
     * @param dSubsidy 驾驶员-补助
     * @return 结果
     */
    public int updateDSubsidy(DSubsidy dSubsidy);

    /**
     * 更新补助-事务
     * @param id 补助记录id
     * @param submitVo 审批信息
     * @param list 补助日期
     * @param dSubsidy 补助信息
     * @return
     */
    /*public int updateDSubsidyOrDays(String id,SubmmitVo submitVo, List<DSubsidyDay> list,DSubsidy dSubsidy);*/

    /**
     * 批量删除驾驶员-补助
     * 
     * @param ids 需要删除的驾驶员-补助ID
     * @return 结果
     */
    public int deleteDSubsidyByIds(String[] ids);

    /**
     * 删除驾驶员-补助信息
     * 
     * @param id 驾驶员-补助ID
     * @return 结果
     */
    public int deleteDSubsidyById(String id);

    /**
     * 审核
     */
    String checkDSubsidy(DSubsidy dSubsidy);

    public int updateDSubsidyStatusById(String id);

    public SysExamineTask sysExamineTaskList(SysExamineTask sysExamineTask);

    /**
     * @describe 分组查询驾驶员的补助记录
     * @author DongCL
     * @date 2021-04-29 10:40
     * @param
     * @return
     */
    List<DSubsidy> queryDriverSubsidyDate(DSubsidy subsidy);

}
