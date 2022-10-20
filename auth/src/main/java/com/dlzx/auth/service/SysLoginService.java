package com.dlzx.auth.service;

import com.dlzx.auth.client.PersonClient;
import com.dlzx.auth.form.DDriver;
import com.dlzx.common.core.utils.VerifyCodeUtil;
import com.dlzx.common.core.web.domain.ESBResult;
import com.dlzx.common.security.service.DriverTokenService;
import com.dlzx.system.api.model.LoginDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.dlzx.common.core.constant.Constants;
import com.dlzx.common.core.constant.UserConstants;
import com.dlzx.common.core.domain.R;
import com.dlzx.common.core.enums.UserStatus;
import com.dlzx.common.core.exception.BaseException;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.security.utils.SecurityUtils;
import com.dlzx.system.api.RemoteLogService;
import com.dlzx.system.api.RemoteUserService;
import com.dlzx.system.api.domain.SysUser;
import com.dlzx.system.api.model.LoginUser;

import java.util.Map;

/**
 * 登录校验方法
 * 
 * @author dlzx
 */
@Component
public class SysLoginService
{
    @Autowired
    private RemoteLogService remoteLogService;

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private DriverTokenService driverTokenService;

    @Autowired
    private PersonClient personClient;

    /**
     * 登录
     */
    public LoginUser login(String username, String password)
    {
        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(username, password))
        {
            remoteLogService.saveLogininfor(username, Constants.LOGIN_FAIL, "用户/密码必须填写");
            throw new BaseException("用户/密码必须填写");
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            remoteLogService.saveLogininfor(username, Constants.LOGIN_FAIL, "用户密码不在指定范围");
            throw new BaseException("用户密码不在指定范围");
        }
        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            remoteLogService.saveLogininfor(username, Constants.LOGIN_FAIL, "用户名不在指定范围");
            throw new BaseException("用户名不在指定范围");
        }
        // 查询用户信息
        R<LoginUser> userResult = remoteUserService.getUserInfo(username);

        if (R.FAIL == userResult.getCode())
        {
            throw new BaseException(userResult.getMsg());
        }

        if (StringUtils.isNull(userResult) || StringUtils.isNull(userResult.getData()))
        {
            remoteLogService.saveLogininfor(username, Constants.LOGIN_FAIL, "登录用户不存在");
            throw new BaseException("登录用户：" + username + " 不存在");
        }
        LoginUser userInfo = userResult.getData();
        SysUser user = userResult.getData().getSysUser();
        if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            remoteLogService.saveLogininfor(username, Constants.LOGIN_FAIL, "对不起，您的账号已被删除");

            throw new BaseException("对不起，您的账号：" + username + " 已被删除");
        }
        if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            remoteLogService.saveLogininfor(username, Constants.LOGIN_FAIL, "用户已停用，请联系管理员");
            throw new BaseException("对不起，您的账号：" + username + " 已停用");
        }
        if (!SecurityUtils.matchesPassword(password, user.getPassword()))
        {
            remoteLogService.saveLogininfor(username, Constants.LOGIN_FAIL, "用户密码错误");
            throw new BaseException("用户不存在/密码错误");
        }
        remoteLogService.saveLogininfor(username, Constants.LOGIN_SUCCESS, "登录成功");
        return userInfo;
    }



    /**
     * 驾驶员登录
     */
    public ESBResult driverLogin(DDriver driver)
    {
        System.out.println("登录驾驶员信息========="+driver);
        if (driver== null){
            return ESBResult.error("请校验参数格式");
        }
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(driver.getContact()))
        {
            remoteLogService.saveLogininfor(driver.getContact(), Constants.LOGIN_FAIL, "手机号必须填写");
            return ESBResult.error("手机号必须填写");
        }

        if(!VerifyCodeUtil.isPhone(driver.getContact())) {
            return ESBResult.error("请输入正确的手机号码");
        }

        // 查询用户信息
        DDriver dDriver = personClient.driverLogin(driver.getContact());

        if (dDriver ==null)
        {
            remoteLogService.saveLogininfor(driver.getContact(), Constants.LOGIN_FAIL, "登录手机号不存在");
            return ESBResult.error("登录手机号：" + driver.getContact() + " 不存在");
        }

        if (UserStatus.DELETED.getCode().equals(dDriver.getDisableStatus()))
        {
            remoteLogService.saveLogininfor(dDriver.getName(), Constants.LOGIN_FAIL, "用户已停用，请联系管理员");
            return ESBResult.error("对不起，您的账号：" + dDriver.getName() + " 已停用");
        }
        remoteLogService.saveLogininfor(dDriver.getName(), Constants.LOGIN_SUCCESS, "登录成功");

        LoginDriver loginDriver = new LoginDriver();
        loginDriver.setUserid(dDriver.getId());
        loginDriver.setUsername(dDriver.getName());
        Map<String, Object> token = driverTokenService.createToken(loginDriver);

        System.out.println("登录手机号==============="+driver.getContact());
        System.out.println("登录接口返回token==============="+token.get("access_token"));
        return ESBResult.success(token);
    }

    public void logout(String loginName)
    {
        remoteLogService.saveLogininfor(loginName, Constants.LOGOUT, "退出成功");
    }
}