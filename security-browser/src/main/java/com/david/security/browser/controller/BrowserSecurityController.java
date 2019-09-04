package com.david.security.browser.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.david.security.browser.support.SimpleResponse;
import com.david.security.core.properties.SecurityProperties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 自定义判断方法
 * @author: lingjian
 * @create: 2019/9/2 14:38
 */
@RestController
public class BrowserSecurityController {
  /** 日志 */
  private Logger logger = LoggerFactory.getLogger(getClass());
  /** 请求缓存 */
  private RequestCache requestCache = new HttpSessionRequestCache();
  /** 请求转发 */
  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

  /** 自定义配置类 */
  @Autowired private SecurityProperties securityProperties;

  /**
   * 当需要身份认证时，跳转到此方法
   *
   * @param request 请求参数
   * @param response 响应参数
   * @return String
   */
  @RequestMapping("/authentication/require")
  @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
  public SimpleResponse requireAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws IOException {
    SavedRequest savedRequest = requestCache.getRequest(request, response);

    if (savedRequest != null) {
      String targetUrl = savedRequest.getRedirectUrl();
      logger.info("引发跳转的请求是：" + targetUrl);
      if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
        redirectStrategy.sendRedirect(
            request, response, securityProperties.getBrowser().getLoginPage());
      }
    }
    return new SimpleResponse("访问的服务需要身份认证,请引导用户到登陆页");
  }
}
