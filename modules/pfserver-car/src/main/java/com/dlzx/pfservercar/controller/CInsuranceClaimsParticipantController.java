package com.dlzx.pfservercar.controller;

import java.util.List;
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
import com.dlzx.pfservercar.domain.CInsuranceClaimsParticipant;
import com.dlzx.pfservercar.service.ICInsuranceClaimsParticipantService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 车辆管理-理赔参与方Controller
 * 
 * @author dongchanglong
 * @date 2021-03-12
 */
@RestController
@RequestMapping("/participant")
public class CInsuranceClaimsParticipantController extends BaseController
{
    @Autowired
    private ICInsuranceClaimsParticipantService cInsuranceClaimsParticipantService;

    /**
     * 查询车辆管理-理赔参与方列表
     */
    @PreAuthorize(hasPermi = "system:participant:list")
    @GetMapping("/list")
    public TableDataInfo list(CInsuranceClaimsParticipant cInsuranceClaimsParticipant)
    {
        startPage();
        List<CInsuranceClaimsParticipant> list = cInsuranceClaimsParticipantService.selectCInsuranceClaimsParticipantList(cInsuranceClaimsParticipant);
        return getDataTable(list);
    }

    /**
     * 获取车辆管理-理赔参与方详细信息
     */
    @PreAuthorize(hasPermi = "system:participant:query")
    @GetMapping(value = "E18ParticipantInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cInsuranceClaimsParticipantService.selectCInsuranceClaimsParticipantById(id));
    }

    /**
     * 新增车辆管理-理赔参与方
     */
    @PreAuthorize(hasPermi = "system:participant:add")
    @Log(title = "车辆管理-理赔参与方", businessType = BusinessType.INSERT)
    @PostMapping("/E18ParticipantAdd")
    public AjaxResult add(@RequestBody @Validated CInsuranceClaimsParticipant cInsuranceClaimsParticipant)
    {
        return toAjax(cInsuranceClaimsParticipantService.insertCInsuranceClaimsParticipant(cInsuranceClaimsParticipant));
    }

    /**
     * 修改车辆管理-理赔参与方
     */
    @PreAuthorize(hasPermi = "system:participant:edit")
    @Log(title = "车辆管理-理赔参与方", businessType = BusinessType.UPDATE)
    @PostMapping("/E18ParticipantEditSave")
    public AjaxResult edit(@RequestBody @Validated CInsuranceClaimsParticipant cInsuranceClaimsParticipant)
    {
        return toAjax(cInsuranceClaimsParticipantService.updateCInsuranceClaimsParticipant(cInsuranceClaimsParticipant));
    }

    /**
     * 删除车辆管理-理赔参与方
     */
    @PreAuthorize(hasPermi = "system:participant:remove")
    @Log(title = "车辆管理-理赔参与方", businessType = BusinessType.DELETE)
	@GetMapping("E18DeleteParticipant/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cInsuranceClaimsParticipantService.deleteCInsuranceClaimsParticipantByIds(ids));
    }
}
