package com.bobo.demo.user.client;

import com.bobo.demo.common.response.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.bobo.demo.common.constant.ModuleInfoConstant.AUTH_MODULE_NAME;

/**
 * 登录验证模块
 *
 * @author bo
 */
@FeignClient(AUTH_MODULE_NAME)
public interface AuthClient {
  /**
   * 验证session 是否登录
   *
   * @param sessionId  session id
   * @param moduleName 模块名称
   * @return
   */
  @PostMapping(value = "checkToken")
  @ResponseBody
  ResponseResult<Object> checkToken(@RequestParam String sessionId, @RequestParam String moduleName);
}
