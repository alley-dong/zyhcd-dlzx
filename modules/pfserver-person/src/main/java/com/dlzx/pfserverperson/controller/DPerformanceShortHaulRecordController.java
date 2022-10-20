package com.dlzx.pfserverperson.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverperson.domain.DPerformanceRecordDuan;
import com.dlzx.pfserverperson.domain.TTransportTask;
import com.dlzx.pfserverperson.service.ITTransportTaskService;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfserverperson.domain.DPerformanceShortHaulRecord;
import com.dlzx.pfserverperson.service.IDPerformanceShortHaulRecordService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 驾驶员-短途绩效记录Controller
 *
 * @author dlzx
 * @date 2020-12-21
 */
@RestController
@RequestMapping("/person/performanceShortHaulRecord")
public class DPerformanceShortHaulRecordController extends BaseController {
    @Autowired
    private IDPerformanceShortHaulRecordService dPerformanceShortHaulRecordService;
    @Autowired
    private ITTransportTaskService tTransportTaskService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询驾驶员-短途绩效记录列表
     */
    @PreAuthorize(hasPermi = "person:performanceShortHaulRecord:list")
    @PostMapping("/G13List")
    public TableDataInfo list(@RequestBody DPerformanceShortHaulRecord dPerformanceShortHaulRecord) {
        startPage(dPerformanceShortHaulRecord.getPageNum(), dPerformanceShortHaulRecord.getPageSize());
        List<DPerformanceShortHaulRecord> list = dPerformanceShortHaulRecordService.selectDPerformanceShortHaulRecordList(dPerformanceShortHaulRecord);
        return getDataTable(list);
    }

    /**
     * 导出驾驶员-短途绩效记录列表
     */
    @PreAuthorize(hasPermi = "system:record:export")
    @Log(title = "驾驶员-短途绩效记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DPerformanceShortHaulRecord dPerformanceShortHaulRecord) throws IOException {
        List<DPerformanceShortHaulRecord> list = dPerformanceShortHaulRecordService.selectDPerformanceShortHaulRecordList(dPerformanceShortHaulRecord);
        ExcelUtil<DPerformanceShortHaulRecord> util = new ExcelUtil<DPerformanceShortHaulRecord>(DPerformanceShortHaulRecord.class);
        util.exportExcel(response, list, "record");
    }

    /**
     * 获取驾驶员-短途绩效记录详细信息
     */
    @PreAuthorize(hasPermi = "person:performanceShortHaulRecord:getInfo")
    @GetMapping(value = "/G13GetInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(dPerformanceShortHaulRecordService.selectDPerformanceShortHaulRecordById(id));
    }

    /**
     * 新增驾驶员-短途绩效记录
     */
    @PreAuthorize(hasPermi = "person:performanceShortHaulRecord:add")
    @Log(title = "驾驶员-短途绩效记录", businessType = BusinessType.INSERT)
    @PostMapping("/G13Add")
    public AjaxResult add(@RequestBody DPerformanceShortHaulRecord dPerformanceShortHaulRecord) {
        List<DPerformanceShortHaulRecord> listDuan = dPerformanceShortHaulRecordService.selectDPerformanceShortHaulRecordList(dPerformanceShortHaulRecord);
        if (listDuan.size() > 0) {
            return AjaxResult.error("该月份记录已存在");
        } else {
            TTransportTask tTransportTask = new TTransportTask();
            tTransportTask.setTaskEndTime(dPerformanceShortHaulRecord.getPerformanceDate());
            List<DPerformanceRecordDuan> list = tTransportTaskService.selectTTransportTaskListDuan(tTransportTask);
            List<DPerformanceShortHaulRecord> list1 = new ArrayList<>();
            LoginUser userInfo = tokenService.getLoginUser();
            for (DPerformanceRecordDuan dPerformanceRecordDuan : list) {
                String Id = UUID.randomUUID().toString();
                DPerformanceShortHaulRecord dPerformanceShortHaulRecord1 = new DPerformanceShortHaulRecord();
                dPerformanceShortHaulRecord1.setId(Id);
                dPerformanceShortHaulRecord1.setCreateBy(userInfo.getUsername());
                dPerformanceShortHaulRecord1.setPerformanceDate(dPerformanceShortHaulRecord.getPerformanceDate());
                dPerformanceShortHaulRecord1.setDriverId(dPerformanceRecordDuan.getDriverId());
                dPerformanceShortHaulRecord1.setDriverName(dPerformanceRecordDuan.getDriverName());
                dPerformanceShortHaulRecord1.setDriverTypeId(dPerformanceRecordDuan.getDriverTypeId());
                dPerformanceShortHaulRecord1.setDriverTypeName(dPerformanceRecordDuan.getName());
                dPerformanceShortHaulRecord1.setUnitPrice(dPerformanceRecordDuan.getAmount());
                dPerformanceShortHaulRecord1.setLicensePlate(dPerformanceRecordDuan.getMainLicensePlate());
                dPerformanceShortHaulRecord1.setTaskId(dPerformanceRecordDuan.getId());
                dPerformanceShortHaulRecord1.setVehicleId(dPerformanceRecordDuan.getMainVehicleId());
                dPerformanceShortHaulRecord1.setDeleteFlag(1L);
                list1.add(dPerformanceShortHaulRecord1);
            }
            if (list1.size() > 0) {
                return toAjax(dPerformanceShortHaulRecordService.insertDPerformanceShortHaulRecordList(list1));
            }else {
                return AjaxResult.error("无内容新增");
            }
        }
    }

