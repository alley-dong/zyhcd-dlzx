package com.dlzx.pfservercar.controller;

import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.domain.AddRepairRecord;
import com.dlzx.pfservercar.domain.CVehicleInspectRecord;
import com.dlzx.pfservercar.domain.RRepairDetailed;
import com.dlzx.pfservercar.service.IRRepairDetailedService;
import com.dlzx.pfservercar.service.IRRepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ylh
 * @program:dlzx
 * @description：统计费用 ：维修保养费用
 * @create:2020-12-14 15-42
 */
@RestController
@RequestMapping("/repair/inspection")
public class CostSticsRepairController extends BaseController {

    @Autowired
    private IRRepairDetailedService repairDetailedService;
    @Autowired
    private TokenService tokenService;


    /**
     * 维修保养费用
     * @param addRepairRecord
     * @return
     */
    @Log(title = "维修保养费用")
    @PreAuthorize(hasPermi = "pfservercar:repairinspection:list")
    @PostMapping("/M01List")
    public TableDataInfo H06AddList(@RequestBody AddRepairRecord addRepairRecord){
        BigDecimal totalCost = BigDecimal.ZERO;
        startPage(addRepairRecord.getPageNum(),addRepairRecord.getPageSize());
        RRepairDetailed rRepairDetailed = new RRepairDetailed();
        rRepairDetailed.setCode(addRepairRecord.getCode());
        rRepairDetailed.setMainLicensePlate(addRepairRecord.getMainLicensePlate());
        rRepairDetailed.setHangLicensePlate(addRepairRecord.getHangLicensePlate());
        rRepairDetailed.setGarageName(addRepairRecord.getGarageName());
        rRepairDetailed.setName(addRepairRecord.getName());
        rRepairDetailed.setStartTime(addRepairRecord.getStartTime());
        rRepairDetailed.setFinshTime(addRepairRecord.getFinishTime());
        rRepairDetailed.setType(addRepairRecord.getType());
        rRepairDetailed.setRepairType(addRepairRecord.getRepairType());
        if (tokenService.getLoginUser().getUserid() != null&&(!tokenService.getLoginUser().getUserid().equals("1"))){
            rRepairDetailed.setGarageId(tokenService.getLoginUser().getSysUser().getGarageId());
        }
        //查询列表
        List<RRepairDetailed> rRepairDetailedList = repairDetailedService.selectCostVehicleList(rRepairDetailed);
        //去除冗余字段
        List<AddRepairRecord> addRepairRecordList = new ArrayList<>();
        for (RRepairDetailed rRepairDetailedOne:rRepairDetailedList){
            AddRepairRecord addRepairRecordOne = new AddRepairRecord();
            addRepairRecordOne.setId(rRepairDetailedOne.getId());
            addRepairRecordOne.setCode(rRepairDetailedOne.getRepair().getCode());
            addRepairRecordOne.setGarageName(rRepairDetailedOne.getGarageName());
            addRepairRecordOne.setHangLicensePlate(rRepairDetailedOne.getRepair().getHangLicensePlate());
            addRepairRecordOne.setMainLicensePlate(rRepairDetailedOne.getRepair().getMainLicensePlate());
            addRepairRecordOne.setCompleteTime(rRepairDetailedOne.getRepair().getCompleteTime());
            addRepairRecordOne.setName(rRepairDetailedOne.getName());
            addRepairRecordOne.setUnit(rRepairDetailedOne.getUnit());
            addRepairRecordOne.setUnitPrice(rRepairDetailedOne.getUnitPrice());
            addRepairRecordOne.setDuration(rRepairDetailedOne.getDuration());
            addRepairRecordOne.setAmount(rRepairDetailedOne.getAmount());
            addRepairRecordOne.setNumber(rRepairDetailedOne.getNumber());
            addRepairRecordOne.setType(rRepairDetailedOne.getRepair().getType());
            addRepairRecordOne.setRepairType(rRepairDetailedOne.getRepair().getRepairType());
            addRepairRecordList.add(addRepairRecordOne);
        }
        //计算总金额
        RRepairDetailed rRepair = new RRepairDetailed();
        rRepair.setCode(addRepairRecord.getCode());
        rRepair.setMainLicensePlate(addRepairRecord.getMainLicensePlate());
        rRepair.setHangLicensePlate(addRepairRecord.getHangLicensePlate());
        rRepair.setGarageName(addRepairRecord.getGarageName());
        rRepair.setName(addRepairRecord.getName());
        rRepair.setStartTime(addRepairRecord.getStartTime());
        rRepair.setFinshTime(addRepairRecord.getFinishTime());
        rRepair.setType(addRepairRecord.getType());
        rRepair.setRepairType(addRepairRecord.getRepairType());
        List<RRepairDetailed> rRepairDetailedLists = repairDetailedService.selectCostVehicleList(rRepair);
        for (RRepairDetailed repairDetailedList : rRepairDetailedLists) {
            totalCost = totalCost.add(repairDetailedList.getAmount());
        }

        int isAdmin = 1;
        if (tokenService.getLoginUser().getSysUser().getGarageId() != null){
            isAdmin = 0;
        }
        // 定义返回集合
        List<Map<String,Object>> mapList = new ArrayList<>();

        // 定义返回集合 对象
        Map<String,Object> map = new HashMap<>();
        map.put("totalCost",totalCost);
        map.put("isAdmin",isAdmin);
        map.put("list",rRepairDetailedList);
        mapList.add(map);
        return getDataTableMap(mapList,rRepairDetailedList);
    }

