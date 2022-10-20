package com.dlzx.pfserverparts.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.domain.SysExamineConfigParams;
import com.dlzx.common.security.service.DriverTokenService;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverparts.client.SystemClient;
import com.dlzx.pfserverparts.domain.GGoods;
import com.dlzx.pfserverparts.domain.GGoodsCheck;
import com.dlzx.pfserverparts.domain.GGoodsCheckItem;
import com.dlzx.pfserverparts.domain.GGoodsInventory;
import com.dlzx.pfserverparts.domain.GGoodsReceivingRecord;
import com.dlzx.pfserverparts.domain.GGoodsReceivingRecordDate;
import com.dlzx.pfserverparts.domain.GGoodsReceivingRecordItem;
import com.dlzx.pfserverparts.domain.GGoodsType;
import com.dlzx.pfserverparts.domain.ReviewSubmit;
import com.dlzx.pfserverparts.mapper.GGoodsCheckItemMapper;
import com.dlzx.pfserverparts.mapper.GGoodsCheckMapper;
import com.dlzx.pfserverparts.mapper.GGoodsInventoryMapper;
import com.dlzx.pfserverparts.mapper.GGoodsMapper;
import com.dlzx.pfserverparts.mapper.GGoodsReceivingRecordDateMapper;
import com.dlzx.pfserverparts.mapper.GGoodsReceivingRecordItemMapper;
import com.dlzx.pfserverparts.mapper.GGoodsReceivingRecordMapper;
import com.dlzx.pfserverparts.mapper.GGoodsTypeMapper;
import com.dlzx.pfserverparts.service.IGGoodsReceivingRecordService;
import com.dlzx.system.api.model.LoginDriver;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dlzx.pfserverparts.mapper.SysDictDataMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 库存管理-物品领用记录Service业务层处理
 * 
 * @author dlzx
 * @date 2020-12-09
 */
@Service
public class GGoodsReceivingRecordServiceImpl implements IGGoodsReceivingRecordService
{
    @Autowired
    private GGoodsReceivingRecordMapper gGoodsReceivingRecordMapper;

    @Autowired
    private GGoodsReceivingRecordItemMapper gGoodsReceivingRecordItemMapper;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    @Autowired
    private GGoodsMapper gGoodsMapper;

    @Autowired
    private GGoodsTypeMapper gGoodsTypeMapper;

    @Autowired
    private GGoodsReceivingRecordDateMapper gGoodsReceivingRecordDateMapper;

    @Autowired
    private GGoodsCheckMapper gGoodsCheckMapper;

    @Autowired
    private GGoodsCheckItemMapper gGoodsCheckItemMapper;

    @Autowired
    private GGoodsInventoryMapper gGoodsInventoryMapper;

    @Resource
    private DriverTokenService driverTokenService;

    @Autowired
    private SystemClient systemClient;

    @Autowired
    private TokenService tokenService;
    /**
     * 查询库存管理-物品领用记录
     * 
     * @param id 库存管理-物品领用记录ID
     * @return 库存管理-物品领用记录
     */
    @Override
    public GGoodsReceivingRecord selectGGoodsReceivingRecordById(String id)
    {
        return gGoodsReceivingRecordMapper.selectGGoodsReceivingRecordById(id);
    }

    /**
     * 根据ID连表查询
     * @param id
     * @return
     */
    @Override
    public List<GGoodsReceivingRecord> selectGGoodsReceivingRecordByGGRRId(String id) {
        return gGoodsReceivingRecordMapper.selectGGoodsReceivingRecordByGGRRId(id);
    }

    @Override
    public List<GGoodsReceivingRecord> selectGGoodsReceivingRecordByGGRRIId(String id) {
        return gGoodsReceivingRecordMapper.selectGGoodsReceivingRecordByGGRRIId(id);
    }

    @Override
    public List<GGoods> selectGGoodsReceivingRecordByByGoodsTypeId(String goodsTypeId) {
        // 根据物品类别ID查询 物品
        return gGoodsMapper.selectGGoodsByTypeGoodsTypeIdList(goodsTypeId);
    }

