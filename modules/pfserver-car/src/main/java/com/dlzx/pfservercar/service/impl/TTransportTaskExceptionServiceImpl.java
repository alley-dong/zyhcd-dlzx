package com.dlzx.pfservercar.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.domain.SysExamineConfigParams;
import com.dlzx.pfservercar.client.PersonClient;
import com.dlzx.pfservercar.client.SystemClient;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.DPerformance;
import com.dlzx.pfservercar.domain.DPerformanceRecord;
import com.dlzx.pfservercar.domain.ReviewSubmit;
import com.dlzx.pfservercar.domain.SysFile;
import com.dlzx.pfservercar.domain.model.TTransportTaskExceptionModel;
import com.dlzx.pfservercar.domain.model.TTransportTaskModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskExceptionVm;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.pfservercar.domain.vm.TTransportTaskVm;
import com.dlzx.pfservercar.mapper.CVehicleMapper;
import com.dlzx.pfservercar.mapper.SysFileMapper;
import com.dlzx.pfservercar.mapper.TTransportTaskMapper;
import com.dlzx.pfservercar.service.IDDriverMsgService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfservercar.mapper.TTransportTaskExceptionMapper;
import com.dlzx.pfservercar.domain.TTransportTaskException;
import com.dlzx.pfservercar.service.ITTransportTaskExceptionService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 任务管理-运输任务异常上报Service业务层处理
 * 
 * @author dcl
 * @date 2020-12-18
 */
@Service
public class TTransportTaskExceptionServiceImpl implements ITTransportTaskExceptionService 
{
    @Autowired
    private TTransportTaskExceptionMapper tTransportTaskExceptionMapper;

    @Autowired
    private SysFileMapper sysFileMapper;

    @Autowired
    private SystemClient systemClient;

    @Autowired
    private PersonClient personClient;

    @Autowired
    private IDDriverMsgService idDriverMsgService;

    @Autowired
    private TTransportTaskMapper tTransportTaskMapper;

    @Autowired
    private CVehicleMapper cVehicleMapper;

    /**
     * 查询任务管理-运输任务异常上报
     * 
     * @param id 任务管理-运输任务异常上报ID
     * @return 任务管理-运输任务异常上报
     */
    @Override
    public TTransportTaskExceptionVm selectTTransportTaskExceptionById(String id)
    {
        TTransportTaskExceptionVm tTransportTaskExceptionVm = tTransportTaskExceptionMapper.selectTTransportTaskExceptionById(id);

        // 任务异常情况照片
        List<SysFile> taskExceptionFileList = sysFileMapper.queryFilesByRelationId(id,"14");
        if (taskExceptionFileList.size()>0){
            List<String> list = taskExceptionFileList.stream().map(SysFile::getFilePath).collect(Collectors.toList());
            tTransportTaskExceptionVm.setTaskExceptionFileList(list);
        }
        return tTransportTaskExceptionVm;
    }

    /**
     * 查询任务管理-运输任务异常上报列表
     * 
     * @param tTransportTaskExceptionModel 任务管理-运输任务异常上报
     * @return 任务管理-运输任务异常上报
     */
    @Override
    public List<TTransportTaskExceptionVm> selectTTransportTaskExceptionList(TTransportTaskExceptionModel tTransportTaskExceptionModel)
    {
        return tTransportTaskExceptionMapper.selectTTransportTaskExceptionList(tTransportTaskExceptionModel);
    }

    /**
     * 新增任务管理-运输任务异常上报
     * 
     * @param tTransportTaskExceptionModel 任务管理-运输任务异常上报
     * @return 结果
     */
    @Override
    public int insertTTransportTaskException(TTransportTaskExceptionModel tTransportTaskExceptionModel)
    {
        tTransportTaskExceptionModel.setCreateTime(DateUtils.getNowDate());
        return tTransportTaskExceptionMapper.insertTTransportTaskException(tTransportTaskExceptionModel);
    }

