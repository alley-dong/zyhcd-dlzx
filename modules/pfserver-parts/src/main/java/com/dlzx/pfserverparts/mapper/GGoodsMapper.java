package com.dlzx.pfserverparts.mapper;

import java.util.List;

import com.dlzx.pfserverparts.domain.DDriver;
import com.dlzx.pfserverparts.domain.GGoods;
import org.apache.ibatis.annotations.Param;

/**
 * 库存管理-物品Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-01
 */
public interface GGoodsMapper 
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
    public List<GGoods> selectGGoodsByTypeGoodsTypeIdList(@Param("goodsTypeId") String goodsTypeId);

    public List<GGoods> countAll(@Param("name") String name);
    public List<GGoods> countCodeAll(@Param("code") String code);

    public int countAllEdit(@Param("id") String id, @Param("name") String name);

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
     * 删除库存管理-物品
     * 
     * @param id 库存管理-物品ID
     * @return 结果
     */
    public int deleteGGoodsById(String id);

    /**
     * 批量删除库存管理-物品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGGoodsByIds(String[] ids);

    /**
     * @describe
     * @author DongCL
     * @date 2021-01-18 14:39
     * @param
     * @return
     */
    List<GGoods> selectGGoodsByIdsList(List<String> goodIds);

    /** 批量修改*/
    int updateList(List<GGoods> gGoodsList);

    /** 获取驾驶员*/
    List<DDriver> selectDriversList();

    /**
     * @describe
     * @author DongCL
     * @date 2021-01-26 20:38
     * @param
     * @return
     */
    List<GGoods> queryInList(List<String> goodIds);
}