    @Override
    public List<GGoodsReceivingRecord> selectGGoodsReceivingRecordByByGoodsId(String goodsId) {
        return gGoodsReceivingRecordMapper.selectGGoodsReceivingRecordByGoodsId(goodsId);
    }

    @Override
    public List<GGoodsReceivingRecord> selectGGoodsReceivingRecordByGoodsIdOrDate(String goodsId) {
        return gGoodsReceivingRecordMapper.selectGGoodsReceivingRecordByGoodsIdOrDate(goodsId);
    }

    @Override
    public GGoodsReceivingRecord selectGGoodsReceivingRecordInfo(String id) {
        return gGoodsReceivingRecordMapper.selectGGoodsReceivingRecordInfo(id);
    }

    // M11轮胎费下拉选 规格
    @Override
    public List<GGoodsReceivingRecord> selectGGoodsReceivingRecordDrop() {
        return gGoodsReceivingRecordMapper.selectGGoodsReceivingRecordDrop();
    }

    @Override
    public List<GGoodsReceivingRecord> selectGGoodsReceivingRecordInfoList() {
        return gGoodsReceivingRecordMapper.selectGGoodsReceivingRecordInfoList();
    }

    @Override
    public List<GGoodsReceivingRecord> selectGGoodsReceivingRecordAmount(GGoodsReceivingRecord gGoodsReceivingRecord) {
        return gGoodsReceivingRecordMapper.selectGGoodsReceivingRecordAmount(gGoodsReceivingRecord);
    }

