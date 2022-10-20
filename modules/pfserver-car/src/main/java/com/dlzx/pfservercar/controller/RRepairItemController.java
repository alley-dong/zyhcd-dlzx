package com.dlzx.pfservercar.controller;

import java.util.Comparator;
import java.util.List;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.domain.RRepairItemDate;
import com.dlzx.pfservercar.service.IRRepairItemDateService;
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
import com.dlzx.pfservercar.domain.RRepairItem;
import com.dlzx.pfservercar.service.IRRepairItemService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 维修保养-维修保养项Controller
 * 
 * @author yangluhe
 * @date 2020-12-01
 */
@RestController
@RequestMapping("/car/repairItem")
public class RRepairItemController extends BaseController
{
    @Autowired
    private IRRepairItemService rRepairItemService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private IRRepairItemDateService repairItemDateService;

    /**
     * 查询维修保养-维修保养项列表
     */
    @PreAuthorize(hasPermi = "pfservercar:repairitem:list")
    @PostMapping("/H14List")
    public TableDataInfo H14List(@RequestBody RRepairItem rRepairItem)
    {
        startPage(rRepairItem.getPageNum(),rRepairItem.getPageSize());
        List<RRepairItem> list = rRepairItemService.selectRRepairItemList(rRepairItem);
        return getDataTable(list);
    }

