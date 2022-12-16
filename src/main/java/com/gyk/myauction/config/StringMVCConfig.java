package com.gyk.myauction.config;

import com.gyk.myauction.utils.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 对特定页面拦截过滤
 */
@Configuration
public class StringMVCConfig implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**") //要拦截那些请求
                .excludePathPatterns(
                        "/login","/doLogin","/getCode",
                        "/js/**","/css/**","/images/**"
                )
        ;
    }
}
