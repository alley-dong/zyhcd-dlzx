package com.dlzx.pfserverperson.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverperson.client.SystemClient;
import com.dlzx.pfserverperson.domain.*;
import com.dlzx.pfserverperson.domain.commonDomain.SubmmitVo;
import com.dlzx.pfserverperson.mapper.DSubsidyDayMapper;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DSubsidyMapper;
import com.dlzx.pfserverperson.service.IDSubsidyService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 驾驶员-补助Service业务层处理
 *
 * @author dlzx
 * @date 2020-12-08
 */
@Service
public class DSubsidyServiceImpl implements IDSubsidyService {
    @Autowired
    private DSubsidyMapper dSubsidyMapper;
    @Autowired
    private DSubsidyDayMapper dSubsidyDayMapper;
    @Autowired
    private SystemClient systemClient;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询驾驶员-补助
     *
     * @param id 驾驶员-补助ID
     * @return 驾驶员-补助
     */
    @Override
    public DSubsidy selectDSubsidyById(String id) {
        return dSubsidyMapper.selectDSubsidyById(id);
    }

    @Override
    public DSubsidy selectDSubsidyListTongById(String id) {
        return dSubsidyMapper.selectDSubsidyListTongById(id);
    }

    /**
     * 查询驾驶员-补助列表
     *
     * @param dSubsidy 驾驶员-补助
     * @return 驾驶员-补助
     */
    @Override
    public List<DSubsidy> selectDSubsidyList(DSubsidy dSubsidy) {
        return dSubsidyMapper.selectDSubsidyList(dSubsidy);
    }

    @Override
    public List<DSubsidy> selectDSubsidyListTong(DSubsidy dSubsidy) {
        return dSubsidyMapper.selectDSubsidyListTong(dSubsidy);
    }

    /**
     * 新增驾驶员-补助
     *
     * @param dSubsidy 驾驶员-补助
     * @return 结果
     */
    @Override
    public int insertDSubsidy(DSubsidy dSubsidy) {
        dSubsidy.setCreateTime(DateUtils.getNowDate());
        return dSubsidyMapper.insertDSubsidy(dSubsidy);
    }


    /**
     * 新增补助-事务
     *
     * @param submitVo 审批信息
     * @param list     补助日期
     * @param dSubsidy 补助信息
     * @return
     */
//    @Override
//    @Transactional
//    public int insertDSubsidyOrDays(SubmmitVo submitVo, List<DSubsidyDay> list, DSubsidy dSubsidy) {
//
//        try {
//
//            int result = systemClient.submitCommon(1L,dSubsidy.getId());
//            if (result == 0){
//                dSubsidy.setStatus(2L);
//            }
//
//            this.insertDSubsidy(dSubsidy);
//            if (list.size() > 0) {
//                dSubsidyDayMapper.insertDSubsidyDayList(list);
//            }
//        } catch (Exception e) {
//            throw e;
//        }
//        return 1;
//    }

    /**
     * 修改驾驶员-补助
     *
     * @param dSubsidy 驾驶员-补助
     * @return 结果
     */
    @Override
    public int updateDSubsidy(DSubsidy dSubsidy) {
        dSubsidy.setUpdateTime(DateUtils.getNowDate());
        return dSubsidyMapper.updateDSubsidy(dSubsidy);
    }


    /**
     * 更新补助-事务
     *
     * @param id       补助记录id
     * @param submitVo 审批信息
     * @param list     补助日期
     * @param dSubsidy 补助信息
     * @return
     */
    /*@Override
    @Transactional
    public int updateDSubsidyOrDays(String id, SubmmitVo submitVo, List<DSubsidyDay> list, DSubsidy dSubsidy) {

        try {
            dSubsidyDayMapper.deleteDSubsidyDayBySubId(id);
            int result = systemClient.submitCommon(1L,dSubsidy.getId());
            if (result == 0){
                dSubsidy.setStatus(2L);
            }
            this.updateDSubsidy(dSubsidy);
            if (list.size() > 0) {
                dSubsidyDayMapper.insertDSubsidyDayList(list);
            }
        } catch (Exception e) {
            throw e;
        }
        return 1;
    }*/

    /**
     * 批量删除驾驶员-补助
     *
     * @param ids 需要删除的驾驶员-补助ID
     * @return 结果
     */
    @Override
    public int deleteDSubsidyByIds(String[] ids) {
        return dSubsidyMapper.deleteDSubsidyByIds(ids);
    }

    /**
     * 删除驾驶员-补助信息
     *
     * @param id 驾驶员-补助ID
     * @return 结果
     */
    @Override
    public int deleteDSubsidyById(String id) {
        return dSubsidyMapper.deleteDSubsidyById(id);
    }


    @Override
    public int updateDSubsidyStatusById(String id) {
        return dSubsidyMapper.updateDSubsidyStatusById(id);
    }

    @Override
    public SysExamineTask sysExamineTaskList(SysExamineTask sysExamineTask) {
        return systemClient.sysExamineTaskList(sysExamineTask);
    }

    /**
     * @describe 分组查询驾驶员的补助记录
     * @author DongCL
     * @date 2021-04-29 10:40
     * @param
     * @return
     */
    @Override
    public List<DSubsidy> queryDriverSubsidyDate(DSubsidy subsidy) {
        List<DSubsidy> queryDriverSubsidyDate = dSubsidyMapper.queryDriverSubsidyDate(subsidy);

        return queryDriverSubsidyDate;
    }

    /**
     * 审核
     */
    @Override
    public String checkDSubsidy(DSubsidy dSubsidy) {
        //调用审核方法进行审核
        ReviewSubmit reviewSubmit = new ReviewSubmit();
        reviewSubmit.setRelationId(dSubsidy.getId());
        reviewSubmit.setReviewRemarks(dSubsidy.getReviewRemarks());
        reviewSubmit.setUserId(dSubsidy.getUserId());
        reviewSubmit.setStatus(dSubsidy.getStatus());
        Map map = systemClient.reviewCommon(reviewSubmit);

        int code = (int) map.get("code");
        // 判断审核通过或拒绝
        if (code == 3 || code == 4) {
            DSubsidy dSubsidy1 = dSubsidyMapper.selectDSubsidyById(dSubsidy.getId());

            dSubsidy1.setStatus(code == 3 ? 2L : 3L);
            LoginUser userInfo = tokenService.getLoginUser();
            dSubsidy1.setReviewUser(userInfo.getUsername());
            dSubsidy1.setReviewRemarks(reviewSubmit.getReviewRemarks());
            dSubsidy1.setReviewTime(new Date());
            int result = dSubsidyMapper.updateDSubsidy(dSubsidy1);
            if (result <= 0) {
                return "";
            }
        }

        return (String) map.get("msg");
    }
}
