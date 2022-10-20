package com.dlzx.pfservercar.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.pfservercar.domain.model.TTransportTaskPartsModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskPartsVm;
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
import com.dlzx.pfservercar.domain.TTransportTaskParts;
import com.dlzx.pfservercar.service.ITTransportTaskPartsService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 任务管理-运输部件Controller
 * 
 * @author dcl
 * @date 2020-12-18
 */
@RestController
@RequestMapping("/transportTaskParts")
public class TTransportTaskPartsController extends BaseController
{
    @Autowired
    private ITTransportTaskPartsService tTransportTaskPartsService;

    /**
     * 查询任务管理-运输部件列表
     */
    @PreAuthorize(hasPermi = "pfservercar:transportTaskParts:list")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody TTransportTaskPartsModel tTransportTaskPartsModel)
    {
        startPage(tTransportTaskPartsModel.getPageNum(),tTransportTaskPartsModel.getPageSize());
        List<TTransportTaskPartsVm> list = tTransportTaskPartsService.selectTTransportTaskPartsList(tTransportTaskPartsModel);
        return getDataTable(list);
    }

    /**
     * 获取任务管理-运输部件详细信息
     */
    @PreAuthorize(hasPermi = "pfservercar:transportTaskParts:info")
    @PostMapping(value = "/info")
    public AjaxResult getInfo(@RequestBody TTransportTaskPartsModel tTransportTaskPartsModel)
    {
        return AjaxResult.success(tTransportTaskPartsService.selectTTransportTaskPartsById(tTransportTaskPartsModel.getId()));
    }

}
