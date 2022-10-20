package com.dlzx.pfserverperson.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverperson.domain.DPerformance;
import com.dlzx.pfserverperson.domain.OTMTTransportTaskModel;
import com.dlzx.pfserverperson.service.IDPerformanceService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfserverperson.domain.DPerformanceRecord;
import com.dlzx.pfserverperson.service.IDPerformanceRecordService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 驾驶员-长途绩效记录Controller
 * 
 * @author dlzx
 * @date 2020-12-21
 */
@RestController
@RequestMapping("/person/performanceRecord")
public class DPerformanceRecordController extends BaseController
{
    @Autowired
    private IDPerformanceRecordService dPerformanceRecordService;
    @Autowired
    private IDPerformanceService idPerformanceService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询驾驶员-长途绩效记录列表
     */
    @PreAuthorize(hasPermi = "person:performanceRecord:list")
    @PostMapping("/G08List")
    public TableDataInfo list(@RequestBody DPerformanceRecord dPerformanceRecord)
    {
        startPage(dPerformanceRecord.getPageNum(),dPerformanceRecord.getPageSize());
        List<DPerformanceRecord> list = dPerformanceRecordService.selectDPerformanceRecordList(dPerformanceRecord);
        return getDataTable(list);
    }

    /**
     * 导出驾驶员-长途绩效记录列表
     */
    @PreAuthorize(hasPermi = "system:record:export")
    @Log(title = "驾驶员-长途绩效记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DPerformanceRecord dPerformanceRecord) throws IOException
    {
        List<DPerformanceRecord> list = dPerformanceRecordService.selectDPerformanceRecordList(dPerformanceRecord);
        ExcelUtil<DPerformanceRecord> util = new ExcelUtil<DPerformanceRecord>(DPerformanceRecord.class);
        util.exportExcel(response, list, "record");
    }

    /**
     * 获取驾驶员-长途绩效记录详细信息
     */
    @PreAuthorize(hasPermi = "person:performanceRecord:getInfo")
    @GetMapping(value = "/G09GetInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(dPerformanceRecordService.selectDPerformanceRecordById(id));
    }

    /**
     * 新增驾驶员-长途绩效记录
     */
    @PreAuthorize(hasPermi = "system:record:add")
    @Log(title = "驾驶员-长途绩效记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DPerformanceRecord dPerformanceRecord)
    {
        return toAjax(dPerformanceRecordService.insertDPerformanceRecord(dPerformanceRecord));
    }

    /**
     * 修改驾驶员-长途绩效记录
     */
    @PreAuthorize(hasPermi = "person:performanceRecord:edit")
    @Log(title = "驾驶员-长途绩效记录", businessType = BusinessType.UPDATE)
    @PostMapping("/G10Edit")
    public AjaxResult edit(@RequestBody DPerformanceRecord dPerformanceRecord)
    {
        LoginUser userInfo = tokenService.getLoginUser();
        dPerformanceRecord.setUpdateBy(userInfo.getUsername());
        return toAjax(dPerformanceRecordService.updateDPerformanceRecord(dPerformanceRecord));
    }

    /**
     * 删除驾驶员-长途绩效记录
     */
    @PreAuthorize(hasPermi = "system:record:remove")
    @Log(title = "驾驶员-长途绩效记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(dPerformanceRecordService.deleteDPerformanceRecordByIds(ids));
    }

    /**
     * 统计长途司机的绩效工资
     * @param dPerformanceRecord
     * @return
     */
    @PostMapping("/longPerMoney")
    public List<DPerformanceRecord> longPerMoney(@RequestBody DPerformanceRecord dPerformanceRecord){
        return dPerformanceRecordService.selectDPerformanceRecordSalaryList(dPerformanceRecord);
    }

    /**
     * 根据任务id查找绩效工资总额
     * @param id
     * @return
     */
    @GetMapping("/transportMoney/{id}")
    public BigDecimal transportMoney(@PathVariable(value = "id",required = false)String id){
        return dPerformanceRecordService.selectSumAmount(id);
    }


    /**
     * @describe car服务调用 - 新增长途绩效记录
     * @author DongCL
     * @date 2021-01-08 10:25
     * @param dPerformanceRecord
     * @return AjaxResult
     */
    @RequestMapping(path = "/createTaskRecord",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult create(@RequestBody DPerformanceRecord dPerformanceRecord)
    {
        return toAjax(dPerformanceRecordService.insertDPerformanceRecord(dPerformanceRecord));
    }

    /**
     * @describe car服务调用 - 获取绩效信息
     * @author DongCL
     * @date 2021-01-08 10:25
     * @param vehicleType 车型
     * @param routeId 线路ID
     * @param isVice 是否有对班
     * @return AjaxResult
     */
    @RequestMapping(path = "/performanceDetail",method = RequestMethod.POST)
    public List<DPerformance> performanceDetail(@RequestParam(value = "vehicleType",required = false)String vehicleType, @RequestParam(value = "routeId",required = false)String routeId,
                                          @RequestParam(value = "isVice",required = false)Long isVice)
    {
        return idPerformanceService.queryPerformance(vehicleType,routeId,isVice);
    }


    /**
     * @describe car服务调用 - 更新长途绩效的开始/结束时间
     * @author DongCL
     * @date 2021-01-08 10:25
     * @param otmtTransportTaskModel
     * @return AjaxResult
     */
    @RequestMapping(path = "/updateTaskTime",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult updateTaskTime(@RequestBody OTMTTransportTaskModel otmtTransportTaskModel)
    {
        return AjaxResult.success(dPerformanceRecordService.updateTaskTime(otmtTransportTaskModel));
    }



    /**
     * @describe car服务调用 - 根据任务ID获取长途绩效记录
     * @author DongCL
     * @date 2021-01-08 10:25
     * @param taskId 任务ID
     * @return AjaxResult
     */
    @RequestMapping(path = "/queryPerformanceById",method = RequestMethod.POST)
    public DPerformanceRecord queryPerformanceById(@RequestParam(value = "taskId",required = false)String taskId)
    {
        return dPerformanceRecordService.queryPerformanceById(taskId);
    }


    /**
     * @describe car服务调用 - 根据任务ID获取长途绩效记录
     * @author DongCL
     * @date 2021-01-08 10:25
     * @param dPerformanceRecord 长途绩效对象
     * @return AjaxResult
     */
    @RequestMapping(path = "/updatePerformanceRecord",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult updatePerformanceRecord(@RequestBody DPerformanceRecord dPerformanceRecord)
    {
        return AjaxResult.success(dPerformanceRecordService.updateDPerformanceRecord(dPerformanceRecord));
    }
}
