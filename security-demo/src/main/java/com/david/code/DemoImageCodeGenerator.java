package com.david.code;

import javax.xml.transform.Source;

import com.david.security.core.validate.code.ImageCode;
import com.david.security.core.validate.generator.ValidateCodeGenerator;

import org.springframework.stereotype.Component;

/**
 * @description: 覆盖验证码生成接口
 * @author: lingjian
 * @create: 2019/9/4 10:27
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
  @Override
  public ImageCode generator() {
    System.out.println("更高级的图形验证码生成代码");
    return null;
  }
}
