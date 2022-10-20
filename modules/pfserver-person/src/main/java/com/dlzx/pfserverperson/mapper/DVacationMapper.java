package com.dlzx.pfserverperson.mapper;

import java.util.Date;
import java.util.List;
import com.dlzx.pfserverperson.domain.DVacation;
import org.apache.ibatis.annotations.Param;

/**
 * 驾驶员-考勤Mapper接口
 * 
 * @author dcl
 * @date 2020-11-30
 */
public interface DVacationMapper 
{
    /**
     * 查询驾驶员-考勤
     * 
     * @param id 驾驶员-考勤ID
     * @return 驾驶员-考勤
     */
    public DVacation selectDVacationById(String id);

    /**
     * 查询驾驶员-考勤列表
     * 
     * @param dVacation 驾驶员-考勤
     * @return 驾驶员-考勤集合
     */
    public List<DVacation> selectDVacationList(DVacation dVacation);

    /**
     * 新增驾驶员-考勤
     * 
     * @param dVacation 驾驶员-考勤
     * @return 结果
     */
    public int insertDVacation(DVacation dVacation);

    /**
     * 修改驾驶员-考勤
     * 
     * @param dVacation 驾驶员-考勤
     * @return 结果
     */
    public int updateDVacation(DVacation dVacation);

    /**
     * 删除驾驶员-考勤
     * 
     * @param id 驾驶员-考勤ID
     * @return 结果
     */
    public int deleteDVacationById(String id);

    /**
     * 批量删除驾驶员-考勤
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDVacationByIds(String[] ids);
    /**
     * 删除驾驶员-审核
     *
     * @param dVacation 驾驶员-考勤ID
     * @return 结果
     */
    int checkDVacation(DVacation dVacation);
    /**
     * 请假统计
     *
     * @param dVacation
     * @return 结果
     */
    List<DVacation> queryLeaveStatistics(DVacation dVacation);
    /**
     * 请假列表（根据驾驶员id查询）
     *
     * @param driverIds 驾驶员id集合
     * @return 结果
     */
    List<DVacation> queryInDriverIds(@Param("list") List<String> driverIds, @Param("vStartTime")Date startTime, @Param("vEndTime")Date vEndTime);

    /**
     * App请假列表（根据驾驶员对象查询）
     *
     * @param dVacation 驾驶员对象
     * @return 结果
     */
    List<DVacation> queryAppVacationList(DVacation dVacation);
}
