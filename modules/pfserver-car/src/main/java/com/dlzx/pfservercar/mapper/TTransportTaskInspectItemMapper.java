package com.dlzx.pfservercar.mapper;

import java.util.List;
import com.dlzx.pfservercar.domain.TTransportTaskInspectItem;
import com.dlzx.pfservercar.domain.model.TTransportTaskInspectItemModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskInspectItemVm;

/**
 * 任务管理-运输任务点检检查项Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-18
 */
public interface TTransportTaskInspectItemMapper 
{
    /**
     * 查询任务管理-运输任务点检检查项
     * 
     * @param id 任务管理-运输任务点检检查项ID
     * @return 任务管理-运输任务点检检查项
     */
    public TTransportTaskInspectItemVm selectTTransportTaskInspectItemById(String id);

    /**
     * 查询任务管理-运输任务点检检查项列表
     * 
     * @param tTransportTaskInspectItemModel 任务管理-运输任务点检检查项
     * @return 任务管理-运输任务点检检查项集合
     */
    public List<TTransportTaskInspectItemVm> selectTTransportTaskInspectItemList(TTransportTaskInspectItemModel tTransportTaskInspectItemModel);

    /**
     * 新增任务管理-运输任务点检检查项
     * 
     * @param tTransportTaskInspectItemModel 任务管理-运输任务点检检查项
     * @return 结果
     */
    public int insertTTransportTaskInspectItem(TTransportTaskInspectItemModel tTransportTaskInspectItemModel);

    /**
     * 修改任务管理-运输任务点检检查项
     * 
     * @param tTransportTaskInspectItemModel 任务管理-运输任务点检检查项
     * @return 结果
     */
    public int updateTTransportTaskInspectItem(TTransportTaskInspectItemModel tTransportTaskInspectItemModel);

    /**
     * 删除任务管理-运输任务点检检查项
     * 
     * @param id 任务管理-运输任务点检检查项ID
     * @return 结果
     */
    public int deleteTTransportTaskInspectItemById(String id);

    /**
     * 批量删除任务管理-运输任务点检检查项
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTTransportTaskInspectItemByIds(String[] ids);

    /**
    * 根据点检ID查询检查项目
    * */
    List<TTransportTaskInspectItemVm> queryTransportTaskInspectItemList(List<String> list);

    /**
     * 批量插入
     * */
    int insertList(List<TTransportTaskInspectItem> list);
}
