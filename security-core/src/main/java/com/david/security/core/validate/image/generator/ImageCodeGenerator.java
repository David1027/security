package com.david.security.core.validate.image.generator;

import com.david.security.core.properties.SecurityProperties;
import com.david.security.core.validate.head.generator.ValidateCodeGenerator;
import com.david.security.core.validate.image.specific.ImageCodeUtils;
import com.david.security.core.validate.image.code.ImageCode;

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
