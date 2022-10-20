package com.dlzx.pfserverparts;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import com.dlzx.common.security.annotation.EnableCustomConfig;
import com.dlzx.common.security.annotation.EnableRyFeignClients;
import com.dlzx.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 车模块
 * 
 * @author dlzx
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringCloudApplication
public class DlzxPFServerPartsApplication extends WebMvcConfigurerAdapter
{
    public static void main(String[] args)
    {
        SpringApplication.run(DlzxPFServerPartsApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  业务模块启动成功   ლ(´ڡ`ლ)");
    }
    @Override
    public void configureAsyncSupport(final AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(2000000);
        configurer.registerCallableInterceptors(timeoutInterceptor());
    }
    @Bean
    public TimeoutCallableProcessingInterceptor timeoutInterceptor() {
        return new TimeoutCallableProcessingInterceptor();
    }
}
