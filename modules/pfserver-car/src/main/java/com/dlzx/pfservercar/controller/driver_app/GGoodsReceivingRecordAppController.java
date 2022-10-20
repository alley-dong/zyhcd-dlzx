package com.dlzx.pfservercar.controller.driver_app;

import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.pfservercar.client.PartsClient;
import com.dlzx.pfservercar.client.SystemClient;
import com.dlzx.pfservercar.domain.GGoodsReceivingRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * jijiawen
 */
@RestController
@RequestMapping("/driver-api/goodsReceivingRecord")
public class GGoodsReceivingRecordAppController {
    @Autowired
    private PartsClient partsClient;
    @Autowired
    private SystemClient systemClient;

    /**
     * 获取库存管理-物品领用记录详细信息
     */
    @RequestMapping("/L01GetInfo/{goodsTypeId}")
    @ResponseBody
    public AjaxResult L01GetInfo(@PathVariable("goodsTypeId") String goodsTypeId) {
        return AjaxResult.success(partsClient.L01GetInfo(goodsTypeId));
    }

    /**
     * 获取库存管理-L02页面的回显数据
     */
    @RequestMapping("/L02GetInfo/{goodsId}")
    @ResponseBody
    public AjaxResult L02GetInfo(@PathVariable("goodsId") String goodsId) {
        return AjaxResult.success(partsClient.L02GetInfo(goodsId));
    }


    /**
     * 获取库存管理-L07页面的回显数据
     */
    @RequestMapping("/L07GetInfo/{id}")
    @ResponseBody
    public AjaxResult L07GetInfo(@PathVariable("id") String id) {
        return AjaxResult.success(partsClient.L07GetInfo(id));
    }

    /**
     * 获取库存管理-L03页面的回显数据
     */
    @RequestMapping("/L03GetInfo/{goodsId}")
    @ResponseBody
    public AjaxResult L03GetInfo(@PathVariable("goodsId") String goodsId) {
        return AjaxResult.success(partsClient.L03GetInfo(goodsId));
    }

    /**
     * 获取库存管理-L04页面的回显数据
     */
    @RequestMapping("/L04GetInfo/{goodsId}")
    @ResponseBody
    public AjaxResult L04GetInfo(@PathVariable("goodsId") String goodsId) {
        return AjaxResult.success(partsClient.L04GetInfo(goodsId));
    }


    /**
     * 领用记录-记录详情(待审批)
     */
    @RequestMapping("/L09List")
    @ResponseBody
    public AjaxResult L09List(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord) {
        return AjaxResult.success(partsClient.L09List(gGoodsReceivingRecord));
    }

    /**
     * 领用记录-记录详情(已通过)
     */
    @RequestMapping("/L10List")
    @ResponseBody
    public AjaxResult L10List(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord) {
        return AjaxResult.success(partsClient.L10List(gGoodsReceivingRecord));
    }

    /**
     * 领用记录-记录详情(已拒绝)
     */
    @RequestMapping("/L11List")
    @ResponseBody
    public AjaxResult L11List(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord) {
        return AjaxResult.success(partsClient.L11List(gGoodsReceivingRecord));
    }

    /**
     * 领用记录-记录详情(已领取)
     */
    @RequestMapping("/L12List")
    @ResponseBody
    public AjaxResult L12List(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord) {
        return AjaxResult.success(partsClient.L12List(gGoodsReceivingRecord));
    }

    /**
     * 领用记录-记录详情(已撤销)
     */
    @RequestMapping("/L13List")
    @ResponseBody
    public AjaxResult L13List(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord) {
        return AjaxResult.success(partsClient.L13List(gGoodsReceivingRecord));
    }

    /**
     * 领用记录-重新申请回显
     */
    @RequestMapping("/L12GetInfo/{id}")
    @ResponseBody
    public AjaxResult L12GetInfo(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord) {
        return AjaxResult.success(partsClient.L12GetInfo(gGoodsReceivingRecord));
    }

//    ====================


    /**
     * 尿素添加申请
     * @param gGoodsReceivingRecord
     * @return
     */
    @RequestMapping("/L02Add")
    @ResponseBody
    public AjaxResult L02Add(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord) {
        if (StringUtils.isNull(gGoodsReceivingRecord.getGoodsId())){
            return AjaxResult.error("请选择领用物品");
        }
        if (StringUtils.isNull(gGoodsReceivingRecord.getSpecification())){
            return AjaxResult.error("规格不能为空");
        }
        if (StringUtils.isNull(gGoodsReceivingRecord.getLicensePlate())){
            return AjaxResult.error("请选择车牌号");
        }
        if (StringUtils.isNull(gGoodsReceivingRecord.getRouteId())){
            return AjaxResult.error("请选择线路");
        }
        if (StringUtils.isNull(gGoodsReceivingRecord.getNumber())){
            return AjaxResult.error("请填入领用数量");
        }
        return partsClient.L02Add(gGoodsReceivingRecord);
    }

