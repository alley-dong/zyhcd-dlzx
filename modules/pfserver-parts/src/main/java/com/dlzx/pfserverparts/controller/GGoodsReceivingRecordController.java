package com.dlzx.pfserverparts.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.DriverTokenService;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverparts.client.SystemClient;
import com.dlzx.pfserverparts.domain.DDriver;
import com.dlzx.pfserverparts.domain.GGoods;
import com.dlzx.pfserverparts.domain.GGoodsCheck;
import com.dlzx.pfserverparts.domain.GGoodsCheckItem;
import com.dlzx.pfserverparts.domain.GGoodsReceivingRecordDate;
import com.dlzx.pfserverparts.domain.GGoodsType;
import com.dlzx.pfserverparts.domain.vm.GGoodsReceivingRecordVm;
import com.dlzx.pfserverparts.service.IGGoodsCheckItemService;
import com.dlzx.pfserverparts.service.IGGoodsCheckService;
import com.dlzx.pfserverparts.service.IGGoodsReceivingRecordDateService;
import com.dlzx.pfserverparts.service.IGGoodsService;
import com.dlzx.system.api.model.LoginDriver;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfserverparts.domain.GGoodsReceivingRecord;
import com.dlzx.pfserverparts.service.IGGoodsReceivingRecordService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 库存管理-物品领用记录Controller
 * 
 * @author jijiawen
 * @date 2020-12-09
 */
@RestController
@RequestMapping("/parts/goodsReceivingRecord")
public class GGoodsReceivingRecordController extends BaseController
{
    @Autowired
    private IGGoodsReceivingRecordService gGoodsReceivingRecordService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private IGGoodsReceivingRecordDateService gGoodsReceivingRecordDateService;
    @Autowired
    private IGGoodsService goodsService;
    @Autowired
    private IGGoodsCheckItemService gGoodsCheckItemService;
    @Autowired
    private IGGoodsCheckService gGoodsCheckService;


    /**
     * 查询库存管理-物品领用记录列表
     * K18List 待审批查看(尿素)
     */
    @PreAuthorize(hasPermi = "parts:goodsReceivingRecord:list")
    @PostMapping(value = {"/K17List", "/K18List"
                        ,"/K20List", "/K21List", "/K22List"})
    public TableDataInfo K17List(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        startPage(gGoodsReceivingRecord.getPageNum(), gGoodsReceivingRecord.getPageSize());
        List<GGoodsReceivingRecord> list = gGoodsReceivingRecordService.selectGGoodsReceivingRecordList(gGoodsReceivingRecord);
        return getDataTable(list);
    }

    /**
     * 领用管理下拉选物品
     */
    @PreAuthorize(hasPermi = "parts:goodsReceivingRecord:list")
    @GetMapping(value = {"/K17InfoList"})
    public TableDataInfo K17InfoList()
    {
        List<GGoods> gGoods = goodsService.selectGGoodsList(new GGoods());
        return getDataTable(gGoods);
    }