    /**
     * 导出维修保养-维修保养项列表
     */
    @PreAuthorize(hasPermi = "pfservercar:repairItem:export")
    @Log(title = "维修保养-维修保养项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RRepairItem rRepairItem) throws IOException
    {
        List<RRepairItem> list = rRepairItemService.selectRRepairItemList(rRepairItem);
        ExcelUtil<RRepairItem> util = new ExcelUtil<RRepairItem>(RRepairItem.class);
        util.exportExcel(response, list, "repairItem");
    }

    /**
     * 获取维修保养-维修保养项详细信息
     */
    @PreAuthorize(hasPermi = "pfservercar:repairitem:edit")
    @GetMapping(value = "/H14Info/{id}")
    public AjaxResult H14Info(@PathVariable("id") String id)
    {
        return AjaxResult.success(rRepairItemService.selectRRepairItemById(id));
    }

    /**
     * 新增维修保养-维修保养项
     */
    @PreAuthorize(hasPermi = "pfservercar:repairitem:add")
    @Log(title = "维修保养-维修保养项", businessType = BusinessType.INSERT)
    @PostMapping("/H14Add")
    public AjaxResult H14Add(@Validated @RequestBody RRepairItem rRepairItem)
    {
        rRepairItem.setId(UUID.randomUUID().toString());
        //获取登录用户信息
        LoginUser userInfo = tokenService.getLoginUser();
        rRepairItem.setCreateBy(userInfo.getUsername());
        rRepairItem.setDeleteFlag(Long.valueOf(1));
        return toAjax(rRepairItemService.insertRRepairItem(rRepairItem));
    }

    /**
     * 修改维修保养-维修保养项
     */
    @PreAuthorize(hasPermi = "pfservercar:repairitem:edit")
    @Log(title = "维修保养-维修保养项", businessType = BusinessType.UPDATE)
    @PostMapping("/H14EditSave")
    public AjaxResult H14EditSave(@Validated @RequestBody RRepairItem rRepairItem)
    {
        //获取登录用户信息
        LoginUser userInfo = tokenService.getLoginUser();
        RRepairItem rRepairItemOne = rRepairItemService.selectRRepairItemById(rRepairItem.getId());
        rRepairItemOne.setUpdateBy(userInfo.getUsername());
        rRepairItemOne.setName(rRepairItem.getName());
        rRepairItemOne.setType(rRepairItem.getType());
        return toAjax(rRepairItemService.updateRRepairItem(rRepairItemOne));
    }

    /**
     * 删除维修保养-维修保养项
     */
    @PreAuthorize(hasPermi = "pfservercar:repairitem:delete")
    @Log(title = "维修保养-维修保养项", businessType = BusinessType.DELETE)
	@GetMapping("/H14Delete/{id}")
    public AjaxResult H14Delete(@PathVariable("id") String id)
    {
        RRepairItem rRepairItem = rRepairItemService.selectRRepairItemById(id);
        rRepairItem.setDeleteFlag(Long.valueOf(2));
        return toAjax(rRepairItemService.updateRRepairItem(rRepairItem));
    }

    /**
     * 维修保养项统计
     * @param rRepairItem
     * @return
     */
    @Log(title = "维修保养项统计list")
    @PostMapping("/H18List")
    public TableDataInfo H18List(@RequestBody RRepairItem rRepairItem){
        startPage(rRepairItem.getPageNum(),rRepairItem.getPageSize());
        RRepairItemDate repairItemDate = new RRepairItemDate();
        if (rRepairItem!= null){
            if (rRepairItem.getStartTime() != null){
                repairItemDate.setStartTime(rRepairItem.getStartTime());
            }
            if (rRepairItem.getFinishTime() != null){
                repairItemDate.setFinishTime(rRepairItem.getFinishTime());
            }
        }
        rRepairItem.setType(1L);
        List<RRepairItemDate> rRepairItemDateList = repairItemDateService.selectRRepairItemDateStat(repairItemDate);
        List<RRepairItem> rRepairItemList = rRepairItemService.selectRRepairItemList(rRepairItem);
        for (RRepairItem repairItem:rRepairItemList){
            RRepairItemDate rRepairItemDate = rRepairItemDateList.stream().filter(o -> Objects.equals(repairItem.getName(),o.getItemName())).findFirst().orElse(null);
            if (rRepairItemDate !=null && rRepairItemDate.getStatistics() != null){
                repairItem.setStatistics(rRepairItemDate.getStatistics());
            }
            if (rRepairItemDate == null){
                repairItem.setStatistics(0L);
            }
        }
        List<RRepairItem> R = rRepairItemList.stream().sorted(Comparator.comparing(RRepairItem::getStatistics).reversed()).collect(Collectors.toList());
        return getDataTable(R);
    }

    /**
     * 统计导出
     * @param response
     * @param rRepairItem
     * @throws IOException
     */
    @Log(title = "维修保养项统计export")
    @PostMapping("/H18Export")
    public void H18Export(HttpServletResponse response, RRepairItem rRepairItem) throws IOException
    {
        RRepairItemDate repairItemDate = new RRepairItemDate();
        if (rRepairItem!= null){
            if (rRepairItem.getStartTime() != null){
                repairItemDate.setStartTime(rRepairItem.getStartTime());
            }
            if (rRepairItem.getFinishTime() != null){
                repairItemDate.setFinishTime(rRepairItem.getFinishTime());
            }
        }
        rRepairItem.setType(1L);
        List<RRepairItemDate> rRepairItemDateList = repairItemDateService.selectRRepairItemDateStat(repairItemDate);
        List<RRepairItem> rRepairItemList = rRepairItemService.selectRRepairItemList(rRepairItem);
        for (RRepairItem repairItem:rRepairItemList){
            RRepairItemDate rRepairItemDate = rRepairItemDateList.stream().filter(o -> Objects.equals(repairItem.getName(),o.getItemName())).findFirst().orElse(null);
            if (rRepairItemDate !=null && rRepairItemDate.getStatistics() != null){
                repairItem.setStatistics(rRepairItemDate.getStatistics());
            }
            if (rRepairItemDate == null){
                repairItem.setStatistics(0L);
            }
            if (repairItem.getType() ==1){
                repairItem.setTypeName("主车维保");
            }
            if (repairItem.getType() == 2){
                repairItem.setTypeName("挂车维保");
            }
        }
        List<RRepairItem> R = rRepairItemList.stream().sorted(Comparator.comparing(RRepairItem::getStatistics).reversed()).collect(Collectors.toList());
        ExcelUtil<RRepairItem> util = new ExcelUtil<RRepairItem>(RRepairItem.class);
        util.exportExcel(response, R, "repairItem");
    }
}
