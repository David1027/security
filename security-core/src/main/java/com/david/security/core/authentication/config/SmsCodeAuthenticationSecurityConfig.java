package com.david.security.core.authentication.config;

import com.david.security.core.authentication.filter.SmsCodeAuthenticationFilter;
import com.david.security.core.authentication.provider.SmsCodeAuthenticationProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @description: 短信登陆验证配置类
 * @author: lingjian
 * @create: 2019/9/5 14:38
 */
@Component
public class SmsCodeAuthenticationSecurityConfig
    extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  @Autowired private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
  @Autowired private AuthenticationFailureHandler myAuthenticationFailureHandler;
  @Autowired private UserDetailsService myUserDetailsService;

  @Override
  public void configure(HttpSecurity http) throws Exception {

    SmsCodeAuthenticationFilter smsFilter = new SmsCodeAuthenticationFilter();
    smsFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
    smsFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
    smsFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);

    SmsCodeAuthenticationProvider smsProvider = new SmsCodeAuthenticationProvider();
    smsProvider.setUserDetailsService(myUserDetailsService);
    http.authenticationProvider(smsProvider)
        .addFilterAfter(smsFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
