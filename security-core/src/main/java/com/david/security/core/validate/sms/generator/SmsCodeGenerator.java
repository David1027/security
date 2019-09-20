package com.david.security.core.validate.sms.generator;

import com.david.security.core.properties.SecurityProperties;
import com.david.security.core.validate.head.generator.ValidateCodeGenerator;
import com.david.security.core.validate.sms.code.SmsCode;

import lombok.Data;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 短信验证码生成器
 * @author: lingjian
 * @create: 2019/9/4 14:22
 */
@Component("smsCodeGenerator")
@Data
public class SmsCodeGenerator implements ValidateCodeGenerator {

  @Autowired private SecurityProperties securityProperties;

  @Override
  public SmsCode generator() {
    String code =
        RandomStringUtils.randomNumeric(securityProperties.getValidate().getSms().getLength());
    return new SmsCode(code, securityProperties.getValidate().getSms().getExpireIn());
  }
}
