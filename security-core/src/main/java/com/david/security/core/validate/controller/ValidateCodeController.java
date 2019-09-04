package com.david.security.core.validate.controller;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.david.security.core.validate.code.ImageCode;
import com.david.security.core.validate.generator.ValidateCodeGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @description: 图形验证码控制层
 * @author: lingjian
 * @create: 2019/9/3 10:02
 */
@RestController
public class ValidateCodeController {

  public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

  @Autowired private ValidateCodeGenerator imageCodeGenerator;

  private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

  @GetMapping("/code/image")
  public void createCode(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    // 生成图片验证码
    ImageCode imageCode = imageCodeGenerator.generator();
    // 将验证码放入session中
    sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
    // IO输出流返回前端
    ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
  }
}
