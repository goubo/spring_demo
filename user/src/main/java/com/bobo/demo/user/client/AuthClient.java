package com.bobo.demo.user.client;

import org.springframework.cloud.openfeign.FeignClient;

import static com.bobo.demo.common.constant.ModuleInfoConstant.AUTH_MODULE_NAME;

/**
 * 登录验证模块
 *
 * @author bo
 */
@FeignClient(AUTH_MODULE_NAME)
public interface AuthClient {
}
