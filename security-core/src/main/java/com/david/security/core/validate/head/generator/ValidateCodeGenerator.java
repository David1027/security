package com.david.security.core.validate.head.generator;

/**
 * @description: 生成验证码的接口
 * @author: lingjian
 * @create: 2019/9/4 9:49
 */
public interface ValidateCodeGenerator {

  /**
   * 生成验证码
   *
   * @return Object
   */
  Object generator();
}
