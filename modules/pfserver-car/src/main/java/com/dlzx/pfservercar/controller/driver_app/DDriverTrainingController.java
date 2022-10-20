package com.dlzx.pfservercar.controller.driver_app;

import com.dlzx.common.core.domain.R;
import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.client.PersonClient;
import com.dlzx.pfservercar.domain.*;


import com.dlzx.system.api.model.LoginUser;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 驾驶员-试卷问题Controller
 *
 * @author dlzx
 * @date 2020-11-19
 */
@RestController
@RequestMapping("/driver-api/driverTraining")
public class DDriverTrainingController extends BaseController {
    @Autowired
    private PersonClient personClient;

    @RequestMapping("/C01List")
    @ResponseBody
    @Log(title = "驾驶员培训列表")
    public TableDataInfo C01List(@RequestBody DTrainingAp dTrainingAp) {
        return personClient.C01List(dTrainingAp);
    }

    @RequestMapping("/C02List")
    @ResponseBody
    @Log(title = "驾驶员培训详情列表")
    public AjaxResult C02List(@RequestParam("id") String id) {
        return personClient.C02List(id);
    }

    @RequestMapping("/C01Change")
    @ResponseBody
    @Log(title = "驾驶员培训更改状态")
    public AjaxResult C01Change(@RequestBody DTrainingAp dTrainingAp) {
        return personClient.C01Change(dTrainingAp);
    }

    @RequestMapping("/C02Edit")
    @ResponseBody
    @Log(title = "驾驶员培训编辑")
    public AjaxResult C02Edit(@RequestBody DTrainingAp dTrainingAp) {
        return personClient.C02Edit(dTrainingAp);
    }
}
