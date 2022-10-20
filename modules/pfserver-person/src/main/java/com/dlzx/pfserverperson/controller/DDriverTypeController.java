package com.dlzx.pfserverperson.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.web.domain.BaseEntity;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverperson.domain.DDriver;
import com.dlzx.pfserverperson.service.IDDriverService;
import com.dlzx.system.api.model.LoginUser;
import com.mysql.cj.xdevapi.Table;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.dlzx.pfserverperson.domain.DDriverType;
import com.dlzx.pfserverperson.service.IDDriverTypeService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 驾驶员-驾驶员类型Controller
 * 
 * @author jijiawen
 * @date 2020-11-18
 */
@RestController
@RequestMapping("/person/driverType")
public class DDriverTypeController extends BaseController
{
    @Autowired
    private IDDriverTypeService dDriverTypeService;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private IDDriverService dDriverService;
    /**
     * 查询驾驶员类型-根据名称模糊查询
     */
    @PreAuthorize(hasPermi = "person:driverType:list")
    @PostMapping("/A23List")
    public TableDataInfo A23List(@RequestBody DDriverType dDriverType) {
        startPage(dDriverType.getPageNum(), dDriverType.getPageSize());
        List<DDriverType> list = dDriverTypeService.selectDDriverTypeList(dDriverType);
        for (DDriverType driverType : list) {
            String id = driverType.getId();
            List<DDriver> dDrivers = dDriverService.selectDDriverByDriverTypeId(id);
            if (dDrivers.size() > 0) {
                driverType.setDeleteType(String.valueOf(1));
            } else {
                driverType.setDeleteType(String.valueOf(2));
            }
        }
        return getDataTable(list);
    }
    /**
     * 导出驾驶员-驾驶员类型列表
     */
    @PreAuthorize(hasPermi = "person:driverType:export")
    @Log(title = "驾驶员-驾驶员类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DDriverType dDriverType) throws IOException
    {
        List<DDriverType> list = dDriverTypeService.selectDDriverTypeList(dDriverType);
        ExcelUtil<DDriverType> util = new ExcelUtil<DDriverType>(DDriverType.class);
        util.exportExcel(response, list, "type");
    }

    /**
     * 获取驾驶员-驾驶员类型详细信息
     */
    @PreAuthorize(hasPermi = "person:driverType:edit")
    @GetMapping(value = "/A23GetInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(dDriverTypeService.selectDDriverTypeById(id));
    }

    /**
     * 新增驾驶员-驾驶员类型
     */
    @PreAuthorize(hasPermi = "person:driverType:add")
    @Log(title = "驾驶员-驾驶员类型", businessType = BusinessType.INSERT)
    @PostMapping("/A23Add")
    public AjaxResult A23Add(@RequestBody DDriverType dDriverType)
    {
        if (dDriverType.getName()==null||dDriverType.getName().equals("")){
            return AjaxResult.error("驾驶员类型不能为空");
        }
        if (dDriverType.getBasicSalary()==null){
            return AjaxResult.error("底薪金额不能为空");
        }
        List<DDriverType> dDriverTypeList = dDriverTypeService.selectDDriverTypeList(new DDriverType());
        for (DDriverType dDriverType1:dDriverTypeList) {
            if (dDriverType1.getName().equals(dDriverType.getName())){
                return AjaxResult.error("此类型已存在");
            }
        }
        LoginUser userInfo = tokenService.getLoginUser();
        dDriverType.setCreateUser(userInfo.getUsername());
        UUID uuid = UUID.randomUUID();
        dDriverType.setId(uuid+"");
        dDriverType.setDeleteFlag(Long.valueOf(1));
        return toAjax(dDriverTypeService.insertDDriverType(dDriverType));
    }

    /**
     * 修改驾驶员-驾驶员类型
     */
    @PreAuthorize(hasPermi = "person:driverType:edit")
    @Log(title = "驾驶员-驾驶员类型", businessType = BusinessType.UPDATE)
    @PostMapping("/A23EditSave")
    public AjaxResult A23EditSave(@RequestBody DDriverType dDriverType)
    {
        if (dDriverType.getName()==null||dDriverType.getName().equals("")){
            return AjaxResult.error("驾驶员类型不能为空");
        }
        if (dDriverType.getBasicSalary()==null) {
            return AjaxResult.error("底薪金额不能为空");
        }
        LoginUser userInfo = tokenService.getLoginUser();
        String username = userInfo.getUsername();
        dDriverType.setUpdateUser(username);
        return toAjax(dDriverTypeService.updateDDriverType(dDriverType));
    }

    /**
     * 删除驾驶员-驾驶员类型
     * 逻辑删除
     */
    @PreAuthorize(hasPermi = "person:driverType:remove")
    @Log(title = "驾驶员-驾驶员类型", businessType = BusinessType.DELETE)
	@GetMapping("/A23Delete/{id}")
    public AjaxResult A23Delete(@PathVariable("id")String id)
    {
        DDriverType dDriverType = dDriverTypeService.selectDDriverTypeById(id);
        dDriverType.setDeleteFlag((long) 2);
        return toAjax(dDriverTypeService.updateDDriverType(dDriverType));
    }

    /**
     * 获取驾驶员-驾驶员类型详细信息
     */
    @GetMapping(value = "/driverTypeGetInfo")
    public DDriverType driverTypeGetInfo(@RequestParam("id") String id)
    {
        return dDriverTypeService.selectDDriverTypeById(id);
    }
}
