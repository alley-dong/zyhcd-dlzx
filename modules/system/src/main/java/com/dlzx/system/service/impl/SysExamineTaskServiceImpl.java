package com.dlzx.system.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.domain.SysExamineConfigParams;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.redis.service.RedisService;
import com.dlzx.common.security.service.DriverTokenService;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.system.api.domain.SysUser;
import com.dlzx.system.api.model.LoginDriver;
import com.dlzx.system.api.model.LoginUser;
import com.dlzx.system.client.CarClient;
import com.dlzx.system.client.PersonClient;
import com.dlzx.system.domain.AppTokenUpdate;
import com.dlzx.system.domain.DDriver;
import com.dlzx.system.domain.DRecruitPlan;
import com.dlzx.system.domain.ReviewSubmit;
import com.dlzx.system.domain.SysExamineConfig;
import com.dlzx.system.domain.SysExamineConfigProcess;
import com.dlzx.system.mapper.SysDictDataMapper;
import com.dlzx.system.mapper.SysExamineConfigMapper;
import com.dlzx.system.mapper.SysExamineConfigProcessMapper;
import com.dlzx.system.mapper.SysUserMapper;
import com.dlzx.system.service.impl.PushMessage.PushMessage;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.system.mapper.SysExamineTaskMapper;
import com.dlzx.system.domain.SysExamineTask;
import com.dlzx.system.service.ISysExamineTaskService;
import org.springframework.transaction.annotation.Transactional;
import com.dlzx.common.core.web.domain.AjaxResult;

/**
 * 系统管理-审批任务Service业务层处理
 * 
 * @author yangluhe
 * @date 2020-12-11
 */
@Service
public class SysExamineTaskServiceImpl implements ISysExamineTaskService 
{
    @Autowired
    private SysExamineTaskMapper sysExamineTaskMapper;
    @Autowired
    private SysExamineConfigProcessMapper sysExamineConfigProcessMapper;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private DriverTokenService driverTokenService;
    @Autowired
    private SysDictDataMapper sysDictDataMapper;
    @Autowired
    private AdministratorMsgServiceImpl administratorMsgService;
    @Autowired
    private CarClient carClient;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private PersonClient personClient;
    @Autowired
    private SysExamineConfigMapper sysExamineConfigMapper;

    /**
     * 查询系统管理-审批任务
     * 
     * @param id 系统管理-审批任务ID
     * @return 系统管理-审批任务
     */
    @Override
    public SysExamineTask selectSysExamineTaskById(String id)
    {
        SysExamineTask sysExamineTask = sysExamineTaskMapper.selectSysExamineTaskById(id);
        sysExamineTask.setTypeEnum(sysDictDataMapper.selectDictLabel("sys_examine_config_type",sysExamineTask.getType()+""));
        return sysExamineTask;
    }

    /**
     * 查询系统管理-审批任务列表
     * 
     * @param sysExamineTask 系统管理-审批任务
     * @return 系统管理-审批任务
     */
    @Override
    public List<SysExamineTask> selectSysExamineTaskList(SysExamineTask sysExamineTask)
    {
        return sysExamineTaskMapper.selectSysExamineTaskList(sysExamineTask);
    }

    /**
     * 新增系统管理-审批任务
     * 
     * @param sysExamineTask 系统管理-审批任务
     * @return 结果
     */
    @Override
    public int insertSysExamineTask(SysExamineTask sysExamineTask)
    {
        sysExamineTask.setCreateTime(DateUtils.getNowDate());
        return sysExamineTaskMapper.insertSysExamineTask(sysExamineTask);
    }

    /**
     * 修改系统管理-审批任务
     * 
     * @param sysExamineTask 系统管理-审批任务
     * @return 结果
     */
    @Override
    public int updateSysExamineTask(SysExamineTask sysExamineTask)
    {
        sysExamineTask.setUpdateTime(DateUtils.getNowDate());
        return sysExamineTaskMapper.updateSysExamineTask(sysExamineTask);
    }

