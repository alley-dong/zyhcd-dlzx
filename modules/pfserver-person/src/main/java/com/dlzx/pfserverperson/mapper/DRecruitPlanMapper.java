package com.dlzx.pfserverperson.mapper;

import java.util.List;
import com.dlzx.pfserverperson.domain.DRecruitPlan;

/**
 * 驾驶员-招聘计划Mapper接口
 * 
 * @author yangluhe
 * @date 2020-11-16
 */
public interface DRecruitPlanMapper 
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
     * 删除驾驶员-招聘计划
     * 
     * @param id 驾驶员-招聘计划ID
     * @return 结果
     */
    public int deleteDRecruitPlanById(String id);

    /**
     * 批量删除驾驶员-招聘计划
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDRecruitPlanByIds(String[] ids);

    /**
     * @param list
     * @return
     * @describe 根据ids去查询招聘计划列表
     * @author DongCL
     * @date 2021-03-12 13:48
     */
    List<DRecruitPlan> dRecruitPlanMapper(List<String> list);
}
