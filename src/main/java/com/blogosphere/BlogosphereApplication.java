package com.blogosphere;

import com.blogosphere.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class BlogosphereApplication {

  public static void main(String[] args) {
    SpringApplication.run(BlogosphereApplication.class, args);
  }
}