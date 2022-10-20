package com.dlzx.auth.controller;

import com.dlzx.auth.form.DDriver;
import com.dlzx.auth.form.LoginBody;
import com.dlzx.auth.service.SysLoginService;
import com.dlzx.common.core.domain.R;
import com.dlzx.common.core.web.domain.ESBParams;
import com.dlzx.common.core.web.domain.ESBResult;
import com.dlzx.common.security.service.DriverTokenService;
import com.dlzx.system.api.model.LoginDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverTokenController {

    @Autowired
    private SysLoginService sysLoginService;

    @PostMapping("/driverLogin")
    public ESBResult login(@RequestBody ESBParams<DDriver> esbParams)
    {
        try {
            // 用户登录
            return sysLoginService.driverLogin(esbParams.getMsgBody());
        } catch (Exception e) {
            return ESBResult.error(e.getMessage());
        }
    }
}
