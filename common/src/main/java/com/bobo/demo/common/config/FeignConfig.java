package com.bobo.demo.common.config;


import cn.hutool.core.util.StrUtil;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author bo
 */
@Configuration
public class FeignConfig {
  
  public RequestInterceptor requestInterceptor() {
    return requestTemplate -> {
      String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
      if (StrUtil.isNotEmpty(sessionId)) {
        requestTemplate.header("Cookie", "SESSION=" + sessionId);
        
      }
      
    };
    
  }
}
