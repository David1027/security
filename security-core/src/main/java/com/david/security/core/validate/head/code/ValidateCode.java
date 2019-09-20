package com.david.security.core.validate.head.code;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description: 验证码
 * @author: lingjian
 * @create: 2019/9/4 14:18
 */
@Data
@AllArgsConstructor
public class ValidateCode {

  /** 验证码 */
  private String code;
  /** 过期时间 */
  private LocalDateTime expireTime;

  public ValidateCode(String code, int expireIn) {
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
