package com.dlzx.pfserverperson.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.utils.domain.EveryDayDetailExcelVm;
import com.dlzx.common.core.utils.domain.PContractEvaluatExcel;
import com.dlzx.common.core.utils.domain.VacationDayExcelVm;
import com.dlzx.common.core.utils.poi.ExportExcel;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverperson.domain.DDriver;
import com.dlzx.pfserverperson.domain.DDriverType;
import com.dlzx.pfserverperson.domain.DLeaveType;
import com.dlzx.pfserverperson.domain.DVacation;
import com.dlzx.pfserverperson.domain.TTransportTask;
import com.dlzx.pfserverperson.domain.model.EveryDayDetailVm;
import com.dlzx.pfserverperson.domain.model.VacationDayVm;
import com.dlzx.pfserverperson.service.IDDriverService;
import com.dlzx.pfserverperson.service.IDDriverTypeService;
import com.dlzx.pfserverperson.service.IDLeaveTypeService;
import com.dlzx.pfserverperson.service.IDVacationService;
import com.dlzx.pfserverperson.service.ITTransportTaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
import com.dlzx.pfserverperson.domain.DVacationDay;
import com.dlzx.pfserverperson.service.IDVacationDayService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 驾驶员-每日考勤记录Controller
 *
 * @author dlzx
 * @date 2020-12-21
 */
@RestController
@RequestMapping("/person/vacationDay")
public class DVacationDayController extends BaseController {
    @Autowired
    private IDVacationDayService dVacationDayService;

    @Autowired
    private IDDriverService driverService;

    @Autowired
    private IDDriverTypeService dDriverTypeService;

    @Autowired
    private ITTransportTaskService tTransportTaskService;

    @Autowired
    private IDVacationService dVacationService;

    @Autowired
    private IDLeaveTypeService dLeaveTypeService;

    /**
     * 查询驾驶员-每日考勤记录列表
     */
    @PreAuthorize(hasPermi = "person:vacationDay:list")
    @PostMapping("/A54List")
    public TableDataInfo A54List(@RequestBody DVacationDay dVacationDay) {
        if (StringUtils.isNull(dVacationDay.getVacationDate())){
            throw new CustomException("请选择考勤月份");
        }
        List<VacationDayVm> vacationDayVms = dVacationDayService.selectDVacationDayStatisticsList(dVacationDay);
        return getDataTable(vacationDayVms);
    }

    /**
     * 导出驾驶员-每日考勤记录列表
     */
    @Log(title = "驾驶员-每日考勤记录", businessType = BusinessType.EXPORT)
    @GetMapping("/vacationDayExport")
    public void export(HttpServletResponse response, DVacationDay dVacationDay) throws IOException {
        List<VacationDayVm> vacationDayVms = dVacationDayService.selectDVacationDayStatisticsList(dVacationDay);

        if (vacationDayVms!= null && vacationDayVms.size()>0){
            // 转类型
            List<VacationDayExcelVm> vacationDayExcelVms = new ArrayList<>();
            for (VacationDayVm vacationDayVm : vacationDayVms) {
                // 最外层
                VacationDayExcelVm vacationDayExcelVm = new VacationDayExcelVm();
                BeanUtils.copyProperties(vacationDayVm,vacationDayExcelVm);
                // 考勤日期转类型
                List<EveryDayDetailExcelVm> everyDayDetailExcelVms = new ArrayList<>();
                for (EveryDayDetailVm everyDayDetailVm : vacationDayVm.getdVacationDayList()) {
                    EveryDayDetailExcelVm everyDayDetaildata = new EveryDayDetailExcelVm();
                    BeanUtils.copyProperties(everyDayDetailVm,everyDayDetaildata);
                    everyDayDetailExcelVms.add(everyDayDetaildata);
                }
                vacationDayExcelVm.setdVacationDayList(everyDayDetailExcelVms);
                vacationDayExcelVms.add(vacationDayExcelVm);
            }
            // 导出
            ExportExcel<VacationDayExcelVm> util = new ExportExcel<>();
            util.VacationDayExcel(response,vacationDayExcelVms);
        }
    }

