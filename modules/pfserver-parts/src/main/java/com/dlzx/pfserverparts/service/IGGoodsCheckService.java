package com.dlzx.pfserverparts.service;

import java.util.List;
import com.dlzx.pfserverparts.domain.GGoodsCheck;
import com.dlzx.pfserverparts.domain.GGoodsType;

/**
 * 库存管理-盘点Service接口
 * 
 * @author dlzx
 * @date 2020-12-03
 */
public interface IGGoodsCheckService 
{
    /**
     * 查询库存管理-盘点
     * 
     * @param id 库存管理-盘点ID
     * @return 库存管理-盘点
     */
    public GGoodsCheck selectGGoodsCheckById(String id);

    public List<GGoodsCheck> selectGGoodsCheckByCheckId(String id);

    /**
     * 查询库存管理-盘点列表
     * 
     * @param gGoodsCheck 库存管理-盘点
     * @return 库存管理-盘点集合
     */
    public List<GGoodsCheck> selectGGoodsCheckList(GGoodsCheck gGoodsCheck);

    public List<GGoodsCheck> countAll(String goodsTypeId);

    /**
     * 根据goodsTypeId查询主键ID
     * @param goodsTypeId
     * @return
     */
    public String selectGGoodsCheckByGoodsTypeId(String goodsTypeId);


    /**
     * 新增库存管理-盘点
     * 
     * @param gGoodsCheck 库存管理-盘点
     * @return 结果
     */
    public int insertGGoodsCheck(GGoodsCheck gGoodsCheck);

    /**
     * 修改库存管理-盘点
     * 
     * @param gGoodsCheck 库存管理-盘点
     * @return 结果
     */
    public int updateGGoodsCheck(GGoodsCheck gGoodsCheck);

    /**
     * 批量删除库存管理-盘点
     * 
     * @param ids 需要删除的库存管理-盘点ID
     * @return 结果
     */
    public int deleteGGoodsCheckByIds(String[] ids);

    /**
     * 删除库存管理-盘点信息
     * 
     * @param id 库存管理-盘点ID
     * @return 结果
     */
    public int deleteGGoodsCheckById(String id);

    /**
     * @describe 新增盘点
     * @author DongCL
     * @date 2021-01-21 18:03
     * @param
     * @return
     */
    public String submitGGoodsCheck(GGoodsType gGoodsType);
}
