package com.david.security.core.validate.sms.specific.impl;

import com.david.security.core.validate.sms.specific.SmsCodeSender;

/**
 * @description: 默认短信验证码生成器
 * @author: lingjian
 * @create: 2019/9/4 14:29
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
  @Override
  public void send(String mobile, String code) {
    System.out.println("向手机" + mobile + "发送短信验证码" + code);
  }
}
