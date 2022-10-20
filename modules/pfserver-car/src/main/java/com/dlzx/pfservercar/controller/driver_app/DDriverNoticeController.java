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


import com.dlzx.pfservercar.domain.DNoticeAp;
import com.dlzx.pfservercar.domain.EtcCard;

import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 驾驶员-试卷问题Controller
 * 
 * @author dlzx
 * @date 2020-11-19
 */
@RestController
@RequestMapping("/driver-api/driverNotice")
public class DDriverNoticeController extends BaseController
{
    @Autowired
    private PersonClient personClient;


    @RequestMapping("/D01List")
    @ResponseBody
    @Log(title = "驾驶员公告列表")
    public TableDataInfo D01List(@RequestBody DNoticeAp dNoticeAp) {
        return personClient.D01List(dNoticeAp);
    }

    @RequestMapping("/D02List")
    @ResponseBody
    @Log(title = "驾驶员公告列表")
    public AjaxResult D02List(@RequestParam("id") String id) {
        return personClient.D02List(id);
    }
}
