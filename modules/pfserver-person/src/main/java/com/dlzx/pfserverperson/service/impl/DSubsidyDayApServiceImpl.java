package com.dlzx.pfserverperson.service.impl;

import com.dlzx.pfserverperson.domain.DSubsidyDay;
import com.dlzx.pfserverperson.mapper.DSubsidyDayMapper;
import com.dlzx.pfserverperson.service.IDSubsidyDayApService;
import com.dlzx.pfserverperson.service.IDSubsidyDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 驾驶员-补助天数Service业务层处理
 *
 * @author dlzx
 * @date 2020-12-14
 */
@Service
public class DSubsidyDayApServiceImpl implements IDSubsidyDayApService {
    @Autowired
    private DSubsidyDayMapper dSubsidyDayMapper;

    /**
     * 查询驾驶员-补助天数
     *
     * @param id 驾驶员-补助天数ID
     * @return 驾驶员-补助天数
     */
    @Override
    public DSubsidyDay selectDSubsidyDayById(String id) {
        return dSubsidyDayMapper.selectDSubsidyDayById(id);
    }

    /**
     * 查询驾驶员-补助天数列表
     *
     * @param dSubsidyDay 驾驶员-补助天数
     * @return 驾驶员-补助天数
     */
    @Override
    public List<DSubsidyDay> selectDSubsidyDayList(DSubsidyDay dSubsidyDay) {
        return dSubsidyDayMapper.selectDSubsidyDayList(dSubsidyDay);
    }

    /**
     * 新增驾驶员-补助天数
     *
     * @param dSubsidyDay 驾驶员-补助天数
     * @return 结果
     */
    @Override
    public int insertDSubsidyDay(DSubsidyDay dSubsidyDay) {
        return dSubsidyDayMapper.insertDSubsidyDay(dSubsidyDay);
    }

    @Override
    public int insertDSubsidyDayList(List<DSubsidyDay> list) {
        return dSubsidyDayMapper.insertDSubsidyDayList(list);
    }

    /**
     * 修改驾驶员-补助天数
     *
     * @param dSubsidyDay 驾驶员-补助天数
     * @return 结果
     */
    @Override
    public int updateDSubsidyDay(DSubsidyDay dSubsidyDay) {
        return dSubsidyDayMapper.updateDSubsidyDay(dSubsidyDay);
    }

    /**
     * 批量删除驾驶员-补助天数
     *
     * @param ids 需要删除的驾驶员-补助天数ID
     * @return 结果
     */
    @Override
    public int deleteDSubsidyDayByIds(String[] ids) {
        return dSubsidyDayMapper.deleteDSubsidyDayByIds(ids);
    }

    /**
     * 删除驾驶员-补助天数信息
     *
     * @param id 驾驶员-补助天数ID
     * @return 结果
     */
    @Override
    public int deleteDSubsidyDayById(String id) {
        return dSubsidyDayMapper.deleteDSubsidyDayById(id);
    }

    //
    @Override
    public int deleteDSubsidyDayBySubId(String subsidyId) {
        return dSubsidyDayMapper.deleteDSubsidyDayBySubId(subsidyId);
    }
}
