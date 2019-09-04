package com.david.security.core;

        import com.david.security.core.properties.SecurityProperties;

        import org.springframework.boot.context.properties.EnableConfigurationProperties;
        import org.springframework.context.annotation.Configuration;

/**
 * @description: 配置类
 * @author: lingjian
 * @create: 2019/9/2 15:17
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {}
