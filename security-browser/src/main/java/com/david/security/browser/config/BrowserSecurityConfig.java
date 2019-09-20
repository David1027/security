package com.david.security.browser.config;

import javax.sql.DataSource;

import com.david.security.core.authentication.config.FormAuthenticationConfig;
import com.david.security.core.authentication.config.SmsCodeAuthenticationSecurityConfig;
import com.david.security.core.properties.SecurityProperties;
import com.david.security.core.validate.head.config.ValidateCodeSecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * @description: 网页端security配置类
 * @author: lingjian
 * @create: 2019/8/30 9:55
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private SecurityProperties securityProperties;
  @Autowired private DataSource dataSource;
  @Autowired private UserDetailsService myUserDetailsService;
  @Autowired private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
  @Autowired private FormAuthenticationConfig formAuthenticationConfig;
  @Autowired private ValidateCodeSecurityConfig validateCodeSecurityConfig;

  /**
   * 密码加密
   *
   * @return PasswordEncoder
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * “记住我”功能
   *
   * @return PersistentTokenRepository
   */
  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
    JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
    tokenRepository.setDataSource(dataSource);
    //    tokenRepository.setCreateTableOnStartup(true);
    return tokenRepository;
  }

  /**
   * 表单校验：formLogin(),弹窗校验：httpBasic()
   *
   * <p>自定义登陆界面：loginPage("/signIn.html")
   *
   * <p>自定义表单校验url：loginProcessingUrl("/authentication/form")
   *
   * <p>.rememberMe() .tokenRepository(persistentTokenRepository())
   * .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
   * .userDetailsService(myUserDetailsService)
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    formAuthenticationConfig.configure(http);

    http.apply(validateCodeSecurityConfig)
        .and()
        .apply(smsCodeAuthenticationSecurityConfig)
        .and()
        .rememberMe()
        .tokenRepository(persistentTokenRepository())
        .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
        .userDetailsService(myUserDetailsService)
        .and()
        .authorizeRequests()
        .antMatchers(
            "/authentication/require", securityProperties.getBrowser().getLoginPage(), "/code/*")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .csrf()
        .disable();
  }
}
