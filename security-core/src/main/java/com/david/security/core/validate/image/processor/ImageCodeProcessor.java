package com.david.security.core.validate.image.processor;

import javax.imageio.ImageIO;

import com.david.security.core.validate.head.processor.impl.AbstractValidateCodeProcessor;
import com.david.security.core.validate.image.code.ImageCode;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @description: 图形验证码发送
 * @author: lingjian
 * @create: 2019/9/5 10:57
 */
@Component("imageCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {
  @Override
  protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
    ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
  }
}
