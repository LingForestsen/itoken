package com.sen.itoken.web.backend.config;

import com.sen.itoken.web.backend.interceptor.BackendInterceptor;
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
    public BackendInterceptor backendInterceptor () {
        return new BackendInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器，指定拦截所有路径除了根目录的/static/下的资源
        registry.addInterceptor(backendInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**");
    }
}
