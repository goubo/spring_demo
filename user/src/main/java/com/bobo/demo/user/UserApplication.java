package com.bobo.demo.user;

import com.bobo.demo.common.base.BaseApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author bo
 * 程序入口
 */

/**
 * Swagger
 */
@EnableOpenApi
/**
 * 缓存
 */
//@EnableCaching
/**
 * nacos 配置自动刷新
 */
@RefreshScope
/**
 * 自动注册
 */
@EnableDiscoveryClient
/**
 * 自动注册 feign
 */

@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"com.bobo.demo"})
public class UserApplication extends BaseApplication {
  public static void main(String[] args) {
    SpringApplication.run(UserApplication.class, args);
  }
}