    /**
     * 修改任务管理-运输任务异常上报
     * 
     * @param tTransportTaskExceptionModel 任务管理-运输任务异常上报
     * @return 结果
     */
    @Override
    public int updateTTransportTaskException(TTransportTaskExceptionModel tTransportTaskExceptionModel)
    {
        // 撤回删除审批记录
        systemClient.deleteExamineTaskByRelationId(tTransportTaskExceptionModel.getId());

        tTransportTaskExceptionModel.setUpdateTime(DateUtils.getNowDate());
        return tTransportTaskExceptionMapper.updateTTransportTaskException(tTransportTaskExceptionModel);
    }

    /**
     * 批量删除任务管理-运输任务异常上报
     * 
     * @param ids 需要删除的任务管理-运输任务异常上报ID
     * @return 结果
     */
    @Override
    public int deleteTTransportTaskExceptionByIds(String[] ids)
    {
        return tTransportTaskExceptionMapper.deleteTTransportTaskExceptionByIds(ids);
    }

    /**
     * 删除任务管理-运输任务异常上报信息
     * 
     * @param id 任务管理-运输任务异常上报ID
     * @return 结果
     */
    @Override
    public int deleteTTransportTaskExceptionById(String id)
    {
        return tTransportTaskExceptionMapper.deleteTTransportTaskExceptionById(id);
    }