    /**
     * 查看（工作餐）
     * @param id
     * @return
     */
    @PreAuthorize(hasPermi = "parts:goodsReceivingRecord:list")
    @GetMapping("/K19GetInfo/{id}")
    public TableDataInfo K19GetInfo(@PathVariable("id") String id)
    {
        List<GGoodsReceivingRecord> list = gGoodsReceivingRecordService.selectGGoodsReceivingRecordByGGRRId(id);
        List<String> list1 = new ArrayList<>();
        for (GGoodsReceivingRecord goodsReceivingRecord : list) {
            Date receiveDates = goodsReceivingRecord.getReceiveDates();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(receiveDates);
            list1.add(format);
            goodsReceivingRecord.setReceiveDateLists(list1);
        }
        ArrayList<GGoodsReceivingRecord> collect = list.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(
                                GGoodsReceivingRecord::getId))), ArrayList::new));
        return getDataTable(collect);
    }

    /**
     * 查看（轮胎）（低值易耗品）
     */
    @PreAuthorize(hasPermi = "parts:goodsReceivingRecord:list")
    @GetMapping("/K20GetInfo/{id}")
    public AjaxResult K20GetInfo(@PathVariable("id") String id)
    {
        GGoodsReceivingRecord gGoodsReceivingRecord = gGoodsReceivingRecordService.selectGGoodsReceivingRecordInfo(id);
        return AjaxResult.success(gGoodsReceivingRecord);
    }

    /**
     * 查看（）
     * @param id
     * @return
     */
    @PreAuthorize(hasPermi = "parts:goodsReceivingRecord:list")
    @GetMapping("/K21GetInfo/{id}")
    public TableDataInfo K21GetInfo(@PathVariable("id") String id)
    {
        List<GGoodsReceivingRecord> list = gGoodsReceivingRecordService.selectGGoodsReceivingRecordByGGRRIId(id);
        List<Date> list1 = new ArrayList<>();
        for (GGoodsReceivingRecord goodsReceivingRecord : list) {
            Date receiveDates = goodsReceivingRecord.getReceiveDates();
            list1.add(receiveDates);
            goodsReceivingRecord.setReceiveDateList(list1);
        }
        return getDataTable(list);
    }

    /**
     * 查询库存管理-物品领用记录列表
     * 只能查到已领取的
     */
    @RequestMapping(path = "/T01List",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public TableDataInfo T01List(@RequestBody GGoodsReceivingRecord good)
    {
        startPage(good.getPageNum(), good.getPageSize());
        List<GGoodsReceivingRecord> list = gGoodsReceivingRecordService.selectGGoodsReceivingRecordList(good);
        return getDataTable(list);
    }

    /**
     * 查询库存管理-物品领用记录列表
     * 只能查到已领取的
     */
    @RequestMapping("/T02List")
    @ResponseBody
    public TableDataInfo T02List(@RequestBody GGoodsReceivingRecord good)
    {
        startPage();
        List<GGoodsReceivingRecord> list = gGoodsReceivingRecordService.selectGGoodsReceivingRecordList(good);
        List<Date> list1 = new ArrayList<>();
        for (GGoodsReceivingRecord goodsReceivingRecord : list) {
            list1.add(goodsReceivingRecord.getReceiveDates());
            goodsReceivingRecord.setReceiveDateList(list1);
        }
        return getDataTable(list);
    }


    /**
     * 查询库存管理-物品领用记录列表
     * L09List L10List L11List L12List L13List 待审批查看
     */
    @RequestMapping(path = "/L09List",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public TableDataInfo L09List(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        startPage(gGoodsReceivingRecord.getPageNum(), gGoodsReceivingRecord.getPageSize());
        List<GGoodsReceivingRecord> list = gGoodsReceivingRecordService.selectGGoodsReceivingRecordList(gGoodsReceivingRecord);
        return getDataTable(list);
    }

    @RequestMapping(path = "/L12GetInfo",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public TableDataInfo L12GetInfo(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        startPage(gGoodsReceivingRecord.getPageNum(), gGoodsReceivingRecord.getPageSize());
        List<GGoodsReceivingRecord> list = gGoodsReceivingRecordService.selectGGoodsReceivingRecordList(gGoodsReceivingRecord);
        return getDataTable(list);
    }

    /**
     * 导出库存管理-物品领用记录列表
     */
    @PreAuthorize(hasPermi = "parts:goodsReceivingRecord:export")
    @Log(title = "库存管理-物品领用记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GGoodsReceivingRecord gGoodsReceivingRecord) throws IOException
    {
        List<GGoodsReceivingRecord> list = gGoodsReceivingRecordService.selectGGoodsReceivingRecordList(gGoodsReceivingRecord);
        ExcelUtil<GGoodsReceivingRecord> util = new ExcelUtil<GGoodsReceivingRecord>(GGoodsReceivingRecord.class);
        util.exportExcel(response, list, "record");
    }

    /**
     * 获取库存管理-物品领用记录详细信息
     */
    @PreAuthorize(hasPermi = "parts:goodsReceivingRecord:list")
    @GetMapping("/K18GetInfo/{id}")
    public AjaxResult K18GetInfo(@PathVariable("id") String id)
    {
        GGoodsReceivingRecord gGoodsReceivingRecord = gGoodsReceivingRecordService.selectGGoodsReceivingRecordById(id);
        return AjaxResult.success(gGoodsReceivingRecord);
    }

    /**
     * 修改库存管理-审核
     */
    @PreAuthorize(hasPermi = "parts:goodsReceivingRecord:edit")
    @Log(title = "库存管理-物品领用记录-审核", businessType = BusinessType.UPDATE)
    @PostMapping("/K18EditSave")
    public AjaxResult K18EditSave(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        if (StringUtils.isNull(gGoodsReceivingRecord.getStatus())){
            return AjaxResult.error("请选择审核结果");
        }
        if (gGoodsReceivingRecord.getStatus()==2 && StringUtils.isEmpty(gGoodsReceivingRecord.getReviewRemarks())){
            return AjaxResult.error("请输入不通过原因");
        }
        LoginUser userInfo = tokenService.getLoginUser();
        gGoodsReceivingRecord.setUserid(userInfo.getUserid().toString());
        return gGoodsReceivingRecordService.checkCInsuranceVehicle(gGoodsReceivingRecord);
    }

// ============== 驾驶员App  start =======
    /**
     * 获取L01页面的物品类别
     * App
     */
    @RequestMapping("/L01GoodTypeList")
    public TableDataInfo L01GoodTypeList()
    {
        return getDataTable(gGoodsReceivingRecordService.queryGoodTypeList());
    }


    /**
     * 查询库存管理-物品领用记录列表
     * App
     */
    @RequestMapping(path = "/L08List",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    @Log(title = "App 物品领用记录列表")
    public TableDataInfo L08List(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        startPage(gGoodsReceivingRecord.getPageNum(), gGoodsReceivingRecord.getPageSize());
        List<GGoodsReceivingRecord> list = gGoodsReceivingRecordService.queryGGoodsReceivingRecordList(gGoodsReceivingRecord);
        return getDataTable(list);
    }

    /**
     * 获取库存管理-物品领用记录详情
     */
    @RequestMapping(path = "/L08GetInfo",method = RequestMethod.POST)
    public AjaxResult L08GetInfo(@RequestParam("id") String id)
    {
        return AjaxResult.success(gGoodsReceivingRecordService.queryGGoodsReceivingRecordById(id));
    }

    /**
     * 获取库存管理-根据物品类别ID获取物品(备件列表)
     */
    @RequestMapping(path = "/L07GetGoodList",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public TableDataInfo L07GetInfo(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        GGoods gGoods = new GGoods();
        gGoods.setGoodsTypeId(gGoodsReceivingRecord.getGoodsTypeId());
        if (StringUtils.isNotEmpty(gGoodsReceivingRecord.getGoodsName())){
            gGoods.setName(gGoodsReceivingRecord.getGoodsName());
        }
        List<GGoods> gGoodsList = goodsService.selectGGoodsList(gGoods);
        return getDataTable(gGoodsList);
    }

    /**
     * 新增库存管理-物品领用记录app-尿素
     */
    @Log(title = "库存管理-物品领用记录", businessType = BusinessType.INSERT)
    @RequestMapping(path = "/L02Add",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult L02Add(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        return AjaxResult.success(gGoodsReceivingRecordService.insertGGoodsReceivingRecord(gGoodsReceivingRecord));
    }

    /**
     * 新增库存管理-物品领用记录app-工作餐
     */
    @Log(title = "库存管理-物品领用记录", businessType = BusinessType.INSERT)
    @RequestMapping(path = "/L03Add",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult L03Add(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        for (Date date : gGoodsReceivingRecord.getReceiveDate()) {
            if (StringUtils.isNull(date)){
                throw new CustomException("请选择领用时间！");
            }
        }
        return AjaxResult.success(gGoodsReceivingRecordService.insertGGoodsReceivingRecordDate(gGoodsReceivingRecord));
    }

    /**
     * 新增库存管理-物品领用记录app-轮胎 / 低值易耗品
     */
    @Log(title = "库存管理-物品领用记录", businessType = BusinessType.INSERT)
    @RequestMapping(path = "/L04Add",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult L04Add(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        return AjaxResult.success(gGoodsReceivingRecordService.insertGGoodsReceivingRecordTyre(gGoodsReceivingRecord));
    }

    /**
     * 新增库存管理-备件添加
     */
    @Log(title = "库存管理-物品领用-备件添加", businessType = BusinessType.INSERT)
    @RequestMapping(path = "/L06Add",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult L06Add(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        return AjaxResult.success(gGoodsReceivingRecordService.insertGGoodsReceivingRecordItem(gGoodsReceivingRecord));
    }

    /**
     * 领用记录app- 拒绝(再次提交)
     */
    @Log(title = "库存管理-物品领用记录-拒绝", businessType = BusinessType.UPDATE)
    @RequestMapping(path = "/L12EditSave",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult L12EditSave(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        return AjaxResult.success(gGoodsReceivingRecordService.submitGGoodsReceivingRecord(gGoodsReceivingRecord));
    }

    /**
     * 领用记录app- 撤回
     */
    @Log(title = "库存管理-物品领用记录-撤回", businessType = BusinessType.UPDATE)
    @RequestMapping(path = "/L08Recall",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult L08Recall(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        return AjaxResult.success(gGoodsReceivingRecordService.recallGGoodsReceivingRecord(gGoodsReceivingRecord));
    }


    /**
     * 领用记录app- 确认领取
     */
    @Log(title = "库存管理-物品领用记录-确认领取", businessType = BusinessType.UPDATE)
    @RequestMapping(path = "/L10Affirm",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult L10Affirm(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        return AjaxResult.success(gGoodsReceivingRecordService.updateGGoodsReceivingRecord(gGoodsReceivingRecord));
    }

//  ========end======


    /**
     * 获取库存管理-L03页面的回显数据
     * App
     */
    @RequestMapping("/L03GetInfo/{goodsId}")
    public AjaxResult L03GetInfo(@PathVariable("goodsId") String goodsId)
    {
        return AjaxResult.success(gGoodsReceivingRecordService.selectGGoodsReceivingRecordByGoodsIdOrDate(goodsId));
    }

    /**
     * 获取库存管理-L04页面的回显数据
     * L05页面的回显数据
     * L02页面的回显数据
     */
    @RequestMapping("/L04GetInfo/{goodsId}")
    public AjaxResult L04GetInfo(@PathVariable("goodsId") String goodsId)
    {
        return AjaxResult.success(gGoodsReceivingRecordService.selectGGoodsReceivingRecordByByGoodsId(goodsId));
    }


    /**
     * 删除库存管理-物品领用记录
     */
    @PreAuthorize(hasPermi = "parts:goodsReceivingRecord:remove")
    @Log(title = "库存管理-物品领用记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(gGoodsReceivingRecordService.deleteGGoodsReceivingRecordByIds(ids));
    }


    /**
     * 尿素费用
     * 轮胎费用
     */
    @PreAuthorize(hasPermi = "parts:goodsReceivingRecord:list")
    @PostMapping(value = {"/M10List", "/M11List"})
    public TableDataInfo M10List(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        startPage(gGoodsReceivingRecord.getPageNum(), gGoodsReceivingRecord.getPageSize());
        List<GGoodsReceivingRecord> list = gGoodsReceivingRecordService.selectGGoodsReceivingRecordAmount(gGoodsReceivingRecord);

        List<GGoodsReceivingRecordVm> recordVmList = new ArrayList<>();
        BigDecimal insuranceCostTotal = BigDecimal.ZERO;
        for (GGoodsReceivingRecord gGoodsReceivingRecord1 : list) {
            GGoodsReceivingRecordVm gGoodsReceivingRecordVm = new GGoodsReceivingRecordVm();
            BigDecimal unitPrice = gGoodsReceivingRecord1.getUnitPrice();
            BigDecimal number = new BigDecimal(gGoodsReceivingRecord1.getNumber());
            gGoodsReceivingRecord1.setInsuranceCostTotal(unitPrice.multiply(number));
            BeanUtils.copyProperties(gGoodsReceivingRecord1, gGoodsReceivingRecordVm);
            recordVmList.add(gGoodsReceivingRecordVm);
        }
        // 计算统计
        GGoodsReceivingRecord data = new GGoodsReceivingRecord();
        data.setLicensePlate(gGoodsReceivingRecord.getLicensePlate());
        data.setStartTime(gGoodsReceivingRecord.getStartTime());
        data.setFinishTime(gGoodsReceivingRecord.getFinishTime());
        data.setGoodsType(gGoodsReceivingRecord.getGoodsType());
        List<GGoodsReceivingRecord> gGoodsReceivingRecords = gGoodsReceivingRecordService.selectGGoodsReceivingRecordAmount(data);

        for (GGoodsReceivingRecord goodsReceivingRecord : gGoodsReceivingRecords) {
            BigDecimal unitPrice = goodsReceivingRecord.getUnitPrice();
            BigDecimal number = new BigDecimal(goodsReceivingRecord.getNumber());
            goodsReceivingRecord.setInsuranceCostTotal(unitPrice.multiply(number));
            insuranceCostTotal = goodsReceivingRecord.getInsuranceCostTotal().add(insuranceCostTotal);
        }
        // 定义返回集合
        List<Map<String,Object>> mapList = new ArrayList<>();
        // 定义返回集合 对象
        Map<String,Object> map = new HashMap<>();
        map.put("amountSum",insuranceCostTotal);
        map.put("list",list);
        mapList.add(map);
        return getDataTableMap(mapList,list);
    }

    /**
     * 轮胎费用下拉列表
     * specification 规格
     */
    @GetMapping("/M11Info")
    public TableDataInfo M11Info()
    {
        List<GGoodsReceivingRecord> list = gGoodsReceivingRecordService.selectGGoodsReceivingRecordDrop();
        return getDataTable(list);
    }



    /**
     * 导出库存管理-物品领用记录列表
     */
    @PreAuthorize(hasPermi = "parts:goodsReceivingRecord:export")
    @Log(title = "库存管理-物品领用记录", businessType = BusinessType.EXPORT)
    @PostMapping(value = {"/M10Export", "/M11Export"})
    public void M10Export(HttpServletResponse response, GGoodsReceivingRecord gGoodsReceivingRecord) throws IOException
    {
        List<GGoodsReceivingRecord> list = gGoodsReceivingRecordService.selectGGoodsReceivingRecordAmount(gGoodsReceivingRecord);
        for (GGoodsReceivingRecord gGoodsReceivingRecord1 : list) {
            BigDecimal unitPrice = gGoodsReceivingRecord1.getUnitPrice();
            BigDecimal number = new BigDecimal(gGoodsReceivingRecord1.getNumber());
            gGoodsReceivingRecord1.setAmountSum(unitPrice.multiply(number));
        }
        ExcelUtil<GGoodsReceivingRecord> util = new ExcelUtil<GGoodsReceivingRecord>(GGoodsReceivingRecord.class);
        util.exportExcel(response, list, "record");
    }


    // =========== 管理员APP start =======

    /**
     * 物品领用——物品下拉列表
     */
    @RequestMapping(path = "/T01GoodsList",method = RequestMethod.POST)
    public TableDataInfo T01GoodsList()
    {
        GGoods gGoods = new GGoods();
        List<GGoods> gGoodsList = goodsService.selectGGoodsList(gGoods);
        System.out.println("\t ");
        return getDataTable(gGoodsList);
    }

    /**
     * 物品领用——驾驶员下拉列表
     */
    @RequestMapping(path = "/T01DriversList",method = RequestMethod.POST)
    public TableDataInfo T01DriversList()
    {
        List<DDriver> dDrivers = goodsService.selectDriversList();
        return getDataTable(dDrivers);
    }

    /**
     * 物品领用-物品领用记录列表
     * App
     */
    @Log(title = "物品领用记录列表")
    @RequestMapping(path = "/T01AdminGGoodsReceivingList",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public TableDataInfo T01AdminGGoodsReceivingList(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        startPage(gGoodsReceivingRecord.getPageNum(), gGoodsReceivingRecord.getPageSize());
        // 查询已领用的数据
        gGoodsReceivingRecord.setStatus(4L);
        List<GGoodsReceivingRecord> list = gGoodsReceivingRecordService.queryAdminGGoodsReceivingRecordList(gGoodsReceivingRecord);
        return getDataTable(list);
    }

    /**
     * 物品领用-物品领用记录详情
     */
    @Log(title = "物品领用记录详情")
    @RequestMapping(path = "/T01AdminGGoodsReceivingInfo",method = RequestMethod.POST)
    public AjaxResult T01AdminGGoodsReceivingInfo(@RequestBody GGoodsReceivingRecord gGoodsReceivingRecord)
    {
        return AjaxResult.success(gGoodsReceivingRecordService.queryGGoodsReceivingRecordById(gGoodsReceivingRecord.getId()));
    }

    /**
     * 盘点-盘点记录列表
     */
    @RequestMapping(path = "/T03AdminGGoodsCheckList",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @Log(title = "盘点记录列表")
    public TableDataInfo T03AdminGGoodsCheckList(@RequestBody GGoodsCheck gGoodsCheck)
    {
        startPage(gGoodsCheck.getPageNum(), gGoodsCheck.getPageSize());
        List<GGoodsCheck> list = gGoodsReceivingRecordService.queryGGoodsCheckList(gGoodsCheck);
        return getDataTable(list);
    }

    /**
     * 盘点-盘点记录详情
     */
    @Log(title = "盘点记录详情")
    @RequestMapping(path = "/T03AdminGGoodsCheckInfo",method = RequestMethod.POST)
    public AjaxResult T03AdminGGoodsCheckInfo(@RequestBody GGoodsCheck gGoodsCheck)
    {
        return AjaxResult.success(gGoodsReceivingRecordService.queryGGoodsCheckById(gGoodsCheck));
    }

    /**
     * 盘点-新增盘点
     */
    @Log(title = "盘点新增", businessType = BusinessType.INSERT)
    @RequestMapping(path = "/T03AdminInsertCheck",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult T03AdminInsertCheck(@RequestBody GGoodsType gGoodsType)
    {
        return AjaxResult.success(gGoodsCheckService.submitGGoodsCheck(gGoodsType));
    }

    /**
     * 盘点项-提交
     */
    @Log(title = "盘点项提交", businessType = BusinessType.INSERT)
    @RequestMapping(path = "/T04AdminSubmitCheckItem",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult T04AdminSubmitCheckItem(@RequestBody GGoodsCheck gGoodsCheck)
    {
        if (gGoodsCheck.getgGoodsCheckItems().size()<=0){
            return AjaxResult.error("请选择物品");
        }
        for (GGoodsCheckItem gGoodsCheckItem : gGoodsCheck.getgGoodsCheckItems()) {
            if (StringUtils.isNull(gGoodsCheckItem.getDifference())){
                return AjaxResult.error("请填写差异数量");
            }
            if (StringUtils.isNull(gGoodsCheckItem.getStatus())){
                return AjaxResult.error("请填写盈亏状态");
            }
        }
        return toAjax(gGoodsCheckItemService.submitCheckItem(gGoodsCheck));
    }

    /**
     * 盘点项-编辑
     */
    @Log(title = "编辑盘点项", businessType = BusinessType.UPDATE)
    @RequestMapping(path = "/T05AdminUpdateCheckItem",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult T05AdminUpdateCheckItem(@RequestBody GGoodsCheckItem gGoodsCheckItem)
    {
        if (StringUtils.isNull(gGoodsCheckItem.getCheckQuantity())){
            return AjaxResult.error("请填写盘点数量！");
        }
        return toAjax(gGoodsCheckItemService.updateCheckItem(gGoodsCheckItem));
    }

    // =========== end   =========

}