    /**
     * 关联添加
     */
    @RequestMapping(path = "/DVacationAdd",method = RequestMethod.POST)
    public void DVacationAdd() {

        // 获取所有的驾驶员（劳务工、长途）
        List<DDriver> dDrivers = driverService.selectDDriverOfIdsList();
        // 取出ID集合
        List<String> driverIds = dDrivers.stream().map(DDriver::getId).collect(Collectors.toList());
        // 查询驾驶员的任务 and 进行中
        List<TTransportTask> tTransportTaskList = tTransportTaskService.selectTTransportTaskByDriverId(driverIds);
        List<TTransportTask> tTransportTaskViceDriverList = tTransportTaskService.selectTTransportTaskByViceDriverId(driverIds);
        // 系统时间
        Date date = new Date();
        // 入库请假集合
        List<DVacationDay> dVacationDays = new ArrayList<>();
        dDrivers.forEach(dDriver->{
            DVacationDay dVacationDay = new DVacationDay();
            // 默认没钱
            dVacationDay.setBasicSalary(BigDecimal.ZERO);

            // 获取驾驶员类型
            BigDecimal divide = BigDecimal.ZERO;
            DDriverType dDriverType = dDriverTypeService.selectDDriverTypeById(dDriver.getDriverTypeId());
            if (dDriverType != null && dDriverType.getBasicSalary() != null){
                BigDecimal basicSalary = dDriverType.getBasicSalary();

                // 获取当月天数
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                BigDecimal bigDecimal = BigDecimal.valueOf(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                // 计算日薪
                divide = basicSalary.divide(bigDecimal, 2);
            }

            TTransportTask tTransportTask = tTransportTaskList.stream().filter(t -> t.getDriverId().equals(dDriver.getId())).findFirst().orElse(null);
            TTransportTask tTransportTaskByViceDriver = tTransportTaskViceDriverList.stream().filter(t -> t.getViceDriverId().equals(dDriver.getId())).findFirst().orElse(null);
            if (tTransportTask != null || tTransportTaskByViceDriver != null) {

                if (tTransportTask != null && tTransportTask.getStatus()==1) {
                    dVacationDay.setStatus(1L);
                    dVacationDay.setBasicSalary(divide);
                }
                if (tTransportTaskByViceDriver != null && tTransportTaskByViceDriver.getStatus()==1) {
                    dVacationDay.setStatus(1L);
                    dVacationDay.setBasicSalary(divide);
                }
            }else {
                DVacation dVacation = new DVacation();
                dVacation.setDriverId(dDriver.getId());
                List<DVacation> collect = dVacationService.selectDVacationList(dVacation)
                        .stream()
                        .filter(d -> d.getvStartTime().getTime() < date.getTime()
                                && d.getvEndTime().getTime() > date.getTime())
                        .collect(Collectors.toList());
                if (collect.size() > 0) {
                    DLeaveType dLeaveType = dLeaveTypeService.selectDLeaveTypeById(collect.stream().findFirst().orElse(new DVacation()).getType());
                    if (dLeaveType != null) {
                        if (dLeaveType.getIsSalary() == 1) {
                            dVacationDay.setBasicSalary(divide);
                        }
                    }
                    dVacationDay.setStatus(3L);
                }else {
                    dVacationDay.setStatus(2L);
                    // 在家也有钱
                    dVacationDay.setBasicSalary(divide);
                }
            }
            dVacationDay.setId(UUID.randomUUID().toString());
            dVacationDay.setVacationDate(new Date());
            dVacationDay.setDriverId(dDriver.getId());
            dVacationDay.setDriverName(dDriver.getName());
            dVacationDay.setDriverTypeId(dDriver.getDriverTypeId());
            dVacationDay.setDeleteFlag(1L);
            dVacationDay.setCreateBy("定时");
            dVacationDay.setCreateTime(date);
            dVacationDays.add(dVacationDay);
        });

        dVacationDayService.insertList(dVacationDays);
    }


    /**
     * 查询驾驶员-每日考勤记录列表
     */
    @PreAuthorize(hasPermi = "person:vacationDay:list")
    @GetMapping("/list")
    public TableDataInfo list(DVacationDay dVacationDay) {
        startPage();
        List<DVacationDay> list = dVacationDayService.selectDVacationDayList(dVacationDay);
        return getDataTable(list);
    }

    /**
     * 查询驾驶员-每日考勤记录列表
     */
    @PreAuthorize(hasPermi = "person:vacationDay:list")
    @PostMapping("/A53List")
    public TableDataInfo A53List(@RequestBody DVacationDay dVacationDay) {
        startPage(dVacationDay.getPageNum(), dVacationDay.getPageSize());
        List<DVacationDay> list = dVacationDayService.selectDVacationDayCountList(dVacationDay);
        return getDataTable(list);
    }

    /**
     * 获取驾驶员-每日考勤记录详细信息
     */
    @PreAuthorize(hasPermi = "person:vacationDay:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(dVacationDayService.selectDVacationDayById(id));
    }

    /**
     * 新增驾驶员-每日考勤记录
     */
    @PreAuthorize(hasPermi = "person:vacationDay:add")
    @Log(title = "驾驶员-每日考勤记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DVacationDay dVacationDay) {
        return toAjax(dVacationDayService.insertDVacationDay(dVacationDay));
    }

    /**
     * 修改驾驶员-每日考勤记录
     */
    @PreAuthorize(hasPermi = "person:vacationDay:edit")
    @Log(title = "驾驶员-每日考勤记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DVacationDay dVacationDay) {
        return toAjax(dVacationDayService.updateDVacationDay(dVacationDay));
    }

    /**
     * 删除驾驶员-每日考勤记录
     */
    @PreAuthorize(hasPermi = "person:vacationDay:remove")
    @Log(title = "驾驶员-每日考勤记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(dVacationDayService.deleteDVacationDayByIds(ids));
    }

    /**
     * 计算当前月的底薪
     */
    @PostMapping("/daySalary")
    public List<DVacationDay> daySalary(@RequestBody DVacationDay dVacationDay) {
        return dVacationDayService.selectDVacationDaySalaryByDriverId(dVacationDay);
    }
}
