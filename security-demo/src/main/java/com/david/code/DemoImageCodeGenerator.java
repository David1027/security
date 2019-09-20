package com.david.code;

import com.david.security.core.validate.image.code.ImageCode;
import com.david.security.core.validate.head.generator.ValidateCodeGenerator;

/**
 * @description: 覆盖验证码生成接口
 * @author: lingjian
 * @create: 2019/9/4 10:27
 */
//@Component("imageValidateCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
  @Override
  public ImageCode generator() {
    System.out.println("更高级的图形验证码生成代码");
    return null;
  }
}
