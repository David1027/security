package com.david.security.core.validate.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @description: 图形验证码的异常处理
 * @author: lingjian
 * @create: 2019/9/3 10:44
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
    super(msg);
  }
}
