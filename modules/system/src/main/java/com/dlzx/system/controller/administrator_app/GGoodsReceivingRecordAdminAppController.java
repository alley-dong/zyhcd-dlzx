package com.dlzx.system.controller.administrator_app;


import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.system.client.AdminPartsClient;
import com.dlzx.system.domain.GGoodsCheck;
import com.dlzx.system.domain.GGoodsCheckItem;
import com.dlzx.system.domain.GGoodsReceivingRecord;
import com.dlzx.system.domain.GGoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * jijiawen
 */
@RestController
@RequestMapping("/administrator-app/goodsReceivingRecord")
public class GGoodsReceivingRecordAdminAppController {
    @Autowired
    private AdminPartsClient adminPartsClient;

    @RequestMapping("/T01List")
    @ResponseBody
    public AjaxResult T01List(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord) {
        return AjaxResult.success(adminPartsClient.T01List(gGoodsReceivingRecord));
    }

    /**
     * 领用记录 根据ID查看详情
     * @param gGoodsReceivingRecord
     * @return
     */
    @RequestMapping("/T02List")
    @ResponseBody
    public AjaxResult T02List(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord) {
        return AjaxResult.success(adminPartsClient.T02List(gGoodsReceivingRecord));
    }

    /**
     * 盘点查看
     * @param gGoodsCheck
     * @return
     */
    @RequestMapping("/T03List")
    @ResponseBody
    public AjaxResult T03List(@RequestBody GGoodsCheck gGoodsCheck) {
        return AjaxResult.success(adminPartsClient.T03List(gGoodsCheck));
    }

    /**
     * 盘点新增
     * @param gGoodsCheckItem
     * @return
     */
    @RequestMapping("/T04Add")
    @ResponseBody
    public AjaxResult T04Add(@RequestBody GGoodsCheckItem gGoodsCheckItem) {
        return AjaxResult.success(adminPartsClient.T04Add(gGoodsCheckItem));
    }

    /**
     * 盘点查看
     * @param gGoodsCheckItem
     * @return
     */
    @RequestMapping("/T06List")
    @ResponseBody
    public AjaxResult T06List(@RequestBody GGoodsCheckItem gGoodsCheckItem) {
        return AjaxResult.success(adminPartsClient.T06List(gGoodsCheckItem));
    }

    /**
     * 盘点查看
     * @param gGoodsCheckItem
     * @return
     */
    @RequestMapping("/T05EditSave")
    @ResponseBody
    public AjaxResult T05EditSave(@RequestBody GGoodsCheckItem gGoodsCheckItem) {
        return AjaxResult.success(adminPartsClient.T05EditSave(gGoodsCheckItem));
    }







    // =========== 管理员APP start =======

    /**
     * 物品领用——物品下拉列表
     */
    @RequestMapping(path = "/T01GoodsList",method = RequestMethod.POST)
    public TableDataInfo T01GoodsList()
    {
        return adminPartsClient.T01GoodsList();
    }

    /**
     * 物品领用——驾驶员下拉列表
     */
    @RequestMapping(path = "/T01DriversList",method = RequestMethod.POST)
    public TableDataInfo T01DriversList()
    {
        return adminPartsClient.T01DriversList();
    }

    /**
     * 物品领用-物品领用记录列表
     */
    @Log(title = "物品领用记录列表")
    @RequestMapping(path = "/T01AdminGGoodsReceivingList",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public TableDataInfo T01AdminGGoodsReceivingList(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        return adminPartsClient.T01AdminGGoodsReceivingList(gGoodsReceivingRecord);
    }

    /**
     * 物品领用-物品领用记录详情
     */
    @Log(title = "物品领用记录详情")
    @RequestMapping(path = "/T01AdminGGoodsReceivingInfo",method = RequestMethod.POST)
    public AjaxResult T01AdminGGoodsReceivingInfo(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        return adminPartsClient.T01AdminGGoodsReceivingInfo(gGoodsReceivingRecord);
    }

    /**
     * 盘点-盘点记录列表
     */
    @RequestMapping(path = "/T03AdminGGoodsCheckList",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @Log(title = "盘点记录列表")
    public TableDataInfo T03AdminGGoodsCheckList(@RequestBody GGoodsCheck gGoodsCheck)
    {
        return adminPartsClient.T03AdminGGoodsCheckList(gGoodsCheck);
    }

    /**
     * 盘点-盘点记录详情
     */
    @Log(title = "盘点记录详情")
    @RequestMapping(path = "/T03AdminGGoodsCheckInfo",method = RequestMethod.POST)
    public AjaxResult T03AdminGGoodsCheckInfo(@RequestBody GGoodsCheck gGoodsCheck)
    {
        return adminPartsClient.T03AdminGGoodsCheckInfo(gGoodsCheck);
    }

    /**
     * 盘点-新增盘点
     */
    @Log(title = "盘点新增", businessType = BusinessType.INSERT)
    @RequestMapping(path = "/T03AdminInsertCheck",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult T03AdminInsertCheck(@RequestBody GGoodsType gGoodsType)
    {
        return adminPartsClient.T03AdminInsertCheck(gGoodsType);
    }

    /**
     * 盘点项-提交
     */
    @Log(title = "盘点项提交", businessType = BusinessType.INSERT)
    @RequestMapping(path = "/T04AdminSubmitCheckItem",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult T04AdminSubmitCheckItem(@RequestBody GGoodsCheck gGoodsCheck)
    {
        return adminPartsClient.T04AdminSubmitCheckItem(gGoodsCheck);
    }

    /**
     * 盘点项-编辑
     */
    @Log(title = "编辑盘点项", businessType = BusinessType.UPDATE)
    @RequestMapping(path = "/T05AdminUpdateCheckItem",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult T05AdminUpdateCheckItem(@RequestBody GGoodsCheckItem gGoodsCheckItem)
    {
        return adminPartsClient.T05AdminUpdateCheckItem(gGoodsCheckItem);
    }

    // =========== end   =========
}
