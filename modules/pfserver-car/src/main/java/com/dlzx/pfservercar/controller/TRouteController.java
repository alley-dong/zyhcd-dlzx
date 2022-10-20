package com.dlzx.pfservercar.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.core.utils.StringUtils;
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
import com.dlzx.pfservercar.domain.TRoute;
import com.dlzx.pfservercar.service.ITRouteService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 任务管理-线路Controller
 * 
 * @author dlzx
 * @date 2020-12-01
 */
@RestController
@RequestMapping("/tRoute")
public class TRouteController extends BaseController
{
    @Autowired
    private ITRouteService tRouteService;

    /**
     * 查询任务管理-线路列表
     */
    @PreAuthorize(hasPermi = "pfservercar:route:list")
    @PostMapping("/P01RouteList")
    public TableDataInfo list(@RequestBody TRoute tRoute)
    {
        startPage(tRoute.getPageNum(),tRoute.getPageSize());
        List<TRoute> list = tRouteService.selectTRouteList(tRoute);
        return getDataTable(list);
    }

    /**
     * 获取任务管理-线路详细信息
     */
    @PreAuthorize(hasPermi = "pfservercar:route:info")
    @PostMapping(value = "/P01RouteInfo")
    public AjaxResult getInfo(@RequestBody TRoute tRoute)
    {
        return AjaxResult.success(tRouteService.selectTRouteById(tRoute.getId()));
    }

    /**
     * 修改任务管理-线路
     */
    @PreAuthorize(hasPermi = "pfservercar:route:edit")
    @Log(title = "任务管理-线路", businessType = BusinessType.UPDATE)
    @PostMapping("/P02RouteEditSave")
    public AjaxResult edit(@RequestBody TRoute tRoute)
    {
        if (StringUtils.isNull(tRoute.getMinInspect())){
            return AjaxResult.error("最少检点次数不能为空");
        }
        return toAjax(tRouteService.updateTRoute(tRoute));
    }

    /**
     * 删除任务管理-线路
     */
    @PreAuthorize(hasPermi = "pfservercar:route:delete")
    @Log(title = "任务管理-线路", businessType = BusinessType.DELETE)
	@GetMapping("/P01RouteDelete/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tRouteService.deleteTRouteByIds(ids));
    }



    /**
     * 新增任务管理-线路
     */
    @PreAuthorize(hasPermi = "pfservercar:route:add")
    @Log(title = "任务管理-线路", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody TRoute tRoute)
    {
        return toAjax(tRouteService.insertTRoute(tRoute));
    }

}
