package com.dlzx.pfservercar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.client.SpringCloudApplication;
import com.dlzx.common.security.annotation.EnableCustomConfig;
import com.dlzx.common.security.annotation.EnableRyFeignClients;
import com.dlzx.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

/**
 * 车模块
 * 
 * @author dlzx
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringCloudApplication
public class DlzxPFServerCarApplication extends WebMvcConfigurerAdapter
{
    public static void main(String[] args)
    {
        SpringApplication.run(DlzxPFServerCarApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  车模块启动成功   ლ(´ڡ`ლ)ﾞ");
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofMegabytes(1024));
        factory.setMaxRequestSize(DataSize.ofGigabytes(1024));
        return factory.createMultipartConfig();
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
