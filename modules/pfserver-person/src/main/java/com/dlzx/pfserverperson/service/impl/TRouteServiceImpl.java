package com.dlzx.pfserverperson.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverperson.mapper.TRouteMapper;
import com.dlzx.pfserverperson.domain.TRoute;
import com.dlzx.pfserverperson.service.ITRouteService;

/**
 * 任务管理-线路Service业务层处理
 * 
 * @author dlzx
 * @date 2020-11-30
 */
@Service
public class TRouteServiceImpl implements ITRouteService 
{
    @Autowired
    private TRouteMapper tRouteMapper;

    /**
     * 查询任务管理-线路
     * 
     * @param id 任务管理-线路ID
     * @return 任务管理-线路
     */
    @Override
    public TRoute selectTRouteById(String id)
    {
        return tRouteMapper.selectTRouteById(id);
    }

    /**
     * 查询任务管理-线路列表
     * 
     * @param tRoute 任务管理-线路
     * @return 任务管理-线路
     */
    @Override
    public List<TRoute> selectTRouteList(TRoute tRoute)
    {
        return tRouteMapper.selectTRouteList(tRoute);
    }

    @Override
    public List<TRoute> selectTRouteListChang(TRoute tRoute)
    {
        return tRouteMapper.selectTRouteListChang(tRoute);
    }

    @Override
    public List<TRoute> selectTRouteListQuan(TRoute tRoute)
    {
        return tRouteMapper.selectTRouteListQuan(tRoute);
    }
    /**
     * 新增任务管理-线路
     * 
     * @param tRoute 任务管理-线路
     * @return 结果
     */
    @Override
    public int insertTRoute(TRoute tRoute)
    {
        tRoute.setCreateTime(DateUtils.getNowDate());
        return tRouteMapper.insertTRoute(tRoute);
    }

    /**
     * 修改任务管理-线路
     * 
     * @param tRoute 任务管理-线路
     * @return 结果
     */
    @Override
    public int updateTRoute(TRoute tRoute)
    {
        tRoute.setUpdateTime(DateUtils.getNowDate());
        return tRouteMapper.updateTRoute(tRoute);
    }

    /**
     * 批量删除任务管理-线路
     * 
     * @param ids 需要删除的任务管理-线路ID
     * @return 结果
     */
    @Override
    public int deleteTRouteByIds(String[] ids)
    {
        return tRouteMapper.deleteTRouteByIds(ids);
    }

    /**
     * 删除任务管理-线路信息
     * 
     * @param id 任务管理-线路ID
     * @return 结果
     */
    @Override
    public int deleteTRouteById(String id)
    {
        return tRouteMapper.deleteTRouteById(id);
    }
}
