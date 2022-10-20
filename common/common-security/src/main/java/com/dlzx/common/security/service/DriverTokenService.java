package com.dlzx.common.security.service;

import com.dlzx.common.core.constant.CacheConstants;
import com.dlzx.common.core.constant.Constants;
import com.dlzx.common.core.utils.IdUtils;
import com.dlzx.common.core.utils.ServletUtils;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.utils.ip.IpUtils;
import com.dlzx.common.redis.service.RedisService;
import com.dlzx.system.api.model.LoginDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * token验证处理
 * 
 * @author dlzx
 */
@Component
public class DriverTokenService
{
    @Autowired
    private RedisService redisService;

//    private final static long EXPIRE_TIME = Constants.TOKEN_EXPIRE * 3600;
    private final static long EXPIRE_TIME = Constants.TOKEN_EXPIRE * 60;

    private final static String ACCESS_TOKEN = CacheConstants.LOGIN_TOKEN_KEY;

    protected static final long MILLIS_SECOND = 1000;


    /**
     * 创建令牌
     */
    public Map<String, Object> createToken(LoginDriver loginDriver)
    {
        // 生成token
        String token = IdUtils.fastUUID();
        loginDriver.setToken(token);
        loginDriver.setUserid(loginDriver.getUserid());
        loginDriver.setUsername(loginDriver.getUsername());
        loginDriver.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
        refreshToken(loginDriver);

        // todo 临时保存或更新用户token，对接第三方系统后需要删除
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("access_token", token);
        map.put("expires_in", EXPIRE_TIME);
        map.put("user_name", loginDriver.getUsername()==null?"":loginDriver.getUsername());
        redisService.setCacheObject(ACCESS_TOKEN + token, loginDriver, EXPIRE_TIME, TimeUnit.SECONDS);
        return map;
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginDriver getLoginDriver()
    {
        return getLoginDriver(ServletUtils.getRequest());
    }

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginDriver getLoginDriver(HttpServletRequest request)
    {
        // 获取请求携带的令牌
        String token = getToken(request);
        if (StringUtils.isNotEmpty(token))
        {
            String userKey = getTokenKey(token);
            LoginDriver user = redisService.getCacheObject(userKey);
            return user;
        }

        return null;
    }

    /**
     * 设置用户身份信息
     */
    public void setLoginDriver(LoginDriver loginDriver)
    {
        if (StringUtils.isNotNull(loginDriver) && StringUtils.isNotEmpty(loginDriver.getToken()))
        {
            refreshToken(loginDriver);
        }
    }

    public void delLoginDriver(String token)
    {
        if (StringUtils.isNotEmpty(token))
        {
            String userKey = getTokenKey(token);
            redisService.deleteObject(userKey);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginDriver 登录信息
     */
    public void refreshToken(LoginDriver loginDriver)
    {
        loginDriver.setLoginTime(System.currentTimeMillis());
        loginDriver.setExpireTime(loginDriver.getLoginTime() + EXPIRE_TIME * MILLIS_SECOND);
        // 根据uuid将loginDriver缓存
        String userKey = getTokenKey(loginDriver.getToken());
        redisService.setCacheObject(userKey, loginDriver, EXPIRE_TIME, TimeUnit.SECONDS);
    }

    private String getTokenKey(String token)
    {
        return ACCESS_TOKEN + token;
    }

    /**
     * 获取请求token
     */
    private String getToken(HttpServletRequest request)
    {
        String token = request.getHeader(CacheConstants.HEADER);
        if (StringUtils.isNotEmpty(token) && token.startsWith(CacheConstants.TOKEN_PREFIX))
        {
            token = token.replace(CacheConstants.TOKEN_PREFIX, "");
        }
        return token;
    }
}