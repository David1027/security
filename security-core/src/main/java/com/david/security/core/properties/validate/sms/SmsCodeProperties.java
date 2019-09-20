package com.david.security.core.properties.validate.sms;

import lombok.Data;

/**
 * @description: 短信验证码配置类
 * @author: lingjian
 * @create: 2019/9/3 13:47
 */
@Data
public class SmsCodeProperties {
  /** 验证码长度 */
  private int length = 6;
  /** 过期时间 */
  private int expireIn = 60;
  /** 请求url */
  private String url;
}
