package com.bobo.demo.login.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bobo.demo.common.response.ResponseResult;
import com.bobo.demo.login.client.UserClient;
import com.bobo.demo.login.entity.VO.UserInfoVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author bo
 */
@RestController
public class LoginController {
  
  private final RestTemplate restTemplate;
  private final UserClient userClient;
  
  public LoginController(RestTemplate restTemplate, UserClient userClient) {
    this.restTemplate = restTemplate;
    this.userClient = userClient;
  }
  
  /**
   * test
   *
   * @return
   */
  @GetMapping(value = "test", produces = "application/json")
  @ResponseBody
  public ResponseResult<Page<UserInfoVO>> test(HttpServletRequest request) {
//    System.out.println(restTemplate.getForObject("http://" + USER_INFO_MODULE_NAME + "/user/user-info", String
//    .class));
    Map<String, String[]> parameterMap = request.getParameterMap();
    System.out.println(parameterMap);
    return userClient.page(1, 10);
  }
}