    /**
     * 批量删除系统管理-审批任务
     * 
     * @param ids 需要删除的系统管理-审批任务ID
     * @return 结果
     */
    @Override
    public int deleteSysExamineTaskByIds(String[] ids)
    {
        return sysExamineTaskMapper.deleteSysExamineTaskByIds(ids);
    }

    /**
     * 删除系统管理-审批任务信息
     * 
     * @param id 系统管理-审批任务ID
     * @return 结果
     */
    @Override
    public int deleteSysExamineTaskById(String id)
    {
        return sysExamineTaskMapper.deleteSysExamineTaskById(id);
    }

    @Override
    public SysExamineTask querySysExamineTaskList(SysExamineTask sysExamineTask) {
        List<SysExamineTask> sysExamineTasks = sysExamineTaskMapper.selectSysExamineTaskList(sysExamineTask);

        // 此处的loginType 是驾驶员端的标示，传则代表是驾驶员
        if (sysExamineTask.getLoginType()!= null && sysExamineTask.getLoginType()==1){
            List<SysExamineTask> sysExamineTaskList = sysExamineTasks.stream().filter(s -> s.getUserType() == 1L).sorted(Comparator.comparing(SysExamineTask::getLevel)).collect(Collectors.toList());
            for (SysExamineTask examineTask : sysExamineTaskList) {
                examineTask.setCcUserName(sysExamineTasks.stream().filter(s->s.getLevel().equals(examineTask.getLevel())&& 2==s.getUserType()).findFirst().orElse(new SysExamineTask()).getUserName());
            }
            sysExamineTask.setSysExamineUserTaskList(sysExamineTaskList);
        }else {
            // 审批人列表
            sysExamineTask.setSysExamineUserTaskList(sysExamineTasks.stream().filter(s->s.getUserType()==1L).sorted(Comparator.comparing(SysExamineTask::getLevel)).collect(Collectors.toList()));
            // 抄送人列表
            sysExamineTask.setSysDuplicateUserTaskList(sysExamineTasks.stream().filter(s->s.getUserType()==2L).sorted(Comparator.comparing(SysExamineTask::getLevel)).collect(Collectors.toList()));
        }
        return sysExamineTask;
    }

