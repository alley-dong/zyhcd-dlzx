package com.dlzx.pfserverperson.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.domain.SysExamineConfigParams;
import com.dlzx.pfserverperson.client.CarClient;
import com.dlzx.pfserverperson.client.SystemClient;
import com.dlzx.pfserverperson.domain.DLeaveType;
import com.dlzx.pfserverperson.domain.PContract;
import com.dlzx.pfserverperson.domain.ReviewSubmit;
import com.dlzx.pfserverperson.domain.SysFile;
import com.dlzx.pfserverperson.mapper.SysDictDataMapper;
import com.dlzx.pfserverperson.mapper.SysFileMapper;
import com.dlzx.pfserverperson.service.IDLeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.DVacationMapper;
import com.dlzx.pfserverperson.domain.DVacation;
import com.dlzx.pfserverperson.service.IDVacationService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 驾驶员-考勤Service业务层处理
 * 
 * @author dcl
 * @date 2020-11-30
 */
@Service
public class DVacationServiceImpl implements IDVacationService 
{
    @Autowired
    private DVacationMapper dVacationMapper;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    @Autowired
    private SysFileMapper sysFileMapper;

    @Autowired
    private SystemClient systemClient;

    @Autowired
    private CarClient carClient;

    @Autowired
    private IDLeaveTypeService idLeaveTypeService;
    /**
     * 查询驾驶员-考勤
     * 
     * @param id 驾驶员-考勤ID
     * @return 驾驶员-考勤
     */
    @Override
    public DVacation selectDVacationById(String id)
    {
        DVacation dVacation = dVacationMapper.selectDVacationById(id);

        List<SysFile> files = sysFileMapper.queryFilesByRelationId(id);
        if (files.size()>0){
            List<String> list = files.stream().map(SysFile::getFilePath).collect(Collectors.toList());
            dVacation.setFileList(list);
        }
        return dVacation;
    }

    /**
     * 查询驾驶员-考勤列表
     * 
     * @param dVacation 驾驶员-考勤
     * @return 驾驶员-考勤
     */
    @Override
    public List<DVacation> selectDVacationList(DVacation dVacation)
    {
        return dVacationMapper.selectDVacationList(dVacation);
    }

    /**
     * 新增驾驶员-考勤
     * 
     * @param dVacation 驾驶员-考勤
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertDVacation(DVacation dVacation)
    {
        try {
            Date nowDate = DateUtils.getNowDate();
            List<SysFile> files = new ArrayList<>();
            if (StringUtils.isNotNull(dVacation.getFileUrls())){
               this.uploadPhoto(files,dVacation,nowDate);
            }
            if (files.size()>0){
                sysFileMapper.insertList(files);
            }
            // 2 请假审批
            int res = systemClient.submitCommon(new SysExamineConfigParams(2L,dVacation.getId(),dVacation.getDriverId(),dVacation.getCreateBy(),dVacation.getSysExamineConfigProcessList()));
            if(res == 0){
                dVacation.setStatus(2L);
            }

            dVacation.setCreateTime(nowDate);
            return dVacationMapper.insertDVacation(dVacation);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 修改驾驶员-考勤
     * 
     * @param dVacation 驾驶员-考勤
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateDVacation(DVacation dVacation)
    {
        try {
            // 根据主键id 去附件表里查询 并删除
            sysFileMapper.delelteFilesByRelationId(dVacation.getId());

            Date nowDate = DateUtils.getNowDate();
            List<SysFile> files = new ArrayList<>();
            if (StringUtils.isNotNull(dVacation.getFileUrls())){
                this.uploadPhoto(files,dVacation,nowDate);
            }
            if (files.size()>0){
                sysFileMapper.insertList(files);
            }
            // 2 请假审批
            int res = systemClient.submitCommon(new SysExamineConfigParams(2L,dVacation.getId(),dVacation.getDriverId(),dVacation.getCreateBy(),dVacation.getSysExamineConfigProcessList()));
            if(res == 0){
                dVacation.setStatus(2L);
            }

            // 2 管理员消息节点
            systemClient.addAdminMsg(2L,dVacation.getId(), "考勤申请",dVacation.getRemarks());

            dVacation.setUpdateTime(nowDate);
            return dVacationMapper.updateDVacation(dVacation);
        } catch (Exception e) {
            throw e;
        }
    }

    private List<SysFile> uploadPhoto(List<SysFile>sysFiles, DVacation dVacation, Date nowDate){
        // 插入附件表
        for (String file : dVacation.getFileUrls()) {
            SysFile sysFile = new SysFile();
            sysFile.setId(UUID.randomUUID()+"");
            sysFile.setFilePath(file);
            sysFile.setRelationId(dVacation.getId());
            sysFile.setCreateUser(dVacation.getCreateBy());
            sysFile.setCreateTime(nowDate);
            // 类型(1,事故照片2,事故其他 3,考勤附件 4,客户合同附件 5供应商合同附件)
            sysFile.setType(3L);
            sysFiles.add(sysFile);
        }
        return sysFiles;
    }
    /**
     * 批量删除驾驶员-考勤
     * 
     * @param ids 需要删除的驾驶员-考勤ID
     * @return 结果
     */
    @Override
    public int deleteDVacationByIds(String[] ids)
    {
        return dVacationMapper.deleteDVacationByIds(ids);
    }

