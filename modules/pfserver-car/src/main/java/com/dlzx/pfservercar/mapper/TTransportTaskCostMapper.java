package com.dlzx.pfservercar.mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.pfservercar.domain.TTransportTaskCost;
import com.dlzx.pfservercar.domain.model.OTMTransportTaskCostModel;

/**
 * 任务管理-运输任务费用Mapper接口
 *
 * @author yangluhe
 * @date 2021-01-05
 */
public interface TTransportTaskCostMapper
{
    /**
     * 查询任务管理-运输任务费用
     *
     * @param id 任务管理-运输任务费用ID
     * @return 任务管理-运输任务费用
     */
    public TTransportTaskCost selectTTransportTaskCostById(String id);

    /**
     * 查询任务管理-运输任务费用列表
     *
     * @param tTransportTaskCost 任务管理-运输任务费用
     * @return 任务管理-运输任务费用集合
     */
    public List<TTransportTaskCost> selectTTransportTaskCostList(TTransportTaskCost tTransportTaskCost);

    /**
     * 新增任务管理-运输任务费用
     *
     * @param tTransportTaskCost 任务管理-运输任务费用
     * @return 结果
     */
    public int insertTTransportTaskCost(TTransportTaskCost tTransportTaskCost);

    /**
     * 修改任务管理-运输任务费用
     *
     * @param tTransportTaskCost 任务管理-运输任务费用
     * @return 结果
     */
    public int updateTTransportTaskCost(TTransportTaskCost tTransportTaskCost);

    /**
     * 删除任务管理-运输任务费用
     *
     * @param id 任务管理-运输任务费用ID
     * @return 结果
     */
    public int deleteTTransportTaskCostById(String id);

    /**
     * 批量删除任务管理-运输任务费用
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTTransportTaskCostByIds(String[] ids);

    /**
     * @describe OTM 车队运输任务运费
     * @author DongCL
     * @date 2021-01-07 13:35
     * @param otmTransportTaskCostModel
     * @return AjaxResult
     */
    int insertOTMTransportTaskCost(OTMTransportTaskCostModel otmTransportTaskCostModel);
}
