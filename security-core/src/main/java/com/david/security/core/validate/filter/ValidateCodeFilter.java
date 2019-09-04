package com.david.security.core.validate.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.david.security.core.properties.SecurityProperties;
import com.david.security.core.validate.code.ImageCode;
import com.david.security.core.validate.controller.ValidateCodeController;
import com.david.security.core.validate.exception.ValidateCodeException;

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
 * @description: 图形验证码过滤器
 * @author: lingjian
 * @create: 2019/9/3 10:39
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {
  /** 处理请求失败处理类 */
  @Getter @Setter private AuthenticationFailureHandler authenticationFailureHandler;
  /** session类 */
  private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
  /** url的集合 */
  private Set<String> urls = new HashSet<>();
  /** properties的总类 */
  @Getter @Setter private SecurityProperties securityProperties;
  /** 匹配的工具类 */
  private AntPathMatcher pathMatcher = new AntPathMatcher();

  @Override
  public void afterPropertiesSet() throws ServletException {
    super.afterPropertiesSet();
    String[] configUrls =
        StringUtils.splitByWholeSeparatorPreserveAllTokens(
            securityProperties.getValidate().getImage().getUrl(), ",");
    for (String configUrl : configUrls) {
      urls.add(configUrl);
    }
    urls.add("/authentication/form");
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    boolean action = false;
    for (String url : urls) {
      if (pathMatcher.match(url, request.getRequestURI())) {
        action = true;
      }
    }

    if (action) {
      try {
        validate(new ServletWebRequest(request));
      } catch (ValidateCodeException e) {
        authenticationFailureHandler.onAuthenticationFailure(request, response, e);
        return;
      }
    }
    filterChain.doFilter(request, response);
  }

  /**
   * 验证码的检验
   *
   * @param request 请求参数
   * @throws ServletRequestBindingException
   */
  private void validate(ServletWebRequest request) throws ServletRequestBindingException {
    ImageCode codeInSession =
        (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);
    String codeInRequest =
        ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

    if (StringUtils.isBlank(codeInRequest)) {
      throw new ValidateCodeException("验证码的值不能为空");
    }
    if (codeInSession == null) {
      throw new ValidateCodeException("验证码不存在");
    }
    if (codeInSession.isExpried()) {
      sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
      throw new ValidateCodeException("验证码已过期");
    }
    if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
      throw new ValidateCodeException("验证码不匹配");
    }

    sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
  }
}