    /**
     * 删除驾驶员-考勤信息
     * 
     * @param id 驾驶员-考勤ID
     * @return 结果
     */
    @Override
    public int deleteDVacationById(String id)
    {
        return dVacationMapper.deleteDVacationById(id);
    }

    /**
     * 考勤审核
     *
     * @param dVacation 驾驶员-考勤ID
     * @return 结果
     */
    @Override
    public AjaxResult checkDVacation(DVacation dVacation) {

        // 审核校验
        ReviewSubmit reviewSubmit = new ReviewSubmit();
        reviewSubmit.setRelationId(dVacation.getId());
        reviewSubmit.setReviewRemarks(dVacation.getReviewRemarks());
        reviewSubmit.setUserId(dVacation.getUserid());
        reviewSubmit.setStatus(dVacation.getStatus());
        Map map = systemClient.reviewCommon(reviewSubmit);
        if (map.get("code").equals(1) || map.get("code").equals(2)){
            dVacation.setStatus(1L);
            return AjaxResult.success(map.get("msg").toString());
        }
        // 已通过
        if (map.get("code").equals(3)){
            dVacation.setStatus(2L);
        }
        // 已驳回
        if (map.get("code").equals(4)){
            dVacation.setStatus(3L);
        }

        // 2  驾驶员消息节点
        carClient.addDriverMsg(2L,dVacation.getId(),dVacation.getDriverId(),"考勤审核结果",dVacation.getRemarks());

        dVacationMapper.checkDVacation(dVacation);
        return AjaxResult.success(map.get("msg").toString());
    }

    /**
     * 请假统计
     *
     * @param dVacation
     * @return 结果
     */
    @Override
    public List<DVacation> queryLeaveStatistics(DVacation dVacation) {
        return dVacationMapper.queryLeaveStatistics(dVacation);
    }

    /**
     * 请假列表（根据驾驶员id查询）
     *
     * @param driverIds 驾驶员id集合
     * @return 结果
     */
    @Override
    public List<DVacation> queryInDriverIds(List<String> driverIds,Date startTime, Date vEndTime) {
        return dVacationMapper.queryInDriverIds(driverIds,startTime,vEndTime);
    }

    /**
     * App请假列表（根据驾驶员对象查询）
     *
     * @param dVacation 驾驶员对象
     * @return 结果
     */
    @Override
    public List<DVacation> queryAppVacationList(DVacation dVacation) {
        return dVacationMapper.queryAppVacationList(dVacation);
    }

    /**
     * 考勤-撤销请假申请接口
     *
     * @param dVacation 驾驶员对象
     * @return 结果
     */
    @Override
    public int undoDVacation(DVacation dVacation) {
        return dVacationMapper.updateDVacation(dVacation);
    }
}
