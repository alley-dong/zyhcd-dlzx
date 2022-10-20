package com.dlzx.pfservercar.controller;

import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.client.SystemClient;
import com.dlzx.pfservercar.domain.CVehicle;
import com.dlzx.pfservercar.domain.FOilCardUsageRecord;
import com.dlzx.pfservercar.domain.SysExamineTask;
import com.dlzx.pfservercar.service.ICVehicleService;
import com.dlzx.pfservercar.service.IFOilCardUsageRecordService;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 类描述：
 * @className IndexStatisticsController
 * company 赛拓信息技术有限公司
 * @author DongCL
 * @date 2021-01-13 11:56
 */

@RestController
@RequestMapping("/index/statistics")
public class IndexStatisticsController extends BaseController {

    @Autowired
    private ICVehicleService icVehicleService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SystemClient systemClient;

    /**
     * @describe 首页统计数据
     * @author DongCL
     * @date 2021-01-13 11:55
     * @return data
     */
    @PostMapping("/indexStatistics")
    public AjaxResult statistics()
    {
        Map<String,Object> data = icVehicleService.queryIndexStatistics();
        return AjaxResult.success(data);
    }

    /**
     * @describe 首页待办
     * @author DongCL
     * @date 2021-01-13 11:55
     * @return data
     */
    @PostMapping("/indexExamineTaskList")
    @ResponseBody
    public TableDataInfo examineTask()
    {
        LoginUser loginUser = tokenService.getLoginUser();
        return systemClient.queryExamineTaskList(loginUser.getUserid());
    }
}
