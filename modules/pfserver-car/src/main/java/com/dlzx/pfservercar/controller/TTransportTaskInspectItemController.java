package com.dlzx.pfservercar.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.dlzx.pfservercar.domain.model.TTransportTaskInspectItemModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskInspectItemVm;
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
import com.dlzx.pfservercar.domain.TTransportTaskInspectItem;
import com.dlzx.pfservercar.service.ITTransportTaskInspectItemService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.utils.poi.ExcelUtil;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 任务管理-运输任务点检检查项Controller
 * 
 * @author dlzx
 * @date 2020-12-18
 */
@RestController
@RequestMapping("/transportTaskInspectItem")
public class TTransportTaskInspectItemController extends BaseController
{
    @Autowired
    private ITTransportTaskInspectItemService tTransportTaskInspectItemService;

    /**
     * 查询任务管理-运输任务点检检查项列表
     */
    @PreAuthorize(hasPermi = "pfservercar:transportTaskInspectItem:list")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody TTransportTaskInspectItemModel tTransportTaskInspectItemModel)
    {
        startPage(tTransportTaskInspectItemModel.getPageNum(),tTransportTaskInspectItemModel.getPageSize());
        List<TTransportTaskInspectItemVm> list = tTransportTaskInspectItemService.selectTTransportTaskInspectItemList(tTransportTaskInspectItemModel);
        return getDataTable(list);
    }

    /**
     * 获取任务管理-运输任务点检检查项详细信息
     */
    @PreAuthorize(hasPermi = "pfservercar:transportTaskInspectItem:info")
    @PostMapping(value = "/info")
    public AjaxResult getInfo(@RequestBody TTransportTaskInspectItemModel tTransportTaskInspectItemModel)
    {
        return AjaxResult.success(tTransportTaskInspectItemService.selectTTransportTaskInspectItemById(tTransportTaskInspectItemModel.getId()));
    }

}
