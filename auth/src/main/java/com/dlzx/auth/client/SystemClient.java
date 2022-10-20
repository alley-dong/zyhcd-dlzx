package com.dlzx.auth.client;

import com.dlzx.auth.form.DDriver;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.security.handler.FeignSupportConfig;
import com.dlzx.system.api.domain.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "system",configuration = FeignSupportConfig.class) //注册中心服务的名字
//@FeignClient(value = "pfserver-person",url = "http://localhost:9500/",configuration = FeignSupportConfig.class) //本地调试
public interface SystemClient {

    /**
     * App 登录
     * */
    @RequestMapping(value = "/user/B01Edit",method = RequestMethod.POST)
    public AjaxResult updateUser(SysUser sysUser);
}