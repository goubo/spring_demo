package com.bobo.demo.user.controller;

import cn.hutool.core.bean.BeanUtil;
import com.bobo.demo.common.base.BaseController;
import com.bobo.demo.common.enums.ResponseCode;
import com.bobo.demo.common.response.ResponseResult;
import com.bobo.demo.user.entity.UserInfo;
import com.bobo.demo.user.entity.VO.AuthVO;
import com.bobo.demo.user.entity.VO.UserInfoVO;
import com.bobo.demo.user.entity.query.AuthQuery;
import com.bobo.demo.user.service.IUserInfoService;
import io.swagger.annotations.ApiModel;
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
  
  @PostMapping(value = "auth")
  @ResponseBody
  public ResponseResult<AuthVO> auth(@RequestBody AuthQuery authQuery) {
    AuthVO authVO = new AuthVO().setUserInfo(new UserInfoVO());
    UserInfo auth = service.auth(authQuery);
    if (auth == null) {
      return new ResponseResult<>(ResponseCode.FORBIDDEN, "登录失败");
    }
    
    BeanUtil.copyProperties(auth, authVO.getUserInfo());
    return new ResponseResult<>(authVO);
  }
  
}
