package com.dlzx.pfserverperson.service.impl;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.domain.SysExamineConfigParams;
import com.dlzx.pfserverperson.client.SystemClient;
import com.dlzx.pfserverperson.domain.*;
import com.dlzx.pfserverperson.domain.commonDomain.SubmmitVo;
import com.dlzx.pfserverperson.mapper.DSubsidyApMapper;
import com.dlzx.pfserverperson.mapper.DSubsidyDayApMapper;
import com.dlzx.pfserverperson.mapper.DSubsidyDayMapper;
import com.dlzx.pfserverperson.mapper.DSubsidyMapper;
import com.dlzx.pfserverperson.service.IDSubsidyApService;
import com.dlzx.pfserverperson.service.IDSubsidyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 驾驶员-补助Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-08
 */
@Service
public class DSubsidyApServiceImpl implements IDSubsidyApService
{
    @Autowired
    private DSubsidyApMapper dSubsidyApMapper;
    @Autowired
    private DSubsidyDayApMapper dSubsidyDayApMapper;
    @Autowired
    private DSubsidyMapper dSubsidyMapper;
    @Autowired
    private SystemClient systemClient;
    /**
     * 查询驾驶员-补助
     * 
     * @param id 驾驶员-补助ID
     * @return 驾驶员-补助
     */
    @Override
    public DSubsidy selectDSubsidyById(String id)
    {
        return dSubsidyMapper.selectDSubsidyById(id);
    }

    @Override
    public DSubsidy selectDSubsidyListTongById(String id)
    {
        return dSubsidyMapper.selectDSubsidyListTongById(id);
    }
    /**
     * 查询驾驶员-补助列表
     * 
     * @param dSubsidy 驾驶员-补助
     * @return 驾驶员-补助
     */
    @Override
    public List<DSubsidy> selectDSubsidyList(DSubsidy dSubsidy)
    {
        return dSubsidyMapper.selectDSubsidyList(dSubsidy);
    }

    @Override
    public List<DSubsidyAp> selectDSubsidyListTong(DSubsidyAp dSubsidy)
    {
        return dSubsidyApMapper.selectDSubsidyListTong(dSubsidy);
    }
    /**
     * 新增驾驶员-补助
     * 
     * @param dSubsidy 驾驶员-补助
     * @return 结果
     */
    @Override
    public int insertDSubsidy(DSubsidyAp dSubsidy)
    {
        dSubsidy.setCreateTime(DateUtils.getNowDate());
        return dSubsidyApMapper.insertDSubsidy(dSubsidy);
    }

    /**
     * 新增补助-事务
     *
     * @param submitVo 审批信息
     * @param list     补助日期
     * @param dSubsidy 补助信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertDSubsidyOrDays(SubmmitVo submitVo, List<DSubsidyDayAp> list, DSubsidyAp dSubsidy) {

        try {

             int result = systemClient.submitCommon(new SysExamineConfigParams(1L,dSubsidy.getId(),dSubsidy.getDriverId(),dSubsidy.getCreateBy(),dSubsidy.getSysExamineConfigProcessList()));
             if (result == 0){
                 dSubsidy.setStatus(2L);
             }

            this.insertDSubsidy(dSubsidy);
            if (list.size() > 0) {
                dSubsidyDayApMapper.insertDSubsidyDayList(list);
            }
        } catch (Exception e) {
            throw e;
        }
        return 1;
    }

    /**
     * 修改驾驶员-补助
     * 
     * @param dSubsidy 驾驶员-补助
     * @return 结果
     */
    @Override
    public int updateDSubsidy(DSubsidyAp dSubsidy)
    {
        dSubsidy.setUpdateTime(DateUtils.getNowDate());
        return dSubsidyApMapper.updateDSubsidy(dSubsidy);
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
    @Override
    @Transactional
    public int updateDSubsidyOrDays(String id, SubmmitVo submitVo, List<DSubsidyDayAp> list, DSubsidyAp dSubsidy) {

        try {
            dSubsidyDayApMapper.deleteDSubsidyDayBySubId(id);
            int result = systemClient.submitCommon(new SysExamineConfigParams(1L,dSubsidy.getId(),dSubsidy.getDriverId(),dSubsidy.getCreateBy(),dSubsidy.getSysExamineConfigProcessList()));
            if (result == 0){
                dSubsidy.setStatus(2L);
            }
            this.updateDSubsidy(dSubsidy);
            if (list.size() > 0) {
                dSubsidyDayApMapper.insertDSubsidyDayList(list);
            }
        } catch (Exception e) {
            throw e;
        }
        return 1;
    }

    /**
     * 批量删除驾驶员-补助
     * 
     * @param ids 需要删除的驾驶员-补助ID
     * @return 结果
     */
    @Override
    public int deleteDSubsidyByIds(String[] ids)
    {
        return dSubsidyMapper.deleteDSubsidyByIds(ids);
    }

    /**
     * 删除驾驶员-补助信息
     * 
     * @param id 驾驶员-补助ID
     * @return 结果
     */
    @Override
    public int deleteDSubsidyById(String id)
    {
        return dSubsidyMapper.deleteDSubsidyById(id);
    }

    @Override
    public int updateDSubsidyStatusById(String id)
    {
        return dSubsidyMapper.updateDSubsidyStatusById(id);
    }

    @Override
    public SysExamineTask sysExamineTaskList(SysExamineTask sysExamineTask){
        return systemClient.sysExamineTaskList(sysExamineTask);
    }
}
