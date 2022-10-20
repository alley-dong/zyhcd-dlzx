package com.dlzx.pfserverperson.mapper;

import java.util.List;
import com.dlzx.pfserverperson.domain.TRoute;

/**
 * 任务管理-线路Mapper接口
 * 
 * @author dlzx
 * @date 2020-11-30
 */
public interface TRouteMapper 
{
    /**
     * 查询任务管理-线路
     * 
     * @param id 任务管理-线路ID
     * @return 任务管理-线路
     */
    public TRoute selectTRouteById(String id);

    /**
     * 查询任务管理-线路列表
     * 
     * @param tRoute 任务管理-线路
     * @return 任务管理-线路集合
     */
    public List<TRoute> selectTRouteList(TRoute tRoute);

    public List<TRoute> selectTRouteListChang(TRoute tRoute);

    public List<TRoute> selectTRouteListQuan(TRoute tRoute);
    /**
     * 新增任务管理-线路
     * 
     * @param tRoute 任务管理-线路
     * @return 结果
     */
    public int insertTRoute(TRoute tRoute);

    /**
     * 修改任务管理-线路
     * 
     * @param tRoute 任务管理-线路
     * @return 结果
     */
    public int updateTRoute(TRoute tRoute);

    /**
     * 删除任务管理-线路
     * 
     * @param id 任务管理-线路ID
     * @return 结果
     */
    public int deleteTRouteById(String id);

    /**
     * 批量删除任务管理-线路
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTRouteByIds(String[] ids);
}
