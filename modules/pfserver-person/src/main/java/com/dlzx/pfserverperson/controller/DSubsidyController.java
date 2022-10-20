package com.dlzx.pfserverperson.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.DriverTokenService;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverperson.domain.*;
import com.dlzx.pfserverperson.domain.commonDomain.SubmmitVo;
import com.dlzx.pfserverperson.service.IDSubsidyApService;
import com.dlzx.pfserverperson.service.IDSubsidyDayService;
import com.dlzx.system.api.model.LoginDriver;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfserverperson.service.IDSubsidyService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 驾驶员-补助Controller
 *
 * @author dlzx
 * @date 2020-12-08
 */
@RestController
@RequestMapping("/person/subsidy")
public class DSubsidyController extends BaseController {
    @Autowired
    private IDSubsidyService dSubsidyService;
    @Autowired
    private IDSubsidyApService dSubsidyApService;
    @Autowired
    private IDSubsidyDayService dSubsidyDayService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private DriverTokenService driverTokenService;
    /**
     * 查询驾驶员-补助列表
     */
    @PreAuthorize(hasPermi = "person:subsidy:C01List")
    @PostMapping("/C01List")
    public TableDataInfo list(@RequestBody DSubsidy dSubsidy) {
        startPage(dSubsidy.getPageNum(), dSubsidy.getPageSize());
        List<DSubsidy> list = dSubsidyService.selectDSubsidyList(dSubsidy);
//        List<DSubsidy> list1=list.stream().filter(b -> b.getStatus() == 4).collect(Collectors.toList());
//        list.removeAll(list1);
        return getDataTable(list);
    }


    /**
     * 查询驾驶员-补助统计
     */
    @PreAuthorize(hasPermi = "person:subsidy:C05List")
    @PostMapping("/C05List")
    public TableDataInfo listT(@RequestBody DSubsidy dSubsidy) {
        startPage(dSubsidy.getPageNum(), dSubsidy.getPageSize());
        List<DSubsidy> list = dSubsidyService.selectDSubsidyListTong(dSubsidy);


        // update by 4.29
        for (DSubsidy subsidy : list) {
            List<DSubsidy> queryDriverSubsidyDates = dSubsidyService.queryDriverSubsidyDate(new DSubsidy(subsidy.getDriverId(),subsidy.getType()));
            long days = 0L;
            long nd = 1000 * 24 * 60 * 60;
            for (DSubsidy queryDriverSubsidyDate : queryDriverSubsidyDates) {
                // 获得两个时间的毫秒时间差异
                long diff = queryDriverSubsidyDate.getSubsidyEndDate().getTime() - queryDriverSubsidyDate.getSubsidyStartDate().getTime();
                days += diff/nd;
            }
            // 计算差多少天
            subsidy.setDays(days);
            subsidy.setdSubsidyList(queryDriverSubsidyDates);
        }

        return getDataTable(list);
    }

    /**
     * 导出驾驶员-补助列表
     */
    @PreAuthorize(hasPermi = "system:subsidy:export")
    @Log(title = "驾驶员-补助", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DSubsidy dSubsidy) throws IOException {
        List<DSubsidy> list = dSubsidyService.selectDSubsidyList(dSubsidy);
        ExcelUtil<DSubsidy> util = new ExcelUtil<DSubsidy>(DSubsidy.class);
        util.exportExcel(response, list, "subsidy");
    }

    /**
     * 获取驾驶员-补助详细信息
     */
    @PreAuthorize(hasPermi = "person:subsidy:getInfo")
    @GetMapping(value = "/C01GetInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        DSubsidy dSubsidy = dSubsidyService.selectDSubsidyById(id);
//        if (dSubsidy.getStatus() == 2 || dSubsidy.getStatus()== 3) {
//            SysExamineTask sysExamineTask = new SysExamineTask();
//            sysExamineTask.setRelationId(id);
//            Map<String, Object> map = new HashMap<>();
//            map.put("s", dSubsidy);
//            map.put("c", dSubsidyService.sysExamineTaskList(sysExamineTask));
//            return AjaxResult.success(map);
//        } else {
            return AjaxResult.success(dSubsidy);
//        }
    }

