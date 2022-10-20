package com.dlzx.pfservercar.client;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.security.handler.FeignSupportConfig;
import com.dlzx.pfservercar.domain.DSalaryDetailedRecords;
import com.dlzx.pfservercar.domain.DVacation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dlzx.pfservercar.domain.GGoodsReceivingRecord;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * jijiawen
 */
@Component
@FeignClient(value = "pfserver-parts",configuration = FeignSupportConfig.class) //注册中心服务的名字
//@FeignClient(value = "pfserver-parts",url = "http://localhost:9400/",configuration = FeignSupportConfig.class) //本地调试
public interface PartsClient {


    /**
     * 获取库存管理-物品领用记录详细信息
     */
    @RequestMapping("/parts/goodsReceivingRecord/L01GetInfo/{goodsTypeId}")
    public GGoodsReceivingRecord L01GetInfo(String goodsTypeId);

    /**
     * 获取库存管理-L02页面的回显数据
     */
    @RequestMapping("/parts/goodsReceivingRecord/L02GetInfo/{goodsId}")
    public GGoodsReceivingRecord L02GetInfo(String goodsId);

    /**
     * 获取库存管理-L07页面的回显数据
     */
    @RequestMapping("/parts/goodsReceivingRecord/L07GetInfo/{id}")
    public GGoodsReceivingRecord L07GetInfo(String id);

    /**
     * 领用记录-列表
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/L08List",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public TableDataInfo L08List(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord);

    /**
     * 物品领用发起申请-尿素
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/L02Add",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult L02Add(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord);

    /**
     * 物品领用发起申请-尿素
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/L03Add",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult L03Add(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord);

    /**
     * 物品领用发起申请-轮胎
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/L04Add",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult L04Add(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord);

    /**
     * 物品领用发起申请-低值易耗品
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/L05Add",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult L05Add(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord);

    /**
     * 物品领用发起申请-尿素
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/L06Add",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult L06Add(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord);

    /**
     * 获取库存管理-L03页面的回显数据
     */
    @RequestMapping("/parts/goodsReceivingRecord/L03GetInfo/{goodsId}")
    public GGoodsReceivingRecord L03GetInfo(String goodsId);

    /**
     * 获取库存管理-L04页面的回显数据
     */
    @RequestMapping("/parts/goodsReceivingRecord/L04GetInfo/{goodsId}")
    public GGoodsReceivingRecord L04GetInfo(String goodsId);

    /**
     * 获取库存管理-L05页面的回显数据
     */
    @RequestMapping("/parts/goodsReceivingRecord/L05GetInfo/{goodsId}")
    public GGoodsReceivingRecord L05GetInfo(String goodsId);

    /**
     * 领用记录-记录详情(待审批)
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/L09List",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public GGoodsReceivingRecord L09List(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord);

    /**
     * 领用记录-记录详情(待审批)
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/L10List",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public GGoodsReceivingRecord L10List(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord);

    /**
     * 领用记录-记录详情(待审批)
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/L11List",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public GGoodsReceivingRecord L11List(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord);

    /**
     * 领用记录-记录详情(待审批)
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/L12List",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public GGoodsReceivingRecord L12List(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord);

    /**
     * 领用记录-记录详情(待审批)
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/L13List",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public GGoodsReceivingRecord L13List(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord);

    /**
     * 领用记录-记录详情(待审批)
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/L12GetInfo",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public GGoodsReceivingRecord L12GetInfo(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord);


    /**
     * 领用记录-拒绝(再次提交)
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/L12EditSave",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult L12EditSave(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord);

    /**
     * APP 薪酬列表
     * @param dSalaryDetailedRecords
     * @return
     */
    @RequestMapping(path = "/parts/salaryRecords/G01List",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult G01List(@RequestBody DSalaryDetailedRecords dSalaryDetailedRecords);

    /**
     * 个人工资信息
     * @param id
     * @return
     */
    @RequestMapping(path = "/parts/salaryRecords/G02Info/{id}",method = RequestMethod.GET)
    public AjaxResult G02Info(@PathVariable(value = "id",required = false)String id);


    /*
    * 首页物品类别列表
    * */
    @RequestMapping(path = "/parts/goodsReceivingRecord/L01GoodTypeList",method = RequestMethod.POST)
    TableDataInfo L01GoodTypeList();


    /**
     * 获取库存管理-物品领用记录详情
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/L08GetInfo",method = RequestMethod.POST)
    public AjaxResult L08GetInfo(@RequestParam("id") String id);


    /**
     * 获取库存管理-根据物品类别ID获取物品(备件列表)
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/L07GetGoodList", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    TableDataInfo L07GetGoodList(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord);


    /**
     * 领用记录-确认领取
     * @param gGoodsReceivingRecord
     * @return
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/L10Affirm", method = RequestMethod.POST)
    AjaxResult L10Affirm(GGoodsReceivingRecord gGoodsReceivingRecord);


    /**
     * @describe-撤回
     * @author DongCL
     * @date 2021-01-21 15:43
     * @param
     * @return
     */
    @RequestMapping(path = "/parts/goodsReceivingRecord/L08Recall", method = RequestMethod.POST)
    AjaxResult L08Recall(GGoodsReceivingRecord gGoodsReceivingRecord);
}
