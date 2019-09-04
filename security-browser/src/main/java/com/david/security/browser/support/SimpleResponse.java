package com.david.security.browser.support;

/**
 * @description: 响应返回类
 * @author: lingjian
 * @create: 2019/9/2 15:05
 */
public class SimpleResponse {

  private Object content;

  public SimpleResponse(Object content) {
    this.content = content;
  }

  public Object getContent() {
    return content;
  }

  public void setContent(Object content) {
    this.content = content;
  }
}
