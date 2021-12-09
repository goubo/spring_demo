package com.bobo.demo.user;

import com.bobo.demo.common.base.BaseApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author bo
 */
@EnableOpenApi
@RefreshScope
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.bobo.demo"})
public class UserApplication extends BaseApplication {
  public static void main(String[] args) {
    SpringApplication.run(UserApplication.class, args);
  }
}
