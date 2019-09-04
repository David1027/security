package com.david.security.browser.config;

import com.david.security.core.properties.SecurityProperties;
import com.david.security.core.validate.filter.ValidateCodeFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @description: 网页端security配置类
 * @author: lingjian
 * @create: 2019/8/30 9:55
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private SecurityProperties securityProperties;
  @Autowired private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
  @Autowired private AuthenticationFailureHandler myAuthenticationFailureHandler;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * 表单校验：formLogin(),弹窗校验：httpBasic()
   *
   * <p>自定义登陆界面：loginPage("/signIn.html")
   *
   * <p>自定义表单校验url：loginProcessingUrl("/authentication/form")
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
    validateCodeFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
    validateCodeFilter.setSecurityProperties(securityProperties);
    validateCodeFilter.afterPropertiesSet();

    http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
        .formLogin()
        .loginPage("/authentication/require")
        .loginProcessingUrl("/authentication/form")
        .successHandler(myAuthenticationSuccessHandler)
        .failureHandler(myAuthenticationFailureHandler)
        .and()
        .authorizeRequests()
        .antMatchers(
            "/authentication/require",
            securityProperties.getBrowser().getLoginPage(),
            "/code/image")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .csrf()
        .disable();
  }
}
