package com.david.security.core.validate.image.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import com.david.security.core.validate.head.code.ValidateCode;

import lombok.Data;

/**
 * @description: 图形验证码
 * @author: lingjian
 * @create: 2019/9/3 9:54
 */
@Data
public class ImageCode extends ValidateCode {

  /** 验证码图片 */
  private BufferedImage image;

  public ImageCode(BufferedImage image, String code, int expireIn) {
    super(code, expireIn);
    this.image = image;
  }

  public ImageCode(BufferedImage image, String code, LocalDateTime expireIn) {
    super(code, expireIn);
    this.image = image;
  }
}
