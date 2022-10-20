package com.dlzx.pfserverperson.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.domain.SysExamineConfigParams;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverperson.client.SystemClient;
import com.dlzx.pfserverperson.domain.ReviewSubmit;
import com.dlzx.pfserverperson.domain.commonDomain.SubmmitVo;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DRecruitPlanMapper;
import com.dlzx.pfserverperson.domain.DRecruitPlan;
import com.dlzx.pfserverperson.service.IDRecruitPlanService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 驾驶员-招聘计划Service业务层处理
 * 
 * @author yangluhe
 * @date 2020-11-16
 */
@Service
public class DRecruitPlanServiceImpl implements IDRecruitPlanService 
{
    @Autowired
    private DRecruitPlanMapper dRecruitPlanMapper;
    @Autowired
    private SystemClient systemClient;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询驾驶员-招聘计划
     * 
     * @param id 驾驶员-招聘计划ID
     * @return 驾驶员-招聘计划
     */
    @Override
    public DRecruitPlan selectDRecruitPlanById(String id)
    {
        return dRecruitPlanMapper.selectDRecruitPlanById(id);
    }

    /**
     * 查询驾驶员-招聘计划列表
     * 
     * @param dRecruitPlan 驾驶员-招聘计划
     * @return 驾驶员-招聘计划
     */
    @Override
    public List<DRecruitPlan> selectDRecruitPlanList(DRecruitPlan dRecruitPlan)
    {
        return dRecruitPlanMapper.selectDRecruitPlanList(dRecruitPlan);
    }

    /**
     * 新增驾驶员-招聘计划
     * 
     * @param dRecruitPlan 驾驶员-招聘计划
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertDRecruitPlan(DRecruitPlan dRecruitPlan)
    {
        int result = systemClient.adminSubmitCommon(6L,dRecruitPlan.getId(),null,tokenService.getLoginUser().getSysUser().getNickName());
        if (result == 0){
            dRecruitPlan.setStatus(3L);
        }
        dRecruitPlan.setCreateTime(DateUtils.getNowDate());
        return dRecruitPlanMapper.insertDRecruitPlan(dRecruitPlan);
    }

    /**
     * 修改驾驶员-招聘计划
     * 
     * @param dRecruitPlan 驾驶员-招聘计划
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateDRecruitPlan(DRecruitPlan dRecruitPlan)
    {
        int result = systemClient.adminSubmitCommon(6L,dRecruitPlan.getId(),null,tokenService.getLoginUser().getUsername());
        if (result == 0){
            dRecruitPlan.setStatus(3L);
        }
        dRecruitPlan.setUpdateTime(DateUtils.getNowDate());
        return dRecruitPlanMapper.updateDRecruitPlan(dRecruitPlan);
    }

    /**
     * 批量删除驾驶员-招聘计划
     * 
     * @param ids 需要删除的驾驶员-招聘计划ID
     * @return 结果
     */
    @Override
    public int deleteDRecruitPlanByIds(String[] ids)
    {
        return dRecruitPlanMapper.deleteDRecruitPlanByIds(ids);
    }

    /**
     * 删除驾驶员-招聘计划信息
     * 
     * @param id 驾驶员-招聘计划ID
     * @return 结果
     */
    @Override
    public int deleteDRecruitPlanById(String id)
    {
        return dRecruitPlanMapper.deleteDRecruitPlanById(id);
    }

    /**
     * 审核
     */
    @Override
    public AjaxResult checkDRecruitPlan(ReviewSubmit reviewSubmit){
        //服务调用
        Map map = systemClient.reviewCommon(reviewSubmit);
        int code = (int) map.get("code");
        if (code == 3 || code == 4){
            //获取当前用户
            LoginUser userInfo = tokenService.getLoginUser();
            // 审核校验
            DRecruitPlan dRecruitPlanOne = dRecruitPlanMapper.selectDRecruitPlanById(reviewSubmit.getRelationId());
            dRecruitPlanOne.setStatus(code==3?3L:2L);
            dRecruitPlanOne.setReviewTime(new Date());
            dRecruitPlanOne.setReviewRemarks(reviewSubmit.getReviewRemarks());
            dRecruitPlanOne.setReviewUser(userInfo.getUsername());
            dRecruitPlanOne.setUpdateBy(userInfo.getUsername());
            dRecruitPlanOne.setUpdateTime(new Date());
            int result = dRecruitPlanMapper.updateDRecruitPlan(dRecruitPlanOne);
            if (result<= 0){
                return AjaxResult.error("操作失败");
            }
        }
        //返回msg
        String msg = (String) map.get("msg");
        if (code == 1){
            return AjaxResult.error(msg);
        }
        return AjaxResult.success(msg);
    }

    /**
     * @param relationIdList
     * @return
     * @describe 根据ids去查询招聘计划列表
     * @author DongCL
     * @date 2021-03-12 13:48
     */
    @Override
    public List<DRecruitPlan> queryRecruitPlanByIds(List<String> relationIdList) {
        return dRecruitPlanMapper.dRecruitPlanMapper(relationIdList);
    }
}