    /**
     * 驾驶员审批插入数据
     */
    @Override
    public int selectTaskList(SysExamineConfigParams sysExamineConfigParams){
        List<SysExamineTask> sysExamineTaskListCheck = sysExamineTaskMapper.checkExistenceStatus(sysExamineConfigParams.getRelationId(),sysExamineConfigParams.getType());
        //判断是否再次提交
        if (sysExamineTaskListCheck.size()!=0){
            sysExamineTaskMapper.updateSysExamineTaskBatch(sysExamineConfigParams.getRelationId());
        }
        // 审批流程记录
        List<SysExamineTask> sysExamineTaskList = new ArrayList<>();

        //todo 这里就不从库里查询了，让前端传就行了
//        List<SysExamineConfigProcess> sysExamineConfigProcessList = sysExamineConfigProcessMapper.selectSysExamineConfigProcessByType(type);
        List<SysExamineConfigProcess> sysExamineConfigProcessList = new ArrayList<>();
        for (com.dlzx.common.core.web.domain.SysExamineConfigProcess sysExamineConfigProcess : sysExamineConfigParams.getSysExamineConfigProcessList()) {
            SysExamineConfigProcess sysExamineConfig = new SysExamineConfigProcess();
            BeanUtils.copyProperties(sysExamineConfigProcess,sysExamineConfig);
            sysExamineConfigProcessList.add(sysExamineConfig);
        }

        for (SysExamineConfigProcess sysExamineConfigProcess : sysExamineConfigProcessList) {
            SysExamineTask sysExamineTask = new SysExamineTask();
            SysExamineTask sysExamineCTask = new SysExamineTask();
            //填入任务表中审核人的记录
            if (sysExamineConfigProcess.getLevel() == 1) {
                sysExamineTask.setTaskStatus(1L);
                sysExamineCTask.setTaskStatus(1L);
            } else {
                sysExamineTask.setTaskStatus(2L);
                sysExamineCTask.setTaskStatus(2L);
            }
            sysExamineTask.setId(UUID.randomUUID().toString());
            sysExamineTask.setType(sysExamineConfigParams.getType());
            sysExamineTask.setDeleteFlag(1L);
            sysExamineTask.setRelationId(sysExamineConfigParams.getRelationId());
            sysExamineTask.setRoleId(sysExamineConfigProcess.getRoleId());
            sysExamineTask.setRoleName(sysExamineConfigProcess.getRoleName());
            sysExamineTask.setLevel(sysExamineConfigProcess.getLevel());
            sysExamineTask.setUserId(sysExamineConfigProcess.getUserId());
            sysExamineTask.setUserName(sysExamineConfigProcess.getUserName());
            sysExamineTask.setUserType(1L);
            sysExamineTask.setCreateTime(DateUtils.getNowDate());
            sysExamineTask.setCreateBy(sysExamineConfigParams.getName());
            sysExamineTask.setStatus(1L);
            //填入任务表中抄送人的记录
            sysExamineTaskList.add(sysExamineTask);
            if (sysExamineConfigProcess.getCcRoleId() != null && sysExamineConfigProcess.getCcUserId() != null) {
                sysExamineCTask.setLevel(sysExamineConfigProcess.getLevel());
                sysExamineCTask.setDeleteFlag(1L);
                sysExamineCTask.setRelationId(sysExamineConfigParams.getRelationId());
                sysExamineCTask.setType(sysExamineConfigParams.getType());
                sysExamineCTask.setUserType(2L);
                sysExamineCTask.setId(UUID.randomUUID().toString());
                sysExamineCTask.setUserId(sysExamineConfigProcess.getCcUserId());
                sysExamineCTask.setUserName(sysExamineConfigProcess.getCcUserName());
                sysExamineCTask.setRoleId(sysExamineConfigProcess.getCcRoleId());
                sysExamineCTask.setRoleName(sysExamineConfigProcess.getCcRoleName());
                sysExamineCTask.setCreateTime(DateUtils.getNowDate());
                sysExamineCTask.setCreateBy(sysExamineConfigParams.getName());
                sysExamineCTask.setStatus(1L);
                sysExamineTaskList.add(sysExamineCTask);

                // 管理员消息节点（抄送）
                if (sysExamineConfigProcess.getLevel()==1L){
                    addAdminNotice(sysExamineConfigParams.getType(), sysExamineConfigParams.getRelationId(),"",sysExamineConfigProcess.getCcUserId().toString());
                }
            }
            // 管理员消息节点（审批）
            if (sysExamineConfigProcess.getLevel()==1L){
                addAdminNotice(sysExamineConfigParams.getType(), sysExamineConfigParams.getRelationId(),"",sysExamineConfigProcess.getUserId().toString());
            }
        }
        if (sysExamineTaskList.size() > 0) {
           int i = sysExamineTaskMapper.insertSysExamineTaskBatch(sysExamineTaskList);
            if (i>0){
                return 1;//新增成功
            }
            else {
                return 2;//新增失败
            }
        }
        else {
            return 0;//无审批人可直接审批通过
        }
    }



