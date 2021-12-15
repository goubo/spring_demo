package com.bobo.demo.user.config;

import com.bobo.demo.user.client.AuthClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author bo
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {
  private final AuthClient authClient;
  
  public LoginConfig(AuthClient authClient) {this.authClient = authClient;}
  
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    //注册TestInterceptor拦截器
    InterceptorRegistration registration = registry.addInterceptor(new UserLoginInterceptor(authClient));
    //所有路径都被拦截
    registration.addPathPatterns("/**");
    //添加不拦截路径
    registration.excludePathPatterns("/login",
      "/**/*.html", "/**/*.js", "/**/*.css");
  }
}
