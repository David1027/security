package com.david.security.core.properties.browser;

import com.david.security.core.enums.LoginType;

import lombok.Data;

/**
 * @description: 浏览器端配置类
 * @author: lingjian
 * @create: 2019/9/2 15:14
 */
@Data
public class BrowserProperties {
  /** 登陆跳转界面 */
  private String loginPage = "/signIn.html";
  /** 登陆跳转类型 */
  private LoginType loginType = LoginType.JSON;
  /** “记住我”过期时间 */
  private int rememberMeSeconds = 3600;
}
