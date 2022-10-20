package com.dlzx.modules.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 监控中心
 * 
 * @author dlzx
 */
@EnableAdminServer
@SpringCloudApplication
public class DlzxMonitorApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(DlzxMonitorApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  监控中心启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
