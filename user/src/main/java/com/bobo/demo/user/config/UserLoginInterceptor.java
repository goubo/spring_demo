package com.bobo.demo.user.config;

import cn.hutool.json.JSONUtil;
import com.bobo.demo.common.entity.auth.AuthVO;
import com.bobo.demo.common.enums.ResponseCode;
import com.bobo.demo.common.response.ResponseResult;
import com.bobo.demo.user.client.AuthClient;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    // 验证登陆的session
    AuthVO loginUser = (AuthVO) request.getSession().getAttribute("login_user");
    if (loginUser == null) {
      response.setStatus(ResponseCode.UNAUTHORIZED.getCode());
      response.getWriter().write(JSONUtil.toJsonStr(new ResponseResult<>(ResponseCode.UNAUTHORIZED)));
      return false;
    } else {
      return true;
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
