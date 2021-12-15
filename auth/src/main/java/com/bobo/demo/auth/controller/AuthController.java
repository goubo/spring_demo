package com.bobo.demo.auth.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bobo.demo.auth.client.UserClient;
import com.bobo.demo.auth.entity.VO.AuthParam;
import com.bobo.demo.auth.entity.VO.AuthVO;
import com.bobo.demo.auth.entity.VO.UserInfoVO;
import com.bobo.demo.common.response.ResponseResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @author bo
 */
@RestController
public class AuthController {
  
  private final RestTemplate restTemplate;
  private final UserClient userClient;
  
  public AuthController(RestTemplate restTemplate, UserClient userClient) {
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
    return userClient.page(1, 10);
  }
  
  @PostMapping(value = "login")
  @ResponseBody
  public ResponseResult<AuthVO> login(@RequestBody AuthParam authParam) {
    return userClient.check(authParam);
  }
  
  @PostMapping(value = "checkToken")
  @ResponseBody
  public ResponseResult<Object> checkToken(@RequestParam String sessionId, @RequestParam String moduleId) {
    return null;
  }
}

