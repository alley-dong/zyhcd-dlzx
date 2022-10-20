package com.dlzx.system;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import com.dlzx.common.security.annotation.EnableCustomConfig;
import com.dlzx.common.security.annotation.EnableRyFeignClients;
import com.dlzx.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 系统模块
 * 
 * @author dlzx
 */
@EnableCustomConfig
@EnableCustomSwagger2
@EnableRyFeignClients
@SpringCloudApplication
public class DlzxSystemApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(DlzxSystemApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  系统模块启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
