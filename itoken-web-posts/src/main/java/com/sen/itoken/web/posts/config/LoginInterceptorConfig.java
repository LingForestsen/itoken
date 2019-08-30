package com.sen.itoken.web.posts.config;

import com.sen.itoken.web.posts.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: Sen
 * @Date: 2019/8/26 22:28
 * @Description:
 */
@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {

    @Bean
    public LoginInterceptor loginInterceptor () {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器，指定拦截所有路径除了根目录的/static/下的资源
        registry.addInterceptor(loginInterceptor()).
                addPathPatterns("/**").
                excludePathPatterns("/static/**");
    }
}
