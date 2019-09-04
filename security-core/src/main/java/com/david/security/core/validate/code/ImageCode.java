package com.david.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description: 图形验证码
 * @author: lingjian
 * @create: 2019/9/3 9:54
 */
@Data
@AllArgsConstructor
public class ImageCode {

  /** 验证码图片 */
  private BufferedImage image;
  /** 验证码随机数 */
  private String code;
  /** 过期时间 */
  private LocalDateTime expireTime;

  public ImageCode(BufferedImage image, String code, int expireIn) {
    this.image = image;
    this.code = code;
    this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
  }

  /**
   * 验证码是否过期
   *
   * @return boolean
   */
  public boolean isExpried() {
    return LocalDateTime.now().isAfter(expireTime);
  }
}
