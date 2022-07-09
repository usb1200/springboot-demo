package com.xuan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * FileName: CorsConfig.java
 * 类的详细说明
 *
 * @author cmx
 * @version 1.0
 * @Date 2022.06.30
 */

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域请求的域名
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET","POST")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
