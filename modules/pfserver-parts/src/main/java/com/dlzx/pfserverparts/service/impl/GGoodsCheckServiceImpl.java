package com.dlzx.pfserverparts.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverparts.domain.GGoods;
import com.dlzx.pfserverparts.domain.GGoodsCheckItem;
import com.dlzx.pfserverparts.domain.GGoodsType;
import com.dlzx.pfserverparts.mapper.GGoodsCheckItemMapper;
import com.dlzx.pfserverparts.mapper.GGoodsMapper;
import com.dlzx.pfserverparts.service.IGGoodsCheckItemService;
import com.dlzx.pfserverparts.service.IGGoodsService;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverparts.mapper.GGoodsCheckMapper;
import com.dlzx.pfserverparts.domain.GGoodsCheck;
import com.dlzx.pfserverparts.service.IGGoodsCheckService;

/**
 * 库存管理-盘点Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-03
 */
@Service
public class GGoodsCheckServiceImpl implements IGGoodsCheckService 
{
    @Autowired
    private GGoodsCheckMapper gGoodsCheckMapper;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private GGoodsCheckItemMapper gGoodsCheckItemMapper;
    @Autowired
    private GGoodsMapper gGoodsMapper;

    /**
     * 查询库存管理-盘点
     * 
     * @param id 库存管理-盘点ID
     * @return 库存管理-盘点
     */
    @Override
    public GGoodsCheck selectGGoodsCheckById(String id)
    {
        return gGoodsCheckMapper.selectGGoodsCheckById(id);
    }

    @Override
    public List<GGoodsCheck> selectGGoodsCheckByCheckId(String id) {
        return gGoodsCheckMapper.selectGGoodsCheckByCheckId(id);
    }


    /**
     * 查询库存管理-盘点列表
     * 
     * @param gGoodsCheck 库存管理-盘点
     * @return 库存管理-盘点
     */
    @Override
    public List<GGoodsCheck> selectGGoodsCheckList(GGoodsCheck gGoodsCheck)
    {
        return gGoodsCheckMapper.selectGGoodsCheckList(gGoodsCheck);
    }

    @Override
    public List<GGoodsCheck> countAll(String goodsTypeId) {
        return gGoodsCheckMapper.countAll(goodsTypeId);
    }

    /**
     * 根据goodsTypeId查询主键ID
     * @param goodsTypeId
     * @return
     */
    @Override
    public String selectGGoodsCheckByGoodsTypeId(String goodsTypeId) {
        return gGoodsCheckMapper.selectGGoodsCheckByGoodsTypeId(goodsTypeId);
    }


    /**
     * 新增库存管理-盘点
     * 
     * @param gGoodsCheck 库存管理-盘点
     * @return 结果
     */
    @Override
    public int insertGGoodsCheck(GGoodsCheck gGoodsCheck)
    {
        gGoodsCheck.setCreateTime(DateUtils.getNowDate());
        return gGoodsCheckMapper.insertGGoodsCheck(gGoodsCheck);
    }

    /**
     * 修改库存管理-盘点
     * 
     * @param gGoodsCheck 库存管理-盘点
     * @return 结果
     */
    @Override
    public int updateGGoodsCheck(GGoodsCheck gGoodsCheck)
    {
        gGoodsCheck.setUpdateTime(DateUtils.getNowDate());
        return gGoodsCheckMapper.updateGGoodsCheck(gGoodsCheck);
    }

    /**
     * 批量删除库存管理-盘点
     * 
     * @param ids 需要删除的库存管理-盘点ID
     * @return 结果
     */
    @Override
    public int deleteGGoodsCheckByIds(String[] ids)
    {
        return gGoodsCheckMapper.deleteGGoodsCheckByIds(ids);
    }

    /**
     * 删除库存管理-盘点信息
     * 
     * @param id 库存管理-盘点ID
     * @return 结果
     */
    @Override
    public int deleteGGoodsCheckById(String id)
    {
        return gGoodsCheckMapper.deleteGGoodsCheckById(id);
    }


    /**
     * @describe 新增盘点
     * @author DongCL
     * @date 2021-01-21 18:03
     * @param
     * @return
     */
    @Override
    public String submitGGoodsCheck(GGoodsType gGoodsType) {

        // 获取是否存在重复
        String id = gGoodsType.getId();
        List<GGoodsCheck> gGoodsChecks = gGoodsCheckMapper.countAll(id);
        if (gGoodsChecks.size()>0){
            throw new CustomException("请提交当前盘点后，再创建新的盘点！");
        }

        // 存在的话用gGoodsType.getId()获取到g_goods_check的主键ID返回给前端
        // 因为下一个页面/K15CheckList/{checkId}需要用到checkId来查询包含的数据
        List<GGoods> gGoodsList = gGoodsMapper.selectGGoodsByTypeGoodsTypeIdList(id);
        if (gGoodsList==null || gGoodsList.size()==0){
            throw new CustomException("当前类别下没有物品！");
        }

        // 不存在时往盘点管理K14列表中添加一条
        GGoodsCheck gGoodsCheck = new GGoodsCheck();
        if (gGoodsChecks.size() < 1) {
            gGoodsCheck.setGoodsTypeId(gGoodsType.getId());
            gGoodsCheck.setGoodsTypeName(gGoodsType.getName());
            // code RK+日期+四位随机
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            Random random = new Random();
            int i1 = 1000;
            int num = random.nextInt(8999)+ i1;
            gGoodsCheck.setCode("PD"+simpleDateFormat.format(date) + num);
            gGoodsCheck.setId(UUID.randomUUID().toString());
            LoginUser loginUser = tokenService.getLoginUser();
            gGoodsCheck.setCreateBy(loginUser.getUsername());
            gGoodsCheck.setStatus(Long.valueOf(1));
            gGoodsCheck.setDeleteFlag(Long.valueOf(1));
            gGoodsCheckMapper.insertGGoodsCheck(gGoodsCheck);
        }

        List<GGoodsCheckItem> list = new ArrayList<>();
        // 取出查询ggoods得到的数据 id code name specification unit amount存到gGoodsCheckItem表里
        for (GGoods gGoods : gGoodsList) {
            GGoodsCheckItem gGoodsCheckItem = new GGoodsCheckItem();
            gGoodsCheckItem.setId(UUID.randomUUID().toString());
            gGoodsCheckItem.setCheckId(gGoodsCheck.getId());
            gGoodsCheckItem.setGoodsId(gGoods.getId());
            gGoodsCheckItem.setGoodsCode(gGoods.getCode());
            gGoodsCheckItem.setGoodsName(gGoods.getName());
            gGoodsCheckItem.setSpecification(gGoods.getSpecification());
            gGoodsCheckItem.setUnit(gGoods.getUnit());
            gGoodsCheckItem.setCurrentQuantity(gGoods.getAmount());
            gGoodsCheckItem.setCreateBy(tokenService.getLoginUser().getUsername());
            list.add(gGoodsCheckItem);
        }
        if (list.size()>0){
            gGoodsCheckItemMapper.insertList(list);
        }
        return gGoodsCheck.getId();
    }
}
