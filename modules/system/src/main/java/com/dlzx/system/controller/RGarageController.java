package com.dlzx.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.system.service.impl.PushMessage.PushMessage;
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
import com.dlzx.system.domain.RGarage;
import com.dlzx.system.service.IRGarageService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 维修保养-维修站Controller
 * 
 * @author dlzx
 * @date 2020-11-17
 */
@RestController
@RequestMapping("/garage")
public class RGarageController extends BaseController
{
    @Autowired
    private IRGarageService rGarageService;


    @Autowired
    private PushMessage pushMessage;

    @PostMapping("/test")
    public AjaxResult test(String title,String token) throws Exception {
        startPage();
        pushMessage.testAdvancedPush(title,token,"10",2);
        return AjaxResult.success();
    }


    /**
     * 查询维修保养-维修站列表
     */
    @PreAuthorize(hasPermi = "system:garage:list")
    @PostMapping("/listCar")
    public TableDataInfo list(RGarage rGarage)
    {
        startPage();
        List<RGarage> list = rGarageService.selectRGarageList(rGarage);
        return getDataTable(list);
    }


    @PostMapping("/B01ListCar")
    public AjaxResult list1()
    {
        RGarage rGarage =new RGarage();
        List<RGarage> list = rGarageService.selectRGarageList(rGarage);
        return AjaxResult.success(list);
    }
    /**
     * 导出维修保养-维修站列表
     */
    @PreAuthorize(hasPermi = "system:garage:export")
    @Log(title = "维修保养-维修站", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RGarage rGarage) throws IOException
    {
        List<RGarage> list = rGarageService.selectRGarageList(rGarage);
        ExcelUtil<RGarage> util = new ExcelUtil<RGarage>(RGarage.class);
        util.exportExcel(response, list, "garage");
    }

    /**
     * 获取维修保养-维修站详细信息
     */
    @PreAuthorize(hasPermi = "system:garage:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(rGarageService.selectRGarageById(id));
    }

    /**
     * 新增维修保养-维修站
     */
    @PreAuthorize(hasPermi = "system:garage:add")
    @Log(title = "维修保养-维修站", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RGarage rGarage)
    {
        return toAjax(rGarageService.insertRGarage(rGarage));
    }

    /**
     * 修改维修保养-维修站
     */
    @PreAuthorize(hasPermi = "system:garage:edit")
    @Log(title = "维修保养-维修站", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RGarage rGarage)
    {
        return toAjax(rGarageService.updateRGarage(rGarage));
    }

    /**
     * 删除维修保养-维修站
     */
    @PreAuthorize(hasPermi = "system:garage:remove")
    @Log(title = "维修保养-维修站", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(rGarageService.deleteRGarageByIds(ids));
    }

    @PreAuthorize(hasPermi = "system:garage:listL")
    @GetMapping("/listL")
    public List listL()
    {
        List<String> list1 = new ArrayList<>();
        List<RGarage> list = rGarageService.selectRGarageListL();
        for (RGarage R:list){
            list1.add(R.getName());
        }
        return list1;
    }

    @PreAuthorize(hasPermi = "system:garage:sid")
    @GetMapping("/sid/{name}")
    public String sid(@PathVariable("name") String name)
    {
        String Id =rGarageService.selectIdByName(name);
        return Id;
    }
}
