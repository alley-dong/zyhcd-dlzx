package com.dlzx.system.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.dlzx.common.core.constant.ServiceNameConstants;
import com.dlzx.common.core.domain.R;
import com.dlzx.system.api.factory.RemoteUserFallbackFactory;
import com.dlzx.system.api.model.LoginUser;

/**
 * 用户服务
 * 
 * @author dlzx
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService
{
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return 结果
     */
    @GetMapping(value = "/user/info/{username}")
    public R<LoginUser> getUserInfo(@PathVariable("username") String username);
}
