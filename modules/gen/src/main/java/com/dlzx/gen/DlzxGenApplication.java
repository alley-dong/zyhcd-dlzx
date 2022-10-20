package com.dlzx.gen;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import com.dlzx.common.security.annotation.EnableCustomConfig;
import com.dlzx.common.security.annotation.EnableRyFeignClients;
import com.dlzx.common.swagger.annotation.EnableCustomSwagger2;

/**
 * 代码生成
 * 
 * @author dlzx
 */
@EnableCustomConfig
@EnableCustomSwagger2   
@EnableRyFeignClients
@SpringCloudApplication
public class DlzxGenApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(DlzxGenApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  代码生成模块启动成功");
    }
}
