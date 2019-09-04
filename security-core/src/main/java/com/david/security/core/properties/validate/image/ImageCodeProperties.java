package com.david.security.core.properties.validate.image;

import lombok.Data;

/**
 * @description: 图形验证码配置类
 * @author: lingjian
 * @create: 2019/9/3 13:47
 */
@Data
public class ImageCodeProperties {
  /** 宽 */
  private int width = 100;
  /** 高 */
  private int height = 30;
  /** 验证码长度 */
  private int length = 4;
  /** 过期时间 */
  private int expireIn = 60;
  /** 请求url */
  private String url;
}
