package com.dlzx.pfservercar.controller.driver_app;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.security.service.DriverTokenService;
import com.dlzx.pfservercar.client.PartsClient;
import com.dlzx.pfservercar.domain.DSalaryDetailedRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ylh
 * @program:dlzx
 * @description：薪酬管理
 * @create:2020-12-23 19-22
 */
@RestController
@RequestMapping("/driver-api/salary")
public class DSalaryAppController {

    @Autowired
    private PartsClient partsClient;
    @Autowired
    private DriverTokenService tokenService;

    /**
     * 薪酬管理列表
     * @param dSalaryDetailedRecords
     * @return
     */
    @Log(title = "薪酬管理list")
    @ResponseBody
    @PostMapping("/G01ListApp")
    public AjaxResult G01List(@RequestBody DSalaryDetailedRecords dSalaryDetailedRecords){
        dSalaryDetailedRecords.setDriverId(tokenService.getLoginDriver().getUserid().toString());
        return partsClient.G01List(dSalaryDetailedRecords);
    }

    /**
     * 个人工资详情
     * @param id
     * @return
     */
    @Log(title = "个人工资")
    @ResponseBody
    @GetMapping("/G02Info/{id}")
    public AjaxResult G02Info(@PathVariable("id")String id){
        return partsClient.G02Info(id);
    }
}
