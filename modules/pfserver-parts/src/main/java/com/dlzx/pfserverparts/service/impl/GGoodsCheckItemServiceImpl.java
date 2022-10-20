package com.dlzx.pfserverparts.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverparts.domain.GGoods;
import com.dlzx.pfserverparts.domain.GGoodsCheck;
import com.dlzx.pfserverparts.domain.GGoodsInventory;
import com.dlzx.pfserverparts.mapper.GGoodsCheckMapper;
import com.dlzx.pfserverparts.mapper.GGoodsInventoryMapper;
import com.dlzx.pfserverparts.mapper.GGoodsMapper;
import com.dlzx.system.api.model.LoginUser;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverparts.mapper.GGoodsCheckItemMapper;
import com.dlzx.pfserverparts.domain.GGoodsCheckItem;
import com.dlzx.pfserverparts.service.IGGoodsCheckItemService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 库存管理-盘点项Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-03
 */
@Service
public class GGoodsCheckItemServiceImpl implements IGGoodsCheckItemService 
{
    @Autowired
    private GGoodsCheckItemMapper gGoodsCheckItemMapper;

    @Autowired
    private GGoodsMapper gGoodsMapper;

    @Autowired
    private GGoodsCheckMapper gGoodsCheckMapper;

    @Autowired
    private GGoodsInventoryMapper gGoodsInventoryMapper;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询库存管理-盘点项
     * 
     * @param id 库存管理-盘点项ID
     * @return 库存管理-盘点项
     */
    @Override
    public GGoodsCheckItem selectGGoodsCheckItemById(String id)
    {
        return gGoodsCheckItemMapper.selectGGoodsCheckItemById(id);
    }

    public List<GGoodsCheckItem> selectGGoodsCheckItemByCheckId(String checkId) {
        return gGoodsCheckItemMapper.selectGGoodsCheckItemByCheckId(checkId);
    }

    /**
     * 查询库存管理-盘点项列表
     * 
     * @param gGoodsCheckItem 库存管理-盘点项
     * @return 库存管理-盘点项
     */
    @Override
    public List<GGoodsCheckItem> selectGGoodsCheckItemList(GGoodsCheckItem gGoodsCheckItem)
    {
        return gGoodsCheckItemMapper.selectGGoodsCheckItemList(gGoodsCheckItem);
    }

    /**
     * 新增库存管理-盘点项
     * 
     * @param gGoodsCheckItem 库存管理-盘点项
     * @return 结果
     */
    @Override
    public int insertGGoodsCheckItem(GGoodsCheckItem gGoodsCheckItem)
    {
        gGoodsCheckItem.setCreateTime(DateUtils.getNowDate());
        return gGoodsCheckItemMapper.insertGGoodsCheckItem(gGoodsCheckItem);
    }

    /**
     * 修改库存管理-盘点项
     * 
     * @param gGoodsCheckItem 库存管理-盘点项
     * @return 结果
     */
    @Override
    public int updateGGoodsCheckItem(GGoodsCheckItem gGoodsCheckItem)
    {
        gGoodsCheckItem.setUpdateTime(DateUtils.getNowDate());
        return gGoodsCheckItemMapper.updateGGoodsCheckItem(gGoodsCheckItem);
    }

    @Override
    public int insertList(List<GGoodsCheckItem> gGoodsCheckItem) {
        return gGoodsCheckItemMapper.insertList(gGoodsCheckItem);
    }

    /**
     * 批量删除库存管理-盘点项
     * 
     * @param ids 需要删除的库存管理-盘点项ID
     * @return 结果
     */
    @Override
    public int deleteGGoodsCheckItemByIds(String[] ids)
    {
        return gGoodsCheckItemMapper.deleteGGoodsCheckItemByIds(ids);
    }

    /**
     * 删除库存管理-盘点项信息
     * 
     * @param id 库存管理-盘点项ID
     * @return 结果
     */
    @Override
    public int deleteGGoodsCheckItemById(String id)
    {
        return gGoodsCheckItemMapper.deleteGGoodsCheckItemById(id);
    }


