package com.bobo.demo.auth;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.bobo.demo.common.base.BaseApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author bo
 */
@EnableOpenApi
@RefreshScope
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"com.bobo.demo"},
  exclude = {DataSourceAutoConfiguration.class, MybatisPlusAutoConfiguration.class})
public class AuthApplication extends BaseApplication {
  public static void main(String[] args) {
    SpringApplication.run(AuthApplication.class, args);
  }
  
  @LoadBalanced
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
