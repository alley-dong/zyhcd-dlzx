package com.dlzx.common.security.handler;

import com.dlzx.common.security.interceptor.FeignBasicAuthRequestInterceptor;
import feign.RequestInterceptor;
import feign.codec.Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign配置注册（全局）
 * company: 赛拓信息技术有限公司
 * @author DongCL
 * @date 2018-08-20 11:44
 **/
@Configuration
public class FeignSupportConfig {

    /**
     * feign请求拦截器
     *
     * @return
     */
    @Bean
    public RequestInterceptor requestInterceptor(){
        return new FeignBasicAuthRequestInterceptor();
    }
}
