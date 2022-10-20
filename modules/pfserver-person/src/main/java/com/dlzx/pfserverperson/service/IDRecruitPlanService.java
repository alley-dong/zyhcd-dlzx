package com.dlzx.pfserverperson.service;

import java.util.List;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.pfserverperson.domain.DRecruitPlan;
import com.dlzx.pfserverperson.domain.ReviewSubmit;
import com.dlzx.pfserverperson.domain.commonDomain.SubmmitVo;

/**
 * 驾驶员-招聘计划Service接口
 * 
 * @author yangluhe
 * @date 2020-11-16
 */
public interface IDRecruitPlanService 
{
    /**
     * 查询驾驶员-招聘计划
     * 
     * @param id 驾驶员-招聘计划ID
     * @return 驾驶员-招聘计划
     */
    public DRecruitPlan selectDRecruitPlanById(String id);

    /**
     * 查询驾驶员-招聘计划列表
     * 
     * @param dRecruitPlan 驾驶员-招聘计划
     * @return 驾驶员-招聘计划集合
     */
    public List<DRecruitPlan> selectDRecruitPlanList(DRecruitPlan dRecruitPlan);

    /**
     * 新增驾驶员-招聘计划
     * 
     * @param dRecruitPlan 驾驶员-招聘计划
     * @return 结果
     */
    public int insertDRecruitPlan(DRecruitPlan dRecruitPlan);

    /**
     * 修改驾驶员-招聘计划
     * 
     * @param dRecruitPlan 驾驶员-招聘计划
     * @return 结果
     */
    public int updateDRecruitPlan(DRecruitPlan dRecruitPlan);

    /**
     * 批量删除驾驶员-招聘计划
     * 
     * @param ids 需要删除的驾驶员-招聘计划ID
     * @return 结果
     */
    public int deleteDRecruitPlanByIds(String[] ids);

    /**
     * 删除驾驶员-招聘计划信息
     * 
     * @param id 驾驶员-招聘计划ID
     * @return 结果
     */
    public int deleteDRecruitPlanById(String id);

    /**
     * 审核
     */
    AjaxResult checkDRecruitPlan(ReviewSubmit reviewSubmit);


    /**
     * @param relationIdList
     * @return
     * @describe 根据ids去查询招聘计划列表
     * @author DongCL
     * @date 2021-03-12 13:48
     */
    List<DRecruitPlan> queryRecruitPlanByIds(List<String> relationIdList);
}
