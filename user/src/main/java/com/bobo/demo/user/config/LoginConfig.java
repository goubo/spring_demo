package com.bobo.demo.user.config;

import com.bobo.demo.common.base.UserLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author bo
 */
@Configuration
public class LoginConfig extends WebMvcConfigurationSupport {
  
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    //注册TestInterceptor拦截器
    InterceptorRegistration registration = registry.addInterceptor(loginInterceptor());
    //所有路径都被拦截
    registration.addPathPatterns("/**");
    //添加不拦截路径
    registration.excludePathPatterns("favicon.ico", "/user-info/auth", "/user-info/register");
  }
  
  @Bean
  public UserLoginInterceptor loginInterceptor() {
    return new UserLoginInterceptor();
  }
}
