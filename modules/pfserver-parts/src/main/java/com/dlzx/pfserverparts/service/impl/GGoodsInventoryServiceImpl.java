package com.dlzx.pfserverparts.service.impl;

import java.util.List;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.pfserverparts.domain.excel.GGoodsInventoryInExcel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverparts.mapper.GGoodsInventoryMapper;
import com.dlzx.pfserverparts.domain.GGoodsInventory;
import com.dlzx.pfserverparts.service.IGGoodsInventoryService;

/**
 * 库存管理-库存Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-02
 */
@Service

public class GGoodsInventoryServiceImpl implements IGGoodsInventoryService
{
    @Autowired
    private GGoodsInventoryMapper gGoodsInventoryMapper;

    /**
     * 查询库存管理-库存
     * 
     * @param id 库存管理-库存ID
     * @return 库存管理-库存
     */
    @Override
    public GGoodsInventory selectGGoodsInventoryById(String id)
    {
        return gGoodsInventoryMapper.selectGGoodsInventoryById(id);
    }

    /**
     * 查询库存管理-库存列表
     * 
     * @param gGoodsInventory 库存管理-库存
     * @return 库存管理-库存
     */
    @Override
    public List<GGoodsInventory> selectGGoodsInventoryList(GGoodsInventory gGoodsInventory)
    {
        switch(gGoodsInventory.getDetailType()){
            case 1 :
                // 正常入库
                gGoodsInventory.setType(2L);
                gGoodsInventory.setInventoryType(1L);
                break;
            case 2 :
                // 盘点入库
                gGoodsInventory.setType(2L);
                gGoodsInventory.setInventoryType(2L);
                break;
            case 3 :
                // 手动出库
                gGoodsInventory.setType(1L);
                gGoodsInventory.setInventoryType(3L);
                break;
            case 4 :
                // 领用出库
                gGoodsInventory.setType(1L);
                gGoodsInventory.setInventoryType(4L);
                break;
            case 5 :
                // 盘点出库
                gGoodsInventory.setType(1L);
                gGoodsInventory.setInventoryType(5L);
                break;
            case 6 :
                // 报废出库
                gGoodsInventory.setType(1L);
                gGoodsInventory.setInventoryType(6L);
                break;
            default :
        }
        return gGoodsInventoryMapper.selectGGoodsInventoryList(gGoodsInventory);
    }

    /**
     * 入库管理出库管理下拉选
     * @return
     */
    @Override
    public List<GGoodsInventory> selectAllGoodsTypeName(String type) {
        return gGoodsInventoryMapper.selectAllGoodsTypeName(type);
    }

    @Override
    public List<GGoodsInventory> selectAllGoodsTypeNameKC() {
        return gGoodsInventoryMapper.selectAllGoodsTypeNameKC();
    }
    /**
     * 报废下拉选
     * @return
     */
    @Override
    public List<GGoodsInventory> selectAllGoodsTypeNameBF() {
        return gGoodsInventoryMapper.selectAllGoodsTypeNameBF();
    }

    @Override
    public int countAll(String goodsName) {
        return gGoodsInventoryMapper.countAll(goodsName);
    }

    @Override
    public int countAllEdit(String goodsId, String goodsName) {
        return gGoodsInventoryMapper.countAllEdit(goodsId, goodsName);
    }


    /**
     * 新增库存管理-库存
     * 
     * @param gGoodsInventory 库存管理-库存
     * @return 结果
     */
    @Override
    public int insertGGoodsInventory(GGoodsInventory gGoodsInventory)
    {
        gGoodsInventory.setCreateTime(DateUtils.getNowDate());
        return gGoodsInventoryMapper.insertGGoodsInventory(gGoodsInventory);
    }

    /**
     * 修改库存管理-库存
     * 
     * @param gGoodsInventory 库存管理-库存
     * @return 结果
     */
    @Override
    public int updateGGoodsInventory(GGoodsInventory gGoodsInventory)
    {
        gGoodsInventory.setUpdateTime(DateUtils.getNowDate());
        return gGoodsInventoryMapper.updateGGoodsInventory(gGoodsInventory);
    }

    /**
     * 批量删除库存管理-库存
     * 
     * @param ids 需要删除的库存管理-库存ID
     * @return 结果
     */
    @Override
    public int deleteGGoodsInventoryByIds(String[] ids)
    {
        return gGoodsInventoryMapper.deleteGGoodsInventoryByIds(ids);
    }

    /**
     * 删除库存管理-库存信息
     * 
     * @param id 库存管理-库存ID
     * @return 结果
     */
    @Override
    public int deleteGGoodsInventoryById(String id)
    {
        return gGoodsInventoryMapper.deleteGGoodsInventoryById(id);
    }

    /** 批量插入*/
    @Override
    public int insertList(List<GGoodsInventory> gGoodsInventories) {
        return gGoodsInventoryMapper.insertList(gGoodsInventories);
    }

    /**
     * @describe 库存管理-查询出库管理列表
     * @author DongCL
     * @date 2021-03-05 09:35
     * @param
     * @return
     */
    @Override
    public List<GGoodsInventory> selectGGoodsOutInventoryList(GGoodsInventory gGoodsInventory) {
        return gGoodsInventoryMapper.selectGGoodsOutInventoryList(gGoodsInventory);
    }

    /**
     * @describe 库存管理-查询库存明细列表
     * @author DongCL
     * @date 2021-03-05 09:45
     * @param
     * @return
     */
    @Override
    public List<GGoodsInventory> selectGGoodsInventoryDetailList(GGoodsInventory gGoodsInventory) {

        // 根据类型去做筛选
        switch(gGoodsInventory.getDetailType()){
            case 1 :
                // 正常入库
                gGoodsInventory.setType(2L);
                gGoodsInventory.setInventoryType(1L);
                break;
            case 2 :
                // 盘点入库
                gGoodsInventory.setType(2L);
                gGoodsInventory.setInventoryType(2L);
                break;
            case 3 :
                // 手动出库
                gGoodsInventory.setType(1L);
                gGoodsInventory.setInventoryType(3L);
                break;
            case 4 :
                // 领用出库
                gGoodsInventory.setType(1L);
                gGoodsInventory.setInventoryType(4L);
                break;
            case 5 :
                // 盘点出库
                gGoodsInventory.setType(1L);
                gGoodsInventory.setInventoryType(5L);
                break;
            case 6 :
                // 报废出库
                gGoodsInventory.setType(1L);
                gGoodsInventory.setInventoryType(6L);
                break;
            default :
        }
        return gGoodsInventoryMapper.selectGGoodsInventoryDetailList(gGoodsInventory);
    }
}
