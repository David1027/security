package com.david.security.core.validate.head.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.david.security.core.properties.SecurityConstants;
import com.david.security.core.validate.head.processor.ValidateCodeProcessor;
import com.david.security.core.validate.head.processor.ValidateCodeProcessorHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @description: 图形验证码控制层
 * @author: lingjian
 * @create: 2019/9/3 10:02
 */
@RestController
public class ValidateCodeController {

  @Autowired private Map<String, ValidateCodeProcessor> validateCodeProcessors;
  @Autowired private ValidateCodeProcessorHolder validateCodeProcessorHolder;

  /**
   * 创建验证码，根据验证码类型不同，调用不同的{@link ValidateCodeProcessor}接口实现
   *
   * @param request 请求参数
   * @param response 响应参数
   * @param type 验证类型
   * @throws Exception 异常
   */
  @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
  public void createCode(
      HttpServletRequest request, HttpServletResponse response, @PathVariable String type)
      throws Exception {
    validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
//    validateCodeProcessors
//        .get(type + "CodeProcessor")
//        .create(new ServletWebRequest(request, response));
  }
}
