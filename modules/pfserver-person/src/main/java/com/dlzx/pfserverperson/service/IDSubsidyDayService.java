package com.dlzx.pfserverperson.service;

import java.util.List;
import com.dlzx.pfserverperson.domain.DSubsidyDay;

/**
 * 驾驶员-补助天数Service接口
 * 
 * @author dlzx
 * @date 2020-12-08
 */
public interface IDSubsidyDayService 
{
    /**
     * 查询驾驶员-补助天数
     * 
     * @param id 驾驶员-补助天数ID
     * @return 驾驶员-补助天数
     */
    public DSubsidyDay selectDSubsidyDayById(String id);

    /**
     * 查询驾驶员-补助天数列表
     * 
     * @param dSubsidyDay 驾驶员-补助天数
     * @return 驾驶员-补助天数集合
     */
    public List<DSubsidyDay> selectDSubsidyDayList(DSubsidyDay dSubsidyDay);

    /**
     * 新增驾驶员-补助天数
     * 
     * @param dSubsidyDay 驾驶员-补助天数
     * @return 结果
     */
    public int insertDSubsidyDay(DSubsidyDay dSubsidyDay);

    public int insertDSubsidyDayList(List<DSubsidyDay> list);
    /**
     * 修改驾驶员-补助天数
     * 
     * @param dSubsidyDay 驾驶员-补助天数
     * @return 结果
     */
    public int updateDSubsidyDay(DSubsidyDay dSubsidyDay);

    /**
     * 批量删除驾驶员-补助天数
     * 
     * @param ids 需要删除的驾驶员-补助天数ID
     * @return 结果
     */
    public int deleteDSubsidyDayByIds(String[] ids);

    /**
     * 删除驾驶员-补助天数信息
     * 
     * @param id 驾驶员-补助天数ID
     * @return 结果
     */
    public int deleteDSubsidyDayById(String id);

    //    通过补助id删对应日期
    public int deleteDSubsidyDayBySubId(String subsidyId);
}
