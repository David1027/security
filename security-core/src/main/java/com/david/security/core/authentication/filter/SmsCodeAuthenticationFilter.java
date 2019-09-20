package com.david.security.core.authentication.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.david.security.core.authentication.token.SmsCodeAuthenticationToken;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

/**
 * @description: 短信验证码过滤器
 * @author: lingjian
 * @create: 2019/9/5 14:13
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  public static final String DAVID_FORM_MOBILE_KEY = "mobile";

  private String mobileParameter = DAVID_FORM_MOBILE_KEY;
  private boolean postOnly = true;

  public SmsCodeAuthenticationFilter() {
    super(new AntPathRequestMatcher("/authentication/mobile", "POST"));
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    if (postOnly && !request.getMethod().equals("POST")) {
      throw new AuthenticationServiceException(
          "Authentication method not supported: " + request.getMethod());
    }

    String mobile = obtainMobile(request);
    if (mobile == null) {
      mobile = "";
    }
    mobile = mobile.trim();
    SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(mobile);
    setDetails(request, authRequest);

    return this.getAuthenticationManager().authenticate(authRequest);
  }

  /** 获取手机号 */
  protected String obtainMobile(HttpServletRequest request) {
    return request.getParameter(mobileParameter);
  }

  protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
    authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
  }

  public void setUsernameParameter(String mobileParameter) {
    Assert.hasText(mobileParameter, "Username parameter must not be empty or null");
    this.mobileParameter = mobileParameter;
  }

  public void setPostOnly(boolean postOnly) {
    this.postOnly = postOnly;
  }

  public final String getMobileParameter() {
    return mobileParameter;
  }
}
