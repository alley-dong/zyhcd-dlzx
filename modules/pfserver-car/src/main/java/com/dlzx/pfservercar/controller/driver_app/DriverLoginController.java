package com.dlzx.pfservercar.controller.driver_app;

import com.dlzx.common.core.constant.Constants;
import com.dlzx.common.core.domain.R;
import com.dlzx.common.core.enums.UserStatus;
import com.dlzx.common.core.exception.BaseException;
import com.dlzx.common.core.utils.StringUtils;
import com.dlzx.common.core.utils.VerifyCodeUtil;
import com.dlzx.common.core.web.domain.AjaxResult;
import com.dlzx.common.core.web.page.TableDataInfo;
import com.dlzx.common.log.annotation.Log;
import com.dlzx.common.security.service.DriverTokenService;
import com.dlzx.common.security.utils.SecurityUtils;
import com.dlzx.pfservercar.client.PersonClient;
import com.dlzx.pfservercar.domain.DDriver;
import com.dlzx.pfservercar.domain.DVacation;
import com.dlzx.system.api.domain.SysUser;
import com.dlzx.system.api.model.LoginDriver;
import com.dlzx.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 类描述： 登录
 * @className DriverLoginController
 * company 赛拓信息技术有限公司
 * @author DongCL
 * @date 2021-01-25 11:21
 */
@RestController
@RequestMapping("/driver-api/login")
public class DriverLoginController {


    @Autowired
    private PersonClient personClient;

    @Autowired
    private DriverTokenService driverTokenService;

    /**
     * App 考勤-驾驶员请假列表
     */
    @RequestMapping("/driverAuthLogin")
    @ResponseBody
    @Log(title = "驾驶员登录")
    public AjaxResult login(@RequestBody DDriver dDriver)
    {
        return personClient.driverAuthLogin(dDriver);
    }


    /**
     * @describe 退出登录
     * @author DongCL
     * @date 2021-02-23 16:30
     * @param
     * @return
     */
    @PostMapping("/driverLogout")
    public AjaxResult driverLogout(HttpServletRequest request)
    {
        LoginDriver loginDriver = driverTokenService.getLoginDriver(request);
        if (StringUtils.isNotNull(loginDriver))
        {
            String username = loginDriver.getUsername();
            // 删除用户缓存记录
            driverTokenService.delLoginDriver(loginDriver.getToken());
        }
        return AjaxResult.success();
    }


    /**
     * @describe 修改密码
     * @author DongCL
     * @date 2021-02-23 16:30
     * @param
     * @return
     */
    @PostMapping("/driverUpdatePassword")
    public AjaxResult updatePwd(@RequestBody DDriver data)
    {
        LoginDriver loginDriver = driverTokenService.getLoginDriver();

        DDriver dDriver = personClient.queryDriverById(loginDriver.getUserid());
        if (dDriver == null){
            return AjaxResult.error("驾驶员信息有误");
        }
        String password = dDriver.getPassword();
        if (!SecurityUtils.matchesPassword(data.getOldPassword(), password))
        {
            return AjaxResult.error("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(data.getNewPassword(), password))
        {
            return AjaxResult.error("新密码不能与旧密码相同");
        }

        // 更新密码
        if (personClient.driverUpdatePassword(dDriver.getId(), SecurityUtils.encryptPassword(data.getNewPassword())) > 0)
        {
            return AjaxResult.success();
        }
        return AjaxResult.error("修改密码异常，请联系管理员");
    }

}
