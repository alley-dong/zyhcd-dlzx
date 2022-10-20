package com.dlzx.pfserverperson.mapper;

import com.dlzx.pfserverperson.domain.DSubsidyDay;
import com.dlzx.pfserverperson.domain.DSubsidyDayAp;

import java.util.List;

/**
 * 驾驶员-补助天数Mapper接口
 *
 * @author dlzx
 * @date 2020-12-14
 */
public interface DSubsidyDayApMapper {
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

    public int insertDSubsidyDayList(List<DSubsidyDayAp> list);

    /**
     * 修改驾驶员-补助天数
     *
     * @param dSubsidyDay 驾驶员-补助天数
     * @return 结果
     */
    public int updateDSubsidyDay(DSubsidyDay dSubsidyDay);

    /**
     * 删除驾驶员-补助天数
     *
     * @param id 驾驶员-补助天数ID
     * @return 结果
     */
    public int deleteDSubsidyDayById(String id);

    //
    public int deleteDSubsidyDayBySubId(String subsidyId);

    /**
     * 批量删除驾驶员-补助天数
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDSubsidyDayByIds(String[] ids);
}
