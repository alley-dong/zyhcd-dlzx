package com.dlzx.pfservercar.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.FOilCardUsageRecord;
import com.dlzx.pfservercar.domain.GGoodsReceivingRecord;
import com.dlzx.pfservercar.service.ICVehicleService;
import com.dlzx.pfservercar.service.IFOilCardUsageRecordService;
import com.dlzx.pfservercar.service.IGGoodsReceivingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfservercar.domain.RVehicleConsumeMonth;
import com.dlzx.pfservercar.service.IRVehicleConsumeMonthService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 统计报-车辆消耗月度统计Controller
 *
 * @author dlzx
 * @date 2020-12-15
 */
@RestController
@RequestMapping("/car/vehicleConsumeMonth")
public class RVehicleConsumeMonthController extends BaseController {
    @Autowired
    private IRVehicleConsumeMonthService rVehicleConsumeMonthService;
    @Autowired
    private IGGoodsReceivingRecordService goodsReceivingRecordService;

    @Autowired
    private IFOilCardUsageRecordService fOilCardUsageRecordService;
    @Autowired
    private ICVehicleService cVehicleService;

    /**
     * 查询统计报-车辆消耗月度统计列表
     */
    @PreAuthorize(hasPermi = "car:vehicleConsumeMonth:N03List")
    @PostMapping("/N03List")
    public TableDataInfo list(@RequestBody RVehicleConsumeMonth rVehicleConsumeMonth) {
        startPage(rVehicleConsumeMonth.getPageNum(),rVehicleConsumeMonth.getPageSize());
        List<RVehicleConsumeMonth> list = rVehicleConsumeMonthService.selectRVehicleConsumeMonthList(rVehicleConsumeMonth);
        return getDataTable(list);
    }

    @PreAuthorize(hasPermi = "car:vehicleConsumeMonth:N01List")
    @PostMapping("/N01List")
    public TableDataInfo listAll(@RequestBody RVehicleConsumeMonth rVehicleConsumeMonth) {
        List<RVehicleConsumeMonth> list = rVehicleConsumeMonthService.selectRVehicleConsumeMonthListAll(rVehicleConsumeMonth);
        return getDataTable(list);
    }

    /**
     * 导出统计报-车辆消耗月度统计列表
     */
    @PreAuthorize(hasPermi = "system:month:export")
    @Log(title = "统计报-车辆消耗月度统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RVehicleConsumeMonth rVehicleConsumeMonth) throws IOException {
        List<RVehicleConsumeMonth> list = rVehicleConsumeMonthService.selectRVehicleConsumeMonthList(rVehicleConsumeMonth);
        ExcelUtil<RVehicleConsumeMonth> util = new ExcelUtil<RVehicleConsumeMonth>(RVehicleConsumeMonth.class);
        util.exportExcel(response, list, "month");
    }

    /**
     * 获取统计报-车辆消耗月度统计详细信息
     */
    @PreAuthorize(hasPermi = "system:month:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(rVehicleConsumeMonthService.selectRVehicleConsumeMonthById(id));
    }

    /**
     * 新增统计报-车辆消耗月度统计
     */
    @PreAuthorize(hasPermi = "car:vehicleConsumeMonth:add")
    @Log(title = "统计报-车辆消耗月度统计", businessType = BusinessType.INSERT)
    @PostMapping("/N02Add")
    public AjaxResult add() {
//        String date = DateUtils.getDate();
//        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//        Date date1=format1.parse(date);
        Date date1 = DateUtils.getNowDate();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date1);
        calendar.add(Calendar.DATE, -1);
        Date date2 = calendar.getTime();
        DateFormat format1 = new SimpleDateFormat("yyyy-MM");
        String mon = format1.format(date2);
        Date month = new Date();
        try {
            month = format1.parse(mon);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        GGoodsReceivingRecord gGoodsReceivingRecord = new GGoodsReceivingRecord();
        gGoodsReceivingRecord.setCreateTime(date2);
        List<GGoodsReceivingRecord> list = goodsReceivingRecordService.selectGGoodsReceivingRecordListNum(gGoodsReceivingRecord);

        FOilCardUsageRecord fOilCardUsageRecord = new FOilCardUsageRecord();
        fOilCardUsageRecord.setTransactionTime(date2);
        List<FOilCardUsageRecord> list1 = fOilCardUsageRecordService.selectFOilCardUsageRecordListNum(fOilCardUsageRecord);

        CVehicle cVehicle = new CVehicle();
        List<CVehicle> listCar = cVehicleService.selectCVehicleListCarZhuNum(cVehicle);
        List<RVehicleConsumeMonth> listMonth = new ArrayList<>();

        if (listCar.size() > 0) {
            for (int i = 0; i <listCar.size(); i++) {
                RVehicleConsumeMonth rVehicleConsumeMonth1=new RVehicleConsumeMonth();
                String Id = UUID.randomUUID().toString();
                rVehicleConsumeMonth1.setId(Id);
                rVehicleConsumeMonth1.setVehicleId(listCar.get(i).getId());
                rVehicleConsumeMonth1.setLicensePlate(listCar.get(i).getLicensePlate());
                rVehicleConsumeMonth1.setMonth(month);
                rVehicleConsumeMonth1.setDeleteFlag(1L);
                CVehicle pro = listCar.get(i);
                String LicensePlate = pro.getLicensePlate();
                List<FOilCardUsageRecord> listYou = list1.stream().filter(a -> Objects.equals(LicensePlate, a.getLicensePlate())).collect(Collectors.toList());
                if (listYou.size() > 0) {
                    rVehicleConsumeMonth1.setMonthFuelVolume(listYou.get(0).getOilNum());
                }
                List<GGoodsReceivingRecord> listNiao = list.stream().filter(a -> Objects.equals(LicensePlate, a.getLicensePlate())).collect(Collectors.toList());
                if (listNiao.size() > 0) {
                    rVehicleConsumeMonth1.setMonthUreaVolume(listNiao.get(0).getSpeNum());
                }
                listMonth.add(rVehicleConsumeMonth1);
            }

        }
        return toAjax(rVehicleConsumeMonthService.insertRVehicleConsumeMonthList(listMonth));
    }

