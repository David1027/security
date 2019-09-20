package com.david.security.core.validate.head.config;

import com.david.security.core.validate.head.generator.ValidateCodeGenerator;
import com.david.security.core.validate.image.generator.ImageCodeGenerator;
import com.david.security.core.validate.sms.specific.SmsCodeSender;
import com.david.security.core.validate.sms.specific.impl.DefaultSmsCodeSender;

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

  /**
   * 图形验证码的bean配置
   *
   * @return ValidateCodeGenerator生成验证码的接口
   */
  @Bean
  @ConditionalOnMissingBean(name = "imageCodeGenerator")
  public ValidateCodeGenerator imageCodeGenerator() {
    return new ImageCodeGenerator();
  }

  /**
   * 短信验证码的bean配置
   *
   * @return SmsCodeSender发送短信验证码的接口
   */
  @Bean
  @ConditionalOnMissingBean(SmsCodeSender.class)
  public SmsCodeSender smsValidateCodeSender() {
    return new DefaultSmsCodeSender();
  }
}
