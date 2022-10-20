package com.dlzx.pfservercar.controller;

import java.util.List;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.mongo.utils.StringUtil;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.domain.FOilCard;
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
import com.dlzx.pfservercar.domain.FFuelPlace;
import com.dlzx.pfservercar.service.IFFuelPlaceService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 燃油定点管理Controller
 * 
 * @author dlzx
 * @date 2020-12-01
 */
@RestController
@RequestMapping("/fuelPlace")
public class FFuelPlaceController extends BaseController
{
    @Autowired
    private IFFuelPlaceService fFuelPlaceService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询燃油定点管理列表
     */
    @PreAuthorize(hasPermi = "pfservercar:place:list")
    @PostMapping("/I03OilPlantsRouteList")
    public TableDataInfo list(@RequestBody FFuelPlace fFuelPlace)
    {
        startPage(fFuelPlace.getPageNum(),fFuelPlace.getPageSize());
        List<FFuelPlace> list = fFuelPlaceService.selectFFuelPlaceList(fFuelPlace);
        return getDataTable(list);
    }

    /**
     * 获取燃油定点管理详细信息
     */
    @PreAuthorize(hasPermi = "pfservercar:place:edit")
    @GetMapping(value = "/I03OilPlantsRouteInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(fFuelPlaceService.selectFFuelPlaceById(id));
    }

    /**
     * 新增燃油定点管理
     */
    @PreAuthorize(hasPermi = "pfservercar:place:add")
    @Log(title = "燃油定点管理", businessType = BusinessType.INSERT)
    @PostMapping("/I03OilPlantsRouteAdd")
    public AjaxResult add(@RequestBody FFuelPlace fFuelPlace)
    {
        if (StringUtils.isEmpty(fFuelPlace.getName())){
            return AjaxResult.error("定点名称不能为空");
        }
        if (StringUtils.isEmpty(fFuelPlace.getRouteId()) || StringUtils.isEmpty(fFuelPlace.getRouteName())){
            return AjaxResult.error("线路不能为空");
        }
        // 校验线路是否已存在
        FFuelPlace checkFuelPlace =  new FFuelPlace();
        checkFuelPlace.setRouteId(fFuelPlace.getRouteId());
        List<FFuelPlace> fFuelPlaces = fFuelPlaceService.selectFFuelPlaceList(checkFuelPlace);
        if (fFuelPlaces.size()>0){
            return AjaxResult.error("此线路已存在不能重复添加");
        }
        LoginUser userInfo = tokenService.getLoginUser();
        fFuelPlace.setCreateBy(userInfo.getUsername());
        fFuelPlace.setId(UUID.randomUUID()+"");
        return toAjax(fFuelPlaceService.insertFFuelPlace(fFuelPlace));
    }

    /**
     * 修改燃油定点管理
     */
    @PreAuthorize(hasPermi = "pfservercar:place:edit")
    @Log(title = "燃油定点管理", businessType = BusinessType.UPDATE)
    @PostMapping("/I03OilPlantsRouteEditSave")
    public AjaxResult edit(@RequestBody FFuelPlace fFuelPlace)
    {
        if (StringUtils.isEmpty(fFuelPlace.getName())){
            return AjaxResult.error("定点名称不能为空");
        }
        if (StringUtils.isEmpty(fFuelPlace.getRouteId()) || StringUtils.isEmpty(fFuelPlace.getRouteName())){
            return AjaxResult.error("线路不能为空");
        }
        // 校验线路是否已存在
        FFuelPlace checkFuelPlace =  new FFuelPlace();
        checkFuelPlace.setRouteId(fFuelPlace.getRouteId());
        List<FFuelPlace> fFuelPlaces = fFuelPlaceService.selectFFuelPlaceList(checkFuelPlace);
        List<FFuelPlace> collect = fFuelPlaces.stream().filter(l -> !l.getId().equals(fFuelPlace.getId())).collect(Collectors.toList());
        if (collect.size()>0){
            return AjaxResult.error("此线路已存在不能重复添加");
        }
        LoginUser userInfo = tokenService.getLoginUser();
        fFuelPlace.setUpdateBy(userInfo.getUsername());
        return toAjax(fFuelPlaceService.updateFFuelPlace(fFuelPlace));
    }


    /**
     * 删除燃油定点管理
     */
    @PreAuthorize(hasPermi = "pfservercar:place:delete")
    @Log(title = "燃油定点管理", businessType = BusinessType.DELETE)
	@GetMapping("/I03OilPlantsRouteDelete/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(fFuelPlaceService.deleteFFuelPlaceByIds(ids));
    }

    /**
     * 导出燃油定点管理列表
     */
    @PreAuthorize(hasPermi = "pfservercar:place:export")
    @Log(title = "燃油定点管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FFuelPlace fFuelPlace) throws IOException
    {
        List<FFuelPlace> list = fFuelPlaceService.selectFFuelPlaceList(fFuelPlace);
        ExcelUtil<FFuelPlace> util = new ExcelUtil<FFuelPlace>(FFuelPlace.class);
        util.exportExcel(response, list, "place");
    }
}
