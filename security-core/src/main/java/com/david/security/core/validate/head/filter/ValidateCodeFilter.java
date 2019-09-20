package com.david.security.core.validate.head.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.david.security.core.properties.SecurityConstants;
import com.david.security.core.properties.SecurityProperties;
import com.david.security.core.validate.head.exception.ValidateCodeException;
import com.david.security.core.validate.head.processor.ValidateCodeProcessor;
import com.david.security.core.validate.head.type.ValidateCodeType;
import com.david.security.core.validate.image.code.ImageCode;
import com.david.security.core.validate.sms.code.SmsCode;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @description: 验证码过滤器
 * @author: lingjian @Date: 2019/9/19 10:56
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {
  /** 处理请求失败处理类 */
  @Getter @Setter private AuthenticationFailureHandler authenticationFailureHandler;
  /** session类 */
  private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
  /** url的集合 */
  private Map<String, ValidateCodeType> urlMap = new HashMap<>();
  /** properties的总类 */
  @Getter @Setter private SecurityProperties securityProperties;
  /** 匹配的工具类 */
  private AntPathMatcher pathMatcher = new AntPathMatcher();

  @Override
  public void afterPropertiesSet() throws ServletException {
    super.afterPropertiesSet();

    urlMap.put(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
    addUrlToMap(securityProperties.getValidate().getImage().getUrl(), ValidateCodeType.IMAGE);

    urlMap.put(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
    addUrlToMap(securityProperties.getValidate().getSms().getUrl(), ValidateCodeType.SMS);
  }

  /**
   * 将系统中配置的验证码的url根据校验的类型放入map
   *
   * @param urlString 校验的url
   * @param type 校验类型
   */
  private void addUrlToMap(String urlString, ValidateCodeType type) {
    if (StringUtils.isNotBlank(urlString)) {
      String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
      for (String url : urls) {
        urlMap.put(url, type);
      }
    }
  }

  /**
   * 获取验证码的类型，如果当前请求不需要校验，返回null
   *
   * @param request 请求
   * @return ValidateCodeType
   */
  private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
    ValidateCodeType result = null;
    if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
      Set<String> urls = urlMap.keySet();
      for (String url : urls) {
        if (pathMatcher.match(url, request.getRequestURI())) {
          result = urlMap.get(url);
        }
      }
    }
    return result;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    ValidateCodeType type = getValidateCodeType(request);
    if (type != null) {
      try {
        if (type.equals(ValidateCodeType.IMAGE)) {
          validateImage(new ServletWebRequest(request));
        } else {
          validateSms(new ServletWebRequest(request));
        }
      } catch (ValidateCodeException e) {
        authenticationFailureHandler.onAuthenticationFailure(request, response, e);
        return;
      }
    }
    filterChain.doFilter(request, response);
  }

  /**
   * 图形验证码的检验
   *
   * @param request 请求参数
   * @throws ServletRequestBindingException
   */
  private void validateImage(ServletWebRequest request) throws ServletRequestBindingException {
    ImageCode codeInSession =
        (ImageCode)
            sessionStrategy.getAttribute(
                request, ValidateCodeProcessor.SESSION_KEY_PREFIX + "IMAGE");
    String codeInRequest =
        ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

    if (StringUtils.isBlank(codeInRequest)) {
      throw new ValidateCodeException("验证码的值不能为空");
    }
    if (codeInSession == null) {
      throw new ValidateCodeException("验证码不存在");
    }
    if (codeInSession.isExpried()) {
      sessionStrategy.removeAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX + "IMAGE");
      throw new ValidateCodeException("验证码已过期");
    }
    if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
      throw new ValidateCodeException("验证码不匹配");
    }

    sessionStrategy.removeAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX + "IMAGE");
  }

  /**
   * 短信验证码的检验
   *
   * @param request
   * @throws ServletRequestBindingException
   */
  private void validateSms(ServletWebRequest request) throws ServletRequestBindingException {
    SmsCode codeInSession =
        (SmsCode)
            sessionStrategy.getAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX + "SMS");
    String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "smsCode");

    if (StringUtils.isBlank(codeInRequest)) {
      throw new ValidateCodeException("验证码的值不能为空");
    }
    if (codeInSession == null) {
      throw new ValidateCodeException("验证码不存在");
    }
    if (codeInSession.isExpried()) {
      sessionStrategy.removeAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX + "SMS");
      throw new ValidateCodeException("验证码已过期");
    }
    if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
      throw new ValidateCodeException("验证码不匹配");
    }

    sessionStrategy.removeAttribute(request, ValidateCodeProcessor.SESSION_KEY_PREFIX + "SMS");
  }
}
