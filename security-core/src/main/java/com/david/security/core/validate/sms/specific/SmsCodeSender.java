package com.david.security.core.validate.sms.specific;

/**
 * @description: 发送短信验证码的接口
 * @author: lingjian
 * @create: 2019/9/4 14:26
 */
public interface SmsCodeSender {

  /**
   * 发送短信验证码
   *
   * @param mobile 手机类型
   * @param code 验证码
   */
  void send(String mobile, String code);
}
