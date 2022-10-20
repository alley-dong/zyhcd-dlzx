package com.dlzx.pfservercar.controller.driver_app;

import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.pfservercar.client.PersonClient;
import com.dlzx.pfservercar.domain.*;

import com.dlzx.pfservercar.service.*;
import com.dlzx.system.api.model.LoginUser;
import feign.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 驾驶员-试卷问题Controller
 *
 * @author dlzx
 * @date 2020-11-19
 */
@RestController
@RequestMapping("/driver-api/driverProblem")
public class DDriverProblemController extends BaseController {

    @Autowired
    private PersonClient personClient;


    @RequestMapping("/B01List")
    @ResponseBody
    @Log(title = "驾驶员考试试题列表")
    public TableDataInfo B01List() {
        return personClient.B01List();
    }

    @RequestMapping("/B02List")
    @Headers({"acceptEncoding: gzip","contentType: application/json"})
    @ResponseBody
    @Log(title = "驾驶员考试试题列表")
    public TableDataInfo B02List(@RequestBody List<DExamPaperDaAn> list) {
        return personClient.B02List(list);
    }
}