    /**
     * 后台审批插入数据
     */
    @Override
    public int selectAdminTaskList(Long type,String relationId,String driverId,String name){
        List<SysExamineTask> sysExamineTaskListCheck = sysExamineTaskMapper.checkExistenceStatus(relationId,type);
        //判断是否再次提交
        if (sysExamineTaskListCheck.size()!=0){
            sysExamineTaskMapper.updateSysExamineTaskBatch(relationId);
        }
        // 审批流程记录
        List<SysExamineTask> sysExamineTaskList = new ArrayList<>();

        List<SysExamineConfigProcess> sysExamineConfigProcessList = sysExamineConfigProcessMapper.selectSysExamineConfigProcessByType(type);
        for (SysExamineConfigProcess sysExamineConfigProcess : sysExamineConfigProcessList) {
            SysExamineTask sysExamineTask = new SysExamineTask();
            SysExamineTask sysExamineCTask = new SysExamineTask();
            //填入任务表中审核人的记录
            if (sysExamineConfigProcess.getLevel() == 1) {
                sysExamineTask.setTaskStatus(1L);
                sysExamineCTask.setTaskStatus(1L);
            } else {
                sysExamineTask.setTaskStatus(2L);
                sysExamineCTask.setTaskStatus(2L);
            }
            sysExamineTask.setId(UUID.randomUUID().toString());
            sysExamineTask.setType(type);
            sysExamineTask.setDeleteFlag(1L);
            sysExamineTask.setRelationId(relationId);
            sysExamineTask.setRoleId(sysExamineConfigProcess.getRoleId());
            sysExamineTask.setRoleName(sysExamineConfigProcess.getRoleName());
            sysExamineTask.setLevel(sysExamineConfigProcess.getLevel());
            sysExamineTask.setUserId(sysExamineConfigProcess.getUserId());
            sysExamineTask.setUserName(sysExamineConfigProcess.getUserName());
            sysExamineTask.setUserType(1L);
            sysExamineTask.setCreateTime(DateUtils.getNowDate());
            sysExamineTask.setCreateBy(name);
            sysExamineTask.setStatus(1L);
            //填入任务表中抄送人的记录
            sysExamineTaskList.add(sysExamineTask);
            if (sysExamineConfigProcess.getCcRoleId() != null && sysExamineConfigProcess.getCcUserId() != null) {
                sysExamineCTask.setLevel(sysExamineConfigProcess.getLevel());
                sysExamineCTask.setDeleteFlag(1L);
                sysExamineCTask.setRelationId(relationId);
                sysExamineCTask.setType(type);
                sysExamineCTask.setUserType(2L);
                sysExamineCTask.setId(UUID.randomUUID().toString());
                sysExamineCTask.setUserId(sysExamineConfigProcess.getCcUserId());
                sysExamineCTask.setUserName(sysExamineConfigProcess.getCcUserName());
                sysExamineCTask.setRoleId(sysExamineConfigProcess.getCcRoleId());
                sysExamineCTask.setRoleName(sysExamineConfigProcess.getCcRoleName());
                sysExamineCTask.setCreateTime(DateUtils.getNowDate());
                sysExamineCTask.setCreateBy(name);
                sysExamineCTask.setStatus(1L);
                sysExamineTaskList.add(sysExamineCTask);

                // 管理员消息节点（抄送）
                if (sysExamineConfigProcess.getLevel()==1L){
                    addAdminNotice(type, relationId,"",sysExamineConfigProcess.getCcUserId().toString());
                }
            }
            // 管理员消息节点（审批）
            if (sysExamineConfigProcess.getLevel()==1L){
                addAdminNotice(type, relationId,"",sysExamineConfigProcess.getUserId().toString());
            }
        }
        if (sysExamineTaskList.size() > 0) {
            int i = sysExamineTaskMapper.insertSysExamineTaskBatch(sysExamineTaskList);
            if (i>0){
                return 1;//新增成功
            }
            else {
                return 2;//新增失败
            }
        }
        else {
            return 0;//无审批人可直接审批通过
        }
    }



    private void addAdminNotice(Long type, String relationId,String content,String userId) {
        // 管理员消息节点
        String title = sysDictDataMapper.selectDictLabel("sys_examine_config_type", type.toString());
        administratorMsgService.insertMessagePort(type, relationId, title, content,userId);
    }

