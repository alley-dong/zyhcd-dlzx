package com.dlzx.system.domain;

import javax.validation.constraints.NotBlank;

/**
 * @author ylh
 * @program:dlzx
 * @description：app登录更新token和设备类型入表DTO
 * @create:2021-02-23 13-23
 */
public class AppTokenUpdate {


    /**设备token*/
    private String appToken;

    @NotBlank(message = "设备token未获取")
    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    //ios android
    @NotBlank(message = "设备类型未获取")
    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**设备类型 ios android*/
    private String deviceType;
}
