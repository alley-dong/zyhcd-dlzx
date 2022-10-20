package com.dlzx.system.service;

import java.util.List;
import java.util.Map;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.domain.SysExamineConfigParams;
import com.dlzx.system.domain.AppTokenUpdate;
import com.dlzx.system.domain.ReviewSubmit;
import com.dlzx.system.domain.SysExamineConfigProcess;
import com.dlzx.system.domain.SysExamineTask;

/**
 * 系统管理-审批任务Service接口
 * 
 * @author yangluhe
 * @date 2020-12-11
 */
public interface ISysExamineTaskService 
{
    /**
     * 查询系统管理-审批任务
     * 
     * @param id 系统管理-审批任务ID
     * @return 系统管理-审批任务
     */
    public SysExamineTask selectSysExamineTaskById(String id);

    /**
     * 查询系统管理-审批任务列表
     * 
     * @param sysExamineTask 系统管理-审批任务
     * @return 系统管理-审批任务集合
     */
    public List<SysExamineTask> selectSysExamineTaskList(SysExamineTask sysExamineTask);

    /**
     * 新增系统管理-审批任务
     * 
     * @param sysExamineTask 系统管理-审批任务
     * @return 结果
     */
    public int insertSysExamineTask(SysExamineTask sysExamineTask);

    /**
     * 修改系统管理-审批任务
     * 
     * @param sysExamineTask 系统管理-审批任务
     * @return 结果
     */
    public int updateSysExamineTask(SysExamineTask sysExamineTask);

    /**
     * 批量删除系统管理-审批任务
     * 
     * @param ids 需要删除的系统管理-审批任务ID
     * @return 结果
     */
    public int deleteSysExamineTaskByIds(String[] ids);

    /**
     * 删除系统管理-审批任务信息
     * 
     * @param id 系统管理-审批任务ID
     * @return 结果
     */
    public int deleteSysExamineTaskById(String id);

    /**
     * 批量新增数据
     */
    public int selectTaskList(SysExamineConfigParams sysExamineConfigParams);

    /**
     * @param sysExamineTask 系统管理-获取审批记录
     * @return 结果
     */
    SysExamineTask querySysExamineTaskList(SysExamineTask sysExamineTask);

    /**
     * 审批
     */
    public SysExamineTask selectSysExamineTaskReview(String relationId,String userId);


    public SysExamineTask selectSysExamineTaskReviewNextLevel(String relationId,Long level,String userId);
    /**
     * 删除审批记录
    * */
    int deleteExamineTaskByRelationId(String relationId);

    /**
     * 管理员app-审批待办列表
    * */
    List<SysExamineTask> querySysExamineTaskBacklogList(SysExamineTask sysExamineTask);

    /**
     * 管理员app-审批记录
     */
    List<SysExamineTask> queryExamineList(SysExamineTask sysExamineTask);

    /**
     * 管理员app-抄送记录
     */
    List<SysExamineTask> querySendDuplicateList(SysExamineTask sysExamineTask);

    /**
    * 公用方法-审核校验
    * */
    Map<String, Object> reviewCommon(ReviewSubmit reviewSubmit);

    /**
     *
     */
    int updateAppToken(AppTokenUpdate appTokenUpdate);

    /**
     * @describe 根据用户ID || 主键ID查询
     * @author DongCL
     * @date 2021-02-24 09:33
     * @param
     * @return
     */
    int updateSysExamineTaskReadStatus(SysExamineTask sysExamineTask);

    int cleanToken();

    /**
     * @describe
     * @author DongCL
     * @date 2021-03-22 15:58
     * @param
     * @return
     */
    List<SysExamineTask> sysExamineTaskDriverList(SysExamineTask sysExamineTask);

    /**
     * @describe 后台审批
     * @author DongCL
     * @date 2021-03-30 11:47
     * @param
     * @return
     */
    public int selectAdminTaskList(Long type, String relationId, String driverId, String name);

    /**
     * @describe 查询token
     * @author DongCL
     * @date 2021-04-14 17:34
     * @param
     * @return
     */
    int queryToken();
}
