package com.dlzx.pfservercar.controller.driver_app;

import com.dlzx.common.core.utils.DateUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.client.PersonClient;

import com.dlzx.pfservercar.domain.DTrainingAp;

import com.dlzx.pfservercar.domain.SafeTrafficTransgression;

import com.dlzx.pfservercar.domain.SysFile;
import com.dlzx.pfservercar.domain.TrafficUrl;

import com.dlzx.pfservercar.service.ISysFileService;
import com.dlzx.system.api.model.LoginDriver;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 驾驶员-交通违章Controller
 *
 * @author dlzx
 * @date 2020-11-19
 */
@RestController
@RequestMapping("/driver-api/driverTraffic")
public class DDriverTrafficController extends BaseController {
    @Autowired
    private PersonClient personClient;

    @RequestMapping("/K01List")
    @ResponseBody
    @Log(title = "驾驶员交通违章列表")
    public TableDataInfo K01List(@RequestBody SafeTrafficTransgression safeTrafficTransgression) {
        return personClient.K01List(safeTrafficTransgression);
    }

    @RequestMapping("/K02GetInfo")
    @ResponseBody
    @Log(title = "驾驶员违章详情")
    public AjaxResult K02GetInfo(@RequestParam("id") String id) {
        return personClient.K02GetInfo(id);
    }

    @RequestMapping("/K02Change")
    @ResponseBody
    @Log(title = "驾驶员违章状态修改")
    public AjaxResult K02Change(@RequestBody SafeTrafficTransgression safeTrafficTransgression) {
        return personClient.K02Change(safeTrafficTransgression);
    }

    @RequestMapping("/K02Shang")
    @ResponseBody
    @Log(title = "驾驶员违章上传")
    public AjaxResult K02Shang(@RequestBody TrafficUrl trafficUrl) {
        return personClient.K02Shang(trafficUrl);
    }
}
