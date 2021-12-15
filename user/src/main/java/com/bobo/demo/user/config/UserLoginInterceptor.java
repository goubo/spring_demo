package com.bobo.demo.user.config;

import cn.hutool.json.JSONUtil;
import com.bobo.demo.common.enums.ResponseCode;
import com.bobo.demo.common.response.ResponseResult;
import com.bobo.demo.user.client.AuthClient;
import com.bobo.demo.user.entity.VO.AuthVO;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.bobo.demo.common.constant.ModuleInfoConstant.USER_INFO_MODULE_NAME;

/**
 * @author bo
 */

public class UserLoginInterceptor implements HandlerInterceptor {
  
  private AuthClient authClient;
  
  public UserLoginInterceptor(AuthClient authClient) {
    this.authClient = authClient;
    
  }
  
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    System.out.println(" ------------ > this is preHandle ");
    //查询session
    String id = request.getSession().getId();
    //通过auth模块验证身份 和 模块权限
    ResponseResult<AuthVO> authVOResponseResult = authClient.checkToken(id, USER_INFO_MODULE_NAME);
    if (authVOResponseResult.success()) {
      System.out.println(authVOResponseResult);
      request.setAttribute("userSession", authVOResponseResult.getObject());
      return true;
    } else {
      response.setStatus(ResponseCode.UNAUTHORIZED.getCode());
      response.getWriter().write(JSONUtil.toJsonStr(new ResponseResult<>(ResponseCode.UNAUTHORIZED)));
      return false;
    }
    
  }
  
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                         ModelAndView modelAndView) throws Exception {
    System.out.println(" ------------ > this is postHandle ");
    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
  }
  
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    System.out.println(" ------------ > this is afterCompletion ");
    HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
  }
}
