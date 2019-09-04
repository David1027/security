package com.david.security.core.properties;

import com.david.security.core.properties.browser.BrowserProperties;
import com.david.security.core.properties.validate.ValidateProperties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description: 安全框架总控制类
 * @author: lingjian
 * @create: 2019/9/2 15:19
 */
@Data
@ConfigurationProperties(prefix = "david.security")
public class SecurityProperties {
  /** 浏览器登陆 */
  private BrowserProperties browser = new BrowserProperties();
  /** 验证码 */
  private ValidateProperties validate = new ValidateProperties();
}
