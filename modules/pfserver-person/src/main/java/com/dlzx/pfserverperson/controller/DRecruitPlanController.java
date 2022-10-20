package com.dlzx.pfserverperson.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverperson.client.SystemClient;
import com.dlzx.pfserverperson.domain.ReviewSubmit;
import com.dlzx.pfserverperson.domain.commonDomain.SubmmitVo;
import com.dlzx.system.api.model.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.index.qual.LengthOf;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfserverperson.domain.DRecruitPlan;
import com.dlzx.pfserverperson.service.IDRecruitPlanService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 驾驶员-招聘计划Controller
 * 
 * @author yangluhe
 * @date 2020-11-16
 */
@Api("招聘计划")
@RestController
@RequestMapping("/person/dRecruitPlan")
public class DRecruitPlanController extends BaseController
{
    @Autowired
    private IDRecruitPlanService dRecruitPlanService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SystemClient systemClient;

    /**
     * 查询驾驶员-招聘计划列表
     */
    @ApiOperation("列表")
    @PreAuthorize(hasPermi = "pfserverperson:dRecruitPlan:list")
    @PostMapping("/A04List")
    public TableDataInfo A04List(@RequestBody DRecruitPlan dRecruitPlan)
    {
        startPage(dRecruitPlan.getPageNum(),dRecruitPlan.getPageSize());
        List<DRecruitPlan> list = dRecruitPlanService.selectDRecruitPlanList(dRecruitPlan);
        return getDataTable(list);
    }

    /**
     * 导出驾驶员-招聘计划列表
     */
    @PreAuthorize(hasPermi = "pfserverperson:dRecruitPlan:export")
    @Log(title = "驾驶员-招聘计划", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DRecruitPlan dRecruitPlan) throws IOException
    {
        List<DRecruitPlan> list = dRecruitPlanService.selectDRecruitPlanList(dRecruitPlan);
        ExcelUtil<DRecruitPlan> util = new ExcelUtil<DRecruitPlan>(DRecruitPlan.class);
        util.exportExcel(response, list, "dRecruitPlan");
    }

    /**
     * 获取驾驶员-招聘计划详细信息
     */
    @ApiOperation("查看详情")
    @PreAuthorize(hasPermi = "pfserverperson:dRecruitPlan:info")
    @GetMapping(value ="/A08Info/{id}")
    public AjaxResult getA08Info(@PathVariable("id") String id)
    {
        return AjaxResult.success(dRecruitPlanService.selectDRecruitPlanById(id));
    }

    /**
     * 新增驾驶员-招聘计划
     */
    @ApiOperation("新增记录")
    @PreAuthorize(hasPermi = "pfserverperson:dRecruitPlan:add")
    @Log(title = "驾驶员-招聘计划", businessType = BusinessType.INSERT)
    @PostMapping("/A05Add")
    public AjaxResult A05Add(@Validated @RequestBody DRecruitPlan dRecruitPlan)
    {
        //获取当前登录用户信息
        LoginUser userInfo = tokenService.getLoginUser();
        dRecruitPlan.setCreateBy(userInfo.getUsername());
        UUID uuid = UUID.randomUUID();
        dRecruitPlan.setId(uuid.toString());
        dRecruitPlan.setDeleteFlag(Long.valueOf(1));
        dRecruitPlan.setStatus(Long.valueOf(1));
        return toAjax(dRecruitPlanService.insertDRecruitPlan(dRecruitPlan));
    }

