package com.adnangondal.posts.config;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.adnangondal.posts")
@SpringBootTest
public class IntegrationTestConfig {
  // Additional test-specific configurations can be added here
}
