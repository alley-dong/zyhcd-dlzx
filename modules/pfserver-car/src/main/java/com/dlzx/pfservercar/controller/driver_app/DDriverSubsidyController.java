package com.dlzx.pfservercar.controller.driver_app;

import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.security.annotation.PreAuthorize;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.client.PersonClient;


import com.dlzx.pfservercar.client.SystemClient;
import com.dlzx.pfservercar.domain.Common.SubmitVo;
import com.dlzx.pfservercar.domain.DSubsidy;
import com.dlzx.pfservercar.domain.DSubsidyDay;
import com.dlzx.pfservercar.domain.SysExamineTask;

import com.dlzx.system.api.model.LoginUser;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 驾驶员-补助问题Controller
 *
 * @author dlzx
 * @date 2020-11-19
 */
@RestController
@RequestMapping("/driver-api/driverSubsidy")
public class DDriverSubsidyController extends BaseController {
    @Autowired
    private PersonClient personClient;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private SystemClient systemClient;

    @RequestMapping("/F01List")
    @ResponseBody
    @Log(title = "驾驶员补助列表")
    public TableDataInfo F01List(@RequestBody DSubsidy dSubsidy) {
        return personClient.F01List(dSubsidy);
    }

    @RequestMapping("/F02Add")
    @ResponseBody
    @Log(title = "驾驶员补助申请")
    public AjaxResult F02Add(@RequestBody DSubsidy dSubsidy) {
        return personClient.F02Add(dSubsidy);
    }

    @RequestMapping("/F07Edit")
    @ResponseBody
    @Log(title = "驾驶员补助重新申请")
    public AjaxResult F07Edit(@RequestBody DSubsidy dSubsidy) {
        return personClient.F07Edit(dSubsidy);
    }

    @RequestMapping("/F07GetInfo")
    @ResponseBody
    @Log(title = "驾驶员补助详情")
    public AjaxResult F07GetInfo(@RequestParam("id") String id) {
        return personClient.F07GetInfo(id);
    }

    @RequestMapping("/F04Change")
    @ResponseBody
    @Log(title = "驾驶员补助撤销")
    public AjaxResult F04Change(@RequestParam("id") String id) {
        systemClient.deleteExamineTaskByRelationId(id);
        return personClient.F04Change(id);
    }

}
