package com.dlzx.system.client;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.handler.FeignSupportConfig;
import com.dlzx.system.domain.GGoodsCheck;
import com.dlzx.system.domain.GGoodsCheckItem;
import com.dlzx.system.domain.GGoodsReceivingRecord;
import com.dlzx.system.domain.GGoodsType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(value = "pfserver-parts",configuration = FeignSupportConfig.class) //注册中心服务的名字
//@FeignClient(value = "pfserver-parts",url = "http://localhost:9400/",configuration = FeignSupportConfig.class) //本地调试
public interface AdminPartsClient {
    @RequestMapping("/parts/goodsReceivingRecord/T01List")
    public GGoodsReceivingRecord T01List(GGoodsReceivingRecord gGoodsReceivingRecord);

    @RequestMapping("/parts/goodsReceivingRecord/T02List")
    public GGoodsReceivingRecord T02List(GGoodsReceivingRecord gGoodsReceivingRecord);

    @RequestMapping("/parts/goodsReceivingRecord/T03List")
    public GGoodsCheck T03List(GGoodsCheck gGoodsCheck);

    @RequestMapping("parts/checkItem/T06List")
    public GGoodsCheckItem T06List(GGoodsCheckItem gGoodsCheckItem);

    @RequestMapping("parts/checkItem/T05EditSave")
    public GGoodsCheckItem T05EditSave(GGoodsCheckItem gGoodsCheckItem);

    @RequestMapping("parts/checkItem/T04Add")
    public GGoodsCheckItem T04Add(GGoodsCheckItem gGoodsCheckItem);


    /**
     * @describe admin 物品下拉列表
     * @author DongCL
     * @date 2021-01-21 16:35
     * @param
     * @return
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/T01GoodsList",method = RequestMethod.POST)
    public TableDataInfo T01GoodsList();

    /**
     * @describe admin 驾驶员下拉列表
     * @author DongCL
     * @date 2021-01-21 16:35
     * @param
     * @return
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/T01DriversList",method = RequestMethod.POST)
    public TableDataInfo T01DriversList();

    /**
     * @describe admin 物品领用记录
     * @author DongCL
     * @date 2021-01-21 16:35
     * @param
     * @return
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/T01AdminGGoodsReceivingList",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public TableDataInfo T01AdminGGoodsReceivingList(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord);

    /**
     * @describe admin 物品领用记录详情
     * @author DongCL
     * @date 2021-01-21 16:35
     * @param
     * @return
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/T01AdminGGoodsReceivingInfo",method = RequestMethod.POST)
    public AjaxResult T01AdminGGoodsReceivingInfo(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord);


    /**
     * @describe 盘点记录列表
     * @author DongCL
     * @date 2021-01-21 18:39
     * @param
     * @return
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/T03AdminGGoodsCheckList",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public TableDataInfo T03AdminGGoodsCheckList(@RequestBody GGoodsCheck gGoodsCheck);

    /**
     * @describe 盘点记录详情
     * @author DongCL
     * @date 2021-01-21 18:40
     * @param
     * @return
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/T03AdminGGoodsCheckInfo",method = RequestMethod.POST)
    public AjaxResult T03AdminGGoodsCheckInfo(@RequestBody GGoodsCheck gGoodsCheck);

    /**
     * @describe 新增盘点
     * @author DongCL
     * @date 2021-01-21 18:41
     * @param
     * @return
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/T03AdminInsertCheck",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult T03AdminInsertCheck(@RequestBody GGoodsType gGoodsType);

    /**
     * @describe 盘点项-提交
     * @author DongCL
     * @date 2021-01-21 18:41
     * @param
     * @return
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/T04AdminSubmitCheckItem",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult T04AdminSubmitCheckItem(@RequestBody GGoodsCheck gGoodsCheck);

    /**
     * @describe 盘点项-编辑
     * @author DongCL
     * @date 2021-01-21 18:41
     * @param
     * @return
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/T05AdminUpdateCheckItem",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult T05AdminUpdateCheckItem(@RequestBody GGoodsCheckItem gGoodsCheckItem);
}