    /**
     * 工作餐添加申请
     * @param gGoodsReceivingRecord
     * @return
     */
    @RequestMapping("/L03Add")
    @ResponseBody
    public AjaxResult L03Add(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord) {

        for (Date date : gGoodsReceivingRecord.getReceiveDate()) {
            if (StringUtils.isNull(date)){
                return AjaxResult.error("请选择领用时间！");
            }
        }
        if (StringUtils.isNull(gGoodsReceivingRecord.getGoodsId())){
            return AjaxResult.error("请选择领用物品");
        }
        if (StringUtils.isNull(gGoodsReceivingRecord.getSpecification())){
            return AjaxResult.error("规格不能为空");
        }
        if (StringUtils.isNull(gGoodsReceivingRecord.getLicensePlate())){
            return AjaxResult.error("请选择车牌号");
        }
        if (StringUtils.isNull(gGoodsReceivingRecord.getNumber())){
            return AjaxResult.error("请填入领用数量");
        }
        return partsClient.L03Add(gGoodsReceivingRecord);
    }

    /**
     * 轮胎添加申请  低值易耗品/轮胎添加申请
     * @param gGoodsReceivingRecord
     * @return
     */
    @RequestMapping("/L04Add")
    @ResponseBody
    public AjaxResult L04Add(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord) {

        if (StringUtils.isNull(gGoodsReceivingRecord.getGoodsId())){
            return AjaxResult.error("请选择领用物品");
        }
        if (StringUtils.isNull(gGoodsReceivingRecord.getSpecification())){
            return AjaxResult.error("规格不能为空");
        }
        if (StringUtils.isNull(gGoodsReceivingRecord.getLicensePlate())){
            return AjaxResult.error("请选择车牌号");
        }
        if (StringUtils.isNull(gGoodsReceivingRecord.getNumber())){
            return AjaxResult.error("请填入领用数量");
        }
        return partsClient.L04Add(gGoodsReceivingRecord);
    }

    /**
     * 备件添加申请
     * @param gGoodsReceivingRecord
     * @return
     */
    @RequestMapping("/L06Add")
    @ResponseBody
    public AjaxResult L06Add(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord) {
        if (StringUtils.isNull(gGoodsReceivingRecord.getLicensePlate())){
            return AjaxResult.error("请选择车牌号");
        }
        if (StringUtils.isNull(gGoodsReceivingRecord.getType())){
            return AjaxResult.error("请选择领用用途");
        }
        return partsClient.L06Add(gGoodsReceivingRecord);
    }

    /**
     * 领用记录-拒绝(再次提交)
     * @param gGoodsReceivingRecord
     * @return
     */
    @RequestMapping("/L12EditSave")
    @ResponseBody
    public AjaxResult L12EditSave(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord) {
        return partsClient.L12EditSave(gGoodsReceivingRecord);
    }

    /**
     * 领用记录-撤回
     * @param gGoodsReceivingRecord
     * @return
     */
    @RequestMapping("/L08Recall")
    @ResponseBody
    public AjaxResult L08Recall(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord) {
        systemClient.deleteExamineTaskByRelationId(gGoodsReceivingRecord.getId());
        return partsClient.L08Recall(gGoodsReceivingRecord);
    }

    /**
     * 领用记录-确认领取
     * @param gGoodsReceivingRecord
     * @return
     */
    @RequestMapping("/L10Affirm")
    @ResponseBody
    public AjaxResult L10Affirm(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord) {
        return partsClient.L10Affirm(gGoodsReceivingRecord);
    }

    /**
     * 获取L01页面的物品类别
     * @return
     */
    @RequestMapping("/L01GoodTypeList")
    public TableDataInfo L01GoodTypeList() {
        return partsClient.L01GoodTypeList();
    }


    /**
     * 获取库存管理-物品领用记录详情
     */
    @RequestMapping("/L08GetInfo")
    public AjaxResult L08GetInfo(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        return partsClient.L08GetInfo(gGoodsReceivingRecord.getId());
    }

    /**
     * 领用记录-列表
     */
    @RequestMapping("/L08List")
    @ResponseBody
    public TableDataInfo L08List(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord) {
        return partsClient.L08List(gGoodsReceivingRecord);
    }

    /**
     * 获取库存管理-根据物品类别ID获取物品(备件列表)
     */
    @RequestMapping("/L07GetGoodList")
    public TableDataInfo L07GetGoodList(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord) {
        return partsClient.L07GetGoodList(gGoodsReceivingRecord);
    }
}
