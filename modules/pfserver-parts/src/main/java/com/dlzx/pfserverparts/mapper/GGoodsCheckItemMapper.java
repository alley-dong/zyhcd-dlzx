package com.dlzx.pfserverparts.mapper;

import java.util.List;
import com.dlzx.pfserverparts.domain.GGoodsCheckItem;

/**
 * 库存管理-盘点项Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-03
 */
public interface GGoodsCheckItemMapper 
{
    /**
     * 查询库存管理-盘点项
     * 
     * @param id 库存管理-盘点项ID
     * @return 库存管理-盘点项
     */
    public GGoodsCheckItem selectGGoodsCheckItemById(String id);

    public List<GGoodsCheckItem> selectGGoodsCheckItemByCheckId(String checkId);

    /**
     * 查询库存管理-盘点项列表
     * 
     * @param gGoodsCheckItem 库存管理-盘点项
     * @return 库存管理-盘点项集合
     */
    public List<GGoodsCheckItem> selectGGoodsCheckItemList(GGoodsCheckItem gGoodsCheckItem);

    /**
     * 新增库存管理-盘点项
     * 
     * @param gGoodsCheckItem 库存管理-盘点项
     * @return 结果
     */
    public int insertGGoodsCheckItem(GGoodsCheckItem gGoodsCheckItem);

    /**
     * 修改库存管理-盘点项
     * 
     * @param gGoodsCheckItem 库存管理-盘点项
     * @return 结果
     */
    public int updateGGoodsCheckItem(GGoodsCheckItem gGoodsCheckItem);

    public int insertList(List<GGoodsCheckItem> gGoodsCheckItem);

    /**
     * 删除库存管理-盘点项
     * 
     * @param id 库存管理-盘点项ID
     * @return 结果
     */
    public int deleteGGoodsCheckItemById(String id);

    /**
     * 批量删除库存管理-盘点项
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGGoodsCheckItemByIds(String[] ids);

    /**
     * @describe
     * @author DongCL
     * @date 2021-01-26 20:46
     * @param
     * @return
     */
    int updateList(List<GGoodsCheckItem> gGoodsCheckItems);
}
