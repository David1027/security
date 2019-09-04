package com.david.security.core.validate.generator.impl;

import com.david.security.core.properties.SecurityProperties;
import com.david.security.core.validate.code.ImageCode;
import com.david.security.core.validate.utils.ImageCodeUtils;
import com.david.security.core.validate.generator.ValidateCodeGenerator;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: 图片验证码生成器
 * @author: lingjian
 * @create: 2019/9/4 9:53
 */
public class ImageCodeGenerator implements ValidateCodeGenerator {

  @Autowired private SecurityProperties securityProperties;

  @Override
  public ImageCode generator() {
    // 生成图片验证码
    return ImageCodeUtils.createImageCode(
        securityProperties.getValidate().getImage().getWidth(),
        securityProperties.getValidate().getImage().getHeight(),
        ImageCodeUtils.getRandomWord(securityProperties.getValidate().getImage().getLength()),
        securityProperties.getValidate().getImage().getExpireIn());
  }
}