    @Log(title = "驾驶员补助审核")
    @PostMapping("/C02Check")
    @PreAuthorize(hasPermi = "person:subsidy:check")
    public AjaxResult check(@RequestBody DSubsidy dSubsidy){
        if (StringUtils.isEmpty(dSubsidy.getStatus().toString())){
            return AjaxResult.error("请选择审核结果");
        }
        if (dSubsidy.getStatus()==2 && StringUtils.isEmpty(dSubsidy.getReviewRemarks())){
            return AjaxResult.error("请输入不通过原因");
        }
        LoginUser userInfo = tokenService.getLoginUser();
        dSubsidy.setUserId(userInfo.getUserid().toString());
        String msg = dSubsidyService.checkDSubsidy(dSubsidy);
        if (StringUtils.isEmpty(msg)){
            return AjaxResult.error("操作失败");
        }
        return AjaxResult.success(msg);
    }

    /**
     * 新增驾驶员-补助
     */
    @PreAuthorize(hasPermi = "system:subsidy:add")
    @Log(title = "驾驶员-补助", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DSubsidy dSubsidy) {
        return toAjax(dSubsidyService.insertDSubsidy(dSubsidy));
    }

    /**
     * 修改驾驶员-补助
     */
    @PreAuthorize(hasPermi = "system:subsidy:edit")
    @Log(title = "驾驶员-补助", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DSubsidy dSubsidy) {
        return toAjax(dSubsidyService.updateDSubsidy(dSubsidy));
    }

    /**
     * 删除驾驶员-补助
     */
    @PreAuthorize(hasPermi = "system:subsidy:remove")
    @Log(title = "驾驶员-补助", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(dSubsidyService.deleteDSubsidyByIds(ids));
    }


