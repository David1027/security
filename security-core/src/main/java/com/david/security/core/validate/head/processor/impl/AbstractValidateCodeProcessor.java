package com.david.security.core.validate.head.processor.impl;

import java.util.Map;

import com.david.security.core.validate.head.code.ValidateCode;
import com.david.security.core.validate.head.generator.ValidateCodeGenerator;
import com.david.security.core.validate.head.processor.ValidateCodeProcessor;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @description: 抽象的校验码处理器
 * @author: lingjian
 * @create: 2019/9/5 9:43
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode>
    implements ValidateCodeProcessor {

  private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

  /** 收集系统中所有{@link ValidateCodeGenerator}接口的实现 */
  @Autowired private Map<String, ValidateCodeGenerator> validateCodeGenerators;

  @Override
  public void create(ServletWebRequest request) throws Exception {
    C validateCode = generate(request);
    save(request, validateCode);
    send(request, validateCode);
  }

  /**
   * 生成校验码
   *
   * @return
   */
  private C generate(ServletWebRequest request) {
    String type = getProcessorType(request);
    ValidateCodeGenerator validateCodeGenerator =
        validateCodeGenerators.get(type + "CodeGenerator");
    return (C) validateCodeGenerator.generator();
  }

  /**
   * 保存校验码
   *
   * @param request 请求参数
   * @param validateCode 返回校验码的类
   */
  private void save(ServletWebRequest request, C validateCode) {
    sessionStrategy.setAttribute(
        request, SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase(), validateCode);
  }

  /**
   * 发送校验码
   *
   * @param request 请求参数
   * @param validateCode 返回校验码的类
   * @throws Exception 异常
   */
  protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

  /**
   * 根据请求的url获取校验码的类型
   *
   * @return String
   */
  private String getProcessorType(ServletWebRequest request) {
    return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
  }
}
