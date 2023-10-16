package ru.morrigan.spring.bootstrap.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Config {
  @Bean
  protected BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);
  }
}
