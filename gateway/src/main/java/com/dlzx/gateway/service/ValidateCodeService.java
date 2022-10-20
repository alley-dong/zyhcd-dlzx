package com.dlzx.gateway.service;

import java.io.IOException;
import com.dlzx.common.core.exception.CaptchaException;
import com.dlzx.common.core.web.domain.AjaxResult;

/**
 * 验证码处理
 * 
 * @author dlzx
 */
public interface ValidateCodeService
{
    /**
     * 生成验证码
     */
    public AjaxResult createCapcha() throws IOException, CaptchaException;

    /**
     * 校验验证码
     */
    public void checkCapcha(String key, String value) throws CaptchaException;
}
