package com.bobo.demo.user.controller;

import cn.hutool.core.bean.BeanUtil;
import com.bobo.demo.common.base.BaseController;
import com.bobo.demo.common.entity.auth.AuthUserInfoVO;
import com.bobo.demo.common.entity.auth.AuthVO;
import com.bobo.demo.common.enums.ResponseCode;
import com.bobo.demo.common.response.ResponseResult;
import com.bobo.demo.user.entity.UserInfo;
import com.bobo.demo.user.entity.query.AuthQuery;
import com.bobo.demo.user.service.IUserInfoService;
import io.swagger.annotations.ApiModel;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author bobo
 * @since 2021-12-08
 */
@RestController
@RequestMapping("/user-info")
@ApiModel(value = "UserInfo", description = "用户相关接口")
public class UserInfoController extends BaseController<UserInfo, IUserInfoService> {
  
  @Autowired
  RedissonClient redissonClient;
  
  @PostMapping(value = "/auth")
  @ResponseBody
  public ResponseResult<AuthVO> auth(@RequestBody AuthQuery authQuery, HttpServletRequest request) {
    AuthVO authVO = new AuthVO().setUserInfo(new AuthUserInfoVO());
    UserInfo auth = service.auth(authQuery);
    if (auth == null) {
      return new ResponseResult<>(ResponseCode.FORBIDDEN, "登录失败");
    }
    BeanUtil.copyProperties(auth, authVO.getUserInfo());
    return new ResponseResult<>(authVO);
  }
  
  @GetMapping(value = "/test/redis")
  @ResponseBody
  public ResponseResult<UserInfo> testRedis() {
    RMap<String, Object> user = redissonClient.getMap("user");
    user.put("111", new UserInfo().setUserName("test1"));
    return new ResponseResult<>();
  }
  
  
  @GetMapping(value = "/test/lock")
  @ResponseBody
  public ResponseResult<UserInfo> testLock() {
    RLock sl = redissonClient.getLock("sl");
    sl.lock();
    return new ResponseResult<>();
  }
  
  
  @GetMapping(value = "/test/unlock")
  @ResponseBody
  public ResponseResult<UserInfo> testUnLock() {
    RLock sl = redissonClient.getLock("sl");
    sl.unlock();
    return new ResponseResult<>();
  }
  
  
}
