package com.bobo.demo.login.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bobo.demo.common.response.ResponseResult;
import com.bobo.demo.login.client.UserClient;
import com.bobo.demo.login.entity.VO.UserInfoVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
  
  
  @GetMapping(value = "test/{string}", produces = "application/json")
  @ResponseBody
  public ResponseResult<Page<UserInfoVO>> test() {
//    System.out.println(restTemplate.getForObject("http://" + USER_INFO_MODULE_NAME + "/user/user-info", String
//    .class));
    return userClient.page(1, 10);
  }
}
