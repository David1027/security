package com.david.security.core.validate.head.processor;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @description: 校验码处理器，封装不同校验码的处理逻辑
 * @author: lingjian
 * @create: 2019/9/5 9:34
 */
public interface ValidateCodeProcessor {

  /** 验证码放入session时的前缀 */
  String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

  /**
   * 创建校验码
   *
   * @throws Exception 异常
   */
  void create(ServletWebRequest request) throws Exception;
}
