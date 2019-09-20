package com.david.security.core.authentication.config;

import com.david.security.core.properties.SecurityConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @description: 表单登陆配置
 * @author: lingjian
 * @create: 2019/9/18 9:14
 */
@Component
public class FormAuthenticationConfig {

  @Autowired private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
  @Autowired private AuthenticationFailureHandler myAuthenticationFailureHandler;

  public void configure(HttpSecurity http) throws Exception {
    http.formLogin()
        .loginPage(SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL)
        .loginProcessingUrl(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM)
        .successHandler(myAuthenticationSuccessHandler)
        .failureHandler(myAuthenticationFailureHandler);
  }
}
