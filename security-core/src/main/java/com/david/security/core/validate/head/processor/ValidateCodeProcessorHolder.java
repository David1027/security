package com.david.security.core.validate.head.processor;

import java.util.Map;

import com.david.security.core.validate.head.exception.ValidateCodeException;
import com.david.security.core.validate.head.type.ValidateCodeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 校验码处理器管理器
 * @author: lingjian
 * @create: 2019/9/17 15:27
 */
@Component
public class ValidateCodeProcessorHolder {
  @Autowired private Map<String, ValidateCodeProcessor> validateCodeProcessor;

  /**
   * 获取校验码处理器转为字符串并全部小写处理
   *
   * @param type 校验码枚举
   * @return ValidateCodeProcessor校验码处理器
   */
  public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
    return findValidateCodeProcessor(type.toString().toLowerCase());
  }

  /**
   * 获取校验码处理器
   *
   * @param type 校验码类型
   * @return ValidateCodeProcessor校验码处理器
   */
  public ValidateCodeProcessor findValidateCodeProcessor(String type) {
    String name = type.toLowerCase() + "CodeProcessor";
    ValidateCodeProcessor processor = validateCodeProcessor.get(name);
    if (processor == null) {
      throw new ValidateCodeException("验证码处理器" + name + "不存在");
    }
    return processor;
  }
}
