package com.dlzx.pfserverperson.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfserverperson.domain.DDriver;
import com.dlzx.pfserverperson.domain.DDriverGroupUser;
import com.dlzx.pfserverperson.domain.DDriverGroupPerson;
import com.dlzx.pfserverperson.service.IDDriverGroupUserService;
import com.dlzx.pfserverperson.service.IDDriverService;
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
import org.springframework.web.bind.annotation.RestController;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfserverperson.domain.DDriverGroup;
import com.dlzx.pfserverperson.service.IDDriverGroupService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 驾驶员-驾驶员群组Controller
 *
 * @author dlzx
 * @date 2020-11-23
 */
@RestController
@RequestMapping("/person/dDriverGroup")
public class DDriverGroupController extends BaseController {
    @Autowired
    private IDDriverGroupService dDriverGroupService;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private IDDriverGroupUserService driverGroupUserService;

    /**
     * 查询驾驶员-驾驶员群组列表
     */
    @PreAuthorize(hasPermi = "person:dDriverGroup:list")
    @PostMapping("/A39List")
    public TableDataInfo list(@RequestBody DDriverGroup dDriverGroup) {
        startPage(dDriverGroup.getPageNum(), dDriverGroup.getPageSize());
        List<DDriverGroup> list = dDriverGroupService.selectDDriverGroupList(dDriverGroup);
        return getDataTable(list);
    }

    @PreAuthorize(hasPermi = "person:dDriverGroup:listT")
    @PostMapping("/A39ListT")
    public TableDataInfo listT(@RequestBody DDriverGroup dDriverGroup) {
        List<DDriverGroup> list = dDriverGroupService.selectDDriverGroupList(dDriverGroup);
        return getDataTable(list);
    }

    /**
     * 导出驾驶员-驾驶员群组列表
     */
    @PreAuthorize(hasPermi = "system:group:export")
    @Log(title = "驾驶员-驾驶员群组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DDriverGroup dDriverGroup) throws IOException {
        List<DDriverGroup> list = dDriverGroupService.selectDDriverGroupList(dDriverGroup);
        ExcelUtil<DDriverGroup> util = new ExcelUtil<DDriverGroup>(DDriverGroup.class);
        util.exportExcel(response, list, "group");
    }

    /**
     * 获取驾驶员-驾驶员群组详细信息
     */
    @PreAuthorize(hasPermi = "person:dDriverGroup:edit")
    @GetMapping(value = "/A39GetInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        DDriverGroup dDriverGroup = dDriverGroupService.selectDDriverGroupById(id);
        List<DDriverGroupUser> list = driverGroupUserService.selectDDriverGroupUserListById(id);
        DDriverGroupPerson dDriverGroupPerson = new DDriverGroupPerson();
        dDriverGroupPerson.setName(dDriverGroup.getName());
        dDriverGroupPerson.setDriver(list);
        return AjaxResult.success(dDriverGroupPerson);
    }

    /**
     * 新增驾驶员-驾驶员群组
     */
    @PreAuthorize(hasPermi = "person:dDriverGroup:add")
    @Log(title = "驾驶员-驾驶员群组", businessType = BusinessType.INSERT)
    @PostMapping("/A40Add")
    public AjaxResult add(@RequestBody @Validated DDriverGroupPerson dDriverGroupPerson) {
        String name = dDriverGroupPerson.getName();
        List<DDriverGroup> list1 = dDriverGroupService.selectDDriverGroupListQunZu(name);
        if (list1.size() > 0) {
            return AjaxResult.error("该群组名已存在");
        } else {
            LoginUser userInfo = tokenService.getLoginUser();
            String groupId = UUID.randomUUID().toString();
            DDriverGroup dDriverGroup = new DDriverGroup();
            dDriverGroup.setId(groupId);
            dDriverGroup.setName(dDriverGroupPerson.getName());
            dDriverGroup.setCreateBy(userInfo.getUsername());
            dDriverGroup.setDeleteFlag(Long.valueOf(1));
            dDriverGroupService.insertDDriverGroup(dDriverGroup);
            List<DDriverGroupUser> list = dDriverGroupPerson.getDriver();
            for (int i = 0; i < list.size(); i++) {
                String id = UUID.randomUUID().toString();
                list.get(i).setId(id);
                list.get(i).setGroupId(groupId);
            }
            int msg = 1;
            if (list.size() > 0) {
                driverGroupUserService.insertDDriverGroupUserList(list);
            }
            return toAjax(msg);
        }
    }

    /**
     * 修改驾驶员-驾驶员群组
     */
    @PreAuthorize(hasPermi = "person:dDriverGroup:edit")
    @Log(title = "驾驶员-驾驶员群组", businessType = BusinessType.UPDATE)
    @PostMapping("/A41Edit")
    public AjaxResult edit(@RequestBody @Validated DDriverGroupPerson dDriverGroupPerson) {
        String name = dDriverGroupPerson.getName();
        List<DDriverGroup> list1 = dDriverGroupService.selectDDriverGroupListQunZu(name);
        DDriverGroup dDriverGroup1 = dDriverGroupService.selectDDriverGroupById(dDriverGroupPerson.getId());
        if (!dDriverGroupPerson.getName().equals(dDriverGroup1.getName()) && list1.size() > 0) {
            return AjaxResult.error("该群组名已存在");
        } else {
            LoginUser userInfo = tokenService.getLoginUser();
            DDriverGroup dDriverGroup = new DDriverGroup();
            dDriverGroup.setId(dDriverGroupPerson.getId());
            dDriverGroup.setName(dDriverGroupPerson.getName());
            dDriverGroup.setUpdateBy(userInfo.getUsername());
            dDriverGroupService.updateDDriverGroup(dDriverGroup);
            List<DDriverGroupUser> list = dDriverGroupPerson.getDriver();
            if (list.size() > 0) {
                String groupId = dDriverGroupPerson.getId();
                driverGroupUserService.deleteDDriverGroupUserByGroupId(groupId);
                for (int i = 0; i < list.size(); i++) {
                    String id = UUID.randomUUID().toString();
                    list.get(i).setId(id);
                    list.get(i).setGroupId(groupId);
                }
            }
            int msg = 1;
            if (list.size() > 0) {
                driverGroupUserService.insertDDriverGroupUserList(list);
            }
            return toAjax(msg);
        }

    }

    /**
     * 删除驾驶员-驾驶员群组
     */
    @PreAuthorize(hasPermi = "person:dDriverGroup:delete")
    @Log(title = "驾驶员-驾驶员群组", businessType = BusinessType.DELETE)
    @GetMapping("/A39Delete/{id}")
    public AjaxResult remove(@PathVariable String id) {
        LoginUser userInfo = tokenService.getLoginUser();
        DDriverGroup dDriverGroup = dDriverGroupService.selectDDriverGroupById(id);
        dDriverGroup.setDeleteFlag(Long.valueOf(2));
        dDriverGroup.setUpdateBy(userInfo.getUsername());
        return toAjax(dDriverGroupService.updateDDriverGroup(dDriverGroup));
    }
}
