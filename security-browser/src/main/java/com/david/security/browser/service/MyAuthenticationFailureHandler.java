package com.david.security.browser.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.david.security.browser.support.SimpleResponse;
import com.david.security.core.enums.LoginType;
import com.david.security.core.properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * @description: 登陆失败后的处理
 * @author: lingjian
 * @create: 2019/9/2 16:22 implements AuthenticationFailureHandler
 */
@Component("myAuthenticationFailureHandler")
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

  private Logger logger = LoggerFactory.getLogger(getClass());
  @Autowired private ObjectMapper objectMapper;
  @Autowired private SecurityProperties securityProperties;

  @Override
  public void onAuthenticationFailure(
      HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
      throws IOException, ServletException {
    logger.info("登陆失败");

    if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
      response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
    } else {
      super.onAuthenticationFailure(request, response, exception);
    }
  }
}
