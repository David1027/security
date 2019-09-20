package com.david.security.core.properties.validate;

import com.david.security.core.properties.validate.image.ImageCodeProperties;
import com.david.security.core.properties.validate.sms.SmsCodeProperties;

import lombok.Data;

/**
 * @description: 验证码
 * @author: lingjian
 * @create: 2019/9/3 13:52
 */
@Data
public class ValidateProperties {
  private ImageCodeProperties image = new ImageCodeProperties();
  private SmsCodeProperties sms = new SmsCodeProperties();
}