    @RequestMapping(path = "/F01List",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    @Log(title = "驾驶员补助列表")
    public TableDataInfo F01List(@RequestBody DSubsidyAp dSubsidy) {

        startPage(dSubsidy.getPageNum(), dSubsidy.getPageSize());
//        dSubsidy.setDriverId("1");
//        LoginDriver userInfo = driverTokenService.getLoginDriver();
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        dSubsidy.setDriverId(loginDriver.getUserid());
        List<DSubsidyAp> list = dSubsidyApService.selectDSubsidyListTong(dSubsidy);
        return getDataTable(list);
    }

    @RequestMapping(path = "/F02Add",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    @Log(title = "驾驶员补助申请")
    public AjaxResult F02Add(@Validated @RequestBody DSubsidyAp dSubsidy) throws ParseException {
//        LoginDriver userInfo = driverTokenService.getLoginDriver();
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        dSubsidy.setDriverId(loginDriver.getUserid());
        dSubsidy.setCreateBy(loginDriver.getUsername());
//        dSubsidy.setDriverId("1");
        String Id = UUID.randomUUID().toString();
        dSubsidy.setId(Id);
        dSubsidy.setStatus(Long.valueOf(1));
        dSubsidy.setDeleteFlag(Long.valueOf(1));
//        dSubsidy.setCreateBy(userInfo.getUsername());

        // 根据dd 来计算，计算天数
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startParse = simpleDateFormat.parse(simpleDateFormat.format(dSubsidy.getSubsidyStartDate()));
        Date endParse = simpleDateFormat.parse(simpleDateFormat.format(dSubsidy.getSubsidyEndDate()));
        Long day = (endParse.getTime() - startParse.getTime()) / (24 * 60 * 60 * 1000);
        List<DSubsidyDayAp> list=new ArrayList<>();
        for (int i=0;i <=day;i++) {
            Calendar calendar = new GregorianCalendar();
            Date date =dSubsidy.getSubsidyStartDate();
            // update by 4.29 如果是最后一条 取结束时间
            if (i == day){
                calendar.setTime(dSubsidy.getSubsidyEndDate());
            }else {
                calendar.setTime(date);
                calendar.add(Calendar.DATE,i);
            }
            date=calendar.getTime();
            DSubsidyDayAp dSubsidyDay=new DSubsidyDayAp();
            String id = UUID.randomUUID().toString();
            dSubsidyDay.setSubsidyId(Id);
            dSubsidyDay.setSubsidyDate(date);
            dSubsidyDay.setId(id);
            list.add(dSubsidyDay);
        }
        SubmmitVo submitVo=new SubmmitVo();
        submitVo.setRelationId(Id);
        submitVo.setType(Long.valueOf(1));
//        dSubsidyService.submmit(submitVo);
//        dSubsidyDayService.insertDSubsidyDayList(list);
        return toAjax(dSubsidyApService.insertDSubsidyOrDays(submitVo,list,dSubsidy));
    }

    @RequestMapping(path = "/F07Edit",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    @Log(title = "驾驶员补助重新申请")
    public AjaxResult F07Edit(@Validated @RequestBody DSubsidyAp dSubsidy) {

//        dSubsidy.setDriverId("1");
//        dSubsidyDayService.deleteDSubsidyDayBySubId(dSubsidy.getId());
//        LoginDriver userInfo = driverTokenService.getLoginDriver();
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        dSubsidy.setStatus(1L);
        dSubsidy.setDriverId(loginDriver.getUserid());
        Long day = (dSubsidy.getSubsidyEndDate().getTime() - dSubsidy.getSubsidyStartDate().getTime()) / (24 * 60 * 60 * 1000);
        List<DSubsidyDayAp> list=new ArrayList<>();
        for (int i=0;i <=day;i++) {
            Calendar calendar = new GregorianCalendar();
            Date date =dSubsidy.getSubsidyStartDate();
            calendar.setTime(date);
            calendar.add(calendar.DATE,i);
            date=calendar.getTime();
            DSubsidyDayAp dSubsidyDay=new DSubsidyDayAp();
            String id = UUID.randomUUID().toString();
            dSubsidyDay.setSubsidyId(dSubsidy.getId());
            dSubsidyDay.setSubsidyDate(date);
            dSubsidyDay.setId(id);
            list.add(dSubsidyDay);
        }
        SubmmitVo submitVo=new SubmmitVo();
        submitVo.setRelationId(dSubsidy.getId());
        submitVo.setType(Long.valueOf(1));
//        dSubsidyService.submmit(submitVo);
//        dSubsidyDayService.insertDSubsidyDayList(list);
        return toAjax(dSubsidyApService.updateDSubsidyOrDays(dSubsidy.getId(),submitVo,list,dSubsidy));
    }

    @RequestMapping("/F07GetInfo")
    @ResponseBody
    @Log(title = "驾驶员补助详情")
    public AjaxResult F07GetInfo(@RequestParam("id") String id) {
//        SysExamineTask sysExamineTask=new SysExamineTask();
//        sysExamineTask.setRelationId(id);
//
//        Map<String,Object> map =new HashMap<>();
//        map.put("s",dSubsidyApService.selectDSubsidyListTongById(id));
//        map.put("c",dSubsidyApService.sysExamineTaskList(sysExamineTask));
        DSubsidy dSubsidy=dSubsidyApService.selectDSubsidyListTongById(id);
        return AjaxResult.success(dSubsidy);
    }

    @RequestMapping("/F04Change")
    @ResponseBody
    @Log(title = "驾驶员补助撤销")
    public AjaxResult F04Change(@RequestParam("id") String id) {

        return toAjax(dSubsidyApService.updateDSubsidyStatusById(id));
    }
}
