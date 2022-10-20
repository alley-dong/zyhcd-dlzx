package com.dlzx.system.client;

import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.security.handler.FeignSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "pfserver-car",configuration = FeignSupportConfig.class) //注册中心服务的名字
//@FeignClient(value = "pfserver-car", url = "http://localhost:9300/",configuration = FeignSupportConfig.class) //注册中心服务的名字
public interface CarClient {

    /**
     * 驾驶员公共接口-消息节点
     */
    @RequestMapping(path = "/driver-api/msg/addDriverMsg",method = RequestMethod.POST)
    public AjaxResult addDriverMsg(@RequestParam(value = "type", required = false) Long type, @RequestParam(value = "relationId", required = false) String relationId,
                                   @RequestParam(value = "driverId", required = false) String driverId, @RequestParam(value = "title", required = false) String title,
                                   @RequestParam(value = "content", required = false) String content);

}