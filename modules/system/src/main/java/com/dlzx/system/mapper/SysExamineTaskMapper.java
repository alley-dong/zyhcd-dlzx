package com.dlzx.system.mapper;

import java.util.Date;
import java.util.List;
import com.dlzx.system.domain.SysExamineTask;
import org.apache.ibatis.annotations.Param;

/**
 * 系统管理-审批任务Mapper接口
 * 
 * @author yangluhe
 * @date 2020-12-11
 */
public interface SysExamineTaskMapper 
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
     * 删除系统管理-审批任务
     * 
     * @param id 系统管理-审批任务ID
     * @return 结果
     */
    public int deleteSysExamineTaskById(String id);

    /**
     * 批量删除系统管理-审批任务
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysExamineTaskByIds(String[] ids);

    /**
     * 批量新增数据
     */
    public int insertSysExamineTaskBatch(List<SysExamineTask> item);

    /**
     * 审批公共
     */
    public SysExamineTask selectSysExamineTaskReview(@Param("relationId")String relationId,@Param("userId")String userId);

    /**
     * 删除审批记录
     * */
    int deleteExamineTaskByRelationId(@Param("relationId")String relationId);

    public SysExamineTask selectSysExamineTaskReviewNextLevel(@Param("relationId")String relationId,@Param("level")Long level,@Param("userId")String userId);

    /**
     * 校验是否是重新提交
     */
    public List<SysExamineTask> checkExistenceStatus(@Param("relationId")String relationId,@Param("type")Long type);

    /**
     * 批量更新
     */
    public int updateSysExamineTaskBatch(String id);

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
     * 管理员app-审核状态修改
    * */
    int checkExamineTask(SysExamineTask sysExamineTask);

    /**
     * 更新拒绝
     */
    SysExamineTask selectSysExamineTaskReviewRefuse(String id);

    /**
     * 针对维修保养异地维修更新审核任务为已通过
     */
    int isFixedUpdate(@Param("status")Long status,@Param("reviewTime")Date reviewTime,@Param("id")String id);

    SysExamineTask selectSysExamineTaskCopy(@Param("relationId")String relationId,@Param("level")Long level,@Param("type")Long type);

    /**
     * @describe 根据用户ID || 主键ID查询
     * @author DongCL
     * @date 2021-02-24 09:33
     * @param
     * @return
     */
    int updateSysExamineTaskReadStatus(SysExamineTask sysExamineTask);

    /**
     * @describe 抄送记录未查看条数
     * @author DongCL
     * @date 2021-03-02 16:24
     * @param
     * @return
     */
    List<SysExamineTask> querySendDuplicateCount(SysExamineTask sysExamineTask);

    /**
     * @describe 审批记录未查看条数
     * @author DongCL
     * @date 2021-03-02 16:25
     * @param
     * @return
     */
    List<SysExamineTask> queryExamineCount(SysExamineTask sysExamineTask);

    /**
     * @describe
     * @author DongCL
     * @date 2021-03-22 15:59
     * @param
     * @return
     */
    List<SysExamineTask> sysExamineTaskDriverList(SysExamineTask sysExamineTask);

    /**
     * @describe 修改任务审批数据和状态
     * @author DongCL
     * @date 2021-04-16 10:41
     * @param
     * @return
     */
    int updateStationStatus(String relationId);

    /**
     * @describe 修改任务展示状态
     * @author DongCL
     * @date 2021-04-16 10:41
     * @param
     * @return
     */
    int updateTaskStatus(String relationId);
}
