package com.dlzx.pfserverperson.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.pfserverperson.domain.DPerformanceShortHaulRecord;
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
import com.dlzx.pfserverperson.domain.DPerformanceOvertimeRecord;
import com.dlzx.pfserverperson.service.IDPerformanceOvertimeRecordService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 驾驶员-加班绩效记录Controller
 * 
 * @author dlzx
 * @date 2020-12-22
 */
@RestController
@RequestMapping("/person/performanceOvertimeRecord")
public class DPerformanceOvertimeRecordController extends BaseController
{
    @Autowired
    private IDPerformanceOvertimeRecordService dPerformanceOvertimeRecordService;

    /**
     * 查询驾驶员-加班绩效记录列表
     */
    @PreAuthorize(hasPermi = "person:performanceOvertimeRecord:list")
    @PostMapping("/G14List")
    public TableDataInfo list(@RequestBody DPerformanceOvertimeRecord dPerformanceOvertimeRecord)
    {
        startPage(dPerformanceOvertimeRecord.getPageNum(),dPerformanceOvertimeRecord.getPageSize());
        List<DPerformanceOvertimeRecord> list = dPerformanceOvertimeRecordService.selectDPerformanceOvertimeRecordList(dPerformanceOvertimeRecord);
        return getDataTable(list);
    }

    /**
     * 导出驾驶员-加班绩效记录列表
     */
    @PreAuthorize(hasPermi = "system:record:export")
    @Log(title = "驾驶员-加班绩效记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DPerformanceOvertimeRecord dPerformanceOvertimeRecord) throws IOException
    {
        List<DPerformanceOvertimeRecord> list = dPerformanceOvertimeRecordService.selectDPerformanceOvertimeRecordList(dPerformanceOvertimeRecord);
        ExcelUtil<DPerformanceOvertimeRecord> util = new ExcelUtil<DPerformanceOvertimeRecord>(DPerformanceOvertimeRecord.class);
        util.exportExcel(response, list, "record");
    }

    /**
     * 获取驾驶员-加班绩效记录详细信息
     */
    @PreAuthorize(hasPermi = "system:record:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(dPerformanceOvertimeRecordService.selectDPerformanceOvertimeRecordById(id));
    }

    /**
     * 新增驾驶员-加班绩效记录
     */
    @PreAuthorize(hasPermi = "system:record:add")
    @Log(title = "驾驶员-加班绩效记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DPerformanceOvertimeRecord dPerformanceOvertimeRecord)
    {
        return toAjax(dPerformanceOvertimeRecordService.insertDPerformanceOvertimeRecord(dPerformanceOvertimeRecord));
    }

    /**
     * 修改驾驶员-加班绩效记录
     */
    @PreAuthorize(hasPermi = "system:record:edit")
    @Log(title = "驾驶员-加班绩效记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DPerformanceOvertimeRecord dPerformanceOvertimeRecord)
    {
        return toAjax(dPerformanceOvertimeRecordService.updateDPerformanceOvertimeRecord(dPerformanceOvertimeRecord));
    }

    /**
     * 删除驾驶员-加班绩效记录
     */
    @PreAuthorize(hasPermi = "system:record:remove")
    @Log(title = "驾驶员-加班绩效记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(dPerformanceOvertimeRecordService.deleteDPerformanceOvertimeRecordByIds(ids));
    }

    /**
     * 所有司机加班费用
     * @param dPerformanceOvertimeRecord
     * @return
     */
    @PostMapping("/overMoney")
    public List<DPerformanceOvertimeRecord> overMoney(@RequestBody DPerformanceOvertimeRecord dPerformanceOvertimeRecord){
        return dPerformanceOvertimeRecordService.selectDPerOverTimeList(dPerformanceOvertimeRecord);
    }


    /**
     * @describe car服务调用 - 插入加班绩效记录
     * @author DongCL
     * @date 2021-01-14 10:10
     * @param dPerformanceOvertimeRecord
     * @return
     */
    @RequestMapping(path = "/submitOverTimeRecord",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public AjaxResult submitOverTimeRecord(@RequestBody DPerformanceOvertimeRecord dPerformanceOvertimeRecord){
        return AjaxResult.success(dPerformanceOvertimeRecordService.insertDPerformanceOvertimeRecord(dPerformanceOvertimeRecord));
    }
}
