package com.dlzx.pfservercar.controller;

import java.util.List;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.domain.SafeAccident;
import com.dlzx.pfservercar.service.ISafeAccidentService;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfservercar.domain.CInsuranceClaims;
import com.dlzx.pfservercar.service.ICInsuranceClaimsService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 车辆管理-理赔Controller
 * 
 * @author dlzx
 * @date 2020-12-03
 */
@RestController
@RequestMapping("/car/insuranceClaims")
public class CInsuranceClaimsController extends BaseController
{
    @Autowired
    private ICInsuranceClaimsService cInsuranceClaimsService;
    @Autowired
    private ISafeAccidentService safeAccidentService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询车辆管理-理赔列表
     */
    @PreAuthorize(hasPermi = "car:insuranceClaims:list")
    @PostMapping("/E18List")
    public TableDataInfo list(@RequestBody CInsuranceClaims cInsuranceClaims)
    {
        startPage(cInsuranceClaims.getPageNum(),cInsuranceClaims.getPageSize());
        List<CInsuranceClaims> list = cInsuranceClaimsService.selectCInsuranceClaimsList(cInsuranceClaims);

        // 如果没有结束时间标红
        list = list.stream().map(l->{
            if (StringUtils.isNull(l.getPayEndTime())){
                l.setColorFlag(1L);
            }
            return l;
        }).collect(Collectors.toList());
        return getDataTable(list);
    }

    /**
     * 导出车辆管理-理赔列表
     */
    @PreAuthorize(hasPermi = "system:claims:export")
    @Log(title = "车辆管理-理赔", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CInsuranceClaims cInsuranceClaims) throws IOException
    {
        List<CInsuranceClaims> list = cInsuranceClaimsService.selectCInsuranceClaimsList(cInsuranceClaims);
        ExcelUtil<CInsuranceClaims> util = new ExcelUtil<CInsuranceClaims>(CInsuranceClaims.class);
        util.exportExcel(response, list, "claims");
    }

    /**
     * 获取车辆管理-理赔详细信息
     */
    @PreAuthorize(hasPermi = "car:insuranceClaims:edit")
    @GetMapping("/E18GetInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cInsuranceClaimsService.selectCInsuranceClaimsById(id));
    }

    /**
     * 新增车辆管理-理赔
     */
    @PreAuthorize(hasPermi = "car:insuranceClaims:add")
    @Log(title = "车辆管理-理赔", businessType = BusinessType.INSERT)
    @PostMapping("/E18Add")
    public AjaxResult add(@RequestBody @Validated CInsuranceClaims cInsuranceClaims)
    {
        SafeAccident safeAccident=new SafeAccident();
        safeAccident.setVehicleName(cInsuranceClaims.getVehicleName());
        safeAccident.setaTime(cInsuranceClaims.getaTime());
        List<SafeAccident> list1= safeAccidentService.selectSafeAccidentListVehNameAll(safeAccident);
        cInsuranceClaims.setAccidentId(list1.get(0).getId());
        cInsuranceClaims.setAccidentLocation(list1.get(0).getAccidentPlace());
        cInsuranceClaims.setDriverId(list1.get(0).getDriverId());
        cInsuranceClaims.setDriverName(list1.get(0).getDriverName());
        String Id = UUID.randomUUID().toString();
        cInsuranceClaims.setId(Id);
        LoginUser userInfo = tokenService.getLoginUser();
        cInsuranceClaims.setCreateBy(userInfo.getUsername());
        cInsuranceClaims.setDeleteFlag(Long.valueOf(1));
        return toAjax(cInsuranceClaimsService.insertCInsuranceClaims(cInsuranceClaims));
    }

    /**
     * 修改车辆管理-理赔
     */
    @PreAuthorize(hasPermi = "car:insuranceClaims:edit")
    @Log(title = "车辆管理-理赔", businessType = BusinessType.UPDATE)
    @PostMapping("/E18Edit")
    public AjaxResult edit(@RequestBody @Validated CInsuranceClaims cInsuranceClaims)
    {

        SafeAccident safeAccident=new SafeAccident();
        safeAccident.setVehicleName(cInsuranceClaims.getVehicleName());
        safeAccident.setaTime(cInsuranceClaims.getaTime());
        List<SafeAccident> list1= safeAccidentService.selectSafeAccidentListVehNameAll(safeAccident);
        cInsuranceClaims.setAccidentId(list1.get(0).getId());
        cInsuranceClaims.setAccidentLocation(list1.get(0).getAccidentPlace());
        cInsuranceClaims.setDriverId(list1.get(0).getDriverId());
        cInsuranceClaims.setDriverName(list1.get(0).getDriverName());
        LoginUser userInfo = tokenService.getLoginUser();
        cInsuranceClaims.setUpdateBy(userInfo.getUsername());
        return toAjax(cInsuranceClaimsService.updateCInsuranceClaims(cInsuranceClaims));
    }

    /**
     * 删除车辆管理-理赔
     */
    @PreAuthorize(hasPermi = "car:insuranceClaims:delete")
    @Log(title = "车辆管理-理赔", businessType = BusinessType.DELETE)
	@GetMapping("/E18Delete/{id}")
    public AjaxResult remove(@PathVariable String id)
    {
        LoginUser userInfo = tokenService.getLoginUser();
        CInsuranceClaims cInsuranceClaims=cInsuranceClaimsService.selectCInsuranceClaimsById(id);
        cInsuranceClaims.setDeleteFlag(Long.valueOf(2));
        cInsuranceClaims.setUpdateBy(userInfo.getUsername());
        return toAjax(cInsuranceClaimsService.updateCInsuranceClaims(cInsuranceClaims));
    }
}
