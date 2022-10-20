package com.dlzx.pfservercar.controller;

import java.math.BigDecimal;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.common.log.enums.BusinessType;
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
import com.dlzx.pfservercar.domain.RTransportTaskCost;
import com.dlzx.pfservercar.service.IRTransportTaskCostService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 统计报-任务成本核算Controller
 * 
 * @author ylh
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/cost")
public class RTransportTaskCostController extends BaseController
{
    @Autowired
    private IRTransportTaskCostService rTransportTaskCostService;

    /**
     * 查询统计报-任务成本核算列表
     */
    @PreAuthorize(hasPermi = "pfservercar:cost:list")
    @PostMapping("/N04List")
    public TableDataInfo list(RTransportTaskCost rTransportTaskCost)
    {
        startPage(rTransportTaskCost.getPageNum(),rTransportTaskCost.getPageSize());
        List<RTransportTaskCost> list = rTransportTaskCostService.selectRTransportTaskCostList(rTransportTaskCost);
        return getDataTable(list);
    }

    /**
     * 导出统计报-任务成本核算列表
     */
    @PreAuthorize(hasPermi = "pfservercar:cost:export")
    @Log(title = "统计报-任务成本核算", businessType = BusinessType.EXPORT)
    @PostMapping("/N04Export")
    public void export(HttpServletResponse response, RTransportTaskCost rTransportTaskCost) throws IOException
    {
        List<RTransportTaskCost> list = rTransportTaskCostService.selectRTransportTaskCostList(rTransportTaskCost);
        for (RTransportTaskCost transportTaskCost:list){
            if (transportTaskCost.getProfitStatus()==2){
                BigDecimal decimal = transportTaskCost.getProfitAndLoss().multiply(new BigDecimal(-1));
                transportTaskCost.setProfitAndLoss(decimal);
            }
        }
        ExcelUtil<RTransportTaskCost> util = new ExcelUtil<RTransportTaskCost>(RTransportTaskCost.class);
        util.exportExcel(response, list, "cost");
    }

    /**
     * 统计报-列表
     * @param rTransportTaskCost
     * @return
     */
    @PreAuthorize(hasPermi = "pfservercar:cost:listtaskid")
    @Log(title = "统计报-核算")
    @PostMapping("/N04Add")
    public AjaxResult N04List(@RequestBody RTransportTaskCost rTransportTaskCost){
        return toAjax(rTransportTaskCostService.selectCostList(rTransportTaskCost));
    }
}
