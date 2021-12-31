package com.bobo.demo.user.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
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
import org.springframework.web.bind.annotation.*;

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
  
  private final RedissonClient redissonClient;
  
  public UserInfoController(RedissonClient redissonClient) {this.redissonClient = redissonClient;}
  
  @PostMapping(value = "/auth")
  @ResponseBody
  public ResponseResult<AuthVO> auth(@RequestBody AuthQuery authQuery) {
    AuthVO authVO = new AuthVO().setUserInfo(new AuthUserInfoVO());
    UserInfo auth = service.auth(authQuery);
    if (auth == null) {
      return new ResponseResult<>(ResponseCode.FORBIDDEN, "登录失败");
    }
    BeanUtil.copyProperties(auth, authVO.getUserInfo());
    return new ResponseResult<>(authVO);
  }
  
  @PostMapping(value = "/register")
  @ResponseBody
  public ResponseResult<AuthVO> register(@RequestBody AuthQuery authQuery) {
    if (StrUtil.isEmpty(authQuery.getUserName())) {
      return new ResponseResult<>(ResponseCode.PARAM_NOT_FOUND, "userName is null");
    }
    if (StrUtil.isEmpty(authQuery.getPassword())) {
      return new ResponseResult<>(ResponseCode.PARAM_NOT_FOUND, "password is null");
    }
    boolean b =
      service.create(new UserInfo().setUserName(authQuery.getUserName()).setUserPassword(authQuery.getPassword()));
    if (b) {
      return new ResponseResult<>();
    } else {
      return new ResponseResult<>(ResponseCode.ERROR_DAO_SERVER_ERROR, "注册失败,请联系后台");
    }
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
