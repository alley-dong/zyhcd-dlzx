package com.dlzx.pfserverparts.service;

import java.util.List;

import com.dlzx.pfserverparts.domain.GGoodsCheck;
import com.dlzx.pfserverparts.domain.GGoodsCheckItem;

/**
 * 库存管理-盘点项Service接口
 * 
 * @author dlzx
 * @date 2020-12-03
 */
public interface IGGoodsCheckItemService 
{
    /**
     * 查询库存管理-盘点项
     * 
     * @param id 库存管理-盘点项ID
     * @return 库存管理-盘点项
     */
    public GGoodsCheckItem selectGGoodsCheckItemById(String id);

    /**
     * 查询库存管理-盘点项列表
     * 
     * @param gGoodsCheckItem 库存管理-盘点项
     * @return 库存管理-盘点项集合
     */
    public List<GGoodsCheckItem> selectGGoodsCheckItemList(GGoodsCheckItem gGoodsCheckItem);

    public List<GGoodsCheckItem> selectGGoodsCheckItemByCheckId(String checkId);

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
     * 批量删除库存管理-盘点项
     * 
     * @param ids 需要删除的库存管理-盘点项ID
     * @return 结果
     */
    public int deleteGGoodsCheckItemByIds(String[] ids);

    /**
     * 删除库存管理-盘点项信息
     * 
     * @param id 库存管理-盘点项ID
     * @return 结果
     */
    public int deleteGGoodsCheckItemById(String id);

    /**
     * @describe 盘点提交
     * @author DongCL 
     * @date 2021-01-18 15:31
     * @param 
     * @return 
     */
    int submitCheckItem(GGoodsCheck gGoodsCheck);

    /**
     * @describe 检查项编辑
     * @author DongCL
     * @date 2021-01-21 18:17
     * @param
     * @return
     */
    int updateCheckItem(GGoodsCheckItem gGoodsCheckItem);

    /** 查询list
     * @describe
     * @author DongCL
     * @date 2021-01-26 20:51
     * @param
     * @return
     */
    List<GGoodsCheckItem> queryGGoodsCheckItemList(GGoodsCheckItem gGoodsCheckItem);
}