    /**
     * 修改库存管理-物品领用记录
     * 
     * @param gGoodsReceivingRecord 库存管理-物品领用记录
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int submitGGoodsReceivingRecord(GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        try {
            LoginDriver loginDriver = driverTokenService.getLoginDriver();
            gGoodsReceivingRecord.setStatus(1L);
            gGoodsReceivingRecord.setDriverId(loginDriver.getUserid());
            gGoodsReceivingRecord.setCreateBy(loginDriver.getUsername());

            if (gGoodsReceivingRecord.getReceiveDate() != null && gGoodsReceivingRecord.getReceiveDate().size()>0){

                // 领用日期
                List<Date> receiveDate = gGoodsReceivingRecord.getReceiveDate();
                List<GGoodsReceivingRecordDate> list = new ArrayList<>();
                for (Date date : receiveDate) {
                    GGoodsReceivingRecordDate gGoodsReceivingRecordDate = new GGoodsReceivingRecordDate();
                    gGoodsReceivingRecordDate.setReceiveDate(date);
                    gGoodsReceivingRecordDate.setId(UUID.randomUUID().toString());
                    gGoodsReceivingRecordDate.setRecordId(gGoodsReceivingRecord.getId());
                    list.add(gGoodsReceivingRecordDate);
                }
                if (list.size()>0){
                    // 删除之前的记录
                    gGoodsReceivingRecordDateMapper.deleteGoodsDateByRecordId(gGoodsReceivingRecord.getId());

                    // 生成记录
                    gGoodsReceivingRecordDateMapper.insertList(list);
                }
            }

            // 备件
            if (gGoodsReceivingRecord.getgGoodsReceivingRecordItems()!= null && gGoodsReceivingRecord.getgGoodsReceivingRecordItems().size()>0){
                List<GGoodsReceivingRecordItem> gGoodsReceivingRecordItems =new ArrayList<>();
                for (GGoodsReceivingRecordItem gGoodsReceivingRecordItem : gGoodsReceivingRecord.getgGoodsReceivingRecordItems()) {

                    // 更新item表
                    GGoodsReceivingRecordItem goodsReceivingRecordItem = new GGoodsReceivingRecordItem();
                    BeanUtils.copyProperties(gGoodsReceivingRecordItem,goodsReceivingRecordItem);
                    goodsReceivingRecordItem.setId(UUID.randomUUID().toString());
                    goodsReceivingRecordItem.setRecordId(gGoodsReceivingRecord.getId());
                    goodsReceivingRecordItem.setGoodsTypeId(gGoodsReceivingRecord.getGoodsTypeId());
                    goodsReceivingRecordItem.setGoodsTypeName(gGoodsReceivingRecord.getGoodsTypeName());
                    gGoodsReceivingRecordItems.add(goodsReceivingRecordItem);

                    // 减库存
                    GGoods gGoods = gGoodsMapper.selectGGoodsById(gGoodsReceivingRecordItem.getGoodsId());
                    if (gGoods.getAmount() < gGoodsReceivingRecordItem.getNumber()) {
                        throw new CustomException("此次填写数量已超过总库存数量,请重新填写！");
                    }
                    gGoods.setAmount(gGoods.getAmount() - gGoodsReceivingRecordItem.getNumber());
                    gGoodsMapper.updateGGoods(gGoods);
                }
                if (gGoodsReceivingRecordItems.size()>0){

                    // 删除之前的记录
                    gGoodsReceivingRecordItemMapper.deleteGoodsItemByRecordId(gGoodsReceivingRecord.getId());

                    // 生成记录
                    gGoodsReceivingRecordItemMapper.insertList(gGoodsReceivingRecordItems);
                }
            }

            // 审批
            this.setExamineTask(gGoodsReceivingRecord);

            gGoodsReceivingRecord.setUpdateTime(DateUtils.getNowDate());
            return gGoodsReceivingRecordMapper.updateGGoodsReceivingRecord(gGoodsReceivingRecord);
        } catch (BeansException e) {
            throw e;
        }
    }

    @Override
    public int updateGGoodsReceivingRecord(GGoodsReceivingRecord gGoodsReceivingRecord) {
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        GGoodsReceivingRecord gGoodsReceivingRecordData = gGoodsReceivingRecordMapper.selectGGoodsReceivingRecordById(gGoodsReceivingRecord.getId());
        if (gGoodsReceivingRecordData.getStatus()!= 2){
            throw new CustomException("审核未通过，无法领用！");
        }
        if(gGoodsReceivingRecordData.getGoodsId()!= null){
            GGoods gGoods = gGoodsMapper.selectGGoodsById(gGoodsReceivingRecordData.getGoodsId());
            if (gGoods == null ) {
                throw new CustomException("物品不存在，无法领用！");
            }
            // 创建领用出库明细
            GGoodsInventory gGoodsInventory = new GGoodsInventory();
            gGoodsInventory.setId(UUID.randomUUID()+"");
            gGoodsInventory.setInventoryType(4L);
            gGoodsInventory.setType(1L);
            gGoodsInventory.setInventoryTime(new Date());
            gGoodsInventory.setCode(DateUtils.getCode(3L));
            gGoodsInventory.setGoodsName(gGoods.getName());
            gGoodsInventory.setInventoryNumber(gGoodsReceivingRecordData.getNumber());
            gGoodsInventory.setUnitPrice(gGoods.getUnitPrice());
            gGoodsInventory.setUnit(gGoods.getUnit());
            gGoodsInventory.setSpecification(gGoods.getSpecification());
            gGoodsInventory.setGoodsCode(gGoods.getCode());
            gGoodsInventory.setGoodsTypeId(gGoods.getGoodsTypeId());
            gGoodsInventory.setGoodsTypeName(gGoods.getGoodsTypeName());
            gGoodsInventory.setGoodsId(gGoods.getId());
            gGoodsInventory.setCreateTime(new Date());
            gGoodsInventory.setCreateBy(loginDriver.getUsername());
            gGoodsInventory.setSupplier(gGoods.getSupplier()==null?"":gGoods.getSupplier());
            gGoodsInventory.setLicensePlate(gGoodsReceivingRecordData.getLicensePlate());
            gGoodsInventoryMapper.insertGGoodsInventory(gGoodsInventory);
        }else {

            GGoodsReceivingRecordItem gGoodsReceivingRecordItem = new GGoodsReceivingRecordItem();
            gGoodsReceivingRecordItem.setRecordId(gGoodsReceivingRecord.getId());
            List<GGoodsReceivingRecordItem> gGoodsReceivingRecordItemList = gGoodsReceivingRecordItemMapper.selectGGoodsReceivingRecordItemList(gGoodsReceivingRecordItem);
            List<String> goodsIds = gGoodsReceivingRecordItemList.stream().map(GGoodsReceivingRecordItem::getGoodsId).collect(Collectors.toList());
            List<GGoods> gGoods = gGoodsMapper.selectGGoodsByIdsList(goodsIds);
            gGoods.forEach(item->{
                // 创建领用出库明细
                GGoodsInventory gGoodsInventory = new GGoodsInventory();
                gGoodsInventory.setId(UUID.randomUUID()+"");
                gGoodsInventory.setInventoryType(4L);
                gGoodsInventory.setType(1L);
                gGoodsInventory.setInventoryTime(new Date());
                gGoodsInventory.setCode(DateUtils.getCode(3L));
                gGoodsInventory.setGoodsName(item.getName());
                gGoodsInventory.setSpecification(item.getSpecification());
                gGoodsInventory.setInventoryNumber(gGoodsReceivingRecordItemList
                        .stream()
                        .filter(f->item.getId().equals(f.getGoodsId()))
                        .findFirst()
                        .orElse(new GGoodsReceivingRecordItem())
                        .getNumber());
                gGoodsInventory.setUnitPrice(item.getUnitPrice());
                gGoodsInventory.setUnit(item.getUnit());
                gGoodsInventory.setGoodsCode(item.getCode());
                gGoodsInventory.setGoodsTypeId(item.getGoodsTypeId());
                gGoodsInventory.setGoodsTypeName(item.getGoodsTypeName());
                gGoodsInventory.setGoodsId(item.getId());
                gGoodsInventory.setCreateTime(new Date());
                gGoodsInventory.setSupplier(item.getSupplier()==null?"":item.getSupplier());
                gGoodsInventory.setCreateBy(loginDriver.getUsername());
                gGoodsInventory.setLicensePlate(gGoodsReceivingRecordData.getLicensePlate());
                gGoodsInventoryMapper.insertGGoodsInventory(gGoodsInventory);
            });
        }
        // 更新领用日期
        gGoodsReceivingRecord.setReceiveTime(new Date());
        gGoodsReceivingRecord.setStatus(4L);
        return gGoodsReceivingRecordMapper.updateGGoodsReceivingRecord(gGoodsReceivingRecord);
    }

    @Override
    public AjaxResult checkCInsuranceVehicle(GGoodsReceivingRecord gGoodsReceivingRecord) {
        // 审核校验
        ReviewSubmit reviewSubmit = new ReviewSubmit();
        reviewSubmit.setRelationId(gGoodsReceivingRecord.getId());
        reviewSubmit.setReviewRemarks(gGoodsReceivingRecord.getReviewRemarks());
        reviewSubmit.setUserId(gGoodsReceivingRecord.getUserid()+"");
        reviewSubmit.setStatus(gGoodsReceivingRecord.getStatus());
        Map map = systemClient.reviewCommon(reviewSubmit);
        if (map.get("code").equals(1) || map.get("code").equals(2)){
            gGoodsReceivingRecord.setStatus(1L);
            return AjaxResult.success(map.get("msg").toString());
        }
        // 已通过
        if (map.get("code").equals(3)){
            gGoodsReceivingRecord.setStatus(2L);
        }
        // 已驳回
        if (map.get("code").equals(4)){
            // 如果关联的备件表下有数据，则循环回退数量
            GGoodsReceivingRecordItem gGoodsReceivingRecordItem = new GGoodsReceivingRecordItem();
            gGoodsReceivingRecordItem.setRecordId(gGoodsReceivingRecord.getId());
            List<GGoodsReceivingRecordItem> gGoodsReceivingRecordItems = gGoodsReceivingRecordItemMapper.selectGGoodsReceivingRecordItemList(gGoodsReceivingRecordItem);
            if (gGoodsReceivingRecordItems.size()>0){

                List<GGoods> gGoodsList = new ArrayList<>();
                for (GGoodsReceivingRecordItem goodsReceivingRecordItem : gGoodsReceivingRecordItems) {
                    // 增库存
                    GGoods gGoods = gGoodsMapper.selectGGoodsById(goodsReceivingRecordItem.getGoodsId());
                    // 回退申请时减少的数量
                    gGoods.setAmount(gGoods.getAmount() + goodsReceivingRecordItem.getNumber());

                    gGoodsList.add(gGoods);
                }
                gGoodsMapper.updateList(gGoodsList);
            }else {
                GGoodsReceivingRecord goodsReceivingRecord = gGoodsReceivingRecordMapper.selectGGoodsReceivingRecordInfo(gGoodsReceivingRecord.getId());

                // 增库存
                GGoods gGoods = gGoodsMapper.selectGGoodsById(goodsReceivingRecord.getGoodsId());
                // 回退申请时减少的数量
                gGoods.setAmount(gGoods.getAmount() + goodsReceivingRecord.getNumber());
                gGoodsMapper.updateGGoods(gGoods);
            }
            gGoodsReceivingRecord.setStatus(3L);
        }
        gGoodsReceivingRecordMapper.checkCInsuranceVehicle(gGoodsReceivingRecord);
        return AjaxResult.success(map.get("msg").toString());
    }

    /**
     * 批量删除库存管理-物品领用记录
     * 
     * @param ids 需要删除的库存管理-物品领用记录ID
     * @return 结果
     */
    @Override
    public int deleteGGoodsReceivingRecordByIds(String[] ids)
    {
        return gGoodsReceivingRecordMapper.deleteGGoodsReceivingRecordByIds(ids);
    }

