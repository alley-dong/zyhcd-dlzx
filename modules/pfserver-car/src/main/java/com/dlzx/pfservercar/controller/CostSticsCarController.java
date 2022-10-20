package com.dlzx.pfservercar.controller;

import com.dlzx.common.core.domain.R;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.CVehicleInspectRecord;
import com.dlzx.pfservercar.domain.RRepairDetailed;
import com.dlzx.pfservercar.service.ICVehicleInspectRecordService;
import com.dlzx.pfservercar.service.IRRepairDetailedService;
import com.dlzx.pfservercar.service.IRRepairItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ylh
 * @program:dlzx
 * @description：费用统计 车检费
 * @create:2020-12-14 12-00
 */
@RestController
@RequestMapping("/car/inspection")
public class CostSticsCarController extends BaseController {

    @Autowired
    private ICVehicleInspectRecordService icVehicleInspectRecordService;

    @Log(title = "车检费用")
    @PreAuthorize(hasPermi = "pfservercar:cost:carinspection")
    @PostMapping("/M08List")
    public TableDataInfo M08List(@RequestBody CVehicleInspectRecord cVehicleInspectRecord){
        startPage(cVehicleInspectRecord.getPageNum(),cVehicleInspectRecord.getPageSize());
        List<CVehicleInspectRecord> cVehicleInspectRecordList =icVehicleInspectRecordService.selectCVehicleInspectRecordList(cVehicleInspectRecord);
        BigDecimal totalVehiceInspectCost = BigDecimal.ZERO;
        BigDecimal totalOperateInspectCost = BigDecimal.ZERO;
        BigDecimal totalReviewCost = BigDecimal.ZERO;
        BigDecimal allTotalCost = BigDecimal.ZERO;
        //循环计算合计金额
        CVehicleInspectRecord data = new CVehicleInspectRecord();
        data.setLicensePlate(cVehicleInspectRecord.getLicensePlate());
        data.setStartTime(cVehicleInspectRecord.getStartTime());
        data.setFinshTime(cVehicleInspectRecord.getFinshTime());
        List<CVehicleInspectRecord> datas =icVehicleInspectRecordService.selectCVehicleInspectRecordList(data);
        for (CVehicleInspectRecord cVehicleInspectRecordOne:datas){
            totalVehiceInspectCost = totalVehiceInspectCost.add(cVehicleInspectRecordOne.getVehiceInspectCost());
            totalOperateInspectCost = totalOperateInspectCost.add(cVehicleInspectRecordOne.getOperateInspectCost());
            totalReviewCost = totalReviewCost.add(cVehicleInspectRecordOne.getReviewCost());
            allTotalCost = allTotalCost.add(cVehicleInspectRecordOne.getTotalCost());
        }

        // 定义返回集合
        List<Map<String,Object>> mapList = new ArrayList<>();

        // 定义返回集合 对象
        Map<String,Object> map = new HashMap<>();
        map.put("totalVehiceInspectCost",totalVehiceInspectCost);
        map.put("totalOperateInspectCost",totalOperateInspectCost);
        map.put("totalReviewCost",totalReviewCost);
        map.put("allTotalCost",allTotalCost);
        map.put("list",cVehicleInspectRecordList);
        mapList.add(map);
        return getDataTableMap(mapList,cVehicleInspectRecordList);
    }

    /**
     * 导出统计车检费用表
     */
    @PreAuthorize(hasPermi = "pfservercar:cost:export")
    @Log(title = "车辆档案", businessType = BusinessType.EXPORT)
    @PostMapping("/M08Export")
    public void export(HttpServletResponse response, CVehicleInspectRecord cVehicleInspectRecord) throws IOException
    {
        List<CVehicleInspectRecord> cVehicleInspectRecordList =icVehicleInspectRecordService.selectCVehicleInspectRecordList(cVehicleInspectRecord);
        ExcelUtil<CVehicleInspectRecord> util = new ExcelUtil<CVehicleInspectRecord>(CVehicleInspectRecord.class);
        util.exportExcel(response, cVehicleInspectRecordList, "CVehicleInspectRecord");
    }
}
