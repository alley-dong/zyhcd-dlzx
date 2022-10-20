package com.dlzx.pfserverparts.service;

import java.util.List;
import com.dlzx.pfserverparts.domain.GGoodsType;

/**
 * 库存管理-物品类别Service接口
 * 
 * @author dlzx
 * @date 2020-12-01
 */
public interface IGGoodsTypeService 
{
    /**
     * 查询库存管理-物品类别
     * 
     * @param id 库存管理-物品类别ID
     * @return 库存管理-物品类别
     */
    public GGoodsType selectGGoodsTypeById(String id);

    /**
     * 查询库存管理-物品类别列表
     * 
     * @param gGoodsType 库存管理-物品类别
     * @return 库存管理-物品类别集合
     */
    public List<GGoodsType> selectGGoodsTypeList(GGoodsType gGoodsType);

    public int countAll(String name);

    public int countAllEdit(String name);

    /**
     * 新增库存管理-物品类别
     * 
     * @param gGoodsType 库存管理-物品类别
     * @return 结果
     */
    public int insertGGoodsType(GGoodsType gGoodsType);

    /**
     * 修改库存管理-物品类别
     * 
     * @param gGoodsType 库存管理-物品类别
     * @return 结果
     */
    public int updateGGoodsType(GGoodsType gGoodsType);

    /**
     * 批量删除库存管理-物品类别
     * 
     * @param ids 需要删除的库存管理-物品类别ID
     * @return 结果
     */
    public int deleteGGoodsTypeByIds(String[] ids);

    /**
     * 删除库存管理-物品类别信息
     * 
     * @param id 库存管理-物品类别ID
     * @return 结果
     */
    public int deleteGGoodsTypeById(String id);
}
