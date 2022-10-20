package com.dlzx.pfserverparts.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.pfserverparts.domain.DDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverparts.mapper.GGoodsMapper;
import com.dlzx.pfserverparts.domain.GGoods;
import com.dlzx.pfserverparts.service.IGGoodsService;

/**
 * 库存管理-物品Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-01
 */
@Service
public class GGoodsServiceImpl implements IGGoodsService
{
    @Autowired
    private GGoodsMapper gGoodsMapper;

    @Override
    public GGoods selectGGoodsById(String id) {
        return gGoodsMapper.selectGGoodsById(id);
    }

    /**
     * 查询库存管理-物品
     * 
     * @param id 库存管理-物品ID
     * @return 库存管理-物品
     */


    @Override
    public List<GGoods> selectGGoodsByIdList(String id) {
        return gGoodsMapper.selectGGoodsByIdList(id);
    }

    /**
     * 查询库存管理-物品列表
     * 
     * @param gGoods 库存管理-物品
     * @return 库存管理-物品
     */
    @Override
    public List<GGoods> selectGGoodsList(GGoods gGoods)
    {
        return gGoodsMapper.selectGGoodsList(gGoods);
    }

    @Override
    public List<GGoods> selectGGoodsByTypeGoodsTypeIdList(String goodsTypeId) {
        return gGoodsMapper.selectGGoodsByTypeGoodsTypeIdList(goodsTypeId);
    }

    @Override
    public List<GGoods> countAll(String name) {
        return gGoodsMapper.countAll(name);
    }

    @Override
    public List<GGoods> countCodeAll(String code) {
        return gGoodsMapper.countCodeAll(code);
    }

    @Override
    public int countAllEdit(String id, String name) {
        return gGoodsMapper.countAllEdit(id, name);
    }

    /**
     * 新增库存管理-物品
     * 
     * @param gGoods 库存管理-物品
     * @return 结果
     */
    @Override
    public int insertGGoods(GGoods gGoods)
    {
        gGoods.setCreateTime(DateUtils.getNowDate());
        gGoods.setAmount(0L);
        return gGoodsMapper.insertGGoods(gGoods);
    }

    /**
     * 修改库存管理-物品
     * 
     * @param gGoods 库存管理-物品
     * @return 结果
     */
    @Override
    public int updateGGoods(GGoods gGoods)
    {
        gGoods.setUpdateTime(DateUtils.getNowDate());
        return gGoodsMapper.updateGGoods(gGoods);
    }

    /**
     * 批量删除库存管理-物品
     * 
     * @param ids 需要删除的库存管理-物品ID
     * @return 结果
     */
    @Override
    public int deleteGGoodsByIds(String[] ids)
    {
        return gGoodsMapper.deleteGGoodsByIds(ids);
    }

    /**
     * 删除库存管理-物品信息
     * 
     * @param id 库存管理-物品ID
     * @return 结果
     */
    @Override
    public int deleteGGoodsById(String id)
    {
        return gGoodsMapper.deleteGGoodsById(id);
    }

    /**
     * @describe
     * @author DongCL
     * @date 2021-01-18 14:39
     * @param
     * @return
     */
    @Override
    public List<GGoods> selectGGoodsByIdsList(List<String> goodIds) {
        return gGoodsMapper.selectGGoodsByIdsList(goodIds);
    }

    /** 获取驾驶员*/
    @Override
    public List<DDriver> selectDriversList() {
        return gGoodsMapper.selectDriversList();
    }
}
