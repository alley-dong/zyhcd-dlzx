package com.dlzx.auth.client;

import com.dlzx.auth.form.DDriver;
import com.dlzx.common.security.handler.FeignSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "pfserver-person",configuration = FeignSupportConfig.class) //注册中心服务的名字
//@FeignClient(value = "pfserver-person",url = "http://localhost:9500/",configuration = FeignSupportConfig.class) //本地调试
public interface PersonClient {

    /**
     * App 登录
     * */
    @RequestMapping(value = "/person/driver/loginDriverList",method = RequestMethod.POST)
    public DDriver driverLogin(@RequestParam("contact")String contact);
}