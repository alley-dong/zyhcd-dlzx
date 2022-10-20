package com.dlzx.system.service.impl.PushMessage;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.dlzx.common.core.utils.StringUtils;
import org.junit.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;

/**
 * @author ylh
 * @program:dlzx
 * @description：
 * @create:2021-02-21 15-50
 */
public class BaseMessage {
    protected static String region;
    protected static long appKeyIos;
    protected static long appKeyAndroid;
    protected static String deviceIds;
    protected static String deviceId;
    protected static String accounts;
    protected static String account;
    protected static String aliases;
    protected static String alias;
    protected static String tag;
    protected static String tagExpression;

    protected static DefaultAcsClient client;


    /**
     * 从配置文件中读取配置值，初始化Client
     * <p>
     * 1. 如何获取 accessKeyId/accessKeySecret/appKey 照见README.md 中的说明<br/>
     * 2. 先在 push.properties 配置文件中 填入你的获取的值
     */
    @BeforeClass
    public static void beforeClass() throws Exception {
        InputStream inputStream = BaseMessage.class.getClassLoader().getResourceAsStream("push.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        String accessKeyId = properties.getProperty("accessKeyId");
        assertNotNull("先在 push.properties 配置文件中配置 accessKeyId", accessKeyId);

        String accessKeySecret = properties.getProperty("accessKeySecret");
        assertNotNull("先在 push.properties 配置文件中配置 accessKeySecret", accessKeySecret);

        String keyIos = properties.getProperty("appKeyIos");
        assertNotNull("先在 push.properties 配置文件中配置 appKeyIos", appKeyIos);

        String keyAndroid = properties.getProperty("appKeyAndroid");
        assertNotNull("先在 push.properties 配置文件中配置 appKeyAndroid", appKeyAndroid);

        region = properties.getProperty("regionId");
        if (StringUtils.isNotEmpty(keyIos)){
            appKeyIos = Long.valueOf(keyIos);
        }
        if (StringUtils.isNotEmpty(keyAndroid)){
            appKeyAndroid = Long.valueOf(keyAndroid);
        }
        deviceIds = properties.getProperty("deviceIds");
        deviceId = properties.getProperty("deviceId");
        accounts = properties.getProperty("accounts");
        account = properties.getProperty("account");
        aliases = properties.getProperty("aliases");
        alias = properties.getProperty("alias");
        tag = properties.getProperty("tag");
        tagExpression = properties.getProperty("tagExpression");

        IClientProfile profile = DefaultProfile.getProfile(region, accessKeyId, accessKeySecret);
        client = new DefaultAcsClient(profile);
    }
}
