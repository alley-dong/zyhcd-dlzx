package com.dlzx.pfservercar.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.pfservercar.domain.FOilCard;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfservercar.domain.FOilCardUsageRecord;
import com.dlzx.pfservercar.service.IFOilCardUsageRecordService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 燃料管理-油卡使用记录Controller
 * 
 * @author dlzx
 * @date 2020-12-02
 */
@RestController
@RequestMapping("/oilCardRecord")
public class FOilCardUsageRecordController extends BaseController
{
    @Autowired
    private IFOilCardUsageRecordService fOilCardUsageRecordService;

    /**
     * 油料使用记录-油料使用记录列表
     */
    @PreAuthorize(hasPermi = "pfservercar:record:list")
    @PostMapping("/I05OilPlantsRecordList")
    public TableDataInfo list(@RequestBody FOilCardUsageRecord fOilCardUsageRecord) throws ParseException {

        startPage(fOilCardUsageRecord.getPageNum(),fOilCardUsageRecord.getPageSize());
        List<FOilCardUsageRecord> list = fOilCardUsageRecordService.selectFOilCardUsageRecordList(fOilCardUsageRecord);
        for (FOilCardUsageRecord oilCardUsageRecord : list) {
            oilCardUsageRecord.setAdminShowTransactionTime(oilCardUsageRecord.getTransactionTime());
        }
        List<FOilCardUsageRecord> fOilCardUsageRecords = list.stream()
                .filter(l -> "1".equals(l.getIsShowType()))
                .collect(Collectors.toList());
        return getDataTable(fOilCardUsageRecords);
    }

    /**
     * 异常记录-油料使用异常记录列表
     */
    @PreAuthorize(hasPermi = "pfservercar:record:abnormalList")
    @PostMapping("/I06OilPlantsAbnormalRecordList")
    public TableDataInfo abnormalList(@RequestBody FOilCardUsageRecord fOilCardUsageRecord)
    {
        startPage(fOilCardUsageRecord.getPageNum(),fOilCardUsageRecord.getPageSize());
        List<FOilCardUsageRecord> list = fOilCardUsageRecordService.selectFOilCardUsageAbnormalRecordList(fOilCardUsageRecord);
        for (FOilCardUsageRecord oilCardUsageRecord : list) {
            oilCardUsageRecord.setAdminShowTransactionTime(oilCardUsageRecord.getTransactionTime());
        }
        return getDataTable(list);
    }

    /**
     * 油料使用记录-油料使用记录导入
     */
    @ApiOperation("油料使用记录导入")
    @PreAuthorize(hasPermi = "pfservercar:record:import")
    @Log(title = "油料使用记录导入", businessType = BusinessType.IMPORT)
    @PostMapping("/I05OilPlantsRecordImport")
    public AjaxResult I01OilCardImport(@Validated MultipartFile file,String routeId){
        //excel导入判断数据格式和表格式
        ExcelUtil<FOilCardUsageRecord> util = new ExcelUtil<FOilCardUsageRecord>(FOilCardUsageRecord.class);
        List<FOilCardUsageRecord> fOilCardUsageRecords = new ArrayList<>();
        try {
            fOilCardUsageRecords = util.importExcel(file.getInputStream());
        } catch (Exception e){
            e.printStackTrace();
            e.getMessage();
            return AjaxResult.error("数据格式有误");
        }
        //excel导入数据判断数据内容是否符合要求
        Map<String,Integer> message = fOilCardUsageRecordService.importFOilCardUsageRecord(fOilCardUsageRecords,routeId);
        if(message.get("code")>0){
            if (message.get("count")>0){
                return AjaxResult.success("导入成功"+"，有异常记录！");
            }
            return AjaxResult.success("导入成功！");
        } else {
            return AjaxResult.error("导入失败！");
        }
    }

    /**
     * 获取燃料管理-油卡使用记录详细信息
     */
    @PreAuthorize(hasPermi = "pfservercar:record:info")
    @GetMapping(value = "/detail/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(fOilCardUsageRecordService.selectFOilCardUsageRecordById(id));
    }

    /**
     * 新增燃料管理-油卡使用记录
     */
    @PreAuthorize(hasPermi = "pfservercar:record:add")
    @Log(title = "燃料管理-油卡使用记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody FOilCardUsageRecord fOilCardUsageRecord)
    {
        return toAjax(fOilCardUsageRecordService.insertFOilCardUsageRecord(fOilCardUsageRecord));
    }

    /**
     * 修改燃料管理-油卡使用记录
     */
    @PreAuthorize(hasPermi = "pfservercar:record:edit")
    @Log(title = "燃料管理-油卡使用记录", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public AjaxResult edit(@RequestBody FOilCardUsageRecord fOilCardUsageRecord)
    {
        return toAjax(fOilCardUsageRecordService.updateFOilCardUsageRecord(fOilCardUsageRecord));
    }

    /**
     * 删除燃料管理-油卡使用记录
     */
    @PreAuthorize(hasPermi = "pfservercar:record:delete")
    @Log(title = "燃料管理-油卡使用记录", businessType = BusinessType.DELETE)
	@GetMapping("/delete{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(fOilCardUsageRecordService.deleteFOilCardUsageRecordByIds(ids));
    }

    /**
     * 导出燃料管理-油卡使用记录列表
     */
    @PreAuthorize(hasPermi = "pfservercar:record:export")
    @Log(title = "燃料管理-油卡使用记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FOilCardUsageRecord fOilCardUsageRecord) throws IOException
    {
        List<FOilCardUsageRecord> list = fOilCardUsageRecordService.selectFOilCardUsageRecordList(fOilCardUsageRecord);
        ExcelUtil<FOilCardUsageRecord> util = new ExcelUtil<FOilCardUsageRecord>(FOilCardUsageRecord.class);
        util.exportExcel(response, list, "record");
    }
}
