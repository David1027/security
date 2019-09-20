package com.david.security.core.properties;

/**
 * @description: 常量配置类
 * @author: lingjian
 * @create: 2019/9/17 15:44
 */
public interface SecurityConstants {
  /** 默认的处理验证码的url前缀 */
  String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";

  /** 当请求需要身份认证时，默认跳转的url */
  String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";

  /** 默认的用户名密码登陆请求处理url */
  String DEFAULT_SIGN_IN_PROCESSING_URL_FORM = "/authentication/form";

  /** 默认的手机验证码登陆请求处理url */
  String DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE = "/authentication/mobile";

  /** 默认登陆页面 */
  String DEFAULT_SIGN_IN_PAGE_URL = "/signIn.html";

  /** 验证图形验证码时，http请求中默认的携带图片验证码信息的参数的名称 */
  String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";

  /** 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称 */
  String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";

  /** 发送短信验证码或验证短信验证码时，传递手机号的参数的名称 */
  String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";
}
