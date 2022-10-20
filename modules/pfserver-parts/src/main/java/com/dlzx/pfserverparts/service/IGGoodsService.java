package com.dlzx.pfserverparts.service;

import java.util.List;

import com.dlzx.pfserverparts.domain.DDriver;
import com.dlzx.pfserverparts.domain.GGoods;

/**
 * 库存管理-物品Service接口
 * 
 * @author dlzx
 * @date 2020-12-01
 */
public interface IGGoodsService 
{
    /**
     * 查询库存管理-物品
     * 
     * @param id 库存管理-物品ID
     * @return 库存管理-物品
     */
    public GGoods selectGGoodsById(String id);

    public List<GGoods> selectGGoodsByIdList(String id);

    /**
     * 查询库存管理-物品列表
     * 
     * @param gGoods 库存管理-物品
     * @return 库存管理-物品集合
     */
    public List<GGoods> selectGGoodsList(GGoods gGoods);

    /**
     * 根据g_goods_type表的id作为goodsTypeId查出数据添加到g_goods_check_item
     * @param goodsTypeId
     * @return
     */
    public List<GGoods> selectGGoodsByTypeGoodsTypeIdList(String goodsTypeId);

    public List<GGoods> countAll(String name);

    public List<GGoods> countCodeAll(String code);

    public int countAllEdit(String id, String name);

    /**
     * 新增库存管理-物品
     * 
     * @param gGoods 库存管理-物品
     * @return 结果
     */
    public int insertGGoods(GGoods gGoods);

    /**
     * 修改库存管理-物品
     * 
     * @param gGoods 库存管理-物品
     * @return 结果
     */
    public int updateGGoods(GGoods gGoods);

    /**
     * 批量删除库存管理-物品
     * 
     * @param ids 需要删除的库存管理-物品ID
     * @return 结果
     */
    public int deleteGGoodsByIds(String[] ids);

    /**
     * 删除库存管理-物品信息
     * 
     * @param id 库存管理-物品ID
     * @return 结果
     */
    public int deleteGGoodsById(String id);

    /**
     * @describe
     * @author DongCL
     * @date 2021-01-18 14:39
     * @param
     * @return
     */
    List<GGoods> selectGGoodsByIdsList(List<String> goodIds);

    /** 获取驾驶员*/
    List<DDriver> selectDriversList();
}
