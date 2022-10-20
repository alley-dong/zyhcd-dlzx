package com.dlzx.pfserverparts.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverparts.domain.DSalaryDetailedRecords;
import com.dlzx.pfserverparts.domain.GGoodsCheck;
import com.dlzx.pfserverparts.domain.ReviewSubmit;
import com.dlzx.pfserverparts.domain.SubmmitVo;
import com.dlzx.pfserverparts.mapper.DSalaryDetailedRecordsMapper;
import com.dlzx.pfserverparts.mapper.DSalaryRecordsMapper;
import com.dlzx.pfserverparts.service.IDSalaryDetailedRecordsService;
import com.dlzx.system.api.model.LoginUser;
import org.checkerframework.checker.index.qual.LengthOf;
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
import com.dlzx.pfserverparts.domain.DSalaryRecords;
import com.dlzx.pfserverparts.service.IDSalaryRecordsService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 驾驶员-薪酬结算Controller
 * 
 * @author yangluhe
 * @date 2020-12-21
 */
@RestController
@RequestMapping("/parts/salaryRecords")
public class DSalaryRecordsController extends BaseController
{
    @Autowired
    private IDSalaryRecordsService dSalaryRecordsService;
    @Autowired
    private IDSalaryDetailedRecordsService detailedRecordsService;
    @Autowired
    private DSalaryDetailedRecordsMapper dSalaryDetailedRecordsMapper;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询驾驶员-薪酬结算列表
     */
    @PreAuthorize(hasPermi = "pfserverparts:salaryRecords:list")
    @PostMapping("/C07List")
    public TableDataInfo C07List(@RequestBody DSalaryRecords dSalaryRecords)
    {
        startPage(dSalaryRecords.getPageNum(),dSalaryRecords.getPageSize());
        List<DSalaryRecords> list = dSalaryRecordsService.selectDSalaryRecordsList(dSalaryRecords);
        return getDataTable(list);
    }

    /**
     * 工资核算出长途列表和短途列表
     * @return
     */
    @Log(title = "工资核算")
    @PreAuthorize(hasPermi = "pfserverparts:salaryRecords:wageaccounting")
    @PostMapping("/C09WageAc")
    public AjaxResult C09WageAc(){
        DSalaryRecords dSalaryRecords = new DSalaryRecords();
        dSalaryRecords.setVacationDate(new Date());
        //获取当前系统月份
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        if (dSalaryRecordsService.selectDSalaryRecordsList(dSalaryRecords).size()!=0){
            return AjaxResult.error(month + 1 + "月份工资已核算");
        }
        else {
            return detailedRecordsService.selectDSalaryRecordsTypeList();
        }
    }

    /**
     * 获取数据明细单条信息
     * @param id
     * @return
     */
    @Log(title = "获取数据明细单条信息")
//    @PreAuthorize(hasPermi = "pfserverparts:salaryRecords:info")
    @GetMapping(value = "/C09Info/{id}")
    public AjaxResult C09Info(@PathVariable("id")String id){
        if (null == detailedRecordsService.selectDSalaryDetailedRecordsById(id)){
            return AjaxResult.error("次记录不存在");
        }
        return AjaxResult.success(detailedRecordsService.selectDSalaryDetailedRecordsById(id));
    }

    /**
     * 查看明细单
     * @param
     * @return
     */
    @Log(title = "查看明细单")
    @PreAuthorize(hasPermi = "pfserverparts:salaryRecords:detail")
    @GetMapping(value = "/C06Detail/{id}")
    public AjaxResult C06Detail(@PathVariable("id")String id){
        return AjaxResult.success(detailedRecordsService.selectDSalaryDetail(id));
    }

    /**
     * 编辑保存
     * @param dSalaryDetailedRecords
     * @return
     */
    @Log(title = "编辑明细")
//    @PreAuthorize(hasPermi = "pfserverparts:salaryRecords:editsave")
    @PostMapping("/C09EditSave")
    public AjaxResult C09EditSave(@RequestBody DSalaryDetailedRecords dSalaryDetailedRecords){
        return toAjax(detailedRecordsService.editSaveDetailRecord(dSalaryDetailedRecords));
    }