    /**
     * 维修保养费用导出
     */
    @PreAuthorize(hasPermi = "pfservercar:repairinspection:export")
    @Log(title = "维修保养费用导出", businessType = BusinessType.EXPORT)
    @PostMapping("/M01Export")
    public void export(HttpServletResponse response, AddRepairRecord addRepairRecord) throws IOException, ParseException {
        RRepairDetailed rRepairDetailed = new RRepairDetailed();
        rRepairDetailed.setCode(addRepairRecord.getCode());
        rRepairDetailed.setMainLicensePlate(addRepairRecord.getMainLicensePlate());
        rRepairDetailed.setHangLicensePlate(addRepairRecord.getHangLicensePlate());
        rRepairDetailed.setGarageName(addRepairRecord.getGarageName());
        rRepairDetailed.setName(addRepairRecord.getName());
        rRepairDetailed.setStartTime(addRepairRecord.getStartTime());
        rRepairDetailed.setFinshTime(addRepairRecord.getFinishTime());
        rRepairDetailed.setType(addRepairRecord.getType());
        rRepairDetailed.setRepairType(addRepairRecord.getRepairType());
        //查询列表
        List<RRepairDetailed> rRepairDetailedList = repairDetailedService.selectCostVehicleList(rRepairDetailed);
        //去除冗余字段
        List<AddRepairRecord> addRepairRecordList = new ArrayList<>();
        for (RRepairDetailed rRepairDetailedOne:rRepairDetailedList){
            AddRepairRecord addRepairRecordOne = new AddRepairRecord();
            addRepairRecordOne.setId(rRepairDetailedOne.getId());
            addRepairRecordOne.setCode(rRepairDetailedOne.getRepair().getCode());
            addRepairRecordOne.setGarageName(rRepairDetailedOne.getGarageName());
            addRepairRecordOne.setHangLicensePlate(rRepairDetailedOne.getRepair().getHangLicensePlate());
            addRepairRecordOne.setMainLicensePlate(rRepairDetailedOne.getRepair().getMainLicensePlate());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            addRepairRecordOne.setcTime(format.format(rRepairDetailedOne.getRepair().getCompleteTime()));
//            addRepairRecordOne.setCompleteTime(rRepairDetailedOne.getRepair().getCompleteTime());
            addRepairRecordOne.setName(rRepairDetailedOne.getName());
            addRepairRecordOne.setUnit(rRepairDetailedOne.getUnit());
            addRepairRecordOne.setUnitPrice(rRepairDetailedOne.getUnitPrice());
            addRepairRecordOne.setDuration(rRepairDetailedOne.getDuration());
            addRepairRecordOne.setAmount(rRepairDetailedOne.getAmount());
            addRepairRecordOne.setNumber(rRepairDetailedOne.getNumber());
            addRepairRecordOne.setType(rRepairDetailedOne.getRepair().getType());
            addRepairRecordOne.setRepairType(rRepairDetailedOne.getRepair().getRepairType());
            addRepairRecordList.add(addRepairRecordOne);
        }
        ExcelUtil<AddRepairRecord> util = new ExcelUtil<AddRepairRecord>(AddRepairRecord.class);
        util.exportExcel(response, addRepairRecordList, "RRepairDetailed");
    }
}