    private void addDriverNotice(Long type, String relationId,String driverId,String content) {
        // 驾驶员消息节点
        String title = sysDictDataMapper.selectDictLabel("sys_examine_config_type", type.toString());
        carClient.addDriverMsg(type, relationId, driverId,title, content);
//        try {
//            pushMessage.testAdvancedPush(title,content,driverId,2);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public SysExamineTask selectSysExamineTaskReview(String relationId,String userId){
        return sysExamineTaskMapper.selectSysExamineTaskReview(relationId,userId);
    }

    @Override
    public SysExamineTask selectSysExamineTaskReviewNextLevel(String relationId,Long level,String userId){
        return sysExamineTaskMapper.selectSysExamineTaskReviewNextLevel(relationId,level,userId);
    }
    /**
     * 删除审批记录
     * */
    @Override
    public int deleteExamineTaskByRelationId(String relationId) {
        return sysExamineTaskMapper.deleteExamineTaskByRelationId(relationId);
    }

    /**
     * 管理员app-审批待办列表
     * */
    @Override
    public List<SysExamineTask> querySysExamineTaskBacklogList(SysExamineTask sysExamineTask) {

        List<SysExamineTask> sysExamineTasks = sysExamineTaskMapper.querySysExamineTaskBacklogList(sysExamineTask);

        // 过滤出薪酬审批
        sysExamineTasks = sysExamineTasks.stream().filter(s->s.getType()!= 10).collect(Collectors.toList());

        // 剔除被删除的数据
        queryPlanDelData(sysExamineTasks);

        return sysExamineTasks;
    }

    private void queryPlanDelData(List<SysExamineTask> sysExamineTasks) {
        List<String> relationIdList = sysExamineTasks.stream()
                .filter(sys -> sys.getType() == 6)
                .map(SysExamineTask::getRelationId)
                .collect(Collectors.toList());

        // 去拿relationIdList查询已被删除的招聘计划列表，取到被删除的列表把 sysExamineTasks中被删除的踢掉
        List<DRecruitPlan> dRecruitPlans = personClient.queryRecruitPlanByIds(relationIdList);
        if (dRecruitPlans.size()>0){
            List<SysExamineTask> removeList = new ArrayList<>();
            for (DRecruitPlan dRecruitPlan : dRecruitPlans) {
                removeList.addAll(sysExamineTasks.stream()
                        .filter(sysExamineTask -> dRecruitPlan.getId().equals(sysExamineTask.getRelationId())).collect(Collectors.toList()));
            }
            sysExamineTasks.removeAll(removeList);
        }
    }

    /**
     * 管理员app-审批记录
     */
    @Override
    public List<SysExamineTask> queryExamineList(SysExamineTask sysExamineTask) {
        return sysExamineTaskMapper.queryExamineList(sysExamineTask);
    }

