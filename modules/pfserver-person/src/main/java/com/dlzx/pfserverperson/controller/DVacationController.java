package com.dlzx.pfserverperson.controller;

import java.util.List;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.DriverTokenService;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.system.api.model.LoginDriver;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfserverperson.domain.DVacation;
import com.dlzx.pfserverperson.service.IDVacationService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 驾驶员-考勤Controller
 * 
 * @author dcl
 * @date 2020-11-30
 */
@RestController
@RequestMapping("/vacation")
public class DVacationController extends BaseController
{
    @Autowired
    private IDVacationService dVacationService;

    @Autowired
    private TokenService tokenService;

    @Resource
    private DriverTokenService driverTokenService;

    /**
     * 查询驾驶员-考勤列表
     */
    @PreAuthorize(hasPermi = "pfserverperson:vacation:list")
    @PostMapping("/A49ClockInList")
    public TableDataInfo list(@RequestBody DVacation dVacation)
    {
        startPage(dVacation.getPageNum(),dVacation.getPageSize());
        List<DVacation> list = dVacationService.selectDVacationList(dVacation);
        return getDataTable(list);
    }

    /**
     * 获取驾驶员-考勤详细信息
     */
    @PreAuthorize(hasPermi = "pfserverperson:vacation:info")
    @GetMapping(value = "/A50ClockInInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(dVacationService.selectDVacationById(id));
    }

    /**
     * 考勤审核
     */
    @PreAuthorize(hasPermi = "pfserverperson:vacation:check")
    @Log(title = "驾驶员-考勤审核", businessType = BusinessType.UPDATE)
    @PostMapping("/A51ClockInCheck")
    public AjaxResult edit(@RequestBody DVacation dVacation)
    {
        if (StringUtils.isNull(dVacation.getStatus())){
            return AjaxResult.error("请选择审核结果");
        }
        if (dVacation.getStatus()==3 && StringUtils.isEmpty(dVacation.getReviewRemarks())){
            return AjaxResult.error("请输入不通过原因");
        }
        // 后台操作人
        LoginUser userInfo = tokenService.getLoginUser();
        dVacation.setUserid(userInfo.getUserid()+"");
        return dVacationService.checkDVacation(dVacation);
    }

    /**
     * 请假统计
     */
    @PreAuthorize(hasPermi = "pfserverperson:vacation:checkinlist")
    @GetMapping("/A52ClockInStatistics")
    public TableDataInfo A52ClockInStatistics(DVacation dVacation)
    {
        startPage();
        // 获取请假统计
        List<DVacation> list = dVacationService.queryLeaveStatistics(dVacation);
        // 取出请假人员id
        List<String> driverIds = list.stream()
                .map(DVacation::getDriverId)
                .collect(Collectors.toList());

        if (driverIds.size()>0){
            // 根据请假人员id获取 每个人员的请假数据
            List<DVacation> dVacationList = dVacationService.queryInDriverIds(driverIds,dVacation.getvStartTime(),dVacation.getvEndTime());

            // 把对应人员的请假数据赋值到其list中
            for (DVacation vacation : list) {
                List<DVacation> collect = dVacationList.stream()
                        .filter(
                                d -> d.getDriverId().equals(vacation.getDriverId()))
                        .collect(Collectors.toList());
                vacation.setdVacationList(collect);
            }
        }

        return getDataTable(list);
    }

    /**
     * 删除驾驶员-考勤
     */
    @PreAuthorize(hasPermi = "pfserverperson:vacation:remove")
    @Log(title = "驾驶员-考勤", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(dVacationService.deleteDVacationByIds(ids));
    }

    /**
     * 导出驾驶员-考勤列表
     */
    @PreAuthorize(hasPermi = "pfserverperson:vacation:export")
    @Log(title = "驾驶员-考勤", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DVacation dVacation) throws IOException
    {
        List<DVacation> list = dVacationService.selectDVacationList(dVacation);
        ExcelUtil<DVacation> util = new ExcelUtil<DVacation>(DVacation.class);
        util.exportExcel(response, list, "vacation");
    }



    /**
     * App 考勤-驾驶员请假列表
    * */
    @RequestMapping(path = "/E01VacationList",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    @Log(title = "App 考勤-驾驶员请假列表")
    public TableDataInfo vacationList(@RequestBody DVacation dVacation)
    {
        startPage(dVacation.getPageNum(),dVacation.getPageSize());
        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        dVacation.setDriverId(loginDriver.getUserid()+"");
        return getDataTable(dVacationService.queryAppVacationList(dVacation));
    }

    /**
     * App 考勤-请假详情（区分四种状态）
     */
    @RequestMapping("/E02VacationDetail")
    @ResponseBody
    @Log(title = "App 考勤-请假详情（区分四种状态）")
    public AjaxResult e02VacationDetail(@RequestParam("id")String id)
    {
        return AjaxResult.success(dVacationService.selectDVacationById(id));
    }

    /**
     * App 考勤-新增请假记录
     */
    @RequestMapping(path = "/E04VacationAdd",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @Log(title = "App 考勤-新增请假记录")
    public AjaxResult add(@RequestBody DVacation dVacation)
    {
        if (StringUtils.isNull(dVacation.getType())){
            return AjaxResult.error("请输入请假类型");
        }
        if (StringUtils.isNull(dVacation.getvStartTime())){
            return AjaxResult.error("请输入开始时间");
        }
        if (StringUtils.isNull(dVacation.getvEndTime())){
            return AjaxResult.error("请输入结束时间");
        }
        if (StringUtils.isNull(dVacation.getDuration())){
            return AjaxResult.error("请输入请假时长");
        }
        if (StringUtils.isNull(dVacation.getRemarks())){
            return AjaxResult.error("请输入请假事由");
        }
        dVacation.setId(UUID.randomUUID()+"");
        // 待审核
        dVacation.setStatus(1L);

        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        dVacation.setCreateBy(loginDriver.getUsername());
        dVacation.setDriverName(loginDriver.getUsername());
        dVacation.setDriverId(loginDriver.getUserid()+"");
        return AjaxResult.success(dVacationService.insertDVacation(dVacation));
    }

    /**
     * App 考勤-请假申请编辑接口
     */
    @RequestMapping(path = "/E04VacationEditSave",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @Log(title = "App 考勤-请假申请编辑接口")
    public AjaxResult update(@RequestBody DVacation dVacation)
    {
        if (StringUtils.isNull(dVacation.getType())){
            return AjaxResult.error("请输入请假类型");
        }
        if (StringUtils.isNull(dVacation.getvStartTime())){
            return AjaxResult.error("请输入开始时间");
        }
        if (StringUtils.isNull(dVacation.getvEndTime())){
            return AjaxResult.error("请输入结束时间");
        }
        if (StringUtils.isNull(dVacation.getDuration())){
            return AjaxResult.error("请输入请假时长");
        }
        if (StringUtils.isNull(dVacation.getRemarks())){
            return AjaxResult.error("请输入请假事由");
        }
        // 待审核
        dVacation.setStatus(1L);

        LoginDriver loginDriver = driverTokenService.getLoginDriver();
        dVacation.setUpdateBy(loginDriver.getUsername());
        return AjaxResult.success(dVacationService.updateDVacation(dVacation));
    }

    /**
     * App 考勤-撤销请假申请接口
     */
    @RequestMapping(path = "/E03UndoVacation",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @Log(title = "App 考勤-撤销请假申请接口")
    public AjaxResult undo(@RequestBody DVacation dVacation)
    {
        // 撤回
        dVacation.setStatus(4L);
        return AjaxResult.success(dVacationService.undoDVacation(dVacation));
    }


}