    /**
     * 修改驾驶员-短途绩效记录
     */
    @PreAuthorize(hasPermi = "person:performanceShortHaulRecord:edit")
    @Log(title = "驾驶员-短途绩效记录", businessType = BusinessType.UPDATE)
    @PostMapping("/G13Edit")
    public AjaxResult edit(@RequestBody DPerformanceShortHaulRecord dPerformanceShortHaulRecord) {
        DPerformanceShortHaulRecord dPerformanceShortHaulRecord1 = dPerformanceShortHaulRecordService.selectDPerformanceShortHaulRecordById(dPerformanceShortHaulRecord.getId());
        Long num = dPerformanceShortHaulRecord.getNumber();
        dPerformanceShortHaulRecord1.setNumber(num);
        String Num = num.toString();
        BigDecimal NUM = new BigDecimal(Num);
        BigDecimal amo = NUM.multiply(dPerformanceShortHaulRecord.getUnitPrice());
        dPerformanceShortHaulRecord1.setAmount(amo);
        LoginUser userInfo = tokenService.getLoginUser();
        dPerformanceShortHaulRecord1.setUpdateBy(userInfo.getUsername());
        return toAjax(dPerformanceShortHaulRecordService.updateDPerformanceShortHaulRecord(dPerformanceShortHaulRecord1));
    }

    /**
     * 删除驾驶员-短途绩效记录
     */
    @PreAuthorize(hasPermi = "person:performanceShortHaulRecord:delete")
    @Log(title = "驾驶员-短途绩效记录", businessType = BusinessType.DELETE)
    @GetMapping("/G13Delete/{id}")
    public AjaxResult remove(@PathVariable String id) {
        LoginUser userInfo = tokenService.getLoginUser();
        DPerformanceShortHaulRecord dPerformanceShortHaulRecord = dPerformanceShortHaulRecordService.selectDPerformanceShortHaulRecordById(id);
        dPerformanceShortHaulRecord.setDeleteFlag(2L);
        dPerformanceShortHaulRecord.setUpdateBy(userInfo.getUsername());
        return toAjax(dPerformanceShortHaulRecordService.updateDPerformanceShortHaulRecord(dPerformanceShortHaulRecord));
    }

    /**
     * 统计所有短途司机的绩效工资
     * @param dPerformanceShortHaulRecord
     * @return
     */
    @PostMapping("/shortPerMoney")
    public List<DPerformanceShortHaulRecord> shortPerMoney(@RequestBody DPerformanceShortHaulRecord dPerformanceShortHaulRecord){
        return dPerformanceShortHaulRecordService.selectDPerShortSalary(dPerformanceShortHaulRecord);
    }

    /**
     * @describe car服务调用 - 插入短途绩效记录
     * @author DongCL
     * @date 2021-01-14 10:10
     * @param dPerformanceShortHaulRecord
     * @return
     */
    @RequestMapping(path = "/submitShortRecord",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult submitShortRecord(@RequestBody DPerformanceShortHaulRecord dPerformanceShortHaulRecord){
        return AjaxResult.success(dPerformanceShortHaulRecordService.insertDPerformanceShortHaulRecord(dPerformanceShortHaulRecord));
    }
}
