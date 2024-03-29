package com.david.security.browser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @description: 自定义用户信息获取
 * @author: lingjian
 * @create: 2019/8/30 11:24
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @Autowired private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    logger.info("登陆用户名：" + username);

    logger.info("登陆密码："+passwordEncoder.encode("123456"));
    // 根据用户名查找用户信息
    return new User(
        username,
        passwordEncoder.encode("123456"),
        true,
        true,
        true,
        true,
        AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
  }
}
