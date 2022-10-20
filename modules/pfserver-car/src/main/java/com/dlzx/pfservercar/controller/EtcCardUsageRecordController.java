package com.dlzx.pfservercar.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.constant.HttpStatus;
import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.utils.poi.ExcelUtilh;
import com.dlzx.pfservercar.domain.EtcCard;
import com.dlzx.pfservercar.domain.EtcCardUsageRecordImport;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import com.dlzx.pfservercar.domain.EtcCardUsageRecord;
import com.dlzx.pfservercar.service.IEtcCardUsageRecordService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * ETC管理-ETC卡使用记录Controller
 * 
 * @author dlzx
 * @date 2020-12-02
 */
@RestController
@RequestMapping("/car/etcCardUsageRecord")
public class EtcCardUsageRecordController extends BaseController
{
    @Autowired
    private IEtcCardUsageRecordService etcCardUsageRecordService;

    /**
     * 查询ETC管理-ETC卡使用记录列表
     */
    @PreAuthorize(hasPermi = "car:etcCardUsageRecord:list")
    @PostMapping("/J05List")
    public TableDataInfo list(@RequestBody EtcCardUsageRecord etcCardUsageRecord)
    {
        startPage(etcCardUsageRecord.getPageNum(),etcCardUsageRecord.getPageSize());
        List<EtcCardUsageRecord> list = etcCardUsageRecordService.selectEtcCardUsageRecordListTime(etcCardUsageRecord);
        return getDataTable(list);
    }

    /**
     * 导出ETC管理-ETC卡使用记录列表
     */
    @PreAuthorize(hasPermi = "system:record:export")
    @Log(title = "ETC管理-ETC卡使用记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EtcCardUsageRecord etcCardUsageRecord) throws IOException
    {
        List<EtcCardUsageRecord> list = etcCardUsageRecordService.selectEtcCardUsageRecordList(etcCardUsageRecord);
        ExcelUtil<EtcCardUsageRecord> util = new ExcelUtil<EtcCardUsageRecord>(EtcCardUsageRecord.class);
        util.exportExcel(response, list, "record");
    }
    /**
     * 校验ETC管理-ETC卡使用记录列表
     */
    @PreAuthorize(hasPermi = "car:etcCardUsageRecord:check")
    @PostMapping("/J05Check")
    public void check(@Validated HttpServletResponse response,MultipartFile file,Date firstTime ,Date finishTime) throws IOException, ParseException {
        ExcelUtil<EtcCardUsageRecordImport> util = new ExcelUtil<EtcCardUsageRecordImport>(EtcCardUsageRecordImport.class);
        List<EtcCardUsageRecordImport> recordList = new ArrayList<>();
        try {
            recordList = util.importExcel(file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            throw new CustomException("无文件校验");
        }
        if (StringUtils.isNull(recordList) || recordList.size() == 0) {
            throw new CustomException("校验数据为空！");
        }
        EtcCardUsageRecord etcCardUsageRecord =new EtcCardUsageRecord();
        etcCardUsageRecord.setFirstTime(firstTime);
        etcCardUsageRecord.setFinishTime(finishTime);
        List<EtcCardUsageRecord> list = etcCardUsageRecordService.selectEtcCardUsageRecordListTime(etcCardUsageRecord);

        List<EtcCardUsageRecord> list1=new ArrayList<>();
        for (EtcCardUsageRecord pro:list) {
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
            String LicensePlate = pro.getLicensePlate();
            String EtcCardNumber = pro.getEtcCardNumber();
            BigDecimal Amount = pro.getAmount();
            Date InboundTime = pro.getInboundTime();
            Date OutboundTime = pro.getOutboundTime();
            Date OutTime = sdf.parse(sdf.format(OutboundTime));
            String RoadSection = pro.getRoadSection();

            List<EtcCardUsageRecordImport> list4 = recordList.stream().filter(a -> a.getLicensePlate().equals(LicensePlate) && a.getAmount().compareTo(Amount)==0
                     && a.getOutboundTime().equals(pro.getOutboundTime())).collect(Collectors.toList());
            if (list4.size()==0){
                EtcCardUsageRecord ppo = new EtcCardUsageRecord();
                ppo.setAmount(Amount);
                ppo.setLicensePlate(LicensePlate);
                ppo.setEtcCardNumber(EtcCardNumber);
                ppo.setInboundTime(InboundTime);
                ppo.setOutboundTime(OutboundTime);
                ppo.setRoadSection(RoadSection);
                list1.add(ppo);
            }
        }
        if (list1.size()==0){
            throw new CustomException("此次校验无异常", HttpStatus.SUCCESS);
        }
        else {
            ExcelUtil<EtcCardUsageRecord> util1 = new ExcelUtil<EtcCardUsageRecord>(EtcCardUsageRecord.class);
            util1.exportExcel( response,list1, "校验结果");
        }
    }

    /**
     * 导入列表
     */
    @ApiOperation("导入列表")
    @PreAuthorize(hasPermi = "car:etcCardUsageRecord:import")
    @Log(title = "ETC管理-ETC卡使用记录", businessType = BusinessType.IMPORT)
    @PostMapping("/J05ImportData")
    public AjaxResult importData(@Validated MultipartFile file) throws Exception {
        ExcelUtil<EtcCardUsageRecordImport> util = new ExcelUtil<EtcCardUsageRecordImport>(EtcCardUsageRecordImport.class);
        List<EtcCardUsageRecordImport> recordList = new ArrayList<>();
        try {
            recordList = util.importExcel(file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return AjaxResult.error("无文件导入");
        }

        Map<String, String> message = etcCardUsageRecordService.importUser(recordList);
        String code = message.get("code");
        if ("0".equals(code)) {
            return AjaxResult.success(message.get("msg"));
        } else {
            return AjaxResult.error(message.get("msg"));
        }
    }
    /**
     * 获取ETC管理-ETC卡使用记录详细信息
     */
    @PreAuthorize(hasPermi = "system:record:query")
    @GetMapping(value = "/getInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(etcCardUsageRecordService.selectEtcCardUsageRecordById(id));
    }

    /**
     * 新增ETC管理-ETC卡使用记录
     */
    @PreAuthorize(hasPermi = "system:record:add")
    @Log(title = "ETC管理-ETC卡使用记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody EtcCardUsageRecord etcCardUsageRecord)
    {
        return toAjax(etcCardUsageRecordService.insertEtcCardUsageRecord(etcCardUsageRecord));
    }

    /**
     * 修改ETC管理-ETC卡使用记录
     */
    @PreAuthorize(hasPermi = "system:record:edit")
    @Log(title = "ETC管理-ETC卡使用记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody EtcCardUsageRecord etcCardUsageRecord)
    {
        return toAjax(etcCardUsageRecordService.updateEtcCardUsageRecord(etcCardUsageRecord));
    }

    /**
     * 删除ETC管理-ETC卡使用记录
     */
    @PreAuthorize(hasPermi = "system:record:remove")
    @Log(title = "ETC管理-ETC卡使用记录", businessType = BusinessType.DELETE)
	@GetMapping("/delete/{id}")
    public AjaxResult remove(@PathVariable String id)
    {

        return toAjax(etcCardUsageRecordService.deleteEtcCardUsageRecordById(id));
    }
}
