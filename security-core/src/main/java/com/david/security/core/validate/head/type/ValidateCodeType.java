package com.david.security.core.validate.head.type;

/**
 * @description: 验证码类型
 * @author: lingjian
 * @create: 2019/9/17 15:31
 */
public enum ValidateCodeType {

  /** 短信验证码 */
  SMS {
    @Override
    public String getParamNameOnValidate() {
      return "DEFAULT_PARAMETER_NAME_CODE_SMS";
    }
  },
  IMAGE {
    @Override
    public String getParamNameOnValidate() {
      return "DEFAULT_PARAMETER_NAME_CODE_IMAGE";
    }
  };

  /**
   * 校验时从请求中获取参数的名字
   *
   * @return 字符串对象
   */
  public abstract String getParamNameOnValidate();
}
