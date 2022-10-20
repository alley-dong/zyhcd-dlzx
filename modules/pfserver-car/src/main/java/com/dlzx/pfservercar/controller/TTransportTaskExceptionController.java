package com.dlzx.pfservercar.controller;

import java.util.List;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.domain.model.TTransportTaskExceptionModel;
import com.dlzx.pfservercar.domain.vm.TTransportTaskExceptionVm;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.pfservercar.service.ITTransportTaskExceptionService;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;

/**
 * 任务管理-运输任务异常上报Controller
 * 
 * @author dcl
 * @date 2020-12-18
 */
@RestController
@RequestMapping("/transportException")
public class TTransportTaskExceptionController extends BaseController
{
    @Autowired
    private ITTransportTaskExceptionService tTransportTaskExceptionService;

    @Autowired
    private TokenService tokenService;
    /**
     * 查询任务管理-运输任务异常上报列表
     */
    @PreAuthorize(hasPermi = "pfservercar:transportException:list")
    @PostMapping("/P06TransportTaskExceptionList")
    public TableDataInfo list(@RequestBody TTransportTaskExceptionModel tTransportTaskExceptionModel)
    {
        startPage(tTransportTaskExceptionModel.getPageNum(),tTransportTaskExceptionModel.getPageSize());
        List<TTransportTaskExceptionVm> list = tTransportTaskExceptionService.selectTTransportTaskExceptionList(tTransportTaskExceptionModel);
        return getDataTable(list);
    }

    /**
     * 获取任务管理-运输任务异常上报详细信息
     */
    @PreAuthorize(hasPermi = "pfservercar:transportException:info")
    @PostMapping(value = "/P08TransportTaskExceptionInfo")
    @Log(title = "任务管理-任务异常信息")
    public AjaxResult getInfo(@RequestBody TTransportTaskExceptionModel tTransportTaskExceptionModel)
    {
        return AjaxResult.success(tTransportTaskExceptionService.selectTTransportTaskExceptionById(tTransportTaskExceptionModel.getId()));
    }

    /**
     * 任务管理-运输任务异常上报审核
     */
    @PreAuthorize(hasPermi = "pfservercar:transportException:check")
    @PostMapping(value = "/P07TransportTaskExceptionCheck")
    @Log(title = "任务管理-任务异常审核")
    public AjaxResult check(@RequestBody TTransportTaskExceptionModel tTransportTaskExceptionModel)
    {
        if (StringUtils.isNull(tTransportTaskExceptionModel.getStatus())){
            return AjaxResult.error("请选择审核结果");
        }
        if (tTransportTaskExceptionModel.getStatus()==2 && StringUtils.isEmpty(tTransportTaskExceptionModel.getReviewRemarks())){
            return AjaxResult.error("请输入不通过原因");
        }
        // 后台操作人
        LoginUser userInfo = tokenService.getLoginUser();
        tTransportTaskExceptionModel.setUserid(userInfo.getUserid()+"");

        return tTransportTaskExceptionService.transportTaskExceptionCheck(tTransportTaskExceptionModel);
    }


}
