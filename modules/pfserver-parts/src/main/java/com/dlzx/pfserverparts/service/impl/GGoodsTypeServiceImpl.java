package com.dlzx.pfserverparts.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverparts.mapper.GGoodsTypeMapper;
import com.dlzx.pfserverparts.domain.GGoodsType;
import com.dlzx.pfserverparts.service.IGGoodsTypeService;

/**
 * 库存管理-物品类别Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-01
 */
@Service
public class GGoodsTypeServiceImpl implements IGGoodsTypeService
{
    @Autowired
    private GGoodsTypeMapper gGoodsTypeMapper;

    /**
     * 查询库存管理-物品类别
     * 
     * @param id 库存管理-物品类别ID
     * @return 库存管理-物品类别
     */
    @Override
    public GGoodsType selectGGoodsTypeById(String id)
    {
        return gGoodsTypeMapper.selectGGoodsTypeById(id);
    }

    /**
     * 查询库存管理-物品类别列表
     * 
     * @param gGoodsType 库存管理-物品类别
     * @return 库存管理-物品类别
     */
    @Override
    public List<GGoodsType> selectGGoodsTypeList(GGoodsType gGoodsType)
    {
        return gGoodsTypeMapper.selectGGoodsTypeList(gGoodsType);
    }

    @Override
    public int countAll(String name) {
        return gGoodsTypeMapper.countAll(name);
    }

    @Override
    public int countAllEdit(String name) {
        return gGoodsTypeMapper.countAllEdit(name);
    }

    /**
     * 新增库存管理-物品类别
     * 
     * @param gGoodsType 库存管理-物品类别
     * @return 结果
     */
    @Override
    public int insertGGoodsType(GGoodsType gGoodsType)
    {
        gGoodsType.setCreateTime(DateUtils.getNowDate());
        return gGoodsTypeMapper.insertGGoodsType(gGoodsType);
    }

    /**
     * 修改库存管理-物品类别
     * 
     * @param gGoodsType 库存管理-物品类别
     * @return 结果
     */
    @Override
    public int updateGGoodsType(GGoodsType gGoodsType)
    {
        gGoodsType.setUpdateTime(DateUtils.getNowDate());
        return gGoodsTypeMapper.updateGGoodsType(gGoodsType);
    }

    /**
     * 批量删除库存管理-物品类别
     * 
     * @param ids 需要删除的库存管理-物品类别ID
     * @return 结果
     */
    @Override
    public int deleteGGoodsTypeByIds(String[] ids)
    {
        return gGoodsTypeMapper.deleteGGoodsTypeByIds(ids);
    }

    /**
     * 删除库存管理-物品类别信息
     * 
     * @param id 库存管理-物品类别ID
     * @return 结果
     */
    @Override
    public int deleteGGoodsTypeById(String id)
    {
        return gGoodsTypeMapper.deleteGGoodsTypeById(id);
    }
}