    /**
     * 删除库存管理-物品领用记录信息
     * 
     * @param id 库存管理-物品领用记录ID
     * @return 结果
     */
    @Override
    public int deleteGGoodsReceivingRecordById(String id)
    {
        return gGoodsReceivingRecordMapper.deleteGGoodsReceivingRecordById(id);
    }

    /**
     * @describe 撤回
     * @author DongCL
     * @date 2021-01-17 15:57
     * @param
     * @return
     */
    @Override
    public int recallGGoodsReceivingRecord(GGoodsReceivingRecord gGoodsReceivingRecord) {
        GGoodsReceivingRecord gGoodsReceivingRecordData = gGoodsReceivingRecordMapper.selectGGoodsReceivingRecordById(gGoodsReceivingRecord.getId());

        // 如果关联的备件表下有数据，则循环回退数量
        GGoodsReceivingRecordItem gGoodsReceivingRecordItem = new GGoodsReceivingRecordItem();
        gGoodsReceivingRecordItem.setRecordId(gGoodsReceivingRecord.getId());
        List<GGoodsReceivingRecordItem> gGoodsReceivingRecordItems = gGoodsReceivingRecordItemMapper.selectGGoodsReceivingRecordItemList(gGoodsReceivingRecordItem);
        if (gGoodsReceivingRecordItems.size()>0){

            List<GGoods> gGoodsList = new ArrayList<>();
            for (GGoodsReceivingRecordItem goodsReceivingRecordItem : gGoodsReceivingRecordItems) {
                // 增库存
                GGoods gGoods = gGoodsMapper.selectGGoodsById(goodsReceivingRecordItem.getGoodsId());
                // 回退申请时减少的数量
                gGoods.setAmount(gGoods.getAmount() + goodsReceivingRecordItem.getNumber());

                gGoodsList.add(gGoods);
            }
            gGoodsMapper.updateList(gGoodsList);

        }else {

            // 增库存
            GGoods gGoods = gGoodsMapper.selectGGoodsById(gGoodsReceivingRecordData.getGoodsId());
            // 回退申请时减少的数量
            gGoods.setAmount(gGoods.getAmount() + gGoodsReceivingRecordData.getNumber());
            gGoodsMapper.updateGGoods(gGoods);
        }
        // 已撤回
        gGoodsReceivingRecord.setStatus(5L);
        return gGoodsReceivingRecordMapper.updateGGoodsReceivingRecord(gGoodsReceivingRecord);
    }


