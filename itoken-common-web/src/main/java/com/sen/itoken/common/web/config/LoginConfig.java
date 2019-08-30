package com.sen.itoken.common.web.config;

import com.sen.itoken.common.web.interceptor.ConstansInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: Sen
 * @Date: 2019/8/25 17:17
 * @Description: 配置登录常量拦截器
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {
    @Bean
    public ConstansInterceptor constansInterceptor() {
        return new ConstansInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(constansInterceptor()).addPathPatterns("/**");
    }
}
