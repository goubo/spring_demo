package com.bobo.demo.auth.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bobo.demo.auth.client.UserClient;
import com.bobo.demo.auth.entity.VO.AuthParam;
import com.bobo.demo.auth.entity.VO.AuthVO;
import com.bobo.demo.auth.entity.VO.UserInfoVO;
import com.bobo.demo.common.enums.ResponseCode;
import com.bobo.demo.common.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author bo
 */
@RestController
@Slf4j
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
  
  @PostMapping(value = "/login")
  @ResponseBody
  public ResponseResult<AuthVO> login(@RequestBody AuthParam authParam, HttpServletRequest request) {
    ResponseResult<AuthVO> check = userClient.check(authParam);
    if (check.success()) {
      log.info("登录成功", check);
      request.getSession().setAttribute("login_user", check.getObject());
    }
    return check;
  }
  
  @PostMapping(value = "checkToken")
  @ResponseBody
  public ResponseResult<AuthVO> checkToken(@RequestParam String sessionId, @RequestParam String moduleName,
                                           HttpServletRequest request) {
    HttpSession session = request.getSession();
    System.out.println(sessionId);
    System.out.println(session.getId());
    AuthVO loginUser = (AuthVO) session.getAttribute("login_user");
    if (loginUser == null) {
      return new ResponseResult<>(ResponseCode.UNAUTHORIZED);
    }
    System.out.println(loginUser);
    return new ResponseResult<>(loginUser);
  }
}