    /**
     * 管理员app-抄送记录
     */
    @Override
    public List<SysExamineTask> querySendDuplicateList(SysExamineTask sysExamineTask) {
        List<SysExamineTask> sysExamineTasks = sysExamineTaskMapper.querySendDuplicateList(sysExamineTask);

        // 剔除被删除的数据
        queryPlanDelData(sysExamineTasks);

        return sysExamineTasks;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, Object> reviewCommon(ReviewSubmit reviewSubmit) {
        Map<String,Object> map = new HashMap<>();
        try {
            SysExamineTask sysExamineTask = this.selectSysExamineTaskReview(reviewSubmit.getRelationId(),reviewSubmit.getUserId());
            if (sysExamineTask == null){
                map.put("code",1);
                map.put("msg","您没有权限进行操作");
                return map;
            }
            sysExamineTask.setTaskStatus(2L);
            if (reviewSubmit.getStatus() == 1){
                sysExamineTask.setStatus(2L);
            }
            else {
                // 修改审批人/抄送人的审批状态（已拒绝）
                SysExamineTask sysExamineTaskOne = sysExamineTaskMapper.selectSysExamineTaskReview(sysExamineTask.getRelationId(),reviewSubmit.getUserId());
                sysExamineTaskOne.setRelationId(sysExamineTask.getRelationId());
                sysExamineTaskOne.setStatus(3L);
                sysExamineTaskOne.setTaskStatus(2L);
                sysExamineTaskOne.setReviewTime(new Date());
                sysExamineTaskOne.setReviewRemarks(reviewSubmit.getReviewRemarks());
                this.updateSysExamineTask(sysExamineTaskOne);
                SysExamineTask sysExamineTaskCopy = sysExamineTaskMapper.selectSysExamineTaskCopy(sysExamineTask.getRelationId(),reviewSubmit.getLevel(),sysExamineTask.getType());
                if (sysExamineTaskCopy != null) {
                    sysExamineTaskCopy.setRelationId(sysExamineTask.getRelationId());
                    sysExamineTaskCopy.setStatus(3L);
                    sysExamineTaskCopy.setTaskStatus(2L);
                    sysExamineTaskCopy.setReviewTime(new Date());
                    sysExamineTaskCopy.setReviewRemarks(reviewSubmit.getReviewRemarks());
                    this.updateSysExamineTask(sysExamineTaskCopy);
                }
                // 驾驶员消息节点
                if (StringUtils.isNotEmpty(sysExamineTaskOne.getCreateBy())) {
                    DDriver dDriver = personClient.DriverId(sysExamineTaskOne.getCreateBy());
                    if (dDriver != null) {
                        if (StringUtils.isNotEmpty(dDriver.getId())) {
                            addDriverNotice(sysExamineTask.getType(), sysExamineTaskOne.getRelationId(), dDriver.getId(), "");
                        }
                    }
                }
                map.put("code",4);
                map.put("msg","您的审批被拒绝");
                return map;
            }

            // 修改审批人/抄送人的审批状态 (已通过)
            SysExamineTask sysExamineTaskOne = new SysExamineTask();
            sysExamineTaskOne.setRelationId(sysExamineTask.getRelationId());
            sysExamineTaskOne.setTaskStatus(2L);
            sysExamineTaskOne.setStatus(2L);
            sysExamineTaskOne.setReviewTime(new Date());
            sysExamineTaskOne.setId(sysExamineTask.getId());
            sysExamineTaskOne.setReviewRemarks(reviewSubmit.getReviewRemarks());
            this.updateSysExamineTask(sysExamineTaskOne);
            SysExamineTask sysExamineTaskCopy = sysExamineTaskMapper.selectSysExamineTaskCopy(sysExamineTask.getRelationId(),sysExamineTask.getLevel(),sysExamineTask.getType());
            if (sysExamineTaskCopy != null){
            sysExamineTaskCopy.setRelationId(sysExamineTask.getRelationId());
            sysExamineTaskCopy.setStatus(2L);
            sysExamineTaskCopy.setTaskStatus(2L);
            sysExamineTaskCopy.setReviewTime(new Date());
            sysExamineTaskCopy.setReviewRemarks(reviewSubmit.getReviewRemarks());
            this.updateSysExamineTask(sysExamineTaskCopy);
            }
            Long i = sysExamineTask.getLevel();
            int next = i.intValue();
            next = next+1;
            Long nextLevel = new Long((long)next);
            SysExamineTask sysExamineTaskNext = this.selectSysExamineTaskReviewNextLevel(reviewSubmit.getRelationId(),nextLevel,reviewSubmit.getUserId());
            if (sysExamineTaskNext == null){
                map.put("code",3);
                map.put("msg","审核通过");
                if (StringUtils.isNotEmpty(sysExamineTask.getCreateBy())) {
                    // 驾驶员消息节点
                    DDriver dDriver = personClient.DriverId(sysExamineTask.getCreateBy());
                    if (dDriver != null) {
                        if (StringUtils.isNotEmpty(dDriver.getId())) {
                            addDriverNotice(sysExamineTask.getType(), sysExamineTaskOne.getRelationId(), dDriver.getId(), "");
                        }
                    }
                }
                return map;
            } else {
                // 审批人和抄送人的消息节点插入
                // 管理员消息节点（抄送）
                SysExamineTask cCUser = sysExamineTaskMapper.selectSysExamineTaskCopy(sysExamineTask.getRelationId(), nextLevel, sysExamineTask.getType());
                if (cCUser != null && cCUser.getUserId() != null) {
                    addAdminNotice(sysExamineTask.getType(), reviewSubmit.getRelationId(), "", cCUser.getUserId().toString());
                }
                // 管理员消息节点（审批）
                if (sysExamineTaskNext.getUserId() != null) {
                    addAdminNotice(sysExamineTask.getType(), reviewSubmit.getRelationId(), "", sysExamineTaskNext.getUserId().toString());
                }
                sysExamineTaskNext.setTaskStatus(1L);
                this.updateSysExamineTask(sysExamineTaskNext);
                map.put("code",2);
                map.put("msg","审批流程进行中");
                return map;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public int updateAppToken(AppTokenUpdate appTokenUpdate){
        //LoginDriver loginDriver = driverTokenService.getLoginDriver();
        LoginUser loginUser = tokenService.getLoginUser();
        if (loginUser != null){
            SysUser sysUser = loginUser.getSysUser();
            System.out.println("进入更新后台admin获取token方法体==================="+ appTokenUpdate.getAppToken());
            System.out.println("进入更新后台admin获取type方法体==================="+ appTokenUpdate.getDeviceType());
            sysUser.setToken(appTokenUpdate.getAppToken());
            sysUser.setDeviceType(appTokenUpdate.getDeviceType());
           return sysUserMapper.updateUser(sysUser);
        }
        else {
            System.out.println("进入更新用户token方法体==================="+ appTokenUpdate.getAppToken());
            LoginDriver loginDriver = driverTokenService.getLoginDriver();
            String id = loginDriver.getUserid();
            DDriver dDriver = personClient.DriverId(id);
            dDriver.setToken(appTokenUpdate.getAppToken());
            dDriver.setDeviceType(appTokenUpdate.getDeviceType());
            AjaxResult ajaxResult = personClient.A10EditSave(dDriver);
            if (ajaxResult.get("code").toString().equals("200")){
                return 1;
            }
            else {
                return 0;
            }
        }
    }

    /**
     * @describe 根据用户ID || 主键ID查询
     * @author DongCL
     * @date 2021-02-24 09:33
     * @param
     * @return
     */
    @Override
    public int updateSysExamineTaskReadStatus(SysExamineTask sysExamineTask) {
        sysExamineTask.setUpdateTime(DateUtils.getNowDate());
        return sysExamineTaskMapper.updateSysExamineTaskReadStatus(sysExamineTask);
    }

    @Override
    public int cleanToken(){
        LoginUser loginUser = tokenService.getLoginUser();
        if (loginUser != null){
            SysUser sysUser = loginUser.getSysUser();
            sysUser.setToken(null);
            sysUser.setDeviceType(null);
            return sysUserMapper.updateUser(sysUser);
        }
        else {
            return 0;
        }
    }

    @Override
    public int queryToken() {
        LoginUser loginUser = tokenService.getLoginUser();
        if (loginUser==null){
            return 0;
        }
        return 1;
    }

    /**
     * @describe
     * @author DongCL
     * @date 2021-03-22 15:58
     * @param
     * @return
     */
    @Override
    public List<SysExamineTask> sysExamineTaskDriverList(SysExamineTask sysExamineTask) {
        List<SysExamineTask> sysExamineTasks = sysExamineTaskMapper.sysExamineTaskDriverList(sysExamineTask);
        return sysExamineTasks;
    }
}
