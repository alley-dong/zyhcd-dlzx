package com.dlzx.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import com.dlzx.common.security.annotation.EnableRyFeignClients;

/**
 * 认证授权中心
 * 
 * @author dlzx
 */
@EnableRyFeignClients
@SpringCloudApplication
public class DlzxAuthApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(DlzxAuthApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  认证授权中心启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
