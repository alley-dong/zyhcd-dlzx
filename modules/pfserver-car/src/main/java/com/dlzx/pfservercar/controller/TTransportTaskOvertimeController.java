package com.dlzx.pfservercar.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.pfservercar.domain.model.TTransportTaskOvertimeModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskOvertimeVm;
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
import com.dlzx.pfservercar.domain.TTransportTaskOvertime;
import com.dlzx.pfservercar.service.ITTransportTaskOvertimeService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 *  任务管理-运输任务加班Controller
 * 
 * @author dcl
 * @date 2020-12-18
 */
@RestController
@RequestMapping("/transportTaskOvertime")
public class TTransportTaskOvertimeController extends BaseController
{
    @Autowired
    private ITTransportTaskOvertimeService tTransportTaskOvertimeService;

    /**
     * 查询 任务管理-运输任务加班列表
     */
    @PreAuthorize(hasPermi = "pfservercar:transportTaskOvertime:list")
    @PostMapping("/P11TransportTaskOvertimeList")
    public TableDataInfo list(@RequestBody TTransportTaskOvertimeModel tTransportTaskOvertimeModel)
    {
        startPage(tTransportTaskOvertimeModel.getPageNum(),tTransportTaskOvertimeModel.getPageSize());
        List<TTransportTaskOvertimeVm> list = tTransportTaskOvertimeService.selectTTransportTaskOvertimeList(tTransportTaskOvertimeModel);
        return getDataTable(list);
    }



    /**
     * 获取 任务管理-运输任务加班详细信息
     */
    @PreAuthorize(hasPermi = "pfservercar:transportTaskOvertime:info")
    @PostMapping(value = "/info")
    public AjaxResult getInfo(@RequestBody TTransportTaskOvertimeModel tTransportTaskOvertimeModel)
    {
        return AjaxResult.success(tTransportTaskOvertimeService.selectTTransportTaskOvertimeById(tTransportTaskOvertimeModel.getId()));
    }

}