    /**
     * @describe
     * @author DongCL
     * @date 2021-01-19 14:34
     * @return
     */
    @Override
    public List<GGoodsType> queryGoodTypeList() {
        return  gGoodsTypeMapper.queryIsdeleteGoodTypeList();
    }


    /**
     * 查询库存管理-物品领用记录列表
     *
     * @param gGoodsReceivingRecord 库存管理-物品领用记录
     * @return 库存管理-物品领用记录
     */
    @Override
    public List<GGoodsReceivingRecord> selectGGoodsReceivingRecordList(GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        return gGoodsReceivingRecordMapper.selectGGoodsReceivingRecordList(gGoodsReceivingRecord);
    }

   /**
    * @describe 尿素
    * @author DongCL
    * @date 2021-01-19 17:10
    * @param
    * @return
    */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertGGoodsReceivingRecord(GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        try {
            // 基础值
            this.setDetail(gGoodsReceivingRecord);
            return gGoodsReceivingRecordMapper.insertGGoodsReceivingRecord(gGoodsReceivingRecord);
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * @describe 工作餐
     * @author DongCL
     * @date 2021-01-19 17:03
     * @param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertGGoodsReceivingRecordDate(GGoodsReceivingRecord gGoodsReceivingRecord) {

        try {
            // 基础值
            this.setDetail(gGoodsReceivingRecord);

            // 领用日期
            List<Date> receiveDate = gGoodsReceivingRecord.getReceiveDate();
            List<GGoodsReceivingRecordDate> list = new ArrayList<>();
            for (Date date : receiveDate) {
                GGoodsReceivingRecordDate gGoodsReceivingRecordDate = new GGoodsReceivingRecordDate();
                gGoodsReceivingRecordDate.setReceiveDate(date);
                gGoodsReceivingRecordDate.setId(UUID.randomUUID().toString());
                gGoodsReceivingRecordDate.setRecordId(gGoodsReceivingRecord.getId());
                list.add(gGoodsReceivingRecordDate);
            }
            gGoodsReceivingRecordDateMapper.insertList(list);

            return gGoodsReceivingRecordMapper.insertGGoodsReceivingRecord(gGoodsReceivingRecord);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @describe 轮胎 / 低值易耗品
     * @author DongCL
     * @date 2021-01-19 17:12
     * @param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertGGoodsReceivingRecordTyre(GGoodsReceivingRecord gGoodsReceivingRecord) {
        try {
            // 基础值
            this.setDetail(gGoodsReceivingRecord);
            return gGoodsReceivingRecordMapper.insertGGoodsReceivingRecord(gGoodsReceivingRecord);
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * @describe 备件
     * @author DongCL
     * @date 2021-01-19 17:21
     * @param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertGGoodsReceivingRecordItem(GGoodsReceivingRecord gGoodsReceivingRecord) {

        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        gGoodsReceivingRecord.setId(UUID.randomUUID()+"");
        gGoodsReceivingRecord.setStatus(1L);
        gGoodsReceivingRecord.setDriverId(loginDriver.getUserid());
        gGoodsReceivingRecord.setCreateBy(loginDriver.getUsername());
        gGoodsReceivingRecord.setCreateTime(DateUtils.getNowDate());
        gGoodsReceivingRecord.setGoodsName("备件");

        try {
            // 备件
            if (gGoodsReceivingRecord.getgGoodsReceivingRecordItems()!= null){
                List<GGoodsReceivingRecordItem> gGoodsReceivingRecordItems =new ArrayList<>();

                if (gGoodsReceivingRecord.getgGoodsReceivingRecordItems().size()<=0){
                    throw new CustomException("请选择备件！");
                }
                for (GGoodsReceivingRecordItem gGoodsReceivingRecordItem : gGoodsReceivingRecord.getgGoodsReceivingRecordItems()) {

                    // 更新item表
                    GGoodsReceivingRecordItem goodsReceivingRecordItem = new GGoodsReceivingRecordItem();
                    BeanUtils.copyProperties(gGoodsReceivingRecordItem,goodsReceivingRecordItem);
                    goodsReceivingRecordItem.setId(UUID.randomUUID().toString());
                    goodsReceivingRecordItem.setRecordId(gGoodsReceivingRecord.getId());
                    goodsReceivingRecordItem.setGoodsTypeId(gGoodsReceivingRecord.getGoodsTypeId());
                    goodsReceivingRecordItem.setGoodsTypeName(gGoodsReceivingRecord.getGoodsTypeName());
                    gGoodsReceivingRecordItems.add(goodsReceivingRecordItem);

                    // 减库存
                    GGoods gGoods = gGoodsMapper.selectGGoodsById(gGoodsReceivingRecordItem.getGoodsId());
                    if (gGoods == null){
                        throw new CustomException("物品不存在！");
                    }
                    if (gGoods.getAmount() < gGoodsReceivingRecordItem.getNumber()) {
                        throw new CustomException("此次填写数量已超过总库存数量,请重新填写！");
                    }
                    gGoods.setAmount(gGoods.getAmount() - gGoodsReceivingRecordItem.getNumber());
                    gGoodsMapper.updateGGoods(gGoods);
                }
                if (gGoodsReceivingRecordItems.size()>0){
                    gGoodsReceivingRecordItemMapper.insertList(gGoodsReceivingRecordItems);
                }
            }

            // 审批
            this.setExamineTask(gGoodsReceivingRecord);

            gGoodsReceivingRecord.setDeleteFlag(1L);
            return gGoodsReceivingRecordMapper.insertGGoodsReceivingRecord(gGoodsReceivingRecord);
        } catch (BeansException e) {
            throw e;
        }
    }

    @Override
    public GGoodsReceivingRecord queryGGoodsReceivingRecordById(String id) {
        GGoodsReceivingRecord gGoodsReceivingRecord = gGoodsReceivingRecordMapper.queryGGoodsReceivingRecordById(id);

        // 根据记录id去查询备件列表
        GGoodsReceivingRecordItem gGoodsReceivingRecordItem = new GGoodsReceivingRecordItem();
        gGoodsReceivingRecordItem.setRecordId(id);
        List<GGoodsReceivingRecordItem> gGoodsReceivingRecordItems = gGoodsReceivingRecordItemMapper.queryGGoodsReceivingRecordItemList(gGoodsReceivingRecordItem);
        if (gGoodsReceivingRecordItems.size()>0){
            gGoodsReceivingRecord.setgGoodsReceivingRecordItems(gGoodsReceivingRecordItems);
        }

        // 根据记录id去查询领用日期
        GGoodsReceivingRecordDate gGoodsReceivingRecordDate = new GGoodsReceivingRecordDate();
        gGoodsReceivingRecordDate.setRecordId(id);
        List<GGoodsReceivingRecordDate> gGoodsReceivingRecordDates = gGoodsReceivingRecordDateMapper.selectGGoodsReceivingRecordDateList(gGoodsReceivingRecordDate);
        if (gGoodsReceivingRecordDates.size()>0){
            gGoodsReceivingRecord.setgGoodsReceivingRecordDates(gGoodsReceivingRecordDates);
        }

        GGoodsType gGoodsType = gGoodsTypeMapper.selectGGoodsTypeById(gGoodsReceivingRecord.getGoodsTypeId());
        gGoodsReceivingRecord.setGoodsType(gGoodsType.getType());
        return gGoodsReceivingRecord;
    }


    /**
     * @describe 物品领用列表（用于驾驶员端）
     * @author DongCL
     * @date 2021-01-20 11:41
     * @param
     * @return
     */
    @Override
    public List<GGoodsReceivingRecord> queryGGoodsReceivingRecordList(GGoodsReceivingRecord gGoodsReceivingRecord) {
        // 获取驾驶员信息  todo —— udpate by 2021 3.11 19.36
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        gGoodsReceivingRecord.setDriverId(loginDriver.getUserid());
        return gGoodsReceivingRecordMapper.queryGGoodsReceivingRecordList(gGoodsReceivingRecord);
    }


    /**
     * @describe 盘点列表
     * @author DongCL
     * @date 2021-01-21 17:42
     * @param
     * @return
     */
    @Override
    public List<GGoodsCheck> queryGGoodsCheckList(GGoodsCheck gGoodsCheck) {
        return gGoodsCheckMapper.queryAdminGGoodsCheckList(gGoodsCheck);
    }

    /**
     * @describe 盘点详情
     * @author DongCL
     * @date 2021-01-21 17:42
     * @param
     * @return
     */
    @Override
    public GGoodsCheck queryGGoodsCheckById(GGoodsCheck gGoodsCheckData) {

        // todo 盘点后如果有对物品库存的修改，需要同步到盘点详情中，在这里统一做修改同步
        GGoodsCheck gGoodsCheck = gGoodsCheckMapper.selectGGoodsCheckById(gGoodsCheckData.getId());
//
//        List<GGoodsCheckItem> gGoodsCheckItems = gGoodsCheckItemMapper.selectGGoodsCheckItemByCheckId(gGoodsCheck.getId());
//        List<String> goodIds = gGoodsCheckItems.stream().map(GGoodsCheckItem::getGoodsId).collect(Collectors.toList());
//        List<GGoods> gGoods = gGoodsMapper.queryInList(goodIds);
//        for (GGoodsCheckItem gGoodsCheckItem : gGoodsCheckItems) {
//            GGoods gGoodData = gGoods.stream().filter(g -> g.getId().equals(gGoodsCheckItem.getGoodsId())).findFirst().orElse(null);
//            if (gGoodData != null){
//                gGoodsCheckItem.setCurrentQuantity(gGoodData.getAmount());
//            }
//        }
//        gGoodsCheckItemMapper.updateList(gGoodsCheckItems);

        if (gGoodsCheck != null){
            GGoodsCheckItem gGoodsCheckItem = new GGoodsCheckItem();
            gGoodsCheckItem.setCheckId(gGoodsCheck.getId());
            if (!StringUtils.isEmpty(gGoodsCheckData.getGoodName())){
                gGoodsCheckItem.setGoodsName(gGoodsCheckData.getGoodName());
            }
            gGoodsCheck.setgGoodsCheckItems(gGoodsCheckItemMapper.selectGGoodsCheckItemList(gGoodsCheckItem));
        }
        return gGoodsCheck;
    }


    /**
     * @describe 物品领用列表(管理员端)
     * @author DongCL
     * @date 2021-01-20 11:41
     * @param
     * @return
     */
    @Override
    public List<GGoodsReceivingRecord> queryAdminGGoodsReceivingRecordList(GGoodsReceivingRecord gGoodsReceivingRecord) {
        return gGoodsReceivingRecordMapper.queryGGoodsReceivingRecordList(gGoodsReceivingRecord);
    }


    //  公用 —— 更新驾驶员等信息
    @Transactional
    public int setDetail(GGoodsReceivingRecord gGoodsReceivingRecord) {
        try {
            LoginDriver loginDriver = driverTokenService.getLoginDriver();
            gGoodsReceivingRecord.setId(UUID.randomUUID()+"");
            gGoodsReceivingRecord.setStatus(1L);
            gGoodsReceivingRecord.setDriverId(loginDriver.getUserid());
            gGoodsReceivingRecord.setCreateBy(loginDriver.getUsername());
            gGoodsReceivingRecord.setDeleteFlag(1L);
            gGoodsReceivingRecord.setCreateTime(DateUtils.getNowDate());

            //减库存
            GGoods gGoods = gGoodsMapper.selectGGoodsById(gGoodsReceivingRecord.getGoodsId());
            gGoodsReceivingRecord.setSpecification(gGoods.getSpecification()==null?"":gGoods.getSpecification());
            gGoodsReceivingRecord.setUnit(gGoods.getUnit()==null?"":gGoods.getUnit());
            if (gGoods.getAmount() < gGoodsReceivingRecord.getNumber()) {
                throw new CustomException("此次填写数量已超过总库存数量,请重新填写！");
            }
            gGoods.setAmount(gGoods.getAmount() - gGoodsReceivingRecord.getNumber());

            // 审批
            this.setExamineTask(gGoodsReceivingRecord);

            return gGoodsMapper.updateGGoods(gGoods);
        } catch (Exception e) {
            throw e;
        }
    }


    //  公用 —— 审批
    @Transactional
    public int setExamineTask(GGoodsReceivingRecord gGoodsReceivingRecord) {
        try {
            //新增审批任务 type = 12
            int result = systemClient.submitCommon(new SysExamineConfigParams(12L,gGoodsReceivingRecord.getId(),gGoodsReceivingRecord.getDriverId(),gGoodsReceivingRecord.getCreateBy(),gGoodsReceivingRecord.getSysExamineConfigProcessList()));
            //无审批人直接通过
            if (result == 0){
                gGoodsReceivingRecord.setStatus(2L);
            }
            return result;
        } catch (Exception e) {
            throw e;
        }
    }
}
