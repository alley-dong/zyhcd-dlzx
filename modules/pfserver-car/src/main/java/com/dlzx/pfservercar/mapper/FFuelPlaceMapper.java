package com.dlzx.pfservercar.mapper;

import java.util.List;
import com.dlzx.pfservercar.domain.FFuelPlace;

/**
 * 燃油定点管理Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-01
 */
public interface FFuelPlaceMapper 
{
    /**
     * 查询燃油定点管理
     * 
     * @param id 燃油定点管理ID
     * @return 燃油定点管理
     */
    public FFuelPlace selectFFuelPlaceById(String id);

    /**
     * 查询燃油定点管理列表
     * 
     * @param fFuelPlace 燃油定点管理
     * @return 燃油定点管理集合
     */
    public List<FFuelPlace> selectFFuelPlaceList(FFuelPlace fFuelPlace);

    /**
     * 新增燃油定点管理
     * 
     * @param fFuelPlace 燃油定点管理
     * @return 结果
     */
    public int insertFFuelPlace(FFuelPlace fFuelPlace);

    /**
     * 修改燃油定点管理
     * 
     * @param fFuelPlace 燃油定点管理
     * @return 结果
     */
    public int updateFFuelPlace(FFuelPlace fFuelPlace);

    /**
     * 删除燃油定点管理
     * 
     * @param id 燃油定点管理ID
     * @return 结果
     */
    public int deleteFFuelPlaceById(String id);

    /**
     * 批量删除燃油定点管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFFuelPlaceByIds(String[] ids);

    /**
     * 根据线路ID获取定点信息
     *
     * @param routeId 线路ID
     * @return 结果
     */
    FFuelPlace queryFFuelPlaceByRouteId(String routeId);
}
