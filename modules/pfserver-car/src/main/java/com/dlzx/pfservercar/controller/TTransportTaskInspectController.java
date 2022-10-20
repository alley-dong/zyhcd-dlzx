package com.dlzx.pfservercar.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.pfservercar.domain.model.TTransportTaskInspectModel;
import com.dlzx.pfservercar.domain.model.TTransportTaskModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskInspectVm;
import com.dlzx.pfservercar.domain.vm.TTransportTaskVm;
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
import com.dlzx.pfservercar.domain.TTransportTaskInspect;
import com.dlzx.pfservercar.service.ITTransportTaskInspectService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 任务管理-运输任务点检Controller
 * 
 * @author dcl
 * @date 2020-12-18
 */
@RestController
@RequestMapping("/transportTaskInspect")
public class TTransportTaskInspectController extends BaseController
{
    @Autowired
    private ITTransportTaskInspectService tTransportTaskInspectService;


    /**
     * 查询任务管理-运输任务点检列表
     */
    @PreAuthorize(hasPermi = "pfservercar:transportTaskInspect:list")
    @PostMapping("/P09TransportTaskInspectList")
    public TableDataInfo list(@RequestBody TTransportTaskModel tTransportTaskModel)
    {
        startPage(tTransportTaskModel.getPageNum(),tTransportTaskModel.getPageSize());
        List<TTransportTaskVm> list = tTransportTaskInspectService.queryTTransportTaskInspectList(tTransportTaskModel);
        return getDataTable(list);
    }

    /**
     * 获取任务管理-运输任务点检详细信息
     */
    @PreAuthorize(hasPermi = "pfservercar:transportTaskInspect:info")
    @PostMapping(value = "/P10TransportTaskInspectInfo")
    public AjaxResult getInfo(@RequestBody TTransportTaskModel tTransportTaskModel)
    {
        return AjaxResult.success(tTransportTaskInspectService.queryTTransportTaskInspectByTaskId(tTransportTaskModel.getId()));
    }

}
