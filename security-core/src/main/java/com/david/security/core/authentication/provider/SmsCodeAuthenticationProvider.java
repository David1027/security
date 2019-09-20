package com.david.security.core.authentication.provider;

import com.david.security.core.authentication.token.SmsCodeAuthenticationToken;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @description: 短信登陆提供者
 * @author: lingjian
 * @create: 2019/9/5 14:24
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

  @Getter @Setter private UserDetailsService userDetailsService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
    UserDetails user =
        userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());

    if (user == null) {
      throw new InternalAuthenticationServiceException("无法获取认证用户信息");
    }

    SmsCodeAuthenticationToken authenticationResult =
        new SmsCodeAuthenticationToken(user, user.getAuthorities());
    authenticationResult.setDetails(authenticationToken.getDetails());

    return authenticationResult;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
