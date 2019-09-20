package com.david.security.core.validate.head.config;

import com.david.security.core.properties.SecurityProperties;
import com.david.security.core.validate.head.filter.ValidateCodeFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @description: 校验码相关配置
 * @author: lingjian
 * @create: 2019/9/18 9:20
 */
@Component
public class ValidateCodeSecurityConfig
    extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  @Autowired private SecurityProperties securityProperties;
  @Autowired private AuthenticationFailureHandler myAuthenticationFailureHandler;

  @Override
  public void configure(HttpSecurity http) throws Exception {

    ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
    validateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
    validateCodeFilter.setSecurityProperties(securityProperties);
    validateCodeFilter.afterPropertiesSet();

    http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
