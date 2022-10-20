package com.dlzx.pfserverparts.client;
import com.dlzx.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("vod") //注册中心服务的名字
public interface VodClient {

    //定义调用方法的路径
    //@PathVariable一定要注意名称，否则会出错
    @DeleteMapping("/user/B01GetInfoL/{userId}")
    public R getUser(@PathVariable("userId") String userId);

}