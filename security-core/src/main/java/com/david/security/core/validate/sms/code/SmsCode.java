package com.david.security.core.validate.sms.code;

import java.time.LocalDateTime;

import com.david.security.core.validate.head.code.ValidateCode;

import lombok.Data;

/**
 * @description: 短信验证码
 * @author: lingjian
 * @create: 2019/9/4 14:18
 */
@Data
public class SmsCode extends ValidateCode {

  public SmsCode(String code, int expireIn) {
    super(code, expireIn);
  }
}
