package com.dlzx.pfserverparts.mapper;

import java.util.List;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.pfserverparts.domain.GGoodsCheck;
import com.dlzx.pfserverparts.domain.GGoodsReceivingRecord;
import org.apache.ibatis.annotations.Param;

/**
 * 库存管理-盘点Mapper接口
 * 
 * @author dlzx
 * @date 2020-12-03
 */
public interface GGoodsCheckMapper 
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

    public int countAllEdit(@Param("id") String id, @Param("name") String name);


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
     * 删除库存管理-盘点
     * 
     * @param id 库存管理-盘点ID
     * @return 结果
     */
    public int deleteGGoodsCheckById(String id);

    /**
     * 批量删除库存管理-盘点
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGGoodsCheckByIds(String[] ids);

    /**
     * @describe 盘点列表
     * @author DongCL
     * @date 2021-01-21 17:42
     * @param
     * @return
     */
    List<GGoodsCheck> queryAdminGGoodsCheckList(GGoodsCheck gGoodsCheck);


}