    /**
     * 任务异常审核
     * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public AjaxResult transportTaskExceptionCheck(TTransportTaskExceptionModel tTransportTaskExceptionModel) {
        // 获取异常任务信息
        TTransportTaskExceptionVm tTransportTaskExceptionVm = tTransportTaskExceptionMapper.selectTTransportTaskExceptionById(tTransportTaskExceptionModel.getId());
        try {
            // 审核校验
            ReviewSubmit reviewSubmit = new ReviewSubmit();
            reviewSubmit.setRelationId(tTransportTaskExceptionModel.getId());
            reviewSubmit.setReviewRemarks(tTransportTaskExceptionModel.getReviewRemarks());
            reviewSubmit.setUserId(tTransportTaskExceptionModel.getUserid());
            reviewSubmit.setStatus(tTransportTaskExceptionModel.getStatus());
            Map map = systemClient.reviewCommon(reviewSubmit);
            if (map.get("code").equals(1) || map.get("code").equals(2)) {
                tTransportTaskExceptionModel.setStatus(1L);
                return AjaxResult.success(map.get("msg").toString());
            }
            // 已通过
            if (map.get("code").equals(3)) {
                tTransportTaskExceptionModel.setStatus(2L);

                // 获取任务信息
                TTransportTaskVm tTransportTaskVm = tTransportTaskMapper.selectTTransportTaskById(tTransportTaskExceptionVm.getTaskId());
                if (StringUtils.isNotNull(tTransportTaskVm)) {

                    // 如果是任务异常则更新任务表的异常字段
                    if (tTransportTaskExceptionVm.getType()==2){
                        tTransportTaskVm.setIsException(1L);
                        TTransportTaskModel tTransportTaskModel = new TTransportTaskModel();
                        BeanUtils.copyProperties(tTransportTaskVm,tTransportTaskModel);
                        tTransportTaskMapper.updateTTransportTask(tTransportTaskModel);

                        // 更新长途绩效记录的任务异常状态
                        DPerformanceRecord dPerformanceRecord = personClient.queryPerformanceById(tTransportTaskVm.getId());
                        if (dPerformanceRecord != null) {
                            dPerformanceRecord.setTaskStatus(1L); // 异常
                            personClient.updatePerformanceRecord(dPerformanceRecord);
                        }
                    }
                }
            }
            // 已驳回
            if (map.get("code").equals(4)) {
                tTransportTaskExceptionModel.setStatus(3L);
            }

            tTransportTaskExceptionMapper.checkTaskException(tTransportTaskExceptionModel);
            return AjaxResult.success(map.get("msg").toString());
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 运输任务异常上报
     * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int submitException(TTransportTaskExceptionModel tTransportTaskExceptionModel) {
        try {
            Date nowDate = DateUtils.getNowDate();
            List<SysFile> files = new ArrayList<>();
            if (StringUtils.isNotNull(tTransportTaskExceptionModel.getFileUrls())){
                this.uploadPhoto(files,tTransportTaskExceptionModel,nowDate);
            }
            if (files.size()>0){
                sysFileMapper.insertList(files);
            }
            // 9 任务异常审批
            int res = systemClient.submitCommon(new SysExamineConfigParams(9L,tTransportTaskExceptionModel.getId(),tTransportTaskExceptionModel.getDriverId(),tTransportTaskExceptionModel.getDriverName(),tTransportTaskExceptionModel.getSysExamineConfigProcessList()));
            if(res == 0){
                tTransportTaskExceptionModel.setStatus(2L);
            }
            tTransportTaskExceptionModel.setCreateTime(nowDate);

            TTransportTaskVm tTransportTaskVm = tTransportTaskMapper.selectTTransportTaskById(tTransportTaskExceptionModel.getTaskId());
            // 线路
            tTransportTaskExceptionModel.setRouteId(tTransportTaskVm.getRouteId());
            tTransportTaskExceptionModel.setRouteName(tTransportTaskVm.getRouteName());

            tTransportTaskExceptionModel.setCreateTime(new Date());
            return tTransportTaskExceptionMapper.insertTTransportTaskException(tTransportTaskExceptionModel);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 运输任务异常再次上报
     * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int resubmitTaskException(TTransportTaskExceptionModel tTransportTaskExceptionModel) {
        try {
            // 根据主键id 去附件表里查询 并删除
            sysFileMapper.delelteFilesByRelationId(tTransportTaskExceptionModel.getId());

            Date nowDate = DateUtils.getNowDate();
            List<SysFile> sysFiles = new ArrayList<>();
            if (StringUtils.isNotNull(tTransportTaskExceptionModel.getFileUrls())){
                this.uploadPhoto(sysFiles,tTransportTaskExceptionModel,nowDate);
            }
            if (sysFiles.size()>0){
                sysFileMapper.insertList(sysFiles);
            }

            // 9 任务异常审批
            int res = systemClient.submitCommon(new SysExamineConfigParams(9L,tTransportTaskExceptionModel.getId(),tTransportTaskExceptionModel.getDriverId(),tTransportTaskExceptionModel.getDriverName(),tTransportTaskExceptionModel.getSysExamineConfigProcessList()));
            if(res == 0){
                tTransportTaskExceptionModel.setStatus(2L);
            }

            // 6 管理员消息节点
            systemClient.addAdminMsg(6L,tTransportTaskExceptionModel.getId(), "运输任务异常上报",tTransportTaskExceptionModel.getRemarks());

            tTransportTaskExceptionModel.setUpdateTime(nowDate);
            return tTransportTaskExceptionMapper.updateTTransportTaskException(tTransportTaskExceptionModel);
        } catch (Exception e) {
            throw e;
        }
    }

    private List<SysFile> uploadPhoto(List<SysFile>sysFiles, TTransportTaskExceptionModel tTransportTaskInspectModel, Date nowDate){
        // 插入附件表
        for (String file : tTransportTaskInspectModel.getFileUrls()) {
            SysFile sysFile = new SysFile();
            sysFile.setId(UUID.randomUUID()+"");
            sysFile.setFilePath(file);
            sysFile.setRelationId(tTransportTaskInspectModel.getId());
            sysFile.setCreateUser(tTransportTaskInspectModel.getCreateBy());
            sysFile.setCreateTime(nowDate);
            // 类型(1,事故照片2,事故其他 3,考勤附件 4,客户合同附件 5供应商合同附件)
            sysFile.setType(14L);
            sysFiles.add(sysFile);
        }
        return sysFiles;
    }
}
