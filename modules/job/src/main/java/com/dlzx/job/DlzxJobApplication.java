package com.dlzx.job;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import com.dlzx.common.security.annotation.EnableCustomConfig;
import com.dlzx.common.security.annotation.EnableRyFeignClients;
import com.dlzx.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 定时任务
 * 
 * @author dlzx
 */
@EnableCustomConfig
@EnableCustomSwagger2   
@EnableRyFeignClients
@SpringCloudApplication
@EnableScheduling
public class DlzxJobApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(DlzxJobApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  定时任务模块启动成功   ლ(´ڡ`ლ)ﾞ ");
    }
}
