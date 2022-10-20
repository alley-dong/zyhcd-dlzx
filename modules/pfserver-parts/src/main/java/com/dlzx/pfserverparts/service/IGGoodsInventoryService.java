package com.dlzx.pfserverparts.service;

import java.util.List;
import com.dlzx.pfserverparts.domain.GGoodsInventory;
import com.dlzx.pfserverparts.domain.excel.GGoodsInventoryInExcel;

/**
 * 库存管理-库存Service接口
 * 
 * @author dlzx
 * @date 2020-12-02
 */
public interface IGGoodsInventoryService 
{
    /**
     * 查询库存管理-库存
     * 
     * @param id 库存管理-库存ID
     * @return 库存管理-库存
     */
    public GGoodsInventory selectGGoodsInventoryById(String id);

    /**
     * 查询库存管理-库存列表
     * 
     * @param gGoodsInventory 库存管理-库存
     * @return 库存管理-库存集合
     */
    public List<GGoodsInventory> selectGGoodsInventoryList(GGoodsInventory gGoodsInventory);

    /**
     * 入库管理出库管理下拉选
     * @return
     */
    public List<GGoodsInventory> selectAllGoodsTypeName(String type);

    /**
     * 库存下拉选
     * @param
     * @return
     */
    public List<GGoodsInventory> selectAllGoodsTypeNameKC();

    /**
     * 报废下拉选
     * @return
     */
    public List<GGoodsInventory> selectAllGoodsTypeNameBF();

    public int countAll(String goodsName);

    public int countAllEdit(String goodsId, String goodsName);
    /**
     * 新增库存管理-库存
     * 
     * @param gGoodsInventory 库存管理-库存
     * @return 结果
     */
    public int insertGGoodsInventory(GGoodsInventory gGoodsInventory);

    /**
     * 修改库存管理-库存
     * 
     * @param gGoodsInventory 库存管理-库存
     * @return 结果
     */
    public int updateGGoodsInventory(GGoodsInventory gGoodsInventory);

    /**
     * 批量删除库存管理-库存
     * 
     * @param ids 需要删除的库存管理-库存ID
     * @return 结果
     */
    public int deleteGGoodsInventoryByIds(String[] ids);

    /**
     * 删除库存管理-库存信息
     * 
     * @param id 库存管理-库存ID
     * @return 结果
     */
    public int deleteGGoodsInventoryById(String id);

    /** 批量插入*/
    int insertList(List<GGoodsInventory> gGoodsInventories);

    /**
     * @describe 库存管理-查询出库管理列表
     * @author DongCL
     * @date 2021-03-05 09:35
     * @param
     * @return
     */
    List<GGoodsInventory> selectGGoodsOutInventoryList(GGoodsInventory gGoodsInventory);

    /**
     * @describe 库存管理-查询库存明细列表
     * @author DongCL 
     * @date 2021-03-05 09:45
     * @param 
     * @return 
     */
    List<GGoodsInventory> selectGGoodsInventoryDetailList(GGoodsInventory gGoodsInventory);
}
