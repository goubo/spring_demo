package com.bobo.demo.user.config;

import com.bobo.demo.common.response.ResponseResult;
import com.bobo.demo.user.client.AuthClient;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bo
 */
public record UserLoginInterceptor(AuthClient authClient) implements HandlerInterceptor {
  
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    System.out.println(" ------------ > this is preHandle ");
    //查询session
    String id = request.getSession().getId();
    //通过auth模块验证身份 和 模块权限
    ResponseResult<Object> objectResponseResult = authClient.checkToken(id, "");
    System.out.println(objectResponseResult.toString());
    return true;
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
