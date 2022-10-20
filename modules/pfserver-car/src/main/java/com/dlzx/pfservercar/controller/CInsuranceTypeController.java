package com.dlzx.pfservercar.controller;

import java.util.List;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.domain.CInsuranceTypeItem;
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
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfservercar.domain.CInsuranceType;
import com.dlzx.pfservercar.service.ICInsuranceTypeService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 车辆管理-险种类别Controller
 * 
 * @author jijawen
 * @date 2020-11-23
 */
@RestController
@RequestMapping("/car/insuranceType")
public class  CInsuranceTypeController extends BaseController
{
    @Autowired
    private ICInsuranceTypeService cInsuranceTypeService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询车辆管理-险种类别列表
     */
    @PreAuthorize(hasPermi = "car:insuranceType:list")
    @PostMapping("/E12List")
    public TableDataInfo E12List(@RequestBody CInsuranceType cInsuranceType)
    {
        startPage(cInsuranceType.getPageNum(),cInsuranceType.getPageSize());
        List<CInsuranceType> list = cInsuranceTypeService.selectCInsuranceTypeList(cInsuranceType);
        return getDataTable(list);
    }

    /**
     * 导出车辆管理-险种类别列表
     */
    @PreAuthorize(hasPermi = "car:insuranceType:export")
    @Log(title = "车辆管理-险种类别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CInsuranceType cInsuranceType) throws IOException
    {
        List<CInsuranceType> list = cInsuranceTypeService.selectCInsuranceTypeList(cInsuranceType);
        ExcelUtil<CInsuranceType> util = new ExcelUtil<CInsuranceType>(CInsuranceType.class);
        util.exportExcel(response, list, "type");
    }

    /**
     * 获取车辆管理-险种类别详细信息
     */
    @PreAuthorize(hasPermi = "car:insuranceType:query")
    @GetMapping(value = "E12GetInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cInsuranceTypeService.selectCInsuranceTypeById(id));
    }

    /**
     * 新增车辆管理-险种类别
     */
    @PreAuthorize(hasPermi = "car:insuranceType:add")
    @Log(title = "车辆管理-险种类别", businessType = BusinessType.INSERT)
    @PostMapping("/E12Add")
    public AjaxResult E12Add(@RequestBody CInsuranceType cInsuranceType)
    {
        if (cInsuranceType.getName()==null||cInsuranceType.getName().equals("")){
            return AjaxResult.error("险种类别不能为空");
        }
        int i = cInsuranceTypeService.countAll(cInsuranceType.getName());
        if (i > 0) {
            return AjaxResult.error("此险种已存在");
        }
        LoginUser userInfo = tokenService.getLoginUser();
        cInsuranceType.setCreateBy(userInfo.getUsername());
        cInsuranceType.setDeleteFlag(Long.valueOf(1));
        UUID uuid = UUID.randomUUID();
        cInsuranceType.setId(uuid+"");
        return toAjax(cInsuranceTypeService.insertCInsuranceType(cInsuranceType));
    }

    /**
     * 修改车辆管理-险种类别
     */
    @PreAuthorize(hasPermi = "car:insuranceType:edit")
    @Log(title = "车辆管理-险种类别", businessType = BusinessType.UPDATE)
    @PostMapping("/E12EditSave")
    public AjaxResult E12EditSave(@RequestBody CInsuranceType cInsuranceType)
    {
        if (cInsuranceType.getName() == null || cInsuranceType.getName().equals("")) {
            return AjaxResult.error("险种类别不能为空");
        }
        List<CInsuranceType> cInsuranceTypeList = cInsuranceTypeService.selectCInsuranceTypeList(new CInsuranceType());
        Optional<CInsuranceType> insuranceType = cInsuranceTypeList.stream().filter(o -> o.getName().equals(cInsuranceType.getName())).findFirst();
        if (insuranceType.isPresent()){
            if (!insuranceType.get().getId().equals(cInsuranceType.getId())){
                return AjaxResult.error("该险种已存在");
            }
        }
        LoginUser userInfo = tokenService.getLoginUser();
        cInsuranceType.setUpdateBy(userInfo.getUsername());
        return toAjax(cInsuranceTypeService.updateCInsuranceType(cInsuranceType));
    }

    /**
     * 删除车辆管理-险种类别
     * 逻辑删除
     */
    @PreAuthorize(hasPermi = "car:insuranceType:remove")
    @Log(title = "车辆管理-险种类别", businessType = BusinessType.DELETE)
	@GetMapping("/E12Delete/{id}")
    public AjaxResult E12Delete(@PathVariable("id") String id)
    {
        CInsuranceType cInsuranceType = cInsuranceTypeService.selectCInsuranceTypeById(id);
        cInsuranceType.setDeleteFlag(Long.valueOf(2));
        return toAjax(cInsuranceTypeService.updateCInsuranceType(cInsuranceType));
    }
}
