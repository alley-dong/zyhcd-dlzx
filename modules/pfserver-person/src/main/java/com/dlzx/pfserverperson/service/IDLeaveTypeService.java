package com.dlzx.pfserverperson.service;

import java.util.List;
import com.dlzx.pfserverperson.domain.DLeaveType;

/**
 * 驾驶员-请假类型Service接口
 * 
 * @author yangluhe
 * @date 2020-11-24
 */
public interface IDLeaveTypeService 
{
    /**
     * 查询驾驶员-请假类型
     * 
     * @param id 驾驶员-请假类型ID
     * @return 驾驶员-请假类型
     */
    public DLeaveType selectDLeaveTypeById(String id);

    /**
     * 查询驾驶员-请假类型列表
     * 
     * @param dLeaveType 驾驶员-请假类型
     * @return 驾驶员-请假类型集合
     */
    public List<DLeaveType> selectDLeaveTypeList(DLeaveType dLeaveType);

    /**
     * 新增驾驶员-请假类型
     * 
     * @param dLeaveType 驾驶员-请假类型
     * @return 结果
     */
    public int insertDLeaveType(DLeaveType dLeaveType);

    /**
     * 修改驾驶员-请假类型
     * 
     * @param dLeaveType 驾驶员-请假类型
     * @return 结果
     */
    public int updateDLeaveType(DLeaveType dLeaveType);

    /**
     * 批量删除驾驶员-请假类型
     * 
     * @param ids 需要删除的驾驶员-请假类型ID
     * @return 结果
     */
    public int deleteDLeaveTypeByIds(String[] ids);

    /**
     * 删除驾驶员-请假类型信息
     * 
     * @param id 驾驶员-请假类型ID
     * @return 结果
     */
    public int deleteDLeaveTypeById(String id);

    /**
     * @describe 请假类型
     * @author DongCL
     * @date 2021-01-27 14:38
     * @param
     * @return
     */
    List<DLeaveType> E03VacationTypeList();

}