    /**
     * 修改统计报-车辆消耗月度统计
     */
    @PreAuthorize(hasPermi = "car:vehicleConsumeMonth:edit")
    @Log(title = "统计报-车辆消耗月度统计", businessType = BusinessType.UPDATE)
    @PostMapping("/N03Edit")
    public AjaxResult edit(@RequestBody RVehicleConsumeMonth rVehicleConsumeMonth) {
        if(StringUtils.isNull(rVehicleConsumeMonth.getMonthEndMileage())){
            return AjaxResult.error("月末时点里程不能为空");
        }
        Date date = rVehicleConsumeMonth.getMonth();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);//当前时间前去一个月，即一个月前的时间
        date = calendar.getTime();

        RVehicleConsumeMonth rVehicleConsumeMonth2 = rVehicleConsumeMonthService.selectRVehicleConsumeMonthById(rVehicleConsumeMonth.getId());
        RVehicleConsumeMonth rVehicleConsumeMonth1 = new RVehicleConsumeMonth();
        rVehicleConsumeMonth1.setMonth(date);
        rVehicleConsumeMonth1.setVehicleId(rVehicleConsumeMonth2.getVehicleId());
        List<RVehicleConsumeMonth> list = rVehicleConsumeMonthService.selectRVehicleConsumeMonthList(rVehicleConsumeMonth1);

        BigDecimal monthEndMileage = BigDecimal.ZERO;
        if (list.size() > 0) {
            monthEndMileage = list.get(0).getMonthEndMileage();
        }
        BigDecimal meM = rVehicleConsumeMonth.getMonthEndMileage().subtract(monthEndMileage);
        rVehicleConsumeMonth.setMonthEndMileage(rVehicleConsumeMonth.getMonthEndMileage());
        rVehicleConsumeMonth.setLastMonthEndMileage(meM.longValue());
        Long jiaYou = rVehicleConsumeMonth2.getMonthFuelVolume();
        String Mem = meM.toString();
        String JiaYou = jiaYou.toString();
        BigDecimal A = new BigDecimal(Mem);
        BigDecimal B = new BigDecimal(JiaYou);
        BigDecimal C = new BigDecimal("100");
        BigDecimal youHao = B.divide(A.divide(C)).setScale(2, BigDecimal.ROUND_HALF_UP);

        Long niaoSu = rVehicleConsumeMonth2.getMonthUreaVolume();
        String NiaoSu = niaoSu.toString();
        BigDecimal D = new BigDecimal(NiaoSu);
        BigDecimal niaoHao = D.divide(A.divide(C)).setScale(2, BigDecimal.ROUND_HALF_UP);

        rVehicleConsumeMonth.setKmFuel(youHao);
        rVehicleConsumeMonth.setKmUrea(niaoHao);
        return toAjax(rVehicleConsumeMonthService.updateRVehicleConsumeMonth(rVehicleConsumeMonth));
    }

    /**
     * 删除统计报-车辆消耗月度统计
     */
    @PreAuthorize(hasPermi = "system:month:remove")
    @Log(title = "统计报-车辆消耗月度统计", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(rVehicleConsumeMonthService.deleteRVehicleConsumeMonthByIds(ids));
    }
}
