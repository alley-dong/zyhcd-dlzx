package com.dlzx.job.client;

import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.security.handler.FeignSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.List;

@Component
@FeignClient(value = "pfserver-person") //注册中心服务的名字
//@FeignClient(value = "pfserver-person",url = "http://localhost:9500/") //本地调试
public interface PersonClient {

    /**
     * @describe person服务调用 - 定时
     * @author DongCL
     * @date 2021-01-25 11:43
     * @return
     */
    @RequestMapping(path = "/person/vacationDay/DVacationAdd",method = RequestMethod.POST)
    TableDataInfo DVacationAdd();
}