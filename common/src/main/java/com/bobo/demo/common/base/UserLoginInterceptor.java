package com.bobo.demo.common.base;

import cn.hutool.json.JSONUtil;
import com.bobo.demo.common.entity.auth.AuthVO;
import com.bobo.demo.common.enums.ResponseCode;
import com.bobo.demo.common.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author bo
 */

@Slf4j
public class UserLoginInterceptor implements HandlerInterceptor {
  
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//    log.debug(" ------------ > this is preHandle ");
    // 验证登陆的session
    AuthVO loginUser = (AuthVO) request.getSession().getAttribute("login_user");
    if (loginUser == null) {
      response.setContentType("application/json; charset=UTF-8");
      response.setStatus(ResponseCode.UNAUTHORIZED.getHttpCode());
      PrintWriter pw = response.getWriter();
      pw.write(JSONUtil.toJsonStr(new ResponseResult<>(ResponseCode.UNAUTHORIZED)));
      pw.flush();
      pw.close();
      return false;
    } else {
      return true;
    }
  }
  
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                         ModelAndView modelAndView) throws Exception {
//    log.debug(" ------------ > this is postHandle ");
    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
  }
  
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//    log.debug(" ------------ > this is afterCompletion ");
    HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
  }
}