    /**
     * 修改招聘计划
     */
    @PreAuthorize(hasPermi = "pfserverperson:dRecruitPlan:edit")
    @ApiOperation("修改招聘计划")
    @Log(title = "驾驶员修改",businessType = BusinessType.UPDATE)
    @PostMapping("/A06Edit/{id}")
    public AjaxResult A06Edit(@PathVariable("id")String id){
        return AjaxResult.success(dRecruitPlanService.selectDRecruitPlanById(id));
    }
    /**
     * 修改驾驶员-招聘计划
     */
    @ApiOperation("保存修改记录")
    @PreAuthorize(hasPermi = "pfserverperson:dRecruitPlan:editSave")
    @Log(title = "保存修改驾驶员-招聘计划", businessType = BusinessType.UPDATE)
    @PostMapping("/A06EditSave")
    public AjaxResult A06EditSave(@Validated @RequestBody DRecruitPlan dRecruitPlan)
    {
        //获取当前登录用户信息
        if (dRecruitPlan.getStatus()==1||dRecruitPlan.getStatus()==3){
            return AjaxResult.error("此条记录不能修改");
        }
        DRecruitPlan dRecruitPlanOne = dRecruitPlanService.selectDRecruitPlanById(dRecruitPlan.getId());
        dRecruitPlanOne.setRouteName(dRecruitPlan.getRouteName());
        dRecruitPlanOne.setAge(dRecruitPlan.getAge());
        dRecruitPlanOne.setNumber(dRecruitPlan.getNumber());
        dRecruitPlanOne.setEducation(dRecruitPlan.getEducation());
        dRecruitPlanOne.setDrivingRequirements(dRecruitPlan.getDrivingRequirements());
        dRecruitPlanOne.setEntryTime(dRecruitPlan.getEntryTime());
        dRecruitPlanOne.setDuty(dRecruitPlan.getDuty());
        dRecruitPlanOne.setReason(dRecruitPlan.getReason());
        dRecruitPlanOne.setWage(dRecruitPlan.getWage());
        LoginUser userInfo = tokenService.getLoginUser();
        dRecruitPlanOne.setUpdateBy(userInfo.getUsername());
        dRecruitPlanOne.setStatus(1L);
        return toAjax(dRecruitPlanService.updateDRecruitPlan(dRecruitPlanOne));
    }

    /**
     * 删除驾驶员-招聘计划
     */
    @PreAuthorize(hasPermi = "pfserverperson:dRecruitPlan:delete")
    @Log(title = "驾驶员-招聘计划", businessType = BusinessType.DELETE)
	@GetMapping(value ="/A04Delete/{id}")
    public AjaxResult A04Delete(@PathVariable("id") String id)
    {
        DRecruitPlan dRecruitPlan = dRecruitPlanService.selectDRecruitPlanById(id);
        //置为删除状态
        dRecruitPlan.setDeleteFlag(Long.valueOf(2));
        /*dRecruitPlanService.updateDRecruitPlan(dRecruitPlan);*/
        systemClient.deleteExamineTaskByRelationId(id);
        return toAjax(dRecruitPlanService.deleteDRecruitPlanById(id));
    }

    @Log(title = "驾驶员招聘计划审核")
    @PostMapping("/A07check")
    @PreAuthorize(hasPermi = "pfserverperson:dRecruitPlan:check")
    public AjaxResult A07check(@Validated @RequestBody ReviewSubmit reviewSubmit) {
        if (reviewSubmit.getStatus() == 2 && StringUtils.isEmpty(reviewSubmit.getReviewRemarks())) {
            return AjaxResult.error("请输入不通过原因");
        }
        LoginUser userInfo = tokenService.getLoginUser();
        reviewSubmit.setUserId(userInfo.getUserid().toString());
        return dRecruitPlanService.checkDRecruitPlan(reviewSubmit);
    }


    /**
     * 审批记录表
     */
    @Log(title = "审批记录表")
    @RequestMapping("/A08RecordList")
    @PreAuthorize(hasPermi = "pfserverperson:dRecruitPlan:recordlist")
    public AjaxResult A08RecordList(Long type){
        return AjaxResult.success(systemClient.queryExamineConfigProcessList(type));
    }


    /**
     * @param relationIdList
     * @return
     * @describe 根据ids去查询招聘计划列表
     * @author DongCL
     * @date 2021-03-12 13:48
     */
    @Log(title = "获取审批记录")
    @RequestMapping("/queryRecruitPlanByIds")
    public List<DRecruitPlan> queryRecruitPlanByIds(@RequestParam(value = "relationIdList", required = false) List<String> relationIdList) {
        List<DRecruitPlan> dRecruitPlans = new ArrayList<>();
        if (relationIdList != null && relationIdList.size() > 0) {
            dRecruitPlans = dRecruitPlanService.queryRecruitPlanByIds(relationIdList);
        }
        return dRecruitPlans;
    }
}
