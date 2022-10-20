package com.dlzx.system.controller;

import com.aliyuncs.exceptions.ClientException;
import com.dlzx.common.core.exception.CustomException;
import com.dlzx.common.core.utils.HttpClient;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.utils.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import com.dlzx.common.redis.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.dlzx.common.core.web.controller.BaseController;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.log.enums.BusinessType;
import com.dlzx.common.security.service.TokenService;
import com.dlzx.common.security.utils.SecurityUtils;
import com.dlzx.system.api.domain.SysUser;
import com.dlzx.system.api.model.LoginUser;
import com.dlzx.system.service.ISysUserService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

/**
 * 个人信息 业务处理
 * 
 * @author dlzx
 */
@RestController
@RequestMapping("/user/profile")
public class SysProfileController extends BaseController
{
    private final static long EXPIRE_TIME = 600;

    @Autowired
    private ISysUserService userService;
    
    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisService redisService;

    /**
     * 个人信息
     */
    @GetMapping
    public AjaxResult profile()
    {
        String username = SecurityUtils.getUsername();
        SysUser user = userService.selectUserByUserName(username);
        AjaxResult ajax = AjaxResult.success(user);
        ajax.put("roleGroup", userService.selectUserRoleGroup(username));
        ajax.put("postGroup", userService.selectUserPostGroup(username));
        return ajax;
    }

    /**
     * 修改用户
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult updateProfile(@RequestBody SysUser user)
    {
        if (userService.updateUserProfile(user) > 0)
        {
            LoginUser loginUser = tokenService.getLoginUser();
            // 更新缓存用户信息
            loginUser.getSysUser().setNickName(user.getNickName());
            loginUser.getSysUser().setPhonenumber(user.getPhonenumber());
            loginUser.getSysUser().setEmail(user.getEmail());
            loginUser.getSysUser().setSex(user.getSex());
            tokenService.setLoginUser(loginUser);
            return AjaxResult.success();
        }
        return AjaxResult.error("修改个人信息异常，请联系管理员");
    }

    /**
     * 重置密码
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping("/updatePwd")
    public AjaxResult updatePwd(String oldPassword, String newPassword)
    {
        String username = SecurityUtils.getUsername();
        SysUser user = userService.selectUserByUserName(username);
        String password = user.getPassword();
        if (!SecurityUtils.matchesPassword(oldPassword, password))
        {
            return AjaxResult.error("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password))
        {
            return AjaxResult.error("新密码不能与旧密码相同");
        }
        if (userService.resetUserPwd(username, SecurityUtils.encryptPassword(newPassword)) > 0)
        {
            // 更新缓存用户密码
            LoginUser loginUser = tokenService.getLoginUser();
            loginUser.getSysUser().setPassword(SecurityUtils.encryptPassword(newPassword));
            tokenService.setLoginUser(loginUser);
            return AjaxResult.success();
        }
        return AjaxResult.error("修改密码异常，请联系管理员");
    }


    /**
     * 发送验证码
     * @throws ClientException
     */
    @RequestMapping("/sendCode")
    @ResponseBody
    public AjaxResult sendCode(@RequestBody String phone) throws UnsupportedEncodingException {
        if(StringUtils.isEmpty(phone)){
            return AjaxResult.error("手机号码不能为空");
        }
        if(VerifyCodeUtil.isPhone(phone)) {
            throw new CustomException("请输入正确的手机号码");
        }
        // 验证码
        String phoneCode = VerifyCodeUtil.getPhoneCode();
        // 发送短信
        String gbk = URLEncoder.encode("您正在短信验证，验证码" + phoneCode + "，切勿将验证码泄露于他人，本条验证码有效期10分钟。", "gbk");
        String string = HttpClient.sendGet("http://219.150.30.12:9080/CCYQ/SMS_PushMW.jsp", "Corp_id=82&Dest="+phone+"&Content="+gbk+"");
        System.out.println("验证码============="+phoneCode);
        System.out.println("短信接口执行结果============="+string);
        // 存入redis中(10分钟有效)
        redisService.setCacheObject(phone,phoneCode,EXPIRE_TIME, TimeUnit.SECONDS);
        return AjaxResult.success();
    }

    /**
     *找回密码
     */
    @RequestMapping("/findPassword")
    @ResponseBody
    public AjaxResult findPassword( String phone,String smsCode,String passwd)
    {
        LoginUser loginUser = tokenService.getLoginUser();
        if(StringUtils.isNull(loginUser)){
            return AjaxResult.error("用户不存在！");
        }
        if(StringUtils.isNull(phone)){
            return AjaxResult.error("手机号码不能为空！");
        }
        if(StringUtils.isNull(smsCode)){
            return AjaxResult.error("验证码不能为空！");
        }
        if(StringUtils.isNull(passwd)){
            return AjaxResult.error("密码不能为空！");
        }
        if (!VerifyCodeUtil.checkPasswordRule(passwd)){
            return AjaxResult.error("密码长度必须8位及以上，包含大小写字母，数字，特殊字符。");
        }
        if(StringUtils.isNull(redisService.getCacheObject(phone))){
            return AjaxResult.error("验证码已失效！");
        }
        if (!redisService.getCacheObject(phone).equals(smsCode)){
            return AjaxResult.error("验证码错误！");
        }
        // 获取用户信息
        SysUser sysUser = userService.selectUserById(loginUser.getUserid());
        if (sysUser != null) {
            userService.resetUserPwd(loginUser.getUsername(), SecurityUtils.encryptPassword(passwd));

            //清空redis
            redisService.deleteObject(phone);
            return AjaxResult.success();
        } else {
            return AjaxResult.error("您的手机号未注册使用,无法找回密码!");
        }
    }
}
