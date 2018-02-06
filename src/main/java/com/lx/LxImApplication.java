package com.lx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
// "com.fwyun.io",
// @EnableConfigurationProperties({FwyunClientProperties.class, FwyunServerProperties.class})
public class LxImApplication {
  public static void main(String[] args) {
    SpringApplication.run(LxImApplication.class, args);
  }
}