    /**
     * 工资核算保存
     * @param id
     * @return
     */
    @Log(title = "工资核算保存")
    @PreAuthorize(hasPermi = "pfserverparts:salaryRecords:checksave")
    @GetMapping("/C09CheckSave/{id}")
    public AjaxResult C09CheckSave(@PathVariable("id")String id){
        DSalaryRecords dSalaryRecords = dSalaryRecordsService.selectDSalaryRecordsById(id);
        dSalaryRecords.setStatus(4L);
        return toAjax(dSalaryRecordsService.updateDSalaryRecords(dSalaryRecords));
    }

    /**
     * 下发工资按钮
     * @param id
     * @return
     */
    @Log(title = "下发工资按钮")
    @PreAuthorize(hasPermi = "pfserverparts:salaryRecords:issue")
    @GetMapping(value = "/C09Issue/{id}")
    public AjaxResult C09Issue(@PathVariable("id")String id){
        DSalaryRecords dSalaryRecords = dSalaryRecordsService.selectDSalaryRecordsById(id);
        if (dSalaryRecords.getStatus() == 2){
        dSalaryRecords.setSalaryStatus(2L);
        return toAjax(dSalaryRecordsService.updateDSalaryRecords(dSalaryRecords));
        }
        else {
            return AjaxResult.error("未通过审核不能下发工资单");
        }
    }

    /**
     * 撤销按钮
     * @param id
     * @return
     */
    @Log(title = "撤销按钮")
    @PreAuthorize(hasPermi = "pfserverparts:salaryRecords:revoke")
    @GetMapping(value = "/C09Revoke/{id}")
    public AjaxResult C09Revoke(@PathVariable("id")String id){
        return toAjax(detailedRecordsService.revoke(id));
    }

    /**
     * 工资明细导出
     * @param response
     * @param id
     * @throws IOException
     */
    @Log(title = "工资明细导出")
    @PreAuthorize(hasPermi = "pfserverparts:salaryRecords:export")
    @PostMapping("/C10Export")
    public void export(HttpServletResponse response, String id) throws IOException
    {
        List<DSalaryDetailedRecords> list = dSalaryDetailedRecordsMapper.selectDSalaryDetailForm(id);
        for (DSalaryDetailedRecords dSalaryDetailedRecords : list) {
            if (dSalaryDetailedRecords.getType() == 1L){
                dSalaryDetailedRecords.setTypeName("长途");
            }
            else {
                dSalaryDetailedRecords.setTypeName("短途");
            }
        }
        ExcelUtil<DSalaryDetailedRecords> util = new ExcelUtil<DSalaryDetailedRecords>(DSalaryDetailedRecords.class);
        util.exportExcel(response, list, "DSalaryDetailedRecords");
    }

    /**
     * 工资明细审核
     * @param reviewSubmit
     * @return
     */
    @Log(title = "工资明细审核")
    @PreAuthorize(hasPermi = "pfserverparts:salaryRecords:review")
    @PostMapping("/C08Review")
    public AjaxResult C08Review(@Validated @RequestBody ReviewSubmit reviewSubmit){
        if (reviewSubmit.getStatus() == 2 && StringUtils.isEmpty(reviewSubmit.getReviewRemarks())) {
            return AjaxResult.error("请输入不通过原因");
        }
        LoginUser userInfo = tokenService.getLoginUser();
        reviewSubmit.setUserId(userInfo.getUserid().toString());
        return detailedRecordsService.review(reviewSubmit);
    }


    /**
     * 提交审核
     * @param submmitVo
     * @return
     */
    @Log(title = "提交审核")
//    @PreAuthorize(hasPermi = "pfserverparts:salaryRecords:submit")
    @PostMapping("/C09Submit")
    public AjaxResult C09Submit(@Validated @RequestBody SubmmitVo submmitVo){
        return toAjax(detailedRecordsService.submmit(submmitVo));
    }

    /**
     * APP 薪酬管理
     */
    @PostMapping("/G01List")
    public AjaxResult G01List(@RequestBody DSalaryDetailedRecords dSalaryDetailedRecords){
        return AjaxResult.success(detailedRecordsService.selectAppList(dSalaryDetailedRecords));
    }

    /**
     * App 个人工资
     */
    @GetMapping("/G02Info/{id}")
    public AjaxResult G02Info(@PathVariable("id")String id){
        if(detailedRecordsService.selectDSalaryDetailedRecordsById(id) == null){
            return AjaxResult.error("暂无此信息");
        }
        return AjaxResult.success(detailedRecordsService.selectDSalaryDetailedRecordsById(id));
    }
}
