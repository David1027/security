package com.david.security.core.validate.sms.processor;

import com.david.security.core.validate.head.processor.impl.AbstractValidateCodeProcessor;
import com.david.security.core.validate.sms.code.SmsCode;
import com.david.security.core.validate.sms.specific.SmsCodeSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @description: 短信验证码发送
 * @author: lingjian
 * @create: 2019/9/5 10:47
 */
@Component("smsCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<SmsCode> {
  @Autowired private SmsCodeSender smsCodeSender;

  @Override
  protected void send(ServletWebRequest request, SmsCode smsCode) throws Exception {
    String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
    smsCodeSender.send(mobile, smsCode.getCode());
  }
}
