package com.david.security.core.validate.generator;

import com.david.security.core.validate.code.ImageCode;

/**
 * @description: 生成验证码的接口
 * @author: lingjian
 * @create: 2019/9/4 9:49
 */
public interface ValidateCodeGenerator {

  ImageCode generator();
}
