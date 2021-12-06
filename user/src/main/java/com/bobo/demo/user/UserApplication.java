package com.bobo.demo.user;

import com.bobo.demo.common.base.BaseApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author bo
 */
@EnableOpenApi
@SpringBootApplication(scanBasePackages = "com.bobo.demo")
public class UserApplication extends BaseApplication {
  public static void main(String[] args) {
    SpringApplication.run(UserApplication.class, args);
  }
  
}
