package com.dlzx.pfserverperson.controller;

import java.util.List;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.security.service.TokenService;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfserverperson.domain.DLeaveType;
import com.dlzx.pfserverperson.service.IDLeaveTypeService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 驾驶员-请假类型Controller
 * 
 * @author yangluhe
 * @date 2020-11-24
 */
@RestController
@RequestMapping("/person/leaveType")
public class DLeaveTypeController extends BaseController
{
    @Autowired
    private IDLeaveTypeService dLeaveTypeService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询驾驶员-请假类型列表
     */
    @PreAuthorize(hasPermi = "pfserverperson:leavetype:list")
    @PostMapping("/A48LeaveTypeList")
    public TableDataInfo A48LeaveTypeList(@RequestBody DLeaveType dLeaveType)
    {
        startPage(dLeaveType.getPageNum(),dLeaveType.getPageSize());
        List<DLeaveType> list = dLeaveTypeService.selectDLeaveTypeList(dLeaveType);
        return getDataTable(list);
    }

    /**
     * 导出驾驶员-请假类型列表
     */
    @PreAuthorize(hasPermi = "pfserverperson:leavetype:export")
    @Log(title = "驾驶员-请假类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DLeaveType dLeaveType) throws IOException
    {
        List<DLeaveType> list = dLeaveTypeService.selectDLeaveTypeList(dLeaveType);
        ExcelUtil<DLeaveType> util = new ExcelUtil<DLeaveType>(DLeaveType.class);
        util.exportExcel(response, list, "leave");
    }

    /**
     * 获取驾驶员-请假类型详细信息
     */
    @PreAuthorize(hasPermi = "pfserverperson:leavetype:edit")
    @GetMapping(value = "/A48LeaveTypeInfo/{id}")
    public AjaxResult A48LeaveTypeInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(dLeaveTypeService.selectDLeaveTypeById(id));
    }

    /**
     * 新增驾驶员-请假类型
     */
    @PreAuthorize(hasPermi = "pfserverperson:leavetype:add")
    @Log(title = "驾驶员-请假类型", businessType = BusinessType.INSERT)
    @PostMapping("/A48LeaveTypeAdd")
    public AjaxResult A48LeaveTypeAdd(@Validated @RequestBody DLeaveType dLeaveType)
    {
        //获取当前登录用户信息
        LoginUser userInfo = tokenService.getLoginUser();
        dLeaveType.setCreateBy(userInfo.getUsername());
        UUID uuid = UUID.randomUUID();
        dLeaveType.setId(uuid.toString());
        dLeaveType.setDeleteFlag(Long.valueOf(1));
        return toAjax(dLeaveTypeService.insertDLeaveType(dLeaveType));
    }

    /**
     * 修改驾驶员-请假类型
     */
    @PreAuthorize(hasPermi = "pfserverperson:leavetype:edit")
    @Log(title = "驾驶员-请假类型", businessType = BusinessType.UPDATE)
    @PostMapping("/A48LeaveTypeEditSave")
    public AjaxResult A48LeaveTypeEditSave(@Validated @RequestBody DLeaveType dLeaveType)
    {
        //获取当前登录用户信息
        LoginUser userInfo = tokenService.getLoginUser();
        dLeaveType.setUpdateBy(userInfo.getUsername());
        return toAjax(dLeaveTypeService.updateDLeaveType(dLeaveType));
    }

    /**
     * 删除驾驶员-请假类型
     */
    @PreAuthorize(hasPermi = "pfserverperson:leavetype:delete")
    @Log(title = "驾驶员-请假类型", businessType = BusinessType.DELETE)
	@GetMapping(value = "/A48LeaveTypeDelete/{id}")
    public AjaxResult A48LeaveTypeDelete(@PathVariable("id") String id)
    {
        DLeaveType dLeaveType = dLeaveTypeService.selectDLeaveTypeById(id);
        dLeaveType.setDeleteFlag(Long.valueOf(2));
        return toAjax(dLeaveTypeService.updateDLeaveType(dLeaveType));
    }


   /**
    * @describe App 请假类型列表
    * @author DongCL
    * @date 2021-01-27 14:50
    * @param
    * @return
    */
   @RequestMapping(path = "/E03VacationTypeList",method = RequestMethod.POST)
    public TableDataInfo E03VacationTypeList()
    {
        List<DLeaveType> list = dLeaveTypeService.E03VacationTypeList();
        return getDataTable(list);
    }
}