    /**
     * @describe 提交
     * @author DongCL
     * @date 2021-01-18 15:30
     * @param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int submitCheckItem(GGoodsCheck gGoodsCheck) {

        try {
            LoginUser loginUser = tokenService.getLoginUser();

            List<String> goodIds = gGoodsCheck.getgGoodsCheckItems().stream().map(GGoodsCheckItem::getGoodsId).collect(Collectors.toList());
            List<GGoods> gGoods = gGoodsMapper.selectGGoodsByIdsList(goodIds);


            List<GGoodsInventory> gGoodsInventories = new ArrayList<>();
            List<GGoods> gGoodsList = new ArrayList<>();

            // 根据盈亏情况插入出入库数据
            for (GGoodsCheckItem gGoodsCheckItem : gGoodsCheck.getgGoodsCheckItems()) {

                GGoods gGood = gGoods.stream().filter(g -> g.getId().equals(gGoodsCheckItem.getGoodsId())).findFirst().orElse(new GGoods());

                // 更新商品
                GGoods updateGood = new GGoods();
                updateGood.setId(gGood.getId());

                // 插入库存流水
                GGoodsInventory gGoodsInventory = new GGoodsInventory();
                if (gGoodsCheckItem.getStatus()==1){
                    //盘盈 入库
                    gGoodsInventory.setType(2L);
                    gGoodsInventory.setCode(DateUtils.getCode(2L));
                    gGoodsInventory.setInventoryType(2L);

                    // 更新物品数量
//                    updateGood.setAmount(gGood.getAmount() + gGoodsCheckItem.getDifference());
                }else if (gGoodsCheckItem.getStatus()==2){
                    //盘亏 出库
                    gGoodsInventory.setType(1L);
                    gGoodsInventory.setCode(DateUtils.getCode(1L));
                    gGoodsInventory.setInventoryType(5L);

                    // 更新物品数量（前端传的负数,减去绝对值）
//                    updateGood.setAmount(gGood.getAmount() - Math.abs(gGoodsCheckItem.getDifference()));
                }else {
                    gGoodsInventory.setType(3L); //平

                    // 保持数量
//                    updateGood.setAmount(gGood.getAmount());
                }
                // update by 5.7 盘点不更新商品库存数量
                updateGood.setAmount(gGood.getAmount());
                // 盘点
                gGoodsInventory.setId(UUID.randomUUID()+"");
                gGoodsInventory.setGoodsTypeId(gGood.getGoodsTypeId());
                gGoodsInventory.setGoodsTypeName(gGood.getGoodsTypeName());
                gGoodsInventory.setGoodsId(gGood.getId());
                gGoodsInventory.setGoodsCode(gGood.getCode());
                gGoodsInventory.setGoodsName(gGood.getName());
                gGoodsInventory.setSpecification(gGood.getSpecification());
                gGoodsInventory.setUnit(gGood.getUnit());
                gGoodsInventory.setUnitPrice(gGood.getUnitPrice());
                gGoodsInventory.setSupplier(gGood.getSupplier());
                gGoodsInventory.setInventoryNumber(gGoodsCheckItem.getDifference());
                gGoodsInventory.setCreateBy(loginUser.getUsername());

                gGoodsList.add(updateGood);
                gGoodsInventories.add(gGoodsInventory);
            }
            gGoodsMapper.updateList(gGoodsList);
            // update by 5.6
//            gGoodsInventoryMapper.insertList(gGoodsInventories);

            gGoodsCheck.setStatus(2L);
            gGoodsCheck.setUpdateBy(loginUser.getUsername());
            gGoodsCheck.setCreateTime(new Date());
            return gGoodsCheckMapper.updateGGoodsCheck(gGoodsCheck);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @describe 检查项编辑
     * @author DongCL
     * @date 2021-01-21 18:17
     * @param
     * @return
     */
    @Override
    public int updateCheckItem(GGoodsCheckItem gGoodsCheckItem) {

        GGoodsCheckItem gGoodsCheckItem1 = gGoodsCheckItemMapper.selectGGoodsCheckItemById(gGoodsCheckItem.getId());
        // 当前库存量
        Long i = null;
        i = gGoodsCheckItem1.getCurrentQuantity();
        // 盘点库存量
        Long j = null;
        j = gGoodsCheckItem.getCheckQuantity();
        Long l = j - i;
        gGoodsCheckItem.setDifference(l);
        if (l > 0) {
            gGoodsCheckItem.setStatus(Long.valueOf(1));
        } else if (l < 0) {
            gGoodsCheckItem.setStatus(Long.valueOf(2));
        } else {
            gGoodsCheckItem.setStatus(Long.valueOf(3));
        }
        LoginUser loginUser = tokenService.getLoginUser();
        gGoodsCheckItem.setUpdateBy(loginUser.getUsername());
        return gGoodsCheckItemMapper.updateGGoodsCheckItem(gGoodsCheckItem);
    }

    @Override
    public List<GGoodsCheckItem> queryGGoodsCheckItemList(GGoodsCheckItem gGoodsCheckItem) {

        // todo 盘点后如果有对物品库存的修改，需要同步到盘点详情中，在这里统一做修改同步
//        GGoodsCheck gGoodsCheck = gGoodsCheckMapper.selectGGoodsCheckById(gGoodsCheckItem.getCheckId());

//        List<GGoodsCheckItem> gGoodsCheckItems = gGoodsCheckItemMapper.selectGGoodsCheckItemByCheckId(gGoodsCheck.getId());
//        List<String> goodIds = gGoodsCheckItems.stream().map(GGoodsCheckItem::getGoodsId).collect(Collectors.toList());
//        List<GGoods> gGoods = gGoodsMapper.queryInList(goodIds);
//        for (GGoodsCheckItem gGoodsCheckData : gGoodsCheckItems) {
//            GGoods gGoodData = gGoods.stream().filter(g -> g.getId().equals(gGoodsCheckData.getGoodsId())).findFirst().orElse(null);
//            if (gGoodData != null){
//                gGoodsCheckData.setCurrentQuantity(gGoodData.getAmount());
//            }
//        }
        // 批量修改
//        gGoodsCheckItemMapper.updateList(gGoodsCheckItems);

        return gGoodsCheckItemMapper.selectGGoodsCheckItemList(gGoodsCheckItem);
    }
}
