package com.bobo.demo.sso;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.bobo.demo.common.base.BaseApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author bo
 */
@EnableOpenApi
@RefreshScope
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"com.bobo.demo"}, exclude = {DataSourceAutoConfiguration.class,
  MybatisPlusAutoConfiguration.class})
public class SsoApplication extends BaseApplication {
  public static void main(String[] args) {
    SpringApplication.run(SsoApplication.class, args);
  }
  
}
