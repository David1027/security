package com.david.security.core.validate.config;

import com.david.security.core.validate.generator.ValidateCodeGenerator;
import com.david.security.core.validate.generator.impl.ImageCodeGenerator;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 验证码的配置类
 * @author: lingjian
 * @create: 2019/9/4 10:20
 */
@Configuration
public class ValidateCodeBeanConfig {

  @Bean
  @ConditionalOnMissingBean(name = "imageCodeGenerator")
  public ValidateCodeGenerator imageCodeGenerator() {
    ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
    return codeGenerator;
  }
}
